# [929. Unique Email Addresses](https://leetcode.com/problems/unique-email-addresses)

[中文文档](/solution/0900-0999/0929.Unique%20Email%20Addresses/README.md)

## Description

<p>Every <strong>valid email</strong> consists of a <strong>local name</strong> and a <strong>domain name</strong>, separated by the <code>&#39;@&#39;</code> sign. Besides lowercase letters, the email may contain one or more <code>&#39;.&#39;</code> or <code>&#39;+&#39;</code>.</p>

<ul>
	<li>For example, in <code>&quot;alice@leetcode.com&quot;</code>, <code>&quot;alice&quot;</code> is the <strong>local name</strong>, and <code>&quot;leetcode.com&quot;</code> is the <strong>domain name</strong>.</li>
</ul>

<p>If you add periods <code>&#39;.&#39;</code> between some characters in the <strong>local name</strong> part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule <strong>does not apply</strong> to <strong>domain names</strong>.</p>

<ul>
	<li>For example, <code>&quot;alice.z@leetcode.com&quot;</code> and <code>&quot;alicez@leetcode.com&quot;</code> forward to the same email address.</li>
</ul>

<p>If you add a plus <code>&#39;+&#39;</code> in the <strong>local name</strong>, everything after the first plus sign <strong>will be ignored</strong>. This allows certain emails to be filtered. Note that this rule <strong>does not apply</strong> to <strong>domain names</strong>.</p>

<ul>
	<li>For example, <code>&quot;m.y+name@email.com&quot;</code> will be forwarded to <code>&quot;my@email.com&quot;</code>.</li>
</ul>

<p>It is possible to use both of these rules at the same time.</p>

<p>Given an array of strings <code>emails</code> where we send one email to each <code>email[i]</code>, return <em>the number of different addresses that actually receive mails</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> emails = [&quot;test.email+alex@leetcode.com&quot;,&quot;test.e.mail+bob.cathy@leetcode.com&quot;,&quot;testemail+david@lee.tcode.com&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> &quot;testemail@leetcode.com&quot; and &quot;testemail@lee.tcode.com&quot; actually receive mails.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> emails = [&quot;a@leetcode.com&quot;,&quot;b@leetcode.com&quot;,&quot;c@leetcode.com&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= emails.length &lt;= 100</code></li>
	<li><code>1 &lt;= emails[i].length &lt;= 100</code></li>
	<li><code>email[i]</code> consist of lowercase English letters, <code>&#39;+&#39;</code>, <code>&#39;.&#39;</code> and <code>&#39;@&#39;</code>.</li>
	<li>Each <code>emails[i]</code> contains exactly one <code>&#39;@&#39;</code> character.</li>
	<li>All local and domain names are non-empty.</li>
	<li>Local names do not start with a <code>&#39;+&#39;</code> character.</li>
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
