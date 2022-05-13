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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def oneEditAway(self, first: str, second: str) -> bool:
        n1, n2 = len(first), len(second)
        diff = n1 - n2
        if abs(diff) > 1:
            return False
        i, j, op = 0, 0, 1
        while i < n1 and j < n2:
            not_same = first[i] != second[j]
            if not_same:
                if diff == 1:
                    i += 1
                elif diff == -1:
                    j += 1
                else:
                    i += 1
                    j += 1
                op -= 1
            else:
                i += 1
                j += 1
            if op < 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean oneEditAway(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        int diff = n1 - n2;
        if (Math.abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            boolean notSame = first.charAt(i) != second.charAt(j);
            if (notSame) {
                if (diff == 1) {
                    // --j, ++i, ++j => ++i
                    --j;
                } else if (diff == -1) {
                    // --i, ++i, ++j => ++j
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool oneEditAway(string first, string second) {
        int n1 = first.size(), n2 = second.size();
        int diff = n1 - n2;
        if (abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            bool notSame = first[i] != second[j];
            if (notSame) {
                if (diff == 1) {
                    --j;
                } else if (diff == -1) {
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

Similar to [Edit Distance](https://leetcode.cn/problems/edit-distance/) solution

```go
func oneEditAway(first string, second string) bool {
	if first == second {
		return true
	}
	m, n := len(first), len(second)
	dp := make([][]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = make([]int, n+1)
	}
	for i := 0; i <= m; i++ {
		dp[i][0] = i
	}
	for j := 0; j <= n; j++ {
		dp[0][j] = j
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if first[i-1] == second[j-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				insert := dp[i][j-1] + 1
				delete := dp[i-1][j] + 1
				update := dp[i-1][j-1] + 1
				dp[i][j] = min(insert, delete, update)
			}
		}
	}
	return dp[m][n] == 1
}

func min(x, y, z int) int {
	if x < y {
		if x < z {
			return x
		}
		return z
	}
	if y < z {
		return y
	}
	return z
}
```

### **TypeScript**

```ts
function oneEditAway(first: string, second: string): boolean {
    const n = first.length;
    const m = second.length;

    let count = 0;
    let i = 0;
    let j = 0;
    while (i < n || j < m) {
        if (first[i] !== second[j]) {
            count++;

            if (i < n && first[i + 1] === second[j]) {
                i++;
            } else if (j < m && first[i] === second[j + 1]) {
                j++;
            }
        }
        i++;
        j++;
    }
    return count <= 1;
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
