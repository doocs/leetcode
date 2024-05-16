---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1553.Minimum%20Number%20of%20Days%20to%20Eat%20N%20Oranges/README.md
rating: 2048
source: 第 202 场周赛 Q4
tags:
    - 记忆化搜索
    - 动态规划
---

# [1553. 吃掉 N 个橘子的最少天数](https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges)

[English Version](/solution/1500-1599/1553.Minimum%20Number%20of%20Days%20to%20Eat%20N%20Oranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>厨房里总共有 <code>n</code>&nbsp;个橘子，你决定每一天选择如下方式之一吃这些橘子：</p>

<ul>
	<li>吃掉一个橘子。</li>
	<li>如果剩余橘子数 <code>n</code>&nbsp;能被 2 整除，那么你可以吃掉 <code>n/2</code> 个橘子。</li>
	<li>如果剩余橘子数&nbsp;<code>n</code>&nbsp;能被 3 整除，那么你可以吃掉 <code>2*(n/3)</code> 个橘子。</li>
</ul>

<p>每天你只能从以上 3 种方案中选择一种方案。</p>

<p>请你返回吃掉所有 <code>n</code>&nbsp;个橘子的最少天数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 10
<strong>输出：</strong>4
<strong>解释：</strong>你总共有 10 个橘子。
第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
你需要至少 4 天吃掉 10 个橘子。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6
<strong>输出：</strong>3
<strong>解释：</strong>你总共有 6 个橘子。
第 1 天：吃 3 个橘子，剩余橘子数 6 - 6/2 = 6 - 3 = 3。（6 可以被 2 整除）
第 2 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。（3 可以被 3 整除）
第 3 天：吃掉剩余 1 个橘子，剩余橘子数 1 - 1 = 0。
你至少需要 3 天吃掉 6 个橘子。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 56
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2*10^9</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

根据题目描述，对于每个 $n$，我们可以选择三种方式之一：

1. 将 $n$ 减少 $1$；
2. 如果 $n$ 能被 $2$ 整除，将 $n$ 的值除以 $2$；
3. 如果 $n$ 能被 $3$ 整除，将 $n$ 的值除以 $3$。

因此，问题等价于求解通过上述三种方式，将 $n$ 减少到 $0$ 的最少天数。

我们设计一个函数 $dfs(n)$，表示将 $n$ 减少到 $0$ 的最少天数。函数 $dfs(n)$ 的执行过程如下：

1. 如果 $n < 2$，返回 $n$；
2. 否则，我们可以先通过 $n \bmod 2$ 次操作 $1$，将 $n$ 减少到 $2$ 的倍数，然后执行操作 $2$，将 $n$ 减少到 $n/2$；我们也可以先通过 $n \bmod 3$ 次操作 $1$，将 $n$ 减少到 $3$ 的倍数，然后执行操作 $3$，将 $n$ 减少到 $n/3$。我们选择上述两种方式中最少的一种，即 $1 + \min(n \bmod 2 + dfs(n/2), n \bmod 3 + dfs(n/3))$。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的 $dfs(n)$ 的值存储在哈希表中。

时间复杂度 $O(\log^2 n)$，空间复杂度 $O(\log^2 n)$。

<!-- tabs:start -->

```python
class Solution:
    def minDays(self, n: int) -> int:
        @cache
        def dfs(n: int) -> int:
            if n < 2:
                return n
            return 1 + min(n % 2 + dfs(n // 2), n % 3 + dfs(n // 3))

        return dfs(n)
```

```java
class Solution {
    private Map<Integer, Integer> f = new HashMap<>();

    public int minDays(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n < 2) {
            return n;
        }
        if (f.containsKey(n)) {
            return f.get(n);
        }
        int res = 1 + Math.min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));
        f.put(n, res);
        return res;
    }
}
```

```cpp
class Solution {
public:
    unordered_map<int, int> f;

    int minDays(int n) {
        return dfs(n);
    }

    int dfs(int n) {
        if (n < 2) {
            return n;
        }
        if (f.count(n)) {
            return f[n];
        }
        int res = 1 + min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));
        f[n] = res;
        return res;
    }
};
```

```go
func minDays(n int) int {
	f := map[int]int{0: 0, 1: 1}
	var dfs func(int) int
	dfs = func(n int) int {
		if v, ok := f[n]; ok {
			return v
		}
		res := 1 + min(n%2+dfs(n/2), n%3+dfs(n/3))
		f[n] = res
		return res
	}
	return dfs(n)
}
```

```ts
function minDays(n: number): number {
    const f: Record<number, number> = {};
    const dfs = (n: number): number => {
        if (n < 2) {
            return n;
        }
        if (f[n] !== undefined) {
            return f[n];
        }
        f[n] = 1 + Math.min((n % 2) + dfs((n / 2) | 0), (n % 3) + dfs((n / 3) | 0));
        return f[n];
    };
    return dfs(n);
}
```

<!-- tabs:end -->

<!-- end -->
