---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1121.Divide%20Array%20Into%20Increasing%20Sequences/README_EN.md
rating: 1664
source: Biweekly Contest 4 Q4
tags:
    - Array
    - Counting
---

<!-- problem:start -->

# [1121. Divide Array Into Increasing Sequences 🔒](https://leetcode.com/problems/divide-array-into-increasing-sequences)

[中文文档](/solution/1100-1199/1121.Divide%20Array%20Into%20Increasing%20Sequences/README.md)

## Description

<p>Given an integer array <code>nums</code> sorted in non-decreasing order and an integer <code>k</code>, return <code>true</code><em> if this array can be divided into one or more disjoint increasing subsequences of length at least </em><code>k</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3,3,4,4], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be divided into two subsequences [1,2,3,4] and [2,3,4] with lengths at least 3 each.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6,6,7,8], k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no way to divide the array using the conditions required.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> is sorted in non-decreasing order.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Quick Thinking

We assume that the array can be divided into $m$ strictly increasing subsequences of length at least $k$. If the number of the most frequent number in the array is $cnt$, then these $cnt$ numbers must be in different subsequences, so $m \geq cnt$. Also, since the length of $m$ subsequences is at least $k$, the fewer the number of subsequences, the better, so $m = cnt$. Therefore, $cnt \times k \leq n$ must be satisfied. Hence, we only need to count the number of the most frequent number $cnt$ in the array, and then judge whether $cnt \times k \leq n$. If it is, return `true`, otherwise return `false`.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def canDivideIntoSubsequences(self, nums: List[int], k: int) -> bool:
        mx = max(len(list(x)) for _, x in groupby(nums))
        return mx * k <= len(nums)
```

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, cnt.merge(x, 1, Integer::sum));
        }
        return mx * k <= nums.length;
    }
}
```

```cpp
class Solution {
public:
    bool canDivideIntoSubsequences(vector<int>& nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int& b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.size()) {
                return false;
            }
            a = b;
        }
        return true;
    }
};
```

```go
func canDivideIntoSubsequences(nums []int, k int) bool {
	cnt, a := 0, 0
	for _, b := range nums {
		cnt++
		if a != b {
			cnt = 1
		}
		if cnt*k > len(nums) {
			return false
		}
		a = b
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```java
class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int cnt = 0;
        int a = 0;
        for (int b : nums) {
            cnt = a == b ? cnt + 1 : 1;
            if (cnt * k > nums.length) {
                return false;
            }
            a = b;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
