# [2489. Number of Substrings With Fixed Ratio](https://leetcode.cn/problems/number-of-substrings-with-fixed-ratio)

[English Version](/solution/2400-2499/2489.Number%20of%20Substrings%20With%20Fixed%20Ratio/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a binary string <code>s</code>, and two integers <code>num1</code> and <code>num2</code>. <code>num1</code> and <code>num2</code> are coprime numbers.</p>

<p>A <strong>ratio substring</strong> is a substring of s where the ratio between the number of <code>0</code>&#39;s and the number of <code>1</code>&#39;s in the substring is exactly <code>num1 : num2</code>.</p>

<ul>
	<li>For example, if <code>num1 = 2</code> and <code>num2 = 3</code>, then <code>&quot;01011&quot;</code> and <code>&quot;1110000111&quot;</code> are ratio substrings, while <code>&quot;11000&quot;</code> is not.</li>
</ul>

<p>Return <em>the number of <strong>non-empty</strong> ratio substrings of </em><code>s</code>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
	<li>Two values <code>x</code> and <code>y</code> are <strong>coprime</strong> if <code>gcd(x, y) == 1</code> where <code>gcd(x, y)</code> is the greatest common divisor of <code>x</code> and <code>y</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110011&quot;, num1 = 1, num2 = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> There exist 4 non-empty ratio substrings.
- The substring s[0..2]: &quot;<u>011</u>0011&quot;. It contains one 0 and two 1&#39;s. The ratio is 1 : 2.
- The substring s[1..4]: &quot;0<u>110</u>011&quot;. It contains one 0 and two 1&#39;s. The ratio is 1 : 2.
- The substring s[4..6]: &quot;0110<u>011</u>&quot;. It contains one 0 and two 1&#39;s. The ratio is 1 : 2.
- The substring s[1..6]: &quot;0<u>110011</u>&quot;. It contains two 0&#39;s and four 1&#39;s. The ratio is 2 : 4 == 1 : 2.
It can be shown that there are no more ratio substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10101&quot;, num1 = 3, num2 = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no ratio substrings of s. We return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= num1, num2 &lt;= s.length</code></li>
	<li><code>num1</code> and <code>num2</code> are coprime integers.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 计数**

我们用 $one[i]$ 表示字符串 $s[0,..i]$ 中 $1$ 的个数，用 $zero[i]$ 表示字符串 $s[0,..i]$ 中 $0$ 的个数。子串符合条件，需要满足

$$
\frac{zero[j] - zero[i]}{one[j] - one[i]} = \frac{num1}{num2}
$$

其中 $i < j$。我们可以将上式转化为

$$
one[j] \times num1 - zero[j] \times num2 = one[i] \times num1 - zero[i] \times num2
$$

遍历到下标 $j$ 时，我们只需要统计有多少个下标 $i$ 满足上式即可。因此，我们可以用哈希表记录 $one[i] \times num1 - zero[i] \times num2$ 出现的次数，遍历到下标 $j$ 时，只需要统计 $one[j] \times num1 - zero[j] \times num2$ 出现的次数即可。

哈希表初始时只有一个键值对 $(0, 1)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fixedRatio(self, s: str, num1: int, num2: int) -> int:
        n0 = n1 = 0
        ans = 0
        cnt = Counter({0: 1})
        for c in s:
            n0 += c == '0'
            n1 += c == '1'
            x = n1 * num1 - n0 * num2
            ans += cnt[x]
            cnt[x] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long fixedRatio(String s, int num1, int num2) {
        long n0 = 0, n1 = 0;
        long ans = 0;
        Map<Long, Long> cnt = new HashMap<>();
        cnt.put(0L, 1L);
        for (char c : s.toCharArray()) {
            n0 += c == '0' ? 1 : 0;
            n1 += c == '1' ? 1 : 0;
            long x = n1 * num1 - n0 * num2;
            ans += cnt.getOrDefault(x, 0L);
            cnt.put(x, cnt.getOrDefault(x, 0L) + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long fixedRatio(string s, int num1, int num2) {
        ll n0 = 0, n1 = 0;
        ll ans = 0;
        unordered_map<ll, ll> cnt;
        cnt[0] = 1;
        for (char& c : s) {
            n0 += c == '0';
            n1 += c == '1';
            ll x = n1 * num1 - n0 * num2;
            ans += cnt[x];
            ++cnt[x];
        }
        return ans;
    }
};
```

### **Go**

```go
func fixedRatio(s string, num1 int, num2 int) int64 {
	n0, n1 := 0, 0
	ans := 0
	cnt := map[int]int{0: 1}
	for _, c := range s {
		if c == '0' {
			n0++
		} else {
			n1++
		}
		x := n1*num1 - n0*num2
		ans += cnt[x]
		cnt[x]++
	}
	return int64(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
