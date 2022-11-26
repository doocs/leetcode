# [1857. Largest Color Value in a Directed Graph](https://leetcode.com/problems/largest-color-value-in-a-directed-graph)

[中文文档](/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/README.md)

## Description

<p>There is a <strong>directed graph</strong> of <code>n</code> colored nodes and <code>m</code> edges. The nodes are numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a string <code>colors</code> where <code>colors[i]</code> is a lowercase English letter representing the <strong>color</strong> of the <code>i<sup>th</sup></code> node in this graph (<strong>0-indexed</strong>). You are also given a 2D array <code>edges</code> where <code>edges[j] = [a<sub>j</sub>, b<sub>j</sub>]</code> indicates that there is a <strong>directed edge</strong> from node <code>a<sub>j</sub></code> to node <code>b<sub>j</sub></code>.</p>

<p>A valid <strong>path</strong> in the graph is a sequence of nodes <code>x<sub>1</sub> -&gt; x<sub>2</sub> -&gt; x<sub>3</sub> -&gt; ... -&gt; x<sub>k</sub></code> such that there is a directed edge from <code>x<sub>i</sub></code> to <code>x<sub>i+1</sub></code> for every <code>1 &lt;= i &lt; k</code>. The <strong>color value</strong> of the path is the number of nodes that are colored the <strong>most frequently</strong> occurring color along that path.</p>

<p>Return <em>the <strong>largest color value</strong> of any valid path in the given graph, or </em><code>-1</code><em> if the graph contains a cycle</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet1.png" style="width: 400px; height: 182px;" /></p>

<pre>

<strong>Input:</strong> colors = &quot;abaca&quot;, edges = [[0,1],[0,2],[2,3],[3,4]]

<strong>Output:</strong> 3

<strong>Explanation:</strong> The path 0 -&gt; 2 -&gt; 3 -&gt; 4 contains 3 nodes that are colored <code>&quot;a&quot; (red in the above image)</code>.

</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet2.png" style="width: 85px; height: 85px;" /></p>

<pre>

<strong>Input:</strong> colors = &quot;a&quot;, edges = [[0,0]]

<strong>Output:</strong> -1

<strong>Explanation:</strong> There is a cycle from 0 to 0.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>n == colors.length</code></li>

    <li><code>m == edges.length</code></li>

    <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>

    <li><code>0 &lt;= m &lt;= 10<sup>5</sup></code></li>

    <li><code>colors</code> consists of lowercase English letters.</li>

    <li><code>0 &lt;= a<sub>j</sub>, b<sub>j</sub>&nbsp;&lt; n</code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestPathValue(self, colors: str, edges: List[List[int]]) -> int:
        n = len(colors)
        indeg = [0] * n
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            indeg[b] += 1
        q = deque()
        dp = [[0] * 26 for _ in range(n)]
        for i, v in enumerate(indeg):
            if v == 0:
                q.append(i)
                c = ord(colors[i]) - ord('a')
                dp[i][c] += 1
        cnt = 0
        ans = 1
        while q:
            i = q.popleft()
            cnt += 1
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
                c = ord(colors[j]) - ord('a')
                for k in range(26):
                    dp[j][k] = max(dp[j][k], dp[i][k] + (c == k))
                    ans = max(ans, dp[j][k])
        return -1 if cnt < n else ans
```

### **Java**

```java
class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[][] dp = new int[n][26];
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
                int c = colors.charAt(i) - 'a';
                ++dp[i][c];
            }
        }
        int cnt = 0;
        int ans = 1;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            ++cnt;
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
                int c = colors.charAt(j) - 'a';
                for (int k = 0; k < 26; ++k) {
                    dp[j][k] = Math.max(dp[j][k], dp[i][k] + (c == k ? 1 : 0));
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }
        return cnt == n ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestPathValue(string colors, vector<vector<int>>& edges) {
        int n = colors.size();
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        vector<vector<int>> dp(n, vector<int>(26));
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
                int c = colors[i] - 'a';
                dp[i][c]++;
            }
        }
        int cnt = 0;
        int ans = 1;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            ++cnt;
            for (int j : g[i]) {
                if (--indeg[j] == 0) q.push(j);
                int c = colors[j] - 'a';
                for (int k = 0; k < 26; ++k) {
                    dp[j][k] = max(dp[j][k], dp[i][k] + (c == k));
                    ans = max(ans, dp[j][k]);
                }
            }
        }
        return cnt == n ? ans : -1;
    }
};
```

### **Go**

```go
func largestPathValue(colors string, edges [][]int) int {
	n := len(colors)
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		indeg[b]++
	}
	q := []int{}
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, 26)
	}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
			c := colors[i] - 'a'
			dp[i][c]++
		}
	}
	cnt := 0
	ans := 1
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		cnt++
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
			c := int(colors[j] - 'a')
			for k := 0; k < 26; k++ {
				t := 0
				if c == k {
					t = 1
				}
				dp[j][k] = max(dp[j][k], dp[i][k]+t)
				ans = max(ans, dp[j][k])
			}
		}
	}
	if cnt == n {
		return ans
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
