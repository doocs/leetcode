# [2747. 统计没有收到请求的服务器数目](https://leetcode.cn/problems/count-zero-request-servers)

[English Version](/solution/2700-2799/2747.Count%20Zero%20Request%20Servers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示服务器的总数目，再给你一个下标从 <strong>0</strong>&nbsp;开始的 <strong>二维</strong>&nbsp;整数数组&nbsp;<code>logs</code>&nbsp;，其中&nbsp;<code>logs[i] = [server_id, time]</code>&nbsp;表示 id 为&nbsp;<code>server_id</code>&nbsp;的服务器在&nbsp;<code>time</code>&nbsp;时收到了一个请求。</p>

<p>同时给你一个整数&nbsp;<code>x</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>queries</code>&nbsp; 。</p>

<p>请你返回一个长度等于&nbsp;<code>queries.length</code>&nbsp;的数组&nbsp;<code>arr</code>&nbsp;，其中&nbsp;<code>arr[i]</code>&nbsp;表示在时间区间&nbsp;<code>[queries[i] - x, queries[i]]</code>&nbsp;内没有收到请求的服务器数目。</p>

<p>注意时间区间是个闭区间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
<b>输出：</b>[1,2]
<b>解释：</b>
对于 queries[0]：id 为 1 和 2 的服务器在区间 [5, 10] 内收到了请求，所以只有服务器 3 没有收到请求。
对于 queries[1]：id 为 2 的服务器在区间 [6,11] 内收到了请求，所以 id 为 1 和 3 的服务器在这个时间段内没有收到请求。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
<b>输出：</b>[0,1]
<b>解释：</b>
对于 queries[0]：区间 [1, 3] 内所有服务器都收到了请求。
对于 queries[1]：只有 id 为 3 的服务器在区间 [2,4] 内没有收到请求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>logs[i].length == 2</code></li>
	<li><code>1 &lt;= logs[i][0] &lt;= n</code></li>
	<li><code>1 &lt;= logs[i][1] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>5</sup></code></li>
	<li><code>x &lt;&nbsp;queries[i]&nbsp;&lt;= 10<sup>6</sup></code></li>
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
