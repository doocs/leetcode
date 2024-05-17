---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0544.Output%20Contest%20Matches/README.md
tags:
    - 递归
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [544. 输出比赛匹配对 🔒](https://leetcode.cn/problems/output-contest-matches)

[English Version](/solution/0500-0599/0544.Output%20Contest%20Matches/README_EN.md)

## 题目描述

<!-- description:start -->

<p>&nbsp;</p>

<p>在 NBA 季后赛期间，我们总是安排相对强大的球队与相对弱小的球队比赛，就像让排名第 <code>1</code> 的球队与排名第 <code>n</code> 的球队比赛一样，这是一种使比赛更加有趣的好策略。</p>

<p>现给定 <code>n</code> 支球队，请以字符串的形式返回它们的最终的比赛匹配方案。</p>

<p>这 <code>n</code> 支球队从 <code>1</code> 到 <code>n</code> 进行标记，代表它们的初始排名（即，排名 <code>1</code> 的是最强的球队，排名 <code>n</code> 的是最弱的球队）。</p>

<p>我们将使用括号 <code>'('</code> 和 <code>')'</code> 以及逗号 <code>','</code> 来表示比赛的匹配情况。我们使用括号来表示匹配，逗号来表示分组。在每一轮的匹配过程中，你总是需要遵循使相对强大的球队与相对弱小的球队配对的策略。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> n = 4
<strong>输出:</strong> "((1,4),(2,3))"
<strong>解释:</strong> 
在第一轮，我们将队伍 1 和 4 配对，2 和 3 配对，以满足将强队和弱队搭配的效果。得到(1,4),(2,3).
在第二轮，(1,4) 和 (2,3) 的赢家需要进行比赛以确定最终赢家，因此需要再在外面加一层括号。
于是最终答案是((1,4),(2,3))。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> n = 8
<strong>输出:</strong> "(((1,8),(4,5)),((2,7),(3,6)))"
<strong>解释:</strong> 
第一轮: (1,8),(2,7),(3,6),(4,5)
第二轮: ((1,8),(4,5)),((2,7),(3,6))
第三轮 (((1,8),(4,5)),((2,7),(3,6)))
由于第三轮会决出最终胜者，故输出答案为(((1,8),(4,5)),((2,7),(3,6)))。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == 2x</code>，并且 <code>x</code> 在范围 <code>[1,12]</code> 内。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

假设 `team[i]` 为当前轮次中第 i 强的队伍。

每一轮，将第 i 支队伍变成 `"(" + team[i] + "," + team[n-1-i] + ")"`，并且每一轮淘汰一半的队伍。

<!-- tabs:start -->

```python
class Solution:
    def findContestMatch(self, n: int) -> str:
        team = [str(i + 1) for i in range(n)]
        while n > 1:
            for i in range(n >> 1):
                team[i] = f'({team[i]},{team[n - 1 - i]})'
            n >>= 1
        return team[0]
```

```java
class Solution {
    public String findContestMatch(int n) {
        String[] team = new String[n];
        for (int i = 0; i < n; ++i) {
            team[i] = "" + (i + 1);
        }
        for (; n > 1; n /= 2) {
            for (int i = 0; i < n / 2; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }
}
```

```cpp
class Solution {
public:
    string findContestMatch(int n) {
        vector<string> team(n);
        for (int i = 0; i < n; ++i) team[i] = to_string(i + 1);
        for (; n > 1; n >>= 1) {
            for (int i = 0; i < n >> 1; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }
};
```

```go
func findContestMatch(n int) string {
	team := make([]string, n)
	for i := range team {
		team[i] = strconv.Itoa(i + 1)
	}
	for n > 1 {
		for i := 0; i < n>>1; i++ {
			team[i] = "(" + team[i] + "," + team[n-1-i] + ")"
		}
		n >>= 1
	}
	return team[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
