# [2140. Solving Questions With Brainpower](https://leetcode.com/problems/solving-questions-with-brainpower)

[中文文档](/solution/2100-2199/2140.Solving%20Questions%20With%20Brainpower/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>questions</code> where <code>questions[i] = [points<sub>i</sub>, brainpower<sub>i</sub>]</code>.</p>

<p>The array describes the questions of an exam, where you have to process the questions <strong>in order</strong> (i.e., starting from question <code>0</code>) and make a decision whether to <strong>solve</strong> or <strong>skip</strong> each question. Solving question <code>i</code> will <strong>earn</strong> you <code>points<sub>i</sub></code> points but you will be <strong>unable</strong> to solve each of the next <code>brainpower<sub>i</sub></code> questions. If you skip question <code>i</code>, you get to make the decision on the next question.</p>

<ul>
	<li>For example, given <code>questions = [[3, 2], [4, 3], [4, 4], [2, 5]]</code>:

    <ul>
    	<li>If question <code>0</code> is solved, you will earn <code>3</code> points but you will be unable to solve questions <code>1</code> and <code>2</code>.</li>
    	<li>If instead, question <code>0</code> is skipped and question <code>1</code> is solved, you will earn <code>4</code> points but you will be unable to solve questions <code>2</code> and <code>3</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the <strong>maximum</strong> points you can earn for the exam</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> questions = [[3,2],[4,3],[4,4],[2,5]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The maximum points can be earned by solving questions 0 and 3.
- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
- Unable to solve questions 1 and 2
- Solve question 3: Earn 2 points
Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The maximum points can be earned by solving questions 1 and 4.
- Skip question 0
- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
- Unable to solve questions 2 and 3
- Solve question 4: Earn 5 points
Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= questions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>questions[i].length == 2</code></li>
	<li><code>1 &lt;= points<sub>i</sub>, brainpower<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

```ts

```

### **...**

```

```

<!-- tabs:end -->
