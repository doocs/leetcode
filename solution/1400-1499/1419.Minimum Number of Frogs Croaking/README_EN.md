# [1419. Minimum Number of Frogs Croaking](https://leetcode.com/problems/minimum-number-of-frogs-croaking)

[中文文档](/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README.md)

## Description

<p>You are given the string <code>croakOfFrogs</code>, which represents a combination of the string <code>&quot;croak&quot;</code> from different frogs, that is, multiple frogs can croak at the same time, so multiple <code>&quot;croak&quot;</code> are mixed.</p>

<p><em>Return the minimum number of </em>different<em> frogs to finish all the croaks in the given string.</em></p>

<p>A valid <code>&quot;croak&quot;</code> means a frog is printing five letters <code>&#39;c&#39;</code>, <code>&#39;r&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;a&#39;</code>, and <code>&#39;k&#39;</code> <strong>sequentially</strong>. The frogs have to print all five letters to finish a croak. If the given string is not a combination of a valid <code>&quot;croak&quot;</code> return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;croakcroak&quot;
<strong>Output:</strong> 1 
<strong>Explanation:</strong> One frog yelling &quot;croak<strong>&quot;</strong> twice.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;crcoakroak&quot;
<strong>Output:</strong> 2 
<strong>Explanation:</strong> The minimum number of frogs is two. 
The first frog could yell &quot;<strong>cr</strong>c<strong>oak</strong>roak&quot;.
The second frog could yell later &quot;cr<strong>c</strong>oak<strong>roak</strong>&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> croakOfFrogs = &quot;croakcrook&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong> The given string is an invalid combination of &quot;croak<strong>&quot;</strong> from different frogs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= croakOfFrogs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>croakOfFrogs</code> is either <code>&#39;c&#39;</code>, <code>&#39;r&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;a&#39;</code>, or <code>&#39;k&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        c = r = o = a = k = ans = 0
        for ch in croakOfFrogs:
            if ch == 'c':
                c += 1
                if k > 0:
                    k -= 1
                else:
                    ans += 1
            elif ch == 'r':
                r += 1
                c -= 1
            elif ch == 'o':
                o += 1
                r -= 1
            elif ch == 'a':
                a += 1
                o -= 1
            else:
                k += 1
                a -= 1
            if c < 0 or r < 0 or o < 0 or a < 0:
                return -1
        return -1 if c != 0 or r != 0 or o != 0 or a != 0 else ans
```

### **Java**

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int ans = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                ++c;
                if (k > 0) {
                    --k;
                } else {
                    ++ans;
                }
            } else if (ch == 'r') {
                ++r;
                --c;
            } else if (ch == 'o') {
                ++o;
                --r;
            } else if (ch == 'a') {
                ++a;
                --o;
            } else {
                ++k;
                --a;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                return -1;
            }
        }
        return c == 0 && r == 0 && o == 0 && a == 0 ? ans : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char ch : croakOfFrogs) {
            if (ch == 'c') {
                ++c;
                if (k > 0)
                    --k;
                else
                    ++ans;
            } else if (ch == 'r') {
                ++r;
                --c;
            } else if (ch == 'o') {
                ++o;
                --r;
            } else if (ch == 'a') {
                ++a;
                --o;
            } else {
                ++k;
                --a;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) return -1;
        }
        return c == 0 && r == 0 && o == 0 && a == 0 ? ans : -1;
    }
};
```

### **Go**

```go
func minNumberOfFrogs(croakOfFrogs string) int {
	c, r, o, a, k, ans := 0, 0, 0, 0, 0, 0
	for i := range croakOfFrogs {
		ch := croakOfFrogs[i]
		if ch == 'c' {
			c++
			if k > 0 {
				k--
			} else {
				ans++
			}
		} else if ch == 'r' {
			r++
			c--
		} else if ch == 'o' {
			o++
			r--
		} else if ch == 'a' {
			a++
			o--
		} else {
			k++
			a--
		}
		if c < 0 || r < 0 || o < 0 || a < 0 {
			return -1
		}
	}
	if c == 0 && r == 0 && o == 0 && a == 0 {
		return ans
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
