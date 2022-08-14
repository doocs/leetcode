# [1753. 移除石子的最大得分](https://leetcode.cn/problems/maximum-score-from-removing-stones)

[English Version](/solution/1700-1799/1753.Maximum%20Score%20From%20Removing%20Stones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在玩一个单人游戏，面前放置着大小分别为 <code>a</code>​​​​​​、<code>b</code> 和 <code>c</code>​​​​​​ 的 <strong>三堆</strong> 石子。</p>

<p>每回合你都要从两个 <strong>不同的非空堆</strong> 中取出一颗石子，并在得分上加 <code>1</code> 分。当存在 <strong>两个或更多</strong> 的空堆时，游戏停止。</p>

<p>给你三个整数 <code>a</code> 、<code>b</code> 和 <code>c</code> ，返回可以得到的 <strong>最大分数</strong> 。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = 4, c = 6
<strong>输出：</strong>6
<strong>解释：</strong>石子起始状态是 (2, 4, 6) ，最优的一组操作是：
- 从第一和第三堆取，石子状态现在是 (1, 4, 5)
- 从第一和第三堆取，石子状态现在是 (0, 4, 4)
- 从第二和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：6 分 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 4, c = 6
<strong>输出：</strong>7
<strong>解释：</strong>石子起始状态是 (4, 4, 6) ，最优的一组操作是：
- 从第一和第二堆取，石子状态现在是 (3, 3, 6)
- 从第一和第三堆取，石子状态现在是 (2, 3, 5)
- 从第一和第三堆取，石子状态现在是 (1, 3, 4)
- 从第一和第三堆取，石子状态现在是 (0, 3, 3)
- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
总分：7 分 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 8, c = 8
<strong>输出：</strong>8
<strong>解释：</strong>最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= a, b, c <= 10<sup>5</sup></code></li>
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
