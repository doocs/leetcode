---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3079.Find%20the%20Sum%20of%20Encrypted%20Integers/README_EN.md
rating: 1190
source: Biweekly Contest 126 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3079. Find the Sum of Encrypted Integers](https://leetcode.com/problems/find-the-sum-of-encrypted-integers)

[中文文档](/solution/3000-3099/3079.Find%20the%20Sum%20of%20Encrypted%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> containing <strong>positive</strong> integers. We define a function <code>encrypt</code> such that <code>encrypt(x)</code> replaces <strong>every</strong> digit in <code>x</code> with the <strong>largest</strong> digit in <code>x</code>. For example, <code>encrypt(523) = 555</code> and <code>encrypt(213) = 333</code>.</p>

<p>Return <em>the <strong>sum </strong>of encrypted elements</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,2,3]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">6</span></p>

<p><strong>Explanation:</strong> The encrypted elements are&nbsp;<code>[1,2,3]</code>. The sum of encrypted elements is <code>1 + 2 + 3 == 6</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [10,21,31]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">66</span></p>

<p><strong>Explanation:</strong> The encrypted elements are <code>[11,22,33]</code>. The sum of encrypted elements is <code>11 + 22 + 33 == 66</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We directly simulate the encryption process by defining a function $encrypt(x)$, which replaces each digit in an integer $x$ with the maximum digit in $x$. The implementation of the function is as follows:

We can obtain each digit of $x$ by continuously taking the modulus and integer division of $x$ by $10$, and find the maximum digit, denoted as $mx$. During the loop, we can also use a variable $p$ to record the base number of $mx$, i.e., $p = 1, 11, 111, \cdots$. Finally, return $mx \times p$.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array, and $M$ is the maximum value in the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sumOfEncryptedInt(self, nums: List[int]) -> int:
        def encrypt(x: int) -> int:
            mx = p = 0
            while x:
                x, v = divmod(x, 10)
                mx = max(mx, v)
                p = p * 10 + 1
            return mx * p

        return sum(encrypt(x) for x in nums)
```

```java
class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }

    private int encrypt(int x) {
        int mx = 0, p = 0;
        for (; x > 0; x /= 10) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    }
}
```

```cpp
class Solution {
public:
    int sumOfEncryptedInt(vector<int>& nums) {
        auto encrypt = [&](int x) {
            int mx = 0, p = 0;
            for (; x; x /= 10) {
                mx = max(mx, x % 10);
                p = p * 10 + 1;
            }
            return mx * p;
        };
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }
};
```

```go
func sumOfEncryptedInt(nums []int) (ans int) {
	encrypt := func(x int) int {
		mx, p := 0, 0
		for ; x > 0; x /= 10 {
			mx = max(mx, x%10)
			p = p*10 + 1
		}
		return mx * p
	}
	for _, x := range nums {
		ans += encrypt(x)
	}
	return
}
```

```ts
function sumOfEncryptedInt(nums: number[]): number {
    const encrypt = (x: number): number => {
        let [mx, p] = [0, 0];
        for (; x > 0; x = Math.floor(x / 10)) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    };
    return nums.reduce((acc, x) => acc + encrypt(x), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
