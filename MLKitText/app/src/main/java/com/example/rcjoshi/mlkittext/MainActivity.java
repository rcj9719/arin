package com.example.rcjoshi.mlkittext;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.TextBlock;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //int rotation = getRotationCompensation("CameraID",)
        CameraManager cameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        ImageView mImageView = (ImageView) findViewById(R.id.imageViewid);
        Button mCapture = (Button) findViewById(R.id.capturebtnid);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE);
        }
            Drawable myDrawable = getResources().getDrawable(R.drawable.location1);
            Bitmap bitmap = ((BitmapDrawable) myDrawable).getBitmap();

            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
            FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance()
                    .getOnDeviceTextRecognizer();

            textRecognizer.processImage(image)
                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                        @Override
                        public void onSuccess(FirebaseVisionText result) {
                            // Task completed successfully
                            // ...
                            String resultText = result.getText();
                            for (FirebaseVisionText.TextBlock block : result.getTextBlocks()) {
                                String blockText = block.getText();
                                Float blockConfidence = block.getConfidence();
                                List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                                Point[] blockCornerPoints = block.getCornerPoints();
                                Rect blockFrame = block.getBoundingBox();
                                for (FirebaseVisionText.Line line : block.getLines()) {
                                    String lineText = line.getText();
                                    Float lineConfidence = line.getConfidence();
                                    List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                                    Point[] lineCornerPoints = line.getCornerPoints();
                                    Rect lineFrame = line.getBoundingBox();
                                    for (FirebaseVisionText.Element element : line.getElements()) {
                                        String elementText = element.getText();
                                        Toast.makeText(getApplicationContext(), elementText, Toast.LENGTH_SHORT).show();
                                        Float elementConfidence = element.getConfidence();
                                        List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                                        Point[] elementCornerPoints = element.getCornerPoints();
                                        Rect elementFrame = element.getBoundingBox();
                                    }
                                }
                            }
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Task failed with an exception
                                    // ...
                                    Toast.makeText(getApplicationContext(),"InFailure",Toast.LENGTH_SHORT).show();
                                }
                            });
        Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();


        /*
        mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        try {
            mImageView.setImageResource(R.drawable.Loc1);
            z.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
            Image mediaImage = (Image) getDrawable(R.drawable.Loc1);
            int rotation;
            String cameraId = getBackFacingCameraId(cameraManager);
            rotation = getRotationCompensation(cameraId,this,getApplicationContext());
            FirebaseVisionImage image = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);
        } catch (CameraAccessException e) {
            Toast.makeText(this, "Cannot access the camera.", Toast.LENGTH_SHORT).show();
            this.finish();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.");
        }
        */

    }

    /*

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    String getBackFacingCameraId(CameraManager cManager) throws CameraAccessException {
        for(final String cameraId : cManager.getCameraIdList()){
            CameraCharacteristics characteristics = cManager.getCameraCharacteristics(cameraId);
            int cOrientation = characteristics.get(CameraCharacteristics.LENS_FACING);
            if(cOrientation == CameraCharacteristics.LENS_FACING_BACK) return cameraId;
        }
        return null;
    }
    */
     //Get the angle by which an image must be rotated given the device's current orientation.
    /*
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int getRotationCompensation(String cameraId, Activity activity, Context context)
            throws CameraAccessException {
        // Get the device's current rotation relative to its "native" orientation.
        // Then, from the ORIENTATIONS table, look up the angle the image must be
        // rotated to compensate for the device's rotation.
        int deviceRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int rotationCompensation = ORIENTATIONS.get(deviceRotation);

        // On most devices, the sensor orientation is 90 degrees, but for some
        // devices it is 270 degrees. For devices with a sensor orientation of
        // 270, rotate the image an additional 180 ((270 + 270) % 360) degrees.
        CameraManager cameraManager = (CameraManager) context.getSystemService(CAMERA_SERVICE);
        int sensorOrientation = cameraManager
                .getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SENSOR_ORIENTATION);
        rotationCompensation = (rotationCompensation + sensorOrientation + 270) % 360;

        // Return the corresponding FirebaseVisionImageMetadata rotation value.
        int result;
        switch (rotationCompensation) {
            case 0:
                result = FirebaseVisionImageMetadata.ROTATION_0;
                break;
            case 90:
                result = FirebaseVisionImageMetadata.ROTATION_90;
                break;
            case 180:
                result = FirebaseVisionImageMetadata.ROTATION_180;
                break;
            case 270:
                result = FirebaseVisionImageMetadata.ROTATION_270;
                break;
            default:
                result = FirebaseVisionImageMetadata.ROTATION_0;
                Log.e("", "Bad rotation value: " + rotationCompensation);
        }
        return result;
    }
    */
}
