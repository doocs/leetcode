---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9844.%20%E6%95%B0%E5%AD%97%E5%BA%8F%E5%88%97%E4%B8%AD%E6%9F%90%E4%B8%80%E4%BD%8D%E7%9A%84%E6%95%B0%E5%AD%97/README.md
---

<!-- problem:start -->

# [面试题 44. 数字序列中某一位的数字](https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)

## 题目描述

<!-- description:start -->

<p>数字以123456789101112131415&hellip;的格式序列化到一个字符序列中。在这个序列中，第5位是5，第13位是1，第19位是4，等等。</p>

<p>请写一个函数，求任意第n位对应的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 11
<strong>输出：</strong>0</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;&nbsp;2^31</code></li>
</ul>

<p>注意：本题与主站 400 题相同：<a href="https://leetcode.cn/problems/nth-digit/">https://leetcode.cn/problems/nth-digit/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

位数为 $k$ 的最小整数和最大整数分别为 $10^{k-1}$ 和 $10^k-1$，因此 $k$ 位数的总位数为 $k \times 9 \times 10^{k-1}$。

我们用 $k$ 表示当前数字的位数，用 $cnt$ 表示当前位数的数字的总数，初始时 $k=1$, $cnt=9$。

每次将 $n$ 减去 $cnt \times k$，当 $n$ 小于等于 $cnt \times k$ 时，说明 $n$ 对应的数字在当前位数的数字范围内，此时可以计算出对应的数字。

具体做法是，首先计算出 $n$ 对应的是当前位数的哪一个数字，然后计算出是该数字的第几位，从而得到该位上的数字。

时间复杂度 $O(\log_{10} n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的数字。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findNthDigit(self, n: int) -> int:
        k, cnt = 1, 9
        while k * cnt < n:
            n -= k * cnt
            k += 1
            cnt *= 10
        num = 10 ** (k - 1) + (n - 1) // k
        idx = (n - 1) % k
        return int(str(num)[idx])
```

#### Java

```java
class Solution {
    public int findNthDigit(int n) {
        int k = 1, cnt = 9;
        while ((long) k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = (int) Math.pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return String.valueOf(num).charAt(idx) - '0';
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findNthDigit(int n) {
        int k = 1, cnt = 9;
        while (1ll * k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return to_string(num)[idx] - '0';
    }
};
```

#### Go

```go
func findNthDigit(n int) int {
	k, cnt := 1, 9
	for k*cnt < n {
		n -= k * cnt
		k++
		cnt *= 10
	}
	num := int(math.Pow10(k-1)) + (n-1)/k
	idx := (n - 1) % k
	return int(strconv.Itoa(num)[idx] - '0')
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number}
 */
var findNthDigit = function (n) {
    let k = 1,
        cnt = 9;
    while (k * cnt < n) {
        n -= k * cnt;
        ++k;
        cnt *= 10;
    }
    const num = Math.pow(10, k - 1) + (n - 1) / k;
    const idx = (n - 1) % k;
    return num.toString()[idx];
};
```

#### C#

```cs
public class Solution {
    public int FindNthDigit(int n) {
        int k = 1, cnt = 9;
        while ((long) k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = (int) Math.Pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return num.ToString()[idx] - '0';
    }
}
```

#### Swift

```swift
class Solution {
    func findNthDigit(_ n: Int) -> Int {
        var n = n
        var k = 1
        var count = 9

        while k * count < n {
            n -= k * count
            k += 1
            count *= 10
        }

        let num = Int(Double(10).power(Double(k - 1))) + (n - 1) / k
        let idx = (n - 1) % k
        let numString = String(num)
        let char = numString[numString.index(numString.startIndex, offsetBy: idx)]

        return char.wholeNumberValue!
    }
}

extension Double {
    func power(_ exponent: Double) -> Double {
        return pow(self, exponent)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findNthDigit(self, n: int) -> int:
        if n < 10:
            return n
        n -= 10
        k, p = 2, 10
        while n >= 9 * k * p:
            n -= 9 * k * p
            k += 1
            p *= 10
        x = p + n // k
        return int(str(x)[n % k])
```

#### Java

```java
class Solution {
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        n -= 10;
        int k = 2, p = 10;
        while (n >= (long) 9 * k * p) {
            n -= 9 * k * p;
            ++k;
            p *= 10;
        }
        int x = p + n / k;
        return String.valueOf(x).charAt(n % k) - '0';
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        n -= 10;
        int k = 2, p = 10;
        while (n >= 9ll * k * p) {
            n -= 9 * k * p;
            ++k;
            p *= 10;
        }
        int x = p + n / k;
        return to_string(x)[n % k] - '0';
    }
};
```

#### Go

```go
func findNthDigit(n int) int {
	if n < 10 {
		return n
	}
	n -= 10
	k, p := 2, 10
	for n >= 9*k*p {
		n -= 9 * k * p
		k++
		p *= 10
	}
	x := p + n/k
	return int(strconv.Itoa(x)[n%k] - '0')
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
