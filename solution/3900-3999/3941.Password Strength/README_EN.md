---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3941.Password%20Strength/README_EN.md
---

<!-- problem:start -->

# [3941. Password Strength](https://leetcode.com/problems/password-strength)

[中文文档](/solution/3900-3999/3941.Password%20Strength/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>password</code>.</p>

<p>The <strong>strength</strong> of the password is calculated based on the following rules:</p>

<ul>
	<li>1 point for each distinct lowercase letter (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>).</li>
	<li>2 points for each distinct uppercase letter (<code>&#39;A&#39;</code> to <code>&#39;Z&#39;</code>).</li>
	<li>3 points for each distinct digit (<code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>).</li>
	<li>5 points for each distinct special character from the set <code>&quot;!@#$&quot;</code>.</li>
</ul>

<p>Each character contributes <strong>at most</strong> once, even if it appears multiple times.</p>

<p>Return an integer denoting the strength of the password.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">password = &quot;aA1!&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The distinct characters are <code>&#39;a&#39;</code>, <code>&#39;A&#39;</code>, <code>&#39;1&#39;</code> and <code>&#39;!&#39;</code>.</li>
	<li>Thus, the <code>strength = 1 + 2 + 3 + 5 = 11</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">password = &quot;bbB11#&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The distinct characters are <code>&#39;b&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;1&#39;</code> and <code>&#39;#&#39;</code>.</li>
	<li>Thus, the <code>strength = 1 + 2 + 3 + 5 = 11</code>.​​​​​​​</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= password.length &lt;= 10<sup>5</sup></code></li>
	<li><code>password</code> consists of lowercase and uppercase English letters, digits, and special characters from <code>&quot;!@#$&quot;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We store each character in the input string in a hash set $\textit{st}$, so we can quickly ensure each distinct character is counted only once.

Then, we iterate through each character in $\textit{st}$ and compute the password strength according to the rules:

- If the character is a lowercase letter ('a' to 'z'), add 1 point.
- If the character is an uppercase letter ('A' to 'Z'), add 2 points.
- If the character is a digit ('0' to '9'), add 3 points.
- If the character is a special character (from the set "!@#$"), add 5 points.

Finally, return the computed password strength.

The time complexity is $O(n)$, where $n$ is the length of the input string. The space complexity is $O(m)$, where $m$ is the number of distinct characters in the input string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def passwordStrength(self, password: str) -> int:
        st = set(password)
        ans = 0
        for ch in st:
            if ch.islower():
                ans += 1
            elif ch.isupper():
                ans += 2
            elif ch.isdigit():
                ans += 3
            else:
                ans += 5
        return ans
```

#### Java

```java
class Solution {
    public int passwordStrength(String password) {
        var st = password.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        int ans = 0;

        for (char ch : st) {
            if (Character.isLowerCase(ch)) {
                ans += 1;
            } else if (Character.isUpperCase(ch)) {
                ans += 2;
            } else if (Character.isDigit(ch)) {
                ans += 3;
            } else {
                ans += 5;
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int passwordStrength(string password) {
        unordered_set<char> st(password.begin(), password.end());

        int ans = 0;

        for (char ch : st) {
            if (islower(ch)) {
                ans += 1;
            } else if (isupper(ch)) {
                ans += 2;
            } else if (isdigit(ch)) {
                ans += 3;
            } else {
                ans += 5;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func passwordStrength(password string) (ans int) {
	st := map[rune]struct{}{}

	for _, ch := range password {
		st[ch] = struct{}{}
	}

	for ch := range st {
		switch {
		case unicode.IsLower(ch):
			ans += 1
		case unicode.IsUpper(ch):
			ans += 2
		case unicode.IsDigit(ch):
			ans += 3
		default:
			ans += 5
		}
	}

	return
}
```

#### TypeScript

```ts
function passwordStrength(password: string): number {
    const st = new Set(password);

    let ans = 0;

    for (const ch of st) {
        if (/[a-z]/u.test(ch)) {
            ans += 1;
        } else if (/[A-Z]/u.test(ch)) {
            ans += 2;
        } else if (/\d/u.test(ch)) {
            ans += 3;
        } else {
            ans += 5;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
