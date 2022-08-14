# [2246. Longest Path With Different Adjacent Characters](https://leetcode.com/problems/longest-path-with-different-adjacent-characters)

[中文文档](/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/README.md)

## Description

<p>You are given a <strong>tree</strong> (i.e. a connected, undirected graph that has no cycles) <strong>rooted</strong> at node <code>0</code> consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. The tree is represented by a <strong>0-indexed</strong> array <code>parent</code> of size <code>n</code>, where <code>parent[i]</code> is the parent of node <code>i</code>. Since node <code>0</code> is the root, <code>parent[0] == -1</code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code>, where <code>s[i]</code> is the character assigned to node <code>i</code>.</p>

<p>Return <em>the length of the <strong>longest path</strong> in the tree such that no pair of <strong>adjacent</strong> nodes on the path have the same character assigned to them.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/testingdrawio.png" style="width: 201px; height: 241px;" />
<pre>
<strong>Input:</strong> parent = [-1,0,0,1,1,2], s = &quot;abacbe&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -&gt; 1 -&gt; 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions. 
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/graph2drawio.png" style="width: 201px; height: 221px;" />
<pre>
<strong>Input:</strong> parent = [-1,0,0,0], s = &quot;aabc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest path where each two adjacent nodes have different characters is the path: 2 -&gt; 0 -&gt; 3. The length of this path is 3, so 3 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i &gt;= 1</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li><code>s</code> consists of only lowercase English letters.</li>
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
function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    let graph = Array.from({ length: n }, v => []);
    for (let i = 1; i < n; i++) {
        graph[parent[i]].push(i);
    }
    let ans = 0;
    function dfs(x: number): number {
        let maxLen = 0;
        for (let y of graph[x]) {
            let len = dfs(y) + 1;
            if (s.charAt(x) !== s.charAt(y)) {
                ans = Math.max(maxLen + len, ans);
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
    dfs(0);
    return ans + 1;
}
```

### **...**

```

```

<!-- tabs:end -->
