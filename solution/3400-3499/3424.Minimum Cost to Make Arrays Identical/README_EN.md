---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3424.Minimum%20Cost%20to%20Make%20Arrays%20Identical/README_EN.md
rating: 1502
source: Biweekly Contest 148 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3424. Minimum Cost to Make Arrays Identical](https://leetcode.com/problems/minimum-cost-to-make-arrays-identical)

[中文文档](/solution/3400-3499/3424.Minimum%20Cost%20to%20Make%20Arrays%20Identical/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>arr</code> and <code>brr</code> of length <code>n</code>, and an integer <code>k</code>. You can perform the following operations on <code>arr</code> <em>any</em> number of times:</p>

<ul>
	<li>Split <code>arr</code> into <em>any</em> number of <strong>contiguous</strong> <span data-keyword="subarray-nonempty">subarrays</span> and rearrange these subarrays in <em>any order</em>. This operation has a fixed cost of <code>k</code>.</li>
	<li>
	<p>Choose any element in <code>arr</code> and add or subtract a positive integer <code>x</code> to it. The cost of this operation is <code>x</code>.</p>
	</li>
</ul>

<p>Return the <strong>minimum </strong>total cost to make <code>arr</code> <strong>equal</strong> to <code>brr</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arr = [-7,9,5], brr = [7,-2,-5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>arr</code> into two contiguous subarrays: <code>[-7]</code> and <code>[9, 5]</code> and rearrange them as <code>[9, 5, -7]</code>, with a cost of 2.</li>
	<li>Subtract 2 from element <code>arr[0]</code>. The array becomes <code>[7, 5, -7]</code>. The cost of this operation is 2.</li>
	<li>Subtract 7 from element <code>arr[1]</code>. The array becomes <code>[7, -2, -7]</code>. The cost of this operation is 7.</li>
	<li>Add 2 to element <code>arr[2]</code>. The array becomes <code>[7, -2, -5]</code>. The cost of this operation is 2.</li>
</ul>

<p>The total cost to make the arrays equal is <code>2 + 2 + 7 + 2 = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">arr = [2,1], brr = [2,1], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since the arrays are already equal, no operations are needed, and the total cost is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length == brr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>10</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= brr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

If splitting the array is not allowed, we can directly calculate the sum of absolute differences between the two arrays as the total cost $c_1$. If splitting is allowed, we can divide the array $\textit{arr}$ into $n$ subarrays of length 1, then rearrange them in any order, and compare with array $\textit{brr}$, calculating the sum of absolute differences as the total cost $c_2$. To minimize $c_2$, we can sort both arrays and then calculate the sum of absolute differences. The final result is $\min(c_1, c_2 + k)$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, arr: List[int], brr: List[int], k: int) -> int:
        c1 = sum(abs(a - b) for a, b in zip(arr, brr))
        arr.sort()
        brr.sort()
        c2 = k + sum(abs(a - b) for a, b in zip(arr, brr))
        return min(c1, c2)
```

#### Java

```java
class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        long c1 = calc(arr, brr);
        Arrays.sort(arr);
        Arrays.sort(brr);
        long c2 = calc(arr, brr) + k;
        return Math.min(c1, c2);
    }

    private long calc(int[] arr, int[] brr) {
        long ans = 0;
        for (int i = 0; i < arr.length; ++i) {
            ans += Math.abs(arr[i] - brr[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(vector<int>& arr, vector<int>& brr, long long k) {
        auto calc = [&](vector<int>& arr, vector<int>& brr) {
            long long ans = 0;
            for (int i = 0; i < arr.size(); ++i) {
                ans += abs(arr[i] - brr[i]);
            }
            return ans;
        };
        long long c1 = calc(arr, brr);
        ranges::sort(arr);
        ranges::sort(brr);
        long long c2 = calc(arr, brr) + k;
        return min(c1, c2);
    }
};
```

#### Go

```go
func minCost(arr []int, brr []int, k int64) int64 {
	calc := func(a, b []int) (ans int64) {
		for i := range a {
			ans += int64(abs(a[i] - b[i]))
		}
		return
	}
	c1 := calc(arr, brr)
	sort.Ints(arr)
	sort.Ints(brr)
	c2 := calc(arr, brr) + k
	return min(c1, c2)
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
function minCost(arr: number[], brr: number[], k: number): number {
    const calc = (a: number[], b: number[]) => {
        let ans = 0;
        for (let i = 0; i < a.length; ++i) {
            ans += Math.abs(a[i] - b[i]);
        }
        return ans;
    };
    const c1 = calc(arr, brr);
    arr.sort((a, b) => a - b);
    brr.sort((a, b) => a - b);
    const c2 = calc(arr, brr) + k;
    return Math.min(c1, c2);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost(mut arr: Vec<i32>, mut brr: Vec<i32>, k: i64) -> i64 {
        let c1: i64 = arr.iter()
            .zip(&brr)
            .map(|(a, b)| (*a - *b).abs() as i64)
            .sum();

        arr.sort_unstable();
        brr.sort_unstable();

        let c2: i64 = k + arr.iter()
            .zip(&brr)
            .map(|(a, b)| (*a - *b).abs() as i64)
            .sum::<i64>();

        c1.min(c2)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
