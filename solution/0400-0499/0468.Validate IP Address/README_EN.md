# [468. Validate IP Address](https://leetcode.com/problems/validate-ip-address)

## Description
<p>

Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

</p>



<p>

<b>IPv4</b> addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,<code>172.16.254.1</code>;

</p>



<p>

Besides, leading zeros in the IPv4 is invalid. For example, the address <code>172.16.254.01</code> is invalid.

</p>



<p>

<b>IPv6</b> addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address <code>2001:0db8:85a3:0000:0000:8a2e:0370:7334</code> is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so <code>2001:db8:85a3:0:0:8A2E:0370:7334</code> is also a valid IPv6 address(Omit leading zeros and using upper cases).

</p>





<p>

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, <code>2001:0db8:85a3::8A2E:0370:7334</code> is an invalid IPv6 address.

</p>



<p>

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address <code>02001:0db8:85a3:0000:0000:8a2e:0370:7334</code> is invalid.

</p>





<p><b>Note:</b>

You may assume there is no extra space or special characters in the input string.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> "172.16.254.1"



<b>Output:</b> "IPv4"



<b>Explanation:</b> This is a valid IPv4 address, return "IPv4".

</pre>

</p>





<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> "2001:0db8:85a3:0:0:8A2E:0370:7334"



<b>Output:</b> "IPv6"



<b>Explanation:</b> This is a valid IPv6 address, return "IPv6".

</pre>

</p>



<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> "256.256.256.256"



<b>Output:</b> "Neither"



<b>Explanation:</b> This is neither a IPv4 address nor a IPv6 address.

</pre>

</p>


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