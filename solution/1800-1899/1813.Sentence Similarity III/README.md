# [1813. 句子相似性 III](https://leetcode.cn/problems/sentence-similarity-iii)

[English Version](/solution/1800-1899/1813.Sentence%20Similarity%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，<code>"Hello World"</code> ，<code>"HELLO"</code> ，<code>"hello world hello world"</code> 都是句子。每个单词都 <strong>只</strong> 包含大写和小写英文字母。</p>

<p>如果两个句子 <code>sentence1</code> 和 <code>sentence2</code> ，可以通过往其中一个句子插入一个任意的句子（<strong>可以是空句子</strong>）而得到另一个句子，那么我们称这两个句子是 <strong>相似的</strong> 。比方说，<code>sentence1 = "Hello my name is Jane"</code> 且 <code>sentence2 = "Hello Jane"</code> ，我们可以往 <code>sentence2</code> 中 <code>"Hello"</code> 和 <code>"Jane"</code> 之间插入 <code>"my name is"</code> 得到 <code>sentence1</code> 。</p>

<p>给你两个句子 <code>sentence1</code> 和 <code>sentence2</code> ，如果<em> </em><code>sentence1</code> 和<em> </em><code>sentence2</code> 是相似的，请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>sentence1 = "My name is Haley", sentence2 = "My Haley"
<b>输出：</b>true
<b>解释：</b>可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>sentence1 = "of", sentence2 = "A lot of words"
<b>输出：</b>false
<strong>解释：</strong>没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>sentence1 = "Eating right now", sentence2 = "Eating"
<b>输出：</b>true
<b>解释：</b>可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>sentence1 = "Luky", sentence2 = "Lucccky"
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li>
	<li><code>sentence1</code> 和 <code>sentence2</code> 都只包含大小写英文字母和空格。</li>
	<li><code>sentence1</code> 和 <code>sentence2</code> 中的单词都只由单个空格隔开。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

把句子分割成单词数组，然后通过公共前后缀进行判断

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        if sentence1 == sentence2:
            return True
        n1, n2 = len(sentence1), len(sentence2)
        if n1 == n2:
            return False
        if n1 < n2:
            sentence1, sentence2 = sentence2, sentence1
        words1, words2 = sentence1.split(), sentence2.split()
        i = j = 0
        n1, n2 = len(words1), len(words2)
        while i < n2 and words1[i] == words2[i]:
            i += 1
        if i == n2:
            return True
        while j < n2 and words1[n1 - 1 - j] == words2[n2 - 1 - j]:
            j += 1
        return j == n2 or i + j == n2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) {
            return true;
        }
        int n1 = sentence1.length(), n2 = sentence2.length();
        if (n1 == n2) {
            return false;
        }
        if (n1 < n2) {
            String t = sentence1;
            sentence1 = sentence2;
            sentence2 = t;
        }
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0, j = 0;
        n1 = words1.length;
        n2 = words2.length;
        while (i < n2 &&  words1[i].equals(words2[i])) {
            ++i;
        }
        if (i == n2) {
            return true;
        }
        while (j < n2 && words1[n1 - 1 - j].equals(words2[n2 - 1 - j])) {
            ++j;
        }
        return j == n2 || i + j == n2;
    }
}
```

### **Go**

```go
func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	if sentence1 == sentence2 {
		return true
	}
	l1, l2 := len(sentence1), len(sentence2)
	if l1 == l2 {
		return false
	}
	if l1 < l2 {
		sentence1, sentence2 = sentence2, sentence1
	}
	i, j := 0, 0
	w1, w2 := strings.Fields(sentence1), strings.Fields(sentence2)
	l1, l2 = len(w1), len(w2)
	for i < l2 && w1[i] == w2[i] {
		i++
	}
	if i == l2 {
		return true
	}
	for j < l2 && w1[l1-1-j] == w2[l2-1-j] {
		j++
	}
	return j == l2 || i+j == l2
}
```

### **C++**

```cpp
class Solution {
public:
    bool areSentencesSimilar(string sentence1, string sentence2) {
        if (sentence1 == sentence2) return true;
        int n1 = sentence1.size(), n2 = sentence2.size();
        if (n1 == n2) return false;

        if (n1 < n2) swap(sentence1, sentence2);
        auto words1 = split(sentence1);
        auto words2 = split(sentence2);
        int i = 0, j = 0;
        n1 = words1.size(), n2 = words2.size();

        while (i < n2 && words1[i] == words2[i]) ++i;
        if (i == n2) return true;

        while (j < n2 && words1[n1 - 1 - j] == words2[n2 - 1 - j]) ++j;
        return j == n2 || i + j == n2;
    }

    vector<string> split(const string& str) {
        vector<string> words;
        int i = str.find_first_not_of(' ');
        int j = str.find_first_of(' ', i);
        while (j != string::npos) {
            words.emplace_back(str.substr(i, j - i));
            i = str.find_first_not_of(' ', j);
            j = str.find_first_of(' ', i);
        }
        words.emplace_back(str.substr(i));
        return words;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
