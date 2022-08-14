# [1544. Make The String Great](https://leetcode.com/problems/make-the-string-great)

[中文文档](/solution/1500-1599/1544.Make%20The%20String%20Great/README.md)

## Description

<p>Given a string <code>s</code> of lower and upper case English letters.</p>

<p>A good string is a string which doesn&#39;t have <strong>two adjacent characters</strong> <code>s[i]</code> and <code>s[i + 1]</code> where:</p>

<ul>
	<li><code>0 &lt;= i &lt;= s.length - 2</code></li>
	<li><code>s[i]</code> is a lower-case letter and <code>s[i + 1]</code> is the same letter but in upper-case or <strong>vice-versa</strong>.</li>
</ul>

<p>To make the string good, you can choose <strong>two adjacent</strong> characters that make the string bad and remove them. You can keep doing this until the string becomes good.</p>

<p>Return <em>the string</em> after making it good. The answer is guaranteed to be unique under the given constraints.</p>

<p><strong>Notice</strong> that an empty string is also good.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leEeetcode&quot;
<strong>Output:</strong> &quot;leetcode&quot;
<strong>Explanation:</strong> In the first step, either you choose i = 1 or i = 2, both will result &quot;leEeetcode&quot; to be reduced to &quot;leetcode&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abBAcC&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> We have many possible scenarios, and all lead to the same answer. For example:
&quot;abBAcC&quot; --&gt; &quot;aAcC&quot; --&gt; &quot;cC&quot; --&gt; &quot;&quot;
&quot;abBAcC&quot; --&gt; &quot;abBA&quot; --&gt; &quot;aA&quot; --&gt; &quot;&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;s&quot;
<strong>Output:</strong> &quot;s&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> contains only lower and upper case English letters.</li>
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
