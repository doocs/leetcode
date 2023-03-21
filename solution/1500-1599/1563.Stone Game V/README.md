# [1563. 石子游戏 V](https://leetcode.cn/problems/stone-game-v)

[English Version](/solution/1500-1599/1563.Stone%20Game%20V/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>几块石子 <strong>排成一行</strong> ，每块石子都有一个关联值，关联值为整数，由数组 <code>stoneValue</code> 给出。</p>

<p>游戏中的每一轮：Alice 会将这行石子分成两个 <strong>非空行</strong>（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。</p>

<p>只 <strong>剩下一块石子</strong> 时，游戏结束。Alice 的分数最初为 <strong><code>0</code></strong> 。</p>

<p>返回 <strong>Alice 能够获得的最大分数</strong><em> 。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>stoneValue = [6,2,3,4,5,5]
<strong>输出：</strong>18
<strong>解释：</strong>在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>stoneValue = [7,7,7,7,7,7,7]
<strong>输出：</strong>28
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>stoneValue = [4]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stoneValue.length &lt;= 500</code></li>
	<li><code>1 &lt;=&nbsp;stoneValue[i] &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索 + 剪枝**

我们先预处理出前缀和数组 $s$，其中 $s[i]$ 表示数组 $stoneValue$ 前 $i$ 个元素的和。

接下来，我们设计一个函数 $dfs(i, j)$，表示数组 $stoneValue$ 中下标范围 $[i, j]$ 内的石子，Alice 能够获得的最大分数。那么答案就是 $dfs(0, n - 1)$。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $i = j$，说明只剩下一块石子，Alice 无法进行分割，因此返回 $0$。
-   否则，我们枚举分割点 $k$，即 $i \leq k \lt j$，将数组 $stoneValue$ 中下标范围 $[i, j]$ 内的石子分割为 $[i, k]$ 和 $[k + 1, j]$ 两部分，计算出 $a$ 和 $b$，分别表示两部分的石子总和。然后我们分别计算 $dfs(i, k)$ 和 $dfs(k + 1, j)$，并更新答案。

注意，如果满足 $a \lt b$ 并且 $ans \geq a \times 2$，那么这一次枚举可以跳过；如果满足 $a \gt b$ 并且 $ans \geq b \times 2$，那么后续的枚举都可以跳过，直接退出循环。

最后，我们返回答案即可。

为了避免重复计算，我们可以使用记忆化搜索，同时使用剪枝优化枚举的效率。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $stoneValue$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        @cache
        def dfs(i, j):
            if i == j:
                return 0
            ans = a = 0
            for k in range(i, j):
                a += stoneValue[k]
                b = s[j + 1] - s[i] - a
                if a < b:
                    if ans >= a * 2:
                        continue
                    ans = max(ans, a + dfs(i, k))
                elif a > b:
                    if ans >= b * 2:
                        break
                    ans = max(ans, b + dfs(k + 1, j))
                else:
                    ans = max(ans, a + dfs(i, k), b + dfs(k + 1, j))
            return ans

        s = list(accumulate(stoneValue, initial=0))
        return dfs(0, len(stoneValue) - 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[] s;
    private int[] stoneValue;
    private Integer[][] f;

    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        this.stoneValue = stoneValue;
        s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        f = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        int a = 0;
        for (int k = i; k < j; ++k) {
            a += stoneValue[k];
            int b = s[j + 1] - s[i] - a;
            if (a < b) {
                if (ans >= a * 2) {
                    continue;
                }
                ans = Math.max(ans, a + dfs(i, k));
            } else if (a > b) {
                if (ans >= b * 2) {
                    break;
                }
                ans = Math.max(ans, b + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, Math.max(a + dfs(i, k), b + dfs(k + 1, j)));
            }
        }
        return f[i][j] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n][n];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i == j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int ans = 0;
            int a = 0;
            for (int k = i; k < j; ++k) {
                a += stoneValue[k];
                int b = s[j + 1] - s[i] - a;
                if (a < b) {
                    if (ans >= a * 2) {
                        continue;
                    }
                    ans = max(ans, a + dfs(i, k));
                } else if (a > b) {
                    if (ans >= b * 2) {
                        break;
                    }
                    ans = max(ans, b + dfs(k + 1, j));
                } else {
                    ans = max({ans, a + dfs(i, k), b + dfs(k + 1, j)});
                }
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
```

### **Go**

```go
func stoneGameV(stoneValue []int) int {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans, a := 0, 0
		for k := i; k < j; k++ {
			a += stoneValue[k]
			b := s[j+1] - s[i] - a
			if a < b {
				if ans >= a*2 {
					continue
				}
				ans = max(ans, a+dfs(i, k))
			} else if a > b {
				if ans >= b*2 {
					break
				}
				ans = max(ans, b+dfs(k+1, j))
			} else {
				ans = max(ans, max(a+dfs(i, k), b+dfs(k+1, j)))
			}
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
