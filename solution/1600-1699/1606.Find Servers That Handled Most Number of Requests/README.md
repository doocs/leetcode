# [1606. 找到处理最多请求的服务器](https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests)

[English Version](/solution/1600-1699/1606.Find%20Servers%20That%20Handled%20Most%20Number%20of%20Requests/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>k</code> 个服务器，编号为 <code>0</code> 到 <code>k-1</code> ，它们可以同时处理多个请求组。每个服务器有无穷的计算能力但是 <strong>不能同时处理超过一个请求</strong> 。请求分配到服务器的规则如下：</p>

<ul>
	<li>第 <code>i</code> （序号从 0 开始）个请求到达。</li>
	<li>如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。</li>
	<li>如果第 <code>(i % k)</code> 个服务器空闲，那么对应服务器会处理该请求。</li>
	<li>否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 <code>i</code> 个服务器在忙，那么会查看第 <code>(i+1)</code> 个服务器，第 <code>(i+2)</code> 个服务器等等。</li>
</ul>

<p>给你一个 <strong>严格递增</strong> 的正整数数组 <code>arrival</code> ，表示第 <code>i</code> 个任务的到达时间，和另一个数组 <code>load</code> ，其中 <code>load[i]</code> 表示第 <code>i</code> 个请求的工作量（也就是服务器完成它所需要的时间）。你的任务是找到 <strong>最繁忙的服务器</strong> 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。</p>

<p>请你返回包含所有 <strong>最繁忙服务器</strong> 序号的列表，你可以以任意顺序返回这个列表。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1606.Find%20Servers%20That%20Handled%20Most%20Number%20of%20Requests/images/load-1.png" style="height: 221px; width: 389px;" /></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
<strong>输出：</strong>[1] 
<strong>解释：</strong>
所有服务器一开始都是空闲的。
前 3 个请求分别由前 3 台服务器依次处理。
请求 3 进来的时候，服务器 0 被占据，所以它呗安排到下一台空闲的服务器，也就是服务器 1 。
请求 4 进来的时候，由于所有服务器都被占据，该请求被舍弃。
服务器 0 和 2 分别都处理了一个请求，服务器 1 处理了两个请求。所以服务器 1 是最忙的服务器。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
<strong>输出：</strong>[0]
<strong>解释：</strong>
前 3 个请求分别被前 3 个服务器处理。
请求 3 进来，由于服务器 0 空闲，它被服务器 0 处理。
服务器 0 处理了两个请求，服务器 1 和 2 分别处理了一个请求。所以服务器 0 是最忙的服务器。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3], load = [10,12,11]
<strong>输出：</strong>[0,1,2]
<strong>解释：</strong>每个服务器分别处理了一个请求，所以它们都是最忙的服务器。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>k = 3, arrival = [1,2,3,4,8,9,10], load = [5,2,10,3,1,2,2]
<strong>输出：</strong>[1]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>k = 1, arrival = [1], load = [1]
<strong>输出：</strong>[0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
	<li><code>1 <= arrival.length, load.length <= 10<sup>5</sup></code></li>
	<li><code>arrival.length == load.length</code></li>
	<li><code>1 <= arrival[i], load[i] <= 10<sup>9</sup></code></li>
	<li><code>arrival</code> 保证 <strong>严格递增</strong> 。</li>
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
