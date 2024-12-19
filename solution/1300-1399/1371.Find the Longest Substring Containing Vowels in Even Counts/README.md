---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README.md
rating: 2040
source: 第 21 场双周赛 Q2
tags:
    - 位运算
    - 哈希表
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [1371. 每个元音包含偶数次的最长子字符串](https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts)

[English Version](/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即&nbsp;&#39;a&#39;，&#39;e&#39;，&#39;i&#39;，&#39;o&#39;，&#39;u&#39; ，在子字符串中都恰好出现了偶数次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;eleetminicoworoep&quot;
<strong>输出：</strong>13
<strong>解释：</strong>最长子字符串是 &quot;leetminicowor&quot; ，它包含 <strong>e，i，o</strong>&nbsp;各 2 个，以及 0 个 <strong>a</strong>，<strong>u </strong>。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;leetcodeisgreat&quot;
<strong>输出：</strong>5
<strong>解释：</strong>最长子字符串是 &quot;leetc&quot; ，其中包含 2 个 <strong>e</strong> 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;bcbcbc&quot;
<strong>输出：</strong>6
<strong>解释：</strong>这个示例中，字符串 &quot;bcbcbc&quot; 本身就是最长的，因为所有的元音 <strong>a，</strong><strong>e，</strong><strong>i，</strong><strong>o，</strong><strong>u</strong> 都出现了 0 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 x 10^5</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀异或 + 数组或哈希表

根据题目描述，如果我们用一个数字表示字符串 $\textit{s}$ 的某个前缀中每个元音字母出现的次数的奇偶性，那么当两个前缀的这个数字相同时，这两个前缀的交集就是一个符合条件的子字符串。

我们可以用一个二进制数的低五位分别表示五个元音字母的奇偶性，其中第 $i$ 位为 $1$ 表示该元音字母在子字符串中出现了奇数次，为 $0$ 表示该元音字母在子字符串中出现了偶数次。

我们用 $\textit{mask}$ 表示这个二进制数，用一个数组或哈希表 $\textit{d}$ 记录每个 $\textit{mask}$ 第一次出现的位置。初始时，我们将 $\textit{d}[0] = -1$，表示空字符串的开始位置为 $-1$。

遍历字符串 $\textit{s}$，如果遇到元音字母，就将 $\textit{mask}$ 的对应位取反。接下来，我们判断 $\textit{mask}$ 是否在之前出现过，如果出现过，那么我们就找到了一个符合条件的子字符串，其长度为当前位置减去 $\textit{mask}$ 上一次出现的位置。否则，我们将 $\textit{mask}$ 的当前位置存入 $\textit{d}$。

遍历结束后，我们就找到了最长的符合条件的子字符串。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{s}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        d = {0: -1}
        ans = mask = 0
        for i, c in enumerate(s):
            if c in "aeiou":
                mask ^= 1 << (ord(c) - ord("a"))
            if mask in d:
                j = d[mask]
                ans = max(ans, i - j)
            else:
                d[mask] = i
        return ans
```

#### Java

```java
class Solution {
    public int findTheLongestSubstring(String s) {
        String vowels = "aeiou";
        int[] d = new int[32];
        Arrays.fill(d, 1 << 29);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s.charAt(i - 1);
            for (int j = 0; j < 5; ++j) {
                if (c == vowels.charAt(j)) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = Math.max(ans, i - d[mask]);
            d[mask] = Math.min(d[mask], i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTheLongestSubstring(string s) {
        string vowels = "aeiou";
        vector<int> d(32, INT_MAX);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s[i - 1];
            for (int j = 0; j < 5; ++j) {
                if (c == vowels[j]) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = max(ans, i - d[mask]);
            d[mask] = min(d[mask], i);
        }
        return ans;
    }
};
```

#### Go

```go
func findTheLongestSubstring(s string) (ans int) {
    vowels := "aeiou"
    d := [32]int{}
    for i := range d {
        d[i] = 1 << 29
    }
    d[0] = 0
    mask := 0
    for i := 1; i <= len(s); i++ {
        c := s[i-1]
        for j := 0; j < 5; j++ {
            if c == vowels[j] {
                mask ^= 1 << j
                break
            }
        }
        ans = max(ans, i-d[mask])
        d[mask] = min(d[mask], i)
    }
    return
}
```

#### TypeScript

```ts
function findTheLongestSubstring(s: string): number {
    const vowels = 'aeiou';
    const d: number[] = Array(32).fill(1 << 29);
    d[0] = 0;
    let [ans, mask] = [0, 0];
    for (let i = 1; i <= s.length; i++) {
        const c = s[i - 1];
        for (let j = 0; j < 5; j++) {
            if (c === vowels[j]) {
                mask ^= 1 << j;
                break;
            }
        }
        ans = Math.max(ans, i - d[mask]);
        d[mask] = Math.min(d[mask], i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
