---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0011.Container%20With%20Most%20Water/README.md
tags:
    - 贪心
    - 数组
    - 双指针
---

<!-- problem:start -->

# [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water)

[English Version](/solution/0000-0099/0011.Container%20With%20Most%20Water/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>height</code>&nbsp;。有&nbsp;<code>n</code>&nbsp;条垂线，第 <code>i</code> 条线的两个端点是&nbsp;<code>(i, 0)</code>&nbsp;和&nbsp;<code>(i, height[i])</code>&nbsp;。</p>

<p>找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>

<p>返回容器可以储存的最大水量。</p>

<p><strong>说明：</strong>你不能倾斜容器。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0011.Container%20With%20Most%20Water/images/question_11.jpg" /></p>

<pre>
<strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
<strong>输出：</strong>49 
<strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [1,1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们使用两个指针 $l$ 和 $r$ 分别指向数组的左右两端，即 $l = 0$，而 $r = n - 1$，其中 $n$ 是数组的长度。

接下来，我们使用变量 $\textit{ans}$ 记录容器的最大容量，初始化为 $0$。

然后，我们开始进行循环，每次循环中，我们计算当前容器的容量，即 $\textit{min}(height[l], height[r]) \times (r - l)$，并将其与 $\textit{ans}$ 进行比较，将较大值赋给 $\textit{ans}$。然后，我们判断 $height[l]$ 和 $height[r]$ 的大小，如果 $\textit{height}[l] < \textit{height}[r]$，移动 $r$ 指针不会使得结果变得更好，因为容器的高度由较短的那根垂直线决定，所以我们移动 $l$ 指针。反之，我们移动 $r$ 指针。

遍历结束后，返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{height}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        l, r = 0, len(height) - 1
        ans = 0
        while l < r:
            t = min(height[l], height[r]) * (r - l)
            ans = max(ans, t)
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1
        return ans
```

#### Java

```java
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int t = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
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
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1;
        int ans = 0;
        while (l < r) {
            int t = min(height[l], height[r]) * (r - l);
            ans = max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxArea(height []int) (ans int) {
	l, r := 0, len(height)-1
	for l < r {
		t := min(height[l], height[r]) * (r - l)
		ans = max(ans, t)
		if height[l] < height[r] {
			l++
		} else {
			r--
		}
	}
	return
}
```

#### TypeScript

```ts
function maxArea(height: number[]): number {
    let [l, r] = [0, height.length - 1];
    let ans = 0;
    while (l < r) {
        const t = Math.min(height[l], height[r]) * (r - l);
        ans = Math.max(ans, t);
        if (height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = height.len() - 1;
        let mut ans = 0;
        while l < r {
            ans = ans.max(height[l].min(height[r]) * ((r - l) as i32));
            if height[l] < height[r] {
                l += 1;
            } else {
                r -= 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
    let [l, r] = [0, height.length - 1];
    let ans = 0;
    while (l < r) {
        const t = Math.min(height[l], height[r]) * (r - l);
        ans = Math.max(ans, t);
        if (height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxArea(int[] height) {
        int l = 0, r = height.Length - 1;
        int ans = 0;
        while (l < r) {
            int t = Math.Min(height[l], height[r]) * (r - l);
            ans = Math.Max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $height
     * @return Integer
     */
    function maxArea($height) {
        $l = 0;
        $r = count($height) - 1;
        $ans = 0;
        while ($l < $r) {
            $t = min($height[$l], $height[$r]) * ($r - $l);
            $ans = max($ans, $t);
            if ($height[$l] < $height[$r]) {
                ++$l;
            } else {
                --$r;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
