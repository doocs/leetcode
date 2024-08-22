---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README.md
rating: 2267
source: 第 125 场双周赛 Q4
tags:
    - 贪心
    - 位运算
    - 树
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [3068. 最大节点价值之和](https://leetcode.cn/problems/find-the-maximum-sum-of-node-values)

[English Version](/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <code>n</code>&nbsp;个节点的 <strong>无向</strong>&nbsp;树，节点从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;编号。树以长度为 <code>n - 1</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的二维整数数组 <code>edges</code>&nbsp;的形式给你，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示树中节点&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条边。同时给你一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;和一个长度为 <code>n</code>&nbsp;下标从&nbsp;<strong>0</strong>&nbsp;开始的&nbsp;<strong>非负</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;表示节点 <code>i</code>&nbsp;的 <strong>价值</strong>&nbsp;。</p>

<p>Alice&nbsp;想 <strong>最大化</strong>&nbsp;树中所有节点价值之和。为了实现这一目标，Alice 可以执行以下操作 <strong>任意</strong>&nbsp;次（<strong>包括</strong><strong>&nbsp;0 次</strong>）：</p>

<ul>
	<li>选择连接节点&nbsp;<code>u</code>&nbsp;和&nbsp;<code>v</code>&nbsp;的边&nbsp;<code>[u, v]</code>&nbsp;，并将它们的值更新为：

    <ul>
    	<li><code>nums[u] = nums[u] XOR k</code></li>
    	<li><code>nums[v] = nums[v] XOR k</code></li>
    </ul>
    </li>

</ul>

<p>请你返回 Alice 通过执行以上操作 <strong>任意次</strong>&nbsp;后，可以得到所有节点 <strong>价值之和</strong>&nbsp;的 <strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
<b>输出：</b>6
<b>解释：</b>Alice 可以通过一次操作得到最大价值和 6 ：
- 选择边 [0,2] 。nums[0] 和 nums[2] 都变为：1 XOR 3 = 2 ，数组 nums 变为：[1,2,1] -&gt; [2,2,2] 。
所有节点价值之和为 2 + 2 + 2 = 6 。
6 是可以得到最大的价值之和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2024-01-09-220017.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 300px; height: 239px;" /></p>

<pre>
<b>输入：</b>nums = [2,3], k = 7, edges = [[0,1]]
<b>输出：</b>9
<b>解释：</b>Alice 可以通过一次操作得到最大和 9 ：
- 选择边 [0,1] 。nums[0] 变为：2 XOR 7 = 5 ，nums[1] 变为：3 XOR 7 = 4 ，数组 nums 变为：[2,3] -&gt; [5,4] 。
所有节点价值之和为 5 + 4 = 9 。
9 是可以得到最大的价值之和。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
<b>输出：</b>42
<b>解释：</b>Alice 不需要执行任何操作，就可以得到最大价值之和 42 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;构成一棵合法的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

对于任意一个数 $x$，与 $k$ 异或偶数次后，值不变。所以，对于一棵树的任意一条路径，我们将路径上所有的边都进行操作，那么该路径上除了起点和终点外，其他节点的值都不会改变。

另外，无论进行了多少次操作，总会有偶数个元素异或了 $k$，其余元素不变。

因此，问题转化为：对于数组 $\textit{nums}$，任选其中偶数个元素异或 $k$，使得和最大。

我们可以使用动态规划解决这个问题。设 $f_0$ 表示当前有偶数个元素异或了 $k$ 时的最大和，而 $f_1$ 表示当前有奇数个元素异或了 $k$ 时的最大和。那么状态转移方程为：

$$
\begin{aligned}
f_0 &= \max(f_0 + x, f_1 + (x \oplus k)) \\
f_1 &= \max(f_1 + x, f_0 + (x \oplus k))
\end{aligned}
$$

其中 $x$ 表示当前元素的值。

我们遍历数组 $\textit{nums}$，根据上述状态转移方程更新 $f_0$ 和 $f_1$，最后返回 $f_0$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumValueSum(self, nums: List[int], k: int, edges: List[List[int]]) -> int:
        f0, f1 = 0, -inf
        for x in nums:
            f0, f1 = max(f0 + x, f1 + (x ^ k)), max(f1 + x, f0 + (x ^ k))
        return f0
```

#### Java

```java
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long tmp = f0;
            f0 = Math.max(f0 + x, f1 + (x ^ k));
            f1 = Math.max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumValueSum(vector<int>& nums, int k, vector<vector<int>>& edges) {
        long long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long long tmp = f0;
            f0 = max(f0 + x, f1 + (x ^ k));
            f1 = max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
};
```

#### Go

```go
func maximumValueSum(nums []int, k int, edges [][]int) int64 {
	f0, f1 := 0, -0x3f3f3f3f
	for _, x := range nums {
		f0, f1 = max(f0+x, f1+(x^k)), max(f1+x, f0+(x^k))
	}
	return int64(f0)
}
```

#### TypeScript

```ts
function maximumValueSum(nums: number[], k: number, edges: number[][]): number {
    let [f0, f1] = [0, -Infinity];
    for (const x of nums) {
        [f0, f1] = [Math.max(f0 + x, f1 + (x ^ k)), Math.max(f1 + x, f0 + (x ^ k))];
    }
    return f0;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_value_sum(nums: Vec<i32>, k: i32, edges: Vec<Vec<i32>>) -> i64 {
        let mut f0: i64 = 0;
        let mut f1: i64 = i64::MIN;

        for &x in &nums {
            let tmp = f0;
            f0 = std::cmp::max(f0 + x as i64, f1 + (x ^ k) as i64);
            f1 = std::cmp::max(f1 + x as i64, tmp + (x ^ k) as i64);
        }

        f0
    }
}
```

#### C#

```cs
public class Solution {
    public long MaximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = -0x3f3f3f3f;
        foreach (int x in nums) {
            long tmp = f0;
            f0 = Math.Max(f0 + x, f1 + (x ^ k));
            f1 = Math.Max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
