---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3861.Minimum%20Capacity%20Box/README.md
rating: 1154
source: 第 492 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3861. 容量最小的箱子](https://leetcode.cn/problems/minimum-capacity-box)

[English Version](/solution/3800-3899/3861.Minimum%20Capacity%20Box/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>capacity</code>，其中 <code>capacity[i]</code> 表示第 <code>i</code>&nbsp;个箱子的容量，以及一个整数 <code>itemSize</code>，表示一个物品的大小。</p>

<p>如果第 <code>i</code>&nbsp;个箱子的容量满足 <code>capacity[i] &gt;= itemSize</code>，那么该箱子可以存放该物品。</p>

<p>要求返回可以存放该物品的容量 <strong>最小</strong> 的箱子的下标。如果有多个这样的箱子，返回下标&nbsp;<strong>最小</strong> 的一个。</p>

<p>如果没有任何箱子可以存放该物品，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">capacity = [1,5,3,7], itemSize = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>下标为 2 的箱子容量为 3，是可以存放该物品的容量最小的箱子，因此答案是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">capacity = [3,5,4,3], itemSize = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>可以存放该物品的最小容量为 3，出现在下标 0 和 3。由于下标 0 更小，因此答案是 0。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">capacity = [4], itemSize = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>没有任何箱子的容量足够存放该物品，因此答案是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= capacity.length &lt;= 100</code></li>
	<li><code>1 &lt;= capacity[i] &lt;= 100</code></li>
	<li><code>1 &lt;= itemSize &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们初始化一个变量 $\textit{ans}$，表示可以存放该物品的容量最小的箱子的下标，初始值为 $-1$。我们遍历数组 $\textit{capacity}$，对于每个箱子，如果它的容量大于等于 $\textit{itemSize}$，则说明它可以存放该物品。此时，我们需要判断它是否是目前为止容量最小的箱子，如果是，则更新 $\textit{ans}$ 的值。最后返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{capacity}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumIndex(self, capacity: list[int], itemSize: int) -> int:
        ans = -1
        for i, x in enumerate(capacity):
            if x >= itemSize and (ans == -1 or x < capacity[ans]):
                ans = i
        return ans
```

#### Java

```java
class Solution {
    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.length; ++i) {
            int x = capacity[i];
            if (x >= itemSize && (ans == -1 || x < capacity[ans])) {
                ans = i;
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
    int minimumIndex(vector<int>& capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.size(); ++i) {
            int x = capacity[i];
            if (x >= itemSize && (ans == -1 || x < capacity[ans])) {
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumIndex(capacity []int, itemSize int) int {
	ans := -1
	for i, x := range capacity {
		if x >= itemSize && (ans == -1 || x < capacity[ans]) {
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minimumIndex(capacity: number[], itemSize: number): number {
    let ans = -1;
    for (let i = 0; i < capacity.length; ++i) {
        const x = capacity[i];
        if (x >= itemSize && (ans === -1 || x < capacity[ans])) {
            ans = i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
