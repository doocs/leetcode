---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3745.Maximize%20Expression%20of%20Three%20Elements/README.md
rating: 1218
source: 第 476 场周赛 Q1
---

<!-- problem:start -->

# [3745. 三元素表达式的最大值](https://leetcode.cn/problems/maximize-expression-of-three-elements)

[English Version](/solution/3700-3799/3745.Maximize%20Expression%20of%20Three%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>从 <code>nums</code> 中选择三个元素 <code>a</code>、<code>b</code> 和 <code>c</code>，它们的下标需&nbsp;<strong>互不相同</strong>&nbsp;，使表达式 <code>a + b - c</code> 的值最大化。</p>

<p>返回该表达式可能的<strong>&nbsp;最大值&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,4,2,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<p>可以选择 <code>a = 4</code>，<code>b = 5</code>，<code>c = 1</code>。表达式的值为 <code>4 + 5 - 1 = 8</code>，这是可能的最大值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-2,0,5,-2,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">11</span></p>

<p><strong>解释：</strong></p>

<p>可以选择 <code>a = 5</code>，<code>b = 4</code>，<code>c = -2</code>。表达式的值为 <code>5 + 4 - (-2) = 11</code>，这是可能的最大值。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：求最大值和次大值以及最小值

根据题目描述，我们需要选择三个互不相同下标的元素 $a$、$b$ 和 $c$，使得表达式 $a + b - c$ 的值最大化。

我们只需要遍历数组，找到最大的两个元素 $a$ 和 $b$ 以及最小的元素 $c$。然后计算表达式的值即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

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
