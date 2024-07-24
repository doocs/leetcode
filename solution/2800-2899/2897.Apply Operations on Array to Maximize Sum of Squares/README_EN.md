---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README_EN.md
rating: 2301
source: Weekly Contest 366 Q4
tags:
    - Greedy
    - Bit Manipulation
    - Array
    - Hash Table
---

<!-- problem:start -->

# [2897. Apply Operations on Array to Maximize Sum of Squares](https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-squares)

[中文文档](/solution/2800-2899/2897.Apply%20Operations%20on%20Array%20to%20Maximize%20Sum%20of%20Squares/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>You can do the following operation on the array <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two distinct indices <code>i</code> and <code>j</code> and <strong>simultaneously</strong> update the values of <code>nums[i]</code> to <code>(nums[i] AND nums[j])</code> and <code>nums[j]</code> to <code>(nums[i] OR nums[j])</code>. Here, <code>OR</code> denotes the bitwise <code>OR</code> operation, and <code>AND</code> denotes the bitwise <code>AND</code> operation.</li>
</ul>

<p>You have to choose <code>k</code> elements from the final array and calculate the sum of their <strong>squares</strong>.</p>

<p>Return <em>the <strong>maximum</strong> sum of squares you can achieve</em>.</p>

<p>Since the answer can be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,5,8], k = 2
<strong>Output:</strong> 261
<strong>Explanation:</strong> We can do the following operations on the array:
- Choose i = 0 and j = 3, then change nums[0] to (2 AND 8) = 0 and nums[3] to (2 OR 8) = 10. The resulting array is nums = [0,6,5,10].
- Choose i = 2 and j = 3, then change nums[2] to (5 AND 10) = 0 and nums[3] to (5 OR 10) = 15. The resulting array is nums = [0,6,0,15].
We can choose the elements 15 and 6 from the final array. The sum of squares is 15<sup>2</sup> + 6<sup>2</sup> = 261.
It can be shown that this is the maximum value we can get.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,5,4,7], k = 3
<strong>Output:</strong> 90
<strong>Explanation:</strong> We do not need to apply any operations.
We can choose the elements 7, 5, and 4 with a sum of squares: 7<sup>2</sup> + 5<sup>2</sup> + 4<sup>2</sup> = 90.
It can be shown that this is the maximum value we can get.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bitwise Operation + Greedy

According to the problem description, for an operation, we can change $nums[i]$ to $nums[i] \textit{ AND } nums[j]$, and change $nums[j]$ to $nums[i] \textit{ OR } nums[j]$. Let's consider the bits of the numbers. If two bits are both $1$ or both $0$, the result of the operation will not change the bits. If two bits are different, the result of the operation will change the bits to $0$ and $1$, respectively. Therefore, we can move $1$ bits to $0$ bits, but not vice versa.

We can use an array $cnt$ to count the number of $1$ bits in each position, and then select $k$ numbers from them. To maximize the sum of squares, we should choose the largest numbers as much as possible. This is because, assuming the sum of squares of two numbers is $a^2 + b^2$ (where $a \gt b$), changing them to $(a + c)^2 + (b - c)^2 = a^2 + b^2 + 2c(a - b) + 2c^2 \gt a^2 + b^2$ will increase the sum of squares. Therefore, to maximize the sum of squares, we should choose the largest number.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $M$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        mod = 10**9 + 7
        cnt = [0] * 31
        for x in nums:
            for i in range(31):
                if x >> i & 1:
                    cnt[i] += 1
        ans = 0
        for _ in range(k):
            x = 0
            for i in range(31):
                if cnt[i]:
                    x |= 1 << i
                    cnt[i] -= 1
            ans = (ans + x * x) % mod
        return ans
```

#### Java

```java
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = new int[31];
        for (int x : nums) {
            for (int i = 0; i < 31; ++i) {
                if ((x >> i & 1) == 1) {
                    ++cnt[i];
                }
            }
        }
        long ans = 0;
        while (k-- > 0) {
            int x = 0;
            for (int i = 0; i < 31; ++i) {
                if (cnt[i] > 0) {
                    x |= 1 << i;
                    --cnt[i];
                }
            }
            ans = (ans + 1L * x * x) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        int cnt[31]{};
        for (int x : nums) {
            for (int i = 0; i < 31; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        while (k--) {
            int x = 0;
            for (int i = 0; i < 31; ++i) {
                if (cnt[i]) {
                    x |= 1 << i;
                    --cnt[i];
                }
            }
            ans = (ans + 1LL * x * x) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int, k int) (ans int) {
	cnt := [31]int{}
	for _, x := range nums {
		for i := 0; i < 31; i++ {
			if x>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	const mod int = 1e9 + 7
	for ; k > 0; k-- {
		x := 0
		for i := 0; i < 31; i++ {
			if cnt[i] > 0 {
				x |= 1 << i
				cnt[i]--
			}
		}
		ans = (ans + x*x) % mod
	}
	return
}
```

#### TypeScript

```ts
function maxSum(nums: number[], k: number): number {
    const cnt: number[] = Array(31).fill(0);
    for (const x of nums) {
        for (let i = 0; i < 31; ++i) {
            if ((x >> i) & 1) {
                ++cnt[i];
            }
        }
    }
    let ans = 0n;
    const mod = 1e9 + 7;
    while (k-- > 0) {
        let x = 0;
        for (let i = 0; i < 31; ++i) {
            if (cnt[i] > 0) {
                x |= 1 << i;
                --cnt[i];
            }
        }
        ans = (ans + BigInt(x) * BigInt(x)) % BigInt(mod);
    }
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
