# [1451. 重新排列句子中的单词](https://leetcode.cn/problems/rearrange-words-in-a-sentence)

[English Version](/solution/1400-1499/1451.Rearrange%20Words%20in%20a%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 <code>text</code> :</p>

<ul>
	<li>句子的首字母大写</li>
	<li><code>text</code> 中的每个单词都用单个空格分隔。</li>
</ul>

<p>请你重新排列 <code>text</code> 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。</p>

<p>请同样按上述格式返回新的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;Leetcode is cool&quot;
<strong>输出：</strong>&quot;Is cool leetcode&quot;
<strong>解释：</strong>句子中共有 3 个单词，长度为 8 的 &quot;Leetcode&quot; ，长度为 2 的 &quot;is&quot; 以及长度为 4 的 &quot;cool&quot; 。
输出需要按单词的长度升序排列，新句子中的第一个单词首字母需要大写。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot;Keep calm and code on&quot;
<strong>输出：</strong>&quot;On and keep calm code&quot;
<strong>解释：</strong>输出的排序情况如下：
&quot;On&quot; 2 个字母。
&quot;and&quot; 3 个字母。
&quot;keep&quot; 4 个字母，因为存在长度相同的其他单词，所以它们之间需要保留在原句子中的相对顺序。
&quot;calm&quot; 4 个字母。
&quot;code&quot; 4 个字母。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;To be or not to be&quot;
<strong>输出：</strong>&quot;To be or to be not&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>text</code> 以大写字母开头，然后包含若干小写字母以及单词间的单个空格。</li>
	<li><code>1 &lt;= text.length &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

将 `text` 按空格切分为字符串数组 `words`，并将 `words[0]` 转为小写。然后对 `words` 进行排序（这里需要确保长度相同的情况下，相对顺序保持不变，因此是一种稳定排序）。

排完序后，对 `words[0]` 首字母转为大写。最后将 `words` 所有字符串用空格拼接成一个字符串。

时间复杂度 $O(n\log n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrangeWords(self, text: str) -> str:
        words = text.split()
        words[0] = words[0].lower()
        words.sort(key=len)
        words[0] = words[0].title()
        return " ".join(words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String arrangeWords(String text) {
        String[] words = text.split(" ");
        words[0] = words[0].toLowerCase();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);
        return String.join(" ", words);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string arrangeWords(string text) {
        vector<string> words;
        stringstream ss(text);
        string t;
        while (ss >> t) {
            words.push_back(t);
        }
        words[0][0] = tolower(words[0][0]);
        stable_sort(words.begin(), words.end(), [](const string& a, const string& b) {
            return a.size() < b.size();
        });
        string ans = "";
        for (auto& s : words) {
            ans += s + " ";
        }
        ans.pop_back();
        ans[0] = toupper(ans[0]);
        return ans;
    }
};
```

### **Go**

```go
func arrangeWords(text string) string {
	words := strings.Split(text, " ")
	words[0] = strings.ToLower(words[0])
	sort.SliceStable(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	words[0] = strings.Title(words[0])
	return strings.Join(words, " ")
}
```

### **...**

```

```

<!-- tabs:end -->
