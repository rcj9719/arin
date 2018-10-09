import image

def color_separator(im):
    if im.getpalette():
        im = im.convert('RGB')

    colors = im.getcolors()
    width, height = im.size
    colors_dict = dict((val[1],image.new('RGB', (width, height), (0,0,0)))
                        for val in colors)
    pix = im.load()
    for i in xrange(width):
        for j in xrange(height):
            colors_dict[pix[i,j]].putpixel((i,j), pix[i,j])
    return colors_dict

im = image.open("colorwheel.tiff")
colors_dict = color_separator(im)
#show the images:
colors_dict.popitem()[1].show()
colors_dict.popitem()[1].show()