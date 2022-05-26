# [01.03. String to URL](https://leetcode.cn/problems/string-to-url-lcci)

[中文文档](/lcci/01.03.String%20to%20URL/README.md)

## Description

<p>Write a method to replace all spaces in a string with &#39;%20&#39;. You may assume that the string has sufficient space at the end to hold the additional characters,and that you are given the &quot;true&quot; length of the string. (Note: If implementing in Java,please use a character array so that you can perform this operation in place.)</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;Mr John Smith &quot;, 13

<strong>Output: </strong>&quot;Mr%20John%20Smith&quot;

<strong>Explanation: </strong>

The missing numbers are [5,6,8,...], hence the third missing number is 8.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>&quot;               &quot;, 5

<strong>Output: </strong>&quot;%20%20%20%20%20&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= S.length &lt;= 500000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

Use `replace()` function.

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        return S[:length].replace(' ', '%20')
```

Use list:

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        chars = []
        for c in S[:length]:
            chars.append('%20' if c == ' ' else c)
        return ''.join(chars)
```

### **Java**

```java
class Solution {
    public String replaceSpaces(String S, int length) {
        char[] chars = S.toCharArray();
        int j = chars.length;
        for (int i = length - 1; i >= 0; --i) {
            if (chars[i] == ' ') {
                chars[--j] = '0';
                chars[--j] = '2';
                chars[--j] = '%';
            } else {
                chars[--j] = chars[i];
            }
        }
        return new String(chars, j, chars.length - j);
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} S
 * @param {number} length
 * @return {string}
 */
var replaceSpaces = function (S, length) {
    return encodeURI(S.substring(0, length));
};
```

### **Go**

```go
func replaceSpaces(S string, length int) string {
	// return url.PathEscape(S[:length])
	j := len(S)
	b := []byte(S)
	for i := length - 1; i >= 0; i-- {
		if b[i] == ' ' {
			b[j-1] = '0'
			b[j-2] = '2'
			b[j-3] = '%'
			j -= 3
		} else {
			b[j-1] = b[i]
			j--
		}
	}
	return string(b[j:])
}
```

### **TypeScript**

```ts
function replaceSpaces(S: string, length: number): string {
    return S.slice(0, length).replace(/\s/g, '%20');
}
```

### **Rust**

```rust
impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s[..length as usize].replace(' ', "%20")
    }
}
```

```rust
impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s.chars()
            .take(length as usize)
            .map(|c| {
                if c == ' ' {
                    "%20".to_string()
                } else {
                    c.to_string()
                }
            })
            .collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
