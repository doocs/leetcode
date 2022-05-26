# [393. UTF-8 Validation](https://leetcode.com/problems/utf-8-validation)

[中文文档](/solution/0300-0399/0393.UTF-8%20Validation/README.md)

## Description

<p>A character in UTF8 can be from <b>1 to 4 bytes</b> long, subjected to the following rules:</p>

<ol>

<li>For 1-byte character, the first bit is a 0, followed by its unicode code.</li>

<li>For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.</li>

</ol>

<p>This is how the UTF-8 encoding would work:</p>

<pre><code>   Char. number range  |        UTF-8 octet sequence

      (hexadecimal)    |              (binary)

   --------------------+---------------------------------------------

   0000 0000-0000 007F | 0xxxxxxx

   0000 0080-0000 07FF | 110xxxxx 10xxxxxx

   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx

   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

</code></pre>

<p>

Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

</p>

<p>

<b>Note:</b><br />

The input is an array of integers. Only the <b>least significant 8 bits</b> of each integer is used to store the data. This means each integer represents only 1 byte of data.

</p>

<p>

<b>Example 1:</b>

<pre>

data = [197, 130, 1], which represents the octet sequence: <b>11000101 10000010 00000001</b>.



Return <b>true</b>.

It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.

</pre>

</p>

<p>

<b>Example 2:</b>

<pre>

data = [235, 140, 4], which represented the octet sequence: <b>11101011 10001100 00000100</b>.



Return <b>false</b>.

The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.

The next byte is a continuation byte which starts with 10 and that's correct.

But the second continuation byte does not start with 10, so it is invalid.

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
