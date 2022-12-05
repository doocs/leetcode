# [2490. 回环句](https://leetcode.cn/problems/circular-sentence)

[English Version](/solution/2400-2499/2490.Circular%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>句子</strong> 是由单个空格分隔的一组单词，且不含前导或尾随空格。</p>

<ul>
	<li>例如，<code>"Hello World"</code>、<code>"HELLO"</code>、<code>"hello world hello world"</code> 都是符合要求的句子。</li>
</ul>

<p>单词 <strong>仅</strong> 由大写和小写英文字母组成。且大写和小写字母会视作不同字符。</p>

<p>如果句子满足下述全部条件，则认为它是一个 <strong>回环句</strong> ：</p>

<ul>
	<li>单词的最后一个字符和下一个单词的第一个字符相等。</li>
	<li>最后一个单词的最后一个字符和第一个单词的第一个字符相等。</li>
</ul>

<p>例如，<code>"leetcode exercises sound delightful"</code>、<code>"eetcode"</code>、<code>"leetcode eats soul"</code> 都是回环句。然而，<code>"Leetcode is cool"</code>、<code>"happy Leetcode"</code>、<code>"Leetcode"</code> 和 <code>"I like Leetcode"</code> 都 <strong>不</strong> 是回环句。</p>

<p>给你一个字符串 <code>sentence</code> ，请你判断它是不是一个回环句。如果是，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "leetcode exercises sound delightful"
<strong>输出：</strong>true
<strong>解释：</strong>句子中的单词是 ["leetcode", "exercises", "sound", "delightful"] 。
- leetcod<strong><em>e</em></strong> 的最后一个字符和 <strong><em>e</em></strong>xercises 的第一个字符相等。
- exercise<em><strong>s</strong></em> 的最后一个字符和 <em><strong>s</strong></em>ound 的第一个字符相等。
- <em><strong>s</strong></em>ound 的最后一个字符和 delightfu<em><strong>l</strong></em> 的第一个字符相等。
- delightfu<em><strong>l</strong></em> 的最后一个字符和 <em><strong>l</strong></em>eetcode 的第一个字符相等。
这个句子是回环句。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "eetcode"
<strong>输出：</strong>true
<strong>解释：</strong>句子中的单词是 ["eetcode"] 。
- eetcod<em><strong>e</strong></em> 的最后一个字符和 eetcod<em><strong>e</strong></em> 的第一个字符相等。
这个句子是回环句。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sentence = "Leetcode is cool"
<strong>输出：</strong>false
<strong>解释：</strong>句子中的单词是 ["Leetcode", "is", "cool"] 。
- Leetcod<em><strong>e</strong></em>&nbsp;的最后一个字符和 i<strong><em>s</em></strong> 的第一个字符 <strong>不</strong> 相等。 
这个句子 <strong>不</strong> 是回环句。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 500</code></li>
	<li><code>sentence</code> 仅由大小写英文字母和空格组成</li>
	<li><code>sentence</code> 中的单词由单个空格进行分隔</li>
	<li>不含任何前导或尾随空格</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意模拟即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        sentence = sentence.split()
        return all(s[0] == sentence[i - 1][-1] for i, s in enumerate(sentence))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        String[] ss = sentence.split(" ");
        for (int i = 1; i < ss.length; ++i) {
            if (ss[i].charAt(0) != ss[i - 1].charAt(ss[i - 1].length() - 1)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isCircularSentence(string sentence) {
        if (sentence[0] != sentence[sentence.size() - 1]) return false;
        istringstream is(sentence);
        vector<string> ss;
        string s;
        while (is >> s) ss.emplace_back(s);
        for (int i = 1; i < ss.size(); ++i) {
            if (ss[i][0] != ss[i - 1][ss[i - 1].size() - 1]) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isCircularSentence(sentence string) bool {
	if sentence[0] != sentence[len(sentence)-1] {
		return false
	}
	ss := strings.Split(sentence, " ")
	for i := 1; i < len(ss); i++ {
		if ss[i][0] != ss[i-1][len(ss[i-1])-1] {
			return false
		}
	}
	return true
}
```

### **JavaScript**

```js
var isCircularSentence = function (sentence) {
    const words = sentence.split(' ');
    const post = words[0].charCodeAt(0);
    let prev = words[0].charCodeAt(words[0].length - 1);
    const n = words.length;
    for (let i = 1; i < n; i++) {
        let cur = words[i];
        if (cur.charCodeAt(0) !== prev) {
            return false;
        }
        prev = cur.charCodeAt(cur.length - 1);
    }
    return post === prev;
};
```

### **TypeScript**

```ts
function isCircularSentence(sentence: string): boolean {
    const ss = sentence.split(' ');
    const n = ss.length;
    if (ss[0][0] !== ss[n - 1][ss[n - 1].length - 1]) {
        return false;
    }
    for (let i = 0; i < n - 1; i++) {
        if (ss[i][ss[i].length - 1] !== ss[i + 1][0]) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let ss: Vec<String> = sentence.split(' ').map(String::from).collect();
        let n = ss.len();
        if ss[0].as_bytes()[0] != ss[n - 1].as_bytes()[ss[n - 1].len() - 1] {
            return false;
        }
        for i in 1..n {
            if ss[i - 1].as_bytes()[ss[i - 1].len() - 1] != ss[i].as_bytes()[0] {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
