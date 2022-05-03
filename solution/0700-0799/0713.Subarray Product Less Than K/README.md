# [713. 乘积小于 K 的子数组](https://leetcode.cn/problems/subarray-product-less-than-k)

[English Version](/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回子数组内所有元素的乘积严格小于<em> </em><code>k</code> 的连续子数组的数目。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,5,2,6], k = 100
<strong>输出：</strong>8
<strong>解释：</strong>8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], k = 0
<strong>输出：</strong>0</pre>

<p>&nbsp;</p>

<p><strong>提示:&nbsp;</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **C++**

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int left = 0, right;
        long mul = 1;
        int count = 0;

        for (right = 0; right < nums.size(); right++) {
            mul *= nums[right];

            while(left <= right && mul >= k) {
                mul /= nums[left++];
            }

            count += right >= left? right - left + 1: 0;
        }

        return count;
    }
};
```

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function numSubarrayProductLessThanK(nums: number[], k: number): number {
    if (k <= 1) {
        return 0;
    }

    const n = nums.length;
    let res = 0;
    for (let i = 0; i < n; i++) {
        let product = 1;
        for (let j = i; j < n; j++) {
            product *= nums[j];
            if (product >= k) {
                break;
            }
            res++;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn num_subarray_product_less_than_k(nums: Vec<i32>, k: i32) -> i32 {
        if k <= 1 {
            return 0;
        }

        let mut res = 0;
        let mut product = 1;
        let mut i = 0;
        nums.iter().enumerate().for_each(|(j, v)| {
            product *= v;
            while product >= k {
                product /= nums[i];
                i += 1;
            }
            res += j - i + 1;
        });
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
