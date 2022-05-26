# [158. 用 Read4 读取 N 个字符 II](https://leetcode-cn.com/problems/read-n-characters-given-read4-ii-call-multiple-times)

[English Version](/solution/0100-0199/0158.Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个文件，并且该文件只能通过给定的&nbsp;<code>read4</code>&nbsp;方法来读取，请实现一个方法使其能够读取 n 个字符。<strong>注意：你的</strong>&nbsp;<strong><code>read</code> 方法可能会被调用多次。</strong></p>

<p><strong>read4 的定义：</strong></p>

<pre>参数类型: char[] buf
返回类型: int

注意: buf[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf[] 当中。
</pre>

<p>下列是一些使用 <code>read4</code> 的例子：</p>

<pre><code>File file(&quot;abcdefghijk&quot;); // 文件名为 &quot;abcdefghijk&quot;， 初始文件指针 (fp) 指向 &#39;a&#39; 
char[] buf = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf); // read4 返回 4。现在 buf = &quot;abcd&quot;，fp 指向 &#39;e&#39;
read4(buf); // read4 返回 4。现在 buf = &quot;efgh&quot;，fp 指向 &#39;i&#39;
read4(buf); // read4 返回 3。现在 buf = &quot;ijk&quot;，fp 指向文件末尾</code></pre>

<p><strong>read 方法：</strong></p>

<p>通过使用 <code>read4</code> 方法，实现&nbsp;<code>read</code> 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组&nbsp;<code>buf</code> 中。您&nbsp;<strong>不能&nbsp;</strong>直接操作文件。</p>

<p>返回值为实际读取的字符。</p>

<p><strong>read&nbsp;的定义：</strong></p>

<pre>参数:   char[] buf, int n
返回值: int

注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
</pre>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>File file(&quot;abc&quot;);
Solution sol;
// 假定 buf 已经被分配了内存，并且有足够的空间来存储文件中的所有字符。
sol.read(buf, 1); // 当调用了您的 read 方法后，buf 需要包含 &quot;a&quot;。 一共读取 1 个字符，因此返回 1。
sol.read(buf, 2); // 现在 buf 需要包含 &quot;bc&quot;。一共读取 2 个字符，因此返回 2。
sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
</pre>

<p><strong>Example 2:</strong></p>

<pre>File file(&quot;abc&quot;);
Solution sol;
sol.read(buf, 4); // 当调用了您的 read 方法后，buf 需要包含 &quot;abc&quot;。 一共只能读取 3 个字符，因此返回 3。
sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
</pre>

<p><strong>注意：</strong></p>

<ol>
	<li>你 <strong>不能</strong> 直接操作该文件，文件只能通过 <code>read4</code> 获取而 <strong>不能</strong> 通过 <code>read</code>。</li>
	<li><code>read</code>&nbsp; 函数可以被调用&nbsp;<strong>多次</strong>。</li>
	<li>请记得&nbsp;<strong>重置&nbsp;</strong>在 Solution 中声明的类变量（静态变量），因为类变量会&nbsp;<strong>在多个测试用例中保持不变</strong>，影响判题准确。请 <a href="https://support.leetcode-cn.com/hc/kb/section/1071534/" target="_blank">查阅</a> 这里。</li>
	<li>你可以假定目标缓存数组&nbsp;<code>buf</code> 保证有足够的空间存下 n 个字符。&nbsp;</li>
	<li>保证在一个给定测试用例中，<code>read</code> 函数使用的是同一个 <code>buf</code>。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
