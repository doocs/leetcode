---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3937.Minimum%20Operations%20to%20Make%20Array%20Modulo%20Alternating%20I/README_EN.md
---

<!-- problem:start -->

# [3937. Minimum Operations to Make Array Modulo Alternating I](https://leetcode.com/problems/minimum-operations-to-make-array-modulo-alternating-i)

[中文文档](/solution/3900-3999/3937.Minimum%20Operations%20to%20Make%20Array%20Modulo%20Alternating%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>In one operation, you can <strong>increase</strong> or <strong>decrease</strong> any element of <code>nums</code> by 1.</p>

<p>An array is called <strong>modulo alternating</strong> if there exist two <strong>distinct</strong> integers <code>x</code> and <code>y</code> (<code>0 &lt;= x, y &lt; k</code>) such that:</p>

<ul>
	<li>For every <strong>even</strong> index <code>i</code>, <code>nums[i] % k == x</code></li>
	<li>For every <strong>odd</strong> index <code>i</code>, <code>nums[i] % k == y</code></li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make <code>nums</code> <strong>modulo alternating</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,8], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Let&#39;s choose <code>x = 1</code> for even indices and <code>y = 2</code> for odd indices.</li>
	<li>Perform the following operations:
	<ul>
		<li>Increment <code>nums[1] = 4</code> by 1, giving <code>nums = [1, 5, 2, 8]</code>.</li>
		<li>Decrement <code>nums[2] = 2</code> by 1, giving <code>nums = [1, 5, 1, 8]</code>.</li>
	</ul>
	</li>
	<li>Now, for even indices, <code>nums[i] % k = 1</code>, and for odd indices, <code>nums[i] % k = 2</code>.</li>
	<li>Thus, the total number of operations required is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Incrementing <code>nums[1]</code> by 1 gives <code>nums = [1, 2, 1]</code>, which satisfies the condition with <code>x = 1</code> and <code>y = 2</code>.</li>
	<li>Thus, the total number of operations required is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate the target value $x$ for even indices and the target value $y$ for odd indices, where $0 \leq x, y < k$ and $x \neq y$. For each element, we calculate the number of operations required to change it to the target value, and accumulate the total number of operations. Finally, we return the minimum value among all enumeration results.

The time complexity is $O(n \times k^2)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: list[int], k: int) -> int:
        for i, v in enumerate(nums):
            nums[i] = v % k
        ans = inf
        for x in range(k):
            for y in range(k):
                if x != y:
                    cnt = 0
                    for i, v in enumerate(nums):
                        target = x if i % 2 == 0 else y
                        diff = abs(target - v)
                        cnt += min(diff, k - diff)
                    ans = min(ans, cnt)
        return ans
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] %= k;
        }

        int ans = Integer.MAX_VALUE;

        for (int x = 0; x < k; ++x) {
            for (int y = 0; y < k; ++y) {
                if (x != y) {
                    int cnt = 0;

                    for (int i = 0; i < n; ++i) {
                        int target = (i & 1) == 0 ? x : y;
                        int diff = Math.abs(target - nums[i]);
                        cnt += Math.min(diff, k - diff);
                    }

                    ans = Math.min(ans, cnt);
                }
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
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();

        for (int& v : nums) {
            v %= k;
        }

        int ans = INT_MAX;

        for (int x = 0; x < k; ++x) {
            for (int y = 0; y < k; ++y) {
                if (x != y) {
                    int cnt = 0;

                    for (int i = 0; i < n; ++i) {
                        int target = (i & 1) ? y : x;
                        int diff = abs(target - nums[i]);
                        cnt += min(diff, k - diff);
                    }

                    ans = min(ans, cnt);
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int {
	for i, v := range nums {
		nums[i] = v % k
	}

	ans := int(^uint(0) >> 1)

	for x := 0; x < k; x++ {
		for y := 0; y < k; y++ {
			if x != y {
				cnt := 0

				for i, v := range nums {
					target := x
					if i&1 == 1 {
						target = y
					}

					diff := abs(target - v)
					cnt += min(diff, k-diff)
				}

				ans = min(ans, cnt)
			}
		}
	}

	return ans
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
function minOperations(nums: number[], k: number): number {
    const n = nums.length;

    for (let i = 0; i < n; ++i) {
        nums[i] %= k;
    }

    let ans = Infinity;

    for (let x = 0; x < k; ++x) {
        for (let y = 0; y < k; ++y) {
            if (x !== y) {
                let cnt = 0;

                for (let i = 0; i < n; ++i) {
                    const target = (i & 1) === 0 ? x : y;
                    const diff = Math.abs(target - nums[i]);
                    cnt += Math.min(diff, k - diff);
                }

                ans = Math.min(ans, cnt);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
