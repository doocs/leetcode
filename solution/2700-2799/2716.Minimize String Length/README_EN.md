---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2716.Minimize%20String%20Length/README_EN.md
rating: 1242
tags:
    - Hash Table
    - String
---

# [2716. Minimize String Length](https://leetcode.com/problems/minimize-string-length)

[中文文档](/solution/2700-2799/2716.Minimize%20String%20Length/README.md)

## Description

<p>Given a <strong>0-indexed</strong> string <code>s</code>, repeatedly perform the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose an index <code>i</code> in the string, and let <code>c</code> be the character in position <code>i</code>. <strong>Delete</strong> the <strong>closest occurrence</strong> of <code>c</code> to the <strong>left</strong> of <code>i</code> (if any) and the <strong>closest occurrence</strong> of <code>c</code> to the <strong>right</strong> of <code>i</code> (if any).</li>
</ul>

<p>Your task is to <strong>minimize</strong> the length of <code>s</code> by performing the above operation any number of times.</p>

<p>Return <em>an integer denoting the length of the <strong>minimized</strong> string.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, s is &quot;aaabc&quot;. We can start by selecting the character &#39;a&#39; at index 1. We then remove the closest &#39;a&#39; to the left of index 1, which is at index 0, and the closest &#39;a&#39; to the right of index 1, which is at index 2. After this operation, the string becomes &quot;abc&quot;. Any further operation we perform on the string will leave it unchanged. Therefore, the length of the minimized string is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> For this we can start with character &#39;b&#39; at index 1. There is no occurrence of &#39;b&#39; to the left of index 1, but there is one to the right at index 2, so we delete the &#39;b&#39; at index 2. The string becomes &quot;cbd&quot; and further operations will leave it unchanged. Hence, the minimized length is 3.&nbsp;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;dddaaa&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> For this, we can start with the character &#39;d&#39; at index 1. The closest occurrence of a &#39;d&#39; to its left is at index 0, and the closest occurrence of a &#39;d&#39; to its right is at index 2. We delete both index 0 and 2, so the string becomes &quot;daaa&quot;. In the new string, we can select the character &#39;a&#39; at index 2. The closest occurrence of an &#39;a&#39; to its left is at index 1, and the closest occurrence of an &#39;a&#39; to its right is at index 3. We delete both of them, and the string becomes &quot;da&quot;. We cannot minimize this further, so the minimized length is 2.
</pre>

<div class="notranslate" style="all: initial;">&nbsp;</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> contains only lowercase English letters</li>
</ul>

## Solutions

### Solution 1: Hash Table

The problem can actually be transformed into finding the number of different characters in the string. Therefore, we only need to count the number of different characters in the string.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string, and $C$ is the size of the character set. In this problem, the character set is lowercase English letters, so $C=26$.

<!-- tabs:start -->

```python
class Solution:
    def minimizedStringLength(self, s: str) -> int:
        return len(set(s))
```

```java
class Solution {
    public int minimizedStringLength(String s) {
        Set<Character> ss = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            ss.add(s.charAt(i));
        }
        return ss.size();
    }
}
```

```cpp
class Solution {
public:
    int minimizedStringLength(string s) {
        unordered_set<char> ss(s.begin(), s.end());
        return ss.size();
    }
};
```

```go
func minimizedStringLength(s string) int {
	ss := map[rune]struct{}{}
	for _, c := range s {
		ss[c] = struct{}{}
	}
	return len(ss)
}
```

```ts
function minimizedStringLength(s: string): number {
    return new Set(s.split('')).size;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut hash = HashMap::new();

        for c in s.chars() {
            hash.insert(c, true);
        }

        hash.len() as i32
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```rust
use std::collections::HashSet;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut set = HashSet::new();

        for c in s.chars() {
            set.insert(c);
        }

        set.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
