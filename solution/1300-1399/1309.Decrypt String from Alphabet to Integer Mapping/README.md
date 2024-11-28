---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README.md
rating: 1257
source: 第 170 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1309. 解码字母到整数映射](https://leetcode.cn/problems/decrypt-string-from-alphabet-to-integer-mapping)

[English Version](/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>，它由数字（<code>'0'</code> - <code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;组成。我们希望按下述规则将&nbsp;<code>s</code>&nbsp;映射为一些小写英文字符：</p>

<ul>
	<li>字符（<code>'a'</code> - <code>'i'</code>）分别用（<code>'1'</code> -&nbsp;<code>'9'</code>）表示。</li>
	<li>字符（<code>'j'</code> - <code>'z'</code>）分别用（<code>'10#'</code>&nbsp;-&nbsp;<code>'26#'</code>）表示。&nbsp;</li>
</ul>

<p>返回映射之后形成的新字符串。</p>

<p>题目数据保证映射始终唯一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "10#11#12"
<strong>输出：</strong>"jkab"
<strong>解释：</strong>"j" -&gt; "10#" , "k" -&gt; "11#" , "a" -&gt; "1" , "b" -&gt; "2".
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "1326#"
<strong>输出：</strong>"acz"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 只包含数字（<code>'0'</code>-<code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;字符。</li>
	<li><code>s</code>&nbsp;是映射始终存在的有效字符串。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接模拟即可。

遍历字符串 $s$，对于当前遍历到的下标 $i$，如果 $i + 2 < n$ 且 $s[i + 2]$ 为 `#`，则将 $s[i]$ 和 $s[i + 1]$ 组成的字符串转换为整数，加上 `a` 的 ASCII 码值减去 1，然后转换为字符，添加到结果数组中，并将 $i$ 增加 3；否则，将 $s[i]$ 转换为整数，加上 `a` 的 ASCII 码值减去 1，然后转换为字符，添加到结果数组中，并将 $i$ 增加 1。

最后将结果数组转换为字符串返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def freqAlphabets(self, s: str) -> str:
        ans = []
        i, n = 0, len(s)
        while i < n:
            if i + 2 < n and s[i + 2] == "#":
                ans.append(chr(int(s[i : i + 2]) + ord("a") - 1))
                i += 3
            else:
                ans.append(chr(int(s[i]) + ord("a") - 1))
                i += 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String freqAlphabets(String s) {
        int i = 0, n = s.length();
        StringBuilder ans = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 2)) - 1));
                i += 3;
            } else {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 1)) - 1));
                i++;
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string freqAlphabets(string s) {
        string ans = "";
        int i = 0, n = s.size();
        while (i < n) {
            if (i + 2 < n && s[i + 2] == '#') {
                ans += char(stoi(s.substr(i, 2)) + 'a' - 1);
                i += 3;
            } else {
                ans += char(s[i] - '0' + 'a' - 1);
                i += 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func freqAlphabets(s string) string {
	var ans []byte
	for i, n := 0, len(s); i < n; {
		if i+2 < n && s[i+2] == '#' {
			num := (int(s[i])-'0')*10 + int(s[i+1]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 3
		} else {
			num := int(s[i]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 1
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function freqAlphabets(s: string): string {
    const ans: string[] = [];
    for (let i = 0, n = s.length; i < n; ) {
        if (i + 2 < n && s[i + 2] === '#') {
            ans.push(String.fromCharCode(96 + +s.slice(i, i + 2)));
            i += 3;
        } else {
            ans.push(String.fromCharCode(96 + +s[i]));
            i++;
        }
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn freq_alphabets(s: String) -> String {
        let s = s.as_bytes();
        let mut ans = String::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            if i + 2 < n && s[i + 2] == b'#' {
                let num = (s[i] - b'0') * 10 + (s[i + 1] - b'0');
                ans.push((96 + num) as char);
                i += 3;
            } else {
                let num = s[i] - b'0';
                ans.push((96 + num) as char);
                i += 1;
            }
        }
        ans
    }
}
```

#### C

```c
char* freqAlphabets(char* s) {
    int n = strlen(s);
    int i = 0;
    int j = 0;
    char* ans = malloc(sizeof(s) * n);
    while (i < n) {
        int t;
        if (i + 2 < n && s[i + 2] == '#') {
            t = (s[i] - '0') * 10 + s[i + 1];
            i += 3;
        } else {
            t = s[i];
            i += 1;
        }
        ans[j++] = 'a' + t - '1';
    }
    ans[j] = '\0';
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
