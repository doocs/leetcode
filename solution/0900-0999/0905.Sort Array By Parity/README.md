# [905. 按奇偶排序数组](https://leetcode.cn/problems/sort-array-by-parity)

[English Version](/solution/0900-0999/0905.Sort%20Array%20By%20Parity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>，将 <code>nums</code> 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。</p>

<p>返回满足此条件的 <strong>任一数组</strong> 作为答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,2,4]
<strong>输出：</strong>[2,4,3,1]
<strong>解释：</strong>[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针原地交换数组元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortArrayByParity(self, nums: List[int]) -> List[int]:
        i, j = 0, len(nums) - 1
        while i < j:
            if nums[i] & 1:
                nums[i], nums[j] = nums[j], nums[i]
                j -= 1
            else:
                i += 1
        return nums
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j;) {
            if (nums[i] % 2 == 1) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                --j;
            } else {
                ++i;
            }
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
var sortArrayByParity = function (nums) {
    for (let i = 0, j = nums.length - 1; i < j; ) {
        if (nums[i] & 1) {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            --j;
        } else {
            ++i;
        }
    }
    return nums;
};
```

### **Rust**

```rust
impl Solution {
    pub fn sort_array_by_parity(mut nums: Vec<i32>) -> Vec<i32> {
        let (mut l, mut r) = (0, nums.len() - 1);
        while l < r {
            while l < r && nums[l] & 1 == 0 {
                l += 1;
            }
            while l < r && nums[r] & 1 == 1 {
                r -= 1;
            }
            nums.swap(l, r);
        }
        nums
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        for (int i = 0, j = nums.size() - 1; i < j;) {
            if (nums[i] & 1)
                swap(nums[i], nums[j--]);
            else
                ++i;
        }
        return nums;
    }
};
```

### **Go**

```go
func sortArrayByParity(nums []int) []int {
	for i, j := 0, len(nums)-1; i < j; {
		if nums[i]%2 == 1 {
			nums[i], nums[j] = nums[j], nums[i]
			j--
		} else {
			i++
		}
	}
	return nums
}
```

### **...**

```

```

<!-- tabs:end -->
