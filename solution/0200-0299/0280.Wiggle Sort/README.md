# [280. 摆动排序](https://leetcode-cn.com/problems/wiggle-sort)

[English Version](/solution/0200-0299/0280.Wiggle%20Sort/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个无序的数组&nbsp;<code>nums</code>, 将该数字&nbsp;<strong>原地&nbsp;</strong>重排后使得&nbsp;<code>nums[0] &lt;= nums[1] &gt;= nums[2] &lt;= nums[3]...</code>。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>nums = [3,5,2,1,6,4]</code>
<strong>输出:</strong> 一个可能的解答是 [3,5,1,6,2,4]</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        for i in range(1, len(nums)):
            if (i % 2 == 1 and nums[i] < nums[i - 1]) or (i % 2 == 0 and nums[i] > nums[i - 1]):
                nums[i], nums[i - 1] = nums[i - 1], nums[i]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
