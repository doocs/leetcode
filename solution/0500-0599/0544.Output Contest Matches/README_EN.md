---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0544.Output%20Contest%20Matches/README_EN.md
tags:
    - Recursion
    - String
    - Simulation
---

<!-- problem:start -->

# [544. Output Contest Matches 🔒](https://leetcode.com/problems/output-contest-matches)

[中文文档](/solution/0500-0599/0544.Output%20Contest%20Matches/README.md)

## Description

<!-- description:start -->

<p>During the NBA playoffs, we always set the rather strong team to play with the rather weak team, like making&nbsp;the rank <code>1</code> team play with the rank <code>n<sup>th</sup></code> team, which is a good strategy to make the contest more interesting.</p>

<p>Given <code>n</code> teams, return <em>their final contest matches in the form of a string</em>.</p>

<p>The <code>n</code> teams are labeled from <code>1</code> to <code>n</code>, which represents their initial rank (i.e., Rank <code>1</code> is the strongest team and Rank <code>n</code> is the weakest team).</p>

<p>We will use parentheses <code>&#39;(&#39;</code>, and <code>&#39;)&#39;</code> and commas <code>&#39;,&#39;</code> to represent the contest team pairing. We use the parentheses for pairing and the commas for partition. During the pairing process in each round, you always need to follow the strategy of making the rather strong one pair with the rather weak one.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> &quot;((1,4),(2,3))&quot;
<strong>Explanation:</strong>
In the first round, we pair the team 1 and 4, the teams 2 and 3 together, as we need to make the strong team and weak team together.
And we got (1, 4),(2, 3).
In the second round, the winners of (1, 4) and (2, 3) need to play again to generate the final winner, so you need to add the paratheses outside them.
And we got the final answer ((1,4),(2,3)).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> &quot;(((1,8),(4,5)),((2,7),(3,6)))&quot;
<strong>Explanation:</strong>
First round: (1, 8),(2, 7),(3, 6),(4, 5)
Second round: ((1, 8),(4, 5)),((2, 7),(3, 6))
Third round: (((1, 8),(4, 5)),((2, 7),(3, 6)))
Since the third round will generate the final winner, you need to output the answer (((1,8),(4,5)),((2,7),(3,6))).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == 2<sup>x</sup></code> where <code>x</code> in in the range <code>[1, 12]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can use an array $s$ of length $n$ to store the ID of each team, and then simulate the process of the matches.

In each round of matches, we pair up the first $n$ elements in array $s$ two by two, and then store the ID of the winners in the first $n/2$ positions of array $s$. After that, we halve $n$ and continue to the next round of matches, until $n$ is reduced to $1$. At this point, the first element in array $s$ is the final match-up scheme.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of teams.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findContestMatch(self, n: int) -> str:
        s = [str(i + 1) for i in range(n)]
        while n > 1:
            for i in range(n >> 1):
                s[i] = f"({s[i]},{s[n - i - 1]})"
            n >>= 1
        return s[0]
```

#### Java

```java
class Solution {
    public String findContestMatch(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; ++i) {
            s[i] = String.valueOf(i + 1);
        }
        for (; n > 1; n >>= 1) {
            for (int i = 0; i < n >> 1; ++i) {
                s[i] = String.format("(%s,%s)", s[i], s[n - i - 1]);
            }
        }
        return s[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findContestMatch(int n) {
        vector<string> s(n);
        for (int i = 0; i < n; ++i) {
            s[i] = to_string(i + 1);
        }
        for (; n > 1; n >>= 1) {
            for (int i = 0; i < n >> 1; ++i) {
                s[i] = "(" + s[i] + "," + s[n - i - 1] + ")";
            }
        }
        return s[0];
    }
};
```

#### Go

```go
func findContestMatch(n int) string {
	s := make([]string, n)
	for i := 0; i < n; i++ {
		s[i] = strconv.Itoa(i + 1)
	}
	for ; n > 1; n >>= 1 {
		for i := 0; i < n>>1; i++ {
			s[i] = fmt.Sprintf("(%s,%s)", s[i], s[n-i-1])
		}
	}
	return s[0]
}
```

#### TypeScript

```ts
function findContestMatch(n: number): string {
    const s: string[] = Array.from({ length: n }, (_, i) => (i + 1).toString());
    for (; n > 1; n >>= 1) {
        for (let i = 0; i < n >> 1; ++i) {
            s[i] = `(${s[i]},${s[n - i - 1]})`;
        }
    }
    return s[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
