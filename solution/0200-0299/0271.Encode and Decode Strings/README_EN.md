# [271. Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings)

[中文文档](/solution/0200-0299/0271.Encode%20and%20Decode%20Strings/README.md)

## Description
<p>Design an algorithm to encode <b>a list of strings</b> to <b>a string</b>. The encoded string is then sent over the network and is decoded back to the original list of strings.</p>

<p>Machine 1 (sender) has the function:</p>

<pre>
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}</pre>
Machine 2 (receiver) has the function:

<pre>
vector<string> decode(string s) {
  //... your code
  return strs;
}
</pre>

<p>So Machine 1 does:</p>

<pre>
string encoded_string = encode(strs);
</pre>

<p>and Machine 2 does:</p>

<pre>
vector<string> strs2 = decode(encoded_string);
</pre>

<p><code>strs2</code> in Machine 2 should be the same as <code>strs</code> in Machine 1.</p>

<p>Implement the <code>encode</code> and <code>decode</code> methods.</p>

<p> </p>

<p><b>Note:</b></p>

<ul>
	<li>The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.</li>
	<li>Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.</li>
	<li>Do not rely on any library method such as <code>eval</code> or serialize methods. You should implement your own encode/decode algorithm.</li>
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