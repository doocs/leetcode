# [734. Sentence Similarity](https://leetcode.com/problems/sentence-similarity)

[中文文档](/solution/0700-0799/0734.Sentence%20Similarity/README.md)

## Description

<p>We can represent a sentence as an array of words, for example, the sentence <code>&quot;I am happy with leetcode&quot;</code> can be represented as <code>arr = [&quot;I&quot;,&quot;am&quot;,happy&quot;,&quot;with&quot;,&quot;leetcode&quot;]</code>.</p>

<p>Given two sentences <code>sentence1</code> and <code>sentence2</code> each represented as a string array and given an array of string pairs <code>similarPairs</code> where <code>similarPairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> indicates that the two words <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> are similar.</p>

<p>Return <em><code>true</code> if <code>sentence1</code> and <code>sentence2</code> are similar, or <code>false</code> if they are not similar</em>.</p>

<p>Two sentences are similar if:</p>

<ul>
	<li>They have <strong>the same length</strong> (i.e., the same number of words)</li>
	<li><code>sentence1[i]</code> and <code>sentence2[i]</code> are similar.</li>
</ul>

<p>Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words <code>a</code> and <code>b</code> are similar, and the words <code>b</code> and <code>c</code> are similar, <code>a</code> and <code>c</code> are <strong>not necessarily similar</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;,&quot;acting&quot;,&quot;skills&quot;], sentence2 = [&quot;fine&quot;,&quot;drama&quot;,&quot;talent&quot;], similarPairs = [[&quot;great&quot;,&quot;fine&quot;],[&quot;drama&quot;,&quot;acting&quot;],[&quot;skills&quot;,&quot;talent&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;], sentence2 = [&quot;great&quot;], similarPairs = []
<strong>Output:</strong> true
<strong>Explanation:</strong> A word is similar to itself.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;], sentence2 = [&quot;doubleplus&quot;,&quot;good&quot;], similarPairs = [[&quot;great&quot;,&quot;doubleplus&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> As they don&#39;t have the same length, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sentence1[i].length, sentence2[i].length &lt;= 20</code></li>
	<li><code>sentence1[i]</code> and <code>sentence2[i]</code> consist of English letters.</li>
	<li><code>0 &lt;= similarPairs.length &lt;= 1000</code></li>
	<li><code>similarPairs[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>.length, y<sub>i</sub>.length &lt;= 20</code></li>
	<li><code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> consist of lower-case and upper-case English letters.</li>
	<li>All the pairs <code>(x<sub>i</sub>,<sub> </sub>y<sub>i</sub>)</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
