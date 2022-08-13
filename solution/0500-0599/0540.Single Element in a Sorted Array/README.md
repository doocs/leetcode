# [540. 有序数组中的单一元素](https://leetcode.cn/problems/single-element-in-a-sorted-array)

[English Version](/solution/0500-0599/0540.Single%20Element%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。</p>

<p>请你找出并返回只出现一次的那个数。</p>

<p>你设计的解决方案必须满足 <code>O(log n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,2,3,3,4,4,8,8]
<strong>输出:</strong> 2
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums =  [3,3,7,7,10,11,11]
<strong>输出:</strong> 10
</pre>

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

给与的数组是有序的，由此可以使用二分查找，那条件该如何判断呢。

先观察一下线性遍历是如何确定目标的：

```c
for (int i = 0; i < n - 1; i += 2) {
    if (nums[i] != nums[i + 1]) {
        return nums[i];
    }
}
return nums[n - 1];
```

偶数下标：当 `nums[i] != nums[i + 1] && i % 2 == 0` 成立，结果便是 `nums[i]`。
奇数下标：当 `nums[i] != nums[i - 1] && i % 2 == 1` 成立，结果便是 `nums[i - 1]`。

于是二分模板就有了：

```txt
l = 0
r = n - 1
while l < r
    m = l + (r - l) / 2
    if m % 2 == 0
        if nums[m] == nums[m + 1]
            l = m + 1
        else
            r = m
    else
        if nums[m] == nums[m - 1]
            l = m + 1
        else
            r = m
return nums[l]
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = (left + right) >> 1
            # Equals to: if (mid % 2 == 0 and nums[mid] != nums[mid + 1]) or (mid % 2 == 1 and nums[mid] != nums[mid - 1]):
            if nums[mid] != nums[mid ^ 1]:
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
            int mid = (left + right) >> 1;
            // if ((mid % 2 == 0 && nums[mid] != nums[mid + 1]) || (mid % 2 == 1 && nums[mid] != nums[mid - 1])) {
            if (nums[mid] != nums[mid ^ 1]) {
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
    let left = 0,
        right = nums.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (nums[mid] != nums[mid ^ 1]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return nums[left];
}
```

### **C**

```c
int singleNonDuplicate(int* nums, int numsSize) {
    int left = 0;
    int right = numsSize - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == nums[mid ^ 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return nums[left];
}
```

### **C++**

```cpp
class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] != nums[mid ^ 1])
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
		if nums[mid] != nums[mid^1] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return nums[left]
}
```

### **Rust**

```rust
impl Solution {
    pub fn single_non_duplicate(nums: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            let mid = l + r >> 1;
            if nums[mid] == nums[mid ^ 1] {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        nums[l]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
