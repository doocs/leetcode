# [734. 句子相似性](https://leetcode.cn/problems/sentence-similarity)

[English Version](/solution/0700-0799/0734.Sentence%20Similarity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areSentencesSimilar(
        self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]
    ) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        s = {(a, b) for a, b in similarPairs}
        return all(
            a == b or (a, b) in s or (b, a) in s for a, b in zip(sentence1, sentence2)
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areSentencesSimilar(
        String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<String> s = new HashSet<>();
        for (List<String> e : similarPairs) {
            s.add(e.get(0) + "." + e.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            String a = sentence1[i], b = sentence2[i];
            if (!a.equals(b) && !s.contains(a + "." + b) && !s.contains(b + "." + a)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areSentencesSimilar(vector<string>& sentence1, vector<string>& sentence2, vector<vector<string>>& similarPairs) {
        int m = sentence1.size(), n = sentence2.size();
        if (m != n) return false;
        unordered_set<string> s;
        for (auto e : similarPairs) s.insert(e[0] + "." + e[1]);
        for (int i = 0; i < n; ++i) {
            string a = sentence1[i], b = sentence2[i];
            if (a != b && !s.count(a + "." + b) && !s.count(b + "." + a)) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func areSentencesSimilar(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	s := map[string]bool{}
	for _, e := range similarPairs {
		s[e[0]+"."+e[1]] = true
	}
	for i, a := range sentence1 {
		b := sentence2[i]
		if a != b && !s[a+"."+b] && !s[b+"."+a] {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
