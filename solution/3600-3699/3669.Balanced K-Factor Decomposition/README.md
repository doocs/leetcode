---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README.md
rating: 1917
source: 第 465 场周赛 Q2
tags:
    - 数学
    - 回溯
    - 数论
---

<!-- problem:start -->

# [3669. K 因数分解](https://leetcode.cn/problems/balanced-k-factor-decomposition)

[English Version](/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>，将数字 <code>n</code> 恰好分割成 <code>k</code> 个正整数，使得这些整数的&nbsp;<strong>乘积&nbsp;</strong>等于 <code>n</code>。</p>

<p>返回一个分割方案，使得这些数字中&nbsp;<strong>最大值&nbsp;</strong>和&nbsp;<strong>最小值&nbsp;</strong>之间的&nbsp;<strong>差值&nbsp;</strong>最小化。结果可以以&nbsp;<strong>任意顺序</strong>&nbsp;返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 100, k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">[10,10]</span></p>

<p><strong>解释：</strong></p>

<p>分割方案 <code>[10, 10]</code> 的结果是 <code>10 * 10 = 100</code>，且最大值与最小值的差值为 0，这是最小可能值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 44, k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">[2,2,11]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>分割方案 <code>[1, 1, 44]</code> 的差值为 43</li>
	<li>分割方案 <code>[1, 2, 22]</code> 的差值为 21</li>
	<li>分割方案 <code>[1, 4, 11]</code> 的差值为 10</li>
	<li>分割方案 <code>[2, 2, 11]</code> 的差值为 9</li>
</ul>

<p>因此，<code>[2, 2, 11]</code> 是最优分割方案，其差值最小，为 9。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= k &lt;= 5</code></li>
	<li><code>k</code> 严格小于 <code>n</code> 的正因数的总数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
mx = 10**5 + 1
g = [[] for _ in range(mx)]
for i in range(1, mx):
    for j in range(i, mx, i):
        g[j].append(i)


class Solution:
    def minDifference(self, n: int, k: int) -> List[int]:
        def dfs(i: int, x: int, mi: int, mx: int):
            if i == 0:
                nonlocal cur, ans
                d = max(mx, x) - min(mi, x)
                if d < cur:
                    cur = d
                    path[i] = x
                    ans = path[:]
                return
            for y in g[x]:
                path[i] = y
                dfs(i - 1, x // y, min(mi, y), max(mx, y))

        ans = None
        path = [0] * k
        cur = inf
        dfs(k - 1, n, inf, 0)
        return ans
```

#### Java

```java
class Solution {
    static final int MX = 100_001;
    static List<Integer>[] g = new ArrayList[MX];

    static {
        for (int i = 0; i < MX; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) {
                g[j].add(i);
            }
        }
    }

    private int cur;
    private int[] ans;
    private int[] path;

    public int[] minDifference(int n, int k) {
        cur = Integer.MAX_VALUE;
        ans = null;
        path = new int[k];
        dfs(k - 1, n, Integer.MAX_VALUE, 0);
        return ans;
    }

    private void dfs(int i, int x, int mi, int mx) {
        if (i == 0) {
            int d = Math.max(mx, x) - Math.min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = path.clone();
            }
            return;
        }
        for (int y : g[x]) {
            path[i] = y;
            dfs(i - 1, x / y, Math.min(mi, y), Math.max(mx, y));
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    static const int MX = 100001;
    static vector<vector<int>> g;

    vector<int> ans;
    vector<int> path;
    int cur;

    vector<int> minDifference(int n, int k) {
        if (g.empty()) {
            g.resize(MX);
            for (int i = 1; i < MX; i++) {
                for (int j = i; j < MX; j += i) {
                    g[j].push_back(i);
                }
            }
        }

        cur = INT_MAX;
        ans.clear();
        path.assign(k, 0);

        dfs(k - 1, n, INT_MAX, 0);
        return ans;
    }

private:
    void dfs(int i, int x, int mi, int mx) {
        if (i == 0) {
            int d = max(mx, x) - min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = path;
            }
            return;
        }
        for (int y : g[x]) {
            path[i] = y;
            dfs(i - 1, x / y, min(mi, y), max(mx, y));
        }
    }
};

vector<vector<int>> Solution::g;
```

#### Go

```go
const MX = 100001

var g [][]int

func init() {
	g = make([][]int, MX)
	for i := 1; i < MX; i++ {
		for j := i; j < MX; j += i {
			g[j] = append(g[j], i)
		}
	}
}

var (
	cur  int
	ans  []int
	path []int
)

func minDifference(n int, k int) []int {
	cur = math.MaxInt32
	ans = nil
	path = make([]int, k)
	dfs(k-1, n, math.MaxInt32, 0)
	return ans
}

func dfs(i, x, mi, mx int) {
	if i == 0 {
		d := max(mx, x) - min(mi, x)
		if d < cur {
			cur = d
			path[i] = x
			ans = slices.Clone(path)
		}
		return
	}
	for _, y := range g[x] {
		path[i] = y
		dfs(i-1, x/y, min(mi, y), max(mx, y))
	}
}
```

#### TypeScript

```ts
const MX = 100001;
const g: number[][] = Array.from({ length: MX }, () => []);
for (let i = 1; i < MX; i++) {
    for (let j = i; j < MX; j += i) {
        g[j].push(i);
    }
}

function minDifference(n: number, k: number): number[] {
    let cur = Number.MAX_SAFE_INTEGER;
    let ans: number[] | null = null;
    const path: number[] = Array(k).fill(0);

    function dfs(i: number, x: number, mi: number, mx: number): void {
        if (i === 0) {
            const d = Math.max(mx, x) - Math.min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = [...path];
            }
            return;
        }
        for (const y of g[x]) {
            path[i] = y;
            dfs(i - 1, Math.floor(x / y), Math.min(mi, y), Math.max(mx, y));
        }
    }

    dfs(k - 1, n, Number.MAX_SAFE_INTEGER, 0);
    return ans ?? [];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
