---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3200.Maximum%20Height%20of%20a%20Triangle/README.md
rating: 1451
source: 第 404 场周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3200. 三角形的最大高度](https://leetcode.cn/problems/maximum-height-of-a-triangle)

[English Version](/solution/3200-3299/3200.Maximum%20Height%20of%20a%20Triangle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>red</code> 和 <code>blue</code>，分别表示红色球和蓝色球的数量。你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2 个球，第 3 行有 3 个球，依此类推。</p>

<p>每一行的球必须是 <strong>相同 </strong>颜色，且相邻行的颜色必须<strong> 不同</strong>。</p>

<p>返回可以实现的三角形的 <strong>最大 </strong>高度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">red = 2, blue = 4</span></p>

<p><strong>输出：</strong> 3</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3200.Maximum%20Height%20of%20a%20Triangle/images/brb.png" style="width: 300px; height: 240px; padding: 10px;" /></p>

<p>上图显示了唯一可能的排列方式。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">red = 2, blue = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3200.Maximum%20Height%20of%20a%20Triangle/images/br.png" style="width: 150px; height: 135px; padding: 10px;" /><br />
上图显示了唯一可能的排列方式。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">red = 1, blue = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">red = 10, blue = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3200.Maximum%20Height%20of%20a%20Triangle/images/br.png" style="width: 150px; height: 135px; padding: 10px;" /><br />
上图显示了唯一可能的排列方式。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= red, blue &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以枚举第一行的颜色，然后模拟构造三角形，计算最大高度。

时间复杂度 $O(\sqrt{n})$，其中 $n$ 为红色球和蓝色球的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxHeightOfTriangle(self, red: int, blue: int) -> int:
        ans = 0
        for k in range(2):
            c = [red, blue]
            i, j = 1, k
            while i <= c[j]:
                c[j] -= i
                j ^= 1
                ans = max(ans, i)
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        int ans = 0;
        for (int k = 0; k < 2; ++k) {
            int[] c = {red, blue};
            for (int i = 1, j = k; i <= c[j]; j ^= 1, ++i) {
                c[j] -= i;
                ans = Math.max(ans, i);
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
    int maxHeightOfTriangle(int red, int blue) {
        int ans = 0;
        for (int k = 0; k < 2; ++k) {
            int c[2] = {red, blue};
            for (int i = 1, j = k; i <= c[j]; j ^= 1, ++i) {
                c[j] -= i;
                ans = max(ans, i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxHeightOfTriangle(red int, blue int) (ans int) {
	for k := 0; k < 2; k++ {
		c := [2]int{red, blue}
		for i, j := 1, k; i <= c[j]; i, j = i+1, j^1 {
			c[j] -= i
			ans = max(ans, i)
		}
	}
	return
}
```

#### TypeScript

```ts
function maxHeightOfTriangle(red: number, blue: number): number {
    let ans = 0;
    for (let k = 0; k < 2; ++k) {
        const c: [number, number] = [red, blue];
        for (let i = 1, j = k; i <= c[j]; ++i, j ^= 1) {
            c[j] -= i;
            ans = Math.max(ans, i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
