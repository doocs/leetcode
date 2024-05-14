---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.03.String%20to%20URL/README_EN.md
---

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

### Solution 1: Using `replace()` function

Directly use `replace` to replace all ` ` with `%20`:

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        return S[:length].replace(' ', '%20')
```

```ts
function replaceSpaces(S: string, length: number): string {
    return S.slice(0, length).replace(/\s/g, '%20');
}
```

```rust
impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s[..length as usize].replace(' ', "%20")
    }
}
```

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

```swift
class Solution {
    func replaceSpaces(_ S: String, _ length: Int) -> String {
        let substring = S.prefix(length)
        var result = ""

        for character in substring {
            if character == " " {
                result += "%20"
            } else {
                result.append(character)
            }
        }

        return result
    }
}
```

<!-- tabs:end -->

### Solution 2: Simulation

Traverse each character $c$ in the string. When encountering a space, add `%20` to the result, otherwise add $c$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        return ''.join(['%20' if c == ' ' else c for c in S[:length]])
```

```java
class Solution {
    public String replaceSpaces(String S, int length) {
        char[] cs = S.toCharArray();
        int j = cs.length;
        for (int i = length - 1; i >= 0; --i) {
            if (cs[i] == ' ') {
                cs[--j] = '0';
                cs[--j] = '2';
                cs[--j] = '%';
            } else {
                cs[--j] = cs[i];
            }
        }
        return new String(cs, j, cs.length - j);
    }
}
```

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

```rust
impl Solution {
    pub fn replace_spaces(s: String, length: i32) -> String {
        s.chars()
            .take(length as usize)
            .map(|c| {
                if c == ' ' { "%20".to_string() } else { c.to_string() }
            })
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
