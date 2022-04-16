# [896. Monotonic Array](https://leetcode.com/problems/monotonic-array)

[中文文档](/solution/0800-0899/0896.Monotonic%20Array/README.md)

## Description

<p>An array is <strong>monotonic</strong> if it is either monotone increasing or monotone decreasing.</p>

<p>An array <code>nums</code> is monotone increasing if for all <code>i &lt;= j</code>, <code>nums[i] &lt;= nums[j]</code>. An array <code>nums</code> is monotone decreasing if for all <code>i &lt;= j</code>, <code>nums[i] &gt;= nums[j]</code>.</p>

<p>Given an integer array <code>nums</code>, return <code>true</code><em> if the given array is monotonic, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,4]
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        incr = decr = True
        for i, v in enumerate(nums[1:]):
            if not incr and not decr:
                return False
            if v < nums[i]:
                incr = False
            elif v > nums[i]:
                decr = False
        return incr or decr
```

### **Java**

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean incr = true, decr = true;
        for (int i = 1; i < nums.length; ++i) {
            if (!incr && !decr) {
                return false;
            }
            if (nums[i] < nums[i - 1]) {
                incr = false;
            } else if (nums[i] > nums[i - 1]) {
                decr = false;
            }
        }
        return incr || decr;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool incr = true;
        bool decr = true;
        for (int i = 1; i < nums.size(); ++i)
        {
            if (!incr && !decr) return false;
            if (nums[i] < nums[i - 1]) incr = false;
            if (nums[i] > nums[i - 1]) decr = false;
        }
        return incr || decr;
    }
};
```

### **Go**

```go
func isMonotonic(nums []int) bool {
	incr, decr := true, true
	for i, v := range nums[1:] {
		if !incr && !decr {
			return false
		}
		if v < nums[i] {
			incr = false
		} else if v > nums[i] {
			decr = false
		}
	}
	return incr || decr
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let incr = true;
    let decr = true;
    for (let i = 1; i < nums.length; ++i) {
        if (!incr && !decr) {
            return false;
        }
        if (nums[i] < nums[i - 1]) {
            incr = false;
        }
        if (nums[i] > nums[i - 1]) {
            decr = false;
        }
    }
    return incr || decr;
};
```

### **...**

```

```

<!-- tabs:end -->
