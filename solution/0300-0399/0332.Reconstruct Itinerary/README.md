---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0332.Reconstruct%20Itinerary/README.md
tags:
    - 深度优先搜索
    - 图
    - 欧拉回路
---

<!-- problem:start -->

# [332. 重新安排行程](https://leetcode.cn/problems/reconstruct-itinerary)

[English Version](/solution/0300-0399/0332.Reconstruct%20Itinerary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一份航线列表 <code>tickets</code> ，其中 <code>tickets[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。</p>

<p>所有这些机票都属于一个从 <code>JFK</code>（肯尼迪国际机场）出发的先生，所以该行程必须从 <code>JFK</code> 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。</p>

<ul>
	<li>例如，行程 <code>["JFK", "LGA"]</code> 与 <code>["JFK", "LGB"]</code> 相比就更小，排序更靠前。</li>
</ul>

<p>假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0332.Reconstruct%20Itinerary/images/itinerary1-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>输入：</strong>tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
<strong>输出：</strong>["JFK","MUC","LHR","SFO","SJC"]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0332.Reconstruct%20Itinerary/images/itinerary2-graph.jpg" style="width: 222px; height: 230px;" />
<pre>
<strong>输入：</strong>tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
<strong>输出：</strong>["JFK","ATL","JFK","SFO","ATL","SFO"]
<strong>解释：</strong>另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= tickets.length <= 300</code></li>
	<li><code>tickets[i].length == 2</code></li>
	<li><code>from<sub>i</sub>.length == 3</code></li>
	<li><code>to<sub>i</sub>.length == 3</code></li>
	<li><code>from<sub>i</sub></code> 和 <code>to<sub>i</sub></code> 由大写英文字母组成</li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：欧拉路径

题目实际上是给定 $n$ 个点和 $m$ 条边，要求从指定的起点出发，经过所有的边恰好一次，使得路径字典序最小。这是一个典型的欧拉路径问题。

由于本题保证了至少存在一种合理的行程，因此，我们直接利用 Hierholzer 算法，输出从起点出发的欧拉路径即可。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 是边的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        def dfs(f: str):
            while g[f]:
                dfs(g[f].pop())
            ans.append(f)

        g = defaultdict(list)
        for f, t in sorted(tickets, reverse=True):
            g[f].append(t)
        ans = []
        dfs("JFK")
        return ans[::-1]
```

#### Java

```java
class Solution {
    private Map<String, List<String>> g = new HashMap<>();
    private List<String> ans = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> b.get(1).compareTo(a.get(1)));
        for (List<String> ticket : tickets) {
            g.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        }
        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(String f) {
        while (g.containsKey(f) && !g.get(f).isEmpty()) {
            String t = g.get(f).remove(g.get(f).size() - 1);
            dfs(t);
        }
        ans.add(f);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        sort(tickets.rbegin(), tickets.rend());
        unordered_map<string, vector<string>> g;
        for (const auto& ticket : tickets) {
            g[ticket[0]].push_back(ticket[1]);
        }
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, string& f) -> void {
            while (!g[f].empty()) {
                string t = g[f].back();
                g[f].pop_back();
                dfs(t);
            }
            ans.emplace_back(f);
        };
        string f = "JFK";
        dfs(f);
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func findItinerary(tickets [][]string) (ans []string) {
	sort.Slice(tickets, func(i, j int) bool {
		return tickets[i][0] > tickets[j][0] || (tickets[i][0] == tickets[j][0] && tickets[i][1] > tickets[j][1])
	})
	g := make(map[string][]string)
	for _, ticket := range tickets {
		g[ticket[0]] = append(g[ticket[0]], ticket[1])
	}
	var dfs func(f string)
	dfs = func(f string) {
		for len(g[f]) > 0 {
			t := g[f][len(g[f])-1]
			g[f] = g[f][:len(g[f])-1]
			dfs(t)
		}
		ans = append(ans, f)
	}
	dfs("JFK")
	for i := 0; i < len(ans)/2; i++ {
		ans[i], ans[len(ans)-1-i] = ans[len(ans)-1-i], ans[i]
	}
	return
}
```

#### TypeScript

```ts
function findItinerary(tickets: string[][]): string[] {
    const g: Record<string, string[]> = {};
    tickets.sort((a, b) => b[1].localeCompare(a[1]));
    for (const [f, t] of tickets) {
        g[f] = g[f] || [];
        g[f].push(t);
    }
    const ans: string[] = [];
    const dfs = (f: string) => {
        while (g[f] && g[f].length) {
            const t = g[f].pop()!;
            dfs(t);
        }
        ans.push(f);
    };
    dfs('JFK');
    return ans.reverse();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
