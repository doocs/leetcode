# [1273. 删除树节点](https://leetcode.cn/problems/delete-tree-nodes)

[English Version](/solution/1200-1299/1273.Delete%20Tree%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵以节点 0 为根节点的树，定义如下：</p>

<ul>
	<li>节点的总数为&nbsp;<code>nodes</code>&nbsp;个；</li>
	<li>第&nbsp;<code>i</code> 个节点的值为&nbsp;<code>value[i]</code>&nbsp;；</li>
	<li>第&nbsp;<code>i</code> 个节点的父节点是&nbsp;<code>parent[i]</code>&nbsp;。</li>
</ul>

<p>请你删除节点值之和为 0 的每一棵子树。</p>

<p>在完成所有删除之后，返回树中剩余节点的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="height: 347px; width: 403px;"></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
<strong>输出：</strong>6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,1,0,0], value = [-672,441,18,728,378]
<strong>输出：</strong>5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,0,1,1], value = [-686,-842,616,-739,-746]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nodes &lt;= 10^4</code></li>
	<li><code>parent.length == nodes</code></li>
	<li><code>0 &lt;= parent[i] &lt;= nodes - 1</code></li>
	<li><code>parent[0] == -1</code>&nbsp;表示节点 <code>0</code> 是树的根。</li>
	<li><code>value.length == nodes</code></li>
	<li><code>-10^5 &lt;= value[i] &lt;= 10^5</code></li>
	<li>题目输入数据 <strong>保证</strong> 是一棵 <strong>有效的树</strong> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先构建图。

然后对于树中的每一个节点 u，我们通过深度优先搜索的方法，递归地搜索它的所有子节点 v，计算出以 v 为根的子树的节点数目和权值之和。在这之后，我们将子节点的值分别进行累加，就可以得到以 u 为根的子树的节点数目和权值之和。如果权值之和为零，那么以 u 为根的子树需要被删除，我们将其节点数目也置为零，作为结果返回到上一层。最终根节点 0 对应的节点数目即为答案。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        def dfs(u):
            for v in g[u]:
                dfs(v)
                value[u] += value[v]
                counter[u] += counter[v]
            if value[u] == 0:
                counter[u] = 0

        g = defaultdict(list)
        for i, p in enumerate(parent):
            if p != -1:
                g[p].append(i)
        counter = [1] * nodes
        dfs(0)
        return counter[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] counter;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new HashMap<>();
        for (int i = 0; i < nodes; ++i) {
            if (parent[i] != -1) {
                g.computeIfAbsent(parent[i], k -> new ArrayList<>()).add(i);
            }
        }
        this.value = value;
        counter = new int[nodes];
        Arrays.fill(counter, 1);
        dfs(0);
        return counter[0];
    }

    private void dfs(int u) {
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) {
            counter[u] = 0;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, vector<int>> g;
    vector<int> counter;
    vector<int> value;

    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        for (int i = 0; i < nodes; ++i)
            if (parent[i] != -1)
                g[parent[i]].push_back(i);
        counter.resize(nodes, 1);
        this->value = value;
        dfs(0);
        return counter[0];
    }

    void dfs(int u) {
        for (int v : g[u]) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) counter[u] = 0;
    }
};
```

### **Go**

```go
func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make(map[int][]int)
	for i, p := range parent {
		if p != -1 {
			g[p] = append(g[p], i)
		}
	}
	counter := make([]int, nodes)
	for i := range counter {
		counter[i] = 1
	}
	var dfs func(u int)
	dfs = func(u int) {
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v)
				value[u] += value[v]
				counter[u] += counter[v]
			}
		}
		if value[u] == 0 {
			counter[u] = 0
		}
	}
	dfs(0)
	return counter[0]
}
```

### **...**

```

```

<!-- tabs:end -->
