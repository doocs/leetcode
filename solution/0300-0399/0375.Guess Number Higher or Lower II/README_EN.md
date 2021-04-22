# [375. Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii)

[中文文档](/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README.md)

## Description

<p>We are playing the Guessing Game. The game will work as follows:</p>

<ol>
	<li>I pick a number between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>n</code>.</li>
	<li>You guess a number.</li>
	<li>If you guess the right number, <strong>you win the game</strong>.</li>
	<li>If you guess the wrong number, then I will tell you whether the number I picked is <strong>higher or lower</strong>, and you will continue guessing.</li>
	<li>Every time you guess a wrong number&nbsp;<code>x</code>, you will pay&nbsp;<code>x</code>&nbsp;dollars. If you run out of money, <strong>you lose the game</strong>.</li>
</ol>

<p>Given a particular&nbsp;<code>n</code>, return&nbsp;<em>the minimum amount of money you need to&nbsp;<strong>guarantee a win regardless of what number I pick</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="/solution/0300-0399/0375.Guess Number Higher or Lower II/images/graph.png" style="width: 505px; height: 388px;" />
<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 16
<strong>Explanation:</strong> The winning strategy is as follows:
- The range is [1,10]. Guess 7.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $7.
&nbsp;   - If my number is higher, the range is [8,10]. Guess 9.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $9.
&nbsp;       - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
&nbsp;       - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
&nbsp;   - If my number is lower, the range is [1,6]. Guess 3.
&nbsp;       - If this is my number, your total is $7. Otherwise, you pay $3.
&nbsp;       - If my number is higher, the range is [4,6]. Guess 5.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
&nbsp;           - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
&nbsp;           - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
&nbsp;       - If my number is lower, the range is [1,2]. Guess 1.
&nbsp;           - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
&nbsp;           - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;There is only one possible number, so you can guess 1 and not have to pay anything.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;There are two possible numbers, 1 and 2.
- Guess 1.
&nbsp;   - If this is my number, your total is $0. Otherwise, you pay $1.
&nbsp;   - If my number is higher, it must be 2. Guess 2. Your total is $1.
The worst case is that you pay $1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
