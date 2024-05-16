---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1750.Minimum%20Length%20of%20String%20After%20Deleting%20Similar%20Ends/README_EN.md
rating: 1501
source: Biweekly Contest 45 Q3
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [1750. Minimum Length of String After Deleting Similar Ends](https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends)

[中文文档](/solution/1700-1799/1750.Minimum%20Length%20of%20String%20After%20Deleting%20Similar%20Ends/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> consisting only of characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>. You are asked to apply the following algorithm on the string any number of times:</p>

<ol>
	<li>Pick a <strong>non-empty</strong> prefix from the string <code>s</code> where all the characters in the prefix are equal.</li>
	<li>Pick a <strong>non-empty</strong> suffix from the string <code>s</code> where all the characters in this suffix are equal.</li>
	<li>The prefix and the suffix should not intersect at any index.</li>
	<li>The characters from the prefix and suffix must be the same.</li>
	<li>Delete both the prefix and the suffix.</li>
</ol>

<p>Return <em>the <strong>minimum length</strong> of </em><code>s</code> <em>after performing the above operation any number of times (possibly zero times)</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ca&quot;
<strong>Output:</strong> 2
<strong>Explanation: </strong>You can&#39;t remove any characters, so the string stays as is.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cabaabac&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> An optimal sequence of operations is:
- Take prefix = &quot;c&quot; and suffix = &quot;c&quot; and remove them, s = &quot;abaaba&quot;.
- Take prefix = &quot;a&quot; and suffix = &quot;a&quot; and remove them, s = &quot;baab&quot;.
- Take prefix = &quot;b&quot; and suffix = &quot;b&quot; and remove them, s = &quot;aa&quot;.
- Take prefix = &quot;a&quot; and suffix = &quot;a&quot; and remove them, s = &quot;&quot;.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabccabba&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> An optimal sequence of operations is:
- Take prefix = &quot;aa&quot; and suffix = &quot;a&quot; and remove them, s = &quot;bccabb&quot;.
- Take prefix = &quot;b&quot; and suffix = &quot;bb&quot; and remove them, s = &quot;cca&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> only consists of characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two pointers

We define two pointers $i$ and $j$ to point to the head and tail of the string $s$ respectively, then move them to the middle until the characters pointed to by $i$ and $j$ are not equal, then $\max(0, j - i + 1)$ is the answer.

The time complexity is $O(n)$ and the space complexity is $O(1)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def minimumLength(self, s: str) -> int:
        i, j = 0, len(s) - 1
        while i < j and s[i] == s[j]:
            while i + 1 < j and s[i] == s[i + 1]:
                i += 1
            while i < j - 1 and s[j - 1] == s[j]:
                j -= 1
            i, j = i + 1, j - 1
        return max(0, j - i + 1)
```

```java
class Solution {
    public int minimumLength(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            while (i + 1 < j && s.charAt(i) == s.charAt(i + 1)) {
                ++i;
            }
            while (i < j - 1 && s.charAt(j) == s.charAt(j - 1)) {
                --j;
            }
            ++i;
            --j;
        }
        return Math.max(0, j - i + 1);
    }
}
```

```cpp
class Solution {
public:
    int minimumLength(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j && s[i] == s[j]) {
            while (i + 1 < j && s[i] == s[i + 1]) {
                ++i;
            }
            while (i < j - 1 && s[j] == s[j - 1]) {
                --j;
            }
            ++i;
            --j;
        }
        return max(0, j - i + 1);
    }
};
```

```go
func minimumLength(s string) int {
	i, j := 0, len(s)-1
	for i < j && s[i] == s[j] {
		for i+1 < j && s[i] == s[i+1] {
			i++
		}
		for i < j-1 && s[j] == s[j-1] {
			j--
		}
		i, j = i+1, j-1
	}
	return max(0, j-i+1)
}
```

```ts
function minimumLength(s: string): number {
    let i = 0;
    let j = s.length - 1;
    while (i < j && s[i] === s[j]) {
        while (i + 1 < j && s[i + 1] === s[i]) {
            ++i;
        }
        while (i < j - 1 && s[j - 1] === s[j]) {
            --j;
        }
        ++i;
        --j;
    }
    return Math.max(0, j - i + 1);
}
```

```rust
impl Solution {
    pub fn minimum_length(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut start = 0;
        let mut end = n - 1;
        while start < end && s[start] == s[end] {
            while start + 1 < end && s[start] == s[start + 1] {
                start += 1;
            }
            while start < end - 1 && s[end] == s[end - 1] {
                end -= 1;
            }
            start += 1;
            end -= 1;
        }
        (0).max(end - start + 1) as i32
    }
}
```

```c
int minimumLength(char* s) {
    int n = strlen(s);
    int start = 0;
    int end = n - 1;
    while (start < end && s[start] == s[end]) {
        while (start + 1 < end && s[start] == s[start + 1]) {
            start++;
        }
        while (start < end - 1 && s[end] == s[end - 1]) {
            end--;
        }
        start++;
        end--;
    }
    if (start > end) {
        return 0;
    }
    return end - start + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
