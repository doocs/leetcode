# [207. Course Schedule](https://leetcode.com/problems/course-schedule)

[中文文档](/solution/0200-0299/0207.Course%20Schedule/README.md)

## Description

<p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        edges = defaultdict(list)
        indegree = [0] * numCourses
        for a, b in prerequisites:
            edges[b].append(a)
            indegree[a] += 1
        q = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                q.append(i)
        n = 0
        while q:
            b = q.popleft()
            n += 1
            for a in edges[b]:
                indegree[a] -= 1
                if indegree[a] == 0:
                    q.append(a)
        return n == numCourses
```

### **Java**

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        int n = 0;
        while (!q.isEmpty()) {
            int b = q.poll();
            ++n;
            for (int a : edges[b]) {
                if (--indegree[a] == 0) {
                    q.offer(a);
                }
            }
        }
        return n == numCourses;
    }
}
```

### **TypeScript**

```ts
function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    let edges: number[][] = Array.from({ length: numCourses }, () => ([]));
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

    let visited: number = 0;
    while (queue.length) {
        visited += 1;
        const u = queue.shift();
        for (let v of edges[u]) {
            indeg[v] -= 1;
            if (!indeg[v]) {
                queue.push(v);
            }
        }
    }
    return visited == numCourses;
};
```

### **C++**

```cpp
class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
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
        int n = 0;
        while (!q.empty())
        {
            int b = q.front();
            q.pop();
            ++n;
            for (int a : edges[b])
                if (--indegree[a] == 0)
                    q.push(a);
        }
        return n == numCourses;
    }
};
```

### **Go**

```go
func canFinish(numCourses int, prerequisites [][]int) bool {
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
	n := 0
	for len(q) > 0 {
		b := q[0]
		q = q[1:]
		n++
		for _, a := range edges[b] {
			indegree[a]--
			if indegree[a] == 0 {
				q = append(q, a)
			}
		}
	}
	return n == numCourses
}
```

### **C#**

```cs
public class Solution {
    public bool CanFinish(int numCourses, int[][] prerequisites) {
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
        var n = 0;
        while (q.Count > 0)
        {
            int b = q.Dequeue();
            ++n;
            foreach (int a in edges[b])
            {
                if (--indegree[a] == 0) q.Enqueue(a);
            }
        }
        return n == numCourses;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
