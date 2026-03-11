---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README.md
rating: 1677
source: 第 118 场双周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [2943. 最大化网格图中正方形空洞的面积](https://leetcode.cn/problems/maximize-area-of-square-hole-in-grid)

[English Version](/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>m</code>，以及两个整数数组 <code>hBars</code> 和 <code>vBars</code>。网格由 <code>n + 2</code> 条水平线和 <code>m + 2</code> 条竖直线组成，形成 1x1 的单元格。网格中的线条从 <code>1</code> 开始编号。</p>

<p>你可以从 <code>hBars</code> 中&nbsp;<strong>删除</strong> 一些水平线条，并从 <code>vBars</code> 中删除一些竖直线条。注意，其他线条是固定的，无法删除。</p>

<p>返回一个整数表示移除一些线条（可以不移除任何线条）后，网格中<strong>&nbsp;正方形空洞的最大面积&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-40-25.png" style="width: 411px; height: 220px;" /></p>

<div class="example-block">
<p><strong>输入: </strong><span class="example-io">n = 2, m = 1, hBars = [2,3], vBars = [2]</span></p>

<p><strong>输出: </strong><span class="example-io">4</span></p>

<p><strong>解释:</strong></p>

<p>左侧图片展示了网格的初始状态。水平线是 <code>[1,2,3,4]</code>，竖直线是 <code>[1,2,3]</code>。</p>

<p>构造最大正方形空洞的一种方法是移除水平线 2 和竖直线 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-04-17-01-02.png" style="width: 368px; height: 145px;" /></p>

<div class="example-block">
<p><strong>输入: </strong><span class="example-io">n = 1, m = 1, hBars = [2], vBars = [2]</span></p>

<p><strong>输出: </strong><span class="example-io">4</span></p>

<p><strong>解释:</strong></p>

<p>移除水平线 2 和竖直线 2，可以得到最大正方形空洞。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/unsaved-image-2.png" style="width: 648px; height: 218px;" /></p>

<div class="example-block">
<p><strong>输入: </strong><span class="example-io">n = 2, m = 3, hBars = [2,3], vBars = [2,4]</span></p>

<p><strong>输出: </strong><span class="example-io">4</span></p>

<p><strong>解释:</strong></p>

<p>构造最大正方形空洞的一种方法是移除水平线 3 和竖直线 4。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= hBars[i] &lt;= n + 1</code></li>
	<li><code>1 &lt;= vBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= vBars[i] &lt;= m + 1</code></li>
	<li><code>hBars</code> 中所有值互不相同。</li>
	<li><code>vBars</code> 中所有值互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

题目实际上要我们找出数组中最长的连续递增子序列的长度，然后再加上 $1$。

我们定义一个函数 $f(\textit{nums})$，表示数组 $\textit{nums}$ 中最长的连续递增子序列的长度。

对于数组 $\textit{nums}$，我们先对其进行排序，然后遍历数组，如果当前元素 $\textit{nums}[i]$ 等于前一个元素 $\textit{nums}[i - 1]$ 加 $1$，则说明当前元素可以加入到连续递增子序列中，否则，说明当前元素不能加入到连续递增子序列中，我们需要重新开始计算连续递增子序列的长度。最后，我们返回连续递增子序列的长度加 $1$。

我们在求出 $\textit{hBars}$ 和 $\textit{vBars}$ 中最长的连续递增子序列的长度之后，我们取两者中的最小值作为正方形的边长，然后再求出正方形的面积即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{hBars}$ 或 $\textit{vBars}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximizeSquareHoleArea(
        self, n: int, m: int, hBars: List[int], vBars: List[int]
    ) -> int:
        def f(nums: List[int]) -> int:
            nums.sort()
            ans = cnt = 1
            for i in range(1, len(nums)):
                if nums[i] == nums[i - 1] + 1:
                    cnt += 1
                    ans = max(ans, cnt)
                else:
                    cnt = 1
            return ans + 1

        return min(f(hBars), f(vBars)) ** 2
```

#### Java

```java
class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int x = Math.min(f(hBars), f(vBars));
        return x * x;
    }

    private int f(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, cnt = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximizeSquareHoleArea(int n, int m, vector<int>& hBars, vector<int>& vBars) {
        auto f = [](vector<int>& nums) {
            int ans = 1, cnt = 1;
            sort(nums.begin(), nums.end());
            for (int i = 1; i < nums.size(); ++i) {
                if (nums[i] == nums[i - 1] + 1) {
                    ans = max(ans, ++cnt);
                } else {
                    cnt = 1;
                }
            }
            return ans + 1;
        };
        int x = min(f(hBars), f(vBars));
        return x * x;
    }
};
```

#### Go

```go
func maximizeSquareHoleArea(n int, m int, hBars []int, vBars []int) int {
	f := func(nums []int) int {
		sort.Ints(nums)
		ans, cnt := 1, 1
		for i, x := range nums[1:] {
			if x == nums[i]+1 {
				cnt++
				ans = max(ans, cnt)
			} else {
				cnt = 1
			}
		}
		return ans + 1
	}
	x := min(f(hBars), f(vBars))
	return x * x
}
```

#### TypeScript

```ts
function maximizeSquareHoleArea(n: number, m: number, hBars: number[], vBars: number[]): number {
    const f = (nums: number[]): number => {
        nums.sort((a, b) => a - b);
        let [ans, cnt] = [1, 1];
        for (let i = 1; i < nums.length; ++i) {
            if (nums[i] === nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    };
    return Math.min(f(hBars), f(vBars)) ** 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximize_square_hole_area(n: i32, m: i32, h_bars: Vec<i32>, v_bars: Vec<i32>) -> i32 {
        let f = |nums: &mut Vec<i32>| -> i32 {
            let mut ans = 1;
            let mut cnt = 1;
            nums.sort();
            for i in 1..nums.len() {
                if nums[i] == nums[i - 1] + 1 {
                    cnt += 1;
                    ans = ans.max(cnt);
                } else {
                    cnt = 1;
                }
            }
            ans + 1
        };

        let mut h_bars = h_bars;
        let mut v_bars = v_bars;
        let x = f(&mut h_bars).min(f(&mut v_bars));
        x * x
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
