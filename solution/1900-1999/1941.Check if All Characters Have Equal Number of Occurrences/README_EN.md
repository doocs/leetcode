---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README_EN.md
rating: 1242
source: Biweekly Contest 57 Q1
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [1941. Check if All Characters Have Equal Number of Occurrences](https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences)

[中文文档](/solution/1900-1999/1941.Check%20if%20All%20Characters%20Have%20Equal%20Number%20of%20Occurrences/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <code>true</code><em> if </em><code>s</code><em> is a <strong>good</strong> string, or </em><code>false</code><em> otherwise</em>.</p>

<p>A string <code>s</code> is <strong>good</strong> if <strong>all</strong> the characters that appear in <code>s</code> have the <strong>same</strong> number of occurrences (i.e., the same frequency).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacbc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The characters that appear in s are &#39;a&#39;, &#39;b&#39;, and &#39;c&#39;. All characters occur 2 times in s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabb&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The characters that appear in s are &#39;a&#39; and &#39;b&#39;.
&#39;a&#39; occurs 3 times while &#39;b&#39; occurs 2 times, which is not the same number of times.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We use a hash table or an array of length $26$ called $\textit{cnt}$ to record the number of occurrences of each character in the string $s$.

Next, we traverse each value in $\textit{cnt}$ and check if all non-zero values are equal.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string $s$, and $\Sigma$ is the size of the character set. In this problem, the character set consists of lowercase English letters, so $|\Sigma|=26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        return len(set(Counter(s).values())) == 1
```

#### Java

```java
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int v = 0;
        for (int x : cnt) {
            if (x == 0) {
                continue;
            }
            if (v > 0 && v != x) {
                return false;
            }
            v = x;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool areOccurrencesEqual(string s) {
        vector<int> cnt(26);
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int v = 0;
        for (int x : cnt) {
            if (x == 0) {
                continue;
            }
            if (v && v != x) {
                return false;
            }
            v = x;
        }
        return true;
    }
};
```

#### Go

```go
func areOccurrencesEqual(s string) bool {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	v := 0
	for _, x := range cnt {
		if x == 0 {
			continue
		}
		if v > 0 && v != x {
			return false
		}
		v = x
	}
	return true
}
```

#### TypeScript

```ts
function areOccurrencesEqual(s: string): boolean {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    const v = cnt.find(v => v);
    return cnt.every(x => !x || v === x);
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Boolean
     */
    function areOccurrencesEqual($s) {
        $cnt = array_fill(0, 26, 0);
        for ($i = 0; $i < strlen($s); $i++) {
            $cnt[ord($s[$i]) - ord('a')]++;
        }
        $v = 0;
        foreach ($cnt as $x) {
            if ($x == 0) {
                continue;
            }
            if ($v && $v != $x) {
                return false;
            }
            $v = $x;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
