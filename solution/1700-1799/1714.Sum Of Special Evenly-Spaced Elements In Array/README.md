# [1714. 数组中特殊等间距元素的和](https://leetcode.cn/problems/sum-of-special-evenly-spaced-elements-in-array)

[English Version](/solution/1700-1799/1714.Sum%20Of%20Special%20Evenly-Spaced%20Elements%20In%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong>索引从 0 开始</strong>的整数类型数组 <code>nums</code> ，包含 <code>n</code> 个非负整数。</p>

<p>另外给定一个（包含查询指令的）数组 <code>queries</code> ，其中 <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>。 第 <code>i</code> 个查询指令的答案是 <code>nums[j]</code> 中满足该条件的所有元素的和： <code>x<sub>i</sub> &lt;= j &lt; n</code> 且 <code>(j - x<sub>i</sub>)</code> 能被 <code>y<sub>i</sub></code> 整除。</p>

<p>返回一个数组<em> </em><code>answer</code>，其中<em>  </em><code>answer.length == queries.length</code> 且 <code>answer[i]</code> 是第 <code>i</code> 个查询指令的答案对 <code>10<sup>9 </sup>+ 7</code> 取模。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [0,1,2,3,4,5,6,7], queries = [[0,3],[5,1],[4,2]]
<strong>输出:</strong> [9,18,10]
<strong>解释:</strong> 每次查询的答案如下：
1) 符合查询条件的索引 j 有 0、 3 和 6。 nums[0] + nums[3] + nums[6] = 9
2) 符合查询条件的索引 j 有 5、 6 和 7。 nums[5] + nums[6] + nums[7] = 18
3) 符合查询条件的索引 j 有 4 和 6。 nums[4] + nums[6] = 10
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [100,200,101,201,102,202,103,203], queries = [[0,7]]
<strong>输出:</strong> [303]
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 1.5 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= x<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= y<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分块**

这道题是一道比较典型的分块题目，对于步长较大的查询，我们可以直接暴力求解；对于步长较小的查询，我们可以预处理出每个位置的后缀和，然后直接查询。

本题中，我们将步长较大的查询的步长限制为 $\sqrt{n}$，这样就可以保证每个查询的时间复杂度为 $O(\sqrt{n})$。

我们定义一个二维数组 $suf$，其中 $suf[i][j]$ be 表示从位置 $j$ 开始，步长为 $i$ 的后缀和。那么对于每个查询 $[x, y]$，我们可以分为两种情况：

-   如果 $y \le \sqrt{n}$，那么我们可以直接查询 $suf[y][x]$；
-   如果 $y \gt \sqrt{n}$，那么我们可以直接暴力求解。

时间复杂度 $O((n +  m) \times \sqrt{n})$，空间复杂度 $O(n \times \sqrt{n})$。其中 $n$ 是数组的长度，而 $m$ 是查询的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def solve(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        mod = 10**9 + 7
        n = len(nums)
        m = int(sqrt(n))
        suf = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(n - 1, -1, -1):
                suf[i][j] = suf[i][min(n, j + i)] + nums[j]
        ans = []
        for x, y in queries:
            if y <= m:
                ans.append(suf[y][x] % mod)
            else:
                ans.append(sum(nums[x::y]) % mod)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] solve(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = (int) Math.sqrt(n);
        final int mod = (int) 1e9 + 7;
        int[][] suf = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                suf[i][j] = (suf[i][Math.min(n, j + i)] + nums[j]) % mod;
            }
        }
        int k = queries.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (y <= m) {
                ans[i] = suf[y][x];
            } else {
                int s = 0;
                for (int j = x; j < n; j += y) {
                    s = (s + nums[j]) % mod;
                }
                ans[i] = s;
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
    vector<int> solve(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int m = (int) sqrt(n);
        const int mod = 1e9 + 7;
        int suf[m + 1][n + 1];
        memset(suf, 0, sizeof(suf));
        for (int i = 1; i <= m; ++i) {
            for (int j = n - 1; ~j; --j) {
                suf[i][j] = (suf[i][min(n, j + i)] + nums[j]) % mod;
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1];
            if (y <= m) {
                ans.push_back(suf[y][x]);
            } else {
                int s = 0;
                for (int i = x; i < n; i += y) {
                    s = (s + nums[i]) % mod;
                }
                ans.push_back(s);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func solve(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	m := int(math.Sqrt(float64(n)))
	const mod int = 1e9 + 7
	suf := make([][]int, m+1)
	for i := range suf {
		suf[i] = make([]int, n+1)
		for j := n - 1; j >= 0; j-- {
			suf[i][j] = (suf[i][min(n, j+i)] + nums[j]) % mod
		}
	}
	for _, q := range queries {
		x, y := q[0], q[1]
		if y <= m {
			ans = append(ans, suf[y][x])
		} else {
			s := 0
			for i := x; i < n; i += y {
				s = (s + nums[i]) % mod
			}
			ans = append(ans, s)
		}
	}
	return
}
```

### **TypeScript**

```ts
function solve(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const m = Math.floor(Math.sqrt(n));
    const mod = 10 ** 9 + 7;
    const suf: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = n - 1; j >= 0; --j) {
            suf[i][j] = (suf[i][Math.min(n, j + i)] + nums[j]) % mod;
        }
    }
    const ans: number[] = [];
    for (const [x, y] of queries) {
        if (y <= m) {
            ans.push(suf[y][x]);
        } else {
            let s = 0;
            for (let i = x; i < n; i += y) {
                s = (s + nums[i]) % mod;
            }
            ans.push(s);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
