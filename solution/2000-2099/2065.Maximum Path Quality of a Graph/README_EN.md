# [2065. Maximum Path Quality of a Graph](https://leetcode.com/problems/maximum-path-quality-of-a-graph)

[中文文档](/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README.md)

## Description

<p>There is an <strong>undirected</strong> graph with <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>). You are given a <strong>0-indexed</strong> integer array <code>values</code> where <code>values[i]</code> is the <strong>value </strong>of the <code>i<sup>th</sup></code> node. You are also given a <strong>0-indexed</strong> 2D integer array <code>edges</code>, where each <code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>, time<sub>j</sub>]</code> indicates that there is an undirected edge between the nodes <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code>,<sub> </sub>and it takes <code>time<sub>j</sub></code> seconds to travel between the two nodes. Finally, you are given an integer <code>maxTime</code>.</p>

<p>A <strong>valid</strong> <strong>path</strong> in the graph is any path that starts at node <code>0</code>, ends at node <code>0</code>, and takes <strong>at most</strong> <code>maxTime</code> seconds to complete. You may visit the same node multiple times. The <strong>quality</strong> of a valid path is the <strong>sum</strong> of the values of the <strong>unique nodes</strong> visited in the path (each node&#39;s value is added <strong>at most once</strong> to the sum).</p>

<p>Return <em>the <strong>maximum</strong> quality of a valid path</em>.</p>

<p><strong>Note:</strong> There are <strong>at most four</strong> edges connected to each node.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex1drawio.png" style="width: 269px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
<strong>Output:</strong> 75
<strong>Explanation:</strong>
One possible path is 0 -&gt; 1 -&gt; 0 -&gt; 3 -&gt; 0. The total time taken is 10 + 10 + 10 + 10 = 40 &lt;= 49.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex2drawio.png" style="width: 269px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
<strong>Output:</strong> 25
<strong>Explanation:</strong>
One possible path is 0 -&gt; 3 -&gt; 0. The total time taken is 10 + 10 = 20 &lt;= 30.
The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex31drawio.png" style="width: 236px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
<strong>Output:</strong> 7
<strong>Explanation:</strong>
One possible path is 0 -&gt; 1 -&gt; 3 -&gt; 1 -&gt; 0. The total time taken is 10 + 13 + 13 + 10 = 46 &lt;= 50.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= values[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2000</code></li>
	<li><code>edges[j].length == 3 </code></li>
	<li><code>0 &lt;= u<sub>j </sub>&lt; v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>10 &lt;= time<sub>j</sub>, maxTime &lt;= 100</code></li>
	<li>All the pairs <code>[u<sub>j</sub>, v<sub>j</sub>]</code> are <strong>unique</strong>.</li>
	<li>There are <strong>at most four</strong> edges connected to each node.</li>
	<li>The graph may not be connected.</li>
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
function maximalPathQuality(
    values: number[],
    edges: number[][],
    maxTime: number,
): number {
    const n = values.length;
    let g: Array<Array<Array<number>>> = Array.from(
        { length: n },
        v => new Array(),
    );
    for (let edge of edges) {
        let [u, v, t] = edge;
        g[u].push([v, t]);
        g[v].push([u, t]);
    }
    let visited = new Array(n).fill(false);
    let ans = 0;

    function dfs(u: number, time: number, value: number): void {
        // 索引0为终点
        if (!u) {
            ans = Math.max(value, ans);
        }
        for (let [v, dist] of g[u]) {
            if (time - dist >= 0) {
                if (!visited[v]) {
                    visited[v] = true;
                    dfs(v, time - dist, value + values[v]);
                    visited[v] = false; // 回溯
                } else {
                    dfs(v, time - dist, value);
                }
            }
        }
    }

    // 索引0为起点
    visited[0] = true;
    dfs(0, maxTime, values[0]);

    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
