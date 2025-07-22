---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0046.Permutations/README_EN.md
tags:
    - Array
    - Backtracking
---

<!-- problem:start -->

# [46. Permutations](https://leetcode.com/problems/permutations)

[中文文档](/solution/0000-0099/0046.Permutations/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code> of distinct integers, return all the possible <span data-keyword="permutation-array">permutations</span>. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> [[0,1],[1,0]]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1]
<strong>Output:</strong> [[1]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 6</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS (Backtracking)

We design a function $dfs(i)$ to represent that the first $i$ positions have been filled, and now we need to fill the $i+1$ position. We enumerate all possible numbers, if this number has not been filled, we fill in this number, and then continue to fill the next position, until all positions are filled.

The time complexity is $O(n \times n!)$, where $n$ is the length of the array. There are $n!$ permutations in total, and each permutation takes $O(n)$ time to construct.

Similar problems:

-   [47. Permutations II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0047.Permutations%20II/README_EN.md)

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
