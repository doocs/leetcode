# [2441. Largest Positive Integer That Exists With Its Negative](https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative)

[中文文档](/solution/2400-2499/2441.Largest%20Positive%20Integer%20That%20Exists%20With%20Its%20Negative/README.md)

## Description

<p>Given an integer array <code>nums</code> that <strong>does not contain</strong> any zeros, find <strong>the largest positive</strong> integer <code>k</code> such that <code>-k</code> also exists in the array.</p>

<p>Return <em>the positive integer </em><code>k</code>. If there is no such integer, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,2,-3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 3 is the only valid k we can find in the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,10,6,7,-7,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-10,8,6,7,-2,-3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no a single valid k, we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

## Solutions

### Solution 1: Hash Table

We can use a hash table $s$ to record all elements that appear in the array, and a variable $ans$ to record the maximum positive integer that satisfies the problem requirements, initially $ans = -1$.

Next, we traverse each element $x$ in the hash table $s$. If $-x$ exists in $s$, then we update $ans = \max(ans, x)$.

After the traversal ends, return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def findMaxK(self, nums: List[int]) -> int:
        s = set(nums)
        return max((x for x in s if -x in s), default=-1)
```

```java
class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int x : s) {
            if (s.contains(-x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMaxK(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = -1;
        for (int x : s) {
            if (s.count(-x)) {
                ans = max(ans, x);
            }
        }
        return ans;
    }
};
```

```go
func findMaxK(nums []int) int {
	ans := -1
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x := range s {
		if s[-x] && ans < x {
			ans = x
		}
	}
	return ans
}
```

```ts
function findMaxK(nums: number[]): number {
    let ans = -1;
    const s = new Set(nums);
    for (const x of s) {
        if (s.has(-x)) {
            ans = Math.max(ans, x);
        }
    }
    return ans;
}
```

```rust
use std::collections::HashSet;
impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let s = nums.into_iter().collect::<HashSet<i32>>();
        let mut ans = -1;
        for &x in s.iter() {
            if s.contains(&-x) {
                ans = ans.max(x);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
use std::collections::HashSet;

impl Solution {
    pub fn find_max_k(nums: Vec<i32>) -> i32 {
        let mut ans = -1;
        let mut h = HashSet::new();

        for &n in &nums {
            h.insert(n);
        }

        for &n in &nums {
            if h.contains(&-n) && n > ans {
                ans = n;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
