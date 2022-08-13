# [1366. Rank Teams by Votes](https://leetcode.com/problems/rank-teams-by-votes)

[中文文档](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README.md)

## Description

<p>In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.</p>

<p>The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.</p>

<p>Given an array of strings <code>votes</code> which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.</p>

<p>Return <em>a string of all teams</em> <strong>sorted</strong> by the ranking system.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ABC&quot;,&quot;ACB&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;ACB&quot;]
<strong>Output:</strong> &quot;ACB&quot;
<strong>Explanation:</strong> Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
Team B was ranked second by 2 voters and was ranked third by 3 voters.
Team C was ranked second by 3 voters and was ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team and team B is the third.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;WXYZ&quot;,&quot;XYZW&quot;]
<strong>Output:</strong> &quot;XWYZ&quot;
<strong>Explanation:</strong> X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position while W doesn&#39;t have any votes as second position. 
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> votes = [&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;]
<strong>Output:</strong> &quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;
<strong>Explanation:</strong> Only one voter so his votes are used for the ranking.
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
        d = defaultdict(lambda: [0] * len(votes[0]))
        for vote in votes:
            for i, v in enumerate(vote):
                d[v][i] -= 1
        ans = sorted(votes[0], key=lambda x: (d[x], x))
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> counter = new HashMap<>();
        int n = votes[0].length();
        for (String vote : votes) {
            for (int i = 0; i < n; ++i) {
                char v = vote.charAt(i);
                counter.computeIfAbsent(v, k -> new int[26])[i]++;
            }
        }
        List<Map.Entry<Character, int[]>> t = new ArrayList<>(counter.entrySet());
        Collections.sort(t, (a, b) -> {
            int[] v1 = a.getValue();
            int[] v2 = b.getValue();
            for (int i = 0; i < 26; ++i) {
                if (v1[i] != v2[i]) {
                    return v2[i] - v1[i];
                }
            }
            return a.getKey() - b.getKey();
        });
        StringBuilder ans = new StringBuilder();
        t.forEach(e -> ans.append(e.getKey()));
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string rankTeams(vector<string>& votes) {
        unordered_map<char, vector<int>> counter;
        int n = votes[0].size();
        for (auto& vote : votes) {
            for (int i = 0; i < n; ++i) {
                char v = vote[i];
                counter[v].resize(n);
                ++counter[v][i];
            }
        }
        string ans = votes[0];
        sort(ans.begin(), ans.end(), [&](char a, char b) {
            return counter[a] > counter[b] || (counter[a] == counter[b] && a < b);
        });
        return ans;
    }
};
```

### **Go**

```go
func rankTeams(votes []string) string {
	n := len(votes[0])
	counter := make(map[byte][]int)
	for _, v := range votes[0] {
		counter[byte(v)] = make([]int, n)
	}
	for _, vote := range votes {
		for i, v := range vote {
			counter[byte(v)][i]++
		}
	}
	ans := []byte(votes[0])
	sort.Slice(ans, func(i, j int) bool {
		v1, v2 := counter[ans[i]], counter[ans[j]]
		for i := range v1 {
			if v1[i] != v2[i] {
				return v1[i] > v2[i]
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
