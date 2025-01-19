---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1400.Construct%20K%20Palindrome%20Strings/README_EN.md
rating: 1530
source: Biweekly Contest 23 Q2
tags:
    - Greedy
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [1400. Construct K Palindrome Strings](https://leetcode.com/problems/construct-k-palindrome-strings)

[中文文档](/solution/1400-1499/1400.Construct%20K%20Palindrome%20Strings/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> and an integer <code>k</code>, return <code>true</code> if you can use all the characters in <code>s</code> to construct <strong>non-empty</strong> <code>k</code> <span data-keyword="palindrome-string">palindrome strings</span> or <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;annabelle&quot;, k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> You can construct two palindromes using all characters in s.
Some possible constructions &quot;anna&quot; + &quot;elble&quot;, &quot;anbna&quot; + &quot;elle&quot;, &quot;anellena&quot; + &quot;b&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to construct 3 palindromes using all the characters of s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;true&quot;, k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> The only possible solution is to put each character in a separate string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

First, we check if the length of string $s$ is less than $k$. If it is, we cannot construct $k$ palindrome strings, so we can directly return `false`.

Otherwise, we use a hash table or an array $cnt$ to count the occurrences of each character in string $s$. Finally, we only need to count the number of characters $x$ that appear an odd number of times in $cnt$. If $x$ is greater than $k$, we cannot construct $k$ palindrome strings, so we return `false`; otherwise, we return `true`.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Where $n$ is the length of string $s$, and $C$ is the size of the character set, here $C=26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canConstruct(self, s: str, k: int) -> bool:
        if len(s) < k:
            return False
        cnt = Counter(s)
        return sum(v & 1 for v in cnt.values()) <= k
```

#### Java

```java
class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canConstruct(string s, int k) {
        if (s.size() < k) {
            return false;
        }
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
};
```

#### Go

```go
func canConstruct(s string, k int) bool {
	if len(s) < k {
		return false
	}
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	x := 0
	for _, v := range cnt {
		x += v & 1
	}
	return x <= k
}
```

#### TypeScript

```ts
function canConstruct(s: string, k: number): boolean {
    if (s.length < k) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let x = 0;
    for (const v of cnt) {
        x += v & 1;
    }
    return x <= k;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
