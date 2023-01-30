# [1540. K 次操作转变字符串](https://leetcode.cn/problems/can-convert-string-in-k-moves)

[English Version](/solution/1500-1599/1540.Can%20Convert%20String%20in%20K%20Moves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，你的目标是在 <code>k</code>&nbsp;次操作以内把字符串&nbsp;<code>s</code>&nbsp;转变成&nbsp;<code>t</code>&nbsp;。</p>

<p>在第 <code>i</code>&nbsp;次操作时（<code>1 &lt;= i &lt;= k</code>），你可以选择进行如下操作：</p>

<ul>
	<li>选择字符串 <code>s</code>&nbsp;中满足 <code>1 &lt;= j &lt;= s.length</code>&nbsp;且之前未被选过的任意下标 <code>j</code>&nbsp;（下标从 1 开始），并将此位置的字符切换 <code>i</code>&nbsp;次。</li>
	<li>不进行任何操作。</li>
</ul>

<p>切换 1 个字符的意思是用字母表中该字母的下一个字母替换它（字母表环状接起来，所以 <code>'z'</code>&nbsp;切换后会变成 <code>'a'</code>）。第 <code>i</code>&nbsp;次操作意味着该字符应切换&nbsp;<code>i</code>&nbsp;次</p>

<p>请记住任意一个下标 <code>j</code>&nbsp;最多只能被操作&nbsp;1 次。</p>

<p>如果在不超过 <code>k</code>&nbsp;次操作内可以把字符串 <code>s</code>&nbsp;转变成 <code>t</code>&nbsp;，那么请你返回&nbsp;<code>true</code>&nbsp;，否则请你返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "input", t = "ouput", k = 9
<strong>输出：</strong>true
<strong>解释：</strong>第 6 次操作时，我们将 'i' 切换 6 次得到 'o' 。第 7 次操作时，我们将 'n' 切换 7 次得到 'u' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abc", t = "bcd", k = 10
<strong>输出：</strong>false
<strong>解释：</strong>我们需要将每个字符切换 1 次才能得到 t 。我们可以在第 1 次操作时将 'a' 切换成 'b' ，但另外 2 个字母在剩余操作中无法再转变为 t 中对应字母。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aab", t = "bbb", k = 27
<strong>输出：</strong>true
<strong>解释：</strong>第 1 次操作时，我们将第一个 'a' 切换 1 次得到 'b' 。在第 27 次操作时，我们将第二个字母 'a' 切换 27 次得到 'b' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= k &lt;= 10^9</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们首先判断字符串 $s$ 和字符串 $t$ 的长度是否相等，如果不相等，直接返回 `false`。

如果相等，我们可以统计每个位置的字符需要操作的最小次数，即 $cnt[x]$ 表示最小操作次数为 $x$ 的字符的个数。

如果有 $cnt[x]$ 个字符需要操作 $x$ 次，那么我们需要 $x + 26 \times (cnt[x] - 1)$ 次操作才能将这些字符转换为 $t$ 中对应的字符。因此，我们在 $[1,..25] 范围内枚举 $x$，如果 $x + 26 \times (cnt[x] - 1) \gt k$，说明我们无法将所有字符转换为 $t$ 中对应的字符，返回 `false`。

否则，枚举结束后，说明我们可以将所有字符转换为 $t$ 中对应的字符，返回 `true`。

时间复杂度 $O(n + C)$，空间复杂度 $O(C)$，其中 $n$ 为字符串 $s$ 和 $t$ 的长度；而 $C$ 为字符集大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canConvertString(self, s: str, t: str, k: int) -> bool:
        if len(s) != len(t):
            return False
        cnt = [0] * 26
        for a, b in zip(s, t):
            x = (ord(b) - ord(a) + 26) % 26
            cnt[x] += 1
        for i in range(1, 26):
            if i + 26 * (cnt[i] - 1) > k:
                return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int x = (t.charAt(i) - s.charAt(i) + 26) % 26;
            ++cnt[x];
        }
        for (int i = 1; i < 26; ++i) {
            if (i + 26 * (cnt[i] - 1) > k) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canConvertString(string s, string t, int k) {
        if (s.size() != t.size()) {
            return false;
        }
        int cnt[26]{};
        for (int i = 0; i < s.size(); ++i) {
            int x = (t[i] - s[i] + 26) % 26;
            ++cnt[x];
        }
        for (int i = 1; i < 26; ++i) {
            if (i + 26 * (cnt[i] - 1) > k) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func canConvertString(s string, t string, k int) bool {
	if len(s) != len(t) {
		return false
	}
	cnt := [26]int{}
	for i := range s {
		x := (t[i] - s[i] + 26) % 26
		cnt[x]++
	}
	for i := 1; i < 26; i++ {
		if i+26*(cnt[i]-1) > k {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
