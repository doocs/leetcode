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

我们定义两个指针 $i$ 和 $j$，其中指针 $i$ 指向当前元素，指针 $j$ 指向当前最后一个奇数的下一个位置。

接下来，我们从左到右遍历数组，当 $nums[i]$ 是奇数时，我们将其与 $nums[j]$ 交换，然后指针 $j$ 向右移动一位。指针 $i$ 每次向右移动一位，直到遍历完整个数组。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exchange(self, nums: List[int]) -> List[int]:
        j = 0
        for i, x in enumerate(nums):
            if x & 1:
                nums[i], nums[j] = nums[j], nums[i]
                j += 1
        return nums
```

### **Java**

```java
class Solution {
    public int[] exchange(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 1) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j++] = t;
            }
        }
        return nums;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        int j = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] & 1) {
                swap(nums[i], nums[j++]);
            }
        }
        return nums;
    }
};
```

### **Go**

```go
func exchange(nums []int) []int {
	j := 0
	for i, x := range nums {
		if x&1 == 1 {
			nums[i], nums[j] = nums[j], nums[i]
			j++
		}
	}
	return nums
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
    let j = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] & 1) {
            const t = nums[i];
            nums[i] = nums[j];
            nums[j++] = t;
        }
    }
    return nums;
};
```

### **TypeScript**

```ts
function exchange(nums: number[]): number[] {
    let j = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] & 1) {
            const t = nums[i];
            nums[i] = nums[j];
            nums[j++] = t;
        }
    }
    return nums;
}
```

### **Rust**

```rust
impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        let mut j = 0;
        for i in 0..nums.len() {
            if nums[i] % 2 == 1 {
                nums.swap(i, j);
                j += 1;
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
        int j = 0;
        for (int i = 0; i < nums.Length; ++i) {
            if (nums[i] % 2 == 1) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j++] = t;
            }
        }
        return nums;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
