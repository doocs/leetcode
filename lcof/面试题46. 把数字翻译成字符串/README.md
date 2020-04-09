# [面试题46. 把数字翻译成字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

**示例 1:**

```
输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
```

**提示：**

- 0 <= num < 2<sup>31</sup>

## 解法
<!-- 这里可写通用的实现逻辑 -->
递归求解。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def translateNum(self, num: int) -> int:
        def cal(s):
            if len(s) < 2:
                return 1
            t = int(s[:2])
            return cal(s[1:]) if t < 10 or t > 25 else cal(s[1:]) + cal(s[2:])
        return cal(str(num))
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int translateNum(int num) {
        return cal(String.valueOf(num));
    }

    private int cal(String s) {
        int n = s.length();
        if (n < 2) {
            return 1;
        }
        int t = Integer.parseInt(s.substring(0, 2));
        return t < 10 || t > 25 ? cal(s.substring(1)) : cal(s.substring(1)) + cal(s.substring(2));
    }
}
```

### ...
```

```
