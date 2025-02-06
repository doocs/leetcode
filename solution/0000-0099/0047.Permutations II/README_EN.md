---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0047.Permutations%20II/README_EN.md
tags:
    - Array
    - Backtracking
    - Sorting
---

<!-- problem:start -->

# [47. Permutations II](https://leetcode.com/problems/permutations-ii)

[中文文档](/solution/0000-0099/0047.Permutations%20II/README.md)

## Description

<!-- description:start -->

<p>Given a collection of numbers, <code>nums</code>,&nbsp;that might contain duplicates, return <em>all possible unique permutations <strong>in any order</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong>
[[1,1,2],
 [1,2,1],
 [2,1,1]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 8</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Backtracking

We can first sort the array so that duplicate numbers are placed together, making it easier to remove duplicates.

Then, we design a function $\textit{dfs}(i)$, which represents the current number to be placed at the $i$-th position. The specific implementation of the function is as follows:

-   If $i = n$, it means we have filled all positions, add the current permutation to the answer array, and then return.
-   Otherwise, we enumerate the number $nums[j]$ for the $i$-th position, where the range of $j$ is $[0, n - 1]$. We need to ensure that $nums[j]$ has not been used and is different from the previously enumerated number to ensure that the current permutation is not duplicated. If the conditions are met, we can place $nums[j]$ and continue to recursively fill the next position by calling $\textit{dfs}(i + 1)$. After the recursive call ends, we need to mark $nums[j]$ as unused to facilitate subsequent enumeration.

In the main function, we first sort the array, then call $\textit{dfs}(0)$ to start filling from the 0th position, and finally return the answer array.

The time complexity is $O(n \times n!)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array. We need to perform $n!$ enumerations, and each enumeration requires $O(n)$ time to check for duplicates. Additionally, we need a marker array to mark whether each position has been used, so the space complexity is $O(n)$.

Similar problems:

-   [46. Permutations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0046.Permutations/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == n:
                ans.append(t[:])
                return
            for j in range(n):
                if vis[j] or (j and nums[j] == nums[j - 1] and not vis[j - 1]):
                    continue
                t[i] = nums[j]
                vis[j] = True
                dfs(i + 1)
                vis[j] = False

        n = len(nums)
        nums.sort()
        ans = []
        t = [0] * n
        vis = [False] * n
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;
    private boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t.add(nums[j]);
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        vector<vector<int>> ans;
        vector<int> t(n);
        vector<bool> vis(n);
        auto dfs = [&](this auto&& dfs, int i) {
            if (i == n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j] || (j && nums[j] == nums[j - 1] && !vis[j - 1])) {
                    continue;
                }
                t[i] = nums[j];
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func permuteUnique(nums []int) (ans [][]int) {
	slices.Sort(nums)
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j := 0; j < n; j++ {
			if vis[j] || (j > 0 && nums[j] == nums[j-1] && !vis[j-1]) {
				continue
			}
			vis[j] = true
			t[i] = nums[j]
			dfs(i + 1)
			vis[j] = false
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function permuteUnique(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans: number[][] = [];
    const t: number[] = Array(n);
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && nums[j] === nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t[i] = nums[j];
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn permute_unique(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        let mut t = vec![0; n];
        let mut vis = vec![false; n];

        fn dfs(
            nums: &Vec<i32>,
            t: &mut Vec<i32>,
            vis: &mut Vec<bool>,
            ans: &mut Vec<Vec<i32>>,
            i: usize,
        ) {
            if i == nums.len() {
                ans.push(t.clone());
                return;
            }
            for j in 0..nums.len() {
                if vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1]) {
                    continue;
                }
                t[i] = nums[j];
                vis[j] = true;
                dfs(nums, t, vis, ans, i + 1);
                vis[j] = false;
            }
        }

        dfs(&nums, &mut t, &mut vis, &mut ans, 0);
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
var permuteUnique = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans = [];
    const t = Array(n);
    const vis = Array(n).fill(false);
    const dfs = i => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && nums[j] === nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t[i] = nums[j];
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] nums;
    private bool[] vis;

    public IList<IList<int>> PermuteUnique(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        vis = new bool[n];
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        for (int j = 0; j < nums.Length; ++j) {
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.Add(nums[j]);
            dfs(i + 1);
            t.RemoveAt(t.Count - 1);
            vis[j] = false;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
