# [2934. Minimum Operations to Maximize Last Elements in Arrays](https://leetcode.com/problems/minimum-operations-to-maximize-last-elements-in-arrays)

[中文文档](/solution/2900-2999/2934.Minimum%20Operations%20to%20Maximize%20Last%20Elements%20in%20Arrays/README.md)

<!-- tags:Array,Enumeration -->

<!-- difficulty:Medium -->

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays, <code>nums1</code> and <code>nums2</code>, both having length <code>n</code>.</p>

<p>You are allowed to perform a series of <strong>operations</strong> (<strong>possibly none</strong>).</p>

<p>In an operation, you select an index <code>i</code> in the range <code>[0, n - 1]</code> and <strong>swap</strong> the values of <code>nums1[i]</code> and <code>nums2[i]</code>.</p>

<p>Your task is to find the <strong>minimum</strong> number of operations required to satisfy the following conditions:</p>

<ul>
	<li><code>nums1[n - 1]</code> is equal to the <strong>maximum value</strong> among all elements of <code>nums1</code>, i.e., <code>nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1])</code>.</li>
	<li><code>nums2[n - 1]</code> is equal to the <strong>maximum</strong> <strong>value</strong> among all elements of <code>nums2</code>, i.e., <code>nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1])</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>minimum</strong> number of operations needed to meet <strong>both</strong> conditions</em>, <em>or </em><code>-1</code><em> if it is <strong>impossible</strong> to satisfy both conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,7], nums2 = [4,5,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, an operation can be performed using index i = 2.
When nums1[2] and nums2[2] are swapped, nums1 becomes [1,2,3] and nums2 becomes [4,5,7].
Both conditions are now satisfied.
It can be shown that the minimum number of operations needed to be performed is 1.
So, the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,3,4,5,9], nums2 = [8,8,4,4,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, the following operations can be performed:
First operation using index i = 4.
When nums1[4] and nums2[4] are swapped, nums1 becomes [2,3,4,5,4], and nums2 becomes [8,8,4,4,9].
Another operation using index i = 3.
When nums1[3] and nums2[3] are swapped, nums1 becomes [2,3,4,4,4], and nums2 becomes [8,8,4,5,9].
Both conditions are now satisfied.
It can be shown that the minimum number of operations needed to be performed is 2.
So, the answer is 2.   
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,5,4], nums2 = [2,5,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> In this example, it is not possible to satisfy both conditions. 
So, the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length == nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Case Discussion + Greedy

We can discuss two cases:

1. Do not swap the values of $nums1[n - 1]$ and $nums2[n - 1]$
2. Swap the values of $nums1[n - 1]$ and $nums2[n - 1]$

For each case, we denote the last values of the arrays $nums1$ and $nums2$ as $x$ and $y$, respectively. Then we traverse the first $n - 1$ values of the arrays $nums1$ and $nums2$, and use a variable $cnt$ to record the number of swaps. If $nums1[i] \leq x$ and $nums2[i] \leq y$, then no swap is needed. Otherwise, if $nums1[i] \leq y$ and $nums2[i] \leq x$, then a swap is needed. If neither condition is met, return $-1$. Finally, return $cnt$.

We denote the number of swaps in the two cases as $a$ and $b$, respectively. If $a + b = -2$, then it is impossible to satisfy both conditions, so return $-1$. Otherwise, return $\min(a, b + 1)$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        def f(x: int, y: int) -> int:
            cnt = 0
            for a, b in zip(nums1[:-1], nums2[:-1]):
                if a <= x and b <= y:
                    continue
                if not (a <= y and b <= x):
                    return -1
                cnt += 1
            return cnt

        a, b = f(nums1[-1], nums2[-1]), f(nums2[-1], nums1[-1])
        return -1 if a + b == -2 else min(a, b + 1)
```

```java
class Solution {
    private int n;

    public int minOperations(int[] nums1, int[] nums2) {
        n = nums1.length;
        int a = f(nums1, nums2, nums1[n - 1], nums2[n - 1]);
        int b = f(nums1, nums2, nums2[n - 1], nums1[n - 1]);
        return a + b == -2 ? -1 : Math.min(a, b + 1);
    }

    private int f(int[] nums1, int[] nums2, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        auto f = [&](int x, int y) {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums1[i] <= x && nums2[i] <= y) {
                    continue;
                }
                if (!(nums1[i] <= y && nums2[i] <= x)) {
                    return -1;
                }
                ++cnt;
            }
            return cnt;
        };
        int a = f(nums1.back(), nums2.back());
        int b = f(nums2.back(), nums1.back());
        return a + b == -2 ? -1 : min(a, b + 1);
    }
};
```

```go
func minOperations(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := func(x, y int) (cnt int) {
		for i, a := range nums1[:n-1] {
			b := nums2[i]
			if a <= x && b <= y {
				continue
			}
			if !(a <= y && b <= x) {
				return -1
			}
			cnt++
		}
		return
	}
	a, b := f(nums1[n-1], nums2[n-1]), f(nums2[n-1], nums1[n-1])
	if a+b == -2 {
		return -1
	}
	return min(a, b+1)
}
```

```ts
function minOperations(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f = (x: number, y: number): number => {
        let cnt = 0;
        for (let i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    };
    const a = f(nums1.at(-1), nums2.at(-1));
    const b = f(nums2.at(-1), nums1.at(-1));
    return a + b === -2 ? -1 : Math.min(a, b + 1);
}
```

<!-- tabs:end -->

<!-- end -->
