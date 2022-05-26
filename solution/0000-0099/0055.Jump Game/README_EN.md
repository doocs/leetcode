# [55. Jump Game](https://leetcode.com/problems/jump-game)

[中文文档](/solution/0000-0099/0055.Jump%20Game/README.md)

## Description

<p>You are given an integer array <code>nums</code>. You are initially positioned at the array&#39;s <strong>first index</strong>, and each element in the array represents your maximum jump length at that position.</p>

<p>Return <code>true</code><em> if you can reach the last index, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,1,4]
<strong>Output:</strong> true
<strong>Explanation:</strong> Jump 1 step from index 0 to 1, then 3 steps to the last index.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,0,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        mx = 0
        for i, num in enumerate(nums):
            if i > mx:
                return False
            mx = max(mx, i + num)
        return True
```

### **Java**

```java
class Solution {
    public boolean canJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > mx) {
                return false;
            }
            mx = Math.max(mx, i + nums[i]);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canJump(vector<int>& nums) {
        int mx = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (i > mx) {
                return false;
            }
            mx = max(mx, i + nums[i]);
        }
        return true;
    }
};
```

### **Go**

```go
func canJump(nums []int) bool {
	mx := 0
	for i, num := range nums {
		if i > mx {
			return false
		}
		mx = max(mx, i+num)
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public bool CanJump(int[] nums) {
        int mx = 0;
        for (int i = 0; i < nums.Length; ++i)
        {
            if (i > mx)
            {
                return false;
            }
            mx = Math.Max(mx, i + nums[i]);
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
