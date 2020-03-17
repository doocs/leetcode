## 宝石与石头

### 问题描述

 给定字符串 `J` 代表石头中宝石的类型，和字符串 `S` 代表你拥有的石头。 `S` 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

`J` 中的字母不重复，`J` 和 `S` 中的所有字符都是字母。字母区分大小写，因此`"a"`和`"A"`是不同类型的石头。

**示例1:**

```
输入: J = "aA", S = "aAAbbbb"
输出: 3
```

**示例2:**

```
输入: J = "z", S = "ZZ"
输出: 0
```

**注意:**

- `S` 和 `J` 最多含有 50 个字母。
- `J` 中的字符不重复。

### 解法
题目中 `J` 字母不重复，因此，直接将 `J` 转换为 set。之后遍历 `S` 中每个字符，判断该字符是否在 set 中，若是则累加 1。最后得到累计后的结果。

1. Java 解法

```java
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char ch : J.toCharArray()) {
            set.add(ch);
        }
        int res = 0;
        for (char ch : S.toCharArray()) {
            res += (set.contains(ch) ? 1 : 0);
        }
        return res;
    }
}
```

2. Python 解法

```python
class Solution:
    def numJewelsInStones(self, J: str, S: str) -> int:
        record = {ch for ch in J}
        sum = 0
        for ch in S:
            sum += 1 if ch in record else 0
        return sum
```