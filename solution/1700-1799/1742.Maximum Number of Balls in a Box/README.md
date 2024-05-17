---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1742.Maximum%20Number%20of%20Balls%20in%20a%20Box/README.md
rating: 1277
source: 第 226 场周赛 Q1
tags:
    - 哈希表
    - 数学
    - 计数
---

<!-- problem:start -->

# [1742. 盒子中小球的最大数量](https://leetcode.cn/problems/maximum-number-of-balls-in-a-box)

[English Version](/solution/1700-1799/1742.Maximum%20Number%20of%20Balls%20in%20a%20Box/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你在一家生产小球的玩具厂工作，有 <code>n</code> 个小球，编号从 <code>lowLimit</code> 开始，到 <code>highLimit</code> 结束（包括 <code>lowLimit</code> 和 <code>highLimit</code> ，即 <code>n == highLimit - lowLimit + 1</code>）。另有无限数量的盒子，编号从 <code>1</code> 到 <code>infinity</code> 。</p>

<p>你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。例如，编号 <code>321</code> 的小球应当放入编号 <code>3 + 2 + 1 = 6</code> 的盒子，而编号 <code>10</code> 的小球应当放入编号 <code>1 + 0 = 1</code> 的盒子。</p>

<p>给你两个整数 <code>lowLimit</code> 和 <code>highLimit</code> ，返回放有最多小球的盒子中的小球数量<em>。</em>如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>lowLimit = 1, highLimit = 10
<strong>输出：</strong>2
<strong>解释：</strong>
盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
小球数量：2 1 1 1 1 1 1 1 1 0  0  ...
编号 1 的盒子放有最多小球，小球数量为 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>lowLimit = 5, highLimit = 15
<strong>输出：</strong>2
<strong>解释：</strong>
盒子编号：1 2 3 4 5 6 7 8 9 10 11 ...
小球数量：1 1 1 1 2 2 1 1 1 0  0  ...
编号 5 和 6 的盒子放有最多小球，每个盒子中的小球数量都是 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>lowLimit = 19, highLimit = 28
<strong>输出：</strong>2
<strong>解释：</strong>
盒子编号：1 2 3 4 5 6 7 8 9 10 11 12 ...
小球数量：0 1 1 1 1 1 1 1 1 2  0  0  ...
编号 10 的盒子放有最多小球，小球数量为 2 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= lowLimit <= highLimit <= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组 + 模拟

观察题目的数据范围，小球的编号最大不超过 $10^5$，那么每个编号的各个位数之和的最大值小于 $50$。因此，我们可以直接开一个长度为 $50$ 的数组 $cnt$ 来统计每个编号的各个位数之和的数量。

答案就是数组 $cnt$ 中的最大值。

时间复杂度 $O(n \times \log_{10}m)$。其中 $n = highLimit - lowLimit + 1$，而 $m = highLimit$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBalls(self, lowLimit: int, highLimit: int) -> int:
        cnt = [0] * 50
        for x in range(lowLimit, highLimit + 1):
            y = 0
            while x:
                y += x % 10
                x //= 10
            cnt[y] += 1
        return max(cnt)
```

#### Java

```java
class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnt = new int[50];
        for (int i = lowLimit; i <= highLimit; ++i) {
            int y = 0;
            for (int x = i; x > 0; x /= 10) {
                y += x % 10;
            }
            ++cnt[y];
        }
        return Arrays.stream(cnt).max().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countBalls(int lowLimit, int highLimit) {
        int cnt[50] = {0};
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int y = 0;
            for (int x = i; x; x /= 10) {
                y += x % 10;
            }
            ans = max(ans, ++cnt[y]);
        }
        return ans;
    }
};
```

#### Go

```go
func countBalls(lowLimit int, highLimit int) (ans int) {
	cnt := [50]int{}
	for i := lowLimit; i <= highLimit; i++ {
		y := 0
		for x := i; x > 0; x /= 10 {
			y += x % 10
		}
		cnt[y]++
		if ans < cnt[y] {
			ans = cnt[y]
		}
	}
	return
}
```

#### TypeScript

```ts
function countBalls(lowLimit: number, highLimit: number): number {
    const cnt: number[] = Array(50).fill(0);
    for (let i = lowLimit; i <= highLimit; ++i) {
        let y = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        ++cnt[y];
    }
    return Math.max(...cnt);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
