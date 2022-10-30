# [2457. 美丽整数的最小增量](https://leetcode.cn/problems/minimum-addition-to-make-integer-beautiful)

[English Version](/solution/2400-2499/2457.Minimum%20Addition%20to%20Make%20Integer%20Beautiful/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数 <code>n</code> 和 <code>target</code> 。</p>

<p>如果某个整数每一位上的数字相加小于或等于 <code>target</code> ，则认为这个整数是一个 <strong>美丽整数</strong> 。</p>

<p>找出并返回满足 <code>n + x</code> 是 <strong>美丽整数</strong> 的最小非负整数 <code>x</code> 。生成的输入保证总可以使 <code>n</code> 变成一个美丽整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 16, target = 6
<strong>输出：</strong>4
<strong>解释：</strong>最初，n 是 16 ，且其每一位数字的和是 1 + 6 = 7 。在加 4 之后，n 变为 20 且每一位数字的和变成 2 + 0 = 2 。可以证明无法加上一个小于 4 的非负整数使 n 变成一个美丽整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 467, target = 6
<strong>输出：</strong>33
<strong>解释：</strong>最初，n 是 467 ，且其每一位数字的和是 4 + 6 + 7 = 17 。在加 33 之后，n 变为 500 且每一位数字的和变成 5 + 0 + 0 = 5 。可以证明无法加上一个小于 33 的非负整数使 n 变成一个美丽整数。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1, target = 1
<strong>输出：</strong>0
<strong>解释：</strong>最初，n 是 1 ，且其每一位数字的和是 1 ，已经小于等于 target 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>12</sup></code></li>
	<li><code>1 &lt;= target &lt;= 150</code></li>
	<li>生成的输入保证总可以使 <code>n</code> 变成一个美丽整数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们定义函数 $f(x)$ 表示一个整数 $x$ 的每一位数字之和，那么题目要求的最小非负整数 $x$ 就是 $f(n + x) \leq target$ 的最小值。

初始化 $x = 0$，循环判断 $f(n+x)$ 是否大于 $target$，如果大于，此时 $n+x$ 的最低一位非 $0$ 的数要置为 $0$，而前一位要加 $1$，然后继续判断。

循环结束，返回 $x$ 即可。

时间复杂度 $O(\log^2 n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def f(x):
            v = 0
            while x:
                v += x % 10
                x //= 10
            return v

        x = 0
        while f(n + x) > target:
            y = n + x
            p = 10
            while y % 10 == 0:
                y //= 10
                p *= 10
            x = (y // 10 + 1) * p - n
        return x
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        long x = 0;
        while (f(n + x) > target) {
            long y = n + x;
            long p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }

    private int f(long x) {
        int v = 0;
        while (x > 0) {
            v += x % 10;
            x /= 10;
        }
        return v;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long makeIntegerBeautiful(long long n, int target) {
        auto f = [](ll x) {
            int v = 0;
            while (x) {
                v += x % 10;
                x /= 10;
            }
            return v;
        };
        ll x = 0;
        while (f(n + x) > target) {
            ll y = n + x;
            ll p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }
};
```

### **Go**

```go
func makeIntegerBeautiful(n int64, target int) int64 {
	f := func(x int64) int {
		v := 0
		for x > 0 {
			v += int(x % 10)
			x /= 10
		}
		return v
	}
	var x int64
	for f(n+x) > target {
		y := n + x
		var p int64 = 10
		for y%10 == 0 {
			y /= 10
			p *= 10
		}
		x = (y/10+1)*p - n
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
