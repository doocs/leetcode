---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README_EN.md
rating: 1257
source: Weekly Contest 170 Q1
tags:
    - String
---

<!-- problem:start -->

# [1309. Decrypt String from Alphabet to Integer Mapping](https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping)

[中文文档](/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> formed by digits and <code>&#39;#&#39;</code>. We want to map <code>s</code> to English lowercase characters as follows:</p>

<ul>
	<li>Characters (<code>&#39;a&#39;</code> to <code>&#39;i&#39;</code>) are represented by (<code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>) respectively.</li>
	<li>Characters (<code>&#39;j&#39;</code> to <code>&#39;z&#39;</code>) are represented by (<code>&#39;10#&#39;</code> to <code>&#39;26#&#39;</code>) respectively.</li>
</ul>

<p>Return <em>the string formed after mapping</em>.</p>

<p>The test cases are generated so that a unique mapping will always exist.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10#11#12&quot;
<strong>Output:</strong> &quot;jkab&quot;
<strong>Explanation:</strong> &quot;j&quot; -&gt; &quot;10#&quot; , &quot;k&quot; -&gt; &quot;11#&quot; , &quot;a&quot; -&gt; &quot;1&quot; , &quot;b&quot; -&gt; &quot;2&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1326#&quot;
<strong>Output:</strong> &quot;acz&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of digits and the <code>&#39;#&#39;</code> letter.</li>
	<li><code>s</code> will be a valid string such that mapping is always possible.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process.

Traverse the string $s$. For the current index $i$, if $i + 2 < n$ and $s[i + 2]$ is `#`, then convert the substring formed by $s[i]$ and $s[i + 1]$ to an integer, add the ASCII value of `a` minus 1, convert it to a character, add it to the result array, and increment $i$ by 3. Otherwise, convert $s[i]$ to an integer, add the ASCII value of `a` minus 1, convert it to a character, add it to the result array, and increment $i$ by 1.

Finally, convert the result array to a string and return it.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def freqAlphabets(self, s: str) -> str:
        ans = []
        i, n = 0, len(s)
        while i < n:
            if i + 2 < n and s[i + 2] == "#":
                ans.append(chr(int(s[i : i + 2]) + ord("a") - 1))
                i += 3
            else:
                ans.append(chr(int(s[i]) + ord("a") - 1))
                i += 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String freqAlphabets(String s) {
        int i = 0, n = s.length();
        StringBuilder ans = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 2)) - 1));
                i += 3;
            } else {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 1)) - 1));
                i++;
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
    string freqAlphabets(string s) {
        string ans = "";
        int i = 0, n = s.size();
        while (i < n) {
            if (i + 2 < n && s[i + 2] == '#') {
                ans += char(stoi(s.substr(i, 2)) + 'a' - 1);
                i += 3;
            } else {
                ans += char(s[i] - '0' + 'a' - 1);
                i += 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func freqAlphabets(s string) string {
	var ans []byte
	for i, n := 0, len(s); i < n; {
		if i+2 < n && s[i+2] == '#' {
			num := (int(s[i])-'0')*10 + int(s[i+1]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 3
		} else {
			num := int(s[i]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 1
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function freqAlphabets(s: string): string {
    const ans: string[] = [];
    for (let i = 0, n = s.length; i < n; ) {
        if (i + 2 < n && s[i + 2] === '#') {
            ans.push(String.fromCharCode(96 + +s.slice(i, i + 2)));
            i += 3;
        } else {
            ans.push(String.fromCharCode(96 + +s[i]));
            i++;
        }
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn freq_alphabets(s: String) -> String {
        let s = s.as_bytes();
        let mut ans = String::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            if i + 2 < n && s[i + 2] == b'#' {
                let num = (s[i] - b'0') * 10 + (s[i + 1] - b'0');
                ans.push((96 + num) as char);
                i += 3;
            } else {
                let num = s[i] - b'0';
                ans.push((96 + num) as char);
                i += 1;
            }
        }
        ans
    }
}
```

#### C

```c
char* freqAlphabets(char* s) {
    int n = strlen(s);
    int i = 0;
    int j = 0;
    char* ans = malloc(sizeof(s) * n);
    while (i < n) {
        int t;
        if (i + 2 < n && s[i + 2] == '#') {
            t = (s[i] - '0') * 10 + s[i + 1];
            i += 3;
        } else {
            t = s[i];
            i += 1;
        }
        ans[j++] = 'a' + t - '1';
    }
    ans[j] = '\0';
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
