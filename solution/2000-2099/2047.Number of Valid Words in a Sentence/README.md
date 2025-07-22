---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2047.Number%20of%20Valid%20Words%20in%20a%20Sentence/README.md
rating: 1471
source: 第 264 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [2047. 句子中的有效单词数](https://leetcode.cn/problems/number-of-valid-words-in-a-sentence)

[English Version](/solution/2000-2099/2047.Number%20of%20Valid%20Words%20in%20a%20Sentence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>句子仅由小写字母（<code>'a'</code> 到 <code>'z'</code>）、数字（<code>'0'</code> 到 <code>'9'</code>）、连字符（<code>'-'</code>）、标点符号（<code>'!'</code>、<code>'.'</code> 和 <code>','</code>）以及空格（<code>' '</code>）组成。每个句子可以根据空格分解成 <strong>一个或者多个 token</strong> ，这些 token 之间由一个或者多个空格 <code>' '</code> 分隔。</p>

<p>如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：</p>

<ul>
	<li>仅由小写字母、连字符和/或标点（不含数字）组成。</li>
	<li><strong>至多一个</strong> 连字符 <code>'-'</code> 。如果存在，连字符两侧应当都存在小写字母（<code>"a-b"</code> 是一个有效单词，但 <code>"-ab"</code> 和 <code>"ab-"</code> 不是有效单词）。</li>
	<li><strong>至多一个 </strong>标点符号。如果存在，标点符号应当位于 token 的 <strong>末尾</strong> 。</li>
</ul>

<p>这里给出几个有效单词的例子：<code>"a-b."</code>、<code>"afad"</code>、<code>"ba-c"</code>、<code>"a!"</code> 和 <code>"!"</code> 。</p>

<p>给你一个字符串 <code>sentence</code> ，请你找出并返回<em> </em><code>sentence</code> 中<strong> 有效单词的数目</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "<em><strong>cat</strong></em> <em><strong>and</strong></em>  <em><strong>dog</strong></em>"
<strong>输出：</strong>3
<strong>解释：</strong>句子中的有效单词是 "cat"、"and" 和 "dog"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "!this  1-s b8d!"
<strong>输出：</strong>0
<strong>解释：</strong>句子中没有有效单词
"!this" 不是有效单词，因为它以一个标点开头
"1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sentence = "<em><strong>alice</strong></em> <em><strong>and</strong></em>  <em><strong>bob</strong></em> <em><strong>are</strong></em> <em><strong>playing</strong></em> stone-game10"
<strong>输出：</strong>5
<strong>解释：</strong>句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
"stone-game10" 不是有效单词，因为它含有数字
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
	<li><code>sentence</code> 由小写英文字母、数字（<code>0-9</code>）、以及字符（<code>' '</code>、<code>'-'</code>、<code>'!'</code>、<code>'.'</code> 和 <code>','</code>）组成</li>
	<li>句子中至少有 <code>1</code> 个 token</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们首先将句子按空格分割成单词，然后对每个单词进行检查，判断是否为有效单词。

对于每个单词，我们可以使用一个布尔变量 $\textit{st}$ 来记录是否已经出现过连字符，然后遍历单词中的每个字符，根据题目描述的规则进行判断。

对于每个字符 $s[i]$，我们有以下几种情况：

-   如果 $s[i]$ 是数字，那么 $s$ 不是有效单词，直接返回 $\text{false}$；
-   如果 $s[i]$ 是标点符号（'!'、'.'、','）且 $i < \text{len}(s) - 1$，那么 $s$ 不是有效单词，直接返回 $\text{false}$；
-   如果 $s[i]$ 是连字符，那么我们需要判断是否满足以下条件：
    -   连字符只能出现一次；
    -   连字符不能出现在单词的开头或结尾；
    -   连字符两侧必须是字母；
-   如果 $s[i]$ 是字母，那么我们不需要做任何处理。

最后，我们统计出句子中的有效单词数即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是句子的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countValidWords(self, sentence: str) -> int:
        def check(s: str) -> bool:
            st = False
            for i, c in enumerate(s):
                if c.isdigit() or (c in "!.," and i < len(s) - 1):
                    return False
                if c == "-":
                    if (
                        st
                        or i in (0, len(s) - 1)
                        or not s[i - 1].isalpha()
                        or not s[i + 1].isalpha()
                    ):
                        return False
                    st = True
            return True

        return sum(check(s) for s in sentence.split())
```

#### Java

```java
class Solution {
    public int countValidWords(String sentence) {
        int ans = 0;
        for (String s : sentence.split(" ")) {
            ans += check(s.toCharArray());
        }
        return ans;
    }

    private int check(char[] s) {
        if (s.length == 0) {
            return 0;
        }
        boolean st = false;
        for (int i = 0; i < s.length; ++i) {
            if (Character.isDigit(s[i])) {
                return 0;
            }
            if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length - 1) {
                return 0;
            }
            if (s[i] == '-') {
                if (st || i == 0 || i == s.length - 1) {
                    return 0;
                }
                if (!Character.isAlphabetic(s[i - 1]) || !Character.isAlphabetic(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countValidWords(string sentence) {
        auto check = [](const string& s) -> int {
            bool st = false;
            for (int i = 0; i < s.length(); ++i) {
                if (isdigit(s[i])) {
                    return 0;
                }
                if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length() - 1) {
                    return 0;
                }
                if (s[i] == '-') {
                    if (st || i == 0 || i == s.length() - 1) {
                        return 0;
                    }
                    if (!isalpha(s[i - 1]) || !isalpha(s[i + 1])) {
                        return 0;
                    }
                    st = true;
                }
            }
            return 1;
        };

        int ans = 0;
        stringstream ss(sentence);
        string s;
        while (ss >> s) {
            ans += check(s);
        }
        return ans;
    }
};
```

#### Go

```go
func countValidWords(sentence string) (ans int) {
	check := func(s string) int {
		if len(s) == 0 {
			return 0
		}
		st := false
		for i, r := range s {
			if unicode.IsDigit(r) {
				return 0
			}
			if (r == '!' || r == '.' || r == ',') && i < len(s)-1 {
				return 0
			}
			if r == '-' {
				if st || i == 0 || i == len(s)-1 {
					return 0
				}
				if !unicode.IsLetter(rune(s[i-1])) || !unicode.IsLetter(rune(s[i+1])) {
					return 0
				}
				st = true
			}
		}
		return 1
	}
	for _, s := range strings.Fields(sentence) {
		ans += check(s)
	}
	return ans
}
```

#### TypeScript

```ts
function countValidWords(sentence: string): number {
    const check = (s: string): number => {
        if (s.length === 0) {
            return 0;
        }
        let st = false;
        for (let i = 0; i < s.length; ++i) {
            if (/\d/.test(s[i])) {
                return 0;
            }
            if (['!', '.', ','].includes(s[i]) && i < s.length - 1) {
                return 0;
            }
            if (s[i] === '-') {
                if (st || [0, s.length - 1].includes(i)) {
                    return 0;
                }
                if (!/[a-zA-Z]/.test(s[i - 1]) || !/[a-zA-Z]/.test(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    };
    return sentence.split(/\s+/).reduce((acc, s) => acc + check(s), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
