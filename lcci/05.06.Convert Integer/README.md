---
comment: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.06.Convert%20Integer/README.md
---

# [面试题 05.06. 整数转换](https://leetcode.cn/problems/convert-integer-lcci)

[English Version](/lcci/05.06.Convert%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：A = 29 （或者0b11101）, B = 15（或者0b01111）
<strong> 输出</strong>：2
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：A = 1，B = 2
<strong> 输出</strong>：2
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>A，B范围在[-2147483648, 2147483647]之间</li>
</ol>

## 解法

### 方法一：位运算

我们将 A 和 B 进行异或运算，得到的结果的二进制表示中 $1$ 的个数即为需要改变的位数。

时间复杂度 $O(\log n)$，其中 $n$ 为 A 和 B 的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def convertInteger(self, A: int, B: int) -> int:
        A &= 0xFFFFFFFF
        B &= 0xFFFFFFFF
        return (A ^ B).bit_count()
```

```java
class Solution {
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A ^ B);
    }
}
```

```cpp
class Solution {
public:
    int convertInteger(int A, int B) {
        unsigned int c = A ^ B;
        return __builtin_popcount(c);
    }
};
```

```go
func convertInteger(A int, B int) int {
	return bits.OnesCount32(uint32(A ^ B))
}
```

```ts
function convertInteger(A: number, B: number): number {
    let res = 0;
    while (A !== 0 || B !== 0) {
        if ((A & 1) !== (B & 1)) {
            res++;
        }
        A >>>= 1;
        B >>>= 1;
    }
    return res;
}
```

```rust
impl Solution {
    pub fn convert_integer(a: i32, b: i32) -> i32 {
        (a ^ b).count_ones() as i32
    }
}
```

```swift
class Solution {
    func convertInteger(_ A: Int, _ B: Int) -> Int {
        return (Int32(A) ^ Int32(B)).nonzeroBitCount
    }
}
```

<!-- tabs:end -->

<!-- end -->
