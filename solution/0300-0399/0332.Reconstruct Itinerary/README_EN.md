# [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary)

[中文文档](/solution/0300-0399/0332.Reconstruct%20Itinerary/README.md)

<!-- tags:Depth-First Search,Graph,Eulerian Circuit -->

<!-- difficulty:Hard -->

## Description

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

## Solutions

### Solution 1

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

<!-- end -->
