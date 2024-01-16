# [面试题 05.07. 配对交换](https://leetcode.cn/problems/exchange-lcci)

[English Version](/lcci/05.07.Exchange/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：num = 2（或者0b10）
<strong> 输出</strong> 1 (或者 0b01)
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：num = 3
<strong> 输出</strong>：3
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li><code>num</code>的范围在[0, 2^30 - 1]之间，不会发生整数溢出。</li>
</ol>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def exchangeBits(self, num: int) -> int:
        return ((num & 0x55555555) << 1) | ((num & 0xAAAAAAAA) >> 1)
```

```java
class Solution {
    public int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa)) >> 1;
    }
}
```

```cpp
class Solution {
public:
    int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa)) >> 1;
    }
};
```

```go
func exchangeBits(num int) int {
	return ((num & 0x55555555) << 1) | (num&0xaaaaaaaa)>>1
}
```

```rust
impl Solution {
    pub fn exchange_bits(mut num: i32) -> i32 {
        let mut res = 0;
        let mut i = 0;
        while num != 0 {
            let a = num & 1;
            num >>= 1;
            let b = num & 1;
            num >>= 1;
            res |= a << (i + 1);
            res |= b << i;
            i += 2;
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- end -->
