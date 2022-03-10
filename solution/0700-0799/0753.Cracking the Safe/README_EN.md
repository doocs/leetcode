# [753. Cracking the Safe](https://leetcode.com/problems/cracking-the-safe)

[中文文档](/solution/0700-0799/0753.Cracking%20the%20Safe/README.md)

## Description

<p>There is a safe protected by a password. The password is a sequence of <code>n</code> digits where each digit can be in the range <code>[0, k - 1]</code>.</p>

<p>The safe has a peculiar way of checking the password. When you enter in a sequence, it checks the <strong>most recent </strong><code>n</code><strong> digits</strong> that were entered each time you type a digit.</p>

<ul>
	<li>For example, the correct password is <code>&quot;345&quot;</code> and you enter in <code>&quot;012345&quot;</code>:
    <ul>
    	<li>After typing <code>0</code>, the most recent <code>3</code> digits is <code>&quot;0&quot;</code>, which is incorrect.</li>
    	<li>After typing <code>1</code>, the most recent <code>3</code> digits is <code>&quot;01&quot;</code>, which is incorrect.</li>
    	<li>After typing <code>2</code>, the most recent <code>3</code> digits is <code>&quot;012&quot;</code>, which is incorrect.</li>
    	<li>After typing <code>3</code>, the most recent <code>3</code> digits is <code>&quot;123&quot;</code>, which is incorrect.</li>
    	<li>After typing <code>4</code>, the most recent <code>3</code> digits is <code>&quot;234&quot;</code>, which is incorrect.</li>
    	<li>After typing <code>5</code>, the most recent <code>3</code> digits is <code>&quot;345&quot;</code>, which is correct and the safe unlocks.</li>
    </ul>
    </li>

</ul>

<p>Return <em>any string of <strong>minimum length</strong> that will unlock the safe <strong>at some point</strong> of entering it</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 2
<strong>Output:</strong> &quot;10&quot;
<strong>Explanation:</strong> The password is a single digit, so enter each digit. &quot;01&quot; would also unlock the safe.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 2
<strong>Output:</strong> &quot;01100&quot;
<strong>Explanation:</strong> For each possible password:
- &quot;00&quot; is typed in starting from the 4<sup>th</sup> digit.
- &quot;01&quot; is typed in starting from the 1<sup>st</sup> digit.
- &quot;10&quot; is typed in starting from the 3<sup>rd</sup> digit.
- &quot;11&quot; is typed in starting from the 2<sup>nd</sup> digit.
Thus &quot;01100&quot; will unlock the safe. &quot;01100&quot;, &quot;10011&quot;, and &quot;11001&quot; would also unlock the safe.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>1 &lt;= k<sup>n</sup> &lt;= 4096</code></li>
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
