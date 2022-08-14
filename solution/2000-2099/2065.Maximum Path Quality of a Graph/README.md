# [2065. 最大化一张图中的路径价值](https://leetcode.cn/problems/maximum-path-quality-of-a-graph)

[English Version](/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一张 <strong>无向</strong>&nbsp;图，图中有 <code>n</code>&nbsp;个节点，节点编号从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;（<strong>都包括</strong>）。同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>values</code>&nbsp;，其中&nbsp;<code>values[i]</code>&nbsp;是第 <code>i</code>&nbsp;个节点的 <strong>价值</strong>&nbsp;。同时给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>, time<sub>j</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>j</sub></code> 和&nbsp;<code>v<sub>j</sub></code>&nbsp;之间有一条需要&nbsp;<code>time<sub>j</sub></code>&nbsp;秒才能通过的无向边。最后，给你一个整数&nbsp;<code>maxTime</code>&nbsp;。</p>

<p><strong>合法路径</strong>&nbsp;指的是图中任意一条从节点&nbsp;<code>0</code>&nbsp;开始，最终回到节点 <code>0</code>&nbsp;，且花费的总时间 <strong>不超过</strong>&nbsp;<code>maxTime</code> 秒的一条路径。你可以访问一个节点任意次。一条合法路径的 <b>价值</b>&nbsp;定义为路径中 <strong>不同节点</strong>&nbsp;的价值 <strong>之和</strong>&nbsp;（每个节点的价值 <strong>至多</strong>&nbsp;算入价值总和中一次）。</p>

<p>请你返回一条合法路径的 <strong>最大</strong>&nbsp;价值。</p>

<p><strong>注意：</strong>每个节点 <strong>至多</strong>&nbsp;有 <strong>四条</strong>&nbsp;边与之相连。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex1drawio.png" style="width: 269px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
<b>输出：</b>75
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 1 -&gt; 0 -&gt; 3 -&gt; 0 。总花费时间为 10 + 10 + 10 + 10 = 40 &lt;= 49 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex2drawio.png" style="width: 269px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
<b>输出：</b>25
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 3 -&gt; 0 。总花费时间为 10 + 10 = 20 &lt;= 30 。
访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex31drawio.png" style="width: 236px; height: 170px;" /></p>

<pre>
<b>输入：</b>values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
<b>输出：</b>7
<strong>解释：</strong>
一条可能的路径为：0 -&gt; 1 -&gt; 3 -&gt; 1 -&gt; 0 。总花费时间为 10 + 13 + 13 + 10 = 46 &lt;= 50 。
访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。</pre>

<p><strong>示例 4：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex4drawio.png" style="width: 270px; height: 71px;" /></strong></p>

<pre>
<b>输入：</b>values = [0,1,2], edges = [[1,2,10]], maxTime = 10
<b>输出：</b>0
<b>解释：</b>
唯一一条路径为 0 。总花费时间为 0 。
唯一访问过的节点为 0 ，最大路径价值为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= values[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2000</code></li>
	<li><code>edges[j].length == 3 </code></li>
	<li><code>0 &lt;= u<sub>j </sub>&lt; v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>10 &lt;= time<sub>j</sub>, maxTime &lt;= 100</code></li>
	<li><code>[u<sub>j</sub>, v<sub>j</sub>]</code>&nbsp;所有节点对 <strong>互不相同</strong>&nbsp;。</li>
	<li>每个节点 <strong>至多有四条&nbsp;</strong>边。</li>
	<li>图可能不连通。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
