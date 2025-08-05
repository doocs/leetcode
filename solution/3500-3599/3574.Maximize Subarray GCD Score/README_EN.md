---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3574.Maximize%20Subarray%20GCD%20Score/README_EN.md
rating: 2257
source: Biweekly Contest 158 Q3
tags:
    - Array
    - Math
    - Enumeration
    - Number Theory
---

<!-- problem:start -->

# [3574. Maximize Subarray GCD Score](https://leetcode.com/problems/maximize-subarray-gcd-score)

[中文文档](/solution/3500-3599/3574.Maximize%20Subarray%20GCD%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an array of positive integers <code>nums</code> and an integer <code>k</code>.</p>

<p>You may perform at most <code>k</code> operations. In each operation, you can choose one element in the array and <strong>double</strong> its value. Each element can be doubled <strong>at most</strong> once.</p>

<p>The <strong>score</strong> of a contiguous <strong><span data-keyword="subarray">subarray</span></strong> is defined as the <strong>product</strong> of its length and the <em>greatest common divisor (GCD)</em> of all its elements.</p>

<p>Your task is to return the <strong>maximum</strong> <strong>score</strong> that can be achieved by selecting a contiguous subarray from the modified array.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>greatest common divisor (GCD)</strong> of an array is the largest integer that evenly divides all the array elements.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Double <code>nums[0]</code> to 4 using one operation. The modified array becomes <code>[4, 4]</code>.</li>
	<li>The GCD of the subarray <code>[4, 4]</code> is 4, and the length is 2.</li>
	<li>Thus, the maximum possible score is <code>2 &times; 4 = 8</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,7], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Double <code>nums[2]</code> to 14 using one operation. The modified array becomes <code>[3, 5, 14]</code>.</li>
	<li>The GCD of the subarray <code>[14]</code> is 14, and the length is 1.</li>
	<li>Thus, the maximum possible score is <code>1 &times; 14 = 14</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray <code>[5, 5, 5]</code> has a GCD of 5, and its length is 3.</li>
	<li>Since doubling any element doesn&#39;t improve the score, the maximum score is <code>3 &times; 5 = 15</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Mathematics

We notice that the length of the array in this problem is $n \leq 1500$, so we can enumerate all subarrays. For each subarray, calculate its GCD score and find the maximum value as the answer.

Since each number can be doubled at most once, the GCD of a subarray can be multiplied by at most $2$. Therefore, we need to count the minimum number of factors of $2$ among all numbers in the subarray, as well as the number of times this minimum occurs. If the count is greater than $k$, the GCD score is the GCD itself; otherwise, the GCD score is the GCD multiplied by $2$.

Thus, we can preprocess the number of factors of $2$ for each number, and when enumerating subarrays, maintain the current subarray's GCD, the minimum number of factors of $2$, and the number of times this minimum occurs.

The time complexity is $O(n^2 \times \log n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxGCDScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = [0] * n
        for i, x in enumerate(nums):
            while x % 2 == 0:
                cnt[i] += 1
                x //= 2
        ans = 0
        for l in range(n):
            g = 0
            mi = inf
            t = 0
            for r in range(l, n):
                g = gcd(g, nums[r])
                if cnt[r] < mi:
                    mi = cnt[r]
                    t = 1
                elif cnt[r] == mi:
                    t += 1
                ans = max(ans, (g if t > k else g * 2) * (r - l + 1))
        return ans
```

#### Java

```java
class Solution {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int x = nums[i]; x % 2 == 0; x /= 2) {
                ++cnt[i];
            }
        }
        long ans = 0;
        for (int l = 0; l < n; ++l) {
            int g = 0;
            int mi = 1 << 30;
            int t = 0;
            for (int r = l; r < n; ++r) {
                g = gcd(g, nums[r]);
                if (cnt[r] < mi) {
                    mi = cnt[r];
                    t = 1;
                } else if (cnt[r] == mi) {
                    ++t;
                }
                ans = Math.max(ans, (r - l + 1L) * (t > k ? g : g * 2));
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxGCDScore(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> cnt(n);
        for (int i = 0; i < n; ++i) {
            for (int x = nums[i]; x % 2 == 0; x /= 2) {
                ++cnt[i];
            }
        }

        long long ans = 0;
        for (int l = 0; l < n; ++l) {
            int g = 0;
            int mi = INT32_MAX;
            int t = 0;
            for (int r = l; r < n; ++r) {
                g = gcd(g, nums[r]);
                if (cnt[r] < mi) {
                    mi = cnt[r];
                    t = 1;
                } else if (cnt[r] == mi) {
                    ++t;
                }
                long long score = static_cast<long long>(r - l + 1) * (t > k ? g : g * 2);
                ans = max(ans, score);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxGCDScore(nums []int, k int) int64 {
	n := len(nums)
	cnt := make([]int, n)
	for i, x := range nums {
		for x%2 == 0 {
			cnt[i]++
			x /= 2
		}
	}

	ans := 0
	for l := 0; l < n; l++ {
		g := 0
		mi := math.MaxInt32
		t := 0
		for r := l; r < n; r++ {
			g = gcd(g, nums[r])
			if cnt[r] < mi {
				mi = cnt[r]
				t = 1
			} else if cnt[r] == mi {
				t++
			}
			length := r - l + 1
			score := g * length
			if t <= k {
				score *= 2
			}
			ans = max(ans, score)
		}
	}

	return int64(ans)
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
```

#### TypeScript

```ts
function maxGCDScore(nums: number[], k: number): number {
    const n = nums.length;
    const cnt: number[] = Array(n).fill(0);

    for (let i = 0; i < n; ++i) {
        let x = nums[i];
        while (x % 2 === 0) {
            cnt[i]++;
            x /= 2;
        }
    }

    let ans = 0;
    for (let l = 0; l < n; ++l) {
        let g = 0;
        let mi = Number.MAX_SAFE_INTEGER;
        let t = 0;
        for (let r = l; r < n; ++r) {
            g = gcd(g, nums[r]);
            if (cnt[r] < mi) {
                mi = cnt[r];
                t = 1;
            } else if (cnt[r] === mi) {
                t++;
            }
            const len = r - l + 1;
            const score = (t > k ? g : g * 2) * len;
            ans = Math.max(ans, score);
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        const temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
