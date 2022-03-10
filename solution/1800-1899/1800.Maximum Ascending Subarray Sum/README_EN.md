# [1800. Maximum Ascending Subarray Sum](https://leetcode.com/problems/maximum-ascending-subarray-sum)

[中文文档](/solution/1800-1899/1800.Maximum%20Ascending%20Subarray%20Sum/README.md)

## Description

<p>Given an array of positive integers <code>nums</code>, return the <em>maximum possible sum of an <strong>ascending</strong> subarray in </em><code>nums</code>.</p>

<p>A subarray is defined as a contiguous sequence of numbers in an array.</p>

<p>A subarray <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> is <strong>ascending</strong> if for all <code>i</code> where <code>l &lt;= i &lt; r</code>, <code>nums<sub>i </sub> &lt; nums<sub>i+1</sub></code>. Note that a subarray of size <code>1</code> is <strong>ascending</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30,5,10,50]
<strong>Output:</strong> 65
<strong>Explanation: </strong>[5,10,50] is the ascending subarray with the maximum sum of 65.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30,40,50]
<strong>Output:</strong> 150
<strong>Explanation: </strong>[10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,17,15,13,10,11,12]
<strong>Output:</strong> 33
<strong>Explanation: </strong>[10,11,12] is the ascending subarray with the maximum sum of 33.
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
    def maxAscendingSum(self, nums: List[int]) -> int:
        res, cur = 0, nums[0]
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                cur += nums[i]
            else:
                res = max(res, cur)
                cur = nums[i]
        res = max(res, cur)
        return res
```

### **Java**

```java
class Solution {
    public int maxAscendingSum(int[] nums) {
        int cur = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                cur += nums[i];
            } else {
                res = Math.max(res, cur);
                cur = nums[i];
            }
        }
        res = Math.max(res, cur);
        return res;
    }
}
```

### **TypeScript**

```ts
function maxAscendingSum(nums: number[]): number {
    let res = 0,
        sum = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] > nums[i - 1]) {
            sum += nums[i];
        } else {
            res = Math.max(res, sum);
            sum = nums[i];
        }
    }
    res = Math.max(res, sum);
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxAscendingSum(vector<int>& nums) {
        int res = 0, cur = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > nums[i - 1]) {
                cur += nums[i];
            } else {
                res = max(res, cur);
                cur = nums[i];
            }
        }
        res = max(res, cur);
        return res;
    }
};
```

### **Go**

```go
func maxAscendingSum(nums []int) int {
	res, cur := 0, nums[0]
	for i := 1; i < len(nums); i++ {
		if nums[i] > nums[i-1] {
			cur += nums[i]
		} else {
			if res < cur {
				res = cur
			}
			cur = nums[i]
		}
	}
	if res < cur {
		res = cur
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
