# [277. Find the Celebrity](https://leetcode.com/problems/find-the-celebrity)

[中文文档](/solution/0200-0299/0277.Find%20the%20Celebrity/README.md)

## Description

<p>Suppose you are at a party with <code>n</code> people (labeled from <code>0</code> to <code>n - 1</code>), and among them, there may exist one celebrity. The definition of a celebrity is that all the other <code>n - 1</code> people know him/her, but he/she does not know any of them.</p>

<p>Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: &quot;Hi, A. Do you know B?&quot; to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).</p>

<p>You are given a helper function <code>bool knows(a, b)</code> which tells you whether A knows B. Implement a function <code>int findCelebrity(n)</code>. There will be exactly one celebrity if he/she is in the party. Return the celebrity&#39;s label if there is a celebrity in the party. If there is no celebrity, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0277.Find%20the%20Celebrity/images/277_example_1_bold.png" style="width: 186px; height: 181px;" />
<pre>
<strong>Input:</strong> graph = [[1,1,0],[0,1,0],[1,1,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0277.Find%20the%20Celebrity/images/277_example_2.png" style="width: 193px; height: 192px;" />
<pre>
<strong>Input:</strong> graph = [[1,0,1],[1,1,0],[0,1,1]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no celebrity.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>n == graph[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>graph[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>graph[i][i] == 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> If the maximum number of allowed calls to the API <code>knows</code> is <code>3 * n</code>, could you find a solution without exceeding the maximum number of calls?

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
