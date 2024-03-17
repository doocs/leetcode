# [3083. 字符串及其反转中是否存在同一子字符串](https://leetcode.cn/problems/existence-of-a-substring-in-a-string-and-its-reverse)

[English Version](/solution/3000-3099/3083.Existence%20of%20a%20Substring%20in%20a%20String%20and%20Its%20Reverse/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请你判断字符串 <code>s</code> 是否存在一个长度为 <code>2</code> 的子字符串，在其反转后的字符串中也出现。</p>

<p>如果存在这样的子字符串，返回 <code>true</code>；如果不存在，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "leetcode"</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">true</span></p>

<p><strong>解释：</strong>子字符串 <code>"ee"</code> 的长度为 <code>2</code>，它也出现在 <code>reverse(s) == "edocteel"</code> 中。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "abcba"</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">true</span></p>

<p><strong>解释：</strong>所有长度为 <code>2</code> 的子字符串 <code>"ab"</code>、<code>"bc"</code>、<code>"cb"</code>、<code>"ba"</code> 也都出现在 <code>reverse(s) == "abcba"</code> 中。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>输入：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">s = "abcd"</span></p>

<p><strong>输出：</strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">false</span></p>

<p><strong>解释：</strong>字符串 <code>s</code> 中不存在满足「在其反转后的字符串中也出现」且长度为 <code>2</code> 的子字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li>字符串 <code>s</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：哈希表或数组

我们可以用一个哈希表或者二维数组 $st$ 来存储字符串 $s$ 反转后的所有长度为 $2$ 的子字符串。

然后我们遍历字符串 $s$，对于每一个长度为 $2$ 的子字符串，我们判断它是否在 $st$ 中出现过，是则返回 `true`。否则，遍历结束后返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|^2)$。其中 $n$ 为字符串 $s$ 的长度；而 $\Sigma$ 为字符串 $s$ 的字符集，本题中 $\Sigma$ 为小写英文字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def isSubstringPresent(self, s: str) -> bool:
        st = {(a, b) for a, b in pairwise(s[::-1])}
        return any((a, b) in st for a, b in pairwise(s))
```

```java
class Solution {
    public boolean isSubstringPresent(String s) {
        boolean[][] st = new boolean[26][26];
        int n = s.length();
        for (int i = 0; i < n - 1; ++i) {
            st[s.charAt(i + 1) - 'a'][s.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s.charAt(i) - 'a'][s.charAt(i + 1) - 'a']) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool isSubstringPresent(string s) {
        bool st[26][26]{};
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            st[s[i + 1] - 'a'][s[i] - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s[i] - 'a'][s[i + 1] - 'a']) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func isSubstringPresent(s string) bool {
	st := [26][26]bool{}
	for i := 0; i < len(s)-1; i++ {
		st[s[i+1]-'a'][s[i]-'a'] = true
	}
	for i := 0; i < len(s)-1; i++ {
		if st[s[i]-'a'][s[i+1]-'a'] {
			return true
		}
	}
	return false
}
```

```ts
function isSubstringPresent(s: string): boolean {
    const st: boolean[][] = Array.from({ length: 26 }, () => Array(26).fill(false));
    for (let i = 0; i < s.length - 1; ++i) {
        st[s.charCodeAt(i + 1) - 97][s.charCodeAt(i) - 97] = true;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        if (st[s.charCodeAt(i) - 97][s.charCodeAt(i + 1) - 97]) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
