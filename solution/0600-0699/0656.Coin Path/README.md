# [656. 金币路径](https://leetcode.cn/problems/coin-path)

[English Version](/solution/0600-0699/0656.Coin%20Path/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>A</code>（下标从 <code>1</code> 开始）包含 N 个整数：A<sub>1</sub>，A<sub>2</sub>，&hellip;&hellip;，A<sub>N</sub>&nbsp;和一个整数 <code>B</code>。你可以从数组 <code>A</code> 中的任何一个位置（下标为 <code>i</code>）跳到下标&nbsp;<code>i+1</code>，<code>i+2</code>，&hellip;&hellip;，<code>i+B</code>&nbsp;的任意一个可以跳到的位置上。如果你在下标为 <code>i</code> 的位置上，你需要支付 A<sub>i</sub> 个金币。如果 A<sub>i</sub> 是 -1，意味着下标为 <code>i</code> 的位置是不可以跳到的。</p>

<p>现在，你希望花费最少的金币从数组 <code>A</code> 的 <code>1</code> 位置跳到&nbsp;<code>N</code> 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。</p>

<p>如果有多种花费最少的方案，输出字典顺序最小的路径。</p>

<p>如果无法到达 N 位置，请返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong>样例 1 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 2
<strong>输出:</strong> [1,3,5]
</pre>

<p>&nbsp;</p>

<p><strong>样例 2 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 1
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>注释 :</strong></p>

<ol>
	<li>路径 Pa<sub>1</sub>，Pa<sub>2</sub>，&hellip;&hellip;，Pa<sub>n&nbsp;</sub>是字典序小于 Pb<sub>1</sub>，Pb<sub>2</sub>，&hellip;&hellip;，Pb<sub>m&nbsp;</sub>的，当且仅当第一个 Pa<sub>i</sub> 和 Pb<sub>i</sub> 不同的 <code>i</code> 满足 Pa<sub>i</sub> &lt; Pb<sub>i</sub>，如果不存在这样的 <code>i</code> 那么满足 <code>n</code> &lt; <code>m</code>。</li>
	<li>A<sub>1</sub> &gt;= 0。&nbsp;A<sub>2</sub>, ..., A<sub>N</sub>&nbsp;（如果存在）&nbsp;的范围是 [-1, 100]。</li>
	<li>A 数组的长度范围 [1, 1000].</li>
	<li>B 的范围&nbsp;[1, 100].</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划（逆向）**

题目需要我们找到从下标 1 到下标 n 的最小花费路径，且字典序最小，我们可以使用动态规划求解。

我们定义 $f[i]$ 表示从下标 $i$ 到下标 $n-1$ 的最小花费。如果 $coins[n - 1] = -1$，则不存在从下标 $n-1$ 到下标 $n-1$ 的路径，直接返回空数组即可。否则 $f[n - 1] = coins[n - 1]$。

接下来，我们从下标 $n-2$ 开始，逆向遍历数组，对于下标 $i$，如果 $coins[i] = -1$，则 $f[i] = \infty$，否则 $f[i] = \min_{j = i + 1}^{min(n - 1, i + maxJump)} f[j] + coins[i]$。

然后我们判断 $f[0]$ 是否为 $\infty$，如果是，则不存在一条满足条件的路径，返回空数组即可。否则，我们的总花费为 $s = f[0]$，我们从下标 0 开始，向后遍历数组，如果 $f[i] = s$，则说明从下标 $i$ 到下标 $n-1$ 的花费为 $s$，我们将 $s$ 减去 $coins[i]$，并将下标 $i+1$ 加入到结果数组中，直到遍历到下标 $n-1$，返回结果数组即可。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为数组的长度和最大跳跃长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
