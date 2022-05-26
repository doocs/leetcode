# [833. 字符串中的查找与替换](https://leetcode.cn/problems/find-and-replace-in-string)

[English Version](/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你会得到一个字符串 <code>s</code>&nbsp;(索引从 0 开始)，你必须对它执行 <code>k</code> 个替换操作。替换操作以三个长度均为 <code>k</code> 的并行数组给出：<code>indices</code>,&nbsp;<code>sources</code>,&nbsp;&nbsp;<code>targets</code>。</p>

<p>要完成第 <code>i</code> 个替换操作:</p>

<ol>
	<li>检查 <strong>子字符串</strong> &nbsp;<code>sources[i]</code>&nbsp;是否出现在 <strong>原字符串</strong> <code>s</code> 的索引&nbsp;<code>indices[i]</code>&nbsp;处。</li>
	<li>如果没有出现，&nbsp;<strong>什么也不做</strong>&nbsp;。</li>
	<li>如果出现，则用&nbsp;<code>targets[i]</code>&nbsp;<strong>替换</strong>&nbsp;该子字符串。</li>
</ol>

<p>例如，如果 <code>s = "abcd"</code>&nbsp;，&nbsp;<code>indices[i] = 0</code> ,&nbsp;<code>sources[i] = "ab"</code>， <code>targets[i] = "eee"</code> ，那么替换的结果将是 <code>"<u>eee</u>cd"</code> 。</p>

<p>所有替换操作必须 <strong>同时</strong> 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间<strong>不会重叠 </strong>。</p>

<ul>
	<li>例如，一个 <code>s = "abc"</code> ，&nbsp; <code>indices = [0,1]</code> ， <code>sources = ["ab"，"bc"]</code>&nbsp;的测试用例将不会生成，因为 <code>"ab"</code> 和 <code>"bc"</code> 替换重叠。</li>
</ul>

<p><em>在对 <code>s</code>&nbsp;执行所有替换操作后返回 <strong>结果字符串</strong> 。</em></p>

<p><strong>子字符串</strong> 是字符串中连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex1.png" style="height: 251px; width: 411px;" /></p>

<pre>
<strong>输入：</strong>s = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeebffff"
<strong>解释：
</strong>"a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
</pre>

<p><strong>示例 2：</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0833.Find%20And%20Replace%20in%20String/images/833-ex2-1.png" style="height: 251px; width: 411px;" /></p>

<pre>
<strong>输入：</strong>s = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
<strong>输出：</strong>"eeecd"
<strong>解释：
</strong>"ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"ec" 没有从<strong>原始的</strong> S 中的索引 2 开始，所以它没有被替换。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>k == indices.length == sources.length == targets.length</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= indexes[i] &lt; s.length</code></li>
	<li><code>1 &lt;= sources[i].length, targets[i].length &lt;= 50</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>sources[i]</code> 和 <code>targets[i]</code> 仅由小写英文字母组成</li>
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
