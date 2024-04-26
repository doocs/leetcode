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

### 方法一：位运算

我们可以将 $\text{num}$ 与 $\text{0x55555555}$ 进行与运算，得到的结果是 $\text{num}$ 的偶数位，然后将其左移一位。再将 $\text{num}$ 与 $\text{0xaaaaaaaa}$ 进行与运算，得到的结果是 $\text{num}$ 的奇数位，然后将其右移一位。最后将两个结果进行或运算，即可得到答案。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

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

```ts
function exchangeBits(num: number): number {
    return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >>> 1);
}
```

```rust
impl Solution {
    pub fn exchange_bits(num: i32) -> i32 {
        let num = num as u32;
        (((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >> 1)) as i32
    }
}
```

```swift
class Solution {
    func exchangeBits(_ num: Int) -> Int {
        let oddShifted = (num & 0x55555555) << 1

        let evenShifted = (num & 0xaaaaaaaa) >> 1

        return oddShifted | evenShifted
    }
}
```

<!-- tabs:end -->

<!-- end -->
