---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2016.Maximum%20Difference%20Between%20Increasing%20Elements/README.md
rating: 1246
source: 第 260 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [2016. 增量元素之间的最大差值](https://leetcode.cn/problems/maximum-difference-between-increasing-elements)

[English Version](/solution/2000-2099/2016.Maximum%20Difference%20Between%20Increasing%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，该数组的大小为 <code>n</code> ，请你计算 <code>nums[j] - nums[i]</code> 能求得的 <strong>最大差值 </strong>，其中 <code>0 &lt;= i &lt; j &lt; n</code> 且 <code>nums[i] &lt; nums[j]</code> 。</p>

<p>返回 <strong>最大差值</strong> 。如果不存在满足要求的 <code>i</code> 和 <code>j</code> ，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,<em><strong>1</strong></em>,<em><strong>5</strong></em>,4]
<strong>输出：</strong>4
<strong>解释：</strong>
最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 &gt; 4 ，但 i &gt; j 不满足题面要求，所以 6 不是有效的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,4,3,2]
<strong>输出：</strong>-1
<strong>解释：</strong>
不存在同时满足 i &lt; j 和 nums[i] &lt; nums[j] 这两个条件的 i, j 组合。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [<em><strong>1</strong></em>,5,2,<em><strong>10</strong></em>]
<strong>输出：</strong>9
<strong>解释：</strong>
最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护前缀最小值

我们用一个变量 $\textit{mi}$ 表示当前遍历到的元素中的最小值，用一个变量 $\textit{ans}$ 表示最大差值，初始时 $\textit{mi}$ 为 $+\infty$，而 $\textit{ans}$ 为 $-1$。

遍历数组，对于当前遍历到的元素 $x$，如果 $x \gt \textit{mi}$，则更新 $\textit{ans}$ 为 $\max(\textit{ans}, x - \textit{mi})$，否则更新 $\textit{mi} = x$。

遍历结束后，返回 $\textit{ans}$。

遍历结束后，返回 $ans$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumDifference(self, nums: List[int]) -> int:
        mi = inf
        ans = -1
        for x in nums:
            if x > mi:
                ans = max(ans, x - mi)
            else:
                mi = x
        return ans
```

#### Java

```java
class Solution {
    public int maximumDifference(int[] nums) {
        int mi = 1 << 30;
        int ans = -1;
        for (int x : nums) {
            if (x > mi) {
                ans = Math.max(ans, x - mi);
            } else {
                mi = x;
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
    int maximumDifference(vector<int>& nums) {
        int mi = 1 << 30;
        int ans = -1;
        for (int& x : nums) {
            if (x > mi) {
                ans = max(ans, x - mi);
            } else {
                mi = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumDifference(nums []int) int {
	mi := 1 << 30
	ans := -1
	for _, x := range nums {
		if mi < x {
			ans = max(ans, x-mi)
		} else {
			mi = x
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maximumDifference(nums: number[]): number {
    let [ans, mi] = [-1, Infinity];
    for (const x of nums) {
        if (x > mi) {
            ans = Math.max(ans, x - mi);
        } else {
            mi = x;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_difference(nums: Vec<i32>) -> i32 {
        let mut mi = i32::MAX;
        let mut ans = -1;

        for &x in &nums {
            if x > mi {
                ans = ans.max(x - mi);
            } else {
                mi = x;
            }
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumDifference = function (nums) {
    let [ans, mi] = [-1, Infinity];
    for (const x of nums) {
        if (x > mi) {
            ans = Math.max(ans, x - mi);
        } else {
            mi = x;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
