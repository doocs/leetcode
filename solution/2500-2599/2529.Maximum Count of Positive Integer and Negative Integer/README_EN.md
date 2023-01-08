# [2529. Maximum Count of Positive Integer and Negative Integer](https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer)

[中文文档](/solution/2500-2599/2529.Maximum%20Count%20of%20Positive%20Integer%20and%20Negative%20Integer/README.md)

## Description

<p>Given an array <code>nums</code> sorted in <strong>non-decreasing</strong> order, return <em>the maximum between the number of positive integers and the number of negative integers.</em></p>

<ul>
	<li>In other words, if the number of positive integers in <code>nums</code> is <code>pos</code> and the number of negative integers is <code>neg</code>, then return the maximum of <code>pos</code> and <code>neg</code>.</li>
</ul>

<p><strong>Note</strong> that <code>0</code> is neither positive nor negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-1,-1,1,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,-2,-1,0,0,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,20,66,1314]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-2000 &lt;= nums[i] &lt;= 2000</code></li>
	<li><code>nums</code> is sorted in a <strong>non-decreasing order</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumCount(self, nums: List[int]) -> int:
        a = sum(v > 0 for v in nums)
        b = sum(v < 0 for v in nums)
        return max(a, b)
```

### **Java**

```java
class Solution {
    public int maximumCount(int[] nums) {
        int a = 0, b = 0;
        for (int v : nums) {
            if (v > 0) {
                ++a;
            }
            if (v < 0) {
                ++b;
            }
        }
        return Math.max(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int a = 0, b = 0;
        for (int& v : nums) {
            if (v > 0) {
                ++a;
            }
            if (v < 0) {
                ++b;
            }
        }
        return max(a, b);
    }
};
```

### **Go**

```go
func maximumCount(nums []int) int {
	a, b := 0, 0
	for _, v := range nums {
		if v > 0 {
			a++
		}
		if v < 0 {
			b++
		}
	}
	return max(a, b)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
