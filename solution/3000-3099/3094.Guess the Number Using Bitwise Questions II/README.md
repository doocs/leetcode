# [3094. 使用按位查询猜测数字 II 🔒](https://leetcode.cn/problems/guess-the-number-using-bitwise-questions-ii)

[English Version](/solution/3000-3099/3094.Guess%20the%20Number%20Using%20Bitwise%20Questions%20II/README_EN.md)

<!-- tags:位运算,交互 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要找到一个在 <code>0</code> 和&nbsp;<code>2<sup>30</sup> - 1</code>&nbsp;（均包含）之间的数字 <code>n</code>。</p>

<p>有一个预定义的 API <code>int commonBits(int num)</code>&nbsp;能帮助你完成任务。但挑战是每次你调用这个函数，<code>n</code>&nbsp;都会以某种方式改变。但是记住，你需要找到的是<strong>&nbsp;</strong><code>n</code>&nbsp;的 <strong>初始值</strong>。</p>

<p><code>commonBits(int num)</code> 的操作如下：</p>

<ul>
	<li>计算&nbsp;<code>n</code>&nbsp;和&nbsp;<code>num</code>&nbsp;的二进制表示中值相同的二进制位的位的数量&nbsp;<code>count</code>。</li>
	<li><code>n = n XOR num</code></li>
	<li>返回&nbsp;<code>count</code>。</li>
</ul>

<p>返回数字&nbsp;<code>n</code>。</p>

<p><strong>注意：</strong>在这个世界中，所有数字都在&nbsp;<code>0</code>&nbsp;和&nbsp;<code>2<sup>30</sup> - 1</code>&nbsp;之间（均包含），因此在计算公共二进制位时，我们只看那些数字的前 30 个二进制位。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">n = 31 </span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">31 </span></p>

<p><strong>解释：</strong>可以证明，使用提供的 API 可以找到 31。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">n = 33 </span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">33 </span></p>

<p><strong>解释：</strong>可以证明，使用提供的 API 可以找到 33。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 2<sup>30</sup> - 1</code></li>
	<li><code>0 &lt;= num &lt;= 2<sup>30</sup> - 1</code></li>
	<li>如果你查询的&nbsp;<code>num</code>&nbsp;超出了给定的范围，输出将会是不可靠的。</li>
</ul>

## 解法

### 方法一：位运算

根据题目描述，我们观察到：

-   如果我们对同一个数调用两次 `commonBits` 函数，那么 $n$ 的值将不会发生变化。
-   如果我们调用 `commonBits(1 << i)`，那么 $n$ 的第 $i$ 位将会被翻转，即 $n$ 的第 $i$ 位为 $1$ 时，调用后变为 $0$，反之亦然。

因此，对于每一位 $i$，我们可以调用 `commonBits(1 << i)` 两次，分别记为 `count1` 和 `count2`，如果 `count1 > count2`，那么说明 $n$ 的第 $i$ 位为 $1$，否则为 $0$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# Definition of commonBits API.
# def commonBits(num: int) -> int:


class Solution:
    def findNumber(self) -> int:
        n = 0
        for i in range(32):
            count1 = commonBits(1 << i)
            count2 = commonBits(1 << i)
            if count1 > count2:
                n |= 1 << i
        return n
```

```java
/**
 * Definition of commonBits API (defined in the parent class Problem).
 * int commonBits(int num);
 */

public class Solution extends Problem {
    public int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            int count1 = commonBits(1 << i);
            int count2 = commonBits(1 << i);
            if (count1 > count2) {
                n |= 1 << i;
            }
        }
        return n;
    }
}
```

```cpp
/**
 * Definition of commonBits API.
 * int commonBits(int num);
 */

class Solution {
public:
    int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            int count1 = commonBits(1 << i);
            int count2 = commonBits(1 << i);
            if (count1 > count2) {
                n |= 1 << i;
            }
        }
        return n;
    }
};
```

```go
/**
 * Definition of commonBits API.
 * func commonBits(num int) int;
 */

func findNumber() (n int) {
	for i := 0; i < 32; i++ {
		count1 := commonBits(1 << i)
		count2 := commonBits(1 << i)
		if count1 > count2 {
			n |= 1 << i
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
