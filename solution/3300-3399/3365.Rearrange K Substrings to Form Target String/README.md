---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3365.Rearrange%20K%20Substrings%20to%20Form%20Target%20String/README.md
tags:
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [3365. 重排子字符串以形成目标字符串](https://leetcode.cn/problems/rearrange-k-substrings-to-form-target-string)

[English Version](/solution/3300-3399/3365.Rearrange%20K%20Substrings%20to%20Form%20Target%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code>（它们互为字母异位词），以及一个整数 <code>k</code>。</p>

<p>你的任务是判断是否可以将字符串 <code>s</code> 分割成 <code>k</code> 个等长的子字符串，然后重新排列这些子字符串，并以任意顺序连接它们，使得最终得到的新字符串与给定的字符串 <code>t</code> 相匹配。</p>

<p>如果可以做到，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p><strong>字母异位词&nbsp;</strong>是指由另一个单词或短语的所有字母重新排列形成的单词或短语，使用所有原始字母恰好一次。</p>

<p><strong>子字符串&nbsp;</strong>是字符串中的一个连续&nbsp;<b>非空&nbsp;</b>字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcd", t = "cdab", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>s</code> 分割成 2 个长度为 2 的子字符串：<code>["ab", "cd"]</code>。</li>
	<li>重新排列这些子字符串为 <code>["cd", "ab"]</code>，然后连接它们得到 <code>"cdab"</code>，与 <code>t</code> 相匹配。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aabbcc", t = "bbaacc", k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>s</code> 分割成 3 个长度为 2 的子字符串：<code>["aa", "bb", "cc"]</code>。</li>
	<li>重新排列这些子字符串为 <code>["bb", "aa", "cc"]</code>，然后连接它们得到 <code>"bbaacc"</code>，与 <code>t</code> 相匹配。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aabbcc", t = "bbaacc", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>s</code> 分割成 2 个长度为 3 的子字符串：<code>["aab", "bcc"]</code>。</li>
	<li>这些子字符串无法重新排列形成 <code>t = "bbaacc"</code>，所以输出 <code>false</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length == t.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s.length</code> 能被 <code>k</code> 整除。</li>
	<li><code>s</code> 和 <code>t</code> 仅由小写英文字母组成。</li>
	<li>输入保证 <code>s</code> 和 <code>t</code> 互为字母异位词。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们记字符串 $s$ 的长度为 $n$，那么每个子字符串的长度为 $m = n / k$。

用一个哈希表 $\textit{cnt}$ 记录每个长度为 $m$ 的子字符串在字符串 $s$ 中出现的次数与在字符串 $t$ 中出现的次数之差。

遍历字符串 $s$，每次取出长度为 $m$ 的子字符串，更新哈希表 $\textit{cnt}$。

最后判断哈希表 $\textit{cnt}$ 中的所有值是否都为 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleToRearrange(self, s: str, t: str, k: int) -> bool:
        cnt = Counter()
        n = len(s)
        m = n // k
        for i in range(0, n, m):
            cnt[s[i : i + m]] += 1
            cnt[t[i : i + m]] -= 1
        return all(v == 0 for v in cnt.values())
```

#### Java

```java
class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        Map<String, Integer> cnt = new HashMap<>(k);
        int n = s.length();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            cnt.merge(s.substring(i, i + m), 1, Integer::sum);
            cnt.merge(t.substring(i, i + m), -1, Integer::sum);
        }
        for (int v : cnt.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleToRearrange(string s, string t, int k) {
        unordered_map<string, int> cnt;
        int n = s.size();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            cnt[s.substr(i, m)]++;
            cnt[t.substr(i, m)]--;
        }
        for (auto& [_, v] : cnt) {
            if (v) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleToRearrange(s string, t string, k int) bool {
	n := len(s)
	m := n / k
	cnt := map[string]int{}
	for i := 0; i < n; i += m {
		cnt[s[i:i+m]]++
		cnt[t[i:i+m]]--
	}
	for _, v := range cnt {
		if v != 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isPossibleToRearrange(s: string, t: string, k: number): boolean {
    const cnt: Record<string, number> = {};
    const n = s.length;
    const m = Math.floor(n / k);
    for (let i = 0; i < n; i += m) {
        const a = s.slice(i, i + m);
        cnt[a] = (cnt[a] || 0) + 1;
        const b = t.slice(i, i + m);
        cnt[b] = (cnt[b] || 0) - 1;
    }
    return Object.values(cnt).every(x => x === 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
