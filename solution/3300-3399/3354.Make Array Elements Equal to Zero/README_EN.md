---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3354.Make%20Array%20Elements%20Equal%20to%20Zero/README_EN.md
rating: 1397
source: Weekly Contest 424 Q1
tags:
    - Array
    - Prefix Sum
    - Simulation
---

<!-- problem:start -->

# [3354. Make Array Elements Equal to Zero](https://leetcode.com/problems/make-array-elements-equal-to-zero)

[中文文档](/solution/3300-3399/3354.Make%20Array%20Elements%20Equal%20to%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Start by selecting a starting position <code>curr</code> such that <code>nums[curr] == 0</code>, and choose a movement <strong>direction</strong> of&nbsp;either left or right.</p>

<p>After that, you repeat the following process:</p>

<ul>
	<li>If <code>curr</code> is out of the range <code>[0, n - 1]</code>, this process ends.</li>
	<li>If <code>nums[curr] == 0</code>, move in the current direction by <strong>incrementing</strong> <code>curr</code> if you are moving right, or <strong>decrementing</strong> <code>curr</code> if you are moving left.</li>
	<li>Else if <code>nums[curr] &gt; 0</code>:
	<ul>
		<li>Decrement <code>nums[curr]</code> by 1.</li>
		<li><strong>Reverse</strong>&nbsp;your movement direction (left becomes right and vice versa).</li>
		<li>Take a step in your new direction.</li>
	</ul>
	</li>
</ul>

<p>A selection of the initial position <code>curr</code> and movement direction is considered <strong>valid</strong> if every element in <code>nums</code> becomes 0 by the end of the process.</p>

<p>Return the number of possible <strong>valid</strong> selections.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2,0,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible valid selections are the following:</p>

<ul>
	<li>Choose <code>curr = 3</code>, and a movement direction to the left.

    <ul>
    	<li><code>[1,0,2,<strong><u>0</u></strong>,3] -&gt; [1,0,<strong><u>2</u></strong>,0,3] -&gt; [1,0,1,<strong><u>0</u></strong>,3] -&gt; [1,0,1,0,<strong><u>3</u></strong>] -&gt; [1,0,1,<strong><u>0</u></strong>,2] -&gt; [1,0,<strong><u>1</u></strong>,0,2] -&gt; [1,0,0,<strong><u>0</u></strong>,2] -&gt; [1,0,0,0,<strong><u>2</u></strong>] -&gt; [1,0,0,<strong><u>0</u></strong>,1] -&gt; [1,0,<strong><u>0</u></strong>,0,1] -&gt; [1,<strong><u>0</u></strong>,0,0,1] -&gt; [<strong><u>1</u></strong>,0,0,0,1] -&gt; [0,<strong><u>0</u></strong>,0,0,1] -&gt; [0,0,<strong><u>0</u></strong>,0,1] -&gt; [0,0,0,<strong><u>0</u></strong>,1] -&gt; [0,0,0,0,<strong><u>1</u></strong>] -&gt; [0,0,0,0,0]</code>.</li>
    </ul>
    </li>
    <li>Choose <code>curr = 3</code>, and a movement direction to the right.
    <ul>
    	<li><code>[1,0,2,<strong><u>0</u></strong>,3] -&gt; [1,0,2,0,<strong><u>3</u></strong>] -&gt; [1,0,2,<strong><u>0</u></strong>,2] -&gt; [1,0,<strong><u>2</u></strong>,0,2] -&gt; [1,0,1,<strong><u>0</u></strong>,2] -&gt; [1,0,1,0,<strong><u>2</u></strong>] -&gt; [1,0,1,<strong><u>0</u></strong>,1] -&gt; [1,0,<strong><u>1</u></strong>,0,1] -&gt; [1,0,0,<strong><u>0</u></strong>,1] -&gt; [1,0,0,0,<strong><u>1</u></strong>] -&gt; [1,0,0,<strong><u>0</u></strong>,0] -&gt; [1,0,<strong><u>0</u></strong>,0,0] -&gt; [1,<strong><u>0</u></strong>,0,0,0] -&gt; [<strong><u>1</u></strong>,0,0,0,0] -&gt; [0,0,0,0,0].</code></li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,4,0,4,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no possible valid selections.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li>There is at least one element <code>i</code> where <code>nums[i] == 0</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Prefix Sum

Suppose we initially move to the left and encounter a non-zero element. In that case, we need to decrement this element by one, then change the direction of movement and continue moving.

Therefore, we can maintain the sum of elements to the left of each zero-value element as $l$, and the sum of elements to the right as $s - l$. If $l = s - l$, meaning the sum of elements on the left equals the sum of elements on the right, we can choose the current zero-value element and move either left or right, adding $2$ to the answer. If $|l - (s - l)| = 1$, and the sum of elements on the left is greater, we can choose the current zero-value element and move left, adding $1$ to the answer. If the sum of elements on the right is greater, we can choose the current zero-value element and move right, adding $1$ to the answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countValidSelections(self, nums: List[int]) -> int:
        s = sum(nums)
        ans = l = 0
        for x in nums:
            if x:
                l += x
            elif l * 2 == s:
                ans += 2
            elif abs(l * 2 - s) == 1:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countValidSelections(int[] nums) {
        int s = Arrays.stream(nums).sum();
        int ans = 0, l = 0;
        for (int x : nums) {
            if (x != 0) {
                l += x;
            } else if (l * 2 == s) {
                ans += 2;
            } else if (Math.abs(l * 2 - s) <= 1) {
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
    int countValidSelections(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        int ans = 0, l = 0;
        for (int x : nums) {
            if (x) {
                l += x;
            } else if (l * 2 == s) {
                ans += 2;
            } else if (abs(l * 2 - s) <= 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countValidSelections(nums []int) (ans int) {
	l, s := 0, 0
	for _, x := range nums {
		s += x
	}
	for _, x := range nums {
		if x != 0 {
			l += x
		} else if l*2 == s {
			ans += 2
		} else if abs(l*2-s) <= 1 {
			ans++
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function countValidSelections(nums: number[]): number {
    const s = nums.reduce((acc, x) => acc + x, 0);
    let [ans, l] = [0, 0];
    for (const x of nums) {
        if (x) {
            l += x;
        } else if (l * 2 === s) {
            ans += 2;
        } else if (Math.abs(l * 2 - s) <= 1) {
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_valid_selections(nums: Vec<i32>) -> i32 {
        let s: i32 = nums.iter().sum();
        let mut ans = 0;
        let mut l = 0;
        for &x in &nums {
            if x != 0 {
                l += x;
            } else if l * 2 == s {
                ans += 2;
            } else if (l * 2 - s).abs() <= 1 {
                ans += 1;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int CountValidSelections(int[] nums) {
        int s = nums.Sum();
        int ans = 0, l = 0;
        foreach (int x in nums) {
            if (x != 0) {
                l += x;
            } else if (l * 2 == s) {
                ans += 2;
            } else if (Math.Abs(l * 2 - s) <= 1) {
                ans += 1;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
