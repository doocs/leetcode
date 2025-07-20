---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/README.md
rating: 1658
source: 第 447 场周赛 Q2
tags:
    - 并查集
    - 图
    - 数组
    - 哈希表
    - 二分查找
---

<!-- problem:start -->

# [3532. 针对图的路径存在性查询 I](https://leetcode.cn/problems/path-existence-queries-in-a-graph-i)

[English Version](/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，表示图中的节点数量，这些节点按从 <code>0</code> 到 <code>n - 1</code>&nbsp;编号。</p>

<p>同时给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，该数组按&nbsp;<strong>非递减&nbsp;</strong>顺序排序，以及一个整数 <code>maxDiff</code>。</p>

<p>如果满足 <code>|nums[i] - nums[j]| &lt;= maxDiff</code>（即 <code>nums[i]</code> 和 <code>nums[j]</code> 的&nbsp;<strong>绝对差&nbsp;</strong>至多为 <code>maxDiff</code>），则节点 <code>i</code> 和节点 <code>j</code> 之间存在一条&nbsp;<strong>无向边&nbsp;</strong>。</p>

<p>此外，给你一个二维整数数组 <code>queries</code>。对于每个 <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>，需要判断节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间是否存在路径。</p>

<p>返回一个布尔数组 <code>answer</code>，其中 <code>answer[i]</code> 等于 <code>true</code> 表示在第 <code>i</code> 个查询中节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间存在路径，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 2, nums = [1,3], maxDiff = 1, queries = [[0,0],[0,1]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[true,false]</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>查询 <code>[0,0]</code>：节点 0 有一条到自己的显然路径。</li>
	<li>查询 <code>[0,1]</code>：节点 0 和节点 1 之间没有边，因为 <code>|nums[0] - nums[1]| = |1 - 3| = 2</code>，大于 <code>maxDiff</code>。</li>
	<li>因此，在处理完所有查询后，最终答案为 <code>[true, false]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 4, nums = [2,5,6,8], maxDiff = 2, queries = [[0,1],[0,2],[1,3],[2,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[false,false,true,true]</span></p>

<p><strong>解释:</strong></p>

<p>生成的图如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/images/1745660506-eNVQtC-screenshot-2025-03-26-at-122249.png" style="width: 300px; height: 170px;" /></p>

<ul>
	<li>查询 <code>[0,1]</code>：节点 0 和节点 1 之间没有边，因为 <code>|nums[0] - nums[1]| = |2 - 5| = 3</code>，大于 <code>maxDiff</code>。</li>
	<li>查询 <code>[0,2]</code>：节点 0 和节点 2 之间没有边，因为 <code>|nums[0] - nums[2]| = |2 - 6| = 4</code>，大于 <code>maxDiff</code>。</li>
	<li>查询 <code>[1,3]</code>：节点 1 和节点 3 之间存在路径通过节点 2，因为 <code>|nums[1] - nums[2]| = |5 - 6| = 1</code> 和 <code>|nums[2] - nums[3]| = |6 - 8| = 2</code>，都小于等于 <code>maxDiff</code>。</li>
	<li>查询 <code>[2,3]</code>：节点 2 和节点 3 之间有一条边，因为 <code>|nums[2] - nums[3]| = |6 - 8| = 2</code>，等于 <code>maxDiff</code>。</li>
	<li>因此，在处理完所有查询后，最终答案为 <code>[false, false, true, true]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> 按&nbsp;<strong>非递减&nbsp;</strong>顺序排序。</li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组

根据题目描述，同一个连通分量的节点编号，一定是连续的。因此，我们可以用一个数组 $g$ 来记录每个节点所在的连通分量编号，用一个变量 $\textit{cnt}$ 来记录当前连通分量的编号。遍历 $\textit{nums}$ 数组，如果当前节点和前一个节点的差值大于 $\textit{maxDiff}$，则说明当前节点和前一个节点不在同一个连通分量中，我们就将 $\textit{cnt}$ 加 1。然后，我们将当前节点的连通分量编号赋值为 $\textit{cnt}$。

最后，对于每个查询 $(u, v)$，我们只需要判断 $g[u]$ 和 $g[v]$ 是否相等即可，如果相等，则说明 $u$ 和 $v$ 在同一个连通分量中，那么第 $i$ 个查询的答案就是 $\text{true}$，否则就是 $\text{false}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 $\textit{nums}$ 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pathExistenceQueries(
        self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]
    ) -> List[bool]:
        g = [0] * n
        cnt = 0
        for i in range(1, n):
            if nums[i] - nums[i - 1] > maxDiff:
                cnt += 1
            g[i] = cnt
        return [g[u] == g[v] for u, v in queries]
```

#### Java

```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] g = new int[n];
        int cnt = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                cnt++;
            }
            g[i] = cnt;
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = g[u] == g[v];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<int> g(n);
        int cnt = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                ++cnt;
            }
            g[i] = cnt;
        }

        vector<bool> ans;
        for (const auto& q : queries) {
            int u = q[0], v = q[1];
            ans.push_back(g[u] == g[v]);
        }
        return ans;
    }
};
```

#### Go

```go
func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) (ans []bool) {
	g := make([]int, n)
	cnt := 0
	for i := 1; i < n; i++ {
		if nums[i]-nums[i-1] > maxDiff {
			cnt++
		}
		g[i] = cnt
	}

	for _, q := range queries {
		u, v := q[0], q[1]
		ans = append(ans, g[u] == g[v])
	}
	return
}
```

#### TypeScript

```ts
function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): boolean[] {
    const g: number[] = Array(n).fill(0);
    let cnt = 0;

    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > maxDiff) {
            ++cnt;
        }
        g[i] = cnt;
    }

    return queries.map(([u, v]) => g[u] === g[v]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
