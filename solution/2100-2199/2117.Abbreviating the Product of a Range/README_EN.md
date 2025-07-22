---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2117.Abbreviating%20the%20Product%20of%20a%20Range/README_EN.md
rating: 2476
source: Biweekly Contest 68 Q4
tags:
    - Math
---

<!-- problem:start -->

# [2117. Abbreviating the Product of a Range](https://leetcode.com/problems/abbreviating-the-product-of-a-range)

[中文文档](/solution/2100-2199/2117.Abbreviating%20the%20Product%20of%20a%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given two positive integers <code>left</code> and <code>right</code> with <code>left &lt;= right</code>. Calculate the <strong>product</strong> of all integers in the <strong>inclusive</strong> range <code>[left, right]</code>.</p>

<p>Since the product may be very large, you will <strong>abbreviate</strong> it following these steps:</p>

<ol>
	<li>Count all <strong>trailing</strong> zeros in the product and <strong>remove</strong> them. Let us denote this count as <code>C</code>.

    <ul>
    	<li>For example, there are <code>3</code> trailing zeros in <code>1000</code>, and there are <code>0</code> trailing zeros in <code>546</code>.</li>
    </ul>
    </li>
    <li>Denote the remaining number of digits in the product as <code>d</code>. If <code>d &gt; 10</code>, then express the product as <code>&lt;pre&gt;...&lt;suf&gt;</code> where <code>&lt;pre&gt;</code> denotes the <strong>first</strong> <code>5</code> digits of the product, and <code>&lt;suf&gt;</code> denotes the <strong>last</strong> <code>5</code> digits of the product <strong>after</strong> removing all trailing zeros. If <code>d &lt;= 10</code>, we keep it unchanged.
    <ul>
    	<li>For example, we express <code>1234567654321</code> as <code>12345...54321</code>, but <code>1234567</code> is represented as <code>1234567</code>.</li>
    </ul>
    </li>
    <li>Finally, represent the product as a <strong>string</strong> <code>&quot;&lt;pre&gt;...&lt;suf&gt;eC&quot;</code>.
    <ul>
    	<li>For example, <code>12345678987600000</code> will be represented as <code>&quot;12345...89876e5&quot;</code>.</li>
    </ul>
    </li>

</ol>

<p>Return <em>a string denoting the <strong>abbreviated product</strong> of all integers in the <strong>inclusive</strong> range</em> <code>[left, right]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 1, right = 4
<strong>Output:</strong> &quot;24e0&quot;
<strong>Explanation:</strong> The product is 1 &times; 2 &times; 3 &times; 4 = 24.
There are no trailing zeros, so 24 remains the same. The abbreviation will end with &quot;e0&quot;.
Since the number of digits is 2, which is less than 10, we do not have to abbreviate it further.
Thus, the final representation is &quot;24e0&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 2, right = 11
<strong>Output:</strong> &quot;399168e2&quot;
<strong>Explanation:</strong> The product is 39916800.
There are 2 trailing zeros, which we remove to get 399168. The abbreviation will end with &quot;e2&quot;.
The number of digits after removing the trailing zeros is 6, so we do not abbreviate it further.
Hence, the abbreviated product is &quot;399168e2&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> left = 371, right = 375
<strong>Output:</strong> &quot;7219856259e3&quot;
<strong>Explanation:</strong> The product is 7219856259000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

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
            return str(int(pre)) + "..." + str(suf % int(1e5)).zfill(5) + "e" + str(c)
        return str(suf) + "e" + str(c)
```

#### Java

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

#### C++

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

#### Go

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

<!-- solution:end -->

<!-- problem:end -->
