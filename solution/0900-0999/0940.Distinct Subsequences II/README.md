---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0940.Distinct%20Subsequences%20II/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [940. 不同的子序列 II](https://leetcode.cn/problems/distinct-subsequences-ii)

[English Version](/solution/0900-0999/0940.Distinct%20Subsequences%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>s</code>，计算 <code>s</code> 的 <strong>不同非空子序列</strong> 的个数。因为结果可能很大，所以返回答案需要对<strong> </strong><strong><code>10^9 + 7</code> 取余</strong> 。</p>

<p>字符串的 <strong>子序列</strong> 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。</p>

<ul>
	<li>例如，<code>"ace"</code> 是 <code>"<em><strong>a</strong></em>b<em><strong>c</strong></em>d<em><strong>e</strong></em>"</code> 的一个子序列，但 <code>"aec"</code> 不是。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abc"
<strong>输出：</strong>7
<strong>解释：</strong>7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aba"
<strong>输出：</strong>6
<strong>解释：</strong>6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaa"
<strong>输出：</strong>3
<strong>解释：</strong>3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i]$ 表示以第 $i$ 个小写字母结尾的不同子序列的个数。初始时 $f$ 中所有元素均为 $0$。

遍历字符串 $s$，对于当前字符 $c$，我们将 $f[c]$ 更新为 $\sum_{i=0}^{25} f[i] + 1$。其中 $\sum_{i=0}^{25} f[i]$ 表示此前已经得到的所有不同子序列的个数，而 $+1$ 表示字符 $c$ 本身也可以作为一个子序列。

最后，答案为 $\sum_{i=0}^{25} f[i]$，对 $10^9 + 7$ 取余。

时间复杂度 $O(n \times C)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度，而 $C$ 是字符集的大小，本题中 $C = 26$。

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

### 方法二：动态规划优化

在方法一的基础上，我们可以维护一个变量 $\textit{ans}$ 表示当前 $f$ 数组中所有元素的和。每次更新 $f[i]$ 时，新增的不同子序列个数为 $\textit{ans} - f[i] + 1$，据此同时更新 $\textit{ans}$ 与 $f[i]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。

相似题目：

- [1987. 不同的好子序列数目](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README.md)

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
