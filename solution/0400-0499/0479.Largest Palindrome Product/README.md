# [479. 最大回文数乘积](https://leetcode.cn/problems/largest-palindrome-product)

[English Version](/solution/0400-0499/0479.Largest%20Palindrome%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 n ，返回 <em>可表示为两个 <code>n</code>&nbsp;位整数乘积的 <strong>最大回文整数</strong></em> 。因为答案可能非常大，所以返回它对 <code>1337</code> <strong>取余</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>n = 2
<b>输出：</b>987
<strong>解释：</strong>99 x 91 = 9009, 9009 % 1337 = 987
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> n = 1
<strong>输出：</strong> 9
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestPalindrome(self, n: int) -> int:
        mx = 10**n - 1
        for a in range(mx, mx // 10, -1):
            b = x = a
            while b:
                x = x * 10 + b % 10
                b //= 10
            t = mx
            while t * t >= x:
                if x % t == 0:
                    return x % 1337
                t -= 1
        return 9
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestPalindrome(int n) {
        int mx = (int) Math.pow(10, n) - 1;
        for (int a = mx; a > mx / 10; --a) {
            int b = a;
            long x = a;
            while (b != 0) {
                x = x * 10 + b % 10;
                b /= 10;
            }
            for (long t = mx; t * t >= x; --t) {
                if (x % t == 0) {
                    return (int) (x % 1337);
                }
            }
        }
        return 9;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestPalindrome(int n) {
        int mx = pow(10, n) - 1;
        for (int a = mx; a > mx / 10; --a) {
            int b = a;
            long x = a;
            while (b) {
                x = x * 10 + b % 10;
                b /= 10;
            }
            for (long t = mx; t * t >= x; --t)
                if (x % t == 0)
                    return x % 1337;
        }
        return 9;
    }
};
```

### **Go**

```go
func largestPalindrome(n int) int {
	mx := int(math.Pow10(n)) - 1
	for a := mx; a > mx/10; a-- {
		x := a
		for b := a; b != 0; b /= 10 {
			x = x*10 + b%10
		}
		for t := mx; t*t >= x; t-- {
			if x%t == 0 {
				return x % 1337
			}
		}
	}
	return 9
}
```

### **...**

```

```

<!-- tabs:end -->
