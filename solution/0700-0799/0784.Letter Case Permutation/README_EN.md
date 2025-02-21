---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0784.Letter%20Case%20Permutation/README_EN.md
tags:
    - Bit Manipulation
    - String
    - Backtracking
---

<!-- problem:start -->

# [784. Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation)

[中文文档](/solution/0700-0799/0784.Letter%20Case%20Permutation/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, you&nbsp;can transform every letter individually to be lowercase or uppercase to create another string.</p>

<p>Return <em>a list of all possible strings we could create</em>. Return the output in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a1b2&quot;
<strong>Output:</strong> [&quot;a1b2&quot;,&quot;a1B2&quot;,&quot;A1b2&quot;,&quot;A1B2&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3z4&quot;
<strong>Output:</strong> [&quot;3z4&quot;,&quot;3Z4&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 12</code></li>
	<li><code>s</code> consists of lowercase English letters, uppercase English letters, and digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

Since each letter in $s$ can be converted to uppercase or lowercase, we can use the DFS (Depth-First Search) method to enumerate all possible cases.

Specifically, traverse the string $s$ from left to right. For each letter encountered, you can choose to convert it to uppercase or lowercase, and then continue to traverse the subsequent letters. When you reach the end of the string, you get a conversion scheme and add it to the answer.

The method of converting case can be implemented using bitwise operations. For a letter, the difference between the ASCII codes of its lowercase and uppercase forms is $32$, so we can achieve case conversion by XORing the ASCII code of the letter with $32$.

The time complexity is $O(n \times 2^n)$, where $n$ is the length of the string $s$. For each letter, we can choose to convert it to uppercase or lowercase, so there are $2^n$ conversion schemes in total. For each conversion scheme, we need $O(n)$ time to generate a new string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i: int) -> None:
            if i >= len(t):
                ans.append("".join(t))
                return
            dfs(i + 1)
            if t[i].isalpha():
                t[i] = chr(ord(t[i]) ^ 32)
                dfs(i + 1)

        t = list(s)
        ans = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] t;

    public List<String> letterCasePermutation(String s) {
        t = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= t.length) {
            ans.add(new String(t));
            return;
        }
        dfs(i + 1);
        if (Character.isLetter(t[i])) {
            t[i] ^= 32;
            dfs(i + 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        string t = s;
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= t.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            if (isalpha(t[i])) {
                t[i] ^= 32;
                dfs(i + 1);
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func letterCasePermutation(s string) (ans []string) {
	t := []byte(s)
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(t) {
			ans = append(ans, string(t))
			return
		}
		dfs(i + 1)
		if t[i] >= 'A' {
			t[i] ^= 32
			dfs(i + 1)
		}
	}

	dfs(0)
	return ans
}
```

#### TypeScript

```ts
function letterCasePermutation(s: string): string[] {
    const t = s.split('');
    const ans: string[] = [];
    const dfs = (i: number) => {
        if (i >= t.length) {
            ans.push(t.join(''));
            return;
        }
        dfs(i + 1);
        if (t[i].charCodeAt(0) >= 65) {
            t[i] = String.fromCharCode(t[i].charCodeAt(0) ^ 32);
            dfs(i + 1);
        }
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        fn dfs(i: usize, t: &mut Vec<char>, ans: &mut Vec<String>) {
            if i >= t.len() {
                ans.push(t.iter().collect());
                return;
            }
            dfs(i + 1, t, ans);
            if t[i].is_alphabetic() {
                t[i] = (t[i] as u8 ^ 32) as char;
                dfs(i + 1, t, ans);
            }
        }

        let mut t: Vec<char> = s.chars().collect();
        let mut ans = Vec::new();
        dfs(0, &mut t, &mut ans);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary Enumeration

For a letter, we can convert it to uppercase or lowercase. Therefore, for each letter, we can use a binary bit to represent its conversion scheme, where $1$ represents lowercase and $0$ represents uppercase.

First, we count the number of letters in the string $s$, denoted as $n$. Then, there are $2^n$ conversion schemes in total. We can use each bit of a binary number to represent the conversion scheme of each letter, enumerating from $0$ to $2^n-1$.

Specifically, we can use a variable $i$ to represent the current binary number being enumerated, where the $j$-th bit of $i$ represents the conversion scheme of the $j$-th letter. That is, the $j$-th bit of $i$ being $1$ means the $j$-th letter is converted to lowercase, and $0$ means the $j$-th letter is converted to uppercase.

The time complexity is $O(n \times 2^n)$, where $n$ is the length of the string $s$. For each letter, we can choose to convert it to uppercase or lowercase, so there are $2^n$ conversion schemes in total. For each conversion scheme, we need $O(n)$ time to generate a new string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        ans = []
        n = sum(c.isalpha() for c in s)
        for i in range(1 << n):
            j, t = 0, []
            for c in s:
                if c.isalpha():
                    c = c.lower() if (i >> j) & 1 else c.upper()
                    j += 1
                t.append(c)
            ans.append(''.join(t))
        return ans
```

#### Java

```java
class Solution {
    public List<String> letterCasePermutation(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'A') {
                ++n;
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            StringBuilder t = new StringBuilder();
            for (int k = 0; k < s.length(); ++k) {
                char x = s.charAt(k);
                if (x >= 'A') {
                    x = ((i >> j) & 1) == 1 ? Character.toLowerCase(x) : Character.toUpperCase(x);
                    ++j;
                }
                t.append(x);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        int n = count_if(s.begin(), s.end(), [](char c) { return isalpha(c); });
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            string t;
            for (char c : s) {
                if (isalpha(c)) {
                    c = (i >> j & 1) ? tolower(c) : toupper(c);
                    ++j;
                }
                t += c;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

#### Go

```go
func letterCasePermutation(s string) (ans []string) {
	n := 0
	for _, c := range s {
		if c >= 'A' {
			n++
		}
	}
	for i := 0; i < 1<<n; i++ {
		j := 0
		t := []rune{}
		for _, c := range s {
			if c >= 'A' {
				if ((i >> j) & 1) == 1 {
					c = unicode.ToLower(c)
				} else {
					c = unicode.ToUpper(c)
				}
				j++
			}
			t = append(t, c)
		}
		ans = append(ans, string(t))
	}
	return ans
}
```

#### TypeScript

```ts
function letterCasePermutation(s: string): string[] {
    const ans: string[] = [];
    const n: number = Array.from(s).filter(c => /[a-zA-Z]/.test(c)).length;
    for (let i = 0; i < 1 << n; ++i) {
        let j = 0;
        const t: string[] = [];
        for (let c of s) {
            if (/[a-zA-Z]/.test(c)) {
                t.push((i >> j) & 1 ? c.toLowerCase() : c.toUpperCase());
                j++;
            } else {
                t.push(c);
            }
        }
        ans.push(t.join(''));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        let n = s.chars().filter(|&c| c.is_alphabetic()).count();
        let mut ans = Vec::new();
        for i in 0..(1 << n) {
            let mut j = 0;
            let mut t = String::new();
            for c in s.chars() {
                if c.is_alphabetic() {
                    if (i >> j) & 1 == 1 {
                        t.push(c.to_lowercase().next().unwrap());
                    } else {
                        t.push(c.to_uppercase().next().unwrap());
                    }
                    j += 1;
                } else {
                    t.push(c);
                }
            }
            ans.push(t);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
