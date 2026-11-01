---
comments: true
difficulty: Hard
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [940. Distinct Subsequences II](https://leetcode.com/problems/distinct-subsequences-ii)

[中文文档](/solution/0900-0999/0940.Distinct%20Subsequences%20II/README.md)

## Description

<!-- description:start -->

<p>Given a string s, return <em>the number of <strong>distinct non-empty subsequences</strong> of</em> <code>s</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>
A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code> while <code>&quot;aec&quot;</code> is not.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> The 7 distinct subsequences are &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;ab&quot;, &quot;ac&quot;, &quot;bc&quot;, and &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The 6 distinct subsequences are &quot;a&quot;, &quot;b&quot;, &quot;ab&quot;, &quot;aa&quot;, &quot;ba&quot;, and &quot;aba&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 distinct subsequences are &quot;a&quot;, &quot;aa&quot; and &quot;aaa&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Count distinct nonempty subsequences. $n\le 2000$, so listing subsets is impossible. Classify by last letter: $f[c]$ is the number of distinct subsequences now ending with $c$. On reading $c$, it may follow any previous subsequence or stand alone, so $f[c]\leftarrow \sum f+1$; a repeated $c$ overwrites the old family that ended with $c$.

<!-- thinking:end -->

We define $f[i]$ as the number of distinct subsequences ending with the $i$-th lowercase letter. Initially, all elements in $f$ are $0$.

Traverse the string $s$. For the current character $c$, update $f[c]$ to $\sum_{i=0}^{25} f[i] + 1$. Here $\sum_{i=0}^{25} f[i]$ is the number of distinct subsequences obtained so far, and $+1$ means the character $c$ itself can also be a subsequence.

Finally, the answer is $\sum_{i=0}^{25} f[i]$ modulo $10^9 + 7$.

The time complexity is $O(n \times C)$, and the space complexity is $O(C)$, where $n$ is the length of $s$ and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        f = [0] * 26
        for c in s:
            f[ord(c) - ord("a")] = (sum(f) + 1) % mod
        return sum(f) % mod
```

#### Java

```java
class Solution {
    public int distinctSubseqII(String s) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int x = 1;
            for (int v : f) {
                x = (x + v) % mod;
            }
            f[s.charAt(i) - 'a'] = x;
        }
        int ans = 0;
        for (int v : f) {
            ans = (ans + v) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int distinctSubseqII(string s) {
        const int mod = 1e9 + 7;
        int f[26]{};
        for (char& c : s) {
            int x = 1;
            for (int v : f) {
                x = (x + v) % mod;
            }
            f[c - 'a'] = x;
        }
        int ans = 0;
        for (int v : f) {
            ans = (ans + v) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	f := [26]int{}
	for _, c := range s {
		x := 1
		for _, v := range f {
			x = (x + v) % mod
		}
		f[c-'a'] = x
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(26).fill(0);
    for (const c of s) {
        f[c.charCodeAt(0) - 97] = f.reduce((acc, v) => (acc + v) % mod, 1);
    }
    return f.reduce((acc, v) => (acc + v) % mod);
}
```

#### Rust

```rust
impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut f = [0; 26];
        for u in s.bytes() {
            let mut x = 1;
            for &v in &f {
                x = (x + v) % MOD;
            }
            f[(u - b'a') as usize] = x;
        }
        f.iter().fold(0, |acc, &v| (acc + v) % MOD)
    }
}
```

#### C

```c
int distinctSubseqII(char* s) {
    const int mod = 1e9 + 7;
    int f[26] = {0};
    for (int i = 0; s[i]; ++i) {
        int x = 1;
        for (int j = 0; j < 26; ++j) {
            x = (x + f[j]) % mod;
        }
        f[s[i] - 'a'] = x;
    }
    int ans = 0;
    for (int i = 0; i < 26; ++i) {
        ans = (ans + f[i]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Optimized Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Method 1 rescans $26$ cells on every update. Keep the running sum $\textit{ans}$; the increment is $\textit{ans}-f[i]+1$, so both $f[i]$ and $\textit{ans}$ update in $O(1)$.

<!-- thinking:end -->

Based on Solution 1, we can maintain a variable $\textit{ans}$ as the sum of all elements in $f$. Each time we update $f[i]$, the number of newly added distinct subsequences is $\textit{ans} - f[i] + 1$. We then update both $\textit{ans}$ and $f[i]$ accordingly.

The time complexity is $O(n)$, and the space complexity is $O(C)$.

Similar problems:

- [1987. Number of Unique Good Subsequences](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        f = [0] * 26
        ans = 0
        for c in s:
            i = ord(c) - ord("a")
            add = (ans + 1 - f[i]) % mod
            ans = (ans + add) % mod
            f[i] = (f[i] + add) % mod
        return ans
```

#### Java

```java
class Solution {
    public int distinctSubseqII(String s) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            int add = (ans + 1 + mod - f[j]) % mod;
            ans = (ans + add) % mod;
            f[j] = (f[j] + add) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int distinctSubseqII(string s) {
        const int mod = 1e9 + 7;
        int f[26]{};
        int ans = 0;
        for (char& c : s) {
            int i = c - 'a';
            int add = (ans + 1 + mod - f[i]) % mod;
            ans = (ans + add) % mod;
            f[i] = (f[i] + add) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	f := [26]int{}
	ans := 0
	for _, c := range s {
		i := c - 'a'
		add := (ans + 1 + mod - f[i]) % mod
		ans = (ans + add) % mod
		f[i] = (f[i] + add) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(26).fill(0);
    let ans = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 97;
        const add = (ans + 1 + mod - f[i]) % mod;
        ans = (ans + add) % mod;
        f[i] = (f[i] + add) % mod;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut f = [0; 26];
        let mut ans = 0;
        for u in s.bytes() {
            let i = (u - b'a') as usize;
            let add = (ans + 1 + MOD - f[i]) % MOD;
            ans = (ans + add) % MOD;
            f[i] = (f[i] + add) % MOD;
        }
        ans
    }
}
```

#### C

```c
int distinctSubseqII(char* s) {
    const int mod = 1e9 + 7;
    int f[26] = {0};
    int ans = 0;
    for (int i = 0; s[i]; ++i) {
        int j = s[i] - 'a';
        int add = (ans + 1LL + mod - f[j]) % mod;
        ans = (ans + add) % mod;
        f[j] = (f[j] + add) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
