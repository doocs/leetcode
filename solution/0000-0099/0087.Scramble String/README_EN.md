# [87. Scramble String](https://leetcode.com/problems/scramble-string)

[中文文档](/solution/0000-0099/0087.Scramble%20String/README.md)

## Description

<p>Given a string <em>s1</em>, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.</p>

<p>Below is one possible representation of <em>s1</em> = <code>&quot;great&quot;</code>:</p>

<pre>

    great

   /    \

  gr    eat

 / \    /  \

g   r  e   at

           / \

          a   t

</pre>

<p>To scramble the string, we may choose any non-leaf node and swap its two children.</p>

<p>For example, if we choose the node <code>&quot;gr&quot;</code> and swap its two children, it produces a scrambled string <code>&quot;rgeat&quot;</code>.</p>

<pre>

    rgeat

   /    \

  rg    eat

 / \    /  \

r   g  e   at

           / \

          a   t

</pre>

<p>We say that <code>&quot;rgeat&quot;</code> is a scrambled string of <code>&quot;great&quot;</code>.</p>

<p>Similarly, if we continue to swap the children of nodes <code>&quot;eat&quot;</code> and <code>&quot;at&quot;</code>, it produces a scrambled string <code>&quot;rgtae&quot;</code>.</p>

<pre>

    rgtae

   /    \

  rg    tae

 / \    /  \

r   g  ta  e

       / \

      t   a

</pre>

<p>We say that <code>&quot;rgtae&quot;</code> is a scrambled string of <code>&quot;great&quot;</code>.</p>

<p>Given two strings <em>s1</em> and <em>s2</em> of the same length, determine if <em>s2</em> is a scrambled string of <em>s1</em>.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> s1 = &quot;great&quot;, s2 = &quot;rgeat&quot;

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> s1 = &quot;abcde&quot;, s2 = &quot;caebd&quot;

<strong>Output:</strong> false</pre>

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
