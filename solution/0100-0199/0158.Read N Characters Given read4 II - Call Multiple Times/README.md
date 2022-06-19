# [158. 用 Read4 读取 N 个字符 II](https://leetcode.cn/problems/read-n-characters-given-read4-ii-call-multiple-times)

[English Version](/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个文件<meta charset="UTF-8" />&nbsp;<code>file</code>&nbsp;，并且该文件只能通过给定的&nbsp;<code>read4</code>&nbsp;方法来读取，请实现一个方法使其能够使&nbsp;<code>read</code> 读取 <code>n</code> 个字符。<strong>注意：你的</strong>&nbsp;<strong><code>read</code> 方法可能会被调用多次。</strong></p>

<p><strong>read4 的定义：</strong></p>

<p><code>read4</code> API 从文件中读取<strong> 4 个连续的字符</strong>，然后将这些字符写入缓冲区数组 <code>buf4</code> 。</p>

<p>返回值是读取的实际字符数。</p>

<p>请注意，<code>read4()</code> 有其自己的文件指针，类似于 C 中的 <code>FILE * fp</code> 。</p>

<pre>
    参数类型: char[] buf4
    返回类型: int

注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
</pre>

<p>下列是一些使用 <code>read4</code> 的例子：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0158.Read%20N%20Characters%20Given%20read4%20II%20-%20Call%20Multiple%20Times/images/157_example.png" style="height: 403px; width: 600px;" /></p>

<pre>
<code>File file("abcde"); // 文件名为 "abcde"， 初始文件指针 (fp) 指向 'a' 
char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf4); // read4 返回 4。现在 buf4 = "abcd"，fp 指向 'e'
read4(buf4); // read4 返回 1。现在 buf4 = "e"，fp 指向文件末尾
read4(buf4); // read4 返回 0。现在 buf4 = ""，fp 指向文件末尾</code></pre>

<p>&nbsp;</p>

<p><strong>read 方法：</strong></p>

<p>通过使用 <code>read4</code> 方法，实现&nbsp;<code>read</code> 方法。该方法可以从文件中读取 <code>n</code> 个字符并将其存储到缓存数组&nbsp;<code>buf</code> 中。您&nbsp;<strong>不能&nbsp;</strong>直接操作&nbsp;<code>file</code> 。</p>

<p>返回值为实际读取的字符。</p>

<p><strong>read&nbsp;的定义：</strong></p>

<pre>
    参数类型:  char[] buf, int n
    返回类型:  int

注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
</pre>

<p><strong>注意：</strong></p>

<ul>
	<li>你 <strong>不能</strong> 直接操作该文件，文件只能通过 <code>read4</code> 获取而 <strong>不能</strong> 通过 <code>read</code>。</li>
	<li><code>read</code>&nbsp; 函数可以被调用&nbsp;<strong>多次</strong>。</li>
	<li>请记得&nbsp;<strong>重置&nbsp;</strong>在 Solution 中声明的类变量（静态变量），因为类变量会&nbsp;<strong>在多个测试用例中保持不变</strong>，影响判题准确。请 <a href="https://support.leetcode.cn/hc/kb/section/1071534/" target="_blank">查阅</a> 这里。</li>
	<li>你可以假定目标缓存数组&nbsp;<code>buf</code> 保证有足够的空间存下 n 个字符。&nbsp;</li>
	<li>保证在一个给定测试用例中，<code>read</code> 函数使用的是同一个 <code>buf</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> file = "abc"， queries = [1,2,1]
<strong>输出：</strong>[1,2,0]
<strong>解释：</strong>测试用例表示以下场景:
File file("abc");
Solution sol;
sol.read (buf, 1); // 调用 read 方法后，buf 应该包含 “a”。我们从文件中总共读取了 1 个字符，所以返回 1。
sol.read (buf, 2); // 现在 buf 应该包含 "bc"。我们从文件中总共读取了 2 个字符，所以返回 2。
sol.read (buf, 1); // 我们已经到达文件的末尾，不能读取更多的字符。所以返回 0。
假设已经分配了 buf ，并保证有足够的空间存储文件中的所有字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>file = "abc"， queries = [4,1]
<strong>输出：</strong>[3,0]
<strong>解释：</strong>测试用例表示以下场景:
File file("abc");
Solution sol;
sol.read (buf, 4); // 调用 read 方法后，buf 应该包含 “abc”。我们从文件中总共读取了 3 个字符，所以返回 3。
sol.read (buf, 1); // 我们已经到达文件的末尾，不能读取更多的字符。所以返回 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= file.length &lt;= 500</code></li>
	<li><code>file</code>&nbsp;由英语字母和数字组成</li>
	<li><code>1 &lt;= queries.length &lt;= 10</code></li>
	<li><code>1 &lt;= queries[i] &lt;= 500</code></li>
</ul>

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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
