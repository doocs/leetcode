# [733. Flood Fill](https://leetcode.com/problems/flood-fill)

## Description
<p>
An <code>image</code> is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
</p><p>
Given a coordinate <code>(sr, sc)</code> representing the starting pixel (row and column) of the flood fill, and a pixel value <code>newColor</code>, "flood fill" the image.
</p><p>
To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on.  Replace the color of all of the aforementioned pixels with the newColor.
</p><p>
At the end, return the modified image.
</p>
<p><b>Example 1:</b><br />
<pre>
<b>Input:</b> 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
<b>Output:</b> [[2,2,2],[2,2,0],[2,0,1]]
<b>Explanation:</b> 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
</pre>
</p>

<p><b>Note:</b>
<li>The length of <code>image</code> and <code>image[0]</code> will be in the range <code>[1, 50]</code>.</li>
<li>The given starting pixel will satisfy <code>0 <= sr < image.length</code> and <code>0 <= sc < image[0].length</code>.</li>
<li>The value of each color in <code>image[i][j]</code> and <code>newColor</code> will be an integer in <code>[0, 65535]</code>.</li>
</p>


## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
