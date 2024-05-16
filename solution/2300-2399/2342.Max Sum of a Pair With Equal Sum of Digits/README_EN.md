---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README_EN.md
rating: 1308
source: Weekly Contest 302 Q2
tags:
    - Array
    - Hash Table
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2342. Max Sum of a Pair With Equal Sum of Digits](https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits)

[中文文档](/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers. You can choose two indices <code>i</code> and <code>j</code>, such that <code>i != j</code>, and the sum of digits of the number <code>nums[i]</code> is equal to that of <code>nums[j]</code>.</p>

<p>Return <em>the <strong>maximum</strong> value of </em><code>nums[i] + nums[j]</code><em> that you can obtain over all possible indices </em><code>i</code><em> and </em><code>j</code><em> that satisfy the conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [18,43,36,13,7]
<strong>Output:</strong> 54
<strong>Explanation:</strong> The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,12,19,14]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no two numbers that satisfy the conditions, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $d$ to record the maximum value corresponding to each digit sum, and initialize an answer variable $ans = -1$.

Next, we traverse the array $nums$. For each number $v$, we calculate its digit sum $x$. If $x$ exists in the hash table $d$, then we update the answer $ans = \max(ans, d[x] + v)$. Then update the hash table $d[x] = \max(d[x], v)$.

Finally, return the answer $ans$.

Since the maximum element in $nums$ is $10^9$, the maximum digit sum is $9 \times 9 = 81$. We can directly define an array $d$ of length $100$ to replace the hash table.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(D)$. Here, $n$ is the length of the array $nums$, and $M$ and $D$ are the maximum value of the elements in the array $nums$ and the maximum value of the digit sum, respectively. In this problem, $M \leq 10^9$, $D \leq 81$.

<!-- tabs:start -->

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        d = defaultdict(int)
        ans = -1
        for v in nums:
            x, y = 0, v
            while y:
                x += y % 10
                y //= 10
            if x in d:
                ans = max(ans, d[x] + v)
            d[x] = max(d[x], v)
        return ans
```

```java
class Solution {
    public int maximumSum(int[] nums) {
        int[] d = new int[100];
        int ans = -1;
        for (int v : nums) {
            int x = 0;
            for (int y = v; y > 0; y /= 10) {
                x += y % 10;
            }
            if (d[x] > 0) {
                ans = Math.max(ans, d[x] + v);
            }
            d[x] = Math.max(d[x], v);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumSum(vector<int>& nums) {
        int d[100]{};
        int ans = -1;
        for (int v : nums) {
            int x = 0;
            for (int y = v; y; y /= 10) {
                x += y % 10;
            }
            if (d[x]) {
                ans = max(ans, d[x] + v);
            }
            d[x] = max(d[x], v);
        }
        return ans;
    }
};
```

```go
func maximumSum(nums []int) int {
	d := [100]int{}
	ans := -1
	for _, v := range nums {
		x := 0
		for y := v; y > 0; y /= 10 {
			x += y % 10
		}
		if d[x] > 0 {
			ans = max(ans, d[x]+v)
		}
		d[x] = max(d[x], v)
	}
	return ans
}
```

```ts
function maximumSum(nums: number[]): number {
    const d: number[] = Array(100).fill(0);
    let ans = -1;
    for (const v of nums) {
        let x = 0;
        for (let y = v; y; y = (y / 10) | 0) {
            x += y % 10;
        }
        if (d[x]) {
            ans = Math.max(ans, d[x] + v);
        }
        d[x] = Math.max(d[x], v);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn maximum_sum(nums: Vec<i32>) -> i32 {
        let mut d = vec![0; 100];
        let mut ans = -1;

        for &v in nums.iter() {
            let mut x: usize = 0;
            let mut y = v;
            while y > 0 {
                x += (y % 10) as usize;
                y /= 10;
            }
            if d[x] > 0 {
                ans = ans.max(d[x] + v);
            }
            d[x] = d[x].max(v);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
