---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README_EN.md
rating: 1626
source: Weekly Contest 178 Q2
tags:
    - Array
    - Hash Table
    - String
    - Counting
    - Sorting
---

<!-- problem:start -->

# [1366. Rank Teams by Votes](https://leetcode.com/problems/rank-teams-by-votes)

[中文文档](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Custom Sorting

For each candidate, we can count the number of votes they receive at each rank, then compare the vote counts for different ranks in order. If the vote counts are the same, we compare the letters.

The time complexity is $O(n \times m + m^2 \times \log m)$, and the space complexity is $O(m^2)$. Here, $n$ is the length of $\textit{votes}$, and $m$ is the number of candidates, i.e., the length of $\textit{votes}[0]$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rankTeams(self, votes: List[str]) -> str:
        m = len(votes[0])
        cnt = defaultdict(lambda: [0] * m)
        for vote in votes:
            for i, c in enumerate(vote):
                cnt[c][i] += 1
        return "".join(sorted(cnt, key=lambda c: (cnt[c], -ord(c)), reverse=True))
```

#### Java

```java
class Solution {
    public String rankTeams(String[] votes) {
        int m = votes[0].length();
        int[][] cnt = new int[26][m + 1];
        for (var vote : votes) {
            for (int i = 0; i < m; ++i) {
                ++cnt[vote.charAt(i) - 'A'][i];
            }
        }
        Character[] s = new Character[m];
        for (int i = 0; i < m; ++i) {
            s[i] = votes[0].charAt(i);
        }
        Arrays.sort(s, (a, b) -> {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < m; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[j][k] - cnt[i][k];
                }
            }
            return a - b;
        });
        StringBuilder ans = new StringBuilder();
        for (var c : s) {
            ans.append(c);
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string rankTeams(vector<string>& votes) {
        int m = votes[0].size();
        array<array<int, 27>, 26> cnt{};

        for (const auto& vote : votes) {
            for (int i = 0; i < m; ++i) {
                ++cnt[vote[i] - 'A'][i];
            }
        }
        string s = votes[0];
        ranges::sort(s, [&](char a, char b) {
            int i = a - 'A', j = b - 'A';
            for (int k = 0; k < m; ++k) {
                if (cnt[i][k] != cnt[j][k]) {
                    return cnt[i][k] > cnt[j][k];
                }
            }
            return a < b;
        });
        return string(s.begin(), s.end());
    }
};
```

#### Go

```go
func rankTeams(votes []string) string {
	m := len(votes[0])
	cnt := [26][27]int{}
	for _, vote := range votes {
		for i, ch := range vote {
			cnt[ch-'A'][i]++
		}
	}
	s := []rune(votes[0])
	sort.Slice(s, func(i, j int) bool {
		a, b := s[i]-'A', s[j]-'A'
		for k := 0; k < m; k++ {
			if cnt[a][k] != cnt[b][k] {
				return cnt[a][k] > cnt[b][k]
			}
		}
		return s[i] < s[j]
	})
	return string(s)
}
```

#### TypeScript

```ts
function rankTeams(votes: string[]): string {
    const m = votes[0].length;
    const cnt: number[][] = Array.from({ length: 26 }, () => Array(m + 1).fill(0));
    for (const vote of votes) {
        for (let i = 0; i < m; i++) {
            cnt[vote.charCodeAt(i) - 65][i]++;
        }
    }
    const s: string[] = votes[0].split('');
    s.sort((a, b) => {
        const i = a.charCodeAt(0) - 65;
        const j = b.charCodeAt(0) - 65;
        for (let k = 0; k < m; k++) {
            if (cnt[i][k] !== cnt[j][k]) {
                return cnt[j][k] - cnt[i][k];
            }
        }
        return a.localeCompare(b);
    });
    return s.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn rank_teams(votes: Vec<String>) -> String {
        let m = votes[0].len();
        let mut cnt = vec![vec![0; m + 1]; 26];

        for vote in &votes {
            for (i, ch) in vote.chars().enumerate() {
                cnt[(ch as u8 - b'A') as usize][i] += 1;
            }
        }

        let mut s: Vec<char> = votes[0].chars().collect();

        s.sort_by(|&a, &b| {
            let i = (a as u8 - b'A') as usize;
            let j = (b as u8 - b'A') as usize;

            for k in 0..m {
                if cnt[i][k] != cnt[j][k] {
                    return cnt[j][k].cmp(&cnt[i][k]);
                }
            }
            a.cmp(&b)
        });

        s.into_iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
