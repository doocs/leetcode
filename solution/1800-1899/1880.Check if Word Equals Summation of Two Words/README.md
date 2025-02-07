---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1880.Check%20if%20Word%20Equals%20Summation%20of%20Two%20Words/README.md
rating: 1187
source: 第 243 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1880. 检查某单词是否等于两单词之和](https://leetcode.cn/problems/check-if-word-equals-summation-of-two-words)

[English Version](/solution/1800-1899/1880.Check%20if%20Word%20Equals%20Summation%20of%20Two%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>字母的 <strong>字母值</strong> 取决于字母在字母表中的位置，<strong>从 0 开始</strong> 计数。即，<code>'a' -&gt; 0</code>、<code>'b' -&gt; 1</code>、<code>'c' -&gt; 2</code>，以此类推。</p>

<p>对某个由小写字母组成的字符串 <code>s</code> 而言，其 <strong>数值</strong> 就等于将 <code>s</code> 中每个字母的 <strong>字母值</strong> 按顺序 <strong>连接</strong> 并 <strong>转换</strong> 成对应整数。</p>

<ul>
	<li>例如，<code>s = "acb"</code> ，依次连接每个字母的字母值可以得到 <code>"021"</code> ，转换为整数得到 <code>21</code> 。</li>
</ul>

<p>给你三个字符串 <code>firstWord</code>、<code>secondWord</code> 和 <code>targetWord</code> ，每个字符串都由从 <code>'a'</code> 到 <code>'j'</code> （<strong>含 </strong><code>'a'</code> 和 <code>'j'</code><strong> </strong>）的小写英文字母组成。</p>

<p>如果 <code>firstWord</code><em> </em>和<em> </em><code>secondWord</code> 的 <strong>数值之和</strong> 等于<em> </em><code>targetWord</code><em> </em>的数值，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>firstWord = "acb", secondWord = "cba", targetWord = "cdb"
<strong>输出：</strong>true
<strong>解释：</strong>
firstWord 的数值为 "acb" -&gt; "021" -&gt; 21
secondWord 的数值为 "cba" -&gt; "210" -&gt; 210
targetWord 的数值为 "cdb" -&gt; "231" -&gt; 231
由于 21 + 210 == 231 ，返回 true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>firstWord = "aaa", secondWord = "a", targetWord = "aab"
<strong>输出：</strong>false
<strong>解释：</strong>
firstWord 的数值为 "aaa" -&gt; "000" -&gt; 0
secondWord 的数值为 "a" -&gt; "0" -&gt; 0
targetWord 的数值为 "aab" -&gt; "001" -&gt; 1
由于 0 + 0 != 1 ，返回 false</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>firstWord = "aaa", secondWord = "a", targetWord = "aaaa"
<strong>输出：</strong>true
<strong>解释：</strong>
firstWord 的数值为 "aaa" -&gt; "000" -&gt; 0
secondWord 的数值为 "a" -&gt; "0" -&gt; 0
targetWord 的数值为 "aaaa" -&gt; "0000" -&gt; 0
由于 0 + 0 == 0 ，返回 true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= firstWord.length, </code><code>secondWord.length, </code><code>targetWord.length &lt;= 8</code></li>
	<li><code>firstWord</code>、<code>secondWord</code> 和 <code>targetWord</code> 仅由从 <code>'a'</code> 到 <code>'j'</code> （<strong>含 </strong><code>'a'</code> 和 <code>'j'</code><strong> </strong>）的小写英文字母组成<strong>。</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串转数字

我们定义一个函数 $\textit{f}(s)$，用来计算字符串 $s$ 的数值。对于字符串 $s$ 中的每个字符 $c$，我们将其转换为对应的数字 $x$，然后将 $x$ 依次连接起来，最后转换为整数。

最后，我们只需要判断 $\textit{f}(\textit{firstWord}) + \textit{f}(\textit{secondWord})$ 是否等于 $\textit{f}(\textit{targetWord})$ 即可。

时间复杂度 $O(L)$，其中 $L$ 为题目中所有字符串的长度之和。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        def f(s: str) -> int:
            ans, a = 0, ord("a")
            for c in map(ord, s):
                x = c - a
                ans = ans * 10 + x
            return ans

        return f(firstWord) + f(secondWord) == f(targetWord)
```

#### Java

```java
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return f(firstWord) + f(secondWord) == f(targetWord);
    }

    private int f(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            ans = ans * 10 + (c - 'a');
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        auto f = [](string& s) -> int {
            int ans = 0;
            for (char c : s) {
                ans = ans * 10 + (c - 'a');
            }
            return ans;
        };
        return f(firstWord) + f(secondWord) == f(targetWord);
    }
};
```

#### Go

```go
func isSumEqual(firstWord string, secondWord string, targetWord string) bool {
	f := func(s string) (ans int) {
		for _, c := range s {
			ans = ans*10 + int(c-'a')
		}
		return
	}
	return f(firstWord)+f(secondWord) == f(targetWord)
}
```

#### TypeScript

```ts
function isSumEqual(firstWord: string, secondWord: string, targetWord: string): boolean {
    const f = (s: string): number => {
        let ans = 0;
        for (const c of s) {
            ans = ans * 10 + c.charCodeAt(0) - 97;
        }
        return ans;
    };
    return f(firstWord) + f(secondWord) == f(targetWord);
}
```

#### Rust

```rust
impl Solution {
    pub fn is_sum_equal(first_word: String, second_word: String, target_word: String) -> bool {
        fn f(s: &str) -> i64 {
            let mut ans = 0;
            let a = 'a' as i64;
            for c in s.chars() {
                let x = c as i64 - a;
                ans = ans * 10 + x;
            }
            ans
        }
        f(&first_word) + f(&second_word) == f(&target_word)
    }
}
```

#### JavaScript

```js
/**
 * @param {string} firstWord
 * @param {string} secondWord
 * @param {string} targetWord
 * @return {boolean}
 */
var isSumEqual = function (firstWord, secondWord, targetWord) {
    const f = s => {
        let ans = 0;
        for (const c of s) {
            ans = ans * 10 + c.charCodeAt(0) - 97;
        }
        return ans;
    };
    return f(firstWord) + f(secondWord) == f(targetWord);
};
```

#### C

```c
int f(const char* s) {
    int ans = 0;
    while (*s) {
        ans = ans * 10 + (*s - 'a');
        s++;
    }
    return ans;
}

bool isSumEqual(char* firstWord, char* secondWord, char* targetWord) {
    return f(firstWord) + f(secondWord) == f(targetWord);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
