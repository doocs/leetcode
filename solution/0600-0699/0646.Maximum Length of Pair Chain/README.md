---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0646.Maximum%20Length%20of%20Pair%20Chain/README.md
tags:
    - 贪心
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [646. 最长数对链](https://leetcode.cn/problems/maximum-length-of-pair-chain)

[English Version](/solution/0600-0699/0646.Maximum%20Length%20of%20Pair%20Chain/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由&nbsp;<code>n</code>&nbsp;个数对组成的数对数组&nbsp;<code>pairs</code>&nbsp;，其中&nbsp;<code>pairs[i] = [left<sub>i</sub>, right<sub>i</sub>]</code>&nbsp;且&nbsp;<code>left<sub>i</sub>&nbsp;&lt; right<sub>i</sub></code><sub> 。</sub></p>

<p>现在，我们定义一种 <strong>跟随</strong> 关系，当且仅当&nbsp;<code>b &lt; c</code>&nbsp;时，数对&nbsp;<code>p2 = [c, d]</code>&nbsp;才可以跟在&nbsp;<code>p1 = [a, b]</code>&nbsp;后面。我们用这种形式来构造 <strong>数对链</strong> 。</p>

<p>找出并返回能够形成的 <strong>最长数对链的长度</strong> 。</p>

<p>你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>pairs =&nbsp;[[1,2], [2,3], [3,4]]
<strong>输出：</strong>2
<strong>解释：</strong>最长的数对链是 [1,2] -&gt; [3,4] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>pairs = [[1,2],[7,8],[4,5]]
<b>输出：</b>3
<b>解释：</b>最长的数对链是 [1,2] -&gt; [4,5] -&gt; [7,8] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == pairs.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>-1000 &lt;= left<sub>i</sub>&nbsp;&lt; right<sub>i</sub>&nbsp;&lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们将所有数对按照第二个数的升序排序，用一个变量 $\textit{pre}$ 维护已经选择的数对的第二个数的最大值。

遍历排序后的数对，如果当前数对的第一个数大于 $\textit{pre}$，那么我们可以贪心地选择当前数对，答案加一，并将 $\textit{pre}$ 更新为当前数对的第二个数。

遍历结束后，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数对的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        pairs.sort(key=lambda x: x[1])
        ans, pre = 0, -inf
        for a, b in pairs:
            if pre < a:
                ans += 1
                pre = b
        return ans
```

#### Java

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        int ans = 0, pre = Integer.MIN_VALUE;
        for (var p : pairs) {
            if (pre < p[0]) {
                ++ans;
                pre = p[1];
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
    int findLongestChain(vector<vector<int>>& pairs) {
        ranges::sort(pairs, {}, [](const auto& p) { return p[1]; });
        int ans = 0, pre = INT_MIN;
        for (const auto& p : pairs) {
            if (pre < p[0]) {
                pre = p[1];
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLongestChain(pairs [][]int) (ans int) {
	sort.Slice(pairs, func(i, j int) bool { return pairs[i][1] < pairs[j][1] })
	pre := math.MinInt
	for _, p := range pairs {
		if pre < p[0] {
			ans++
			pre = p[1]
		}
	}
	return
}
```

#### TypeScript

```ts
function findLongestChain(pairs: number[][]): number {
    pairs.sort((a, b) => a[1] - b[1]);
    let [ans, pre] = [0, -Infinity];
    for (const [a, b] of pairs) {
        if (pre < a) {
            ++ans;
            pre = b;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by_key(|pair| pair[1]);
        let mut ans = 0;
        let mut pre = i32::MIN;
        for pair in pairs {
            let (a, b) = (pair[0], pair[1]);
            if pre < a {
                ans += 1;
                pre = b;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
