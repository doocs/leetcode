# [500. 键盘行](https://leetcode.cn/problems/keyboard-row)

[English Version](/solution/0500-0599/0500.Keyboard%20Row/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，只返回可以使用在 <strong>美式键盘</strong> 同一行的字母打印出来的单词。键盘如下图所示。</p>

<p><strong>美式键盘</strong> 中：</p>

<ul>
	<li>第一行由字符 <code>"qwertyuiop"</code> 组成。</li>
	<li>第二行由字符 <code>"asdfghjkl"</code> 组成。</li>
	<li>第三行由字符 <code>"zxcvbnm"</code> 组成。</li>
</ul>

<p><img alt="American keyboard" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0500.Keyboard%20Row/images/keyboard.png" style="width: 100%; max-width: 600px" /></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["Hello","Alaska","Dad","Peace"]
<strong>输出：</strong>["Alaska","Dad"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["omk"]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["adsdf","sfd"]
<strong>输出：</strong>["adsdf","sfd"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 20</code></li>
	<li><code>1 <= words[i].length <= 100</code></li>
	<li><code>words[i]</code> 由英文字母（小写和大写字母）组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

用三个集合存储键盘每一行字母。

遍历单词列表 `words`，判断每个单词 `word` 转 set 后是否被以上三个集合其中之一包含，若是，将单词添加到结果数组。

```python
class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        s1 = set('qwertyuiop')
        s2 = set('asdfghjkl')
        s3 = set('zxcvbnm')
        res = []
        for word in words:
            t = set(word.lower())
            if t <= s1 or t <= s2 or t <= s3:
                # 利用 python set 比较
                res.append(word)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

用三个字符串存储键盘每一行字母。

遍历单词列表 `words`，对于每个单词 `word`：

1. 分别设置三个计数器，存储当前单词在对应键盘字符串的字母个数；
2. 遍历 `word` 中的每个字母，如果在对应的键盘字符串中，则对应的计数器加 1；
3. 单词遍历结束后，判断是否存在一个计数器值与 `word` 长度相同。如果有，说明该单词所有字母都在同一个键盘字符串中，将单词添加到结果数组。

```java
class Solution {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n1 = 0, n2 = 0, n3 = 0;
            int n = word.length();
            for (int i = 0; i < n; ++i) {
                if (s1.contains(String.valueOf(word.charAt(i)))) {
                    ++n1;
                } else if (s2.contains(String.valueOf(word.charAt(i)))) {
                    ++n2;
                } else {
                    ++n3;
                }
            }
            if (n1 == n || n2 == n || n3 == n) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

```java
class Solution {
    public String[] findWords(String[] words) {
        String s = "12210111011122000010020202";
        List<String> res = new ArrayList<>();
        for (String word : words) {
            Set<Character> t = new HashSet<>();
            for (char c : word.toLowerCase().toCharArray()) {
                t.add(s.charAt(c - 'a'));
            }
            if (t.size() == 1) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        string s = "12210111011122000010020202";
        vector<string> ans;
        for (auto& word : words) {
            unordered_set<char> t;
            for (char c : word) t.insert(s[tolower(c) - 'a']);
            if (t.size() == 1) ans.push_back(word);
        }
        return ans;
    }
};
```

### **Go**

```go
func findWords(words []string) []string {
	s := "12210111011122000010020202"
	var ans []string
	for _, word := range words {
		t := make(map[byte]bool)
		for _, c := range word {
			t[s[unicode.ToLower(c)-'a']] = true
		}
		if len(t) == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
