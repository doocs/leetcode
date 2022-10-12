# [1437. Check If All 1's Are at Least Length K Places Away](https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away)

[中文文档](/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/README.md)

## Description

<p>Given an binary array <code>nums</code> and an integer <code>k</code>, return <code>true</code><em> if all </em><code>1</code><em>&#39;s are at least </em><code>k</code><em> places away from each other, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/images/sample_1_1791.png" style="width: 428px; height: 181px;" />
<pre>
<strong>Input:</strong> nums = [1,0,0,0,1,0,0,1], k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> Each of the 1s are at least 2 places away from each other.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1437.Check%20If%20All%201%27s%20Are%20at%20Least%20Length%20K%20Places%20Away/images/sample_2_1791.png" style="width: 320px; height: 173px;" />
<pre>
<strong>Input:</strong> nums = [1,0,0,1,0,1], k = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> The second 1 and third 1 are only one apart from each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
	<li><code>nums[i]</code> is <code>0</code> or <code>1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kLengthApart(self, nums: List[int], k: int) -> bool:
        j = -1
        for i, v in enumerate(nums):
            if v == 1:
                if j > -1 and i - j - 1 < k:
                    return False
                j = i
        return True
```

### **Java**

```java
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int j = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                if (j != -1 && i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool kLengthApart(vector<int>& nums, int k) {
        int j = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 1) {
                if (j != -1 && i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func kLengthApart(nums []int, k int) bool {
	j := -1
	for i, v := range nums {
		if v == 1 {
			if j != -1 && i-j-1 < k {
				return false
			}
			j = i
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
