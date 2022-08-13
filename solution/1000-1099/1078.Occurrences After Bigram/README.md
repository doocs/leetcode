# [1078. Bigram 分词](https://leetcode.cn/problems/occurrences-after-bigram)

[English Version](/solution/1000-1099/1078.Occurrences%20After%20Bigram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出第一个词&nbsp;<code>first</code> 和第二个词&nbsp;<code>second</code>，考虑在某些文本&nbsp;<code>text</code>&nbsp;中可能以 <code>"first second third"</code> 形式出现的情况，其中&nbsp;<code>second</code>&nbsp;紧随&nbsp;<code>first</code>&nbsp;出现，<code>third</code>&nbsp;紧随&nbsp;<code>second</code>&nbsp;出现。</p>

<p>对于每种这样的情况，将第三个词 "<code>third</code>" 添加到答案中，并返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>text = "alice is a good girl she is a good student", first = "a", second = "good"
<strong>输出：</strong>["girl","student"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>text = "we will we will rock you", first = "we", second = "will"
<strong>输出：</strong>["we","rock"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code>&nbsp;由小写英文字母和空格组成</li>
	<li><code>text</code> 中的所有单词之间都由 <strong>单个空格字符</strong> 分隔</li>
	<li><code>1 &lt;= first.length, second.length &lt;= 10</code></li>
	<li><code>first</code> 和&nbsp;<code>second</code>&nbsp;由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

将 text 按空格切分为 words 列表，然后遍历 words，判断是否满足 `words[i] == first && words[i + 1] == second`，若是，则将 `words[i + 2]` 添加至结果列表 ans 中。

最后返回 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        words = text.split(' ')
        return [
            words[i + 2]
            for i in range(len(words) - 2)
            if words[i] == first and words[i + 1] == second
        ]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length - 2; ++i) {
            if (first.equals(words[i]) && second.equals(words[i + 1])) {
                ans.add(words[i + 2]);
            }
        }
        return ans.toArray(new String[0]);
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<string> findOcurrences(string text, string first, string second) {
        istringstream is(text);
        vector<string> words;
        string word;
        while (is >> word) words.push_back(word);
        vector<string> ans;
        for (int i = 0; i < words.size() - 2; ++i)
            if (words[i] == first && words[i + 1] == second)
                ans.push_back(words[i + 2]);
        return ans;
    }
};
```

### **Go**

```go
func findOcurrences(text string, first string, second string) []string {
	words := strings.Split(text, " ")
	var ans []string
	for i := 0; i < len(words)-2; i++ {
		if words[i] == first && words[i+1] == second {
			ans = append(ans, words[i+2])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
