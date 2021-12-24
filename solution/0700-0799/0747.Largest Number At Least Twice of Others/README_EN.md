# [747. Largest Number At Least Twice of Others](https://leetcode.com/problems/largest-number-at-least-twice-of-others)

[中文文档](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README.md)

## Description

<p>You are given an integer array <code>nums</code> where the largest integer is <strong>unique</strong>.</p>

<p>Find whether the largest element in the array is at least twice as much as every other number in the array. If it is, return <em>the index of the largest element</em>, otherwise, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,1,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 6 is the largest integer and for every other number in the array x,
6 is more than twice as big as x.
The index of value 6 is 1, so we return 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 4 is not at least as big as twice the value of 3, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li>The largest element in <code>nums</code> is unique.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        max_idx, n = 0, len(nums)
        for i in range(1, n):
            if nums[i] > nums[max_idx]:
                max_idx = i
        for i in range(n):
            if i != max_idx and nums[i] * 2 > nums[max_idx]:
                return -1
        return max_idx
```

### **Java**

```java
class Solution {
  public int dominantIndex(int[] nums) {
    int maxIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > nums[maxIndex])
        maxIndex = i;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] * 2 > nums[maxIndex] && i != maxIndex)
        return -1;
    }
    return maxIndex;
  }
}
```

### **C++**

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int maxIdx = 0, n = nums.size();
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        for (int i = 0; i < n; ++i) {
            if (i != maxIdx && nums[i] * 2 > nums[maxIdx]) return -1;
        }
        return maxIdx;
    }
};
```

### **Go**

```go
func dominantIndex(nums []int) int {
	maxIndex, n := 0, len(nums)
	for i := 1; i < n; i++ {
		if nums[i] > nums[maxIndex] {
			maxIndex = i
		}
	}
	for i := 0; i < n; i++ {
		if i != maxIndex && nums[i]*2 > nums[maxIndex] {
			return -1
		}
	}
	return maxIndex
}
```

### **...**

```

```

<!-- tabs:end -->
