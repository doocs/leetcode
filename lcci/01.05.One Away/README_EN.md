# [01.05. One Away](https://leetcode-cn.com/problems/one-away-lcci)

## Description
<p>There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.</p>



<p>&nbsp;</p>



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


### Python3

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

### Java

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

### ...
```

```
