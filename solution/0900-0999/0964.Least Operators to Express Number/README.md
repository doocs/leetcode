---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0964.Least%20Operators%20to%20Express%20Number/README.md
tags:
    - 记忆化搜索
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [964. 表示数字的最少运算符](https://leetcode.cn/problems/least-operators-to-express-number)

[English Version](/solution/0900-0999/0964.Least%20Operators%20to%20Express%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>x</code>，我们将会写出一个形如&nbsp;<code>x (op1) x (op2) x (op3) x ...</code>&nbsp;的表达式，其中每个运算符&nbsp;<code>op1</code>，<code>op2</code>，… 可以是加、减、乘、除（<code>+</code>，<code>-</code>，<code>*</code>，或是&nbsp;<code>/</code>）之一。例如，对于&nbsp;<code>x = 3</code>，我们可以写出表达式&nbsp;<code>3 * 3 / 3 + 3 - 3</code>，该式的值为 3 。</p>

<p>在写这样的表达式时，我们需要遵守下面的惯例：</p>

<ul>
	<li>除运算符（<code>/</code>）返回有理数。</li>
	<li>任何地方都没有括号。</li>
	<li>我们使用通常的操作顺序：乘法和除法发生在加法和减法之前。</li>
	<li>不允许使用一元否定运算符（<code>-</code>）。例如，“<code>x - x</code>” 是一个有效的表达式，因为它只使用减法，但是 “<code>-x + x</code>” 不是，因为它使用了否定运算符。&nbsp;</li>
</ul>

<p>我们希望编写一个能使表达式等于给定的目标值 <code>target</code> 且运算符最少的表达式。返回所用运算符的最少数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 3, target = 19
<strong>输出：</strong>5
<strong>解释：</strong>3 * 3 + 3 * 3 + 3 / 3 。表达式包含 5 个运算符。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 5, target = 501
<strong>输出：</strong>8
<strong>解释：</strong>5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5 。表达式包含 8 个运算符。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 100, target = 100000000
<strong>输出：</strong>3
<strong>解释：</strong>100 * 100 * 100 * 100 。表达式包含 3 个运算符。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= x &lt;= 100</code></li>
	<li><code>1 &lt;= target &lt;= 2 * 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们定义一个函数 $dfs(v)$，表示用 $x$ 凑成数字 $v$ 所需要的最少运算符数量。那么答案就是 $dfs(target)$。

函数 $dfs(v)$ 的执行逻辑如下：

如果 $x \geq v$，那么此时可以用 $v$ 个 $x / x$ 相加来得到 $v$，运算符数量为 $v \times 2 - 1$；也可以用 $x$ 减去 $(x - v)$ 个 $x / x$ 来得到 $v$，运算符数量为 $(x - v) \times 2$。取两者的最小值。

否则，我们从 $k=2$ 开始枚举 $x^k$，找到第一个 $x^k \geq v$ 的 $k$：

-   如果此时 $x^k - v \geq v$，那么只能先得到 $x^{k-1}$，然后再递归计算 $dfs(v - x^{k-1})$，此时运算符数量为 $k - 1 + dfs(v - x^{k-1})$；
-   如果此时 $x^k - v < v$，那么可以按照上面的方式得到 $v$，此时运算符数量为 $k - 1 + dfs(v - x^{k-1})$；也可以先得到 $x^k$，再递归计算 $dfs(x^k - v)$，此时运算符数量为 $k + dfs(x^k - v)$。取两者的最小值。

为了避免重复计算，我们使用记忆化搜索的方式实现 $dfs$ 函数。

时间复杂度 $O(\log_{x}{target})$，空间复杂度 $O(\log_{x}{target})$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leastOpsExpressTarget(self, x: int, target: int) -> int:
        @cache
        def dfs(v: int) -> int:
            if x >= v:
                return min(v * 2 - 1, 2 * (x - v))
            k = 2
            while x**k < v:
                k += 1
            if x**k - v < v:
                return min(k + dfs(x**k - v), k - 1 + dfs(v - x ** (k - 1)))
            return k - 1 + dfs(v - x ** (k - 1))

        return dfs(target)
```

#### Java

```java
class Solution {
    private int x;
    private Map<Integer, Integer> f = new HashMap<>();

    public int leastOpsExpressTarget(int x, int target) {
        this.x = x;
        return dfs(target);
    }

    private int dfs(int v) {
        if (x >= v) {
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        if (f.containsKey(v)) {
            return f.get(v);
        }
        int k = 2;
        long y = (long) x * x;
        while (y < v) {
            y *= x;
            ++k;
        }
        int ans = k - 1 + dfs(v - (int) (y / x));
        if (y - v < v) {
            ans = Math.min(ans, k + dfs((int) y - v));
        }
        f.put(v, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int leastOpsExpressTarget(int x, int target) {
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int v) -> int {
            if (x >= v) {
                return min(v * 2 - 1, 2 * (x - v));
            }
            if (f.count(v)) {
                return f[v];
            }
            int k = 2;
            long long y = x * x;
            while (y < v) {
                y *= x;
                ++k;
            }
            int ans = k - 1 + dfs(v - y / x);
            if (y - v < v) {
                ans = min(ans, k + dfs(y - v));
            }
            f[v] = ans;
            return ans;
        };
        return dfs(target);
    }
};
```

#### Go

```go
func leastOpsExpressTarget(x int, target int) int {
	f := map[int]int{}
	var dfs func(int) int
	dfs = func(v int) int {
		if x > v {
			return min(v*2-1, 2*(x-v))
		}
		if val, ok := f[v]; ok {
			return val
		}
		k := 2
		y := x * x
		for y < v {
			y *= x
			k++
		}
		ans := k - 1 + dfs(v-y/x)
		if y-v < v {
			ans = min(ans, k+dfs(y-v))
		}
		f[v] = ans
		return ans
	}
	return dfs(target)
}
```

#### TypeScript

```ts
function leastOpsExpressTarget(x: number, target: number): number {
    const f: Map<number, number> = new Map();
    const dfs = (v: number): number => {
        if (x > v) {
            return Math.min(v * 2 - 1, 2 * (x - v));
        }
        if (f.has(v)) {
            return f.get(v)!;
        }
        let k = 2;
        let y = x * x;
        while (y < v) {
            y *= x;
            ++k;
        }
        let ans = k - 1 + dfs(v - Math.floor(y / x));
        if (y - v < v) {
            ans = Math.min(ans, k + dfs(y - v));
        }
        f.set(v, ans);
        return ans;
    };
    return dfs(target);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
