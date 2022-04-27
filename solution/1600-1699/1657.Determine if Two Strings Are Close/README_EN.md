# [1657. Determine if Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close)

[中文文档](/solution/1600-1699/1657.Determine%20if%20Two%20Strings%20Are%20Close/README.md)

## Description

<p>Two strings are considered <strong>close</strong> if you can attain one from the other using the following operations:</p>

<ul>
	<li>Operation 1: Swap any two <strong>existing</strong> characters.

    <ul>
    	<li>For example, <code>a<u>b</u>cd<u>e</u> -&gt; a<u>e</u>cd<u>b</u></code></li>
    </ul>
    </li>
    <li>Operation 2: Transform <strong>every</strong> occurrence of one <strong>existing</strong> character into another <strong>existing</strong> character, and do the same with the other character.
    <ul>
    	<li>For example, <code><u>aa</u>c<u>abb</u> -&gt; <u>bb</u>c<u>baa</u></code> (all <code>a</code>&#39;s turn into <code>b</code>&#39;s, and all <code>b</code>&#39;s turn into <code>a</code>&#39;s)</li>
    </ul>
    </li>

</ul>

<p>You can use the operations on either string as many times as necessary.</p>

<p>Given two strings, <code>word1</code> and <code>word2</code>, return <code>true</code><em> if </em><code>word1</code><em> and </em><code>word2</code><em> are <strong>close</strong>, and </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abc&quot;, word2 = &quot;bca&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can attain word2 from word1 in 2 operations.
Apply Operation 1: &quot;a<u>bc</u>&quot; -&gt; &quot;a<u>cb</u>&quot;
Apply Operation 1: &quot;<u>a</u>c<u>b</u>&quot; -&gt; &quot;<u>b</u>c<u>a</u>&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;a&quot;, word2 = &quot;aa&quot;
<strong>Output:</strong> false
<strong>Explanation: </strong>It is impossible to attain word2 from word1, or vice versa, in any number of operations.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;cabbba&quot;, word2 = &quot;abbccc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can attain word2 from word1 in 3 operations.
Apply Operation 1: &quot;ca<u>b</u>bb<u>a</u>&quot; -&gt; &quot;ca<u>a</u>bb<u>b</u>&quot;
<code>Apply Operation 2: &quot;</code><u>c</u>aa<u>bbb</u>&quot; -&gt; &quot;<u>b</u>aa<u>ccc</u>&quot;
Apply Operation 2: &quot;<u>baa</u>ccc&quot; -&gt; &quot;<u>abb</u>ccc&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> and <code>word2</code> contain&nbsp;only lowercase English letters.</li>
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
