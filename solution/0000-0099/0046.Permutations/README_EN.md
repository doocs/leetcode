# [46. Permutations](https://leetcode.com/problems/permutations)

[中文文档](/solution/0000-0099/0046.Permutations/README.md)

<!-- tags:Array,Backtracking -->

## Description

<p>Given an array <code>nums</code> of distinct integers, return <em>all the possible permutations</em>. You can return the answer in <strong>any order</strong>.</p>

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

## Solutions

### Solution 1: DFS (Backtracking)

We design a function $dfs(i)$ to represent that the first $i$ positions have been filled, and now we need to fill the $i+1$ position. We enumerate all possible numbers, if this number has not been filled, we fill in this number, and then continue to fill the next position, until all positions are filled.

The time complexity is $O(n \times n!)$, where $n$ is the length of the array. There are $n!$ permutations in total, and each permutation takes $O(n)$ time to construct.

Similar problems:

-   [47. Permutations II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0047.Permutations%20II/README_EN.md)

<!-- tabs:start -->

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        return list(permutations(nums))
```

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

```cpp
class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        vector<int> t(n);
        vector<bool> vis(n);
        function<void(int)> dfs = [&](int i) {
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
		for j, v := range nums {
			if !vis[j] {
				vis[j] = true
				t[i] = v
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
```

```ts
function permute(nums: number[]): number[][] {
    const n = nums.length;
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...nums]);
        }
        for (let j = i; j < n; j++) {
            [nums[i], nums[j]] = [nums[j], nums[i]];
            dfs(i + 1);
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    };
    dfs(0);
    return res;
}
```

```rust
impl Solution {
    fn dfs(i: usize, nums: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        let n = nums.len();
        if i == n {
            res.push(nums.clone());
            return;
        }
        for j in i..n {
            nums.swap(i, j);
            Self::dfs(i + 1, nums, res);
            nums.swap(i, j);
        }
    }

    pub fn permute(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(0, &mut nums, &mut res);
        res
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function (nums) {
    const n = nums.length;
    const ans = [];
    const t = [];
    const vis = new Array(n).fill(false);
    function dfs(i) {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t.push(nums[j]);
                dfs(i + 1);
                vis[j] = false;
                t.pop();
            }
        }
    }
    dfs(0);
    return ans;
};
```

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

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(i):
            if i == n:
                ans.append(t[:])
                return
            for j in range(n):
                if not vis[j]:
                    vis[j] = True
                    t[i] = nums[j]
                    dfs(i + 1)
                    vis[j] = False

        n = len(nums)
        vis = [False] * n
        t = [0] * n
        ans = []
        dfs(0)
        return ans
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn permute(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ret = Vec::new();
        let mut cur_vec = Vec::new();
        let mut vis = vec![false; nums.len() + 1];
        Self::dfs(&nums, &mut vis, 0, &mut cur_vec, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn dfs(
        nums: &Vec<i32>,
        vis: &mut Vec<bool>,
        index: i32,
        cur_vec: &mut Vec<i32>,
        ans: &mut Vec<Vec<i32>>
    ) {
        let n = nums.len();
        if (index as usize) == n {
            ans.push(cur_vec.clone());
            return;
        }
        // Otherwise, let's iterate the nums vector
        for i in 0..n {
            if !vis[i] {
                // If this number hasn't been visited, then visit it
                vis[i] = true;
                cur_vec.push(nums[i]);
                Self::dfs(nums, vis, index + 1, cur_vec, ans);
                // Reset the changes
                cur_vec.pop().unwrap();
                vis[i] = false;
            }
        }
    }
}
```

<!-- tabs:end -->

### Solution 3

<!-- tabs:start -->

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(i):
            nonlocal mask
            if i == n:
                ans.append(t[:])
                return
            for j in range(n):
                if (mask >> j & 1) == 0:
                    mask |= 1 << j
                    t[i] = nums[j]
                    dfs(i + 1)
                    mask ^= 1 << j

        n = len(nums)
        mask = 0
        t = [0] * n
        ans = []
        dfs(0)
        return ans
```

<!-- tabs:end -->

<!-- end -->
