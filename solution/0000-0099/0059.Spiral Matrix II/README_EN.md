# [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii)

[中文文档](/solution/0000-0099/0059.Spiral%20Matrix%20II/README.md)

## Description

<p>Given a positive integer <code>n</code>, generate an <code>n x n</code> <code>matrix</code> filled with elements from <code>1</code> to <code>n<sup>2</sup></code> in spiral order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0059.Spiral%20Matrix%20II/images/spiraln.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [[1,2,3],[8,9,4],[7,6,5]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        res = [[0] * n for _ in range(n)]
        num = 1
        m1, m2 = 0, n - 1
        while m1 < m2:
            for j in range(m1, m2):
                res[m1][j] = num
                num += 1
            for i in range(m1, m2):
                res[i][m2] = num
                num += 1
            for j in range(m2, m1, -1):
                res[m2][j] = num
                num += 1
            for i in range(m2, m1, -1):
                res[i][m1] = num
                num += 1
            m1 += 1
            m2 -= 1
        if m1 == m2:
            res[m1][m1] = num
        return res
```

### **Java**

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int m1 = 0, m2 = n - 1;
        while (m1 < m2) {
            for (int j = m1; j < m2; ++j) {
                res[m1][j] = num++;
            }
            for (int i = m1; i < m2; ++i) {
                res[i][m2] = num++;
            }
            for (int j = m2; j > m1; --j) {
                res[m2][j] = num++;
            }
            for (int i = m2; i > m1; --i) {
                res[i][m1] = num++;
            }
            ++m1;
            --m2;
        }
        if (m1 == m2) {
            res[m1][m1] = num;
        }

        return res;
    }
}
```

### **TypeScript**

```ts
function generateMatrix(n: number): number[][] {
    let ans = Array.from({ length: n }, v => new Array(n));
    let dir = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    let i = 0, j = 0;
    for (let cnt = 1, k = 0; cnt <= n * n; cnt++) {
        ans[i][j] = cnt;
        let x = i + dir[k][0], y = j + dir[k][1];
        if (x < 0 || x == n || y < 0 || y == n || ans[x][y]) {
            k = (k + 1) % 4;
            x = i + dir[k][0], y = j + dir[k][1];
        }
        i = x, j = y;
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> res(n, vector<int>(n, 0));
        int num = 1;
        int m1 = 0, m2 = n - 1;
        while (m1 < m2) {
            for (int j = m1; j < m2; ++j) {
                res[m1][j] = num++;
            }
            for (int i = m1; i < m2; ++i) {
                res[i][m2] = num++;
            }
            for (int j = m2; j > m1; --j) {
                res[m2][j] = num++;
            }
            for (int i = m2; i > m1; --i) {
                res[i][m1] = num++;
            }
            ++m1;
            --m2;
        }
        if (m1 == m2) {
            res[m1][m1] = num;
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
