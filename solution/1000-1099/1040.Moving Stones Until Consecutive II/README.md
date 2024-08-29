---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1040.Moving%20Stones%20Until%20Consecutive%20II/README.md
rating: 2455
source: 第 135 场周赛 Q4
tags:
    - 数组
    - 数学
    - 双指针
    - 排序
---

<!-- problem:start -->

# [1040. 移动石子直到连续 II](https://leetcode.cn/problems/moving-stones-until-consecutive-ii)

[English Version](/solution/1000-1099/1040.Moving%20Stones%20Until%20Consecutive%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在 X 轴上有一些不同位置的石子。给定一个整数数组&nbsp;<code>stones</code>&nbsp;表示石子的位置。</p>

<p>如果一个石子在最小或最大的位置，称其为&nbsp;<strong>端点石子</strong>。每个回合，你可以将一颗 <strong>端点石子</strong> 拿起并移动到一个未占用的位置，使得该石子不再是一颗 <strong>端点石子</strong>。</p>

<ul>
	<li>值得注意的是，如果石子像&nbsp;<code>stones = [1,2,5]</code>&nbsp;这样，你将 <strong>无法 </strong>移动位于位置 <code>5</code> 的端点石子，因为无论将它移动到任何位置（例如 <code>0</code> 或 <code>3</code>），该石子都仍然会是端点石子。</li>
</ul>

<p>当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。</p>

<p>以长度为 2 的数组形式返回答案，其中：</p>

<ul>
	<li><code>answer[0]</code>&nbsp;是你可以移动的最小次数</li>
	<li><code>answer[1]</code>&nbsp;是你可以移动的最大次数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[7,4,9]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>
我们可以移动一次，4 -&gt; 8，游戏结束。
或者，我们可以移动两次 9 -&gt; 5，4 -&gt; 6，游戏结束。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>[6,5,4,3,10]
<strong>输出：</strong>[2,3]
<strong>解释：</strong>
我们可以移动 3 -&gt; 8，接着是 10 -&gt; 7，游戏结束。
或者，我们可以移动 3 -&gt; 7, 4 -&gt; 8, 5 -&gt; 9，游戏结束。
注意，我们无法进行 10 -&gt; 2 这样的移动来结束游戏，因为这是不合要求的移动。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= stones.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= stones[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>stones</code>&nbsp;的值各不相同。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 分类讨论 + 双指针

我们先对数组 `stones` 进行升序排序，接下来分别考虑最大移动次数 $mx$ 和最小移动次数 $mi$。

对于最大移动次数 $mx$：

由于我们每一次只能选择将端点石子移动到未占用且不是端点石子的位置，如果我们选择 `stones[0]` 作为第一次移动的端点石子，那么从 `stones[0]` 到 `stones[1]` 之间的所有未占用的位置都会被跳过，我们可以选择移动到最近的且未占用的位置，接下来每一次都将最左端的石子移动到最近的且未占用的位置，那么最多可以移动的次数为 $stones[n - 1] - stones[1] + 1 - (n - 1)$；同理，如果我们选择 `stones[n - 1]` 作为第一次移动的端点石子，那么最多可以移动的次数为 $stones[n - 2] - stones[0] + 1 - (n - 1)$。取两者的最大值即为最大移动次数 $mx$。

对于最小移动次数 $mi$：

我们用双指针 $i$ 和 $j$ 标识一个窗口的左右端点，若窗口内的位置数 $stones[j] - stones[i] + 1 \gt n$ 时，我们需要缩小窗口，即指针 $i$ 向右移动。如果此时窗口中有连续的 $n-1$ 个石子，即满足 $j - i + 1 = n - 1$ 且 $stones[j] - stones[i] + 1 = n - 1$，那么最少需要移动的次数为 $2$；否则，我们用 $n$ 减去窗口内的石子数，可以得到最少需要移动的次数，即 $n - (j - i + 1)$。取所有情况的最小值即为最小移动次数 $mi$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `stones` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numMovesStonesII(self, stones: List[int]) -> List[int]:
        stones.sort()
        mi = n = len(stones)
        mx = max(stones[-1] - stones[1] + 1, stones[-2] - stones[0] + 1) - (n - 1)
        i = 0
        for j, x in enumerate(stones):
            while x - stones[i] + 1 > n:
                i += 1
            if j - i + 1 == n - 1 and x - stones[i] == n - 2:
                mi = min(mi, 2)
            else:
                mi = min(mi, n - (j - i + 1))
        return [mi, mx]
```

#### Java

```java
class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int mi = n;
        int mx = Math.max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - (n - 1);
        for (int i = 0, j = 0; j < n; ++j) {
            while (stones[j] - stones[i] + 1 > n) {
                ++i;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] == n - 2) {
                mi = Math.min(mi, 2);
            } else {
                mi = Math.min(mi, n - (j - i + 1));
            }
        }
        return new int[] {mi, mx};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> numMovesStonesII(vector<int>& stones) {
        sort(stones.begin(), stones.end());
        int n = stones.size();
        int mi = n;
        int mx = max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - (n - 1);
        for (int i = 0, j = 0; j < n; ++j) {
            while (stones[j] - stones[i] + 1 > n) {
                ++i;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] == n - 2) {
                mi = min(mi, 2);
            } else {
                mi = min(mi, n - (j - i + 1));
            }
        }
        return {mi, mx};
    }
};
```

#### Go

```go
func numMovesStonesII(stones []int) []int {
	sort.Ints(stones)
	n := len(stones)
	mi := n
	mx := max(stones[n-1]-stones[1]+1, stones[n-2]-stones[0]+1) - (n - 1)
	i := 0
	for j, x := range stones {
		for x-stones[i]+1 > n {
			i++
		}
		if j-i+1 == n-1 && stones[j]-stones[i] == n-2 {
			mi = min(mi, 2)
		} else {
			mi = min(mi, n-(j-i+1))
		}
	}
	return []int{mi, mx}
}
```

#### TypeScript

```ts
function numMovesStonesII(stones: number[]): number[] {
    stones.sort((a, b) => a - b);
    const n = stones.length;
    let mi = n;
    const mx = Math.max(stones[n - 1] - stones[1] + 1, stones[n - 2] - stones[0] + 1) - (n - 1);
    for (let i = 0, j = 0; j < n; ++j) {
        while (stones[j] - stones[i] + 1 > n) {
            ++i;
        }
        if (j - i + 1 === n - 1 && stones[j] - stones[i] === n - 2) {
            mi = Math.min(mi, 2);
        } else {
            mi = Math.min(mi, n - (j - i + 1));
        }
    }
    return [mi, mx];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
