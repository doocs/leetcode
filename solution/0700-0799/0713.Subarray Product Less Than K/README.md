---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README.md
tags:
    - 数组
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [713. 乘积小于 K 的子数组](https://leetcode.cn/problems/subarray-product-less-than-k)

[English Version](/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README_EN.md)

## 题目描述

<!-- description:start -->

给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回子数组内所有元素的乘积严格小于<em> </em><code>k</code> 的连续子数组的数目。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,5,2,6], k = 100
<strong>输出：</strong>8
<strong>解释：</strong>8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以用双指针维护一个滑动窗口，窗口内所有元素的乘积小于 $k$。

定义两个指针 $l$ 和 $r$ 分别指向滑动窗口的左右边界，初始时 $l = r = 0$。我们用一个变量 $p$ 记录窗口内所有元素的乘积，初始时 $p = 1$。

每次，我们将 $r$ 右移一位，将 $r$ 指向的元素 $x$ 加入窗口，更新 $p = p \times x$。然后，如果 $p \geq k$，我们循环地将 $l$ 右移一位，并更新 $p = p \div \text{nums}[l]$，直到 $p < k$ 或者 $l \gt r$ 为止。这样，以 $r$ 结尾的、乘积小于 $k$ 的连续子数组的个数即为 $r - l + 1$。然后我们将答案加上这个数量，并继续移动 $r$，直到 $r$ 到达数组的末尾。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        ans = l = 0
        p = 1
        for r, x in enumerate(nums):
            p *= x
            while l <= r and p >= k:
                p //= nums[l]
                l += 1
            ans += r - l + 1
        return ans
```

#### Java

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.length; ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.size(); ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func numSubarrayProductLessThanK(nums []int, k int) (ans int) {
    l, p := 0, 1
    for r, x := range nums {
        p *= x
        for l <= r && p >= k {
            p /= nums[l]
            l++
        }
        ans += r - l + 1
    }
    return
}
```

#### TypeScript

```ts
function numSubarrayProductLessThanK(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, l, p] = [0, 0, 1];
    for (let r = 0; r < n; ++r) {
        p *= nums[r];
        while (l <= r && p >= k) {
            p /= nums[l++];
        }
        ans += r - l + 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_subarray_product_less_than_k(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut l = 0;
        let mut p = 1;

        for (r, &x) in nums.iter().enumerate() {
            p *= x;
            while l <= r && p >= k {
                p /= nums[l];
                l += 1;
            }
            ans += (r - l + 1) as i32;
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let [ans, l, p] = [0, 0, 1];
    for (let r = 0; r < n; ++r) {
        p *= nums[r];
        while (l <= r && p >= k) {
            p /= nums[l++];
        }
        ans += r - l + 1;
    }
    return ans;
};
```

#### Kotlin

```kotlin
class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var ans = 0
        var l = 0
        var p = 1

        for (r in nums.indices) {
            p *= nums[r]
            while (l <= r && p >= k) {
                p /= nums[l]
                l++
            }
            ans += r - l + 1
        }

        return ans
    }
}
```

#### C#

```cs
public class Solution {
    public int NumSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.Length; ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
