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

遍历矩阵每一行，利用双指针 p, q 进行水平交换翻转，顺便反转图像（1 变 0，0 变 1：`1 ^ 1` = 0，`0 ^ 1` = 1）。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def flipAndInvertImage(self, A: List[List[int]]) -> List[List[int]]:
        m, n = len(A), len(A[0])
        for i in range(m):
            p, q = 0, n - 1
            while p < q:
                t = A[i][p] ^ 1
                A[i][p] = A[i][q] ^ 1
                A[i][q] = t
                p += 1
                q -= 1
            if p == q:
                A[i][p] ^= 1
        return A
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; ++i) {
            int p = 0, q = n - 1;
            while (p < q) {
                int t = A[i][p] ^ 1;
                A[i][p] = A[i][q] ^ 1;
                A[i][q] = t;
                ++p;
                --q;
            }
            if (p == q) {
                A[i][p] ^= 1;
            }
        }
        return A;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> flipAndInvertImage(vector<vector<int>>& A) {
        int m = A.size(), n = A[0].size();
        for (int i = 0; i < m; ++i) {
            int p = 0, q = n - 1;
            while (p < q) {
                int t = A[i][p] ^ 1;
                A[i][p] = A[i][q] ^ 1;
                A[i][q] = t;
                ++p;
                --q;
            }
            if (p == q) {
                A[i][p] ^= 1;
            }
        }
        return A;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
