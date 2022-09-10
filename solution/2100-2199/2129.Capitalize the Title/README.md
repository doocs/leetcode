# [2129. 将标题首字母大写](https://leetcode.cn/problems/capitalize-the-title)

[English Version](/solution/2100-2199/2129.Capitalize%20the%20Title/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>title</code>&nbsp;，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。请你按以下规则将每个单词的首字母 <strong>大写</strong>&nbsp;：</p>

<ul>
	<li>如果单词的长度为&nbsp;<code>1</code>&nbsp;或者&nbsp;<code>2</code>&nbsp;，所有字母变成小写。</li>
	<li>否则，将单词首字母大写，剩余字母变成小写。</li>
</ul>

<p>请你返回 <strong>大写后</strong>&nbsp;的<em>&nbsp;</em><code>title</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre><b>输入：</b>title = "capiTalIze tHe titLe"
<b>输出：</b>"Capitalize The Title"
<strong>解释：</strong>
由于所有单词的长度都至少为 3 ，将每个单词首字母大写，剩余字母变为小写。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>title = "First leTTeR of EACH Word"
<b>输出：</b>"First Letter of Each Word"
<strong>解释：</strong>
单词 "of" 长度为 2 ，所以它保持完全小写。
其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>title = "i lOve leetcode"
<b>输出：</b>"i Love Leetcode"
<strong>解释：</strong>
单词 "i" 长度为 1 ，所以它保留小写。
其他单词长度都至少为 3 ，所以其他单词首字母大写，剩余字母小写。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= title.length &lt;= 100</code></li>
	<li><code>title</code>&nbsp;由单个空格隔开的单词组成，且不含有任何前导或后缀空格。</li>
	<li>每个单词由大写和小写英文字母组成，且都是 <strong>非空</strong>&nbsp;的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

直接模拟，按空格切分字符串，得到每个单词，再按题目转大小写。最后用空格连接每个单词。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 `title` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def capitalizeTitle(self, title: str) -> str:
        words = [w.lower() if len(w) < 3 else w.capitalize() for w in title.split()]
        return " ".join(words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String capitalizeTitle(String title) {
        List<String> ans = new ArrayList<>();
        for (String s : title.split(" ")) {
            if (s.length() < 3) {
                ans.add(s.toLowerCase());
            } else {
                ans.add(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
            }
        }
        return String.join(" ", ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string capitalizeTitle(string title) {
        transform(title.begin(), title.end(), title.begin(), ::tolower);
        istringstream ss(title);
        string ans;
        while (ss >> title) {
            if (title.size() > 2) title[0] = toupper(title[0]);
            ans += title;
            ans += " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

### **Go**

```go
func capitalizeTitle(title string) string {
	title = strings.ToLower(title)
	words := strings.Split(title, " ")
	for i, s := range words {
		if len(s) > 2 {
			words[i] = strings.Title(s)
		}
	}
	return strings.Join(words, " ")
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
