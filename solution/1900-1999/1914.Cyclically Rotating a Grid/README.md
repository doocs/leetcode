# [1914. 循环轮转矩阵](https://leetcode.cn/problems/cyclically-rotating-a-grid)

[English Version](/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>grid</code>​​​ ，其中 <code>m</code> 和 <code>n</code> 都是 <strong>偶数</strong> ；另给你一个整数 <code>k</code> 。</p>

<p>矩阵由若干层组成，如下图所示，每种颜色代表一层：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid.png" style="width: 231px; height: 258px;"></p>

<p>矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 <strong>逆时针 </strong>方向的相邻元素。轮转示例如下：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/explanation_grid.jpg" style="width: 500px; height: 268px;">
<p>返回执行 <code>k</code> 次循环轮转操作后的矩阵。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/rod2.png" style="width: 421px; height: 191px;">
<pre><strong>输入：</strong>grid = [[40,10],[30,20]], k = 1
<strong>输出：</strong>[[10,20],[40,30]]
<strong>解释：</strong>上图展示了矩阵在执行循环轮转操作时每一步的状态。</pre>

<p><strong>示例 2：</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid5.png" style="width: 231px; height: 262px;"></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid6.png" style="width: 231px; height: 262px;"></strong> <strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1914.Cyclically%20Rotating%20a%20Grid/images/ringofgrid7.png" style="width: 231px; height: 262px;"></strong>

<pre><strong>输入：</strong>grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
<strong>输出：</strong>[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
<strong>解释：</strong>上图展示了矩阵在执行循环轮转操作时每一步的状态。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>m</code> 和 <code>n</code> 都是 <strong>偶数</strong></li>
	<li><code>1 &lt;= grid[i][j] &lt;=<sup> </sup>5000</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
