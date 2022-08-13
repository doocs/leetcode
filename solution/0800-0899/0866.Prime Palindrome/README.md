# [866. 回文素数](https://leetcode.cn/problems/prime-palindrome)

[English Version](/solution/0800-0899/0866.Prime%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>求出大于或等于&nbsp;<code>N</code>&nbsp;的最小回文素数。</p>

<p>回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是<em>素数</em>。</p>

<p>例如，2，3，5，7，11 以及&nbsp;13 是素数。</p>

<p>回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是<em>回文数。</em></p>

<p>例如，12321 是回文数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>6
<strong>输出：</strong>7
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>8
<strong>输出：</strong>11
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入：</strong>13
<strong>输出：</strong>101</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 10^8</code></li>
	<li>答案肯定存在，且小于&nbsp;<code>2 * 10^8</code>。</li>
</ul>

<p>&nbsp;</p>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def primePalindrome(self, n: int) -> int:
        def is_prime(x):
            if x < 2:
                return False
            v = 2
            while v * v <= x:
                if x % v == 0:
                    return False
                v += 1
            return True

        def reverse(x):
            res = 0
            while x:
                res = res * 10 + x % 10
                x //= 10
            return res

        while 1:
            if reverse(n) == n and is_prime(n):
                return n
            if 10**7 < n < 10**8:
                n = 10**8
            n += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int primePalindrome(int n) {
        while (true) {
            if (reverse(n) == n && isPrime(n)) {
                return n;
            }
            if (n > 10000000 && n < 100000000) {
                n = 100000000;
            }
            ++n;
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int v = 2; v * v <= x; ++v) {
            if (x % v == 0) {
                return false;
            }
        }
        return true;
    }

    private int reverse(int x) {
        int res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int primePalindrome(int n) {
        while (1) {
            if (reverse(n) == n && isPrime(n)) return n;
            if (n > 10000000 && n < 100000000) n = 100000000;
            ++n;
        }
    }

    bool isPrime(int x) {
        if (x < 2) return false;
        for (int v = 2; v * v <= x; ++v)
            if (x % v == 0)
                return false;
        return true;
    }

    int reverse(int x) {
        int res = 0;
        while (x) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
};
```

### **Go**

```go
func primePalindrome(n int) int {
	isPrime := func(x int) bool {
		if x < 2 {
			return false
		}
		for v := 2; v*v <= x; v++ {
			if x%v == 0 {
				return false
			}
		}
		return true
	}

	reverse := func(x int) int {
		res := 0
		for x != 0 {
			res = res*10 + x%10
			x /= 10
		}
		return res
	}
	for {
		if reverse(n) == n && isPrime(n) {
			return n
		}
		if n > 10000000 && n < 100000000 {
			n = 100000000
		}
		n++
	}
}
```

### **...**

```

```

<!-- tabs:end -->
