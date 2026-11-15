---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4111. Count Consistent Subarrays I](https://leetcode.com/problems/count-consistent-subarrays-i)

[中文文档](/solution/4100-4199/4111.Count%20Consistent%20Subarrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A subarray is called <strong>consistent</strong> if the <strong>bitwise AND</strong> of all its elements is equal to the <strong>minimum</strong> value of the subarray.</p>

<p>Return the number of <strong>consistent subarrays</strong> in <code>nums</code>​​​​​​​.</p>

<p>A <strong>subarray</strong> is a contiguous, non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The following subarrays are consistent:</p>

<table style="border: 1px solid black; text-align: center;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Values</th>
			<th style="border: 1px solid black;">Bitwise AND</th>
			<th style="border: 1px solid black;">Minimum Value</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..0]</td>
			<td style="border: 1px solid black;">[5]</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..1]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[2..2]</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[3..3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..2]</td>
			<td style="border: 1px solid black;">[3, 1]</td>
			<td style="border: 1px solid black;">3 AND 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..2]</td>
			<td style="border: 1px solid black;">[5, 3, 1]</td>
			<td style="border: 1px solid black;">5 AND 3 AND 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Therefore, the number of consistent subarrays is 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>For every subarray, both the bitwise AND and the minimum value are equal to 1. Therefore, the answer is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subarray is <code>[0]</code>, whose bitwise AND and minimum value are both 0. Therefore, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

<!-- thinking:start -->

> **Thinking**
>
> $n$ is at most $100$. Recomputing the bitwise AND and the minimum of every subarray from scratch takes $O(n^3)$ time and still fits. Each step that extends the right endpoint repeats that work over the whole segment.
>
> The bitwise AND and the minimum of $\textit{nums}[i..j]$ are determined by $\textit{nums}[i..j-1]$ together with $\textit{nums}[j]$, so each of them updates in $O(1)$ time when the right endpoint moves.
>
> Fix the left endpoint $i$ and scan the right endpoint $j$ from $i$ to $n-1$. Keep the running AND in $x$ and the running minimum in $y$, and count the subarray when $x=y$.

<!-- thinking:end -->

We enumerate the left endpoint $i$. Let $x$ and $y$ be the bitwise AND and the minimum of the subarray $\textit{nums}[i..j]$, both initially equal to $\textit{nums}[i]$. The right endpoint $j$ runs from $i$ to $n-1$. Each step sets $x \mathrel{\&}= \textit{nums}[j]$ and updates $y$ to $\min(y,\textit{nums}[j])$. When $x=y$, the current subarray is consistent and the answer increases by one.

The time complexity is $O(n^2)$, and the space complexity is $O(1)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countConsistent(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i, x in enumerate(nums):
            y = x
            for j in range(i, n):
                x &= nums[j]
                y = min(y, nums[j])
                if x == y:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = Math.min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countConsistent(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countConsistent(nums []int) int {
	n := len(nums)
	ans := 0
	for i, x := range nums {
		y := x
		for j := i; j < n; j++ {
			x &= nums[j]
			y = min(y, nums[j])
			if x == y {
				ans++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countConsistent(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        let y = x;
        for (let j = i; j < n; j++) {
            x &= nums[j];
            y = Math.min(y, nums[j]);
            if (x === y) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
