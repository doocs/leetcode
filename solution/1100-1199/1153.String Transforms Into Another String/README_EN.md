# [1153. String Transforms Into Another String](https://leetcode.com/problems/string-transforms-into-another-string)

[中文文档](/solution/1100-1199/1153.String%20Transforms%20Into%20Another%20String/README.md)

## Description

<p>Given two strings <code>str1</code> and <code>str2</code> of the same length, determine whether you can transform <code>str1</code> into <code>str2</code> by doing <strong>zero or more</strong> <em>conversions</em>.</p>

<p>In one conversion you can convert <strong>all</strong> occurrences of one character in <code>str1</code> to <strong>any</strong> other lowercase English character.</p>

<p>Return <code>true</code> if and only if you can transform <code>str1</code> into <code>str2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;aabcc&quot;, str2 = &quot;ccdee&quot;
<strong>Output:</strong> true
<strong>Explanation: </strong>Convert &#39;c&#39; to &#39;e&#39; then &#39;b&#39; to &#39;d&#39; then &#39;a&#39; to &#39;c&#39;. Note that the order of conversions matter.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;leetcode&quot;, str2 = &quot;codeleet&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>There is no way to transform str1 to str2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= str1.length == str2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>str1</code> and <code>str2</code> contain only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canConvert(self, str1: str, str2: str) -> bool:
        if str1 == str2:
            return True
        if len(set(str2)) == 26:
            return False
        d = {}
        for a, b in zip(str1, str2):
            if a not in d:
                d[a] = b
            elif d[a] != b:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        int m = 0;
        int[] cnt = new int[26];
        int n = str1.length();
        for (int i = 0; i < n; ++i) {
            if (++cnt[str2.charAt(i) - 'a'] == 1) {
                ++m;
            }
        }
        if (m == 26) {
            return false;
        }
        int[] d = new int[26];
        for (int i = 0; i < n; ++i) {
            int a = str1.charAt(i) - 'a';
            int b = str2.charAt(i) - 'a';
            if (d[a] == 0) {
                d[a] = b + 1;
            } else if (d[a] != b + 1) {
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
    bool canConvert(string str1, string str2) {
        if (str1 == str2) {
            return true;
        }
        int cnt[26]{};
        int m = 0;
        for (char& c : str2) {
            if (++cnt[c - 'a'] == 1) {
                ++m;
            }
        }
        if (m == 26) {
            return false;
        }
        int d[26]{};
        for (int i = 0; i < str1.size(); ++i) {
            int a = str1[i] - 'a';
            int b = str2[i] - 'a';
            if (d[a] == 0) {
                d[a] = b + 1;
            } else if (d[a] != b + 1) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func canConvert(str1 string, str2 string) bool {
	if str1 == str2 {
		return true
	}
	s := map[rune]bool{}
	for _, c := range str2 {
		s[c] = true
		if len(s) == 26 {
			return false
		}
	}
	d := [26]int{}
	for i, c := range str1 {
		a, b := int(c-'a'), int(str2[i]-'a')
		if d[a] == 0 {
			d[a] = b + 1
		} else if d[a] != b+1 {
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
