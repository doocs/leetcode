# [158. Read N Characters Given Read4 II - Call multiple times](https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times)

[中文文档](/solution/0100-0199/0158.Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times/README.md)

## Description

<p>Given a file and assume that you can only read the file using a given method&nbsp;<code>read4</code>, implement a method <code>read</code> to read <em>n</em> characters. <strong>Your method <code>read</code> may be called multiple times.</strong></p>

<p>&nbsp;</p>

<p><b>Method read4: </b></p>

<p>The API&nbsp;<code>read4</code> reads 4 consecutive characters from the file, then writes those characters into the buffer array <code>buf</code>.</p>

<p>The return value is the number of actual characters read.</p>

<p>Note that&nbsp;<code>read4()</code> has its own file pointer, much like <code>FILE *fp</code> in C.</p>

<p><b>Definition of read4:</b></p>

<pre>
    Parameter:  char[] buf4
    Returns:    int

Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
</pre>

<p>Below is a high level example of how <code>read4</code> works:</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/07/01/157_example.png" style="width: 600px; height: 403px;" /></p>

<pre>
<code>File file(&quot;abcde&quot;); // File is &quot;abcde&quot;, initially file pointer (fp) points to &#39;a&#39;
char[] buf = new char[4]; // Create buffer with enough space to store characters
read4(buf4); // read4 returns 4. Now buf = &quot;abcd&quot;, fp points to &#39;e&#39;
read4(buf4); // read4 returns 1. Now buf = &quot;e&quot;, fp points to end of file
read4(buf4); // read4 returns 0. Now buf = &quot;&quot;, fp points to end of file</code>
</pre>

<p>&nbsp;</p>

<p><strong>Method read:</strong></p>

<p>By using the <code>read4</code> method, implement the method&nbsp;<code>read</code> that reads <i>n</i> characters from the file and store it in the&nbsp;buffer array&nbsp;<code>buf</code>. Consider that you <strong>cannot</strong> manipulate the file directly.</p>

<p>The return value is the number of actual characters read.</p>

<p><b>Definition of read: </b></p>

<pre>
    Parameters:	char[] buf, int n
    Returns:	int

Note: buf[] is destination not source, you will need to write the results to buf[]
</pre>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
File file(&quot;abc&quot;);
Solution sol;
// Assume buf is allocated and guaranteed to have enough space for storing all characters from the file.
sol.read(buf, 1); // After calling your read method, buf should contain &quot;a&quot;. We read a total of 1 character from the file, so return 1.
sol.read(buf, 2); // Now buf should contain &quot;bc&quot;. We read a total of 2 characters from the file, so return 2.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 4); // After calling your read method, buf should contain &quot;abc&quot;. We read a total of 3 characters from the file, so return 3.
sol.read(buf, 1); // We have reached the end of file, no more characters can be read. So return 0.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Consider that you <strong>cannot</strong> manipulate the file directly, the file is only accesible for <code>read4</code> but&nbsp;<strong>not</strong> for <code>read</code>.</li>
	<li>The <code>read</code> function may be called <strong>multiple times</strong>.</li>
	<li>Please remember to <b>RESET</b> your class variables declared in Solution, as static/class variables are <b>persisted across multiple test cases</b>. Please see <a href="https://leetcode.com/faq/" target="_blank">here</a> for more details.</li>
	<li>You may assume the destination buffer array,&nbsp;<code>buf</code>,&nbsp;is guaranteed to have enough&nbsp;space for storing&nbsp;<em>n</em>&nbsp;characters.</li>
	<li>It is guaranteed that in a given test case the same buffer <code>buf</code> is called by <code>read</code>.</li>
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
