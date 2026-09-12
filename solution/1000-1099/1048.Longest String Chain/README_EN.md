---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1048.Longest%20String%20Chain/README_EN.md
rating: 1599
source: Weekly Contest 137 Q3
tags:
    - Array
    - Hash Table
    - Two Pointers
    - String
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1048. Longest String Chain](https://leetcode.com/problems/longest-string-chain)

[中文文档](/solution/1000-1099/1048.Longest%20String%20Chain/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <code>words</code> where each word consists of lowercase English letters.</p>

<p><code>word<sub>A</sub></code> is a <strong>predecessor</strong> of <code>word<sub>B</sub></code> if and only if we can insert <strong>exactly one</strong> letter anywhere in <code>word<sub>A</sub></code> <strong>without changing the order of the other characters</strong> to make it equal to <code>word<sub>B</sub></code>.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> is a <strong>predecessor</strong> of <code>&quot;ab<u>a</u>c&quot;</code>, while <code>&quot;cba&quot;</code> is not a <strong>predecessor</strong> of <code>&quot;bcad&quot;</code>.</li>
</ul>

<p>A <strong>word chain</strong><em> </em>is a sequence of words <code>[word<sub>1</sub>, word<sub>2</sub>, ..., word<sub>k</sub>]</code> with <code>k &gt;= 1</code>, where <code>word<sub>1</sub></code> is a <strong>predecessor</strong> of <code>word<sub>2</sub></code>, <code>word<sub>2</sub></code> is a <strong>predecessor</strong> of <code>word<sub>3</sub></code>, and so on. A single word is trivially a <strong>word chain</strong> with <code>k == 1</code>.</p>

<p>Return <em>the <strong>length</strong> of the <strong>longest possible word chain</strong> with words chosen from the given list of </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bca&quot;,&quot;bda&quot;,&quot;bdca&quot;]
<strong>Output:</strong> 4
<strong>Explanation</strong>: One of the longest word chains is [&quot;a&quot;,&quot;<u>b</u>a&quot;,&quot;b<u>d</u>a&quot;,&quot;bd<u>c</u>a&quot;].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;xbc&quot;,&quot;pcxbcf&quot;,&quot;xb&quot;,&quot;cxbc&quot;,&quot;pcxbc&quot;]
<strong>Output:</strong> 5
<strong>Explanation:</strong> All the words can be put in a word chain [&quot;xb&quot;, &quot;xb<u>c</u>&quot;, &quot;<u>c</u>xbc&quot;, &quot;<u>p</u>cxbc&quot;, &quot;pcxbc<u>f</u>&quot;].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;dbqca&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The trivial word chain [&quot;abcd&quot;] is one of the longest word chains.
[&quot;abcd&quot;,&quot;dbqca&quot;] is not a valid word chain because the ordering of the letters is changed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 16</code></li>
	<li><code>words[i]</code> only consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> A chain grows by one character. $n\le 1000$ and length $\le 16$ allow DP after sorting by length. The best chain ending at $i$ comes from some predecessor $j$ whose length is one smaller.
>
> Two pointers test whether $a$ becomes $b$ by inserting one letter. Each $i$ tries earlier $j$ and sets $f[i]=\max(f[i],f[j]+1)$ on a hit.
>
> The answer is the maximum of $f$.

<!-- thinking:end -->

First, sort $\textit{words}$ by string length in ascending order. Define $f[i]$ as the length of the longest word chain ending with $\textit{words}[i]$. Initially, $f[i] = 1$.

For each $i$, enumerate $j \in [0, i)$. If $\textit{words}[j]$ is a predecessor of $\textit{words}[i]$, update $f[i] = \max(f[i], f[j] + 1)$. Two strings form a predecessor pair if their lengths differ by $1$ and the shorter one can be obtained by deleting exactly one character from the longer one.

The answer is $\max(f)$.

The time complexity is $O(n^2 \times L)$ and the space complexity is $O(n)$, where $n$ is the length of the array and $L$ is the maximum length of a string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        def check(a: str, b: str) -> bool:
            if len(a) + 1 != len(b):
                return False
            i = 0
            for c in b:
                if i < len(a) and a[i] == c:
                    i += 1
            return i == len(a)

        words.sort(key=len)
        n = len(words)
        f = [1] * n
        for i in range(n):
            for j in range(i):
                if check(words[j], words[i]):
                    f[i] = max(f[i], f[j] + 1)
        return max(f)
```

#### Java

```java
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (check(words[j], words[i])) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    private boolean check(String a, String b) {
        if (a.length() + 1 != b.length()) {
            return false;
        }
        int i = 0;
        for (int j = 0; j < b.length(); ++j) {
            if (i < a.length() && a.charAt(i) == b.charAt(j)) {
                ++i;
            }
        }
        return i == a.length();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestStrChain(vector<string>& words) {
        ranges::sort(words, [](const string& a, const string& b) { return a.size() < b.size(); });
        int n = words.size();
        int f[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (check(words[j], words[i])) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
            ans = max(ans, f[i]);
        }
        return ans;
    }

    bool check(const string& a, const string& b) {
        if (a.size() + 1 != b.size()) {
            return false;
        }
        int i = 0;
        for (char c : b) {
            if (i < a.size() && a[i] == c) {
                ++i;
            }
        }
        return i == a.size();
    }
};
```

#### Go

```go
func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	n := len(words)
	f := make([]int, n)
	ans := 0
	for i := 0; i < n; i++ {
		f[i] = 1
		for j := 0; j < i; j++ {
			if check(words[j], words[i]) {
				f[i] = max(f[i], f[j]+1)
			}
		}
		ans = max(ans, f[i])
	}
	return ans
}

func check(a, b string) bool {
	if len(a)+1 != len(b) {
		return false
	}
	i := 0
	for j := range b {
		if i < len(a) && a[i] == b[j] {
			i++
		}
	}
	return i == len(a)
}
```

#### TypeScript

```ts
function longestStrChain(words: string[]): number {
    const check = (a: string, b: string): boolean => {
        if (a.length + 1 !== b.length) {
            return false;
        }
        let i = 0;
        for (const c of b) {
            if (i < a.length && a[i] === c) {
                ++i;
            }
        }
        return i === a.length;
    };

    words.sort((a, b) => a.length - b.length);
    const n = words.length;
    const f: number[] = Array(n).fill(1);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (check(words[j], words[i])) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
    }
    return Math.max(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_str_chain(mut words: Vec<String>) -> i32 {
        fn check(a: &[u8], b: &[u8]) -> bool {
            if a.len() + 1 != b.len() {
                return false;
            }
            let mut i = 0;
            for &c in b {
                if i < a.len() && a[i] == c {
                    i += 1;
                }
            }
            i == a.len()
        }

        words.sort_unstable_by_key(|w| w.len());
        let n = words.len();
        let mut f = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if check(words[j].as_bytes(), words[i].as_bytes()) {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
        }
        *f.iter().max().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming + Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 scans every shorter word even when the length gap is not $1$. A predecessor is $w$ with one character deleted, so there are at most $L$ candidates and a hash map can store scores by word.
>
> Still sorting by length, we delete each index of $w$ and take $f[p]+1$. The time becomes $O(nL^2)$.

<!-- thinking:end -->

Sort $\textit{words}$ by length as well. Use a hash table $f$ to record the longest word chain length ending at each word.

For the current word $w$, enumerate each predecessor $p$ obtained by deleting one character. If $p$ is already in the hash table, update $f[w]$ with $f[p] + 1$.

The answer is the maximum value among all $f[w]$.

The time complexity is $O(n \times L^2)$ and the space complexity is $O(n \times L)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        words.sort(key=len)
        f = {}
        ans = 0
        for w in words:
            x = 1
            for i in range(len(w)):
                pred = w[:i] + w[i + 1 :]
                x = max(x, f.get(pred, 0) + 1)
            f[w] = x
            ans = max(ans, x)
        return ans
```

#### Java

```java
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> f = new HashMap<>();
        int ans = 0;
        for (String w : words) {
            int x = 1;
            for (int i = 0; i < w.length(); ++i) {
                String pred = w.substring(0, i) + w.substring(i + 1);
                x = Math.max(x, f.getOrDefault(pred, 0) + 1);
            }
            f.put(w, x);
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestStrChain(vector<string>& words) {
        ranges::sort(words, [](const string& a, const string& b) { return a.size() < b.size(); });
        unordered_map<string, int> f;
        int ans = 0;
        for (auto& w : words) {
            int x = 1;
            for (int i = 0; i < w.size(); ++i) {
                string pred = w.substr(0, i) + w.substr(i + 1);
                x = max(x, f[pred] + 1);
            }
            f[w] = x;
            ans = max(ans, x);
        }
        return ans;
    }
};
```

#### Go

```go
func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	f := map[string]int{}
	ans := 0
	for _, w := range words {
		x := 1
		for i := range w {
			pred := w[:i] + w[i+1:]
			x = max(x, f[pred]+1)
		}
		f[w] = x
		ans = max(ans, x)
	}
	return ans
}
```

#### TypeScript

```ts
function longestStrChain(words: string[]): number {
    words.sort((a, b) => a.length - b.length);
    const f = new Map<string, number>();
    let ans = 0;
    for (const w of words) {
        let x = 1;
        for (let i = 0; i < w.length; ++i) {
            const pred = w.slice(0, i) + w.slice(i + 1);
            x = Math.max(x, (f.get(pred) || 0) + 1);
        }
        f.set(w, x);
        ans = Math.max(ans, x);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn longest_str_chain(mut words: Vec<String>) -> i32 {
        words.sort_unstable_by_key(|w| w.len());
        let mut f = HashMap::new();
        let mut ans = 0;
        for w in words {
            let mut x = 1;
            for i in 0..w.len() {
                let pred = format!("{}{}", &w[..i], &w[i + 1..]);
                x = x.max(f.get(&pred).copied().unwrap_or(0) + 1);
            }
            f.insert(w, x);
            ans = ans.max(x);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
