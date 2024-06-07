---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1788.Maximize%20the%20Beauty%20of%20the%20Garden/README.md
tags:
    - 贪心
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [1788. 最大化花园的美观度 🔒](https://leetcode.cn/problems/maximize-the-beauty-of-the-garden)

[English Version](/solution/1700-1799/1788.Maximize%20the%20Beauty%20of%20the%20Garden/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个花园，有 <code>n</code> 朵花，这些花都有一个用整数表示的美观度。这些花被种在一条线上。给定一个长度为 <code>n</code> 的整数类型数组 <code>flowers</code> ，每一个 <code>flowers[i]</code> 表示第 <code>i</code> 朵花的美观度。</p>

<p>一个花园满足下列条件时，该花园是<strong>有效</strong>的。</p>

<ul>
	<li>花园中至少包含两朵花。</li>
	<li>第一朵花和最后一朵花的美观度相同。</li>
</ul>

<p>作为一个被钦定的园丁，你可以从花园中<strong>去除</strong>任意朵花（也可以不去除任意一朵）。你想要通过一种方法移除某些花朵，使得剩下的花园变得<strong>有效</strong>。花园的美观度是其中所有剩余的花朵美观度之和。</p>

<p>返回你去除了任意朵花（也可以不去除任意一朵）之后形成的<strong>有效</strong>花园中最大可能的美观度。</p>

<p> </p>

<p><b>示例 1：</b></p>

<pre><strong>输入:</strong> flowers = [1,2,3,1,2]
<strong>输出:</strong> 8
<strong>解释:</strong> 你可以修整为有效花园 [2,3,1,2] 来达到总美观度 2 + 3 + 1 + 2 = 8。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> flowers = [100,1,1,-3,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 你可以修整为有效花园 [1,1,1] 来达到总美观度 1 + 1 + 1 = 3。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> flowers = [-1,-2,0,-1]
<strong>输出:</strong> -2
<strong>解释:</strong> 你可以修整为有效花园 [-1,-1] 来达到总美观度 -1 + -1 = -2。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= flowers[i] &lt;= 10<sup>4</sup></code></li>
	<li>去除一些花朵（可能没有）后，是有可能形成一个有效花园的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和

我们用哈希表 $d$ 记录每个美观度第一次出现的位置，用前缀和数组 $s$ 记录当前位置之前的美观度之和。如果一个美观度 $v$ 在位置 $i$ 和 $j$ 出现过（其中 $i \lt j$），那么我们可以得到一个有效的花园 $[i+1,j]$，其美观度为 $s[i] - s[j + 1] + v \times 2$，我们用这个值更新答案。否则，我们将当前美观度所在的位置 $i$ 记录到哈希表 $d$ 中。接下来，我们更新前缀和，如果美观度 $v$ 为负数，我们将其视为 $0$。

遍历完所有的美观度之后，我们就可以得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为花朵的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBeauty(self, flowers: List[int]) -> int:
        s = [0] * (len(flowers) + 1)
        d = {}
        ans = -inf
        for i, v in enumerate(flowers):
            if v in d:
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2)
            else:
                d[v] = i
            s[i + 1] = s[i] + max(v, 0)
        return ans
```

#### Java

```java
class Solution {
    public int maximumBeauty(int[] flowers) {
        int n = flowers.length;
        int[] s = new int[n + 1];
        Map<Integer, Integer> d = new HashMap<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.containsKey(v)) {
                ans = Math.max(ans, s[i] - s[d.get(v) + 1] + v * 2);
            } else {
                d.put(v, i);
            }
            s[i + 1] = s[i] + Math.max(v, 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& flowers) {
        int n = flowers.size();
        vector<int> s(n + 1);
        unordered_map<int, int> d;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = flowers[i];
            if (d.count(v)) {
                ans = max(ans, s[i] - s[d[v] + 1] + v * 2);
            } else {
                d[v] = i;
            }
            s[i + 1] = s[i] + max(v, 0);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumBeauty(flowers []int) int {
	n := len(flowers)
	s := make([]int, n+1)
	d := map[int]int{}
	ans := math.MinInt32
	for i, v := range flowers {
		if j, ok := d[v]; ok {
			ans = max(ans, s[i]-s[j+1]+v*2)
		} else {
			d[v] = i
		}
		s[i+1] = s[i] + max(v, 0)
	}
	return ans
}
```

#### TypeScript

```ts
function maximumBeauty(flowers: number[]): number {
    const n = flowers.length;
    const s: number[] = Array(n + 1).fill(0);
    const d: Map<number, number> = new Map();
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        const v = flowers[i];
        if (d.has(v)) {
            ans = Math.max(ans, s[i] - s[d.get(v)! + 1] + v * 2);
        } else {
            d.set(v, i);
        }
        s[i + 1] = s[i] + Math.max(v, 0);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn maximum_beauty(flowers: Vec<i32>) -> i32 {
        let mut s = vec![0; flowers.len() + 1];
        let mut d = HashMap::new();
        let mut ans = i32::MIN;

        for (i, &v) in flowers.iter().enumerate() {
            if let Some(&j) = d.get(&v) {
                ans = ans.max(s[i] - s[j + 1] + v * 2);
            } else {
                d.insert(v, i);
            }
            s[i + 1] = s[i] + v.max(0);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
