# [1095. 山脉数组中查找目标值](https://leetcode.cn/problems/find-in-mountain-array)

[English Version](/solution/1000-1099/1095.Find%20in%20Mountain%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>（这是一个 <strong>交互式问题&nbsp;</strong>）</p>

<p>给你一个 <strong>山脉数组</strong>&nbsp;<code>mountainArr</code>，请你返回能够使得&nbsp;<code>mountainArr.get(index)</code>&nbsp;<strong>等于</strong>&nbsp;<code>target</code>&nbsp;<strong>最小</strong>&nbsp;的下标 <code>index</code>&nbsp;值。</p>

<p>如果不存在这样的下标 <code>index</code>，就请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p>何为山脉数组？如果数组&nbsp;<code>A</code> 是一个山脉数组的话，那它满足如下条件：</p>

<p><strong>首先</strong>，<code>A.length &gt;= 3</code></p>

<p><strong>其次</strong>，在&nbsp;<code>0 &lt; i&nbsp;&lt; A.length - 1</code>&nbsp;条件下，存在 <code>i</code> 使得：</p>

<ul>
	<li><code>A[0] &lt; A[1] &lt; ... A[i-1] &lt; A[i]</code></li>
	<li><code>A[i] &gt; A[i+1] &gt; ... &gt; A[A.length - 1]</code></li>
</ul>

<p>&nbsp;</p>

<p>你将&nbsp;<strong>不能直接访问该山脉数组</strong>，必须通过&nbsp;<code>MountainArray</code>&nbsp;接口来获取数据：</p>

<ul>
	<li><code>MountainArray.get(k)</code>&nbsp;- 会返回数组中索引为<code>k</code>&nbsp;的元素（下标从 0 开始）</li>
	<li><code>MountainArray.length()</code>&nbsp;- 会返回该数组的长度</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<p>对&nbsp;<code>MountainArray.get</code>&nbsp;发起超过 <code>100</code> 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。</p>

<p>为了帮助大家更好地理解交互式问题，我们准备了一个样例 &ldquo;<strong>答案</strong>&rdquo;：<a href="https://leetcode.cn/playground/RKhe3ave" target="_blank">https://leetcode.cn/playground/RKhe3ave</a>，请注意这 <strong>不是一个正确答案</strong>。</p>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>array = [1,2,3,4,5,3,1], target = 3
<strong>输出：</strong>2
<strong>解释：</strong>3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>array = [0,1,2,4,2,1], target = 3
<strong>输出：</strong>-1
<strong>解释：</strong>3 在数组中没有出现，返回 -1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= mountain_arr.length() &lt;= 10000</code></li>
	<li><code>0 &lt;= target &lt;= 10^9</code></li>
	<li><code>0 &lt;= mountain_arr.get(index) &lt;=&nbsp;10^9</code></li>
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
