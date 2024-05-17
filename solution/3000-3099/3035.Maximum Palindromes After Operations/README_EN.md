---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3035.Maximum%20Palindromes%20After%20Operations/README_EN.md
rating: 1856
source: Weekly Contest 384 Q3
tags:
    - Greedy
    - Array
    - Hash Table
    - String
    - Counting
    - Sorting
---

<!-- problem:start -->

# [3035. Maximum Palindromes After Operations](https://leetcode.com/problems/maximum-palindromes-after-operations)

[中文文档](/solution/3000-3099/3035.Maximum%20Palindromes%20After%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string array <code>words</code> having length <code>n</code> and containing <strong>0-indexed</strong> strings.</p>

<p>You are allowed to perform the following operation <strong>any</strong> number of times (<strong>including</strong> <strong>zero</strong>):</p>

<ul>
	<li>Choose integers <code>i</code>, <code>j</code>, <code>x</code>, and <code>y</code> such that <code>0 &lt;= i, j &lt; n</code>, <code>0 &lt;= x &lt; words[i].length</code>, <code>0 &lt;= y &lt; words[j].length</code>, and <strong>swap</strong> the characters <code>words[i][x]</code> and <code>words[j][y]</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>maximum</strong> number of <span data-keyword="palindrome-string">palindromes</span> </em><code>words</code><em> can contain, after performing some operations.</em></p>

<p><strong>Note:</strong> <code>i</code> and <code>j</code> may be equal during an operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abbb&quot;,&quot;ba&quot;,&quot;aa&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, one way to get the maximum number of palindromes is:
Choose i = 0, j = 1, x = 0, y = 0, so we swap words[0][0] and words[1][0]. words becomes [&quot;bbbb&quot;,&quot;aa&quot;,&quot;aa&quot;].
All strings in words are now palindromes.
Hence, the maximum number of palindromes achievable is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;ab&quot;]
<strong>Output:</strong> 2
<strong>Explanation: </strong>In this example, one way to get the maximum number of palindromes is: 
Choose i = 0, j = 1, x = 1, y = 0, so we swap words[0][1] and words[1][0]. words becomes [&quot;aac&quot;,&quot;bb&quot;].
Choose i = 0, j = 0, x = 1, y = 2, so we swap words[0][1] and words[0][2]. words becomes [&quot;aca&quot;,&quot;bb&quot;].
Both strings are now palindromes.
Hence, the maximum number of palindromes achievable is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cd&quot;,&quot;ef&quot;,&quot;a&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, there is no need to perform any operation.
There is one palindrome in words &quot;a&quot;.
It can be shown that it is not possible to get more than one palindrome after any number of operations.
Hence, the answer is 1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPalindromesAfterOperations(self, words: List[str]) -> int:
        s = mask = 0
        for w in words:
            s += len(w)
            for c in w:
                mask ^= 1 << (ord(c) - ord("a"))
        s -= mask.bit_count()
        words.sort(key=len)
        ans = 0
        for w in words:
            s -= len(w) // 2 * 2
            if s < 0:
                break
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int s = 0, mask = 0;
        for (var w : words) {
            s += w.length();
            for (var c : w.toCharArray()) {
                mask ^= 1 << (c - 'a');
            }
        }
        s -= Integer.bitCount(mask);
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int ans = 0;
        for (var w : words) {
            s -= w.length() / 2 * 2;
            if (s < 0) {
                break;
            }
            ++ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPalindromesAfterOperations(vector<string>& words) {
        int s = 0, mask = 0;
        for (const auto& w : words) {
            s += w.length();
            for (char c : w) {
                mask ^= 1 << (c - 'a');
            }
        }
        s -= __builtin_popcount(mask);
        sort(words.begin(), words.end(), [](const string& a, const string& b) { return a.length() < b.length(); });
        int ans = 0;
        for (const auto& w : words) {
            s -= w.length() / 2 * 2;
            if (s < 0) {
                break;
            }
            ++ans;
        }
        return ans;
    }
};
```

#### Go

```go
func maxPalindromesAfterOperations(words []string) (ans int) {
	var s, mask int
	for _, w := range words {
		s += len(w)
		for _, c := range w {
			mask ^= 1 << (c - 'a')
		}
	}
	s -= bits.OnesCount(uint(mask))
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
	for _, w := range words {
		s -= len(w) / 2 * 2
		if s < 0 {
			break
		}
		ans++
	}
	return
}
```

#### TypeScript

```ts
function maxPalindromesAfterOperations(words: string[]): number {
    let s: number = 0;
    let mask: number = 0;
    for (const w of words) {
        s += w.length;
        for (const c of w) {
            mask ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }
    s -= (mask.toString(2).match(/1/g) || []).length;
    words.sort((a, b) => a.length - b.length);
    let ans: number = 0;
    for (const w of words) {
        s -= Math.floor(w.length / 2) * 2;
        if (s < 0) {
            break;
        }
        ans++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
