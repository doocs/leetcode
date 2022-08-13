# [1822. 数组元素积的符号](https://leetcode.cn/problems/sign-of-the-product-of-an-array)

[English Version](/solution/1800-1899/1822.Sign%20of%20the%20Product%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>已知函数 <code>signFunc(x)</code> 将会根据 <code>x</code> 的正负返回特定值：</p>

<ul>
	<li>如果 <code>x</code> 是正数，返回 <code>1</code> 。</li>
	<li>如果 <code>x</code> 是负数，返回 <code>-1</code> 。</li>
	<li>如果 <code>x</code> 是等于 <code>0</code> ，返回 <code>0</code> 。</li>
</ul>

<p>给你一个整数数组 <code>nums</code> 。令 <code>product</code> 为数组 <code>nums</code> 中所有元素值的乘积。</p>

<p>返回 <code>signFunc(product)</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3,-4,3,2,1]
<strong>输出：</strong>1
<strong>解释：</strong>数组中所有值的乘积是 144 ，且 signFunc(144) = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,0,2,-3]
<strong>输出：</strong>0
<strong>解释：</strong>数组中所有值的乘积是 0 ，且 signFunc(0) = 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,1,-1,1,-1]
<strong>输出：</strong>-1
<strong>解释：</strong>数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>-100 <= nums[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

不可模拟乘积过程，给定的范围有可能导致数值溢出，只关注数值的符号变化即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arraySign(self, nums: List[int]) -> int:
        res = 1
        for num in nums:
            if num == 0:
                return 0
            if num < 0:
                res *= -1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int arraySign(int[] nums) {
        int res = 1;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) res *= -1;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var arraySign = function (nums) {
    let res = 1;
    for (let num of nums) {
        if (num == 0) return 0;
        if (num < 0) res *= -1;
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    int arraySign(vector<int>& nums) {
        int res = 1;
        for (auto& num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                res *= -1;
            }
        }
        return res;
    }
};
```

### **Go**

```go
func arraySign(nums []int) int {
	res := 1
	for _, num := range nums {
		if num == 0 {
			return 0
		}
		if num < 0 {
			res *= -1
		}
	}
	return res
}
```

### **Rust**

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn array_sign(nums: Vec<i32>) -> i32 {
        let mut res = 1;
        for num in nums.iter() {
            match num.cmp(&0) {
                Ordering::Equal => return 0,
                Ordering::Less => res *= -1,
                Ordering::Greater => {}
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
