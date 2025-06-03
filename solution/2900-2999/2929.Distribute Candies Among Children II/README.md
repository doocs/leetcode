---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2929.Distribute%20Candies%20Among%20Children%20II/README.md
rating: 1701
source: 第 117 场双周赛 Q2
tags:
    - 数学
    - 组合数学
    - 枚举
---

<!-- problem:start -->

# [2929. 给小朋友们分糖果 II](https://leetcode.cn/problems/distribute-candies-among-children-ii)

[English Version](/solution/2900-2999/2929.Distribute%20Candies%20Among%20Children%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数&nbsp;<code>n</code> 和&nbsp;<code>limit</code>&nbsp;。</p>

<p>请你将 <code>n</code>&nbsp;颗糖果分给 <code>3</code>&nbsp;位小朋友，确保没有任何小朋友得到超过 <code>limit</code>&nbsp;颗糖果，请你返回满足此条件下的&nbsp;<strong>总方案数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 5, limit = 2
<b>输出：</b>3
<b>解释：</b>总共有 3 种方法分配 5 颗糖果，且每位小朋友的糖果数不超过 2 ：(1, 2, 2) ，(2, 1, 2) 和 (2, 2, 1) 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, limit = 3
<b>输出：</b>10
<b>解释：</b>总共有 10 种方法分配 3 颗糖果，且每位小朋友的糖果数不超过 3 ：(0, 0, 3) ，(0, 1, 2) ，(0, 2, 1) ，(0, 3, 0) ，(1, 0, 2) ，(1, 1, 1) ，(1, 2, 0) ，(2, 0, 1) ，(2, 1, 0) 和 (3, 0, 0) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：组合数学 + 容斥原理

根据题目描述，我们需要将 $n$ 个糖果分给 $3$ 个小孩，每个小孩分到的糖果数在 $[0, limit]$ 之间。

这实际上等价于把 $n$ 个球放入 $3$ 个盒子中。由于盒子可以为空，我们可以再增加 $3$ 个虚拟球，然后再利用隔板法，即一共有 $n + 3$ 个球，我们在其中 $n + 3 - 1$ 个位置插入 $2$ 个隔板，从而将实际的 $n$ 个球分成 $3$ 组，并且允许盒子为空，因此初始方案数为 $C_{n + 2}^2$。

我们需要在这些方案中，排除掉存在盒子分到的小球数超过 $limit$ 的方案。考虑其中有一个盒子分到的小球数超过 $limit$，那么剩下的球（包括虚拟球）最多有 $n + 3 - (limit + 1) = n - limit + 2$ 个，位置数为 $n - limit + 1$，因此方案数为 $C_{n - limit + 1}^2$。由于存在 $3$ 个盒子，因此这样的方案数为 $3 \times C_{n - limit + 1}^2$。这样子算，我们会多排除掉同时存在两个盒子分到的小球数超过 $limit$ 的方案，因此我们需要再加上这样的方案数，即 $3 \times C_{n - 2 \times limit}^2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distributeCandies(self, n: int, limit: int) -> int:
        if n > 3 * limit:
            return 0
        ans = comb(n + 2, 2)
        if n > limit:
            ans -= 3 * comb(n - limit + 1, 2)
        if n - 2 >= 2 * limit:
            ans += 3 * comb(n - 2 * limit, 2)
        return ans
```

#### Java

```java
class Solution {
    public long distributeCandies(int n, int limit) {
        if (n > 3 * limit) {
            return 0;
        }
        long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return ans;
    }

    private long comb2(int n) {
        return 1L * n * (n - 1) / 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long distributeCandies(int n, int limit) {
        auto comb2 = [](int n) {
            return 1LL * n * (n - 1) / 2;
        };
        if (n > 3 * limit) {
            return 0;
        }
        long long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return ans;
    }
};
```

#### Go

```go
func distributeCandies(n int, limit int) int64 {
	comb2 := func(n int) int {
		return n * (n - 1) / 2
	}
	if n > 3*limit {
		return 0
	}
	ans := comb2(n + 2)
	if n > limit {
		ans -= 3 * comb2(n-limit+1)
	}
	if n-2 >= 2*limit {
		ans += 3 * comb2(n-2*limit)
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function distributeCandies(n: number, limit: number): number {
    const comb2 = (n: number) => (n * (n - 1)) / 2;
    if (n > 3 * limit) {
        return 0;
    }
    let ans = comb2(n + 2);
    if (n > limit) {
        ans -= 3 * comb2(n - limit + 1);
    }
    if (n - 2 >= 2 * limit) {
        ans += 3 * comb2(n - 2 * limit);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn distribute_candies(n: i32, limit: i32) -> i64 {
        if n > 3 * limit {
            return 0;
        }
        let mut ans = Self::comb2(n + 2);
        if n > limit {
            ans -= 3 * Self::comb2(n - limit + 1);
        }
        if n - 2 >= 2 * limit {
            ans += 3 * Self::comb2(n - 2 * limit);
        }
        ans
    }

    fn comb2(n: i32) -> i64 {
        (n as i64) * (n as i64 - 1) / 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
