# [1015. 可被 K 整除的最小整数](https://leetcode.cn/problems/smallest-integer-divisible-by-k)

[English Version](/solution/1000-1099/1015.Smallest%20Integer%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数 <code>k</code>&nbsp;，你需要找出可以被 <code>k</code>&nbsp;整除的、仅包含数字 <code><strong>1</strong></code> 的最 <strong>小</strong> 正整数 <code>n</code>&nbsp;的长度。</p>

<p>返回 <code>n</code>&nbsp;的长度。如果不存在这样的 <code>n</code>&nbsp;，就返回-1。</p>

<p><strong>注意：</strong> <code>n</code>&nbsp;不符合 64 位带符号整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 1
<strong>输出：</strong>1
<strong>解释：</strong>最小的答案是 n = 1，其长度为 1。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2
<strong>输出：</strong>-1
<strong>解释：</strong>不存在可被 2 整除的正整数 n 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3
<strong>输出：</strong>3
<strong>解释：</strong>最小的答案是 n = 111，其长度为 3。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

我们注意到，正整数 $n$ 初始值为 $1$，每次乘以 $10$ 后再加 $1$，即 $n = n \times 10 + 1$，而 $(n \times 10 + 1) \bmod k = ((n \bmod k) \times 10 + 1) \bmod k$，因此我们可以通过计算 $n \bmod k$ 来判断 $n$ 是否能被 $k$ 整除。

我们从 $n = 1$ 开始，每次计算 $n \bmod k$，直到 $n \bmod k = 0$，此时 $n$ 就是我们要求的最小正整数，其长度即为 $n$ 的位数。否则，我们更新 $n = (n \times 10 + 1) \bmod k$。如果循环 $k$ 次后，仍然没有找到 $n \bmod k = 0$，则说明不存在这样的 $n$，返回 $-1$。

时间复杂度 $O(k)$，空间复杂度 $O(1)$。其中 $k$ 为给定的正整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestRepunitDivByK(self, k: int) -> int:
        n = 1 % k
        for i in range(1, k + 1):
            if n == 0:
                return i
            n = (n * 10 + 1) % k
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
};
```

### **Go**

```go
func smallestRepunitDivByK(k int) int {
	n := 1 % k
	for i := 1; i <= k; i++ {
		if n == 0 {
			return i
		}
		n = (n*10 + 1) % k
	}
	return -1
}
```

### **TypeScript**

```ts
function smallestRepunitDivByK(k: number): number {
    let n = 1 % k;
    for (let i = 1; i <= k; ++i) {
        if (n === 0) {
            return i;
        }
        n = (n * 10 + 1) % k;
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
