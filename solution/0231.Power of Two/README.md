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
#### 解法一
可以利用 2^31 对该数取余，结果为 0 则为 2 的幂次方。
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && 1073741824 % n == 0;
    }
}
```

#### 解法二
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

#### 解法三
利用 `n & -n`：
```
n & -n 表示 n 的二进制表示的最右边一个1
```

只要 (n & -n) == n，说明 n 的二进制表示中只有一个 1，那么也就说明它是 2 的幂。
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
```

