---
comments: true
difficulty: Medium
rating: 2126
source: Biweekly Contest 107 Q3
tags:
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2746. Decremental String Concatenation](https://leetcode.com/problems/decremental-string-concatenation)

[中文文档](/solution/2700-2799/2746.Decremental%20String%20Concatenation/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>words</code> containing <code>n</code> strings.</p>

<p>Let&#39;s define a <strong>join</strong> operation <code>join(x, y)</code> between two strings <code>x</code> and <code>y</code> as concatenating them into <code>xy</code>. However, if the last character of <code>x</code> is equal to the first character of <code>y</code>, one of them is <strong>deleted</strong>.</p>

<p>For example <code>join(&quot;ab&quot;, &quot;ba&quot;) = &quot;aba&quot;</code> and <code>join(&quot;ab&quot;, &quot;cde&quot;) = &quot;abcde&quot;</code>.</p>

<p>You are to perform <code>n - 1</code> <strong>join</strong> operations. Let <code>str<sub>0</sub> = words[0]</code>. Starting from <code>i = 1</code> up to <code>i = n - 1</code>, for the <code>i<sup>th</sup></code> operation, you can do one of the following:</p>

<ul>
	<li>Make <code>str<sub>i</sub> = join(str<sub>i - 1</sub>, words[i])</code></li>
	<li>Make <code>str<sub>i</sub> = join(words[i], str<sub>i - 1</sub>)</code></li>
</ul>

<p>Your task is to <strong>minimize</strong> the length of <code>str<sub>n - 1</sub></code>.</p>

<p>Return <em>an integer denoting the minimum possible length of</em> <code>str<sub>n - 1</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aa&quot;,&quot;ab&quot;,&quot;bc&quot;]
<strong>Output:</strong> 4
<strong>Explanation: </strong>In this example, we can perform join operations in the following order to minimize the length of str<sub>2</sub>: 
str<sub>0</sub> = &quot;aa&quot;
str<sub>1</sub> = join(str<sub>0</sub>, &quot;ab&quot;) = &quot;aab&quot;
str<sub>2</sub> = join(str<sub>1</sub>, &quot;bc&quot;) = &quot;aabc&quot; 
It can be shown that the minimum possible length of str<sub>2</sub> is 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;b&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, str<sub>0</sub> = &quot;ab&quot;, there are two ways to get str<sub>1</sub>: 
join(str<sub>0</sub>, &quot;b&quot;) = &quot;ab&quot; or join(&quot;b&quot;, str<sub>0</sub>) = &quot;bab&quot;. 
The first string, &quot;ab&quot;, has the minimum length. Hence, the answer is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aaa&quot;,&quot;c&quot;,&quot;aba&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> In this example, we can perform join operations in the following order to minimize the length of str<sub>2</sub>: 
str<sub>0</sub> = &quot;aaa&quot;
str<sub>1</sub> = join(str<sub>0</sub>, &quot;c&quot;) = &quot;aaac&quot;
str<sub>2</sub> = join(&quot;aba&quot;, str<sub>1</sub>) = &quot;abaaac&quot;
It can be shown that the minimum possible length of str<sub>2</sub> is 6.
</pre>

<div class="notranslate" style="all: initial;">&nbsp;</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li>Each character in <code>words[i]</code> is an English lowercase letter</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

<!-- thinking:start -->

> **Thinking**
>
> Words must be prepended or appended in order; equal touching letters collapse into one, and we want the shortest final length. Two choices per word at $n\le 1000$ make a raw search impossible.
>
> The future cost depends only on the current first and last letters. $dfs(i,a,b)$ is the extra length from word $i$ with ends $a,b$: append compares $s[0]$ with $b$, prepend compares $s[-1]$ with $a$. Memoization yields $O(n\cdot 26^2)$ states.

<!-- thinking:end -->

We notice that when concatenating strings, the first and last characters of the string will affect the length of the concatenated string. Therefore, we design a function $dfs(i, a, b)$, which represents the minimum length of the concatenated string starting from the $i$-th string, and the first character of the previously concatenated string is $a$, and the last character is $b$.

The execution process of the function $dfs(i, a, b)$ is as follows:

- If $i = n$, it means that all strings have been concatenated, return $0$;
- Otherwise, we consider concatenating the $i$-th string to the end or the beginning of the already concatenated string, and get the lengths $x$ and $y$ of the concatenated string, then $dfs(i, a, b) = \min(x, y) + |words[i]|$.

To avoid repeated calculations, we use the method of memoization search. Specifically, we use a three-dimensional array $f$ to store all the return values of $dfs(i, a, b)$. When we need to calculate $dfs(i, a, b)$, if $f[i][a][b]$ has been calculated, we directly return $f[i][a][b]$; otherwise, we calculate the value of $dfs(i, a, b)$ according to the above recurrence relation, and store it in $f[i][a][b]$.

In the main function, we directly return $|words[0]| + dfs(1, words[0][0], words[0][|words[0]| - 1])$.

The time complexity is $O(n \times C^2)$, and the space complexity is $O(n \times C^2)$. Where $C$ represents the maximum length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizeConcatenatedLength(self, words: List[str]) -> int:
        @cache
        def dfs(i: int, a: str, b: str) -> int:
            if i >= len(words):
                return 0
            s = words[i]
            x = dfs(i + 1, a, s[-1]) - int(s[0] == b)
            y = dfs(i + 1, s[0], b) - int(s[-1] == a)
            return len(s) + min(x, y)

        return len(words[0]) + dfs(1, words[0][0], words[0][-1])
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private String[] words;
    private int n;

    public int minimizeConcatenatedLength(String[] words) {
        n = words.length;
        this.words = words;
        f = new Integer[n][26][26];
        return words[0].length()
            + dfs(1, words[0].charAt(0) - 'a', words[0].charAt(words[0].length() - 1) - 'a');
    }

    private int dfs(int i, int a, int b) {
        if (i >= n) {
            return 0;
        }
        if (f[i][a][b] != null) {
            return f[i][a][b];
        }
        String s = words[i];
        int m = s.length();
        int x = dfs(i + 1, a, s.charAt(m - 1) - 'a') - (s.charAt(0) - 'a' == b ? 1 : 0);
        int y = dfs(i + 1, s.charAt(0) - 'a', b) - (s.charAt(m - 1) - 'a' == a ? 1 : 0);
        return f[i][a][b] = m + Math.min(x, y);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeConcatenatedLength(vector<string>& words) {
        int n = words.size();
        int f[n][26][26];
        memset(f, 0, sizeof(f));
        function<int(int, int, int)> dfs = [&](int i, int a, int b) {
            if (i >= n) {
                return 0;
            }
            if (f[i][a][b]) {
                return f[i][a][b];
            }
            auto s = words[i];
            int m = s.size();
            int x = dfs(i + 1, a, s[m - 1] - 'a') - (s[0] - 'a' == b);
            int y = dfs(i + 1, s[0] - 'a', b) - (s[m - 1] - 'a' == a);
            return f[i][a][b] = m + min(x, y);
        };
        return words[0].size() + dfs(1, words[0].front() - 'a', words[0].back() - 'a');
    }
};
```

#### Go

```go
func minimizeConcatenatedLength(words []string) int {
	n := len(words)
	f := make([][26][26]int, n)
	var dfs func(i, a, b int) int
	dfs = func(i, a, b int) int {
		if i >= n {
			return 0
		}
		if f[i][a][b] > 0 {
			return f[i][a][b]
		}
		s := words[i]
		m := len(s)
		x := dfs(i+1, a, int(s[m-1]-'a'))
		y := dfs(i+1, int(s[0]-'a'), b)
		if int(s[0]-'a') == b {
			x--
		}
		if int(s[m-1]-'a') == a {
			y--
		}
		f[i][a][b] = m + min(x, y)
		return f[i][a][b]
	}
	return len(words[0]) + dfs(1, int(words[0][0]-'a'), int(words[0][len(words[0])-1]-'a'))
}
```

#### TypeScript

```ts
function minimizeConcatenatedLength(words: string[]): number {
    const n = words.length;
    const f: number[][][] = Array(n)
        .fill(0)
        .map(() =>
            Array(26)
                .fill(0)
                .map(() => Array(26).fill(0)),
        );
    const dfs = (i: number, a: number, b: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][a][b] > 0) {
            return f[i][a][b];
        }
        const s = words[i];
        const m = s.length;
        const x =
            dfs(i + 1, a, s[m - 1].charCodeAt(0) - 97) - (s[0].charCodeAt(0) - 97 === b ? 1 : 0);
        const y =
            dfs(i + 1, s[0].charCodeAt(0) - 97, b) - (s[m - 1].charCodeAt(0) - 97 === a ? 1 : 0);
        return (f[i][a][b] = Math.min(x + m, y + m));
    };
    return (
        words[0].length +
        dfs(1, words[0][0].charCodeAt(0) - 97, words[0][words[0].length - 1].charCodeAt(0) - 97)
    );
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Each word is attached to the left or the right, and equal letters that meet count once. There can be $1000$ words, and trying the right side first always moves to the next index, so the search has depth $n$. Only the current first and last letters affect later choices. Let $f[i][a][b]$ be the extra length from word $i$ with those ends, set $f[n]$ to $0$, and fill decreasing $i$ over every pair of ends.

<!-- thinking:end -->

Let $f[i][a][b]$ be the shortest extra length when connecting from word $i$, with the string so far starting with $a$ and ending with $b$. The boundary is $f[n][a][b]=0$. For $i$ from $n-1$ down to $1$, and for every pair of ends $a,b$, try both sides. Appending $words[i]$ saves one character when its first character equals $b$, and the new end is its last character. Prepending saves one character when its last character equals $a$, and the new start is its first character. $f[i][a][b]$ is the shorter of those two lengths plus $|words[i]|$. The answer is $|words[0]| + f[1][words[0][0]][words[0][|words[0]|-1]]$.

The time complexity is $O(n \times |\Sigma|^2)$, and the space complexity is $O(n \times |\Sigma|^2)$. Here, $n$ is the number of words, and $|\Sigma|$ is the alphabet size, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizeConcatenatedLength(self, words: List[str]) -> int:
        n = len(words)
        f = [[[0] * 26 for _ in range(26)] for _ in range(n + 1)]
        for i in range(n - 1, 0, -1):
            s = words[i]
            m = len(s)
            c, d = ord(s[0]) - 97, ord(s[-1]) - 97
            for a in range(26):
                for b in range(26):
                    x = f[i + 1][a][d] - (c == b)
                    y = f[i + 1][c][b] - (d == a)
                    f[i][a][b] = m + min(x, y)
        a, b = ord(words[0][0]) - 97, ord(words[0][-1]) - 97
        return len(words[0]) + f[1][a][b]
```

#### Java

```java
class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        int[][][] f = new int[n + 1][26][26];
        for (int i = n - 1; i > 0; --i) {
            String s = words[i];
            int m = s.length();
            int c = s.charAt(0) - 'a';
            int d = s.charAt(m - 1) - 'a';
            for (int a = 0; a < 26; ++a) {
                for (int b = 0; b < 26; ++b) {
                    int x = f[i + 1][a][d] - (c == b ? 1 : 0);
                    int y = f[i + 1][c][b] - (d == a ? 1 : 0);
                    f[i][a][b] = m + Math.min(x, y);
                }
            }
        }
        int a = words[0].charAt(0) - 'a';
        int b = words[0].charAt(words[0].length() - 1) - 'a';
        return words[0].length() + f[1][a][b];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizeConcatenatedLength(vector<string>& words) {
        int n = words.size();
        vector<vector<vector<int>>> f(n + 1, vector<vector<int>>(26, vector<int>(26)));
        for (int i = n - 1; i > 0; --i) {
            auto& s = words[i];
            int m = s.size();
            int c = s[0] - 'a';
            int d = s[m - 1] - 'a';
            for (int a = 0; a < 26; ++a) {
                for (int b = 0; b < 26; ++b) {
                    int x = f[i + 1][a][d] - (c == b);
                    int y = f[i + 1][c][b] - (d == a);
                    f[i][a][b] = m + min(x, y);
                }
            }
        }
        int a = words[0].front() - 'a';
        int b = words[0].back() - 'a';
        return words[0].size() + f[1][a][b];
    }
};
```

#### Go

```go
func minimizeConcatenatedLength(words []string) int {
	n := len(words)
	f := make([][26][26]int, n+1)
	for i := n - 1; i > 0; i-- {
		s := words[i]
		m := len(s)
		c := int(s[0] - 'a')
		d := int(s[m-1] - 'a')
		for a := 0; a < 26; a++ {
			for b := 0; b < 26; b++ {
				x := f[i+1][a][d]
				y := f[i+1][c][b]
				if c == b {
					x--
				}
				if d == a {
					y--
				}
				f[i][a][b] = m + min(x, y)
			}
		}
	}
	a := int(words[0][0] - 'a')
	b := int(words[0][len(words[0])-1] - 'a')
	return len(words[0]) + f[1][a][b]
}
```

#### TypeScript

```ts
function minimizeConcatenatedLength(words: string[]): number {
    const n = words.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: 26 }, () => Array(26).fill(0)),
    );
    for (let i = n - 1; i > 0; --i) {
        const s = words[i];
        const m = s.length;
        const c = s.charCodeAt(0) - 97;
        const d = s.charCodeAt(m - 1) - 97;
        for (let a = 0; a < 26; ++a) {
            for (let b = 0; b < 26; ++b) {
                const x = f[i + 1][a][d] - (c === b ? 1 : 0);
                const y = f[i + 1][c][b] - (d === a ? 1 : 0);
                f[i][a][b] = m + Math.min(x, y);
            }
        }
    }
    const a = words[0].charCodeAt(0) - 97;
    const b = words[0].charCodeAt(words[0].length - 1) - 97;
    return words[0].length + f[1][a][b];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
