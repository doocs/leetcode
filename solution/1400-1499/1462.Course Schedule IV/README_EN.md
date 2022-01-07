# [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv)

[中文文档](/solution/1400-1499/1462.Course%20Schedule%20IV/README.md)

## Description

<p>There are a total of <code>n</code> courses you have to take, labeled from <code>0</code> to <code>n-1</code>.</p>

<p>Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: <code>[1,0]</code></p>

<p>Given the total number of courses <code>n</code>,&nbsp;a list of direct&nbsp;<code>prerequisite</code> <strong>pairs</strong> and a list of <code>queries</code> <strong>pairs</strong>.</p>

<p>You should answer for each <code>queries[i]</code> whether the course <code>queries[i][0]</code> is a&nbsp;prerequisite of the course&nbsp;<code>queries[i][1]</code> or not.</p>

<p>Return <em>a list of boolean</em>, the answers to the given <code>queries</code>.</p>

<p>Please note that if course <strong>a</strong> is a prerequisite of course <strong>b</strong> and course <strong>b</strong> is a prerequisite&nbsp;of course <strong>c</strong>, then, course <strong>a</strong> is a&nbsp;prerequisite of course <strong>c</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> course 0 is not a prerequisite of course 1 but the opposite is true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>Output:</strong> [false,false]
<strong>Explanation:</strong> There are no prerequisites and each course is independent.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph-1.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>Output:</strong> [true,true]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
<strong>Output:</strong> [false,true]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
<strong>Output:</strong> [true,false,true,false]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisite.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= prerequisite[i][0], prerequisite[i][1] &lt; n</code></li>
	<li><code>prerequisite[i][0] != prerequisite[i][1]</code></li>
	<li>The prerequisites graph has no cycles.</li>
	<li>The prerequisites graph has no repeated edges.</li>
	<li><code>1 &lt;= queries.length &lt;= 10^4</code></li>
	<li><code>queries[i][0] != queries[i][1]</code></li>
</ul>

## Solutions

<!-- tabs:start -->

DFS.

### **Python3**

```python
class Solution:
    def checkIfPrerequisite(self, numCourses: int, prerequisites: List[List[int]], queries: List[List[int]]) -> List[bool]:
        @lru_cache(None)
        def dfs(a, b):
            if b in g[a] or a == b:
                return True
            for c in g[a]:
                if dfs(c, b):
                    return True
            return False

        g = defaultdict(set)
        for a, b in prerequisites:
            g[a].add(b)
        return [dfs(a, b) for a, b in queries]
```

### **Java**

```java
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] g = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; ++i) {
            Arrays.fill(g[i], -1);
        }
        for (int[] e : prerequisites) {
            int a = e[0], b = e[1];
            g[a][b] = 1;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] e : queries) {
            int a = e[0], b = e[1];
            ans.add(dfs(a, b, g));
        }
        return ans;
    }

    private boolean dfs(int a, int b, int[][] g) {
        if (g[a][b] != -1) {
            return g[a][b] == 1;
        }
        if (a == b) {
            g[a][b] = 1;
            return true;
        }
        for (int i = 0; i < g[a].length; ++i) {
            if (g[a][i] == 1 && dfs(i, b, g)) {
                g[a][b] = 1;
                return true;
            }
        }
        g[a][b] = 0;
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> checkIfPrerequisite(int numCourses, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        vector<vector<int>> g(numCourses, vector<int>(numCourses, -1));
        for (auto& e : prerequisites)
        {
            int a = e[0], b = e[1];
            g[a][b] = 1;
        }
        vector<bool> ans;
        for (auto& e : queries)
        {
            int a = e[0], b = e[1];
            ans.push_back(dfs(a, b, g));
        }
        return ans;
    }

    bool dfs(int a, int b, vector<vector<int>>& g) {
        if (g[a][b] != -1) return g[a][b] == 1;
        if (a == b)
        {
            g[a][b] = 1;
            return true;
        }
        for (int i = 0; i < g[a].size(); ++i)
        {
            if (g[a][i] == 1 && dfs(i, b, g))
            {
                g[a][b] = 1;
                return true;
            }
        }
        g[a][b] = 0;
        return false;
    }
};
```

### **Go**

```go
func checkIfPrerequisite(numCourses int, prerequisites [][]int, queries [][]int) []bool {
	g := make([][]int, numCourses)
	for i := range g {
		g[i] = make([]int, numCourses)
		for j := range g[i] {
			g[i][j] = -1
		}
	}
	for _, e := range prerequisites {
		a, b := e[0], e[1]
		g[a][b] = 1
	}
	var ans []bool
	var dfs func(a, b int) bool
	dfs = func(a, b int) bool {
		if g[a][b] != -1 {
			return g[a][b] == 1
		}
		if a == b {
			g[a][b] = 1
			return true
		}
		for i, c := range g[a] {
			if c == 1 && dfs(i, b) {
				g[a][b] = 1
				return true
			}
		}
		g[a][b] = 0
		return false
	}
	for _, e := range queries {
		a, b := e[0], e[1]
		ans = append(ans, dfs(a, b))
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
