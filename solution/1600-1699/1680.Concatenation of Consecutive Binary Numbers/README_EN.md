# [1680. Concatenation of Consecutive Binary Numbers](https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers)

[中文文档](/solution/1600-1699/1680.Concatenation%20of%20Consecutive%20Binary%20Numbers/README.md)

## Description

<p>Given an integer <code>n</code>, return <em>the <strong>decimal value</strong> of the binary string formed by concatenating the binary representations of </em><code>1</code><em> to </em><code>n</code><em> in order, <strong>modulo </strong></em><code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation: </strong>&quot;1&quot; in binary corresponds to the decimal value 1. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 27
<strong>Explanation: </strong>In binary, 1, 2, and 3 corresponds to &quot;1&quot;, &quot;10&quot;, and &quot;11&quot;.
After concatenating them, we have &quot;11011&quot;, which corresponds to the decimal value 27.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 12
<strong>Output:</strong> 505379714
<strong>Explanation</strong>: The concatenation results in &quot;1101110010111011110001001101010111100&quot;.
The decimal value of that is 118505380540.
After modulo 10<sup>9</sup> + 7, the result is 505379714.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
