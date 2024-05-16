---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/README.md
rating: 2209
source: 第 365 场周赛 Q4
tags:
    - 图
    - 记忆化搜索
    - 动态规划
---

# [2876. 有向图访问计数](https://leetcode.cn/problems/count-visited-nodes-in-a-directed-graph)

[English Version](/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个有向图，其中包含 <code>n</code> 个节点，节点编号从 <code>0</code> 到 <code>n - 1</code> 。此外，该图还包含了 <code>n</code> 条有向边。</p>

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>edges</code> ，其中 <code>edges[i]</code> 表示存在一条从节点 <code>i</code> 到节点 <code>edges[i]</code> 的边。</p>

<p>想象在图上发生以下过程：</p>

<ul>
	<li>你从节点 <code>x</code> 开始，通过边访问其他节点，直到你在<strong> 此过程 </strong>中再次访问到之前已经访问过的节点。</li>
</ul>

<p>返回数组 <code>answer</code> 作为答案，其中 <code>answer[i]</code> 表示如果从节点 <code>i</code> 开始执行该过程，你可以访问到的不同节点数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaphdrawio-1.png" />
<pre>
<strong>输入：</strong>edges = [1,2,0,0]
<strong>输出：</strong>[3,3,3,4]
<strong>解释：</strong>从每个节点开始执行该过程，记录如下：
- 从节点 0 开始，访问节点 0 -&gt; 1 -&gt; 2 -&gt; 0 。访问的不同节点数是 3 。
- 从节点 1 开始，访问节点 1 -&gt; 2 -&gt; 0 -&gt; 1 。访问的不同节点数是 3 。
- 从节点 2 开始，访问节点 2 -&gt; 0 -&gt; 1 -&gt; 2 。访问的不同节点数是 3 。
- 从节点 3 开始，访问节点 3 -&gt; 0 -&gt; 1 -&gt; 2 -&gt; 0 。访问的不同节点数是 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2876.Count%20Visited%20Nodes%20in%20a%20Directed%20Graph/images/graaph2drawio.png" style="width: 191px; height: 251px;" />
<pre>
<strong>输入：</strong>edges = [1,2,3,4,0]
<strong>输出：</strong>[5,5,5,5,5]
<strong>解释：</strong>无论从哪个节点开始，在这个过程中，都可以访问到图中的每一个节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges[i] &lt;= n - 1</code></li>
	<li><code>edges[i] != i</code></li>
</ul>

## 解法

### 方法一：基环树 + 遍历搜索

我们可以用一个数组 $ans$ 记录每个节点的答案，用一个数组 $vis$ 记录每个节点的访问次序。

遍历每个节点 $i$，如果当前节点 $i$ 未被访问，我们就从节点 $i$ 开始遍历，此时有两种情况：

1. 如果遍历过程中，遇到了当前节点出发时走过的节点，那么此次遍历，一定是先走到了环内，然后沿着环走了一圈。对于环外的节点，其答案就是环的长度加上节点到环的距离；对于环内的节点，其答案就是环的长度。
1. 如果遍历过程中，遇到了此前节点出发时走过的节点，那么对于每个走过的节点，其答案就是当前节点到此节点的距离，加上此节点的答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $edges$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def countVisitedNodes(self, edges: List[int]) -> List[int]:
        n = len(edges)
        ans = [0] * n
        vis = [0] * n
        for i in range(n):
            if not ans[i]:
                cnt, j = 0, i
                while not vis[j]:
                    cnt += 1
                    vis[j] = cnt
                    j = edges[j]
                cycle, total = 0, cnt + ans[j]
                if not ans[j]:
                    cycle = cnt - vis[j] + 1
                    total = cnt
                j = i
                while not ans[j]:
                    ans[j] = max(total, cycle)
                    total -= 1
                    j = edges[j]
        return ans
```

```java
class Solution {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] ans = new int[n];
        int[] vis = new int[n];
        for (int i = 0; i < n; ++i) {
            if (ans[i] == 0) {
                int cnt = 0, j = i;
                while (vis[j] == 0) {
                    vis[j] = ++cnt;
                    j = edges.get(j);
                }
                int cycle = 0, total = cnt + ans[j];
                if (ans[j] == 0) {
                    cycle = cnt - vis[j] + 1;
                }
                j = i;
                while (ans[j] == 0) {
                    ans[j] = Math.max(total--, cycle);
                    j = edges.get(j);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countVisitedNodes(vector<int>& edges) {
        int n = edges.size();
        vector<int> ans(n), vis(n);
        for (int i = 0; i < n; ++i) {
            if (!ans[i]) {
                int cnt = 0, j = i;
                while (vis[j] == 0) {
                    vis[j] = ++cnt;
                    j = edges[j];
                }
                int cycle = 0, total = cnt + ans[j];
                if (ans[j] == 0) {
                    cycle = cnt - vis[j] + 1;
                }
                j = i;
                while (ans[j] == 0) {
                    ans[j] = max(total--, cycle);
                    j = edges[j];
                }
            }
        }
        return ans;
    }
};
```

```go
func countVisitedNodes(edges []int) []int {
	n := len(edges)
	ans := make([]int, n)
	vis := make([]int, n)
	for i := range ans {
		if ans[i] == 0 {
			cnt, j := 0, i
			for vis[j] == 0 {
				cnt++
				vis[j] = cnt
				j = edges[j]
			}
			cycle, total := 0, cnt+ans[j]
			if ans[j] == 0 {
				cycle = cnt - vis[j] + 1
			}
			j = i
			for ans[j] == 0 {
				ans[j] = max(total, cycle)
				total--
				j = edges[j]
			}
		}
	}
	return ans
}
```

```ts
function countVisitedNodes(edges: number[]): number[] {
    const n = edges.length;
    const ans: number[] = Array(n).fill(0);
    const vis: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        if (ans[i] === 0) {
            let [cnt, j] = [0, i];
            while (vis[j] === 0) {
                vis[j] = ++cnt;
                j = edges[j];
            }
            let [cycle, total] = [0, cnt + ans[j]];
            if (ans[j] === 0) {
                cycle = cnt - vis[j] + 1;
            }
            j = i;
            while (ans[j] === 0) {
                ans[j] = Math.max(total--, cycle);
                j = edges[j];
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```java
class Solution {
    void dfs(int curr, List<Integer> edges, int[] ans) {

        List<Integer> path = new ArrayList<>();
        int prev = -1;
        while (ans[curr] == 0) {
            path.add(curr);
            ans[curr] = prev == -1 ? -1 : ans[prev] - 1;
            prev = curr;
            curr = edges.get(curr);
        }
        int idx = path.size() - 1;
        if (ans[curr] < 0) {
            int cycle = ans[curr] - ans[path.get(idx)] + 1;
            int start = ans[curr];
            for (; idx >= 0 && ans[path.get(idx)] <= start; idx--) {
                ans[path.get(idx)] = cycle;
            }
        }
        for (; idx >= 0; idx--) {
            ans[path.get(idx)] = ans[edges.get(path.get(idx))] + 1;
        }
    }

    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (ans[i] > 0) {
                continue;
            }
            dfs(i, edges, ans);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
