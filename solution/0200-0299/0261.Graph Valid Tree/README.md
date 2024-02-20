# [261. 以图判树](https://leetcode.cn/problems/graph-valid-tree)

[English Version](/solution/0200-0299/0261.Graph%20Valid%20Tree/README_EN.md)

<!-- tags:深度优先搜索,广度优先搜索,并查集,图 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定编号从 <code>0</code> 到 <code>n - 1</code>&nbsp;的&nbsp;<code>n</code> 个结点。给定一个整数&nbsp;<code>n</code>&nbsp;和一个&nbsp;<code>edges</code>&nbsp;列表，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间存在一条无向边。</p>

<p>如果这些边能够形成一个合法有效的树结构，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree1-graph.jpg" /></p>

<pre>
<strong>输入:</strong> <code>n = 5</code>, edges<code> = [[0,1],[0,2],[0,3],[1,4]]</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree2-graph.jpg" /></p>

<pre>
<strong>输入:</strong> <code>n = 5, </code>edges<code> = [[0,1],[1,2],[2,3],[1,3],[1,4]]</code>
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>不存在自循环或重复的边</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for a, b in edges:
            if find(a) == find(b):
                return False
            p[find(a)] = find(b)
            n -= 1
        return n == 1
```

```java
class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) {
                return false;
            }
            p[find(a)] = find(b);
            --n;
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    vector<int> p;

    bool validTree(int n, vector<vector<int>>& edges) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) return 0;
            p[find(a)] = find(b);
            --n;
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

```go
func validTree(n int, edges [][]int) bool {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		if find(a) == find(b) {
			return false
		}
		p[find(a)] = find(b)
		n--
	}
	return n == 1
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    let p = new Array(n);
    for (let i = 0; i < n; ++i) {
        p[i] = i;
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (const [a, b] of edges) {
        if (find(a) == find(b)) {
            return false;
        }
        p[find(a)] = find(b);
        --n;
    }
    return n == 1;
};
```

<!-- tabs:end -->

<!-- end -->
