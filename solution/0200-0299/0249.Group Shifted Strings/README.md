# [249. 移位字符串分组 🔒](https://leetcode.cn/problems/group-shifted-strings)

[English Version](/solution/0200-0299/0249.Group%20Shifted%20Strings/README_EN.md)

<!-- tags:数组,哈希表,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，对该字符串可以进行 &ldquo;移位&rdquo; 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如：<code>&quot;abc&quot; -&gt; &quot;bcd&quot;</code>。这样，我们可以持续进行 &ldquo;移位&rdquo; 操作，从而生成如下移位序列：</p>

<pre>&quot;abc&quot; -&gt; &quot;bcd&quot; -&gt; ... -&gt; &quot;xyz&quot;</pre>

<p>给定一个包含仅小写字母字符串的列表，将该列表中所有满足&nbsp;&ldquo;移位&rdquo; 操作规律的组合进行分组并返回。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong><code>[&quot;abc&quot;, &quot;bcd&quot;, &quot;acef&quot;, &quot;xyz&quot;, &quot;az&quot;, &quot;ba&quot;, &quot;a&quot;, &quot;z&quot;]</code>
<strong>输出：</strong>
[
  [&quot;abc&quot;,&quot;bcd&quot;,&quot;xyz&quot;],
  [&quot;az&quot;,&quot;ba&quot;],
  [&quot;acef&quot;],
  [&quot;a&quot;,&quot;z&quot;]
]
<strong>解释：</strong>可以认为字母表首尾相接，所以 &#39;z&#39; 的后续为 &#39;a&#39;，所以 [&quot;az&quot;,&quot;ba&quot;] 也满足 &ldquo;移位&rdquo; 操作规律。</pre>

## 解法

### 方法一：哈希表

我们用一个哈希表 $g$ 来存储每个字符串移位后且首位为 '`a`' 的字符串。即 $g[t]$ 表示所有字符串移位后字符串为 $t$ 的字符串集合。

我们遍历每个字符串，对于每个字符串，我们计算其移位后的字符串 $t$，然后将其加入到 $g[t]$ 中。

最后，我们将 $g$ 中的所有值取出来，即为答案。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为所有字符串的长度之和。

<!-- tabs:start -->

```python
class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        g = defaultdict(list)
        for s in strings:
            diff = ord(s[0]) - ord("a")
            t = []
            for c in s:
                c = ord(c) - diff
                if c < ord("a"):
                    c += 26
                t.append(chr(c))
            g["".join(t)].append(s)
        return list(g.values())
```

```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> g = new HashMap<>();
        for (var s : strings) {
            char[] t = s.toCharArray();
            int diff = t[0] - 'a';
            for (int i = 0; i < t.length; ++i) {
                t[i] = (char) (t[i] - diff);
                if (t[i] < 'a') {
                    t[i] += 26;
                }
            }
            g.computeIfAbsent(new String(t), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(g.values());
    }
}
```

```cpp
class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        unordered_map<string, vector<string>> g;
        for (auto& s : strings) {
            string t;
            int diff = s[0] - 'a';
            for (int i = 0; i < s.size(); ++i) {
                char c = s[i] - diff;
                if (c < 'a') {
                    c += 26;
                }
                t.push_back(c);
            }
            g[t].emplace_back(s);
        }
        vector<vector<string>> ans;
        for (auto& p : g) {
            ans.emplace_back(move(p.second));
        }
        return ans;
    }
};
```

```go
func groupStrings(strings []string) [][]string {
	g := make(map[string][]string)
	for _, s := range strings {
		t := []byte(s)
		diff := t[0] - 'a'
		for i := range t {
			t[i] -= diff
			if t[i] < 'a' {
				t[i] += 26
			}
		}
		g[string(t)] = append(g[string(t)], s)
	}
	ans := make([][]string, 0, len(g))
	for _, v := range g {
		ans = append(ans, v)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
