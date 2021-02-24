# [832. Flipping an Image](https://leetcode.com/problems/flipping-an-image)

[中文文档](/solution/0800-0899/0832.Flipping%20an%20Image/README.md)

## Description

<p>Given a binary matrix <code>A</code>, we want to flip the image horizontally, then invert it, and return the resulting image.</p>

<p>To flip an image horizontally means that each row of the image is reversed.&nbsp; For example, flipping&nbsp;<code>[1, 1, 0]</code>&nbsp;horizontally results in&nbsp;<code>[0, 1, 1]</code>.</p>

<p>To invert an image means&nbsp;that each <code>0</code> is replaced by <code>1</code>, and each <code>1</code> is replaced by <code>0</code>.&nbsp;For example, inverting&nbsp;<code>[0, 1, 1]</code>&nbsp;results in&nbsp;<code>[1, 0, 0]</code>.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[[1,1,0],[1,0,1],[0,0,0]]

<strong>Output: </strong>[[1,0,0],[0,1,0],[1,1,1]]

<strong>Explanation:</strong> First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].

Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]

<strong>Output: </strong>[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

<strong>Explanation:</strong> First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].

Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

</pre>

<p><strong>Notes:</strong></p>

<ul>
    <li><code>1 &lt;= A.length = A[0].length &lt;= 20</code></li>
    <li><code>0 &lt;= A[i][j]<font face="sans-serif, Arial, Verdana, Trebuchet MS">&nbsp;&lt;=&nbsp;</font>1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
