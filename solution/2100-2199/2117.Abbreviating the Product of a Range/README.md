# [2117. 一个区间内所有数乘积的缩写](https://leetcode.cn/problems/abbreviating-the-product-of-a-range)

[English Version](/solution/2100-2199/2117.Abbreviating%20the%20Product%20of%20a%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;，满足&nbsp;<code>left &lt;= right</code>&nbsp;。请你计算&nbsp;<strong>闭区间</strong>&nbsp;<code>[left, right]</code>&nbsp;中所有整数的&nbsp;<strong>乘积</strong>&nbsp;。</p>

<p>由于乘积可能非常大，你需要将它按照以下步骤 <strong>缩写</strong>&nbsp;：</p>

<ol>
	<li>统计乘积中&nbsp;<strong>后缀</strong> 0 的数目，并 <strong>移除</strong> 这些 0 ，将这个数目记为&nbsp;<code>C</code>&nbsp;。

    <ul>
    	<li>比方说，<code>1000</code>&nbsp;中有 <code>3</code> 个后缀 0&nbsp;，<code>546</code>&nbsp;中没有后缀 0 。</li>
    </ul>
    </li>
    <li>将乘积中剩余数字的位数记为&nbsp;<code>d</code>&nbsp;。如果&nbsp;<code>d &gt; 10</code>&nbsp;，那么将乘积表示为&nbsp;<code>&lt;pre&gt;...&lt;suf&gt;</code>&nbsp;的形式，其中&nbsp;<code>&lt;pre&gt;</code>&nbsp;表示乘积最 <strong>开始</strong>&nbsp;的 <code>5</code>&nbsp;个数位，<code>&lt;suf&gt;</code>&nbsp;表示删除后缀 0 <strong>之后</strong>&nbsp;结尾的 <code>5</code>&nbsp;个数位。如果&nbsp;<code>d &lt;= 10</code>&nbsp;，我们不对它做修改。
    <ul>
    	<li>比方说，我们将&nbsp;<code>1234567654321</code>&nbsp;表示为&nbsp;<code>12345...54321</code>&nbsp;，但是&nbsp;<code>1234567</code>&nbsp;仍然表示为&nbsp;<code>1234567</code>&nbsp;。</li>
    </ul>
    </li>
    <li>最后，将乘积表示为 <strong>字符串</strong>&nbsp;<code>"&lt;pre&gt;...&lt;suf&gt;eC"</code>&nbsp;。
    <ul>
    	<li>比方说，<code>12345678987600000</code>&nbsp;被表示为&nbsp;<code>"12345...89876e5"</code>&nbsp;。</li>
    </ul>
    </li>

</ol>

<p>请你返回一个字符串，表示 <strong>闭区间</strong>&nbsp;<code>[left, right]</code>&nbsp;中所有整数&nbsp;<strong>乘积</strong>&nbsp;的&nbsp;<strong>缩写</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>left = 1, right = 4
<b>输出：</b>"24e0"
<strong>解释：</strong>
乘积为 1 × 2 × 3 × 4 = 24 。
由于没有后缀 0 ，所以 24 保持不变，缩写的结尾为 "e0" 。
因为乘积的结果是 2 位数，小于 10 ，所欲我们不进一步将它缩写。
所以，最终将乘积表示为 "24e0" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>left = 2, right = 11
<strong>输出：</strong>"399168e2"
<strong>解释：</strong>乘积为 39916800 。
有 2 个后缀 0 ，删除后得到 399168 。缩写的结尾为 "e2" 。 
删除后缀 0 后是 6 位数，不需要进一步缩写。 
所以，最终将乘积表示为 "399168e2" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>left = 371, right = 375
<strong>输出：</strong>"7219856259e3"
<strong>解释：</strong>乘积为 7219856259000 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
import numpy


class Solution:
    def abbreviateProduct(self, left: int, right: int) -> str:
        cnt2 = cnt5 = 0
        z = numpy.float128(0)
        for x in range(left, right + 1):
            z += numpy.log10(x)
            while x % 2 == 0:
                x //= 2
                cnt2 += 1
            while x % 5 == 0:
                x //= 5
                cnt5 += 1
        c = cnt2 = cnt5 = min(cnt2, cnt5)
        suf = y = 1
        gt = False
        for x in range(left, right + 1):
            while cnt2 and x % 2 == 0:
                x //= 2
                cnt2 -= 1
            while cnt5 and x % 5 == 0:
                x //= 5
                cnt5 -= 1
            suf = suf * x % 100000
            if not gt:
                y *= x
                gt = y >= 1e10
        if not gt:
            return str(y) + "e" + str(c)
        pre = int(pow(10, z - int(z) + 4))
        return str(pre) + "..." + str(suf).zfill(5) + "e" + str(c)
```

```java
class Solution {

    public String abbreviateProduct(int left, int right) {
        int cnt2 = 0, cnt5 = 0;
        for (int i = left; i <= right; ++i) {
            int x = i;
            for (; x % 2 == 0; x /= 2) {
                ++cnt2;
            }
            for (; x % 5 == 0; x /= 5) {
                ++cnt5;
            }
        }
        int c = Math.min(cnt2, cnt5);
        cnt2 = cnt5 = c;
        long suf = 1;
        double pre = 1;
        boolean gt = false;
        for (int i = left; i <= right; ++i) {
            for (suf *= i; cnt2 > 0 && suf % 2 == 0; suf /= 2) {
                --cnt2;
            }
            for (; cnt5 > 0 && suf % 5 == 0; suf /= 5) {
                --cnt5;
            }
            if (suf >= (long) 1e10) {
                gt = true;
                suf %= (long) 1e10;
            }
            for (pre *= i; pre > 1e5; pre /= 10) {
            }
        }
        if (gt) {
            return (int) pre + "..." + String.format("%05d", suf % (int) 1e5) + "e" + c;
        }
        return suf + "e" + c;
    }
}
```

```cpp
class Solution {
public:
    string abbreviateProduct(int left, int right) {
        int cnt2 = 0, cnt5 = 0;
        for (int i = left; i <= right; ++i) {
            int x = i;
            for (; x % 2 == 0; x /= 2) {
                ++cnt2;
            }
            for (; x % 5 == 0; x /= 5) {
                ++cnt5;
            }
        }
        int c = min(cnt2, cnt5);
        cnt2 = cnt5 = c;
        long long suf = 1;
        long double pre = 1;
        bool gt = false;
        for (int i = left; i <= right; ++i) {
            for (suf *= i; cnt2 && suf % 2 == 0; suf /= 2) {
                --cnt2;
            }
            for (; cnt5 && suf % 5 == 0; suf /= 5) {
                --cnt5;
            }
            if (suf >= 1e10) {
                gt = true;
                suf %= (long long) 1e10;
            }
            for (pre *= i; pre > 1e5; pre /= 10) {
            }
        }
        if (gt) {
            char buf[10];
            snprintf(buf, sizeof(buf), "%0*lld", 5, suf % (int) 1e5);
            return to_string((int) pre) + "..." + string(buf) + "e" + to_string(c);
        }
        return to_string(suf) + "e" + to_string(c);
    }
};
```

```go
func abbreviateProduct(left int, right int) string {
	cnt2, cnt5 := 0, 0
	for i := left; i <= right; i++ {
		x := i
		for x%2 == 0 {
			cnt2++
			x /= 2
		}
		for x%5 == 0 {
			cnt5++
			x /= 5
		}
	}
	c := int(math.Min(float64(cnt2), float64(cnt5)))
	cnt2 = c
	cnt5 = c
	suf := int64(1)
	pre := float64(1)
	gt := false
	for i := left; i <= right; i++ {
		for suf *= int64(i); cnt2 > 0 && suf%2 == 0; {
			cnt2--
			suf /= int64(2)
		}
		for cnt5 > 0 && suf%5 == 0 {
			cnt5--
			suf /= int64(5)
		}
		if float64(suf) >= 1e10 {
			gt = true
			suf %= int64(1e10)
		}
		for pre *= float64(i); pre > 1e5; {
			pre /= 10
		}
	}
	if gt {
		return fmt.Sprintf("%05d...%05de%d", int(pre), int(suf)%int(1e5), c)
	}
	return fmt.Sprintf("%de%d", suf, c)
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def abbreviateProduct(self, left: int, right: int) -> str:
        cnt2 = cnt5 = 0
        for x in range(left, right + 1):
            while x % 2 == 0:
                cnt2 += 1
                x //= 2
            while x % 5 == 0:
                cnt5 += 1
                x //= 5
        c = cnt2 = cnt5 = min(cnt2, cnt5)
        pre = suf = 1
        gt = False
        for x in range(left, right + 1):
            suf *= x
            while cnt2 and suf % 2 == 0:
                suf //= 2
                cnt2 -= 1
            while cnt5 and suf % 5 == 0:
                suf //= 5
                cnt5 -= 1
            if suf >= 1e10:
                gt = True
                suf %= int(1e10)
            pre *= x
            while pre > 1e5:
                pre /= 10
        if gt:
            return str(int(pre)) + "..." + str(suf % int(1e5)).zfill(5) + 'e' + str(c)
        return str(suf) + "e" + str(c)
```

<!-- tabs:end -->

<!-- end -->
