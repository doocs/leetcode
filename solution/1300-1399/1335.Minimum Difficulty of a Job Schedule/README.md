# [1335. 工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule)

[English Version](/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要制定一份&nbsp;<code>d</code>&nbsp;天的工作计划表。工作之间存在依赖，要想执行第&nbsp;<code>i</code>&nbsp;项工作，你必须完成全部&nbsp;<code>j</code>&nbsp;项工作（&nbsp;<code>0 &lt;= j &lt; i</code>）。</p>

<p>你每天 <strong>至少</strong>&nbsp;需要完成一项任务。工作计划的总难度是这&nbsp;<code>d</code>&nbsp;天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。</p>

<p>给你一个整数数组&nbsp;<code>jobDifficulty</code>&nbsp;和一个整数&nbsp;<code>d</code>，分别代表工作难度和需要计划的天数。第&nbsp;<code>i</code>&nbsp;项工作的难度是&nbsp;<code>jobDifficulty[i]</code>。</p>

<p>返回整个工作计划的 <strong>最小难度</strong> 。如果无法制定工作计划，则返回&nbsp;<strong>-1&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/images/untitled.png" style="height: 304px; width: 365px;"></p>

<pre><strong>输入：</strong>jobDifficulty = [6,5,4,3,2,1], d = 2
<strong>输出：</strong>7
<strong>解释：</strong>第一天，您可以完成前 5 项工作，总难度 = 6.
第二天，您可以完成最后一项工作，总难度 = 1.
计划表的难度 = 6 + 1 = 7 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>jobDifficulty = [9,9,9], d = 4
<strong>输出：</strong>-1
<strong>解释：</strong>就算你每天完成一项工作，仍然有一天是空闲的，你无法制定一份能够满足既定工作时间的计划表。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>jobDifficulty = [1,1,1], d = 3
<strong>输出：</strong>3
<strong>解释：</strong>工作计划为每天一项工作，总难度为 3 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>jobDifficulty = [7,1,7,1,7,1], d = 3
<strong>输出：</strong>15
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
<strong>输出：</strong>843
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= jobDifficulty.length &lt;= 300</code></li>
	<li><code>0 &lt;=&nbsp;jobDifficulty[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= d &lt;= 10</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示完成前 $i$ 项工作，且一共用了 $j$ 天的最小难度。初始时 $f[0][0] = 0$，其余 $f[i][j]$ 均为 $\infty$。

考虑第 $j$ 天的工作安排，我们可以枚举第 $j$ 天完成的工作 $[k,..i]$，那么有状态转移方程：

$$
f[i][j] = \min_{k \in [1,i]} \{f[k-1][j-1] + \max_{k \leq t \leq i} \{jobDifficulty[t]\}\}
$$

最终答案即为 $f[n][d]$。

时间复杂度 $O(n^2 \times d)$，空间复杂度 $O(n \times d)$。其中 $n$ 和 $d$ 分别为工作数量和需要计划的天数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        f = [[inf] * (d + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(jobDifficulty, 1):
            for j in range(1, d + 1):
                mx = 0
                for k in range(i, 0, -1):
                    mx = max(mx, jobDifficulty[k - 1])
                    f[i][j] = min(f[i][j], f[k - 1][j - 1] + mx)
        return f[n][d] if f[n][d] != inf else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        final int inf = 1 << 30;
        int[][] f = new int[n + 1][d + 1];
        for (int[] g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= d; ++j) {
                int mx = 0;
                for (int k = i; k > 0; --k) {
                    mx = Math.max(mx, jobDifficulty[k - 1]);
                    f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] < inf ? f[n][d] : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDifficulty(vector<int>& jobDifficulty, int d) {
        int n = jobDifficulty.size();
        int f[n + 1][d + 1];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= d; ++j) {
                int mx = 0;
                for (int k = i; k; --k) {
                    mx = max(mx, jobDifficulty[k - 1]);
                    f[i][j] = min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] == 0x3f3f3f3f ? -1 : f[n][d];
    }
};
```

### **Go**

```go
func minDifficulty(jobDifficulty []int, d int) int {
	n := len(jobDifficulty)
	f := make([][]int, n+1)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([]int, d+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= d; j++ {
			mx := 0
			for k := i; k > 0; k-- {
				mx = max(mx, jobDifficulty[k-1])
				f[i][j] = min(f[i][j], f[k-1][j-1]+mx)
			}
		}
	}
	if f[n][d] == inf {
		return -1
	}
	return f[n][d]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
