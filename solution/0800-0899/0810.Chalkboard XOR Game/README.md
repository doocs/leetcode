# [810. 黑板异或游戏](https://leetcode.cn/problems/chalkboard-xor-game)

[English Version](/solution/0800-0899/0810.Chalkboard%20XOR%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>黑板上写着一个非负整数数组 <code>nums[i]</code> 。</p>

<p>Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 <code>0</code> 的话，当前玩家游戏失败。&nbsp;另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为&nbsp;<code>0</code>。</p>

<p>并且，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 <code>0</code> ，这个玩家获胜。</p>

<p>假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 <code>true</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,2]
<strong>输出:</strong> false
<strong>解释:</strong> 
Alice 有两个选择: 擦掉数字 1 或 2。
如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> true
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3]
<strong>输出:</strong> true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>16</sup></code></li>
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
