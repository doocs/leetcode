---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README.md
rating: 1626
source: 第 178 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

<!-- problem:start -->

# [1366. 通过投票对团队排名](https://leetcode.cn/problems/rank-teams-by-votes)

[English Version](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。</p>

<p>排名规则如下：</p>

<ul>
	<li>参赛团队的排名次序依照其所获「排位第一」的票的多少决定。如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。</li>
	<li>如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。</li>
</ul>

<p>给你一个字符串数组&nbsp;<code>votes</code> 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。</p>

<p>请你返回能表示按排名系统 <strong>排序后</strong> 的所有团队排名的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>votes = ["ABC","ACB","ABC","ACB","ACB"]
<strong>输出：</strong>"ACB"
<strong>解释：</strong>
A 队获得五票「排位第一」，没有其他队获得「排位第一」，所以 A 队排名第一。
B 队获得两票「排位第二」，三票「排位第三」。
C 队获得三票「排位第二」，两票「排位第三」。
由于 C 队「排位第二」的票数较多，所以 C 队排第二，B 队排第三。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>votes = ["WXYZ","XYZW"]
<strong>输出：</strong>"XWYZ"
<strong>解释：</strong>
X 队在并列僵局打破后成为排名第一的团队。X 队和 W 队的「排位第一」票数一样，但是 X 队有一票「排位第二」，而 W 没有获得「排位第二」。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
<strong>输出：</strong>"ZMNAGUEDSJYLBOPHRQICWFXTVK"
<strong>解释：</strong>只有一个投票者，所以排名完全按照他的意愿。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= votes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= votes[i].length &lt;= 26</code></li>
	<li><code>votes[i].length ==&nbsp;votes[j].length</code> for&nbsp;<code>0 &lt;= i, j &lt; votes.length</code></li>
	<li><code>votes[i][j]</code>&nbsp;是英文 <strong>大写</strong> 字母</li>
	<li><code>votes[i]</code>&nbsp;中的所有字母都是唯一的</li>
	<li><code>votes[0]</code>&nbsp;中出现的所有字母 <strong>同样也</strong> 出现在&nbsp;<code>votes[j]</code>&nbsp;中，其中&nbsp;<code>1 &lt;= j &lt; votes.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 自定义排序

对于每个候选人，我们可以统计他在每个排位上的票数，然后根据不同的排位依次比较票数，票数相同则比较字母。

时间复杂度 $O(n \times m + m^2 \times \log m)$，空间复杂度 $O(m^2)$。其中 $n$ 是 $\textit{votes}$ 的长度，而 $m$ 是候选人的数量，即 $\textit{votes}[0]$ 的长度。

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
