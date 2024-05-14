# [2490. 回环句](https://leetcode.cn/problems/circular-sentence)

[English Version](/solution/2400-2499/2490.Circular%20Sentence/README_EN.md)

<!-- tags:字符串 -->

<!-- difficulty:简单 -->

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
- eetcod<em><strong>e</strong></em> 的最后一个字符和 <em><strong>e</strong></em>etcod<em>e</em> 的第一个字符相等。
这个句子是回环句。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sentence = "Leetcode is cool"
<strong>输出：</strong>false
<strong>解释：</strong>句子中的单词是 ["Leetcode", "is", "cool"] 。
- Leetcod<em><strong>e</strong></em>&nbsp;的最后一个字符和 <em><strong>i</strong></em>s 的第一个字符 <strong>不</strong> 相等。 
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

### 方法一：模拟

我们将字符串按照空格分割成单词，然后判断每个单词的最后一个字符和下一个单词的第一个字符是否相等，如果不相等则返回 `false`，否则遍历完所有单词后返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

```python
class Solution:
    def isCircularSentence(self, sentence: str) -> bool:
        ss = sentence.split()
        n = len(ss)
        return all(s[-1] == ss[(i + 1) % n][0] for i, s in enumerate(ss))
```

```java
class Solution {
    public boolean isCircularSentence(String sentence) {
        var ss = sentence.split(" ");
        int n = ss.length;
        for (int i = 0; i < n; ++i) {
            if (ss[i].charAt(ss[i].length() - 1) != ss[(i + 1) % n].charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isCircularSentence(string sentence) {
        auto ss = split(sentence, ' ');
        int n = ss.size();
        for (int i = 0; i < n; ++i) {
            if (ss[i].back() != ss[(i + 1) % n][0]) {
                return false;
            }
        }
        return true;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};
```

```go
func isCircularSentence(sentence string) bool {
	ss := strings.Split(sentence, " ")
	n := len(ss)
	for i, s := range ss {
		if s[len(s)-1] != ss[(i+1)%n][0] {
			return false
		}
	}
	return true
}
```

```ts
function isCircularSentence(sentence: string): boolean {
    const ss = sentence.split(' ');
    const n = ss.length;
    for (let i = 0; i < n; ++i) {
        if (ss[i][ss[i].length - 1] !== ss[(i + 1) % n][0]) {
            return false;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let ss: Vec<String> = sentence.split(' ').map(String::from).collect();
        let n = ss.len();
        for i in 0..n {
            if ss[i].as_bytes()[ss[i].len() - 1] != ss[(i + 1) % n].as_bytes()[0] {
                return false;
            }
        }
        return true;
    }
}
```

```js
/**
 * @param {string} sentence
 * @return {boolean}
 */
var isCircularSentence = function (sentence) {
    const ss = sentence.split(' ');
    const n = ss.length;
    for (let i = 0; i < n; ++i) {
        if (ss[i][ss[i].length - 1] !== ss[(i + 1) % n][0]) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

### 方法二：模拟（空间优化）

我们可以先判断字符串的第一个字符和最后一个字符是否相等，如果不相等则返回 `false`，否则遍历字符串，如果当前字符是空格，则判断前一个字符和后一个字符是否相等，如果不相等则返回 `false`，否则遍历完所有字符后返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def isCircularSentence(self, s: str) -> bool:
        return s[0] == s[-1] and all(
            c != " " or s[i - 1] == s[i + 1] for i, c in enumerate(s)
        )
```

```java
class Solution {
    public boolean isCircularSentence(String s) {
        int n = s.length();
        if (s.charAt(0) != s.charAt(n - 1)) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == ' ' && s.charAt(i - 1) != s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isCircularSentence(string s) {
        int n = s.size();
        if (s[0] != s.back()) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (s[i] == ' ' && s[i - 1] != s[i + 1]) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isCircularSentence(s string) bool {
	n := len(s)
	if s[0] != s[n-1] {
		return false
	}
	for i := 1; i < n; i++ {
		if s[i] == ' ' && s[i-1] != s[i+1] {
			return false
		}
	}
	return true
}
```

```ts
function isCircularSentence(s: string): boolean {
    const n = s.length;
    if (s[0] !== s[n - 1]) {
        return false;
    }
    for (let i = 1; i < n; ++i) {
        if (s[i] === ' ' && s[i - 1] !== s[i + 1]) {
            return false;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let n = sentence.len();
        let chars: Vec<char> = sentence.chars().collect();

        if chars[0] != chars[n - 1] {
            return false;
        }

        for i in 1..n - 1 {
            if chars[i] == ' ' && chars[i - 1] != chars[i + 1] {
                return false;
            }
        }

        true
    }
}
```

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var isCircularSentence = function (s) {
    const n = s.length;
    if (s[0] !== s[n - 1]) {
        return false;
    }
    for (let i = 1; i < n; ++i) {
        if (s[i] === ' ' && s[i - 1] !== s[i + 1]) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- end -->
