# [1021. Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses)

[中文文档](/solution/1000-1099/1021.Remove%20Outermost%20Parentheses/README.md)

## Description

<p>A valid parentheses string is either empty <code>(&quot;&quot;)</code>, <code>&quot;(&quot; + A + &quot;)&quot;</code>, or <code>A + B</code>, where <code>A</code> and <code>B</code> are valid parentheses strings, and <code>+</code> represents string concatenation.&nbsp; For example, <code>&quot;&quot;</code>, <code>&quot;()&quot;</code>, <code>&quot;(())()&quot;</code>, and <code>&quot;(()(()))&quot;</code> are all valid parentheses strings.</p>

<p>A valid parentheses string <code>S</code> is <strong>primitive</strong> if it is nonempty, and there does not exist a way to split it into <code>S = A+B</code>, with <code>A</code> and <code>B</code> nonempty valid parentheses strings.</p>

<p>Given a valid parentheses string <code>S</code>, consider its primitive decomposition: <code>S = P_1 + P_2 + ... + P_k</code>, where <code>P_i</code> are primitive valid parentheses strings.</p>

<p>Return <code>S</code> after removing the outermost parentheses of every primitive string in the primitive decomposition of <code>S</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;(()())(())&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;()()()&quot;</span>

<strong>Explanation: </strong>

The input string is &quot;(()())(())&quot;, with primitive decomposition &quot;(()())&quot; + &quot;(())&quot;.

After removing outer parentheses of each part, this is &quot;()()&quot; + &quot;()&quot; = &quot;()()()&quot;.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;(()())(())(()(()))&quot;</span>

<strong>Output: </strong><span id="example-output-2">&quot;()()()()(())&quot;</span>

<strong>Explanation: </strong>

The input string is &quot;(()())(())(()(()))&quot;, with primitive decomposition &quot;(()())&quot; + &quot;(())&quot; + &quot;(()(()))&quot;.

After removing outer parentheses of each part, this is &quot;()()&quot; + &quot;()&quot; + &quot;()(())&quot; = &quot;()()()()(())&quot;.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;()()&quot;</span>

<strong>Output: </strong><span id="example-output-3">&quot;&quot;</span>

<strong>Explanation: </strong>

The input string is &quot;()()&quot;, with primitive decomposition &quot;()&quot; + &quot;()&quot;.

After removing outer parentheses of each part, this is &quot;&quot; + &quot;&quot; = &quot;&quot;.

</pre>

<p>&nbsp;</p>

</div>

</div>

<p><strong>Note:</strong></p>

<ol>
	<li><code>S.length &lt;= 10000</code></li>
	<li><code>S[i]</code> is <code>&quot;(&quot;</code> or <code>&quot;)&quot;</code></li>
	<li><code>S</code> is a valid parentheses string</li>
</ol>

<div>

<div>

<div>&nbsp;</div>

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
