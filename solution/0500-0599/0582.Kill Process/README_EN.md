# [582. Kill Process](https://leetcode.com/problems/kill-process)

[中文文档](/solution/0500-0599/0582.Kill%20Process/README.md)

## Description

<p>Given <b>n</b> processes, each process has a unique <b>PID (process id)</b> and its <b>PPID (parent process id)</b>.

<p>Each process only has one parent process, but may have one or more children processes. This is just like a tree structure.  Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.</p>

<p>We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID. </p>
 
<p>Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.</p>

<p><b>Example 1:</b><br />
<pre>
<b>Input:</b> 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
<b>Output:</b> [5,10]
<b>Explanation:</b> 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
</pre>
</p>

<p><b>Note:</b><br>
<ol>
<li>The given kill id is guaranteed to be one of the given PIDs.</li>
<li>n >= 1.</li>
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
