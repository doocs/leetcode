---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2968.Apply%20Operations%20to%20Maximize%20Frequency%20Score/README_EN.md
rating: 2444
source: Weekly Contest 376 Q4
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [2968. Apply Operations to Maximize Frequency Score](https://leetcode.com/problems/apply-operations-to-maximize-frequency-score)

[中文文档](/solution/2900-2999/2968.Apply%20Operations%20to%20Maximize%20Frequency%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You can perform the following operation on the array <strong>at most</strong> <code>k</code> times:</p>

<ul>
	<li>Choose any index <code>i</code> from the array and <strong>increase</strong> or <strong>decrease</strong> <code>nums[i]</code> by <code>1</code>.</li>
</ul>

<p>The score of the final array is the <strong>frequency</strong> of the most frequent element in the array.</p>

<p>Return <em>the <strong>maximum</strong> score you can achieve</em>.</p>

<p>The frequency of an element is the number of occurences of that element in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,6,4], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can do the following operations on the array:
- Choose i = 0, and increase the value of nums[0] by 1. The resulting array is [2,2,6,4].
- Choose i = 3, and decrease the value of nums[3] by 1. The resulting array is [2,2,6,3].
- Choose i = 3, and decrease the value of nums[3] by 1. The resulting array is [2,2,6,2].
The element 2 is the most frequent in the final array so our score is 3.
It can be shown that we cannot achieve a better score.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,4,2,4], k = 0
<strong>Output:</strong> 3
<strong>Explanation:</strong> We cannot apply any operations so our score will be the frequency of the most frequent element in the original array, which is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>14</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Prefix Sum + Binary Search

The problem asks for the maximum frequency of the mode we can get after performing at most $k$ operations. If we sort the array $nums$ in ascending order, it would be best to turn a continuous segment of numbers into the same number, which can reduce the number of operations and increase the frequency of the mode.

Therefore, we might as well sort the array $nums$ first.

Next, we analyze that if a frequency $x$ is feasible, then for any $y \le x$, the frequency $y$ is also feasible, which shows monotonicity. Therefore, we can use binary search to find the maximum feasible frequency.

We binary search the frequency, define the left boundary of the binary search as $l = 0$, and the right boundary as $r = n$, where $n$ is the length of the array. In each binary search process, we take the middle value $mid = \lfloor \frac{l + r + 1}{2} \rfloor$, and then determine whether there exists a continuous subarray of length $mid$ in $nums$, such that all elements in this subarray become the median of this subarray, and the number of operations does not exceed $k$. If it exists, then we update the left boundary $l$ to $mid$, otherwise we update the right boundary $r$ to $mid - 1$.

To determine whether such a subarray exists, we can use prefix sum. We first define two pointers $i$ and $j$, initially $i = 0$, $j = i + mid$. Then all elements from $nums[i]$ to $nums[j - 1]$ are changed to $nums[(i + j) / 2]$, and the number of operations required is $left + right$, where:

$$
\begin{aligned}
\textit{left} &= \sum_{k = i}^{(i + j) / 2 - 1} (nums[(i + j) / 2] - nums[k]) \\
&= ((i + j) / 2 - i) \times nums[(i + j) / 2] - \sum_{k = i}^{(i + j) / 2 - 1} nums[k]
\end{aligned}
$$

$$
\begin{aligned}
\textit{right} &= \sum_{k = (i + j) / 2 + 1}^{j} (nums[k] - nums[(i + j) / 2]) \\
&= \sum_{k = (i + j) / 2 + 1}^{j} nums[k] - (j - (i + j) / 2) \times nums[(i + j) / 2]
\end{aligned}
$$

We can use the prefix sum array $s$ to calculate $\sum_{k = i}^{j} nums[k]$, so as to calculate $left$ and $right$ in $O(1)$ time.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            ok = False
            for i in range(n - mid + 1):
                j = i + mid
                x = nums[(i + j) // 2]
                left = ((i + j) // 2 - i) * x - (s[(i + j) // 2] - s[i])
                right = (s[j] - s[(i + j) // 2]) - ((j - (i + j) // 2) * x)
                if left + right <= k:
                    ok = True
                    break
            if ok:
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            boolean ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long left = ((i + j) / 2 - i) * (long) x - (s[(i + j) / 2] - s[i]);
                long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long) x);
                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, long long k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            bool ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long long left = ((i + j) / 2 - i) * (long long) x - (s[(i + j) / 2] - s[i]);
                long long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long long) x);

                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
};
```

#### Go

```go
func maxFrequencyScore(nums []int, k int64) int {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int64, n+1)
	for i := 1; i <= n; i++ {
		s[i] = s[i-1] + int64(nums[i-1])
	}

	l, r := 0, n
	for l < r {
		mid := (l + r + 1) >> 1
		ok := false

		for i := 0; i <= n-mid; i++ {
			j := i + mid
			x := int64(nums[(i+j)/2])
			left := (int64((i+j)/2-i) * x) - (s[(i+j)/2] - s[i])
			right := (s[j] - s[(i+j)/2]) - (int64(j-(i+j)/2) * x)

			if left+right <= k {
				ok = true
				break
			}
		}

		if ok {
			l = mid
		} else {
			r = mid - 1
		}
	}

	return l
}
```

#### TypeScript

```ts
function maxFrequencyScore(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let l: number = 0;
    let r: number = n;
    while (l < r) {
        const mid: number = (l + r + 1) >> 1;
        let ok: boolean = false;
        for (let i = 0; i <= n - mid; i++) {
            const j = i + mid;
            const x = nums[Math.floor((i + j) / 2)];
            const left = (Math.floor((i + j) / 2) - i) * x - (s[Math.floor((i + j) / 2)] - s[i]);
            const right = s[j] - s[Math.floor((i + j) / 2)] - (j - Math.floor((i + j) / 2)) * x;
            if (left + right <= k) {
                ok = true;
                break;
            }
        }
        if (ok) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
