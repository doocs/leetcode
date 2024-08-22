---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3226.Number%20of%20Bit%20Changes%20to%20Make%20Two%20Integers%20Equal/README.md
tags:
    - 位运算
---

<!-- problem:start -->

# [3226. 使两个整数相等的位更改次数](https://leetcode.cn/problems/number-of-bit-changes-to-make-two-integers-equal)

[English Version](/solution/3200-3299/3226.Number%20of%20Bit%20Changes%20to%20Make%20Two%20Integers%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数 <code>n</code> 和 <code>k</code>。</p>

<p>你可以选择 <code>n</code> 的 <strong>二进制表示</strong> 中任意一个值为 1 的位，并将其改为 0。</p>

<p>返回使得 <code>n</code> 等于 <code>k</code> 所需要的更改次数。如果无法实现，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 13, k = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong><br />
最初，<code>n</code> 和 <code>k</code> 的二进制表示分别为 <code>n = (1101)<sub>2</sub></code> 和 <code>k = (0100)<sub>2</sub></code>，</p>

<p>我们可以改变 <code>n</code> 的第一位和第四位。结果整数为 <code>n = (<u><strong>0</strong></u>10<u><strong>0</strong></u>)<sub>2</sub> = k</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 21, k = 21</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong><br />
<code>n</code> 和 <code>k</code> 已经相等，因此不需要更改。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 14, k = 13</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong><br />
无法使 <code>n</code> 等于 <code>k</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

如果 $n$ 和 $k$ 的按位与结果不等于 $k$，说明 $k$ 存在某一位为 $1$，而 $n$ 对应的位为 $0$，此时无法通过改变 $n$ 的某一位使得 $n$ 等于 $k$，返回 $-1$；否则，我们统计 $n \oplus k$ 的二进制表示中 $1$ 的个数即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minChanges(self, n: int, k: int) -> int:
        return -1 if n & k != k else (n ^ k).bit_count()
```

#### Java

```java
class Solution {
    public int minChanges(int n, int k) {
        return (n & k) != k ? -1 : Integer.bitCount(n ^ k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minChanges(int n, int k) {
        return (n & k) != k ? -1 : __builtin_popcount(n ^ k);
    }
};
```

#### Go

```go
func minChanges(n int, k int) int {
	if n&k != k {
		return -1
	}
	return bits.OnesCount(uint(n ^ k))
}
```

#### TypeScript

```ts
function minChanges(n: number, k: number): number {
    return (n & k) !== k ? -1 : bitCount(n ^ k);
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
