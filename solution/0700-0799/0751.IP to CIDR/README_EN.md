# [751. IP to CIDR](https://leetcode.com/problems/ip-to-cidr)

[中文文档](/solution/0700-0799/0751.IP%20to%20CIDR/README.md)

## Description

<p>An <strong>IP address</strong> is a formatted 32-bit unsigned integer where each group of 8 bits is printed as a decimal number and the dot character <code>&#39;.&#39;</code> splits the groups.</p>

<ul>
	<li>For example, the binary number <code>00001111 10001000 11111111 01101011</code> (spaces added for clarity) formatted as an IP address would be <code>&quot;15.136.255.107&quot;</code>.</li>
</ul>

<p>A <strong>CIDR block</strong> is a format used to denote a specific set of IP addresses. It is a string consisting of a base IP address, followed by a slash, followed by a prefix length <code>k</code>. The addresses it covers are all the IPs whose <strong>first <code>k</code> bits</strong> are the same as the base IP address.</p>

<ul>
	<li>For example, <code>&quot;123.45.67.89/20&quot;</code> is a CIDR block with a prefix length of <code>20</code>. Any IP address whose binary representation matches <code>01111011 00101101 0100xxxx xxxxxxxx</code>, where <code>x</code> can be either <code>0</code> or <code>1</code>, is in the set covered by the CIDR block.</li>
</ul>

<p>You are given a start IP address <code>ip</code> and the number of IP addresses we need to cover <code>n</code>. Your goal is to use <strong>as few CIDR blocks as possible</strong> to cover all the IP addresses in the <strong>inclusive</strong> range <code>[ip, ip + n - 1]</code> <strong>exactly</strong>. No other IP addresses outside of the range should be covered.</p>

<p>Return <em>the <strong>shortest</strong> list of <strong>CIDR blocks</strong> that covers the range of IP addresses. If there are multiple answers, return <strong>any</strong> of them</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> ip = &quot;255.0.0.7&quot;, n = 10
<strong>Output:</strong> [&quot;255.0.0.7/32&quot;,&quot;255.0.0.8/29&quot;,&quot;255.0.0.16/32&quot;]
<strong>Explanation:</strong>
The IP addresses that need to be covered are:
- 255.0.0.7  -&gt; 11111111 00000000 00000000 00000111
- 255.0.0.8  -&gt; 11111111 00000000 00000000 00001000
- 255.0.0.9  -&gt; 11111111 00000000 00000000 00001001
- 255.0.0.10 -&gt; 11111111 00000000 00000000 00001010
- 255.0.0.11 -&gt; 11111111 00000000 00000000 00001011
- 255.0.0.12 -&gt; 11111111 00000000 00000000 00001100
- 255.0.0.13 -&gt; 11111111 00000000 00000000 00001101
- 255.0.0.14 -&gt; 11111111 00000000 00000000 00001110
- 255.0.0.15 -&gt; 11111111 00000000 00000000 00001111
- 255.0.0.16 -&gt; 11111111 00000000 00000000 00010000
The CIDR block &quot;255.0.0.7/32&quot; covers the first address.
The CIDR block &quot;255.0.0.8/29&quot; covers the middle 8 addresses (binary format of 11111111 00000000 00000000 00001xxx).
The CIDR block &quot;255.0.0.16/32&quot; covers the last address.
Note that while the CIDR block &quot;255.0.0.0/28&quot; does cover all the addresses, it also includes addresses outside of the range, so we cannot use it.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> ip = &quot;117.145.102.62&quot;, n = 8
<strong>Output:</strong> [&quot;117.145.102.62/31&quot;,&quot;117.145.102.64/30&quot;,&quot;117.145.102.68/31&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>7 &lt;= ip.length &lt;= 15</code></li>
	<li><code>ip</code> is a valid <strong>IPv4</strong> on the form <code>&quot;a.b.c.d&quot;</code> where <code>a</code>, <code>b</code>, <code>c</code>, and <code>d</code> are integers in the range <code>[0, 255]</code>.</li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li>Every implied address <code>ip + x</code> (for <code>x &lt; n</code>) will be a valid IPv4 address.</li>
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
