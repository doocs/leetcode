---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3730.Maximum%20Calories%20Burnt%20from%20Jumps/README.md
---

<!-- problem:start -->

# [3730. Maximum Calories Burnt from Jumps 🔒](https://leetcode.cn/problems/maximum-calories-burnt-from-jumps)

[English Version](/solution/3700-3799/3730.Maximum%20Calories%20Burnt%20from%20Jumps/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer array <code>heights</code> of size <code>n</code>, where <code>heights[i]</code> represents the height of the <code>i<sup>th</sup></code> block in an exercise routine.</p>

<p>You start on the ground (height 0) and <strong>must</strong> jump onto each block <strong>exactly once</strong> in any order.</p>

<ul>
	<li>The <strong>calories burned</strong> for a jump from a block of height <code>a</code> to a block of height <code>b</code> is <code>(a - b)<sup>2</sup></code>.</li>
	<li>The <strong>calories burned</strong> for the first jump from the ground to the chosen first block <code>heights[i]</code> is <code>(0 - heights[i])<sup>2</sup></code>.</li>
</ul>

<p>Return the <strong>maximum</strong> total calories you can burn by selecting an optimal jumping sequence.</p>

<p><strong>Note:</strong> Once you jump onto the first block, you cannot return to the ground.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">heights = [1,7,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">181</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<p>The optimal sequence is <code>[9, 1, 7]</code>.</p>

<ul>
	<li>Initial jump from the ground to <code>heights[2] = 9</code>: <code>(0 - 9)<sup>2</sup> = 81</code>.</li>
	<li>Next jump to <code>heights[0] = 1</code>: <code>(9 - 1)<sup>2</sup> = 64</code>.</li>
	<li>Final jump to <code>heights[1] = 7</code>: <code>(1 - 7)<sup>2</sup> = 36</code>.</li>
</ul>

<p>Total calories burned = <code>81 + 64 + 36 = 181</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">heights = [5,2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">38</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal sequence is <code>[5, 2, 4]</code>.</p>

<ul>
	<li>Initial jump from the ground to <code>heights[0] = 5</code>: <code>(0 - 5)<sup>2</sup> = 25</code>.</li>
	<li>Next jump to <code>heights[1] = 2</code>: <code>(5 - 2)<sup>2</sup> = 9</code>.</li>
	<li>Final jump to <code>heights[2] = 4</code>: <code>(2 - 4)<sup>2</sup> = 4</code>.</li>
</ul>

<p>Total calories burned = <code>25 + 9 + 4 = 38</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">heights = [3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal sequence is <code>[3, 3]</code>.</p>

<ul>
	<li>Initial jump from the ground to <code>heights[0] = 3</code>: <code>(0 - 3)<sup>2</sup> = 9</code>.</li>
	<li>Next jump to <code>heights[1] = 3</code>: <code>(3 - 3)<sup>2</sup> = 0</code>.</li>
</ul>

<p>Total calories burned = <code>9 + 0 = 9</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
