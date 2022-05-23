# [1648. 销售价值减少的颜色球](https://leetcode.cn/problems/sell-diminishing-valued-colored-balls)

[English Version](/solution/1600-1699/1648.Sell%20Diminishing-Valued%20Colored%20Balls/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一些球的库存 <code>inventory</code> ，里面包含着不同颜色的球。一个顾客想要 <strong>任意颜色</strong> 总数为 <code>orders</code> 的球。</p>

<p>这位顾客有一种特殊的方式衡量球的价值：每个球的价值是目前剩下的 <strong>同色球</strong> 的数目。比方说还剩下 <code>6</code> 个黄球，那么顾客买第一个黄球的时候该黄球的价值为 <code>6</code> 。这笔交易以后，只剩下 <code>5</code> 个黄球了，所以下一个黄球的价值为 <code>5</code> （也就是球的价值随着顾客购买同色球是递减的）</p>

<p>给你整数数组 <code>inventory</code> ，其中 <code>inventory[i]</code> 表示第 <code>i</code> 种颜色球一开始的数目。同时给你整数 <code>orders</code> ，表示顾客总共想买的球数目。你可以按照 <strong>任意顺序</strong> 卖球。</p>

<p>请你返回卖了 <code>orders</code> 个球以后 <strong>最大</strong> 总价值之和。由于答案可能会很大，请你返回答案对 <code>10<sup>9</sup> + 7</code> <strong>取余数</strong> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1648.Sell%20Diminishing-Valued%20Colored%20Balls/images/jj.gif" style="width: 480px; height: 270px;" />
<pre>
<b>输入：</b>inventory = [2,5], orders = 4
<b>输出：</b>14
<b>解释：</b>卖 1 个第一种颜色的球（价值为 2 )，卖 3 个第二种颜色的球（价值为 5 + 4 + 3）。
最大总和为 2 + 5 + 4 + 3 = 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>inventory = [3,5], orders = 6
<b>输出：</b>19
<strong>解释：</strong>卖 2 个第一种颜色的球（价值为 3 + 2），卖 4 个第二种颜色的球（价值为 5 + 4 + 3 + 2）。
最大总和为 3 + 2 + 5 + 4 + 3 + 2 = 19 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>inventory = [2,8,4,10,6], orders = 20
<b>输出：</b>110
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>inventory = [1000000000], orders = 1000000000
<b>输出：</b>21
<strong>解释：</strong>卖 1000000000 次第一种颜色的球，总价值为 500000000500000000 。 500000000500000000 对 10<sup>9 </sup>+ 7 取余为 21 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= inventory.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= inventory[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= orders <= min(sum(inventory[i]), 10<sup>9</sup>)</code></li>
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
