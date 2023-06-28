# [2585. Number of Ways to Earn Points](https://leetcode.com/problems/number-of-ways-to-earn-points)

[中文文档](/solution/2500-2599/2585.Number%20of%20Ways%20to%20Earn%20Points/README.md)

## Description

<p>There is a test that has <code>n</code> types of questions. You are given an integer <code>target</code> and a <strong>0-indexed</strong> 2D integer array <code>types</code> where <code>types[i] = [count<sub>i</sub>, marks<sub>i</sub>]</code> indicates that there are <code>count<sub>i</sub></code> questions of the <code>i<sup>th</sup></code> type, and each one of them is worth <code>marks<sub>i</sub></code> points.</p>

<ul>
</ul>

<p>Return <em>the number of ways you can earn <strong>exactly</strong> </em><code>target</code><em> points in the exam</em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that questions of the same type are indistinguishable.</p>

<ul>
	<li>For example, if there are <code>3</code> questions of the same type, then solving the <code>1<sup>st</sup></code> and <code>2<sup>nd</sup></code> questions is the same as solving the <code>1<sup>st</sup></code> and <code>3<sup>rd</sup></code> questions, or the <code>2<sup>nd</sup></code> and <code>3<sup>rd</sup></code> questions.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 6, types = [[6,1],[3,2],[2,3]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> You can earn 6 points in one of the seven ways:
- Solve 6 questions of the 0<sup>th</sup> type: 1 + 1 + 1 + 1 + 1 + 1 = 6
- Solve 4 questions of the 0<sup>th</sup> type and 1 question of the 1<sup>st</sup> type: 1 + 1 + 1 + 1 + 2 = 6
- Solve 2 questions of the 0<sup>th</sup> type and 2 questions of the 1<sup>st</sup> type: 1 + 1 + 2 + 2 = 6
- Solve 3 questions of the 0<sup>th</sup> type and 1 question of the 2<sup>nd</sup> type: 1 + 1 + 1 + 3 = 6
- Solve 1 question of the 0<sup>th</sup> type, 1 question of the 1<sup>st</sup> type and 1 question of the 2<sup>nd</sup> type: 1 + 2 + 3 = 6
- Solve 3 questions of the 1<sup>st</sup> type: 2 + 2 + 2 = 6
- Solve 2 questions of the 2<sup>nd</sup> type: 3 + 3 = 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 5, types = [[50,1],[50,2],[50,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can earn 5 points in one of the four ways:
- Solve 5 questions of the 0<sup>th</sup> type: 1 + 1 + 1 + 1 + 1 = 5
- Solve 3 questions of the 0<sup>th</sup> type and 1 question of the 1<sup>st</sup> type: 1 + 1 + 1 + 2 = 5
- Solve 1 questions of the 0<sup>th</sup> type and 2 questions of the 1<sup>st</sup> type: 1 + 2 + 2 = 5
- Solve 1 question of the 2<sup>nd</sup> type: 5
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 18, types = [[6,1],[3,2],[2,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can only earn 18 points by answering all questions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
	<li><code>n == types.length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>types[i].length == 2</code></li>
	<li><code>1 &lt;= count<sub>i</sub>, marks<sub>i</sub> &lt;= 50</code></li>
</ul>

## Solutions

**Solution 1: Dynamic Programming**

We define $f[i][j]$ to represent the number of methods to get $j$ points exactly from the first $i$ types of questions. Initially, $f[0][0] = 1$, and the rest $f[i][j] = 0$. The answer is $f[n][target]$.

We can enumerate the $i$th type of questions, suppose the number of questions of this type is $count$, and the score is $marks$. Then we can get the following state transition equation:

$$
f[i][j] = \sum_{k=0}^{count} f[i-1][j-k \times marks]
$$

where $k$ represents the number of questions of the $i$th type.

The final answer is $f[n][target]$. Note that the answer may be very large and needs to be modulo $10^9 + 7$.

The time complexity is $O(n \times target \times count)$ and the space complexity is $O(n \times target)$. $n$ is the number of types of questions, and $target$ and $count$ are the target score and the number of questions of each type, respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def waysToReachTarget(self, target: int, types: List[List[int]]) -> int:
        n = len(types)
        mod = 10**9 + 7
        f = [[0] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            count, marks = types[i - 1]
            for j in range(target + 1):
                for k in range(count + 1):
                    if j >= k * marks:
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod
        return f[n][target]
```

### **Java**

```java
class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int count = types[i - 1][0], marks = types[i - 1][1];
            for (int j = 0; j <= target; ++j) {
                for (int k = 0; k <= count; ++k) {
                    if (j >= k * marks) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                    }
                }
            }
        }
        return f[n][target];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int waysToReachTarget(int target, vector<vector<int>>& types) {
        int n = types.size();
        const int mod = 1e9 + 7;
        int f[n + 1][target + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int count = types[i - 1][0], marks = types[i - 1][1];
            for (int j = 0; j <= target; ++j) {
                for (int k = 0; k <= count; ++k) {
                    if (j >= k * marks) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                    }
                }
            }
        }
        return f[n][target];
    }
};
```

### **Go**

```go
func waysToReachTarget(target int, types [][]int) int {
	n := len(types)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
	}
	f[0][0] = 1
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		count, marks := types[i-1][0], types[i-1][1]
		for j := 0; j <= target; j++ {
			for k := 0; k <= count; k++ {
				if j >= k*marks {
					f[i][j] = (f[i][j] + f[i-1][j-k*marks]) % mod
				}
			}
		}
	}
	return f[n][target]
}
```

### **TypeScript**

```ts
function waysToReachTarget(target: number, types: number[][]): number {
    const n = types.length;
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () =>
        Array(target + 1).fill(0),
    );
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const [count, marks] = types[i - 1];
        for (let j = 0; j <= target; ++j) {
            for (let k = 0; k <= count; ++k) {
                if (j >= k * marks) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                }
            }
        }
    }
    return f[n][target];
}
```

### **...**

```

```

<!-- tabs:end -->
