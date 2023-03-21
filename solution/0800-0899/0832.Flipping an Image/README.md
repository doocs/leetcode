# [832. 翻转图像](https://leetcode.cn/problems/flipping-an-image)

[English Version](/solution/0800-0899/0832.Flipping%20an%20Image/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<meta charset="UTF-8" />&nbsp;<code>n x n</code>&nbsp;的二进制矩阵&nbsp;<code>image</code>&nbsp;，先 <strong>水平</strong> 翻转图像，然后&nbsp;<strong>反转&nbsp;</strong>图像并返回&nbsp;<em>结果</em>&nbsp;。</p>

<p><strong>水平</strong>翻转图片就是将图片的每一行都进行翻转，即逆序。</p>

<ul>
	<li>例如，水平翻转&nbsp;<code>[1,1,0]</code>&nbsp;的结果是&nbsp;<code>[0,1,1]</code>。</li>
</ul>

<p><strong>反转</strong>图片的意思是图片中的&nbsp;<code>0</code>&nbsp;全部被&nbsp;<code>1</code>&nbsp;替换，&nbsp;<code>1</code>&nbsp;全部被&nbsp;<code>0</code>&nbsp;替换。</p>

<ul>
	<li>例如，反转&nbsp;<code>[0,1,1]</code>&nbsp;的结果是&nbsp;<code>[1,0,0]</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>image = [[1,1,0],[1,0,1],[0,0,0]]
<strong>输出：</strong>[[1,0,0],[0,1,0],[1,1,1]]
<strong>解释：</strong>首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
<strong>输出：</strong>[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
<strong>解释：</strong>首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>n == image.length</code></li>
	<li><code>n == image[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>images[i][j]</code>&nbsp;==&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以遍历矩阵，对于遍历到的每一行 $row$：

我们使用双指针 $i$ 和 $j$ 分别指向该行的首尾元素，如果 $row[i] = row[j]$，交换后两者的值仍然保持不变，因此，我们只需要对 $row[i]$ 和 $row[j]$ 进行异或反转即可，然后将 $i$ 和 $j$ 分别向中间移动一位，直到 $i \geq j$。如果 $row[i] \neq row[j]$，此时交换后再反转两者的值，仍然保持不变，因此，可以不进行任何操作。

最后，如果 $i = j$，我们直接对 $row[i]$ 进行反转即可。

时间复杂度 $O(n^2)$，其中 $n$ 是矩阵的行数或列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
