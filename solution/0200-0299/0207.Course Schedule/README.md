# [207. 课程表](https://leetcode-cn.com/problems/course-schedule)

[English Version](/solution/0200-0299/0207.Course%20Schedule/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你这个学期必须选修 <code>numCourses</code> 门课程，记为 <code>0</code> 到 <code>numCourses - 1</code> 。</p>

<p>在选修某些课程之前需要一些先修课程。 先修课程按数组 <code>prerequisites</code> 给出，其中 <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> ，表示如果要学习课程 <code>a<sub>i</sub></code> 则 <strong>必须</strong> 先学习课程  <code>b<sub>i</sub></code><sub> </sub>。</p>

<ul>
	<li>例如，先修课程对 <code>[0, 1]</code> 表示：想要学习课程 <code>0</code> ，你需要先完成课程 <code>1</code> 。</li>
</ul>

<p>请你判断是否可能完成所有课程的学习？如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]]
<strong>输出：</strong>true
<strong>解释：</strong>总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>输出：</strong>false
<strong>解释：</strong>总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= numCourses <= 10<sup>5</sup></code></li>
	<li><code>0 <= prerequisites.length <= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 <= a<sub>i</sub>, b<sub>i</sub> < numCourses</code></li>
	<li><code>prerequisites[i]</code> 中的所有课程对 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

拓扑排序，BFS 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScrpt**

```ts
function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    let edges: number[][] = Array.from({ length: numCourses }, () => []);
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
}
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
