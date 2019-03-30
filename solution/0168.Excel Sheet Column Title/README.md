## Excel表列名称
### 题目描述

给定一个正整数，返回它在 Excel 表中相对应的列名称。

例如，
```
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
```

**示例 1:**
```
输入: 1
输出: "A"
```

**示例 2:**
```
输入: 28
输出: "AB"
```

**示例 3:**
```
输入: 701
输出: "ZY"
```

### 解法
1. 将数字 n 减一，则尾数转换为 0-25 的 26 进制数的个位；用除 26 取余的方式，获得尾数对应的符号。
2. 用除26取余的方式，获得尾数对应的符号;
3. 重复步骤1、2直至 n 为 0。

```java
class Solution {
    public String convertToTitle(int n) {
        if (n < 0) {
            return "";
        }        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            int temp = n % 26;
            sb.insert(0,(char)(temp + 'A'));
            n /= 26;
        }
        return sb.toString();
    }
}

```

