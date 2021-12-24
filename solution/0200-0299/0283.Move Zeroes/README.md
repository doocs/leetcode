# [283. 移动零](https://leetcode-cn.com/problems/move-zeroes)

[English Version](/solution/0200-0299/0283.Move%20Zeroes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>[0,1,0,3,12]</code>
<strong>输出:</strong> <code>[1,3,12,0,0]</code></pre>

<p><strong>说明</strong>:</p>

<ol>
	<li>必须在原数组上操作，不能拷贝额外的数组。</li>
	<li>尽量减少操作次数。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        left, n = 0, len(nums)
        for right in range(n):
            if nums[right] != 0:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0, n = nums.length;
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                ++left;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int left = 0, n = nums.size();
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0)
            {
                swap(nums[left], nums[right]);
                ++left;
            }
        }
    }
};
```

### **Go**

```go
func moveZeroes(nums []int) {
	n := len(nums)
	left := 0
	for right := 0; right < n; right++ {
		if nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			left++
		}
	}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0,
        n = nums.length;
    for (let right = 0; right < n; ++right) {
        if (nums[right]) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            ++left;
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
