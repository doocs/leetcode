# [2836. 在传球游戏中最大化函数值](https://leetcode.cn/problems/maximize-value-of-function-in-a-ball-passing-game)

[English Version](/solution/2800-2899/2836.Maximize%20Value%20of%20Function%20in%20a%20Ball%20Passing%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>receiver</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>总共有&nbsp;<code>n</code>&nbsp;名玩家，玩家 <strong>编号</strong>&nbsp;互不相同，且为&nbsp;<code>[0, n - 1]</code>&nbsp;中的整数。这些玩家玩一个传球游戏，<code>receiver[i]</code>&nbsp;表示编号为 <code>i</code>&nbsp;的玩家会传球给编号为 <code>receiver[i]</code>&nbsp;的玩家。玩家可以传球给自己，也就是说&nbsp;<code>receiver[i]</code>&nbsp;可能等于&nbsp;<code>i</code>&nbsp;。</p>

<p>你需要从 <code>n</code>&nbsp;名玩家中选择一名玩家作为游戏开始时唯一手中有球的玩家，球会被传 <strong>恰好</strong>&nbsp;<code>k</code>&nbsp;次。</p>

<p>如果选择编号为 <code>x</code>&nbsp;的玩家作为开始玩家，定义函数&nbsp;<code>f(x)</code>&nbsp;表示从编号为&nbsp;<code>x</code>&nbsp;的玩家开始，<code>k</code>&nbsp;次传球内所有接触过球玩家的编号之&nbsp;<strong>和</strong>&nbsp;，如果有玩家多次触球，则 <strong>累加多次</strong>&nbsp;。换句话说，&nbsp;<code>f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver<sup>(k)</sup>[x]</code>&nbsp;。</p>

<p>你的任务时选择开始玩家 <code>x</code>&nbsp;，目的是<strong>&nbsp;最大化</strong>&nbsp;<code>f(x)</code>&nbsp;。</p>

<p>请你返回函数的 <strong>最大值</strong>&nbsp;。</p>

<p><strong>注意：</strong><code>receiver</code>&nbsp;可能含有重复元素。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">传递次数</th>
			<th style="padding: 5px; border: 1px solid black;">传球者编号</th>
			<th style="padding: 5px; border: 1px solid black;">接球者编号</th>
			<th style="padding: 5px; border: 1px solid black;">x + 所有接球者编号</th>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">0</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">0</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">4</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">6</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<pre>
<b>输入：</b>receiver = [2,0,1], k = 4
<b>输出：</b>6
<b>解释：</b>上表展示了从编号为 x = 2 开始的游戏过程。
从表中可知，f(2) 等于 6 。
6 是能得到最大的函数值。
所以输出为 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">传递次数</th>
			<th style="padding: 5px; border: 1px solid black;">传球者编号</th>
			<th style="padding: 5px; border: 1px solid black;">接球者编号</th>
			<th style="padding: 5px; border: 1px solid black;">x + 所有接球者编号</th>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">&nbsp;</td>
			<td style="padding: 5px; border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">4</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">7</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">9</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">10</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<pre>
<b>输入：</b>receiver = [1,1,1,2,3], k = 3
<b>输出：</b>10
<b>解释：</b>上表展示了从编号为 x = 4 开始的游戏过程。
从表中可知，f(4) 等于 10 。
10 是能得到最大的函数值。
所以输出为 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= receiver.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= receiver[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>10</sup></code></li>
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
