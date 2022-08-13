# [1914. Cyclically Rotating a Grid](https://leetcode.com/problems/cyclically-rotating-a-grid)

[中文文档](/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>​​​, where <code>m</code> and <code>n</code> are both <strong>even</strong> integers, and an integer <code>k</code>.</p>

<p>The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid.png" style="width: 231px; height: 258px;" /></p>

<p>A cyclic rotation of the matrix is done by cyclically rotating <strong>each layer</strong> in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the <strong>counter-clockwise</strong> direction. An example rotation is shown below:</p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/explanation_grid.jpg" style="width: 500px; height: 268px;" />

<p>Return <em>the matrix after applying </em><code>k</code> <em>cyclic rotations to it</em>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/rod2.png" style="width: 421px; height: 191px;" />

<pre>

<strong>Input:</strong> grid = [[40,10],[30,20]], k = 1

<strong>Output:</strong> [[10,20],[40,30]]

<strong>Explanation:</strong> The figures above represent the grid at every state.

</pre>

<p><strong>Example 2:</strong></p>

<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid5.png" style="width: 231px; height: 262px;" /></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid6.png" style="width: 231px; height: 262px;" /></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid7.png" style="width: 231px; height: 262px;" /></strong>

<pre>

<strong>Input:</strong> grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2

<strong>Output:</strong> [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]

<strong>Explanation:</strong> The figures above represent the grid at every state.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>m == grid.length</code></li>
    <li><code>n == grid[i].length</code></li>
    <li><code>2 &lt;= m, n &lt;= 50</code></li>
    <li>Both <code>m</code> and <code>n</code> are <strong>even</strong> integers.</li>
    <li><code>1 &lt;= grid[i][j] &lt;=<sup> </sup>5000</code></li>
    <li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        def rotate(grid, s1, e1, s2, e2, k):
            t = []
            for j in range(e2, e1, -1):
                t.append(grid[s1][j])
            for i in range(s1, s2):
                t.append(grid[i][e1])
            for j in range(e1, e2):
                t.append(grid[s2][j])
            for i in range(s2, s1, -1):
                t.append(grid[i][e2])
            k %= len(t)
            t = t[-k:] + t[:-k]
            k = 0
            for j in range(e2, e1, -1):
                grid[s1][j] = t[k]
                k += 1
            for i in range(s1, s2):
                grid[i][e1] = t[k]
                k += 1
            for j in range(e1, e2):
                grid[s2][j] = t[k]
                k += 1
            for i in range(s2, s1, -1):
                grid[i][e2] = t[k]
                k += 1

        m, n = len(grid), len(grid[0])
        s1 = e1 = 0
        s2, e2 = m - 1, n - 1
        while s1 <= s2 and e1 <= e2:
            rotate(grid, s1, e1, s2, e2, k)
            s1 += 1
            e1 += 1
            s2 -= 1
            e2 -= 1
        return grid
```

### **Java**

```java
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int s1 = 0, e1 = 0;
        int s2 = m - 1, e2 = n - 1;
        while (s1 <= s2 && e1 <= e2) {
            rotate(grid, s1++, e1++, s2--, e2--, k);
        }
        return grid;
    }

    private void rotate(int[][] grid, int s1, int e1, int s2, int e2, int k) {
        List<Integer> t = new ArrayList<>();
        for (int j = e2; j > e1; --j) {
            t.add(grid[s1][j]);
        }
        for (int i = s1; i < s2; ++i) {
            t.add(grid[i][e1]);
        }
        for (int j = e1; j < e2; ++j) {
            t.add(grid[s2][j]);
        }
        for (int i = s2; i > s1; --i) {
            t.add(grid[i][e2]);
        }
        int n = t.size();
        k %= n;
        if (k == 0) {
            return;
        }
        k = n - k;
        for (int j = e2; j > e1; --j) {
            grid[s1][j] = t.get(k);
            k = (k + 1) % n;
        }
        for (int i = s1; i < s2; ++i) {
            grid[i][e1] = t.get(k);
            k = (k + 1) % n;
        }
        for (int j = e1; j < e2; ++j) {
            grid[s2][j] = t.get(k);
            k = (k + 1) % n;
        }
        for (int i = s2; i > s1; --i) {
            grid[i][e2] = t.get(k);
            k = (k + 1) % n;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
