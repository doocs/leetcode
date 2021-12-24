# [238. 除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self)

[English Version](/solution/0200-0299/0238.Product%20of%20Array%20Except%20Self/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<em>n</em>&nbsp;的整数数组&nbsp;<code>nums</code>，其中&nbsp;<em>n</em> &gt; 1，返回输出数组&nbsp;<code>output</code>&nbsp;，其中 <code>output[i]</code>&nbsp;等于&nbsp;<code>nums</code>&nbsp;中除&nbsp;<code>nums[i]</code>&nbsp;之外其余各元素的乘积。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>[1,2,3,4]</code>
<strong>输出:</strong> <code>[24,12,8,6]</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong>题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。</p>

<p><strong>说明: </strong>请<strong>不要使用除法，</strong>且在&nbsp;O(<em>n</em>) 时间复杂度内完成此题。</p>

<p><strong>进阶：</strong><br>
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组<strong>不被视为</strong>额外空间。）</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        output = [1 for _ in nums]
        left = right = 1
        for i in range(n):
            output[i] = left
            left *= nums[i]
        for i in range(n - 1, -1, -1):
            output[i] *= right
            right *= nums[i]
        return output
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            output[i] = left;
            left *= nums[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            output[i] *= right;
            right *= nums[i];
        }
        return output;
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
    let output = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        output[i] = left;
        left *= nums[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        output[i] *= right;
        right *= nums[i];
    }
    return output;
};
```

### **Go**

利用前缀和思想，分别求出 `i` 左右两侧的乘积

```go
func productExceptSelf(nums []int) []int {
	n := len(nums)

	l := make([]int, n)
	l[0] = 1
	for i := 1; i < n; i++ {
		l[i] = l[i-1] * nums[i-1]
	}

	r := make([]int, n)
	r[n-1] = 1
	for i := n - 2; i >= 0; i-- {
		r[i] = r[i+1] * nums[i+1]
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = l[i] * r[i]
	}

	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
