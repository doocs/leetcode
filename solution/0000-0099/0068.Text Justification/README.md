# [68. 文本左右对齐](https://leetcode.cn/problems/text-justification)

[English Version](/solution/0000-0099/0068.Text%20Justification/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单词数组&nbsp;<code>words</code> 和一个长度&nbsp;<code>maxWidth</code>&nbsp;，重新排版单词，使其成为每行恰好有&nbsp;<code>maxWidth</code>&nbsp;个字符，且左右两端对齐的文本。</p>

<p>你应该使用 “<strong>贪心算法</strong>” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格&nbsp;<code>' '</code>&nbsp;填充，使得每行恰好有 <em>maxWidth</em>&nbsp;个字符。</p>

<p>要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。</p>

<p>文本的最后一行应为左对齐，且单词之间不插入<strong>额外的</strong>空格。</p>

<p><strong>注意:</strong></p>

<ul>
	<li>单词是指由非空格字符组成的字符序列。</li>
	<li>每个单词的长度大于 0，小于等于&nbsp;<em>maxWidth</em>。</li>
	<li>输入单词数组 <code>words</code>&nbsp;至少包含一个单词。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
<strong>输出:</strong>
[
&nbsp; &nbsp;"This &nbsp; &nbsp;is &nbsp; &nbsp;an",
&nbsp; &nbsp;"example &nbsp;of text",
&nbsp; &nbsp;"justification. &nbsp;"
]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong>words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
<strong>输出:</strong>
[
&nbsp; "What &nbsp; must &nbsp; be",
&nbsp; "acknowledgment &nbsp;",
&nbsp; "shall be &nbsp; &nbsp; &nbsp; &nbsp;"
]
<strong>解释: </strong>注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
&nbsp;    因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong>words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
<strong>输出:</strong>
[
&nbsp; "Science &nbsp;is &nbsp;what we",
  "understand &nbsp; &nbsp; &nbsp;well",
&nbsp; "enough to explain to",
&nbsp; "a &nbsp;computer. &nbsp;Art is",
&nbsp; "everything &nbsp;else &nbsp;we",
&nbsp; "do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 300</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code>&nbsp;由小写英文字母和符号组成</li>
	<li><code>1 &lt;= maxWidth &lt;= 100</code></li>
	<li><code>words[i].length &lt;= maxWidth</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        def partition(n, cnt):
            res = []
            base, mod = divmod(n, cnt)
            i = j = 0
            while i < cnt:
                t = [' ' * base]
                if j < mod:
                    t.append(' ')
                res.append(''.join(t))
                i, j = i + 1, j + 1
            return res

        ans = []
        i, n = 0, len(words)
        while i < n:
            t = []
            cnt = len(words[i])
            t.append(words[i])
            i += 1
            while i < n and cnt + 1 + len(words[i]) <= maxWidth:
                cnt += 1 + len(words[i])
                t.append(words[i])
                i += 1
            if i == n or len(t) == 1:
                # this is the last line or only one word in a line
                left = ' '.join(t)
                right = ' ' * (maxWidth - len(left))
                ans.append(left + right)
                if i == n:
                    break
                continue
            words_width = cnt - len(t) + 1
            space_width = maxWidth - words_width
            spaces = partition(space_width, len(t) - 1)
            sb = [t[0]]
            for j in range(len(t) - 1):
                sb.append(spaces[j])
                sb.append(t[j + 1])
            ans.append(''.join(sb))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) {
            List<String> t = new ArrayList<>();
            int cnt = words[i].length();
            t.add(words[i++]);
            while (i < n && cnt + 1 + words[i].length() <= maxWidth) {
                cnt += 1 + words[i].length();
                t.add(words[i++]);
            }
            if (i == n || t.size() == 1) {
                // this is the last line or only one word in a line
                String left = String.join(" ", t);
                String right = " ".repeat(maxWidth - left.length());
                ans.add(left + right);
                if (i == n) {
                    break;
                }
                continue;
            }

            int wordsWidth = cnt - t.size() + 1;
            int spaceWidth = maxWidth - wordsWidth;
            List<String> spaces = partition(spaceWidth, t.size() - 1);
            StringBuilder sb = new StringBuilder(t.get(0));
            for (int j = 0; j < t.size() - 1; ++j) {
                sb.append(spaces.get(j));
                sb.append(t.get(j + 1));
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    private List<String> partition(int n, int cnt) {
        List<String> ans = new ArrayList<>();
        int base = n / cnt;
        int mod = n % cnt;
        for (int i = 0, j = 0; i < cnt; ++i, ++j) {
            StringBuilder sb = new StringBuilder(" ".repeat(base));
            if (j < mod) {
                sb.append(' ');
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        int n = words.size();
        vector<string> result;
        for (int i = 0; i < n; i++) {
            int begin = i;
            int wordLen = words[i].size();
            while (i + 1 < n && words[i + 1].size() + wordLen + 1 <= maxWidth) {
                wordLen += words[++i].size() + 1;
            }
            int numberofWords = i - begin + 1;
            int space = 1;
            int extraSpace = 0;
            if (numberofWords > 1 && i < n - 1) {
                int remaining = maxWidth - wordLen;
                space = remaining / (numberofWords - 1) + 1;
                extraSpace = remaining % (numberofWords - 1);
            }
            string line = words[begin];
            for (int j = 1; j < numberofWords; j++) {
                line.append(space, ' ');
                if (j <= extraSpace) {
                    line.push_back(' ');
                }
                line += words[begin + j];
            }
            if (line.size() < maxWidth) {
                line.append(maxWidth - line.size(), ' ');
            }
            result.emplace_back(line);
        }
        return result;
    }
};
```

### **Go**

```go
func fullJustify(words []string, maxWidth int) []string {
	partition := func(n, cnt int) []string {
		var res []string
		base, mod := n/cnt, n%cnt
		for i, j := 0, 0; i < cnt; i, j = i+1, j+1 {
			t := strings.Repeat(" ", base)
			if j < mod {
				t += " "
			}
			res = append(res, t)
		}
		return res
	}

	var ans []string
	for i, n := 0, len(words); i < n; {
		t := []string{words[i]}
		cnt := len(words[i])
		i++
		for i < n && cnt+1+len(words[i]) <= maxWidth {
			cnt += 1 + len(words[i])
			t = append(t, words[i])
			i++
		}
		if i == n || len(t) == 1 {
			left := strings.Join(t, " ")
			right := strings.Repeat(" ", maxWidth-len(left))
			ans = append(ans, left+right)
			if i == n {
				break
			}
			continue
		}
		wordsWidth := cnt - len(t) + 1
		spaceWidth := maxWidth - wordsWidth
		spaces := partition(spaceWidth, len(t)-1)
		sb := t[0]
		for j := 0; j < len(t)-1; j++ {
			sb += spaces[j] + t[j+1]
		}
		ans = append(ans, sb)
	}
	return ans
}
```

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Solution {
    public IList<string> FullJustify(string[] words, int maxWidth) {
        var result = new List<string>();
        var buffer = new List<string>();
        var sb = new StringBuilder();
        var len = 0;

        for (var i = 0; i < words.Length; ++i)
        {
            var newLen = words[i].Length + (len == 0 ? 0 : len + 1);
            if (newLen <= maxWidth)
            {
                buffer.Add(words[i]);
                len = newLen;
            }
            else
            {
                if (buffer.Count == 1)
                {
                    sb.Append(buffer[0]);
                    sb.Append(' ', maxWidth - buffer[0].Length);
                }
                else
                {
                    var spaceCount = maxWidth - len + buffer.Count - 1;
                    for (var j = 0; j < buffer.Count - 1; ++j)
                    {
                        sb.Append(buffer[j]);
                        var spaceToAdd = (spaceCount - 1) / (buffer.Count - j - 1) + 1;
                        sb.Append(' ', spaceToAdd);
                        spaceCount -= spaceToAdd;
                    }
                    sb.Append(buffer.Last());
                }
                result.Add(sb.ToString());
                buffer.Clear();
                buffer.Add(words[i]);
                sb.Clear();
                len = words[i].Length;
            }
        }

        if (buffer.Count > 0)
        {
            for (var j = 0; j < buffer.Count; ++j)
            {
                if (sb.Length > 0)
                {
                    sb.Append(' ');
                }
                sb.Append(buffer[j]);
            }
            if (sb.Length < maxWidth)
            {
                sb.Append(' ', maxWidth - sb.Length);
            }
            result.Add(sb.ToString());
        }

        return result;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
