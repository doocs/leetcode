---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1554.Strings%20Differ%20by%20One%20Character/README.md
tags:
    - 哈希表
    - 字符串
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [1554. 只有一个不同字符的字符串 🔒](https://leetcode.cn/problems/strings-differ-by-one-character)

[English Version](/solution/1500-1599/1554.Strings%20Differ%20by%20One%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串列表&nbsp;<code>dict</code> ，其中所有字符串的长度都相同。</p>

<p>当存在两个字符串在相同索引处只有一个字符不同时，返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

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
	<li><code>dict</code>&nbsp;中的字符数小于或等于&nbsp;<code>10<sup>5</sup></code>&nbsp;。</li>
	<li><code>dict[i].length == dict[j].length</code></li>
	<li><code>dict[i]</code>&nbsp;是互不相同的。</li>
	<li><code>dict[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 判断是否存在恰好相差一位的两个词。词数与长度之积不超过 $10^5$，两两比较每位是平方级。
>
> 把每个词的每一位依次换成通配符，得到「忽略该位」的模式。若某模式已在集合中出现，则存在另一词与当前词只在该位不同。哈希集合保证均摊常数查询。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

<!-- solution:end -->

<!-- problem:end -->
