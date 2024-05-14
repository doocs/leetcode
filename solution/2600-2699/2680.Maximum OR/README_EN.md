# [2680. Maximum OR](https://leetcode.com/problems/maximum-or)

[中文文档](/solution/2600-2699/2680.Maximum%20OR/README.md)

<!-- tags:Greedy,Bit Manipulation,Array,Prefix Sum -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>. In an operation, you can choose an element and multiply it by <code>2</code>.</p>

<p>Return <em>the maximum possible value of </em><code>nums[0] | nums[1] | ... | nums[n - 1]</code> <em>that can be obtained after applying the operation on nums at most </em><code>k</code><em> times</em>.</p>

<p>Note that <code>a | b</code> denotes the <strong>bitwise or</strong> between two integers <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,9], k = 1
<strong>Output:</strong> 30
<strong>Explanation:</strong> If we apply the operation to index 1, our new array nums will be equal to [12,18]. Thus, we return the bitwise or of 12 and 18, which is 30.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,1,2], k = 2
<strong>Output:</strong> 35
<strong>Explanation:</strong> If we apply the operation twice on index 0, we yield a new array of [32,1,2]. Thus, we return 32|1|2 = 35.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 15</code></li>
</ul>

## Solutions

### Solution 1: Greedy + Preprocessing

We notice that in order to maximize the answer, we should apply $k$ times of bitwise OR to the same number.

First, we preprocess the suffix OR value array $suf$ of the array $nums$, where $suf[i]$ represents the bitwise OR value of $nums[i], nums[i + 1], \cdots, nums[n - 1]$.

Next, we traverse the array $nums$ from left to right, and maintain the current prefix OR value $pre$. For the current position $i$, we perform $k$ times of bitwise left shift on $nums[i]$, i.e., $nums[i] \times 2^k$, and perform bitwise OR operation with $pre$ to obtain the intermediate result. Then, we perform bitwise OR operation with $suf[i + 1]$ to obtain the maximum OR value with $nums[i]$ as the last number. By enumerating all possible positions $i$, we can obtain the final answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

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
