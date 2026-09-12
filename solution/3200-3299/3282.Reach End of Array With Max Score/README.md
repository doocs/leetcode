---
comments: true
difficulty: 中等
rating: 1771
source: 第 414 场周赛 Q3
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [3282. 到达数组末尾的最大得分](https://leetcode.cn/problems/reach-end-of-array-with-max-score)

[English Version](/solution/3200-3299/3282.Reach%20End%20of%20Array%20With%20Max%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你的目标是从下标 <code>0</code>&nbsp;出发，到达下标 <code>n - 1</code>&nbsp;处。每次你只能移动到&nbsp;<strong>更大</strong>&nbsp;的下标处。</p>

<p>从下标 <code>i</code>&nbsp;跳到下标 <code>j</code>&nbsp;的得分为&nbsp;<code>(j - i) * nums[i]</code>&nbsp;。</p>

<p>请你返回你到达最后一个下标处能得到的 <strong>最大总得分</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,1,5]</span></p>

<p><b>输出：</b>7</p>

<p><b>解释：</b></p>

<p>一开始跳到下标 1 处，然后跳到最后一个下标处。总得分为&nbsp;<code>1 * 1 + 2 * 3 = 7</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,3,1,3,2]</span></p>

<p><b>输出：</b>16</p>

<p><strong>解释：</strong></p>

<p>直接跳到最后一个下标处。总得分为&nbsp;<code>4 * 4 = 16</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

<!-- thinking:start -->

> **思考**
>
> 从 $i$ 跳到 $j$ 得 $(j-i)\times nums[i]$，可理解为中间每走一格都加上 $nums[i]$。$n\le 10^5$，若某次跳到更小的 $nums[j]$ 再出发，中间那些格用 $nums[i]$ 计价更优，故不应跳到更小值。
>
> 维护从左到当前的最大值 $mx$，对除终点外的每一格累加 $mx$。等价于总是在「历史最大」上停留到必须离开。线性。

<!-- thinking:end -->

假设我们从下标 $i$，跳到下标 $j$，那么得分为 $(j - i) \times \text{nums}[i]$。这相当于我们走了 $j - i$ 步，每一步都得到了 $\text{nums}[i]$ 的得分。然后我们从 $j$ 继续跳到下一个下标 $k$，得分为 $(k - j) \times \text{nums}[j]$，以此类推。如果 $nums[i] \gt nums[j]$，那么我们就不应该从 $i$ 跳到 $j$，因为这样得到的得分一定比从 $i$ 直接跳到 $k$ 得到的得分要少。因此，我们每一次应该跳到下一个比当前下标对应的值更大的下标。

我们可以维护一个变量 $mx$，表示当前为止，我们遇到的最大的 $\text{nums}[i]$ 的值。然后我们从左到右遍历数组，直到倒数第二个元素，每次更新 $mx$，并且累加得分。

遍历结束后，我们得到的就是最大的总得分。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\text{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaximumScore(self, nums: List[int]) -> int:
        ans = mx = 0
        for x in nums[:-1]:
            mx = max(mx, x)
            ans += mx
        return ans
```

#### Java

```java
class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = Math.max(mx, nums.get(i));
            ans += mx;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long findMaximumScore(vector<int>& nums) {
        long long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = max(mx, nums[i]);
            ans += mx;
        }
        return ans;
    }
};
```

#### Go

```go
func findMaximumScore(nums []int) (ans int64) {
	mx := 0
	for _, x := range nums[:len(nums)-1] {
		mx = max(mx, x)
		ans += int64(mx)
	}
	return
}
```

#### TypeScript

```ts
function findMaximumScore(nums: number[]): number {
    let [ans, mx]: [number, number] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        mx = Math.max(mx, x);
        ans += mx;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
