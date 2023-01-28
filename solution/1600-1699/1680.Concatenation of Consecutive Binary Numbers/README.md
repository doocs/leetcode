# [1680. 连接连续二进制数字](https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers)

[English Version](/solution/1600-1699/1680.Concatenation%20of%20Consecutive%20Binary%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，请你将 <code>1</code> 到 <code>n</code> 的二进制表示连接起来，并返回连接结果对应的 <strong>十进制</strong> 数字对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 1
<b>输出：</b>1
<strong>解释：</strong>二进制的 "1" 对应着十进制的 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 3
<b>输出：</b>27
<strong>解释：</strong>二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>n = 12
<b>输出：</b>505379714
<b>解释：</b>连接结果为 "1101110010111011110001001101010111100" 。
对应的十进制数字为 118505380540 。
对 10<sup>9</sup> + 7 取余后，结果为 505379714 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

观察数字的连接规律，我们可以发现，当连接到第 $i$ 个数时，实际上是将前 $i-1$ 个数连接而成的结果 $ans$ 往左移动一定的位数，然后再加上 $i$ 这个数，移动的位数 $shift$ 是 $i$ 中二进制的位数。由于 $i$ 在不断加 $1$，移动的位数要么与上一次移动的位数保持不变，要么加一。当 $i$ 为 $2$ 的幂次方的时候，也即是说 $i$ 的二进制数中只有一位是 $1$ 时，移动的位数相比于上次加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def concatenatedBinary(self, n: int) -> int:
        mod = 10**9 + 7
        ans = 0
        for i in range(1, n + 1):
            ans = (ans << i.bit_length() | i) % mod
        return ans
```

```python
class Solution:
    def concatenatedBinary(self, n: int) -> int:
        mod = 10**9 + 7
        ans = shift = 0
        for i in range(1, n + 1):
            if (i & (i - 1)) == 0:
                shift += 1
            ans = (ans << shift | i) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int concatenatedBinary(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans << (32 - Integer.numberOfLeadingZeros(i)) | i) % mod;
        }
        return (int) ans;
    }
}
```

```java
class Solution {
    public int concatenatedBinary(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) {
                ++shift;
            }
            ans = (ans << shift | i) % mod;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int concatenatedBinary(int n) {
        const int mod = 1e9 + 7;
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans << (32 - __builtin_clz(i)) | i) % mod;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int concatenatedBinary(int n) {
        const int mod = 1e9 + 7;
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) {
                ++shift;
            }
            ans = (ans << shift | i) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func concatenatedBinary(n int) (ans int) {
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		ans = (ans<<bits.Len(uint(i)) | i) % mod
	}
	return
}
```

```go
func concatenatedBinary(n int) (ans int) {
	const mod = 1e9 + 7
	shift := 0
	for i := 1; i <= n; i++ {
		if i&(i-1) == 0 {
			shift++
		}
		ans = (ans<<shift | i) % mod
	}
	return
}
```

### **TypeScript**

```ts
function concatenatedBinary(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    let ans = 0n;
    let shift = 0n;
    for (let i = 1n; i <= n; ++i) {
        if ((i & (i - 1n)) == 0n) {
            ++shift;
        }
        ans = ((ans << shift) | i) % mod;
    }
    return Number(ans);
}
```

### **...**

```

```

<!-- tabs:end -->
