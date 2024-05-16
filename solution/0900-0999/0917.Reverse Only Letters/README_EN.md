---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0917.Reverse%20Only%20Letters/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [917. Reverse Only Letters](https://leetcode.com/problems/reverse-only-letters)

[中文文档](/solution/0900-0999/0917.Reverse%20Only%20Letters/README.md)

## Description

<p>Given a string <code>s</code>, reverse the string according to the following rules:</p>

<ul>
	<li>All the characters that are not English letters remain in the same position.</li>
	<li>All the English letters (lowercase or uppercase) should be reversed.</li>
</ul>

<p>Return <code>s</code><em> after reversing it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "ab-cd"
<strong>Output:</strong> "dc-ba"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "a-bC-dEf-ghIj"
<strong>Output:</strong> "j-Ih-gfE-dCba"
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> s = "Test1ng-Leet=code-Q!"
<strong>Output:</strong> "Qedo1ct-eeLg=ntse-T!"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of characters with ASCII values in the range <code>[33, 122]</code>.</li>
	<li><code>s</code> does not contain <code>&#39;\&quot;&#39;</code> or <code>&#39;\\&#39;</code>.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers $i$ and $j$ to point to the head and tail of the string respectively. When $i < j$, we continuously move $i$ and $j$ until $i$ points to an English letter and $j$ points to an English letter, then we swap $s[i]$ and $s[j]$. Finally, we return the string.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        cs = list(s)
        i, j = 0, len(cs) - 1
        while i < j:
            while i < j and not cs[i].isalpha():
                i += 1
            while i < j and not cs[j].isalpha():
                j -= 1
            if i < j:
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j - 1
        return "".join(cs)
```

```java
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(cs[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(cs[j])) {
                --j;
            }
            if (i < j) {
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
                ++i;
                --j;
            }
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string reverseOnlyLetters(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalpha(s[i])) {
                ++i;
            }
            while (i < j && !isalpha(s[j])) {
                --j;
            }
            if (i < j) {
                swap(s[i++], s[j--]);
            }
        }
        return s;
    }
};
```

```go
func reverseOnlyLetters(s string) string {
	cs := []rune(s)
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !unicode.IsLetter(cs[i]) {
			i++
		}
		for i < j && !unicode.IsLetter(cs[j]) {
			j--
		}
		if i < j {
			cs[i], cs[j] = cs[j], cs[i]
			i++
			j--
		}
	}
	return string(cs)
}
```

```ts
function reverseOnlyLetters(s: string): string {
    const cs = [...s];
    let [i, j] = [0, cs.length - 1];
    while (i < j) {
        while (!/[a-zA-Z]/.test(cs[i]) && i < j) {
            i++;
        }
        while (!/[a-zA-Z]/.test(cs[j]) && i < j) {
            j--;
        }
        [cs[i], cs[j]] = [cs[j], cs[i]];
        i++;
        j--;
    }
    return cs.join('');
}
```

```rust
impl Solution {
    pub fn reverse_only_letters(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if !cs[l].is_ascii_alphabetic() {
                l += 1;
            } else if !cs[r].is_ascii_alphabetic() {
                r -= 1;
            } else {
                cs.swap(l, r);
                l += 1;
                r -= 1;
            }
        }
        cs.iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
