# [2585. 获得分数的方法数](https://leetcode.cn/problems/number-of-ways-to-earn-points)

[English Version](/solution/2500-2599/2585.Number%20of%20Ways%20to%20Earn%20Points/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>考试中有 <code>n</code> 种类型的题目。给你一个整数 <code>target</code> 和一个下标从 <strong>0</strong> 开始的二维整数数组 <code>types</code> ，其中 <code>types[i] = [count<sub>i</sub>, marks<sub>i</sub>] </code>表示第 <code>i</code> 种类型的题目有 <code>count<sub>i</sub></code> 道，每道题目对应 <code>marks<sub>i</sub></code> 分。</p>

<p>返回你在考试中恰好得到 <code>target</code> 分的方法数。由于答案可能很大，结果需要对 <code>10<sup>9</sup> +7</code> 取余。</p>

<p><strong>注意</strong>，同类型题目无法区分。</p>

<ul>
	<li>比如说，如果有 <code>3</code> 道同类型题目，那么解答第 <code>1</code> 和第 <code>2</code> 道题目与解答第 <code>1</code> 和第 <code>3</code> 道题目或者第 <code>2</code> 和第 <code>3</code> 道题目是相同的。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = 6, types = [[6,1],[3,2],[2,3]]
<strong>输出：</strong>7
<strong>解释：</strong>要获得 6 分，你可以选择以下七种方法之一：
- 解决 6 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 + 1 = 6
- 解决 4 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 1 + 2 = 6
- 解决 2 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 1 + 2 + 2 = 6
- 解决 3 道第 0 种类型的题目和 1 道第 2 种类型的题目：1 + 1 + 1 + 3 = 6
- 解决 1 道第 0 种类型的题目、1 道第 1 种类型的题目和 1 道第 2 种类型的题目：1 + 2 + 3 = 6
- 解决 3 道第 1 种类型的题目：2 + 2 + 2 = 6
- 解决 2 道第 2 种类型的题目：3 + 3 = 6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = 5, types = [[50,1],[50,2],[50,5]]
<strong>输出：</strong>4
<strong>解释：</strong>要获得 5 分，你可以选择以下四种方法之一：
- 解决 5 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 = 5
- 解决 3 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 2 = 5
- 解决 1 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 2 + 2 = 5
- 解决 1 道第 2 种类型的题目：5
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>target = 18, types = [[6,1],[3,2],[2,3]]
<strong>输出：</strong>1
<strong>解释：</strong>只有回答所有题目才能获得 18 分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
	<li><code>n == types.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>types[i].length == 2</code></li>
	<li><code>1 &lt;= count<sub>i</sub>, marks<sub>i</sub> &lt;= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示前 $i$ 种类型的题目中，恰好得到 $j$ 分的方法数。初始时 $f[0][0] = 1$，其余 $f[i][j] = 0$。答案即为 $f[n][target]$。

我们可以枚举第 $i$ 种类型的题目，假设该类型题目的数量为 $count$，分数为 $marks$，那么我们可以得到如下状态转移方程：

$$
f[i][j] = \sum_{k=0}^{count} f[i-1][j-k \times marks]
$$

其中 $k$ 表示第 $i$ 种类型的题目的数量。

最终的答案即为 $f[n][target]$。注意答案可能很大，需要对 $10^9 + 7$ 取模。

时间复杂度 $O(n \times target \times count)$，空间复杂度 $O(n \times target)$。其中 $n$ 为题目类型的数量；而 $target$ 和 $count$ 分别为题目的目标分数和每种类型题目的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToReachTarget(self, target: int, types: List[List[int]]) -> int:
        n = len(types)
        mod = 10**9 + 7
        f = [[0] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            count, marks = types[i - 1]
            for j in range(target + 1):
                for k in range(count + 1):
                    if j >= k * marks:
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod
        return f[n][target]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int count = types[i - 1][0], marks = types[i - 1][1];
            for (int j = 0; j <= target; ++j) {
                for (int k = 0; k <= count; ++k) {
                    if (j >= k * marks) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                    }
                }
            }
        }
        return f[n][target];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToReachTarget(int target, vector<vector<int>>& types) {
        int n = types.size();
        const int mod = 1e9 + 7;
        int f[n + 1][target + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int count = types[i - 1][0], marks = types[i - 1][1];
            for (int j = 0; j <= target; ++j) {
                for (int k = 0; k <= count; ++k) {
                    if (j >= k * marks) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                    }
                }
            }
        }
        return f[n][target];
    }
};
```

### **Go**

```go
func waysToReachTarget(target int, types [][]int) int {
	n := len(types)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
	}
	f[0][0] = 1
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		count, marks := types[i-1][0], types[i-1][1]
		for j := 0; j <= target; j++ {
			for k := 0; k <= count; k++ {
				if j >= k*marks {
					f[i][j] = (f[i][j] + f[i-1][j-k*marks]) % mod
				}
			}
		}
	}
	return f[n][target]
}
```

### **TypeScript**

```ts
function waysToReachTarget(target: number, types: number[][]): number {
    const n = types.length;
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () =>
        Array(target + 1).fill(0),
    );
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const [count, marks] = types[i - 1];
        for (let j = 0; j <= target; ++j) {
            for (let k = 0; k <= count; ++k) {
                if (j >= k * marks) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                }
            }
        }
    }
    return f[n][target];
}
```

### **...**

```

```

<!-- tabs:end -->
