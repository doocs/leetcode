---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3668.Restore%20Finishing%20Order/README.md
rating: 1255
source: 第 465 场周赛 Q1
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3668. 重排完成顺序](https://leetcode.cn/problems/restore-finishing-order)

[English Version](/solution/3600-3699/3668.Restore%20Finishing%20Order/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>order</code> 和一个整数数组 <code>friends</code>。</p>

<ul>
	<li><code>order</code> 包含从 1 到 <code>n</code> 的每个整数，且&nbsp;<strong>恰好出现一次&nbsp;</strong>，表示比赛中参赛者按照&nbsp;<strong>完成顺序&nbsp;</strong>的 ID。</li>
	<li><code>friends</code> 包含你朋友们的 ID，按照&nbsp;<strong>严格递增&nbsp;</strong>的顺序排列。<code>friends</code> 中的每个 ID 都保证出现在 <code>order</code> 数组中。</li>
</ul>

<p>请返回一个数组，包含你朋友们的 ID，按照他们的&nbsp;<strong>完成顺序&nbsp;</strong>排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">order = [3,1,2,5,4], friends = [1,3,4]</span></p>

<p><strong>输出：</strong><span class="example-io">[3,1,4]</span></p>

<p><strong>解释：</strong></p>

<p>完成顺序是 <code>[<u><strong>3</strong></u>, <u><strong>1</strong></u>, 2, 5, <u><strong>4</strong></u>]</code>。因此，你朋友的完成顺序是 <code>[3, 1, 4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">order = [1,4,5,3,2], friends = [2,5]</span></p>

<p><strong>输出：</strong><span class="example-io">[5,2]</span></p>

<p><strong>解释：</strong></p>

<p>完成顺序是 <code>[1, 4, <u><strong>5</strong></u>, 3, <u><strong>2</strong></u>]</code>。因此，你朋友的完成顺序是 <code>[5, 2]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == order.length &lt;= 100</code></li>
	<li><code>order</code> 包含从 1 到 <code>n</code> 的每个整数，且恰好出现一次</li>
	<li><code>1 &lt;= friends.length &lt;= min(8, n)</code></li>
	<li><code>1 &lt;= friends[i] &lt;= n</code></li>
	<li><code>friends</code> 是严格递增的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

我们先根据 $\textit{order}$ 数组构建一个映射，记录每个 ID 的完成顺序。然后对 $\textit{friends}$ 数组进行排序，排序的依据就是这些 ID 在 $\textit{order}$ 中的完成顺序。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{order}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def recoverOrder(self, order: List[int], friends: List[int]) -> List[int]:
        d = {x: i for i, x in enumerate(order)}
        return sorted(friends, key=lambda x: d[x])
```

#### Java

```java
class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        int n = order.length;
        int[] d = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            d[order[i]] = i;
        }
        return Arrays.stream(friends)
            .boxed()
            .sorted((a, b) -> d[a] - d[b])
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> recoverOrder(vector<int>& order, vector<int>& friends) {
        int n = order.size();
        vector<int> d(n + 1);
        for (int i = 0; i < n; ++i) {
            d[order[i]] = i;
        }
        sort(friends.begin(), friends.end(), [&](int a, int b) {
            return d[a] < d[b];
        });
        return friends;
    }
};
```

#### Go

```go
func recoverOrder(order []int, friends []int) []int {
	n := len(order)
	d := make([]int, n+1)
	for i, x := range order {
		d[x] = i
	}
	sort.Slice(friends, func(i, j int) bool {
		return d[friends[i]] < d[friends[j]]
	})
	return friends
}
```

#### TypeScript

```ts
function recoverOrder(order: number[], friends: number[]): number[] {
    const n = order.length;
    const d: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        d[order[i]] = i;
    }
    return friends.sort((a, b) => d[a] - d[b]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
