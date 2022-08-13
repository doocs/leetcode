# [792. 匹配子序列的单词数](https://leetcode.cn/problems/number-of-matching-subsequences)

[English Version](/solution/0700-0799/0792.Number%20of%20Matching%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定字符串 <code>s</code>&nbsp;和字符串数组&nbsp;<code>words</code>, 返回&nbsp;&nbsp;<em><code>words[i]</code>&nbsp;中是<code>s</code>的子序列的单词个数</em>&nbsp;。</p>

<p>字符串的 <strong>子序列</strong> 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。</p>

<ul>
	<li>例如， <code>“ace”</code> 是 <code>“abcde”</code> 的子序列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", words = ["a","bb","acd","ace"]
<strong>输出:</strong> 3
<strong>解释:</strong> 有三个是&nbsp;s 的子序列的单词: "a", "acd", "ace"。
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>输入: </strong>s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
<strong>输出:</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>words[i]</code>和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font>&nbsp;都只由小写字母组成。</li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​</span></span></span>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于字符串 S 长度较大，如果暴力求解，会报超时错误，因此要避免对 S 的多次遍历。

可以将所有单词根据首字母不同放入不同的 buckets 中，比如对于 `words = ["a", "bb", "acd", "ace"]`，可以初始化 buckets 为如下形式：

```python
buckets = {
    'a': ["a", "acd", "ace"],
    'b': ["bb"],
}
```

然后遍历 S 中每个字符 c，在 buckets 中找到对应的 bucket 并取出所有元素 old。对于每个元素 t，如果长度为 1，说明 t 对应的单词已经遍历结束，该单词属于 S 的一个子序列，累加 res；否则将 t 取子串 `t[1:]` 放入 buckets 中。继续往下遍历 S，直至结束。

最后返回 res 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numMatchingSubseq(self, s: str, words: List[str]) -> int:
        buckets = defaultdict(list)
        for word in words:
            buckets[word[0]].append(word)
        res = 0
        for c in s:
            old = buckets[c][::1]
            buckets[c].clear()
            for t in old:
                if len(t) == 1:
                    res += 1
                else:
                    buckets[t[1]].append(t[1:])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<String>[] buckets = new List[26];
        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new ArrayList<>();
        }
        for (String word : words) {
            buckets[word.charAt(0) - 'a'].add(word);
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            List<String> old = new ArrayList<>(buckets[c - 'a']);
            buckets[c - 'a'].clear();
            for (String t : old) {
                if (t.length() == 1) {
                    ++res;
                } else {
                    buckets[t.charAt(1) - 'a'].add(t.substring(1));
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<vector<string>> buckets(26);
        for (auto word : words) buckets[word[0] - 'a'].push_back(word);
        int res = 0;
        for (auto c : s) {
            auto old = buckets[c - 'a'];
            buckets[c - 'a'].clear();
            for (auto t : old) {
                if (t.size() == 1)
                    ++res;
                else
                    buckets[t[1] - 'a'].push_back(t.substr(1));
            }
        }
        return res;
    }
};
```

### **Go**

```go
func numMatchingSubseq(s string, words []string) int {
	buckets := make([][]string, 26)
	for _, word := range words {
		buckets[word[0]-'a'] = append(buckets[word[0]-'a'], word)
	}
	res := 0
	for _, c := range s {
		old := buckets[c-'a']
		buckets[c-'a'] = nil
		for _, t := range old {
			if len(t) == 1 {
				res++
			} else {
				buckets[t[1]-'a'] = append(buckets[t[1]-'a'], t[1:])
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
