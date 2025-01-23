---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2178.Maximum%20Split%20of%20Positive%20Even%20Integers/README.md
rating: 1538
source: 第 72 场双周赛 Q3
tags:
    - 贪心
    - 数学
    - 回溯
---

<!-- problem:start -->

# [2178. 拆分成最多数目的正偶数之和](https://leetcode.cn/problems/maximum-split-of-positive-even-integers)

[English Version](/solution/2100-2199/2178.Maximum%20Split%20of%20Positive%20Even%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>finalSum</code>&nbsp;。请你将它拆分成若干个&nbsp;<strong>互不相同</strong> 的正偶数之和，且拆分出来的正偶数数目&nbsp;<strong>最多</strong>&nbsp;。</p>

<ul>
	<li>比方说，给你&nbsp;<code>finalSum = 12</code>&nbsp;，那么这些拆分是&nbsp;<strong>符合要求</strong> 的（互不相同的正偶数且和为&nbsp;<code>finalSum</code>）：<code>(2 + 10)</code>&nbsp;，<code>(2 + 4 + 6)</code>&nbsp;和&nbsp;<code>(4 + 8)</code>&nbsp;。它们中，<code>(2 + 4 + 6)</code>&nbsp;包含最多数目的整数。注意&nbsp;<code>finalSum</code>&nbsp;不能拆分成&nbsp;<code>(2 + 2 + 4 + 4)</code>&nbsp;，因为拆分出来的整数必须互不相同。</li>
</ul>

<p>请你返回一个整数数组，表示将整数拆分成 <strong>最多</strong> 数目的正偶数数组。如果没有办法将&nbsp;<code>finalSum</code>&nbsp;进行拆分，请你返回一个&nbsp;<strong>空</strong>&nbsp;数组。你可以按 <b>任意</b>&nbsp;顺序返回这些整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>finalSum = 12
<b>输出：</b>[2,4,6]
<b>解释：</b>以下是一些符合要求的拆分：<code>(2 + 10)<span style="">，</span></code><code>(2 + 4 + 6) </code>和 <code>(4 + 8) 。</code>
(2 + 4 + 6) 为最多数目的整数，数目为 3 ，所以我们返回 [2,4,6] 。
[2,6,4] ，[6,2,4] 等等也都是可行的解。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>finalSum = 7
<b>输出：</b>[]
<b>解释：</b>没有办法将 finalSum 进行拆分。
所以返回空数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>finalSum = 28
<b>输出：</b>[6,8,2,12]
<b>解释：</b>以下是一些符合要求的拆分：<code>(2 + 26)<span style="">，</span></code><code>(6 + 8 + 2 + 12)</code> 和 <code>(4 + 24) 。</code>
<code>(6 + 8 + 2 + 12)</code> 有最多数目的整数，数目为 4 ，所以我们返回 [6,8,2,12] 。
[10,2,4,12] ，[6,2,4,16] 等等也都是可行的解。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= finalSum &lt;= 10<sup>10</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

如果 $\textit{finalSum}$ 是奇数，那么无法拆分成若干个互不相同的正偶数之和，直接返回空数组。

否则，我们可以贪心地按照 $2, 4, 6, \cdots$ 的顺序拆分 $\textit{finalSum}$，直到 $\textit{finalSum}$ 无法再拆分出一个不同的正偶数为止，此时我们将剩余的 $\textit{finalSum}$ 加到最后一个正偶数上即可。

时间复杂度 $O(\sqrt{\textit{finalSum}})$，忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumEvenSplit(self, finalSum: int) -> List[int]:
        if finalSum & 1:
            return []
        ans = []
        i = 2
        while i <= finalSum:
            finalSum -= i
            ans.append(i)
            i += 2
        ans[-1] += finalSum
        return ans
```

#### Java

```java
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.add(i);
            finalSum -= i;
        }
        ans.add(ans.remove(ans.size() - 1) + finalSum);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> maximumEvenSplit(long long finalSum) {
        vector<long long> ans;
        if (finalSum % 2) {
            return ans;
        }
        for (long long i = 2; i <= finalSum; i += 2) {
            ans.push_back(i);
            finalSum -= i;
        }
        ans.back() += finalSum;
        return ans;
    }
};
```

#### Go

```go
func maximumEvenSplit(finalSum int64) (ans []int64) {
	if finalSum%2 == 1 {
		return
	}
	for i := int64(2); i <= finalSum; i += 2 {
		ans = append(ans, i)
		finalSum -= i
	}
	ans[len(ans)-1] += finalSum
	return
}
```

#### TypeScript

```ts
function maximumEvenSplit(finalSum: number): number[] {
    const ans: number[] = [];
    if (finalSum % 2 === 1) {
        return ans;
    }
    for (let i = 2; i <= finalSum; i += 2) {
        ans.push(i);
        finalSum -= i;
    }
    ans[ans.length - 1] += finalSum;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_even_split(mut final_sum: i64) -> Vec<i64> {
        let mut ans = Vec::new();
        if final_sum % 2 != 0 {
            return ans;
        }
        let mut i = 2;
        while i <= final_sum {
            ans.push(i);
            final_sum -= i;
            i += 2;
        }
        if let Some(last) = ans.last_mut() {
            *last += final_sum;
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public IList<long> MaximumEvenSplit(long finalSum) {
        IList<long> ans = new List<long>();
        if (finalSum % 2 == 1) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.Add(i);
            finalSum -= i;
        }
        ans[ans.Count - 1] += finalSum;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
