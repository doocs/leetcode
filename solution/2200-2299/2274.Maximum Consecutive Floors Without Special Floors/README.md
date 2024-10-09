---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2274.Maximum%20Consecutive%20Floors%20Without%20Special%20Floors/README.md
rating: 1332
source: 第 293 场周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [2274. 不含特殊楼层的最大连续楼层数](https://leetcode.cn/problems/maximum-consecutive-floors-without-special-floors)

[English Version](/solution/2200-2299/2274.Maximum%20Consecutive%20Floors%20Without%20Special%20Floors/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice 决定将一些楼层作为 <strong>特殊楼层</strong> ，仅用于放松。</p>

<p>给你两个整数 <code>bottom</code> 和 <code>top</code> ，表示 Alice 租用了从 <code>bottom</code> 到 <code>top</code>（含 <code>bottom</code> 和 <code>top</code> 在内）的所有楼层。另给你一个整数数组 <code>special</code> ，其中 <code>special[i]</code> 表示&nbsp; Alice 指定用于放松的特殊楼层。</p>

<p>返回不含特殊楼层的 <strong>最大</strong> 连续楼层数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>bottom = 2, top = 9, special = [4,6]
<strong>输出：</strong>3
<strong>解释：</strong>下面列出的是不含特殊楼层的连续楼层范围：
- (2, 3) ，楼层数为 2 。
- (5, 5) ，楼层数为 1 。
- (7, 9) ，楼层数为 3 。
因此，返回最大连续楼层数 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>bottom = 6, top = 8, special = [7,6,8]
<strong>输出：</strong>0
<strong>解释：</strong>每层楼都被规划为特殊楼层，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示</strong></p>

<ul>
	<li><code>1 &lt;= special.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= bottom &lt;= special[i] &lt;= top &lt;= 10<sup>9</sup></code></li>
	<li><code>special</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们可以将特殊楼层按照升序排序，然后计算相邻两个特殊楼层之间的楼层数，最后再计算第一个特殊楼层和 $\textit{bottom}$ 之间的楼层数，以及最后一个特殊楼层和 $\textit{top}$ 之间的楼层数，取这些楼层数的最大值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{special}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxConsecutive(self, bottom: int, top: int, special: List[int]) -> int:
        special.sort()
        ans = max(special[0] - bottom, top - special[-1])
        for x, y in pairwise(special):
            ans = max(ans, y - x - 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int n = special.length;
        int ans = Math.max(special[0] - bottom, top - special[n - 1]);
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, special[i] - special[i - 1] - 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxConsecutive(int bottom, int top, vector<int>& special) {
        ranges::sort(special);
        int ans = max(special[0] - bottom, top - special.back());
        for (int i = 1; i < special.size(); ++i) {
            ans = max(ans, special[i] - special[i - 1] - 1);
        }
        return ans;
    }
};
```

#### Go

```go
func maxConsecutive(bottom int, top int, special []int) int {
	sort.Ints(special)
	ans := max(special[0]-bottom, top-special[len(special)-1])
	for i, x := range special[1:] {
		ans = max(ans, x-special[i]-1)
	}
	return ans
}
```

#### TypeScript

```ts
function maxConsecutive(bottom: number, top: number, special: number[]): number {
    special.sort((a, b) => a - b);
    const n = special.length;
    let ans = Math.max(special[0] - bottom, top - special[n - 1]);
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, special[i] - special[i - 1] - 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
