# [面试题 11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

## 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组  `[3,4,5,1,2]` 为 `[1,2,3,4,5]` 的一个旋转，该数组的最小值为 1。

**示例 1：**

```
输入：[3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：[2,2,2,0,1]
输出：0
```

## 解法

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minArray(self, numbers: List[int]) -> int:
        l, r = 0, len(numbers) - 1
        while l < r:
            m = (l + r) >> 1
            if numbers[m] > numbers[r]:
                l = m + 1
            elif numbers[m] < numbers[r]:
                r = m
            else:
                r -= 1
        return numbers[l]
```

### **Java**

```java
class Solution {
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                --r;
            }
        }
        return numbers[l];
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} numbers
 * @return {number}
 */
var minArray = function (numbers) {
    let l = 0,
        r = numbers.length - 1;
    while (l < r) {
        let m = (l + r) >>> 1;
        if (numbers[m] > numbers[r]) {
            l = m + 1;
        } else if (numbers[m] < numbers[r]) {
            r = m;
        } else {
            --r;
        }
    }
    return numbers[l];
};
```

### **Go**

```go
func minArray(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		mid := l + (r-l)>>1
		if nums[mid] > nums[r] {
			l = mid + 1
		} else if nums[mid] < nums[r] {
			r = mid // r 本身不需要被排除
		} else {
			r--
		}
	}
	return nums[l]
}
```

### **C++**

```cpp
class Solution {
public:
    int minArray(vector<int>& numbers) {
        int left = 0, right = numbers.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return min(numbers[left], numbers[right]);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
