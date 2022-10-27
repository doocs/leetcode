# [1726. 同积元组](https://leetcode.cn/problems/tuple-with-same-product)

[English Version](/solution/1700-1799/1726.Tuple%20with%20Same%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>不同</strong> 正整数组成的数组 <code>nums</code> ，请你返回满足&nbsp;<code>a * b = c * d</code> 的元组<em> </em><code>(a, b, c, d)</code><em> </em>的数量。其中 <code>a</code>、<code>b</code>、<code>c</code> 和 <code>d</code> 都是 <code>nums</code> 中的元素，且 <code>a != b != c != d</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,4,6]
<strong>输出：</strong>8
<strong>解释：</strong>存在 8 个满足题意的元组：
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,5,10]
<strong>输出：</strong>16
<strong>解释：</strong>存在 16 个满足题意的元组：
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：组合数+哈希表**

假设存在 `n` 组数，对于其中任意两组数 `a、b` 和 `c、d`，均满足 $a * b = c * d$ 的条件，则这样的组合一共有 $\mathrm{C}_n^2 = \frac{n*(n-1)}{2}$ 个。

根据题意每一组满足上述条件的组合可以构成 `8` 个满足题意的元组，故将各个相同乘积的组合数乘以 `8` 相加即可得到结果。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def tupleSameProduct(self, nums: List[int]) -> int:
        product, n = {}, len(nums)
        for i in range(1, n):
            for j in range(0, i):
                multiplier = nums[i]*nums[j]
                product[multiplier] = product.setdefault(multiplier, 0) + 1
        ans = int(0)
        for v in product.values():
            ans += int(v*(v-1)/2)
        return ans << 3
```

### **Go**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```go
func tupleSameProduct(nums []int) int {
	product, n := make(map[int]int), len(nums)
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			multiplier := nums[i] * nums[j]
			if _, ok := product[multiplier]; !ok {
				product[multiplier] = 0
			}
			product[multiplier] += 1
		}
	}
	ans := 0
	for _, v := range product {
		ans += v * (v - 1) / 2
	}
	return ans << 3
}
```

### **...**

```

```

<!-- tabs:end -->
