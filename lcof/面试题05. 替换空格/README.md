# [面试题05. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

## 题目描述
请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

**示例 1：**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

**限制：**

- `0 <= s 的长度 <= 10000`

## 解法
使用 replace 替换即可。

### Python3
```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        return s.replace(' ', '%20')
```

### Java
```java
class Solution {
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
```

### ...
```

```
