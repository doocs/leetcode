# [269. 火星词典](https://leetcode.cn/problems/alien-dictionary)

[English Version](/solution/0200-0299/0269.Alien%20Dictionary/README_EN.md)

<!-- tags:深度优先搜索,广度优先搜索,图,拓扑排序,数组,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一种使用英语字母的火星语言，这门语言的字母顺序对你来说是未知的。</p>

<p>给你一个来自这种外星语言字典的字符串列表 <code>words</code> ，<code>words</code> 中的字符串已经 <strong>按这门新语言的<span data-keyword="lexicographically-smaller-string-alien">字典序</span>进行了排序</strong> 。</p>

<p>如果这种说法是错误的，并且给出的 <code>words</code> 不能对应任何字母的顺序，则返回 <code>""</code> 。</p>

<p>否则，返回一个按新语言规则的&nbsp;<strong>字典递增顺序 </strong>排序的独特字符串。如果有多个解决方案，则返回其中 <strong>任意一个</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["wrt","wrf","er","ett","rftt"]
<strong>输出：</strong>"wertf"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["z","x"]
<strong>输出：</strong>"zx"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["z","x","z"]
<strong>输出：</strong>""
<strong>解释：</strong>不存在合法字母顺序，因此返回 <code>"" 。</code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：拓扑排序 + BFS

用数组 $g$ 记录在火星字典中的字母先后关系，$g[i][j] = true$ 表示字母 $i + 'a'$ 在字母 $j + 'a'$ 的前面；用数组 $s$ 记录当前字典出现过的字母，$cnt$ 表示出现过的字母数。

一个很简单的想法是遍历每一个单词，比较该单词和其后的所有单词，把所有的先后关系更新进数组 $g$，这样遍历时间复杂度为 $O(n^3)$；但是我们发现其实比较相邻的两个单词就可以了，比如 $a < b < c$ 则比较 $a < b$ 和 $b < c$， $a$ 和 $c$ 的关系便确定了。因此算法可以优化成比较相邻两个单词，时间复杂度为 $O(n²)$。

出现矛盾的情况：

-   $g[i][j]$ = $g[j][i]$ = $true$；
-   后一个单词是前一个单词的前缀；
-   在拓扑排序后 $ans$ 的长度小于统计到的字母个数。

拓扑排序：

-   统计所有出现的字母入度；
-   将所有入度为 $0$ 的字母加入队列；
-   当队列不空，出队并更新其他字母的入度，入度为 $0$ 则入队，同时出队时将当前字母加入 $ans$ 的结尾；
-   得到的便是字母的拓扑序，也就是火星字典的字母顺序。

<!-- tabs:start -->

```python
class Solution:
    def alienOrder(self, words: List[str]) -> str:
        g = [[False] * 26 for _ in range(26)]
        s = [False] * 26
        cnt = 0
        n = len(words)
        for i in range(n - 1):
            for c in words[i]:
                if cnt == 26:
                    break
                o = ord(c) - ord('a')
                if not s[o]:
                    cnt += 1
                    s[o] = True
            m = len(words[i])
            for j in range(m):
                if j >= len(words[i + 1]):
                    return ''
                c1, c2 = words[i][j], words[i + 1][j]
                if c1 == c2:
                    continue
                o1, o2 = ord(c1) - ord('a'), ord(c2) - ord('a')
                if g[o2][o1]:
                    return ''
                g[o1][o2] = True
                break
        for c in words[n - 1]:
            if cnt == 26:
                break
            o = ord(c) - ord('a')
            if not s[o]:
                cnt += 1
                s[o] = True

        indegree = [0] * 26
        for i in range(26):
            for j in range(26):
                if i != j and s[i] and s[j] and g[i][j]:
                    indegree[j] += 1
        q = deque()
        ans = []
        for i in range(26):
            if s[i] and indegree[i] == 0:
                q.append(i)
        while q:
            t = q.popleft()
            ans.append(chr(t + ord('a')))
            for i in range(26):
                if s[i] and i != t and g[t][i]:
                    indegree[i] -= 1
                    if indegree[i] == 0:
                        q.append(i)
        return '' if len(ans) < cnt else ''.join(ans)
```

```java
class Solution {

    public String alienOrder(String[] words) {
        boolean[][] g = new boolean[26][26];
        boolean[] s = new boolean[26];
        int cnt = 0;
        int n = words.length;
        for (int i = 0; i < n - 1; ++i) {
            for (char c : words[i].toCharArray()) {
                if (cnt == 26) {
                    break;
                }
                c -= 'a';
                if (!s[c]) {
                    ++cnt;
                    s[c] = true;
                }
            }
            int m = words[i].length();
            for (int j = 0; j < m; ++j) {
                if (j >= words[i + 1].length()) {
                    return "";
                }
                char c1 = words[i].charAt(j), c2 = words[i + 1].charAt(j);
                if (c1 == c2) {
                    continue;
                }
                if (g[c2 - 'a'][c1 - 'a']) {
                    return "";
                }
                g[c1 - 'a'][c2 - 'a'] = true;
                break;
            }
        }
        for (char c : words[n - 1].toCharArray()) {
            if (cnt == 26) {
                break;
            }
            c -= 'a';
            if (!s[c]) {
                ++cnt;
                s[c] = true;
            }
        }

        int[] indegree = new int[26];
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (i != j && s[i] && s[j] && g[i][j]) {
                    ++indegree[j];
                }
            }
        }
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            if (s[i] && indegree[i] == 0) {
                q.offerLast(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            int t = q.pollFirst();
            ans.append((char) (t + 'a'));
            for (int i = 0; i < 26; ++i) {
                if (i != t && s[i] && g[t][i]) {
                    if (--indegree[i] == 0) {
                        q.offerLast(i);
                    }
                }
            }
        }
        return ans.length() < cnt ? "" : ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string alienOrder(vector<string>& words) {
        vector<vector<bool>> g(26, vector<bool>(26));
        vector<bool> s(26);
        int cnt = 0;
        int n = words.size();
        for (int i = 0; i < n - 1; ++i) {
            for (char c : words[i]) {
                if (cnt == 26) break;
                c -= 'a';
                if (!s[c]) {
                    ++cnt;
                    s[c] = true;
                }
            }
            int m = words[i].size();
            for (int j = 0; j < m; ++j) {
                if (j >= words[i + 1].size()) return "";
                char c1 = words[i][j], c2 = words[i + 1][j];
                if (c1 == c2) continue;
                if (g[c2 - 'a'][c1 - 'a']) return "";
                g[c1 - 'a'][c2 - 'a'] = true;
                break;
            }
        }
        for (char c : words[n - 1]) {
            if (cnt == 26) break;
            c -= 'a';
            if (!s[c]) {
                ++cnt;
                s[c] = true;
            }
        }
        vector<int> indegree(26);
        for (int i = 0; i < 26; ++i)
            for (int j = 0; j < 26; ++j)
                if (i != j && s[i] && s[j] && g[i][j])
                    ++indegree[j];
        queue<int> q;
        for (int i = 0; i < 26; ++i)
            if (s[i] && indegree[i] == 0)
                q.push(i);
        string ans = "";
        while (!q.empty()) {
            int t = q.front();
            ans += (t + 'a');
            q.pop();
            for (int i = 0; i < 26; ++i)
                if (i != t && s[i] && g[t][i])
                    if (--indegree[i] == 0)
                        q.push(i);
        }
        return ans.size() < cnt ? "" : ans;
    }
};
```

<!-- tabs:end -->

<!-- end -->
