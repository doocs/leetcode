# [822. 翻转卡片游戏](https://leetcode-cn.com/problems/card-flipping-game)

[English Version](/solution/0800-0899/0822.Card%20Flipping%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在桌子上有 <code>N</code> 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。</p>

<p>我们可以先翻转任意张卡片，然后选择其中一张卡片。</p>

<p>如果选中的那张卡片背面的数字 <code>X</code> 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。</p>

<p>哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。</p>

<p>其中, <code>fronts[i]</code>&nbsp;和&nbsp;<code>backs[i]</code>&nbsp;分别代表第&nbsp;<code>i</code>&nbsp;张卡片的正面和背面的数字。</p>

<p>如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
<strong>输出：</strong><code>2</code>
<strong>解释：</strong>假设我们翻转第二张卡片，那么在正面的数变成了 <code>[1,3,4,4,7]</code> ， 背面的数变成了 <code>[1,2,4,1,3]。</code>
接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= fronts.length == backs.length&nbsp;&lt;=&nbsp;1000</code></li>
	<li><code>1 &lt;=&nbsp;fronts[i]&nbsp;&lt;= 2000</code></li>
	<li><code>1 &lt;= backs[i]&nbsp;&lt;= 2000</code></li>
</ol>

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
