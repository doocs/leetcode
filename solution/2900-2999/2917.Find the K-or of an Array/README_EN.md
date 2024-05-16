---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2917.Find%20the%20K-or%20of%20an%20Array/README_EN.md
rating: 1388
source: Weekly Contest 369 Q1
tags:
    - Bit Manipulation
    - Array
---

<!-- problem:start -->

# [2917. Find the K-or of an Array](https://leetcode.com/problems/find-the-k-or-of-an-array)

[中文文档](/solution/2900-2999/2917.Find%20the%20K-or%20of%20an%20Array/README.md)

## Description

<p>You are given an integer array <code>nums</code>, and an integer <code>k</code>. Let&#39;s introduce&nbsp;<strong>K-or</strong> operation by extending the standard bitwise OR. In K-or, a bit position in the result is set to <code>1</code>&nbsp;if at least <code>k</code> numbers in <code>nums</code> have a <code>1</code> in that position.</p>

<p>Return <em>the K-or of</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [7,12,9,8,9,15], k = 4 </span></p>

<p><strong>Output:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 9 </span></p>

<p><strong>Explanation: </strong></p>

<p>Represent numbers in binary:</p>

<table style="text-indent:10px; margin-bottom=20px;">
	<tbody>
		<tr>
			<th><b>Number</b></th>
			<th>Bit 3</th>
			<th>Bit 2</th>
			<th>Bit 1</th>
			<th>Bit 0</th>
		</tr>
		<tr>
			<td><b>7</b></td>
			<td>0</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td><b>12</b></td>
			<td>1</td>
			<td>1</td>
			<td>0</td>
			<td>0</td>
		</tr>
		<tr>
			<td><b>9</b></td>
			<td>1</td>
			<td>0</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td><b>8</b></td>
			<td>1</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
		</tr>
		<tr>
			<td><b>9</b></td>
			<td>1</td>
			<td>0</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td><b>15</b></td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td><b>Result = 9</b></td>
			<td>1</td>
			<td>0</td>
			<td>0</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Bit 0 is set in 7, 9, 9, and 15. Bit 3 is set in 12, 9, 8, 9, and 15.<br />
Only bits 0 and 3 qualify. The result is <code>(1001)<sub>2</sub> = 9</code>.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [2,12,1,11,4,5], k = 6 </span></p>

<p><strong>Output:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 0 </span></p>

<p><strong>Explanation:&nbsp;</strong>No bit appears as 1 in all six array numbers, as required for K-or with <code>k = 6</code>. Thus, the result is 0.</p>
</div>

<p><strong class="example">Example 3: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> nums = [10,8,5,9,11,6,8], k = 1 </span></p>

<p><strong>Output:</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 15 </span></p>

<p><strong>Explanation: </strong> Since <code>k == 1</code>, the 1-or of the array is equal to the bitwise OR of all its elements. Hence, the answer is <code>10 OR 8 OR 5 OR 9 OR 11 OR 6 OR 8 = 15</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>31</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate each bit $i$ in the range $[0, 32)$, and count the number of numbers in the array $nums$ whose $i$-th bit is $1$, denoted as $cnt$. If $cnt \ge k$, we add $2^i$ to the answer.

After the enumeration, we return the answer.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array $nums$ and the maximum value in $nums$, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findKOr(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(x >> i & 1 for x in nums)
            if cnt >= k:
                ans |= 1 << i
        return ans
```

```java
class Solution {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int x : nums) {
                cnt += (x >> i & 1);
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findKOr(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int x : nums) {
                cnt += (x >> i & 1);
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
};
```

```go
func findKOr(nums []int, k int) (ans int) {
	for i := 0; i < 32; i++ {
		cnt := 0
		for _, x := range nums {
			cnt += (x >> i & 1)
		}
		if cnt >= k {
			ans |= 1 << i
		}
	}
	return
}
```

```ts
function findKOr(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        let cnt = 0;
        for (const x of nums) {
            cnt += (x >> i) & 1;
        }
        if (cnt >= k) {
            ans |= 1 << i;
        }
    }
    return ans;
}
```

```cs
public class Solution {
    public int FindKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            foreach (int x in nums) {
                cnt += (x >> i & 1);
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
