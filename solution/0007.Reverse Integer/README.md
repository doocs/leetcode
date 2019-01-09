## 反转整数
### 题目描述

给定一个 32 位有符号整数，将整数中的数字进行反转。

示例 1:
```
输入: 123
输出: 321
```

 示例 2:
```
输入: -123
输出: -321
```

示例 3:
```
输入: 120
输出: 21
```

注意:

假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。

### 解法
- 解法1

用 long 型存储该整数，取绝对值，然后转成 StringBuilder 进行 reverse，后转回 int。注意判断该数是否在[Integer.MIN_VALUE, Intger.MAX_VALUE] 范围内。

```java
class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        
        long tmp = x;
        boolean isPositive = true;
        if (tmp < 0) {
            isPositive = false;
            tmp = -tmp;
        }
        
        long val = Long.parseLong(new StringBuilder(String.valueOf(tmp)).reverse().toString());
        
        return isPositive ? (val > Integer.MAX_VALUE ? 0 : (int) val) : (-val < Integer.MIN_VALUE ? 0 : (int) (-val)); 
        
    }
}
```

- 解法2

循环对数字求 `%, /` ，累加，最后返回结果。注意判断值是否溢出。
```java
class Solution {
    public int reverse(int x) {
        long res = 0;
        // 考虑负数情况，所以这里条件为: x != 0
        while (x != 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) 
            ? 0
            : (int) res;
            
    }
}
```