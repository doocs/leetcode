# [2019. The Score of Students Solving Math Expression](https://leetcode.com/problems/the-score-of-students-solving-math-expression)

[中文文档](/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/README.md)

## Description

<p>You are given a string <code>s</code> that contains digits <code>0-9</code>, addition symbols <code>&#39;+&#39;</code>, and multiplication symbols <code>&#39;*&#39;</code> <strong>only</strong>, representing a <strong>valid</strong> math expression of <strong>single digit numbers</strong> (e.g., <code>3+5*2</code>). This expression was given to <code>n</code> elementary school students. The students were instructed to get the answer of the expression by following this <strong>order of operations</strong>:</p>

<ol>
	<li>Compute <strong>multiplication</strong>, reading from <strong>left to right</strong>; Then,</li>
	<li>Compute <strong>addition</strong>, reading from <strong>left to right</strong>.</li>
</ol>

<p>You are given an integer array <code>answers</code> of length <code>n</code>, which are the submitted answers of the students in no particular order. You are asked to grade the <code>answers</code>, by following these <strong>rules</strong>:</p>

<ul>
	<li>If an answer <strong>equals</strong> the correct answer of the expression, this student will be rewarded <code>5</code> points;</li>
	<li>Otherwise, if the answer <strong>could be interpreted</strong> as if the student applied the operators <strong>in the wrong order</strong> but had <strong>correct arithmetic</strong>, this student will be rewarded <code>2</code> points;</li>
	<li>Otherwise, this student will be rewarded <code>0</code> points.</li>
</ul>

<p>Return <em>the sum of the points of the students</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/images/student_solving_math.png" style="width: 678px; height: 109px;" />
<pre>
<strong>Input:</strong> s = &quot;7+3*1*2&quot;, answers = [20,13,42]
<strong>Output:</strong> 7
<strong>Explanation:</strong> As illustrated above, the correct answer of the expression is 13, therefore one student is rewarded 5 points: [20,<u><strong>13</strong></u>,42]
A student might have applied the operators in this wrong order: ((7+3)*1)*2 = 20. Therefore one student is rewarded 2 points: [<u><strong>20</strong></u>,13,42]
The points for the students are: [2,5,0]. The sum of the points is 2+5+0=7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3+5*2&quot;, answers = [13,0,10,13,13,16,16]
<strong>Output:</strong> 19
<strong>Explanation:</strong> The correct answer of the expression is 13, therefore three students are rewarded 5 points each: [<strong><u>13</u></strong>,0,10,<strong><u>13</u></strong>,<strong><u>13</u></strong>,16,16]
A student might have applied the operators in this wrong order: ((3+5)*2 = 16. Therefore two students are rewarded 2 points: [13,0,10,13,13,<strong><u>16</u></strong>,<strong><u>16</u></strong>]
The points for the students are: [5,0,0,5,5,2,2]. The sum of the points is 5+0+0+5+5+2+2=19.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;6+0*1&quot;, answers = [12,9,6,4,8,6]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The correct answer of the expression is 6.
If a student had incorrectly done (6+0)*1, the answer would also be 6.
By the rules of grading, the students will still be rewarded 5 points (as they got the correct answer), not 2 points.
The points for the students are: [0,0,5,0,0,5]. The sum of the points is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 31</code></li>
	<li><code>s</code> represents a valid expression that contains only digits <code>0-9</code>, <code>&#39;+&#39;</code>, and <code>&#39;*&#39;</code> only.</li>
	<li>All the integer operands in the expression are in the <strong>inclusive</strong> range <code>[0, 9]</code>.</li>
	<li><code>1 &lt;=</code> The count of all operators (<code>&#39;+&#39;</code> and <code>&#39;*&#39;</code>) in the math expression <code>&lt;= 15</code></li>
	<li>Test data are generated such that the correct answer of the expression is in the range of <code>[0, 1000]</code>.</li>
	<li><code>n == answers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= answers[i] &lt;= 1000</code></li>
</ul>

## Solutions

**Solution 1: Dynamic Programming (Interval DP)**

First, we design a function $cal(s)$ to calculate the result of a valid mathematical expression that only contains single-digit numbers. The correct answer is $x = cal(s)$.

Let the length of the string $s$ be $n$, then the number of digits in $s$ is $m = \frac{n+1}{2}$.

We define $f[i][j]$ as the possible values of the result calculated by selecting the digits from the $i$-th to the $j$-th in $s$ (index starts from $0$). Initially, $f[i][i]$ represents the selection of the $i$-th digit, and the result can only be this digit itself, i.e., $f[i][i] = \{s[i \times 2]\}$ (the $i$-th digit maps to the character at index $i \times 2$ in the string $s$).

Next, we enumerate $i$ from large to small, and then enumerate $j$ from small to large. We need to find out the possible values of the results of the operation of all digits in the interval $[i, j]$. We enumerate the boundary point $k$ in the interval $[i, j]$, then $f[i][j]$ can be obtained from $f[i][k]$ and $f[k+1][j]$ through the operator $s[k \times 2 + 1]$. Therefore, we can get the following state transition equation:

$$
f[i][j] = \begin{cases}
\{s[i \times 2]\}, & i = j \\
\bigcup\limits_{k=i}^{j-1} \{f[i][k] \otimes f[k+1][j]\}, & i < j
\end{cases}
$$

Where $\otimes$ represents the operator, i.e., $s[k \times 2 + 1]$.

The possible values of the results of all digit operations in the string $s$ are $f[0][m-1]$.

Finally, we count the answer. We use an array $cnt$ to count the number of times each answer appears in the answer array $answers$. If the answer is equal to $x$, then this student gets $5$ points, otherwise if the answer is in $f[0][m-1]$, then this student gets $2$ points. Traverse $cnt$ to count the answer.

The time complexity is $O(n^3 \times M^2)$, and the space complexity is $O(n^2 \times M^2)$. Here, $M$ is the maximum possible value of the answer, and $n$ is the number of digits in the length of the string $s$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def scoreOfStudents(self, s: str, answers: List[int]) -> int:
        def cal(s: str) -> int:
            res, pre = 0, int(s[0])
            for i in range(1, n, 2):
                if s[i] == "*":
                    pre *= int(s[i + 1])
                else:
                    res += pre
                    pre = int(s[i + 1])
            res += pre
            return res

        n = len(s)
        x = cal(s)
        m = (n + 1) >> 1
        f = [[set() for _ in range(m)] for _ in range(m)]
        for i in range(m):
            f[i][i] = {int(s[i << 1])}
        for i in range(m - 1, -1, -1):
            for j in range(i, m):
                for k in range(i, j):
                    for l in f[i][k]:
                        for r in f[k + 1][j]:
                            if s[k << 1 | 1] == "+" and l + r <= 1000:
                                f[i][j].add(l + r)
                            elif s[k << 1 | 1] == "*" and l * r <= 1000:
                                f[i][j].add(l * r)
        cnt = Counter(answers)
        ans = cnt[x] * 5
        for k, v in cnt.items():
            if k != x and k in f[0][m - 1]:
                ans += v << 1
        return ans
```

### **Java**

```java
class Solution {
    public int scoreOfStudents(String s, int[] answers) {
        int n = s.length();
        int x = cal(s);
        int m = (n + 1) >> 1;
        Set<Integer>[][] f = new Set[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                f[i][j] = new HashSet<>();
            }
            f[i][i].add(s.charAt(i << 1) - '0');
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = i; j < m; ++j) {
                for (int k = i; k < j; ++k) {
                    for (int l : f[i][k]) {
                        for (int r : f[k + 1][j]) {
                            char op = s.charAt(k << 1 | 1);
                            if (op == '+' && l + r <= 1000) {
                                f[i][j].add(l + r);
                            } else if (op == '*' && l * r <= 1000) {
                                f[i][j].add(l * r);
                            }
                        }
                    }
                }
            }
        }
        int[] cnt = new int[1001];
        for (int ans : answers) {
            ++cnt[ans];
        }
        int ans = 5 * cnt[x];
        for (int i = 0; i <= 1000; ++i) {
            if (i != x && f[0][m - 1].contains(i)) {
                ans += 2 * cnt[i];
            }
        }
        return ans;
    }

    private int cal(String s) {
        int res = 0, pre = s.charAt(0) - '0';
        for (int i = 1; i < s.length(); i += 2) {
            char op = s.charAt(i);
            int cur = s.charAt(i + 1) - '0';
            if (op == '*') {
                pre *= cur;
            } else {
                res += pre;
                pre = cur;
            }
        }
        res += pre;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int scoreOfStudents(string s, vector<int>& answers) {
        int n = s.size();
        int x = cal(s);
        int m = (n + 1) >> 1;
        unordered_set<int> f[m][m];
        for (int i = 0; i < m; ++i) {
            f[i][i] = {s[i * 2] - '0'};
        }
        for (int i = m - 1; ~i; --i) {
            for (int j = i; j < m; ++j) {
                for (int k = i; k < j; ++k) {
                    for (int l : f[i][k]) {
                        for (int r : f[k + 1][j]) {
                            char op = s[k << 1 | 1];
                            if (op == '+' && l + r <= 1000) {
                                f[i][j].insert(l + r);
                            } else if (op == '*' && l * r <= 1000) {
                                f[i][j].insert(l * r);
                            }
                        }
                    }
                }
            }
        }
        int cnt[1001]{};
        for (int t : answers) {
            ++cnt[t];
        }
        int ans = 5 * cnt[x];
        for (int i = 0; i <= 1000; ++i) {
            if (i != x && f[0][m - 1].count(i)) {
                ans += cnt[i] << 1;
            }
        }
        return ans;
    }

    int cal(string& s) {
        int res = 0;
        int pre = s[0] - '0';
        for (int i = 1; i < s.size(); i += 2) {
            int cur = s[i + 1] - '0';
            if (s[i] == '*') {
                pre *= cur;
            } else {
                res += pre;
                pre = cur;
            }
        }
        res += pre;
        return res;
    }
};
```

### **Go**

```go
func scoreOfStudents(s string, answers []int) int {
	n := len(s)
	x := cal(s)
	m := (n + 1) >> 1
	f := make([][]map[int]bool, m)
	for i := range f {
		f[i] = make([]map[int]bool, m)
		for j := range f[i] {
			f[i][j] = make(map[int]bool)
		}
		f[i][i][int(s[i<<1]-'0')] = true
	}
	for i := m - 1; i >= 0; i-- {
		for j := i; j < m; j++ {
			for k := i; k < j; k++ {
				for l := range f[i][k] {
					for r := range f[k+1][j] {
						op := s[k<<1|1]
						if op == '+' && l+r <= 1000 {
							f[i][j][l+r] = true
						} else if op == '*' && l*r <= 1000 {
							f[i][j][l*r] = true
						}
					}
				}
			}
		}
	}
	cnt := [1001]int{}
	for _, v := range answers {
		cnt[v]++
	}
	ans := cnt[x] * 5
	for k, v := range cnt {
		if k != x && f[0][m-1][k] {
			ans += v << 1
		}
	}
	return ans
}

func cal(s string) int {
	res, pre := 0, int(s[0]-'0')
	for i := 1; i < len(s); i += 2 {
		cur := int(s[i+1] - '0')
		if s[i] == '+' {
			res += pre
			pre = cur
		} else {
			pre *= cur
		}
	}
	res += pre
	return res
}
```

### **TypeScript**

```ts
function scoreOfStudents(s: string, answers: number[]): number {
    const n = s.length;
    const cal = (s: string): number => {
        let res = 0;
        let pre = s.charCodeAt(0) - '0'.charCodeAt(0);
        for (let i = 1; i < s.length; i += 2) {
            const cur = s.charCodeAt(i + 1) - '0'.charCodeAt(0);
            if (s[i] === '+') {
                res += pre;
                pre = cur;
            } else {
                pre *= cur;
            }
        }
        res += pre;
        return res;
    };
    const x = cal(s);
    const m = (n + 1) >> 1;
    const f: Set<number>[][] = Array(m)
        .fill(0)
        .map(() =>
            Array(m)
                .fill(0)
                .map(() => new Set()),
        );
    for (let i = 0; i < m; ++i) {
        f[i][i].add(s[i << 1].charCodeAt(0) - '0'.charCodeAt(0));
    }
    for (let i = m - 1; i >= 0; --i) {
        for (let j = i; j < m; ++j) {
            for (let k = i; k < j; ++k) {
                for (const l of f[i][k]) {
                    for (const r of f[k + 1][j]) {
                        const op = s[(k << 1) + 1];
                        if (op === '+' && l + r <= 1000) {
                            f[i][j].add(l + r);
                        } else if (op === '*' && l * r <= 1000) {
                            f[i][j].add(l * r);
                        }
                    }
                }
            }
        }
    }
    const cnt: number[] = Array(1001).fill(0);
    for (const v of answers) {
        ++cnt[v];
    }
    let ans = cnt[x] * 5;
    for (let i = 0; i <= 1000; ++i) {
        if (i !== x && f[0][m - 1].has(i)) {
            ans += cnt[i] << 1;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
