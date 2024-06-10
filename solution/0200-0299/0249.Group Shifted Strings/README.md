---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0249.Group%20Shifted%20Strings/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [249. 移位字符串分组 🔒](https://leetcode.cn/problems/group-shifted-strings)

[English Version](/solution/0200-0299/0249.Group%20Shifted%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对字符串进行 “移位” 的操作：</p>

<ul>
	<li><strong>右移</strong>：将字符串中每个字母都变为其在字母表中 <strong>后续</strong> 的字母，其中用 'a' 替换 'z'。比如，<code>"abc"</code>&nbsp;能够右移为&nbsp;<code>"bcd"</code>，<code>"xyz"</code>&nbsp;能够右移为&nbsp;<code>"yza"</code>。</li>
	<li><strong>左移</strong>：将字符串中每个字母都变为其在字母表中 <b>之前</b>&nbsp;的字母，其中用 'z' 替换 'a'。比如，<code>"bcd"</code>&nbsp;能够右移为&nbsp;<code>"abc"</code>，<code>"yza"</code>&nbsp;能够右移为&nbsp;<code>"xyz"</code>。</li>
</ul>

<p>我们可以不断地向两个方向移动字符串，形成 <strong>无限的移位序列</strong>。</p>

<ul>
	<li>例如，移动&nbsp;<code>"abc"</code>&nbsp;来形成序列：<code>... &lt;-&gt; "abc" &lt;-&gt; "bcd" &lt;-&gt; ... &lt;-&gt; "xyz" &lt;-&gt; "yza" &lt;-&gt; ... &lt;-&gt; "zab" &lt;-&gt; "abc" &lt;-&gt; ...</code></li>
</ul>

<p>给定一个字符串数组&nbsp;<code>strings</code>，将属于相同移位序列的所有&nbsp;<code>strings[i]</code>&nbsp;进行分组。你可以以 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">strings = ["abc","bcd","acef","xyz","az","ba","a","z"]</span></p>

<p><strong>输出：</strong><span class="example-io">[["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]</span></p>

<p>&nbsp;</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">strings = ["a"]</span></p>

<p><strong>输出：</strong><span class="example-io">[["a"]]</span></p>

<p>&nbsp;</p>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strings.length &lt;= 200</code></li>
	<li><code>1 &lt;= strings[i].length &lt;= 50</code></li>
	<li><code>strings[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $g$ 来存储每个字符串移位后且首位为 '`a`' 的字符串。即 $g[t]$ 表示所有字符串移位后字符串为 $t$ 的字符串集合。

我们遍历每个字符串，对于每个字符串，我们计算其移位后的字符串 $t$，然后将其加入到 $g[t]$ 中。

最后，我们将 $g$ 中的所有值取出来，即为答案。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为所有字符串的长度之和。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- solution:end -->

<!-- problem:end -->
