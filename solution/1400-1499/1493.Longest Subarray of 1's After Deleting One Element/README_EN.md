# [1493. Longest Subarray of 1's After Deleting One Element](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element)

[中文文档](/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README.md)

## Description

<p>Given a binary array <code>nums</code>, you should delete one element from it.</p>

<p>Return <em>the size of the longest non-empty subarray containing only </em><code>1</code><em>&#39;s in the resulting array</em>. Return <code>0</code> if there is no such subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,1,0,1,1,0,1]
<strong>Output:</strong> 5
<strong>Explanation:</strong> After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1&#39;s is [1,1,1,1,1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You must delete one element.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i in range(1, n):
            if nums[i - 1] == 1:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if nums[i + 1] == 1:
                right[i] = right[i + 1] + 1
        return max(a + b for a, b in zip(left, right))
```

### **Java**

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i + 1] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, left[i] + right[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; ~i; --i) {
            if (nums[i + 1] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, left[i] + right[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestSubarray(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := 1; i < n; i++ {
		if nums[i-1] == 1 {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if nums[i+1] == 1 {
			right[i] = right[i+1] + 1
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, left[i]+right[i])
	}
	return ans
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
