# [面试题 11. 旋转数组的最小数字](https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

## 题目描述

<p>把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。</p>

<p>给你一个可能存在&nbsp;<strong>重复</strong>&nbsp;元素值的数组&nbsp;<code>numbers</code>&nbsp;，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组&nbsp;<code>[3,4,5,1,2]</code> 为 <code>[1,2,3,4,5]</code> 的一次旋转，该数组的最小值为1。&nbsp;&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3,4,5,1,2]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,2,2,0,1]
<strong>输出：</strong>0
</pre>

<p>注意：本题与主站 154 题相同：<a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/">https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/</a></p>

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

### **Rust**

```rust
impl Solution {
    pub fn min_array(numbers: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = numbers.len() - 1;
        while l < r {
            let mid = l + r >> 1;
            match numbers[mid].cmp(&numbers[r]) {
                std::cmp::Ordering::Less => r = mid,
                std::cmp::Ordering::Equal => r -= 1,
                std::cmp::Ordering::Greater => l = mid + 1,
            }
        }
        numbers[l]
    }
}
```

### **C#**

```cs
public class Solution {
    public int MinArray(int[] numbers) {
        int left = 0, right = numbers.Length - 1, mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
