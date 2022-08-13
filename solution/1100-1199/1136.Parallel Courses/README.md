# [1136. 并行课程](https://leetcode.cn/problems/parallel-courses)

[English Version](/solution/1100-1199/1136.Parallel%20Courses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，表示编号从 <code>1</code> 到 <code>n</code> 的 <code>n</code> 门课程。另给你一个数组 <code>relations</code> ，其中 <code>relations[i] = [prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code> ，表示课程 <code>prevCourse<sub>i</sub></code> 和课程 <code>nextCourse<sub>i</sub></code> 之间存在先修关系：课程 <code>prevCourse<sub>i</sub></code> 必须在 <code>nextCourse<sub>i</sub></code> 之前修读完成。</p>

<p>在一个学期内，你可以学习 <strong>任意数量</strong> 的课程，但前提是你已经在上一学期修读完待学习课程的所有先修课程。</p>

<div class="original__bRMd">
<div>
<p>请你返回学完全部课程所需的 <strong>最少</strong> 学期数。如果没有办法做到学完全部这些课程的话，就返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course1graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>输入：</strong>n = 3, relations = [[1,3],[2,3]]
<strong>输出：</strong>2
<strong>解释：</strong>上图表示课程之间的关系图：
在第一学期，可以修读课程 1 和 2 。
在第二学期，可以修读课程 3 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course2graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>输入：</strong>n = 3, relations = [[1,2],[2,3],[3,1]]
<strong>输出：</strong>-1
<strong>解释：</strong>没有课程可以学习，因为它们互为先修课程。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>1 &lt;= relations.length &lt;= 5000</code></li>
	<li><code>relations[i].length == 2</code></li>
	<li><code>1 &lt;= prevCourse<sub>i</sub>, nextCourse<sub>i</sub> &lt;= n</code></li>
	<li><code>prevCourse<sub>i</sub> != nextCourse<sub>i</sub></code></li>
	<li>所有 <code>[prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code> <strong>互不相同</strong></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        g = defaultdict(list)
        indeg = [0] * n
        for a, b in relations:
            g[a - 1].append(b - 1)
            indeg[b - 1] += 1
        ans = 0
        q = deque([i for i, v in enumerate(indeg) if v == 0])
        while q:
            ans += 1
            for _ in range(len(q)):
                i = q.popleft()
                n -= 1
                for j in g[i]:
                    indeg[j] -= 1
                    if indeg[j] == 0:
                        q.append(j)
        return -1 if n else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        int[] indeg = new int[n];
        for (int[] r : relations) {
            int a = r[0] - 1, b = r[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int i = q.poll();
                --n;
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }
        return n == 0 ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSemesters(int n, vector<vector<int>>& relations) {
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& r : relations) {
            int a = r[0] - 1, b = r[1] - 1;
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) q.push(i);
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                --n;
                for (int j : g[i])
                    if (--indeg[j] == 0) q.push(j);
            }
        }
        return n == 0 ? ans : -1;
    }
};
```

### **Go**

```go
func minimumSemesters(n int, relations [][]int) int {
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, r := range relations {
		a, b := r[0]-1, r[1]-1
		g[a] = append(g[a], b)
		indeg[b]++
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	ans := 0
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			n--
			for _, j := range g[i] {
				indeg[j]--
				if indeg[j] == 0 {
					q = append(q, j)
				}
			}
		}
	}
	if n == 0 {
		return ans
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
