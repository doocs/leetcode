# [1872. 石子游戏 VIII](https://leetcode.cn/problems/stone-game-viii)

[English Version](/solution/1800-1899/1872.Stone%20Game%20VIII/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 玩一个游戏，两人轮流操作， <strong>Alice 先手</strong> 。</p>

<p>总共有 <code>n</code> 个石子排成一行。轮到某个玩家的回合时，如果石子的数目 <strong>大于 1</strong> ，他将执行以下操作：</p>

<ol>
	<li>选择一个整数 <code>x &gt; 1</code> ，并且 <strong>移除</strong> 最左边的 <code>x</code> 个石子。</li>
	<li>将<strong> 移除</strong> 的石子价值之 <strong>和</strong> 累加到该玩家的分数中。</li>
	<li>将一个 <strong>新的石子</strong> 放在最左边，且新石子的值为被移除石子值之和。</li>
</ol>

<p>当只剩下 <strong>一个</strong> 石子时，游戏结束。</p>

<p>Alice 和 Bob 的 <strong>分数之差</strong> 为 <code>(Alice 的分数 - Bob 的分数)</code> 。 Alice 的目标是<strong> 最大化</strong> 分数差，Bob 的目标是 <strong>最小化</strong> 分数差。</p>

<p>给你一个长度为 <code>n</code> 的整数数组 <code>stones</code> ，其中 <code>stones[i]</code> 是 <strong>从左边起</strong> 第 <code>i</code> 个石子的价值。请你返回在双方都采用 <strong>最优</strong> 策略的情况下，Alice 和 Bob 的 <strong>分数之差</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>stones = [-1,2,-3,4,-5]
<b>输出：</b>5
<strong>解释：</strong>
- Alice 移除最左边的 4 个石子，得分增加 (-1) + 2 + (-3) + 4 = 2 ，并且将一个价值为 2 的石子放在最左边。stones = [2,-5] 。
- Bob 移除最左边的 2 个石子，得分增加 2 + (-5) = -3 ，并且将一个价值为 -3 的石子放在最左边。stones = [-3] 。
两者分数之差为 2 - (-3) = 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>stones = [7,-6,5,10,5,-2,-6]
<b>输出：</b>13
<b>解释：</b>
- Alice 移除所有石子，得分增加 7 + (-6) + 5 + 10 + 5 + (-2) + (-6) = 13 ，并且将一个价值为 13 的石子放在最左边。stones = [13] 。
两者分数之差为 13 - 0 = 13 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>stones = [-10,-12]
<b>输出：</b>-22
<strong>解释：</strong>
- Alice 只有一种操作，就是移除所有石子。得分增加 (-10) + (-12) = -22 ，并且将一个价值为 -22 的石子放在最左边。stones = [-22] 。
两者分数之差为 (-22) - 0 = -22 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == stones.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= stones[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 记忆化搜索**

根据题目描述，每次取走最左边的 $x$ 个石子，把它们的和加到自己的分数中，然后把一个价值为这个和的石子放在最左边，相当于把这 $x$ 个石子合并成了一个价值为这个和的石子，前缀和不变。

我们可以用一个长度为 $n$ 的前缀和数组 $s$ 来表示数组 $stones$ 的前缀和，其中 $s[i]$ 表示 $stones[0..i]$ 的元素和。

接下来，我们设计一个函数 $dfs(i)$，表示当前从 $stones[i:]$ 中取石子，返回当前玩家能得到的最大分数差。

函数 $dfs(i)$ 的执行过程如下：

-   如果 $i \geq n - 1$，说明当前只能取走全部石子，因此返回 $s[n - 1]$。
-   否则，我们可以选择从 $stones[i + 1:]$ 中取走全部石子，得到的分数差为 $dfs(i + 1)$；也可以选择取走 $stones[:i]$ 的石子，得到的分数差为 $s[i] - dfs(i + 1)$。我们取两种情况中的最大值，即为当前玩家能得到的最大分数差。

最终，我们可以得到 Alice 和 Bob 的分数之差为 $dfs(1)$，即 $Alice$ 必须从 $stones[1:]$ 中取石子开始游戏。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $stones$ 的长度。

**方法二：前缀和 + 动态规划**

我们也可以使用动态规划的方法来解决这个问题。

与方法一类似，我们先用一个长度为 $n$ 的前缀和数组 $s$ 来表示数组 $stones$ 的前缀和，其中 $s[i]$ 表示 $stones[0..i]$ 的元素和。

我们定义 $f[i]$ 表示当前从 $stones[i:]$ 中取石子，返回当前玩家能得到的最大分数差。

若玩家选择取走 $stones[:i]$ 的石子，那么获得的分数为 $s[i]$，此时另一个玩家会在 $stones[i+1:]$ 中取石子，那么另一个玩家能得到的最大分数差为 $f[i+1]$，因此当前玩家能得到的最大分数差为 $s[i] - f[i+1]$。

若玩家选择从 $stones[i+1:]$ 中取石子，那么获得的最大分数差为 $f[i+1]$。

因此我们可以得到状态转移方程：

$$
f[i] = \max\{s[i] - f[i+1], f[i+1]\}
$$

最终，我们可以得到 Alice 和 Bob 的分数之差为 $f[1]$，即 $Alice$ 必须从 $stones[1:]$ 中取石子开始游戏。

我们注意到 $f[i]$ 只与 $f[i+1]$ 有关，因此我们只需要使用一个变量 $f$ 来表示 $f[i]$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $stones$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stones) - 1:
                return s[-1]
            return max(dfs(i + 1), s[i] - dfs(i + 1))

        s = list(accumulate(stones))
        return dfs(1)
```

```python
class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        s = list(accumulate(stones))
        f = s[-1]
        for i in range(len(s) - 2, 0, -1):
            f = max(f, s[i] - f)
        return f
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[] f;
    private int[] s;
    private int n;

    public int stoneGameVIII(int[] stones) {
        n = stones.length;
        f = new Integer[n];
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        s = stones;
        return dfs(1);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return s[i];
        }
        if (f[i] == null) {
            f[i] = Math.max(dfs(i + 1), s[i] - dfs(i + 1));
        }
        return f[i];
    }
}
```

```java
class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f = stones[n - 1];
        for (int i = n - 2; i > 0; --i) {
            f = Math.max(f, stones[i] - f);
        }
        return f;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n - 1) {
                return stones[i];
            }
            if (f[i] == -1) {
                f[i] = max(dfs(i + 1), stones[i] - dfs(i + 1));
            }
            return f[i];
        };
        return dfs(1);
    }
};
```

```cpp
class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f = stones.back();
        for (int i = n - 2; i; --i) {
            f = max(f, stones[i] - f);
        }
        return f;
    }
};
```

### **Go**

```go
func stoneGameVIII(stones []int) int {
	n := len(stones)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	for i := 1; i < n; i++ {
		stones[i] += stones[i-1]
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n-1 {
			return stones[i]
		}
		if f[i] == -1 {
			f[i] = max(dfs(i+1), stones[i]-dfs(i+1))
		}
		return f[i]
	}
	return dfs(1)
}
```

### **TypeScript**

```ts
function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    const f: number[] = Array(n).fill(-1);
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    const dfs = (i: number): number => {
        if (i >= n - 1) {
            return stones[i];
        }
        if (f[i] === -1) {
            f[i] = Math.max(dfs(i + 1), stones[i] - dfs(i + 1));
        }
        return f[i];
    };
    return dfs(1);
}
```

```ts
function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    let f = stones[n - 1];
    for (let i = n - 2; i; --i) {
        f = Math.max(f, stones[i] - f);
    }
    return f;
}
```

### **...**

```

```

<!-- tabs:end -->
