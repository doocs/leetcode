# [210. 课程表 II](https://leetcode-cn.com/problems/course-schedule-ii)

[English Version](/solution/0200-0299/0210.Course%20Schedule%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现在你总共有 <em>n</em> 门课需要选，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>。</p>

<p>在选修某些课程之前需要一些先修课程。&nbsp;例如，想要学习课程 0 ，你需要先完成课程&nbsp;1 ，我们用一个匹配来表示他们: <code>[0,1]</code></p>

<p>给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。</p>

<p>可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 2, [[1,0]] 
<strong>输出: </strong><code>[0,1]</code>
<strong>解释:</strong>&nbsp;总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 <code>[0,1] 。</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 4, [[1,0],[2,0],[3,1],[3,2]]
<strong>输出: </strong><code>[0,1,2,3] or [0,2,1,3]</code>
<strong>解释:</strong>&nbsp;总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
&nbsp;    因此，一个正确的课程顺序是&nbsp;<code>[0,1,2,3]</code> 。另一个正确的排序是&nbsp;<code>[0,2,1,3]</code> 。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>输入的先决条件是由<strong>边缘列表</strong>表示的图形，而不是邻接矩阵。详情请参见<a href="http://blog.csdn.net/woaidapaopao/article/details/51732947" target="_blank">图的表示法</a>。</li>
	<li>你可以假定输入的先决条件中没有重复的边。</li>
</ol>

<p><strong>提示:</strong></p>

<ol>
	<li>这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。</li>
	<li><a href="https://www.coursera.org/specializations/algorithms" target="_blank">通过 DFS 进行拓扑排序</a> - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。</li>
	<li>
	<p>拓扑排序也可以通过&nbsp;<a href="https://baike.baidu.com/item/%E5%AE%BD%E5%BA%A6%E4%BC%98%E5%85%88%E6%90%9C%E7%B4%A2/5224802?fr=aladdin&amp;fromid=2148012&amp;fromtitle=%E5%B9%BF%E5%BA%A6%E4%BC%98%E5%85%88%E6%90%9C%E7%B4%A2" target="_blank">BFS</a>&nbsp;完成。</p>
	</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

拓扑排序，BFS 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        edges = collections.defaultdict(list)
        indegree = [0] * numCourses
        for i, j in prerequisites:
            edges[j].append(i)
            indegree[i] += 1
        q = collections.deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                q.append(i)
        res = []
        while q:
            i = q.popleft()
            res.append(i)
            for j in edges[i]:
                indegree[j] -= 1
                if indegree[j] == 0:
                    q.append(j)
        return res if len(res) == numCourses else []
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
            edges[p[1]].add(p[0]);
            ++indegree[p[0]];
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int[] res = new int[numCourses];
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            res[cnt++] = i;
            for (int j : edges[i]) {
                --indegree[j];
                if (indegree[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return cnt == numCourses ? res : new int[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> edges(numCourses);
        vector<int> indegree(numCourses);
        for (auto p : prerequisites)
        {
            edges[p[1]].push_back(p[0]);
            ++indegree[p[0]];
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i)
        {
            if (indegree[i] == 0) q.push(i);
        }
        vector<int> res;
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            res.push_back(i);
            for (int j : edges[i])
            {
                --indegree[j];
                if (indegree[j] == 0) q.push(j);
            }
        }
        return res.size() == numCourses ? res : vector<int>();
    }
};
```

### **Go**

```go
func findOrder(numCourses int, prerequisites [][]int) []int {
	edges := make([][]int, numCourses)
	indegree := make([]int, numCourses)
	for _, p := range prerequisites {
		edges[p[1]] = append(edges[p[1]], p[0])
		indegree[p[0]]++
	}
	var q []int
	for i := 0; i < numCourses; i++ {
		if indegree[i] == 0 {
			q = append(q, i)
		}
	}
	var res []int
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		res = append(res, i)
		for _, j := range edges[i] {
			indegree[j]--
			if indegree[j] == 0 {
				q = append(q, j)
			}
		}
	}
	if len(res) == numCourses {
		return res
	}
	return []int{}
}
```

### **C#**

```cs
using System.Collections.Generic;

public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var indegree = new int[numCourses];
        var edgeCount = prerequisites.Length;
        var edge = new List<int>[numCourses];
        for (var i = 0; i < edgeCount; ++i)
        {
            var child = prerequisites[i][0];
            var parent = prerequisites[i][1];
            if (edge[parent] == null)
            {
                edge[parent] = new List<int>();
            }
            edge[parent].Add(child);
            ++indegree[child];
        }

        var queue = new Queue<int>();
        for (var i = 0; i < numCourses; ++i)
        {
            if (indegree[i] == 0) queue.Enqueue(i);
        }

        var result = new int[numCourses];
        var count = 0;
        while (queue.Count > 0)
        {
            var node = queue.Dequeue();
            result[count++] = node;
            if (edge[node] != null)
            {
                foreach (var next in edge[node])
                {
                    if (--indegree[next] == 0)
                    {
                        queue.Enqueue(next);
                    }
                }
            }
        }
        return count == numCourses ? result : new int[0];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
