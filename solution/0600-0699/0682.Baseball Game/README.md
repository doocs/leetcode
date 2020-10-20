# [682. 棒球比赛](https://leetcode-cn.com/problems/baseball-game)

[English Version](/solution/0600-0699/0682.Baseball%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你现在是棒球比赛记录员。<br>
给定一个字符串列表，每个字符串可以是以下四种类型之一：<br>
1.<code>整数</code>（一轮的得分）：直接表示您在本轮中获得的积分数。<br>
2. <code>&quot;+&quot;</code>（一轮的得分）：表示本轮获得的得分是前两轮<code>有效</code>&nbsp;回合得分的总和。<br>
3. <code>&quot;D&quot;</code>（一轮的得分）：表示本轮获得的得分是前一轮<code>有效</code>&nbsp;回合得分的两倍。<br>
4. <code>&quot;C&quot;</code>（一个操作，这不是一个回合的分数）：表示您获得的最后一个<code>有效</code>&nbsp;回合的分数是无效的，应该被移除。<br>
<br>
每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。<br>
你需要返回你在所有回合中得分的总和。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [&quot;5&quot;,&quot;2&quot;,&quot;C&quot;,&quot;D&quot;,&quot;+&quot;]
<strong>输出:</strong> 30
<strong>解释:</strong> 
第1轮：你可以得到5分。总和是：5。
第2轮：你可以得到2分。总和是：7。
操作1：第2轮的数据无效。总和是：5。
第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
第4轮：你可以得到5 + 10 = 15分。总数是：30。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [&quot;5&quot;,&quot;-2&quot;,&quot;4&quot;,&quot;C&quot;,&quot;D&quot;,&quot;9&quot;,&quot;+&quot;,&quot;+&quot;]
<strong>输出:</strong> 27
<strong>解释:</strong> 
第1轮：你可以得到5分。总和是：5。
第2轮：你可以得到-2分。总数是：3。
第3轮：你可以得到4分。总和是：7。
操作1：第3轮的数据无效。总数是：3。
第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
第5轮：你可以得到9分。总数是：8。
第6轮：你可以得到-4 + 9 = 5分。总数是13。
第7轮：你可以得到9 + 5 = 14分。总数是27。
</pre>

<p><strong>注意：</strong></p>

<ul>
	<li>输入列表的大小将介于1和1000之间。</li>
	<li>列表中的每个整数都将介于-30000和30000之间。</li>
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
