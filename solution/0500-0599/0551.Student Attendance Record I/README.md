# [551. 学生出勤记录 I](https://leetcode-cn.com/problems/student-attendance-record-i)

[English Version](/solution/0500-0599/0551.Student%20Attendance%20Record%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：</p>

<ol>
	<li><strong>&#39;A&#39;</strong> : Absent，缺勤</li>
	<li><strong>&#39;L&#39;</strong> : Late，迟到</li>
	<li><strong>&#39;P&#39;</strong> : Present，到场</li>
</ol>

<p>如果一个学生的出勤记录中不<strong>超过一个&#39;A&#39;(缺勤)</strong>并且<strong>不超过两个连续的&#39;L&#39;(迟到)</strong>,那么这个学生会被奖赏。</p>

<p>你需要根据这个学生的出勤记录判断他是否会被奖赏。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> &quot;PPALLP&quot;
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> &quot;PPALLL&quot;
<strong>输出:</strong> False
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkRecord(self, s: str) -> bool:
        return s.count('A') <= 1 and 'LLL' not in s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
