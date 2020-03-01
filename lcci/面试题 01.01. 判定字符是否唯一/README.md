# [面试题 01.01. 判定字符是否唯一](https://leetcode-cn.com/problems/is-unique-lcci/)

## 题目描述
实现一个算法，确定一个字符串 `s` 的所有字符是否全都不同。

**示例 1：**
```
输入: s = "leetcode"
输出: false 
```

**示例 2：**
```
输入: s = "abc"
输出: true
```

限制：

- `0 <= len(s) <= 100`
- 如果你不使用额外的数据结构，会很加分。


## 解法
### Python3
```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        sets = set(astr)
        return len(sets) == len(astr)
```

### Java
```java
class Solution {
    public boolean isUnique(String astr) {
        char[] chars = astr.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### ...
```

```