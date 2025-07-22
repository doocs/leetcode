---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0500.Keyboard%20Row/README_EN.md
tags:
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [500. Keyboard Row](https://leetcode.com/problems/keyboard-row)

[中文文档](/solution/0500-0599/0500.Keyboard%20Row/README.md)

## Description

<!-- description:start -->

<p>Given an array of strings <code>words</code>, return <em>the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below</em>.</p>

<p><strong>Note</strong> that the strings are <strong>case-insensitive</strong>, both lowercased and uppercased of the same letter are treated as if they are at the same row.</p>

<p>In the <strong>American keyboard</strong>:</p>

<ul>
	<li>the first row consists of the characters <code>&quot;qwertyuiop&quot;</code>,</li>
	<li>the second row consists of the characters <code>&quot;asdfghjkl&quot;</code>, and</li>
	<li>the third row consists of the characters <code>&quot;zxcvbnm&quot;</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0500.Keyboard%20Row/images/keyboard.png" style="width: 800px; max-width: 600px; height: 267px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;Hello&quot;,&quot;Alaska&quot;,&quot;Dad&quot;,&quot;Peace&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;Alaska&quot;,&quot;Dad&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>Both <code>&quot;a&quot;</code> and <code>&quot;A&quot;</code> are in the 2nd row of the American keyboard due to case insensitivity.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;omk&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;adsdf&quot;,&quot;sfd&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;adsdf&quot;,&quot;sfd&quot;]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 20</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists of English letters (both lowercase and uppercase).&nbsp;</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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

### Solution 2

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
