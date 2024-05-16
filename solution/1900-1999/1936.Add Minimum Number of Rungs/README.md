---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1936.Add%20Minimum%20Number%20of%20Rungs/README.md
rating: 1322
source: 第 250 场周赛 Q2
tags:
    - 贪心
    - 数组
---

# [1936. 新增的最少台阶数](https://leetcode.cn/problems/add-minimum-number-of-rungs)

[English Version](/solution/1900-1999/1936.Add%20Minimum%20Number%20of%20Rungs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>严格递增</strong> 的整数数组 <code>rungs</code> ，用于表示梯子上每一台阶的 <strong>高度</strong> 。当前你正站在高度为 <code>0</code> 的地板上，并打算爬到最后一个台阶。</p>

<p>另给你一个整数 <code>dist</code> 。每次移动中，你可以到达下一个距离你当前位置（地板或台阶）<strong>不超过</strong> <code>dist</code> 高度的台阶。当然，你也可以在任何正 <strong>整数</strong> 高度处插入尚不存在的新台阶。</p>

<p>返回爬到最后一阶时必须添加到梯子上的 <strong>最少</strong> 台阶数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rungs = [1,3,5,10], dist = 2
<strong>输出：</strong>2
<strong>解释：
</strong>现在无法到达最后一阶。
在高度为 7 和 8 的位置增设新的台阶，以爬上梯子。 
梯子在高度为 [1,3,5,<strong><em>7</em></strong>,<strong><em>8</em></strong>,10] 的位置上有台阶。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rungs = [3,6,8,10], dist = 3
<strong>输出：</strong>0
<strong>解释：</strong>
这个梯子无需增设新台阶也可以爬上去。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rungs = [3,4,6,7], dist = 2
<strong>输出：</strong>1
<strong>解释：</strong>
现在无法从地板到达梯子的第一阶。 
在高度为 1 的位置增设新的台阶，以爬上梯子。 
梯子在高度为 [<strong><em>1</em></strong>,3,4,6,7] 的位置上有台阶。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rungs = [5], dist = 10
<strong>输出：</strong>0
<strong>解释：</strong>这个梯子无需增设新台阶也可以爬上去。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= rungs.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= rungs[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= dist <= 10<sup>9</sup></code></li>
	<li><code>rungs</code> <strong>严格递增</strong></li>
</ul>

## 解法

### 方法一：贪心 + 模拟

根据题目描述，我们知道，每一次计划爬上一个新的台阶，都需要满足新的台阶的高度与当前所在位置的高度之差不超过 `dist`，否则，我们需要贪心地在距离当前位置 $dist$ 的地方插入一个新的台阶，爬上一个新的台阶，一共需要插入的台阶数为 $\lfloor \frac{b - a - 1}{dist} \rfloor$，其中 $a$ 和 $b$ 分别为当前位置和新台阶的高度。那么答案即为所有插入的台阶数之和。

时间复杂度 $O(n)$，其中 $n$ 为 `rungs` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def addRungs(self, rungs: List[int], dist: int) -> int:
        rungs = [0] + rungs
        return sum((b - a - 1) // dist for a, b in pairwise(rungs))
```

```java
class Solution {
    public int addRungs(int[] rungs, int dist) {
        int ans = 0, prev = 0;
        for (int x : rungs) {
            ans += (x - prev - 1) / dist;
            prev = x;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int addRungs(vector<int>& rungs, int dist) {
        int ans = 0, prev = 0;
        for (int& x : rungs) {
            ans += (x - prev - 1) / dist;
            prev = x;
        }
        return ans;
    }
};
```

```go
func addRungs(rungs []int, dist int) (ans int) {
	prev := 0
	for _, x := range rungs {
		ans += (x - prev - 1) / dist
		prev = x
	}
	return
}
```

```ts
function addRungs(rungs: number[], dist: number): number {
    let ans = 0;
    let prev = 0;
    for (const x of rungs) {
        ans += ((x - prev - 1) / dist) | 0;
        prev = x;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn add_rungs(rungs: Vec<i32>, dist: i32) -> i32 {
        let mut ans = 0;
        let mut prev = 0;

        for &x in rungs.iter() {
            ans += (x - prev - 1) / dist;
            prev = x;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
