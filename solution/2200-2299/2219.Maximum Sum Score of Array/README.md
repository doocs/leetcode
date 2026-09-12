---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2219. 数组的最大总分 🔒](https://leetcode.cn/problems/maximum-sum-score-of-array)

[English Version](/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，数组长度为 <code>n</code> 。</p>

<p><code>nums</code> 在下标 <code>i</code> （<code>0 &lt;= i &lt; n</code>）处的 <strong>总分</strong> 等于下面两个分数中的 <strong>最大值</strong> ：</p>

<ul>
	<li><code>nums</code><strong> 前</strong> <code>i + 1</code> 个元素的总和</li>
	<li><code>nums</code> <strong>后</strong> <code>n - i</code> 个元素的总和</li>
</ul>

<p>返回数组 <code>nums</code> 在任一下标处能取得的 <strong>最大总分</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,-2,5]
<strong>输出：</strong>10
<strong>解释：</strong>
下标 0 处的最大总分是 max(4, 4 + 3 + -2 + 5) = max(4, 10) = 10 。
下标 1 处的最大总分是 max(4 + 3, 3 + -2 + 5) = max(7, 6) = 7 。
下标 2 处的最大总分是 max(4 + 3 + -2, -2 + 5) = max(5, 3) = 5 。
下标 3 处的最大总分是 max(4 + 3 + -2 + 5, 5) = max(10, 5) = 10 。
nums 可取得的最大总分是 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-3,-5]
<strong>输出：</strong>-3
<strong>解释：</strong>
下标 0 处的最大总分是 max(-3, -3 + -5) = max(-3, -8) = -3 。
下标 1 处的最大总分是 max(-3 + -5, -5) = max(-8, -5) = -5 。
nums 可取得的最大总分是 -3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

<!-- thinking:start -->

> **思考**
>
> 下标 $i$ 的得分为「$i$ 左侧含自身的前缀和」与「$i$ 右侧含自身的后缀和」中的较大者，要求所有 $i$ 上的最大得分。$n \le 10^5$，对每个 $i$ 重新求和不可行。
>
> 两个和都可以在扫描时维护：先记下总和 $r$，再从左往右把 $x$ 加入前缀 $l$，此时 $l$ 与 $r$ 恰是当前下标的两个候选，取较大者更新答案，然后从 $r$ 中减去 $x$。一遍即可。

<!-- thinking:end -->

我们可以使用两个变量 $l$ 和 $r$ 分别表示数组的前缀和和后缀和，初始时 $l = 0$, $r = \sum_{i=0}^{n-1} \textit{nums}[i]$。

接下来，我们遍历数组 $\textit{nums}$，对于每个元素 $x$，我们将 $l$ 增加 $x$，并更新答案 $\textit{ans} = \max(\textit{ans}, l, r)$，然后将 $r$ 减少 $x$。

遍历结束后，返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSumScore(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        ans = -inf
        for x in nums:
            l += x
            ans = max(ans, l, r)
            r -= x
        return ans
```

#### Java

```java
class Solution {
    public long maximumSumScore(int[] nums) {
        long l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        long ans = Long.MIN_VALUE;
        for (int x : nums) {
            l += x;
            ans = Math.max(ans, Math.max(l, r));
            r -= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        long long l = 0, r = accumulate(nums.begin(), nums.end(), 0LL);
        long long ans = -1e18;
        for (int x : nums) {
            l += x;
            ans = max({ans, l, r});
            r -= x;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSumScore(nums []int) int64 {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	ans := math.MinInt64
	for _, x := range nums {
		l += x
		ans = max(ans, max(l, r))
		r -= x
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function maximumSumScore(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = -Infinity;
    for (const x of nums) {
        l += x;
        ans = Math.max(ans, l, r);
        r -= x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_sum_score(nums: Vec<i32>) -> i64 {
        let mut l = 0;
        let mut r: i64 = nums.iter().map(|&x| x as i64).sum();
        let mut ans = std::i64::MIN;
        for &x in &nums {
            l += x as i64;
            ans = ans.max(l).max(r);
            r -= x as i64;
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
var maximumSumScore = function (nums) {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = -Infinity;
    for (const x of nums) {
        l += x;
        ans = Math.max(ans, l, r);
        r -= x;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
