---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/README.md
rating: 1657
source: 第 381 场周赛 Q2
tags:
    - 广度优先搜索
    - 图
    - 前缀和
---

# [3015. 按距离统计房屋对数目 I](https://leetcode.cn/problems/count-the-number-of-houses-at-a-certain-distance-i)

[English Version](/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个<strong> 正整数 </strong><code>n</code> 、<code>x</code> 和 <code>y</code> 。</p>

<p>在城市中，存在编号从 <code>1</code> 到 <code>n</code> 的房屋，由 <code>n</code> 条街道相连。对所有 <code>1 &lt;= i &lt; n</code> ，都存在一条街道连接编号为 <code>i</code> 的房屋与编号为 <code>i + 1</code> 的房屋。另存在一条街道连接编号为 <code>x</code> 的房屋与编号为 <code>y</code> 的房屋。</p>

<p>对于每个 <code>k</code>（<code>1 &lt;= k &lt;= n</code>），你需要找出所有满足要求的 <strong>房屋对 </strong><code>[house<sub>1</sub>, house<sub>2</sub>]</code> ，即从 <code>house<sub>1</sub></code> 到 <code>house<sub>2</sub></code> 需要经过的<strong> 最少</strong> 街道数为 <code>k</code> 。</p>

<p>返回一个下标从 <strong>1</strong> 开始且长度为 <code>n</code> 的数组 <code>result</code> ，其中 <code>result[k]</code> 表示所有满足要求的房屋对的数量，即从一个房屋到另一个房屋需要经过的<strong> 最少 </strong>街道数为 <code>k</code> 。</p>

<p><strong>注意</strong>，<code>x</code> 与 <code>y</code> 可以 <strong>相等 </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example2.png" style="width: 474px; height: 197px;" />
<pre>
<strong>输入：</strong>n = 3, x = 1, y = 3
<strong>输出：</strong>[6,0,0]
<strong>解释：</strong>让我们检视每个房屋对
- 对于房屋对 (1, 2)，可以直接从房屋 1 到房屋 2。
- 对于房屋对 (2, 1)，可以直接从房屋 2 到房屋 1。
- 对于房屋对 (1, 3)，可以直接从房屋 1 到房屋 3。
- 对于房屋对 (3, 1)，可以直接从房屋 3 到房屋 1。
- 对于房屋对 (2, 3)，可以直接从房屋 2 到房屋 3。
- 对于房屋对 (3, 2)，可以直接从房屋 3 到房屋 2。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example3.png" style="width: 668px; height: 174px;" />
<pre>
<strong>输入：</strong>n = 5, x = 2, y = 4
<strong>输出：</strong>[10,8,2,0,0]
<strong>解释：</strong>对于每个距离 k ，满足要求的房屋对如下：
- 对于 k == 1，满足要求的房屋对有 (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5), 以及 (5, 4)。
- 对于 k == 2，满足要求的房屋对有 (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), 以及 (5, 3)。
- 对于 k == 3，满足要求的房屋对有 (1, 5)，以及 (5, 1) 。
- 对于 k == 4 和 k == 5，不存在满足要求的房屋对。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example5.png" style="width: 544px; height: 130px;" />
<pre>
<strong>输入：</strong>n = 4, x = 1, y = 1
<strong>输出：</strong>[6,4,2,0]
<strong>解释：</strong>对于每个距离 k ，满足要求的房屋对如下：
- 对于 k == 1，满足要求的房屋对有 (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), 以及 (4, 3)。
- 对于 k == 2，满足要求的房屋对有 (1, 3), (3, 1), (2, 4), 以及 (4, 2)。
- 对于 k == 3，满足要求的房屋对有 (1, 4), 以及 (4, 1)。
- 对于 k == 4，不存在满足要求的房屋对。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举每个点对 $(i, j)$，那么从 $i$ 到 $j$ 的最短距离为 $min(|i - j|, |i - x| + 1 + |j - y|, |i - y| + 1 + |j - x|)$，我们将该距离的出现次数加 $2$，因为 $(i, j)$ 和 $(j, i)$ 都是满足要求的点对。

时间复杂度 $O(n^2)$，其中 $n$ 是题目给定的 $n$。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        x, y = x - 1, y - 1
        ans = [0] * n
        for i in range(n):
            for j in range(i + 1, n):
                a = j - i
                b = abs(i - x) + 1 + abs(j - y)
                c = abs(i - y) + 1 + abs(j - x)
                ans[min(a, b, c) - 1] += 2
        return ans
```

```java
class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] ans = new int[n];
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = Math.abs(i - x) + 1 + Math.abs(j - y);
                int c = Math.abs(i - y) + 1 + Math.abs(j - x);
                ans[Math.min(a, Math.min(b, c)) - 1] += 2;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countOfPairs(int n, int x, int y) {
        vector<int> ans(n);
        x--;
        y--;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = j - i;
                int b = abs(x - i) + abs(y - j) + 1;
                int c = abs(y - i) + abs(x - j) + 1;
                ans[min({a, b, c}) - 1] += 2;
            }
        }
        return ans;
    }
};
```

```go
func countOfPairs(n int, x int, y int) []int {
	ans := make([]int, n)
	x, y = x-1, y-1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			a := j - i
			b := abs(x-i) + abs(y-j) + 1
			c := abs(x-j) + abs(y-i) + 1
			ans[min(a, min(b, c))-1] += 2
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countOfPairs(n: number, x: number, y: number): number[] {
    const ans: number[] = Array(n).fill(0);
    x--;
    y--;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const a = j - i;
            const b = Math.abs(x - i) + Math.abs(y - j) + 1;
            const c = Math.abs(y - i) + Math.abs(x - j) + 1;
            ans[Math.min(a, b, c) - 1] += 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
