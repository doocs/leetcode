# [1857. 有向图中最大颜色值](https://leetcode.cn/problems/largest-color-value-in-a-directed-graph)

[English Version](/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有向图</strong> ，它含有 <code>n</code> 个节点和 <code>m</code> 条边。节点编号从 <code>0</code> 到 <code>n - 1</code> 。</p>

<p>给你一个字符串 <code>colors</code> ，其中 <code>colors[i]</code> 是小写英文字母，表示图中第 <code>i</code> 个节点的 <b>颜色</b> （下标从 <strong>0</strong> 开始）。同时给你一个二维数组 <code>edges</code> ，其中 <code>edges[j] = [a<sub>j</sub>, b<sub>j</sub>]</code> 表示从节点 <code>a<sub>j</sub></code> 到节点 <code>b<sub>j</sub></code><sub> </sub>有一条 <strong>有向边</strong> 。</p>

<p>图中一条有效 <strong>路径</strong> 是一个点序列 <code>x<sub>1</sub> -&gt; x<sub>2</sub> -&gt; x<sub>3</sub> -&gt; ... -&gt; x<sub>k</sub></code> ，对于所有 <code>1 &lt;= i &lt; k</code> ，从 <code>x<sub>i</sub></code> 到 <code>x<sub>i+1</sub></code> 在图中有一条有向边。路径的 <strong>颜色值</strong> 是路径中 <strong>出现次数最多</strong> 颜色的节点数目。</p>

<p>请你返回给定图中有效路径里面的 <strong>最大颜色值</strong><strong> 。</strong>如果图中含有环，请返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet1.png" style="width: 400px; height: 182px;"></p>

<pre><b>输入：</b>colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
<b>输出：</b>3
<b>解释：</b>路径 0 -&gt; 2 -&gt; 3 -&gt; 4 含有 3 个颜色为 <code>"a" 的节点（上图中的红色节点）。</code>
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet2.png" style="width: 85px; height: 85px;"></p>

<pre><b>输入：</b>colors = "a", edges = [[0,0]]
<b>输出：</b>-1
<b>解释：</b>从 0 到 0 有一个环。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == colors.length</code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>colors</code> 只含有小写英文字母。</li>
	<li><code>0 &lt;= a<sub>j</sub>, b<sub>j</sub> &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序 + 动态规划**

求出每个点的入度，进行拓扑排序。每个点维护一个长度为 $26$ 的数组，记录每个字母从任意起点到当前点的出现次数。

时间复杂度 $O(n+m)$，空间复杂度 $O(n+m)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
