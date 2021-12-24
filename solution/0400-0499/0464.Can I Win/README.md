# [464. 我能赢吗](https://leetcode-cn.com/problems/can-i-win)

[English Version](/solution/0400-0499/0464.Can%20I%20Win/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 &quot;100 game&quot; 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到或超过 100 的玩家，即为胜者。</p>

<p>如果我们将游戏规则改为 &ldquo;玩家不能重复使用整数&rdquo; 呢？</p>

<p>例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 &gt;= 100。</p>

<p>给定一个整数&nbsp;<code>maxChoosableInteger</code>&nbsp;（整数池中可选择的最大数）和另一个整数&nbsp;<code>desiredTotal</code>（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？</p>

<p>你可以假设&nbsp;<code>maxChoosableInteger</code>&nbsp;不会大于 20，&nbsp;<code>desiredTotal</code>&nbsp;不会大于 300。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
maxChoosableInteger = 10
desiredTotal = 11

<strong>输出：</strong>
false

<strong>解释：
</strong>无论第一个玩家选择哪个整数，他都会失败。
第一个玩家可以选择从 1 到 10 的整数。
如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
第二个玩家可以通过选择整数 10（那么累积和为 11 &gt;= desiredTotal），从而取得胜利.
同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
</pre>

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
