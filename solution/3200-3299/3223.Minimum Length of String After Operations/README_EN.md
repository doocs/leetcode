---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3223.Minimum%20Length%20of%20String%20After%20Operations/README_EN.md
rating: 1445
source: Biweekly Contest 135 Q2
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3223. Minimum Length of String After Operations](https://leetcode.com/problems/minimum-length-of-string-after-operations)

[中文文档](/solution/3200-3299/3223.Minimum%20Length%20of%20String%20After%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>.</p>

<p>You can perform the following process on <code>s</code> <strong>any</strong> number of times:</p>

<ul>
	<li>Choose an index <code>i</code> in the string such that there is <strong>at least</strong> one character to the left of index <code>i</code> that is equal to <code>s[i]</code>, and <strong>at least</strong> one character to the right that is also equal to <code>s[i]</code>.</li>
	<li>Delete the <strong>closest</strong> character to the <strong>left</strong> of index <code>i</code> that is equal to <code>s[i]</code>.</li>
	<li>Delete the <strong>closest</strong> character to the <strong>right</strong> of index <code>i</code> that is equal to <code>s[i]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> length of the final string <code>s</code> that you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abaacbcbb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong><br />
We do the following operations:</p>

<ul>
	<li>Choose index 2, then remove the characters at indices 0 and 3. The resulting string is <code>s = &quot;bacbcbb&quot;</code>.</li>
	<li>Choose index 3, then remove the characters at indices 0 and 5. The resulting string is <code>s = &quot;acbcb&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
We cannot perform any operations, so we return the length of the original string.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can count the occurrences of each character in the string, and then iterate through the count array. If a character appears an odd number of times, then $1$ of that character remains in the end; if a character appears an even number of times, then $2$ of that character remain. We can sum the remaining counts of all characters to get the final length of the string.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $|\Sigma|$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumLength(self, s: str) -> int:
        cnt = Counter(s)
        return sum(1 if x & 1 else 2 for x in cnt.values())
```

#### Java

```java
class Solution {
    public int minimumLength(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int ans = 0;
        for (int x : cnt) {
            if (x > 0) {
                ans += x % 2 == 1 ? 1 : 2;
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
    int minimumLength(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int ans = 0;
        for (int x : cnt) {
            if (x) {
                ans += x % 2 ? 1 : 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumLength(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, x := range cnt {
		if x > 0 {
			if x&1 == 1 {
				ans += 1
			} else {
				ans += 2
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumLength(s: string): number {
    const cnt = new Map<string, number>();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    let ans = 0;
    for (const x of cnt.values()) {
        ans += x & 1 ? 1 : 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
