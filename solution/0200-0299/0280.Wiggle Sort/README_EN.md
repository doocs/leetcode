# [280. Wiggle Sort](https://leetcode.com/problems/wiggle-sort)

[中文文档](/solution/0200-0299/0280.Wiggle%20Sort/README.md)

## Description

<p>Given an integer array <code>nums</code>, reorder it such that <code>nums[0] &lt;= nums[1] &gt;= nums[2] &lt;= nums[3]...</code>.</p>

<p>You may assume the input array always has a valid answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,2,1,6,4]
<strong>Output:</strong> [3,5,1,6,2,4]
<strong>Explanation:</strong> [1,6,2,5,3,4] is also accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,6,5,6,3,8]
<strong>Output:</strong> [6,6,5,6,3,8]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>It is guaranteed that there will be an answer for the given input <code>nums</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve the problem in <code>O(n)</code> time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        for i in range(1, len(nums)):
            if (i % 2 == 1 and nums[i] < nums[i - 1]) or (
                i % 2 == 0 and nums[i] > nums[i - 1]
            ):
                nums[i], nums[i - 1] = nums[i - 1], nums[i]
```

### **Java**

```java
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        for (int i = 1; i < nums.size(); ++i) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums[i], nums[i - 1]);
            }
        }
    }
};
```

### **Go**

```go
func wiggleSort(nums []int) {
	for i := 1; i < len(nums); i++ {
		if (i%2 == 1 && nums[i] < nums[i-1]) || (i%2 == 0 && nums[i] > nums[i-1]) {
			nums[i], nums[i-1] = nums[i-1], nums[i]
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
