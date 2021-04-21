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

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> sentence1 = &quot;Luky&quot;, sentence2 = &quot;Lucccky&quot;
<strong>Output:</strong> false
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

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
