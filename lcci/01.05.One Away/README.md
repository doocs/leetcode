# [面试题 01.05. 一次编辑](https://leetcode.cn/problems/one-away-lcci)

[English Version](/lcci/01.05.One%20Away/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong>
first = &quot;pale&quot;
second = &quot;ple&quot;
<strong>输出:</strong> True</pre>

<p>&nbsp;</p>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong>
first = &quot;pales&quot;
second = &quot;pal&quot;
<strong>输出:</strong> False
</pre>

## 解法

### 方法一：分情况讨论 + 双指针

我们将字符串 $first$ 和 $second$ 的长度记为 $m$ 和 $n$，不妨设 $m \geq n$。

接下来分情况讨论：

-   当 $m - n \gt 1$ 时，$first$ 和 $second$ 无法通过一次编辑得到，返回 `false`；
-   当 $m = n$ 时，$first$ 和 $second$ 只有在且仅在有且仅有一个字符不同的情况下才能通过一次编辑得到；
-   当 $m - n = 1$ 时，$first$ 和 $second$ 只有在且仅在 $second$ 是 $first$ 删除一个字符后得到的情况下才能通过一次编辑得到，我们可以使用双指针来实现。

时间复杂度 $O(n)$，其中 $n$ 为字符串长度。空间复杂度 $O(1)$。

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
