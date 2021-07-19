# [1838. Frequency of the Most Frequent Element](https://leetcode.com/problems/frequency-of-the-most-frequent-element)

[中文文档](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README.md)

## Description

<p>The <strong>frequency</strong> of an element is the number of times it occurs in an array.</p>

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. In one operation, you can choose an index of <code>nums</code> and increment the element at that index by <code>1</code>.</p>

<p>Return <em>the <strong>maximum possible frequency</strong> of an element after performing <strong>at most</strong> </em><code>k</code><em> operations</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 5
<strong>Output:</strong> 3<strong>
Explanation:</strong> Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,8,13], k = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,9,6], k = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        window = 0
        l, r, n = 0, 1, len(nums)
        while r < n:
            window += (nums[r] - nums[r - 1]) * (r - l)
            r += 1
            while window > k:
                window -= nums[r - 1] - nums[l]
                l += 1
            ans = max(ans, r - l)
        return ans
```

### **Java**

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int window = 0;
        int l = 0, r = 1, n = nums.length;
        while (r < n) {
            window += (nums[r] - nums[r - 1]) * (r++ - l);
            while (window > k) {
                window -= nums[r - 1] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
```

### **Go**

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	window := 0
	l, r, n := 0, 1, len(nums)
	for r < n {
		window += (nums[r] - nums[r-1]) * (r - l)
		r++
		for window > k {
			window -= nums[r-1] - nums[l]
			l++
		}
		ans = max(ans, r-l)
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
