# [2141. 同时运行 N 台电脑的最长时间](https://leetcode.cn/problems/maximum-running-time-of-n-computers)

[English Version](/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有&nbsp;<code>n</code>&nbsp;台电脑。给你整数&nbsp;<code>n</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>batteries</code>&nbsp;，其中第&nbsp;<code>i</code>&nbsp;个电池可以让一台电脑 <strong>运行&nbsp;</strong><code>batteries[i]</code>&nbsp;分钟。你想使用这些电池让&nbsp;<strong>全部</strong>&nbsp;<code>n</code>&nbsp;台电脑 <b>同时</b>&nbsp;运行。</p>

<p>一开始，你可以给每台电脑连接 <strong>至多一个电池</strong>&nbsp;。然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 <strong>任意次</strong>&nbsp;。新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池。断开连接和连接新的电池不会花费任何时间。</p>

<p>注意，你不能给电池充电。</p>

<p>请你返回你可以让 <code>n</code>&nbsp;台电脑同时运行的 <strong>最长</strong>&nbsp;分钟数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example1-fit.png" style="width: 762px; height: 150px;"></p>

<pre><b>输入：</b>n = 2, batteries = [3,3,3]
<b>输出：</b>4
<b>解释：</b>
一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。
2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。
在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。
在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。
我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example2.png" style="width: 629px; height: 150px;"></p>

<pre><b>输入：</b>n = 2, batteries = [1,1,1,1]
<b>输出：</b>2
<b>解释：</b>
一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 2 连接。
一分钟后，电池 0 和电池 2 同时耗尽，所以你需要将它们断开连接，并将电池 1 和第一台电脑连接，电池 3 和第二台电脑连接。
1 分钟后，电池 1 和电池 3 也耗尽了，所以两台电脑都无法继续运行。
我们最多能让两台电脑同时运行 2 分钟，所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= batteries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= batteries[i] &lt;= 10<sup>9</sup></code></li>
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

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
