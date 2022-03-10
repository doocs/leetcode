# [680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii)

[中文文档](/solution/0600-0699/0680.Valid%20Palindrome%20II/README.md)

## Description

<p>Given a string <code>s</code>, return <code>true</code> <em>if the </em><code>s</code><em> can be palindrome after deleting <strong>at most one</strong> character from it</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abca&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You could delete the character &#39;c&#39;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validPalindrome(self, s: str) -> bool:
        def check(i, j):
            while i < j:
                if s[i] != s[j]:
                    return False
                i, j = i + 1, j - 1
            return True

        i, j = 0, len(s) - 1
        while i < j:
            if s[i] != s[j]:
                return check(i, j - 1) or check(i + 1, j)
            i, j = i + 1, j - 1
        return True
```

### **Java**

```java
class Solution {
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return check(s, i + 1, j) || check(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean check(String s, int i, int j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function validPalindrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return (
                isPalinddrome(s.slice(i, j)) ||
                isPalinddrome(s.slice(i + 1, j + 1))
            );
        }
    }
    return true;
}

function isPalinddrome(s: string): boolean {
    for (let i: number = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool validPalindrome(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j)
            if (s[i] != s[j])
                return check(s, i + 1, j) || check(s, i, j - 1);
        return 1;
    }

    bool check(string s, int i, int j) {
        for (; i < j; ++i, --j)
            if (s[i] != s[j])
                return 0;
        return 1;
    }
};
```

### **Go**

```go
func validPalindrome(s string) bool {
	check := func(i, j int) bool {
		for ; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return check(i+1, j) || check(i, j-1)
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    let check = function (i, j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->
