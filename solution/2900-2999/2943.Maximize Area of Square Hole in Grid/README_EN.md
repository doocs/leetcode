---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README_EN.md
rating: 1677
source: Biweekly Contest 118 Q2
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [2943. Maximize Area of Square Hole in Grid](https://leetcode.com/problems/maximize-area-of-square-hole-in-grid)

[中文文档](/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given the two integers, <code>n</code> and <code>m</code> and two integer arrays, <code>hBars</code> and <code>vBars</code>. The grid has <code>n + 2</code> horizontal and <code>m + 2</code> vertical bars, creating 1 x 1 unit cells. The bars are indexed starting from <code>1</code>.</p>

<p>You can <strong>remove</strong> some of the bars in <code>hBars</code> from horizontal bars and some of the bars in <code>vBars</code> from vertical bars. Note that other bars are fixed and cannot be removed.</p>

<p>Return an integer denoting the <strong>maximum area</strong> of a <em>square-shaped</em> hole in the grid, after removing some bars (possibly none).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-05-22-40-25.png" style="width: 411px; height: 220px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">n = 2, m = 1, hBars = [2,3], vBars = [2]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The left image shows the initial grid formed by the bars. The horizontal bars are <code>[1,2,3,4]</code>, and the vertical bars are&nbsp;<code>[1,2,3]</code>.</p>

<p>One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/screenshot-from-2023-11-04-17-01-02.png" style="width: 368px; height: 145px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">n = 1, m = 1, hBars = [2], vBars = [2]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>Explanation:</strong></p>

<p>To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2943.Maximize%20Area%20of%20Square%20Hole%20in%20Grid/images/unsaved-image-2.png" style="width: 648px; height: 218px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">n = 2, m = 3, hBars = [2,3], vBars = [2,4]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">4</span></p>

<p><strong>Explanation:</strong></p>

<p><span style="color: var(--text-secondary); font-size: 0.875rem;">One way to get the maximum square-shaped hole is by removing horizontal bar 3, and vertical bar 4.</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= hBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= hBars[i] &lt;= n + 1</code></li>
	<li><code>1 &lt;= vBars.length &lt;= 100</code></li>
	<li><code>2 &lt;= vBars[i] &lt;= m + 1</code></li>
	<li>All values in <code>hBars</code> are distinct.</li>
	<li>All values in <code>vBars</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

The problem essentially asks us to find the length of the longest consecutive increasing subsequence in the array, and then add 1 to it.

We define a function $f(nums)$, which represents the length of the longest consecutive increasing subsequence in the array $nums$.

For the array $nums$, we first sort it, then traverse the array. If the current element $nums[i]$ equals the previous element $nums[i - 1]$ plus 1, it means that the current element can be added to the consecutive increasing subsequence. Otherwise, it means that the current element cannot be added to the consecutive increasing subsequence, and we need to start calculating the length of the consecutive increasing subsequence again. Finally, we return the length of the consecutive increasing subsequence plus 1.

After finding the length of the longest consecutive increasing subsequence in $hBars$ and $vBars$, we take the minimum of the two as the side length of the square, and then calculate the area of the square.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $hBars$ or $vBars$.

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
