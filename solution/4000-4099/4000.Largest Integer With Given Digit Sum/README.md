---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4000.Largest%20Integer%20With%20Given%20Digit%20Sum/README.md
rating: 1199
source: 第 512 场周赛 Q1
---

<!-- problem:start -->

# [4000. 给定数位和的最大整数](https://leetcode.cn/problems/largest-integer-with-given-digit-sum)

[English Version](/solution/4000-4099/4000.Largest%20Integer%20With%20Given%20Digit%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个非负整数 <code>n</code> 和 <code>s</code>。</p>

<p>返回满足下述条件的&nbsp;<strong>最大</strong>&nbsp;整数：</p>

<ul>
	<li>最多有 <code>n</code> 位数字。</li>
	<li>其各位数字之和等于 <code>s</code>&nbsp;。</li>
</ul>

<p>如果不存在这样的整数，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, s = 9</span></p>

<p><strong>输出：</strong> <span class="example-io">90</span></p>

<p><strong>解释：</strong></p>

<p>最多由 2 位数字组成且各位数字之和为 9 的最大整数是 90。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, s = 19</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>不存在最多由 2 位数字组成且各位数字之和为 19 的整数，因此答案为 <code>-1</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, s = 0</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>唯一一个各位数字之和为 0 的非负整数是 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5</code></li>
	<li><code>0 &lt;= s &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

若 $n \times 9 < s$，即使每一位都取 $9$ 也无法凑出数位和 $s$，返回 $-1$。

否则，为使整数尽可能大，应优先让高位取尽可能大的数字。从高位到低位共构造 $n$ 位：每一位取 $\min(s, 9)$，并令 $s$ 减去该值。最终得到的整数即为答案（若 $s = 0$，结果为 $0$）。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, n: int, s: int) -> int:
        if n * 9 < s:
            return -1
        ans = 0
        for _ in range(n):
            x = min(s, 9)
            ans = ans * 10 + x
            s -= x
        return ans
```

#### Java

```java
class Solution {
    public int largestInteger(int n, int s) {
        if (n * 9 < s) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = Math.min(s, 9);
            ans = ans * 10 + x;
            s -= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(int n, int s) {
        if (n * 9 < s) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = min(s, 9);
            ans = ans * 10 + x;
            s -= x;
        }
        return ans;
    }
};
```

#### Go

```go
func largestInteger(n int, s int) (ans int) {
	if n*9 < s {
		return -1
	}
	for i := 0; i < n; i++ {
		x := min(s, 9)
		ans = ans*10 + x
		s -= x
	}
	return
}
```

#### TypeScript

```ts
function largestInteger(n: number, s: number): number {
    if (n * 9 < s) {
        return -1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const x = Math.min(s, 9);
        ans = ans * 10 + x;
        s -= x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
