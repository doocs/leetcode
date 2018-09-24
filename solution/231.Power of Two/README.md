## 2的幂
### 题目描述

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:
```
输入: 1
输出: true
解释: 2^0 = 1
```

示例 2:
```
输入: 16
输出: true
解释: 2^4 = 16
```

示例 3:
```
输入: 218
输出: false
```

### 解法
可以利用 2^31 对该数取余，结果为 0 则为 2 的幂次方。
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && 1073741824 % n == 0;
    }
}
```

也可以循环取余，每次除以 2，判断最终结果是否为 1。
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        
        while (n % 2 == 0) {
            n >>= 1;
        }
        return n == 1;
    }
}
```