---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/README.md
rating: 1240
source: 第 268 场周赛 Q1
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2078. 两栋颜色不同且距离最远的房子](https://leetcode.cn/problems/two-furthest-houses-with-different-colors)

[English Version](/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>街上有 <code>n</code> 栋房子整齐地排成一列，每栋房子都粉刷上了漂亮的颜色。给你一个下标从 <strong>0</strong> 开始且长度为 <code>n</code> 的整数数组 <code>colors</code> ，其中 <code>colors[i]</code> 表示第&nbsp; <code>i</code> 栋房子的颜色。</p>

<p>返回 <strong>两栋</strong> 颜色 <strong>不同</strong> 房子之间的 <strong>最大</strong> 距离。</p>

<p>第 <code>i</code> 栋房子和第 <code>j</code> 栋房子之间的距离是 <code>abs(i - j)</code> ，其中 <code>abs(x)</code> 是 <code>x</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg1.png" style="width: 610px; height: 84px;" /></p>

<pre>
<strong>输入：</strong>colors = [<strong><em>1</em></strong>,1,1,<em><strong>6</strong></em>,1,1,1]
<strong>输出：</strong>3
<strong>解释：</strong>上图中，颜色 1 标识成蓝色，颜色 6 标识成红色。
两栋颜色不同且距离最远的房子是房子 0 和房子 3 。
房子 0 的颜色是颜色 1 ，房子 3 的颜色是颜色 6 。两栋房子之间的距离是 abs(0 - 3) = 3 。
注意，房子 3 和房子 6 也可以产生最佳答案。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg2.png" style="width: 426px; height: 84px;" /></p>

<pre>
<strong>输入：</strong>colors = [<em><strong>1</strong></em>,8,3,8,<em><strong>3</strong></em>]
<strong>输出：</strong>4
<strong>解释：</strong>上图中，颜色 1 标识成蓝色，颜色 8 标识成黄色，颜色 3 标识成绿色。
两栋颜色不同且距离最远的房子是房子 0 和房子 4 。
房子 0 的颜色是颜色 1 ，房子 4 的颜色是颜色 3 。两栋房子之间的距离是 abs(0 - 4) = 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>colors = [<em><strong>0</strong></em>,<em><strong>1</strong></em>]
<strong>输出：</strong>1
<strong>解释：</strong>两栋颜色不同且距离最远的房子是房子 0 和房子 1 。
房子 0 的颜色是颜色 0 ，房子 1 的颜色是颜色 1 。两栋房子之间的距离是 abs(0 - 1) = 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n ==&nbsp;colors.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= colors[i] &lt;= 100</code></li>
	<li>生成的测试数据满足 <strong>至少 </strong>存在 2 栋颜色不同的房子</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以发现，如果第一栋房子和最后一栋房子颜色不同，那么它们之间的距离就是最大的距离，即 $n - 1$。

如果第一栋房子和最后一栋房子颜色相同，那么我们可以从左向右找到第一栋颜色不同的房子，记为 $i$，从右向左找到第一栋颜色不同的房子，记为 $j$，那么最大的距离就是 $\max(n - i - 1, j)$。

时间复杂度 $O(n)$，其中 $n$ 是房子的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, colors: List[int]) -> int:
        n = len(colors)
        if colors[0] != colors[-1]:
            return n - 1
        i, j = 1, n - 2
        while colors[i] == colors[0]:
            i += 1
        while colors[j] == colors[0]:
            j -= 1
        return max(n - i - 1, j)
```

#### Java

```java
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return Math.max(n - i - 1, j);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return max(n - i - 1, j);
    }
};
```

#### Go

```go
func maxDistance(colors []int) int {
	n := len(colors)
	if colors[0] != colors[n-1] {
		return n - 1
	}
	i, j := 1, n-2
	for colors[i] == colors[0] {
		i++
	}
	for colors[j] == colors[0] {
		j--
	}
	return max(n-i-1, j)
}
```

#### TypeScript

```ts
function maxDistance(colors: number[]): number {
    const n = colors.length;
    if (colors[0] !== colors[n - 1]) {
        return n - 1;
    }
    let [i, j] = [1, n - 2];
    while (colors[i] === colors[0]) {
        i++;
    }
    while (colors[j] === colors[0]) {
        j--;
    }
    return Math.max(n - i - 1, j);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_distance(colors: Vec<i32>) -> i32 {
        let n = colors.len();
        if colors[0] != colors[n - 1] {
            return (n - 1) as i32;
        }
        let mut i = 1;
        while colors[i] == colors[0] {
            i += 1;
        }
        let mut j = n - 2;
        while colors[j] == colors[0] {
            j -= 1;
        }
        std::cmp::max((n - i - 1) as i32, j as i32)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
