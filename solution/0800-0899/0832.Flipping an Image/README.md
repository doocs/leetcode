# [832. 翻转图像](https://leetcode-cn.com/problems/flipping-an-image)

[English Version](/solution/0800-0899/0832.Flipping%20an%20Image/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个二进制矩阵&nbsp;<code>A</code>，我们想先水平翻转图像，然后反转图像并返回结果。</p>

<p>水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转&nbsp;<code>[1, 1, 0]</code>&nbsp;的结果是&nbsp;<code>[0, 1, 1]</code>。</p>

<p>反转图片的意思是图片中的&nbsp;<code>0</code>&nbsp;全部被&nbsp;<code>1</code>&nbsp;替换，&nbsp;<code>1</code>&nbsp;全部被&nbsp;<code>0</code>&nbsp;替换。例如，反转&nbsp;<code>[0, 1, 1]</code>&nbsp;的结果是&nbsp;<code>[1, 0, 0]</code>。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>[[1,1,0],[1,0,1],[0,0,0]]
<strong>输出: </strong>[[1,0,0],[0,1,0],[1,1,1]]
<strong>解释:</strong> 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
<strong>输出: </strong>[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
<strong>解释:</strong> 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
</pre>

<p><strong>说明:</strong></p>

<ul>
	<li><code>1 &lt;= A.length = A[0].length &lt;= 20</code></li>
	<li><code>0 &lt;= A[i][j]&nbsp;&lt;=&nbsp;1</code></li>
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
