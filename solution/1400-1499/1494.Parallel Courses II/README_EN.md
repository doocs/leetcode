# [1494. Parallel Courses II](https://leetcode.com/problems/parallel-courses-ii)

[中文文档](/solution/1400-1499/1494.Parallel%20Courses%20II/README.md)

## Description

<p>You are given an integer <code>n</code>, which indicates that there are <code>n</code> courses labeled from <code>1</code> to <code>n</code>. You are also given an array <code>relations</code> where <code>relations[i] = [prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code>, representing a prerequisite relationship between course <code>prevCourse<sub>i</sub></code> and course <code>nextCourse<sub>i</sub></code>: course <code>prevCourse<sub>i</sub></code> has to be taken before course <code>nextCourse<sub>i</sub></code>. Also, you are given the integer <code>k</code>.</p>

<p>In one semester, you can take <strong>at most</strong> <code>k</code> courses as long as you have taken all the prerequisites in the <strong>previous</strong> semesters for the courses you are taking.</p>

<p>Return <em>the <strong>minimum</strong> number of semesters needed to take all courses</em>. The testcases will be generated such that it is possible to take every course.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1494.Parallel%20Courses%20II/images/leetcode_parallel_courses_1.png" style="width: 269px; height: 147px;" />
<pre>
<strong>Input:</strong> n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The figure above represents the given graph.
In the first semester, you can take courses 2 and 3.
In the second semester, you can take course 1.
In the third semester, you can take course 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1494.Parallel%20Courses%20II/images/leetcode_parallel_courses_2.png" style="width: 271px; height: 211px;" />
<pre>
<strong>Input:</strong> n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> The figure above represents the given graph.
In the first semester, you can only take courses 2 and 3 since you cannot take more than two per semester.
In the second semester, you can take course 4.
In the third semester, you can take course 1.
In the fourth semester, you can take course 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= relations.length &lt;= n * (n-1) / 2</code></li>
	<li><code>relations[i].length == 2</code></li>
	<li><code>1 &lt;= prevCourse<sub>i</sub>, nextCourse<sub>i</sub> &lt;= n</code></li>
	<li><code>prevCourse<sub>i</sub> != nextCourse<sub>i</sub></code></li>
	<li>All the pairs <code>[prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code> are <strong>unique</strong>.</li>
	<li>The given graph is a directed acyclic graph.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minNumberOfSemesters(self, n: int, relations: List[List[int]], k: int) -> int:
        d = [0] * (n + 1)
        for x, y in relations:
            d[y] |= 1 << x
        q = deque([(0, 0)])
        vis = {0}
        while q:
            cur, t = q.popleft()
            if cur == (1 << (n + 1)) - 2:
                return t
            nxt = 0
            for i in range(1, n + 1):
                if (cur & d[i]) == d[i]:
                    nxt |= 1 << i
            nxt ^= cur
            if nxt.bit_count() <= k:
                if (nxt | cur) not in vis:
                    vis.add(nxt | cur)
                    q.append((nxt | cur, t + 1))
            else:
                x = nxt
                while nxt:
                    if nxt.bit_count() == k and (nxt | cur) not in vis:
                        vis.add(nxt | cur)
                        q.append((nxt | cur, t + 1))
                    nxt = (nxt - 1) & x
```

### **Java**

```java
class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] d = new int[n + 1];
        for (var e : relations) {
            d[e[1]] |= 1 << e[0];
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        while (!q.isEmpty()) {
            var p = q.pollFirst();
            int cur = p[0], t = p[1];
            if (cur == (1 << (n + 1)) - 2) {
                return t;
            }
            int nxt = 0;
            for (int i = 1; i <= n; ++i) {
                if ((cur & d[i]) == d[i]) {
                    nxt |= 1 << i;
                }
            }
            nxt ^= cur;
            if (Integer.bitCount(nxt) <= k) {
                if (vis.add(nxt | cur)) {
                    q.offer(new int[] {nxt | cur, t + 1});
                }
            } else {
                int x = nxt;
                while (nxt > 0) {
                    if (Integer.bitCount(nxt) == k && vis.add(nxt | cur)) {
                        q.offer(new int[] {nxt | cur, t + 1});
                    }
                    nxt = (nxt - 1) & x;
                }
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    int minNumberOfSemesters(int n, vector<vector<int>>& relations, int k) {
        vector<int> d(n + 1);
        for (auto& e : relations) {
            d[e[1]] |= 1 << e[0];
        }
        queue<pii> q;
        q.push({0, 0});
        unordered_set<int> vis{{0}};
        while (!q.empty()) {
            auto [cur, t] = q.front();
            q.pop();
            if (cur == (1 << (n + 1)) - 2) {
                return t;
            }
            int nxt = 0;
            for (int i = 1; i <= n; ++i) {
                if ((cur & d[i]) == d[i]) {
                    nxt |= 1 << i;
                }
            }
            nxt ^= cur;
            if (__builtin_popcount(nxt) <= k) {
                if (!vis.count(nxt | cur)) {
                    vis.insert(nxt | cur);
                    q.push({nxt | cur, t + 1});
                }
            } else {
                int x = nxt;
                while (nxt) {
                    if (__builtin_popcount(nxt) == k && !vis.count(nxt | cur)) {
                        vis.insert(nxt | cur);
                        q.push({nxt | cur, t + 1});
                    }
                    nxt = (nxt - 1) & x;
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func minNumberOfSemesters(n int, relations [][]int, k int) int {
	d := make([]int, n+1)
	for _, e := range relations {
		d[e[1]] |= 1 << e[0]
	}
	type pair struct{ v, t int }
	q := []pair{pair{0, 0}}
	vis := map[int]bool{0: true}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		cur, t := p.v, p.t
		if cur == (1<<(n+1))-2 {
			return t
		}
		nxt := 0
		for i := 1; i <= n; i++ {
			if (cur & d[i]) == d[i] {
				nxt |= 1 << i
			}
		}
		nxt ^= cur
		if bits.OnesCount(uint(nxt)) <= k {
			if !vis[nxt|cur] {
				vis[nxt|cur] = true
				q = append(q, pair{nxt | cur, t + 1})
			}
		} else {
			x := nxt
			for nxt > 0 {
				if bits.OnesCount(uint(nxt)) == k && !vis[nxt|cur] {
					vis[nxt|cur] = true
					q = append(q, pair{nxt | cur, t + 1})
				}
				nxt = (nxt - 1) & x
			}
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
