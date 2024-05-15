---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1078.Occurrences%20After%20Bigram/README.md
rating: 1274
tags:
    - 字符串
---

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

### 方法一：字符串分割

我们可以将字符串 $text$ 按照空格分割成字符串数组 $words$，然后遍历 $words$，如果 $words[i]$ 和 $words[i+1]$ 分别等于 $first$ 和 $second$，那么就将 $words[i+2]$ 添加到答案中。

遍历结束后，返回答案列表。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 是 $text$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        words = text.split()
        ans = []
        for i in range(len(words) - 2):
            a, b, c = words[i : i + 3]
            if a == first and b == second:
                ans.append(c)
        return ans
```

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

```cpp
class Solution {
public:
    vector<string> findOcurrences(string text, string first, string second) {
        istringstream is(text);
        vector<string> words;
        string word;
        while (is >> word) {
            words.emplace_back(word);
        }
        vector<string> ans;
        int n = words.size();
        for (int i = 0; i < n - 2; ++i) {
            if (words[i] == first && words[i + 1] == second) {
                ans.emplace_back(words[i + 2]);
            }
        }
        return ans;
    }
};
```

```go
func findOcurrences(text string, first string, second string) (ans []string) {
	words := strings.Split(text, " ")
	n := len(words)
	for i := 0; i < n-2; i++ {
		if words[i] == first && words[i+1] == second {
			ans = append(ans, words[i+2])
		}
	}
	return
}
```

```ts
function findOcurrences(text: string, first: string, second: string): string[] {
    const words = text.split(' ');
    const n = words.length;
    const ans: string[] = [];
    for (let i = 0; i < n - 2; i++) {
        if (words[i] === first && words[i + 1] === second) {
            ans.push(words[i + 2]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
