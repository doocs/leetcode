# [1535. 找出数组游戏的赢家](https://leetcode.cn/problems/find-the-winner-of-an-array-game)

[English Version](/solution/1500-1599/1535.Find%20the%20Winner%20of%20an%20Array%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>不同</strong> 整数组成的整数数组 <code>arr</code> 和一个整数 <code>k</code> 。</p>

<p>每回合游戏都在数组的前两个元素（即 <code>arr[0]</code> 和 <code>arr[1]</code> ）之间进行。比较 <code>arr[0]</code> 与 <code>arr[1]</code> 的大小，较大的整数将会取得这一回合的胜利并保留在位置 <code>0</code> ，较小的整数移至数组的末尾。当一个整数赢得 <code>k</code> 个连续回合时，游戏结束，该整数就是比赛的 <strong>赢家</strong> 。</p>

<p>返回赢得比赛的整数。</p>

<p>题目数据 <strong>保证</strong> 游戏存在赢家。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,1,3,5,4,6,7], k = 2
<strong>输出：</strong>5
<strong>解释：</strong>一起看一下本场游戏每回合的情况：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1535.Find%20the%20Winner%20of%20an%20Array%20Game/images/q-example.png" style="height: 90px; width: 400px;">
因此将进行 4 回合比赛，其中 5 是赢家，因为它连胜 2 回合。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [3,2,1], k = 10
<strong>输出：</strong>3
<strong>解释：</strong>3 将会在前 10 个回合中连续获胜。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,9,8,2,3,7,6,4,5], k = 7
<strong>输出：</strong>9
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
<strong>输出：</strong>99
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^6</code></li>
	<li><code>arr</code> 所含的整数 <strong>各不相同</strong> 。</li>
	<li><code>1 &lt;= k &lt;= 10^9</code></li>
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
