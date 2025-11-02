---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3732.Maximum%20Product%20of%20Three%20Elements%20After%20One%20Replacement/README_EN.md
---

<!-- problem:start -->

# [3732. Maximum Product of Three Elements After One Replacement](https://leetcode.com/problems/maximum-product-of-three-elements-after-one-replacement)

[中文文档](/solution/3700-3799/3732.Maximum%20Product%20of%20Three%20Elements%20After%20One%20Replacement/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You <strong>must</strong> replace <strong>exactly one</strong> element in the array with <strong>any</strong> integer value in the range <code>[-10<sup>5</sup>, 10<sup>5</sup>]</code> (inclusive).</p>

<p>After performing this single replacement, determine the <strong>maximum possible product</strong> of <strong>any three</strong> elements at <strong>distinct indices</strong> from the modified array.</p>

<p>Return an integer denoting the <strong>maximum product</strong> achievable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-5,7,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">3500000</span></p>

<p><strong>Explanation:</strong></p>

<p>Replacing 0 with -10<sup>5</sup> gives the array <code>[-5, 7, -10<sup>5</sup>]</code>, which has a product <code>(-5) * 7 * (-10<sup>5</sup>) = 3500000</code>. The maximum product is 3500000.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-4,-2,-1,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1200000</span></p>

<p><strong>Explanation:</strong></p>

<p>Two ways to achieve the maximum product include:</p>

<ul>
	<li><code>[-4, -2, -3]</code> &rarr; replace -2 with 10<sup>5</sup> &rarr; product = <code>(-4) * 10<sup>5</sup> * (-3) = 1200000</code>.</li>
	<li><code>[-4, -1, -3]</code> &rarr; replace -1 with 10<sup>5</sup> &rarr; product = <code>(-4) * 10<sup>5</sup> * (-3) = 1200000</code>.</li>
</ul>
The maximum product is 1200000.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,10,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no way to replace an element with another integer and not have a 0 in the array. Hence, the product of all three elements will always be 0, and the maximum product is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

According to the problem description, we can replace one element in the array with any integer in the range $[-10^5, 10^5]$. To maximize the product of three elements, we can consider the following cases:

1. Select the two smallest elements in the array and replace the third element with $10^5$.
2. Select the two largest elements in the array and replace the third element with $10^5$.
3. Select the smallest element and the two largest elements in the array, and replace the middle element with $-10^5$.

The maximum product among these three cases is the answer.

Therefore, we can first sort the array, then calculate the products for the above three cases, and return the maximum value among them.

The time complexity is $O(n \log n)$ and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        nums.sort()
        a, b = nums[0], nums[1]
        c, d = nums[-2], nums[-1]
        x = 10**5
        return max(a * b * x, c * d * x, a * d * -x)
```

#### Java

```java
class Solution {
    public long maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long a = nums[0], b = nums[1];
        long c = nums[n - 2], d = nums[n - 1];
        final int x = 100000;
        return Math.max(Math.max(a * b * x, c * d * x), -a * d * x);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long a = nums[0], b = nums[1];
        long long c = nums[n - 2], d = nums[n - 1];
        const int x = 100000;
        return max({a * b * x, c * d * x, -a * d * x});
    }
};
```

#### Go

```go
func maxProduct(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	a, b := int64(nums[0]), int64(nums[1])
	c, d := int64(nums[n-2]), int64(nums[n-1])
	const x int64 = 100000
	return max(a*b*x, c*d*x, -a*d*x)
}
```

#### TypeScript

```ts
function maxProduct(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const [a, b] = [nums[0], nums[1]];
    const [c, d] = [nums[n - 2], nums[n - 1]];
    const x = 100000;
    return Math.max(a * b * x, c * d * x, -a * d * x);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
