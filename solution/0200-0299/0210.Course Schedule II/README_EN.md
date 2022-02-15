# [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii)

[中文文档](/solution/0200-0299/0210.Course%20Schedule%20II/README.md)

## Description

<p>There are a total of <code>n</code> courses you have to take labelled from <code>0</code> to <code>n - 1</code>.</p>

<p>Some courses may have <code>prerequisites</code>, for example, if&nbsp;<code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;this means you must take the course <code>b<sub>i</sub></code> before the course <code>a<sub>i</sub></code>.</p>

<p>Given the total number of courses&nbsp;<code>numCourses</code> and a list of the <code>prerequisite</code> pairs, return the ordering of courses you should take to finish all courses.</p>

<p>If there are many valid answers, return <strong>any</strong> of them.&nbsp;If it is impossible to finish all courses, return <strong>an empty array</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
<strong>Output:</strong> [0,2,1,3]
<strong>Explanation:</strong> There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 1, prerequisites = []
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;=&nbsp;prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;&nbsp;numCourses</code></li>
	<li><code>a<sub>i</sub>&nbsp;!=&nbsp;b<sub>i</sub></code></li>
	<li>All the pairs <code>[a<sub>i</sub>, b<sub>i</sub>]</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
