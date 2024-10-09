---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1560.Most%20Visited%20Sector%20in%20%20a%20Circular%20Track/README.md
rating: 1443
source: 第 203 场周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [1560. 圆形赛道上经过次数最多的扇区](https://leetcode.cn/problems/most-visited-sector-in-a-circular-track)

[English Version](/solution/1500-1599/1560.Most%20Visited%20Sector%20in%20%20a%20Circular%20Track/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 和一个整数数组 <code>rounds</code> 。有一条圆形赛道由 <code>n</code> 个扇区组成，扇区编号从 <code>1</code> 到 <code>n</code> 。现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 <code>m</code> 个阶段组成。其中，第 <code>i</code> 个阶段将会从扇区 <code>rounds[i - 1]</code> 开始，到扇区 <code>rounds[i]</code> 结束。举例来说，第 <code>1</code> 阶段从&nbsp;<code>rounds[0]</code>&nbsp;开始，到&nbsp;<code>rounds[1]</code>&nbsp;结束。</p>

<p>请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 <strong>升序</strong> 排列。</p>

<p>注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1560.Most%20Visited%20Sector%20in%20%20a%20Circular%20Track/images/3rd45e.jpg" style="height: 341px; width: 433px;"></p>

<pre><strong>输入：</strong>n = 4, rounds = [1,3,1,2]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示：
1 --&gt; 2 --&gt; 3（阶段 1 结束）--&gt; 4 --&gt; 1（阶段 2 结束）--&gt; 2（阶段 3 结束，即本场马拉松结束）
其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4 都只经过了一次。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2, rounds = [2,1,2,1,2,1,2,1,2]
<strong>输出：</strong>[2]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 7, rounds = [1,3,5,7]
<strong>输出：</strong>[1,2,3,4,5,6,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>rounds.length == m + 1</code></li>
	<li><code>1 &lt;= rounds[i] &lt;= n</code></li>
	<li><code>rounds[i] != rounds[i + 1]</code> ，其中 <code>0 &lt;= i &lt; m</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：考虑开始、结束的位置关系

由于每个阶段的结束位置是下一个阶段的开始位置，并且每个阶段都是逆时针方向的，所以我们可以根据开始和结束的位置关系来确定每个扇区的经过次数。

如果 $\textit{rounds}[0] \leq \textit{rounds}[m]$，那么从 $\textit{rounds}[0]$ 开始，到 $\textit{rounds}[m]$ 结束的所有扇区经过的次数是最多的，我们可以直接返回这个区间内的所有扇区。

否则，从 $1$ 开始，到 $\textit{rounds}[m]$ 结束的所有扇区和从 $\textit{rounds}[0]$ 开始，到 $n$ 结束的所有扇区的并集是经过次数最多的，我们可以返回这两个区间的并集。

时间复杂度 $O(n)$，其中 $n$ 是扇区的个数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostVisited(self, n: int, rounds: List[int]) -> List[int]:
        if rounds[0] <= rounds[-1]:
            return list(range(rounds[0], rounds[-1] + 1))
        return list(range(1, rounds[-1] + 1)) + list(range(rounds[0], n + 1))
```

#### Java

```java
class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int m = rounds.length - 1;
        List<Integer> ans = new ArrayList<>();
        if (rounds[0] <= rounds[m]) {
            for (int i = rounds[0]; i <= rounds[m]; ++i) {
                ans.add(i);
            }
        } else {
            for (int i = 1; i <= rounds[m]; ++i) {
                ans.add(i);
            }
            for (int i = rounds[0]; i <= n; ++i) {
                ans.add(i);
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
    vector<int> mostVisited(int n, vector<int>& rounds) {
        int m = rounds.size() - 1;
        vector<int> ans;
        if (rounds[0] <= rounds[m]) {
            for (int i = rounds[0]; i <= rounds[m]; ++i) {
                ans.push_back(i);
            }
        } else {
            for (int i = 1; i <= rounds[m]; ++i) {
                ans.push_back(i);
            }
            for (int i = rounds[0]; i <= n; ++i) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func mostVisited(n int, rounds []int) []int {
	m := len(rounds) - 1
	var ans []int
	if rounds[0] <= rounds[m] {
		for i := rounds[0]; i <= rounds[m]; i++ {
			ans = append(ans, i)
		}
	} else {
		for i := 1; i <= rounds[m]; i++ {
			ans = append(ans, i)
		}
		for i := rounds[0]; i <= n; i++ {
			ans = append(ans, i)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function mostVisited(n: number, rounds: number[]): number[] {
    const ans: number[] = [];
    const m = rounds.length - 1;
    if (rounds[0] <= rounds[m]) {
        for (let i = rounds[0]; i <= rounds[m]; ++i) {
            ans.push(i);
        }
    } else {
        for (let i = 1; i <= rounds[m]; ++i) {
            ans.push(i);
        }
        for (let i = rounds[0]; i <= n; ++i) {
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
