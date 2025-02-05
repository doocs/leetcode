---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3437.Permutations%20III/README.md
tags:
    - 数组
    - 回溯
---

<!-- problem:start -->

# [3437. Permutations III 🔒](https://leetcode.cn/problems/permutations-iii)

[English Version](/solution/3400-3499/3437.Permutations%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Given an integer <code>n</code>, an <strong>alternating permutation</strong> is a permutation of the first <code>n</code> positive integers such that no <strong>two</strong> adjacent elements are <strong>both</strong> odd or <strong>both</strong> even.</p>

<p>Return <em>all such </em><strong>alternating permutations</strong> sorted in lexicographical order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2,3,4],[1,4,3,2],[2,1,4,3],[2,3,4,1],[3,2,1,4],[3,4,1,2],[4,1,2,3],[4,3,2,1]]</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2],[2,1]]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2,3],[3,2,1]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：回溯

我们设计一个函数 $\textit{dfs}(i)$，表示当前要填第 $i$ 个位置的数，位置编号从 $0$ 开始。

在 $\textit{dfs}(i)$ 中，如果 $i \geq n$，说明所有位置都已经填完，将当前排列加入答案数组中。

否则，我们枚举当前位置可以填的数 $j$，如果 $j$ 没有被使用过，并且 $j$ 和当前排列的最后一个数不同奇偶性，我们就可以将 $j$ 放在当前位置，继续递归填下一个位置。

时间复杂度 $O(n \times n!)$，空间复杂度 $O(n)$。其中 $n$ 为排列的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permute(self, n: int) -> List[List[int]]:
        def dfs(i: int) -> None:
            if i >= n:
                ans.append(t[:])
                return
            for j in range(1, n + 1):
                if not vis[j] and (i == 0 or t[-1] % 2 != j % 2):
                    t.append(j)
                    vis[j] = True
                    dfs(i + 1)
                    vis[j] = False
                    t.pop()

        ans = []
        t = []
        vis = [False] * (n + 1)
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<int[]> ans = new ArrayList<>();
    private boolean[] vis;
    private int[] t;
    private int n;

    public int[][] permute(int n) {
        this.n = n;
        t = new int[n];
        vis = new boolean[n + 1];
        dfs(0);
        return ans.toArray(new int[0][]);
    }

    private void dfs(int i) {
        if (i >= n) {
            ans.add(t.clone());
            return;
        }
        for (int j = 1; j <= n; ++j) {
            if (!vis[j] && (i == 0 || t[i - 1] % 2 != j % 2)) {
                vis[j] = true;
                t[i] = j;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> permute(int n) {
        vector<vector<int>> ans;
        vector<bool> vis(n);
        vector<int> t;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j] && (i == 0 || t[i - 1] % 2 != j % 2)) {
                    vis[j] = true;
                    t.push_back(j);
                    dfs(i + 1);
                    t.pop_back();
                    vis[j] = false;
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func permute(n int) (ans [][]int) {
	vis := make([]bool, n+1)
	t := make([]int, n)
	var dfs func(i int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j := 1; j <= n; j++ {
			if !vis[j] && (i == 0 || t[i-1]%2 != j%2) {
				vis[j] = true
				t[i] = j
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function permute(n: number): number[][] {
    const ans: number[][] = [];
    const vis: boolean[] = Array(n).fill(false);
    const t: number[] = Array(n).fill(0);
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        for (let j = 1; j <= n; ++j) {
            if (!vis[j] && (i === 0 || t[i - 1] % 2 !== j % 2)) {
                vis[j] = true;
                t[i] = j;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
