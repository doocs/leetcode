# [266. Palindrome Permutation 🔒](https://leetcode.com/problems/palindrome-permutation)

[中文文档](/solution/0200-0299/0266.Palindrome%20Permutation/README.md)

<!-- tags:Bit Manipulation,Hash Table,String -->

<!-- difficulty:Easy -->

## Description

<p>Given a string <code>s</code>, return <code>true</code> <em>if a permutation of the string could form a </em><span data-keyword="palindrome-string"><em><strong>palindrome</strong></em></span><em> and </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;code&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;carerac&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5000</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        return sum(v & 1 for v in Counter(s).values()) < 2
```

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int odd = 0;
        for (int x : cnt) {
            odd += x & 1;
        }
        return odd < 2;
    }
}
```

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> cnt(26);
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int odd = 0;
        for (int x : cnt) {
            odd += x & 1;
        }
        return odd < 2;
    }
};
```

```go
func canPermutePalindrome(s string) bool {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	odd := 0
	for _, x := range cnt {
		odd += x & 1
	}
	return odd < 2
}
```

```ts
function canPermutePalindrome(s: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    return cnt.filter(c => c % 2 === 1).length < 2;
}
```

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var canPermutePalindrome = function (s) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt() - 'a'.charCodeAt()];
    }
    return cnt.filter(c => c % 2 === 1).length < 2;
};
```

<!-- tabs:end -->

<!-- end -->
