# [246. Strobogrammatic Number](https://leetcode.com/problems/strobogrammatic-number)

[中文文档](/solution/0200-0299/0246.Strobogrammatic%20Number/README.md)

## Description

<p>Given a string <code>num</code> which represents an integer, return <code>true</code> <em>if</em> <code>num</code> <em>is a <strong>strobogrammatic number</strong></em>.</p>

<p>A <strong>strobogrammatic number</strong> is a number that looks the same when rotated <code>180</code> degrees (looked at upside down).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;69&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;88&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;962&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 50</code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>num</code> does not contain any leading zeros except for zero itself.</li>
</ul>

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
