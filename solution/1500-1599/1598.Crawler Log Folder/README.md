# [1598. 文件夹操作日志搜集器](https://leetcode.cn/problems/crawler-log-folder)

[English Version](/solution/1500-1599/1598.Crawler%20Log%20Folder/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。</p>

<p>下面给出对变更操作的说明：</p>

<ul>
	<li><code>&quot;../&quot;</code> ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 <strong>继续停留在当前文件夹</strong> 。</li>
	<li><code>&quot;./&quot;</code> ：继续停留在当前文件夹<strong>。</strong></li>
	<li><code>&quot;x/&quot;</code> ：移动到名为 <code>x</code> 的子文件夹中。题目数据 <strong>保证总是存在文件夹 <code>x</code></strong> 。</li>
</ul>

<p>给你一个字符串列表 <code>logs</code> ，其中 <code>logs[i]</code> 是用户在 <code>i<sup>th</sup></code> 步执行的操作。</p>

<p>文件系统启动时位于主文件夹，然后执行 <code>logs</code> 中的操作。</p>

<p>执行完所有变更文件夹操作后，请你找出 <strong>返回主文件夹所需的最小步数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1598.Crawler%20Log%20Folder/images/sample_11_1957.png" style="height: 151px; width: 775px;"></p>

<pre><strong>输入：</strong>logs = [&quot;d1/&quot;,&quot;d2/&quot;,&quot;../&quot;,&quot;d21/&quot;,&quot;./&quot;]
<strong>输出：</strong>2
<strong>解释：</strong>执行 &quot;../&quot; 操作变更文件夹 2 次，即可回到主文件夹
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1598.Crawler%20Log%20Folder/images/sample_22_1957.png" style="height: 270px; width: 600px;"></p>

<pre><strong>输入：</strong>logs = [&quot;d1/&quot;,&quot;d2/&quot;,&quot;./&quot;,&quot;d3/&quot;,&quot;../&quot;,&quot;d31/&quot;]
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>logs = [&quot;d1/&quot;,&quot;../&quot;,&quot;../&quot;,&quot;../&quot;]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>3</sup></code></li>
	<li><code>2 &lt;= logs[i].length &lt;= 10</code></li>
	<li><code>logs[i]</code> 包含小写英文字母，数字，<code>&#39;.&#39;</code> 和 <code>&#39;/&#39;</code></li>
	<li><code>logs[i]</code> 符合语句中描述的格式</li>
	<li>文件夹名称由小写英文字母和数字组成</li>
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
