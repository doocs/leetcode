# [2895. 最小处理时间](https://leetcode.cn/problems/minimum-processing-time)

[English Version](/solution/2800-2899/2895.Minimum%20Processing%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code> 颗处理器，每颗处理器都有 <code>4</code> 个核心。现有 <code>n * 4</code> 个待执行任务，每个核心只执行 <strong>一个</strong> 任务。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>processorTime</code> ，表示每颗处理器最早空闲时间。另给你一个下标从 <strong>0</strong> 开始的整数数组 <code>tasks</code> ，表示执行每个任务所需的时间。返回所有任务都执行完毕需要的 <strong>最小时间</strong> 。</p>

<p>注意：每个核心独立执行任务。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]
<strong>输出：</strong>16
<strong>解释：</strong>
最优的方案是将下标为 4, 5, 6, 7 的任务分配给第一颗处理器（最早空闲时间 time = 8），下标为 0, 1, 2, 3 的任务分配给第二颗处理器（最早空闲时间 time = 10）。 
第一颗处理器执行完所有任务需要花费的时间 = max(8 + 8, 8 + 7, 8 + 4, 8 + 5) = 16 。
第二颗处理器执行完所有任务需要花费的时间 = max(10 + 2, 10 + 2, 10 + 3, 10 + 1) = 13 。
因此，可以证明执行完所有任务需要花费的最小时间是 16 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
<strong>输出：</strong>23
<strong>解释：</strong>
最优的方案是将下标为 1, 4, 5, 6 的任务分配给第一颗处理器（最早空闲时间 time = 10），下标为 0, 2, 3, 7 的任务分配给第二颗处理器（最早空闲时间 time = 20）。 
第一颗处理器执行完所有任务需要花费的时间 = max(10 + 3, 10 + 5, 10 + 8, 10 + 4) = 18 。 
第二颗处理器执行完所有任务需要花费的时间 = max(20 + 2, 20 + 1, 20 + 2, 20 + 3) = 23 。 
因此，可以证明执行完所有任务需要花费的最小时间是 23 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == processorTime.length &lt;= 25000</code></li>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= processorTime[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>tasks.length == 4 * n</code></li>
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
