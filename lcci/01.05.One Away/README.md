# [面试题 01.05. 一次编辑](https://leetcode-cn.com/problems/one-away-lcci)

[English Version](/lcci/01.05.One%20Away/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 
first = &quot;pale&quot;
second = &quot;ple&quot;
<strong>输出:</strong> True</pre>

<p>&nbsp;</p>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 
first = &quot;pales&quot;
second = &quot;pal&quot;
<strong>输出:</strong> False
</pre>


## 解法
<!-- 这里可写通用的实现逻辑 -->
遍历两个字符串，逐个字符比较判断。

<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        n1, n2 = len(first), len(second)
        if abs(n1 - n2) > 1:
            return False
        if n1 + n2 <= 2:
            return True
        if first[0] != second[0]:
            if n1 == n2:
                return first[1:] == second[1:]
            else:
                return first[1:] == second or second[1:] == first
        else:
            return self.oneEditAway(first[1:], second[1:])
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        int differ = Math.abs(n1 - n2);
        if (differ > 1) {
            return false;
        }
        if (n1 + n2 <= 2) {
            return true;
        }
        if (first.charAt(0) != second.charAt(0)) {
            if (n1 == n2) {
                return first.substring(1).equals(second.substring(1));
            } else {
                return first.substring(1).equals(second) || second.substring(1).equals(first);
            }
        } else {
            return oneEditAway(first.substring(1), second.substring(1));
        }
    }
}
```

### **...**
```

```

<!-- tabs:end -->