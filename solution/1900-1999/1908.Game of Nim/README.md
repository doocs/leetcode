# [1908. Nim 游戏 II](https://leetcode.cn/problems/game-of-nim)

[English Version](/solution/1900-1999/1908.Game%20of%20Nim/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和&nbsp;Bob 交替进行一个游戏，<strong>由 Alice 先手</strong>。</p>

<p>在游戏中，共有&nbsp;<code>n</code>&nbsp;堆石头。在每个玩家的回合中，玩家需要 <strong>选择</strong> 任一非空石头堆，从中移除任意 <strong>非零</strong> 数量的石头。如果不能移除任意的石头，就输掉游戏，同时另一人获胜。</p>

<p>给定一个整数数组&nbsp;<code>piles</code> ，<code>piles[i]</code> 为 第&nbsp;<code>i</code>&nbsp;堆石头的数量，如果 Alice 能获胜返回&nbsp;<code>true</code><em>&nbsp;</em>，反之返回&nbsp;<code>false</code><em>&nbsp;。</em></p>

<p>Alice 和 Bob 都会采取<strong> 最优策略 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [1]
<strong>输出：</strong>true
<strong>解释：</strong>只有一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。piles = [0]。
- 第二回合，Bob 没有任何石头可以移除。Alice 获胜。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,1]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 1 堆中 1 块石头。 piles = [0,1]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>piles = [1,2,3]
<strong>输出：</strong>false
<strong>解释：</strong>可以证明，Bob一定能获胜。一种可能的情况：
- 第一回合，Alice 移除了第 3 堆中 3 块石头。 piles = [1,2,0]。
- 第二回合，Bob 移除了第 2 堆中 1 块石头。 piles = [1,1,0]。
- 第三回合，Alice 移除了第 1 堆中 1 块石头。piles = [0,1,0]。
- 第四回合，Bob 移除了第 2 堆中 1 块石头。 piles = [0,0,0]。
- 第三回合，Alice 没有任何石头可以移除。Bob 获胜。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 7</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 7</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能想出一个&nbsp;<strong>线性时间&nbsp;</strong>的解决方案吗？虽然这一答案可能超出了面试所需的范围，但了解它可能会很有趣。</p>

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
