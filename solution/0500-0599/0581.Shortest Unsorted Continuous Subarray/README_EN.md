# [581. Shortest Unsorted Continuous Subarray](https://leetcode.com/problems/shortest-unsorted-continuous-subarray)

[中文文档](/solution/0500-0599/0581.Shortest%20Unsorted%20Continuous%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code>, you need to find one <b>continuous subarray</b> such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.</p>

<p>Return <em>the shortest such subarray and output its length</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,4,8,10,9,15]
<strong>Output:</strong> 5
<strong>Explanation:</strong> You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        arr = sorted(nums)
        left, right = 0, len(nums) - 1
        while left <= right and nums[left] == arr[left]:
            left += 1
        while left <= right and nums[right] == arr[right]:
            right -= 1
        return right - left + 1
```

### **Java**

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int left = 0, right = nums.length - 1;
        while (left <= right && nums[left] == arr[left]) {
            ++left;
        }
        while (left <= right && nums[right] == arr[right]) {
            --right;
        }
        return right - left + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        int left = 0, right = arr.size() - 1;
        while (left <= right && nums[left] == arr[left]) ++left;
        while (left <= right && nums[right] == arr[right]) --right;
        return right - left + 1;
    }
};
```

### **Go**

```go
func findUnsortedSubarray(nums []int) int {
	n := len(nums)
	arr := make([]int, n)
	copy(arr, nums)
	sort.Ints(arr)
	left, right := 0, n-1
	for left <= right && nums[left] == arr[left] {
		left++
	}
	for left <= right && nums[right] == arr[right] {
		right--
	}
	return right - left + 1
}
```

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
