---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2273.Find%20Resultant%20Array%20After%20Removing%20Anagrams/README.md
rating: 1294
source: 第 293 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [2273. 移除字母异位词后的结果数组](https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams)

[English Version](/solution/2200-2299/2273.Find%20Resultant%20Array%20After%20Removing%20Anagrams/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组&nbsp;<code>words</code> ，其中 <code>words[i]</code> 由小写英文字符组成。</p>

<p>在一步操作中，需要选出任一下标 <code>i</code> ，从 <code>words</code> 中 <strong>删除</strong> <code>words[i]</code> 。其中下标 <code>i</code> 需要同时满足下述两个条件：</p>

<ol>
	<li><code>0 &lt; i &lt; words.length</code></li>
	<li><code>words[i - 1]</code> 和 <code>words[i]</code> 是 <strong>字母异位词</strong> 。</li>
</ol>

<p>只要可以选出满足条件的下标，就一直执行这个操作。</p>

<p>在执行所有操作后，返回 <code>words</code> 。可以证明，按任意顺序为每步操作选择下标都会得到相同的结果。</p>

<p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。例如，<code>"dacb"</code> 是 <code>"abdc"</code> 的一个字母异位词。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["abba","baba","bbaa","cd","cd"]
<strong>输出：</strong>["abba","cd"]
<strong>解释：</strong>
获取结果数组的方法之一是执行下述步骤：
- 由于 words[2] = "bbaa" 和 words[1] = "baba" 是字母异位词，选择下标 2 并删除 words[2] 。
  现在 words = ["abba","baba","cd","cd"] 。
- 由于 words[1] = "baba" 和 words[0] = "abba" 是字母异位词，选择下标 1 并删除 words[1] 。
  现在 words = ["abba","cd","cd"] 。
- 由于 words[2] = "cd" 和 words[1] = "cd" 是字母异位词，选择下标 2 并删除 words[2] 。
  现在 words = ["abba","cd"] 。
无法再执行任何操作，所以 ["abba","cd"] 是最终答案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a","b","c","d","e"]
<strong>输出：</strong>["a","b","c","d","e"]
<strong>解释：</strong>
words 中不存在互为字母异位词的两个相邻字符串，所以无需执行任何操作。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们首先将 $\textit{words}[0]$ 加入答案数组，然后从 $\textit{words}[1]$ 开始遍历，如果 $\textit{words}[i - 1]$ 和 $\textit{words}[i]$ 不是字母异位词，我们就将 $\textit{words}[i]$ 加入答案数组。

问题转换为判断两个字符串是否是字母异位词，我们定义一个辅助函数 $\textit{check}(s, t)$ 来实现这个功能。如果 $s$ 和 $t$ 不是字母异位词，我们就返回 $\text{true}$，否则返回 $\text{false}$。

在函数 $\textit{check}(s, t)$ 中，我们首先判断 $s$ 和 $t$ 的长度是否相等，如果不相等，我们就返回 $\text{true}$。否则，我们用一个长度为 $26$ 的数组 $\textit{cnt}$ 来统计 $s$ 中每个字符出现的次数，然后遍历 $t$ 中的每个字符，将 $\textit{cnt}[c]$ 减 $1$，如果 $\textit{cnt}[c]$ 小于 $0$，我们就返回 $\text{true}$。如果正常遍历完 $t$ 中的每个字符，说明 $s$ 和 $t$ 是字母异位词，我们返回 $\text{false}$。

时间复杂度 $O(L)$，空间复杂度 $O(|\Sigma|)$。其中 $L$ 是数组 $\textit{words}$ 的长度，而 $\Sigma$ 是字符集，这里是小写英文字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeAnagrams(self, words: List[str]) -> List[str]:
        def check(s: str, t: str) -> bool:
            if len(s) != len(t):
                return True
            cnt = Counter(s)
            for c in t:
                cnt[c] -= 1
                if cnt[c] < 0:
                    return True
            return False

        return [words[0]] + [t for s, t in pairwise(words) if check(s, t)]
```

#### Java

```java
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            if (check(words[i - 1], words[i])) {
                ans.add(words[i]);
            }
        }
        return ans;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return true;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < t.length(); ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        auto check = [](string& s, string& t) -> bool {
            if (s.size() != t.size()) {
                return true;
            }
            int cnt[26]{};
            for (char& c : s) {
                ++cnt[c - 'a'];
            }
            for (char& c : t) {
                if (--cnt[c - 'a'] < 0) {
                    return true;
                }
            }
            return false;
        };

        vector<string> ans = {words[0]};
        for (int i = 1; i < words.size(); ++i) {
            if (check(words[i - 1], words[i])) {
                ans.emplace_back(words[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func removeAnagrams(words []string) []string {
	ans := []string{words[0]}
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return true
		}
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, c := range t {
			cnt[c-'a']--
			if cnt[c-'a'] < 0 {
				return true
			}
		}
		return false
	}
	for i, t := range words[1:] {
		if check(words[i], t) {
			ans = append(ans, t)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function removeAnagrams(words: string[]): string[] {
    const ans: string[] = [words[0]];
    const check = (s: string, t: string): boolean => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt: number[] = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt(0) - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt(0) - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn remove_anagrams(words: Vec<String>) -> Vec<String> {
        fn check(s: &str, t: &str) -> bool {
            if s.len() != t.len() {
                return true;
            }
            let mut cnt = [0; 26];
            for c in s.bytes() {
                cnt[(c - b'a') as usize] += 1;
            }
            for c in t.bytes() {
                let idx = (c - b'a') as usize;
                cnt[idx] -= 1;
                if cnt[idx] < 0 {
                    return true;
                }
            }
            false
        }

        let mut ans = vec![words[0].clone()];
        for i in 1..words.len() {
            if check(&words[i - 1], &words[i]) {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} words
 * @return {string[]}
 */
var removeAnagrams = function (words) {
    const ans = [words[0]];
    const check = (s, t) => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt() - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt() - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
