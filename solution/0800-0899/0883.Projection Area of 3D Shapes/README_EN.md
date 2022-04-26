# [883. Projection Area of 3D Shapes](https://leetcode.com/problems/projection-area-of-3d-shapes)

[中文文档](/solution/0800-0899/0883.Projection%20Area%20of%203D%20Shapes/README.md)

## Description

<p>You are given an <code>n x n</code> <code>grid</code> where we place some <code>1 x 1 x 1</code> cubes that are axis-aligned with the <code>x</code>, <code>y</code>, and <code>z</code> axes.</p>

<p>Each value <code>v = grid[i][j]</code> represents a tower of <code>v</code> cubes placed on top of the cell <code>(i, j)</code>.</p>

<p>We view the projection of these cubes onto the <code>xy</code>, <code>yz</code>, and <code>zx</code> planes.</p>

<p>A <strong>projection</strong> is like a shadow, that maps our <strong>3-dimensional</strong> figure to a <strong>2-dimensional</strong> plane. We are viewing the &quot;shadow&quot; when looking at the cubes from the top, the front, and the side.</p>

<p>Return <em>the total area of all three projections</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0883.Projection%20Area%20of%203D%20Shapes/images/shadow.png" style="width: 800px; height: 214px;" />
<pre>
<strong>Input:</strong> grid = [[1,2],[3,4]]
<strong>Output:</strong> 17
<strong>Explanation:</strong> Here are the three projections (&quot;shadows&quot;) of the shape made with each axis-aligned plane.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[2]]
<strong>Output:</strong> 5
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,2]]
<strong>Output:</strong> 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 50</code></li>
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
function projectionArea(grid: number[][]): number {
    const n = grid.length;
    let res = grid.reduce(
        (r, v) => r + v.reduce((r, v) => r + (v === 0 ? 0 : 1), 0),
        0,
    );
    for (let i = 0; i < n; i++) {
        let xMax = 0;
        let yMax = 0;
        for (let j = 0; j < n; j++) {
            xMax = Math.max(xMax, grid[i][j]);
            yMax = Math.max(yMax, grid[j][i]);
        }
        res += xMax + yMax;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn projection_area(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut res = 0;
        let mut x_max = vec![0; n];
        let mut y_max = vec![0; n];
        for i in 0..n {
            for j in 0..n {
                let val = grid[i][j];
                if val == 0 {
                    continue;
                }
                res += 1;
                x_max[i] = x_max[i].max(val);
                y_max[j] = y_max[j].max(val);
            }
        }
        res + y_max.iter().sum::<i32>() + x_max.iter().sum::<i32>()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
