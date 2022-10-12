# [1909. Remove One Element to Make the Array Strictly Increasing](https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing)

[中文文档](/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README.md)

## Description

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, return <code>true</code> <em>if it can be made <strong>strictly increasing</strong> after removing <strong>exactly one</strong> element, or </em><code>false</code><em> otherwise. If the array is already strictly increasing, return </em><code>true</code>.</p>

<p>The array <code>nums</code> is <strong>strictly increasing</strong> if <code>nums[i - 1] &lt; nums[i]</code> for each index <code>(1 &lt;= i &lt; nums.length).</code></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,<u>10</u>,5,7]
<strong>Output:</strong> true
<strong>Explanation:</strong> By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong>
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canBeIncreasing(self, nums: List[int]) -> bool:
        def check(nums, i):
            prev = -inf
            for j, num in enumerate(nums):
                if i == j:
                    continue
                if prev >= nums[j]:
                    return False
                prev = nums[j]
            return True

        i, n = 1, len(nums)
        while i < n and nums[i - 1] < nums[i]:
            i += 1
        return check(nums, i - 1) or check(nums, i)
```

### **Java**

```java
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int i = 1, n = nums.length;
        for (; i < n && nums[i - 1] < nums[i]; ++i)
            ;
        return check(nums, i - 1) || check(nums, i);
    }

    private boolean check(int[] nums, int i) {
        int prev = Integer.MIN_VALUE;
        for (int j = 0; j < nums.length; ++j) {
            if (i == j) {
                continue;
            }
            if (prev >= nums[j]) {
                return false;
            }
            prev = nums[j];
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int i = 1, n = nums.size();
        for (; i < n && nums[i - 1] < nums[i]; ++i)
            ;
        return check(nums, i - 1) || check(nums, i);
    }

    bool check(vector<int>& nums, int i) {
        int prev = 0;
        for (int j = 0; j < nums.size(); ++j) {
            if (i == j) continue;
            if (prev >= nums[j]) return false;
            prev = nums[j];
        }
        return true;
    }
};
```

### **Go**

```go
func canBeIncreasing(nums []int) bool {
	i, n := 1, len(nums)
	for ; i < n && nums[i-1] < nums[i]; i++ {

	}
	return check(nums, i-1) || check(nums, i)
}

func check(nums []int, i int) bool {
	prev := 0
	for j := 0; j < len(nums); j++ {
		if i == j {
			continue
		}
		if prev >= nums[j] {
			return false
		}
		prev = nums[j]
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
