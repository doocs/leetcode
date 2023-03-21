# [2400. 恰好移动 k 步到达某一位置的方法数目](https://leetcode.cn/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps)

[English Version](/solution/2400-2499/2400.Number%20of%20Ways%20to%20Reach%20a%20Position%20After%20Exactly%20k%20Steps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>正</strong> 整数 <code>startPos</code> 和 <code>endPos</code> 。最初，你站在 <strong>无限</strong> 数轴上位置 <code>startPos</code> 处。在一步移动中，你可以向左或者向右移动一个位置。</p>

<p>给你一个正整数 <code>k</code> ，返回从 <code>startPos</code> 出发、<strong>恰好</strong> 移动 <code>k</code> 步并到达 <code>endPos</code> 的 <strong>不同</strong> 方法数目。由于答案可能会很大，返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>如果所执行移动的顺序不完全相同，则认为两种方法不同。</p>

<p><strong>注意：</strong>数轴包含负整数<strong>。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>startPos = 1, endPos = 2, k = 3
<strong>输出：</strong>3
<strong>解释：</strong>存在 3 种从 1 到 2 且恰好移动 3 步的方法：
- 1 -&gt; 2 -&gt; 3 -&gt; 2.
- 1 -&gt; 2 -&gt; 1 -&gt; 2.
- 1 -&gt; 0 -&gt; 1 -&gt; 2.
可以证明不存在其他方法，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>startPos = 2, endPos = 5, k = 10
<strong>输出：</strong>0
<strong>解释：</strong>不存在从 2 到 5 且恰好移动 10 步的方法。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= startPos, endPos, k &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j)$，表示当前位置距离目标位置的距离为 $i$，还剩 $j$ 步，有多少种方法到达目标位置。那么答案就是 $dfs(abs(startPos - endPos), k)$。

函数 $dfs(i, j)$ 的计算方式如下：

-   如果 $i \gt j$ 或者 $j \lt 0$，说明当前位置距离目标位置的距离大于剩余步数，或者剩余步数为负数，此时无法到达目标位置，返回 $0$；
-   如果 $j = 0$，说明剩余步数为 $0$，此时只有当前位置距离目标位置的距离为 $0$ 时才能到达目标位置，否则无法到达目标位置，返回 $1$ 或者 $0$；
-   否则，当前位置距离目标位置的距离为 $i$，还剩 $j$ 步，那么有两种方法到达目标位置：
    -   向左移动一步，此时当前位置距离目标位置的距离为 $i + 1$，还剩 $j - 1$ 步，方法数为 $dfs(i + 1, j - 1)$；
    -   向右移动一步，此时当前位置距离目标位置的距离为 $abs(i - 1)$，还剩 $j - 1$ 步，方法数为 $dfs(abs(i - 1), j - 1)$；
-   最后，返回两种方法的和对 $10^9 + 7$ 取余的结果。

为了避免重复计算，我们使用记忆化搜索，即使用一个二维数组 $f$ 记录函数 $dfs(i, j)$ 的结果，当函数 $dfs(i, j)$ 被调用时，如果 $f[i][j]$ 不为 $-1$，则直接返回 $f[i][j]$，否则计算 $f[i][j]$ 的值，并返回 $f[i][j]$。

时间复杂度 $O(k^2)$，空间复杂度 $O(k^2)$。其中 $k$ 为题目给定的步数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j or j < 0:
                return 0
            if j == 0:
                return 1 if i == 0 else 0
            return (dfs(i + 1, j - 1) + dfs(abs(i - 1), j - 1)) % mod

        mod = 10**9 + 7
        return dfs(abs(startPos - endPos), k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int numberOfWays(int startPos, int endPos, int k) {
        f = new Integer[k + 1][k + 1];
        return dfs(Math.abs(startPos - endPos), k);
    }

    private int dfs(int i, int j) {
        if (i > j || j < 0) {
            return 0;
        }
        if (j == 0) {
            return i == 0 ? 1 : 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = dfs(i + 1, j - 1) + dfs(Math.abs(i - 1), j - 1);
        ans %= mod;
        return f[i][j] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfWays(int startPos, int endPos, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1][k + 1];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i > j || j < 0) {
                return 0;
            }
            if (j == 0) {
                return i == 0 ? 1 : 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = (dfs(i + 1, j - 1) + dfs(abs(i - 1), j - 1)) % mod;
            return f[i][j];
        };
        return dfs(abs(startPos - endPos), k);
    }
};
```

### **Go**

```go
func numberOfWays(startPos int, endPos int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j || j < 0 {
			return 0
		}
		if j == 0 {
			if i == 0 {
				return 1
			}
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = (dfs(i+1, j-1) + dfs(abs(i-1), j-1)) % mod
		return f[i][j]
	}
	return dfs(abs(startPos-endPos), k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function numberOfWays(startPos: number, endPos: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f = new Array(k + 1).fill(0).map(() => new Array(k + 1).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i > j || j < 0) {
            return 0;
        }
        if (j === 0) {
            return i === 0 ? 1 : 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + dfs(Math.abs(i - 1), j - 1);
        f[i][j] %= mod;
        return f[i][j];
    };
    return dfs(Math.abs(startPos - endPos), k);
}
```

### **...**

```


```

<!-- tabs:end -->
