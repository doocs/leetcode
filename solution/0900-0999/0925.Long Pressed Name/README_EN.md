# [925. Long Pressed Name](https://leetcode.com/problems/long-pressed-name)

[中文文档](/solution/0900-0999/0925.Long%20Pressed%20Name/README.md)

## Description

<p>Your friend is typing his <code>name</code> into a keyboard. Sometimes, when typing a character <code>c</code>, the key might get <em>long pressed</em>, and the character will be typed 1 or more times.</p>

<p>You examine the <code>typed</code> characters of the keyboard. Return <code>True</code> if it is possible that it was your friends name, with some characters (possibly none) being long pressed.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> name = &quot;alex&quot;, typed = &quot;aaleex&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>&#39;a&#39; and &#39;e&#39; in &#39;alex&#39; were long pressed.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> name = &quot;saeed&quot;, typed = &quot;ssaaedd&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>&#39;e&#39; must have been pressed twice, but it was not in the typed output.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= name.length, typed.length &lt;= 1000</code></li>
	<li><code>name</code> and <code>typed</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        m, n = len(name), len(typed)
        i = j = 0
        while i < m and j < n:
            if name[i] != typed[j]:
                return False
            cnt1 = cnt2 = 0
            c = name[i]
            while i + 1 < m and name[i + 1] == c:
                i += 1
                cnt1 += 1
            while j + 1 < n and typed[j + 1] == c:
                j += 1
                cnt2 += 1
            if cnt1 > cnt2:
                return False
            i, j = i + 1, j + 1
        return i == m and j == n
```

### **Java**

```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            int cnt1 = 0, cnt2 = 0;
            char c = name.charAt(i);
            while (i + 1 < m && name.charAt(i + 1) == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed.charAt(j + 1) == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) {
                return false;
            }
        }
        return i == m && j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int m = name.size(), n = typed.size();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name[i] != typed[j]) return false;
            int cnt1 = 0, cnt2 = 0;
            char c = name[i];
            while (i + 1 < m && name[i + 1] == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed[j + 1] == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) return false;
        }
        return i == m && j == n;
    }
};
```

### **Go**

```go
func isLongPressedName(name string, typed string) bool {
	m, n := len(name), len(typed)
	i, j := 0, 0
	for ; i < m && j < n; i, j = i+1, j+1 {
		if name[i] != typed[j] {
			return false
		}
		cnt1, cnt2 := 0, 0
		c := name[i]
		for i+1 < m && name[i+1] == c {
			i++
			cnt1++
		}
		for j+1 < n && typed[j+1] == c {
			j++
			cnt2++
		}
		if cnt1 > cnt2 {
			return false
		}
	}
	return i == m && j == n
}
```

### **...**

```

```

<!-- tabs:end -->
