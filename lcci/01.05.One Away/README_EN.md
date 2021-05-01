# [01.05. One Away](https://leetcode-cn.com/problems/one-away-lcci)

[中文文档](/lcci/01.05.One%20Away/README.md)

## Description

<p>There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.</p>

<p><strong>Example&nbsp;1:</strong></p>

<pre>

<strong>Input:</strong> 

first = &quot;pale&quot;

second = &quot;ple&quot;

<strong>Output:</strong> True

</pre>

<p><strong>Example&nbsp;2:</strong></p>

<pre>

<strong>Input:</strong> 

first = &quot;pales&quot;

second = &quot;pal&quot;

<strong>Output:</strong> False

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        n1, n2 = len(first), len(second)
        diff = n1 - n2
        if abs(diff) > 1:
            return False
        i, j, op = 0, 0, 1
        while i < n1 and j < n2:
            not_same = first[i] != second[j]
            if not_same:
                if diff == 1:
                    i += 1
                elif diff == -1:
                    j += 1
                else:
                    i += 1
                    j += 1
                op -= 1
            else:
                i += 1
                j += 1
            if op < 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        int diff = n1 - n2;
        if (Math.abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            boolean notSame = first.charAt(i) != second.charAt(j);
            if (notSame) {
                if (diff == 1) {
                    // --j, ++i, ++j => ++i
                    --j;
                } else if (diff == -1) {
                    // --i, ++i, ++j => ++j
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool oneEditAway(string first, string second) {
        int n1 = first.size(), n2 = second.size();
        int diff = n1 - n2;
        if (abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            bool notSame = first[i] != second[j];
            if (notSame) {
                if (diff == 1) {
                    --j;
                } else if (diff == -1) {
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
