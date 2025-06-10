---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3560.Find%20Minimum%20Log%20Transportation%20Cost/README.md
rating: 1339
source: 第 451 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [3560. 木材运输的最小成本](https://leetcode.cn/problems/find-minimum-log-transportation-cost)

[English Version](/solution/3500-3599/3560.Find%20Minimum%20Log%20Transportation%20Cost/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个整数 <code>n</code>、<code>m</code> 和 <code>k</code>。</p>

<p>有两根长度分别为 <code>n</code> 和 <code>m</code> 单位的木材，需要通过三辆卡车运输。每辆卡车最多只能装载一根长度&nbsp;<strong>不超过</strong> <code>k</code> 单位的木材。</p>

<p>你可以将木材切成更小的段，其中将长度为 <code>x</code> 的木材切割成长度为 <code>len1</code> 和 <code>len2</code> 的段的成本为 <code>cost = len1 * len2</code>，并且满足 <code>len1 + len2 = x</code>。</p>

<p>返回将木材分配到卡车上的&nbsp;<strong>最小总成本&nbsp;</strong>。如果木材不需要切割，总成本为 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 6, m = 5, k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>将长度为 6 的木材切割成长度为 1 和 5 的两段，成本为 <code>1 * 5 == 5</code>。现在三段长度分别为 1、5 和 5 的木材可以分别装载到每辆卡车。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, m = 4, k = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>两根木材已经可以直接装载到卡车上，因此不需要切割。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n, m &lt;= 2 * k</code></li>
	<li>输入数据保证木材总存在能被运输的方案。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

如果两根木材的长度都不超过卡车的最大载重 $k$，则不需要切割，直接返回 $0$。

否则，说明只有一个木材的长度超过了 $k$，我们需要将其切割成两段。设较长的木材长度为 $x$，则切割成本为 $k \times (x - k)$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCuttingCost(self, n: int, m: int, k: int) -> int:
        x = max(n, m)
        return 0 if x <= k else k * (x - k)
```

#### Java

```java
class Solution {
public:
    long long minCuttingCost(int n, int m, int k) {
        int x = max(n, m);
        return x <= k ? 0 : 1LL * k * (x - k);
    }
};
```

#### C++

```cpp
class Solution {
public:
    long long minCuttingCost(int n, int m, int k) {
        int x = max(n, m);
        return x <= k ? 0 : 1LL * k * (x - k);
    }
};
```

#### Go

```go
func minCuttingCost(n int, m int, k int) int64 {
	x := max(n, m)
	if x <= k {
		return 0
	}
	return int64(k * (x - k))
}
```

#### TypeScript

```ts
function minCuttingCost(n: number, m: number, k: number): number {
    const x = Math.max(n, m);
    return x <= k ? 0 : k * (x - k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
