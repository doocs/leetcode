# [1199. 建造街区的最短时间](https://leetcode.cn/problems/minimum-time-to-build-blocks)

[English Version](/solution/1100-1199/1199.Minimum%20Time%20to%20Build%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是个城市规划工作者，手里负责管辖一系列的街区。在这个街区列表中&nbsp;<code>blocks[i] = t</code>&nbsp;意味着第 &nbsp;<code>i</code>&nbsp;个街区需要&nbsp;<code>t</code>&nbsp;个单位的时间来建造。</p>

<p>由于一个街区只能由一个工人来完成建造。</p>

<p>所以，一个工人要么需要再召唤一个工人（工人数增加 1）；要么建造完一个街区后回家。这两个决定都需要花费一定的时间。</p>

<p>一个工人再召唤一个工人所花费的时间由整数&nbsp;<code>split</code>&nbsp;给出。</p>

<p>注意：如果两个工人同时召唤别的工人，那么他们的行为是并行的，所以时间花费仍然是&nbsp;<code>split</code>。</p>

<p>最开始的时候只有&nbsp;<strong>一个&nbsp;</strong>工人，请你最后输出建造完所有街区所需要的最少时间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>blocks = [1], split = 1
<strong>输出：</strong>1
<strong>解释：</strong>我们使用 1 个工人在 1 个时间单位内来建完 1 个街区。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>blocks = [1,2], split = 5
<strong>输出：</strong>7
<strong>解释：</strong>我们用 5 个时间单位将这个工人分裂为 2 个工人，然后指派每个工人分别去建造街区，从而时间花费为 5 + max(1, 2) = 7
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>blocks = [1,2,3], split = 1
<strong>输出：</strong>4
<strong>解释：
</strong>将 1 个工人分裂为 2 个工人，然后指派第一个工人去建造最后一个街区，并将第二个工人分裂为 2 个工人。
然后，用这两个未分派的工人分别去建造前两个街区。
时间花费为 1 + max(3, 1 + max(1, 2)) = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= blocks.length &lt;= 1000</code></li>
	<li><code>1 &lt;= blocks[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= split &lt;= 100</code></li>
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
