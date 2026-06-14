---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3954.Sum%20of%20Compatible%20Numbers%20in%20Range%20I/README.md
rating: 1210
source: 第 505 场周赛 Q1
tags:
    - 位运算
    - 动态规划
    - 枚举
---

<!-- problem:start -->

# [3954. 区间内的兼容数字之和 I](https://leetcode.cn/problems/sum-of-compatible-numbers-in-range-i)

[English Version](/solution/3900-3999/3954.Sum%20of%20Compatible%20Numbers%20in%20Range%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>。</p>

<p>如果一个 <strong>正</strong>&nbsp;整数 <code>x</code> 同时满足以下两个条件，则称其为 <strong>兼容</strong> 整数：</p>

<ul>
	<li><code>abs(n - x) &lt;= k</code></li>
	<li><code>(n &amp; x) == 0</code></li>
</ul>

<p>返回所有&nbsp;<strong>兼容</strong>&nbsp;整数 <code>x</code> 的总和。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>这里，<code>&amp;</code> 表示&nbsp;<strong>按位与&nbsp;</strong>运算符。</li>
	<li>整数 <code>i</code> 和 <code>j</code> 之间的<strong>&nbsp;绝对&nbsp;</strong>差定义为 <code>abs(i - j)</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>兼容整数为：</p>

<ul>
	<li><code>x = 1</code>，因为 <code>abs(2 - 1) = 1</code> 且 <code>2 &amp; 1 = 0</code>。</li>
	<li><code>x = 4</code>，因为 <code>abs(2 - 4) = 2</code> 且 <code>2 &amp; 4 = 0</code>。</li>
	<li><code>x = 5</code>，因为 <code>abs(2 - 5) = 3</code> 且 <code>2 &amp; 5 = 0</code>。</li>
</ul>

<p>因此，答案为 <code>1 + 4 + 5 = 10</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5, k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>区间 <code>[4, 6]</code> 中没有兼容整数。因此，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们在 $[\max(1, n - k), n + k]$ 的范围内枚举 $x$，如果 $n$ 与 $x$ 的按位与结果为 $0$，则将 $x$ 累加到答案中。

枚举结束后，返回答案即可。

时间复杂度 $O(k)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfGoodIntegers(self, n: int, k: int) -> int:
        ans = 0
        for x in range(max(1, n - k), n + k + 1):
            if (n & x) == 0:
                ans += x
        return ans
```

#### Java

```java
class Solution {
    public int sumOfGoodIntegers(int n, int k) {
        int ans = 0;
        int start = Math.max(1, n - k);
        int end = n + k;
        for (int x = start; x <= end; x++) {
            if ((n & x) == 0) {
                ans += x;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfGoodIntegers(int n, int k) {
        int ans = 0;
        int start = max(1, n - k);
        int end = n + k;
        for (int x = start; x <= end; ++x) {
            if ((n & x) == 0) {
                ans += x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfGoodIntegers(n int, k int) (ans int) {
	start := max(1, n-k)
	end := n + k
	for x := start; x <= end; x++ {
		if (n & x) == 0 {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function sumOfGoodIntegers(n: number, k: number): number {
    let ans = 0;
    const start = Math.max(1, n - k);
    const end = n + k;
    for (let x = start; x <= end; x++) {
        if ((n & x) === 0) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
