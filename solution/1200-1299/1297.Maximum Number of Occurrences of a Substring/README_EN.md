---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1297.Maximum%20Number%20of%20Occurrences%20of%20a%20Substring/README_EN.md
rating: 1748
source: Weekly Contest 168 Q3
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [1297. Maximum Number of Occurrences of a Substring](https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring)

[中文文档](/solution/1200-1299/1297.Maximum%20Number%20of%20Occurrences%20of%20a%20Substring/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return the maximum number of occurrences of <strong>any</strong> substring under the following rules:</p>

<ul>
	<li>The number of unique characters in the substring must be less than or equal to <code>maxLetters</code>.</li>
	<li>The substring size must be between <code>minSize</code> and <code>maxSize</code> inclusive.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababcaab&quot;, maxLetters = 2, minSize = 3, maxSize = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;aab&quot; has 2 occurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaa&quot;, maxLetters = 1, minSize = 3, maxSize = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;aaa&quot; occur 2 times in the string. It can overlap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maxLetters &lt;= 26</code></li>
	<li><code>1 &lt;= minSize &lt;= maxSize &lt;= min(26, s.length)</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

According to the problem description, if a long string meets the condition, then its substring of length $\textit{minSize}$ must also meet the condition. Therefore, we only need to enumerate all substrings of length $\textit{minSize}$ in $s$, then use a hash table to record the occurrence frequency of all substrings, and find the maximum frequency as the answer.

The time complexity is $O(n \times m)$, and the space complexity is $O(n \times m)$. Here, $n$ and $m$ are the lengths of the string $s$ and $\textit{minSize}$, respectively. In this problem, $m$ does not exceed $26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreq(self, s: str, maxLetters: int, minSize: int, maxSize: int) -> int:
        ans = 0
        cnt = Counter()
        for i in range(len(s) - minSize + 1):
            t = s[i : i + minSize]
            ss = set(t)
            if len(ss) <= maxLetters:
                cnt[t] += 1
                ans = max(ans, cnt[t])
        return ans
```

#### Java

```java
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        Map<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i < s.length() - minSize + 1; ++i) {
            String t = s.substring(i, i + minSize);
            Set<Character> ss = new HashSet<>();
            for (int j = 0; j < minSize; ++j) {
                ss.add(t.charAt(j));
            }
            if (ss.size() <= maxLetters) {
                cnt.put(t, cnt.getOrDefault(t, 0) + 1);
                ans = Math.max(ans, cnt.get(t));
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
    int maxFreq(string s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        unordered_map<string, int> cnt;
        for (int i = 0; i < s.size() - minSize + 1; ++i) {
            string t = s.substr(i, minSize);
            unordered_set<char> ss(t.begin(), t.end());
            if (ss.size() <= maxLetters) {
                ans = max(ans, ++cnt[t]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxFreq(s string, maxLetters int, minSize int, maxSize int) (ans int) {
	cnt := map[string]int{}
	for i := 0; i < len(s)-minSize+1; i++ {
		t := s[i : i+minSize]
		ss := map[rune]bool{}
		for _, c := range t {
			ss[c] = true
		}
		if len(ss) <= maxLetters {
			cnt[t]++
			if ans < cnt[t] {
				ans = cnt[t]
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function maxFreq(s: string, maxLetters: number, minSize: number, maxSize: number): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (let i = 0; i < s.length - minSize + 1; ++i) {
        const t = s.slice(i, i + minSize);
        const ss = new Set(t.split(''));
        if (ss.size <= maxLetters) {
            cnt.set(t, (cnt.get(t) || 0) + 1);
            ans = Math.max(ans, cnt.get(t)!);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
