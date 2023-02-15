# [1250. 检查「好数组」](https://leetcode.cn/problems/check-if-it-is-a-good-array)

[English Version](/solution/1200-1299/1250.Check%20If%20It%20Is%20a%20Good%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>nums</code>，你需要从中任选一些子集，然后将子集中每一个数乘以一个 <strong>任意整数</strong>，并求出他们的和。</p>

<p>假如该和结果为&nbsp;<code>1</code>，那么原数组就是一个「<strong>好数组</strong>」，则返回 <code>True</code>；否则请返回 <code>False</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [12,5,7,23]
<strong>输出：</strong>true
<strong>解释：</strong>挑选数字 5 和 7。
5*3 + 7*(-2) = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [29,6,10]
<strong>输出：</strong>true
<strong>解释：</strong>挑选数字 29, 6 和 10。
29*1 + 6*(-3) + 10*(-1) = 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,6]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学（裴蜀定理）**

我们先可以考虑选取两个数的情况，若选取的数是 $a$ 和 $b$，那么根据题目的要求，我们需要满足 $a \times x + b \times y = 1$，其中 $x$ 和 $y$ 是任意整数。

根据裴蜀定理，可以得知，如果 $a$ 和 $b$ 互质，那么上述等式一定有解。实际上，裴蜀定理也可以推广到多个数的情况，即如果 $a_1, a_2, \cdots, a_i$ 互质，那么 $a_1 \times x_1 + a_2 \times x_2 + \cdots + a_i \times x_i = 1$ 一定有解，其中 $x_1, x_2, \cdots, x_i$ 是任意整数。

因此，我们只需要判断在数组 `nums` 中是否存在 $i$ 个互质的数即可。两个数互质的充要条件是它们的最大公约数为 $1$。如果数组 `nums` 存在 $i$ 个互质的数，那么数组 `nums` 中的所有数的最大公约数也为 $1$。

所以我们将题目转化为：判断数组 `nums` 中的所有数的最大公约数是否为 $1$。遍历数组 `nums`，求出数组 `nums` 中的所有数的最大公约数即可。

时间复杂度 $O(n + log m)$，空间复杂度 $O(1)$，其中 $n$ 是数组 `nums` 的长度，而 $m$ 是数组 `nums` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isGoodArray(self, nums: List[int]) -> bool:
        return reduce(gcd, nums) == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isGoodArray(int[] nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isGoodArray(vector<int>& nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }
};
```

### **Go**

```go
func isGoodArray(nums []int) bool {
	g := 0
	for _, x := range nums {
		g = gcd(x, g)
	}
	return g == 1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
