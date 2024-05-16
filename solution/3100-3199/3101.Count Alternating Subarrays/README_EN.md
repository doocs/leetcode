---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3101.Count%20Alternating%20Subarrays/README_EN.md
rating: 1404
source: Weekly Contest 391 Q3
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3101. Count Alternating Subarrays](https://leetcode.com/problems/count-alternating-subarrays)

[中文文档](/solution/3100-3199/3101.Count%20Alternating%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="binary-array">binary array</span> <code>nums</code>.</p>

<p>We call a <span data-keyword="subarray-nonempty">subarray</span> <strong>alternating</strong> if <strong>no</strong> two <strong>adjacent</strong> elements in the subarray have the <strong>same</strong> value.</p>

<p>Return <em>the number of alternating subarrays in </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The following subarrays are alternating: <code>[0]</code>, <code>[1]</code>, <code>[1]</code>, <code>[1]</code>, and <code>[0,1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>Every subarray of the array is alternating. There are 10 possible subarrays that we can choose.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate the subarrays ending at each position, calculate the number of subarrays that meet the conditions, and sum up the number of subarrays that meet the conditions at all positions.

Specifically, we define a variable $s$ to represent the number of subarrays that meet the conditions and end with the element $nums[i]$. Initially, we set $s$ to $1$, which means the number of subarrays that meet the conditions and end with the first element is $1$.

Next, we start to traverse the array from the second element. For each position $i$, we update the value of $s$ based on the relationship between $nums[i]$ and $nums[i-1]$:

-   If $nums[i] \neq nums[i-1]$, the value of $s$ increases by $1$, that is, $s = s + 1$;
-   If $nums[i] = nums[i-1]$, the value of $s$ is reset to $1$, that is, $s = 1$.

Then, we add the value of $s$ to the answer and continue to traverse the next position of the array until the entire array is traversed.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countAlternatingSubarrays(self, nums: List[int]) -> int:
        ans = s = 1
        for a, b in pairwise(nums):
            s = s + 1 if a != b else 1
            ans += s
        return ans
```

```java
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 1, s = 1;
        for (int i = 1; i < nums.length; ++i) {
            s = nums[i] != nums[i - 1] ? s + 1 : 1;
            ans += s;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countAlternatingSubarrays(vector<int>& nums) {
        long long ans = 1, s = 1;
        for (int i = 1; i < nums.size(); ++i) {
            s = nums[i] != nums[i - 1] ? s + 1 : 1;
            ans += s;
        }
        return ans;
    }
};
```

```go
func countAlternatingSubarrays(nums []int) int64 {
	ans, s := int64(1), int64(1)
	for i, x := range nums[1:] {
		if x != nums[i] {
			s++
		} else {
			s = 1
		}
		ans += s
	}
	return ans
}
```

```ts
function countAlternatingSubarrays(nums: number[]): number {
    let [ans, s] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        s = nums[i] !== nums[i - 1] ? s + 1 : 1;
        ans += s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
