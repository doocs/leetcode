---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README_EN.md
rating: 2162
source: Weekly Contest 502 Q4
---

<!-- problem:start -->

# [3934. Smallest Unique Subarray](https://leetcode.com/problems/smallest-unique-subarray)

[中文文档](/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Find the <strong>minimum </strong>length of a <span data-keyword="subarray">subarray</span> that is <strong>not</strong> <strong>identical</strong> to any other <strong>subarray</strong> in <code>nums</code>.</p>

<p>Return an integer denoting the <strong>minimum possible length</strong> of such a <strong>subarray</strong>.</p>

<p>Two <strong>subarrays</strong> are considered identical if they have the same length and the same elements in corresponding positions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Subarrays of length 1: <code>[3]</code> &rarr; appears 3 times</li>
	<li>Subarrays of length 2: <code>[3, 3]</code> &rarr; appears 2 times</li>
	<li>Subarrays of length 3: <code>[3, 3, 3]</code> &rarr; appears once</li>
</ul>

<p>The subarray <code>[3, 3, 3]</code> is unique, so the smallest unique subarray length is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[2]</code> &rarr; appears 2 times</li>
	<li><code>[1]</code> &rarr; appears once</li>
	<li><code>[3]</code> &rarr; appears 2 times</li>
</ul>
The subarray <code>[1]</code> is unique, so the smallest unique subarray length is 1.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[1]</code> &rarr; appears 3 times</li>
	<li><code>[2]</code> &rarr; appears 2 times</li>
</ul>

<p>Subarrays of length 2:</p>

<ul>
	<li><code>[1, 1]</code> &rarr; appears once</li>
	<li><code>[1, 2]</code> &rarr; appears once</li>
	<li><code>[2, 2]</code> &rarr; appears once</li>
	<li><code>[2, 1]</code> &rarr; appears once</li>
</ul>
There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Rolling Hash + Binary Search

At $\textit{mid_len} = \frac{\textit{min_len} + \textit{max_len}}{2}$, for each candidate
subarray length $\textit{mid_len}$, we slide a rolling hash window along all subarrays
of such a length, recording how many times each hash value shows up.

If any hash value appears exactly once, a unique subarray of length $\textit{mid_len}$ is found.
We can thereby try to shrink $\textit{max_len}$ to $\textit{mid_len} - 1$.

Otherwise, it means that no unique subarray of that length exists, so we must raise
$\textit{min_len}$ to $\textit{mid_len} + 1$.

This approach works because once a unique subarray exists at length $l$,
any subarray with length $> l$ is also guaranteed to exist.

Time complexity is $O(n \log n)$ and space complexity is $O(n)$, where $n$ is original array length.

We have a total of $O(\log n)$ binary searches, each costing $O(n)$ rolling hash time.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        self.nums = nums

        self.base = 19
        self.modulo = 10**9 + 7
        self.powers = [1] * (len(nums) + 1)

        self.hash_values: dict[int, int] = dict()

        min_possible_len = len(nums)  # Base case.

        min_len, max_len = 1, len(nums)  # For binary search usage.

        while min_len <= max_len:
            mid_len = (min_len + max_len) // 2

            if self._check_uniqueness(mid_len):
                if mid_len < min_possible_len:
                    min_possible_len = mid_len
                max_len = mid_len - 1

            else:
                min_len = mid_len + 1

        return min_possible_len

    def _check_uniqueness(self, subarray_len: int) -> bool:
        # Only need to reset leftmost power to 1 before usage.
        self.powers[0] = 1  # Because powers are calculated by bottom-up.

        for idx in range(1, len(self.nums) + 1):
            self.powers[idx] = (self.powers[idx - 1] * self.base) % self.modulo

        current_hash = 0
        for idx in range(subarray_len):
            current_hash *= self.base
            current_hash += self.nums[idx]
            current_hash %= self.modulo

        self.hash_values.clear()  # Clear before usage.
        self.hash_values[current_hash] = 1

        for idx in range(1, len(self.nums) - subarray_len + 1):
            # Window shifts: deduct leftmost num's share from hash value.
            current_hash -= self.powers[subarray_len - 1] * self.nums[idx - 1]

            # Integrate newly added num's share into hash value.
            current_hash *= self.base
            current_hash += self.nums[idx + subarray_len - 1]
            current_hash %= self.modulo

            if current_hash not in self.hash_values.keys():
                self.hash_values.update({current_hash: 0})
            self.hash_values[current_hash] += 1

        return True if 1 in self.hash_values.values() else False
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
