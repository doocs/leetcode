---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0332.Reconstruct%20Itinerary/README_EN.md
tags:
    - Depth-First Search
    - Graph
    - Eulerian Circuit
---

<!-- problem:start -->

# [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary)

[中文文档](/solution/0300-0399/0332.Reconstruct%20Itinerary/README.md)

## Description

<!-- description:start -->

<p>You are given a list of airline <code>tickets</code> where <code>tickets[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.</p>

<p>All of the tickets belong to a man who departs from <code>&quot;JFK&quot;</code>, thus, the itinerary must begin with <code>&quot;JFK&quot;</code>. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.</p>

<ul>
	<li>For example, the itinerary <code>[&quot;JFK&quot;, &quot;LGA&quot;]</code> has a smaller lexical order than <code>[&quot;JFK&quot;, &quot;LGB&quot;]</code>.</li>
</ul>

<p>You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0332.Reconstruct%20Itinerary/images/itinerary1-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> tickets = [[&quot;MUC&quot;,&quot;LHR&quot;],[&quot;JFK&quot;,&quot;MUC&quot;],[&quot;SFO&quot;,&quot;SJC&quot;],[&quot;LHR&quot;,&quot;SFO&quot;]]
<strong>Output:</strong> [&quot;JFK&quot;,&quot;MUC&quot;,&quot;LHR&quot;,&quot;SFO&quot;,&quot;SJC&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0332.Reconstruct%20Itinerary/images/itinerary2-graph.jpg" style="width: 222px; height: 230px;" />
<pre>
<strong>Input:</strong> tickets = [[&quot;JFK&quot;,&quot;SFO&quot;],[&quot;JFK&quot;,&quot;ATL&quot;],[&quot;SFO&quot;,&quot;ATL&quot;],[&quot;ATL&quot;,&quot;JFK&quot;],[&quot;ATL&quot;,&quot;SFO&quot;]]
<strong>Output:</strong> [&quot;JFK&quot;,&quot;ATL&quot;,&quot;JFK&quot;,&quot;SFO&quot;,&quot;ATL&quot;,&quot;SFO&quot;]
<strong>Explanation:</strong> Another possible reconstruction is [&quot;JFK&quot;,&quot;SFO&quot;,&quot;ATL&quot;,&quot;JFK&quot;,&quot;ATL&quot;,&quot;SFO&quot;] but it is larger in lexical order.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tickets.length &lt;= 300</code></li>
	<li><code>tickets[i].length == 2</code></li>
	<li><code>from<sub>i</sub>.length == 3</code></li>
	<li><code>to<sub>i</sub>.length == 3</code></li>
	<li><code>from<sub>i</sub></code> and <code>to<sub>i</sub></code> consist of uppercase English letters.</li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Eulerian Path

The problem is essentially about finding a path that starts from a specified starting point, passes through all the edges exactly once, and has the smallest lexicographical order among all such paths, given $n$ vertices and $m$ edges. This is a classic Eulerian path problem.

Since the problem guarantees that there is at least one feasible itinerary, we can directly use the Hierholzer algorithm to output the Eulerian path starting from the starting point.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(m)$. Here, $m$ is the number of edges.

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
        auto dfs = [&](auto&& dfs, string& f) -> void {
            while (!g[f].empty()) {
                string t = g[f].back();
                g[f].pop_back();
                dfs(dfs, t);
            }
            ans.emplace_back(f);
        };
        string f = "JFK";
        dfs(dfs, f);
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
