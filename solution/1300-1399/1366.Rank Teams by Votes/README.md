# [1366. 通过投票对团队排名](https://leetcode.cn/problems/rank-teams-by-votes)

[English Version](/solution/1300-1399/1366.Rank%20Teams%20by%20Votes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。</p>

<p>排名规则如下：</p>

<ul>
	<li>参赛团队的排名次序依照其所获「排位第一」的票的多少决定。如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。</li>
	<li>如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。</li>
</ul>

<p>给你一个字符串数组&nbsp;<code>votes</code> 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。</p>

<p>请你返回能表示按排名系统 <strong>排序后</strong> 的所有团队排名的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>votes = [&quot;ABC&quot;,&quot;ACB&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;ACB&quot;]
<strong>输出：</strong>&quot;ACB&quot;
<strong>解释：</strong>A 队获得五票「排位第一」，没有其他队获得「排位第一」，所以 A 队排名第一。
B 队获得两票「排位第二」，三票「排位第三」。
C 队获得三票「排位第二」，两票「排位第三」。
由于 C 队「排位第二」的票数较多，所以 C 队排第二，B 队排第三。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>votes = [&quot;WXYZ&quot;,&quot;XYZW&quot;]
<strong>输出：</strong>&quot;XWYZ&quot;
<strong>解释：</strong>X 队在并列僵局打破后成为排名第一的团队。X 队和 W 队的「排位第一」票数一样，但是 X 队有一票「排位第二」，而 W 没有获得「排位第二」。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>votes = [&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;]
<strong>输出：</strong>&quot;ZMNAGUEDSJYLBOPHRQICWFXTVK&quot;
<strong>解释：</strong>只有一个投票者，所以排名完全按照他的意愿。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>votes = [&quot;BCA&quot;,&quot;CAB&quot;,&quot;CBA&quot;,&quot;ABC&quot;,&quot;ACB&quot;,&quot;BAC&quot;]
<strong>输出：</strong>&quot;ABC&quot;
<strong>解释：</strong> 
A 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
B 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
C 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
完全并列，所以我们需要按照字母升序排名。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>votes = [&quot;M&quot;,&quot;M&quot;,&quot;M&quot;,&quot;M&quot;]
<strong>输出：</strong>&quot;M&quot;
<strong>解释：</strong>只有 M 队参赛，所以它排名第一。
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 自定义排序**

对于每个候选人，我们可以统计他在每个排位上的票数，然后根据不同的排位依次比较票数，票数相同则比较字母。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n^2)$。其中 $n$ 为候选人的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
