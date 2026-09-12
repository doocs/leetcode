---
comments: true
difficulty: 中等
rating: 1599
source: 第 137 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 双指针
    - 字符串
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [1048. 最长字符串链](https://leetcode.cn/problems/longest-string-chain)

[English Version](/solution/1000-1099/1048.Longest%20String%20Chain/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一个单词数组&nbsp;<code>words</code>&nbsp;，其中每个单词都由小写英文字母组成。</p>

<p>如果我们可以&nbsp;<strong>不改变其他字符的顺序&nbsp;</strong>，在 <code>word<sub>A</sub></code>&nbsp;的任何地方添加 <strong>恰好一个</strong> 字母使其变成&nbsp;<code>word<sub>B</sub></code>&nbsp;，那么我们认为&nbsp;<code>word<sub>A</sub></code>&nbsp;是&nbsp;<code>word<sub>B</sub></code>&nbsp;的 <strong>前身</strong> 。</p>

<ul>
	<li>例如，<code>"abc"</code>&nbsp;是&nbsp;<code>"abac"</code>&nbsp;的 <strong>前身</strong>&nbsp;，而&nbsp;<code>"cba"</code>&nbsp;不是&nbsp;<code>"bcad"</code>&nbsp;的 <strong>前身</strong></li>
</ul>

<p><strong>词链</strong>是单词&nbsp;<code>[word_1, word_2, ..., word_k]</code>&nbsp;组成的序列，<code>k &gt;= 1</code>，其中&nbsp;<code>word<sub>1</sub></code>&nbsp;是&nbsp;<code>word<sub>2</sub></code>&nbsp;的前身，<code>word<sub>2</sub></code>&nbsp;是&nbsp;<code>word<sub>3</sub></code>&nbsp;的前身，依此类推。一个单词通常是 <code>k == 1</code> 的 <strong>单词链</strong>&nbsp;。</p>

<p>从给定单词列表 <code>words</code> 中选择单词组成词链，返回 词链的&nbsp;<strong>最长可能长度</strong> 。<br />
&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["a","b","ba","bca","bda","bdca"]
<strong>输出：</strong>4
<strong>解释：</strong>最长单词链之一为 ["a","<u>b</u>a","b<u>d</u>a","bd<u>c</u>a"]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
<b>输出：</b>5
<b>解释：</b>所有的单词都可以放入单词链 ["xb", "xb<u>c</u>", "<u>c</u>xbc", "<u>p</u>cxbc", "pcxbc<u>f</u>"].
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<b>输入：</b>words = ["abcd","dbqca"]
<strong>输出：</strong>1
<b>解释：</b>字链["abcd"]是最长的字链之一。
["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 16</code></li>
	<li><code>words[i]</code>&nbsp;仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 词链要求每次只多一个字符，$n\le 1000$、串长 $\le 16$，可按长度排序后做序列 DP。以 $i$ 结尾的最长链，来自某个长度恰少 $1$ 且为前身的 $j$。
>
> 双指针判断 $a$ 能否通过插入一个字符得到 $b$。对每个 $i$ 枚举更短的 $j$，满足前身则用 $f[j]+1$ 更新 $f[i]$。
>
> 答案为 $f$ 的最大值。

<!-- thinking:end -->

我们先将 $\textit{words}$ 按照字符串长度从小到大排序。定义 $f[i]$ 表示以 $\textit{words}[i]$ 结尾的最长词链长度，初始时 $f[i] = 1$。

对于每个 $i$，我们枚举 $j \in [0, i)$。如果 $\textit{words}[j]$ 是 $\textit{words}[i]$ 的前身，则更新 $f[i] = \max(f[i], f[j] + 1)$。判断前身时，两个字符串的长度需相差 $1$，且较短串可由较长串删除恰好一个字符得到。

答案为 $\max(f)$。

时间复杂度 $O(n^2 \times L)$，空间复杂度 $O(n)$。其中 $n$ 是数组长度，而 $L$ 是字符串的最大长度。

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

### 方法二：动态规划 + 哈希表

<!-- thinking:start -->

> **思考**
>
> 方法一对每个 $i$ 扫描全部更短单词，即使长度差不是 $1$。前身由删去 $w$ 的一个字符唯一确定，至多 $L$ 个候选，可用哈希表按单词取值。
>
> 仍按长度排序，对当前 $w$ 枚举删除一位得到的 $p$，用 $f[p]+1$ 更新 $f[w]$。时间降为 $O(nL^2)$。

<!-- thinking:end -->

我们同样先将 $\textit{words}$ 按长度排序。用哈希表 $f$ 记录每个单词对应的最长词链长度。

对于当前单词 $w$，枚举删除其中一个字符得到的前身 $p$。若 $p$ 已在哈希表中，则可以用 $f[p] + 1$ 更新 $f[w]$。

答案为所有 $f[w]$ 的最大值。

时间复杂度 $O(n \times L^2)$，空间复杂度 $O(n \times L)$。

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
