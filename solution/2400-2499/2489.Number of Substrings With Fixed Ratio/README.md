# [2489. 固定比率的子字符串数](https://leetcode.cn/problems/number-of-substrings-with-fixed-ratio)

[English Version](/solution/2400-2499/2489.Number%20of%20Substrings%20With%20Fixed%20Ratio/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制字符串 <code>s</code>&nbsp;和两个整数 <code>num1</code> 和 <code>num2</code>。<code>num1</code> 和 <code>num2</code> 为互质。</p>

<p><strong>比率子串&nbsp;</strong>是 s 的子串，其中子串中 <code>0</code> 的数量与 <code>1</code>&nbsp;的数量之比正好是&nbsp;<code>num1 : num2</code>。</p>

<ul>
	<li>例如，如果 <code>num1 = 2</code>&nbsp;和 <code>num2 = 3</code>，那么 <code>"01011"</code>&nbsp;和 <code>"1110000111"</code>&nbsp;是比率子串，而 <code>"11000"</code>&nbsp;不是。</li>
</ul>

<p>返回 <em><code>s</code> 的&nbsp;<strong>非空&nbsp;</strong>比率子串的个数。</em></p>

<p><b>注意</b>:</p>

<ul>
	<li><strong>子串&nbsp;</strong>是字符串中连续的字符序列。</li>
	<li>如果 <code>gcd(x, y) == 1</code>，则 <code>x</code> 和 <code>y</code> 为&nbsp;<strong>互质</strong>，其中 <code>gcd(x, y)</code>&nbsp;为 <code>x</code>&nbsp;和 <code>y</code> 的最大公约数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "0110011", num1 = 1, num2 = 2
<strong>输出:</strong> 4
<strong>解释:</strong> 有 4 个非空的比率子串。
- 子字符串 s[0..2]: "<u>011</u>0011"。它包含一个 0 和两个 1。比例是 1:2。
- 子字符串 s[1..4]: "0<u>110</u>011"。它包含一个 0 和两个 1。比例是 1:2。
- 子字符串 s[4..6]: "0110<u>011</u>"。它包含一个 0 和两个 1。比例是 1:2。
- 子字符串 s[1..6]: "0<u>110011</u>"。它包含两个 0 和四个 1。比例是 2:4 == 1:2。
它可以显示没有更多的比率子串。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "10101", num1 = 3, num2 = 1
<strong>输出:</strong> 0
<strong>解释:</strong> s 没有比率子串，返回 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= num1, num2 &lt;= s.length</code></li>
	<li><code>num1</code> 和&nbsp;<code>num2</code> 互质。</li>
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
