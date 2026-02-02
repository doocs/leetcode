---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3792.Sum%20of%20Increasing%20Product%20Blocks/README.md
tags:
    - 数学
    - 模拟
---

<!-- problem:start -->

# [3792. 递增乘积块之和 🔒](https://leetcode.cn/problems/sum-of-increasing-product-blocks)

[English Version](/solution/3700-3799/3792.Sum%20of%20Increasing%20Product%20Blocks/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数&nbsp;<code>n</code>。</p>

<p>一个序列的形成如下：</p>

<ul>
	<li>第&nbsp;<code>1</code>&nbsp;块包含&nbsp;<code>1</code>。</li>
	<li>第&nbsp;<code>2</code>&nbsp;块包含&nbsp;<code>2 * 3</code>。</li>
	<li>第&nbsp;<code>i</code>&nbsp;块是之后&nbsp;<code>i</code>&nbsp;个连续整数的乘积。</li>
</ul>

<p>令&nbsp;<code>F(n)</code>&nbsp;为前 <code>n</code>&nbsp;块之和。</p>

<p>返回一个整数表示&nbsp;<code>F(n)</code> <strong>模上</strong>&nbsp;<code>10<sup>9</sup> + 7</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3</span></p>

<p><span class="example-io"><b>输出：</b>127</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>块 1：<code>1</code></li>
	<li>块 2：<code>2 * 3 = 6</code></li>
	<li>块 3：<code>4 * 5 * 6 = 120</code></li>
</ul>

<p><code>F(3) = 1 + 6 + 120 = 127</code></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 7</span></p>

<p><span class="example-io"><b>输出：</b>6997165</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>块 1：<code>1</code></li>
	<li>块 2：<code>2 * 3 = 6</code></li>
	<li>块 3：<code>4 * 5 * 6 = 120</code></li>
	<li>块 4：<code>7 * 8 * 9 * 10 = 5040</code></li>
	<li>块 5：<code>11 * 12 * 13 * 14 * 15 = 360360</code></li>
	<li>块 6：<code>16 * 17 * 18 * 19 * 20 * 21 = 39070080</code></li>
	<li>块 7：<code>22 * 23 * 24 * 25 * 26 * 27 * 28 = 5967561600</code></li>
</ul>

<p><code>F(7) = 6006997207 % (10<sup>9</sup> + 7) = 6997165</code></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟每一块的乘积并累加到答案中。需要注意的是，由于乘积可能会非常大，我们需要在每一步计算时对结果取模。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfBlocks(self, n: int) -> int:
        ans = 0
        mod = 10**9 + 7
        k = 1
        for i in range(1, n + 1):
            x = 1
            for j in range(k, k + i):
                x = (x * j) % mod
            ans = (ans + x) % mod
            k += i
        return ans
```

#### Java

```java
class Solution {
    public int sumOfBlocks(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sumOfBlocks(int n) {
        const int mod = 1e9 + 7;
        long long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfBlocks(n int) (ans int) {
	const mod int = 1e9 + 7
	k := 1
	for i := 1; i <= n; i++ {
		x := 1
		for j := k; j < k+i; j++ {
			x = x * j % mod
		}
		ans = (ans + x) % mod
		k += i
	}
	return
}
```

#### TypeScript

```ts
function sumOfBlocks(n: number): number {
    const mod = 1000000007;
    let k = 1;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        let x = 1;
        for (let j = k; j < k + i; j++) {
            x = (x * j) % mod;
        }
        ans = (ans + x) % mod;
        k += i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
