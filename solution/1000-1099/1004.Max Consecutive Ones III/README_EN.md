---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README_EN.md
rating: 1655
source: Weekly Contest 126 Q3
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

# [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii)

[中文文档](/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README.md)

## Description

<p>Given a binary array <code>nums</code> and an integer <code>k</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array if you can flip at most</em> <code>k</code> <code>0</code>&#39;s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> [1,1,1,0,0,<u><strong>1</strong>,1,1,1,1,<strong>1</strong></u>]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> [0,0,<u>1,1,<strong>1</strong>,<strong>1</strong>,1,1,1,<strong>1</strong>,1,1</u>,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

### Solution 1: Sliding Window

We define a sliding window, the number of $0$s in the window does not exceed $k$. The right boundary of the window keeps moving to the right. When the number of $0$s in the window exceeds $k$, the left boundary of the window moves to the right until the number of $0$s in the window does not exceed $k$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

Similar problems:

-   [487. Max Consecutive Ones II](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README_EN.md)
-   [2024. Maximize the Confusion of an Exam](https://github.com/doocs/leetcode/blob/main/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README_EN.md)

<!-- tabs:start -->

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        ans = 0
        cnt = j = 0
        for i, v in enumerate(nums):
            if v == 0:
                cnt += 1
            while cnt > k:
                if nums[j] == 0:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int j = 0, cnt = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > k) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int ans = 0;
        int cnt = 0, j = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > k) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func longestOnes(nums []int, k int) int {
	ans := 0
	j, cnt := 0, 0
	for i, v := range nums {
		if v == 0 {
			cnt++
		}
		for cnt > k {
			if nums[j] == 0 {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

```ts
function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, cnt, j] = [0, 0, 0];
    for (let i = 0; i < n; ++i) {
        cnt += nums[i] ^ 1;
        while (cnt > k) {
            cnt -= nums[j++] ^ 1;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Sliding Window (Optimized)

Below is the optimized version of the sliding window.

Maintain a monotonically variable-length window. This kind of window often appears in problems seeking the "maximum window": because we are seeking the "maximum", there is no need to shorten the window, so the code lacks the part to shorten the window; from another perspective, the K in this problem is the number of resources, once overdrawn, the window can no longer grow.

-   `l` is the left endpoint of the window, responsible for moving the starting position
-   `r` is the right endpoint of the window, responsible for expanding the window
-   `k` is the number of resources, each time a 0 needs to be replaced, `k` decreases by 1, and `r` moves to the right at the same time
-   `r++` will be executed every time, `l++` is only triggered when the resource `k < 0`, therefore the value of `r - l` will only increase monotonically (or remain unchanged)
-   When moving the left endpoint, if the current element is 0, it means that a resource can be released, `k` increases by 1

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        l = r = -1
        while r < len(nums) - 1:
            r += 1
            if nums[r] == 0:
                k -= 1
            if k < 0:
                l += 1
                if nums[l] == 0:
                    k += 1
        return r - l
```

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
}
```

```cpp
class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int l = 0, r = 0;
        while (r < nums.size()) {
            if (nums[r++] == 0) --k;
            if (k < 0 && nums[l++] == 0) ++k;
        }
        return r - l;
    }
};
```

```go
func longestOnes(nums []int, k int) int {
	l, r := -1, -1
	for r < len(nums)-1 {
		r++
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			l++
			if nums[l] == 0 {
				k++
			}
		}
	}
	return r - l
}
```

```ts
function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let l = 0;
    for (const num of nums) {
        if (num === 0) {
            k--;
        }
        if (k < 0 && nums[l++] === 0) {
            k++;
        }
    }
    return n - l;
}
```

```rust
impl Solution {
    pub fn longest_ones(nums: Vec<i32>, mut k: i32) -> i32 {
        let n = nums.len();
        let mut l = 0;
        for num in nums.iter() {
            if num == &0 {
                k -= 1;
            }
            if k < 0 {
                if nums[l] == 0 {
                    k += 1;
                }
                l += 1;
            }
        }
        (n - l) as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
