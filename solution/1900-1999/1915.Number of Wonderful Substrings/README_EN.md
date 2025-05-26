---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README_EN.md
rating: 2234
source: Weekly Contest 247 Q3
tags:
    - Bit Manipulation
    - Hash Table
    - String
    - Prefix Sum
---

<!-- problem:start -->

# [1915. Number of Wonderful Substrings](https://leetcode.com/problems/number-of-wonderful-substrings)

[中文文档](/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README.md)

## Description

<!-- description:start -->

<p>A <strong>wonderful</strong> string is a string where <strong>at most one</strong> letter appears an <strong>odd</strong> number of times.</p>

<ul>

    <li>For example, <code>&quot;ccjjc&quot;</code> and <code>&quot;abab&quot;</code> are wonderful, but <code>&quot;ab&quot;</code> is not.</li>

</ul>

<p>Given a string <code>word</code> that consists of the first ten lowercase English letters (<code>&#39;a&#39;</code> through <code>&#39;j&#39;</code>), return <em>the <strong>number of wonderful non-empty substrings</strong> in </em><code>word</code><em>. If the same substring appears multiple times in </em><code>word</code><em>, then count <strong>each occurrence</strong> separately.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;aba&quot;

<strong>Output:</strong> 4

<strong>Explanation:</strong> The four wonderful substrings are underlined below:

- &quot;<u><strong>a</strong></u>ba&quot; -&gt; &quot;a&quot;

- &quot;a<u><strong>b</strong></u>a&quot; -&gt; &quot;b&quot;

- &quot;ab<u><strong>a</strong></u>&quot; -&gt; &quot;a&quot;

- &quot;<u><strong>aba</strong></u>&quot; -&gt; &quot;aba&quot;

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;aabb&quot;

<strong>Output:</strong> 9

<strong>Explanation:</strong> The nine wonderful substrings are underlined below:

- &quot;<strong><u>a</u></strong>abb&quot; -&gt; &quot;a&quot;

- &quot;<u><strong>aa</strong></u>bb&quot; -&gt; &quot;aa&quot;

- &quot;<u><strong>aab</strong></u>b&quot; -&gt; &quot;aab&quot;

- &quot;<u><strong>aabb</strong></u>&quot; -&gt; &quot;aabb&quot;

- &quot;a<u><strong>a</strong></u>bb&quot; -&gt; &quot;a&quot;

- &quot;a<u><strong>abb</strong></u>&quot; -&gt; &quot;abb&quot;

- &quot;aa<u><strong>b</strong></u>b&quot; -&gt; &quot;b&quot;

- &quot;aa<u><strong>bb</strong></u>&quot; -&gt; &quot;bb&quot;

- &quot;aab<u><strong>b</strong></u>&quot; -&gt; &quot;b&quot;

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;he&quot;

<strong>Output:</strong> 2

<strong>Explanation:</strong> The two wonderful substrings are underlined below:

- &quot;<b><u>h</u></b>e&quot; -&gt; &quot;h&quot;

- &quot;h<strong><u>e</u></strong>&quot; -&gt; &quot;e&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>

    <li><code>word</code> consists of lowercase English letters from <code>&#39;a&#39;</code>&nbsp;to <code>&#39;j&#39;</code>.</li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### JavaScript

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

<!-- solution:end -->

<!-- problem:end -->
