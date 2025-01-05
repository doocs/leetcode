---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [151. 反转字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string)

[English Version](/solution/0100-0199/0151.Reverse%20Words%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，请你反转字符串中 <strong>单词</strong> 的顺序。</p>

<p><strong>单词</strong> 是由非空格字符组成的字符串。<code>s</code> 中使用至少一个空格将字符串中的 <strong>单词</strong> 分隔开。</p>

<p>返回 <strong>单词</strong> 顺序颠倒且 <strong>单词</strong> 之间用单个空格连接的结果字符串。</p>

<p><strong>注意：</strong>输入字符串 <code>s</code>中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "the sky is blue"
<strong>输出：</strong>"blue is sky the"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = " &nbsp;hello world &nbsp;"
<strong>输出：</strong>"world hello"
<strong>解释：</strong>反转后的字符串中不能存在前导空格和尾随空格。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "a good &nbsp; example"
<strong>输出：</strong>"example good a"
<strong>解释：</strong>如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 包含英文大小写字母、数字和空格 <code>' '</code></li>
	<li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li>
</ul>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用&nbsp;<code>O(1)</code> 额外空间复杂度的 <strong>原地</strong> 解法。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以使用双指针 $i$ 和 $j$，每次找到一个单词，将其添加到结果列表中，最后将结果列表反转，再拼接成字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        words = []
        i, n = 0, len(s)
        while i < n:
            while i < n and s[i] == " ":
                i += 1
            if i < n:
                j = i
                while j < n and s[j] != " ":
                    j += 1
                words.append(s[i:j])
                i = j
        return " ".join(words[::-1])
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n;) {
            while (i < n && s.charAt(i) == ' ') {
                ++i;
            }
            if (i < n) {
                StringBuilder t = new StringBuilder();
                int j = i;
                while (j < n && s.charAt(j) != ' ') {
                    t.append(s.charAt(j++));
                }
                words.add(t.toString());
                i = j;
            }
        }
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseWords(string s) {
        int i = 0;
        int j = 0;
        int n = s.size();
        while (i < n) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                if (j != 0) {
                    s[j++] = ' ';
                }
                int k = i;
                while (k < n && s[k] != ' ') {
                    s[j++] = s[k++];
                }
                reverse(s.begin() + j - (k - i), s.begin() + j);
                i = k;
            }
        }
        s.erase(s.begin() + j, s.end());
        reverse(s.begin(), s.end());
        return s;
    }
};
```

#### Go

```go
func reverseWords(s string) string {
	words := []string{}
	i, n := 0, len(s)
	for i < n {
		for i < n && s[i] == ' ' {
			i++
		}
		if i < n {
			j := i
			t := []byte{}
			for j < n && s[j] != ' ' {
				t = append(t, s[j])
				j++
			}
			words = append(words, string(t))
			i = j
		}
	}
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    const words: string[] = [];
    const n = s.length;
    let i = 0;
    while (i < n) {
        while (i < n && s[i] === ' ') {
            i++;
        }
        if (i < n) {
            let j = i;
            while (j < n && s[j] !== ' ') {
                j++;
            }
            words.push(s.slice(i, j));
            i = j;
        }
    }
    return words.reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        let mut words = Vec::new();
        let s: Vec<char> = s.chars().collect();
        let mut i = 0;
        let n = s.len();

        while i < n {
            while i < n && s[i] == ' ' {
                i += 1;
            }
            if i < n {
                let mut j = i;
                while j < n && s[j] != ' ' {
                    j += 1;
                }
                words.push(s[i..j].iter().collect::<String>());
                i = j;
            }
        }

        words.reverse();
        words.join(" ")
    }
}
```

#### C#

```cs
public class Solution {
    public string ReverseWords(string s) {
        List<string> words = new List<string>();
        int n = s.Length;
        for (int i = 0; i < n;) {
            while (i < n && s[i] == ' ') {
                ++i;
            }
            if (i < n) {
                System.Text.StringBuilder t = new System.Text.StringBuilder();
                int j = i;
                while (j < n && s[j] != ' ') {
                    t.Append(s[j++]);
                }
                words.Add(t.ToString());
                i = j;
            }
        }
        words.Reverse();
        return string.Join(" ", words);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：字符串分割

我们可以使用语言内置的字符串分割函数，将字符串按空格分割成单词列表，然后将列表反转，再拼接成字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        return " ".join(reversed(s.split()))
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
```

#### Go

```go
func reverseWords(s string) string {
	words := strings.Fields(s)
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    return s.trim().split(/\s+/).reverse().join(' ');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split_whitespace().rev().collect::<Vec<&str>>().join(" ")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
