---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [238. 除了自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self)

[English Version](/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>，返回 数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;等于&nbsp;<code>nums</code>&nbsp;中除了&nbsp;<code>nums[i]</code>&nbsp;之外其余各元素的乘积&nbsp;。</p>

<p>题目数据 <strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内。</p>

<p>请&nbsp;<strong>不要使用除法，</strong>且在&nbsp;<code>O(n)</code> 时间复杂度内完成此题。</p>

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
	<li>输入&nbsp;<strong>保证</strong> 数组&nbsp;<code>answer[i]</code>&nbsp;在&nbsp; <strong>32 位</strong> 整数范围内</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以在 <code>O(1)</code>&nbsp;的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组&nbsp;<strong>不被视为&nbsp;</strong>额外空间。）</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次遍历

我们定义两个变量 $\textit{left}$ 和 $\textit{right}$，分别表示当前元素左边所有元素的乘积和右边所有元素的乘积。初始时 $\textit{left}=1$, $\textit{right}=1$。定义一个长度为 $n$ 的答案数组 $\textit{ans}$。

我们先从左到右遍历数组，对于遍历到的第 $i$ 个元素，我们用 $\textit{left}$ 更新 $\textit{ans}[i]$，然后 $\textit{left}$ 乘以 $\textit{nums}[i]$。

然后，我们从右到左遍历数组，对于遍历到的第 $i$ 个元素，我们更新 $\textit{ans}[i]$ 为 $\textit{ans}[i] \times \textit{right}$，然后 $\textit{right}$ 乘以 $\textit{nums}[i]$。

遍历结束后，返回答案数组 $\textit{ans}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        left = right = 1
        for i, x in enumerate(nums):
            ans[i] = left
            left *= x
        for i in range(n - 1, -1, -1):
            ans[i] *= right
            right *= nums[i]
        return ans
```

#### Java

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

#### C++

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
        for (int i = n - 1, right = 1; ~i; --i) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func productExceptSelf(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	left, right := 1, 1
	for i, x := range nums {
		ans[i] = left
		left *= x
	}
	for i := n - 1; i >= 0; i-- {
		ans[i] *= right
		right *= nums[i]
	}
	return ans
}
```

#### TypeScript

```ts
function productExceptSelf(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(n);
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

#### Rust

```rust
impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![1; n];
        for i in 1..n {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        let mut r = 1;
        for i in (0..n).rev() {
            ans[i] *= r;
            r *= nums[i];
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
    const n = nums.length;
    const ans = new Array(n);
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

#### C#

```cs
public class Solution {
    public int[] ProductExceptSelf(int[] nums) {
        int n = nums.Length;
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

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function productExceptSelf($nums) {
        $n = count($nums);
        $ans = [];
        for ($i = 0, $left = 1; $i < $n; ++$i) {
            $ans[$i] = $left;
            $left *= $nums[$i];
        }
        for ($i = $n - 1, $right = 1; $i >= 0; --$i) {
            $ans[$i] *= $right;
            $right *= $nums[$i];
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
