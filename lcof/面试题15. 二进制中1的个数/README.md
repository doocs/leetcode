# [面试题 15. 二进制中 1 的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

## 题目描述

请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9  表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

## 解法

`n & (n - 1)` 会消除 n 中最后一位中的 1。

同 [LeetCode 191. 位 1 的个数](/solution/0100-0199/0191.Number%20of%201%20Bits/README.md)

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        res = 0
        while n:
            n &= (n - 1)
            res += 1
        return res
```

### **Java**

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            ++res;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n - a positive integer
 * @return {number}
 */
var hammingWeight = function (n) {
  let cnt = 0;
  while (n) {
    cnt += n & 1;
    n >>>= 1;
  }
  return cnt;
};
```

### **Go**

```go
func hammingWeight(num uint32) int {
	ans := 0
	// num &=num-1 消除最右边的1
	for num != 0 {
		num &= num - 1
		ans++
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n &= (n - 1);
            ++ans;
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
