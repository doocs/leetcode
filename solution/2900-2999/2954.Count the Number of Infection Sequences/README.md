# [2954. 统计感冒序列的数目](https://leetcode.cn/problems/count-the-number-of-infection-sequences)

[English Version](/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>sick</code>&nbsp;，数组按 <strong>升序</strong>&nbsp;排序。</p>

<p>有&nbsp;<code>n</code>&nbsp;位小朋友站成一排，按顺序编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。数组&nbsp;<code>sick</code>&nbsp;包含一开始得了感冒的小朋友的位置。如果位置为&nbsp;<code>i</code>&nbsp;的小朋友得了感冒，他会传染给下标为 <code>i - 1</code>&nbsp;或者 <code>i + 1</code>&nbsp;的小朋友，<strong>前提</strong> 是被传染的小朋友存在且还没有得感冒。每一秒中， <strong>至多一位</strong>&nbsp;还没感冒的小朋友会被传染。</p>

<p>经过有限的秒数后，队列中所有小朋友都会感冒。<strong>感冒序列</strong>&nbsp;指的是 <strong>所有</strong>&nbsp;一开始没有感冒的小朋友最后得感冒的顺序序列。请你返回所有感冒序列的数目。</p>

<p>由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p><b>注意</b>，感冒序列 <strong>不</strong> 包含一开始就得了感冒的小朋友的下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 5, sick = [0,4]
<b>输出：</b>4
<b>解释：</b>一开始，下标为 1 ，2 和 3 的小朋友没有感冒。总共有 4 个可能的感冒序列：
- 一开始，下标为 1 和 3 的小朋友可以被传染，因为他们分别挨着有感冒的小朋友 0 和 4 ，令下标为 1 的小朋友先被传染。
然后，下标为 2 的小朋友挨着感冒的小朋友 1 ，下标为 3 的小朋友挨着感冒的小朋友 4 ，两位小朋友都可以被传染，令下标为 2 的小朋友被传染。
最后，下标为 3 的小朋友被传染，因为他挨着感冒的小朋友 2 和 4 ，感冒序列为 [1,2,3] 。
- 一开始，下标为 1 和 3 的小朋友可以被传染，因为他们分别挨着感冒的小朋友 0 和 4 ，令下标为 1 的小朋友先被传染。
然后，下标为 2 的小朋友挨着感冒的小朋友 1 ，下标为 3 的小朋友挨着感冒的小朋友 4 ，两位小朋友都可以被传染，令下标为 3 的小朋友被传染。
最后，下标为 2 的小朋友被传染，因为他挨着感冒的小朋友 1 和 3 ，感冒序列为  [1,3,2] 。
- 感冒序列 [3,1,2] ，被传染的顺序：[<strong><em>0</em></strong>,1,2,3,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,2,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,2,<em><strong>3</strong></em>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] 。
- 感冒序列 [3,2,1] ，被传染的顺序：[<strong><em>0</em></strong>,1,2,3,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,2,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,1,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>4</em></strong>] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 4, sick = [1]
<b>输出：</b>3
<b>解释：</b>一开始，下标为 0 ，2 和 3 的小朋友没有感冒。总共有 3 个可能的感冒序列：
- 感冒序列 [0,2,3] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,2,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
- 感冒序列 [2,0,3] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
- 感冒序列 [2,3,0] ，被传染的顺序：[0,<strong><em>1</em></strong>,2,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,3] =&gt; [0,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] =&gt; [<strong><em>0</em></strong>,<strong><em>1</em></strong>,<strong><em>2</em></strong>,<strong><em>3</em></strong>] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sick.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= sick[i] &lt;= n - 1</code></li>
	<li><code>sick</code>&nbsp;按升序排列。</li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
