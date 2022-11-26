# [828. 统计子串中的唯一字符](https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string)

[English Version](/solution/0800-0899/0828.Count%20Unique%20Characters%20of%20All%20Substrings%20of%20a%20Given%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们定义了一个函数 <code>countUniqueChars(s)</code> 来统计字符串 <code>s</code> 中的唯一字符，并返回唯一字符的个数。</p>

<p>例如：<code>s = "LEETCODE"</code> ，则其中 <code>"L"</code>, <code>"T"</code>,<code>"C"</code>,<code>"O"</code>,<code>"D"</code> 都是唯一字符，因为它们只出现一次，所以 <code>countUniqueChars(s) = 5</code> 。</p>

<p>本题将会给你一个字符串 <code>s</code> ，我们需要返回 <code>countUniqueChars(t)</code> 的总和，其中 <code>t</code> 是 <code>s</code> 的子字符串。输入用例保证返回值为&nbsp;32 位整数。</p>

<p>注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 <code>s</code> 的所有子字符串中的唯一字符）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>s = "ABC"
<strong>输出: </strong>10
<strong>解释:</strong> 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
     其中，每一个子串都由独特字符构成。
     所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>s = "ABA"
<strong>输出: </strong>8
<strong>解释: </strong>除<code>了 countUniqueChars</code>("ABA") = 1 之外，其余与示例 1 相同。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "LEETCODE"
<strong>输出：</strong>92
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> 只包含大写英文字符</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计算每个字符的贡献**

对于字符串 `s` 的每个字符 $c_i$，当它在某个子字符串中仅出现一次时，它会对这个子字符串统计唯一字符时有贡献。只需对每个字符 $c_i$，计算有多少子字符串仅包含该字符一次即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def uniqueLetterString(self, s: str) -> int:
        d = defaultdict(list)
        for i, c in enumerate(s):
            d[c].append(i)
        ans = 0
        for v in d.values():
            v = [-1] + v + [len(s)]
            for i in range(1, len(v) - 1):
                ans += (v[i] - v[i - 1]) * (v[i + 1] - v[i])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int uniqueLetterString(String s) {
        List<Integer>[] d = new List[26];
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 0; i < 26; ++i) {
            d[i].add(-1);
        }
        for (int i = 0; i < s.length(); ++i) {
            d[s.charAt(i) - 'A'].add(i);
        }
        int ans = 0;
        for (var v : d) {
            v.add(s.length());
            for (int i = 1; i < v.size() - 1; ++i) {
                ans += (v.get(i) - v.get(i - 1)) * (v.get(i + 1) - v.get(i));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int uniqueLetterString(string s) {
        vector<vector<int>> d(26, {-1});
        for (int i = 0; i < s.size(); ++i) {
            d[s[i] - 'A'].push_back(i);
        }
        int ans = 0;
        for (auto& v : d) {
            v.push_back(s.size());
            for (int i = 1; i < v.size() - 1; ++i) {
                ans += (v[i] - v[i - 1]) * (v[i + 1] - v[i]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func uniqueLetterString(s string) int {
	d := make([][]int, 26)
	for i := range d {
		d[i] = []int{-1}
	}
	for i, c := range s {
		d[c-'A'] = append(d[c-'A'], i)
	}
	ans := 0
	for _, v := range d {
		v = append(v, len(s))
		for i := 1; i < len(v)-1; i++ {
			ans += (v[i] - v[i-1]) * (v[i+1] - v[i])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
