# [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water)

[English Version](/solution/0000-0099/0042.Trapping%20Rain%20Water/README_EN.md)

<!-- tags:栈,数组,双指针,动态规划,单调栈 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定&nbsp;<code>n</code> 个非负整数表示每个宽度为 <code>1</code> 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0042.Trapping%20Rain%20Water/images/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>

<pre>
<strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出：</strong>6
<strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [4,2,0,3,2,5]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $left[i]$ 表示下标 $i$ 位置及其左边的最高柱子的高度，定义 $right[i]$ 表示下标 $i$ 位置及其右边的最高柱子的高度。那么下标 $i$ 位置能接的雨水量为 $\min(left[i], right[i]) - height[i]$。我们遍历数组，计算出 $left[i]$ 和 $right[i]$，最后答案为 $\sum_{i=0}^{n-1} \min(left[i], right[i]) - height[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        left = [height[0]] * n
        right = [height[-1]] * n
        for i in range(1, n):
            left[i] = max(left[i - 1], height[i])
            right[n - i - 1] = max(right[n - i], height[n - i - 1])
        return sum(min(l, r) - h for l, r, h in zip(left, right, height))
```

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        int left[n], right[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = max(left[i - 1], height[i]);
            right[n - i - 1] = max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += min(left[i], right[i]) - height[i];
        }
        return ans;
    }
};
```

```go
func trap(height []int) (ans int) {
	n := len(height)
	left := make([]int, n)
	right := make([]int, n)
	left[0], right[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], height[i])
		right[n-i-1] = max(right[n-i], height[n-i-1])
	}
	for i, h := range height {
		ans += min(left[i], right[i]) - h
	}
	return
}
```

```ts
function trap(height: number[]): number {
    const n = height.length;
    const left: number[] = new Array(n).fill(height[0]);
    const right: number[] = new Array(n).fill(height[n - 1]);
    for (let i = 1; i < n; ++i) {
        left[i] = Math.max(left[i - 1], height[i]);
        right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(left[i], right[i]) - height[i];
    }
    return ans;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn trap(height: Vec<i32>) -> i32 {
        let n = height.len();
        let mut left: Vec<i32> = vec![0; n];
        let mut right: Vec<i32> = vec![0; n];

        left[0] = height[0];
        right[n - 1] = height[n - 1];

        // Initialize the left & right vector
        for i in 1..n {
            left[i] = std::cmp::max(left[i - 1], height[i]);
            right[n - i - 1] = std::cmp::max(right[n - i], height[n - i - 1]);
        }

        let mut ans = 0;

        // Calculate the ans
        for i in 0..n {
            ans += std::cmp::min(left[i], right[i]) - height[i];
        }

        ans
    }
}
```

```cs
public class Solution {
    public int Trap(int[] height) {
        int n = height.Length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.Max(left[i - 1], height[i]);
            right[n - i - 1] = Math.Max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

```php
class Solution {
    /**
     * @param integer[] $height
     * @return integer
     */

    function trap($height) {
        $n = count($height);

        if ($n == 0) {
            return 0;
        }

        $left = 0;
        $right = $n - 1;
        $leftMax = 0;
        $rightMax = 0;
        $ans = 0;

        while ($left < $right) {
            if ($height[$left] < $height[$right]) {
                if ($height[$left] > $leftMax) {
                    $leftMax = $height[$left];
                } else {
                    $ans += $leftMax - $height[$left];
                }
                $left++;
            } else {
                if ($height[$right] > $rightMax) {
                    $rightMax = $height[$right];
                } else {
                    $ans += $rightMax - $height[$right];
                }
                $right--;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
