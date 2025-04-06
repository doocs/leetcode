---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0368.Largest%20Divisible%20Subset/README_EN.md
tags:
    - Array
    - Math
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [368. Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset)

[中文文档](/solution/0300-0399/0368.Largest%20Divisible%20Subset/README.md)

## Description

<!-- description:start -->

<p>Given a set of <strong>distinct</strong> positive integers <code>nums</code>, return the largest subset <code>answer</code> such that every pair <code>(answer[i], answer[j])</code> of elements in this subset satisfies:</p>

<ul>
	<li><code>answer[i] % answer[j] == 0</code>, or</li>
	<li><code>answer[j] % answer[i] == 0</code></li>
</ul>

<p>If there are multiple solutions, return any of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> [1,3] is also accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,8]
<strong>Output:</strong> [1,2,4,8]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>9</sup></code></li>
	<li>All the integers in <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        f = [1] * n
        k = 0
        for i in range(n):
            for j in range(i):
                if nums[i] % nums[j] == 0:
                    f[i] = max(f[i], f[j] + 1)
            if f[k] < f[i]:
                k = i
        m = f[k]
        i = k
        ans = []
        while m:
            if nums[k] % nums[i] == 0 and f[i] == m:
                ans.append(nums[i])
                k, m = i, m - 1
            i -= 1
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int k = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            if (f[k] < f[i]) {
                k = i;
            }
        }
        int m = f[k];
        List<Integer> ans = new ArrayList<>();
        for (int i = k; m > 0; --i) {
            if (nums[k] % nums[i] == 0 && f[i] == m) {
                ans.add(nums[i]);
                k = i;
                --m;
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
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        int f[n];
        int k = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
            if (f[k] < f[i]) {
                k = i;
            }
        }
        int m = f[k];
        vector<int> ans;
        for (int i = k; m > 0; --i) {
            if (nums[k] % nums[i] == 0 && f[i] == m) {
                ans.push_back(nums[i]);
                k = i;
                --m;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func largestDivisibleSubset(nums []int) (ans []int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n)
	k := 0
	for i := 0; i < n; i++ {
		f[i] = 1
		for j := 0; j < i; j++ {
			if nums[i]%nums[j] == 0 {
				f[i] = max(f[i], f[j]+1)
			}
		}
		if f[k] < f[i] {
			k = i
		}
	}
	m := f[k]
	for i := k; m > 0; i-- {
		if nums[k]%nums[i] == 0 && f[i] == m {
			ans = append(ans, nums[i])
			k = i
			m--
		}
	}
	return
}
```

#### TypeScript

```ts
function largestDivisibleSubset(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    let k = 0;

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] % nums[j] === 0) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
        if (f[k] < f[i]) {
            k = i;
        }
    }

    let m = f[k];
    const ans: number[] = [];
    for (let i = k; m > 0; --i) {
        if (nums[k] % nums[i] === 0 && f[i] === m) {
            ans.push(nums[i]);
            k = i;
            --m;
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn largest_divisible_subset(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;
        nums.sort();

        let n = nums.len();
        let mut f = vec![1; n];
        let mut k = 0;

        for i in 0..n {
            for j in 0..i {
                if nums[i] % nums[j] == 0 {
                    f[i] = f[i].max(f[j] + 1);
                }
            }
            if f[k] < f[i] {
                k = i;
            }
        }

        let mut m = f[k];
        let mut ans = Vec::new();

        for i in (0..=k).rev() {
            if nums[k] % nums[i] == 0 && f[i] == m {
                ans.push(nums[i]);
                k = i;
                m -= 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
