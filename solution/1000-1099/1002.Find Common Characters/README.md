---
comments: true
difficulty: 简单
rating: 1279
source: 第 126 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1002. 查找共用字符](https://leetcode.cn/problems/find-common-characters)

[English Version](/solution/1000-1099/1002.Find%20Common%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

给你一个字符串数组 <code>words</code> ，请你找出所有在 <code>words</code> 的每个字符串中都出现的共用字符（<strong>包括重复字符</strong>），并以数组形式返回。你可以按 <strong>任意顺序</strong> 返回答案。
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["bella","label","roller"]
<strong>输出：</strong>["e","l","l"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["cool","lock","cook"]
<strong>输出：</strong>["c","o"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

<!-- thinking:start -->

> **思考**
>
> 对每个字符扫描全部单词可以统计公共出现次数，$n$ 与串长均不超过 $100$，时间足够。重复扫描同一字符集会做许多无用比较。
>
> 一个字符能进入答案的次数，等于它在所有单词中出现次数的最小值，即各单词字母多重集合的交。
>
> 为此先统计第一个单词的频次，再与其余单词逐个取 $\min$，最后按频次展开即可，字符集大小为 $26$，额外空间为常数。

<!-- thinking:end -->

我们用一个长度为 $26$ 的数组 $cnt$ 记录每个字符在所有字符串中出现的最小次数，最后遍历 $cnt$ 数组，将出现次数大于 $0$ 的字符加入答案即可。

时间复杂度 $O(n \sum w_i)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 为字符串数组 $words$ 的长度，而 $w_i$ 为字符串数组 $words$ 中第 $i$ 个字符串的长度，另外 $|\Sigma|$ 为字符集的大小，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        cnt = Counter(words[0])
        for w in words:
            t = Counter(w)
            for c in cnt:
                cnt[c] = min(cnt[c], t[c])
        return list(cnt.elements())
```

#### Java

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26];
        Arrays.fill(cnt, 20000);
        for (var w : words) {
            int[] t = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++t[w.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.min(cnt[i], t[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            ans.addAll(Collections.nCopies(cnt[i], String.valueOf((char) ('a' + i))));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        vector<int> cnt(26, 20000);
        for (const auto& w : words) {
            vector<int> t(26, 0);
            for (char c : w) {
                ++t[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = min(cnt[i], t[i]);
            }
        }
        vector<string> ans;
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.push_back(string(1, 'a' + i));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func commonChars(words []string) (ans []string) {
	cnt := make([]int, 26)
	for i := range cnt {
		cnt[i] = 20000
	}
	for _, w := range words {
		t := make([]int, 26)
		for _, c := range w {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			cnt[i] = min(cnt[i], t[i])
		}
	}
	for i := 0; i < 26; i++ {
		for j := 0; j < cnt[i]; j++ {
			ans = append(ans, string('a'+rune(i)))
		}
	}
	return ans
}
```

#### TypeScript

```ts
function commonChars(words: string[]): string[] {
    const cnt = Array(26).fill(20000);
    const aCode = 'a'.charCodeAt(0);
    for (const w of words) {
        const t = Array(26).fill(0);
        for (const c of w) {
            t[c.charCodeAt(0) - aCode]++;
        }
        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.min(cnt[i], t[i]);
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < 26; i++) {
        cnt[i] && ans.push(...String.fromCharCode(i + aCode).repeat(cnt[i]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
