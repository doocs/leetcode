# [1922. 统计好数字的数目](https://leetcode.cn/problems/count-good-numbers)

[English Version](/solution/1900-1999/1922.Count%20Good%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个数字字符串是 <strong>好数字</strong> 当它满足（下标从 <strong>0</strong> 开始）<strong>偶数</strong> 下标处的数字为 <strong>偶数</strong> 且 <strong>奇数</strong> 下标处的数字为 <strong>质数</strong> （<code>2</code>，<code>3</code>，<code>5</code> 或 <code>7</code>）。</p>

<ul>
	<li>比方说，<code>"2582"</code> 是好数字，因为偶数下标处的数字（<code>2</code> 和 <code>8</code>）是偶数且奇数下标处的数字（<code>5</code> 和 <code>2</code>）为质数。但 <code>"3245"</code> <strong>不是</strong> 好数字，因为 <code>3</code> 在偶数下标处但不是偶数。</li>
</ul>

<p>给你一个整数 <code>n</code> ，请你返回长度为 <code>n</code> 且为好数字的数字字符串 <strong>总数</strong> 。由于答案可能会很大，请你将它对<strong> </strong><code>10<sup>9</sup> + 7</code> <strong>取余后返回</strong> 。</p>

<p>一个 <strong>数字字符串</strong> 是每一位都由 <code>0</code> 到 <code>9</code> 组成的字符串，且可能包含前导 0 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 1
<b>输出：</b>5
<b>解释：</b>长度为 1 的好数字包括 "0"，"2"，"4"，"6"，"8" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>400
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 50
<b>输出：</b>564908303
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 10<sup>15</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countGoodNumbers(self, n: int) -> int:
        mod = 10**9 + 7

        def myPow(x, n):
            res = 1
            while n:
                if (n & 1) == 1:
                    res = res * x % mod
                x = x * x % mod
                n >>= 1
            return res

        return myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int mod = 1000000007;

    public int countGoodNumbers(long n) {
        return (int) (myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % mod);
    }

    private long myPow(long x, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
```

### **C++**

```cpp
int MOD = 1000000007;

class Solution {
public:
    int countGoodNumbers(long long n) {
        return (int)(myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % MOD);
    }

private:
    long long myPow(long long x, long long n) {
        long long res = 1;
        while (n) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
};
```

### **Go**

```go
const mod int64 = 1e9 + 7

func countGoodNumbers(n int64) int {
	return int(myPow(5, (n+1)>>1) * myPow(4, n>>1) % mod)
}

func myPow(x, n int64) int64 {
	var res int64 = 1
	for n != 0 {
		if (n & 1) == 1 {
			res = res * x % mod
		}
		x = x * x % mod
		n >>= 1
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
