---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2760.Longest%20Even%20Odd%20Subarray%20With%20Threshold/README_EN.md
rating: 1420
source: Weekly Contest 352 Q1
tags:
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [2760. Longest Even Odd Subarray With Threshold](https://leetcode.com/problems/longest-even-odd-subarray-with-threshold)

[中文文档](/solution/2700-2799/2760.Longest%20Even%20Odd%20Subarray%20With%20Threshold/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>threshold</code>.</p>

<p>Find the length of the <strong>longest subarray</strong> of <code>nums</code> starting at index <code>l</code> and ending at index <code>r</code> <code>(0 &lt;= l &lt;= r &lt; nums.length)</code> that satisfies the following conditions:</p>

<ul>
	<li><code>nums[l] % 2 == 0</code></li>
	<li>For all indices <code>i</code> in the range <code>[l, r - 1]</code>, <code>nums[i] % 2 != nums[i + 1] % 2</code></li>
	<li>For all indices <code>i</code> in the range <code>[l, r]</code>, <code>nums[i] &lt;= threshold</code></li>
</ul>

<p>Return <em>an integer denoting the length of the longest such subarray.</em></p>

<p><strong>Note:</strong> A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,5,4], threshold = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, we can select the subarray that starts at l = 1 and ends at r = 3 =&gt; [2,5,4]. This subarray satisfies the conditions.
Hence, the answer is the length of the subarray, 3. We can show that 3 is the maximum possible achievable length.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2], threshold = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, we can select the subarray that starts at l = 1 and ends at r = 1 =&gt; [2]. 
It satisfies all the conditions and we can show that 1 is the maximum possible achievable length.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4,5], threshold = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, we can select the subarray that starts at l = 0 and ends at r = 2 =&gt; [2,3,4]. 
It satisfies all the conditions.
Hence, the answer is the length of the subarray, 3. We can show that 3 is the maximum possible achievable length.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100 </code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100 </code></li>
	<li><code>1 &lt;= threshold &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We enumerate all $l$ in the range $[0,..n-1]$. If $nums[l]$ satisfies $nums[l] \bmod 2 = 0$ and $nums[l] \leq threshold$, then we start from $l+1$ to find the largest $r$ that meets the condition. At this time, the length of the longest odd-even subarray with $nums[l]$ as the left endpoint is $r - l$. We take the maximum of all $r - l$ as the answer.

The time complexity is $O(n^2)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        ans, n = 0, len(nums)
        for l in range(n):
            if nums[l] % 2 == 0 and nums[l] <= threshold:
                r = l + 1
                while r < n and nums[r] % 2 != nums[r - 1] % 2 and nums[r] <= threshold:
                    r += 1
                ans = max(ans, r - l)
        return ans
```

```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0, n = nums.length;
        for (int l = 0; l < n; ++l) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = Math.max(ans, r - l);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestAlternatingSubarray(vector<int>& nums, int threshold) {
        int ans = 0, n = nums.size();
        for (int l = 0; l < n; ++l) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = max(ans, r - l);
            }
        }
        return ans;
    }
};
```

```go
func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	n := len(nums)
	for l := range nums {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
		}
	}
	return
}
```

```ts
function longestAlternatingSubarray(nums: number[], threshold: number): number {
    const n = nums.length;
    let ans = 0;
    for (let l = 0; l < n; ++l) {
        if (nums[l] % 2 === 0 && nums[l] <= threshold) {
            let r = l + 1;
            while (r < n && nums[r] % 2 !== nums[r - 1] % 2 && nums[r] <= threshold) {
                ++r;
            }
            ans = Math.max(ans, r - l);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Optimized Enumeration

We notice that the problem actually divides the array into several disjoint subarrays that meet the condition. We only need to find the longest one among these subarrays. Therefore, when enumerating $l$ and $r$, we don't need to backtrack, we just need to traverse from left to right once.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        ans, l, n = 0, 0, len(nums)
        while l < n:
            if nums[l] % 2 == 0 and nums[l] <= threshold:
                r = l + 1
                while r < n and nums[r] % 2 != nums[r - 1] % 2 and nums[r] <= threshold:
                    r += 1
                ans = max(ans, r - l)
                l = r
            else:
                l += 1
        return ans
```

```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0;
        for (int l = 0, n = nums.length; l < n;) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = Math.max(ans, r - l);
                l = r;
            } else {
                ++l;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestAlternatingSubarray(vector<int>& nums, int threshold) {
        int ans = 0;
        for (int l = 0, n = nums.size(); l < n;) {
            if (nums[l] % 2 == 0 && nums[l] <= threshold) {
                int r = l + 1;
                while (r < n && nums[r] % 2 != nums[r - 1] % 2 && nums[r] <= threshold) {
                    ++r;
                }
                ans = max(ans, r - l);
                l = r;
            } else {
                ++l;
            }
        }
        return ans;
    }
};
```

```go
func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	for l, n := 0, len(nums); l < n; {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
			l = r
		} else {
			l++
		}
	}
	return
}
```

```ts
function longestAlternatingSubarray(nums: number[], threshold: number): number {
    const n = nums.length;
    let ans = 0;
    for (let l = 0; l < n; ) {
        if (nums[l] % 2 === 0 && nums[l] <= threshold) {
            let r = l + 1;
            while (r < n && nums[r] % 2 !== nums[r - 1] % 2 && nums[r] <= threshold) {
                ++r;
            }
            ans = Math.max(ans, r - l);
            l = r;
        } else {
            ++l;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
