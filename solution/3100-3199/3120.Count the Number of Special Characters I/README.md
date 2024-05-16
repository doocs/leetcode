---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3120.Count%20the%20Number%20of%20Special%20Characters%20I/README.md
rating: 1205
source: 第 394 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

# [3120. 统计特殊字母的数量 I](https://leetcode.cn/problems/count-the-number-of-special-characters-i)

[English Version](/solution/3100-3199/3120.Count%20the%20Number%20of%20Special%20Characters%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>word</code>。如果 <code>word</code> 中同时存在某个字母的小写形式和大写形式，则称这个字母为 <strong>特殊字母</strong> 。</p>

<p>返回 <code>word</code> 中<strong> </strong><strong>特殊字母 </strong>的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "aaAbcBC"</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><code>word</code> 中的特殊字母是 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "abc"</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p><code>word</code> 中不存在大小写形式同时出现的字母。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "abBCab"</span></p>

<p><strong>输出：</strong>1</p>

<p><strong>解释：</strong></p>

<p><code>word</code> 中唯一的特殊字母是 <code>'b'</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>word</code> 仅由小写和大写英文字母组成。</li>
</ul>

## 解法

### 方法一：哈希表或数组

我们用一个哈希表或数组 $s$ 来记录字符串 $word$ 中出现的字符。然后遍历 $26$ 个字母，如果小写字母和大写字母都在 $s$ 中出现，则特殊字符的数量加一。

最后返回特殊字符的数量即可。

时间复杂度 $O(n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 为字符串 $word$ 的长度；而 $|\Sigma|$ 为字符集大小，本题中 $|\Sigma| \leq 128$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfSpecialChars(self, word: str) -> int:
        s = set(word)
        return sum(a in s and b in s for a, b in zip(ascii_lowercase, ascii_uppercase))
```

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] s = new boolean['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            s[word.charAt(i)] = true;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (s['a' + i] && s['A' + i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSpecialChars(string word) {
        vector<bool> s('z' + 1);
        for (char& c : word) {
            s[c] = true;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans += s['a' + i] && s['A' + i];
        }
        return ans;
    }
};
```

```go
func numberOfSpecialChars(word string) (ans int) {
	s := make([]bool, 'z'+1)
	for _, c := range word {
		s[c] = true
	}
	for i := 0; i < 26; i++ {
		if s['a'+i] && s['A'+i] {
			ans++
		}
	}
	return
}
```

```ts
function numberOfSpecialChars(word: string): number {
    const s: boolean[] = Array.from({ length: 'z'.charCodeAt(0) + 1 }, () => false);
    for (let i = 0; i < word.length; ++i) {
        s[word.charCodeAt(i)] = true;
    }
    let ans: number = 0;
    for (let i = 0; i < 26; ++i) {
        if (s['a'.charCodeAt(0) + i] && s['A'.charCodeAt(0) + i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
