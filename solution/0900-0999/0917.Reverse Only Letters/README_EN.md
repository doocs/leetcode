# [917. Reverse Only Letters](https://leetcode.com/problems/reverse-only-letters)

[中文文档](/solution/0900-0999/0917.Reverse%20Only%20Letters/README.md)

## Description

<p>Given a string <code>s</code>, reverse the string according to the following rules:</p>

<ul>
	<li>All the characters that are not English letters remain in the same position.</li>
	<li>All the English letters (lowercase or uppercase) should be reversed.</li>
</ul>

<p>Return <code>s</code><em> after reversing it</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "ab-cd"
<strong>Output:</strong> "dc-ba"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "a-bC-dEf-ghIj"
<strong>Output:</strong> "j-Ih-gfE-dCba"
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "Test1ng-Leet=code-Q!"
<strong>Output:</strong> "Qedo1ct-eeLg=ntse-T!"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of characters with ASCII values in the range <code>[33, 122]</code>.</li>
	<li><code>s</code> does not contain <code>&#39;\&quot;&#39;</code> or <code>&#39;\\&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        s = list(s)
        i, j = 0, len(s) - 1
        while i < j:
            while i < j and not s[i].isalpha():
                i += 1
            while i < j and not s[j].isalpha():
                j -= 1
            if i < j:
                s[i], s[j] = s[j], s[i]
                i, j = i + 1, j - 1
        return ''.join(s)
```

### **Java**

```java
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(chars[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(chars[j])) {
                --j;
            }
            if (i < j) {
                char t = chars[i];
                chars[i] = chars[j];
                chars[j] = t;
                ++i;
                --j;
            }
        }
        return new String(chars);
    }
}
```

### **TypeScript**

```ts
function reverseOnlyLetters(s: string): string {
    const n = s.length;
    let i = 0,
        j = n - 1;
    let ans = [...s];
    while (i < j) {
        while (!/[a-zA-Z]/.test(ans[i]) && i < j) i++;
        while (!/[a-zA-Z]/.test(ans[j]) && i < j) j--;
        [ans[i], ans[j]] = [ans[j], ans[i]];
        i++;
        j--;
    }
    return ans.join('');
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseOnlyLetters(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalpha(s[i])) ++i;
            while (i < j && !isalpha(s[j])) --j;
            if (i < j) {
                swap(s[i], s[j]);
                ++i;
                --j;
            }
        }
        return s;
    }
};
```

### **Go**

```go
func reverseOnlyLetters(s string) string {
	ans := []rune(s)
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !unicode.IsLetter(ans[i]) {
			i++
		}
		for i < j && !unicode.IsLetter(ans[j]) {
			j--
		}
		if i < j {
			ans[i], ans[j] = ans[j], ans[i]
			i++
			j--
		}
	}
	return string(ans)
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_only_letters(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if !cs[l].is_ascii_alphabetic() {
                l += 1;
            } else if !cs[r].is_ascii_alphabetic() {
                r -= 1;
            } else {
                cs.swap(l, r);
                l += 1;
                r -= 1;
            }
        }
        cs.iter().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
