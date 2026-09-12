---
comments: true
difficulty: 中等
tags:
    - 数组
    - 数学
    - 双指针
    - 排序
---

<!-- problem:start -->

# [360. 有序转化数组 🔒](https://leetcode.cn/problems/sort-transformed-array)

[English Version](/solution/0300-0399/0360.Sort%20Transformed%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个已经<strong>&nbsp;排好序</strong>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和整数&nbsp;<code>a</code>&nbsp;、&nbsp;<code>b</code>&nbsp;、&nbsp;<code>c</code>&nbsp;。对于数组中的每一个元素&nbsp;<code>nums[i]</code>&nbsp;，计算函数值&nbsp;<code>f(<em>x</em>) = <em>ax</em><sup>2</sup> + <em>bx</em> + c</code>&nbsp;，请 <em>按升序返回数组</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>nums = [-4,-2,2,4], a = 1, b = 3, c = 5
<strong>输出: </strong>[3,9,15,33]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>nums = [-4,-2,2,4], a = -1, b = 3, c = 5
<strong>输出: </strong>[-23,-5,1,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>-100 &lt;= nums[i], a, b, c &lt;= 100</code></li>
	<li><code>nums</code>&nbsp;按照 <strong>升序排列</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以在时间复杂度为&nbsp;<code>O(n)</code>&nbsp;的情况下解决这个问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 双指针

<!-- thinking:start -->

> **思考**
>
> $nums$ 已排序，对 $ax^2+bx+c$ 后再排序。直接计算再 $O(n\log n)$ 排序即可，但二次函数在有序定义域上单调性已知。
>
> $a>0$ 开口向上，两端较大，从答案尾部填较大值；$a\le 0$ 则两端较小，从头部填较小值。双指针向内收，一次线性完成。

<!-- thinking:end -->

根据数学知识可知，二次函数的图像是一条抛物线，当 $a \gt 0$ 时，抛物线开口向上，顶点为最小值；当 $a \lt 0$ 时，抛物线开口向下，顶点为最大值。

由于数组 $\textit{nums}$ 已经排好序，我们可以使用双指针分别指向数组的两端，根据 $a$ 的正负决定从结果数组的头部还是尾部开始填充较大（或较小）的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

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
