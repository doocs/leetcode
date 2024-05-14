---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2050.Parallel%20Courses%20III/README.md
rating: 2084
tags:
    - 图
    - 拓扑排序
    - 数组
    - 动态规划
---

# [2050. 并行课程 III](https://leetcode.cn/problems/parallel-courses-iii)

[English Version](/solution/2000-2099/2050.Parallel%20Courses%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示有&nbsp;<code>n</code>&nbsp;节课，课程编号从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;。同时给你一个二维整数数组&nbsp;<code>relations</code>&nbsp;，其中&nbsp;<code>relations[j] = [prevCourse<sub>j</sub>, nextCourse<sub>j</sub>]</code>&nbsp;，表示课程&nbsp;<code>prevCourse<sub>j</sub></code>&nbsp;必须在课程&nbsp;<code>nextCourse<sub>j</sub></code>&nbsp;<strong>之前</strong>&nbsp;完成（先修课的关系）。同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>time</code>&nbsp;，其中&nbsp;<code>time[i]</code>&nbsp;表示完成第&nbsp;<code>(i+1)</code>&nbsp;门课程需要花费的 <strong>月份</strong>&nbsp;数。</p>

<p>请你根据以下规则算出完成所有课程所需要的 <strong>最少</strong>&nbsp;月份数：</p>

<ul>
	<li>如果一门课的所有先修课都已经完成，你可以在 <strong>任意</strong>&nbsp;时间开始这门课程。</li>
	<li>你可以&nbsp;<strong>同时</strong>&nbsp;上&nbsp;<strong>任意门课程</strong>&nbsp;。</li>
</ul>

<p>请你返回完成所有课程所需要的 <strong>最少</strong>&nbsp;月份数。</p>

<p><strong>注意：</strong>测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2050.Parallel%20Courses%20III/images/ex1.png" style="width: 392px; height: 232px;"></strong></p>

<pre><strong>输入：</strong>n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
<b>输出：</b>8
<b>解释：</b>上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
你可以在月份 0 同时开始课程 1 和 2 。
课程 1 花费 3 个月，课程 2 花费 2 个月。
所以，最早开始课程 3 的时间是月份 3 ，完成所有课程所需时间为 3 + 5 = 8 个月。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2050.Parallel%20Courses%20III/images/ex2.png" style="width: 500px; height: 365px;"></strong></p>

<pre><b>输入：</b>n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
<b>输出：</b>12
<b>解释：</b>上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
你可以在月份 0 同时开始课程 1 ，2 和 3 。
在月份 1，2 和 3 分别完成这三门课程。
课程 4 需在课程 3 之后开始，也就是 3 个月后。课程 4 在 3 + 4 = 7 月完成。
课程 5 需在课程 1，2，3 和 4 之后开始，也就是在 max(1,2,3,7) = 7 月开始。
所以完成所有课程所需的最少时间为 7 + 5 = 12 个月。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= relations.length &lt;= min(n * (n - 1) / 2, 5 * 10<sup>4</sup>)</code></li>
	<li><code>relations[j].length == 2</code></li>
	<li><code>1 &lt;= prevCourse<sub>j</sub>, nextCourse<sub>j</sub> &lt;= n</code></li>
	<li><code>prevCourse<sub>j</sub> != nextCourse<sub>j</sub></code></li>
	<li>所有的先修课程对&nbsp;<code>[prevCourse<sub>j</sub>, nextCourse<sub>j</sub>]</code>&nbsp;都是 <strong>互不相同</strong>&nbsp;的。</li>
	<li><code>time.length == n</code></li>
	<li><code>1 &lt;= time[i] &lt;= 10<sup>4</sup></code></li>
	<li>先修课程图是一个有向无环图。</li>
</ul>

## 解法

### 方法一：拓扑排序 + 动态规划

我们首先根据给定的先修课程关系，构建出一个有向无环图，对该图进行拓扑排序，然后根据拓扑排序的结果，使用动态规划求出完成所有课程所需要的最少时间。

我们定义以下几个数据结构或变量：

-   邻接表 $g$ 存储有向无环图，同时使用一个数组 $indeg$ 存储每个节点的入度；
-   队列 $q$ 存储所有入度为 $0$ 的节点；
-   数组 $f$ 存储每个节点的最早完成时间，初始时 $f[i] = 0$；
-   变量 $ans$ 记录最终的答案，初始时 $ans = 0$；

当 $q$ 非空时，依次取出队首节点 $i$，遍历 $g[i]$ 中的每个节点 $j$，更新 $f[j] = \max(f[j], f[i] + time[j])$，同时更新 $ans = \max(ans, f[j])$，并将 $j$ 的入度减 $1$，如果此时 $j$ 的入度为 $0$，则将 $j$ 加入队列 $q$ 中；

最终返回 $ans$。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 是数组 $relations$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumTime(self, n: int, relations: List[List[int]], time: List[int]) -> int:
        g = defaultdict(list)
        indeg = [0] * n
        for a, b in relations:
            g[a - 1].append(b - 1)
            indeg[b - 1] += 1
        q = deque()
        f = [0] * n
        ans = 0
        for i, (v, t) in enumerate(zip(indeg, time)):
            if v == 0:
                q.append(i)
                f[i] = t
                ans = max(ans, t)
        while q:
            i = q.popleft()
            for j in g[i]:
                f[j] = max(f[j], f[i] + time[j])
                ans = max(ans, f[j])
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return ans
```

```java
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[n];
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                f[i] = t;
                ans = Math.max(ans, t);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                f[j] = Math.max(f[j], f[i] + time[j]);
                ans = Math.max(ans, f[j]);
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumTime(int n, vector<vector<int>>& relations, vector<int>& time) {
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        vector<int> f(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.push(i);
                f[i] = t;
                ans = max(ans, t);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.push(j);
                }
                f[j] = max(f[j], f[i] + time[j]);
                ans = max(ans, f[j]);
            }
        }
        return ans;
    }
};
```

```go
func minimumTime(n int, relations [][]int, time []int) int {
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, e := range relations {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		indeg[b]++
	}
	f := make([]int, n)
	q := []int{}
	ans := 0
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
			f[i] = time[i]
			ans = max(ans, time[i])
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
			f[j] = max(f[j], f[i]+time[j])
			ans = max(ans, f[j])
		}
	}
	return ans
}
```

```ts
function minimumTime(n: number, relations: number[][], time: number[]): number {
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => []);
    const indeg: number[] = Array(n).fill(0);
    for (const [a, b] of relations) {
        g[a - 1].push(b - 1);
        ++indeg[b - 1];
    }
    const q: number[] = [];
    const f: number[] = Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
            f[i] = time[i];
            ans = Math.max(ans, f[i]);
        }
    }
    while (q.length > 0) {
        const i = q.shift()!;
        for (const j of g[i]) {
            f[j] = Math.max(f[j], f[i] + time[j]);
            ans = Math.max(ans, f[j]);
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
