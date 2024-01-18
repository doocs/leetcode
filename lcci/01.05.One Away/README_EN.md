# [01.05. One Away](https://leetcode.cn/problems/one-away-lcci)

[中文文档](/lcci/01.05.One%20Away/README.md)

## Description

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

## Solutions

### Solution 1: Case Discussion + Two Pointers

We denote the lengths of strings $first$ and $second$ as $m$ and $n$, respectively, where $m \geq n$.

Next, we discuss different cases:

-   When $m - n > 1$, $first$ and $second$ cannot be obtained through a single edit, so we return `false`.
-   When $m = n$, $first$ and $second$ can only be obtained through a single edit if and only if exactly one character is different.
-   When $m - n = 1$, $first$ and $second$ can only be obtained through a single edit if and only if $second$ is obtained by deleting one character from $first$. We can use two pointers to implement this.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
