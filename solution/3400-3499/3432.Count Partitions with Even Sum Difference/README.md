---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3432.Count%20Partitions%20with%20Even%20Sum%20Difference/README.md
rating: 1199
source: 第 434 场周赛 Q1
tags:
    - 数组
    - 数学
    - 前缀和
---

<!-- problem:start -->

# [3432. 统计元素和差值为偶数的分区方案](https://leetcode.cn/problems/count-partitions-with-even-sum-difference)

[English Version](/solution/3400-3499/3432.Count%20Partitions%20with%20Even%20Sum%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p><strong>分区</strong>&nbsp;是指将数组按照下标&nbsp;<code>i</code>&nbsp;（<code>0 &lt;= i &lt; n - 1</code>）划分成两个 <strong>非空</strong> 子数组，其中：</p>

<ul>
	<li>左子数组包含区间&nbsp;<code>[0, i]</code>&nbsp;内的所有下标。</li>
	<li>右子数组包含区间&nbsp;<code>[i + 1, n - 1]</code>&nbsp;内的所有下标。</li>
</ul>

<p>对左子数组和右子数组先求元素 <strong>和</strong> 再做 <strong>差</strong> ，统计并返回差值为 <strong>偶数</strong> 的 <strong>分区</strong> 方案数。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><b>输入：</b><span class="example-io">nums = [10,10,3,7,6]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><b>解释：</b></p>

<p>共有 4 个满足题意的分区方案：</p>

<ul>
	<li><code>[10]</code>、<code>[10, 3, 7, 6]</code>&nbsp;元素和的差值为&nbsp;<code>10 - 26 = -16</code>&nbsp;，是偶数。</li>
	<li><code>[10, 10]</code>、<code>[3, 7, 6]</code> 元素和的差值为&nbsp;<code>20 - 16 = 4</code>，是偶数。</li>
	<li><code>[10, 10, 3]</code>、<code>[7, 6]</code> 元素和的差值为&nbsp;<code>23 - 13 = 10</code>，是偶数。</li>
	<li><code>[10, 10, 3, 7]</code>、<code>[6]</code> 元素和的差值为&nbsp;<code>30 - 6 = 24</code>，是偶数。</li>
</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,2]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>不存在元素和的差值为偶数的分区方案。</p>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,4,6,8]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><b>解释：</b></p>

<p>所有分区方案都满足元素和的差值为偶数。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们用两个变量 $l$ 和 $r$ 分别表示左子数组和右子数组的和，初始时 $l = 0$，而 $r = \sum_{i=0}^{n-1} \textit{nums}[i]$。

接下来，我们遍历前 $n - 1$ 个元素，每次将当前元素加到左子数组中，同时从右子数组中减去当前元素，然后判断 $l - r$ 是否为偶数，如果是则答案加一。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPartitions(self, nums: List[int]) -> int:
        l, r = 0, sum(nums)
        ans = 0
        for x in nums[:-1]:
            l += x
            r -= x
            ans += (l - r) % 2 == 0
        return ans
```

#### Java

```java
class Solution {
    public int countPartitions(int[] nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
                ++ans;
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
    int countPartitions(vector<int>& nums) {
        int l = 0, r = accumulate(nums.begin(), nums.end(), 0);
        int ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPartitions(nums []int) (ans int) {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	for _, x := range nums[:len(nums)-1] {
		l += x
		r -= x
		if (l-r)%2 == 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countPartitions(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = 0;
    for (const x of nums.slice(0, -1)) {
        l += x;
        r -= x;
        ans += (l - r) % 2 === 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
