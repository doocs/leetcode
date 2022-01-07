# [1462. 课程表 IV](https://leetcode-cn.com/problems/course-schedule-iv)

[English Version](/solution/1400-1499/1462.Course%20Schedule%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你总共需要上 <code>n</code>&nbsp;门课，课程编号依次为 <code>0</code>&nbsp;到 <code>n-1</code>&nbsp;。</p>

<p>有的课会有直接的先修课程，比如如果想上课程&nbsp;0 ，你必须先上课程 1 ，那么会以 <code>[1,0]</code>&nbsp;数对的形式给出先修课程数对。</p>

<p>给你课程总数 <code>n</code>&nbsp;和一个直接先修课程数对列表&nbsp;<code>prerequisite</code> 和一个查询对列表&nbsp;<code>queries</code>&nbsp;。</p>

<p>对于每个查询对 <code>queries[i]</code>&nbsp;，请判断&nbsp;<code>queries[i][0]</code>&nbsp;是否是&nbsp;<code>queries[i][1]</code>&nbsp;的先修课程。</p>

<p>请返回一个布尔值列表，列表中每个元素依次分别对应 <code>queries</code>&nbsp;每个查询对的判断结果。</p>

<p><strong>注意：</strong>如果课程&nbsp;<strong>a</strong>&nbsp;是课程&nbsp;<strong>b</strong>&nbsp;的先修课程且课程&nbsp;<strong>b</strong>&nbsp;是课程&nbsp;<strong>c</strong>&nbsp;的先修课程，那么课程&nbsp;<strong>a</strong>&nbsp;也是课程&nbsp;<strong>c</strong>&nbsp;的先修课程。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph.png" style="height: 300px; width: 300px;"></p>

<pre><strong>输入：</strong>n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>输出：</strong>[false,true]
<strong>解释：</strong>课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>输出：</strong>[false,false]
<strong>解释：</strong>没有先修课程对，所以每门课程之间是独立的。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph-1.png" style="height: 300px; width: 300px;"></p>

<pre><strong>输入：</strong>n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>输出：</strong>[true,true]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
<strong>输出：</strong>[false,true]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
<strong>输出：</strong>[true,false,true,false]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisite.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= prerequisite[i][0], prerequisite[i][1] &lt; n</code></li>
	<li><code>prerequisite[i][0] != prerequisite[i][1]</code></li>
	<li>先修课程图中没有环。</li>
	<li>先修课程图中没有重复的边。</li>
	<li><code>1 &lt;= queries.length &lt;= 10^4</code></li>
	<li><code>queries[i][0] != queries[i][1]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 记忆化搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
