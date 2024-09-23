---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README.md
rating: 1730
source: 第 129 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [1014. 最佳观光组合](https://leetcode.cn/problems/best-sightseeing-pair)

[English Version](/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组 <code>values</code>，其中 <code>values[i]</code> 表示第 <code>i</code> 个观光景点的评分，并且两个景点 <code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>j - i</code>。</p>

<p>一对景点（<code>i < j</code>）组成的观光组合的得分为 <code>values[i] + values[j] + i - j</code> ，也就是景点的评分之和<strong> 减去 </strong>它们两者之间的距离。</p>

<p>返回一对观光景点能取得的最高分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>values = [8,1,5,2,6]
<strong>输出：</strong>11
<strong>解释：</strong>i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2]
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= values.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= values[i] <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以从左到右枚举 $j$，同时维护 $j$ 左侧元素中 $values[i] + i$ 的最大值 $mx$，这样对于每个 $j$，最大得分为 $mx + values[j] - j$。我们取所有位置的最大得分的最大值即为答案。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{values}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        ans = mx = 0
        for j, x in enumerate(values):
            ans = max(ans, mx + x - j)
            mx = max(mx, x + j)
        return ans
```

#### Java

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = 0;
        for (int j = 0; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int ans = 0, mx = 0;
        for (int j = 0; j < values.size(); ++j) {
            ans = max(ans, mx + values[j] - j);
            mx = max(mx, values[j] + j);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScoreSightseeingPair(values []int) (ans int) {
	mx := 0
	for j, x := range values {
		ans = max(ans, mx+x-j)
		mx = max(mx, x+j)
	}
	return
}
```

#### TypeScript

```ts
function maxScoreSightseeingPair(values: number[]): number {
    let [ans, mx] = [0, 0];
    for (let j = 0; j < values.length; ++j) {
        ans = Math.max(ans, mx + values[j] - j);
        mx = Math.max(mx, values[j] + j);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score_sightseeing_pair(values: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for (j, &x) in values.iter().enumerate() {
            ans = ans.max(mx + x - j as i32);
            mx = mx.max(x + j as i32);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
