# [1366. Rank Teams by Votes](https://leetcode.com/problems/rank-teams-by-votes)

[中文文档](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README.md)

## Description

<p>In a special ranking system, each voter gives a rank from highest to lowest to all teams participating in the competition.</p>

<p>The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.</p>

<p>You are given an array of strings <code>votes</code> which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.</p>

<p>Return <em>a string of all teams <strong>sorted</strong> by the ranking system</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ABC&quot;,&quot;ACB&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;ACB&quot;]
<strong>Output:</strong> &quot;ACB&quot;
<strong>Explanation:</strong> 
Team A was ranked first place by 5 voters. No other team was voted as first place, so team A is the first team.
Team B was ranked second by 2 voters and ranked third by 3 voters.
Team C was ranked second by 3 voters and ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team, and team B is the third.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;WXYZ&quot;,&quot;XYZW&quot;]
<strong>Output:</strong> &quot;XWYZ&quot;
<strong>Explanation:</strong>
X is the winner due to the tie-breaking rule. X has the same votes as W for the first position, but X has one vote in the second position, while W does not have any votes in the second position. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;]
<strong>Output:</strong> &quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;
<strong>Explanation:</strong> Only one voter, so their votes are used for the ranking.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= votes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= votes[i].length &lt;= 26</code></li>
	<li><code>votes[i].length == votes[j].length</code> for <code>0 &lt;= i, j &lt; votes.length</code>.</li>
	<li><code>votes[i][j]</code> is an English <strong>uppercase</strong> letter.</li>
	<li>All characters of <code>votes[i]</code> are unique.</li>
	<li>All the characters that occur in <code>votes[0]</code> <strong>also occur</strong> in <code>votes[j]</code> where <code>1 &lt;= j &lt; votes.length</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rankTeams(self, votes: List[str]) -> str:
        n = len(votes[0])
        cnt = defaultdict(lambda: [0] * n)
        for vote in votes:
            for i, c in enumerate(vote):
                cnt[c][i] += 1
        return "".join(sorted(votes[0], key=lambda x: (cnt[x], -ord(x)), reverse=True))
```

### **Java**

```java
class Solution {
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        int[][] cnt = new int[26][n];
        for (var vote : votes) {
            for (int i = 0; i < n; ++i) {
                cnt[vote.charAt(i) - 'A'][i]++;
            }
        }
        Character[] cs = new Character[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = votes[0].charAt(i);
        }
        Arrays.sort(cs, (a, b) -> {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < n; ++k) {
                int d = cnt[i][k] - cnt[j][k];
                if (d != 0) {
                    return d > 0 ? -1 : 1;
                }
            }
            return a - b;
        });
        StringBuilder ans = new StringBuilder();
        for (char c : cs) {
            ans.append(c);
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string rankTeams(vector<string>& votes) {
        int n = votes[0].size();
        int cnt[26][n];
        memset(cnt, 0, sizeof cnt);
        for (auto& vote : votes) {
            for (int i = 0; i < n; ++i) {
                cnt[vote[i] - 'A'][i]++;
            }
        }
        string ans = votes[0];
        sort(ans.begin(), ans.end(), [&](auto& a, auto& b) {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < n; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[i][k] > cnt[j][k];
                }
            }
            return a < b;
        });
        return ans;
    }
};
```

### **Go**

```go
func rankTeams(votes []string) string {
	cnt := [26][26]int{}
	for _, vote := range votes {
		for i, c := range vote {
			cnt[c-'A'][i]++
		}
	}
	ans := []byte(votes[0])
	sort.Slice(ans, func(i, j int) bool {
		cnt1, cnt2 := cnt[ans[i]-'A'], cnt[ans[j]-'A']
		for k, a := range cnt1 {
			b := cnt2[k]
			if a != b {
				return a > b
			}
		}
		return ans[i] < ans[j]
	})
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
