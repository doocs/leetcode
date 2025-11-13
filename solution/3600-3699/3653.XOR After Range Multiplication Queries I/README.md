---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3653.XOR%20After%20Range%20Multiplication%20Queries%20I/README.md
rating: 1556
source: 第 463 场周赛 Q2
tags:
    - 数组
    - 分治
    - 模拟
---

<!-- problem:start -->

# [3653. 区间乘法查询后的异或 I](https://leetcode.cn/problems/xor-after-range-multiplication-queries-i)

[English Version](/solution/3600-3699/3653.XOR%20After%20Range%20Multiplication%20Queries%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个大小为 <code>q</code> 的二维整数数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, k<sub>i</sub>, v<sub>i</sub>]</code>。</p>

<p>对于每个查询，按以下步骤执行操作：</p>

<ul>
	<li>设定 <code>idx = l<sub>i</sub></code>。</li>
	<li>当 <code>idx &lt;= r<sub>i</sub></code> 时：
	<ul>
		<li>更新：<code>nums[idx] = (nums[idx] * v<sub>i</sub>) % (10<sup>9</sup> + 7)</code></li>
		<li>将 <code>idx += k<sub>i</sub></code>。</li>
	</ul>
	</li>
</ul>

<p>在处理完所有查询后，返回数组 <code>nums</code> 中所有元素的&nbsp;<strong>按位异或&nbsp;</strong>结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1], queries = [[0,2,1,4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>唯一的查询 <code>[0, 2, 1, 4]</code> 将下标&nbsp;0 到下标&nbsp;2 的每个元素乘以 4。</li>
	<li>数组从 <code>[1, 1, 1]</code> 变为 <code>[4, 4, 4]</code>。</li>
	<li>所有元素的异或为 <code>4 ^ 4 ^ 4 = 4</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,1,5,4], queries = [[1,4,2,3],[0,2,1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">31</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个查询 <code>[1, 4, 2, 3]</code> 将下标&nbsp;1 和 3 的元素乘以 3，数组变为 <code>[2, 9, 1, 15, 4]</code>。</li>
	<li>第二个查询 <code>[0, 2, 1, 2]</code> 将下标&nbsp;0、1 和 2 的元素乘以 2，数组变为 <code>[4, 18, 2, 15, 4]</code>。</li>
	<li>所有元素的异或为 <code>4 ^ 18 ^ 2 ^ 15 ^ 4 = 31</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= q == queries.length &lt;= 10<sup>3</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>, k<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= v<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟题目中的操作，遍历每个查询并更新数组 $\textit{nums}$ 中的对应元素。最后计算数组中所有元素的按位异或结果并返回。

时间复杂度 $O(q \times \frac{n}{k})$，其中 $n$ 是数组 $\textit{nums}$ 的长度，而 $q$ 是查询的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def xorAfterQueries(self, nums: List[int], queries: List[List[int]]) -> int:
        mod = 10**9 + 7
        for l, r, k, v in queries:
            for idx in range(l, r + 1, k):
                nums[idx] = nums[idx] * v % mod
        return reduce(xor, nums)
```

#### Java

```java
class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int mod = (int) 1e9 + 7;
        for (var q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) (1L * nums[idx] * v % mod);
            }
        }
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        const int mod = 1e9 + 7;
        for (const auto& q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = 1LL * nums[idx] * v % mod;
            }
        }
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
};
```

#### Go

```go
func xorAfterQueries(nums []int, queries [][]int) int {
	const mod = int(1e9 + 7)
	for _, q := range queries {
		l, r, k, v := q[0], q[1], q[2], q[3]
		for idx := l; idx <= r; idx += k {
			nums[idx] = nums[idx] * v % mod
		}
	}
	ans := 0
	for _, x := range nums {
		ans ^= x
	}
	return ans
}
```

#### TypeScript

```ts
function xorAfterQueries(nums: number[], queries: number[][]): number {
    const mod = 1e9 + 7;
    for (const [l, r, k, v] of queries) {
        for (let idx = l; idx <= r; idx += k) {
            nums[idx] = (nums[idx] * v) % mod;
        }
    }
    return nums.reduce((acc, x) => acc ^ x, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn xor_after_queries(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> i32 {
        let modv: i64 = 1_000_000_007;
        for q in queries {
            let (l, r, k, v) = (q[0] as usize, q[1] as usize, q[2] as usize, q[3] as i64);
            let mut idx = l;
            while idx <= r {
                nums[idx] = ((nums[idx] as i64 * v) % modv) as i32;
                idx += k;
            }
        }
        let mut ans = 0;
        for x in nums {
            ans ^= x;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
