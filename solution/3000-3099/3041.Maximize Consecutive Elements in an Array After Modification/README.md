---
comments: true
difficulty: 困难
rating: 2231
source: 第 124 场双周赛 Q4
tags:
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [3041. 修改数组后最大化数组中的连续元素数目](https://leetcode.cn/problems/maximize-consecutive-elements-in-an-array-after-modification)

[English Version](/solution/3000-3099/3041.Maximize%20Consecutive%20Elements%20in%20an%20Array%20After%20Modification/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始只包含 <strong>正</strong>&nbsp;整数的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一开始，你可以将数组中 <strong>任意数量</strong> 元素增加 <strong>至多</strong> <code>1</code> 。</p>

<p>修改后，你可以从最终数组中选择 <strong>一个或者更多</strong>&nbsp;元素，并确保这些元素升序排序后是 <strong>连续</strong>&nbsp;的。比方说，<code>[3, 4, 5]</code> 是连续的，但是&nbsp;<code>[3, 4, 6]</code> 和&nbsp;<code>[1, 1, 2, 3]</code>&nbsp;不是连续的。<!-- notionvc: 312f8c5d-40d0-4cd1-96cc-9e96a846735b --></p>

<p>请你返回 <strong>最多</strong>&nbsp;可以选出的元素数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,5,1,1]
<b>输出：</b>3
<b>解释：</b>我们将下标 0 和 3 处的元素增加 1 ，得到结果数组 nums = [3,1,5,2,1] 。
我们选择元素 [<em><strong>3</strong></em>,<em><strong>1</strong></em>,5,<em><strong>2</strong></em>,1] 并将它们排序得到 [1,2,3] ，是连续元素。
最多可以得到 3 个连续元素。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,7,10]
<b>输出：</b>1
<b>解释：</b>我们可以选择的最多元素数目是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 动态规划

<!-- thinking:start -->

> **思考**
>
> 每个元素至多加 $1$，再从修改后的值里选出一段连续整数。 $n \le 10^5$，若枚举连续段的起点再逐个匹配，时间上过不了这组约束。
>
> 排序之后，尚未处理的值都不会更小。连续段一旦断开，更早的结尾长度就不会再被用到，状态可以只留在上一个原始值附近。
>
> 一个数要么保持原值，要么变成比它大 $1$ 的数，所以相对上一个原始值，只需要「以原值结尾」和「以原值加一结尾」两条长度。
>
> 新元素与上一个原始值的差决定它能接到哪一条上。相等时只能作为加一接上去；相差 $1$ 时两条都能延长；相差 $2$ 时只能接上加一那条；再大就重新起段。因此先排序，再按这个顺序扫一遍即可。

<!-- thinking:end -->

我们将数组 $\textit{nums}$ 从小到大排序，然后从左到右扫描。记上一个原始值为 $\textit{prev}$。 $f_0$ 是以 $\textit{prev}$ 结尾的最长连续长度， $f_1$ 是以 $\textit{prev}+1$ 结尾的最长连续长度。每个元素只能保持原值或加 $1$，连续段中同一个值至多出现一次，因此比 $\textit{prev}$ 更早的结尾不必再保存。

设当前元素为 $x$。按 $x$ 与 $\textit{prev}$ 的差转移；转移结束后把 $\textit{prev}$ 更新为 $x$，两条长度改成相对这个新值：

- 若 $x = \textit{prev}$，当前元素不能再作为 $\textit{prev}$。把它加 $1$ 后接到以 $\textit{prev}$ 结尾的连续段上，得到 $f_1 = f_0 + 1$。
- 若 $x = \textit{prev}+1$，保持原值可以延长以 $\textit{prev}$ 结尾的段，加 $1$ 可以延长以 $\textit{prev}+1$ 结尾的段，因此 $f_0$ 与 $f_1$ 都增加 $1$。
- 若 $x = \textit{prev}+2$，只有以 $\textit{prev}+1$ 结尾的段能接上当前原值，新的 $f_0 = f_1 + 1$。把当前元素加 $1$ 则单独成段， $f_1 = 1$。
- 否则两段都无法衔接， $f_0$ 与 $f_1$ 都重置为 $1$。

扫描过程中 $f_0$ 与 $f_1$ 的最大值就是答案。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()
        ans = 1
        prev = -inf
        f0 = f1 = 1
        for x in nums:
            if x == prev:
                f1 = f0 + 1
            elif x == prev + 1:
                f0 += 1
                f1 += 1
            elif x == prev + 2:
                f0 = f1 + 1
                f1 = 1
            else:
                f0 = f1 = 1
            ans = max(ans, f0, f1)
            prev = x
        return ans
```

#### Java

```java
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, prev = Integer.MIN_VALUE;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = Math.max(ans, Math.max(f0, f1));
            prev = x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 1, prev = INT_MIN;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = max({ans, f0, f1});
            prev = x;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSelectedElements(nums []int) int {
	sort.Ints(nums)
	ans, prev := 1, math.MinInt32
	f0, f1 := 1, 1
	for _, x := range nums {
		if x == prev {
			f1 = f0 + 1
		} else if x == prev+1 {
			f0++
			f1++
		} else if x == prev+2 {
			f0 = f1 + 1
			f1 = 1
		} else {
			f0, f1 = 1, 1
		}
		ans = max(ans, f0, f1)
		prev = x
	}
	return ans
}
```

#### TypeScript

```ts
function maxSelectedElements(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let prev = -Infinity;
    let f0 = 1;
    let f1 = 1;
    for (const x of nums) {
        if (x === prev) {
            f1 = f0 + 1;
        } else if (x === prev + 1) {
            ++f0;
            ++f1;
        } else if (x === prev + 2) {
            f0 = f1 + 1;
            f1 = 1;
        } else {
            f0 = 1;
            f1 = 1;
        }
        ans = Math.max(ans, f0, f1);
        prev = x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_selected_elements(mut nums: Vec<i32>) -> i32 {
        nums.sort_unstable();
        let mut ans = 1;
        let mut prev = i32::MIN;
        let mut f0 = 1;
        let mut f1 = 1;
        for &x in &nums {
            if x == prev {
                f1 = f0 + 1;
            } else if x == prev + 1 {
                f0 += 1;
                f1 += 1;
            } else if x == prev + 2 {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = ans.max(f0).max(f1);
            prev = x;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
