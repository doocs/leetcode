# [1816. 截断句子](https://leetcode.cn/problems/truncate-sentence)

[English Version](/solution/1800-1899/1816.Truncate%20Sentence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>句子</strong> 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。</p>

<ul>
	<li>例如，<code>"Hello World"</code>、<code>"HELLO"</code> 和 <code>"hello world hello world"</code> 都是句子。</li>
</ul>

<p>给你一个句子 <code>s</code>​​​​​​ 和一个整数 <code>k</code>​​​​​​ ，请你将 <code>s</code>​​ <strong>截断</strong> ​，​​​使截断后的句子仅含 <strong>前</strong> <code>k</code>​​​​​​ 个单词。返回 <strong>截断</strong> <code>s</code>​​​​<em>​​ </em>后得到的句子<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "Hello how are you Contestant", k = 4
<strong>输出：</strong>"Hello how are you"
<strong>解释：</strong>
s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
前 4 个单词为 ["Hello", "how", "are", "you"]
因此，应当返回 "Hello how are you"
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "What is the solution to this problem", k = 4
<strong>输出：</strong>"What is the solution"
<strong>解释：</strong>
s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
前 4 个单词为 ["What", "is", "the", "solution"]
因此，应当返回 "What is the solution"</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "chopper is not a tanuki", k = 5
<strong>输出：</strong>"chopper is not a tanuki"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>k</code> 的取值范围是 <code>[1,  s 中单词的数目]</code></li>
	<li><code>s</code> 仅由大小写英文字母和空格组成</li>
	<li><code>s</code> 中的单词之间由单个空格隔开</li>
	<li>不存在前导或尾随空格</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们从前往后遍历字符串 $s$，对于当前遍历到的字符 $s[i]$，如果 $s[i]$ 是空格，那么 $k$ 自减 1，当 $k$ 为 0 时，说明已经截取了 $k$ 个单词，截取字符串 $s[0:i]$ 返回即可。

遍历结束，返回 $s$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def truncateSentence(self, s: str, k: int) -> str:
        return ' '.join(s.split()[:k])
```

```python
class Solution:
    def truncateSentence(self, s: str, k: int) -> str:
        for i, c in enumerate(s):
            k -= c == ' '
            if k == 0:
                return s[:i]
        return s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ' && (--k) == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string truncateSentence(string s, int k) {
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == ' ' && (--k) == 0) {
                return s.substr(0, i);
            }
        }
        return s;
    }
};
```

### **Go**

```go
func truncateSentence(s string, k int) string {
	for i, c := range s {
		if c == ' ' {
			k--
		}
		if k == 0 {
			return s[:i]
		}
	}
	return s
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
var truncateSentence = function (s, k) {
    for (let i = 0; i < s.length; ++i) {
        if (s[i] == ' ' && --k == 0) {
            return s.slice(0, i);
        }
    }
    return s;
};
```

### **...**

```

```

<!-- tabs:end -->
