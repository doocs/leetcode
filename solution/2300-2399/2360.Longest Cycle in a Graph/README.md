# [2360. 图中的最长环](https://leetcode.cn/problems/longest-cycle-in-a-graph)

[English Version](/solution/2300-2399/2360.Longest%20Cycle%20in%20a%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的 <b>有向图</b>&nbsp;，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，其中每个节点&nbsp;<strong>至多</strong>&nbsp;有一条出边。</p>

<p>图用一个大小为 <code>n</code>&nbsp;下标从<strong>&nbsp;0</strong>&nbsp;开始的数组&nbsp;<code>edges</code>&nbsp;表示，节点 <code>i</code>&nbsp;到节点&nbsp;<code>edges[i]</code>&nbsp;之间有一条有向边。如果节点&nbsp;<code>i</code>&nbsp;没有出边，那么&nbsp;<code>edges[i] == -1</code>&nbsp;。</p>

<p>请你返回图中的 <strong>最长</strong>&nbsp;环，如果没有任何环，请返回 <code>-1</code>&nbsp;。</p>

<p>一个环指的是起点和终点是 <strong>同一个</strong>&nbsp;节点的路径。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2360.Longest%20Cycle%20in%20a%20Graph/images/graph4drawio-5.png" style="width: 335px; height: 191px;" /></p>

<pre>
<b>输入：</b>edges = [3,3,4,2,3]
<b>输出去：</b>3
<b>解释：</b>图中的最长环是：2 -&gt; 4 -&gt; 3 -&gt; 2 。
这个环的长度为 3 ，所以返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2360.Longest%20Cycle%20in%20a%20Graph/images/graph4drawio-1.png" style="width: 171px; height: 161px;" /></p>

<pre>
<b>输入：</b>edges = [2,-1,3,1]
<b>输出：</b>-1
<b>解释：</b>图中没有任何环。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

从 $[0, n)$ 遍历出发点，记录途经的点，如果遇到已经访问过的点，说明找到了环的入口。减去起始点到出发点的距离，就是环的长度。求最大的环长度。

时间复杂度 $O(n)$。

相似题目：[2127. 参加会议的最多员工数](/solution/2100-2199/2127.Maximum%20Employees%20to%20Be%20Invited%20to%20a%20Meeting/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestCycle(self, edges: List[int]) -> int:
        n = len(edges)
        vis = [False] * n
        ans = -1
        for i in range(n):
            if vis[i]:
                continue
            curr = i
            cycle = []
            while curr != -1 and not vis[curr]:
                vis[curr] = True
                cycle.append(curr)
                curr = edges[curr]
            if curr == -1:
                continue
            for j, v in enumerate(cycle):
                if v == curr:
                    ans = max(ans, len(cycle) - j)
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] vis = new boolean[n];
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            int curr = i;
            List<Integer> cycle = new ArrayList<>();
            while (curr != -1 && !vis[curr]) {
                cycle.add(curr);
                vis[curr] = true;
                curr = edges[curr];
            }
            if (curr == -1) {
                continue;
            }
            for (int j = 0; j < cycle.size(); ++j) {
                if (cycle.get(j) == curr) {
                    ans = Math.max(ans, cycle.size() - j);
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestCycle(vector<int>& edges) {
        int n = edges.size();
        vector<int> vis(n);
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            int curr = i;
            vector<int> cycle;
            while (curr != -1 && !vis[curr]) {
                cycle.push_back(curr);
                vis[curr] = true;
                curr = edges[curr];
            }
            if (curr == -1) continue;
            for (int j = 0; j < cycle.size(); ++j) {
                if (cycle[j] == curr) {
                    ans = max(ans, (int)cycle.size() - j);
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestCycle(edges []int) int {
	n := len(edges)
	vis := make([]bool, n)
	ans := -1
	for i := range edges {
		if vis[i] {
			continue
		}
		curr := i
		cycle := []int{}
		for curr != -1 && !vis[curr] {
			cycle = append(cycle, curr)
			vis[curr] = true
			curr = edges[curr]
		}
		if curr == -1 {
			continue
		}
		for j, v := range cycle {
			if v == curr {
				ans = max(ans, len(cycle)-j)
				break
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
