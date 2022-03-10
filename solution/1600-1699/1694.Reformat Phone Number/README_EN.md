# [1694. Reformat Phone Number](https://leetcode.com/problems/reformat-phone-number)

[中文文档](/solution/1600-1699/1694.Reformat%20Phone%20Number/README.md)

## Description

<p>You are given a phone number as a string <code>number</code>. <code>number</code> consists of digits, spaces <code>&#39; &#39;</code>, and/or dashes <code>&#39;-&#39;</code>.</p>

<p>You would like to reformat the phone number in a certain manner. Firstly, <strong>remove</strong> all spaces and dashes. Then, <strong>group</strong> the digits from left to right into blocks of length 3 <strong>until</strong> there are 4 or fewer digits. The final digits are then grouped as follows:</p>

<ul>
	<li>2 digits: A single block of length 2.</li>
	<li>3 digits: A single block of length 3.</li>
	<li>4 digits: Two blocks of length 2 each.</li>
</ul>

<p>The blocks are then joined by dashes. Notice that the reformatting process should <strong>never</strong> produce any blocks of length 1 and produce <strong>at most</strong> two blocks of length 2.</p>

<p>Return <em>the phone number after formatting.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;1-23-45 6&quot;
<strong>Output:</strong> &quot;123-456&quot;
<strong>Explanation:</strong> The digits are &quot;123456&quot;.
Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is &quot;123&quot;.
Step 2: There are 3 digits remaining, so put them in a single block of length 3. The 2nd block is &quot;456&quot;.
Joining the blocks gives &quot;123-456&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;123 4-567&quot;
<strong>Output:</strong> &quot;123-45-67&quot;
<strong>Explanation: </strong>The digits are &quot;1234567&quot;.
Step 1: There are more than 4 digits, so group the next 3 digits. The 1st block is &quot;123&quot;.
Step 2: There are 4 digits left, so split them into two blocks of length 2. The blocks are &quot;45&quot; and &quot;67&quot;.
Joining the blocks gives &quot;123-45-67&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> number = &quot;123 4-5678&quot;
<strong>Output:</strong> &quot;123-456-78&quot;
<strong>Explanation:</strong> The digits are &quot;12345678&quot;.
Step 1: The 1st block is &quot;123&quot;.
Step 2: The 2nd block is &quot;456&quot;.
Step 3: There are 2 digits left, so put them in a single block of length 2. The 3rd block is &quot;78&quot;.
Joining the blocks gives &quot;123-456-78&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> consists of digits and the characters <code>&#39;-&#39;</code> and <code>&#39; &#39;</code>.</li>
	<li>There are at least <strong>two</strong> digits in <code>number</code>.</li>
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
