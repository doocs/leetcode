---
comments: true
difficulty: Easy
rating: 1232
source: Biweekly Contest 193 Q1
---

<!-- problem:start -->

# [4074. Maximum Product Pair With Target Sum](https://leetcode.com/problems/maximum-product-pair-with-target-sum)

[中文文档](/solution/4000-4099/4074.Maximum%20Product%20Pair%20With%20Target%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>A pair of <strong>distinct</strong> indices <code>(i, j)</code> is <strong>valid</strong> if:</p>

<ul>
	<li><code>nums[i] + nums[j] == target</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>Return a <strong>valid</strong> pair <code>[i, j]</code> whose <strong>product</strong> <code>nums[i] * nums[j]</code> is <strong>maximum</strong> among all valid pairs. If no valid pair exists, return <code>[-1, -1]</code>.</p>

<p>If multiple valid pairs achieve the <strong>maximum</strong> product, you may return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], target = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 valid pairs:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">No.</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">Sum</th>
			<th scope="col" style="text-align:center;">Product</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(3, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">4</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(2, 1)</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">5</td>
			<td style="text-align:center;">6</td>
		</tr>
	</tbody>
</table>

<p>Both pairs sum to 5 and satisfy <code>nums[i] &gt; nums[j]</code>. The second pair has the larger product, <code>3 * 2 = 6</code>, so the answer is <code>[2, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,-1,4,2], target = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 valid pairs:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">No.</th>
			<th scope="col" style="text-align:center;"><code>(i, j)</code></th>
			<th scope="col" style="text-align:center;"><code>nums[i]</code></th>
			<th scope="col" style="text-align:center;"><code>nums[j]</code></th>
			<th scope="col" style="text-align:center;">Sum</th>
			<th scope="col" style="text-align:center;">Product</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">(2, 0)</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;">-3</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-12</td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">(3, 1)</td>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">-1</td>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">-2</td>
		</tr>
	</tbody>
</table>

<p>Since <code>-2 &gt; -12</code>, the pair at indices <code>(3, 1)</code> is chosen and the answer is <code>[3, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,5], target = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong>No valid pair exists, since <code>nums[0]</code> and <code>nums[1]</code> sum to 6 but are equal.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i], target &lt;= 100</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

<!-- thinking:start -->

> **Thinking**
>
> With $n\le 100$, checking every pair of indices is already fast enough. Negative values need separate care: once the sum is fixed, moving the two numbers closer to $\textit{target}/2$ does not make the product larger, so each product is compared directly.
>
> The complement of $x$ is always $y=\textit{target}-x$. A valid pair also requires the two values to be different, so the case $x=y$ can be skipped.
>
> The product depends only on those two values. While scanning from left to right, a hash map stores the latest index of each value; when $x$ is seen, look up $y$ and keep the pair with the larger product.

<!-- thinking:end -->

Use a hash map $\textit{pos}$ to store the latest index of each value. Scan $\textit{nums}[i]=x$ from left to right and set $y=\textit{target}-x$. If $x\neq y$, $y$ has already occurred, and $x\cdot y$ is greater than the best product so far, update the answer and place the larger value first so that $\textit{nums}[i]>\textit{nums}[j]$. If no valid pair is found, the answer stays $[-1,-1]$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
from math import inf


class Solution:
    def maxProductPair(self, nums: list[int], target: int) -> list[int]:
        pos = {}
        mx = -inf
        ans = [-1, -1]
        for i, x in enumerate(nums):
            y = target - x
            if x != y and x * y > mx and y in pos:
                j = pos[y]
                mx = x * y
                ans = [i, j] if x > y else [j, i]
            pos[x] = i
        return ans
```

#### Java

```java
class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        int mx = Integer.MIN_VALUE;
        int[] ans = {-1, -1};
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.containsKey(y)) {
                int j = pos.get(y);
                mx = x * y;
                ans = x > y ? new int[] {i, j} : new int[] {j, i};
            }
            pos.put(x, i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maxProductPair(vector<int>& nums, int target) {
        unordered_map<int, int> pos;
        int mx = INT_MIN;
        vector<int> ans = {-1, -1};
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.contains(y)) {
                int j = pos[y];
                mx = x * y;
                ans = x > y ? vector<int>{i, j} : vector<int>{j, i};
            }
            pos[x] = i;
        }
        return ans;
    }
};
```

#### Go

```go
func maxProductPair(nums []int, target int) []int {
	pos := map[int]int{}
	mx := -int(^uint(0)>>1) - 1
	ans := []int{-1, -1}
	for i, x := range nums {
		y := target - x
		if j, ok := pos[y]; x != y && ok && x*y > mx {
			mx = x * y
			if x > y {
				ans = []int{i, j}
			} else {
				ans = []int{j, i}
			}
		}
		pos[x] = i
	}
	return ans
}
```

#### TypeScript

```ts
function maxProductPair(nums: number[], target: number): number[] {
    const pos = new Map<number, number>();
    let mx = -Infinity;
    let ans = [-1, -1];
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i],
            y = target - x;
        if (x !== y && x * y > mx && pos.has(y)) {
            const j = pos.get(y)!;
            mx = x * y;
            ans = x > y ? [i, j] : [j, i];
        }
        pos.set(x, i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
