---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0090.Subsets%20II/README.md
tags:
    - 位运算
    - 数组
    - 回溯
---

<!-- problem:start -->

# [90. 子集 II](https://leetcode.cn/problems/subsets-ii)

[English Version](/solution/0000-0099/0090.Subsets%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，其中可能包含重复元素，请你返回该数组所有可能的 <span data-keyword="subset">子集</span>（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。返回的解集中，子集可以按 <strong>任意顺序</strong> 排列。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2]
<strong>输出：</strong>[[],[1],[1,2],[1,2,2],[2],[2,2]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[[],[0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + DFS

我们可以先对数组 $\textit{nums}$ 进行排序，方便去重。

然后，我们设计一个函数 $\textit{dfs}(i)$，表示当前从第 $i$ 个元素开始搜索子集。函数 $\textit{dfs}(i)$ 的执行逻辑如下：

如果 $i \geq n$，说明已经搜索完所有元素，将当前子集加入答案数组中，递归结束。

如果 $i < n$，将第 $i$ 个元素加入子集，执行 $\textit{dfs}(i + 1)$，然后将第 $i$ 个元素从子集中移除。接下来，我们判断第 $i$ 个元素是否和下一个元素相同，如果相同，则循环跳过该元素，直到找到第一个和第 $i$ 个元素不同的元素，执行 $\textit{dfs}(i + 1)$。

最后，我们只需要调用 $\textit{dfs}(0)$，返回答案数组即可。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == len(nums):
                ans.append(t[:])
                return
            t.append(nums[i])
            dfs(i + 1)
            x = t.pop()
            while i + 1 < len(nums) and nums[i + 1] == x:
                i += 1
            dfs(i + 1)

        nums.sort()
        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[i]);
        dfs(i + 1);
        int x = t.remove(t.size() - 1);
        while (i + 1 < nums.length && nums[i + 1] == x) {
            ++i;
        }
        dfs(i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        ranges::sort(nums);
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            t.push_back(nums[i]);
            dfs(i + 1);
            t.pop_back();
            while (i + 1 < n && nums[i + 1] == nums[i]) {
                ++i;
            }
            dfs(i + 1);
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func subsetsWithDup(nums []int) (ans [][]int) {
	slices.Sort(nums)
	n := len(nums)
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, slices.Clone(t))
			return
		}
		t = append(t, nums[i])
		dfs(i + 1)
		t = t[:len(t)-1]
		for i+1 < n && nums[i+1] == nums[i] {
			i++
		}
		dfs(i + 1)
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t: number[] = [];
    const ans: number[][] = [];
    const dfs = (i: number): void => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
        while (i + 1 < n && nums[i] === nums[i + 1]) {
            i++;
        }
        dfs(i + 1);
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let mut ans = Vec::new();
        let mut t = Vec::new();

        fn dfs(i: usize, nums: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
            if i >= nums.len() {
                ans.push(t.clone());
                return;
            }
            t.push(nums[i]);
            dfs(i + 1, nums, t, ans);
            t.pop();
            let mut i = i;
            while i + 1 < nums.len() && nums[i + 1] == nums[i] {
                i += 1;
            }
            dfs(i + 1, nums, t, ans);
        }

        dfs(0, &nums, &mut t, &mut ans);
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
var subsetsWithDup = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t = [];
    const ans = [];
    const dfs = i => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
        while (i + 1 < n && nums[i] === nums[i + 1]) {
            i++;
        }
        dfs(i + 1);
    };
    dfs(0);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private IList<IList<int>> ans = new List<IList<int>>();
    private IList<int> t = new List<int>();
    private int[] nums;

    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        this.nums = nums;
        Dfs(0);
        return ans;
    }

    private void Dfs(int i) {
        if (i >= nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        t.Add(nums[i]);
        Dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        while (i + 1 < nums.Length && nums[i + 1] == nums[i]) {
            ++i;
        }
        Dfs(i + 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：排序 + 二进制枚举

与方法一类似，我们先对数组 $\textit{nums}$ 进行排序，方便去重。

接下来，我们在 $[0, 2^n)$ 的范围内枚举一个二进制数 $\textit{mask}$，其中 $\textit{mask}$ 的二进制表示是一个 $n$ 位的位串，如果 $\textit{mask}$ 的第 $i$ 位为 $1$，表示选择 $\textit{nums}[i]$，为 $0$ 表示不选择 $\textit{nums}[i]$。注意，如果 $\textit{mask}$ 的 $i - 1$ 位为 $0$，且 $\textit{nums}[i] = \textit{nums}[i - 1]$，则说明在当前枚举到的方案中，第 $i$ 个元素和第 $i - 1$ 个元素相同，为了避免重复，我们跳过这种情况。否则，我们将 $\textit{mask}$ 对应的子集加入答案数组中。

枚举结束后，我们返回答案数组即可。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        n = len(nums)
        ans = []
        for mask in range(1 << n):
            ok = True
            t = []
            for i in range(n):
                if mask >> i & 1:
                    if i and (mask >> (i - 1) & 1) == 0 and nums[i] == nums[i - 1]:
                        ok = False
                        break
                    t.append(nums[i])
            if ok:
                ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (ok) {
                ans.add(t);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << n; ++mask) {
            vector<int> t;
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.push_back(nums[i]);
                }
            }
            if (ok) {
                ans.push_back(t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func subsetsWithDup(nums []int) (ans [][]int) {
	sort.Ints(nums)
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		ok := true
		for i := 0; i < n; i++ {
			if mask>>i&1 == 1 {
				if i > 0 && mask>>(i-1)&1 == 0 && nums[i] == nums[i-1] {
					ok = false
					break
				}
				t = append(t, nums[i])
			}
		}
		if ok {
			ans = append(ans, t)
		}
	}
	return
}
```

#### TypeScript

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        let ok: boolean = true;
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                if (i && ((mask >> (i - 1)) & 1) === 0 && nums[i] === nums[i - 1]) {
                    ok = false;
                    break;
                }
                t.push(nums[i]);
            }
        }
        if (ok) {
            ans.push(t);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        for mask in 0..1 << n {
            let mut t = Vec::new();
            let mut ok = true;
            for i in 0..n {
                if ((mask >> i) & 1) == 1 {
                    if i > 0 && ((mask >> (i - 1)) & 1) == 0 && nums[i] == nums[i - 1] {
                        ok = false;
                        break;
                    }
                    t.push(nums[i]);
                }
            }
            if ok {
                ans.push(t);
            }
        }
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
var subsetsWithDup = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t = [];
        let ok = true;
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                if (i && ((mask >> (i - 1)) & 1) === 0 && nums[i] === nums[i - 1]) {
                    ok = false;
                    break;
                }
                t.push(nums[i]);
            }
        }
        if (ok) {
            ans.push(t);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        IList<IList<int>> ans = new List<IList<int>>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            IList<int> t = new List<int>();
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.Add(nums[i]);
                }
            }
            if (ok) {
                ans.Add(t);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
