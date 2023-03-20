# [1012. 至少有 1 位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits)

[English Version](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定正整数&nbsp;<code>n</code>，返回在<em>&nbsp;</em><code>[1, n]</code><em>&nbsp;</em>范围内具有 <strong>至少 1 位</strong> 重复数字的正整数的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 20
<strong>输出：</strong>1
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 20）只有 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>10
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1000
<strong>输出：</strong>262
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 数位 DP**

题目要求统计 $[1,..n]$ 中至少有一位重复的数字的个数，我们可以换一种思路，用一个函数 $f(n)$ 统计 $[1,..n]$ 中没有重复数字的个数，那么答案就是 $n - f(n)$。

另外，我们可以用一个二进制数来记录数字中出现过的数字，比如数字中出现了 $1$, $2$, $4$，那么对应的二进制数就是 $\underline{1}0\underline{1}\underline{1}0$。

接下来，我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为整型数组 $nums$，其中 $nums[0]$ 为最低位，而 $nums[i]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, mask, lead, limit)$，其中：

-   参数 $pos$ 表示当前搜索到的数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此 $pos$ 的初始值为数字的高位下标；
-   参数 $mask$ 表示当前数字中出现过的数字；
-   参数 $lead$ 表示当前数字是否仅包含前导零；
-   参数 $limit$ 表示当前可填的数字的限制，如果无限制，那么可以选择 $i \in [0,1,..9]$，否则，只能选择 $i \in [0,..nums[pos]]$。如果 $limit$ 为 `true` 且已经取到了能取到的最大值，那么下一个 $limit$ 同样为 `true`；如果 $limit$ 为 `true` 但是还没有取到最大值，或者 $limit$ 为 `false`，那么下一个 $limit$ 为 `false`。

答案为 $dfs(0, 0, true, true)$。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(m \times 2^m \times 10)$，空间复杂度 $O(m \times 2^m)$。其中 $m$ 为数字 $n$ 的位数。

相似题目：

-   [233. 数字 1 的个数](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        return n - self.f(n)

    def f(self, n):
        def A(m, n):
            return 1 if n == 0 else A(m, n - 1) * (m - n + 1)

        vis = [False] * 10
        ans = 0
        digits = [int(c) for c in str(n)[::-1]]
        m = len(digits)
        for i in range(1, m):
            ans += 9 * A(9, i - 1)
        for i in range(m - 1, -1, -1):
            v = digits[i]
            j = 1 if i == m - 1 else 0
            while j < v:
                if not vis[j]:
                    ans += A(10 - (m - i), i)
                j += 1
            if vis[v]:
                break
            vis[v] = True
            if i == 0:
                ans += 1
        return ans
```

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        return n - self.f(n)

    def f(self, n: int) -> int:
        @cache
        def dfs(pos: int, mask: int, lead: bool, limit: bool) -> int:
            if pos < 0:
                return int(lead) ^ 1
            up = nums[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if mask >> i & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead, limit and i == up)
                else:
                    ans += dfs(pos - 1, mask | 1 << i, False, limit and i == up)
            return ans

        nums = []
        while n:
            nums.append(n % 10)
            n //= 10
        return dfs(len(nums) - 1, 0, True, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    public int f(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        int m = digits.size();
        int ans = 0;
        for (int i = 1; i < m; ++i) {
            ans += 9 * A(9, i - 1);
        }
        boolean[] vis = new boolean[10];
        for (int i = m - 1; i >= 0; --i) {
            int v = digits.get(i);
            for (int j = i == m - 1 ? 1 : 0; j < v; ++j) {
                if (vis[j]) {
                    continue;
                }
                ans += A(10 - (m - i), i);
            }
            if (vis[v]) {
                break;
            }
            vis[v] = true;
            if (i == 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
}
```

```java
class Solution {
    private int[] nums = new int[11];
    private Integer[][] dp = new Integer[11][1 << 11];

    public int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    private int f(int n) {
        int i = -1;
        for (; n > 0; n /= 10) {
            nums[++i] = n % 10;
        }
        return dfs(i, 0, true, true);
    }

    private int dfs(int pos, int mask, boolean lead, boolean limit) {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != null) {
            return dp[pos][mask];
        }
        int ans = 0;
        int up = limit ? nums[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if ((mask >> i & 1) == 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    int f(int n) {
        int ans = 0;
        vector<int> digits;
        while (n) {
            digits.push_back(n % 10);
            n /= 10;
        }
        int m = digits.size();
        vector<bool> vis(10);
        for (int i = 1; i < m; ++i) {
            ans += 9 * A(9, i - 1);
        }
        for (int i = m - 1; ~i; --i) {
            int v = digits[i];
            for (int j = i == m - 1 ? 1 : 0; j < v; ++j) {
                if (!vis[j]) {
                    ans += A(10 - (m - i), i);
                }
            }
            if (vis[v]) {
                break;
            }
            vis[v] = true;
            if (i == 0) {
                ++ans;
            }
        }
        return ans;
    }

    int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
};
```

```cpp
class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

private:
    int nums[11];
    int dp[11][1 << 11];

    int f(int n) {
        memset(dp, -1, sizeof(dp));
        int i = -1;
        for (; n; n /= 10) {
            nums[++i] = n % 10;
        }
        return dfs(i, 0, true, true);
    }

    int dfs(int pos, int mask, bool lead, bool limit) {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int up = limit ? nums[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (mask >> i & 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func numDupDigitsAtMostN(n int) int {
	return n - f(n)
}

func f(n int) int {
	digits := []int{}
	for n != 0 {
		digits = append(digits, n%10)
		n /= 10
	}
	m := len(digits)
	vis := make([]bool, 10)
	ans := 0
	for i := 1; i < m; i++ {
		ans += 9 * A(9, i-1)
	}
	for i := m - 1; i >= 0; i-- {
		v := digits[i]
		j := 0
		if i == m-1 {
			j = 1
		}
		for ; j < v; j++ {
			if !vis[j] {
				ans += A(10-(m-i), i)
			}
		}
		if vis[v] {
			break
		}
		vis[v] = true
		if i == 0 {
			ans++
		}
	}
	return ans
}

func A(m, n int) int {
	if n == 0 {
		return 1
	}
	return A(m, n-1) * (m - n + 1)
}
```

```go
func numDupDigitsAtMostN(n int) int {
	return n - f(n)
}

func f(n int) int {
	nums := []int{}
	for ; n > 0; n /= 10 {
		nums = append(nums, n%10)
	}
	dp := [11][1 << 11]int{}
	for i := range dp {
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(pos int, mask int, lead bool, limit bool) int {
		if pos < 0 {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		up := 9
		if limit {
			up = nums[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if mask>>i&1 == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead, limit && i == up)
			} else {
				ans += dfs(pos-1, mask|1<<i, false, limit && i == up)
			}
		}
		if !lead && !limit {
			dp[pos][mask] = ans
		}
		return ans
	}
	return dfs(len(nums)-1, 0, true, true)
}
```

### **TypeScript**

```ts
function numDupDigitsAtMostN(n: number): number {
    return n - f(n);
}

function f(n: number): number {
    const nums: number[] = [];
    let i = -1;
    for (; n; n = Math.floor(n / 10)) {
        nums[++i] = n % 10;
    }
    const dp = Array.from({ length: 11 }, () => Array(1 << 11).fill(-1));
    const dfs = (
        pos: number,
        mask: number,
        lead: boolean,
        limit: boolean,
    ): number => {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] !== -1) {
            return dp[pos][mask];
        }
        const up = limit ? nums[pos] : 9;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if ((mask >> i) & 1) {
                continue;
            }
            if (lead && i === 0) {
                ans += dfs(pos - 1, mask, lead, limit && i === up);
            } else {
                ans += dfs(pos - 1, mask | (1 << i), false, limit && i === up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    };
    return dfs(i, 0, true, true);
}
```

### **...**

```

```

<!-- tabs:end -->
