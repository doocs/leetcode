# [734. Sentence Similarity](https://leetcode.com/problems/sentence-similarity)

[中文文档](/solution/0700-0799/0734.Sentence%20Similarity/README.md)

## Description

<p>We can represent a&nbsp;sentence as an array of words, for example, the&nbsp;sentence <code>&quot;I am happy with leetcode&quot;</code> can be represented as <code>arr = [&quot;I&quot;,&quot;am&quot;,happy&quot;,&quot;with&quot;,&quot;leetcode&quot;]</code>.</p>

<p>Given two&nbsp;sentences&nbsp;<code>sentence1</code> and&nbsp;<code>sentence2</code> each represented as a string array and given an array of string pairs <code>similarPairs</code> where&nbsp;<code>similarPairs[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;indicates that the two words&nbsp;<code>x<sub>i</sub></code> and&nbsp;<code>y<sub>i</sub></code> are similar.</p>

<p>Return <em><code>true</code> if&nbsp;<code>sentence1</code>&nbsp;and&nbsp;<code>sentence2</code>&nbsp;are similar, or <code>false</code> if they are not similar</em>.</p>

<p>Two sentences are similar if:</p>

<ul>
	<li>They have <strong>the same length</strong> (i.e. the same number of words)</li>
	<li><code>sentence1[i]</code> and&nbsp;<code>sentence2[i]</code>&nbsp;are similar.</li>
</ul>

<p>Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words&nbsp;<code><font face="monospace">a</font></code>&nbsp;and <code>b</code> are similar and the words <code>b</code>&nbsp;and <code>c</code> are similar, <code>a</code> and <code>c</code> are&nbsp;<strong>not&nbsp;necessarily similar</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;,&quot;acting&quot;,&quot;skills&quot;], sentence2 = [&quot;fine&quot;,&quot;drama&quot;,&quot;talent&quot;], similarPairs = [[&quot;great&quot;,&quot;fine&quot;],[&quot;drama&quot;,&quot;acting&quot;],[&quot;skills&quot;,&quot;talent&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;], sentence2 = [&quot;great&quot;], similarPairs = []
<strong>Output:</strong> true
<strong>Explanation:</strong> A word is similar to itself.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = [&quot;great&quot;], sentence2 = [&quot;doubleplus&quot;,&quot;good&quot;], similarPairs = [[&quot;great&quot;,&quot;doubleplus&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> As they don&#39;t have the same length, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;sentence1.length,&nbsp;sentence2.length &lt;= 1000</code></li>
	<li><code>1 &lt;=&nbsp;sentence1[i].length,&nbsp;sentence2[i].length &lt;= 20</code></li>
	<li><code>sentence1[i]</code> and&nbsp;<code>sentence2[i]</code>&nbsp;consist of lower-case and upper-case English letters.</li>
	<li><code>0 &lt;=&nbsp;similarPairs.length &lt;= 1000</code></li>
	<li><code>similarPairs[i].length == 2</code></li>
	<li><code>1 &lt;=&nbsp;x<sub>i</sub>.length,&nbsp;y<sub>i</sub>.length&nbsp;&lt;= 20</code></li>
	<li><code>x<sub>i</sub></code> and <code>y<sub>i</sub></code>&nbsp;consist of lower-case and upper-case English letters.</li>
	<li>All the pairs <code>(x<sub>i</sub>,<sub>&nbsp;</sub>y<sub>i</sub>)</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areSentencesSimilar(self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        pairs = {(word1, word2) for word1, word2 in similarPairs}
        for i in range(len(sentence1)):
            similar = (sentence1[i], sentence2[i]) in pairs or (sentence2[i], sentence1[i]) in pairs or sentence1[i] == sentence2[i]
            if not similar:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Set<String> pairs = new HashSet<>();
        for (List<String> pair : similarPairs) {
            pairs.add(pair.get(0) + "." + pair.get(1));
        }
        for (int i = 0; i < sentence1.length; ++i) {
            boolean similar =  pairs.contains(sentence1[i] + "." + sentence2[i]) || pairs.contains(sentence2[i] + "." + sentence1[i]) || sentence1[i].equals(sentence2[i]);
            if (!similar) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
