# [2212. 射箭比赛中的最大得分](https://leetcode.cn/problems/maximum-points-in-an-archery-competition)

[English Version](/solution/2200-2299/2212.Maximum%20Points%20in%20an%20Archery%20Competition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制枚举**

枚举 bob 射箭的最终状态，寻找满足题意的、且使得 bob 得分最大的状态。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumBobPoints(self, numArrows: int, aliceArrows: List[int]) -> List[int]:
        n = len(aliceArrows)
        state = 0
        mx = -1
        for mask in range(1 << n):
            cnt = points = 0
            for i, alice in enumerate(aliceArrows):
                if (mask >> i) & 1:
                    cnt += alice + 1
                    points += i
            if cnt <= numArrows and mx < points:
                state = mask
                mx = points
        ans = [0] * n
        for i, alice in enumerate(aliceArrows):
            if (state >> i) & 1:
                ans[i] = alice + 1
                numArrows -= ans[i]
        ans[0] = numArrows
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int n = aliceArrows.length;
        int mx = -1;
        int state = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int cnt = 0, points = 0;
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    cnt += aliceArrows[i] + 1;
                    points += i;
                }
            }
            if (cnt <= numArrows && mx < points) {
                state = mask;
                mx = points;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (((state >> i) & 1) == 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> maximumBobPoints(int numArrows, vector<int>& aliceArrows) {
        int n = aliceArrows.size();
        int state = 0, mx = -1;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int cnt = 0, points = 0;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i) & 1) {
                    cnt += aliceArrows[i] + 1;
                    points += i;
                }
            }
            if (cnt <= numArrows && mx < points) {
                state = mask;
                mx = points;
            }
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            if ((state >> i) & 1) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] += numArrows;
        return ans;
    }
};
```

### **Go**

```go
func maximumBobPoints(numArrows int, aliceArrows []int) []int {
	n := len(aliceArrows)
	state, mx := 0, -1
	for mask := 1; mask < 1<<n; mask++ {
		cnt, points := 0, 0
		for i, alice := range aliceArrows {
			if (mask>>i)&1 == 1 {
				cnt += alice + 1
				points += i
			}
		}
		if cnt <= numArrows && mx < points {
			state = mask
			mx = points
		}
	}
	ans := make([]int, n)
	for i, alice := range aliceArrows {
		if (state>>i)&1 == 1 {
			ans[i] = alice + 1
			numArrows -= ans[i]
		}
	}
	ans[0] += numArrows
	return ans
}
```

### **TypeScript**

```ts
function maximumBobPoints(numArrows: number, aliceArrows: number[]): number[] {
    const dfs = (arr: number[], i: number, c: number): number[] => {
        if (i < 0 || c === 0) {
            arr[0] += c;
            return arr;
        }
        const a1 = dfs([...arr], i - 1, c);
        if (c > aliceArrows[i]) {
            arr[i] = aliceArrows[i] + 1;
            const a2 = dfs(arr, i - 1, c - aliceArrows[i] - 1);
            if (
                a2.reduce((p, v, i) => p + (v > 0 ? i : 0), 0) >=
                a1.reduce((p, v, i) => p + (v > 0 ? i : 0), 0)
            ) {
                return a2;
            }
        }
        return a1;
    };
    return dfs(new Array(12).fill(0), 11, numArrows);
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(alice_arrows: &Vec<i32>, mut res: Vec<i32>, count: i32, i: usize) -> Vec<i32> {
        if i == 0 || count == 0 {
            res[0] += count;
            return res;
        }
        let r1 = Self::dfs(alice_arrows, res.clone(), count, i - 1);
        if count > alice_arrows[i] {
            res[i] = alice_arrows[i] + 1;
            let r2 = Self::dfs(alice_arrows, res, count - alice_arrows[i] - 1, i - 1);
            if r2
                .iter()
                .enumerate()
                .map(|(i, v)| if v > &0 { i } else { 0 })
                .sum::<usize>()
                > r1.iter()
                    .enumerate()
                    .map(|(i, v)| if v > &0 { i } else { 0 })
                    .sum::<usize>()
            {
                return r2;
            }
        }
        r1
    }

    pub fn maximum_bob_points(num_arrows: i32, alice_arrows: Vec<i32>) -> Vec<i32> {
        Self::dfs(&alice_arrows, vec![0; 12], num_arrows, 11)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
