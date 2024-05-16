---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README_EN.md
rating: 1357
source: Biweekly Contest 58 Q1
tags:
    - String
---

<!-- problem:start -->

# [1957. Delete Characters to Make Fancy String](https://leetcode.com/problems/delete-characters-to-make-fancy-string)

[中文文档](/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README.md)

## Description

<p>A <strong>fancy string</strong> is a string where no <strong>three</strong> <strong>consecutive</strong> characters are equal.</p>

<p>Given a string <code>s</code>, delete the <strong>minimum</strong> possible number of characters from <code>s</code> to make it <strong>fancy</strong>.</p>

<p>Return <em>the final string after the deletion</em>. It can be shown that the answer will always be <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;le<u>e</u>etcode&quot;
<strong>Output:</strong> &quot;leetcode&quot;
<strong>Explanation:</strong>
Remove an &#39;e&#39; from the first group of &#39;e&#39;s to create &quot;leetcode&quot;.
No three consecutive characters are equal, so return &quot;leetcode&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<u>a</u>aab<u>aa</u>aa&quot;
<strong>Output:</strong> &quot;aabaa&quot;
<strong>Explanation:</strong>
Remove an &#39;a&#39; from the first group of &#39;a&#39;s to create &quot;aabaaaa&quot;.
Remove two &#39;a&#39;s from the second group of &#39;a&#39;s to create &quot;aabaa&quot;.
No three consecutive characters are equal, so return &quot;aabaa&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> &quot;aab&quot;
<strong>Explanation:</strong> No three consecutive characters are equal, so return &quot;aab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def makeFancyString(self, s: str) -> str:
        ans = []
        for c in s:
            if len(ans) > 1 and ans[-1] == ans[-2] == c:
                continue
            ans.append(c)
        return ''.join(ans)
```

```java
class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = ans.length();
            if (n > 1 && ans.charAt(n - 1) == c && ans.charAt(n - 2) == c) {
                continue;
            }
            ans.append(c);
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string makeFancyString(string s) {
        string ans = "";
        for (char& c : s) {
            int n = ans.size();
            if (n > 1 && ans[n - 1] == c && ans[n - 2] == c) continue;
            ans.push_back(c);
        }
        return ans;
    }
};
```

```go
func makeFancyString(s string) string {
	ans := []rune{}
	for _, c := range s {
		n := len(ans)
		if n > 1 && ans[n-1] == c && ans[n-2] == c {
			continue
		}
		ans = append(ans, c)
	}
	return string(ans)
}
```

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] == $s[$i + 1] && $s[$i] == $s[$i + 2]) {
                continue;
            } else {
                $rs .= $s[$i];
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
