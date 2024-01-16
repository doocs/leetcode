# [1554. 只有一个不同字符的字符串](https://leetcode.cn/problems/strings-differ-by-one-character)

[English Version](/solution/1500-1599/1554.Strings%20Differ%20by%20One%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串列表&nbsp;<code>dict</code> ，其中所有字符串的长度都相同。</p>

<p>当存在两个字符串在相同索引处只有一个字符不同时，返回 <code>True</code> ，否则返回 <code>False</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dict = ["abcd","acbd", "aacd"]
<strong>输出：</strong>true
<strong>解释：</strong>字符串 "a<strong>b</strong>cd" 和 "a<strong>a</strong>cd" 只在索引 1 处有一个不同的字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dict = ["ab","cd","yz"]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dict = ["abcd","cccc","abyd","abab"]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>dict</code>&nbsp;中的字符数小于或等于&nbsp;<code>10^5</code>&nbsp;。</li>
	<li><code>dict[i].length == dict[j].length</code></li>
	<li><code>dict[i]</code>&nbsp;是互不相同的。</li>
	<li><code>dict[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以以 <code>O(n*m)</code> 的复杂度解决问题吗？其中 n 是列表 <code>dict</code> 的长度，<code>m</code> 是字符串的长度。</p>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def differByOne(self, dict: List[str]) -> bool:
        s = set()
        for word in dict:
            for i in range(len(word)):
                t = word[:i] + "*" + word[i + 1 :]
                if t in s:
                    return True
                s.add(t)
        return False
```

```java
class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> s = new HashSet<>();
        for (String word : dict) {
            for (int i = 0; i < word.length(); ++i) {
                String t = word.substring(0, i) + "*" + word.substring(i + 1);
                if (s.contains(t)) {
                    return true;
                }
                s.add(t);
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool differByOne(vector<string>& dict) {
        unordered_set<string> s;
        for (auto word : dict) {
            for (int i = 0; i < word.size(); ++i) {
                auto t = word;
                t[i] = '*';
                if (s.count(t)) return true;
                s.insert(t);
            }
        }
        return false;
    }
};
```

```go
func differByOne(dict []string) bool {
	s := make(map[string]bool)
	for _, word := range dict {
		for i := range word {
			t := word[:i] + "*" + word[i+1:]
			if s[t] {
				return true
			}
			s[t] = true
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- end -->
