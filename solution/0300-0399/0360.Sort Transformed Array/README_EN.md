---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0360.Sort%20Transformed%20Array/README_EN.md
tags:
    - Array
    - Math
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [360. Sort Transformed Array 🔒](https://leetcode.com/problems/sort-transformed-array)

[中文文档](/solution/0300-0399/0360.Sort%20Transformed%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>sorted</strong> integer array <code>nums</code> and three integers <code>a</code>, <code>b</code> and <code>c</code>, apply a quadratic function of the form <code>f(x) = ax<sup>2</sup> + bx + c</code> to each element <code>nums[i]</code> in the array, and return <em>the array in a sorted order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [-4,-2,2,4], a = 1, b = 3, c = 5
<strong>Output:</strong> [3,9,15,33]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [-4,-2,2,4], a = -1, b = 3, c = 5
<strong>Output:</strong> [-23,-5,1,7]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-100 &lt;= nums[i], a, b, c &lt;= 100</code></li>
	<li><code>nums</code> is sorted in <strong>ascending</strong> order.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it in <code>O(n)</code> time?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Math + Two Pointers

By mathematical knowledge, the graph of a quadratic function is a parabola. When $a \gt 0$, the parabola opens upwards and its vertex is the minimum value; when $a \lt 0$, the parabola opens downwards and its vertex is the maximum value.

Since the array $\textit{nums}$ is already sorted, we can use two pointers at both ends of the array. Depending on the sign of $a$, we decide whether to fill the result array from the beginning or the end with the larger (or smaller) values.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortTransformedArray(
        self, nums: List[int], a: int, b: int, c: int
    ) -> List[int]:
        def f(x: int) -> int:
            return a * x * x + b * x + c

        n = len(nums)
        i, j = 0, n - 1
        ans = [0] * n
        for k in range(n):
            y1, y2 = f(nums[i]), f(nums[j])
            if a > 0:
                if y1 > y2:
                    ans[n - k - 1] = y1
                    i += 1
                else:
                    ans[n - k - 1] = y2
                    j -= 1
            else:
                if y1 > y2:
                    ans[k] = y2
                    j -= 1
                else:
                    ans[k] = y1
                    i += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] ans = new int[n];
        int i = 0, j = n - 1;

        IntUnaryOperator f = x -> a * x * x + b * x + c;

        for (int k = 0; k < n; k++) {
            int y1 = f.applyAsInt(nums[i]);
            int y2 = f.applyAsInt(nums[j]);
            if (a > 0) {
                if (y1 > y2) {
                    ans[n - k - 1] = y1;
                    i++;
                } else {
                    ans[n - k - 1] = y2;
                    j--;
                }
            } else {
                if (y1 > y2) {
                    ans[k] = y2;
                    j--;
                } else {
                    ans[k] = y1;
                    i++;
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
    vector<int> sortTransformedArray(vector<int>& nums, int a, int b, int c) {
        int n = nums.size();
        vector<int> ans(n);
        int i = 0, j = n - 1;

        auto f = [&](int x) {
            return a * x * x + b * x + c;
        };

        for (int k = 0; k < n; k++) {
            int y1 = f(nums[i]);
            int y2 = f(nums[j]);
            if (a > 0) {
                if (y1 > y2) {
                    ans[n - k - 1] = y1;
                    i++;
                } else {
                    ans[n - k - 1] = y2;
                    j--;
                }
            } else {
                if (y1 > y2) {
                    ans[k] = y2;
                    j--;
                } else {
                    ans[k] = y1;
                    i++;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sortTransformedArray(nums []int, a int, b int, c int) []int {
	f := func(x int) int {
		return a*x*x + b*x + c
	}

	n := len(nums)
	ans := make([]int, n)
	i, j := 0, n-1

	for k := 0; k < n; k++ {
		y1, y2 := f(nums[i]), f(nums[j])
		if a > 0 {
			if y1 > y2 {
				ans[n-k-1] = y1
				i++
			} else {
				ans[n-k-1] = y2
				j--
			}
		} else {
			if y1 > y2 {
				ans[k] = y2
				j--
			} else {
				ans[k] = y1
				i++
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function sortTransformedArray(nums: number[], a: number, b: number, c: number): number[] {
    const f = (x: number): number => a * x * x + b * x + c;
    const n = nums.length;
    let [i, j] = [0, n - 1];
    const ans: number[] = Array(n);
    for (let k = 0; k < n; ++k) {
        const y1 = f(nums[i]);
        const y2 = f(nums[j]);
        if (a > 0) {
            if (y1 > y2) {
                ans[n - k - 1] = y1;
                ++i;
            } else {
                ans[n - k - 1] = y2;
                --j;
            }
        } else {
            if (y1 > y2) {
                ans[k] = y2;
                --j;
            } else {
                ans[k] = y1;
                ++i;
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} a
 * @param {number} b
 * @param {number} c
 * @return {number[]}
 */
var sortTransformedArray = function (nums, a, b, c) {
    const f = x => a * x * x + b * x + c;
    const n = nums.length;
    let [i, j] = [0, n - 1];
    const ans = Array(n);
    for (let k = 0; k < n; ++k) {
        const y1 = f(nums[i]);
        const y2 = f(nums[j]);
        if (a > 0) {
            if (y1 > y2) {
                ans[n - k - 1] = y1;
                ++i;
            } else {
                ans[n - k - 1] = y2;
                --j;
            }
        } else {
            if (y1 > y2) {
                ans[k] = y2;
                --j;
            } else {
                ans[k] = y1;
                ++i;
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
