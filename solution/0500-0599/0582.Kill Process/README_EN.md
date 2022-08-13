# [582. Kill Process](https://leetcode.com/problems/kill-process)

[中文文档](/solution/0500-0599/0582.Kill%20Process/README.md)

## Description

<p>You have <code>n</code> processes forming a rooted tree structure. You are given two integer arrays <code>pid</code> and <code>ppid</code>, where <code>pid[i]</code> is the ID of the <code>i<sup>th</sup></code> process and <code>ppid[i]</code> is the ID of the <code>i<sup>th</sup></code> process&#39;s parent process.</p>

<p>Each process has only <strong>one parent process</strong> but may have multiple children processes. Only one process has <code>ppid[i] = 0</code>, which means this process has <strong>no parent process</strong> (the root of the tree).</p>

<p>When a process is <strong>killed</strong>, all of its children processes will also be killed.</p>

<p>Given an integer <code>kill</code> representing the ID of a process you want to kill, return <em>a list of the IDs of the processes that will be killed. You may return the answer in <strong>any order</strong>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0582.Kill%20Process/images/ptree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>Input:</strong> pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
<strong>Output:</strong> [5,10]
<strong>Explanation:</strong>&nbsp;The processes colored in red are the processes that should be killed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> pid = [1], ppid = [0], kill = 1
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == pid.length</code></li>
	<li><code>n == ppid.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= pid[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ppid[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li>Only one process has no parent.</li>
	<li>All the values of <code>pid</code> are <strong>unique</strong>.</li>
	<li><code>kill</code> is <strong>guaranteed</strong> to be in <code>pid</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def killProcess(self, pid: List[int], ppid: List[int], kill: int) -> List[int]:
        def dfs(u):
            ans.append(u)
            for v in g[u]:
                dfs(v)

        g = defaultdict(list)
        n = len(pid)
        for c, p in zip(pid, ppid):
            g[p].append(c)
        ans = []
        dfs(kill)
        return ans
```

### **Java**

```java
class Solution {
    private Map<Integer, List<Integer>> g;
    private List<Integer> ans;

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        g = new HashMap<>();
        for (int i = 0, n = pid.size(); i < n; ++i) {
            int c = pid.get(i), p = ppid.get(i);
            g.computeIfAbsent(p, k -> new ArrayList<>()).add(c);
        }
        ans = new ArrayList<>();
        dfs(kill);
        return ans;
    }

    private void dfs(int u) {
        ans.add(u);
        for (int v : g.getOrDefault(u, new ArrayList<>())) {
            dfs(v);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        unordered_map<int, vector<int>> g;
        vector<int> ans;
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            int c = pid[i], p = ppid[i];
            g[p].push_back(c);
        }
        dfs(kill, g, ans);
        return ans;
    }

    void dfs(int u, unordered_map<int, vector<int>>& g, vector<int>& ans) {
        ans.push_back(u);
        for (int v : g[u]) dfs(v, g, ans);
    }
};
```

### **Go**

```go
func killProcess(pid []int, ppid []int, kill int) []int {
	g := make(map[int][]int)
	for i, c := range pid {
		p := ppid[i]
		g[p] = append(g[p], c)
	}
	var ans []int
	var dfs func(u int)
	dfs = func(u int) {
		ans = append(ans, u)
		for _, v := range g[u] {
			dfs(v)
		}
	}
	dfs(kill)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
