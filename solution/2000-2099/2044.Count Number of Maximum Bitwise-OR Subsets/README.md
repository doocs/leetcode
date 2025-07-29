---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README.md
rating: 1567
source: 第 263 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 回溯
    - 枚举
---

<!-- problem:start -->

# [2044. 统计按位或能得到最大值的子集数目](https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets)

[English Version](/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，请你找出 <code>nums</code> 子集 <strong>按位或</strong> 可能得到的<strong> </strong><strong>最大值</strong> ，并返回按位或能得到最大值的 <strong>不同非空子集的数目</strong> 。</p>

<p>如果数组 <code>a</code> 可以由数组 <code>b</code> 删除一些元素（或不删除）得到，则认为数组 <code>a</code> 是数组 <code>b</code> 的一个 <strong>子集</strong> 。如果选中的元素下标位置不一样，则认为两个子集 <strong>不同</strong> 。</p>

<p>对数组 <code>a</code> 执行 <strong>按位或</strong>&nbsp;，结果等于 <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code>（下标从 <strong>0</strong> 开始）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1]
<strong>输出：</strong>2
<strong>解释：</strong>子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
- [3]
- [3,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2]
<strong>输出：</strong>7
<strong>解释：</strong>[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2<sup>3</sup> - 1 = 7 个子集。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,5]
<strong>输出：</strong>6
<strong>解释：</strong>子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

数组 $\textit{nums}$ 中按位或的最大值 $\textit{mx}$ 可以通过对数组中所有元素按位或得到。

然后我们可以使用深度优先搜索来枚举所有子集，统计按位或等于 $\textit{mx}$ 的子集个数。我们设计一个函数 $\text{dfs(i, t)}$，表示从下标 $\textit{i}$ 开始，当前按位或的值为 $\textit{t}$ 的子集个数。初始时 $\textit{i} = 0$, $\textit{t} = 0$。

在函数 $\text{dfs(i, t)}$ 中，如果 $\textit{i}$ 等于数组长度，说明已经枚举完所有元素，此时如果 $\textit{t}$ 等于 $\textit{mx}$，则答案加一。否则，我们可以选择不包含当前元素 $\textit{nums[i]}$，或者包含当前元素 $\textit{nums[i]}$，因此我们可以递归调用 $\text{dfs(i + 1, t)}$ 和 $\text{dfs(i + 1, t | nums[i])}$。

最后返回答案即可。

时间复杂度 $O(2^n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

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

### 方法二：二进制枚举

我们可以使用二进制枚举来统计所有子集的按位或结果。对于长度为 $n$ 的数组 $\textit{nums}$，我们可以使用一个整数 $\textit{mask}$ 来表示一个子集，其中 $\textit{mask}$ 的第 $i$ 位为 1 表示包含元素 $\textit{nums[i]}$，为 0 则表示不包含。

我们可以遍历所有可能的 $\textit{mask}$，从 $0$ 到 $2^n - 1$。对于每个 $\textit{mask}$，我们可以计算出对应子集的按位或结果，并更新最大值 $\textit{mx}$ 和答案 $\textit{ans}$。

时间复杂度 $O(2^n \cdot n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

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
