# [1192. 查找集群内的「关键连接」](https://leetcode.cn/problems/critical-connections-in-a-network)

[English Version](/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣数据中心有&nbsp;<code>n</code>&nbsp;台服务器，分别按从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;的方式进行了编号。它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接&nbsp;<code>connections</code> 是无向的。从形式上讲，<code>connections[i] = [a, b]</code>&nbsp;表示服务器 <code>a</code>&nbsp;和 <code>b</code>&nbsp;之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。</p>

<p><em>「关键连接」</em>&nbsp;是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。</p>

<p>请你以任意顺序返回该集群内的所有 「关键连接」。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/images/critical-connections-in-a-network.png" style="height: 205px; width: 200px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
<strong>输出：</strong>[[1,3]]
<strong>解释：</strong>[[3,1]] 也是正确的。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>n = 2, connections = [[0,1]]
<b>输出：</b>[[0,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>n-1 &lt;= connections.length &lt;= 10^5</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
	<li>不存在重复的连接</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：Tarjan 算法**

此题中的「关键连接」即为「桥」。

「桥」：在一连通的无向图中，若去除某一边后会使得图不再连通，则这条边可以视作「桥」。

与之相应的概念还有「割点」。

「割点」：在一连通的无向图中，若去除某一点及所有与其相连的边后会使得图不再连通，则这个点可以视作「割点」。

用于求图中的「桥」与「割点」有一算法：tarjan 算法，这个算法使用先递归的访问相邻节点后访问节点自身的 dfs 方法，通过记录「访问的顺序：DFN」以及在递归结束后访问节点自身时探索其可以回溯到的最早被访问的节点来更新「最早可回溯的节点：low」，可以实现在 O(n)时间内找到图的「桥」与「割点」。同时，此种算法可以用于查找有向图中的强连通分量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp
class Solution {
public:
    int count = 0;
    vector<int> dfn, low;
    vector<vector<int>> graph;
    vector<vector<int>> res;
    void tarjan(int u, int fa) {
        dfn[u] = low[u] = ++count;
        for (auto& v : graph[u]) {
            if (v == fa)
                continue;
            if (!dfn[v]) {
                tarjan(v, u);
                low[u] = min(low[u], low[v]);
                if (dfn[u] < low[v])
                    res.push_back({u, v});
            } else {
                low[u] = min(dfn[v], low[u]);
            }
        }
    }

    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        dfn.resize(n);
        low.resize(n);
        graph.resize(n);
        for (auto& edge : connections) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!dfn[i])
                tarjan(i, -1);
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
