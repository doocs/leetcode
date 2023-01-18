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

**方法一：哈希表 + 枚举**

我们先用哈希表 $d$ 记录每个字符可以替换成的字符集合。

然后我们枚举 $s$ 中所有长度为 $sub$ 长度的子串，判断字符串 $sub$ 是否可以通过替换得到该子串，如果可以则返回 `true`，否则枚举下一个子串。

枚举结束，说明 $sub$ 无法通过替换得到 $s$ 中的任何子串，返回 `false`。

时间复杂度 $O(m \times n)$，空间复杂度 $O(C^2)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $sub$ 的长度，而 $C$ 是字符集的大小。

**方法二：数组 + 枚举**

由于字符集只包含大写和小写英文字母和数字，因此我们可以直接用一个 $128 \times 128$ 的数组 $d$ 记录每个字符可以替换成的字符集合。

时间复杂度 $O(m \times n)$，空间复杂度 $O(C^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = defaultdict(set)
        for a, b in mappings:
            d[a].add(b)
        for i in range(len(s) - len(sub) + 1):
            if all(a == b or a in d[b] for a, b in zip(s[i: i + len(sub)], sub)):
                return True
        return False
```

```python
class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = [[False] * 128 for _ in range(128)]
        for a, b in mappings:
            d[ord(a)][ord(b)] = True
        for i in range(len(s) - len(sub) + 1):
            if all(a == b or d[ord(b)][ord(a)] for a, b in zip(s[i: i + len(sub)], sub)):
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        Map<Character, Set<Character>> d = new HashMap<>();
        for (var e : mappings) {
            d.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
        }
        int m = s.length(), n = sub.length();
        for (int i = 0; i < m - n + 1; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a != b && !d.getOrDefault(b, Collections.emptySet()).contains(a)) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] d = new boolean[128][128];
        for (var e : mappings) {
            d[e[0]][e[1]] = true;
        }
        int m = s.length(), n = sub.length();
        for (int i = 0; i < m - n + 1; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a != b && !d[b][a]) {
                    ok = false;
                }
            }
            if (ok) {
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
        for (auto& e : mappings) {
            d[e[0]].insert(e[1]);
        }
        int m = s.size(), n = sub.size();
        for (int i = 0; i < m - n + 1; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s[i + j], b = sub[j];
                if (a != b && !d[b].count(a)) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
};
```

```cpp
class Solution {
public:
    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {
        bool d[128][128]{};
        for (auto& e : mappings) {
            d[e[0]][e[1]] = true;
        }
        int m = s.size(), n = sub.size();
        for (int i = 0; i < m - n + 1; ++i) {
            bool ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s[i + j], b = sub[j];
                if (a != b && !d[b][a]) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
};
```

### **Go**

```go
func matchReplacement(s string, sub string, mappings [][]byte) bool {
	d := map[byte]map[byte]bool{}
	for _, e := range mappings {
		if d[e[0]] == nil {
			d[e[0]] = map[byte]bool{}
		}
		d[e[0]][e[1]] = true
	}
	for i := 0; i < len(s)-len(sub)+1; i++ {
		ok := true
		for j := 0; j < len(sub) && ok; j++ {
			a, b := s[i+j], sub[j]
			if a != b && !d[b][a] {
				ok = false
			}
		}
		if ok {
			return true
		}
	}
	return false
}
```

```go
func matchReplacement(s string, sub string, mappings [][]byte) bool {
	d := [128][128]bool{}
	for _, e := range mappings {
		d[e[0]][e[1]] = true
	}
	for i := 0; i < len(s)-len(sub)+1; i++ {
		ok := true
		for j := 0; j < len(sub) && ok; j++ {
			a, b := s[i+j], sub[j]
			if a != b && !d[b][a] {
				ok = false
			}
		}
		if ok {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
