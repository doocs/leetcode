# [1813. Sentence Similarity III](https://leetcode.com/problems/sentence-similarity-iii)

[中文文档](/solution/1800-1899/1813.Sentence%20Similarity%20III/README.md)

## Description

<p>A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, <code>&quot;Hello World&quot;</code>, <code>&quot;HELLO&quot;</code>, <code>&quot;hello world hello world&quot;</code> are all sentences. Words consist of <strong>only</strong> uppercase and lowercase English letters.</p>

<p>Two sentences <code>sentence1</code> and <code>sentence2</code> are <strong>similar</strong> if it is possible to insert an arbitrary sentence <strong>(possibly empty)</strong> inside one of these sentences such that the two sentences become equal. For example, <code>sentence1 = &quot;Hello my name is Jane&quot;</code> and <code>sentence2 = &quot;Hello Jane&quot;</code> can be made equal by inserting <code>&quot;my name is&quot;</code> between <code>&quot;Hello&quot;</code> and <code>&quot;Jane&quot;</code> in <code>sentence2</code>.</p>

<p>Given two sentences <code>sentence1</code> and <code>sentence2</code>, return <code>true</code> <em>if </em><code>sentence1</code> <em>and </em><code>sentence2</code> <em>are similar.</em> Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;My name is Haley&quot;, sentence2 = &quot;My Haley&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> sentence2 can be turned to sentence1 by inserting &quot;name is&quot; between &quot;My&quot; and &quot;Haley&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;of&quot;, sentence2 = &quot;A lot of words&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>No single sentence can be inserted inside one of the sentences to make it equal to the other.
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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
