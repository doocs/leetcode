---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0011.Container%20With%20Most%20Water/README_EN.md
tags:
    - Greedy
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)

[中文文档](/solution/0000-0099/0011.Container%20With%20Most%20Water/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>height</code> of length <code>n</code>. There are <code>n</code> vertical lines drawn such that the two endpoints of the <code>i<sup>th</sup></code> line are <code>(i, 0)</code> and <code>(i, height[i])</code>.</p>

<p>Find two lines that together with the x-axis form a container, such that the container contains the most water.</p>

<p>Return <em>the maximum amount of water a container can store</em>.</p>

<p><strong>Notice</strong> that you may not slant the container.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0011.Container%20With%20Most%20Water/images/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Input:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Output:</strong> 49
<strong>Explanation:</strong> The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [1,1]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers $l$ and $r$ to point to the left and right ends of the array, respectively, i.e., $l = 0$ and $r = n - 1$, where $n$ is the length of the array.

Next, we use a variable $\textit{ans}$ to record the maximum capacity of the container, initially set to $0$.

Then, we start a loop. In each iteration, we calculate the current capacity of the container, i.e., $\textit{min}(height[l], height[r]) \times (r - l)$, and compare it with $\textit{ans}$, assigning the larger value to $\textit{ans}$. Then, we compare the values of $height[l]$ and $height[r]$. If $\textit{height}[l] < \textit{height}[r]$, moving the $r$ pointer will not improve the result because the height of the container is determined by the shorter vertical line, so we move the $l$ pointer. Otherwise, we move the $r$ pointer.

After the iteration, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{height}$. The space complexity is $O(1)$.

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
