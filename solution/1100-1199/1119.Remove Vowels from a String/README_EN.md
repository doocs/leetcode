---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README_EN.md
rating: 1232
source: Biweekly Contest 4 Q2
tags:
    - String
---

<!-- problem:start -->

# [1119. Remove Vowels from a String 🔒](https://leetcode.com/problems/remove-vowels-from-a-string)

[中文文档](/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README.md)

## Description

<p>Given a string <code>s</code>, remove the vowels <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code> from it, and return the new string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcodeisacommunityforcoders&quot;
<strong>Output:</strong> &quot;ltcdscmmntyfrcdrs&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aeiou&quot;
<strong>Output:</strong> &quot;&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly traverse the string according to the requirements of the problem, and append characters that are not vowels to the result string.

The time complexity is $O(n)$, where $n$ is the length of the string. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def removeVowels(self, s: str) -> str:
        return "".join(c for c in s if c not in "aeiou")
```

```java
class Solution {
    public String removeVowels(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string removeVowels(string s) {
        string ans;
        for (char& c : s) {
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                ans += c;
            }
        }
        return ans;
    }
};
```

```go
func removeVowels(s string) string {
	ans := []rune{}
	for _, c := range s {
		if !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

```ts
function removeVowels(s: string): string {
    return s.replace(/[aeiou]/g, '');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
