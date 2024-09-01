---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3277.Maximum%20XOR%20Score%20Subarray%20Queries/README.md
---

<!-- problem:start -->

# [3277. 查询子数组最大异或值](https://leetcode.cn/problems/maximum-xor-score-subarray-queries)

[English Version](/solution/3200-3299/3277.Maximum%20XOR%20Score%20Subarray%20Queries/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个整数组成的数组 <code>nums</code>，以及一个大小为 <code>q</code> 的二维整数数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>。</p>

<p>对于每一个查询，你需要找出 <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> 中任意 <span data-keyword="subarray">子数组</span> 的 <strong>最大异或值</strong>。</p>

<p><strong>数组的异或值 </strong>需要对数组 <code>a</code> 反复执行以下操作，直到只剩一个元素，剩下的那个元素就是 <strong>异或值</strong>：</p>

<ul>
	<li><span class="text-only" data-eleid="9" style="white-space: pre;">对于除最后一个下标以外的所有下标</span> <code>i</code>，同时将 <code>a[i]</code> 替换为 <code>a[i] XOR a[i + 1]</code> 。</li>
	<li>移除数组的最后一个元素。</li>
</ul>

<p>返回一个大小为 <code>q</code> 的数组 <code>answer</code>，其中 <code>answer[i]</code> 表示查询 <code>i</code> 的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,8,4,32,16,1], queries = [[0,2],[1,4],[0,5]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[12,60,60]</span></p>

<p><strong>解释：</strong></p>

<p>在第一个查询中，<code>nums[0..2]</code> 的子数组分别是 <code>[2]</code>, <code>[8]</code>, <code>[4]</code>, <code>[2, 8]</code>, <code>[8, 4]</code>, 和 <code>[2, 8, 4]</code>，它们的异或值分别为 2, 8, 4, 10, 12, 和 6。查询的答案是 12，所有异或值中的最大值。</p>

<p>在第二个查询中，<code>nums[1..4]</code> 的子数组中最大的异或值是子数组 <code>nums[1..4]</code> 的异或值，为 60。</p>

<p>在第三个查询中，<code>nums[0..5]</code> 的子数组中最大的异或值是子数组 <code>nums[1..4]</code> 的异或值，为 60。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,7,3,2,8,5,1], queries = [[0,3],[1,5],[2,4],[2,6],[5,6]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[7,14,11,14,5]</span></p>

<p><strong>解释：</strong></p>

<table height="70" width="472">
	<thead>
		<tr>
			<th>下标</th>
			<th>nums[l<sub>i</sub>..r<sub>i</sub>]</th>
			<th>最大异或值子数组</th>
			<th>子数组最大异或值</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[0, 7, 3, 2]</td>
			<td>[7]</td>
			<td>7</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[7, 3, 2, 8, 5]</td>
			<td>[7, 3, 2, 8]</td>
			<td>14</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[3, 2, 8]</td>
			<td>[3, 2, 8]</td>
			<td>11</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[3, 2, 8, 5, 1]</td>
			<td>[2, 8, 5, 1]</td>
			<td>14</td>
		</tr>
		<tr>
			<td>4</td>
			<td>[5, 1]</td>
			<td>[5]</td>
			<td>5</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= q == queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示 $\textit{nums}[i..j]$ 的异或值，那么根据题目描述，我们可以得到状态转移方程：

$$
f[i][j] = f[i][j-1] \oplus f[i+1][j]
$$

其中 $\oplus$ 表示异或运算。

我们再定义 $g[i][j]$ 表示 $f[i][j]$ 的最大值，那么状态转移方程为：

$$
g[i][j] = \max(f[i][j], g[i][j-1], g[i+1][j])
$$

最后，我们遍历查询数组，对于每个查询 $[l, r]$，将 $g[l][r]$ 加入答案数组即可。

时间复杂度 $O(n^2 + m)$，空间复杂度 $O(n^2)$。其中 $n$ 和 $m$ 分别为数组 $\textit{nums}$ 和 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSubarrayXor(
        self, nums: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            f[i][i] = g[i][i] = nums[i]
            for j in range(i + 1, n):
                f[i][j] = f[i][j - 1] ^ f[i + 1][j]
                g[i][j] = max(f[i][j], g[i][j - 1], g[i + 1][j])
        return [g[l][r] for l, r in queries]
```

#### Java

```java
class Solution {
    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = nums[i];
            g[i][i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = f[i][j - 1] ^ f[i + 1][j];
                g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = g[l][r];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maximumSubarrayXor(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(n));
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = nums[i];
            g[i][i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = f[i][j - 1] ^ f[i + 1][j];
                g[i][j] = max({f[i][j], g[i][j - 1], g[i + 1][j]});
            }
        }
        vector<int> ans;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(g[l][r]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSubarrayXor(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := 0; i < n; i++ {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		f[i][i] = nums[i]
		g[i][i] = nums[i]
		for j := i + 1; j < n; j++ {
			f[i][j] = f[i][j-1] ^ f[i+1][j]
			g[i][j] = max(f[i][j], max(g[i][j-1], g[i+1][j]))
		}
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, g[l][r])
	}
	return
}
```

#### TypeScript

```ts
function maximumSubarrayXor(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        f[i][i] = nums[i];
        g[i][i] = nums[i];
        for (let j = i + 1; j < n; j++) {
            f[i][j] = f[i][j - 1] ^ f[i + 1][j];
            g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
        }
    }
    return queries.map(([l, r]) => g[l][r]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
