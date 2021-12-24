# [831. Masking Personal Information](https://leetcode.com/problems/masking-personal-information)

[中文文档](/solution/0800-0899/0831.Masking%20Personal%20Information/README.md)

## Description

<p>We are given a&nbsp;personal information string <code>S</code>, which may represent&nbsp;either <strong>an email address</strong> or <strong>a phone number.</strong></p>

<p>We would like to mask this&nbsp;personal information according to the&nbsp;following rules:</p>

<p><br />

<u><strong>1. Email address:</strong></u></p>

<p>We define a&nbsp;<strong>name</strong> to be a string of <code>length &ge; 2</code> consisting&nbsp;of only lowercase letters&nbsp;<code>a-z</code> or uppercase&nbsp;letters&nbsp;<code>A-Z</code>.</p>

<p>An email address starts with a name, followed by the&nbsp;symbol <code>&#39;@&#39;</code>, followed by a name, followed by the&nbsp;dot&nbsp;<code>&#39;.&#39;</code>&nbsp;and&nbsp;followed by a name.&nbsp;</p>

<p>All email addresses are&nbsp;guaranteed to be valid and in the format of&nbsp;<code>&quot;name1@name2.name3&quot;.</code></p>

<p>To mask an email, <strong>all names must be converted to lowercase</strong> and <strong>all letters between the first and last letter of the first name</strong> must be replaced by 5 asterisks <code>&#39;*&#39;</code>.</p>

<p><br />

<u><strong>2. Phone number:</strong></u></p>

<p>A phone number is a string consisting of&nbsp;only the digits <code>0-9</code> or the characters from the set <code>{&#39;+&#39;, &#39;-&#39;, &#39;(&#39;, &#39;)&#39;, &#39;&nbsp;&#39;}.</code>&nbsp;You may assume a phone&nbsp;number contains&nbsp;10 to 13 digits.</p>

<p>The last 10 digits make up the local&nbsp;number, while the digits before those make up the country code. Note that&nbsp;the country code is optional. We want to expose only the last 4 digits&nbsp;and mask all other&nbsp;digits.</p>

<p>The local&nbsp;number&nbsp;should be formatted and masked as <code>&quot;***-***-1111&quot;,&nbsp;</code>where <code>1</code> represents the exposed digits.</p>

<p>To mask a phone number with country code like <code>&quot;+111 111 111 1111&quot;</code>, we write it in the form <code>&quot;+***-***-***-1111&quot;.</code>&nbsp; The <code>&#39;+&#39;</code>&nbsp;sign and the first <code>&#39;-&#39;</code>&nbsp;sign before the local number should only exist if there is a country code.&nbsp; For example, a 12 digit phone number mask&nbsp;should start&nbsp;with <code>&quot;+**-&quot;</code>.</p>

<p>Note that extraneous characters like <code>&quot;(&quot;, &quot;)&quot;, &quot; &quot;</code>, as well as&nbsp;extra dashes or plus signs not part of the above formatting scheme should be removed.</p>

<p>&nbsp;</p>

<p>Return the correct &quot;mask&quot; of the information provided.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;LeetCode@LeetCode.com&quot;

<strong>Output: </strong>&quot;l*****e@leetcode.com&quot;

<strong>Explanation:&nbsp;</strong>All names are converted to lowercase, and the letters between the

&nbsp;            first and last letter of the first name is replaced by 5 asterisks.

&nbsp;            Therefore, &quot;leetcode&quot; -&gt; &quot;l*****e&quot;.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>&quot;AB@qq.com&quot;

<strong>Output: </strong>&quot;a*****b@qq.com&quot;

<strong>Explanation:&nbsp;</strong>There must be 5 asterisks between the first and last letter 

&nbsp;            of the first name &quot;ab&quot;. Therefore, &quot;ab&quot; -&gt; &quot;a*****b&quot;.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>&quot;1(234)567-890&quot;

<strong>Output: </strong>&quot;***-***-7890&quot;

<strong>Explanation:</strong>&nbsp;10 digits in the phone number, which means all digits make up the local number.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: </strong>&quot;86-(10)12345678&quot;

<strong>Output: </strong>&quot;+**-***-***-5678&quot;

<strong>Explanation:</strong>&nbsp;12 digits, 2 digits for country code and 10 digits for local number. 

</pre>

<p><strong>Notes:</strong></p>

<ol>
	<li><code>S.length&nbsp;&lt;=&nbsp;40</code>.</li>
	<li>Emails have length at least 8.</li>
	<li>Phone numbers have length at least 10.</li>
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
