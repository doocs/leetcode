# [609. 在系统中查找重复文件](https://leetcode-cn.com/problems/find-duplicate-file-in-system)

[English Version](/solution/0600-0699/0609.Find%20Duplicate%20File%20in%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个目录信息列表，包括目录路径，以及该目录中的所有包含内容的文件，您需要找到文件系统中的所有重复文件组的路径。一组重复的文件至少包括<strong>二个</strong>具有完全相同内容的文件。</p>

<p><strong>输入</strong>列表中的单个目录信息字符串的格式如下：</p>

<p><code>&quot;root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)&quot;</code></p>

<p>这意味着有 n 个文件（<code>f1.txt</code>,&nbsp;<code>f2.txt</code>&nbsp;...&nbsp;<code>fn.txt</code> 的内容分别是 <code>f1_content</code>,&nbsp;<code>f2_content</code>&nbsp;...&nbsp;<code>fn_content</code>）在目录&nbsp;<code>root/d1/d2/.../dm</code>&nbsp;下。注意：n&gt;=1 且 m&gt;=0。如果 m=0，则表示该目录是根目录。</p>

<p>该<strong>输出</strong>是重复文件路径组的列表。对于每个组，它包含具有相同内容的文件的所有文件路径。文件路径是具有下列格式的字符串：</p>

<p><code>&quot;directory_path/file_name.txt&quot;</code></p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[&quot;root/a 1.txt(abcd) 2.txt(efgh)&quot;, &quot;root/c 3.txt(abcd)&quot;, &quot;root/c/d 4.txt(efgh)&quot;, &quot;root 4.txt(efgh)&quot;]
<strong>输出：</strong>  
[[&quot;root/a/2.txt&quot;,&quot;root/c/d/4.txt&quot;,&quot;root/4.txt&quot;],[&quot;root/a/1.txt&quot;,&quot;root/c/3.txt&quot;]]
</pre>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ol>
	<li>最终输出不需要顺序。</li>
	<li>您可以假设目录名、文件名和文件内容只有字母和数字，并且文件内容的长度在 [1，50] 的范围内。</li>
	<li>给定的文件数量在 [1，20000] 个范围内。</li>
	<li>您可以假设在同一目录中没有任何文件或目录共享相同的名称。</li>
	<li>您可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用一个空格分隔。</li>
</ol>

<p>&nbsp;</p>

<p><strong>超越竞赛的后续行动：</strong></p>

<ol>
	<li>假设您有一个真正的文件系统，您将如何搜索文件？广度搜索还是宽度搜索？</li>
	<li>如果文件内容非常大（GB级别），您将如何修改您的解决方案？</li>
	<li>如果每次只能读取 1 kb 的文件，您将如何修改解决方案？</li>
	<li>修改后的解决方案的时间复杂度是多少？其中最耗时的部分和消耗内存的部分是什么？如何优化？</li>
	<li>如何确保您发现的重复文件不是误报？</li>
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
