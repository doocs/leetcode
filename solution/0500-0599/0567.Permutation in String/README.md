---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0567.Permutation%20in%20String/README.md
tags:
    - 哈希表
    - 双指针
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [567. 字符串的排列](https://leetcode.cn/problems/permutation-in-string)

[English Version](/solution/0500-0599/0567.Permutation%20in%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的 <span data-keyword="permutation-string">排列</span>。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
<strong>输出：</strong>true
<strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们用一个数组 $\textit{cnt}$ 记录当前需要匹配的字符及其个数，用一个变量 $\textit{need}$ 记录当前还需要匹配的字符种类数，初始时 $\textit{cnt}$ 为字符串 $\textit{s1}$ 中各字符出现次数，而 $\textit{need}$ 为 $\textit{s1}$ 中不同字符的个数。

然后我们遍历字符串 $\textit{s2}$，对于每个字符，我们将其在 $\textit{cnt}$ 中的对应值减一，如果减一后的值等于 $0$，说明当前字符在 $\textit{s1}$ 中出现次数已经满足要求，我们将 $\textit{need}$ 减一。如果当前下标 $i$ 大于等于 $\textit{s1}$ 的长度，我们需要将 $\textit{s2}[i-\textit{s1}]\textit{cnt}$ 中对应值加一，如果加一后的值等于 $1$，说明当前字符在 $\textit{s1}$ 中出现次数不再满足要求，我们将 $\textit{need}$ 加一。在遍历过程中，如果 $\textit{need}$ 的值等于 $0$，说明所有字符的出现次数都满足要求，我们就找到了一个满足要求的子串，返回 $\text{true}$。

否则，如果遍历结束后没有找到满足要求的子串，我们返回 $\text{false}$。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是字符串 $\textit{s1}$ 和 $\textit{s2}$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这道题中字符集为小写字母，所以空间复杂度是常数级别的。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        cnt = Counter(s1)
        need = len(cnt)
        m = len(s1)
        for i, c in enumerate(s2):
            cnt[c] -= 1
            if cnt[c] == 0:
                need -= 1
            if i >= m:
                cnt[s2[i - m]] += 1
                if cnt[s2[i - m]] == 1:
                    need += 1
            if need == 0:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int need = 0;
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        int m = s1.length(), n = s2.length();
        for (int i = 0; i < n; ++i) {
            int c = s2.charAt(i) - 'a';
            if (--cnt[c] == 0) {
                --need;
            }
            if (i >= m) {
                c = s2.charAt(i - m) - 'a';
                if (++cnt[c] == 1) {
                    ++need;
                }
            }
            if (need == 0) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int need = 0;
        int cnt[26]{};
        for (char c : s1) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        int m = s1.size(), n = s2.size();
        for (int i = 0; i < n; ++i) {
            int c = s2[i] - 'a';
            if (--cnt[c] == 0) {
                --need;
            }
            if (i >= m) {
                c = s2[i - m] - 'a';
                if (++cnt[c] == 1) {
                    ++need;
                }
            }
            if (need == 0) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func checkInclusion(s1 string, s2 string) bool {
	need := 0
	cnt := [26]int{}

	for _, c := range s1 {
		if cnt[c-'a']++; cnt[c-'a'] == 1 {
			need++
		}
	}

	m, n := len(s1), len(s2)
	for i := 0; i < n; i++ {
		c := s2[i] - 'a'
		if cnt[c]--; cnt[c] == 0 {
			need--
		}
		if i >= m {
			c = s2[i-m] - 'a'
			if cnt[c]++; cnt[c] == 1 {
				need++
			}
		}
		if need == 0 {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function checkInclusion(s1: string, s2: string): boolean {
    let need = 0;
    const cnt: number[] = Array(26).fill(0);
    const a = 'a'.charCodeAt(0);
    for (const c of s1) {
        if (++cnt[c.charCodeAt(0) - a] === 1) {
            need++;
        }
    }

    const [m, n] = [s1.length, s2.length];
    for (let i = 0; i < n; i++) {
        let c = s2.charCodeAt(i) - a;
        if (--cnt[c] === 0) {
            need--;
        }
        if (i >= m) {
            c = s2.charCodeAt(i - m) - a;
            if (++cnt[c] === 1) {
                need++;
            }
        }
        if (need === 0) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        let mut need = 0;
        let mut cnt = vec![0; 26];

        for c in s1.chars() {
            let index = (c as u8 - b'a') as usize;
            if cnt[index] == 0 {
                need += 1;
            }
            cnt[index] += 1;
        }

        let m = s1.len();
        let n = s2.len();
        let s2_bytes = s2.as_bytes();

        for i in 0..n {
            let c = (s2_bytes[i] - b'a') as usize;
            cnt[c] -= 1;
            if cnt[c] == 0 {
                need -= 1;
            }

            if i >= m {
                let c = (s2_bytes[i - m] - b'a') as usize;
                cnt[c] += 1;
                if cnt[c] == 1 {
                    need += 1;
                }
            }

            if need == 0 {
                return true;
            }
        }

        false
    }
}
```

#### C#

```cs
public class Solution {
    public bool CheckInclusion(string s1, string s2) {
        int need = 0;
        int[] cnt = new int[26];

        foreach (char c in s1) {
            if (++cnt[c - 'a'] == 1) {
                need++;
            }
        }

        int m = s1.Length, n = s2.Length;
        for (int i = 0; i < n; i++) {
            int c = s2[i] - 'a';
            if (--cnt[c] == 0) {
                need--;
            }

            if (i >= m) {
                c = s2[i - m] - 'a';
                if (++cnt[c] == 1) {
                    need++;
                }
            }

            if (need == 0) {
                return true;
            }
        }
        return false;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s1
     * @param String $s2
     * @return Boolean
     */
    function checkInclusion($s1, $s2) {
        $need = 0;
        $cnt = array_fill(0, 26, 0);

        for ($i = 0; $i < strlen($s1); $i++) {
            $index = ord($s1[$i]) - ord('a');
            if (++$cnt[$index] == 1) {
                $need++;
            }
        }

        $m = strlen($s1);
        $n = strlen($s2);

        for ($i = 0; $i < $n; $i++) {
            $c = ord($s2[$i]) - ord('a');
            if (--$cnt[$c] == 0) {
                $need--;
            }

            if ($i >= $m) {
                $c = ord($s2[$i - $m]) - ord('a');
                if (++$cnt[$c] == 1) {
                    $need++;
                }
            }

            if ($need == 0) {
                return true;
            }
        }

        return false;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
