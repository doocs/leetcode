---
comments: true
difficulty: 中等
rating: 1847
source: 第 416 场周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [3297. 统计重新排列后包含另一个字符串的子字符串数目 I](https://leetcode.cn/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i)

[English Version](/solution/3200-3299/3297.Count%20Substrings%20That%20Can%20Be%20Rearranged%20to%20Contain%20a%20String%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串&nbsp;<code>word1</code> 和&nbsp;<code>word2</code>&nbsp;。</p>

<p>如果一个字符串 <code>x</code>&nbsp;重新排列后，<code>word2</code>&nbsp;是重排字符串的&nbsp;<span data-keyword="string-prefix">前缀</span>&nbsp;，那么我们称字符串&nbsp;<code>x</code>&nbsp;是&nbsp;<strong>合法的</strong>&nbsp;。</p>

<p>请你返回 <code>word1</code>&nbsp;中 <strong>合法</strong>&nbsp;<span data-keyword="substring-nonempty">子字符串</span>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>word1 = "bcca", word2 = "abc"</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>唯一合法的子字符串是&nbsp;<code>"bcca"</code>&nbsp;，可以重新排列得到&nbsp;<code>"abcc"</code>&nbsp;，<code>"abc"</code>&nbsp;是它的前缀。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>word1 = "abcabc", word2 = "abc"</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p>除了长度为 1 和 2 的所有子字符串都是合法的。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>word1 = "abcabc", word2 = "aaabc"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>
</div>

<p>&nbsp;</p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>1 &lt;= word1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= word2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>word1</code> 和&nbsp;<code>word2</code>&nbsp;都只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

<!-- thinking:start -->

> **思考**
>
> 子串重排后能覆盖 $\textit{word2}$ 的全部字符，即子串是 $\textit{word2}$ 的超多重集合。枚举子串再比较计数在长串上过慢。覆盖关系对窗口单调：一旦满足，再加右端仍满足，缩左端会找到最短覆盖。
>
> 维护 $need$ 种尚未满足的字符。右端纳入使某字符刚好达标则 $need$ 减一；$need=0$ 时尽量右移左端。此时左端左侧每一个起点与当前右端都能覆盖，答案加上左端下标。一次滑窗。

<!-- thinking:end -->

题目实际上是求在 $\textit{word1}$ 中，有多少个子串包含了 $\textit{word2}$ 中的所有字符。我们可以使用滑动窗口来处理。

首先，如果 $\textit{word1}$ 的长度小于 $\textit{word2}$ 的长度，那么 $\textit{word1}$ 中不可能包含 $\textit{word2}$ 的所有字符，直接返回 $0$。

接下来，我们用一个哈希表或长度为 $26$ 的数组 $\textit{cnt}$ 来统计 $\textit{word2}$ 中的字符出现的次数。然后，我们用 $\textit{need}$ 来记录还需要多少个字符才能满足条件，初始化为 $\textit{cnt}$ 的长度。

接着，我们用一个滑动窗口 $\textit{win}$ 来记录当前窗口中的字符出现的次数。我们用 $\textit{ans}$ 来记录满足条件的子串的个数，用 $\textit{l}$ 来记录窗口的左边界。

遍历 $\textit{word1}$ 中的每个字符，对于当前字符 $c$，我们将其加入到 $\textit{win}$ 中，如果 $\textit{win}[c]$ 的值等于 $\textit{cnt}[c]$，那么说明当前窗口中已经包含了 $\textit{word2}$ 中的所有字符之一，那么 $\textit{need}$ 减一。如果 $\textit{need}$ 等于 $0$，说明当前窗口中包含了 $\textit{word2}$ 中的所有字符，我们需要缩小窗口的左边界，直到 $\textit{need}$ 大于 $0$。具体地，如果 $\textit{win}[\textit{word1}[l]]$ 等于 $\textit{cnt}[\textit{word1}[l]]$，那么说明当前窗口中包含了 $\textit{word2}$ 中的所有字符之一，那么缩小窗口的左边界之后，就不满足条件了，所以 $\textit{need}$ 加一，同时 $\textit{win}[\textit{word1}[l]]$ 减一。然后，我们将 $\textit{l}$ 加一。此时窗口为 $[l, r]$，那么对于任意 $0 \leq l' \lt l$，$[l', r]$ 都是满足条件的子串，一共有 $l$ 个，我们累加到答案中。

遍历完 $\textit{word1}$ 中的所有字符之后，我们就得到了答案。

时间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是 $\textit{word1}$ 和 $\textit{word2}$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这里是小写字母集合，所以空间复杂度是常数级别的。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validSubstringCount(self, word1: str, word2: str) -> int:
        if len(word1) < len(word2):
            return 0
        cnt = Counter(word2)
        need = len(cnt)
        ans = l = 0
        win = Counter()
        for c in word1:
            win[c] += 1
            if win[c] == cnt[c]:
                need -= 1
            while need == 0:
                if win[word1[l]] == cnt[word1[l]]:
                    need += 1
                win[word1[l]] -= 1
                l += 1
            ans += l
        return ans
```

#### Java

```java
class Solution {
    public long validSubstringCount(String word1, String word2) {
        if (word1.length() < word2.length()) {
            return 0;
        }
        int[] cnt = new int[26];
        int need = 0;
        for (int i = 0; i < word2.length(); ++i) {
            if (++cnt[word2.charAt(i) - 'a'] == 1) {
                ++need;
            }
        }
        long ans = 0;
        int[] win = new int[26];
        for (int l = 0, r = 0; r < word1.length(); ++r) {
            int c = word1.charAt(r) - 'a';
            if (++win[c] == cnt[c]) {
                --need;
            }
            while (need == 0) {
                c = word1.charAt(l) - 'a';
                if (win[c] == cnt[c]) {
                    ++need;
                }
                --win[c];
                ++l;
            }
            ans += l;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long validSubstringCount(string word1, string word2) {
        if (word1.size() < word2.size()) {
            return 0;
        }
        int cnt[26]{};
        int need = 0;
        for (char& c : word2) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        long long ans = 0;
        int win[26]{};
        int l = 0;
        for (char& c : word1) {
            int i = c - 'a';
            if (++win[i] == cnt[i]) {
                --need;
            }
            while (need == 0) {
                i = word1[l] - 'a';
                if (win[i] == cnt[i]) {
                    ++need;
                }
                --win[i];
                ++l;
            }
            ans += l;
        }
        return ans;
    }
};
```

#### Go

```go
func validSubstringCount(word1 string, word2 string) (ans int64) {
	if len(word1) < len(word2) {
		return 0
	}
	cnt := [26]int{}
	need := 0
	for _, c := range word2 {
		cnt[c-'a']++
		if cnt[c-'a'] == 1 {
			need++
		}
	}
	win := [26]int{}
	l := 0
	for _, c := range word1 {
		i := int(c - 'a')
		win[i]++
		if win[i] == cnt[i] {
			need--
		}
		for need == 0 {
			i = int(word1[l] - 'a')
			if win[i] == cnt[i] {
				need++
			}
			win[i]--
			l++
		}
		ans += int64(l)
	}
	return
}
```

#### TypeScript

```ts
function validSubstringCount(word1: string, word2: string): number {
    if (word1.length < word2.length) {
        return 0;
    }
    const cnt: number[] = Array(26).fill(0);
    let need: number = 0;
    for (const c of word2) {
        if (++cnt[c.charCodeAt(0) - 97] === 1) {
            ++need;
        }
    }
    const win: number[] = Array(26).fill(0);
    let [ans, l] = [0, 0];
    for (const c of word1) {
        const i = c.charCodeAt(0) - 97;
        if (++win[i] === cnt[i]) {
            --need;
        }
        while (need === 0) {
            const j = word1[l].charCodeAt(0) - 97;
            if (win[j] === cnt[j]) {
                ++need;
            }
            --win[j];
            ++l;
        }
        ans += l;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
