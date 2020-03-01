# [面试题 01.02. 判定是否互为字符重排](https://leetcode-cn.com/problems/check-permutation-lcci/)

## 题目描述
给定两个字符串 `s1` 和 `s2`，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。

**示例 1：**

```
输入: s1 = "abc", s2 = "bca"
输出: true 
```

**示例 2：**
```
输入: s1 = "abc", s2 = "bad"
输出: false
```

**说明：**

- `0 <= len(s1) <= 100`
- `0 <= len(s2) <= 100`


## 解法
### Python3
```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

### Java
```java

```

### ...
```

```