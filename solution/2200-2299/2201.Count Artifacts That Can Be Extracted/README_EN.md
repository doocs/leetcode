# [2201. Count Artifacts That Can Be Extracted](https://leetcode.com/problems/count-artifacts-that-can-be-extracted)

[中文文档](/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/README.md)

## Description

<p>There is an <code>n x n</code> <strong>0-indexed</strong> grid with some artifacts buried in it. You are given the integer <code>n</code> and a <strong>0-indexed </strong>2D integer array <code>artifacts</code> describing the positions of the rectangular artifacts where <code>artifacts[i] = [r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>]</code> denotes that the <code>i<sup>th</sup></code> artifact is buried in the subgrid where:</p>

<ul>
	<li><code>(r1<sub>i</sub>, c1<sub>i</sub>)</code> is the coordinate of the <strong>top-left</strong> cell of the <code>i<sup>th</sup></code> artifact and</li>
	<li><code>(r2<sub>i</sub>, c2<sub>i</sub>)</code> is the coordinate of the <strong>bottom-right</strong> cell of the <code>i<sup>th</sup></code> artifact.</li>
</ul>

<p>You will excavate some cells of the grid and remove all the mud from them. If the cell has a part of an artifact buried underneath, it will be uncovered. If all the parts of an artifact are uncovered, you can extract it.</p>

<p>Given a <strong>0-indexed</strong> 2D integer array <code>dig</code> where <code>dig[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> indicates that you will excavate the cell <code>(r<sub>i</sub>, c<sub>i</sub>)</code>, return <em>the number of artifacts that you can extract</em>.</p>

<p>The test cases are generated such that:</p>

<ul>
	<li>No two artifacts overlap.</li>
	<li>Each artifact only covers at most <code>4</code> cells.</li>
	<li>The entries of <code>dig</code> are unique.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram.jpg" style="width: 216px; height: 216px;" />
<pre>
<strong>Input:</strong> n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
The different colors represent different artifacts. Excavated cells are labeled with a &#39;D&#39; in the grid.
There is 1 artifact that can be extracted, namely the red artifact.
The blue artifact has one part in cell (1,1) which remains uncovered, so we cannot extract it.
Thus, we return 1.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram-1.jpg" style="width: 216px; height: 216px;" />
<pre>
<strong>Input:</strong> n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1],[1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Both the red and blue artifacts have all parts uncovered (labeled with a &#39;D&#39;) and can be extracted, so we return 2. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= artifacts.length, dig.length &lt;= min(n<sup>2</sup>, 10<sup>5</sup>)</code></li>
	<li><code>artifacts[i].length == 4</code></li>
	<li><code>dig[i].length == 2</code></li>
	<li><code>0 &lt;= r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>, r<sub>i</sub>, c<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>r1<sub>i</sub> &lt;= r2<sub>i</sub></code></li>
	<li><code>c1<sub>i</sub> &lt;= c2<sub>i</sub></code></li>
	<li>No two artifacts will overlap.</li>
	<li>The number of cells covered by an artifact is <strong>at most</strong> <code>4</code>.</li>
	<li>The entries of <code>dig</code> are unique.</li>
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
function digArtifacts(
    n: number,
    artifacts: number[][],
    dig: number[][],
): number {
    let visited = Array.from({ length: n }, v => new Array(n).fill(false));
    for (let [i, j] of dig) {
        visited[i][j] = true;
    }
    let ans = 0;
    for (let [a, b, c, d] of artifacts) {
        let flag = true;
        for (let i = a; i <= c && flag; i++) {
            for (let j = b; j <= d && flag; j++) {
                if (!visited[i][j]) {
                    flag = false;
                }
            }
        }
        flag && ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
