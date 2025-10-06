---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3658.GCD%20of%20Odd%20and%20Even%20Sums/README.md
rating: 1220
source: 第 464 场周赛 Q1
tags:
    - 数学
    - 数论
---

<!-- problem:start -->

# [3658. 奇数和与偶数和的最大公约数](https://leetcode.cn/problems/gcd-of-odd-and-even-sums)

[English Version](/solution/3600-3699/3658.GCD%20of%20Odd%20and%20Even%20Sums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。请你计算以下两个值的&nbsp;<strong>最大公约数</strong>（GCD）：</p>

<ul>
	<li>
	<p><code>sumOdd</code>：前 <code>n</code> 个奇数的总和。</p>
	</li>
	<li>
	<p><code>sumEven</code>：前 <code>n</code> 个偶数的总和。</p>
	</li>
</ul>

<p>返回 <code>sumOdd</code> 和 <code>sumEven</code> 的 GCD。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>前 4 个奇数的总和 <code>sumOdd = 1 + 3 + 5 + 7 = 16</code></li>
	<li>前 4 个偶数的总和 <code>sumEven = 2 + 4 + 6 + 8 = 20</code></li>
</ul>

<p>因此，<code>GCD(sumOdd, sumEven) = GCD(16, 20) = 4</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>前 5 个奇数的总和 <code>sumOdd = 1 + 3 + 5 + 7 + 9 = 25</code></li>
	<li>前 5 个偶数的总和 <code>sumEven = 2 + 4 + 6 + 8 + 10 = 30</code></li>
</ul>

<p>因此，<code>GCD(sumOdd, sumEven) = GCD(25, 30) = 5</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

前 $n$ 个奇数的总和为 $n^2$，而前 $n$ 个偶数的总和为 $n(n + 1)$。这两者的最大公约数至少为 $n$，又由于 $n$ 和 $n + 1$ 互质，因此答案就是 $n$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def gcdOfOddEvenSums(self, n: int) -> int:
        return n
```

#### Java

```java
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int gcdOfOddEvenSums(int n) {
        return n;
    }
};
```

#### Go

```go
func gcdOfOddEvenSums(n int) int {
	return n
}
```

#### TypeScript

```ts
function gcdOfOddEvenSums(n: number): number {
    return n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
