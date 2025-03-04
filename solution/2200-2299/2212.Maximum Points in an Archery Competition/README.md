---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README.md
rating: 1868
source: 第 285 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 回溯
    - 枚举
---

<!-- problem:start -->

# [2212. 射箭比赛中的最大得分](https://leetcode.cn/problems/maximum-points-in-an-archery-competition)

[English Version](/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 是一场射箭比赛中的对手。比赛规则如下：</p>

<ol>
	<li>Alice 先射 <code>numArrows</code> 支箭，然后 Bob 也射 <code>numArrows</code> 支箭。</li>
	<li>分数按下述规则计算：
	<ol>
		<li>箭靶有若干整数计分区域，范围从 <code>0</code> 到 <code>11</code> （含 <code>0</code> 和 <code>11</code>）。</li>
		<li>箭靶上每个区域都对应一个得分 <code>k</code>（范围是 <code>0</code> 到 <code>11</code>），Alice 和 Bob 分别在得分 <code>k</code>&nbsp;区域射中&nbsp;<code>a<sub>k</sub></code> 和 <code>b<sub>k</sub></code> 支箭。如果 <code>a<sub>k</sub> &gt;= b<sub>k</sub></code> ，那么 Alice 得 <code>k</code> 分。如果 <code>a<sub>k</sub> &lt; b<sub>k</sub></code> ，则 Bob 得 <code>k</code> 分</li>
		<li>如果 <code>a<sub>k</sub> == b<sub>k</sub> == 0</code> ，那么无人得到 <code>k</code> 分。</li>
	</ol>
	</li>
</ol>

<ul>
	<li>
	<p>例如，Alice 和 Bob 都向计分为 <code>11</code> 的区域射 <code>2</code> 支箭，那么 Alice 得 <code>11</code> 分。如果 Alice 向计分为 <code>11</code> 的区域射 <code>0</code> 支箭，但 Bob 向同一个区域射 <code>2</code> 支箭，那么 Bob 得&nbsp;<code>11</code> 分。</p>
	</li>
</ul>

<p>给你整数 <code>numArrows</code> 和一个长度为 <code>12</code> 的整数数组 <code>aliceArrows</code> ，该数组表示 Alice 射中&nbsp;<code>0</code> 到 <code>11</code> 每个计分区域的箭数量。现在，Bob 想要尽可能 <strong>最大化</strong> 他所能获得的总分。</p>

<p>返回数组 <code>bobArrows</code><em> </em>，该数组表示 Bob 射中&nbsp;<code>0</code> 到 <code>11</code> <strong>每个</strong> 计分区域的箭数量。且 <code>bobArrows</code> 的总和应当等于 <code>numArrows</code> 。</p>

<p>如果存在多种方法都可以使 Bob 获得最大总分，返回其中 <strong>任意一种</strong> 即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/1647744752-kQKrXw-image.png" style="width: 600px; height: 120px;" /></p>

<pre>
<strong>输入：</strong>numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
<strong>输出：</strong>[0,0,0,0,1,1,0,0,1,2,3,1]
<strong>解释：</strong>上表显示了比赛得分情况。
Bob 获得总分 4 + 5 + 8 + 9 + 10 + 11 = 47 。
可以证明 Bob 无法获得比 47 更高的分数。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/images/1647744785-cMHzaC-image.png" style="width: 600px; height: 117px;" /></p>

<pre>
<strong>输入：</strong>numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
<strong>输出：</strong>[0,0,0,0,0,0,0,0,1,1,1,0]
<strong>解释：</strong>上表显示了比赛得分情况。
Bob 获得总分 8 + 9 + 10 = 27 。
可以证明 Bob 无法获得比 27 更高的分数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= numArrows &lt;= 10<sup>5</sup></code></li>
	<li><code>aliceArrows.length == bobArrows.length == 12</code></li>
	<li><code>0 &lt;= aliceArrows[i], bobArrows[i] &lt;= numArrows</code></li>
	<li><code>sum(aliceArrows[i]) == numArrows</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二进制枚举

由于区域数目只有 $12$ 个，因此我们使用二进制枚举的方式，枚举 $\textit{Bob}$ 在哪些区域得分。用一个变量 $\textit{st}$ 表示 $\textit{Bob}$ 获得最大得分的方案，而 $\textit{mx}$ 表示 $\textit{Bob}$ 获得的最大得分。

我们在 $[1, 2^m)$ 的区间内枚举 $\textit{Bob}$ 的得分方案，其中 $m$ 是 $\textit{aliceArrows}$ 的长度。对于每一个方案，我们计算 $\textit{Bob}$ 的得分 $\textit{s}$ 以及射箭的数量 $\textit{cnt}$。如果 $\textit{cnt} \leq \textit{numArrows}$ 且 $\textit{s} > \textit{mx}$，我们就更新 $\textit{mx}$ 和 $\textit{st}$。

然后，我们根据 $\textit{st}$ 计算 $\textit{Bob}$ 的得分方案，如果最后还有剩余的射箭，我们将剩余的射箭分配给第一个区域，即下标为 $0$ 的区域。

时间复杂度 $O(2^m \times m)$，其中 $m$ 是 $\textit{aliceArrows}$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBobPoints(self, numArrows: int, aliceArrows: List[int]) -> List[int]:
        st = mx = 0
        m = len(aliceArrows)
        for mask in range(1, 1 << m):
            cnt = s = 0
            for i, x in enumerate(aliceArrows):
                if mask >> i & 1:
                    s += i
                    cnt += x + 1
            if cnt <= numArrows and s > mx:
                mx = s
                st = mask
        ans = [0] * m
        for i, x in enumerate(aliceArrows):
            if st >> i & 1:
                ans[i] = x + 1
                numArrows -= ans[i]
        ans[0] += numArrows
        return ans
```

#### Java

```java
class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int st = 0, mx = 0;
        int m = aliceArrows.length;
        for (int mask = 1; mask < 1 << m; ++mask) {
            int cnt = 0, s = 0;
            for (int i = 0; i < m; ++i) {
                if ((mask >> i & 1) == 1) {
                    s += i;
                    cnt += aliceArrows[i] + 1;
                }
            }
            if (cnt <= numArrows && s > mx) {
                mx = s;
                st = mask;
            }
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            if ((st >> i & 1) == 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maximumBobPoints(int numArrows, vector<int>& aliceArrows) {
        int st = 0, mx = 0;
        int m = aliceArrows.size();
        for (int mask = 1; mask < 1 << m; ++mask) {
            int cnt = 0, s = 0;
            for (int i = 0; i < m; ++i) {
                if (mask >> i & 1) {
                    s += i;
                    cnt += aliceArrows[i] + 1;
                }
            }
            if (cnt <= numArrows && s > mx) {
                mx = s;
                st = mask;
            }
        }
        vector<int> ans(m);
        for (int i = 0; i < m; ++i) {
            if (st >> i & 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
};
```

#### Go

```go
func maximumBobPoints(numArrows int, aliceArrows []int) []int {
	st, mx := 0, 0
	m := len(aliceArrows)
	for mask := 1; mask < 1<<m; mask++ {
		cnt, s := 0, 0
		for i, x := range aliceArrows {
			if mask>>i&1 == 1 {
				s += i
				cnt += x + 1
			}
		}
		if cnt <= numArrows && s > mx {
			mx = s
			st = mask
		}
	}
	ans := make([]int, m)
	for i, x := range aliceArrows {
		if (st>>i)&1 == 1 {
			ans[i] = x + 1
			numArrows -= ans[i]
		}
	}
	ans[0] += numArrows
	return ans
}
```

#### TypeScript

```ts
function maximumBobPoints(numArrows: number, aliceArrows: number[]): number[] {
    let [st, mx] = [0, 0];
    const m = aliceArrows.length;
    for (let mask = 1; mask < 1 << m; mask++) {
        let [cnt, s] = [0, 0];
        for (let i = 0; i < m; i++) {
            if ((mask >> i) & 1) {
                cnt += aliceArrows[i] + 1;
                s += i;
            }
        }
        if (cnt <= numArrows && s > mx) {
            mx = s;
            st = mask;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = 0; i < m; i++) {
        if ((st >> i) & 1) {
            ans[i] = aliceArrows[i] + 1;
            numArrows -= ans[i];
        }
    }
    ans[0] += numArrows;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_bob_points(num_arrows: i32, alice_arrows: Vec<i32>) -> Vec<i32> {
        let mut st = 0;
        let mut mx = 0;
        let m = alice_arrows.len();
        for mask in 1..(1 << m) {
            let mut cnt = 0;
            let mut s = 0;
            for i in 0..m {
                if (mask >> i) & 1 == 1 {
                    s += i as i32;
                    cnt += alice_arrows[i] + 1;
                }
            }
            if cnt <= num_arrows && s > mx {
                mx = s;
                st = mask;
            }
        }
        let mut ans = vec![0; m];
        let mut num_arrows = num_arrows;
        for i in 0..m {
            if (st >> i) & 1 == 1 {
                ans[i] = alice_arrows[i] + 1;
                num_arrows -= ans[i];
            }
        }
        ans[0] += num_arrows;
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} numArrows
 * @param {number[]} aliceArrows
 * @return {number[]}
 */
var maximumBobPoints = function (numArrows, aliceArrows) {
    let [st, mx] = [0, 0];
    const m = aliceArrows.length;
    for (let mask = 1; mask < 1 << m; mask++) {
        let [cnt, s] = [0, 0];
        for (let i = 0; i < m; i++) {
            if ((mask >> i) & 1) {
                cnt += aliceArrows[i] + 1;
                s += i;
            }
        }
        if (cnt <= numArrows && s > mx) {
            mx = s;
            st = mask;
        }
    }
    const ans = Array(m).fill(0);
    for (let i = 0; i < m; i++) {
        if ((st >> i) & 1) {
            ans[i] = aliceArrows[i] + 1;
            numArrows -= ans[i];
        }
    }
    ans[0] += numArrows;
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
