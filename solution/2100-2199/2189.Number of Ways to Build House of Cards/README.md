---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/README.md
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [2189. 建造纸牌屋的方法数 🔒](https://leetcode.cn/problems/number-of-ways-to-build-house-of-cards)

[English Version](/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，代表你拥有牌的数量。一个&nbsp;<strong>纸牌屋&nbsp;</strong>满足以下条件:</p>

<ul>
	<li>一个<strong> 纸牌屋&nbsp;</strong>由一行或多行&nbsp;<strong>三角形</strong> 和水平纸牌组成。</li>
	<li><strong>三角形&nbsp;</strong>是由两张纸牌相互靠在一起形成的。</li>
	<li>一张纸牌必须水平放置在一行中&nbsp;<strong>所有相邻&nbsp;</strong>的三角形之间。</li>
	<li>比第一行高的任何三角形都必须放在前一行的水平纸牌上。</li>
	<li>每个三角形都被放置在行中&nbsp;<strong>最左边&nbsp;</strong>的可用位置。</li>
</ul>

<p>返回<em>使用所有 <code>n</code> 张</em>纸牌<em>可以构建的不同纸牌屋的数量</em>。如果存在一行两个纸牌屋包含不同数量的纸牌，那么两个纸牌屋被认为是不同的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213243-1.png" style="width: 726px; height: 150px;" />
<pre>
<strong>输入:</strong> n = 16
<strong>输出:</strong> 2
<strong>解释:</strong> 有两种有效的纸牌屋摆法。
图中的第三个纸牌屋无效，因为第一行最右边的三角形没有放在水平纸牌的顶部。
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213306-2.png" style="width: 96px; height: 80px;" />
<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 1
<strong>解释:</strong> 这是唯一可行的纸牌屋。</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213331-3.png" style="width: 330px; height: 85px;" />
<pre>
<strong>输入:</strong> n = 4
<strong>输出:</strong> 0
<strong>解释:</strong> 图中的三种纸牌都是无效的。
第一个纸牌屋需要在两个三角形之间放置一张水平纸牌。
第二个纸牌屋使用 5 张纸牌。
第三个纸牌屋使用 2 张纸牌。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们注意到，每一层的卡片数量为 $3 \times k + 2$，并且每一层的卡片数量都不相同。因此，问题可以转化为：整数 $n$ 可以由多少种 $3 \times k + 2$ 的数相加得到。这是一个经典的背包问题，可以使用记忆化搜索解决。

我们设计一个函数 $\text{dfs}(n, k)$，表示当前剩余卡片数量为 $n$，且当前层为 $k$ 时，可以构建多少不同的纸牌屋。那么答案就是 $\text{dfs}(n, 0)$。

函数 $\text{dfs}(n, k)$ 的执行逻辑如下：

-   如果 $3 \times k + 2 \gt n$，那么当前层无法放置任何卡片，返回 $0$；
-   如果 $3 \times k + 2 = n$，那么当前层可以放置卡片，放置完毕后，整个纸牌屋已经构建完毕，返回 $1$；
-   否则，我们可以选择不放置卡片，或者放置卡片。如果选择不放置卡片，那么剩余卡片数量不变，层数增加 $1$，即 $\text{dfs}(n, k + 1)$；如果选择放置卡片，那么剩余卡片数量减少 $3 \times k + 2$，层数增加 $1$，即 $\text{dfs}(n - (3 \times k + 2), k + 1)$。两者相加即为答案。

过程中，我们可以使用记忆化搜索，避免重复计算。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为卡片数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def houseOfCards(self, n: int) -> int:
        @cache
        def dfs(n: int, k: int) -> int:
            x = 3 * k + 2
            if x > n:
                return 0
            if x == n:
                return 1
            return dfs(n - x, k + 1) + dfs(n, k + 1)

        return dfs(n, 0)
```

#### Java

```java
class Solution {
    private Integer[][] f;

    public int houseOfCards(int n) {
        f = new Integer[n + 1][n / 3];
        return dfs(n, 0);
    }

    private int dfs(int n, int k) {
        int x = 3 * k + 2;
        if (x > n) {
            return 0;
        }
        if (x == n) {
            return 1;
        }
        if (f[n][k] != null) {
            return f[n][k];
        }
        return f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int houseOfCards(int n) {
        int f[n + 1][n / 3 + 1];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int n, int k) -> int {
            int x = 3 * k + 2;
            if (x > n) {
                return 0;
            }
            if (x == n) {
                return 1;
            }
            if (f[n][k] != -1) {
                return f[n][k];
            }
            return f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
        };
        return dfs(n, 0);
    }
};
```

#### Go

```go
func houseOfCards(n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n/3+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(n, k int) int
	dfs = func(n, k int) int {
		x := 3*k + 2
		if x > n {
			return 0
		}
		if x == n {
			return 1
		}
		if f[n][k] == -1 {
			f[n][k] = dfs(n-x, k+1) + dfs(n, k+1)
		}
		return f[n][k]
	}
	return dfs(n, 0)
}
```

#### TypeScript

```ts
function houseOfCards(n: number): number {
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(Math.floor(n / 3) + 1).fill(-1));
    const dfs = (n: number, k: number): number => {
        const x = k * 3 + 2;
        if (x > n) {
            return 0;
        }
        if (x === n) {
            return 1;
        }
        if (f[n][k] === -1) {
            f[n][k] = dfs(n - x, k + 1) + dfs(n, k + 1);
        }
        return f[n][k];
    };
    return dfs(n, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
