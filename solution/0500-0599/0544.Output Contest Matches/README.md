# [544. 输出比赛匹配对](https://leetcode.cn/problems/output-contest-matches)

[English Version](/solution/0500-0599/0544.Output%20Contest%20Matches/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 NBA 季后赛中，我们总是安排较强的队伍对战较弱的队伍，例如用排名第 1 的队伍和第 n 的队伍对决，这是一个可以让比赛更加有趣的好策略。现在，给你&nbsp;<strong>n&nbsp;</strong>支队伍，你需要以字符串格式输出它们的&nbsp;<strong>最终&nbsp;</strong>比赛配对。</p>

<p><strong>n </strong>支队伍按从 1 到 n 的正整数格式给出，分别代表它们的初始排名（排名 1 最强，排名 n 最弱）。我们用括号（&#39;(&#39;, &#39;)&#39;）和逗号（&#39;,&#39;）来表示匹配对&mdash;&mdash;括号（&#39;(&#39;, &#39;)&#39;）表示匹配，逗号（&#39;,&#39;）来用于分割。&nbsp;在每一轮的匹配过程中，你都需要遵循将强队与弱队配对的原则。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:</strong> (1,2)
<strong>解析:</strong> 
初始地，我们有队1和队2两支队伍，按照1，2排列。
因此 用 &#39;(&#39;, &#39;)&#39; 和 &#39;,&#39;来将队1和队2进行配对，得到最终答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> 4
<strong>输出:</strong> ((1,4),(2,3))
<strong>解析:</strong> 
在第一轮，我们将队伍1和4配对，2和3配对，以满足将强队和弱队搭配的效果。得到(1,4),(2,3).
在第二轮，(1,4) 和 (2,3) 的赢家需要进行比赛以确定最终赢家，因此需要再在外面加一层括号。
于是最终答案是((1,4),(2,3))。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> 8
<strong>输出:</strong> (((1,8),(4,5)),((2,7),(3,6)))
<strong>解析:</strong> 
第一轮: (1,8),(2,7),(3,6),(4,5)
第二轮: ((1,8),(4,5)),((2,7),(3,6))
第三轮 (((1,8),(4,5)),((2,7),(3,6)))
由于第三轮会决出最终胜者，故输出答案为(((1,8),(4,5)),((2,7),(3,6)))。
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li><strong>n&nbsp;</strong>的范围是&nbsp;[2, 2<sup>12</sup>].</li>
	<li>保证 n 可以写成&nbsp;2<sup>k</sup>&nbsp;的形式，其中 k 是正整数。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

假设 `team[i]` 为当前轮次中第 i 强的队伍。

每一轮，将第 i 支队伍变成 `"(" + team[i] + "," + team[n-1-i] + ")"`，并且每一轮淘汰一半的队伍。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    string findContestMatch(int n) {
        vector<string> team(n);
        for (int i = 0; i < n; ++i) team[i] = to_string(i + 1);
        for (; n > 1; n >>= 1) {
            for (int i = 0; i<n> > 1; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }
};
```

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
