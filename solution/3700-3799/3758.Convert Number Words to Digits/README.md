---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3758.Convert%20Number%20Words%20to%20Digits/README.md
tags:
    - 字典树
    - 字符串
---

<!-- problem:start -->

# [3758. 将数字词转换为数字 🔒](https://leetcode.cn/problems/convert-number-words-to-digits)

[English Version](/solution/3700-3799/3758.Convert%20Number%20Words%20to%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由小写英文字母组成的字符串&nbsp;<code>s</code>。<code>s</code> 可能包含表示数字 0 到 9 的 <strong>有效连续</strong> 英文单词，没有空格。</p>

<p>你的任务是 <strong>按顺序</strong> 提取 <strong>每个</strong> 有效的数字词，并将其转换为相应的数字，生成一个数字字符串。</p>

<p>从左往右解析&nbsp;<code>s</code>。在每个位置：</p>

<ul>
	<li>如果当前位置有一个有效的数字单词，将其对应的数字添加到结果中，并将位置向前移动该单词的长度。</li>
	<li>否则，跳过 <strong>恰好</strong> 一个字符，继续解析。</li>
</ul>

<p>返回结果数字字符串。如果没有找到数字单词，返回空字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "onefourthree"</span></p>

<p><span class="example-io"><b>输出：</b>"143"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>从左到右解析，提取有效的数字词 "one"，"four"，"three"。</li>
	<li>他们映射到数字 1，4，3。因此，最终结果是&nbsp;<code>"143"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "ninexsix"</span></p>

<p><span class="example-io"><b>输出：</b>"96"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>子字符串&nbsp;<code>"nine"</code>&nbsp;是一个合法的数字词，映射到 9。</li>
	<li>字符&nbsp;<code>"x"</code>&nbsp;没有对应任何有效数字词的前缀，被跳过。</li>
	<li>然后，子字符串&nbsp;<code>"six"</code>&nbsp;是一个有效的数字词并且被映射到 6，所以最后结果是&nbsp;<code>"96"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "zeero"</span></p>

<p><span class="example-io"><b>输出：</b>""</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在从左到右解析过程中，没有任何子串构成有效的数字词。</li>
	<li>所有字符都被跳过，不完整的片段被忽略，因此结果是空字符串。</li>
</ul>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "tw"</span></p>

<p><span class="example-io"><b>输出：</b>""</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在从左到右解析过程中，没有任何子串构成有效的数字词。</li>
	<li>所有字符都被跳过，不完整的片段被忽略，因此结果是空字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们首先将数字单词与对应的数字建立映射关系，记录在数组 $d$ 中，其中 $d[i]$ 表示数字 $i$ 对应的单词。

然后我们从左到右遍历字符串 $s$，对于每个位置 $i$，我们依次枚举数字单词 $d[j]$，判断从位置 $i$ 开始的子串是否与 $d[j]$ 匹配。如果匹配成功，则将数字 $j$ 添加到结果中，并将位置 $i$ 向后移动 $|d[j]|$ 个位置。否则，将位置 $i$ 向后移动 1 个位置。

我们重复上述过程，直到遍历完整个字符串 $s$。最后将结果中的数字连接成字符串并返回。

时间复杂度 $O(n \times |d|)$，空间复杂度 $O(|d|)$，其中 $n$ 是字符串 $s$ 的长度，而 $|d|$ 是数字单词的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convertNumber(self, s: str) -> str:
        d = [
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
        ]
        i, n = 0, len(s)
        ans = []
        while i < n:
            for j, t in enumerate(d):
                m = len(t)
                if i + m <= n and s[i : i + m] == t:
                    ans.append(str(j))
                    i += m - 1
                    break
            i += 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String convertNumber(String s) {
        String[] d
            = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < d.length; ++j) {
                String t = d[j];
                int m = t.length();
                if (i + m <= n && s.substring(i, i + m).equals(t)) {
                    ans.append(j);
                    i += m - 1;
                    break;
                }
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
    string convertNumber(string s) {
        vector<string> d = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int n = s.length();
        string ans;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < d.size(); ++j) {
                string t = d[j];
                int m = t.length();
                if (i + m <= n && s.substr(i, m) == t) {
                    ans += to_string(j);
                    i += m - 1;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func convertNumber(s string) string {
	d := []string{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}
	n := len(s)
	var ans strings.Builder
	for i := 0; i < n; i++ {
		for j, t := range d {
			m := len(t)
			if i+m <= n && s[i:i+m] == t {
				ans.WriteString(strconv.Itoa(j))
				i += m - 1
				break
			}
		}
	}
	return ans.String()
}
```

#### TypeScript

```ts
function convertNumber(s: string): string {
    const d: string[] = [
        'zero',
        'one',
        'two',
        'three',
        'four',
        'five',
        'six',
        'seven',
        'eight',
        'nine',
    ];
    const n = s.length;
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < d.length; ++j) {
            const t = d[j];
            const m = t.length;
            if (i + m <= n && s.substring(i, i + m) === t) {
                ans.push(j.toString());
                i += m - 1;
                break;
            }
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
