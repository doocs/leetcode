---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3437.Permutations%20III/README.md
tags:
    - 数组
    - 回溯
---

<!-- problem:start -->

# [3437. 全排列 III 🔒](https://leetcode.cn/problems/permutations-iii)

[English Version](/solution/3400-3499/3437.Permutations%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数&nbsp;<code>n</code>，一个 <strong>交替排列</strong> 是没有 <strong>两个</strong> 相邻元素 <strong>同时</strong> 为奇数或偶数的前 <code>n</code> 个正整数的排列。</p>

<p>返回所有这样的 <strong>交替排列</strong>&nbsp;以字典序排序。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 4</span></p>

<p><span class="example-io"><b>输出：</b>[[1,2,3,4],[1,4,3,2],[2,1,4,3],[2,3,4,1],[3,2,1,4],[3,4,1,2],[4,1,2,3],[4,3,2,1]]</span></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 2</span></p>

<p><span class="example-io"><b>输出：</b>[[1,2],[2,1]]</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 3</span></p>

<p><span class="example-io"><b>输出：</b>[[1,2,3],[3,2,1]]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：回溯

<!-- thinking:start -->

> **思考**
>
> 要求 $1..n$ 的排列中相邻两数奇偶不同。$n\le 10$，全排列量级可接受，但需剪掉奇偶相同的前缀。
>
> 回溯按位置填数，并用访问数组保证每个数只用一次。
>
> 当前位置若与已填末位同奇偶则跳过。$i=0$ 时无前驱，任意未用数都可。搜到 $i=n$ 时收录一份拷贝。

<!-- thinking:end -->

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
