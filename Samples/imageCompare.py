# import the necessary packages
from skimage import measure
import matplotlib.pyplot as plt
import numpy as np
import cv2

mImage1 = cv2.imread("H:/PCCOE/ARIN/P_20180926_115443.jpg")
mImage2 = cv2.imread("H:/PCCOE/ARIN/P_20180926_115443.jpg")
mImage3 = cv2.imread("H:/PCCOE/ARIN/P_20180926_115457.jpg")

mImage4 = cv2.imread("H:/PCCOE/ARIN/References/P_20180815_133345.jpg")
mImage5 = cv2.imread("H:/PCCOE/ARIN/References/IMG_20180602_102958.jpg")


mImage1 = cv2.cvtColor(mImage1, cv2.COLOR_BGR2GRAY)
mImage3 = cv2.cvtColor(mImage3, cv2.COLOR_BGR2GRAY)
mImage4 = cv2.cvtColor(mImage4, cv2.COLOR_BGR2GRAY)
mImage5 = cv2.cvtColor(mImage5, cv2.COLOR_BGR2GRAY)

def mse(imageA, imageB):
    # the 'Mean Squared Error' between the two images is the
    # sum of the squared difference between the two images;
    # NOTE: the two images must have the same dimension
    err = np.sum((imageA.astype("float") - imageB.astype("float")) ** 2)
    err /= float(imageA.shape[0] * imageA.shape[1])

    # return the MSE, the lower the error, the more "similar"
    # the two images are
    return err

def compare_images(imageA, imageB, title):
    # compute the mean squared error and structural similarity
    # index for the images
    m = mse(imageA, imageB)
    s =  measure.compare_ssim(imageA, imageB)

    # setup the figure
    fig = plt.figure(title)
    plt.suptitle("MSE: %.2f, SSIM: %.2f" % (m, s))

    # show first image
    ax = fig.add_subplot(1, 2, 1)
    plt.imshow(imageA, cmap=plt.cm.gray)
    plt.axis("off")

    # show the second image
    ax = fig.add_subplot(1, 2, 2)
    plt.imshow(imageB, cmap=plt.cm.gray)
    plt.axis("off")

    plt.show()

#compare_images(mImage1, mImage1, "Original vs. Original")
#mImage1.resize(mImage4.size, Image.ANTIALIAS)
compare_images(mImage1, mImage3, "Original vs. Contrast")
#compare_images(original, shopped, "Original vs. Photoshopped")