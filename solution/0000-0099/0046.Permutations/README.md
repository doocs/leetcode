---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0046.Permutations/README.md
tags:
    - 数组
    - 回溯
---

<!-- problem:start -->

# [46. 全排列](https://leetcode.cn/problems/permutations)

[English Version](/solution/0000-0099/0046.Permutations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个不含重复数字的数组 <code>nums</code> ，返回其 <em>所有可能的全排列</em> 。你可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1]
<strong>输出：</strong>[[0,1],[1,0]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>[[1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 6</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS（回溯）

我们设计一个函数 $dfs(i)$ 表示已经填完了前 $i$ 个位置，现在需要填第 $i+1$ 个位置。枚举所有可能的数，如果这个数没有被填过，就填入这个数，然后继续填下一个位置，直到填完所有的位置。

时间复杂度 $O(n \times n!)$，其中 $n$ 是数组的长度。一共有 $n!$ 个排列，每个排列需要 $O(n)$ 的时间来构造。

相似题目：

-   [47. 全排列 II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0047.Permutations%20II/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i >= n:
                ans.append(t[:])
                return
            for j, x in enumerate(nums):
                if not vis[j]:
                    vis[j] = True
                    t[i] = x
                    dfs(i + 1)
                    vis[j] = False

        n = len(nums)
        vis = [False] * n
        t = [0] * n
        ans = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private boolean[] vis;
    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        vis = new boolean[nums.length];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t.add(nums[j]);
                dfs(i + 1);
                t.remove(t.size() - 1);
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
    vector<vector<int>> permute(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        vector<int> t(n);
        vector<bool> vis(n);
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i == n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    t[i] = nums[j];
                    dfs(i + 1);
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
func permute(nums []int) (ans [][]int) {
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j, x := range nums {
			if !vis[j] {
				vis[j] = true
				t[i] = x
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
function permute(nums: number[]): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    const vis: boolean[] = Array(n).fill(false);
    const t: number[] = Array(n).fill(0);
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t[i] = nums[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut ans = Vec::new();
        let mut t = vec![0; n];
        let mut vis = vec![false; n];
        fn dfs(
            nums: &Vec<i32>,
            n: usize,
            t: &mut Vec<i32>,
            vis: &mut Vec<bool>,
            ans: &mut Vec<Vec<i32>>,
            i: usize
        ) {
            if i == n {
                ans.push(t.clone());
                return;
            }
            for j in 0..n {
                if !vis[j] {
                    vis[j] = true;
                    t[i] = nums[j];
                    dfs(nums, n, t, vis, ans, i + 1);
                    vis[j] = false;
                }
            }
        }
        dfs(&nums, n, &mut t, &mut vis, &mut ans, 0);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function (nums) {
    const n = nums.length;
    const ans = [];
    const vis = Array(n).fill(false);
    const t = Array(n).fill(0);
    const dfs = i => {
        if (i >= n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t[i] = nums[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> Permute(int[] nums) {
        var ans = new List<IList<int>>();
        var t = new List<int>();
        var vis = new bool[nums.Length];
        dfs(nums, 0, t, vis, ans);
        return ans;
    }

    private void dfs(int[] nums, int i, IList<int> t, bool[] vis, IList<IList<int>> ans) {
        if (i >= nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        for (int j = 0; j < nums.Length; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t.Add(nums[j]);
                dfs(nums, i + 1, t, vis, ans);
                t.RemoveAt(t.Count - 1);
                vis[j] = false;
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
