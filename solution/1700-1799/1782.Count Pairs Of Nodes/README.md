---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/README.md
rating: 2457
source: 第 47 场双周赛 Q4
tags:
    - 图
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 计数
    - 排序
---

<!-- problem:start -->

# [1782. 统计点对的数目](https://leetcode.cn/problems/count-pairs-of-nodes)

[English Version](/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个无向图，无向图由整数&nbsp;<code>n</code>&nbsp;&nbsp;，表示图中节点的数目，和&nbsp;<code>edges</code>&nbsp;组成，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code><sub>&nbsp;</sub>之间有一条无向边。同时给你一个代表查询的整数数组&nbsp;<code>queries</code>&nbsp;。</p>

<p>第 <code>j</code> 个查询的答案是满足如下条件的点对 <code>(a, b)</code> 的数目：</p>

<ul>
	<li><code>a &lt; b</code></li>
	<li><code>cnt</code>&nbsp;是与 <code>a</code>&nbsp;<strong>或者&nbsp;</strong><code>b</code>&nbsp;相连的边的数目，且 <code>cnt</code>&nbsp;<strong>严格大于&nbsp;</strong><code>queries[j]</code>&nbsp;。</li>
</ul>

<p>请你返回一个数组&nbsp;<code>answers</code>&nbsp;，其中&nbsp;<code>answers.length == queries.length</code> 且&nbsp;<code>answers[j]</code>&nbsp;是第 <code>j</code>&nbsp;个查询的答案。</p>

<p>请注意，图中可能会有 <strong>多重边</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1782.Count%20Pairs%20Of%20Nodes/images/1692844033-Kvxjvr-image.png" style="width: 600px; height: 338px;" /></p>

<pre>
<b>输入：</b>n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
<b>输出：</b>[6,5]
<b>解释：</b>每个点对中，与至少一个点相连的边的数目如上图所示。
answers[0] = 6。所有的点对(a, b)中边数和都大于2，故有6个；
answers[1] = 5。所有的点对(a, b)中除了(3,4)边数等于3，其它点对边数和都大于3，故有5个。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
<b>输出：</b>[10,10,9,8,6]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 &lt;= queries.length &lt;= 20</code></li>
	<li><code>0 &lt;= queries[j] &lt; edges.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序 + 二分查找

根据题目，我们可以知道，与点对 $(a, b)$ 相连的边数等于“与 $a$ 相连的边数”加上“与 $b$ 相连的边数”，再减去同时与 $a$ 和 $b$ 相连的边数。

因此，我们可以先用数组 $cnt$ 统计与每个点相连的边数，用哈希表 $g$ 统计每个点对的数量。

然后，对于每个查询 $q$，我们可以枚举 $a$，对于每个 $a$，我们可以通过二分查找找到第一个满足 $cnt[a] + cnt[b] > q$ 的 $b$，先将数量累加到当前查询的答案中，然后减去一部分重复的边。

时间复杂度 $O(q \times (n \times \log n + m))$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是点数和边数，而 $q$ 是查询数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(
        self, n: int, edges: List[List[int]], queries: List[int]
    ) -> List[int]:
        cnt = [0] * n
        g = defaultdict(int)
        for a, b in edges:
            a, b = a - 1, b - 1
            a, b = min(a, b), max(a, b)
            cnt[a] += 1
            cnt[b] += 1
            g[(a, b)] += 1

        s = sorted(cnt)
        ans = [0] * len(queries)
        for i, t in enumerate(queries):
            for j, x in enumerate(s):
                k = bisect_right(s, t - x, lo=j + 1)
                ans[i] += n - k
            for (a, b), v in g.items():
                if cnt[a] + cnt[b] > t and cnt[a] + cnt[b] - v <= t:
                    ans[i] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] cnt = new int[n];
        Map<Integer, Integer> g = new HashMap<>();
        for (var e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            ++cnt[a];
            ++cnt[b];
            int k = Math.min(a, b) * n + Math.max(a, b);
            g.merge(k, 1, Integer::sum);
        }
        int[] s = cnt.clone();
        Arrays.sort(s);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int t = queries[i];
            for (int j = 0; j < n; ++j) {
                int x = s[j];
                int k = search(s, t - x, j + 1);
                ans[i] += n - k;
            }
            for (var e : g.entrySet()) {
                int a = e.getKey() / n, b = e.getKey() % n;
                int v = e.getValue();
                if (cnt[a] + cnt[b] > t && cnt[a] + cnt[b] - v <= t) {
                    --ans[i];
                }
            }
        }
        return ans;
    }

    private int search(int[] arr, int x, int i) {
        int left = i, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countPairs(int n, vector<vector<int>>& edges, vector<int>& queries) {
        vector<int> cnt(n);
        unordered_map<int, int> g;
        for (auto& e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            ++cnt[a];
            ++cnt[b];
            int k = min(a, b) * n + max(a, b);
            ++g[k];
        }
        vector<int> s = cnt;
        sort(s.begin(), s.end());
        vector<int> ans(queries.size());
        for (int i = 0; i < queries.size(); ++i) {
            int t = queries[i];
            for (int j = 0; j < n; ++j) {
                int x = s[j];
                int k = upper_bound(s.begin() + j + 1, s.end(), t - x) - s.begin();
                ans[i] += n - k;
            }
            for (auto& [k, v] : g) {
                int a = k / n, b = k % n;
                if (cnt[a] + cnt[b] > t && cnt[a] + cnt[b] - v <= t) {
                    --ans[i];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(n int, edges [][]int, queries []int) []int {
	cnt := make([]int, n)
	g := map[int]int{}
	for _, e := range edges {
		a, b := e[0]-1, e[1]-1
		cnt[a]++
		cnt[b]++
		if a > b {
			a, b = b, a
		}
		g[a*n+b]++
	}
	s := make([]int, n)
	copy(s, cnt)
	sort.Ints(s)
	ans := make([]int, len(queries))
	for i, t := range queries {
		for j, x := range s {
			k := sort.Search(n, func(h int) bool { return s[h] > t-x && h > j })
			ans[i] += n - k
		}
		for k, v := range g {
			a, b := k/n, k%n
			if cnt[a]+cnt[b] > t && cnt[a]+cnt[b]-v <= t {
				ans[i]--
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countPairs(n: number, edges: number[][], queries: number[]): number[] {
    const cnt: number[] = new Array(n).fill(0);
    const g: Map<number, number> = new Map();
    for (const [a, b] of edges) {
        ++cnt[a - 1];
        ++cnt[b - 1];
        const k = Math.min(a - 1, b - 1) * n + Math.max(a - 1, b - 1);
        g.set(k, (g.get(k) || 0) + 1);
    }
    const s = cnt.slice().sort((a, b) => a - b);
    const search = (nums: number[], x: number, l: number): number => {
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const t of queries) {
        let res = 0;
        for (let j = 0; j < s.length; ++j) {
            const k = search(s, t - s[j], j + 1);
            res += n - k;
        }
        for (const [k, v] of g) {
            const a = Math.floor(k / n);
            const b = k % n;
            if (cnt[a] + cnt[b] > t && cnt[a] + cnt[b] - v <= t) {
                --res;
            }
        }
        ans.push(res);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
