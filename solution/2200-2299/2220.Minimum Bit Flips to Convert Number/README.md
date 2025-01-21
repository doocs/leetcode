---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2220.Minimum%20Bit%20Flips%20to%20Convert%20Number/README.md
rating: 1282
source: 第 75 场双周赛 Q1
tags:
    - 位运算
---

<!-- problem:start -->

# [2220. 转换数字的最少位翻转次数](https://leetcode.cn/problems/minimum-bit-flips-to-convert-number)

[English Version](/solution/2200-2299/2220.Minimum%20Bit%20Flips%20to%20Convert%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一次 <strong>位翻转</strong>&nbsp;定义为将数字&nbsp;<code>x</code>&nbsp;二进制中的一个位进行 <strong>翻转</strong>&nbsp;操作，即将&nbsp;<code>0</code>&nbsp;变成&nbsp;<code>1</code>&nbsp;，或者将&nbsp;<code>1</code>&nbsp;变成&nbsp;<code>0</code>&nbsp;。</p>

<ul>
	<li>比方说，<code>x = 7</code>&nbsp;，二进制表示为&nbsp;<code>111</code>&nbsp;，我们可以选择任意一个位（包含没有显示的前导 0 ）并进行翻转。比方说我们可以翻转最右边一位得到&nbsp;<code>110</code>&nbsp;，或者翻转右边起第二位得到&nbsp;<code>101</code>&nbsp;，或者翻转右边起第五位（这一位是前导 0 ）得到&nbsp;<code>10111</code>&nbsp;等等。</li>
</ul>

<p>给你两个整数&nbsp;<code>start</code> 和&nbsp;<code>goal</code>&nbsp;，请你返回将&nbsp;<code>start</code>&nbsp;转变成&nbsp;<code>goal</code>&nbsp;的&nbsp;<strong>最少位翻转</strong>&nbsp;次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>start = 10, goal = 7
<b>输出：</b>3
<b>解释：</b>10 和 7 的二进制表示分别为 1010 和 0111 。我们可以通过 3 步将 10 转变成 7 ：
- 翻转右边起第一位得到：101<strong><em>0</em></strong> -&gt; 101<strong><em>1 。</em></strong>
- 翻转右边起第三位：1<strong><em>0</em></strong>11 -&gt; 1<strong><em>1</em></strong>11 。
- 翻转右边起第四位：<strong><em>1</em></strong>111 -&gt; <strong><em>0</em></strong>111 。
我们无法在 3 步内将 10 转变成 7 。所以我们返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>start = 3, goal = 4
<b>输出：</b>3
<b>解释：</b>3 和 4 的二进制表示分别为 011 和 100 。我们可以通过 3 步将 3 转变成 4 ：
- 翻转右边起第一位：01<strong><em>1</em></strong> -&gt; 01<em><strong>0 </strong></em>。
- 翻转右边起第二位：0<strong><em>1</em></strong>0 -&gt; 0<strong><em>0</em></strong>0 。
- 翻转右边起第三位：<strong><em>0</em></strong>00 -&gt; <strong><em>1</em></strong>00 。
我们无法在 3 步内将 3 变成 4 。所以我们返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= start, goal &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与&nbsp;<a href="https://leetcode.cn/problems/hamming-distance/">461. 汉明距离</a>&nbsp;相同。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

根据题目描述，我们只需要计算 $\textit{start} \oplus \textit{goal}$ 的二进制表示中有多少个 1 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是题目中整数的大小。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minBitFlips(self, start: int, goal: int) -> int:
        return (start ^ goal).bit_count()
```

#### Java

```java
class Solution {
    public int minBitFlips(int start, int goal) {
        return Integer.bitCount(start ^ goal);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minBitFlips(int start, int goal) {
        return __builtin_popcount(start ^ goal);
    }
};
```

#### Go

```go
func minBitFlips(start int, goal int) int {
	return bits.OnesCount(uint(start ^ goal))
}
```

#### TypeScript

```ts
function minBitFlips(start: number, goal: number): number {
    return bitCount(start ^ goal);
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
    pub fn min_bit_flips(start: i32, goal: i32) -> i32 {
        (start ^ goal).count_ones() as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number} start
 * @param {number} goal
 * @return {number}
 */
var minBitFlips = function (start, goal) {
    return bitCount(start ^ goal);
};

function bitCount(i) {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### C

```c
int minBitFlips(int start, int goal) {
    int x = start ^ goal;
    int ans = 0;
    while (x) {
        ans += (x & 1);
        x >>= 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
