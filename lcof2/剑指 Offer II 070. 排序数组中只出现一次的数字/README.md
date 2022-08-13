# [剑指 Offer II 070. 排序数组中只出现一次的数字](https://leetcode.cn/problems/skFtm2)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含整数的有序数组 <code>nums</code>&nbsp;，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。</p>

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

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶:</strong>&nbsp;采用的方案可以在 <code>O(log n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度中运行吗？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 540&nbsp;题相同：<a href="https://leetcode.cn/problems/single-element-in-a-sorted-array/">https://leetcode.cn/problems/single-element-in-a-sorted-array/</a></p>

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

### **...**

```

```

<!-- tabs:end -->
