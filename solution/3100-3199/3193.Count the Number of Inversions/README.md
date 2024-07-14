---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3193.Count%20the%20Number%20of%20Inversions/README.md
rating: 2266
source: 第 133 场双周赛 Q4
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3193. 统计逆序对的数目](https://leetcode.cn/problems/count-the-number-of-inversions)

[English Version](/solution/3100-3199/3193.Count%20the%20Number%20of%20Inversions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个二维数组&nbsp;<code>requirements</code>&nbsp;，其中&nbsp;<code>requirements[i] = [end<sub>i</sub>, cnt<sub>i</sub>]</code> <span class="text-only" data-eleid="10" style="white-space: pre;">表示这个要求中的末尾下标和 <strong>逆序对</strong> 的数目。</span></p>

<p>整数数组 <code>nums</code>&nbsp;中一个下标对&nbsp;<code>(i, j)</code>&nbsp;如果满足以下条件，那么它们被称为一个 <strong>逆序对</strong>&nbsp;：</p>

<ul>
	<li><code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &gt; nums[j]</code></li>
</ul>

<p>请你返回&nbsp;<code>[0, 1, 2, ..., n - 1]</code>&nbsp;的&nbsp;<span data-keyword="permutation">排列</span> <code>perm</code>&nbsp;的数目，满足对 <strong>所有</strong>&nbsp;的&nbsp;<code>requirements[i]</code>&nbsp;都有&nbsp;<code>perm[0..end<sub>i</sub>]</code>&nbsp;恰好有&nbsp;<code>cnt<sub>i</sub></code>&nbsp;个逆序对。</p>

<p>由于答案可能会很大，将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, requirements = [[2,2],[0,0]]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>两个排列为：</p>

<ul>
	<li><code>[2, 0, 1]</code>

    <ul>
    	<li>前缀&nbsp;<code>[2, 0, 1]</code>&nbsp;的逆序对为&nbsp;<code>(0, 1)</code> 和&nbsp;<code>(0, 2)</code>&nbsp;。</li>
    	<li>前缀&nbsp;<code>[2]</code>&nbsp;的逆序对数目为 0 个。</li>
    </ul>
    </li>
    <li><code>[1, 2, 0]</code>
    <ul>
    	<li>前缀&nbsp;<code>[1, 2, 0]</code>&nbsp;的逆序对为&nbsp;<code>(0, 2)</code> 和&nbsp;<code>(1, 2)</code>&nbsp;。</li>
    	<li>前缀&nbsp;<code>[1]</code>&nbsp;的逆序对数目为 0 个。</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, requirements = [[2,2],[1,1],[0,0]]</span></p>

<p><b>输出：</b>1</p>

<p><strong>解释：</strong></p>

<p>唯一满足要求的排列是&nbsp;<code>[2, 0, 1]</code>&nbsp;：</p>

<ul>
	<li>前缀&nbsp;<code>[2, 0, 1]</code>&nbsp;的逆序对为&nbsp;<code>(0, 1)</code> 和&nbsp;<code>(0, 2)</code>&nbsp;。</li>
	<li>前缀&nbsp;<code>[2, 0]</code>&nbsp;的逆序对为&nbsp;<code>(0, 1)</code>&nbsp;。</li>
	<li>前缀&nbsp;<code>[2]</code>&nbsp;的逆序对数目为 0 。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 2, requirements = [[0,0],[1,0]]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>唯一满足要求的排列为&nbsp;<code>[0, 1]</code>&nbsp;：</p>

<ul>
	<li>前缀&nbsp;<code>[0]</code>&nbsp;的逆序对数目为 0 。</li>
	<li>前缀&nbsp;<code>[0, 1]</code>&nbsp;的逆序对为&nbsp;<code>(0, 1)</code>&nbsp;。</li>
</ul>
</div>

<div id="gtx-trans" style="position: absolute; left: 198px; top: 756px;">
<div class="gtx-trans-icon">&nbsp;</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 300</code></li>
	<li><code>1 &lt;= requirements.length &lt;= n</code></li>
	<li><code>requirements[i] = [end<sub>i</sub>, cnt<sub>i</sub>]</code></li>
	<li><code>0 &lt;= end<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= cnt<sub>i</sub> &lt;= 400</code></li>
	<li>输入保证至少有一个&nbsp;<code>i</code>&nbsp;满足&nbsp;<code>end<sub>i</sub> == n - 1</code>&nbsp;。</li>
	<li>输入保证所有的&nbsp;<code>end<sub>i</sub></code>&nbsp;互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示 $[0..i]$ 的排列中，逆序对数量为 $j$ 的排列数。考虑下标为 $i$ 的数 $a_i$ 与前面 $i$ 个数的大小关系。如果 $a_i$ 比前面 $k$ 个数小，那么前面的 $k$ 个数与 $a_i$ 都构成了逆序对，逆序对数量为 $k$。因此，我们可以得到状态转移方程：

$$
f[i][j] = \sum_{k=0}^{\min(i, j)} f[i-1][j-k]
$$

由于题目要求 $[0..\text{end}_i]$ 的逆序对数量为 $\text{cnt}_i$，因此，当我们计算 $i = \text{end}_i$ 时，我们只需要计算 $f[i][\text{cnt}_i]$ 即可。其余的 $f[i][..]$ 都为 $0$。

时间复杂度 $O(n \times m \times \min(n, m))$，空间复杂度 $O(n \times m)$。其中 $m$ 是逆序对数量的最大值。本题中 $m \le 400$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPermutations(self, n: int, requirements: List[List[int]]) -> int:
        req = [-1] * n
        for end, cnt in requirements:
            req[end] = cnt
        if req[0] > 0:
            return 0
        req[0] = 0
        mod = 10**9 + 7
        m = max(req)
        f = [[0] * (m + 1) for _ in range(n)]
        f[0][0] = 1
        for i in range(1, n):
            l, r = 0, m
            if req[i] >= 0:
                l = r = req[i]
            for j in range(l, r + 1):
                for k in range(min(i, j) + 1):
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod
        return f[n - 1][req[n - 1]]
```

#### Java

```java
class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        int[] req = new int[n];
        Arrays.fill(req, -1);
        int m = 0;
        for (var r : requirements) {
            req[r[0]] = r[1];
            m = Math.max(m, r[1]);
        }
        if (req[0] > 0) {
            return 0;
        }
        req[0] = 0;
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n][m + 1];
        f[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            int l = 0, r = m;
            if (req[i] >= 0) {
                l = r = req[i];
            }
            for (int j = l; j <= r; ++j) {
                for (int k = 0; k <= Math.min(i, j); ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
                }
            }
        }
        return f[n - 1][req[n - 1]];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPermutations(int n, vector<vector<int>>& requirements) {
        vector<int> req(n, -1);
        int m = 0;
        for (const auto& r : requirements) {
            req[r[0]] = r[1];
            m = max(m, r[1]);
        }
        if (req[0] > 0) {
            return 0;
        }
        req[0] = 0;
        const int mod = 1e9 + 7;
        vector<vector<int>> f(n, vector<int>(m + 1, 0));
        f[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            int l = 0, r = m;
            if (req[i] >= 0) {
                l = r = req[i];
            }
            for (int j = l; j <= r; ++j) {
                for (int k = 0; k <= min(i, j); ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
                }
            }
        }
        return f[n - 1][req[n - 1]];
    }
};
```

#### Go

```go
func numberOfPermutations(n int, requirements [][]int) int {
	req := make([]int, n)
	for i := range req {
		req[i] = -1
	}
	for _, r := range requirements {
		req[r[0]] = r[1]
	}
	if req[0] > 0 {
		return 0
	}
	req[0] = 0
	m := slices.Max(req)
	const mod = int(1e9 + 7)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	f[0][0] = 1
	for i := 1; i < n; i++ {
		l, r := 0, m
		if req[i] >= 0 {
			l, r = req[i], req[i]
		}
		for j := l; j <= r; j++ {
			for k := 0; k <= min(i, j); k++ {
				f[i][j] = (f[i][j] + f[i-1][j-k]) % mod
			}
		}
	}
	return f[n-1][req[n-1]]
}
```

#### TypeScript

```ts
function numberOfPermutations(n: number, requirements: number[][]): number {
    const req: number[] = Array(n).fill(-1);
    for (const [end, cnt] of requirements) {
        req[end] = cnt;
    }
    if (req[0] > 0) {
        return 0;
    }
    req[0] = 0;
    const m = Math.max(...req);
    const mod = 1e9 + 7;
    const f = Array.from({ length: n }, () => Array(m + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i < n; ++i) {
        let [l, r] = [0, m];
        if (req[i] >= 0) {
            l = r = req[i];
        }
        for (let j = l; j <= r; ++j) {
            for (let k = 0; k <= Math.min(i, j); ++k) {
                f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
            }
        }
    }
    return f[n - 1][req[n - 1]];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
