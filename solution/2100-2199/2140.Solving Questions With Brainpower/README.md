# [2140. 解决智力问题](https://leetcode.cn/problems/solving-questions-with-brainpower)

[English Version](/solution/2100-2199/2140.Solving%20Questions%20With%20Brainpower/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>questions</code>&nbsp;，其中&nbsp;<code>questions[i] = [points<sub>i</sub>, brainpower<sub>i</sub>]</code>&nbsp;。</p>

<p>这个数组表示一场考试里的一系列题目，你需要 <strong>按顺序</strong>&nbsp;（也就是从问题 <code>0</code><strong>&nbsp;</strong>开始依次解决），针对每个问题选择 <strong>解决</strong>&nbsp;或者 <strong>跳过</strong>&nbsp;操作。解决问题 <code>i</code>&nbsp;将让你 <b>获得</b>&nbsp;&nbsp;<code>points<sub>i</sub></code>&nbsp;的分数，但是你将 <strong>无法</strong>&nbsp;解决接下来的&nbsp;<code>brainpower<sub>i</sub></code>&nbsp;个问题（即只能跳过接下来的 <code>brainpower<sub>i</sub></code><sub>&nbsp;</sub>个问题）。如果你跳过问题&nbsp;<code>i</code>&nbsp;，你可以对下一个问题决定使用哪种操作。</p>

<ul>
	<li>比方说，给你&nbsp;<code>questions = [[3, 2], [4, 3], [4, 4], [2, 5]]</code>&nbsp;：

    <ul>
    	<li>如果问题&nbsp;<code>0</code>&nbsp;被解决了， 那么你可以获得&nbsp;<code>3</code>&nbsp;分，但你不能解决问题&nbsp;<code>1</code> 和&nbsp;<code>2</code>&nbsp;。</li>
    	<li>如果你跳过问题&nbsp;<code>0</code>&nbsp;，且解决问题&nbsp;<code>1</code>&nbsp;，你将获得 <code>4</code> 分但是不能解决问题&nbsp;<code>2</code> 和&nbsp;<code>3</code>&nbsp;。</li>
    </ul>
    </li>

</ul>

<p>请你返回这场考试里你能获得的 <strong>最高</strong>&nbsp;分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>questions = [[3,2],[4,3],[4,4],[2,5]]
<b>输出：</b>5
<b>解释：</b>解决问题 0 和 3 得到最高分。
- 解决问题 0 ：获得 3 分，但接下来 2 个问题都不能解决。
- 不能解决问题 1 和 2
- 解决问题 3 ：获得 2 分
总得分为：3 + 2 = 5 。没有别的办法获得 5 分或者多于 5 分。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<b>输出：</b>7
<b>解释：</b>解决问题 1 和 4 得到最高分。
- 跳过问题 0
- 解决问题 1 ：获得 2 分，但接下来 2 个问题都不能解决。
- 不能解决问题 2 和 3
- 解决问题 4 ：获得 5 分
总得分为：2 + 5 = 7 。没有别的办法获得 7 分或者多于 7 分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= questions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>questions[i].length == 2</code></li>
	<li><code>1 &lt;= points<sub>i</sub>, brainpower<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostPoints(self, questions: List[List[int]]) -> int:
        @cache
        def dfs(i):
            if i >= len(questions):
                return 0
            return max(questions[i][0] + dfs(i + questions[i][1] + 1), dfs(i + 1))

        return dfs(0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private long[] memo;
    private int[][] questions;

    public long mostPoints(int[][] questions) {
        this.questions = questions;
        memo = new long[questions.length];
        Arrays.fill(memo, -1);
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= questions.length) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        long ans = Math.max(questions[i][0] + dfs(i + questions[i][1] + 1), dfs(i + 1));
        memo[i] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        vector<long long> memo(questions.size(), -1);
        return dfs(0, questions, memo);
    }

    long long dfs(int i, vector<vector<int>>& questions, vector<long long>& memo) {
        if (i >= questions.size()) return 0;
        if (memo[i] != -1) return memo[i];
        long long ans = max(questions[i][0] + dfs(i + questions[i][1] + 1, questions, memo), dfs(i + 1, questions, memo));
        memo[i] = ans;
        return ans;
    }
};
```

### **Go**

```go
func mostPoints(questions [][]int) int64 {
	n := len(questions)
	memo := make([]int, n)
	for i := range memo {
		memo[i] = -1
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if memo[i] != -1 {
			return memo[i]
		}
		ans := max(questions[i][0]+dfs(i+questions[i][1]+1), dfs(i+1))
		memo[i] = ans
		return ans
	}
	return int64(dfs(0))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
