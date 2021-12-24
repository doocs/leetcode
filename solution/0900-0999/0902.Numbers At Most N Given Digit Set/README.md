# [902. 最大为 N 的数字组合](https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set)

[English Version](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一组<strong>排序的</strong>数字 <code>D</code>，它是&nbsp; <code>{&#39;1&#39;,&#39;2&#39;,&#39;3&#39;,&#39;4&#39;,&#39;5&#39;,&#39;6&#39;,&#39;7&#39;,&#39;8&#39;,&#39;9&#39;}</code>&nbsp;的非空子集。（请注意，<code>&#39;0&#39;</code> 不包括在内。）</p>

<p>现在，我们用这些数字进行组合写数字，想用多少次就用多少次。例如&nbsp;<code>D = {&#39;1&#39;,&#39;3&#39;,&#39;5&#39;}</code>，我们可以写出像&nbsp;<code>&#39;13&#39;, &#39;551&#39;, &#39;1351315&#39;</code>&nbsp;这样的数字。</p>

<p>返回可以用 <code>D</code> 中的数字写出的小于或等于 <code>N</code> 的正整数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>D = [&quot;1&quot;,&quot;3&quot;,&quot;5&quot;,&quot;7&quot;], N = 100
<strong>输出：</strong>20
<strong>解释：</strong>
可写出的 20 个数字是：
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>D = [&quot;1&quot;,&quot;4&quot;,&quot;9&quot;], N = 1000000000
<strong>输出：</strong>29523
<strong>解释：</strong>
我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
81 个四位数字，243 个五位数字，729 个六位数字，
2187 个七位数字，6561 个八位数字和 19683 个九位数字。
总共，可以使用D中的数字写出 29523 个整数。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>D</code> 是按排序顺序的数字 <code>&#39;1&#39;-&#39;9&#39;</code> 的子集。</li>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
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
