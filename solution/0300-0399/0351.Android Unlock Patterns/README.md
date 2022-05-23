# [351. 安卓系统手势解锁](https://leetcode.cn/problems/android-unlock-patterns)

[English Version](/solution/0300-0399/0351.Android%20Unlock%20Patterns/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们都知道安卓有个手势解锁的界面，是一个&nbsp;<code>3 x 3</code><strong> </strong>的点所绘制出来的网格。用户可以设置一个 “解锁模式” ，通过连接特定序列中的点，形成一系列彼此连接的线段，每个线段的端点都是序列中两个连续的点。如果满足以下两个条件，则 <code>k</code> 点序列是有效的解锁模式：</p>

<ul>
	<li>解锁模式中的所有点 <strong>互不相同</strong> 。</li>
	<li>假如模式中两个连续点的线段需要经过其他点的 <strong>中心</strong> ，那么要经过的点 <strong>必须提前出现</strong> 在序列中（已经经过），不能跨过任何还未被经过的点。
	<ul>
		<li>例如，点 <code>5</code> 或 <code>6</code>&nbsp;没有提前出现的情况下连接点 <code>2</code>&nbsp;和 <code>9</code>&nbsp;是有效的，因为从点 <code>2</code> 到点 <code>9</code> 的线没有穿过点 <code>5</code> 或 <code>6</code> 的中心。</li>
		<li>然而，点 <code>2</code> 没有提前出现的情况下连接点 <code>1</code> 和&nbsp;<code>3</code>&nbsp;是无效的，因为从圆点 <code>1</code> 到圆点 <code>3</code> 的直线穿过圆点 <code>2</code> 的中心。</li>
	</ul>
	</li>
</ul>

<p>以下是一些有效和无效解锁模式的示例：</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0351.Android%20Unlock%20Patterns/images/android-unlock.png" /></p>

<ul>
	<li><strong>无效手势：</strong><code>[4,1,3,6]</code> ，连接点 1 和点&nbsp;3 时经过了未被连接过的&nbsp;2 号点。</li>
	<li><strong>无效手势：</strong><code>[4,1,9,2]</code> ，连接点 1 和点 9 时经过了未被连接过的 5&nbsp;号点。</li>
	<li><strong>有效手势：</strong><code>[2,4,1,3,6]</code> ，连接点 1 和点&nbsp;3 是有效的，因为虽然它经过了点&nbsp;2 ，但是点 2 在该手势中之前已经被连过了。</li>
	<li><strong>有效手势：</strong><code>[6,5,4,1,9,2]</code> ，连接点 1 和点&nbsp;9 是有效的，因为虽然它经过了按键 5 ，但是点&nbsp;5 在该手势中之前已经被连过了。</li>
</ul>

<p>给你两个整数，分别为 ​​<code>m</code> 和 <code>n</code> ，那么请返回有多少种 <strong>不同且有效的解锁模式 </strong>，是 <strong>至少</strong> 需要经过 <code>m</code> 个点，但是 <strong>不超过</strong> <code>n</code> 个点的。</p>

<p>两个解锁模式 <strong>不同</strong> 需满足：经过的点不同或者经过点的顺序不同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>m = 1, n = 1
<strong>输出：</strong>9
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>m = 1, n = 2
<strong>输出：</strong>65
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 9</code></li>
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
