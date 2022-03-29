# [1085. Sum of Digits in the Minimum Number](https://leetcode.com/problems/sum-of-digits-in-the-minimum-number)

[中文文档](/solution/1000-1099/1085.Sum%20of%20Digits%20in%20the%20Minimum%20Number/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <code>0</code><em> if the sum of the digits of the minimum integer in </em><code>nums</code><em> is odd, or </em><code>1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [34,23,1,24,75,33,54,8]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The minimal element is 1, and the sum of those digits is 1 which is odd, so the answer is 0.
</pre>

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumOfDigits(self, nums: List[int]) -> int:
        x = min(nums)
        s = 0
        while x:
            s += x % 10
            x //= 10
        return 0 if s % 2 else 1
```

### **Java**

```java
class Solution {
    public int sumOfDigits(int[] nums) {
        int x = nums[0];
        for (int v : nums) {
            x = Math.min(x, v);
        }
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return 1 - s % 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfDigits(vector<int>& nums) {
        int x = nums[0];
        for (int& v : nums) x = min(x, v);
        int s = 0;
        for (; x != 0; x /= 10) s += x % 10;
        return 1 - s % 2;
    }
};
```

### **Go**

```go
func sumOfDigits(nums []int) int {
	x := nums[0]
	for _, v := range nums {
		if v < x {
			x = v
		}
	}
	s := 0
	for ; x != 0; x /= 10 {
		s += x % 10
	}
	return 1 - s%2
}
```

### **...**

```

```

<!-- tabs:end -->
