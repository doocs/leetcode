---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3117.Minimum%20Sum%20of%20Values%20by%20Dividing%20Array/README_EN.md
rating: 2735
source: Weekly Contest 393 Q4
tags:
    - Bit Manipulation
    - Segment Tree
    - Queue
    - Array
    - Binary Search
    - Dynamic Programming
---

<!-- problem:start -->

# [3117. Minimum Sum of Values by Dividing Array](https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array)

[中文文档](/solution/3100-3199/3117.Minimum%20Sum%20of%20Values%20by%20Dividing%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays <code>nums</code> and <code>andValues</code> of length <code>n</code> and <code>m</code> respectively.</p>

<p>The <strong>value</strong> of an array is equal to the <strong>last</strong> element of that array.</p>

<p>You have to divide <code>nums</code> into <code>m</code> <strong>disjoint contiguous</strong> <span data-keyword="subarray-nonempty">subarrays</span> such that for the <code>i<sup>th</sup></code> subarray <code>[l<sub>i</sub>, r<sub>i</sub>]</code>, the bitwise <code>AND</code> of the subarray elements is equal to <code>andValues[i]</code>, in other words, <code>nums[l<sub>i</sub>] &amp; nums[l<sub>i</sub> + 1] &amp; ... &amp; nums[r<sub>i</sub>] == andValues[i]</code> for all <code>1 &lt;= i &lt;= m</code>, where <code>&amp;</code> represents the bitwise <code>AND</code> operator.</p>

<p>Return <em>the <strong>minimum</strong> possible sum of the <strong>values</strong> of the </em><code>m</code><em> subarrays </em><code>nums</code><em> is divided into</em>. <em>If it is not possible to divide </em><code>nums</code><em> into </em><code>m</code><em> subarrays satisfying these conditions, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3,3,2], andValues = [0,3,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible way to divide <code>nums</code> is:</p>

<ol>
	<li><code>[1,4]</code> as <code>1 &amp; 4 == 0</code>.</li>
	<li><code>[3]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
	<li><code>[3]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
	<li><code>[2]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
</ol>

<p>The sum of the values for these subarrays is <code>4 + 3 + 3 + 2 = 12</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,7,7,7,5], andValues = [0,7,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<p>There are three ways to divide <code>nums</code>:</p>

<ol>
	<li><code>[[2,3,5],[7,7,7],[5]]</code> with the sum of the values <code>5 + 7 + 5 == 17</code>.</li>
	<li><code>[[2,3,5,7],[7,7],[5]]</code> with the sum of the values <code>7 + 7 + 5 == 19</code>.</li>
	<li><code>[[2,3,5,7,7],[7],[5]]</code> with the sum of the values <code>7 + 7 + 5 == 19</code>.</li>
</ol>

<p>The minimum possible sum of the values is <code>17</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], andValues = [2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The bitwise <code>AND</code> of the entire array <code>nums</code> is <code>0</code>. As there is no possible way to divide <code>nums</code> into a single subarray to have the bitwise <code>AND</code> of elements <code>2</code>, return <code>-1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m == andValues.length &lt;= min(n, 10)</code></li>
	<li><code>1 &lt;= nums[i] &lt; 10<sup>5</sup></code></li>
	<li><code>0 &lt;= andValues[j] &lt; 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $dfs(i, j, a)$, which represents the possible minimum sum of subarray values that can be obtained starting from the $i$-th element, with $j$ subarrays already divided, and the bitwise AND result of the current subarray to be divided is $a$. The answer is $dfs(0, 0, -1)$.

The execution process of the function $dfs(i, j, a)$ is as follows:

-   If $n - i < m - j$, it means that the remaining elements are not enough to divide into $m - j$ subarrays, return $+\infty$.
-   If $j = m$, it means that $m$ subarrays have been divided. At this time, check whether $i = n$ holds. If it holds, return $0$, otherwise return $+\infty$.
-   Otherwise, we perform a bitwise AND operation on $a$ and $nums[i]$ to get a new $a$. If $a < andValues[j]$, it means that the bitwise AND result of the current subarray to be divided does not meet the requirements, return $+\infty$. Otherwise, we have two choices:
    -   Do not divide the current element, i.e., $dfs(i + 1, j, a)$.
    -   Divide the current element, i.e., $dfs(i + 1, j + 1, -1) + nums[i]$.
-   Return the minimum of the above two choices.

To avoid repeated calculations, we use the method of memoization search and store the result of $dfs(i, j, a)$ in a hash table.

The time complexity is $O(n \times m \times \log M)$, and the space complexity is $O(n \times m \times \log M)$. Where $n$ and $m$ are the lengths of the arrays $nums$ and $andValues$ respectively; and $M$ is the maximum value in the array $nums$, in this problem $M \leq 10^5$.

<!-- tabs:start -->

```python
class Solution:
    def minimumValueSum(self, nums: List[int], andValues: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, a: int) -> int:
            if n - i < m - j:
                return inf
            if j == m:
                return 0 if i == n else inf
            a &= nums[i]
            if a < andValues[j]:
                return inf
            ans = dfs(i + 1, j, a)
            if a == andValues[j]:
                ans = min(ans, dfs(i + 1, j + 1, -1) + nums[i])
            return ans

        n, m = len(nums), len(andValues)
        ans = dfs(0, 0, -1)
        return ans if ans < inf else -1
```

```java
class Solution {
    private int[] nums;
    private int[] andValues;
    private final int inf = 1 << 29;
    private Map<Long, Integer> f = new HashMap<>();

    public int minimumValueSum(int[] nums, int[] andValues) {
        this.nums = nums;
        this.andValues = andValues;
        int ans = dfs(0, 0, -1);
        return ans >= inf ? -1 : ans;
    }

    private int dfs(int i, int j, int a) {
        if (nums.length - i < andValues.length - j) {
            return inf;
        }
        if (j == andValues.length) {
            return i == nums.length ? 0 : inf;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return inf;
        }
        long key = (long) i << 36 | (long) j << 32 | a;
        if (f.containsKey(key)) {
            return f.get(key);
        }

        int ans = dfs(i + 1, j, a);
        if (a == andValues[j]) {
            ans = Math.min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        f.put(key, ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumValueSum(vector<int>& nums, vector<int>& andValues) {
        this->nums = nums;
        this->andValues = andValues;
        n = nums.size();
        m = andValues.size();
        int ans = dfs(0, 0, -1);
        return ans >= inf ? -1 : ans;
    }

private:
    vector<int> nums;
    vector<int> andValues;
    int n;
    int m;
    const int inf = 1 << 29;
    unordered_map<long long, int> f;

    int dfs(int i, int j, int a) {
        if (n - i < m - j) {
            return inf;
        }
        if (j == m) {
            return i == n ? 0 : inf;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return inf;
        }
        long long key = (long long) i << 36 | (long long) j << 32 | a;
        if (f.contains(key)) {
            return f[key];
        }
        int ans = dfs(i + 1, j, a);
        if (a == andValues[j]) {
            ans = min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        return f[key] = ans;
    }
};
```

```go
func minimumValueSum(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	f := map[int]int{}
	const inf int = 1 << 29
	var dfs func(i, j, a int) int
	dfs = func(i, j, a int) int {
		if n-i < m-j {
			return inf
		}
		if j == m {
			if i == n {
				return 0
			}
			return inf
		}
		a &= nums[i]
		if a < andValues[j] {
			return inf
		}
		key := i<<36 | j<<32 | a
		if v, ok := f[key]; ok {
			return v
		}
		ans := dfs(i+1, j, a)
		if a == andValues[j] {
			ans = min(ans, dfs(i+1, j+1, -1)+nums[i])
		}
		f[key] = ans
		return ans
	}
	if ans := dfs(0, 0, -1); ans < inf {
		return ans
	}
	return -1
}
```

```ts
function minimumValueSum(nums: number[], andValues: number[]): number {
    const [n, m] = [nums.length, andValues.length];
    const f: Map<bigint, number> = new Map();
    const dfs = (i: number, j: number, a: number): number => {
        if (n - i < m - j) {
            return Infinity;
        }
        if (j === m) {
            return i === n ? 0 : Infinity;
        }
        a &= nums[i];
        if (a < andValues[j]) {
            return Infinity;
        }
        const key = (BigInt(i) << 36n) | (BigInt(j) << 32n) | BigInt(a);
        if (f.has(key)) {
            return f.get(key)!;
        }
        let ans = dfs(i + 1, j, a);
        if (a === andValues[j]) {
            ans = Math.min(ans, dfs(i + 1, j + 1, -1) + nums[i]);
        }
        f.set(key, ans);
        return ans;
    };
    const ans = dfs(0, 0, -1);
    return ans >= Infinity ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
