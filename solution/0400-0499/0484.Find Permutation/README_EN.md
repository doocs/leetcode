# [484. Find Permutation](https://leetcode.com/problems/find-permutation)

[中文文档](/solution/0400-0499/0484.Find%20Permutation/README.md)

## Description
<p>
By now, you are given a <b>secret signature</b> consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our <b>secret signature</b> was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" <b>secret signature</b>.
</p>

<p>
On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given <b>secret signature</b> in the input.
</p>

<p><b>Example 1:</b><br />
<pre>
<b>Input:</b> "I"
<b>Output:</b> [1,2]
<b>Explanation:</b> [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
</pre>
</p>

<p><b>Example 2:</b><br />
<pre>
<b>Input:</b> "DI"
<b>Output:</b> [2,1,3]
<b>Explanation:</b> Both [2,1,3] and [3,1,2] can construct the secret signature "DI", </br>but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
</pre>
</p>

<p><b>Note:</b>
<li>The input string will only contain the character 'D' and 'I'.</li>
<li>The length of input string is a positive integer and will not exceed 10,000</li>
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