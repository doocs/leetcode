---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3430.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subarrays/README_EN.md
rating: 2644
source: Weekly Contest 433 Q4
tags:
    - Stack
    - Array
    - Math
    - Monotonic Stack
---

<!-- problem:start -->

# [3430. Maximum and Minimum Sums of at Most Size K Subarrays](https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays)

[中文文档](/solution/3400-3499/3430.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>. Return the sum of the <strong>maximum</strong> and <strong>minimum</strong> elements of all <span data-keyword="subarray-nonempty">subarrays</span> with <strong>at most</strong> <code>k</code> elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarrays of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subarray</b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">20</td>
		</tr>
	</tbody>
</table>

<p>The output would be 20.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-3,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-6</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarrays of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subarray</b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[-3]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">-6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, -3]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">-2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[-3, 1]</code></td>
			<td style="border: 1px solid black;">-3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">-2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">-6</td>
		</tr>
	</tbody>
</table>

<p>The output would be -6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 80000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var minMaxSubarraySum = function (nums, k) {
    const computeSum = (nums, k, isMin) => {
        const n = nums.length;
        const prev = Array(n).fill(-1);
        const next = Array(n).fill(n);
        let stk = [];

        if (isMin) {
            for (let i = 0; i < n; i++) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] >= nums[i]) {
                    stk.pop();
                }
                prev[i] = stk.length > 0 ? stk[stk.length - 1] : -1;
                stk.push(i);
            }
            stk = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] > nums[i]) {
                    stk.pop();
                }
                next[i] = stk.length > 0 ? stk[stk.length - 1] : n;
                stk.push(i);
            }
        } else {
            for (let i = 0; i < n; i++) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] <= nums[i]) {
                    stk.pop();
                }
                prev[i] = stk.length > 0 ? stk[stk.length - 1] : -1;
                stk.push(i);
            }
            stk = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] < nums[i]) {
                    stk.pop();
                }
                next[i] = stk.length > 0 ? stk[stk.length - 1] : n;
                stk.push(i);
            }
        }

        let totalSum = 0;
        for (let i = 0; i < n; i++) {
            const left = prev[i];
            const right = next[i];
            const a = left + 1;
            const b = i;
            const c = i;
            const d = right - 1;

            let start1 = Math.max(a, i - k + 1);
            let endCandidate1 = d - k + 1;
            let upper1 = Math.min(b, endCandidate1);

            let sum1 = 0;
            if (upper1 >= start1) {
                const termCount = upper1 - start1 + 1;
                const first = start1;
                const last = upper1;
                const indexSum = (last * (last + 1)) / 2 - ((first - 1) * first) / 2;
                const constantSum = (k - i) * termCount;
                sum1 = indexSum + constantSum;
            }

            let start2 = upper1 + 1;
            let end2 = b;
            start2 = Math.max(start2, a);
            end2 = Math.min(end2, b);

            let sum2 = 0;
            if (start2 <= end2) {
                const count = end2 - start2 + 1;
                const term = d - i + 1;
                sum2 = term * count;
            }

            totalSum += nums[i] * (sum1 + sum2);
        }

        return totalSum;
    };

    const minSum = computeSum(nums, k, true);
    const maxSum = computeSum(nums, k, false);
    return minSum + maxSum;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
