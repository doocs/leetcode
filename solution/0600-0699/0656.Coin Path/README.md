# [656. 成本最小路径 🔒](https://leetcode.cn/problems/coin-path)

[English Version](/solution/0600-0699/0656.Coin%20Path/README_EN.md)

<!-- tags:数组,动态规划 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>coins</code>（下标从 <strong>1</strong> 开始）长度为 <code>n</code>，以及一个整数 <code>maxJump</code>。你可以跳到数组 <code>coins</code> 的任意下标 <code>i</code>（满足 <code>coins[i] != -1</code>），访问下标 <code>i</code> 时需要支付 <code>coins[i]</code>。此外，如果你当前位于下标 <code>i</code>，你只能跳到下标 <code>i + k</code>（满足 <code>i + k &lt;= n</code>），其中 <code>k</code> 是范围 <code>[1, maxJump]</code> 内的一个值。</p>

<p>初始时你位于下标 <code>1</code>（<code>coins[1]</code> 不是 <code>-1</code>）。你的目标是找到一条到达下标 <code>n</code> 的成本最小路径。</p>

<p>返回一个整数数组，包含你访问的下标顺序，以便你以最小成本达到下标 <code>n</code> 。如果存在多条成本相同的路径，返回<strong> 字典序最小 </strong>的路径。如果无法达到下标 <code>n</code> ，返回一个空数组。</p>

<p>路径 <code>p1 = [Pa<sub>1</sub>, Pa<sub>2</sub>, ..., Pa<sub>x</sub>]</code> 的长度为 <code>x</code>，路径 <code>p2 = [Pb<sub>1</sub>, Pb<sub>2</sub>, ..., Pb<sub>x</sub>]</code> 的长度为 <code>y</code> ，如果在两条路径的第一个不同的下标 <code>j</code> 处，<code>Pa<sub>j</sub></code> 小于 <code>Pb<sub>j</sub></code>，则 <code>p1</code> 在字典序上小于 <code>p2</code>；如果不存在这样的 <code>j</code>，则较短的路径字典序较小。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>coins = [1,2,4,-1,2], maxJump = 2
<strong>输出：</strong>[1,3,5]
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>coins = [1,2,4,-1,2], maxJump = 1
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 1000</code></li>
	<li><code>-1 &lt;= coins[i] &lt;= 100</code></li>
	<li><code>coins[1] != -1</code></li>
	<li><code>1 &lt;= maxJump &lt;= 100</code></li>
</ul>

## 解法

### 方法一：动态规划（逆向）

题目需要我们找到从下标 1 到下标 n 的最小花费路径，且字典序最小，我们可以使用动态规划求解。

我们定义 $f[i]$ 表示从下标 $i$ 到下标 $n-1$ 的最小花费。如果 $coins[n - 1] = -1$，则不存在从下标 $n-1$ 到下标 $n-1$ 的路径，直接返回空数组即可。否则 $f[n - 1] = coins[n - 1]$。

接下来，我们从下标 $n-2$ 开始，逆向遍历数组，对于下标 $i$，如果 $coins[i] = -1$，则 $f[i] = \infty$，否则 $f[i] = \min_{j = i + 1}^{min(n - 1, i + maxJump)} f[j] + coins[i]$。

然后我们判断 $f[0]$ 是否为 $\infty$，如果是，则不存在一条满足条件的路径，返回空数组即可。否则，我们的总花费为 $s = f[0]$，我们从下标 0 开始，向后遍历数组，如果 $f[i] = s$，则说明从下标 $i$ 到下标 $n-1$ 的花费为 $s$，我们将 $s$ 减去 $coins[i]$，并将下标 $i+1$ 加入到结果数组中，直到遍历到下标 $n-1$，返回结果数组即可。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组的长度和最大跳跃长度。

<!-- tabs:start -->

```python
class Solution:
    def cheapestJump(self, coins: List[int], maxJump: int) -> List[int]:
        if coins[-1] == -1:
            return []
        n = len(coins)
        f = [inf] * n
        f[-1] = coins[-1]
        for i in range(n - 2, -1, -1):
            if coins[i] != -1:
                for j in range(i + 1, min(n, i + maxJump + 1)):
                    if f[i] > f[j] + coins[i]:
                        f[i] = f[j] + coins[i]
        if f[0] == inf:
            return []
        ans = []
        s = f[0]
        for i in range(n):
            if f[i] == s:
                s -= coins[i]
                ans.append(i + 1)
        return ans
```

```java
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        List<Integer> ans = new ArrayList<>();
        if (coins[n - 1] == -1) {
            return ans;
        }
        int[] f = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (coins[i] != -1) {
                for (int j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                    if (f[i] > f[j] + coins[i]) {
                        f[i] = f[j] + coins[i];
                    }
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> cheapestJump(vector<int>& coins, int maxJump) {
        int n = coins.size();
        vector<int> ans;
        if (coins[n - 1] == -1) {
            return ans;
        }
        int f[n];
        const int inf = 1 << 30;
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; ~i; --i) {
            f[i] = inf;
            if (coins[i] != -1) {
                for (int j = i + 1; j < min(n, i + maxJump + 1); ++j) {
                    f[i] = min(f[i], f[j] + coins[i]);
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

```go
func cheapestJump(coins []int, maxJump int) (ans []int) {
	n := len(coins)
	if coins[n-1] == -1 {
		return
	}
	f := make([]int, n)
	f[n-1] = coins[n-1]
	const inf = 1 << 30
	for i := n - 2; i >= 0; i-- {
		f[i] = inf
		if coins[i] != -1 {
			for j := i + 1; j < n && j < i+maxJump+1; j++ {
				if f[i] > f[j]+coins[i] {
					f[i] = f[j] + coins[i]
				}
			}
		}
	}
	if f[0] == inf {
		return
	}
	for i, s := 0, f[0]; i < n; i++ {
		if f[i] == s {
			s -= coins[i]
			ans = append(ans, i+1)
		}
	}
	return
}
```

```ts
function cheapestJump(coins: number[], maxJump: number): number[] {
    const n = coins.length;
    const ans: number[] = [];
    if (coins[n - 1] == -1) {
        return ans;
    }
    const inf = 1 << 30;
    const f: number[] = new Array(n).fill(inf);
    f[n - 1] = coins[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (coins[i] !== -1) {
            for (let j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                f[i] = Math.min(f[i], f[j] + coins[i]);
            }
        }
    }
    if (f[0] === inf) {
        return ans;
    }
    for (let i = 0, s = f[0]; i < n; ++i) {
        if (f[i] == s) {
            s -= coins[i];
            ans.push(i + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
