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

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        graph = defaultdict(list)

        for src, dst in sorted(tickets, reverse=True):
            graph[src].append(dst)

        itinerary = []

        def dfs(airport):
            while graph[airport]:
                dfs(graph[airport].pop())
            itinerary.append(airport)

        dfs("JFK")

        return itinerary[::-1]
```

```java
class Solution {
    void dfs(Map<String, Queue<String>> adjLists, List<String> ans, String curr) {
        Queue<String> neighbors = adjLists.get(curr);
        if (neighbors == null) {
            ans.add(curr);
            return;
        }
        while (!neighbors.isEmpty()) {
            String neighbor = neighbors.poll();
            dfs(adjLists, ans, neighbor);
        }
        ans.add(curr);
        return;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Queue<String>> adjLists = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!adjLists.containsKey(from)) {
                adjLists.put(from, new PriorityQueue<>());
            }
            adjLists.get(from).add(to);
        }
        List<String> ans = new ArrayList<>();
        dfs(adjLists, ans, "JFK");
        Collections.reverse(ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        unordered_map<string, priority_queue<string, vector<string>, greater<string>>> g;
        vector<string> ret;

        // Initialize the graph
        for (const auto& t : tickets) {
            g[t[0]].push(t[1]);
        }

        findItineraryInner(g, ret, "JFK");

        ret = {ret.rbegin(), ret.rend()};

        return ret;
    }

    void findItineraryInner(unordered_map<string, priority_queue<string, vector<string>, greater<string>>>& g, vector<string>& ret, string cur) {
        if (g.count(cur) == 0) {
            // This is the end point
            ret.push_back(cur);
            return;
        } else {
            while (!g[cur].empty()) {
                auto front = g[cur].top();
                g[cur].pop();
                findItineraryInner(g, ret, front);
            }
            ret.push_back(cur);
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
