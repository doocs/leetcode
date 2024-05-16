---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2857.Count%20Pairs%20of%20Points%20With%20Distance%20k/README.md
rating: 2081
source: 第 113 场双周赛 Q3
tags:
    - 位运算
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2857. 统计距离为 k 的点对](https://leetcode.cn/problems/count-pairs-of-points-with-distance-k)

[English Version](/solution/2800-2899/2857.Count%20Pairs%20of%20Points%20With%20Distance%20k/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>二维</strong>&nbsp;整数数组&nbsp;<code>coordinates</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，其中&nbsp;<code>coordinates[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;是第 <code>i</code>&nbsp;个点在二维平面里的坐标。</p>

<p>我们定义两个点&nbsp;<code>(x<sub>1</sub>, y<sub>1</sub>)</code>&nbsp;和&nbsp;<code>(x<sub>2</sub>, y<sub>2</sub>)</code>&nbsp;的 <strong>距离</strong>&nbsp;为&nbsp;<code>(x1 XOR x2) + (y1 XOR y2)</code> ，<code>XOR</code>&nbsp;指的是按位异或运算。</p>

<p>请你返回满足<em>&nbsp;</em><code>i &lt; j</code><em>&nbsp;</em>且点<em>&nbsp;</em><code>i</code><em> </em>和点<em>&nbsp;</em><code>j</code>之间距离为<em>&nbsp;</em><code>k</code>&nbsp;的点对数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
<b>输出：</b>2
<b>解释：</b>以下点对距离为 k ：
- (0, 1)：(1 XOR 4) + (2 XOR 2) = 5 。
- (2, 3)：(1 XOR 5) + (3 XOR 2) = 5 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
<b>输出：</b>10
<b>解释：</b>任何两个点之间的距离都为 0 ，所以总共有 10 组点对。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= coordinates.length &lt;= 50000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们可以用一个哈希表 $cnt$ 统计数组 $coordinates$ 中每个点出现的次数。

接下来，我们枚举数组 $coordinates$ 中的每个点 $(x_2, y_2)$，由于 $k$ 的取值范围为 $[0, 100]$，而 $x_1 \oplus x_2$ 或 $y_1 \oplus y_2$ 的结果一定大于等于 $0$，因此我们可以在 $[0,..k]$ 范围内枚举 $x_1 \oplus x_2$ 的结果 $a$，那么 $y_1 \oplus y_2$ 的结果就是 $b = k - a$。这样一来，我们就可以计算出 $x_1$ 和 $y_1$ 的值，将 $(x_1, y_1)$ 出现的次数累加到答案中。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $coordinates$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def countPairs(self, coordinates: List[List[int]], k: int) -> int:
        cnt = Counter()
        ans = 0
        for x2, y2 in coordinates:
            for a in range(k + 1):
                b = k - a
                x1, y1 = a ^ x2, b ^ y2
                ans += cnt[(x1, y1)]
            cnt[(x2, y2)] += 1
        return ans
```

```java
class Solution {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        Map<List<Integer>, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (var c : coordinates) {
            int x2 = c.get(0), y2 = c.get(1);
            for (int a = 0; a <= k; ++a) {
                int b = k - a;
                int x1 = a ^ x2, y1 = b ^ y2;
                ans += cnt.getOrDefault(List.of(x1, y1), 0);
            }
            cnt.merge(c, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countPairs(vector<vector<int>>& coordinates, int k) {
        map<pair<int, int>, int> cnt;
        int ans = 0;
        for (auto& c : coordinates) {
            int x2 = c[0], y2 = c[1];
            for (int a = 0; a <= k; ++a) {
                int b = k - a;
                int x1 = a ^ x2, y1 = b ^ y2;
                ans += cnt[{x1, y1}];
            }
            ++cnt[{x2, y2}];
        }
        return ans;
    }
};
```

```go
func countPairs(coordinates [][]int, k int) (ans int) {
	cnt := map[[2]int]int{}
	for _, c := range coordinates {
		x2, y2 := c[0], c[1]
		for a := 0; a <= k; a++ {
			b := k - a
			x1, y1 := a^x2, b^y2
			ans += cnt[[2]int{x1, y1}]
		}
		cnt[[2]int{x2, y2}]++
	}
	return
}
```

```ts
function countPairs(coordinates: number[][], k: number): number {
    const cnt: Map<number, number> = new Map();
    const f = (x: number, y: number): number => x * 1000000 + y;
    let ans = 0;
    for (const [x2, y2] of coordinates) {
        for (let a = 0; a <= k; ++a) {
            const b = k - a;
            const [x1, y1] = [a ^ x2, b ^ y2];
            ans += cnt.get(f(x1, y1)) ?? 0;
        }
        cnt.set(f(x2, y2), (cnt.get(f(x2, y2)) ?? 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```cpp
class Solution {
public:
    int countPairs(vector<vector<int>>& coordinates, int k) {
        unordered_map<long long, int> cnt;
        auto f = [](int x, int y) {
            return x * 1000000L + y;
        };
        int ans = 0;
        for (auto& c : coordinates) {
            int x2 = c[0], y2 = c[1];
            for (int a = 0; a <= k; ++a) {
                int b = k - a;
                int x1 = a ^ x2, y1 = b ^ y2;
                ans += cnt[f(x1, y1)];
            }
            ++cnt[f(x2, y2)];
        }
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
