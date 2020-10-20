# [1003. Check If Word Is Valid After Substitutions](https://leetcode.com/problems/check-if-word-is-valid-after-substitutions)

[中文文档](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README.md)

## Description

<p>We are given that the string <code>&quot;abc&quot;</code> is valid.</p>

<p>From any valid string <code>V</code>, we may split&nbsp;<code>V</code> into two pieces <code>X</code> and <code>Y</code> such that <code>X + Y</code> (<code>X</code> concatenated with <code>Y</code>) is equal to <code>V</code>.&nbsp; (<code>X</code> or <code>Y</code> may be empty.)&nbsp; Then, <code>X + &quot;abc&quot; + Y</code> is also valid.</p>

<p>If for example <code>S = &quot;abc&quot;</code>, then examples of valid strings are: <code>&quot;abc&quot;, &quot;aabcbc&quot;, &quot;abcabc&quot;, &quot;abcabcababcc&quot;</code>.&nbsp; Examples of <strong>invalid</strong>&nbsp;strings are: <code>&quot;abccba&quot;</code>, <code>&quot;ab&quot;</code>, <code>&quot;cababc&quot;</code>, <code>&quot;bac&quot;</code>.</p>

<p>Return <code>true</code> if and only if the given string&nbsp;<code>S</code>&nbsp;is valid.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;aabcbc&quot;</span>

<strong>Output: </strong><span id="example-output-1">true</span>

<strong>Explanation: </strong>

We start with the valid string &quot;abc&quot;.

Then we can insert another &quot;abc&quot; between &quot;a&quot; and &quot;bc&quot;, resulting in &quot;a&quot; + &quot;abc&quot; + &quot;bc&quot; which is &quot;aabcbc&quot;.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;abcabcababcc&quot;</span>

<strong>Output: </strong><span id="example-output-2">true</span>

<strong>Explanation: </strong>

&quot;abcabcabc&quot; is valid after consecutive insertings of &quot;abc&quot;.

Then we can insert &quot;abc&quot; before the last letter, resulting in &quot;abcabcab&quot; + &quot;abc&quot; + &quot;c&quot; which is &quot;abcabcababcc&quot;.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;abccba&quot;</span>

<strong>Output: </strong><span id="example-output-3">false</span>

</pre>

<div>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-4-1">&quot;cababc&quot;</span>

<strong>Output: </strong><span id="example-output-4">false</span></pre>

<p>&nbsp;</p>

</div>

</div>

</div>

<p><strong>Note:</strong></p>

<ol>

    <li><code>1 &lt;= S.length &lt;= 20000</code></li>

    <li><code>S[i]</code> is <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, or <code>&#39;c&#39;</code></li>

</ol>

<div>

<div>

<div>

<div>&nbsp;</div>

</div>

</div>

</div>

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
<!-- tabs:end -->
