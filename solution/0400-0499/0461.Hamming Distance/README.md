---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0461.Hamming%20Distance/README.md
tags:
    - 位运算
---

<!-- problem:start -->

# [461. 汉明距离](https://leetcode.cn/problems/hamming-distance)

[English Version](/solution/0400-0499/0461.Hamming%20Distance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>两个整数之间的 <a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E8%B7%9D%E7%A6%BB">汉明距离</a> 指的是这两个数字对应二进制位不同的位置的数目。</p>

<p>给你两个整数 <code>x</code> 和 <code>y</code>，计算并返回它们之间的汉明距离。</p>

<p>&nbsp;</p>

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;x, y &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与&nbsp;<a href="https://leetcode.cn/problems/minimum-bit-flips-to-convert-number/">2220. 转换数字的最少位翻转次数</a>&nbsp;相同。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们将 $x$ 和 $y$ 按位异或，得到的结果中的 $1$ 的个数就是汉明距离。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        return (x ^ y).bit_count()
```

#### Java

```java
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int hammingDistance(int x, int y) {
        return __builtin_popcount(x ^ y);
    }
};
```

#### Go

```go
func hammingDistance(x int, y int) int {
	return bits.OnesCount(uint(x ^ y))
}
```

#### TypeScript

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

#### JavaScript

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
