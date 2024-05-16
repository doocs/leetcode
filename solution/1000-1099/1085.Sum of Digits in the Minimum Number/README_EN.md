---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1085.Sum%20of%20Digits%20in%20the%20Minimum%20Number/README_EN.md
rating: 1256
source: Biweekly Contest 2 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [1085. Sum of Digits in the Minimum Number 🔒](https://leetcode.com/problems/sum-of-digits-in-the-minimum-number)

[中文文档](/solution/1000-1099/1085.Sum%20of%20Digits%20in%20the%20Minimum%20Number/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <code>0</code><em> if the sum of the digits of the minimum integer in </em><code>nums</code><em> is odd, or </em><code>1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [34,23,1,24,75,33,54,8]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The minimal element is 1, and the sum of those digits is 1 which is odd, so the answer is 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [99,77,33,66,55]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The minimal element is 33, and the sum of those digits is 3 + 3 = 6 which is even, so the answer is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def sumOfDigits(self, nums: List[int]) -> int:
        x = min(nums)
        s = 0
        while x:
            s += x % 10
            x //= 10
        return s & 1 ^ 1
```

```java
class Solution {
    public int sumOfDigits(int[] nums) {
        int x = 100;
        for (int v : nums) {
            x = Math.min(x, v);
        }
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
}
```

```cpp
class Solution {
public:
    int sumOfDigits(vector<int>& nums) {
        int x = *min_element(nums.begin(), nums.end());
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s & 1 ^ 1;
    }
};
```

```go
func sumOfDigits(nums []int) int {
	s := 0
	for x := slices.Min(nums); x > 0; x /= 10 {
		s += x % 10
	}
	return s&1 ^ 1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
