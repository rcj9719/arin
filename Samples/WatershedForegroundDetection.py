import numpy as np
import cv2
from matplotlib import pyplot as plt

img = cv2.imread('H:/PCCOE/ARIN/References/P_20180926_133612.jpg')
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(gray,0,255,cv2.THRESH_BINARY_INV+cv2.THRESH_OTSU)

# noise removal
kernel = np.ones((3,3),np.uint8)
opening = cv2.morphologyEx(thresh,cv2.MORPH_OPEN,kernel, iterations = 2)

# sure background area
sure_bg = cv2.dilate(opening,kernel,iterations=3)

# Finding sure foreground area
dist_transform = cv2.distanceTransform(opening,cv2.DIST_L2,5)
ret, sure_fg = cv2.threshold(dist_transform,0.7*dist_transform.max(),255,0)

# Finding unknown region
sure_fg = np.uint8(sure_fg)
unknown = cv2.subtract(sure_bg,sure_fg)

# Marker labelling
ret, markers = cv2.connectedComponents(sure_fg)

# Add one to all labels so that sure background is not 0, but 1
markers = markers+1

# Now, mark the region of unknown with zero
markers[unknown==255] = 0

markers = cv2.watershed(img,markers)
img[markers == -1] = [255,0,0]

plt.subplot(2,3,1),plt.imshow(img,cmap = 'gray')
plt.title('Original'), plt.xticks([]), plt.yticks([])
plt.subplot(2,3,2),plt.imshow(gray,cmap = 'gray')
plt.title('Grey'), plt.xticks([]), plt.yticks([])
plt.subplot(2,3,3),plt.imshow(sure_bg,cmap = 'gray')
plt.title('Bkground'), plt.xticks([]), plt.yticks([])
plt.subplot(2,3,4),plt.imshow(sure_fg,cmap = 'gray')
plt.title('Foreground'), plt.xticks([]), plt.yticks([])
plt.subplot(2,3,5),plt.imshow(markers,cmap = 'gray')
plt.title('Marker'), plt.xticks([]), plt.yticks([])

plt.show()