---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1271.Hexspeak/README_EN.md
rating: 1384
source: Biweekly Contest 14 Q1
tags:
    - Math
    - String
---

<!-- problem:start -->

# [1271. Hexspeak 🔒](https://leetcode.com/problems/hexspeak)

[中文文档](/solution/1200-1299/1271.Hexspeak/README.md)

## Description

<!-- description:start -->

<p>A decimal number can be converted to its <strong>Hexspeak representation</strong> by first converting it to an uppercase hexadecimal string, then replacing all occurrences of the digit <code>&#39;0&#39;</code> with the letter <code>&#39;O&#39;</code>, and the digit <code>&#39;1&#39;</code> with the letter <code>&#39;I&#39;</code>. Such a representation is valid if and only if it consists only of the letters in the set <code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;, &#39;I&#39;, &#39;O&#39;}</code>.</p>

<p>Given a string <code>num</code> representing a decimal integer <code>n</code>, <em>return the <strong>Hexspeak representation</strong> of </em><code>n</code><em> if it is valid, otherwise return </em><code>&quot;ERROR&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;257&quot;
<strong>Output:</strong> &quot;IOI&quot;
<strong>Explanation:</strong> 257 is 101 in hexadecimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;3&quot;
<strong>Output:</strong> &quot;ERROR&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 12</code></li>
	<li><code>num</code> does not contain leading zeros.</li>
	<li>num represents an integer in the range <code>[1, 10<sup>12</sup>]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Convert the number to a hexadecimal string, then traverse the string, convert the number $0$ to the letter $O$, and the number $1$ to the letter $I$. Finally, check whether the converted string is valid.

The time complexity is $O(\log n)$, where $n$ is the size of the decimal number represented by $num$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def toHexspeak 🔒(self, num: str) -> str:
        s = set('ABCDEFIO')
        t = hex(int(num))[2:].upper().replace('0', 'O').replace('1', 'I')
        return t if all(c in s for c in t) else 'ERROR'
```

#### Java

```java
class Solution {
    private static final Set<Character> S = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O');

    public String toHexspeak 🔒(String num) {
        String t
            = Long.toHexString(Long.valueOf(num)).toUpperCase().replace("0", "O").replace("1", "I");
        for (char c : t.toCharArray()) {
            if (!S.contains(c)) {
                return "ERROR";
            }
        }
        return t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string toHexspeak 🔒(string num) {
        stringstream ss;
        ss << hex << stol(num);
        string t = ss.str();
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] >= '2' && t[i] <= '9') return "ERROR";
            if (t[i] == '0')
                t[i] = 'O';
            else if (t[i] == '1')
                t[i] = 'I';
            else
                t[i] = t[i] - 32;
        }
        return t;
    }
};
```

#### Go

```go
func toHexspeak 🔒(num string) string {
	x, _ := strconv.Atoi(num)
	t := strings.ToUpper(fmt.Sprintf("%x", x))
	t = strings.ReplaceAll(t, "0", "O")
	t = strings.ReplaceAll(t, "1", "I")
	for _, c := range t {
		if c >= '2' && c <= '9' {
			return "ERROR"
		}
	}
	return t
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
