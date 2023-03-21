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

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们先将字符串 $s$ 按空格分割成单词数组 $words$，然后遍历单词数组，找出最长的单词长度 $n$。

接下来我们从第 $1$ 到第 $n$ 个字符，分别从单词数组中取出对应的字符，如果当前单词长度不足，则用空格补齐，放到一个字符串 $t$ 中。最后将 $t$ 去掉末尾的空格，加入答案数组中即可。

时间复杂度 $O(m)$，空间复杂度 $O(m)$。其中 $m$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def printVertically(self, s: str) -> List[str]:
        words = s.split()
        n = max(len(w) for w in words)
        ans = []
        for j in range(n):
            t = [w[j] if j < len(w) else ' ' for w in words]
            while t[-1] == ' ':
                t.pop()
            ans.append(''.join(t))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int n = 0;
        for (var w : words) {
            n = Math.max(n, w.length());
        }
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (var w : words) {
                t.append(j < w.length() ? w.charAt(j) : ' ');
            }
            while (t.length() > 0 && t.charAt(t.length() - 1) == ' ') {
                t.deleteCharAt(t.length() - 1);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> printVertically(string s) {
        stringstream ss(s);
        vector<string> words;
        string word;
        int n = 0;
        while (ss >> word) {
            words.emplace_back(word);
            n = max(n, (int) word.size());
        }
        vector<string> ans;
        for (int j = 0; j < n; ++j) {
            string t;
            for (auto& w : words) {
                t += j < w.size() ? w[j] : ' ';
            }
            while (t.size() && t.back() == ' ') {
                t.pop_back();
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func printVertically(s string) (ans []string) {
	words := strings.Split(s, " ")
	n := 0
	for _, w := range words {
		n = max(n, len(w))
	}
	for j := 0; j < n; j++ {
		t := []byte{}
		for _, w := range words {
			if j < len(w) {
				t = append(t, w[j])
			} else {
				t = append(t, ' ')
			}
		}
		for len(t) > 0 && t[len(t)-1] == ' ' {
			t = t[:len(t)-1]
		}
		ans = append(ans, string(t))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
