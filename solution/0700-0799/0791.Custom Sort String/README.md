# [791. 自定义字符串排序](https://leetcode.cn/problems/custom-sort-string)

[English Version](/solution/0700-0799/0791.Custom%20Sort%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <code>order</code> 和 <code>s</code> 。<code>order</code> 的所有单词都是 <strong>唯一</strong> 的，并且以前按照一些自定义的顺序排序。</p>

<p>对 <code>s</code> 的字符进行置换，使其与排序的&nbsp;<code>order</code>&nbsp;相匹配。更具体地说，如果在&nbsp;<code>order</code>&nbsp;中的字符 <code>x</code> 出现字符 <code>y</code> 之前，那么在排列后的字符串中， <code>x</code>&nbsp;也应该出现在 <code>y</code> 之前。</p>

<p>返回 <em>满足这个性质的 <code>s</code> 的任意排列&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> order = "cba", s = "abcd"
<strong>输出:</strong> "cbad"
<strong>解释:</strong> 
“a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> order = "cbafg", s = "abcd"
<strong>输出:</strong> "cbad"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= order.length &lt;= 26</code></li>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>order</code>&nbsp;和&nbsp;<code>s</code>&nbsp;由小写英文字母组成</li>
	<li><code>order</code>&nbsp;中的所有字符都 <strong>不同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 自定义排序**

将字符串中的字符按照 $order$ 中出现的位置（下标）排序，未在 $order$ 中出现的，下标默认视为 $-1$。

时间复杂度 $O(nlogn+m)$，空间复杂度 $O(m)$，其中 $m$ 和 $n$ 分别为 $order$ 和 $s$ 的长度。

**方法二：字符计数**

统计 $s$ 中每个字符的出现次数，存储在 $cnt$ 数组中。

然后把在 $order$ 中出现的字符按照 $order$ 中的顺序排序，剩余字符添加到当前字符串的后面。

时间复杂度 $O(m+n)$，其中 $m$ 和 $n$ 分别为 $order$ 和 $s$ 的长度。空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cs = list(s)
        m = {v: i for i, v in enumerate(order)}
        cs.sort(key=lambda x: m.get(x, -1))
        return ''.join(cs)
```

```python
class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cnt = Counter(s)
        ans = []
        for c in order:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        for c in s:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String customSortString(String order, String s) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < order.length(); ++i) {
            m.put(order.charAt(i), i);
        }
        List<Character> cs = new ArrayList<>();
        for (char c : s.toCharArray()) {
            cs.add(c);
        }
        cs.sort((a, b) -> m.getOrDefault(a, -1) - m.getOrDefault(b, -1));
        StringBuilder ans = new StringBuilder();
        for (char c : cs) {
            ans.append(c);
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (char c : s.toCharArray()) {
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string customSortString(string order, string s) {
        vector<int> cnt(26);
        for (char c : s) ++cnt[c - 'a'];
        string ans = "";
        for (char c : order) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        for (char c : s) {
            while (cnt[c - 'a']-- > 0) {
                ans += c;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func customSortString(order string, s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range order {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	for _, c := range s {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
