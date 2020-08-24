# [1061. Lexicographically Smallest Equivalent String](https://leetcode.com/problems/lexicographically-smallest-equivalent-string)

[中文文档](/solution/1000-1099/1061.Lexicographically%20Smallest%20Equivalent%20String/README.md)

## Description
<p>Given strings <code>A</code> and <code>B</code> of the same length, we say A[i] and B[i] are equivalent characters. For example, if <code>A = "abc"</code> and <code>B = "cde"</code>, then we have <code>'a' == 'c', 'b' == 'd', 'c' == 'e'</code>.</p>

<p>Equivalent characters follow the usual rules of any equivalence relation:</p>

<ul>
	<li>Reflexivity: 'a' == 'a'</li>
	<li>Symmetry: 'a' == 'b' implies 'b' == 'a'</li>
	<li>Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'</li>
</ul>

<p>For example, given the equivalency information from <code>A</code> and <code>B</code> above, <code>S = "eed"</code>, <code>"acd"</code>, and <code>"aab"</code> are equivalent strings, and <code>"aab"</code> is the lexicographically smallest equivalent string of <code>S</code>.</p>

<p>Return the lexicographically smallest equivalent string of <code>S</code> by using the equivalency information from <code>A</code> and <code>B</code>.</p>

<p> </p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>A = <span id="example-input-1-1">"parker"</span>, B = <span id="example-input-1-2">"morris"</span>, S = <span id="example-input-1-3">"parser"</span>
<strong>Output: </strong><span id="example-output-1">"makkek"</span>
<strong>Explanation:</strong> Based on the equivalency information in <code>A</code> and <code>B</code>, we can group their characters as <code>[m,p]</code>, <code>[a,o]</code>, <code>[k,r,s]</code>, <code>[e,i]</code>. The characters in each group are equivalent and sorted in lexicographical order. So the answer is <code>"makkek"</code>.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>A = <span id="example-input-2-1">"hello"</span>, B = <span id="example-input-2-2">"world"</span>, S = <span id="example-input-2-3">"hold"</span>
<strong>Output: </strong><span id="example-output-2">"hdld"</span>
<strong>Explanation: </strong> Based on the equivalency information in <code>A</code> and <code>B</code>, we can group their characters as <code>[h,w]</code>, <code>[d,e,o]</code>, <code>[l,r]</code>. So only the second letter <code>'o'</code> in <code>S</code> is changed to <code>'d'</code>, the answer is <code>"hdld"</code>.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: </strong>A = <span id="example-input-3-1">"leetcode"</span>, B = <span id="example-input-3-2">"programs"</span>, S = <span id="example-input-3-3">"sourcecode"</span>
<strong>Output: </strong><span id="example-output-3">"aauaaaaada"</span>
<strong>Explanation: </strong> We group the equivalent characters in <code>A</code> and <code>B</code> as <code>[a,o,e,r,s,c]</code>, <code>[l,p]</code>, <code>[g,t]</code> and <code>[d,m]</code>, thus all letters in <code>S</code> except <code>'u'</code> and <code>'d'</code> are transformed to <code>'a'</code>, the answer is <code>"aauaaaaada"</code>.
</pre>

<p> </p>

<p><strong>Note:</strong></p>

<ol>
	<li>String <code>A</code>, <code>B</code> and <code>S</code> consist of only lowercase English letters from <code>'a'</code> - <code>'z'</code>.</li>
	<li>The lengths of string <code>A</code>, <code>B</code> and <code>S</code> are between <code>1</code> and <code>1000</code>.</li>
	<li>String <code>A</code> and <code>B</code> are of the same length.</li>
</ol>


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