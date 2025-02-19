---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3460.Longest%20Common%20Prefix%20After%20at%20Most%20One%20Removal/README_EN.md
---

<!-- problem:start -->

# [3460. Longest Common Prefix After at Most One Removal 🔒](https://leetcode.com/problems/longest-common-prefix-after-at-most-one-removal)

[中文文档](/solution/3400-3499/3460.Longest%20Common%20Prefix%20After%20at%20Most%20One%20Removal/README.md)

## Description

<!-- description:start -->

None

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We record the lengths of the strings $s$ and $t$ as $n$ and $m$, respectively. Then, we use two pointers $i$ and $j$ to point to the beginning of the strings $s$ and $t$, and use a boolean variable $\textit{rem}$ to record whether a character has been removed.

Next, we start traversing the strings $s$ and $t$. If $s[i]$ is not equal to $t[j]$, we check if a character has already been removed. If a character has been removed, we exit the loop; otherwise, we mark that a character has been removed and skip $s[i]$. Otherwise, we skip both $s[i]$ and $t[j]$. Continue traversing until $i \geq n$ or $j \geq m$.

Finally, return $j$.

The time complexity is $O(n+m)$, where $n$ and $m$ are the lengths of the strings $s$ and $t$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonPrefix(self, s: str, t: str) -> int:
        n, m = len(s), len(t)
        i = j = 0
        rem = False
        while i < n and j < m:
            if s[i] != t[j]:
                if rem:
                    break
                rem = True
            else:
                j += 1
            i += 1
        return j
```

#### Java

```java
class Solution {
    public int longestCommonPrefix(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        boolean rem = false;
        while (i < n && j < m) {
            if (s.charAt(i) != t.charAt(j)) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestCommonPrefix(string s, string t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        bool rem = false;
        while (i < n && j < m) {
            if (s[i] != t[j]) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
};
```

#### Go

```go
func longestCommonPrefix(s string, t string) int {
	n, m := len(s), len(t)
	i, j := 0, 0
	rem := false
	for i < n && j < m {
		if s[i] != t[j] {
			if rem {
				break
			}
			rem = true
		} else {
			j++
		}
		i++
	}
	return j
}
```

#### TypeScript

```ts
function longestCommonPrefix(s: string, t: string): number {
    const [n, m] = [s.length, t.length];
    let [i, j] = [0, 0];
    let rem: boolean = false;
    while (i < n && j < m) {
        if (s[i] !== t[j]) {
            if (rem) {
                break;
            }
            rem = true;
        } else {
            ++j;
        }
        ++i;
    }
    return j;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_common_prefix(s: String, t: String) -> i32 {
        let (n, m) = (s.len(), t.len());
        let (mut i, mut j) = (0, 0);
        let mut rem = false;

        while i < n && j < m {
            if s.as_bytes()[i] != t.as_bytes()[j] {
                if rem {
                    break;
                }
                rem = true;
            } else {
                j += 1;
            }
            i += 1;
        }

        j as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var longestCommonPrefix = function (s, t) {
    const [n, m] = [s.length, t.length];
    let [i, j] = [0, 0];
    let rem = false;
    while (i < n && j < m) {
        if (s[i] !== t[j]) {
            if (rem) {
                break;
            }
            rem = true;
        } else {
            ++j;
        }
        ++i;
    }
    return j;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
