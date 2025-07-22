---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README.md
rating: 1332
source: 第 146 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [1128. 等价多米诺骨牌对的数量](https://leetcode.cn/problems/number-of-equivalent-domino-pairs)

[English Version](/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一组多米诺骨牌 <code>dominoes</code> 。</p>

<p>形式上，<code>dominoes[i] = [a, b]</code> 与 <code>dominoes[j] = [c, d]</code> <strong>等价</strong> 当且仅当 (<code>a == c</code> 且 <code>b == d</code>) 或者 (<code>a == d</code> 且 <code>b == c</code>) 。即一张骨牌可以通过旋转 <code>0</code>&nbsp;度或 <code>180</code> 度得到另一张多米诺骨牌。</p>

<p>在&nbsp;<code>0 &lt;= i &lt; j &lt; dominoes.length</code>&nbsp;的前提下，找出满足&nbsp;<code>dominoes[i]</code> 和&nbsp;<code>dominoes[j]</code>&nbsp;等价的骨牌对 <code>(i, j)</code> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>dominoes[i].length == 2</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以将每个多米诺骨牌的两个数字按照大小顺序拼接成一个两位数，这样就可以将等价的多米诺骨牌拼接成相同的两位数。例如，`[1, 2]` 和 `[2, 1]` 拼接成的两位数都是 `12`，`[3, 4]` 和 `[4, 3]` 拼接成的两位数都是 `34`。

然后我们遍历所有的多米诺骨牌，用一个哈希表或者一个长度为 $100$ 的数组 $cnt$ 记录每个两位数出现的次数。对于每个多米诺骨牌，我们拼接成的两位数为 $x$，那么答案就会增加 $cnt[x]$，接着我们将 $cnt[x]$ 的值加 $1$。继续遍历下一个多米诺骨牌，就可以统计出所有等价的多米诺骨牌对的数量。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是多米诺骨牌的数量，而 $C$ 是多米诺骨牌中拼接成的两位数的最大数量，即 $100$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        cnt = Counter()
        ans = 0
        for a, b in dominoes:
            x = a * 10 + b if a < b else b * 10 + a
            ans += cnt[x]
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] cnt = new int[100];
        int ans = 0;
        for (var e : dominoes) {
            int x = e[0] < e[1] ? e[0] * 10 + e[1] : e[1] * 10 + e[0];
            ans += cnt[x]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        int cnt[100]{};
        int ans = 0;
        for (auto& e : dominoes) {
            int x = e[0] < e[1] ? e[0] * 10 + e[1] : e[1] * 10 + e[0];
            ans += cnt[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func numEquivDominoPairs(dominoes [][]int) (ans int) {
	cnt := [100]int{}
	for _, e := range dominoes {
		x := e[0]*10 + e[1]
		if e[0] > e[1] {
			x = e[1]*10 + e[0]
		}
		ans += cnt[x]
		cnt[x]++
	}
	return
}
```

#### TypeScript

```ts
function numEquivDominoPairs(dominoes: number[][]): number {
    const cnt: number[] = new Array(100).fill(0);
    let ans = 0;

    for (const [a, b] of dominoes) {
        const key = a < b ? a * 10 + b : b * 10 + a;
        ans += cnt[key];
        cnt[key]++;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_equiv_domino_pairs(dominoes: Vec<Vec<i32>>) -> i32 {
        let mut cnt = [0i32; 100];
        let mut ans = 0;

        for d in dominoes {
            let a = d[0] as usize;
            let b = d[1] as usize;
            let key = if a < b { a * 10 + b } else { b * 10 + a };
            ans += cnt[key];
            cnt[key] += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
