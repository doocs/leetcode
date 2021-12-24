# [810. Chalkboard XOR Game](https://leetcode.com/problems/chalkboard-xor-game)

[中文文档](/solution/0800-0899/0810.Chalkboard%20XOR%20Game/README.md)

## Description

<p>We are given non-negative integers nums[i] which are written on a chalkboard.&nbsp; Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first.&nbsp; If erasing a number causes&nbsp;the bitwise XOR of all the elements of the chalkboard to become&nbsp;0, then that player loses.&nbsp; (Also, we&#39;ll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)</p>

<p>Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.</p>

<p>Return True if and only if Alice wins the game, assuming both players play optimally.</p>

<pre>

<strong>Example:</strong>

<strong>Input:</strong> nums = [1, 1, 2]

<strong>Output:</strong> false

<strong>Explanation:</strong> 

Alice has two choices: erase 1 or erase 2. 

If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3. Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose. 

If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.



</pre>

<p><strong>Notes: </strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 1000</code>.&nbsp;</li>
	<li><code>0 &lt;= nums[i] &lt;= 2^16</code>.</li>
</ul>

<p>&nbsp;</p>

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
