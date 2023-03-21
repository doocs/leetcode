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

**方法一：双指针**

我们可以用双指针维护一个滑动窗口，窗口内所有元素的乘积小于 $k$。

初始时，左右指针都指向下标 0，然后不断地右移右指针，将元素加入窗口，此时判断窗口内所有元素的乘积是否大于等于 $k$，如果大于等于 $k$，则不断地左移左指针，将元素移出窗口，直到窗口内所有元素的乘积小于 $k$。然后我们记录此时的窗口大小，即为以右指针为右端点的满足条件的子数组个数，将其加入答案。

当右指针移动到数组末尾时，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

以下是双指针的常用算法模板：

```java
for (int i = 0, j = 0; i < n; ++i) {
    while (j < i && check(j, i)) {
        ++j;
    }
    // 具体问题的逻辑
}
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        ans, s, j = 0, 1, 0
        for i, v in enumerate(nums):
            s *= v
            while j <= i and s >= k:
                s //= nums[j]
                j += 1
            ans += i - j + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        for (int i = 0, j = 0, s = 1; i < nums.length; ++i) {
            s *= nums[i];
            while (j <= i && s >= k) {
                s /= nums[j++];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0, j = 0, s = 1; i < nums.size(); ++i) {
            s *= nums[i];
            while (j <= i && s >= k) s /= nums[j++];
            ans += i - j + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubarrayProductLessThanK(nums []int, k int) int {
	ans := 0
	for i, j, s := 0, 0, 1; i < len(nums); i++ {
		s *= nums[i]
		for ; j <= i && s >= k; j++ {
			s /= nums[j]
		}
		ans += i - j + 1
	}
	return ans
}
```

### **TypeScript**

```ts
function numSubarrayProductLessThanK(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0, j = 0, s = 1; i < nums.length; ++i) {
        s *= nums[i];
        while (j <= i && s >= k) {
            s /= nums[j++];
        }
        ans += i - j + 1;
    }
    return ans;
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let ans = 0;
    let s = 1;
    for (let i = 0, j = 0; i < n; ++i) {
        s *= nums[i];
        while (j <= i && s >= k) {
            s = Math.floor(s / nums[j++]);
        }
        ans += i - j + 1;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
