---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1181.Before%20and%20After%20Puzzle/README.md
rating: 1558
source: 第 8 场双周赛 Q2
tags:
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

# [1181. 前后拼接 🔒](https://leetcode.cn/problems/before-and-after-puzzle)

[English Version](/solution/1100-1199/1181.Before%20and%20After%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个「短语」列表&nbsp;<code>phrases</code>，请你帮忙按规则生成拼接后的「新短语」列表。</p>

<p>「短语」（phrase）是仅由小写英文字母和空格组成的字符串。「短语」的开头和结尾都不会出现空格，「短语」中的空格不会连续出现。</p>

<p>「前后拼接」（Before and After&nbsp;puzzles）是合并两个「短语」形成「新短语」的方法。我们规定拼接时，<strong>第一个短语的最后一个单词</strong> 和 <strong>第二个短语的第一个单词</strong> 必须相同。</p>

<p>返回每两个「短语」&nbsp;<code>phrases[i]</code>&nbsp;和&nbsp;<code>phrases[j]</code>（<code>i != j</code>）进行「前后拼接」得到的「新短语」。</p>

<p>注意，两个「短语」拼接时的顺序也很重要，我们需要同时考虑这两个「短语」。另外，同一个「短语」可以多次参与拼接，但「新短语」不能再参与拼接。</p>

<p>请你按字典序排列并返回「新短语」列表，列表中的字符串应该是 <strong>不重复的</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;writing code&quot;,&quot;code rocks&quot;]
<strong>输出：</strong>[&quot;writing code rocks&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;mission statement&quot;,
                &quot;a quick bite to eat&quot;,
&nbsp;               &quot;a chip off the old block&quot;,
&nbsp;               &quot;chocolate bar&quot;,
&nbsp;               &quot;mission impossible&quot;,
&nbsp;               &quot;a man on a mission&quot;,
&nbsp;               &quot;block party&quot;,
&nbsp;               &quot;eat my words&quot;,
&nbsp;               &quot;bar of soap&quot;]
<strong>输出：</strong>[&quot;a chip off the old block party&quot;,
&nbsp;     &quot;a man on a mission impossible&quot;,
&nbsp;     &quot;a man on a mission statement&quot;,
&nbsp;     &quot;a quick bite to eat my words&quot;,
      &quot;chocolate bar of soap&quot;]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>phrases = [&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]
<strong>输出：</strong>[&quot;a&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= phrases.length &lt;= 100</code></li>
	<li><code>1 &lt;= phrases[i].length &lt;= 100</code></li>
</ul>

## 解法

### 方法一：哈希表 + 排序

我们先遍历列表 `phrases`，将每个短语的首尾单词存储数组 $ps$ 中，其中 $ps[i][0]$ 和 $ps[i][1]$ 分别表示第 $i$ 个短语的首尾单词。

接下来，我们枚举所有 $(i, j)$，其中 $i, j \in [0, n)$ 且 $i \neq j$。如果 $ps[i][1] = ps[j][0]$，那么我们就可以将第 $i$ 个短语和第 $j$ 个短语进行拼接，得到的新短语为 $phrases[i] + phrases[j][len(ps[j][0]):]$，将新短语加入哈希表 $s$ 中。

最后，我们将哈希表 $s$ 转化为数组并排序，即可得到答案。

时间复杂度 $O(n^2 \times m \times (\log n + \log m))$，空间复杂度 $O(n^2 \times m)$。其中 $n$ 和 $m$ 分别表示数组 $phrases$ 的长度和每个短语的平均长度。

<!-- tabs:start -->

```python
class Solution:
    def beforeAndAfterPuzzles(self, phrases: List[str]) -> List[str]:
        ps = []
        for p in phrases:
            ws = p.split()
            ps.append((ws[0], ws[-1]))
        n = len(ps)
        ans = []
        for i in range(n):
            for j in range(n):
                if i != j and ps[i][1] == ps[j][0]:
                    ans.append(phrases[i] + phrases[j][len(ps[j][0]) :])
        return sorted(set(ans))
```

```java
class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        int n = phrases.length;
        var ps = new String[n][];
        for (int i = 0; i < n; ++i) {
            var ws = phrases[i].split(" ");
            ps[i] = new String[] {ws[0], ws[ws.length - 1]};
        }
        Set<String> s = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && ps[i][1].equals(ps[j][0])) {
                    s.add(phrases[i] + phrases[j].substring(ps[j][0].length()));
                }
            }
        }
        var ans = new ArrayList<>(s);
        Collections.sort(ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> beforeAndAfterPuzzles(vector<string>& phrases) {
        int n = phrases.size();
        pair<string, string> ps[n];
        for (int i = 0; i < n; ++i) {
            int j = phrases[i].find(' ');
            if (j == string::npos) {
                ps[i] = {phrases[i], phrases[i]};
            } else {
                int k = phrases[i].rfind(' ');
                ps[i] = {phrases[i].substr(0, j), phrases[i].substr(k + 1)};
            }
        }
        unordered_set<string> s;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && ps[i].second == ps[j].first) {
                    s.insert(phrases[i] + phrases[j].substr(ps[i].second.size()));
                }
            }
        }
        vector<string> ans(s.begin(), s.end());
        sort(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func beforeAndAfterPuzzles(phrases []string) []string {
	n := len(phrases)
	ps := make([][2]string, n)
	for i, p := range phrases {
		ws := strings.Split(p, " ")
		ps[i] = [2]string{ws[0], ws[len(ws)-1]}
	}
	s := map[string]bool{}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if i != j && ps[i][1] == ps[j][0] {
				s[phrases[i]+phrases[j][len(ps[j][0]):]] = true
			}
		}
	}
	ans := make([]string, 0, len(s))
	for k := range s {
		ans = append(ans, k)
	}
	sort.Strings(ans)
	return ans
}
```

```ts
function beforeAndAfterPuzzles(phrases: string[]): string[] {
    const ps: string[][] = [];
    for (const p of phrases) {
        const ws = p.split(' ');
        ps.push([ws[0], ws[ws.length - 1]]);
    }
    const n = ps.length;
    const s: Set<string> = new Set();
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i !== j && ps[i][1] === ps[j][0]) {
                s.add(`${phrases[i]}${phrases[j].substring(ps[j][0].length)}`);
            }
        }
    }
    return [...s].sort();
}
```

<!-- tabs:end -->

<!-- end -->
