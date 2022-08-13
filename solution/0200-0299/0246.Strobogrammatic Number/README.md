# [246. 中心对称数](https://leetcode.cn/problems/strobogrammatic-number)

[English Version](/solution/0200-0299/0246.Strobogrammatic%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>中心对称数是指一个数字在旋转了&nbsp;180 度之后看起来依旧相同的数字（或者上下颠倒地看）。</p>

<p>请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> num = &quot;69&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> num = &quot;88&quot;
<strong>输出:</strong> true</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> num = &quot;962&quot;
<strong>输出:</strong> false</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>num = &quot;1&quot;
<strong>输出：</strong>true
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
