# [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls)

## Description
<p>Write a class <code>RecentCounter</code> to count recent requests.</p>



<p>It has only one method:&nbsp;<code>ping(int t)</code>, where t represents some time in milliseconds.</p>



<p>Return the number of <code>ping</code>s that have been made from 3000 milliseconds ago until now.</p>



<p>Any ping with time in <code>[t - 3000, t]</code> will count, including the current ping.</p>



<p>It is guaranteed that every call to <code>ping</code> uses a strictly larger value of&nbsp;<code>t</code> than before.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>inputs = <span id="example-input-1-1">[&quot;RecentCounter&quot;,&quot;ping&quot;,&quot;ping&quot;,&quot;ping&quot;,&quot;ping&quot;]</span>, inputs = <span id="example-input-1-2">[[],[1],[100],[3001],[3002]]</span>

<strong>Output: </strong><span id="example-output-1">[null,1,2,3,3]</span></pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li>Each test case will have at most <code>10000</code> calls to <code>ping</code>.</li>

	<li>Each test case will call&nbsp;<code>ping</code> with strictly increasing values of <code>t</code>.</li>

	<li>Each call to ping will have <code>1 &lt;= t &lt;= 10^9</code>.</li>

</ol>



<div>

<p>&nbsp;</p>

</div>


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