---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3133.Minimum%20Array%20End/README.md
rating: 1934
tags:
    - 位运算
---

# [3133. 数组最后一个元素的最小值](https://leetcode.cn/problems/minimum-array-end)

[English Version](/solution/3100-3199/3133.Minimum%20Array%20End/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>n</code> 和 <code>x</code> 。你需要构造一个长度为 <code>n</code> 的 <strong>正整数 </strong>数组 <code>nums</code> ，对于所有 <code>0 &lt;= i &lt; n - 1</code> ，满足 <code>nums[i + 1]</code><strong> 大于 </strong><code>nums[i]</code> ，并且数组 <code>nums</code> 中所有元素的按位 <code>AND</code> 运算结果为 <code>x</code> 。</p>

<p>返回 <code>nums[n - 1]</code> 可能的<strong> 最小 </strong>值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 3, x = 4</span></p>

<p><strong>输出：</strong><span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums</code> 可以是 <code>[4,5,6]</code> ，最后一个元素为 <code>6</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 2, x = 7</span></p>

<p><strong>输出：</strong><span class="example-io">15</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums</code> 可以是 <code>[7,15]</code> ，最后一个元素为 <code>15</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, x &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 位运算

根据题目描述，要使得数组的最后一个元素尽可能小，且数组中的元素按位与的结果为 $x$，那么数组的第一个元素必须为 $x$。

假设 $x$ 的二进制表示为 $\underline{1}00\underline{1}00$，那么数组序列为 $\underline{1}00\underline{1}00$, $\underline{1}00\underline{1}01$, $\underline{1}00\underline{1}10$, $\underline{1}00\underline{1}11$...

如果我们忽略掉下划线部分，那么数组序列为 $0000$, $0001$, $0010$, $0011$...，第一项为 $0$，那么第 $n$ 项为 $n-1$。

因此，答案就是在 $x$ 的基础上，将 $n-1$ 的二进制的每一位依次填入 $x$ 的二进制中的 $0$ 位。

时间复杂度 $O(\log x)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minEnd(self, n: int, x: int) -> int:
        n -= 1
        ans = x
        for i in range(31):
            if x >> i & 1 ^ 1:
                ans |= (n & 1) << i
                n >>= 1
        ans |= n << 31
        return ans
```

```java
class Solution {
    public long minEnd(int n, int x) {
        --n;
        long ans = x;
        for (int i = 0; i < 31; ++i) {
            if ((x >> i & 1) == 0) {
                ans |= (n & 1) << i;
                n >>= 1;
            }
        }
        ans |= (long) n << 31;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minEnd(int n, int x) {
        --n;
        long long ans = x;
        for (int i = 0; i < 31; ++i) {
            if (x >> i & 1 ^ 1) {
                ans |= (n & 1) << i;
                n >>= 1;
            }
        }
        ans |= (1LL * n) << 31;
        return ans;
    }
};
```

```go
func minEnd(n int, x int) (ans int64) {
	n--
	ans = int64(x)
	for i := 0; i < 31; i++ {
		if x>>i&1 == 0 {
			ans |= int64((n & 1) << i)
			n >>= 1
		}
	}
	ans |= int64(n) << 31
	return
}
```

```ts
function minEnd(n: number, x: number): number {
    --n;
    let ans: bigint = BigInt(x);
    for (let i = 0; i < 31; ++i) {
        if (((x >> i) & 1) ^ 1) {
            ans |= BigInt(n & 1) << BigInt(i);
            n >>= 1;
        }
    }
    ans |= BigInt(n) << BigInt(31);
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- end -->
