# [540. 有序数组中的单一元素](https://leetcode-cn.com/problems/single-element-in-a-sorted-array)

[English Version](/solution/0500-0599/0540.Single%20Element%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,1,2,3,3,4,4,8,8]
<strong>输出:</strong> 2
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [3,3,7,7,10,11,11]
<strong>输出:</strong> 10
</pre>

<p><strong>注意:</strong> 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。</p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if (mid & 1) == 1:
                mid -= 1
            if nums[mid] == nums[mid + 1]:
                left = mid + 2
            else:
                right = mid
        return nums[left]
```

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            if (mid % 2 == 0 and nums[mid] != nums[mid + 1]) or (mid % 2 != 0 and nums[mid] != nums[mid - 1]):
                right = mid
            else:
                left = mid + 1
        return nums[left]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if ((mid & 1) == 1) {
                --mid;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
```

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 != 0 && nums[mid] != nums[mid - 1])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
```

### **TypeScript**

```ts
function singleNonDuplicate(nums: number[]): number {
    let left = 0, right = nums.length - 1;
    while (left < right) {
        let mid = (left + right) >> 1;
        if ((mid & 1) == 1) --mid;
        if (nums[mid] == nums[mid + 1]) {
            left = mid + 2;
        } else {
            right = mid;
        }
    }
    return nums[left];
};
```

### **C++**

```cpp
class Solution {
public:
    int singleNonDuplicate(vector<int> &nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right)
        {
            int mid = left + right >> 1;
            if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 != 0 && nums[mid] != nums[mid - 1]))
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left];
    }
};
```

### **Go**

```go
func singleNonDuplicate(nums []int) int {
	left, right := 0, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		if (mid & 1) == 1 {
			mid--
		}
		if nums[mid] == nums[mid+1] {
			left = mid + 2
		} else {
			right = mid
		}
	}
	return nums[left]
}
```

### **...**

```

```

<!-- tabs:end -->
