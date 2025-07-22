---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2140.Solving%20Questions%20With%20Brainpower/README_EN.md
rating: 1709
source: Weekly Contest 276 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [2140. Solving Questions With Brainpower](https://leetcode.com/problems/solving-questions-with-brainpower)

[中文文档](/solution/2100-2199/2140.Solving%20Questions%20With%20Brainpower/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization

We design a function $\textit{dfs}(i)$, which represents the maximum score that can be obtained starting from the $i$-th question. The answer is $\textit{dfs}(0)$.

The function $\textit{dfs}(i)$ is calculated as follows:

-   If $i \geq n$, it means all questions have been solved, so return $0$;
-   Otherwise, let the score of the $i$-th question be $p$, and the number of questions to skip be $b$. Then, $\textit{dfs}(i) = \max(p + \textit{dfs}(i + b + 1), \textit{dfs}(i + 1))$.

To avoid repeated calculations, we can use memoization by storing the values of $\textit{dfs}(i)$ in an array $f$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of questions.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostPoints(self, questions: List[List[int]]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(questions):
                return 0
            p, b = questions[i]
            return max(p + dfs(i + b + 1), dfs(i + 1))

        return dfs(0)
```

#### Java

```java
class Solution {
    private int n;
    private Long[] f;
    private int[][] questions;

    public long mostPoints(int[][] questions) {
        n = questions.length;
        f = new Long[n];
        this.questions = questions;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int p = questions[i][0], b = questions[i][1];
        return f[i] = Math.max(p + dfs(i + b + 1), dfs(i + 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        int n = questions.size();
        long long f[n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            int p = questions[i][0], b = questions[i][1];
            return f[i] = max(p + dfs(i + b + 1), dfs(i + 1));
        };
        return dfs(0);
    }
};
```

#### Go

```go
func mostPoints(questions [][]int) int64 {
	n := len(questions)
	f := make([]int64, n)
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		p, b := questions[i][0], questions[i][1]
		f[i] = max(int64(p)+dfs(i+b+1), dfs(i+1))
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function mostPoints(questions: number[][]): number {
    const n = questions.length;
    const f = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] > 0) {
            return f[i];
        }
        const [p, b] = questions[i];
        return (f[i] = Math.max(p + dfs(i + b + 1), dfs(i + 1)));
    };
    return dfs(0);
}
```

#### Rust

```rust
impl Solution {
    pub fn most_points(questions: Vec<Vec<i32>>) -> i64 {
        let n = questions.len();
        let mut f = vec![-1; n];

        fn dfs(i: usize, questions: &Vec<Vec<i32>>, f: &mut Vec<i64>) -> i64 {
            if i >= questions.len() {
                return 0;
            }
            if f[i] != -1 {
                return f[i];
            }
            let p = questions[i][0] as i64;
            let b = questions[i][1] as usize;
            f[i] = (p + dfs(i + b + 1, questions, f)).max(dfs(i + 1, questions, f));
            f[i]
        }

        dfs(0, &questions, &mut f)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We define $f[i]$ as the maximum score that can be obtained starting from the $i$-th problem. Therefore, the answer is $f[0]$.

Considering $f[i]$, let the score of the $i$-th problem be $p$, and the number of problems to skip be $b$. If we solve the $i$-th problem, then we need to solve the problem after skipping $b$ problems, thus $f[i] = p + f[i + b + 1]$. If we skip the $i$-th problem, then we start solving from the $(i + 1)$-th problem, thus $f[i] = f[i + 1]$. We take the maximum value of the two. The state transition equation is as follows:

$$
f[i] = \max(p + f[i + b + 1], f[i + 1])
$$

We calculate the values of $f$ from back to front, and finally return $f[0]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of problems.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mostPoints(self, questions: List[List[int]]) -> int:
        n = len(questions)
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            p, b = questions[i]
            j = i + b + 1
            f[i] = max(f[i + 1], p + (0 if j > n else f[j]))
        return f[0]
```

#### Java

```java
class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] f = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int p = questions[i][0], b = questions[i][1];
            int j = i + b + 1;
            f[i] = Math.max(f[i + 1], p + (j > n ? 0 : f[j]));
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        int n = questions.size();
        long long f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            int p = questions[i][0], b = questions[i][1];
            int j = i + b + 1;
            f[i] = max(f[i + 1], p + (j > n ? 0 : f[j]));
        }
        return f[0];
    }
};
```

#### Go

```go
func mostPoints(questions [][]int) int64 {
	n := len(questions)
	f := make([]int64, n+1)
	for i := n - 1; i >= 0; i-- {
		p := int64(questions[i][0])
		if j := i + questions[i][1] + 1; j <= n {
			p += f[j]
		}
		f[i] = max(f[i+1], p)
	}
	return f[0]
}
```

#### TypeScript

```ts
function mostPoints(questions: number[][]): number {
    const n = questions.length;
    const f = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const [p, b] = questions[i];
        const j = i + b + 1;
        f[i] = Math.max(f[i + 1], p + (j > n ? 0 : f[j]));
    }
    return f[0];
}
```

#### Rust

```rust
impl Solution {
    pub fn most_points(questions: Vec<Vec<i32>>) -> i64 {
        let n = questions.len();
        let mut f = vec![0; n + 1];
        for i in (0..n).rev() {
            let p = questions[i][0] as i64;
            let b = questions[i][1] as usize;
            let j = i + b + 1;
            f[i] = f[i + 1].max(p + if j > n { 0 } else { f[j] });
        }
        f[0]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
