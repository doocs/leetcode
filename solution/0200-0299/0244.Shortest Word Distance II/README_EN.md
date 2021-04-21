# [244. Shortest Word Distance II](https://leetcode.com/problems/shortest-word-distance-ii)

[中文文档](/solution/0200-0299/0244.Shortest%20Word%20Distance%20II/README.md)

## Description

<p>Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.</p>

<p>Implement the <code>WordDistance</code> class:</p>

<ul>
	<li><code>WordDistance(String[] wordsDict)</code> initializes the object with the strings array <code>wordsDict</code>.</li>
	<li><code>int shortest(String word1, String word2)</code> returns the shortest distance between <code>word1</code> and <code>word2</code> in the array <code>wordsDict</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;WordDistance&quot;, &quot;shortest&quot;, &quot;shortest&quot;]
[[[&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]], [&quot;coding&quot;, &quot;practice&quot;], [&quot;makes&quot;, &quot;coding&quot;]]
<strong>Output</strong>
[null, 3, 1]

<strong>Explanation</strong>
WordDistance wordDistance = new WordDistance([&quot;practice&quot;, &quot;makes&quot;, &quot;perfect&quot;, &quot;coding&quot;, &quot;makes&quot;]);
wordDistance.shortest(&quot;coding&quot;, &quot;practice&quot;); // return 3
wordDistance.shortest(&quot;makes&quot;, &quot;coding&quot;);    // return 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordsDict.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsDict[i].length &lt;= 10</code></li>
	<li><code>wordsDict[i]</code> consists of lowercase English letters.</li>
	<li><code>word1</code> and <code>word2</code> are in <code>wordsDict</code>.</li>
	<li><code>word1 != word2</code></li>
	<li>At most <code>5000</code> calls will be made to <code>shortest</code>.</li>
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
