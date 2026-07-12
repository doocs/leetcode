---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README.md
rating: 2507
source: 第 447 场周赛 Q4
tags:
    - 贪心
    - 位运算
    - 图
    - 数组
    - 双指针
    - 二分查找
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [3534. 针对图的路径存在性查询 II](https://leetcode.cn/problems/path-existence-queries-in-a-graph-ii)

[English Version](/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，表示图中的节点数量，这些节点按从 <code>0</code> 到 <code>n - 1</code>&nbsp;编号。</p>

<p>同时给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，以及一个整数 <code>maxDiff</code>。</p>

<p>如果满足 <code>|nums[i] - nums[j]| &lt;= maxDiff</code>（即 <code>nums[i]</code> 和 <code>nums[j]</code> 的&nbsp;<strong>绝对差&nbsp;</strong>至多为 <code>maxDiff</code>），则节点 <code>i</code> 和节点 <code>j</code> 之间存在一条&nbsp;<strong>无向边&nbsp;</strong>。</p>

<p>此外，给你一个二维整数数组 <code>queries</code>。对于每个 <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>，找到节点 <code>u<sub>i</sub></code> 和节点 <code>v<sub>i</sub></code> 之间的&nbsp;<strong>最短距离&nbsp;</strong>。如果两节点之间不存在路径，则返回 -1。</p>

<p>返回一个数组 <code>answer</code>，其中 <code>answer[i]</code> 是第 <code>i</code> 个查询的结果。</p>

<p><strong>注意：</strong>节点之间的边是无权重（unweighted）的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>解释:</strong></p>

<p>生成的图如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/1745660620-PauXMH-4149example1drawio.png" style="width: 281px; height: 161px;" /></p>

<table>
	<tbody>
		<tr>
			<th>查询</th>
			<th>最短路径</th>
			<th>最短距离</th>
		</tr>
		<tr>
			<td>[0, 3]</td>
			<td>0 → 3</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2, 4]</td>
			<td>2 → 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>因此，输出为 <code>[1, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[1,2,-1,1]</span></p>

<p><strong>解释:</strong></p>

<p>生成的图如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/1745660627-mSVsDs-4149example2drawio.png" style="width: 281px; height: 121px;" /></p>

<table>
	<tbody>
		<tr>
			<th>查询</th>
			<th>最短路径</th>
			<th>最短距离</th>
		</tr>
		<tr>
			<td>[0, 1]</td>
			<td>0 → 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0, 2]</td>
			<td>0 → 1 → 2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>[2, 3]</td>
			<td>无</td>
			<td>-1</td>
		</tr>
		<tr>
			<td>[4, 3]</td>
			<td>3 → 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>因此，输出为 <code>[1, 2, -1, 1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[0,-1,-1]</span></p>

<p><strong>解释:</strong></p>

<p>由于以下原因，任意两个节点之间都不存在边：</p>

<ul>
	<li>节点 0 和节点 1：<code>|nums[0] - nums[1]| = |3 - 6| = 3 &gt; 1</code></li>
	<li>节点 0 和节点 2：<code>|nums[0] - nums[2]| = |3 - 1| = 2 &gt; 1</code></li>
	<li>节点 1 和节点 2：<code>|nums[1] - nums[2]| = |6 - 1| = 5 &gt; 1</code></li>
</ul>

<p>因此，不存在任何可以到达其他节点的节点，输出为 <code>[0, -1, -1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分倍增

关键观察：若两个节点的值之差的绝对值不超过 `maxDiff`，则它们之间有边。将节点按值排序后，从值较小的节点出发，每一步贪心地跳到当前能到达的值最大的节点，即可得到最短路径。

预处理步骤如下：

1. 将 `(nums[i], i)` 按值排序；
2. 使用双指针：对于排序后的每个位置 `l`，找到满足 `pairs[r].first - pairs[l].first <= maxDiff` 的最右位置 `r`，令 `f[i][0] = j`，表示从节点 `i` 一步跳到值差不超过 `maxDiff` 的、值最大的节点 `j`；
3. 通过倍增预处理 `f[i][k]`，表示从节点 `i` 跳 $2^k$ 步后到达的节点。

查询时，设 `nums[u] <= nums[v]`：

- 若 `u == v`，答案为 $0$；
- 若 `nums[u] == nums[v]`，答案为 $1$；
- 否则用倍增求最少跳跃次数，使到达节点的值不小于 `nums[v]`；若仍无法到达则返回 $-1$，否则答案为 $d + 1$。

时间复杂度 $O(n \log n + (n + q) \log n)$，空间复杂度 $O(n \log n)$。其中 $n$ 为节点数，$q$ 为查询次数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pathExistenceQueries(
        self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]
    ) -> List[int]:
        pairs = sorted((x, i) for i, x in enumerate(nums))
        m = 20
        f = [[0] * m for _ in range(n)]
        r = n - 1
        for l in range(n - 1, -1, -1):
            while pairs[r][0] - pairs[l][0] > maxDiff:
                r -= 1
            i, j = pairs[l][1], pairs[r][1]
            f[i][0] = j
            for k in range(1, m):
                f[i][k] = f[f[i][k - 1]][k - 1]

        ans = []
        for i, j in queries:
            if nums[i] > nums[j]:
                i, j = j, i
            if i == j:
                ans.append(0)
                continue
            if nums[i] == nums[j]:
                ans.append(1)
                continue
            d = 0
            for k in range(m - 1, -1, -1):
                if nums[f[i][k]] < nums[j]:
                    d |= 1 << k
                    i = f[i][k]
            if nums[f[i][0]] < nums[j]:
                ans.append(-1)
            else:
                ans.append(d + 1)
        return ans
```

#### Java

```java
class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int m = 20;
        int[][] f = new int[n][m];
        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            while (pairs[r][0] - pairs[l][0] > maxDiff) {
                r--;
            }
            int i = pairs[l][1], j = pairs[r][1];
            f[i][0] = j;
            for (int k = 1; k < m; k++) {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        int[] ans = new int[queries.length];
        for (int t = 0; t < queries.length; t++) {
            int i = queries[t][0], j = queries[t][1];
            if (nums[i] > nums[j]) {
                int tmp = i;
                i = j;
                j = tmp;
            }
            if (i == j) {
                ans[t] = 0;
                continue;
            }
            if (nums[i] == nums[j]) {
                ans[t] = 1;
                continue;
            }
            int d = 0;
            for (int k = m - 1; k >= 0; k--) {
                if (nums[f[i][k]] < nums[j]) {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if (nums[f[i][0]] < nums[j]) {
                ans[t] = -1;
            } else {
                ans[t] = d + 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<pair<int, int>> pairs;
        for (int i = 0; i < n; i++) {
            pairs.emplace_back(nums[i], i);
        }
        sort(pairs.begin(), pairs.end());

        int m = 20;
        vector<vector<int>> f(n, vector<int>(m));
        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            while (pairs[r].first - pairs[l].first > maxDiff) {
                r--;
            }
            int i = pairs[l].second, j = pairs[r].second;
            f[i][0] = j;
            for (int k = 1; k < m; k++) {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        vector<int> ans;
        for (auto& q : queries) {
            int i = q[0], j = q[1];
            if (nums[i] > nums[j]) {
                swap(i, j);
            }
            if (i == j) {
                ans.push_back(0);
                continue;
            }
            if (nums[i] == nums[j]) {
                ans.push_back(1);
                continue;
            }
            int d = 0;
            for (int k = m - 1; k >= 0; k--) {
                if (nums[f[i][k]] < nums[j]) {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if (nums[f[i][0]] < nums[j]) {
                ans.push_back(-1);
            } else {
                ans.push_back(d + 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) []int {
	pairs := make([][2]int, n)
	for i, x := range nums {
		pairs[i] = [2]int{x, i}
	}
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})

	m := 20
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}

	r := n - 1
	for l := n - 1; l >= 0; l-- {
		for pairs[r][0]-pairs[l][0] > maxDiff {
			r--
		}
		i, j := pairs[l][1], pairs[r][1]
		f[i][0] = j
		for k := 1; k < m; k++ {
			f[i][k] = f[f[i][k-1]][k-1]
		}
	}

	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		i, j := q[0], q[1]
		if nums[i] > nums[j] {
			i, j = j, i
		}
		if i == j {
			ans = append(ans, 0)
			continue
		}
		if nums[i] == nums[j] {
			ans = append(ans, 1)
			continue
		}
		d := 0
		for k := m - 1; k >= 0; k-- {
			if nums[f[i][k]] < nums[j] {
				d |= 1 << k
				i = f[i][k]
			}
		}
		if nums[f[i][0]] < nums[j] {
			ans = append(ans, -1)
		} else {
			ans = append(ans, d+1)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): number[] {
    const pairs: number[][] = [];
    for (let i = 0; i < n; i++) {
        pairs.push([nums[i], i]);
    }
    pairs.sort((a, b) => a[0] - b[0]);

    const m = 20;
    const f = Array.from({ length: n }, () => Array(m).fill(0));

    let r = n - 1;
    for (let l = n - 1; l >= 0; l--) {
        while (pairs[r][0] - pairs[l][0] > maxDiff) {
            r--;
        }
        let i = pairs[l][1],
            j = pairs[r][1];
        f[i][0] = j;
        for (let k = 1; k < m; k++) {
            f[i][k] = f[f[i][k - 1]][k - 1];
        }
    }

    const ans: number[] = [];
    for (const q of queries) {
        let i = q[0],
            j = q[1];
        if (nums[i] > nums[j]) {
            [i, j] = [j, i];
        }
        if (i === j) {
            ans.push(0);
            continue;
        }
        if (nums[i] === nums[j]) {
            ans.push(1);
            continue;
        }
        let d = 0;
        for (let k = m - 1; k >= 0; k--) {
            if (nums[f[i][k]] < nums[j]) {
                d |= 1 << k;
                i = f[i][k];
            }
        }
        if (nums[f[i][0]] < nums[j]) {
            ans.push(-1);
        } else {
            ans.push(d + 1);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn path_existence_queries(
        n: i32,
        nums: Vec<i32>,
        max_diff: i32,
        queries: Vec<Vec<i32>>,
    ) -> Vec<i32> {
        let n = n as usize;
        let mut pairs = Vec::with_capacity(n);
        for (i, &x) in nums.iter().enumerate() {
            pairs.push((x, i));
        }
        pairs.sort_unstable();

        let m = 20;
        let mut f = vec![vec![0; m]; n];

        let mut r = n - 1;
        for l in (0..n).rev() {
            while pairs[r].0 - pairs[l].0 > max_diff {
                r -= 1;
            }
            let (i, j) = (pairs[l].1, pairs[r].1);
            f[i][0] = j;
            for k in 1..m {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let (mut i, mut j) = (q[0] as usize, q[1] as usize);
            if nums[i] > nums[j] {
                std::mem::swap(&mut i, &mut j);
            }
            if i == j {
                ans.push(0);
                continue;
            }
            if nums[i] == nums[j] {
                ans.push(1);
                continue;
            }
            let mut d = 0;
            for k in (0..m).rev() {
                if nums[f[i][k]] < nums[j] {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if nums[f[i][0]] < nums[j] {
                ans.push(-1);
            } else {
                ans.push(d + 1);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
