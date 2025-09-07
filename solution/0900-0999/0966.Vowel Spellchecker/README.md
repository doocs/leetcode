---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0966.Vowel%20Spellchecker/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [966. 元音拼写检查器](https://leetcode.cn/problems/vowel-spellchecker)

[English Version](/solution/0900-0999/0966.Vowel%20Spellchecker/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在给定单词列表&nbsp;<code>wordlist</code>&nbsp;的情况下，我们希望实现一个拼写检查器，将查询单词转换为正确的单词。</p>

<p>对于给定的查询单词&nbsp;<code>query</code>，拼写检查器将会处理两类拼写错误：</p>

<ul>
	<li>大小写：如果查询匹配单词列表中的某个单词（<strong>不区分大小写</strong>），则返回的正确单词与单词列表中的大小写相同。

    <ul>
    	<li>例如：<code>wordlist = ["yellow"]</code>, <code>query = "YellOw"</code>: <code>correct = "yellow"</code></li>
    	<li>例如：<code>wordlist = ["Yellow"]</code>, <code>query = "yellow"</code>: <code>correct = "Yellow"</code></li>
    	<li>例如：<code>wordlist = ["yellow"]</code>, <code>query = "yellow"</code>: <code>correct = "yellow"</code></li>
    </ul>
    </li>
    <li>元音错误：如果在将查询单词中的元音 <code>('a', 'e', 'i', 'o', 'u')</code>&nbsp;&nbsp;分别替换为任何元音后，能与单词列表中的单词匹配（<strong>不区分大小写</strong>），则返回的正确单词与单词列表中的匹配项大小写相同。
    <ul>
    	<li>例如：<code>wordlist = ["YellOw"]</code>, <code>query = "yollow"</code>: <code>correct = "YellOw"</code></li>
    	<li>例如：<code>wordlist = ["YellOw"]</code>, <code>query = "yeellow"</code>: <code>correct = ""</code> （无匹配项）</li>
    	<li>例如：<code>wordlist = ["YellOw"]</code>, <code>query = "yllw"</code>: <code>correct = ""</code> （无匹配项）</li>
    </ul>
    </li>

</ul>

<p>此外，拼写检查器还按照以下优先级规则操作：</p>

<ul>
	<li>当查询完全匹配单词列表中的某个单词（<strong>区分大小写</strong>）时，应返回相同的单词。</li>
	<li>当查询匹配到大小写问题的单词时，您应该返回单词列表中的第一个这样的匹配项。</li>
	<li>当查询匹配到元音错误的单词时，您应该返回单词列表中的第一个这样的匹配项。</li>
	<li>如果该查询在单词列表中没有匹配项，则应返回空字符串。</li>
</ul>

<p>给出一些查询 <code>queries</code>，返回一个单词列表 <code>answer</code>，其中 <code>answer[i]</code> 是由查询 <code>query = queries[i]</code> 得到的正确单词。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
<strong>输出：</strong>["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>wordlist = ["yellow"], queries = ["YellOw"]
<b>输出：</b>["yellow"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= wordlist.length, queries.length &lt;= 5000</code></li>
	<li><code>1 &lt;= wordlist[i].length, queries[i].length &lt;= 7</code></li>
	<li><code>wordlist[i]</code>&nbsp;和&nbsp;<code>queries[i]</code>&nbsp;只包含英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们遍历 $\textit{wordlist}$，将单词按照大小写不敏感、元音不敏感的规则分别存入哈希表 $\textit{low}$ 和 $\textit{pat}$ 中，其中 $\textit{low}$ 的键为单词的小写形式，$\textit{pat}$ 的键为将单词的元音字母替换为 `*` 后的字符串，值为单词本身。用哈希表 $\textit{s}$ 存储 $\textit{wordlist}$ 中的单词。

遍历 $\textit{queries}$，对于每个单词 $\textit{q}$，如果 $\textit{q}$ 在 $\textit{s}$ 中，说明 $\textit{q}$ 在 $\textit{wordlist}$ 中，直接将 $\textit{q}$ 加入答案数组 $\textit{ans}$ 中。

否则，如果 $\textit{q}$ 的小写形式在 $\textit{low}$ 中，说明 $\textit{q}$ 在 $\textit{wordlist}$ 中，且大小写不敏感，将 $\textit{low}[q.\text{lower}()]$ 加入答案数组 $\textit{ans}$ 中。

否则，如果将 $\textit{q}$ 的元音字母替换为 `*` 后的字符串在 $\textit{pat}$ 中，说明 $\textit{q}$ 在 $\textit{wordlist}$ 中，且元音不敏感，将 $\textit{pat}[f(q)]$ 加入答案数组 $\textit{ans}$ 中。

否则，说明 $\textit{q}$ 在 $\textit{wordlist}$ 中，且大小写和元音都不敏感，将空字符串加入答案数组 $\textit{ans}$ 中。

最后返回答案数组 $\textit{ans}$ 即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为 $\textit{wordlist}$ 和 $\textit{queries}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def spellchecker(self, wordlist: List[str], queries: List[str]) -> List[str]:
        def f(w):
            t = []
            for c in w:
                t.append("*" if c in "aeiou" else c)
            return "".join(t)

        s = set(wordlist)
        low, pat = {}, {}
        for w in wordlist:
            t = w.lower()
            low.setdefault(t, w)
            pat.setdefault(f(t), w)

        ans = []
        for q in queries:
            if q in s:
                ans.append(q)
                continue
            q = q.lower()
            if q in low:
                ans.append(low[q])
                continue
            q = f(q)
            if q in pat:
                ans.append(pat[q])
                continue
            ans.append("")
        return ans
```

#### Java

```java
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> s = new HashSet<>();
        Map<String, String> low = new HashMap<>();
        Map<String, String> pat = new HashMap<>();
        for (String w : wordlist) {
            s.add(w);
            String t = w.toLowerCase();
            low.putIfAbsent(t, w);
            pat.putIfAbsent(f(t), w);
        }
        int m = queries.length;
        String[] ans = new String[m];
        for (int i = 0; i < m; ++i) {
            String q = queries[i];
            if (s.contains(q)) {
                ans[i] = q;
                continue;
            }
            q = q.toLowerCase();
            if (low.containsKey(q)) {
                ans[i] = low.get(q);
                continue;
            }
            q = f(q);
            if (pat.containsKey(q)) {
                ans[i] = pat.get(q);
                continue;
            }
            ans[i] = "";
        }
        return ans;
    }

    private String f(String w) {
        char[] cs = w.toCharArray();
        for (int i = 0; i < cs.length; ++i) {
            char c = cs[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                cs[i] = '*';
            }
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> s(wordlist.begin(), wordlist.end());
        unordered_map<string, string> low;
        unordered_map<string, string> pat;
        auto f = [](string& w) {
            string res;
            for (char& c : w) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    res += '*';
                } else {
                    res += c;
                }
            }
            return res;
        };
        for (const auto& w : wordlist) {
            string t = w;
            transform(t.begin(), t.end(), t.begin(), ::tolower);
            if (!low.contains(t)) {
                low[t] = w;
            }
            t = f(t);
            if (!pat.contains(t)) {
                pat[t] = w;
            }
        }
        vector<string> ans;
        for (auto& q : queries) {
            if (s.contains(q)) {
                ans.emplace_back(q);
                continue;
            }
            transform(q.begin(), q.end(), q.begin(), ::tolower);
            if (low.contains(q)) {
                ans.emplace_back(low[q]);
                continue;
            }
            q = f(q);
            if (pat.contains(q)) {
                ans.emplace_back(pat[q]);
                continue;
            }
            ans.emplace_back("");
        }
        return ans;
    }
};
```

#### Go

```go
func spellchecker(wordlist []string, queries []string) (ans []string) {
	s := map[string]bool{}
	low := map[string]string{}
	pat := map[string]string{}
	f := func(w string) string {
		res := []byte(w)
		for i := range res {
			if res[i] == 'a' || res[i] == 'e' || res[i] == 'i' || res[i] == 'o' || res[i] == 'u' {
				res[i] = '*'
			}
		}
		return string(res)
	}
	for _, w := range wordlist {
		s[w] = true
		t := strings.ToLower(w)
		if _, ok := low[t]; !ok {
			low[t] = w
		}
		if _, ok := pat[f(t)]; !ok {
			pat[f(t)] = w
		}
	}
	for _, q := range queries {
		if s[q] {
			ans = append(ans, q)
			continue
		}
		q = strings.ToLower(q)
		if s, ok := low[q]; ok {
			ans = append(ans, s)
			continue
		}
		q = f(q)
		if s, ok := pat[q]; ok {
			ans = append(ans, s)
			continue
		}
		ans = append(ans, "")
	}
	return
}
```

#### TypeScript

```ts
function spellchecker(wordlist: string[], queries: string[]): string[] {
    const s = new Set(wordlist);
    const low = new Map<string, string>();
    const pat = new Map<string, string>();

    const f = (w: string): string => {
        let res = '';
        for (const c of w) {
            if ('aeiou'.includes(c)) {
                res += '*';
            } else {
                res += c;
            }
        }
        return res;
    };

    for (const w of wordlist) {
        let t = w.toLowerCase();
        if (!low.has(t)) {
            low.set(t, w);
        }
        t = f(t);
        if (!pat.has(t)) {
            pat.set(t, w);
        }
    }

    const ans: string[] = [];
    for (let q of queries) {
        if (s.has(q)) {
            ans.push(q);
            continue;
        }
        q = q.toLowerCase();
        if (low.has(q)) {
            ans.push(low.get(q)!);
            continue;
        }
        q = f(q);
        if (pat.has(q)) {
            ans.push(pat.get(q)!);
            continue;
        }
        ans.push('');
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{HashSet, HashMap};

impl Solution {
    pub fn spellchecker(wordlist: Vec<String>, queries: Vec<String>) -> Vec<String> {
        let s: HashSet<String> = wordlist.iter().cloned().collect();
        let mut low: HashMap<String, String> = HashMap::new();
        let mut pat: HashMap<String, String> = HashMap::new();

        let f = |w: &str| -> String {
            w.chars()
                .map(|c| match c {
                    'a' | 'e' | 'i' | 'o' | 'u' => '*',
                    _ => c,
                })
                .collect()
        };

        for w in &wordlist {
            let mut t = w.to_lowercase();
            if !low.contains_key(&t) {
                low.insert(t.clone(), w.clone());
            }
            t = f(&t);
            if !pat.contains_key(&t) {
                pat.insert(t.clone(), w.clone());
            }
        }

        let mut ans: Vec<String> = Vec::new();
        for query in queries {
            if s.contains(&query) {
                ans.push(query);
                continue;
            }
            let mut q = query.to_lowercase();
            if let Some(v) = low.get(&q) {
                ans.push(v.clone());
                continue;
            }
            q = f(&q);
            if let Some(v) = pat.get(&q) {
                ans.push(v.clone());
                continue;
            }
            ans.push("".to_string());
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
