# [1487. Making File Names Unique](https://leetcode.com/problems/making-file-names-unique)

[中文文档](/solution/1400-1499/1487.Making%20File%20Names%20Unique/README.md)

## Description

<p>Given an array of strings <code>names</code> of size <code>n</code>. You will create <code>n</code> folders in your file system <strong>such that</strong>, at the <code>ith</code> minute, you will create a folder with the name <code>names[i]</code>.</p>

<p>Since two files <strong>cannot</strong> have the same name, if you enter a folder name which is previously used,&nbsp;the system&nbsp;will have a suffix&nbsp;addition to its name in the form of <code>(k)</code>,&nbsp;where,&nbsp;<code>k</code> is the <strong>smallest positive integer</strong> such that the obtained name remains unique.</p>

<p>Return <em>an array of strings of length <code>n</code></em> where <code>ans[i]</code> is the actual name the system will assign to the <code>ith</code> folder when you create it.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> names = [&quot;pes&quot;,&quot;fifa&quot;,&quot;gta&quot;,&quot;pes(2019)&quot;]

<strong>Output:</strong> [&quot;pes&quot;,&quot;fifa&quot;,&quot;gta&quot;,&quot;pes(2019)&quot;]

<strong>Explanation:</strong> Let&#39;s see how the file system creates folder names:

&quot;pes&quot; --&gt; not assigned before, remains &quot;pes&quot;

&quot;fifa&quot; --&gt; not assigned before, remains &quot;fifa&quot;

&quot;gta&quot; --&gt; not assigned before, remains &quot;gta&quot;

&quot;pes(2019)&quot; --&gt; not assigned before, remains &quot;pes(2019)&quot;

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> names = [&quot;gta&quot;,&quot;gta(1)&quot;,&quot;gta&quot;,&quot;avalon&quot;]

<strong>Output:</strong> [&quot;gta&quot;,&quot;gta(1)&quot;,&quot;gta(2)&quot;,&quot;avalon&quot;]

<strong>Explanation:</strong> Let&#39;s see how the file system creates folder names:

&quot;gta&quot; --&gt; not assigned before, remains &quot;gta&quot;

&quot;gta(1)&quot; --&gt; not assigned before, remains &quot;gta(1)&quot;

&quot;gta&quot; --&gt; the name is reserved, system adds (k), since &quot;gta(1)&quot; is also reserved, systems put k = 2. it becomes &quot;gta(2)&quot;

&quot;avalon&quot; --&gt; not assigned before, remains &quot;avalon&quot;

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> names = [&quot;onepiece&quot;,&quot;onepiece(1)&quot;,&quot;onepiece(2)&quot;,&quot;onepiece(3)&quot;,&quot;onepiece&quot;]

<strong>Output:</strong> [&quot;onepiece&quot;,&quot;onepiece(1)&quot;,&quot;onepiece(2)&quot;,&quot;onepiece(3)&quot;,&quot;onepiece(4)&quot;]

<strong>Explanation:</strong> When the last folder is created, the smallest positive valid k is 4, and it becomes &quot;onepiece(4)&quot;.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> names = [&quot;wano&quot;,&quot;wano&quot;,&quot;wano&quot;,&quot;wano&quot;]

<strong>Output:</strong> [&quot;wano&quot;,&quot;wano(1)&quot;,&quot;wano(2)&quot;,&quot;wano(3)&quot;]

<strong>Explanation:</strong> Just increase the value of k each time you create folder &quot;wano&quot;.

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> names = [&quot;kaido&quot;,&quot;kaido(1)&quot;,&quot;kaido&quot;,&quot;kaido(1)&quot;]

<strong>Output:</strong> [&quot;kaido&quot;,&quot;kaido(1)&quot;,&quot;kaido(2)&quot;,&quot;kaido(1)(1)&quot;]

<strong>Explanation:</strong> Please note that system adds the suffix (k) to current name even it contained the same suffix before.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= names.length &lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>names[i]</code> consists of lower case English letters, digits and/or round brackets.</li>
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
