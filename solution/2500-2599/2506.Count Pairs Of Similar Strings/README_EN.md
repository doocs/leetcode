---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README_EN.md
rating: 1335
source: Weekly Contest 324 Q1
tags:
    - Bit Manipulation
    - Array
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [2506. Count Pairs Of Similar Strings](https://leetcode.com/problems/count-pairs-of-similar-strings)

[中文文档](/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string array <code>words</code>.</p>

<p>Two strings are <strong>similar</strong> if they consist of the same characters.</p>

<ul>
	<li>For example, <code>&quot;abca&quot;</code> and <code>&quot;cba&quot;</code> are similar since both consist of characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li>However, <code>&quot;abacba&quot;</code> and <code>&quot;bcfd&quot;</code> are not similar since they do not consist of the same characters.</li>
</ul>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> such that </em><code>0 &lt;= i &lt; j &lt;= word.length - 1</code><em> and the two strings </em><code>words[i]</code><em> and </em><code>words[j]</code><em> are similar</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aba&quot;,&quot;aabb&quot;,&quot;abcd&quot;,&quot;bac&quot;,&quot;aabc&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters &#39;a&#39; and &#39;b&#39;. 
- i = 3 and j = 4 : both words[3] and words[4] only consist of characters &#39;a&#39;, &#39;b&#39;, and &#39;c&#39;. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aabb&quot;,&quot;ab&quot;,&quot;ba&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters &#39;a&#39; and &#39;b&#39;. 
- i = 0 and j = 2 : both words[0] and words[2] only consist of characters &#39;a&#39; and &#39;b&#39;.
- i = 1 and j = 2 : both words[1] and words[2] only consist of characters &#39;a&#39; and &#39;b&#39;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;nba&quot;,&quot;cba&quot;,&quot;dba&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since there does not exist any pair that satisfies the conditions, we return 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Bit Manipulation

For each string, we can convert it into a binary number of length $26$, where the $i$-th bit being $1$ indicates that the string contains the $i$-th letter.

If two strings contain the same letters, their binary numbers are the same. Therefore, for each string, we use a hash table to count the occurrences of its binary number. Each time we add the count to the answer, then increment the count of its binary number by $1$.

The time complexity is $O(L)$, and the space complexity is $O(n)$. Here, $L$ is the total length of all strings, and $n$ is the number of strings.

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
            ans += cnt.merge(x, 1, Integer::sum) - 1;
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
