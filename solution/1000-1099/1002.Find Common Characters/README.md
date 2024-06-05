---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1002.Find%20Common%20Characters/README.md
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

给你一个字符串数组 <code>words</code> ，请你找出所有在 <code>words</code> 的每个字符串中都出现的共用字符（ <strong>包括重复字符</strong>），并以数组形式返回。你可以按 <strong>任意顺序</strong> 返回答案。

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

我们用一个长度为 $26$ 的数组 $cnt$ 记录每个字符在所有字符串中出现的最小次数，最后遍历 $cnt$ 数组，将出现次数大于 $0$ 的字符加入答案即可。

时间复杂度 $O(n \sum w_i)$，空间复杂度 $O(C)$。其中 $n$ 为字符串数组 $words$ 的长度，而 $w_i$ 为字符串数组 $words$ 中第 $i$ 个字符串的长度，另外 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        cnt = Counter(words[0])
        for w in words:
            ccnt = Counter(w)
            for c in cnt.keys():
                cnt[c] = min(cnt[c], ccnt[c])
        ans = []
        for c, v in cnt.items():
            ans.extend([c] * v)
        return ans
```

#### Java

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26];
        Arrays.fill(cnt, 10000);
        for (String w : words) {
            int[] ccnt = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++ccnt[w.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.min(cnt[i], ccnt[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
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
        int cnt[26];
        memset(cnt, 0x3f, sizeof(cnt));
        for (auto& w : words) {
            int ccnt[26]{};
            for (char& c : w) {
                ++ccnt[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = min(cnt[i], ccnt[i]);
            }
        }
        vector<string> ans;
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]--) {
                ans.emplace_back(1, i + 'a');
            }
        }
        return ans;
    }
};
```

#### Go

```go
func commonChars(words []string) (ans []string) {
	cnt := [26]int{}
	for i := range cnt {
		cnt[i] = 1 << 30
	}
	for _, w := range words {
		ccnt := [26]int{}
		for _, c := range w {
			ccnt[c-'a']++
		}
		for i, v := range cnt {
			cnt[i] = min(v, ccnt[i])
		}
	}
	for i, v := range cnt {
		for v > 0 {
			ans = append(ans, string(i+'a'))
			v--
		}
	}
	return
}
```

#### TypeScript

```ts
function commonChars(words: string[]): string[] {
    const freq: number[] = new Array(26).fill(Number.POSITIVE_INFINITY);
    const aCode = 'a'.charCodeAt(0);
    for (const word of words) {
        const t: number[] = new Array(26).fill(0);
        for (const c of word) {
            ++t[c.charCodeAt(0) - aCode];
        }
        for (let i = 0; i < 26; ++i) {
            freq[i] = Math.min(freq[i], t[i]);
        }
    }
    const res: string[] = [];
    for (let i = 0; i < 26; ++i) {
        if (freq[i]) {
            res.push(...String.fromCharCode(i + aCode).repeat(freq[i]));
        }
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
