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

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can traverse the string $s$ and use an array $\textit{ans}$ to record the current answer. For each character $c$, if the length of $\textit{ans}$ is less than $2$ or the last two characters of $\textit{ans}$ are not equal to $c$, we add $c$ to $\textit{ans}$.

Finally, we concatenate the characters in $\textit{ans}$ to get the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeFancyString(self, s: str) -> str:
        ans = []
        for c in s:
            if len(ans) < 2 or ans[-1] != c or ans[-2] != c:
                ans.append(c)
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = ans.length();
            if (n < 2 || c != ans.charAt(n - 1) || c != ans.charAt(n - 2)) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string makeFancyString(string s) {
        string ans = "";
        for (char& c : s) {
            int n = ans.size();
            if (n < 2 || ans[n - 1] != c || ans[n - 2] != c) {
                ans += c;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func makeFancyString(s string) string {
	ans := []rune{}
	for _, c := range s {
		if n := len(ans); n < 2 || c != ans[n-1] || c != ans[n-2] {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function makeFancyString(s: string): string {
    const ans: string[] = [];
    for (const c of s) {
        const n = ans.length;
        if (n < 2 || c !== ans[n - 1] || c !== ans[n - 2]) {
            ans.push(c);
        }
    }
    return ans.join('');
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $ans = [];
        $length = strlen($s);

        for ($i = 0; $i < $length; $i++) {
            $n = count($ans);
            if ($n < 2 || $s[$i] !== $ans[$n - 1] || $s[$i] !== $ans[$n - 2]) {
                $ans[] = $s[$i];
            }
        }

        return implode('', $ans);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
