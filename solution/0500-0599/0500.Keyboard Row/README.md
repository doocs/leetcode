---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0500.Keyboard%20Row/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [500. 键盘行](https://leetcode.cn/problems/keyboard-row)

[English Version](/solution/0500-0599/0500.Keyboard%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code> ，只返回可以使用在 <strong>美式键盘</strong> 同一行的字母打印出来的单词。键盘如下图所示。</p>

<p><strong>请注意</strong>，字符串&nbsp;<strong>不区分大小写</strong>，相同字母的大小写形式都被视为在同一行<strong>。</strong></p>

<p><strong>美式键盘</strong> 中：</p>

<ul>
	<li>第一行由字符 <code>"qwertyuiop"</code> 组成。</li>
	<li>第二行由字符 <code>"asdfghjkl"</code> 组成。</li>
	<li>第三行由字符 <code>"zxcvbnm"</code> 组成。</li>
</ul>

<p><img alt="American keyboard" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0500.Keyboard%20Row/images/keyboard.png" style="width: 100%; max-width: 600px" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">words = ["Hello","Alaska","Dad","Peace"]</span></p>

<p><b>输出：</b><span class="example-io">["Alaska","Dad"]</span></p>

<p><strong>解释：</strong></p>

<p>由于不区分大小写，<code>"a"</code> 和&nbsp;<code>"A"</code> 都在美式键盘的第二行。</p>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>words = ["omk"]</span></p>

<p><span class="example-io"><b>输出：</b>[]</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">words = ["adsdf","sfd"]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">["adsdf","sfd"]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 20</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 由英文字母（小写和大写字母）组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符映射

我们将每个键盘行的字符映射到对应的行数，然后遍历字符串数组，判断每个字符串是否都在同一行即可。

时间复杂度 $O(L)$，空间复杂度 $O(C)$。其中 $L$ 为所有字符串的长度之和；而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        s1 = set('qwertyuiop')
        s2 = set('asdfghjkl')
        s3 = set('zxcvbnm')
        ans = []
        for w in words:
            s = set(w.lower())
            if s <= s1 or s <= s2 or s <= s3:
                ans.append(w)
        return ans
```

#### Java

```java
class Solution {
    public String[] findWords(String[] words) {
        String s = "12210111011122000010020202";
        List<String> ans = new ArrayList<>();
        for (var w : words) {
            String t = w.toLowerCase();
            char x = s.charAt(t.charAt(0) - 'a');
            boolean ok = true;
            for (char c : t.toCharArray()) {
                if (s.charAt(c - 'a') != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(w);
            }
        }
        return ans.toArray(new String[0]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        string s = "12210111011122000010020202";
        vector<string> ans;
        for (auto& w : words) {
            char x = s[tolower(w[0]) - 'a'];
            bool ok = true;
            for (char& c : w) {
                if (s[tolower(c) - 'a'] != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.emplace_back(w);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findWords(words []string) (ans []string) {
	s := "12210111011122000010020202"
	for _, w := range words {
		x := s[unicode.ToLower(rune(w[0]))-'a']
		ok := true
		for _, c := range w[1:] {
			if s[unicode.ToLower(c)-'a'] != x {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, w)
		}
	}
	return
}
```

#### TypeScript

```ts
function findWords(words: string[]): string[] {
    const s = '12210111011122000010020202';
    const ans: string[] = [];
    for (const w of words) {
        const t = w.toLowerCase();
        const x = s[t.charCodeAt(0) - 'a'.charCodeAt(0)];
        let ok = true;
        for (const c of t) {
            if (s[c.charCodeAt(0) - 'a'.charCodeAt(0)] !== x) {
                ok = false;
                break;
            }
        }
        if (ok) {
            ans.push(w);
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public string[] FindWords(string[] words) {
        string s = "12210111011122000010020202";
        IList<string> ans = new List<string>();
        foreach (string w in words) {
            char x = s[char.ToLower(w[0]) - 'a'];
            bool ok = true;
            for (int i = 1; i < w.Length; ++i) {
                if (s[char.ToLower(w[i]) - 'a'] != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.Add(w);
            }
        }
        return ans.ToArray();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        ans = []
        s = "12210111011122000010020202"
        for w in words:
            x = s[ord(w[0].lower()) - ord('a')]
            if all(s[ord(c.lower()) - ord('a')] == x for c in w):
                ans.append(w)
        return ans
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
