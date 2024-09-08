---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README.md
rating: 1335
source: 第 324 场周赛 Q1
tags:
    - 位运算
    - 数组
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2506. 统计相似字符串对的数目](https://leetcode.cn/problems/count-pairs-of-similar-strings)

[English Version](/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 。</p>

<p>如果两个字符串由相同的字符组成，则认为这两个字符串 <strong>相似</strong> 。</p>

<ul>
	<li>例如，<code>"abca"</code> 和 <code>"cba"</code> 相似，因为它们都由字符 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成。</li>
	<li>然而，<code>"abacba"</code> 和 <code>"bcfd"</code> 不相似，因为它们不是相同字符组成的。</li>
</ul>

<p>请你找出满足字符串&nbsp;<code>words[i]</code><em> </em>和<em> </em><code>words[j]</code> 相似的下标对<em> </em><code>(i, j)</code><em> </em>，并返回下标对的数目，其中 <code>0 &lt;= i &lt; j &lt;= words.length - 1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["aba","aabb","abcd","bac","aabc"]
<strong>输出：</strong>2
<strong>解释：</strong>共有 2 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 3 且 j = 4 ：words[3] 和 words[4] 只由字符 'a'、'b' 和 'c' 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["aabb","ab","ba"]
<strong>输出：</strong>3
<strong>解释：</strong>共有 3 对满足条件：
- i = 0 且 j = 1 ：words[0] 和 words[1] 只由字符 'a' 和 'b' 组成。 
- i = 0 且 j = 2 ：words[0] 和 words[2] 只由字符 'a' 和 'b' 组成。 
- i = 1 且 j = 2 ：words[1] 和 words[2] 只由字符 'a' 和 'b' 组成。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["nba","cba","dba"]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足条件的下标对，返回 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 位运算

对于每个字符串，我们可以将其转换为一个长度为 $26$ 的二进制数，其中第 $i$ 位为 $1$ 表示该字符串中包含第 $i$ 个字母。

如果两个字符串包含相同的字母，则它们的二进制数是相同的，因此，对于每个字符串，我们用哈希表统计其二进制数出现的次数，每一次累加到答案中，再将其二进制数出现的次数加 $1$。

时间复杂度 $O(L)$，空间复杂度 $O(n)$。其中 $L$ 是所有字符串的长度之和，而 $n$ 是字符串的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def similarPairs(self, words: List[str]) -> int:
        ans = 0
        cnt = Counter()
        for s in words:
            x = 0
            for c in map(ord, s):
                x |= 1 << (c - ord("a"))
            ans += cnt[x]
            cnt[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public int similarPairs(String[] words) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var s : words) {
            int x = 0;
            for (char c : s.toCharArray()) {
                x |= 1 << (c - 'a');
            }
            ans += cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int similarPairs(vector<string>& words) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (const auto& s : words) {
            int x = 0;
            for (auto& c : s) {
                x |= 1 << (c - 'a');
            }
            ans += cnt[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func similarPairs(words []string) (ans int) {
	cnt := map[int]int{}
	for _, s := range words {
		x := 0
		for _, c := range s {
			x |= 1 << (c - 'a')
		}
		ans += cnt[x]
		cnt[x]++
	}
	return
}
```

#### TypeScript

```ts
function similarPairs(words: string[]): number {
    let ans = 0;
    const cnt = new Map<number, number>();
    for (const s of words) {
        let x = 0;
        for (const c of s) {
            x |= 1 << (c.charCodeAt(0) - 97);
        }
        ans += cnt.get(x) || 0;
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn similar_pairs(words: Vec<String>) -> i32 {
        let mut ans = 0;
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        for s in words {
            let mut x = 0;
            for c in s.chars() {
                x |= 1 << ((c as u8) - b'a');
            }
            ans += cnt.get(&x).unwrap_or(&0);
            *cnt.entry(x).or_insert(0) += 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
