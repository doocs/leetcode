# [2390. Removing Stars From a String](https://leetcode.com/problems/removing-stars-from-a-string)

[中文文档](/solution/2300-2399/2390.Removing%20Stars%20From%20a%20String/README.md)

## Description

<p>You are given a string <code>s</code>, which contains stars <code>*</code>.</p>

<p>In one operation, you can:</p>

<ul>
	<li>Choose a star in <code>s</code>.</li>
	<li>Remove the closest <strong>non-star</strong> character to its <strong>left</strong>, as well as remove the star itself.</li>
</ul>

<p>Return <em>the string after <strong>all</strong> stars have been removed</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The input will be generated such that the operation is always possible.</li>
	<li>It can be shown that the resulting string will always be unique.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leet**cod*e&quot;
<strong>Output:</strong> &quot;lecoe&quot;
<strong>Explanation:</strong> Performing the removals from left to right:
- The closest character to the 1<sup>st</sup> star is &#39;t&#39; in &quot;lee<strong><u>t</u></strong>**cod*e&quot;. s becomes &quot;lee*cod*e&quot;.
- The closest character to the 2<sup>nd</sup> star is &#39;e&#39; in &quot;le<strong><u>e</u></strong>*cod*e&quot;. s becomes &quot;lecod*e&quot;.
- The closest character to the 3<sup>rd</sup> star is &#39;d&#39; in &quot;leco<strong><u>d</u></strong>*e&quot;. s becomes &quot;lecoe&quot;.
There are no more stars, so we return &quot;lecoe&quot;.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;erase*****&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> The entire string is removed, so we return an empty string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters and stars <code>*</code>.</li>
	<li>The operation above can be performed on <code>s</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeStars(self, s: str) -> str:
        ans = []
        for c in s:
            if c == '*':
                ans.pop()
            else:
                ans.append(c)
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String removeStars(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '*') {
                ans.deleteCharAt(ans.length() - 1);
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeStars(string s) {
        string ans;
        for (char c : s) {
            if (c == '*') {
                ans.pop_back();
            } else {
                ans.push_back(c);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeStars(s string) string {
	ans := []rune{}
	for _, c := range s {
		if c == '*' {
			ans = ans[:len(ans)-1]
		} else {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function removeStars(s: string): string {
    const ans: string[] = [];
    for (const c of s) {
        if (c === '*') {
            ans.pop();
        } else {
            ans.push(c);
        }
    }
    return ans.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_stars(s: String) -> String {
        let mut ans = String::new();
        for &c in s.as_bytes().iter() {
            if c == b'*' {
                ans.pop();
            } else {
                ans.push(char::from(c));
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
