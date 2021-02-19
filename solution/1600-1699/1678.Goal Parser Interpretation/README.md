# [1678. 设计 Goal 解析器](https://leetcode-cn.com/problems/goal-parser-interpretation)

[English Version](/solution/1500-1599/1678.Goal%20Parser%20Interpretation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>请你设计一个可以解释字符串 <code>command</code> 的 <strong>Goal 解析器</strong> 。<code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成。Goal 解析器会将 <code>"G"</code> 解释为字符串 <code>"G"</code>、<code>"()"</code> 解释为字符串 <code>"o"</code> ，<code>"(al)"</code> 解释为字符串 <code>"al"</code> 。然后，按原顺序将经解释得到的字符串连接成一个字符串。</p>

<p>给你字符串 <code>command</code> ，返回<em> </em><strong>Goal<em><strong> </strong></em>解析器 </strong>对<em> </em><code>command</code> 的解释结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>command = "G()(al)"
<strong>输出：</strong>"Goal"
<strong>解释：</strong>Goal 解析器解释命令的步骤如下所示：
G -&gt; G
() -&gt; o
(al) -&gt; al
最后连接得到的结果是 "Goal"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>command = "G()()()()(al)"
<strong>输出：</strong>"Gooooal"
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>command = "(al)G(al)()()G"
<strong>输出：</strong>"alGalooG"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= command.length &lt;= 100</code></li>
	<li><code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成</li>
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
