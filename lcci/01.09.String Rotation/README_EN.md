# [01.09. String Rotation](https://leetcode.cn/problems/string-rotation-lcci)

[中文文档](/lcci/01.09.String%20Rotation/README.md)

## Description

<p>Given two strings, <code>s1</code>&nbsp;and <code>s2</code>, write code to check if <code>s2</code> is a rotation of <code>s1</code> (e.g.,&quot;waterbottle&quot; is a rotation of&quot;erbottlewat&quot;).&nbsp;Can you use&nbsp;only one call to the method that&nbsp;checks if one word is a substring of another?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;waterbottle&quot;, s2 = &quot;erbottlewat&quot;

<strong>Output: </strong>True

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;aa&quot;, &quot;aba&quot;

<strong>Output: </strong>False

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code><font face="monospace">0 &lt;= s1.length, s1.length &lt;=&nbsp;</font>100000</code></li>
</ol>

## Solutions

### Solution 1: String Matching

First, if the lengths of strings $s1$ and $s2$ are not equal, they are definitely not rotation strings of each other.

Second, if the lengths of strings $s1$ and $s2$ are equal, then by concatenating two $s1$ together, the resulting string $s1 + s1$ will definitely include all rotation cases of $s1$. At this point, we just need to check whether $s2$ is a substring of $s1 + s1$.

```bash
# True
s1 = "aba"
s2 = "baa"
s1 + s1 = "abaaba"
            ^^^

# False
s1 = "aba"
s2 = "bab"
s1 + s1 = "abaaba"
```

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of string $s1$.

<!-- tabs:start -->

```python
class Solution:
    def isFlipedString(self, s1: str, s2: str) -> bool:
        return len(s1) == len(s2) and s2 in s1 * 2
```

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}
```

```cpp
class Solution {
public:
    bool isFlipedString(string s1, string s2) {
        return s1.size() == s2.size() && (s1 + s1).find(s2) != string::npos;
    }
};
```

```go
func isFlipedString(s1 string, s2 string) bool {
	return len(s1) == len(s2) && strings.Contains(s1+s1, s2)
}
```

```ts
function isFlipedString(s1: string, s2: string): boolean {
    return s1.length === s2.length && (s2 + s2).indexOf(s1) !== -1;
}
```

```rust
impl Solution {
    pub fn is_fliped_string(s1: String, s2: String) -> bool {
        s1.len() == s2.len() && (s2.clone() + &s2).contains(&s1)
    }
}
```

```swift
class Solution {
    func isFlippedString(_ s1: String, _ s2: String) -> Bool {
        return s1.count == s2.count && (s1 + s1).contains(s2)
    }
}
```

<!-- tabs:end -->

<!-- end -->
