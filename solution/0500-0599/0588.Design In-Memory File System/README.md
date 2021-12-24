# [588. 设计内存文件系统](https://leetcode-cn.com/problems/design-in-memory-file-system)

[English Version](/solution/0500-0599/0588.Design%20In-Memory%20File%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个内存文件系统，模拟以下功能：</p>

<p><code>ls</code>： 以字符串的格式输入一个路径。如果它是一个文件的路径，那么函数返回一个列表，仅包含这个文件的名字。如果它是一个文件夹的的路径，那么返回该 <strong>文件夹内</strong>&nbsp;的所有文件和子文件夹的名字。你的返回结果（包括文件和子文件夹）应该按字典序排列。</p>

<p><code>mkdir</code>：输入一个当前不存在的&nbsp;<strong>文件夹路径</strong>&nbsp;，你需要根据路径名创建一个新的文件夹。如果有上层文件夹路径不存在，那么你也应该将它们全部创建。这个函数的返回类型为 void 。</p>

<p><code>addContentToFile</code>： 输入字符串形式的&nbsp;<strong>文件路径</strong>&nbsp;和 <strong>文件内容</strong>&nbsp;。如果文件不存在，你需要创建包含给定文件内容的文件。如果文件已经存在，那么你需要将给定的文件内容 <strong>追加</strong>&nbsp;在原本内容的后面。这个函数的返回类型为 void 。</p>

<p><code>readContentFromFile</code>： 输入 <strong>文件路径</strong>&nbsp;，以字符串形式返回该文件的&nbsp;<strong>内容</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 
[&quot;FileSystem&quot;,&quot;ls&quot;,&quot;mkdir&quot;,&quot;addContentToFile&quot;,&quot;ls&quot;,&quot;readContentFromFile&quot;]
[[],[&quot;/&quot;],[&quot;/a/b/c&quot;],[&quot;/a/b/c/d&quot;,&quot;hello&quot;],[&quot;/&quot;],[&quot;/a/b/c/d&quot;]]

<strong>输出:</strong>
[null,[],null,null,[&quot;a&quot;],&quot;hello&quot;]

<strong>解释:</strong>
<img alt="filesystem" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0588.Design%20In-Memory%20File%20System/images/filesystem.png" style="width: 640px;">
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>你可以假定所有文件和文件夹的路径都是绝对路径，除非是根目录&nbsp;<code>/</code>&nbsp;自身，其他路径都是以&nbsp;<code>/</code>&nbsp;开头且 <strong>不</strong> 以&nbsp;<code>/</code>&nbsp;结束。</li>
	<li>你可以假定所有操作的参数都是有效的，即用户不会获取不存在文件的内容，或者获取不存在文件夹和文件的列表。</li>
	<li>你可以假定所有文件夹名字和文件名字都只包含小写字母，且同一文件夹下不会有相同名字的文件夹或文件。</li>
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
