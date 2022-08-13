# [2301. 替换字符后匹配](https://leetcode.cn/problems/match-substring-after-replacement)

[English Version](/solution/2300-2399/2301.Match%20Substring%20After%20Replacement/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s</code> 和&nbsp;<code>sub</code>&nbsp;。同时给你一个二维字符数组&nbsp;<code>mappings</code> ，其中&nbsp;<code>mappings[i] = [old<sub>i</sub>, new<sub>i</sub>]</code>&nbsp;表示你可以将&nbsp;<code>sub</code>&nbsp;中任意数目的&nbsp;<code>old<sub>i</sub></code>&nbsp;字符替换为&nbsp;<code>new<sub>i</sub></code>&nbsp;。<code>sub</code>&nbsp;中每个字符 <b>不能</b>&nbsp;被替换超过一次。</p>

<p>如果使用 <code>mappings</code>&nbsp;替换 0 个或者若干个字符，可以将 <code>sub</code>&nbsp;变成 <code>s</code>&nbsp;的一个子字符串，请你返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>一个 <strong>子字符串</strong>&nbsp;是字符串中连续非空的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "fool3e7bar", sub = "leet", mappings = [["e","3"],["t","7"],["t","8"]]
<b>输出：</b>true
<b>解释：</b>将 sub 中第一个 'e' 用 '3' 替换，将 't' 用 '7' 替换。
现在 sub = "l3e7" ，它是 s 的子字符串，所以我们返回 true 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "fooleetbar", sub = "f00l", mappings = [["o","0"]]
<b>输出：</b>false
<b>解释：</b>字符串 "f00l" 不是 s 的子串且没有可以进行的修改。
注意我们不能用 'o' 替换 '0' 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "Fool33tbaR", sub = "leetd", mappings = [["e","3"],["t","7"],["t","8"],["d","b"],["p","b"]]
<b>输出：</b>true
<b>解释：</b>将 sub 里第一个和第二个 'e' 用 '3' 替换，用 'b' 替换 sub 里的 'd' 。
得到 sub = "l33tb" ，它是 s 的子字符串，所以我们返回 true 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sub.length &lt;= s.length &lt;= 5000</code></li>
	<li><code>0 &lt;= mappings.length &lt;= 1000</code></li>
	<li><code>mappings[i].length == 2</code></li>
	<li><code>old<sub>i</sub> != new<sub>i</sub></code></li>
	<li><code>s</code> 和&nbsp;<code>sub</code>&nbsp;只包含大写和小写英文字母和数字。</li>
	<li><code>old<sub>i</sub></code> 和&nbsp;<code>new<sub>i</sub></code>&nbsp;是大写、小写字母或者是个数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = defaultdict(set)
        for a, b in mappings:
            d[a].add(b)
        n, k = len(s), len(sub)
        for i in range(n - k + 1):
            flag = True
            for j in range(k):
                a, b = s[i + j], sub[j]
                if a == b or a in d[b]:
                    continue
                else:
                    flag = False
                    break
            if flag:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        Map<Character, Set<Character>> d = new HashMap<>();
        for (char[] m : mappings) {
            d.computeIfAbsent(m[0], k -> new HashSet<>()).add(m[1]);
        }
        int n = s.length(), k = sub.length();
        for (int i = 0; i <= n - k; ++i) {
            boolean flag = true;
            for (int j = 0; j < k; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a == b || d.getOrDefault(b, Collections.emptySet()).contains(a)) {
                    continue;
                }
                flag = false;
                break;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {
        unordered_map<char, unordered_set<char>> d;
        for (auto& m : mappings) d[m[0]].insert(m[1]);
        int n = s.size(), k = sub.size();
        for (int i = 0; i <= n - k; ++i) {
            bool flag = true;
            for (int j = 0; j < k; ++j) {
                char a = s[i + j], b = sub[j];
                if (a == b || d[b].count(a)) continue;
                flag = false;
                break;
            }
            if (flag) return true;
        }
        return false;
    }
};
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
