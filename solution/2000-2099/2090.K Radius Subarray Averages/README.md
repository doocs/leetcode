---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/README.md
rating: 1358
source: 第 269 场周赛 Q2
tags:
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [2090. 半径为 k 的子数组平均值](https://leetcode.cn/problems/k-radius-subarray-averages)

[English Version](/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组中有 <code>n</code> 个整数，另给你一个整数 <code>k</code> 。</p>

<p><strong>半径为 k 的子数组平均值</strong> 是指：<code>nums</code> 中一个以下标 <code>i</code> 为 <strong>中心</strong> 且 <strong>半径</strong> 为 <code>k</code> 的子数组中所有元素的平均值，即下标在&nbsp;<code>i - k</code> 和 <code>i + k</code> 范围（<strong>含</strong> <code>i - k</code> 和 <code>i + k</code>）内所有元素的平均值。如果在下标 <code>i</code> 前或后不足 <code>k</code> 个元素，那么<strong> 半径为 k 的子数组平均值 </strong>是 <code>-1</code> 。</p>

<p>构建并返回一个长度为 <code>n</code> 的数组<em> </em><code>avgs</code><em> </em>，其中<em> </em><code>avgs[i]</code><em> </em>是以下标 <code>i</code> 为中心的子数组的<strong> 半径为 k 的子数组平均值 </strong>。</p>

<p><code>x</code> 个元素的 <strong>平均值</strong> 是 <code>x</code> 个元素相加之和除以 <code>x</code> ，此时使用截断式 <strong>整数除法</strong> ，即需要去掉结果的小数部分。</p>

<ul>
	<li>例如，四个元素 <code>2</code>、<code>3</code>、<code>1</code> 和 <code>5</code> 的平均值是 <code>(2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75</code>，截断后得到 <code>2</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/images/eg1.png" style="width: 343px; height: 119px;" /></p>

<pre>
<strong>输入：</strong>nums = [7,4,3,9,1,8,5,2,6], k = 3
<strong>输出：</strong>[-1,-1,-1,5,4,4,-1,-1,-1]
<strong>解释：</strong>
- avg[0]、avg[1] 和 avg[2] 是 -1 ，因为在这几个下标前的元素数量都不足 k 个。
- 中心为下标 3 且半径为 3 的子数组的元素总和是：7 + 4 + 3 + 9 + 1 + 8 + 5 = 37 。
  使用截断式 <strong>整数除法</strong>，avg[3] = 37 / 7 = 5 。
- 中心为下标 4 的子数组，avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4 。
- 中心为下标 5 的子数组，avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4 。
- avg[6]、avg[7] 和 avg[8] 是 -1 ，因为在这几个下标后的元素数量都不足 k 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [100000], k = 0
<strong>输出：</strong>[100000]
<strong>解释：</strong>
- 中心为下标 0 且半径 0 的子数组的元素总和是：100000 。
  avg[0] = 100000 / 1 = 100000 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [8], k = 100000
<strong>输出：</strong>[-1]
<strong>解释：</strong>
- avg[0] 是 -1 ，因为在下标 0 前后的元素数量均不足 k 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

半径为 $k$ 的子数组的长度为 $k \times 2 + 1$，因此我们可以维护一个大小为 $k \times 2 + 1$ 的窗口，记窗口中的所有元素和为 $s$。

我们创建一个长度为 $n$ 的答案数组 $\textit{ans}$，初始时每个元素都为 $-1$。

接下来，我们遍历数组 $\textit{nums}$，将 $\textit{nums}[i]$ 的值加到窗口的和 $s$ 中，如果此时 $i \geq k \times 2$，说明此时窗口大小为 $k \times 2 + 1$，那么 $\textit{ans}[i-k] = \frac{s}{k \times 2 + 1}$，然后我们将 $\textit{nums}[i - k \times 2]$ 的值从窗口和 $s$ 中移出。继续遍历下个元素。

最后返回答案数组即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        ans = [-1] * n
        s = 0
        for i, x in enumerate(nums):
            s += x
            if i >= k * 2:
                ans[i - k] = s // (k * 2 + 1)
                s -= nums[i - k * 2]
        return ans
```

#### Java

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i >= k * 2) {
                ans[i - k] = (int) (s / (k * 2 + 1));
                s -= nums[i - k * 2];
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
    vector<int> getAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> ans(n, -1);
        long long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i >= k * 2) {
                ans[i - k] = s / (k * 2 + 1);
                s -= nums[i - k * 2];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getAverages(nums []int, k int) []int {
	ans := make([]int, len(nums))
	for i := range ans {
		ans[i] = -1
	}
	s := 0
	for i, x := range nums {
		s += x
		if i >= k*2 {
			ans[i-k] = s / (k*2 + 1)
			s -= nums[i-k*2]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const ans: number[] = Array(n).fill(-1);
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        if (i >= k * 2) {
            ans[i - k] = Math.floor(s / (k * 2 + 1));
            s -= nums[i - k * 2];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
