# [2245. Maximum Trailing Zeros in a Cornered Path](https://leetcode.com/problems/maximum-trailing-zeros-in-a-cornered-path)

[中文文档](/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/README.md)

## Description

<p>You are given a 2D integer array <code>grid</code> of size <code>m x n</code>, where each cell contains a positive integer.</p>

<p>A <strong>cornered path</strong> is defined as a set of adjacent cells with <strong>at most</strong> one turn. More specifically, the path should exclusively move either <strong>horizontally</strong> or <strong>vertically</strong> up to the turn (if there is one), without returning to a previously visited cell. After the turn, the path will then move exclusively in the <strong>alternate</strong> direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited cell.</p>

<p>The <strong>product</strong> of a path is defined as the product of all the values in the path.</p>

<p>Return <em>the <strong>maximum</strong> number of <strong>trailing zeros</strong> in the product of a cornered path found in </em><code>grid</code>.</p>

<p>Note:</p>

<ul>
	<li><strong>Horizontal</strong> movement means moving in either the left or right direction.</li>
	<li><strong>Vertical</strong> movement means moving in either the up or down direction.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex1new2.jpg" style="width: 577px; height: 190px;" />
<pre>
<strong>Input:</strong> grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The grid on the left shows a valid cornered path.
It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
It can be shown that this is the maximum trailing zeros in the product of a cornered path.

The grid in the middle is not a cornered path as it has more than one turn.
The grid on the right is not a cornered path as it requires a return to a previously visited cell.

</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2245.Maximum%20Trailing%20Zeros%20in%20a%20Cornered%20Path/images/ex2.jpg" style="width: 150px; height: 157px;" />
<pre>
<strong>Input:</strong> grid = [[4,3,2],[7,6,1],[8,8,8]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The grid is shown in the figure above.
There are no cornered paths in the grid that result in a product with a trailing zero.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function maxTrailingZeros(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let r2 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        c2 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        r5 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        c5 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            let cur = grid[i - 1][j - 1];
            let two = 0,
                five = 0;
            while (cur % 2 == 0) two++, (cur /= 2);
            while (cur % 5 == 0) five++, (cur /= 5);
            r2[i][j] = r2[i - 1][j] + two;
            c2[i][j] = c2[i][j - 1] + two;
            r5[i][j] = r5[i - 1][j] + five;
            c5[i][j] = c5[i][j - 1] + five;
        }
    }
    let ans = 0;
    function getMin(i0, j0, i1, j1, i3, j3, i4, j4): number {
        // 横向开始、结束，竖向开始、结束
        const two = c2[i1][j1] - c2[i0][j0] + r2[i4][j4] - r2[i3][j3];
        const five = c5[i1][j1] - c5[i0][j0] + r5[i4][j4] - r5[i3][j3];
        return Math.min(two, five);
    }
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            const leftToTop = getMin(i, 0, i, j, 0, j, i - 1, j),
                leftToBotton = getMin(i, 0, i, j, i, j, m, j),
                rightToTop = getMin(i, j, i, n, 0, j, i, j),
                rightToBotton = getMin(i, j, i, n, i - 1, j, m, j);
            ans = Math.max(
                leftToTop,
                leftToBotton,
                rightToTop,
                rightToBotton,
                ans,
            );
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
