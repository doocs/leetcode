---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1813.Sentence%20Similarity%20III/README.md
rating: 1588
source: 第 49 场双周赛 Q2
tags:
    - 数组
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [1813. 句子相似性 III](https://leetcode.cn/problems/sentence-similarity-iii)

[English Version](/solution/1800-1899/1813.Sentence%20Similarity%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串&nbsp;<code>sentence1</code>&nbsp;和&nbsp;<code>sentence2</code>，每个表示由一些单词组成的一个句子。句子是一系列由&nbsp;<strong>单个 </strong>空格分隔的&nbsp;<strong>单词</strong>，且开头和结尾没有多余空格。每个单词都只包含大写和小写英文字母。</p>

<p>如果两个句子&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，那么我们称这两个句子是 <strong>相似的</strong>&nbsp;。<strong>注意</strong>，插入的句子必须与现有单词用空白隔开。&nbsp;</p>

<p>比方说，</p>

<ul>
	<li><code>s1 = "Hello Jane"</code> 与&nbsp;<code>s2 = "Hello my name is Jane"</code>，我们可以往 <code>s1</code>&nbsp;中&nbsp;<code>"Hello"</code> 和&nbsp;<code>"Jane"</code>&nbsp;之间插入&nbsp;<code>"my name is"</code>&nbsp;得到 <code>s2</code>&nbsp;。</li>
	<li><code>s1 = "Frog cool"</code>&nbsp;与 <code>s2 = "Frogs are cool"</code>&nbsp;不是相似的，因为尽管往&nbsp;<code>s1</code>&nbsp;中插入&nbsp;<code>"s are"</code>，它没有与&nbsp;<code>"Frog"</code>&nbsp;用空格隔开。</li>
</ul>

<p>给你两个句子&nbsp;<code>sentence1</code> 和&nbsp;<code>sentence2</code>&nbsp;，如果<em>&nbsp;</em><code>sentence1</code> 和<em>&nbsp;</em><code>sentence2</code> 是 <strong>相似</strong> 的，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block"><b>输入：</b>sentence1 = "My name is Haley", sentence2 = "My Haley"</div>

<div class="example-block"><b>输出：</b>true</div>

<div class="example-block"><b>解释：</b>可以往 <code>sentence2</code> 中 "My" 和 "Haley" 之间插入 "name is" ，得到 <code>sentence1</code> 。</div>

<div class="example-block">&nbsp;</div>

<p><strong>示例 2：</strong></p>

<div class="example-block"><b>输入：</b>sentence1 = "of", sentence2 = "A lot of words"</div>

<div class="example-block"><b>输出：</b>false</div>

<div class="example-block"><strong>解释：</strong>没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。</div>

<div class="example-block">&nbsp;</div>

<p><strong>示例 3：</strong></p>

<div class="example-block"><b>输入：</b>sentence1 = "Eating right now", sentence2 = "Eating"</div>

<div class="example-block"><b>输出：</b>true</div>

<div class="example-block"><b>解释：</b>可以往 <code>sentence2</code> 的结尾插入 "right now" 得到 <code>sentence1</code> 。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li>
	<li><code>sentence1</code>&nbsp;和&nbsp;<code>sentence2</code>&nbsp;都只包含大小写英文字母和空格。</li>
	<li><code>sentence1</code>&nbsp;和&nbsp;<code>sentence2</code>&nbsp;中的单词都只由单个空格隔开。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们将两个句子按照空格分割成两个单词数组 `words1` 和 `words2`，假设 `words1` 和 `words2` 的长度分别为 $m$ 和 $n$，不妨设 $m \geq n$。

我们使用双指针 $i$ 和 $j$，初始时 $i = j = 0$。接下来，我们循环判断 `words1[i]` 是否等于 `words2[i]`，是则指针 $i$ 继续右移；然后我们循环判断 `words1[m - 1 - j]` 是否等于 `words2[n - 1 - j]`，是则指针 $j$ 继续右移。

循环结束后，如果 $i + j \geq n$，说明两个句子相似，返回 `true`，否则返回 `false`。

时间复杂度 $O(L)$，空间复杂度 $O(L)$。其中 $L$ 为两个句子的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        words1, words2 = sentence1.split(), sentence2.split()
        m, n = len(words1), len(words2)
        if m < n:
            words1, words2 = words2, words1
            m, n = n, m
        i = j = 0
        while i < n and words1[i] == words2[i]:
            i += 1
        while j < n and words1[m - 1 - j] == words2[n - 1 - j]:
            j += 1
        return i + j >= n
```

#### Java

```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        var words1 = sentence1.split(" ");
        var words2 = sentence2.split(" ");
        if (words1.length < words2.length) {
            var t = words1;
            words1 = words2;
            words2 = t;
        }
        int m = words1.length, n = words2.length;
        int i = 0, j = 0;
        while (i < n && words1[i].equals(words2[i])) {
            ++i;
        }
        while (j < n && words1[m - 1 - j].equals(words2[n - 1 - j])) {
            ++j;
        }
        return i + j >= n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool areSentencesSimilar(string sentence1, string sentence2) {
        auto words1 = split(sentence1, ' ');
        auto words2 = split(sentence2, ' ');
        if (words1.size() < words2.size()) {
            swap(words1, words2);
        }
        int m = words1.size(), n = words2.size();
        int i = 0, j = 0;
        while (i < n && words1[i] == words2[i]) {
            ++i;
        }
        while (j < n && words1[m - 1 - j] == words2[n - 1 - j]) {
            ++j;
        }
        return i + j >= n;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};
```

#### Go

```go
func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	words1, words2 := strings.Fields(sentence1), strings.Fields(sentence2)
	if len(words1) < len(words2) {
		words1, words2 = words2, words1
	}
	m, n := len(words1), len(words2)
	i, j := 0, 0
	for i < n && words1[i] == words2[i] {
		i++
	}
	for j < n && words1[m-1-j] == words2[n-1-j] {
		j++
	}
	return i+j >= n
}
```

#### TypeScript

```ts
function areSentencesSimilar(sentence1: string, sentence2: string): boolean {
    const words1 = sentence1.split(' ');
    const words2 = sentence2.split(' ');
    if (words1.length < words2.length) {
        return areSentencesSimilar(sentence2, sentence1);
    }
    const [m, n] = [words1.length, words2.length];
    let [i, j] = [0, 0];
    while (i < n && words1[i] === words2[i]) {
        ++i;
    }
    while (j < n && words1[m - 1 - j] === words2[n - 1 - j]) {
        ++j;
    }
    return i + j >= n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
