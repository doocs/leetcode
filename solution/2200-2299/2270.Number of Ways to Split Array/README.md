---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2270.Number%20of%20Ways%20to%20Split%20Array/README.md
rating: 1334
source: 第 78 场双周赛 Q2
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2270. 分割数组的方案数](https://leetcode.cn/problems/number-of-ways-to-split-array)

[English Version](/solution/2200-2299/2270.Number%20of%20Ways%20to%20Split%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。<br />
<span style="">如果以下描述为真，那么</span><span style=""> </span><code>nums</code>&nbsp;在下标 <code>i</code>&nbsp;处有一个 <strong>合法的分割</strong>&nbsp;：</p>

<ul>
	<li>前&nbsp;<code>i + 1</code>&nbsp;个元素的和 <strong>大于等于</strong>&nbsp;剩下的&nbsp;<code>n - i - 1</code>&nbsp;个元素的和。</li>
	<li>下标 <code>i</code>&nbsp;的右边 <strong>至少有一个</strong>&nbsp;元素，也就是说下标&nbsp;<code>i</code>&nbsp;满足&nbsp;<code>0 &lt;= i &lt; n - 1</code>&nbsp;。</li>
</ul>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中的&nbsp;<strong>合法分割</strong>&nbsp;方案数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [10,4,-8,7]
<b>输出：</b>2
<b>解释：</b>
总共有 3 种不同的方案可以将 nums 分割成两个非空的部分：
- 在下标 0 处分割 nums 。那么第一部分为 [10] ，和为 10 。第二部分为 [4,-8,7] ，和为 3 。因为 10 &gt;= 3 ，所以 i = 0 是一个合法的分割。
- 在下标 1 处分割 nums 。那么第一部分为 [10,4] ，和为 14 。第二部分为 [-8,7] ，和为 -1 。因为 14 &gt;= -1 ，所以 i = 1 是一个合法的分割。
- 在下标 2 处分割 nums 。那么第一部分为 [10,4,-8] ，和为 6 。第二部分为 [7] ，和为 7 。因为 6 &lt; 7 ，所以 i = 2 不是一个合法的分割。
所以 nums 中总共合法分割方案受为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,1,0]
<b>输出：</b>2
<b>解释：</b>
总共有 2 种 nums 的合法分割：
- 在下标 1 处分割 nums 。那么第一部分为 [2,3] ，和为 5 。第二部分为 [1,0] ，和为 1 。因为 5 &gt;= 1 ，所以 i = 1 是一个合法的分割。
- 在下标 2 处分割 nums 。那么第一部分为 [2,3,1] ，和为 6 。第二部分为 [0] ，和为 0 。因为 6 &gt;= 0 ，所以 i = 2 是一个合法的分割。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们首先计算数组 $\textit{nums}$ 的总和 $s$，然后遍历数组 $\textit{nums}$ 的前 $n-1$ 个元素，用变量 $t$ 记录前缀和，如果 $t \geq s - t$，则将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToSplitArray(self, nums: List[int]) -> int:
        s = sum(nums)
        ans = t = 0
        for x in nums[:-1]:
            t += x
            ans += t >= s - t
        return ans
```

#### Java

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        long s = 0;
        for (int x : nums) {
            s += x;
        }
        long t = 0;
        int ans = 0;
        for (int i = 0; i + 1 < nums.length; ++i) {
            t += nums[i];
            ans += t >= s - t ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToSplitArray(vector<int>& nums) {
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        long long t = 0;
        int ans = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            t += nums[i];
            ans += t >= s - t;
        }
        return ans;
    }
};
```

#### Go

```go
func waysToSplitArray(nums []int) (ans int) {
	var s, t int
	for _, x := range nums {
		s += x
	}
	for _, x := range nums[:len(nums)-1] {
		t += x
		if t >= s-t {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function waysToSplitArray(nums: number[]): number {
    const s = nums.reduce((acc, cur) => acc + cur, 0);
    let [ans, t] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        t += x;
        if (t >= s - t) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
