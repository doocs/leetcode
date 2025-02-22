---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2595.Number%20of%20Even%20and%20Odd%20Bits/README.md
rating: 1206
source: 第 337 场周赛 Q1
tags:
    - 位运算
---

<!-- problem:start -->

# [2595. 奇偶位数](https://leetcode.cn/problems/number-of-even-and-odd-bits)

[English Version](/solution/2500-2599/2595.Number%20of%20Even%20and%20Odd%20Bits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>正</strong> 整数 <code>n</code> 。</p>

<p>用 <code>even</code> 表示在 <code>n</code> 的二进制形式（下标从 <strong>0</strong> 开始）中值为 <code>1</code> 的偶数下标的个数。</p>

<p>用 <code>odd</code> 表示在 <code>n</code> 的二进制形式（下标从 <strong>0</strong> 开始）中值为 <code>1</code> 的奇数下标的个数。</p>

<p>请注意，在数字的二进制表示中，位下标的顺序&nbsp;<strong>从右到左</strong>。</p>

<p>返回整数数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer = [even, odd]</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 50</span></p>

<p><span class="example-io"><b>输出：</b>[1,2]</span></p>

<p><strong>解释：</strong></p>

<p>50 的二进制表示是&nbsp;<code>110010</code>。</p>

<p>在下标 1，4，5 对应的值为&nbsp;1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 2</span></p>

<p><span class="example-io"><b>输出：</b>[0,1]</span></p>

<p><strong>解释：</strong></p>

<p>2 的二进制表示是&nbsp;<code>10</code>。</p>

<p>只有下标 1 对应的值为&nbsp;1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们根据题意，枚举 $n$ 的二进制表示中从低位到高位的每一位，如果该位为 $1$，则根据该位的下标是奇数还是偶数，将对应的计数器加 $1$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为给定的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evenOddBit(self, n: int) -> List[int]:
        ans = [0, 0]
        i = 0
        while n:
            ans[i] += n & 1
            i ^= 1
            n >>= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> evenOddBit(int n) {
        vector<int> ans(2);
        for (int i = 0; n > 0; n >>= 1, i ^= 1) {
            ans[i] += n & 1;
        }
        return ans;
    }
};
```

#### Go

```go
func evenOddBit(n int) []int {
	ans := make([]int, 2)
	for i := 0; n != 0; n, i = n>>1, i^1 {
		ans[i] += n & 1
	}
	return ans
}
```

#### TypeScript

```ts
function evenOddBit(n: number): number[] {
    const ans = Array(2).fill(0);
    for (let i = 0; n > 0; n >>= 1, i ^= 1) {
        ans[i] += n & 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn even_odd_bit(mut n: i32) -> Vec<i32> {
        let mut ans = vec![0; 2];

        let mut i = 0;
        while n != 0 {
            ans[i] += n & 1;

            n >>= 1;
            i ^= 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：位运算

我们可以定义一个掩码 $\textit{mask} = \text{0x5555}$，它的二进制表示为 $\text{0101 0101 0101 0101}_2$。那么 $n$ 与 $\textit{mask}$ 进行按位与运算，就可以得到 $n$ 的二进制表示中偶数下标的位，而 $n$ 与 $\textit{mask}$ 取反后再进行按位与运算，就可以得到 $n$ 的二进制表示中奇数下标的位。统计这两个结果中 $1$ 的个数即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。其中 $n$ 为给定的整数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evenOddBit(self, n: int) -> List[int]:
        mask = 0x5555
        even = (n & mask).bit_count()
        odd = (n & ~mask).bit_count()
        return [even, odd]
```

#### Java

```java
class Solution {
    public int[] evenOddBit(int n) {
        int mask = 0x5555;
        int even = Integer.bitCount(n & mask);
        int odd = Integer.bitCount(n & ~mask);
        return new int[] {even, odd};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> evenOddBit(int n) {
        int mask = 0x5555;
        int even = __builtin_popcount(n & mask);
        int odd = __builtin_popcount(n & ~mask);
        return {even, odd};
    }
};
```

#### Go

```go
func evenOddBit(n int) []int {
	mask := 0x5555
	even := bits.OnesCount32(uint32(n & mask))
	odd := bits.OnesCount32(uint32(n & ^mask))
	return []int{even, odd}
}
```

#### TypeScript

```ts
function evenOddBit(n: number): number[] {
    const mask = 0x5555;
    const even = bitCount(n & mask);
    const odd = bitCount(n & ~mask);
    return [even, odd];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### Rust

```rust
impl Solution {
    pub fn even_odd_bit(n: i32) -> Vec<i32> {
        let mask: i32 = 0x5555;
        let even = (n & mask).count_ones() as i32;
        let odd = (n & !mask).count_ones() as i32;
        vec![even, odd]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
