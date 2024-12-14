---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1897.Redistribute%20Characters%20to%20Make%20All%20Strings%20Equal/README.md
rating: 1309
source: 第 245 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1897. 重新分配字符使所有字符串都相等](https://leetcode.cn/problems/redistribute-characters-to-make-all-strings-equal)

[English Version](/solution/1800-1899/1897.Redistribute%20Characters%20to%20Make%20All%20Strings%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code>（下标 <strong>从 0 开始</strong> 计数）。</p>

<p>在一步操作中，需先选出两个 <strong>不同</strong> 下标 <code>i</code> 和 <code>j</code>，其中 <code>words[i]</code> 是一个非空字符串，接着将 <code>words[i]</code> 中的 <strong>任一</strong> 字符移动到 <code>words[j]</code> 中的 <strong>任一</strong> 位置上。</p>

<p>如果执行任意步操作可以使 <code>words</code> 中的每个字符串都相等，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["abc","aabc","bc"]
<strong>输出：</strong>true
<strong>解释：</strong>将 <code>words[1] 中的第一个</code> 'a' 移动到<code> words[2] 的最前面。
使 </code><code>words[1]</code> = "abc" 且 words[2] = "abc" 。
所有字符串都等于 "abc" ，所以返回 <code>true</code> 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["ab","a"]
<strong>输出：</strong>false
<strong>解释：</strong>执行操作无法使所有字符串都相等。
</pre>

<p> </p>

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

根据题目描述，只要每个字符的出现次数能被字符串数组的长度整除，就可以通过移动字符使所有字符串相等。

因此，我们用哈希表或者一个长度为 $26$ 的整数数组 $\textit{cnt}$ 统计每个字符出现的次数，最后判断是否每个字符的出现次数能被字符串数组的长度整除即可。

时间复杂度 $O(L)$，空间复杂度 $O(|\Sigma|)$。其中 $L$ 为数组 $\textit{words}$ 中所有字符串的长度之和，而 $\Sigma$ 为字符集，这里为小写字母集合，所以 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeEqual(self, words: List[str]) -> bool:
        cnt = Counter()
        for w in words:
            for c in w:
                cnt[c] += 1
        n = len(words)
        return all(v % n == 0 for v in cnt.values())
```

#### Java

```java
class Solution {
    public boolean makeEqual(String[] words) {
        int[] cnt = new int[26];
        for (var w : words) {
            for (char c : w.toCharArray()) {
                ++cnt[c - 'a'];
            }
        }
        int n = words.length;
        for (int v : cnt) {
            if (v % n != 0) {
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
    bool makeEqual(vector<string>& words) {
        int cnt[26]{};
        for (const auto& w : words) {
            for (const auto& c : w) {
                ++cnt[c - 'a'];
            }
        }
        int n = words.size();
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % n != 0) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func makeEqual(words []string) bool {
	cnt := [26]int{}
	for _, w := range words {
		for _, c := range w {
			cnt[c-'a']++
		}
	}
	n := len(words)
	for _, v := range cnt {
		if v%n != 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function makeEqual(words: string[]): boolean {
    const cnt: Record<string, number> = {};
    for (const w of words) {
        for (const c of w) {
            cnt[c] = (cnt[c] || 0) + 1;
        }
    }
    const n = words.length;
    return Object.values(cnt).every(v => v % n === 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn make_equal(words: Vec<String>) -> bool {
        let mut cnt = std::collections::HashMap::new();

        for word in words.iter() {
            for c in word.chars() {
                *cnt.entry(c).or_insert(0) += 1;
            }
        }

        let n = words.len();
        cnt.values().all(|&v| v % n == 0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
