---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0090.Subsets%20II/README_EN.md
tags:
    - Bit Manipulation
    - Array
    - Backtracking
---

<!-- problem:start -->

# [90. Subsets II](https://leetcode.com/problems/subsets-ii)

[中文文档](/solution/0000-0099/0090.Subsets%20II/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> that may contain duplicates, return <em>all possible</em> <span data-keyword="subset"><em>subsets</em></span><em> (the power set)</em>.</p>

<p>The solution set <strong>must not</strong> contain duplicate subsets. Return the solution in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> [[],[1],[1,2],[1,2,2],[2],[2,2]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [[],[0]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + DFS

We can first sort the array $nums$ to facilitate deduplication.

Then, we design a function $dfs(i)$, which represents searching for subsets starting from the $i$-th element. The execution logic of the function $dfs(i)$ is as follows:

If $i \geq n$, it means that all elements have been searched, and the current subset is added to the answer array, and the recursion ends.

If $i < n$, add the $i$-th element to the subset, execute $dfs(i + 1)$, and then remove the $i$-th element from the subset. Next, we judge whether the $i$-th element is the same as the next element. If it is the same, we loop to skip this element until we find the first element that is different from the $i$-th element, and execute $dfs(i + 1)$.

Finally, we only need to call $dfs(0)$ and return the answer array.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

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
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        function<void(int)> dfs = [&](int i) {
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
	sort.Ints(nums)
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Binary Enumeration

Similar to Solution 1, we first sort the array $nums$ to facilitate deduplication.

Next, we enumerate a binary number $mask$ in the range of $[0, 2^n)$, where the binary representation of $mask$ is an $n$-bit bit string. If the $i$-th bit of $mask$ is $1$, it means to select $nums[i]$, and $0$ means not to select $nums[i]$. Note that if the $i - 1$ bit of $mask$ is $0$, and $nums[i] = nums[i - 1]$, it means that in the current enumerated scheme, the $i$-th element and the $i - 1$-th element are the same. To avoid repetition, we skip this situation. Otherwise, we add the subset corresponding to $mask$ to the answer array.

After the enumeration ends, we return the answer array.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

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
        sort(nums.begin(), nums.end());
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
