# [1822. Sign of the Product of an Array](https://leetcode.com/problems/sign-of-the-product-of-an-array)

[中文文档](/solution/1800-1899/1822.Sign%20of%20the%20Product%20of%20an%20Array/README.md)

## Description

<p>There is a function <code>signFunc(x)</code> that returns:</p>

<ul>
	<li><code>1</code> if <code>x</code> is positive.</li>
	<li><code>-1</code> if <code>x</code> is negative.</li>
	<li><code>0</code> if <code>x</code> is equal to <code>0</code>.</li>
</ul>

<p>You are given an integer array <code>nums</code>. Let <code>product</code> be the product of all values in the array <code>nums</code>.</p>

<p>Return <code>signFunc(product)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4,3,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The product of all values in the array is 144, and signFunc(144) = 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,0,2,-3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The product of all values in the array is 0, and signFunc(0) = 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1,-1,1,-1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The product of all values in the array is -1, and signFunc(-1) = -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
