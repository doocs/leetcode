# [581. Shortest Unsorted Continuous Subarray](https://leetcode.com/problems/shortest-unsorted-continuous-subarray)

[中文文档](/solution/0500-0599/0581.Shortest%20Unsorted%20Continuous%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code>, you need to find one <b>continuous subarray</b> that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.</p>

<p>Return <em>the shortest such subarray and output its length</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,4,8,10,9,15]
<strong>Output:</strong> 5
<strong>Explanation:</strong> You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you solve it in <code>O(n)</code> time complexity?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        numsSorted = sorted(nums)
        left, right = 0, n - 1
        while left < n and nums[left] == numsSorted[left]:
            left += 1
        while right >= 0 and nums[right] == numsSorted[right]:
            right -= 1
        return 0 if right == -1 else right - left + 1
```

### **Java**

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] numsSorted = new int[n];
        System.arraycopy(nums, 0, numsSorted, 0, n);
        Arrays.sort(numsSorted);
        int left = 0, right = n - 1;
        while (left < n && nums[left] == numsSorted[left]) {
            left++;
        }
        while (right >= 0 && nums[right] == numsSorted[right]) {
            right--;
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
```

### **Go**

```go
func findUnsortedSubarray(nums []int) int {
	n := len(nums)
	maxn, minn := math.MinInt32, math.MaxInt32
	left, right := -1, -1
	for i := 0; i < n; i++ {
		if maxn > nums[i] {
			right = i
		} else {
			maxn = nums[i]
		}
		if minn < nums[n-i-1] {
			left = n - i - 1
		} else {
			minn = nums[n-i-1]
		}
	}
	if right == -1 {
		return 0
	}
	return right - left + 1
}
```

### **...**

```

```

<!-- tabs:end -->
