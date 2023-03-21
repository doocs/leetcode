# [1269. 停在原地的方案数](https://leetcode.cn/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps)

[English Version](/solution/1200-1299/1269.Number%20of%20Ways%20to%20Stay%20in%20the%20Same%20Place%20After%20Some%20Steps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个长度为 <code>arrLen</code> 的数组，开始有一个指针在索引 <code>0</code> 处。</p>

<p>每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。</p>

<p>给你两个整数 <code>steps</code> 和 <code>arrLen</code> ，请你计算并返回：在恰好执行 <code>steps</code> 次操作以后，指针仍然指向索引 <code>0</code> 处的方案数。</p>

<p>由于答案可能会很大，请返回方案数 <strong>模</strong> <code>10^9 + 7</code> 后的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>steps = 3, arrLen = 2
<strong>输出：</strong>4
<strong>解释：</strong>3 步后，总共有 4 种不同的方法可以停在索引 0 处。
向右，向左，不动
不动，向右，向左
向右，不动，向左
不动，不动，不动
</pre>

<p><strong>示例  2：</strong></p>

<pre>
<strong>输入：</strong>steps = 2, arrLen = 4
<strong>输出：</strong>2
<strong>解释：</strong>2 步后，总共有 2 种不同的方法可以停在索引 0 处。
向右，向左
不动，不动
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>steps = 4, arrLen = 2
<strong>输出：</strong>8
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= steps <= 500</code></li>
	<li><code>1 <= arrLen <= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们观察题目的数据范围，可以发现 $steps$ 最大不超过 $500$，这意味着我们最远只能往右走 $500$ 步。

我们可以设计一个函数 $dfs(i, j)$，表示当前在位置 $i$，并且剩余步数为 $j$ 的方案数。那么答案就是 $dfs(0, steps)$。

函数 $dfs(i, j)$ 的执行过程如下：

1. 如果 $i \gt j$ 或者 $i \geq arrLen$ 或者 $i \lt 0$ 或者 $j \lt 0$，那么返回 $0$。
1. 如果 $i = 0$ 且 $j = 0$，那么此时指针已经停在原地，并且没有剩余步数，所以返回 $1$。
1. 否则，我们可以选择向左走一步，向右走一步，或者不动，所以返回 $dfs(i - 1, j - 1) + dfs(i + 1, j - 1) + dfs(i, j - 1)$。注意答案的取模操作。

过程中，我们可以使用记忆化搜索避免重复计算。

时间复杂度 $O(steps \times steps)$，空间复杂度 $O(steps \times steps)$。其中 $steps$ 是题目给定的步数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numWays(self, steps: int, arrLen: int) -> int:
        @cache
        def dfs(i, j):
            if i > j or i >= arrLen or i < 0 or j < 0:
                return 0
            if i == 0 and j == 0:
                return 1
            ans = 0
            for k in range(-1, 2):
                ans += dfs(i + k, j - 1)
                ans %= mod
            return ans

        mod = 10**9 + 7
        return dfs(0, steps)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[][] f;
    private int n;

    public int numWays(int steps, int arrLen) {
        f = new Integer[steps][steps + 1];
        n = arrLen;
        return dfs(0, steps);
    }

    private int dfs(int i, int j) {
        if (i > j || i >= n || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return f[i][j] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(int steps, int arrLen) {
        int f[steps][steps + 1];
        memset(f, -1, sizeof f);
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i > j || i >= arrLen || i < 0 || j < 0) {
                return 0;
            }
            if (i == 0 && j == 0) {
                return 1;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 0;
            for (int k = -1; k <= 1; ++k) {
                ans = (ans + dfs(i + k, j - 1)) % mod;
            }
            return f[i][j] = ans;
        };
        return dfs(0, steps);
    }
};
```

### **Go**

```go
func numWays(steps int, arrLen int) int {
	const mod int = 1e9 + 7
	f := make([][]int, steps)
	for i := range f {
		f[i] = make([]int, steps+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) (ans int) {
		if i > j || i >= arrLen || i < 0 || j < 0 {
			return 0
		}
		if i == 0 && j == 0 {
			return 1
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		for k := -1; k <= 1; k++ {
			ans += dfs(i+k, j-1)
			ans %= mod
		}
		f[i][j] = ans
		return
	}
	return dfs(0, steps)
}
```

### **TypeScript**

```ts
function numWays(steps: number, arrLen: number): number {
    const f = Array.from({ length: steps }, () => Array(steps + 1).fill(-1));
    const mod = 10 ** 9 + 7;
    const dfs = (i: number, j: number) => {
        if (i > j || i >= arrLen || i < 0 || j < 0) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        let ans = 0;
        for (let k = -1; k <= 1; ++k) {
            ans = (ans + dfs(i + k, j - 1)) % mod;
        }
        return (f[i][j] = ans);
    };
    return dfs(0, steps);
}
```

### **...**

```

```

<!-- tabs:end -->
