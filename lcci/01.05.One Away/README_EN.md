---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.05.One%20Away/README_EN.md
---

<!-- problem:start -->

# [01.05. One Away](https://leetcode.cn/problems/one-away-lcci)

[中文文档](/lcci/01.05.One%20Away/README.md)

## Description

<!-- description:start -->

<p>There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.</p>

<p><strong>Example&nbsp;1:</strong></p>

<pre>

<strong>Input:</strong>

first = &quot;pale&quot;

second = &quot;ple&quot;

<strong>Output:</strong> True

</pre>

<p><strong>Example&nbsp;2:</strong></p>

<pre>

<strong>Input:</strong>

first = &quot;pales&quot;

second = &quot;pal&quot;

<strong>Output:</strong> False

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis + Two Pointers

Let the lengths of the strings $\textit{first}$ and $\textit{second}$ be $m$ and $n$, respectively. Assume $m \geq n$.

Next, we discuss the following cases:

-   When $m - n \gt 1$, $\textit{first}$ and $\textit{second}$ cannot be made equal with one edit, so return `false`;
-   When $m = n$, $\textit{first}$ and $\textit{second}$ can be made equal with one edit only if there is exactly one different character;
-   When $m - n = 1$, $\textit{first}$ and $\textit{second}$ can be made equal with one edit only if $\textit{second}$ is obtained by deleting one character from $\textit{first}$. We can use two pointers to achieve this.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        m, n = len(first), len(second)
        if m < n:
            return self.oneEditAway(second, first)
        if m - n > 1:
            return False
        if m == n:
            return sum(a != b for a, b in zip(first, second)) < 2
        i = j = cnt = 0
        while i < m:
            if j == n or (j < n and first[i] != second[j]):
                cnt += 1
            else:
                j += 1
            i += 1
        return cnt < 2
```

#### Java

```java
class Solution {
    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        if (m < n) {
            return oneEditAway(second, first);
        }
        if (m - n > 1) {
            return false;
        }
        int cnt = 0;
        if (m == n) {
            for (int i = 0; i < n; ++i) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (++cnt > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
        for (int i = 0, j = 0; i < m; ++i) {
            if (j == n || (j < n && first.charAt(i) != second.charAt(j))) {
                ++cnt;
            } else {
                ++j;
            }
        }
        return cnt < 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool oneEditAway(std::string first, std::string second) {
        int m = first.length(), n = second.length();
        if (m < n) {
            return oneEditAway(second, first);
        }
        if (m - n > 1) {
            return false;
        }
        int cnt = 0;
        if (m == n) {
            for (int i = 0; i < n; ++i) {
                if (first[i] != second[i]) {
                    if (++cnt > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
        for (int i = 0, j = 0; i < m; ++i) {
            if (j == n || (j < n && first[i] != second[j])) {
                ++cnt;
            } else {
                ++j;
            }
        }
        return cnt < 2;
    }
};
```

#### Go

```go
func oneEditAway(first string, second string) bool {
	m, n := len(first), len(second)
	if m < n {
		return oneEditAway(second, first)
	}
	if m-n > 1 {
		return false
	}
	cnt := 0
	if m == n {
		for i := 0; i < n; i++ {
			if first[i] != second[i] {
				if cnt++; cnt > 1 {
					return false
				}
			}
		}
		return true
	}
	for i, j := 0, 0; i < m; i++ {
		if j == n || (j < n && first[i] != second[j]) {
			cnt++
		} else {
			j++
		}
	}
	return cnt < 2
}
```

#### TypeScript

```ts
function oneEditAway(first: string, second: string): boolean {
    let m: number = first.length;
    let n: number = second.length;
    if (m < n) {
        return oneEditAway(second, first);
    }
    if (m - n > 1) {
        return false;
    }

    let cnt: number = 0;
    if (m === n) {
        for (let i: number = 0; i < n; ++i) {
            if (first[i] !== second[i]) {
                if (++cnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    for (let i: number = 0, j: number = 0; i < m; ++i) {
        if (j === n || (j < n && first[i] !== second[j])) {
            ++cnt;
        } else {
            ++j;
        }
    }
    return cnt < 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn one_edit_away(first: String, second: String) -> bool {
        let (f_len, s_len) = (first.len(), second.len());
        let (first, second) = (first.as_bytes(), second.as_bytes());
        let (mut i, mut j) = (0, 0);
        let mut count = 0;
        while i < f_len && j < s_len {
            if first[i] != second[j] {
                if count > 0 {
                    return false;
                }

                count += 1;
                if i + 1 < f_len && first[i + 1] == second[j] {
                    i += 1;
                } else if j + 1 < s_len && first[i] == second[j + 1] {
                    j += 1;
                }
            }
            i += 1;
            j += 1;
        }
        count += f_len - i + s_len - j;
        count <= 1
    }
}
```

#### Swift

```swift
class Solution {
    func oneEditAway(_ first: String, _ second: String) -> Bool {
        let m = first.count, n = second.count
        if m < n {
            return oneEditAway(second, first)
        }
        if m - n > 1 {
            return false
        }

        var cnt = 0
        var firstIndex = first.startIndex
        var secondIndex = second.startIndex

        if m == n {
            while secondIndex != second.endIndex {
                if first[firstIndex] != second[secondIndex] {
                    cnt += 1
                    if cnt > 1 {
                        return false
                    }
                }
                firstIndex = first.index(after: firstIndex)
                secondIndex = second.index(after: secondIndex)
            }
            return true
        } else {
            while firstIndex != first.endIndex {
                if secondIndex == second.endIndex || (secondIndex != second.endIndex && first[firstIndex] != second[secondIndex]) {
                    cnt += 1
                } else {
                    secondIndex = second.index(after: secondIndex)
                }
                firstIndex = first.index(after: firstIndex)
            }
        }
        return cnt < 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
