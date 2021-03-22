# [551. Student Attendance Record I](https://leetcode.com/problems/student-attendance-record-i)

[中文文档](/solution/0500-0599/0551.Student%20Attendance%20Record%20I/README.md)

## Description

You are given a string representing an attendance record for a student. The record only contains the following three characters:

<p>

<ol>

<li><b>'A'</b> : Absent. </li>

<li><b>'L'</b> : Late.</li>

<li> <b>'P'</b> : Present. </li>

</ol>

</p>

<p>

A student could be rewarded if his attendance record doesn't contain <b>more than one 'A' (absent)</b> or <b>more than two continuous 'L' (late)</b>. </p>

<p>You need to return whether the student could be rewarded according to his attendance record.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> "PPALLP"

<b>Output:</b> True

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> "PPALLL"

<b>Output:</b> False

</pre>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkRecord(self, s: str) -> bool:
        return s.count('A') <= 1 and 'LLL' not in s
```

### **Java**

```java
class Solution {
    public boolean checkRecord(String s) {
        int i = s.indexOf("A");
        return (i == -1 || s.lastIndexOf("A") == i) && !s.contains("LLL");
    }
}
```

### **...**

```

```

<!-- tabs:end -->
