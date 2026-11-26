---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4120. 统计优势元素的数量](https://leetcode.cn/problems/count-dominant-elements)

[English Version](/solution/4100-4199/4120.Count%20Dominant%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果 <code>nums</code> 中的某个元素 <strong>大于或等于</strong> 其相邻元素的平均值，则称它为 <strong>优势</strong> 元素。</p>

<p><code>nums[i]</code> 的相邻元素是 <code>nums[i - 1]</code> 和 <code>nums[i + 1]</code>，仅在对应下标存在时计入。</p>

<p>没有相邻元素的单个元素视为 <strong>优势</strong> 元素。</p>

<p>返回 <code>nums</code> 中 <strong>优势</strong> 元素的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,10,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 1</code>，它唯一的相邻元素是 10。由于 <code>1 &lt; 10</code>，它不是优势元素。</li>
	<li>对于 <code>nums[1] = 10</code>，它的相邻元素是 1 和 2，平均值为 <code>(1 + 2) / 2 = 1.5</code>。由于 <code>10 &gt; 1.5</code>，它是优势元素。</li>
	<li>对于 <code>nums[2] = 2</code>，它唯一的相邻元素是 10。由于 <code>2 &lt; 10</code>，它不是优势元素。</li>
</ul>

<p>因此，答案是 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,4,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 6</code>，它唯一的相邻元素是 4。由于 <code>6 &gt; 4</code>，它是优势元素。</li>
	<li>对于 <code>nums[1] = 4</code>，它的相邻元素是 6 和 2，平均值为 <code>(6 + 2) / 2 = 4</code>。由于 <code>4 = 4</code>，它是优势元素。</li>
	<li>对于 <code>nums[2] = 2</code>，它唯一的相邻元素是 4。由于 <code>2 &lt; 4</code>，它不是优势元素。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 <code>nums[0] = 7</code>，它没有相邻元素，因此它是优势元素。</li>
</ul>

<p>因此，答案是 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$，逐个元素和相邻位置比较即可。
>
> 优势元素要大于等于相邻元素的平均值。单个元素没有邻居，题目规定它本身就是优势元素。
>
> 邻居个数为 $\textit{cnt}$、邻居之和为 $s$ 时，不小于平均值等价于当前值乘 $\textit{cnt}$ 不小于 $s$。左右邻居是否存在，只看下标是否碰到边界。
>
> 因此一遍扫描累加实际存在的邻居，没有邻居或满足不等式就计数。

<!-- thinking:end -->

我们从左到右枚举下标 $i$，用 $s$ 累加存在的相邻元素，用 $\textit{cnt}$ 记录它们的个数。 $i>0$ 时加上 $\textit{nums}[i-1]$， $i<n-1$ 时加上 $\textit{nums}[i+1]$。 $\textit{cnt}=0$ 时当前元素没有邻居，计入答案。否则当 $\textit{nums}[i]\ge s/\textit{cnt}$ 时计入答案。Python 直接比较商，其余语言比较 $\textit{nums}[i]\times\textit{cnt}$ 与 $s$，两者等价。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countDominantElements(self, nums: list[int]) -> int:
        ans = 0
        for i, x in enumerate(nums):
            s = cnt = 0
            if i > 0:
                s += nums[i - 1]
                cnt += 1
            if i < len(nums) - 1:
                s += nums[i + 1]
                cnt += 1
            if cnt == 0 or x >= s / cnt:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countDominantElements(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int s = 0, cnt = 0;
            if (i > 0) {
                s += nums[i - 1];
                cnt++;
            }
            if (i < nums.length - 1) {
                s += nums[i + 1];
                cnt++;
            }
            if (cnt == 0 || nums[i] * cnt >= s) {
                ans++;
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
    int countDominantElements(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            int s = 0, cnt = 0;
            if (i > 0) {
                s += nums[i - 1];
                cnt++;
            }
            if (i < nums.size() - 1) {
                s += nums[i + 1];
                cnt++;
            }
            if (cnt == 0 || nums[i] * cnt >= s) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countDominantElements(nums []int) int {
	ans := 0
	for i, x := range nums {
		s, cnt := 0, 0
		if i > 0 {
			s += nums[i-1]
			cnt++
		}
		if i < len(nums)-1 {
			s += nums[i+1]
			cnt++
		}
		if cnt == 0 || x*cnt >= s {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countDominantElements(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < nums.length; i++) {
        let s = 0,
            cnt = 0;
        if (i > 0) {
            s += nums[i - 1];
            cnt++;
        }
        if (i < nums.length - 1) {
            s += nums[i + 1];
            cnt++;
        }
        if (cnt === 0 || nums[i] * cnt >= s) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
