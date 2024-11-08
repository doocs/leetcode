---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3344.Maximum%20Sized%20Array/README.md
tags:
    - 位运算
    - 二分查找
---

<!-- problem:start -->

# [3344. 最大尺寸数组 🔒](https://leetcode.cn/problems/maximum-sized-array)

[English Version](/solution/3300-3399/3344.Maximum%20Sized%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数&nbsp;<code>s</code>，令&nbsp;<code>A</code>&nbsp;为一个&nbsp;<code>n × n × n</code>&nbsp;的三维数组，其中每个元素&nbsp;<code>A[i][j][k]</code>&nbsp;定义为：</p>

<ul>
	<li><code>A[i][j][k] = i * (j OR k)</code>，其中&nbsp;<code>0 &lt;= i, j, k &lt; n</code>。</li>
</ul>

<p>返回使数组 <code>A</code> 中所有元素的和不超过 <code>s</code>&nbsp;的 <strong>最大的</strong>&nbsp;<code>n</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = 10</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n = 2</code><strong>&nbsp;</strong>时数组&nbsp;<code>A</code> 的元素：

    <ul>
    	<li><code>A[0][0][0] = 0 * (0 OR 0) = 0</code></li>
    	<li><code>A[0][0][1] = 0 * (0 OR 1) = 0</code></li>
    	<li><code>A[0][1][0] = 0 * (1 OR 0) = 0</code></li>
    	<li><code>A[0][1][1] = 0 * (1 OR 1) = 0</code></li>
    	<li><code>A[1][0][0] = 1 * (0 OR 0) = 0</code></li>
    	<li><code>A[1][0][1] = 1 * (0 OR 1) = 1</code></li>
    	<li><code>A[1][1][0] = 1 * (1 OR 0) = 1</code></li>
    	<li><code>A[1][1][1] = 1 * (1 OR 1) = 1</code></li>
    </ul>
    </li>
    <li>数组&nbsp;<code>A</code>&nbsp;中元素的总和为 3，没有超过 10，所以&nbsp;<code>n</code>&nbsp;的最大值为 2。</li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = 0</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n = 1</code><strong>&nbsp;</strong>时数组&nbsp;<code>A</code> 的元素：

    <ul>
    	<li><code>A[0][0][0] = 0 * (0 OR 0) = 0</code></li>
    </ul>
    </li>
    <li>数组&nbsp;<code>A</code>&nbsp;中元素的总和为&nbsp;0，没有超过 0，所以&nbsp;<code>n</code>&nbsp;的最大值为 1。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 二分查找

我们可以粗略估算出 $n$ 的最大值，对于 $j \lor k$，结果的和大致为 $n^2 (n - 1) / 2$，再与 $i \in [0, n)$ 的每个 $i$ 相乘，结果约等于 $(n-1)^5 / 4$，要使得 $(n - 1)^5 / 4 \leq s$，那么 $n \leq 1320$。

因此，我们不妨预处理出 $f[n] = \sum_{i=0}^{n-1} \sum_{j=0}^{i} (i \lor j)$，然后使用二分查找找到最大的 $n$，使得 $f[n-1] \cdot (n-1) \cdot n / 2 \leq s$。

时间复杂度方面，预处理的时间复杂度为 $O(n^2)$，二分查找的时间复杂度为 $O(\log n)$，因此总时间复杂度为 $O(n^2 + \log n)$。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
mx = 1330
f = [0] * mx
for i in range(1, mx):
    f[i] = f[i - 1] + i
    for j in range(i):
        f[i] += 2 * (i | j)


class Solution:
    def maxSizedArray(self, s: int) -> int:
        l, r = 1, mx
        while l < r:
            m = (l + r + 1) >> 1
            if f[m - 1] * (m - 1) * m // 2 <= s:
                l = m
            else:
                r = m - 1
        return l
```

#### Java

```java
class Solution {
    private static final int MX = 1330;
    private static final long[] f = new long[MX];
    static {
        for (int i = 1; i < MX; ++i) {
            f[i] = f[i - 1] + i;
            for (int j = 0; j < i; ++j) {
                f[i] += 2 * (i | j);
            }
        }
    }
    public int maxSizedArray(long s) {
        int l = 1, r = MX;
        while (l < r) {
            int m = (l + r + 1) >> 1;
            if (f[m - 1] * (m - 1) * m / 2 <= s) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
const int MX = 1330;
long long f[MX];
auto init = [] {
    f[0] = 0;
    for (int i = 1; i < MX; ++i) {
        f[i] = f[i - 1] + i;
        for (int j = 0; j < i; ++j) {
            f[i] += 2 * (i | j);
        }
    }
    return 0;
}();

class Solution {
public:
    int maxSizedArray(long long s) {
        int l = 1, r = MX;
        while (l < r) {
            int m = (l + r + 1) >> 1;
            if (f[m - 1] * (m - 1) * m / 2 <= s) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
const MX = 1330

var f [MX]int64

func init() {
	f[0] = 0
	for i := 1; i < MX; i++ {
		f[i] = f[i-1] + int64(i)
		for j := 0; j < i; j++ {
			f[i] += 2 * int64(i|j)
		}
	}
}

func maxSizedArray(s int64) int {
	l, r := 1, MX
	for l < r {
		m := (l + r + 1) >> 1
		if f[m-1]*int64(m-1)*int64(m)/2 <= s {
			l = m
		} else {
			r = m - 1
		}
	}
	return l
}
```

#### TypeScript

```ts
const MX = 1330;
const f: bigint[] = Array(MX).fill(0n);
(() => {
    f[0] = 0n;
    for (let i = 1; i < MX; i++) {
        f[i] = f[i - 1] + BigInt(i);
        for (let j = 0; j < i; j++) {
            f[i] += BigInt(2) * BigInt(i | j);
        }
    }
})();

function maxSizedArray(s: number): number {
    let l = 1,
        r = MX;
    const target = BigInt(s);

    while (l < r) {
        const m = (l + r + 1) >> 1;
        if ((f[m - 1] * BigInt(m - 1) * BigInt(m)) / BigInt(2) <= target) {
            l = m;
        } else {
            r = m - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
