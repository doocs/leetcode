# [1147. Longest Chunked Palindrome Decomposition](https://leetcode.com/problems/longest-chunked-palindrome-decomposition)

[中文文档](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README.md)

## Description

<p>You are given a string <code>text</code>. You should split it to k substrings <code>(subtext<sub>1</sub>, subtext<sub>2</sub>, ..., subtext<sub>k</sub>)</code> such that:</p>

<ul>
	<li><code>subtext<sub>i</sub></code> is a <strong>non-empty</strong> string.</li>
	<li>The concatenation of all the substrings is equal to <code>text</code> (i.e., <code>subtext<sub>1</sub> + subtext<sub>2</sub> + ... + subtext<sub>k</sub> == text</code>).</li>
	<li><code>subtext<sub>i</sub> == subtext<sub>k - i + 1</sub></code> for all valid values of <code>i</code> (i.e., <code>1 &lt;= i &lt;= k</code>).</li>
</ul>

<p>Return the largest possible value of <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;ghiabcdefhelloadamhelloabcdefghi&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can split the string on &quot;(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;merchant&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can split the string on &quot;(merchant)&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;antaprezatepzapreanta&quot;
<strong>Output:</strong> 11
<strong>Explanation:</strong> We can split the string on &quot;(a)(nt)(a)(pre)(za)(tep)(za)(pre)(a)(nt)(a)&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code> consists only of lowercase English characters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestDecomposition(self, text: str) -> int:
        n = len(text)
        if n < 2:
            return n
        for i in range(n // 2 + 1):
            if text[:i] == text[-i:]:
                return 2 + self.longestDecomposition(text[i: -i])
        return 1
```

### **Java**

```java
class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        if (n < 2) {
            return n;
        }
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }
}
```

```java
class Solution {
    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int res = 0;
        for (int i = 0, j = cs.length - 1; i <= j;) {
            boolean flag = true;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(cs, i, j - k + 1, k)) {
                    res += 2;
                    i += k;
                    j -= k;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ++res;
                break;
            }
        }
        return res;
    }

    private boolean check(char[] cs, int i, int j, int k) {
        while (k-- > 0) {
            if (cs[i++] != cs[j++]) {
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
    int longestDecomposition(string text) {
        int n = text.size();
        if (n < 2) return n;
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substr(0, i) == text.substr(n - i)) {
                return 2 + longestDecomposition(text.substr(i, n - i - i));
            }
        }
        return 1;
    }
};
```

### **Go**

```go
func longestDecomposition(text string) int {
	n := len(text)
	if n < 2 {
		return n
	}
	for i := 1; i <= n>>1; i++ {
		if text[:i] == text[n-i:] {
			return 2 + longestDecomposition(text[i:n-i])
		}
	}
	return 1
}
```

### **...**

```

```

<!-- tabs:end -->
