# [832. Flipping an Image](https://leetcode.com/problems/flipping-an-image)

[中文文档](/solution/0800-0899/0832.Flipping%20an%20Image/README.md)

## Description

<p>Given an <code>n x n</code> binary matrix <code>image</code>, flip the image <strong>horizontally</strong>, then invert it, and return <em>the resulting image</em>.</p>

<p>To flip an image horizontally means that each row of the image is reversed.</p>

<ul>
	<li>For example, flipping <code>[1,1,0]</code> horizontally results in <code>[0,1,1]</code>.</li>
</ul>

<p>To invert an image means that each <code>0</code> is replaced by <code>1</code>, and each <code>1</code> is replaced by <code>0</code>.</p>

<ul>
	<li>For example, inverting <code>[0,1,1]</code> results in <code>[1,0,0]</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> image = [[1,1,0],[1,0,1],[0,0,0]]
<strong>Output:</strong> [[1,0,0],[0,1,0],[1,1,1]]
<strong>Explanation:</strong> First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
<strong>Output:</strong> [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
<strong>Explanation:</strong> First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == image.length</code></li>
	<li><code>n == image[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>images[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def flipAndInvertImage(self, image: List[List[int]]) -> List[List[int]]:
        n = len(image)
        for row in image:
            i, j = 0, n - 1
            while i < j:
                if row[i] == row[j]:
                    row[i] ^= 1
                    row[j] ^= 1
                i, j = i + 1, j - 1
            if i == j:
                row[i] ^= 1
        return image
```

### **Java**

```java
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (var row : image) {
            int i = 0, j = row.length - 1;
            for (; i < j; ++i, --j) {
                if (row[i] == row[j]) {
                    row[i] ^= 1;
                    row[j] ^= 1;
                }
            }
            if (i == j) {
                row[i] ^= 1;
            }
        }
        return image;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> flipAndInvertImage(vector<vector<int>>& image) {
        for (auto& row : image) {
            int i = 0, j = row.size() - 1;
            for (; i < j; ++i, --j) {
                if (row[i] == row[j]) {
                    row[i] ^= 1;
                    row[j] ^= 1;
                }
            }
            if (i == j) {
                row[i] ^= 1;
            }
        }
        return image;
    }
};
```

### **Go**

```go
func flipAndInvertImage(image [][]int) [][]int {
	for _, row := range image {
		i, j := 0, len(row)-1
		for ; i < j; i, j = i+1, j-1 {
			if row[i] == row[j] {
				row[i] ^= 1
				row[j] ^= 1
			}
		}
		if i == j {
			row[i] ^= 1
		}
	}
	return image
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} image
 * @return {number[][]}
 */
var flipAndInvertImage = function (image) {
    for (const row of image) {
        let i = 0;
        let j = row.length - 1;
        for (; i < j; ++i, --j) {
            if (row[i] == row[j]) {
                row[i] ^= 1;
                row[j] ^= 1;
            }
        }
        if (i == j) {
            row[i] ^= 1;
        }
    }
    return image;
};
```

### **...**

```

```

<!-- tabs:end -->
