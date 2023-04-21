# [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water)

[English Version](/solution/0000-0099/0011.Container%20With%20Most%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

一开始，我们考虑相距最远的两个柱子所能容纳水的容量。水的宽度是两根柱子之间的距离，而水的高度取决于两根柱子之间较短的那个。

当前柱子是最两侧的柱子，水的宽度最大，其他的组合，水的宽度都比这个小。不妨假设左侧柱子的高度小于等于右侧柱子的高度，那么水的高度就是左侧柱子的高度。如果我们移动右侧柱子，那么水的宽度就减小了，而水的高度却不会增加，因此水的容量一定减少。所以我们移动左侧柱子，更新最大容量。

循环此过程，直到两个柱子相遇。

时间复杂度 $O(n)$，其中 $n$ 是数组 `height` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        i, j = 0, len(height) - 1
        ans = 0
        while i < j:
            t = (j - i) * min(height[i], height[j])
            ans = max(ans, t)
            if height[i] < height[j]:
                i += 1
            else:
                j -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxArea(vector<int>& height) {
        int i = 0, j = height.size() - 1;
        int ans = 0;
        while (i < j) {
            int t = min(height[i], height[j]) * (j - i);
            ans = max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxArea(height []int) (ans int) {
	i, j := 0, len(height)-1
	for i < j {
		t := min(height[i], height[j]) * (j - i)
		ans = max(ans, t)
		if height[i] < height[j] {
			i++
		} else {
			j--
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
    let i = 0;
    let j = height.length - 1;
    let ans = 0;
    while (i < j) {
        const t = Math.min(height[i], height[j]) * (j - i);
        ans = Math.max(ans, t);
        if (height[i] < height[j]) {
            ++i;
        } else {
            --j;
        }
    }
    return ans;
};
```

### **TypeScript**

```ts
function maxArea(height: number[]): number {
    let i = 0;
    let j = height.length - 1;
    let ans = 0;
    while (i < j) {
        const t = Math.min(height[i], height[j]) * (j - i);
        ans = Math.max(ans, t);
        if (height[i] < height[j]) {
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int MaxArea(int[] height) {
        int i = 0, j = height.Length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.Min(height[i], height[j]) * (j - i);
            ans = Math.Max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = height.len() - 1;
        let mut res = 0;
        while i < j {
            res = res.max(height[i].min(height[j]) * (j - i) as i32);
            if height[i] <= height[j] {
                i += 1;
            } else {
                j -= 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
