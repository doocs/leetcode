---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1813.Sentence%20Similarity%20III/README_EN.md
rating: 1588
source: Biweekly Contest 49 Q2
tags:
    - Array
    - Two Pointers
    - String
---

<!-- problem:start -->

# [1813. Sentence Similarity III](https://leetcode.com/problems/sentence-similarity-iii)

[中文文档](/solution/1800-1899/1813.Sentence%20Similarity%20III/README.md)

## Description

<!-- description:start -->

<p>A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, <code>&quot;Hello World&quot;</code>, <code>&quot;HELLO&quot;</code>, <code>&quot;hello world hello world&quot;</code> are all sentences. Words consist of <strong>only</strong> uppercase and lowercase English letters.</p>

<p>Two sentences <code>sentence1</code> and <code>sentence2</code> are <strong>similar</strong> if it is possible to insert an arbitrary sentence <strong>(possibly empty)</strong> inside one of these sentences such that the two sentences become equal. For example, <code>sentence1 = &quot;Hello my name is Jane&quot;</code> and <code>sentence2 = &quot;Hello Jane&quot;</code> can be made equal by inserting <code>&quot;my name is&quot;</code> between <code>&quot;Hello&quot;</code> and <code>&quot;Jane&quot;</code> in <code>sentence2</code>.</p>

<p>Given two sentences <code>sentence1</code> and <code>sentence2</code>, return <code>true</code> <em>if </em><code>sentence1</code> <em>and </em><code>sentence2</code> <em>are similar.</em> Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;My name is Haley&quot;, sentence2 = &quot;My Haley&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> sentence2 can be turned to sentence1 by inserting &quot;name is&quot; between &quot;My&quot; and &quot;Haley&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;of&quot;, sentence2 = &quot;A lot of words&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>No single sentence can be inserted inside one of the sentences to make it equal to the other.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;Eating right now&quot;, sentence2 = &quot;Eating&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> sentence2 can be turned to sentence1 by inserting &quot;right now&quot; at the end of the sentence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li>
	<li><code>sentence1</code> and <code>sentence2</code> consist of lowercase and uppercase English letters and spaces.</li>
	<li>The words in <code>sentence1</code> and <code>sentence2</code> are separated by a single space.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We split the two sentences into two word arrays `words1` and `words2` by spaces. Let the lengths of `words1` and `words2` be $m$ and $n$, respectively, and assume that $m \ge nn.

We use two pointers $i$ and $j$, initially $i = j = 0$. Next, we loop to check whether `words1[i]` is equal to `words2[i]`, and if so, pointer $i$ continues to move right; then we loop to check whether `words1[m - 1 - j]` is equal to `words2[n - 1 - j]`, and if so, pointer $j$ continues to move right.

After the loop, if $i + j \ge n$, it means that the two sentences are similar, and we return `true`; otherwise, we return `false`.

The time complexity is $O(L)$, and the space complexity is $O(L)$, where $L$ is the sum of the lengths of the two sentences.

<!-- tabs:start -->

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
