---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0734.Sentence%20Similarity/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [734. 句子相似性 🔒](https://leetcode.cn/problems/sentence-similarity)

[English Version](/solution/0700-0799/0734.Sentence%20Similarity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们可以将一个句子表示为一个单词数组，例如，句子 <code>"I am happy with leetcode"</code> 可以表示为 <code>arr = ["I","am",happy","with","leetcode"]</code></p>

<p>给定两个句子 <code>sentence1</code> 和 <code>sentence2</code> 分别表示为一个字符串数组，并给定一个字符串对 <code>similarPairs</code> ，其中&nbsp;<code>similarPairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示两个单词&nbsp;<code>x<sub>i</sub></code>&nbsp;and&nbsp;<code>y<sub>i</sub></code>&nbsp;是相似的。</p>

<p>如果 <code>sentence1</code> 和 <code>sentence2</code> 相似则返回 <code>true</code> ，如果不相似则返回 <code>false</code> 。</p>

<p>两个句子是相似的，如果:</p>

<ul>
	<li>它们具有 <strong>相同的长度</strong> (即相同的字数)</li>
	<li><code>sentence1[i]</code>&nbsp;和&nbsp;<code>sentence2[i]</code>&nbsp;是相似的</li>
</ul>

<p>请注意，一个词总是与它自己相似，也请注意，相似关系是不可传递的。例如，如果单词 <code>a</code> 和 <code>b</code> 是相似的，单词&nbsp;<code>b</code> 和 <code>c</code> 也是相似的，那么 <code>a</code> 和 <code>c</code>&nbsp; <strong>不一定相似</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
<strong>输出:</strong> true
<strong>解释:</strong> 这两个句子长度相同，每个单词都相似。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
<strong>输出:</strong> true
<strong>解释:</strong> 一个单词和它本身相似。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
<strong>输出:</strong> false
<strong>解释: </strong>因为它们长度不同，所以返回false。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sentence1[i].length, sentence2[i].length &lt;= 20</code></li>
	<li><code>sentence1[i]</code>&nbsp;和&nbsp;<code>sentence2[i]</code>&nbsp;只包含大小写英文字母</li>
	<li><code>0 &lt;= similarPairs.length &lt;= 2000</code></li>
	<li><code>similarPairs[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>.length, y<sub>i</sub>.length &lt;= 20</code></li>
	<li>所有对&nbsp;<code>(xi, yi)</code>&nbsp;都是 <strong>不同</strong> 的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们首先判断 $\textit{sentence1}$ 和 $\textit{sentence2}$ 的长度是否相等，如果不相等则返回 $\text{false}$。

然后我们使用一个哈希表 $\textit{s}$ 来存储所有相似的单词对，对于 $\textit{similarPairs}$ 中的每一个单词对 $[x, y]$，我们将 $x$ 和 $y$ 加入到哈希表 $\textit{s}$ 中。

接下来我们遍历 $\textit{sentence1}$ 和 $\textit{sentence2}$，对于每一个位置 $i$，如果 $\textit{sentence1}[i]$ 不等于 $\textit{sentence2}[i]$，并且 $(\textit{sentence1}[i], \textit{sentence2}[i])$ 和 $(\textit{sentence2}[i], \textit{sentence1}[i])$ 都不在哈希表 $\textit{s}$ 中，那么返回 $\text{false}$。

如果遍历结束后都没有返回 $\text{false}$，说明 $\textit{sentence1}$ 和 $\textit{sentence2}$ 是相似的，返回 $\text{true}$。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为题目中所有字符串的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areSentencesSimilar(
        self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]
    ) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        s = {(x, y) for x, y in similarPairs}
        for x, y in zip(sentence1, sentence2):
            if x != y and (x, y) not in s and (y, x) not in s:
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean areSentencesSimilar(
        String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<List<String>> s = new HashSet<>();
        for (var p : similarPairs) {
            s.add(p);
        }
        for (int i = 0; i < sentence1.length; i++) {
            if (!sentence1[i].equals(sentence2[i])
                && !s.contains(List.of(sentence1[i], sentence2[i]))
                && !s.contains(List.of(sentence2[i], sentence1[i]))) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool areSentencesSimilar(vector<string>& sentence1, vector<string>& sentence2, vector<vector<string>>& similarPairs) {
        if (sentence1.size() != sentence2.size()) {
            return false;
        }
        unordered_set<string> s;
        for (const auto& p : similarPairs) {
            s.insert(p[0] + "#" + p[1]);
            s.insert(p[1] + "#" + p[0]);
        }
        for (int i = 0; i < sentence1.size(); ++i) {
            if (sentence1[i] != sentence2[i] && !s.contains(sentence1[i] + "#" + sentence2[i])) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func areSentencesSimilar(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	s := map[string]bool{}
	for _, p := range similarPairs {
		s[p[0]+"#"+p[1]] = true
	}
	for i, x := range sentence1 {
		y := sentence2[i]
		if x != y && !s[x+"#"+y] && !s[y+"#"+x] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function areSentencesSimilar(
    sentence1: string[],
    sentence2: string[],
    similarPairs: string[][],
): boolean {
    if (sentence1.length !== sentence2.length) {
        return false;
    }
    const s = new Set<string>();
    for (const [x, y] of similarPairs) {
        s.add(x + '#' + y);
        s.add(y + '#' + x);
    }
    for (let i = 0; i < sentence1.length; i++) {
        if (sentence1[i] !== sentence2[i] && !s.has(sentence1[i] + '#' + sentence2[i])) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn are_sentences_similar(
        sentence1: Vec<String>,
        sentence2: Vec<String>,
        similar_pairs: Vec<Vec<String>>,
    ) -> bool {
        if sentence1.len() != sentence2.len() {
            return false;
        }

        let s: HashSet<(String, String)> = similar_pairs
            .into_iter()
            .map(|pair| (pair[0].clone(), pair[1].clone()))
            .collect();

        for (x, y) in sentence1.iter().zip(sentence2.iter()) {
            if x != y
                && !s.contains(&(x.clone(), y.clone()))
                && !s.contains(&(y.clone(), x.clone()))
            {
                return false;
            }
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} sentence1
 * @param {string[]} sentence2
 * @param {string[][]} similarPairs
 * @return {boolean}
 */
var areSentencesSimilar = function (sentence1, sentence2, similarPairs) {
    if (sentence1.length !== sentence2.length) {
        return false;
    }
    const s = new Set();
    for (const [x, y] of similarPairs) {
        s.add(x + '#' + y);
        s.add(y + '#' + x);
    }
    for (let i = 0; i < sentence1.length; i++) {
        if (sentence1[i] !== sentence2[i] && !s.has(sentence1[i] + '#' + sentence2[i])) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
