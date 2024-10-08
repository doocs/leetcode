---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3309.Maximum%20Possible%20Number%20by%20Binary%20Concatenation/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Enumeration
---

<!-- problem:start -->

# [3309. Maximum Possible Number by Binary Concatenation](https://leetcode.com/problems/maximum-possible-number-by-binary-concatenation)

[中文文档](/solution/3300-3399/3309.Maximum%20Possible%20Number%20by%20Binary%20Concatenation/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> of size 3.</p>

<p>Return the <strong>maximum</strong> possible number whose <em>binary representation</em> can be formed by <strong>concatenating</strong> the <em>binary representation</em> of <strong>all</strong> elements in <code>nums</code> in some order.</p>

<p><strong>Note</strong> that the binary representation of any number <em>does not</em> contain leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> 30</p>

<p><strong>Explanation:</strong></p>

<p>Concatenate the numbers in the order <code>[3, 1, 2]</code> to get the result <code>&quot;11110&quot;</code>, which is the binary representation of 30.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,8,16]</span></p>

<p><strong>Output:</strong> 1296</p>

<p><strong>Explanation:</strong></p>

<p>Concatenate the numbers in the order <code>[2, 8, 16]</code> to get the result <code>&quot;10100010000&quot;</code>, which is the binary representation of 1296.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == 3</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 127</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

According to the problem description, the length of the array $\textit{nums}$ is $3$. We can enumerate all permutations of $\textit{nums}$, which has $3! = 6$ permutations. Then, we convert the elements of the permuted array into binary strings, concatenate these binary strings, and finally convert the concatenated binary string into a decimal number to get the maximum value.

The time complexity is $O(\log M)$, where $M$ represents the maximum value of the elements in $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxGoodNumber(self, nums: List[int]) -> int:
        ans = 0
        for arr in permutations(nums):
            num = int("".join(bin(i)[2:] for i in arr), 2)
            ans = max(ans, num)
        return ans
```

#### Java

```java
class Solution {
    private int[] nums;

    public int maxGoodNumber(int[] nums) {
        this.nums = nums;
        int ans = f(0, 1, 2);
        ans = Math.max(ans, f(0, 2, 1));
        ans = Math.max(ans, f(1, 0, 2));
        ans = Math.max(ans, f(1, 2, 0));
        ans = Math.max(ans, f(2, 0, 1));
        ans = Math.max(ans, f(2, 1, 0));
        return ans;
    }

    private int f(int i, int j, int k) {
        String a = Integer.toBinaryString(nums[i]);
        String b = Integer.toBinaryString(nums[j]);
        String c = Integer.toBinaryString(nums[k]);
        return Integer.parseInt(a + b + c, 2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxGoodNumber(vector<int>& nums) {
        int ans = 0;
        auto f = [&](vector<int>& nums) {
            int res = 0;
            vector<int> t;
            for (int x : nums) {
                for (; x; x >>= 1) {
                    t.push_back(x & 1);
                }
            }
            while (t.size()) {
                res = res * 2 + t.back();
                t.pop_back();
            }
            return res;
        };
        for (int i = 0; i < 6; ++i) {
            ans = max(ans, f(nums));
            next_permutation(nums.begin(), nums.end());
        }
        return ans;
    }
};
```

#### Go

```go
func maxGoodNumber(nums []int) int {
	f := func(i, j, k int) int {
		a := strconv.FormatInt(int64(nums[i]), 2)
		b := strconv.FormatInt(int64(nums[j]), 2)
		c := strconv.FormatInt(int64(nums[k]), 2)
		res, _ := strconv.ParseInt(a+b+c, 2, 64)
		return int(res)
	}
	ans := f(0, 1, 2)
	ans = max(ans, f(0, 2, 1))
	ans = max(ans, f(1, 0, 2))
	ans = max(ans, f(1, 2, 0))
	ans = max(ans, f(2, 0, 1))
	ans = max(ans, f(2, 1, 0))
	return ans
}
```

#### TypeScript

```ts
function maxGoodNumber(nums: number[]): number {
    const f = (i: number, j: number, k: number): number => {
        const a = nums[i].toString(2);
        const b = nums[j].toString(2);
        const c = nums[k].toString(2);
        const res = parseInt(a + b + c, 2);
        return res;
    };

    let ans = f(0, 1, 2);
    ans = Math.max(ans, f(0, 2, 1));
    ans = Math.max(ans, f(1, 0, 2));
    ans = Math.max(ans, f(1, 2, 0));
    ans = Math.max(ans, f(2, 0, 1));
    ans = Math.max(ans, f(2, 1, 0));

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
