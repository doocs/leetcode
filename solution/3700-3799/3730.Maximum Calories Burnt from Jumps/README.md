---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3730.Maximum%20Calories%20Burnt%20from%20Jumps/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [3730. 跳跃燃烧的最大卡路里 🔒](https://leetcode.cn/problems/maximum-calories-burnt-from-jumps)

[English Version](/solution/3700-3799/3730.Maximum%20Calories%20Burnt%20from%20Jumps/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>heights</code>，其中&nbsp;<code>heights[i]</code>&nbsp;表示训练计划中第&nbsp;<code>i</code>&nbsp;个块的高度。</p>

<p>你从地面（高度0）开始，<strong>必须</strong> 按照任意顺序跳到每个方块上，且只能跳 <strong>一次</strong>。</p>

<ul>
	<li>从一个高度为&nbsp;<code>a</code> 的块起跳到另一个高度为&nbsp;<code>b</code>&nbsp;的块所消耗的卡路里是&nbsp;<code>(a - b)<sup>2</sup></code>。</li>
	<li>从地面跳到所选的第一个方块高度 <code>heights[i]</code> 所 <strong>消耗的卡路里</strong> 是 <code>(0 - heights[i])<sup>2</sup></code>。</li>
</ul>

<p>返回通过选择最优跳跃序列所能燃烧的 <strong>最大</strong> 总卡路里。</p>

<p><strong>注意：</strong>一旦你跳到第一个方块上，就无法返回地面。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">heights = [1,7,9]</span></p>

<p><span class="example-io"><b>输出：</b>181</span></p>

<p><b>解释：</b></p>

<p>最优序列是&nbsp;<code>[9, 1, 7]</code>。</p>

<ul>
	<li>从地面到 <code>heights[2] = 9</code>&nbsp;的初始跳跃：<code>(0 - 9)<sup>2</sup> = 81</code>。</li>
	<li>下一次跳跃到&nbsp;<code>heights[0] = 1</code>：<code>(9 - 1)<sup>2</sup> = 64</code>。</li>
	<li>最后一次跳跃到&nbsp;<code>heights[1] = 7</code>：<code>(1 - 7)<sup>2</sup> = 36</code>。</li>
</ul>

<p>消耗的总卡路里&nbsp;= <code>81 + 64 + 36 = 181</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>heights = [5,2,4]</span></p>

<p><strong>输出：</strong><span class="example-io">38</span></p>

<p><strong>示例：</strong></p>

<p>最优序列是&nbsp;<code>[5, 2, 4]</code>。</p>

<ul>
	<li>从地面到 <code>heights[0] = 5</code>&nbsp;的初始跳跃：<code>(0 - 5)<sup>2</sup> = 25</code>。</li>
	<li>下一次跳跃到&nbsp;<code>heights[1] = 2</code>：<code>(5 - 2)<sup>2</sup> = 9</code>。</li>
	<li>最后一次跳跃到&nbsp;<code>heights[2] = 4</code>：<code>(2 - 4)<sup>2</sup> = 4</code>。</li>
</ul>

<p>消耗的总卡路里 = <code>25 + 9 + 4 = 38</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>heights = [3,3]</span></p>

<p><span class="example-io"><b>输出：</b>9</span></p>

<p><b>示例：</b></p>

<p>最优序列是&nbsp;<code>[3, 3]</code>。</p>

<ul>
	<li>从地面到 <code>heights[0] = 3</code>&nbsp;的初始跳跃：<code>(0 - 3)<sup>2</sup> = 9</code>。</li>
	<li>下一次跳跃到&nbsp;<code>heights[1] = 3</code>：<code>(3 - 3)<sup>2</sup> = 0</code>。</li>
</ul>

<p>消耗的总卡路里 = <code>9 + 0 = 9</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == heights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

根据题意，跳跃的顺序会影响总消耗的卡路里数。为了最大化卡路里消耗，我们可以采用贪心策略，优先跳跃高度差较大的块。

因此，我们可以先将块的高度进行排序，然后从最高的块开始跳跃，然后跳到最低的块，依此类推，直到所有块都被跳跃过。

具体步骤如下：

1. 对数组 $\text{heights}$ 进行排序。
1. 初始化变量 $\text{pre} = 0$，表示上一个块的高度，变量 $\text{ans} = 0$，表示总消耗的卡路里数。
1. 使用双指针，左指针 $\text{l}$ 指向数组的开头，右指针 $\text{r}$ 指向数组的结尾。
1. 当 $\text{l} < \text{r}$ 时，执行以下操作：
    1. 计算从上一个块跳到右指针指向的块的卡路里消耗，并将其加入 $\text{ans}$。
    1. 计算从右指针指向的块跳到左指针指向的块的卡路里消耗，并将其加入 $\text{ans}$。
    1. 更新 $\text{pre}$ 为左指针指向的块的高度。
    1. 将左指针向右移动一位，右指针向左移动一位。
1. 最后，计算从上一个块跳到中间块的卡路里消耗，并将其加入 $\text{ans}$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCaloriesBurnt(self, heights: list[int]) -> int:
        heights.sort()
        pre = 0
        l, r = 0, len(heights) - 1
        ans = 0
        while l < r:
            ans += (heights[r] - pre) ** 2
            ans += (heights[l] - heights[r]) ** 2
            pre = heights[l]
            l, r = l + 1, r - 1
        ans += (heights[r] - pre) ** 2
        return ans
```

#### Java

```java
class Solution {
    public long maxCaloriesBurnt(int[] heights) {
        Arrays.sort(heights);
        long ans = 0;
        int pre = 0;
        int r = heights.length - 1;
        for (int l = 0; l < r; ++l, --r) {
            ans += 1L * (heights[r] - pre) * (heights[r] - pre);
            ans += 1L * (heights[l] - heights[r]) * (heights[l] - heights[r]);
            pre = heights[l];
        }
        ans += 1L * (heights[r] - pre) * (heights[r] - pre);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxCaloriesBurnt(vector<int>& heights) {
        ranges::sort(heights);
        long long ans = 0;
        int pre = 0;
        int r = heights.size() - 1;
        for (int l = 0; l < r; ++l, --r) {
            ans += 1LL * (heights[r] - pre) * (heights[r] - pre);
            ans += 1LL * (heights[l] - heights[r]) * (heights[l] - heights[r]);
            pre = heights[l];
        }
        ans += 1LL * (heights[r] - pre) * (heights[r] - pre);
        return ans;
    }
};
```

#### Go

```go
func maxCaloriesBurnt(heights []int) (ans int64) {
	sort.Ints(heights)
	pre := 0
	l, r := 0, len(heights)-1
	for l < r {
		ans += int64(heights[r]-pre) * int64(heights[r]-pre)
		ans += int64(heights[l]-heights[r]) * int64(heights[l]-heights[r])
		pre = heights[l]
		l++
		r--
	}
	ans += int64(heights[r]-pre) * int64(heights[r]-pre)
	return
}
```

#### TypeScript

```ts
function maxCaloriesBurnt(heights: number[]): number {
    heights.sort((a, b) => a - b);
    let ans = 0;
    let pre = 0;
    let [l, r] = [0, heights.length - 1];
    while (l < r) {
        ans += (heights[r] - pre) ** 2;
        ans += (heights[l] - heights[r]) ** 2;
        pre = heights[l];
        l++;
        r--;
    }
    ans += (heights[r] - pre) ** 2;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
