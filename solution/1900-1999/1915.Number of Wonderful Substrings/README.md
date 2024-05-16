---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README.md
rating: 2234
source: 第 247 场周赛 Q3
tags:
    - 位运算
    - 哈希表
    - 字符串
    - 前缀和
---

# [1915. 最美子字符串的数目](https://leetcode.cn/problems/number-of-wonderful-substrings)

[English Version](/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果某个字符串中 <strong>至多一个</strong> 字母出现 <strong>奇数</strong> 次，则称其为 <strong>最美</strong> 字符串。</p>

<ul>
	<li>例如，<code>"ccjjc"</code> 和 <code>"abab"</code> 都是最美字符串，但 <code>"ab"</code> 不是。</li>
</ul>

<p>给你一个字符串 <code>word</code> ，该字符串由前十个小写英文字母组成（<code>'a'</code> 到 <code>'j'</code>）。请你返回 <code>word</code> 中 <strong>最美非空子字符串</strong> 的数目<em>。</em>如果同样的子字符串在<em> </em><code>word</code> 中出现多次，那么应当对 <strong>每次出现</strong> 分别计数<em>。</em></p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "aba"
<strong>输出：</strong>4
<strong>解释：</strong>4 个最美子字符串如下所示：
- "<strong>a</strong>ba" -> "a"
- "a<strong>b</strong>a" -> "b"
- "ab<strong>a</strong>" -> "a"
- "<strong>aba</strong>" -> "aba"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "aabb"
<strong>输出：</strong>9
<strong>解释：</strong>9 个最美子字符串如下所示：
- "<strong>a</strong>abb" -> "a"
- "<strong>aa</strong>bb" -> "aa"
- "<strong>aab</strong>b" -> "aab"
- "<strong>aabb</strong>" -> "aabb"
- "a<strong>a</strong>bb" -> "a"
- "a<strong>abb</strong>" -> "abb"
- "aa<strong>b</strong>b" -> "b"
- "aa<strong>bb</strong>" -> "bb"
- "aab<strong>b</strong>" -> "b"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "he"
<strong>输出：</strong>2
<strong>解释：</strong>2 个最美子字符串如下所示：
- "<b>h</b>e" -> "h"
- "h<strong>e</strong>" -> "e"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 10<sup>5</sup></code></li>
	<li><code>word</code> 由从 <code>'a'</code> 到 <code>'j'</code> 的小写英文字母组成</li>
</ul>

## 解法

### 方法一：前缀异或 + 计数

由于字符串中只包含 $10$ 个小写字母，因此可以用一个长度为 $10$ 的二进制数表示字符串中每个字母的奇偶性，其中第 $i$ 位为 $1$ 表示第 $i$ 个字母出现了奇数次，为 $0$ 表示第 $i$ 个字母出现了偶数次。

我们遍历字符串的每个字符，用一个变量 $st$ 维护当前字符串的前缀异或值，用一个数组 $cnt$ 维护每个前缀异或值出现的次数，初始时 $st = 0$, $cnt[0] = 1$。

对于当前遍历到的字符，我们更新其前缀异或值。如果当前的前缀异或值出现了 $cnt[st]$ 次，也就意味着有 $cnt[st]$ 个子字符串满足所有字母的出现次数均为偶数，因此我们将答案增加 $cnt[st]$。此外，对于 $0 \le i < 10$，如果当前的前缀异或值 $st$ 的第 $i$ 位为 $1$，那么我们还可以找到一个字母出现了奇数次，我们将答案增加 $cnt[st \oplus (1 << i)]$。最后，我们将 $st$ 出现的次数增加 $1$。继续遍历下一个字符，直到遍历完整个字符串。

时间复杂度 $O(n \times \Sigma)$，空间复杂度 $O(2^{\Sigma})$，其中 $\Sigma = 10$，而 $n$ 为字符串的长度。

相似题目：

-   [1371. 每个元音包含偶数次的最长子字符串](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README.md)

<!-- tabs:start -->

```python
class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        cnt = Counter({0: 1})
        ans = st = 0
        for c in word:
            st ^= 1 << (ord(c) - ord("a"))
            ans += cnt[st]
            for i in range(10):
                ans += cnt[st ^ (1 << i)]
            cnt[st] += 1
        return ans
```

```java
class Solution {
    public long wonderfulSubstrings(String word) {
        int[] cnt = new int[1 << 10];
        cnt[0] = 1;
        long ans = 0;
        int st = 0;
        for (char c : word.toCharArray()) {
            st ^= 1 << (c - 'a');
            ans += cnt[st];
            for (int i = 0; i < 10; ++i) {
                ans += cnt[st ^ (1 << i)];
            }
            ++cnt[st];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long wonderfulSubstrings(string word) {
        int cnt[1024] = {1};
        long long ans = 0;
        int st = 0;
        for (char c : word) {
            st ^= 1 << (c - 'a');
            ans += cnt[st];
            for (int i = 0; i < 10; ++i) {
                ans += cnt[st ^ (1 << i)];
            }
            ++cnt[st];
        }
        return ans;
    }
};
```

```go
func wonderfulSubstrings(word string) (ans int64) {
	cnt := [1024]int{1}
	st := 0
	for _, c := range word {
		st ^= 1 << (c - 'a')
		ans += int64(cnt[st])
		for i := 0; i < 10; i++ {
			ans += int64(cnt[st^(1<<i)])
		}
		cnt[st]++
	}
	return
}
```

```ts
function wonderfulSubstrings(word: string): number {
    const cnt: number[] = new Array(1 << 10).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let st = 0;
    for (const c of word) {
        st ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        ans += cnt[st];
        for (let i = 0; i < 10; ++i) {
            ans += cnt[st ^ (1 << i)];
        }
        cnt[st]++;
    }
    return ans;
}
```

```js
/**
 * @param {string} word
 * @return {number}
 */
var wonderfulSubstrings = function (word) {
    const cnt = new Array(1024).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let st = 0;
    for (const c of word) {
        st ^= 1 << (c.charCodeAt() - 'a'.charCodeAt());
        ans += cnt[st];
        for (let i = 0; i < 10; ++i) {
            ans += cnt[st ^ (1 << i)];
        }
        cnt[st]++;
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
