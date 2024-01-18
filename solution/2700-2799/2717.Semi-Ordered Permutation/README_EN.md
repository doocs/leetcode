# [2717. Semi-Ordered Permutation](https://leetcode.com/problems/semi-ordered-permutation)

[中文文档](/solution/2700-2799/2717.Semi-Ordered%20Permutation/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> permutation of <code>n</code> integers <code>nums</code>.</p>

<p>A permutation is called <strong>semi-ordered</strong> if the first number equals <code>1</code> and the last number equals <code>n</code>. You can perform the below operation as many times as you want until you make <code>nums</code> a <strong>semi-ordered</strong> permutation:</p>

<ul>
	<li>Pick two adjacent elements in <code>nums</code>, then swap them.</li>
</ul>

<p>Return <em>the minimum number of operations to make </em><code>nums</code><em> a <strong>semi-ordered permutation</strong></em>.</p>

<p>A <strong>permutation</strong> is a sequence of integers from <code>1</code> to <code>n</code> of length <code>n</code> containing each number exactly once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can make the permutation semi-ordered using these sequence of operations: 
1 - swap i = 0 and j = 1. The permutation becomes [1,2,4,3].
2 - swap i = 2 and j = 3. The permutation becomes [1,2,3,4].
It can be proved that there is no sequence of less than two operations that make nums a semi-ordered permutation. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,1,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can make the permutation semi-ordered using these sequence of operations:
1 - swap i = 1 and j = 2. The permutation becomes [2,1,4,3].
2 - swap i = 0 and j = 1. The permutation becomes [1,2,4,3].
3 - swap i = 2 and j = 3. The permutation becomes [1,2,3,4].
It can be proved that there is no sequence of less than three operations that make nums a semi-ordered permutation.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,4,2,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The permutation is already a semi-ordered permutation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length == n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= 50</code></li>
	<li><code>nums is a permutation.</code></li>
</ul>

## Solutions

### Solution 1: Find the Positions of 1 and n

We can first find the indices $i$ and $j$ of $1$ and $n$, respectively. Then, based on the relative positions of $i$ and $j$, we can determine the number of swaps required.

If $i < j$, the number of swaps required is $i + n - j - 1$. If $i > j$, the number of swaps required is $i + n - j - 2$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def semiOrderedPermutation(self, nums: List[int]) -> int:
        n = len(nums)
        i = nums.index(1)
        j = nums.index(n)
        k = 1 if i < j else 2
        return i + n - j - k
```

```java
class Solution {
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == 1) {
                i = k;
            }
            if (nums[k] == n) {
                j = k;
            }
        }
        int k = i < j ? 1 : 2;
        return i + n - j - k;
    }
}
```

```cpp
class Solution {
public:
    int semiOrderedPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), 1) - nums.begin();
        int j = find(nums.begin(), nums.end(), n) - nums.begin();
        int k = i < j ? 1 : 2;
        return i + n - j - k;
    }
};
```

```go
func semiOrderedPermutation(nums []int) int {
	n := len(nums)
	var i, j int
	for k, x := range nums {
		if x == 1 {
			i = k
		}
		if x == n {
			j = k
		}
	}
	k := 1
	if i > j {
		k = 2
	}
	return i + n - j - k
}
```

```ts
function semiOrderedPermutation(nums: number[]): number {
    const n = nums.length;
    const i = nums.indexOf(1);
    const j = nums.indexOf(n);
    const k = i < j ? 1 : 2;
    return i + n - j - k;
}
```

```rust
impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = 0;
        let mut n = nums.len();

        for idx in 0..n {
            if nums[idx] == 1 {
                i = idx;
            }
            if nums[idx] == (n as i32) {
                j = idx;
            }
        }

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let i = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == 1)
            .map(|(i, _)| i)
            .unwrap();
        let j = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == (n as i32))
            .map(|(i, _)| i)
            .unwrap();

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
