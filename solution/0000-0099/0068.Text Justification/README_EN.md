# [68. Text Justification](https://leetcode.com/problems/text-justification)

[中文文档](/solution/0000-0099/0068.Text%20Justification/README.md)

## Description

<p>Given an array of strings <code>words</code> and a width <code>maxWidth</code>, format the text such that each line has exactly <code>maxWidth</code> characters and is fully (left and right) justified.</p>

<p>You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces <code>&#39; &#39;</code> when necessary so that each line has exactly <code>maxWidth</code> characters.</p>

<p>Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.</p>

<p>For the last line of text, it should be left-justified, and no extra space is inserted between words.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>A word is defined as a character sequence consisting of non-space characters only.</li>
	<li>Each word&#39;s length is guaranteed to be greater than <code>0</code> and not exceed <code>maxWidth</code>.</li>
	<li>The input array <code>words</code> contains at least one word.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;This&quot;, &quot;is&quot;, &quot;an&quot;, &quot;example&quot;, &quot;of&quot;, &quot;text&quot;, &quot;justification.&quot;], maxWidth = 16
<strong>Output:</strong>
[
&nbsp; &nbsp;&quot;This &nbsp; &nbsp;is &nbsp; &nbsp;an&quot;,
&nbsp; &nbsp;&quot;example &nbsp;of text&quot;,
&nbsp; &nbsp;&quot;justification. &nbsp;&quot;
]</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;What&quot;,&quot;must&quot;,&quot;be&quot;,&quot;acknowledgment&quot;,&quot;shall&quot;,&quot;be&quot;], maxWidth = 16
<strong>Output:</strong>
[
&nbsp; &quot;What &nbsp; must &nbsp; be&quot;,
&nbsp; &quot;acknowledgment &nbsp;&quot;,
&nbsp; &quot;shall be &nbsp; &nbsp; &nbsp; &nbsp;&quot;
]
<strong>Explanation:</strong> Note that the last line is &quot;shall be    &quot; instead of &quot;shall     be&quot;, because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;Science&quot;,&quot;is&quot;,&quot;what&quot;,&quot;we&quot;,&quot;understand&quot;,&quot;well&quot;,&quot;enough&quot;,&quot;to&quot;,&quot;explain&quot;,&quot;to&quot;,&quot;a&quot;,&quot;computer.&quot;,&quot;Art&quot;,&quot;is&quot;,&quot;everything&quot;,&quot;else&quot;,&quot;we&quot;,&quot;do&quot;], maxWidth = 20
<strong>Output:</strong>
[
&nbsp; &quot;Science &nbsp;is &nbsp;what we&quot;,
  &quot;understand &nbsp; &nbsp; &nbsp;well&quot;,
&nbsp; &quot;enough to explain to&quot;,
&nbsp; &quot;a &nbsp;computer. &nbsp;Art is&quot;,
&nbsp; &quot;everything &nbsp;else &nbsp;we&quot;,
&nbsp; &quot;do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&quot;
]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 300</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> consists of only English letters and symbols.</li>
	<li><code>1 &lt;= maxWidth &lt;= 100</code></li>
	<li><code>words[i].length &lt;= maxWidth</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

```java
class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n;) {
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
