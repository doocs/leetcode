# [544. Output Contest Matches](https://leetcode.com/problems/output-contest-matches)

[中文文档](/solution/0500-0599/0544.Output%20Contest%20Matches/README.md)

## Description

<p>During the NBA playoffs, we always set the rather strong team to play with the rather weak team, like make the rank <code>1</code> team play with the rank <code>n<sup>th</sup></code> team, which is a good strategy to make the contest more interesting.</p>

<p>Given <code>n</code> teams, return <em>their final contest matches in the form of a string</em>.</p>

<p>The <code>n</code> teams are labeled from <code>1</code> to <code>n</code>, which represents their initial rank (i.e., Rank <code>1</code> is the strongest team and Rank <code>n</code> is the weakest team).</p>

<p>We will use parentheses <code>&#39;(&#39;</code>, and <code>&#39;)&#39;</code> and commas <code>&#39;,&#39;</code> to represent the contest team pairing. We use the parentheses for pairing and the commas for partition. During the pairing process in each round, you always need to follow the strategy of making the rather strong one pair with the rather weak one.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> &quot;((1,4),(2,3))&quot;
<strong>Explanation:</strong>
In the first round, we pair the team 1 and 4, the teams 2 and 3 together, as we need to make the strong team and weak team together.
And we got (1, 4),(2, 3).
In the second round, the winners of (1, 4) and (2, 3) need to play again to generate the final winner, so you need to add the paratheses outside them.
And we got the final answer ((1,4),(2,3)).
</pre>

<p><strong>Example 2:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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
