# [面试题 21. 调整数组顺序使奇数位于偶数前面](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

## 题目描述

<p>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>nums =&nbsp;[1,2,3,4]
<strong>输出：</strong>[1,3,2,4] 
<strong>注：</strong>[3,1,2,4] 也是正确的答案之一。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= nums.length &lt;= 50000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10000</code></li>
</ol>

## 解法

**方法一：双指针**

定义指针 `i`, `j` 分别指向数组的头部和尾部，`i` 向右移动，`j` 向左移动，当 `i` 指向偶数，`j` 指向奇数时，交换两个指针指向的元素，直到 `i` 和 `j` 相遇。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        i, j = 0, len(nums) - 1
        while i < j:
            while i < j and nums[i] & 1:
                i += 1
            while i < j and (nums[j] & 1) == 0:
                j -= 1
            nums[i], nums[j] = nums[j], nums[i]
        return nums
```

### **Java**

```java
class Solution {
    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                ++i;
            }
            while (i < j && nums[j] % 2 == 0) {
                --j;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
    let i = 0;
    let j = nums.length - 1;
    while (i < j) {
        while (i < j && nums[i] % 2 == 1) {
            i++;
        }
        while (i < j && nums[j] % 2 == 0) {
            --j;
        }
        const t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    return nums;
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                ++i;
            }
            while (i < j && nums[j] % 2 == 0) {
                --j;
            }
            swap(nums[i], nums[j]);
        }
        return nums;
    }
};
```

### **Go**

```go
func exchange(nums []int) []int {
	i, j := 0, len(nums)-1
	for i < j {
		for i < j && nums[i]%2 == 1 {
			i++
		}
		for i < j && nums[j]%2 == 0 {
			j--
		}
		nums[i], nums[j] = nums[j], nums[i]
	}
	return nums
}
```

### **TypeScript**

```ts
function exchange(nums: number[]): number[] {
    let i = 0;
    let j = nums.length - 1;
    while (i < j) {
        while (i < j && nums[i] % 2 == 1) {
            i++;
        }
        while (i < j && nums[j] % 2 == 0) {
            --j;
        }
        const t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    return nums;
}
```

### **Rust**

```rust
impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        if nums.len() == 0 {
            return nums;
        }
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            if nums[l] % 2 == 0 {
                nums.swap(l, r);
                r -= 1;
            } else {
                l += 1;
            }
        }
        nums
    }
}
```

### **C#**

```cs
public class Solution {
    public int[] Exchange(int[] nums) {
        int i = 0, j = nums.Length - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                ++i;
            }
            while (i < j && nums[j] % 2 == 0) {
                --j;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
