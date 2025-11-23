---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3745.Maximize%20Expression%20of%20Three%20Elements/README_EN.md
rating: 1218
source: Weekly Contest 476 Q1
---

<!-- problem:start -->

# [3745. Maximize Expression of Three Elements](https://leetcode.com/problems/maximize-expression-of-three-elements)

[中文文档](/solution/3700-3799/3745.Maximize%20Expression%20of%20Three%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Choose three elements <code>a</code>, <code>b</code>, and <code>c</code> from <code>nums</code> at <strong>distinct</strong> indices such that the value of the expression <code>a + b - c</code> is maximized.</p>

<p>Return an integer denoting the <strong>maximum possible value</strong> of this expression.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,2,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>We can choose <code>a = 4</code>, <code>b = 5</code>, and <code>c = 1</code>. The expression value is <code>4 + 5 - 1 = 8</code>, which is the maximum possible.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-2,0,5,-2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>We can choose <code>a = 5</code>, <code>b = 4</code>, and <code>c = -2</code>. The expression value is <code>5 + 4 - (-2) = 11</code>, which is the maximum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Find Maximum, Second Maximum, and Minimum Values

According to the problem description, we need to choose three elements $a$, $b$, and $c$ at distinct indices such that the value of the expression $a + b - c$ is maximized.

We only need to traverse the array to find the largest two elements $a$ and $b$ and the smallest element $c$. Then we can calculate the value of the expression.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximizeExpressionOfThree(self, nums: List[int]) -> int:
        a = b = -inf
        c = inf
        for x in nums:
            if x < c:
                c = x
            if x >= a:
                a, b = x, a
            elif x > b:
                b = x
        return a + b - c
```

#### Java

```java
class Solution {
    public int maximizeExpressionOfThree(int[] nums) {
        final int inf = 1 << 30;
        int a = -inf, b = -inf, c = inf;
        for (int x : nums) {
            if (x < c) {
                c = x;
            }
            if (x >= a) {
                b = a;
                a = x;
            } else if (x > b) {
                b = x;
            }
        }
        return a + b - c;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximizeExpressionOfThree(vector<int>& nums) {
        const int inf = 1 << 30;
        int a = -inf, b = -inf, c = inf;
        for (int x : nums) {
            if (x < c) {
                c = x;
            }
            if (x >= a) {
                b = a;
                a = x;
            } else if (x > b) {
                b = x;
            }
        }
        return a + b - c;
    }
};
```

#### Go

```go
func maximizeExpressionOfThree(nums []int) int {
    const inf = 1 << 30
    a, b, c := -inf, -inf, inf
    for _, x := range nums {
        if x < c {
            c = x
        }
        if x >= a {
            b = a
            a = x
        } else if x > b {
            b = x
        }
    }
    return a + b - c
}
```

#### TypeScript

```ts
function maximizeExpressionOfThree(nums: number[]): number {
    const inf = 1 << 30;
    let [a, b, c] = [-inf, -inf, inf];

    for (const x of nums) {
        if (x < c) {
            c = x;
        }
        if (x >= a) {
            b = a;
            a = x;
        } else if (x > b) {
            b = x;
        }
    }
    return a + b - c;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
