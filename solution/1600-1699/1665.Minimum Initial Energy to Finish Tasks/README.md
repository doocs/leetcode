# [1665. 完成所有任务的最少初始能量](https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks)

[English Version](/solution/1600-1699/1665.Minimum%20Initial%20Energy%20to%20Finish%20Tasks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个任务数组 <code>tasks</code> ，其中 <code>tasks[i] = [actual<sub>i</sub>, minimum<sub>i</sub>]</code> ：</p>

<ul>
	<li><code>actual<sub>i</sub></code> 是完成第 <code>i</code> 个任务 <strong>需要耗费</strong> 的实际能量。</li>
	<li><code>minimum<sub>i</sub></code> 是开始第 <code>i</code> 个任务前需要达到的最低能量。</li>
</ul>

<p>比方说，如果任务为 <code>[10, 12]</code> 且你当前的能量为 <code>11</code> ，那么你不能开始这个任务。如果你当前的能量为 <code>13</code> ，你可以完成这个任务，且完成它后剩余能量为 <code>3</code> 。</p>

<p>你可以按照 <strong>任意顺序</strong> 完成任务。</p>

<p>请你返回完成所有任务的 <strong>最少</strong> 初始能量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [[1,2],[2,4],[4,8]]
<b>输出：</b>8
<strong>解释：</strong>
一开始有 8 能量，我们按照如下顺序完成任务：
    - 完成第 3 个任务，剩余能量为 8 - 4 = 4 。
    - 完成第 2 个任务，剩余能量为 4 - 2 = 2 。
    - 完成第 1 个任务，剩余能量为 2 - 1 = 1 。
注意到尽管我们有能量剩余，但是如果一开始只有 7 能量是不能完成所有任务的，因为我们无法开始第 3 个任务。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
<b>输出：</b>32
<strong>解释：</strong>
一开始有 32 能量，我们按照如下顺序完成任务：
    - 完成第 1 个任务，剩余能量为 32 - 1 = 31 。
    - 完成第 2 个任务，剩余能量为 31 - 2 = 29 。
    - 完成第 3 个任务，剩余能量为 29 - 10 = 19 。
    - 完成第 4 个任务，剩余能量为 19 - 10 = 9 。
    - 完成第 5 个任务，剩余能量为 9 - 8 = 1 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]]
<b>输出：</b>27
<strong>解释：</strong>
一开始有 27 能量，我们按照如下顺序完成任务：
    - 完成第 5 个任务，剩余能量为 27 - 5 = 22 。
    - 完成第 2 个任务，剩余能量为 22 - 2 = 20 。
    - 完成第 3 个任务，剩余能量为 20 - 3 = 17 。
    - 完成第 1 个任务，剩余能量为 17 - 1 = 16 。
    - 完成第 4 个任务，剩余能量为 16 - 4 = 12 。
    - 完成第 6 个任务，剩余能量为 12 - 6 = 6 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= actual<sub>​i</sub> &lt;= minimum<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
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
