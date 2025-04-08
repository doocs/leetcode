---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0916.Word%20Subsets/README.md
tags:
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [916. 单词子集](https://leetcode.cn/problems/word-subsets)

[English Version](/solution/0900-0999/0916.Word%20Subsets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串数组 <code>words1</code>&nbsp;和&nbsp;<code>words2</code>。</p>

<p>现在，如果&nbsp;<code>b</code> 中的每个字母都出现在 <code>a</code> 中，<strong>包括重复出现的字母</strong>，那么称字符串 <code>b</code> 是字符串 <code>a</code> 的 <strong>子集</strong> 。</p>

<ul>
	<li>例如，<code>"wrr"</code> 是 <code>"warrior"</code> 的子集，但不是 <code>"world"</code> 的子集。</li>
</ul>

<p>如果对 <code>words2</code> 中的每一个单词&nbsp;<code>b</code>，<code>b</code> 都是 <code>a</code> 的子集，那么我们称&nbsp;<code>words1</code> 中的单词 <code>a</code> 是<em> </em><strong>通用单词</strong><em> </em>。</p>

<p>以数组形式返回&nbsp;<code>words1</code> 中所有的 <strong>通用</strong> 单词。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]</span></p>

<p><span class="example-io"><b>输出：</b>["facebook","google","leetcode"]</span></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lc","eo"]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">["leetcode"]</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc","b"]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">["cccbb"]</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words1.length, words2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words1[i].length, words2[i].length &lt;= 10</code></li>
	<li><code>words1[i]</code> 和 <code>words2[i]</code> 仅由小写英文字母组成</li>
	<li><code>words1</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

遍历 `words2` 中的每个单词 `b`，统计每个字母出现的最大次数，记为 `cnt`。

然后遍历 `words1` 中的每个单词 `a`，统计每个字母出现的次数，记为 `t`。如果 `cnt` 中的每个字母的出现次数都不大于 `t` 中的出现次数，则 `a` 是通用单词，将其加入答案。

时间复杂度 $O(L)$，其中 $L$ 为 `words1` 和 `words2` 中所有单词的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordSubsets(self, words1: List[str], words2: List[str]) -> List[str]:
        cnt = Counter()
        for b in words2:
            t = Counter(b)
            for c, v in t.items():
                cnt[c] = max(cnt[c], v)
        ans = []
        for a in words1:
            t = Counter(a)
            if all(v <= t[c] for c, v in cnt.items()):
                ans.append(a)
        return ans
```

#### Java

```java
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (var b : words2) {
            int[] t = new int[26];
            for (int i = 0; i < b.length(); ++i) {
                t[b.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.max(cnt[i], t[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (var a : words1) {
            int[] t = new int[26];
            for (int i = 0; i < a.length(); ++i) {
                t[a.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(a);
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
    vector<string> wordSubsets(vector<string>& words1, vector<string>& words2) {
        int cnt[26] = {0};
        int t[26];
        for (auto& b : words2) {
            memset(t, 0, sizeof t);
            for (auto& c : b) {
                t[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = max(cnt[i], t[i]);
            }
        }
        vector<string> ans;
        for (auto& a : words1) {
            memset(t, 0, sizeof t);
            for (auto& c : a) {
                t[c - 'a']++;
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.emplace_back(a);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func wordSubsets(words1 []string, words2 []string) (ans []string) {
	cnt := [26]int{}
	for _, b := range words2 {
		t := [26]int{}
		for _, c := range b {
			t[c-'a']++
		}
		for i := range cnt {
			cnt[i] = max(cnt[i], t[i])
		}
	}
	for _, a := range words1 {
		t := [26]int{}
		for _, c := range a {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if v > t[i] {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, a)
		}
	}
	return
}
```

#### TypeScript

```ts
function wordSubsets(words1: string[], words2: string[]): string[] {
    const cnt: number[] = Array(26).fill(0);
    for (const b of words2) {
        const t: number[] = Array(26).fill(0);
        for (const c of b) {
            t[c.charCodeAt(0) - 97]++;
        }
        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.max(cnt[i], t[i]);
        }
    }

    const ans: string[] = [];
    for (const a of words1) {
        const t: number[] = Array(26).fill(0);
        for (const c of a) {
            t[c.charCodeAt(0) - 97]++;
        }

        let ok = true;
        for (let i = 0; i < 26; i++) {
            if (cnt[i] > t[i]) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans.push(a);
        }
    }

    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string[]} words1
 * @param {string[]} words2
 * @return {string[]}
 */
var wordSubsets = function (words1, words2) {
    const cnt = Array(26).fill(0);

    for (const b of words2) {
        const t = Array(26).fill(0);

        for (const c of b) {
            t[c.charCodeAt(0) - 97]++;
        }

        for (let i = 0; i < 26; i++) {
            cnt[i] = Math.max(cnt[i], t[i]);
        }
    }

    const ans = [];

    for (const a of words1) {
        const t = Array(26).fill(0);

        for (const c of a) {
            t[c.charCodeAt(0) - 97]++;
        }

        let ok = true;
        for (let i = 0; i < 26; i++) {
            if (cnt[i] > t[i]) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans.push(a);
        }
    }

    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
