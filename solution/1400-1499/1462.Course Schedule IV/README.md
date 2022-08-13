# [1462. 课程表 IV](https://leetcode.cn/problems/course-schedule-iv)

[English Version](/solution/1400-1499/1462.Course%20Schedule%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你总共需要上<meta charset="UTF-8" />&nbsp;<code>numCourses</code>&nbsp;门课，课程编号依次为 <code>0</code>&nbsp;到&nbsp;<code>numCourses-1</code>&nbsp;。你会得到一个数组&nbsp;<code>prerequisite</code> ，其中<meta charset="UTF-8" />&nbsp;<code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示如果你想选<meta charset="UTF-8" />&nbsp;<code>b<sub>i</sub></code> 课程，你<strong> 必须</strong> 先选<meta charset="UTF-8" />&nbsp;<code>a<sub>i</sub></code>&nbsp;课程。</p>

<ul>
	<li>有的课会有直接的先修课程，比如如果想上课程 <code>1</code>&nbsp;，你必须先上课程 <code>0</code>&nbsp;，那么会以 <code>[0,1]</code>&nbsp;数对的形式给出先修课程数对。</li>
</ul>

<p>先决条件也可以是 <strong>间接</strong> 的。如果课程 <code>a</code> 是课程 <code>b</code> 的先决条件，课程 <code>b</code> 是课程 <code>c</code> 的先决条件，那么课程 <code>a</code> 就是课程 <code>c</code> 的先决条件。</p>

<p>你也得到一个数组<meta charset="UTF-8" />&nbsp;<code>queries</code>&nbsp;，其中<meta charset="UTF-8" />&nbsp;<code>queries[j] = [u<sub>j</sub>, v<sub>j</sub>]</code>。对于第 <code>j</code> 个查询，您应该回答课程<meta charset="UTF-8" />&nbsp;<code>u<sub>j</sub></code>&nbsp;是否是课程<meta charset="UTF-8" />&nbsp;<code>v<sub>j</sub></code>&nbsp;的先决条件。</p>

<p>返回一个布尔数组 <code>answer</code> ，其中 <code>answer[j]</code> 是第 <code>j</code> 个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-1-graph.jpg" /></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>输出：</strong>[false,true]
<strong>解释：</strong>课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>输出：</strong>[false,false]
<strong>解释：</strong>没有先修课程对，所以每门课程之间是独立的。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/courses4-3-graph.jpg" /></p>

<pre>
<strong>输入：</strong>numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>输出：</strong>[true,true]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>2 &lt;= numCourses &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= (numCourses * (numCourses - 1) / 2)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>每一对<meta charset="UTF-8" />&nbsp;<code>[a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;都 <strong>不同</strong></li>
	<li>先修课程图中没有环。</li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>u<sub>i</sub>&nbsp;!= v<sub>i</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 记忆化搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
