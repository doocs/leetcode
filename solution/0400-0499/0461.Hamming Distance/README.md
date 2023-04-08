# [461. 汉明距离](https://leetcode.cn/problems/hamming-distance)

[English Version](/solution/0400-0499/0461.Hamming%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>两个整数之间的 <a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E8%B7%9D%E7%A6%BB">汉明距离</a> 指的是这两个数字对应二进制位不同的位置的数目。</p>

<p>给你两个整数 <code>x</code> 和 <code>y</code>，计算并返回它们之间的汉明距离。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 1, y = 4
<strong>输出：</strong>2
<strong>解释：</strong>
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 3, y = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= x, y <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

我们将 $x$ 和 $y$ 按位异或，得到的结果中的 $1$ 的个数就是汉明距离。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        return (x ^ y).bit_count()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hammingDistance(int x, int y) {
        return __builtin_popcount(x ^ y);
    }
};
```

### **Go**

```go
func hammingDistance(x int, y int) int {
	return bits.OnesCount(uint(x ^ y))
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @param {number} y
 * @return {number}
 */
var hammingDistance = function (x, y) {
    x ^= y;
    let ans = 0;
    while (x) {
        x -= x & -x;
        ++ans;
    }
    return ans;
};
```

### **TypeScript**

```ts
function hammingDistance(x: number, y: number): number {
    x ^= y;
    let ans = 0;
    while (x) {
        x -= x & -x;
        ++ans;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
