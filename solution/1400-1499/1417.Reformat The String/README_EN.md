---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1417.Reformat%20The%20String/README_EN.md
rating: 1241
source: Weekly Contest 185 Q1
tags:
    - String
---

<!-- problem:start -->

# [1417. Reformat The String](https://leetcode.com/problems/reformat-the-string)

[中文文档](/solution/1400-1499/1417.Reformat%20The%20String/README.md)

## Description

<!-- description:start -->

<p>You are given an alphanumeric string <code>s</code>. (<strong>Alphanumeric string</strong> is a string consisting of lowercase English letters and digits).</p>

<p>You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.</p>

<p>Return <em>the reformatted string</em> or return <strong>an empty string</strong> if it is impossible to reformat the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a0b1c2&quot;
<strong>Output:</strong> &quot;0a1b2c&quot;
<strong>Explanation:</strong> No two adjacent characters have the same type in &quot;0a1b2c&quot;. &quot;a0b1c2&quot;, &quot;0a1b2c&quot;, &quot;0c2a1b&quot; are also valid permutations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> &quot;leetcode&quot; has only characters so we cannot separate them by digits.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1229857369&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> &quot;1229857369&quot; has only digits so we cannot separate them by characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters and/or digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We classify all characters in string $s$ into two categories: "digits" and "letters", and put them into arrays $a$ and $b$ respectively.

Compare the lengths of $a$ and $b$. If the length of $a$ is less than $b$, swap $a$ and $b$. Then check the difference in lengths; if it exceeds $1$, return an empty string.

Next, iterate through both arrays simultaneously, appending characters from $a$ and $b$ alternately to the answer. After the iteration, if $a$ is longer than $b$, append the last character of $a$ to the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reformat(self, s: str) -> str:
        a = [c for c in s if c.islower()]
        b = [c for c in s if c.isdigit()]
        if abs(len(a) - len(b)) > 1:
            return ''
        if len(a) < len(b):
            a, b = b, a
        ans = []
        for x, y in zip(a, b):
            ans.append(x + y)
        if len(a) > len(b):
            ans.append(a[-1])
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String reformat(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                a.append(c);
            } else {
                b.append(c);
            }
        }
        int m = a.length(), n = b.length();
        if (Math.abs(m - n) > 1) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Math.min(m, n); ++i) {
            if (m > n) {
                ans.append(a.charAt(i));
                ans.append(b.charAt(i));
            } else {
                ans.append(b.charAt(i));
                ans.append(a.charAt(i));
            }
        }
        if (m > n) {
            ans.append(a.charAt(m - 1));
        }
        if (m < n) {
            ans.append(b.charAt(n - 1));
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reformat(string s) {
        string a = "", b = "";
        for (char c : s) {
            if (isdigit(c))
                a += c;
            else
                b += c;
        }
        int m = a.size(), n = b.size();
        if (abs(m - n) > 1) return "";
        string ans = "";
        for (int i = 0; i < min(m, n); ++i) {
            if (m > n) {
                ans += a[i];
                ans += b[i];
            } else {
                ans += b[i];
                ans += a[i];
            }
        }
        if (m > n) ans += a[m - 1];
        if (m < n) ans += b[n - 1];
        return ans;
    }
};
```

#### Go

```go
func reformat(s string) string {
	a := []byte{}
	b := []byte{}
	for _, c := range s {
		if unicode.IsLetter(c) {
			a = append(a, byte(c))
		} else {
			b = append(b, byte(c))
		}
	}
	if len(a) < len(b) {
		a, b = b, a
	}
	if len(a)-len(b) > 1 {
		return ""
	}
	var ans strings.Builder
	for i := range b {
		ans.WriteByte(a[i])
		ans.WriteByte(b[i])
	}
	if len(a) > len(b) {
		ans.WriteByte(a[len(a)-1])
	}
	return ans.String()
}
```

#### TypeScript

```ts
function reformat(s: string): string {
    let a: string[] = [];
    let b: string[] = [];

    for (const c of s) {
        if (c >= 'a' && c <= 'z') a.push(c);
        else if (c >= '0' && c <= '9') b.push(c);
    }

    if (Math.abs(a.length - b.length) > 1) {
        return '';
    }

    if (a.length < b.length) {
        [a, b] = [b, a];
    }

    const ans: string[] = [];

    for (let i = 0; i < b.length; i++) {
        ans.push(a[i] + b[i]);
    }

    if (a.length > b.length) {
        ans.push(a[a.length - 1]);
    }

    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn reformat(s: String) -> String {
        let mut a: Vec<char> = Vec::new();
        let mut b: Vec<char> = Vec::new();

        for c in s.chars() {
            if c.is_ascii_lowercase() {
                a.push(c);
            } else if c.is_ascii_digit() {
                b.push(c);
            }
        }

        if (a.len() as i32 - b.len() as i32).abs() > 1 {
            return String::new();
        }

        if a.len() < b.len() {
            std::mem::swap(&mut a, &mut b);
        }

        let mut ans = String::new();

        for i in 0..b.len() {
            ans.push(a[i]);
            ans.push(b[i]);
        }

        if a.len() > b.len() {
            ans.push(a[a.len() - 1]);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
