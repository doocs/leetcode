# [635. Design Log Storage System](https://leetcode.com/problems/design-log-storage-system)

[中文文档](/solution/0600-0699/0635.Design%20Log%20Storage%20System/README.md)

## Description
<p>You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: <code>Year:Month:Day:Hour:Minute:Second</code>, for example, <code>2017:01:01:23:59:59</code>. All domains are zero-padded decimal numbers. </p>

<p>Design a log storage system to implement the following functions:</p>

<p><code>void Put(int id, string timestamp)</code>: Given a log's unique id and timestamp, store the log in your storage system.</p>
<br>
<p><code>int[] Retrieve(String start, String end, String granularity)</code>: Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.</p>

<p><b>Example 1:</b><br />
<pre>
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
</pre>
</p>

<p><b>Note:</b><br>
<ol>
<li>There will be at most 300 operations of Put or Retrieve.</li>
<li>Year ranges from [2000,2017]. Hour ranges from [00,23].</li>
<li>Output for Retrieve has no order required.</li>
</ol>
</p>


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