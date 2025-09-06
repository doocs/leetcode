---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README_EN.md
rating: 1917
source: Weekly Contest 465 Q2
---

<!-- problem:start -->

# [3669. Balanced K-Factor Decomposition](https://leetcode.com/problems/balanced-k-factor-decomposition)

[中文文档](/solution/3600-3699/3669.Balanced%20K-Factor%20Decomposition/README.md)

## Description

<!-- description:start -->

<p>Given two integers <code>n</code> and <code>k</code>, split the number <code>n</code> into exactly <code>k</code> positive integers such that the <strong>product</strong> of these integers is equal to <code>n</code>.</p>

<p>Return <em>any</em> <em>one</em> split in which the <strong>maximum</strong> difference between any two numbers is <strong>minimized</strong>. You may return the result in <em>any order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 100, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[10,10]</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="157" data-start="0">The split <code>[10, 10]</code> yields <code>10 * 10 = 100</code> and a max-min difference of 0, which is minimal.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 44, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,11]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="46" data-start="2">Split <code>[1, 1, 44]</code> yields a difference of 43</li>
	<li data-end="93" data-start="49">Split <code>[1, 2, 22]</code> yields a difference of 21</li>
	<li data-end="140" data-start="96">Split <code>[1, 4, 11]</code> yields a difference of 10</li>
	<li data-end="186" data-start="143">Split <code>[2, 2, 11]</code> yields a difference of 9</li>
</ul>

<p data-end="264" data-is-last-node="" data-is-only-node="" data-start="188">Therefore, <code>[2, 2, 11]</code> is the optimal split with the smallest difference 9.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="54" data-start="37"><code data-end="52" data-start="37">4 &lt;= n &lt;= 10<sup><span style="font-size: 10.8333px;">5</span></sup></code></li>
	<li data-end="71" data-start="57"><code data-end="69" data-start="57">2 &lt;= k &lt;= 5</code></li>
	<li data-end="145" data-is-last-node="" data-start="74"><code data-end="77" data-start="74">k</code> is strictly less than the total number of positive divisors of <code data-end="144" data-is-only-node="" data-start="141">n</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
