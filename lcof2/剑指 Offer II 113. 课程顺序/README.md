# [剑指 Offer II 113. 课程顺序](https://leetcode-cn.com/problems/QA2IGt)

## 题目描述

<!-- 这里写题目描述 -->

<p>现在总共有 <code>numCourses</code>&nbsp;门课需要选，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>numCourses-1</code>。</p>

<p>给定一个数组&nbsp;<code>prerequisites</code> ，它的每一个元素&nbsp;<code>prerequisites[i]</code>&nbsp;表示两门课程之间的先修顺序。&nbsp;例如&nbsp;<code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示想要学习课程 <code>a<sub>i</sub></code>&nbsp;，需要先完成课程 <code>b<sub>i</sub></code>&nbsp;。</p>

<p>请根据给出的总课程数 &nbsp;<code>numCourses</code> 和表示先修顺序的&nbsp;<code>prerequisites</code>&nbsp;得出一个可行的修课序列。</p>

<p>可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> numCourses = 2, prerequisites = [[1,0]] 
<strong>输出: </strong><code>[0,1]</code>
<strong>解释:</strong>&nbsp;总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 <code>[0,1] 。</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
<strong>输出: </strong><code>[0,1,2,3] or [0,2,1,3]</code>
<strong>解释:</strong>&nbsp;总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
&nbsp;因此，一个正确的课程顺序是&nbsp;<code>[0,1,2,3]</code> 。另一个正确的排序是&nbsp;<code>[0,2,1,3]</code> 。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> numCourses = 1, prerequisites = [] 
<strong>输出: </strong><code>[0]</code>
<strong>解释:</strong>&nbsp;总共 1 门课，直接修第一门课就可。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= ai, bi &lt; numCourses</code></li>
	<li><code>ai != bi</code></li>
	<li><code>prerequisites</code>&nbsp;中不存在重复元素</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 210&nbsp;题相同：<a href="https://leetcode-cn.com/problems/course-schedule-ii/">https://leetcode-cn.com/problems/course-schedule-ii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

拓扑排序，BFS 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        edges = defaultdict(list)
        indegree = [0] * numCourses
        for a, b in prerequisites:
            edges[b].append(a)
            indegree[a] += 1
        q = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                q.append(i)
        ans = []
        while q:
            b = q.popleft()
            ans.append(b)
            for a in edges[b]:
                indegree[a] -= 1
                if indegree[a] == 0:
                    q.append(a)
        return ans if len(ans) == numCourses else []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            edges[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            edges[b].add(a);
            ++indegree[a];
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int[] ans = new int[numCourses];
        int n = 0;
        while (!q.isEmpty()) {
            int b = q.poll();
            ans[n++] = b;
            for (int a : edges[b]) {
                if (--indegree[a] == 0) {
                    q.offer(a);
                }
            }
        }
        return n == numCourses ? ans : new int[0];
    }
}
```

### **TypeScript**

```ts
function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    let edges = Array.from({ length: numCourses }, () => []);
    let indeg = new Array(numCourses).fill(0);
    for (let [b, a] of prerequisites) {
        edges[a].push(b);
        indeg[b] += 1;
    }

    let queue = [];
    for (let i = 0; i < numCourses; i++) {
        if (!indeg[i]) {
            queue.push(i);
        }
    }

    let ans = [];
    while (queue.length) {
        const u = queue.shift();
        ans.push(u);
        for (let v of edges[u]) {
            indeg[v] -= 1;
            if (!indeg[v]) {
                queue.push(v);
            }
        }
    }
    return ans.length == numCourses ? ans : [];
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> edges(numCourses);
        vector<int> indegree(numCourses);
        for (auto& p : prerequisites)
        {
            int a = p[0], b = p[1];
            edges[b].push_back(a);
            ++indegree[a];
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i)
            if (indegree[i] == 0)
                q.push(i);
        vector<int> ans;
        while (!q.empty())
        {
            int b = q.front();
            q.pop();
            ans.push_back(b);
            for (int a : edges[b])
                if (--indegree[a] == 0)
                    q.push(a);
        }
        return ans.size() == numCourses ? ans : vector<int>();
    }
};
```

### **Go**

```go
func findOrder(numCourses int, prerequisites [][]int) []int {
	edges := make([][]int, numCourses)
	indegree := make([]int, numCourses)
	for _, p := range prerequisites {
		a, b := p[0], p[1]
		edges[b] = append(edges[b], a)
		indegree[a]++
	}
	var q []int
	for i := 0; i < numCourses; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	var ans []int
	for len(q) > 0 {
		b := q[0]
		q = q[1:]
		ans = append(ans, b)
		for _, a := range edges[b] {
			indegree[a]--
			if indegree[a] == 0 {
				q = append(q, a)
			}
		}
	}
	if len(ans) == numCourses {
		return ans
	}
	return []int{}
}
```

### **C#**

```cs
public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var edges = new List<int>[numCourses];
        for (int i = 0; i < numCourses; ++i)
        {
            edges[i] = new List<int>();
        }
        var indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.Length; ++i)
        {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            edges[b].Add(a);
            ++indegree[a];
        }
        var q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i)
        {
            if (indegree[i] == 0) q.Enqueue(i);
        }
        var ans = new int[numCourses];
        var n = 0;
        while (q.Count > 0)
        {
            int b = q.Dequeue();
            ans[n++] = b;
            foreach (int a in edges[b])
            {
                if (--indegree[a] == 0) q.Enqueue(a);
            }
        }
        return n == numCourses ? ans : new int[0];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
