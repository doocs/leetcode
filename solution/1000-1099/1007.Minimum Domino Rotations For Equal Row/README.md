---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1007.Minimum%20Domino%20Rotations%20For%20Equal%20Row/README.md
rating: 1541
source: 第 127 场周赛 Q3
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [1007. 行相等的最少多米诺旋转](https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row)

[English Version](/solution/1000-1099/1007.Minimum%20Domino%20Rotations%20For%20Equal%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一排多米诺骨牌中，<code>tops[i]</code> 和 <code>bottoms[i]</code>&nbsp;分别代表第 <code>i</code> 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6 的数字同列平铺形成的&nbsp;—— 该平铺的每一半上都有一个数字。）</p>

<p>我们可以旋转第&nbsp;<code>i</code>&nbsp;张多米诺，使得 <code>tops[i]</code> 和 <code>bottoms[i]</code>&nbsp;的值交换。</p>

<p>返回能使 <code>tops</code> 中所有值或者 <code>bottoms</code> 中所有值都相同的最小旋转次数。</p>

<p>如果无法做到，返回&nbsp;<code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1007.Minimum%20Domino%20Rotations%20For%20Equal%20Row/images/domino.png" style="height: 300px; width: 421px;" />
<pre>
<strong>输入：</strong>tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
<strong>输出：</strong>2
<strong>解释：</strong> 
图一表示：在我们旋转之前， tops 和 bottoms 给出的多米诺牌。 
如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。 
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
<strong>输出：</strong>-1
<strong>解释：</strong> 在这种情况下，不可能旋转多米诺牌使一行的值相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= tops.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>bottoms.length == tops.length</code></li>
	<li><code>1 &lt;= tops[i], bottoms[i] &lt;= 6</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

根据题目描述，我们知道，要使得 $tops$ 中所有值或者 $bottoms$ 中所有值都相同，那么这个值必须是 $tops[0]$ 或者 $bottoms[0]$ 中的一个。

因此，我们设计一个函数 $f(x)$，表示将所有的值都变成 $x$ 的最小旋转次数，那么答案就是 $\min\{f(\textit{tops}[0]), f(\textit{bottoms}[0])\}$。

函数 $f(x)$ 的计算方法如下：

我们用两个变量 $cnt1$ 和 $cnt2$ 统计 $tops$ 和 $bottoms$ 中等于 $x$ 的个数，用 $n$ 减去它们的最大值，就是将所有值都变成 $x$ 的最小旋转次数。注意，如果 $tops$ 和 $bottoms$ 中没有等于 $x$ 的值，那么 $f(x)$ 的值就是一个很大的数，我们用 $n + 1$ 表示这个数。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        def f(x: int) -> int:
            cnt1 = cnt2 = 0
            for a, b in zip(tops, bottoms):
                if x not in (a, b):
                    return inf
                cnt1 += a == x
                cnt2 += b == x
            return len(tops) - max(cnt1, cnt2)

        ans = min(f(tops[0]), f(bottoms[0]))
        return -1 if ans == inf else ans
```

#### Java

```java
class Solution {
    private int n;
    private int[] tops;
    private int[] bottoms;

    public int minDominoRotations(int[] tops, int[] bottoms) {
        n = tops.length;
        this.tops = tops;
        this.bottoms = bottoms;
        int ans = Math.min(f(tops[0]), f(bottoms[0]));
        return ans > n ? -1 : ans;
    }

    private int f(int x) {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < n; ++i) {
            if (tops[i] != x && bottoms[i] != x) {
                return n + 1;
            }
            cnt1 += tops[i] == x ? 1 : 0;
            cnt2 += bottoms[i] == x ? 1 : 0;
        }
        return n - Math.max(cnt1, cnt2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDominoRotations(vector<int>& tops, vector<int>& bottoms) {
        int n = tops.size();
        auto f = [&](int x) {
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < n; ++i) {
                if (tops[i] != x && bottoms[i] != x) {
                    return n + 1;
                }
                cnt1 += tops[i] == x;
                cnt2 += bottoms[i] == x;
            }
            return n - max(cnt1, cnt2);
        };
        int ans = min(f(tops[0]), f(bottoms[0]));
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minDominoRotations(tops []int, bottoms []int) int {
	n := len(tops)
	f := func(x int) int {
		cnt1, cnt2 := 0, 0
		for i, a := range tops {
			b := bottoms[i]
			if a != x && b != x {
				return n + 1
			}
			if a == x {
				cnt1++
			}
			if b == x {
				cnt2++
			}
		}
		return n - max(cnt1, cnt2)
	}
	ans := min(f(tops[0]), f(bottoms[0]))
	if ans > n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minDominoRotations(tops: number[], bottoms: number[]): number {
    const n = tops.length;
    const f = (x: number): number => {
        let [cnt1, cnt2] = [0, 0];
        for (let i = 0; i < n; ++i) {
            if (tops[i] !== x && bottoms[i] !== x) {
                return n + 1;
            }
            cnt1 += tops[i] === x ? 1 : 0;
            cnt2 += bottoms[i] === x ? 1 : 0;
        }
        return n - Math.max(cnt1, cnt2);
    };
    const ans = Math.min(f(tops[0]), f(bottoms[0]));
    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
