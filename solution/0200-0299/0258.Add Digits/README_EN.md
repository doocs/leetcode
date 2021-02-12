# [258. Add Digits](https://leetcode.com/problems/add-digits)

[中文文档](/solution/0200-0299/0258.Add%20Digits/README.md)

## Description

<p>Given a non-negative integer <code>num</code>, repeatedly add all its digits until the result has only one digit.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> <code>38</code>

<strong>Output:</strong> 2 

<strong>Explanation: </strong>The process is like: <code>3 + 8 = 11</code>, <code>1 + 1 = 2</code>. 

&nbsp;            Since <code>2</code> has only one digit, return it.

</pre>

<p><b>Follow up:</b><br />

Could you do it without any loop/recursion in O(1) runtime?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addDigits(self, num: int) -> int:
        return 0 if num == 0 else (num - 1) % 9 + 1
```

### **Java**

```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
