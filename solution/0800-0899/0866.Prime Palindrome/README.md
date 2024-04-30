# [866. 回文质数](https://leetcode.cn/problems/prime-palindrome)

[English Version](/solution/0800-0899/0866.Prime%20Palindrome/README_EN.md)

<!-- tags:数学,数论 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，返回大于或等于 <code>n</code> 的最小&nbsp;<stron><strong>回文质数</strong></stron>。</p>
<!-- 一个整数是素数的定义，以及1不是素数的说明 -->

<p>一个整数如果恰好有两个除数：<code>1</code> 和它本身，那么它是 <strong>质数</strong> 。注意，<code>1</code> 不是质数。</p>

<ul>
	<li>例如，<code>2</code>、<code>3</code>、<code>5</code>、<code>7</code>、<code>11</code> 和 <code>13</code> 都是质数。</li>
</ul>

<p>一个整数如果从左向右读和从右向左读是相同的，那么它是<strong> 回文数 </strong>。</p>

<ul>
	<li>例如，<code>101</code> 和 <code>12321</code> 都是回文数。</li>
</ul>

<p>测试用例保证答案总是存在，并且在 <code>[2, 2 * 10<sup>8</sup>]</code> 范围内。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 6
<strong>输出：</strong>7
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 8
<strong>输出：</strong>11
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>101
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
