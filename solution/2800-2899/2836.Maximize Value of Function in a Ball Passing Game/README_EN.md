# [2836. Maximize Value of Function in a Ball Passing Game](https://leetcode.com/problems/maximize-value-of-function-in-a-ball-passing-game)

[中文文档](/solution/2800-2899/2836.Maximize%20Value%20of%20Function%20in%20a%20Ball%20Passing%20Game/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>receiver</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>There are <code>n</code> players having a <strong>unique id</strong> in the range <code>[0, n - 1]</code> who will play a ball passing game, and <code>receiver[i]</code> is the id of the player who receives passes from the player with id <code>i</code>. Players can pass to themselves, <strong>i.e.</strong> <code>receiver[i]</code> may be equal to <code>i</code>.</p>

<p>You must choose one of the <code>n</code> players as the starting player for the game, and the ball will be passed <strong>exactly</strong> <code>k</code> times starting from the chosen player.</p>

<p>For a chosen starting player having id <code>x</code>, we define a function <code>f(x)</code> that denotes the <strong>sum</strong> of <code>x</code> and the <strong>ids</strong> of all players who receive the ball during the <code>k</code> passes, <strong>including repetitions</strong>. In other words, <code>f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver<sup>(k)</sup>[x]</code>.</p>

<p>Your task is to choose a starting player having id <code>x</code> that <strong>maximizes</strong> the value of <code>f(x)</code>.</p>

<p>Return <em>an integer denoting the <strong>maximum</strong> value of the function.</em></p>

<p><strong>Note:</strong> <code>receiver</code> may contain duplicates.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">Pass Number</th>
			<th style="padding: 5px; border: 1px solid black;">Sender ID</th>
			<th style="padding: 5px; border: 1px solid black;">Receiver ID</th>
			<th style="padding: 5px; border: 1px solid black;">x + Receiver IDs</th>
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

<pre>
<strong>Input:</strong> receiver = [2,0,1], k = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> The table above shows a simulation of the game starting with the player having id x = 2. 
From the table, f(2) is equal to 6. 
It can be shown that 6 is the maximum achievable value of the function. 
Hence, the output is 6. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">Pass Number</th>
			<th style="padding: 5px; border: 1px solid black;">Sender ID</th>
			<th style="padding: 5px; border: 1px solid black;">Receiver ID</th>
			<th style="padding: 5px; border: 1px solid black;">x + Receiver IDs</th>
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

<pre>
<strong>Input:</strong> receiver = [1,1,1,2,3], k = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> The table above shows a simulation of the game starting with the player having id x = 4. 
From the table, f(4) is equal to 10. 
It can be shown that 10 is the maximum achievable value of the function. 
Hence, the output is 10. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= receiver.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= receiver[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>10</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
