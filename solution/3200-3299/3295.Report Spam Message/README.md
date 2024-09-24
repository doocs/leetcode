---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3295.Report%20Spam%20Message/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3295. 举报垃圾信息](https://leetcode.cn/problems/report-spam-message)

[English Version](/solution/3200-3299/3295.Report%20Spam%20Message/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>message</code> 和一个字符串数组 <code>bannedWords</code>。</p>

<p>如果数组中 <strong>至少</strong> 存在两个单词与 <code>bannedWords</code> 中的任一单词 <strong>完全相同</strong>，则该数组被视为 <strong>垃圾信息</strong>。</p>

<p>如果数组 <code>message</code> 是垃圾信息，则返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">message = ["hello","world","leetcode"], bannedWords = ["world","hello"]</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>message</code> 中的 <code>"hello"</code> 和 <code>"world"</code> 都出现在数组 <code>bannedWords</code> 中。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">message = ["hello","programming","fun"], bannedWords = ["world","programming","leetcode"]</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>message</code> 中只有一个单词（<code>"programming"</code>）出现在数组 <code>bannedWords</code> 中。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= message.length, bannedWords.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= message[i].length, bannedWords[i].length &lt;= 15</code></li>
	<li><code>message[i]</code> 和 <code>bannedWords[i]</code> 都只由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $s$ 存储 $\textit{bannedWords}$ 中的所有单词，然后遍历 $\textit{message}$ 中的每个单词，如果单词在哈希表 $s$ 中出现，我们就将计数器 $cnt$ 加一，如果 $cnt$ 大于等于 $2$，我们就返回 $\text{true}$，否则返回 $\text{false}$。

时间复杂度 $O((n + m) \times |w|)$，空间复杂度 $O(m \times |w|)$。其中 $n$ 是数组 $\textit{message}$ 的长度，而 $m$ 和 $|w|$ 分别是数组 $\textit{bannedWords}$ 的长度和数组中单词的最大长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reportSpam(self, message: List[str], bannedWords: List[str]) -> bool:
        s = set(bannedWords)
        return sum(w in s for w in message) >= 2
```

#### Java

```java
class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> s = new HashSet<>();
        for (var w : bannedWords) {
            s.add(w);
        }
        int cnt = 0;
        for (var w : message) {
            if (s.contains(w) && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool reportSpam(vector<string>& message, vector<string>& bannedWords) {
        unordered_set<string> s(bannedWords.begin(), bannedWords.end());
        int cnt = 0;
        for (const auto& w : message) {
            if (s.contains(w) && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func reportSpam(message []string, bannedWords []string) bool {
	s := map[string]bool{}
	for _, w := range bannedWords {
		s[w] = true
	}
	cnt := 0
	for _, w := range message {
		if s[w] {
			cnt++
			if cnt >= 2 {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function reportSpam(message: string[], bannedWords: string[]): boolean {
    const s = new Set<string>(bannedWords);
    let cnt = 0;
    for (const w of message) {
        if (s.has(w) && ++cnt >= 2) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
