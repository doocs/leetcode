# [1324. 竖直打印单词](https://leetcode.cn/problems/print-words-vertically)

[English Version](/solution/1300-1399/1324.Print%20Words%20Vertically/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>。请你按照单词在 <code>s</code> 中的出现顺序将它们全部竖直返回。<br>
单词应该以字符串列表的形式返回，必要时用空格补位，但输出尾部的空格需要删除（不允许尾随空格）。<br>
每个单词只能放在一列上，每一列中也只能有一个单词。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;HOW ARE YOU&quot;
<strong>输出：</strong>[&quot;HAY&quot;,&quot;ORO&quot;,&quot;WEU&quot;]
<strong>解释：</strong>每个单词都应该竖直打印。 
 &quot;HAY&quot;
&nbsp;&quot;ORO&quot;
&nbsp;&quot;WEU&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;TO BE OR NOT TO BE&quot;
<strong>输出：</strong>[&quot;TBONTB&quot;,&quot;OEROOE&quot;,&quot;   T&quot;]
<strong>解释：</strong>题目允许使用空格补位，但不允许输出末尾出现空格。
&quot;TBONTB&quot;
&quot;OEROOE&quot;
&quot;   T&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;CONTEST IS COMING&quot;
<strong>输出：</strong>[&quot;CIC&quot;,&quot;OSO&quot;,&quot;N M&quot;,&quot;T I&quot;,&quot;E N&quot;,&quot;S G&quot;,&quot;T&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>s</code>&nbsp;仅含大写英文字母。</li>
	<li>题目数据保证两个单词之间只有一个空格。</li>
</ul>

## 解法

先将字符串 s 按空格切分，然后直接模拟即可。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def printVertically(self, s: str) -> List[str]:
        words = s.split()
        m, n = len(words), max(len(word) for word in words)
        ans = []
        for j in range(n):
            t = []
            for i in range(m):
                word = words[i]
                t.append(word[j] if j < len(word) else ' ')
            ans.append(''.join(t).rstrip())
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int m = words.length, n = maxLen(words);
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < m; ++i) {
                String word = words[i];
                t.append(j < word.length() ? word.charAt(j) : ' ');
            }
            ans.add(rstrip(t));
        }
        return ans;
    }

    private int maxLen(String[] words) {
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, word.length());
        }
        return ans;
    }

    private String rstrip(StringBuilder s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != ' ') {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream in(s);
        vector<string> words;
        string word;
        int n = 0;
        while (in >> word) {
            words.push_back(word);
            n = max(n, (int)word.size());
        }
        int m = words.size();
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t = "";
            for (int i = 0; i < m; ++i) {
                word = words[i];
                t += j < word.size() ? word[j] : ' ';
            }
            while (t.back() == ' ') {
                t.pop_back();
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func printVertically(s string) []string {
	words := strings.Split(s, " ")
	m := len(words)
	var n int
	for _, word := range words {
		if n < len(word) {
			n = len(word)
		}
	}
	var ans []string
	for j := 0; j < n; j++ {
		var t []byte
		for i := 0; i < m; i++ {
			word := words[i]
			if j < len(word) {
				t = append(t, word[j])
			} else {
				t = append(t, ' ')
			}
		}
		s = string(t)
		ans = append(ans, rstrip(s))
	}
	return ans
}

func rstrip(s string) string {
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != ' ' {
			return s[:i+1]
		}
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
