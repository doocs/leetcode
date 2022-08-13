# [238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self)

[English Version](/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <em>数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;等于&nbsp;<code>nums</code>&nbsp;中除&nbsp;<code>nums[i]</code>&nbsp;之外其余各元素的乘积</em>&nbsp;。</p>

<p>题目数据 <strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内。</p>

<p>请<strong>不要使用除法，</strong>且在&nbsp;<code>O(<em>n</em>)</code> 时间复杂度内完成此题。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[1,2,3,4]</code>
<strong>输出:</strong> <code>[24,12,8,6]</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-1,1,0,-3,3]
<strong>输出:</strong> [0,0,9,0,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-30 &lt;= nums[i] &lt;= 30</code></li>
	<li><strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以在 <code>O(1)</code>&nbsp;的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组<strong>不被视为</strong>额外空间。）</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**暴力：**

正常计算除自身之外的乘积。

```txt
PRODUCT_EXCEPT_SELF(A)
    n = A.length
    let r[0..n]be a new array
    for i = 0 in n
        s = 1
        for j = 0 in n
            if j == i
                continue
            s *= A[j]
        r[i] = s
    return r
```

**左右乘积：**

分别从左至右，从右至左累乘一次即可。

```txt
PRODUCT_EXCEPT_SELF(A)
    n = A.length
    let r[0..n]be a new array
    p = 1
    q = 1
    for i = 0 in n
        r[i] = p
        p *= A[i]
    for i = n - 1 in 0 downto
        r[i] *= q
        q *= A[i]
    return r
```

可行性说明：

以 `nums = [1, 2, 3, 4]` 为例。

`r[i]` 的数值由 `nums[0..i - 1]` 的数值乘积所决定。在遍历结束后，最后一个元素得到了最终结果（因为累乘了前方所有数值）。

```txt
nums: [1, 2, 3, 4]
   r: [1, 1, 2, 6]
   p: [1, 2, 6, 24]
```

而其他位置的数值，距离结果还差什么呢，那就是相对自身，后方所有数值的乘积，于是就有了第二次，从后往前的过程。

```txt
nums: [1, 2, 3, 4]
   r: [24, 12, 8, 6]
   p: [24, 24, 12, 4]
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [1] * n
        left = right = 1
        for i in range(n):
            ans[i] = left
            left *= nums[i]
        for i in range(n - 1, -1, -1):
            ans[i] *= right
            right *= nums[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= nums[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
    const n = nums.length;
    let ans = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        ans[i] = left;
        left *= nums[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        ans[i] *= right;
        right *= nums[i];
    }
    return ans;
};
```

### **TypeScript**

```ts
function productExceptSelf(nums: number[]): number[] {
    const n = nums.length;
    let ans = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        ans[i] = left;
        left *= nums[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        ans[i] *= right;
        right *= nums[i];
    }
    return ans;
}
```

```ts
function productExceptSelf(nums: number[]): number[] {
    return nums.map((_, i) =>
        nums.reduce((pre, val, j) => pre * (i === j ? 1 : val), 1),
    );
}
```

### **Go**

```go
func productExceptSelf(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	left, right := 1, 1
	for i := 0; i < n; i++ {
		ans[i] = left
		left *= nums[i]
	}
	for i := n - 1; i >= 0; i-- {
		ans[i] *= right
		right *= nums[i]
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= nums[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let mut dp_left = vec![1_i32; nums.len()];
        let mut dp_right = vec![1_i32; nums.len()];
        for i in 1..nums.len() {
            dp_left[i] = dp_left[i - 1] * nums[i - 1];
        }
        for i in (0..(nums.len() - 1)).rev() {
            dp_right[i] = dp_right[i + 1] * nums[i + 1];
        }
        dp_left
            .into_iter()
            .enumerate()
            .map(|(i, x)| x * dp_right[i])
            .collect()
    }
}
```

```rust
impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut l = 1;
        let mut r = 1;
        let mut res = vec![0; n];
        for i in 0..n {
            res[i] = l;
            l *= nums[i];
        }
        for i in (0..n).rev() {
            res[i] *= r;
            r *= nums[i];
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
