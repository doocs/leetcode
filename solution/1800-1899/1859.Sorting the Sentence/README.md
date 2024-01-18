# [1859. 将句子排序](https://leetcode.cn/problems/sorting-the-sentence)

[English Version](/solution/1800-1899/1859.Sorting%20the%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 <strong>句子</strong> 指的是一个序列的单词用单个空格连接起来，且开头和结尾没有任何空格。每个单词都只包含小写或大写英文字母。</p>

<p>我们可以给一个句子添加 <strong>从 1 开始的单词位置索引 </strong>，并且将句子中所有单词 <strong>打乱顺序</strong> 。</p>

<ul>
	<li>比方说，句子 <code>"This is a sentence"</code> 可以被打乱顺序得到 <code>"sentence4 a3 is2 This1"</code> 或者 <code>"is2 sentence4 This1 a3"</code> 。</li>
</ul>

<p>给你一个 <strong>打乱顺序</strong> 的句子 <code>s</code> ，它包含的单词不超过 <code>9</code> 个，请你重新构造并得到原本顺序的句子。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "is2 sentence4 This1 a3"
<b>输出：</b>"This is a sentence"
<b>解释：</b>将 s 中的单词按照初始位置排序，得到 "This1 is2 a3 sentence4" ，然后删除数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "Myself2 Me1 I4 and3"
<b>输出：</b>"Me Myself and I"
<b>解释：</b>将 s 中的单词按照初始位置排序，得到 "Me1 Myself2 and3 I4" ，然后删除数字。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= s.length <= 200</code></li>
	<li><code>s</code> 只包含小写和大写英文字母、空格以及从 <code>1</code> 到 <code>9</code> 的数字。</li>
	<li><code>s</code> 中单词数目为 <code>1</code> 到 <code>9</code> 个。</li>
	<li><code>s</code> 中的单词由单个空格分隔。</li>
	<li><code>s</code> 不包含任何前导或者后缀空格。</li>
</ul>

## 解法

### 方法一：字符串分割

我们先将字符串 $s$ 按照空格分割，得到字符串数组 $words$。然后，我们创建一个长度为 $|words|$ 的字符串数组 $ans$，用于存放答案。

接下来，遍历字符串数组 $words$ 中的每个字符串 $w$，找到 $w$ 的最后一个字符表示的位置 $i$，然后将 $w$ 的前 $|w|-1$ 个字符作为新的字符串 $w'$，将 $w'$ 放在数组 $ans$ 的第 $i$ 个位置。

最后，将数组 $ans$ 按照空格连接成字符串，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def sortSentence(self, s: str) -> str:
        ws = [(w[:-1], int(w[-1])) for w in s.split()]
        ws.sort(key=lambda x: x[1])
        return ' '.join(w for w, _ in ws)
```

```java
class Solution {
    public String sortSentence(String s) {
        String[] ws = s.split(" ");
        int n = ws.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            String w = ws[i];
            ans[w.charAt(w.length() - 1) - '1'] = w.substring(0, w.length() - 1);
        }
        return String.join(" ", ans);
    }
}
```

```cpp
class Solution {
public:
    string sortSentence(string s) {
        istringstream iss(s);
        string w;
        vector<string> ws;
        while (iss >> w) {
            ws.push_back(w);
        }
        vector<string> ss(ws.size());
        for (auto& w : ws) {
            ss[w.back() - '1'] = w.substr(0, w.size() - 1);
        }
        string ans;
        for (auto& w : ss) {
            ans += w + " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

```go
func sortSentence(s string) string {
	ws := strings.Split(s, " ")
	ans := make([]string, len(ws))
	for _, w := range ws {
		ans[w[len(w)-1]-'1'] = w[:len(w)-1]
	}
	return strings.Join(ans, " ")
}
```

```ts
function sortSentence(s: string): string {
    const ws = s.split(' ');
    const ans = Array(ws.length);
    for (const w of ws) {
        ans[w.charCodeAt(w.length - 1) - '1'.charCodeAt(0)] = w.slice(0, -1);
    }
    return ans.join(' ');
}
```

```js
/**
 * @param {string} s
 * @return {string}
 */
var sortSentence = function (s) {
    const ws = s.split(' ');
    const ans = Array(ws.length);
    for (const w of ws) {
        ans[w.charCodeAt(w.length - 1) - '1'.charCodeAt(0)] = w.slice(0, -1);
    }
    return ans.join(' ');
};
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def sortSentence(self, s: str) -> str:
        ws = s.split()
        ans = [None] * len(ws)
        for w in ws:
            ans[int(w[-1]) - 1] = w[:-1]
        return ' '.join(ans)
```

<!-- tabs:end -->

<!-- end -->
