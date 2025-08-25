---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3000.Maximum%20Area%20of%20Longest%20Diagonal%20Rectangle/README.md
rating: 1249
source: 第 379 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3000. 对角线最长的矩形的面积](https://leetcode.cn/problems/maximum-area-of-longest-diagonal-rectangle)

[English Version](/solution/3000-3099/3000.Maximum%20Area%20of%20Longest%20Diagonal%20Rectangle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从<strong> 0</strong> 开始的二维整数数组 <code>dimensions</code>。</p>

<p>对于所有下标 <code>i</code>（<code>0 &lt;= i &lt; dimensions.length</code>），<code>dimensions[i][0]</code> 表示矩形 <span style="font-size: 13.3333px;"> <code>i</code></span> 的长度，而 <code>dimensions[i][1]</code> 表示矩形 <span style="font-size: 13.3333px;"> <code>i</code></span> 的宽度。</p>

<p>返回对角线最 <strong>长 </strong>的矩形的<strong> 面积 </strong>。如果存在多个对角线长度相同的矩形，返回面积最<strong> 大 </strong>的矩形的面积。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>dimensions = [[9,3],[8,6]]
<strong>输出：</strong>48
<strong>解释：</strong>
下标 = 0，长度 = 9，宽度 = 3。对角线长度 = sqrt(9 * 9 + 3 * 3) = sqrt(90) ≈<!-- notionvc: 882cf44c-3b17-428e-9c65-9940810216f1 --> 9.487。
下标 = 1，长度 = 8，宽度 = 6。对角线长度 = sqrt(8 * 8 + 6 * 6) = sqrt(100) = 10。
因此，下标为 1 的矩形对角线更长，所以返回面积 = 8 * 6 = 48。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>dimensions = [[3,4],[4,3]]
<strong>输出：</strong>12
<strong>解释：</strong>两个矩形的对角线长度相同，为 5，所以最大面积 = 12。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dimensions.length &lt;= 100</code></li>
	<li><code>dimensions[i].length == 2</code></li>
	<li><code>1 &lt;= dimensions[i][0], dimensions[i][1] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据勾股定理，矩形的对角线的平方为 $l^2 + w^2$，其中 $l$ 和 $w$ 分别是矩形的长度和宽度。

我们可以遍历所有矩形，计算它们的对角线长度的平方，并记录下最大的对角线长度和对应的面积。

遍历结束后，我们返回记录的最大面积。

时间复杂度 $O(n)$，其中 $n$ 是矩形的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areaOfMaxDiagonal(self, dimensions: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in dimensions:
            t = l**2 + w**2
            if mx < t:
                mx = t
                ans = l * w
            elif mx == t:
                ans = max(ans, l * w)
        return ans
```

#### Java

```java
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        for (var d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.max(ans, l * w);
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
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        int ans = 0, mx = 0;
        for (auto& d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = max(ans, l * w);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func areaOfMaxDiagonal(dimensions [][]int) (ans int) {
	mx := 0
	for _, d := range dimensions {
		l, w := d[0], d[1]
		t := l*l + w*w
		if mx < t {
			mx = t
			ans = l * w
		} else if mx == t {
			ans = max(ans, l*w)
		}
	}
	return
}
```

#### TypeScript

```ts
function areaOfMaxDiagonal(dimensions: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of dimensions) {
        const t = l * l + w * w;
        if (mx < t) {
            mx = t;
            ans = l * w;
        } else if (mx === t) {
            ans = Math.max(ans, l * w);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn area_of_max_diagonal(dimensions: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for d in dimensions {
            let l = d[0];
            let w = d[1];
            let t = l * l + w * w;
            if mx < t {
                mx = t;
                ans = l * w;
            } else if mx == t {
                ans = ans.max(l * w);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int AreaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        foreach (var d in dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.Max(ans, l * w);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
