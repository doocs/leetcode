---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0482.License%20Key%20Formatting/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [482. License Key Formatting](https://leetcode.com/problems/license-key-formatting)

[中文文档](/solution/0400-0499/0482.License%20Key%20Formatting/README.md)

## Description

<!-- description:start -->

<p>You are given a license key represented as a string <code>s</code> that consists of only alphanumeric characters and dashes. The string is separated into <code>n + 1</code> groups by <code>n</code> dashes. You are also given an integer <code>k</code>.</p>

<p>We want to reformat the string <code>s</code> such that each group contains exactly <code>k</code> characters, except for the first group, which could be shorter than <code>k</code> but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.</p>

<p>Return <em>the reformatted license key</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;5F3Z-2e-9-w&quot;, k = 4
<strong>Output:</strong> &quot;5F3Z-2E9W&quot;
<strong>Explanation:</strong> The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;2-5g-3-J&quot;, k = 2
<strong>Output:</strong> &quot;2-5G-3J&quot;
<strong>Explanation:</strong> The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of English letters, digits, and dashes <code>&#39;-&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

First, we count the number of characters in the string $s$ excluding the hyphens, and take the modulus of $k$ to determine the number of characters in the first group. If it is $0$, then the number of characters in the first group is $k$; otherwise, it is the result of the modulus operation.

Next, we iterate through the string $s$. For each character, if it is a hyphen, we skip it; otherwise, we convert it to an uppercase letter and add it to the answer string. Meanwhile, we maintain a counter $cnt$, representing the remaining number of characters in the current group. When $cnt$ decreases to $0$, we need to update $cnt$ to $k$, and if the current character is not the last one, we need to add a hyphen to the answer string.

Finally, we remove the hyphen at the end of the answer string and return the answer string.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. Ignoring the space consumption of the answer string, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        n = len(s)
        cnt = (n - s.count("-")) % k or k
        ans = []
        for i, c in enumerate(s):
            if c == "-":
                continue
            ans.append(c.upper())
            cnt -= 1
            if cnt == 0:
                cnt = k
                if i != n - 1:
                    ans.append("-")
        return "".join(ans).rstrip("-")
```

#### Java

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        int cnt = (int) (n - s.chars().filter(ch -> ch == '-').count()) % k;
        if (cnt == 0) {
            cnt = k;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            ans.append(Character.toUpperCase(c));
            --cnt;
            if (cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans.append('-');
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        int n = s.length();
        int cnt = (n - count(s.begin(), s.end(), '-')) % k;
        if (cnt == 0) {
            cnt = k;
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (c == '-') {
                continue;
            }
            ans += toupper(c);
            if (--cnt == 0) {
                cnt = k;
                if (i != n - 1) {
                    ans += '-';
                }
            }
        }
        if (!ans.empty() && ans.back() == '-') {
            ans.pop_back();
        }
        return ans;
    }
};
```

#### Go

```go
func licenseKeyFormatting(s string, k int) string {
	n := len(s)
	cnt := (n - strings.Count(s, "-")) % k
	if cnt == 0 {
		cnt = k
	}

	var ans strings.Builder
	for i := 0; i < n; i++ {
		c := s[i]
		if c == '-' {
			continue
		}
		if cnt == 0 {
			cnt = k
			ans.WriteByte('-')
		}
		ans.WriteRune(unicode.ToUpper(rune(c)))
		cnt--
	}

	return ans.String()
}
```

#### TypeScript

```ts
function licenseKeyFormatting(s: string, k: number): string {
    const n = s.length;
    let cnt = (n - (s.match(/-/g) || []).length) % k || k;
    const ans: string[] = [];
    for (let i = 0; i < n; i++) {
        const c = s[i];
        if (c === '-') {
            continue;
        }
        ans.push(c.toUpperCase());
        if (--cnt === 0) {
            cnt = k;
            if (i !== n - 1) {
                ans.push('-');
            }
        }
    }
    while (ans.at(-1) === '-') {
        ans.pop();
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
