## 课程表
### 题目描述
现在你总共有 `n` 门课需要选，记为 `0` 到 `n-1`。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: `[0,1]`。

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

**示例1:**
```
输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
```

**示例2:**
```
输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
```

**说明:**

- 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
- 你可以假定输入的先决条件中没有重复的边。

**提示:**

- 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
- 拓扑排序也可以通过 BFS 完成。


### 解法
利用拓扑排序，拓扑排序是说，如果存在 `a -> b` 的边，那么拓扑排序的结果，a 一定在 b 的前面。

如果存在拓扑排序，那么就可以完成所有课程的学习。

**说明：**

- 拓扑排序的本质是不断输出入度为 0 的点，该算法可**用于判断图中是否存在环**；
- 可以用队列（或者栈）保存入度为 0 的点，避免每次遍历所有的点。

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[][] g = new int[numCourses][numCourses];
        for (int[] e : prerequisites) {
            int cur = e[0];
            int pre = e[1];
            if (g[pre][cur] == 0) {
                ++indegree[cur];
                g[pre][cur] = 1;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int cnt = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            ++cnt;
            for (int j = 0; j < numCourses; ++j) {
                if (g[i][j] == 1) {
                    g[i][j] = 0;
                    --indegree[j];
                    if (indegree[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
        }
        
        return cnt == numCourses;
    }
}
```