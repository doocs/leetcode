---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4019.Merge%20Close%20Characters%20II/README.md
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [4019. 合并靠近字符 II 🔒](https://leetcode.cn/problems/merge-close-characters-ii)

[English Version](/solution/4000-4099/4019.Merge%20Close%20Characters%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由小写英文字母组成的字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>如果两个相同的字符 <code>s[i]</code> 和 <code>s[j]</code> 满足 <code>0 &lt;= i &lt; j &lt; s.length</code> 且 <code>j - i &lt;= k</code>，则认为它们是 <strong>靠近</strong>&nbsp;的。所有下标均指 <strong>当前</strong> 字符串中的下标。</p>

<p>重复执行以下操作，直到不存在靠近字符对：</p>

<ul>
	<li>在所有相邻字符对 <code>(i, j)</code> 中，选择 <code>i</code> 最小的那一对。如果存在多个具有相同 <code>i</code> 的相邻字符对，则选择 <code>j</code> 最小的那一对。</li>
	<li>将右侧字符合并到左侧字符中，即从 <code>s</code> 中删除 <code>s[j]</code>。字符 <code>s[i]</code> 保持不变，其余字符重新编号。</li>
</ul>

<p>返回执行所有可能的合并操作后得到的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abca", k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">"abc"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 0 和 3 的字符 <code>'a'</code> 是相邻的，因为 <code>3 - 0 = 3 &lt;= k</code>。</li>
	<li>删除右侧的 <code>'a'</code>，得到 <code>s = "abc"</code>。</li>
	<li>不存在相邻字符对，因此不再进行合并。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aabca", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">"abca"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 0 和 1 的字符 <code>'a'</code> 是相邻的，因为 <code>1 - 0 = 1 &lt;= k</code>。</li>
	<li>删除右侧的 <code>'a'</code>，得到 <code>s = "abca"</code>。</li>
	<li>剩余的两个 <code>'a'</code> 位于下标 0 和 3。由于 <code>3 - 0 = 3 &gt; k</code>，不存在相邻字符对。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "yybyzybz", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">"ybzybz"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标为 0 和 1 的字符 <code>'y'</code> 是相邻的，因为 <code>1 - 0 = 1 &lt;= k</code>。这对字符的左侧下标是所有相邻字符对中最小的。</li>
	<li>删除右侧的 <code>'y'</code>，得到 <code>s = "ybyzybz"</code>。</li>
	<li>此时下标为 0 和 2 的字符 <code>'y'</code> 是相邻的，因为 <code>2 - 0 = 2 &lt;= k</code>。</li>
	<li>删除右侧的 <code>'y'</code>，得到 <code>s = "ybzybz"</code>。</li>
	<li>不存在相邻字符对，因此不再进行合并。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们使用一个哈希表 $\textit{last}$ 记录每个字符在答案字符串中上一次出现的位置。从左到右遍历 $s$ 的每个字符：设当前答案长度为 $\textit{cur}$，若该字符已出现过，且 $\textit{cur}$ 与其上一次出现位置之差不超过 $k$，则跳过该字符；否则将该字符加入答案，并更新哈希表中的位置。

按题意每次合并总是删除右侧字符，因此答案中每个字符的位置即为当前字符串中的下标。上述贪心过程与反复执行合并操作得到的结果等价。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串的长度，而 $|\Sigma|$ 是字符集的大小。本题中字符集为小写英文字母，因此 $|\Sigma|$ 是常数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mergeCharacters(self, s: str, k: int) -> str:
        last = {}
        ans = []
        for c in s:
            cur = len(ans)
            if c in last and cur - last[c] <= k:
                continue
            ans.append(c)
            last[c] = cur
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String mergeCharacters(String s, int k) {
        Map<Character, Integer> last = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int cur = ans.length();
            if (last.containsKey(c) && cur - last.get(c) <= k) {
                continue;
            }
            ans.append(c);
            last.put(c, cur);
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string mergeCharacters(string s, int k) {
        unordered_map<char, int> last;
        string ans;
        for (char c : s) {
            int cur = ans.size();
            if (last.count(c) && cur - last[c] <= k) {
                continue;
            }
            ans += c;
            last[c] = cur;
        }
        return ans;
    }
};
```

#### Go

```go
func mergeCharacters(s string, k int) string {
	last := make(map[byte]int)
	var ans []byte
	for i := 0; i < len(s); i++ {
		c := s[i]
		cur := len(ans)
		if lastIdx, ok := last[c]; ok && cur-lastIdx <= k {
			continue
		}
		ans = append(ans, c)
		last[c] = cur
	}
	return string(ans)
}
```

#### TypeScript

```ts
function mergeCharacters(s: string, k: number): string {
    const last = new Map<string, number>();
    const ans: string[] = [];
    for (const c of s) {
        const cur = ans.length;
        if (last.has(c) && cur - last.get(c)! <= k) {
            continue;
        }
        ans.push(c);
        last.set(c, cur);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
