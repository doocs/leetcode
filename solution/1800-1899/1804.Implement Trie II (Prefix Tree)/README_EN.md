# [1804. Implement Trie II (Prefix Tree)](https://leetcode.com/problems/implement-trie-ii-prefix-tree)

[中文文档](/solution/1800-1899/1804.Implement%20Trie%20II%20%28Prefix%20Tree%29/README.md)

## Description

<p>A <a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>trie</strong></a> (pronounced as &quot;try&quot;) or <strong>prefix tree</strong> is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.</p>

<p>Implement the Trie class:</p>

<ul>
	<li><code>Trie()</code> Initializes the trie object.</li>
	<li><code>void insert(String word)</code> Inserts the string <code>word</code> into the trie.</li>
	<li><code>int countWordsEqualTo(String word)</code> Returns the number of instances of the string <code>word</code> in the trie.</li>
	<li><code>int countWordsStartingWith(String prefix)</code> Returns the number of strings in the trie that have the string <code>prefix</code> as a prefix.</li>
	<li><code>void erase(String word)</code> Erases the string <code>word</code> from the trie.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;countWordsEqualTo&quot;, &quot;countWordsStartingWith&quot;, &quot;erase&quot;, &quot;countWordsEqualTo&quot;, &quot;countWordsStartingWith&quot;, &quot;erase&quot;, &quot;countWordsStartingWith&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;apple&quot;], [&quot;app&quot;]]
<strong>Output</strong>
[null, null, null, 2, 2, null, 1, 1, null, 0]

<strong>Explanation</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);               // Inserts &quot;apple&quot;.
trie.insert(&quot;apple&quot;);               // Inserts another &quot;apple&quot;.
trie.countWordsEqualTo(&quot;apple&quot;);    // There are two instances of &quot;apple&quot; so return 2.
trie.countWordsStartingWith(&quot;app&quot;); // &quot;app&quot; is a prefix of &quot;apple&quot; so return 2.
trie.erase(&quot;apple&quot;);                // Erases one &quot;apple&quot;.
trie.countWordsEqualTo(&quot;apple&quot;);    // Now there is only one instance of &quot;apple&quot; so return 1.
trie.countWordsStartingWith(&quot;app&quot;); // return 1
trie.erase(&quot;apple&quot;);                // Erases &quot;apple&quot;. Now the trie is empty.
trie.countWordsStartingWith(&quot;app&quot;); // return 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> and <code>prefix</code> consist only of lowercase English letters.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>insert</code>, <code>countWordsEqualTo</code>, <code>countWordsStartingWith</code>, and <code>erase</code>.</li>
	<li>It is guaranteed that for any function call to <code>erase</code>, the string <code>word</code> will exist in the trie.</li>
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
