# [2250. Count Number of Rectangles Containing Each Point](https://leetcode.com/problems/count-number-of-rectangles-containing-each-point)

[中文文档](/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/README.md)

## Description

<p>You are given a 2D integer array <code>rectangles</code> where <code>rectangles[i] = [l<sub>i</sub>, h<sub>i</sub>]</code> indicates that <code>i<sup>th</sup></code> rectangle has a length of <code>l<sub>i</sub></code> and a height of <code>h<sub>i</sub></code>. You are also given a 2D integer array <code>points</code> where <code>points[j] = [x<sub>j</sub>, y<sub>j</sub>]</code> is a point with coordinates <code>(x<sub>j</sub>, y<sub>j</sub>)</code>.</p>

<p>The <code>i<sup>th</sup></code> rectangle has its <strong>bottom-left corner</strong> point at the coordinates <code>(0, 0)</code> and its <strong>top-right corner</strong> point at <code>(l<sub>i</sub>, h<sub>i</sub>)</code>.</p>

<p>Return<em> an integer array </em><code>count</code><em> of length </em><code>points.length</code><em> where </em><code>count[j]</code><em> is the number of rectangles that <strong>contain</strong> the </em><code>j<sup>th</sup></code><em> point.</em></p>

<p>The <code>i<sup>th</sup></code> rectangle <strong>contains</strong> the <code>j<sup>th</sup></code> point if <code>0 &lt;= x<sub>j</sub> &lt;= l<sub>i</sub></code> and <code>0 &lt;= y<sub>j</sub> &lt;= h<sub>i</sub></code>. Note that points that lie on the <strong>edges</strong> of a rectangle are also considered to be contained by that rectangle.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/images/example1.png" style="width: 300px; height: 509px;" />
<pre>
<strong>Input:</strong> rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
<strong>Output:</strong> [2,1]
<strong>Explanation:</strong> 
The first rectangle contains no points.
The second rectangle contains only the point (2, 1).
The third rectangle contains the points (2, 1) and (1, 4).
The number of rectangles that contain the point (2, 1) is 2.
The number of rectangles that contain the point (1, 4) is 1.
Therefore, we return [2, 1].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2250.Count%20Number%20of%20Rectangles%20Containing%20Each%20Point/images/example2.png" style="width: 300px; height: 312px;" />
<pre>
<strong>Input:</strong> rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
<strong>Output:</strong> [1,3]
<strong>Explanation:
</strong>The first rectangle contains only the point (1, 1).
The second rectangle contains only the point (1, 1).
The third rectangle contains the points (1, 3) and (1, 1).
The number of rectangles that contain the point (1, 3) is 1.
The number of rectangles that contain the point (1, 1) is 3.
Therefore, we return [1, 3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length, points.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>rectangles[i].length == points[j].length == 2</code></li>
	<li><code>1 &lt;= l<sub>i</sub>, x<sub>j</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= h<sub>i</sub>, y<sub>j</sub> &lt;= 100</code></li>
	<li>All the <code>rectangles</code> are <strong>unique</strong>.</li>
	<li>All the <code>points</code> are <strong>unique</strong>.</li>
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
function countRectangles(rectangles: number[][], points: number[][]): number[] {
    const n = 101;
    let ymap = Array.from({ length: n }, v => []);
    for (let [x, y] of rectangles) {
        ymap[y].push(x);
    }
    for (let nums of ymap) {
        nums.sort((a, b) => a - b);
    }
    let ans = [];
    for (let [x, y] of points) {
        let count = 0;
        for (let h = y; h < n; h++) {
            const nums = ymap[h];
            let left = 0, right = nums.length;
            while (left < right) {
                let mid = (left + right) >> 1;
                if (x > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            count += (nums.length - right);
        }
        ans.push(count);
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
