# [246. Strobogrammatic Number](https://leetcode.com/problems/strobogrammatic-number)

[中文文档](/solution/0200-0299/0246.Strobogrammatic%20Number/README.md)

## Description

<p>A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).</p>

<p>Write a function to determine if a number is strobogrammatic. The number is represented as a string.</p>

<p> </p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> num = "69"
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> num = "88"
<strong>Output:</strong> true
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> num = "962"
<strong>Output:</strong> false
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> num = "1"
<strong>Output:</strong> true
</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isStrobogrammatic(self, num: str) -> bool:
        def match(a, b):
            if a in {'0', '1', '8'}:
                return a == b
            if a == '6':
                return b == '9'
            if a == '9':
                return b == '6'
            return False
        n = len(num)
        i, j = 0, n - 1
        while i <= j:
            if not match(num[i], num[j]):
                return False
            i += 1
            j -= 1
        return True
```

### **Java**

```java
class Solution {
    public boolean isStrobogrammatic(String num) {
        int n = num.length();
        for (int i = 0, j = n - 1; i <= j; ++i, --j) {
            if (!match(num.charAt(i), num.charAt(j))) return false;
        }
        return true;
    }

    private boolean match(char a, char b) {
        switch (a) {
            case '0':
            case '1':
            case '8':
                return a == b;
            case '6':
                return b == '9';
            case '9':
                return b == '6';
            default:
                return false;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
