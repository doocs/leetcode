# [929. Unique Email Addresses](https://leetcode.com/problems/unique-email-addresses)

## Description
<p>Every email consists of a local name and a domain name, separated by the @ sign.</p>



<p>For example, in <code>alice@leetcode.com</code>,&nbsp;<code>alice</code> is the local name, and <code>leetcode.com</code> is the domain name.</p>



<p>Besides lowercase letters, these emails may contain <code>&#39;.&#39;</code>s or <code>&#39;+&#39;</code>s.</p>



<p>If you add periods (<code>&#39;.&#39;</code>) between some characters in the <strong>local name</strong> part of an email address, mail sent there will be forwarded to the same address without dots in the local name.&nbsp; For example, <code>&quot;alice.z@leetcode.com&quot;</code> and <code>&quot;alicez@leetcode.com&quot;</code> forward to the same email address.&nbsp; (Note that this rule does not apply for domain names.)</p>



<p>If you add a plus (<code>&#39;+&#39;</code>) in the <strong>local name</strong>, everything after the first plus sign will be&nbsp;<strong>ignored</strong>. This allows certain emails to be filtered, for example&nbsp;<code>m.y+name@email.com</code>&nbsp;will be forwarded to&nbsp;<code>my@email.com</code>.&nbsp; (Again, this rule does not apply for domain names.)</p>



<p>It is possible to use both of these rules at the same time.</p>



<p>Given a list of <code>emails</code>, we send one email to each address in the list.&nbsp;&nbsp;How many different addresses actually receive mails?&nbsp;</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">[&quot;test.email+alex@leetcode.com&quot;,&quot;test.e.mail+bob.cathy@leetcode.com&quot;,&quot;testemail+david@lee.tcode.com&quot;]</span>

<strong>Output: </strong><span id="example-output-1">2</span>

<strong><span>Explanation:</span></strong><span>&nbsp;&quot;</span><span id="example-input-1-1">testemail@leetcode.com&quot; and &quot;testemail@lee.tcode.com&quot; </span>actually receive mails

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ul>

	<li><code>1 &lt;= emails[i].length&nbsp;&lt;= 100</code></li>

	<li><code>1 &lt;= emails.length &lt;= 100</code></li>

	<li>Each <code>emails[i]</code> contains exactly one <code>&#39;@&#39;</code> character.</li>

	<li>All local and domain names are non-empty.</li>

	<li>Local names do not start with a <code>&#39;+&#39;</code> character.</li>

</ul>

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