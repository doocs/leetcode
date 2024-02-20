# [2680. 最大或值](https://leetcode.cn/problems/maximum-or)

[English Version](/solution/2600-2699/2680.Maximum%20OR/README_EN.md)

<!-- tags:贪心,位运算,数组,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code> 。每一次操作中，你可以选择一个数并将它乘&nbsp;<code>2</code>&nbsp;。</p>

<p>你最多可以进行 <code>k</code>&nbsp;次操作，请你返回<em>&nbsp;</em><code>nums[0] | nums[1] | ... | nums[n - 1]</code>&nbsp;的最大值。</p>

<p><code>a | b</code>&nbsp;表示两个整数 <code>a</code>&nbsp;和 <code>b</code>&nbsp;的 <strong>按位或</strong>&nbsp;运算。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [12,9], k = 1
<b>输出：</b>30
<b>解释：</b>如果我们对下标为 1 的元素进行操作，新的数组为 [12,18] 。此时得到最优答案为 12 和 18 的按位或运算的结果，也就是 30 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [8,1,2], k = 2
<b>输出：</b>35
<b>解释：</b>如果我们对下标 0 处的元素进行操作，得到新数组 [32,1,2] 。此时得到最优答案为 32|1|2 = 35 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 15</code></li>
</ul>

## 解法

### 方法一：贪心 + 预处理

我们注意到，为了使得答案最大，我们应该将 $k$ 次乘 $2$ 作用于同一个数。

我们先预处理出数组 $nums$ 的后缀或值数组 $suf$，其中 $suf[i]$ 表示 $nums[i], nums[i + 1], \cdots, nums[n - 1]$ 的按位或值。

接下来，我们从左到右遍历数组 $nums$，同时维护当前的前缀或值 $pre$。对于当前遍历到的位置 $i$，我们将 $nums[i]$ 乘 $2$ 的 $k$ 次方，即 $nums[i] \times 2^k$，与 $pre$ 进行按位或运算，得到的结果再与 $suf[i + 1]$ 进行按位或运算，即可得到以 $nums[i]$ 为最后一个数的最大或值。枚举所有的 $i$，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumOr(self, nums: List[int], k: int) -> int:
        n = len(nums)
        suf = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            suf[i] = suf[i + 1] | nums[i]
        ans = pre = 0
        for i, x in enumerate(nums):
            ans = max(ans, pre | (x << k) | suf[i + 1])
            pre |= x
        return ans
```

```java
class Solution {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] suf = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            suf[i] = suf[i + 1] | nums[i];
        }
        long ans = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, pre | (1L * nums[i] << k) | suf[i + 1]);
            pre |= nums[i];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumOr(vector<int>& nums, int k) {
        int n = nums.size();
        long long suf[n + 1];
        memset(suf, 0, sizeof(suf));
        for (int i = n - 1; i >= 0; --i) {
            suf[i] = suf[i + 1] | nums[i];
        }
        long long ans = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, pre | (1LL * nums[i] << k) | suf[i + 1]);
            pre |= nums[i];
        }
        return ans;
    }
};
```

```go
func maximumOr(nums []int, k int) int64 {
	n := len(nums)
	suf := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		suf[i] = suf[i+1] | nums[i]
	}
	ans, pre := 0, 0
	for i, x := range nums {
		ans = max(ans, pre|(nums[i]<<k)|suf[i+1])
		pre |= x
	}
	return int64(ans)
}
```

```ts
function maximumOr(nums: number[], k: number): number {
    const n = nums.length;
    const suf: bigint[] = Array(n + 1).fill(0n);
    for (let i = n - 1; i >= 0; i--) {
        suf[i] = suf[i + 1] | BigInt(nums[i]);
    }
    let [ans, pre] = [0, 0n];
    for (let i = 0; i < n; i++) {
        ans = Math.max(Number(ans), Number(pre | (BigInt(nums[i]) << BigInt(k)) | suf[i + 1]));
        pre |= BigInt(nums[i]);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn maximum_or(nums: Vec<i32>, k: i32) -> i64 {
        let n = nums.len();
        let mut suf = vec![0; n + 1];

        for i in (0..n).rev() {
            suf[i] = suf[i + 1] | (nums[i] as i64);
        }

        let mut ans = 0i64;
        let mut pre = 0i64;
        let k64 = k as i64;
        for i in 0..n {
            ans = ans.max(pre | ((nums[i] as i64) << k64) | suf[i + 1]);
            pre |= nums[i] as i64;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
