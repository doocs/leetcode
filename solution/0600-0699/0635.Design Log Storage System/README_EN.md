# [635. Design Log Storage System](https://leetcode.com/problems/design-log-storage-system)

[中文文档](/solution/0600-0699/0635.Design%20Log%20Storage%20System/README.md)

## Description

<p>You are given several logs, where each log contains a unique ID and timestamp. Timestamp is a string that has the following format: <code>Year:Month:Day:Hour:Minute:Second</code>, for example, <code>2017:01:01:23:59:59</code>. All domains are zero-padded decimal numbers.</p>

<p>Implement the <code>LogSystem</code> class:</p>

<ul>
	<li><code>LogSystem()</code> Initializes the <code>LogSystem</code><b> </b>object.</li>
	<li><code>void put(int id, string timestamp)</code> Stores the given log <code>(id, timestamp)</code> in your storage system.</li>
	<li><code>int[] retrieve(string start, string end, string granularity)</code> Returns the IDs of the logs whose timestamps are within the range from <code>start</code> to <code>end</code> inclusive. <code>start</code> and <code>end</code> all have the same format as <code>timestamp</code>, and <code>granularity</code> means how precise the range should be (i.e. to the exact <code>Day</code>, <code>Minute</code>, etc.). For example, <code>start = &quot;2017:01:01:23:59:59&quot;</code>, <code>end = &quot;2017:01:02:23:59:59&quot;</code>, and <code>granularity = &quot;Day&quot;</code> means that we need to find the logs within the inclusive range from <strong>Jan. 1st 2017</strong> to <strong>Jan. 2nd 2017</strong>, and the <code>Hour</code>, <code>Minute</code>, and <code>Second</code> for each log entry can be ignored.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;LogSystem&quot;, &quot;put&quot;, &quot;put&quot;, &quot;put&quot;, &quot;retrieve&quot;, &quot;retrieve&quot;]
[[], [1, &quot;2017:01:01:23:59:59&quot;], [2, &quot;2017:01:01:22:59:59&quot;], [3, &quot;2016:01:01:00:00:00&quot;], [&quot;2016:01:01:01:01:01&quot;, &quot;2017:01:01:23:00:00&quot;, &quot;Year&quot;], [&quot;2016:01:01:01:01:01&quot;, &quot;2017:01:01:23:00:00&quot;, &quot;Hour&quot;]]
<strong>Output</strong>
[null, null, null, null, [3, 2, 1], [2, 1]]

<strong>Explanation</strong>
LogSystem logSystem = new LogSystem();
logSystem.put(1, &quot;2017:01:01:23:59:59&quot;);
logSystem.put(2, &quot;2017:01:01:22:59:59&quot;);
logSystem.put(3, &quot;2016:01:01:00:00:00&quot;);

// return [3,2,1], because you need to return all logs between 2016 and 2017.
logSystem.retrieve(&quot;2016:01:01:01:01:01&quot;, &quot;2017:01:01:23:00:00&quot;, &quot;Year&quot;);

// return [2,1], because you need to return all logs between Jan. 1, 2016 01:XX:XX and Jan. 1, 2017 23:XX:XX.
// Log 3 is not returned because Jan. 1, 2016 00:00:00 comes before the start of the range.
logSystem.retrieve(&quot;2016:01:01:01:01:01&quot;, &quot;2017:01:01:23:00:00&quot;, &quot;Hour&quot;);
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= id &lt;= 500</code></li>
	<li><code>2000 &lt;= Year &lt;= 2017</code></li>
	<li><code>1 &lt;= Month &lt;= 12</code></li>
	<li><code>1 &lt;= Day &lt;= 31</code></li>
	<li><code>0 &lt;= Hour &lt;= 23</code></li>
	<li><code>0 &lt;= Minute, Second &lt;= 59</code></li>
	<li><code>granularity</code> is one of the values <code>[&quot;Year&quot;, &quot;Month&quot;, &quot;Day&quot;, &quot;Hour&quot;, &quot;Minute&quot;, &quot;Second&quot;]</code>.</li>
	<li>At most <code>500</code> calls will be made to <code>put</code> and <code>retrieve</code>.</li>
</ul>

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
