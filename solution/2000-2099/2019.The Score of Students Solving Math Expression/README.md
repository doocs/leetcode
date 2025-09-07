---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/README.md
rating: 2583
source: 第 260 场周赛 Q4
tags:
    - 栈
    - 记忆化搜索
    - 数组
    - 哈希表
    - 数学
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2019. 解出数学表达式的学生分数](https://leetcode.cn/problems/the-score-of-students-solving-math-expression)

[English Version](/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它 <strong>只</strong> 包含数字&nbsp;<code>0-9</code>&nbsp;，加法运算符&nbsp;<code>'+'</code>&nbsp;和乘法运算符&nbsp;<code>'*'</code>&nbsp;，这个字符串表示一个&nbsp;<strong>合法</strong>&nbsp;的只含有&nbsp;<strong>个位数</strong><strong>数字</strong>&nbsp;的数学表达式（比方说&nbsp;<code>3+5*2</code>）。有 <code>n</code>&nbsp;位小学生将计算这个数学表达式，并遵循如下 <strong>运算顺序</strong>&nbsp;：</p>

<ol>
	<li>按照 <strong>从左到右</strong>&nbsp;的顺序计算 <strong>乘法</strong>&nbsp;，然后</li>
	<li>按照 <strong>从左到右</strong>&nbsp;的顺序计算 <strong>加法</strong>&nbsp;。</li>
</ol>

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>answers</code>&nbsp;，表示每位学生提交的答案。你的任务是给 <code>answer</code>&nbsp;数组按照如下 <strong>规则</strong>&nbsp;打分：</p>

<ul>
	<li>如果一位学生的答案 <strong>等于</strong>&nbsp;表达式的正确结果，这位学生将得到 <code>5</code>&nbsp;分。</li>
	<li>否则，如果答案由&nbsp;<strong>一处或多处错误的运算顺序</strong>&nbsp;计算得到，那么这位学生能得到 <code>2</code>&nbsp;分。</li>
	<li>否则，这位学生将得到 <code>0</code>&nbsp;分。</li>
</ul>

<p>请你返回所有学生的分数和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2019.The%20Score%20of%20Students%20Solving%20Math%20Expression/images/student_solving_math.png" style="width: 678px; height: 109px;" /></p>

<pre>
<b>输入：</b>s = "7+3*1*2", answers = [20,13,42]
<b>输出：</b>7
<b>解释：</b>如上图所示，正确答案为 13 ，因此有一位学生得分为 5 分：[20,<em><strong>13</strong></em>,42] 。
一位学生可能通过错误的运算顺序得到结果 20 ：7+3=10，10*1=10，10*2=20 。所以这位学生得分为 2 分：[<em><strong>20</strong></em>,13,42] 。
所有学生得分分别为：[2,5,0] 。所有得分之和为 2+5+0=7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "3+5*2", answers = [13,0,10,13,13,16,16]
<b>输出：</b>19
<b>解释：</b>表达式的正确结果为 13 ，所以有 3 位学生得到 5 分：[<em><strong>13</strong></em>,0,10,<em><strong>13</strong></em>,<em><strong>13</strong></em>,16,16] 。
学生可能通过错误的运算顺序得到结果 16 ：3+5=8，8*2=16 。所以两位学生得到 2 分：[13,0,10,13,13,<em><strong>16</strong></em>,<em><strong>16</strong></em>] 。
所有学生得分分别为：[5,0,0,5,5,2,2] 。所有得分之和为 5+0+0+5+5+2+2=19 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "6+0*1", answers = [12,9,6,4,8,6]
<b>输出：</b>10
<b>解释：</b>表达式的正确结果为 6 。
如果一位学生通过错误的运算顺序计算该表达式，结果仍为 6 。
根据打分规则，运算顺序错误的学生也将得到 5 分（因为他们仍然得到了正确的结果），而不是 2 分。
所有学生得分分别为：[0,0,5,0,0,5] 。所有得分之和为 10 分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 31</code></li>
	<li><code>s</code>&nbsp;表示一个只包含&nbsp;<code>0-9</code>&nbsp;，<code>'+'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的合法表达式。</li>
	<li>表达式中所有整数运算数字都在闭区间&nbsp;<code>[0, 9]</code>&nbsp;以内。</li>
	<li><code>1 &lt;=</code>&nbsp;数学表达式中所有运算符数目（<code>'+'</code> 和&nbsp;<code>'*'</code>）&nbsp;<code>&lt;= 15</code></li>
	<li>测试数据保证正确表达式结果在范围&nbsp;<code>[0, 1000]</code>&nbsp;以内。</li>
	<li>测试用例保证乘法中间步骤中的值永远不会超过 <code>10<sup>9</sup></code>。</li>
	<li><code>n == answers.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= answers[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划（区间 DP）

我们先设计一个函数 $cal(s)$，用于计算一个合法的只含有个位数数字的数学表达式的结果。那么正确答案就是 $x = cal(s)$。

我们记字符串 $s$ 的长度为 $n$，那么 $s$ 中的数字个数为 $m = \frac{n+1}{2}$。

我们定义 $f[i][j]$ 表示选择 $s$ 中的第 $i$ 个数字到第 $j$ 个数字（下标从 $0$ 开始）这一段数字，计算出的结果可能的取值。初始时 $f[i][i]$ 表示选择第 $i$ 个数字，结果只能是这个数字本身，即 $f[i][i] = \{s[i \times 2]\}$（即第 $i$ 个数字映射到字符串 $s$ 中的下标为 $i \times 2$ 的字符）。

接下来，我们从大到小枚举 $i$，然后从小到大枚举 $j$，我们需要求出区间 $[i, j]$ 所有数字运算的结果可能的取值。我们在区间 $[i, j]$ 中枚举分界点 $k$，那么 $f[i][j]$ 可以由 $f[i][k]$ 和 $f[k+1][j]$ 通过运算符 $s[k \times 2 + 1]$ 得到。因此我们可以得到如下状态转移方程：

$$
f[i][j] = \begin{cases}
\{s[i \times 2]\}, & i = j \\
\bigcup\limits_{k=i}^{j-1} \{f[i][k] \otimes f[k+1][j]\}, & i < j
\end{cases}
$$

其中 $\otimes$ 表示运算符，即 $s[k \times 2 + 1]$。

那么字符串 $s$ 所有数字运算的结果可能的取值就是 $f[0][m-1]$。

最后，我们统计答案。我们用一个数组 $cnt$ 统计答案数组 $answers$ 中每个答案出现的次数。如果答案等于 $x$，那么这个学生得到 $5$ 分，否则如果答案在 $f[0][m-1]$ 中，那么这个学生得到 $2$ 分。遍历 $cnt$，统计答案即可。

时间复杂度 $O(n^3 \times M^2)$，空间复杂度 $O(n^2 \times M^2)$。其中 $M$ 是答案可能的最大值，而 $n$ 是字符串 $s$ 的长度中数字的个数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
