# [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv)

[中文文档](/solution/1400-1499/1462.Course%20Schedule%20IV/README.md)

## Description

<p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>a<sub>i</sub></code> first if you want to take course <code>b<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code> indicates that you have to take course <code>0</code> before you can take course <code>1</code>.</li>
</ul>

<p>Prerequisites can also be <strong>indirect</strong>. If course <code>a</code> is a prerequisite of course <code>b</code>, and course <code>b</code> is a prerequisite of course <code>c</code>, then course <code>a</code> is a prerequisite of course <code>c</code>.</p>

<p>You are also given an array <code>queries</code> where <code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>. For the <code>j<sup>th</sup></code> query, you should answer whether course <code>u<sub>j</sub></code> is a prerequisite of course <code>v<sub>j</sub></code> or not.</p>

<p>Return <i>a boolean array </i><code>answer</code><i>, where </i><code>answer[j]</code><i> is the answer to the </i><code>j<sup>th</sup></code><i> query.</i></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-1-graph.jpg" style="width: 222px; height: 62px;" />
<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>Output:</strong> [false,false]
<strong>Explanation:</strong> There are no prerequisites, and each course is independent.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-3-graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>Output:</strong> [true,true]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numCourses &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= (numCourses * (numCourses - 1) / 2)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>All the pairs <code>[a<sub>i</sub>, b<sub>i</sub>]</code> are <strong>unique</strong>.</li>
	<li>The prerequisites graph has no cycles.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
</ul>

## Solutions

<!-- tabs:start -->

DFS.

### **Python3**

```python
class Solution:
    def checkIfPrerequisite(
        self, numCourses: int, prerequisites: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        @cache
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
    public List<Boolean> checkIfPrerequisite(
        int numCourses, int[][] prerequisites, int[][] queries) {
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
        for (auto& e : prerequisites) {
            int a = e[0], b = e[1];
            g[a][b] = 1;
        }
        vector<bool> ans;
        for (auto& e : queries) {
            int a = e[0], b = e[1];
            ans.push_back(dfs(a, b, g));
        }
        return ans;
    }

    bool dfs(int a, int b, vector<vector<int>>& g) {
        if (g[a][b] != -1) return g[a][b] == 1;
        if (a == b) {
            g[a][b] = 1;
            return true;
        }
        for (int i = 0; i < g[a].size(); ++i) {
            if (g[a][i] == 1 && dfs(i, b, g)) {
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
