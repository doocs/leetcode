---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README_EN.md
rating: 1567
source: Weekly Contest 263 Q3
tags:
    - Bit Manipulation
    - Array
    - Backtracking
    - Enumeration
---

<!-- problem:start -->

# [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets)

[中文文档](/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, find the <strong>maximum</strong> possible <strong>bitwise OR</strong> of a subset of <code>nums</code> and return <em>the <strong>number of different non-empty subsets</strong> with the maximum bitwise OR</em>.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>. Two subsets are considered <strong>different</strong> if the indices of the elements chosen are different.</p>

<p>The bitwise OR of an array <code>a</code> is equal to <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code> (<strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 2<sup>3</sup> - 1 = 7 total subsets.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

The maximum bitwise OR value $\textit{mx}$ in the array $\textit{nums}$ can be obtained by performing bitwise OR on all elements in the array.

Then we can use depth-first search to enumerate all subsets and count the number of subsets whose bitwise OR equals $\textit{mx}$. We design a function $\text{dfs(i, t)}$, which represents the number of subsets starting from index $\textit{i}$ with the current bitwise OR value being $\textit{t}$. Initially, $\textit{i} = 0$ and $\textit{t} = 0$.

In the function $\text{dfs(i, t)}$, if $\textit{i}$ equals the array length, it means we have enumerated all elements. At this point, if $\textit{t}$ equals $\textit{mx}$, we increment the answer by one. Otherwise, we can choose to either exclude the current element $\textit{nums[i]}$ or include the current element $\textit{nums[i]}$, so we can recursively call $\text{dfs(i + 1, t)}$ and $\text{dfs(i + 1, t | nums[i])}$.

Finally, we return the answer.

The time complexity is $O(2^n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        def dfs(i, t):
            nonlocal ans, mx
            if i == len(nums):
                if t == mx:
                    ans += 1
                return
            dfs(i + 1, t)
            dfs(i + 1, t | nums[i])

        ans = 0
        mx = reduce(lambda x, y: x | y, nums)
        dfs(0, 0)
        return ans
```

#### Java

```java
class Solution {
    private int mx;
    private int ans;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        mx = 0;
        for (int x : nums) {
            mx |= x;
        }
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i == nums.length) {
            if (t == mx) {
                ++ans;
            }
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countMaxOrSubsets(vector<int>& nums) {
        int ans = 0;
        int mx = accumulate(nums.begin(), nums.end(), 0, bit_or<int>());
        auto dfs = [&](this auto&& dfs, int i, int t) {
            if (i == nums.size()) {
                if (t == mx) {
                    ans++;
                }
                return;
            }
            dfs(i + 1, t);
            dfs(i + 1, t | nums[i]);
        };
        dfs(0, 0);
        return ans;
    }
};
```

#### Go

```go
func countMaxOrSubsets(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		mx |= x
	}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i == len(nums) {
			if t == mx {
				ans++
			}
			return
		}
		dfs(i+1, t)
		dfs(i+1, t|nums[i])
	}

	dfs(0, 0)
	return
}
```

#### TypeScript

```ts
function countMaxOrSubsets(nums: number[]): number {
    let ans = 0;
    const mx = nums.reduce((x, y) => x | y, 0);

    const dfs = (i: number, t: number) => {
        if (i === nums.length) {
            if (t === mx) {
                ans++;
            }
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    };

    dfs(0, 0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mx = nums.iter().fold(0, |x, &y| x | y);

        fn dfs(i: usize, t: i32, nums: &Vec<i32>, mx: i32, ans: &mut i32) {
            if i == nums.len() {
                if t == mx {
                    *ans += 1;
                }
                return;
            }
            dfs(i + 1, t, nums, mx, ans);
            dfs(i + 1, t | nums[i], nums, mx, ans);
        }

        dfs(0, 0, &nums, mx, &mut ans);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary Enumeration

We can use binary enumeration to count the bitwise OR results of all subsets. For an array $\textit{nums}$ of length $n$, we can use an integer $\textit{mask}$ to represent a subset, where the $i$-th bit of $\textit{mask}$ being 1 means including element $\textit{nums[i]}$, and 0 means not including it.

We can iterate through all possible $\textit{mask}$ values from $0$ to $2^n - 1$. For each $\textit{mask}$, we can calculate the bitwise OR result of the corresponding subset and update the maximum value $\textit{mx}$ and answer $\textit{ans}$.

The time complexity is $O(2^n \cdot n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        mx = 0
        for mask in range(1 << n):
            t = 0
            for i, v in enumerate(nums):
                if (mask >> i) & 1:
                    t |= v
            if mx < t:
                mx = t
                ans = 1
            elif mx == t:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int mx = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t |= nums[i];
                }
            }
            if (mx < t) {
                mx = t;
                ans = 1;
            } else if (mx == t) {
                ++ans;
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
    int countMaxOrSubsets(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        int mx = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) & 1) {
                    t |= nums[i];
                }
            }
            if (mx < t) {
                mx = t;
                ans = 1;
            } else if (mx == t)
                ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func countMaxOrSubsets(nums []int) (ans int) {
	n := len(nums)
	mx := 0

	for mask := 0; mask < (1 << n); mask++ {
		t := 0
		for i, v := range nums {
			if (mask>>i)&1 == 1 {
				t |= v
			}
		}
		if mx < t {
			mx = t
			ans = 1
		} else if mx == t {
			ans++
		}
	}

	return
}
```

#### TypeScript

```ts
function countMaxOrSubsets(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let mx = 0;

    for (let mask = 0; mask < 1 << n; mask++) {
        let t = 0;
        for (let i = 0; i < n; i++) {
            if ((mask >> i) & 1) {
                t |= nums[i];
            }
        }
        if (mx < t) {
            mx = t;
            ans = 1;
        } else if (mx === t) {
            ans++;
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut mx = 0;

        for mask in 0..(1 << n) {
            let mut t = 0;
            for i in 0..n {
                if (mask >> i) & 1 == 1 {
                    t |= nums[i];
                }
            }
            if mx < t {
                mx = t;
                ans = 1;
            } else if mx == t {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
