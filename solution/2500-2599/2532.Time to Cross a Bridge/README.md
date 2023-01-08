# [2532. 过桥的时间](https://leetcode.cn/problems/time-to-cross-a-bridge)

[English Version](/solution/2500-2599/2532.Time%20to%20Cross%20a%20Bridge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>共有 <code>k</code> 位工人计划将 <code>n</code> 个箱子从旧仓库移动到新仓库。给你两个整数 <code>n</code> 和 <code>k</code>，以及一个二维整数数组 <code>time</code> ，数组的大小为 <code>k x 4</code> ，其中 <code>time[i] = [leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub>]</code> 。</p>

<p>一条河将两座仓库分隔，只能通过一座桥通行。旧仓库位于河的右岸，新仓库在河的左岸。开始时，所有 <code>k</code> 位工人都在桥的左侧等待。为了移动这些箱子，第 <code>i</code> 位工人（下标从 <strong>0</strong> 开始）可以：</p>

<ul>
	<li>从左岸（新仓库）跨过桥到右岸（旧仓库），用时 <code>leftToRight<sub>i</sub></code> 分钟。</li>
	<li>从旧仓库选择一个箱子，并返回到桥边，用时 <code>pickOld<sub>i</sub></code> 分钟。不同工人可以同时搬起所选的箱子。</li>
	<li>从右岸（旧仓库）跨过桥到左岸（新仓库），用时 <code>rightToLeft<sub>i</sub></code> 分钟。</li>
	<li>将箱子放入新仓库，并返回到桥边，用时 <code>putNew<sub>i</sub></code> 分钟。不同工人可以同时放下所选的箱子。</li>
</ul>

<p>如果满足下面任一条件，则认为工人 <code>i</code> 的 <strong>效率低于</strong> 工人 <code>j</code> ：</p>

<ul>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> &gt; leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code></li>
	<li><code>leftToRight<sub>i</sub> + rightToLeft<sub>i</sub> == leftToRight<sub>j</sub> + rightToLeft<sub>j</sub></code> 且 <code>i &gt; j</code></li>
</ul>

<p>工人通过桥时需要遵循以下规则：</p>

<ul>
	<li>如果工人 <code>x</code> 到达桥边时，工人 <code>y</code> 正在过桥，那么工人 <code>x</code> 需要在桥边等待。</li>
	<li>如果没有正在过桥的工人，那么在桥右边等待的工人可以先过桥。如果同时有多个工人在右边等待，那么 <strong>效率最低</strong> 的工人会先过桥。</li>
	<li>如果没有正在过桥的工人，且桥右边也没有在等待的工人，同时旧仓库还剩下至少一个箱子需要搬运，此时在桥左边的工人可以过桥。如果同时有多个工人在左边等待，那么 <strong>效率最低</strong> 的工人会先过桥。</li>
</ul>

<p>所有 <code>n</code> 个盒子都需要放入新仓库，<span class="text-only" data-eleid="8" style="white-space: pre;">请你返回最后一个搬运箱子的工人 </span><strong><span class="text-only" data-eleid="9" style="white-space: pre;">到达河左岸</span></strong><span class="text-only" data-eleid="10" style="white-space: pre;"> 的时间。</span></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
从 0 到 1 ：工人 2 从左岸过桥到达右岸。
从 1 到 2 ：工人 2 从旧仓库搬起一个箱子。
从 2 到 6 ：工人 2 从右岸过桥到达左岸。
从 6 到 7 ：工人 2 将箱子放入新仓库。
整个过程在 7 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
<strong>输出：</strong>50
<strong>解释：</strong>
从 0 到 10 ：工人 1 从左岸过桥到达右岸。
从 10 到 20 ：工人 1 从旧仓库搬起一个箱子。
从 10 到 11 ：工人 0 从左岸过桥到达右岸。
从 11 到 20 ：工人 0 从旧仓库搬起一个箱子。
从 20 到 30 ：工人 1 从右岸过桥到达左岸。
从 30 到 40 ：工人 1 将箱子放入新仓库。
从 30 到 31 ：工人 0 从右岸过桥到达左岸。
从 31 到 39 ：工人 0 将箱子放入新仓库。
从 39 到 40 ：工人 0 从左岸过桥到达右岸。
从 40 到 49 ：工人 0 从旧仓库搬起一个箱子。
从 49 到 50 ：工人 0 从右岸过桥到达左岸。
从 50 到 58 ：工人 0 将箱子放入新仓库。
整个过程在 58 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 50 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>4</sup></code></li>
	<li><code>time.length == k</code></li>
	<li><code>time[i].length == 4</code></li>
	<li><code>1 &lt;= leftToRight<sub>i</sub>, pickOld<sub>i</sub>, rightToLeft<sub>i</sub>, putNew<sub>i</sub> &lt;= 1000</code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
