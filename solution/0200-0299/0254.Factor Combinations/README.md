---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0254.Factor%20Combinations/README.md
tags:
    - 回溯
---

# [254. 因子的组合 🔒](https://leetcode.cn/problems/factor-combinations)

[English Version](/solution/0200-0299/0254.Factor%20Combinations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>整数可以被看作是其因子的乘积。</p>

<p>例如：</p>

<pre>8 = 2 x 2 x 2;
  = 2 x 4.</pre>

<p>请实现一个函数，该函数接收一个整数 <em>n</em>&nbsp;并返回该整数所有的因子组合。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>你可以假定 <em>n</em> 为永远为正数。</li>
	<li>因子必须大于 1 并且小于 <em>n</em>。</li>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong><code>1</code>
<strong>输出: </strong>[]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong><code>37</code>
<strong>输出: </strong>[]</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入: </strong><code>12</code>
<strong>输出:</strong>
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]</pre>

<p><strong>示例 4: </strong></p>

<pre><strong>输入: </strong><code>32</code>
<strong>输出:</strong>
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
</pre>

## 解法

### 方法一：回溯

我们设计函数 $dfs(n, i)$，其中 $n$ 表示当前待分解的数，$i$ 表示当前分解的数的最大因子，函数的作用是将 $n$ 分解为若干个因子，其中每个因子都不小于 $i$，并将所有分解结果保存到 $ans$ 中。

在函数 $dfs(n, i)$ 中，我们从 $i$ 开始枚举 $n$ 的因子 $j$，如果 $j$ 是 $n$ 的因子，那么我们将 $j$ 加入当前分解结果，然后继续分解 $n / j$，即调用函数 $dfs(n / j, j)$。

时间复杂度 $O(\sqrt{n})$。

<!-- tabs:start -->

```python
class Solution:
    def getFactors(self, n: int) -> List[List[int]]:
        def dfs(n, i):
            if t:
                ans.append(t + [n])
            j = i
            while j * j <= n:
                if n % j == 0:
                    t.append(j)
                    dfs(n // j, j)
                    t.pop()
                j += 1

        t = []
        ans = []
        dfs(n, 2)
        return ans
```

```java
class Solution {
    private List<Integer> t = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dfs(n, 2);
        return ans;
    }

    private void dfs(int n, int i) {
        if (!t.isEmpty()) {
            List<Integer> cp = new ArrayList<>(t);
            cp.add(n);
            ans.add(cp);
        }
        for (int j = i; j <= n / j; ++j) {
            if (n % j == 0) {
                t.add(j);
                dfs(n / j, j);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> getFactors(int n) {
        vector<int> t;
        vector<vector<int>> ans;
        function<void(int, int)> dfs = [&](int n, int i) {
            if (t.size()) {
                vector<int> cp = t;
                cp.emplace_back(n);
                ans.emplace_back(cp);
            }
            for (int j = i; j <= n / j; ++j) {
                if (n % j == 0) {
                    t.emplace_back(j);
                    dfs(n / j, j);
                    t.pop_back();
                }
            }
        };
        dfs(n, 2);
        return ans;
    }
};
```

```go
func getFactors(n int) [][]int {
	t := []int{}
	ans := [][]int{}
	var dfs func(n, i int)
	dfs = func(n, i int) {
		if len(t) > 0 {
			ans = append(ans, append(slices.Clone(t), n))
		}
		for j := i; j <= n/j; j++ {
			if n%j == 0 {
				t = append(t, j)
				dfs(n/j, j)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(n, 2)
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
