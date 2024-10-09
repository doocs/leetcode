---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2393.Count%20Strictly%20Increasing%20Subarrays/README_EN.md
tags:
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [2393. Count Strictly Increasing Subarrays 🔒](https://leetcode.com/problems/count-strictly-increasing-subarrays)

[中文文档](/solution/2300-2399/2393.Count%20Strictly%20Increasing%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>Return <em>the number of <strong>subarrays</strong> of </em><code>nums</code><em> that are in <strong>strictly increasing</strong> order.</em></p>

<p>A <strong>subarray</strong> is a <strong>contiguous</strong> part of an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,4,4,6]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The strictly increasing subarrays are the following:
- Subarrays of length 1: [1], [3], [5], [4], [4], [6].
- Subarrays of length 2: [1,3], [3,5], [4,6].
- Subarrays of length 3: [1,3,5].
The total number of subarrays is 6 + 3 + 1 = 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 15
<strong>Explanation:</strong> Every subarray is strictly increasing. There are 15 possible subarrays that we can take.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate the number of strictly increasing subarrays ending at each element and then sum them up.

We use a variable $\textit{cnt}$ to record the number of strictly increasing subarrays ending at the current element, initially $\textit{cnt} = 1$. Then we traverse the array starting from the second element. If the current element is greater than the previous element, then $\textit{cnt}$ can be incremented by $1$. Otherwise, $\textit{cnt}$ is reset to $1$. At this point, the number of strictly increasing subarrays ending at the current element is $\textit{cnt}$, and we add it to the answer.

After the traversal, return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = cnt = 1
        for x, y in pairwise(nums):
            if x < y:
                cnt += 1
            else:
                cnt = 1
            ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums) {
        long ans = 1, cnt = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 1, cnt = 1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int) int64 {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
		} else {
			cnt = 1
		}
		ans += cnt
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[]): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ++cnt;
        } else {
            cnt = 1;
        }
        ans += cnt;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
