# [1487. 保证文件名唯一](https://leetcode.cn/problems/making-file-names-unique)

[English Version](/solution/1400-1499/1487.Making%20File%20Names%20Unique/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的字符串数组 <code>names</code> 。你将会在文件系统中创建 <code>n</code> 个文件夹：在第 <code>i</code> 分钟，新建名为 <code>names[i]</code> 的文件夹。</p>

<p>由于两个文件 <strong>不能</strong> 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 <code>(k)</code> 的形式为新文件夹的文件名添加后缀，其中 <code>k</code> 是能保证文件名唯一的 <strong>最小正整数</strong> 。</p>

<p>返回长度为<em> <code>n</code></em> 的字符串数组，其中 <code>ans[i]</code> 是创建第 <code>i</code> 个文件夹时系统分配给该文件夹的实际名称。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>names = [&quot;pes&quot;,&quot;fifa&quot;,&quot;gta&quot;,&quot;pes(2019)&quot;]
<strong>输出：</strong>[&quot;pes&quot;,&quot;fifa&quot;,&quot;gta&quot;,&quot;pes(2019)&quot;]
<strong>解释：</strong>文件系统将会这样创建文件名：
&quot;pes&quot; --&gt; 之前未分配，仍为 &quot;pes&quot;
&quot;fifa&quot; --&gt; 之前未分配，仍为 &quot;fifa&quot;
&quot;gta&quot; --&gt; 之前未分配，仍为 &quot;gta&quot;
&quot;pes(2019)&quot; --&gt; 之前未分配，仍为 &quot;pes(2019)&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>names = [&quot;gta&quot;,&quot;gta(1)&quot;,&quot;gta&quot;,&quot;avalon&quot;]
<strong>输出：</strong>[&quot;gta&quot;,&quot;gta(1)&quot;,&quot;gta(2)&quot;,&quot;avalon&quot;]
<strong>解释：</strong>文件系统将会这样创建文件名：
&quot;gta&quot; --&gt; 之前未分配，仍为 &quot;gta&quot;
&quot;gta(1)&quot; --&gt; 之前未分配，仍为 &quot;gta(1)&quot;
&quot;gta&quot; --&gt; 文件名被占用，系统为该名称添加后缀 (k)，由于 &quot;gta(1)&quot; 也被占用，所以 k = 2 。实际创建的文件名为 &quot;gta(2)&quot; 。
&quot;avalon&quot; --&gt; 之前未分配，仍为 &quot;avalon&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>names = [&quot;onepiece&quot;,&quot;onepiece(1)&quot;,&quot;onepiece(2)&quot;,&quot;onepiece(3)&quot;,&quot;onepiece&quot;]
<strong>输出：</strong>[&quot;onepiece&quot;,&quot;onepiece(1)&quot;,&quot;onepiece(2)&quot;,&quot;onepiece(3)&quot;,&quot;onepiece(4)&quot;]
<strong>解释：</strong>当创建最后一个文件夹时，最小的正有效 k 为 4 ，文件名变为 &quot;onepiece(4)&quot;。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>names = [&quot;wano&quot;,&quot;wano&quot;,&quot;wano&quot;,&quot;wano&quot;]
<strong>输出：</strong>[&quot;wano&quot;,&quot;wano(1)&quot;,&quot;wano(2)&quot;,&quot;wano(3)&quot;]
<strong>解释：</strong>每次创建文件夹 &quot;wano&quot; 时，只需增加后缀中 k 的值即可。</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>names = [&quot;kaido&quot;,&quot;kaido(1)&quot;,&quot;kaido&quot;,&quot;kaido(1)&quot;]
<strong>输出：</strong>[&quot;kaido&quot;,&quot;kaido(1)&quot;,&quot;kaido(2)&quot;,&quot;kaido(1)(1)&quot;]
<strong>解释：</strong>注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= names.length &lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>names[i]</code> 由小写英文字母、数字和/或圆括号组成。</li>
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

### **...**

```

```

<!-- tabs:end -->
