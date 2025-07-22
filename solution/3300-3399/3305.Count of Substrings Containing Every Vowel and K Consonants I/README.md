---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3305.Count%20of%20Substrings%20Containing%20Every%20Vowel%20and%20K%20Consonants%20I/README.md
rating: 1563
source: 第 417 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [3305. 元音辅音字符串计数 I](https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i)

[English Version](/solution/3300-3399/3305.Count%20of%20Substrings%20Containing%20Every%20Vowel%20and%20K%20Consonants%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code> 和一个 <strong>非负 </strong>整数 <code>k</code>。</p>

<p>返回 <code>word</code> 的 <span data-keyword="substring-nonempty">子字符串</span> 中，每个元音字母（<code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code>）<strong>至少</strong> 出现一次，并且 <strong>恰好 </strong>包含 <code>k</code> 个辅音字母的子字符串的总数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "aeioqq", k = 1</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在包含所有元音字母的子字符串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "aeiou", k = 0</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一一个包含所有元音字母且不含辅音字母的子字符串是 <code>word[0..4]</code>，即 <code>"aeiou"</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">word = "ieaouqqieaouqq", k = 1</span></p>

<p><strong>输出：</strong>3</p>

<p><strong>解释：</strong></p>

<p>包含所有元音字母并且恰好含有一个辅音字母的子字符串有：</p>

<ul>
	<li><code>word[0..5]</code>，即 <code>"ieaouq"</code>。</li>
	<li><code>word[6..11]</code>，即 <code>"qieaou"</code>。</li>
	<li><code>word[7..12]</code>，即 <code>"ieaouq"</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>5 &lt;= word.length &lt;= 250</code></li>
	<li><code>word</code> 仅由小写英文字母组成。</li>
	<li><code>0 &lt;= k &lt;= word.length - 5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：问题转换 + 滑动窗口

我们可以转换为求以下两个问题：

1. 求每个元音字母至少出现一次，且至少包含 $k$ 个辅音字母的子字符串的总数 $\textit{f}(k)$；
2. 求每个元音字母至少出现一次，且至少包含 $k + 1$ 个辅音字母的子字符串的总数 $\textit{f}(k + 1)$。

那么答案就是 $\textit{f}(k) - \textit{f}(k + 1)$。

因此，我们设计一个函数 $\textit{f}(k)$，用于统计每个元音字母至少出现一次，且至少包含 $k$ 个辅音字母的子字符串的总数。

我们可以用一个哈希表 $\textit{cnt}$ 统计每个元音字母的出现次数，用一个变量 $\textit{ans}$ 统计答案，用一个变量 $\textit{l}$ 记录滑动窗口的左边界，用一个变量 $\textit{x}$ 记录当前窗口中辅音字母的个数。

遍历字符串，如果当前字符是元音字母，则将其加入哈希表 $\textit{cnt}$ 中，否则将 $\textit{x}$ 加一。如果此时 $\textit{x} \ge k$ 且哈希表 $\textit{cnt}$ 的大小为 $5$，说明当前窗口满足条件，我们循环移动左边界，直到窗口不满足条件。此时，以右边界 $\textit{r}$ 为结尾、且左边界在 $[0,.. \textit{l} - 1]$ 范围内的子字符串都满足条件，一共有 $\textit{l}$ 个。我们将 $\textit{l}$ 加到答案中。继续遍历字符串，直到遍历结束，我们就得到了 $\textit{f}(k)$。

最后，我们返回 $\textit{f}(k) - \textit{f}(k + 1)$。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $\textit{word}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfSubstrings(self, word: str, k: int) -> int:
        def f(k: int) -> int:
            cnt = Counter()
            ans = l = x = 0
            for c in word:
                if c in "aeiou":
                    cnt[c] += 1
                else:
                    x += 1
                while x >= k and len(cnt) == 5:
                    d = word[l]
                    if d in "aeiou":
                        cnt[d] -= 1
                        if cnt[d] == 0:
                            cnt.pop(d)
                    else:
                        x -= 1
                    l += 1
                ans += l
            return ans

        return f(k) - f(k + 1)
```

#### Java

```java
class Solution {
    public int countOfSubstrings(String word, int k) {
        return f(word, k) - f(word, k + 1);
    }

    private int f(String word, int k) {
        int ans = 0;
        int l = 0, x = 0;
        Map<Character, Integer> cnt = new HashMap<>(5);
        for (char c : word.toCharArray()) {
            if (vowel(c)) {
                cnt.merge(c, 1, Integer::sum);
            } else {
                ++x;
            }
            while (x >= k && cnt.size() == 5) {
                char d = word.charAt(l++);
                if (vowel(d)) {
                    if (cnt.merge(d, -1, Integer::sum) == 0) {
                        cnt.remove(d);
                    }
                } else {
                    --x;
                }
            }
            ans += l;
        }
        return ans;
    }

    private boolean vowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfSubstrings(string word, int k) {
        auto f = [&](int k) -> int {
            int ans = 0;
            int l = 0, x = 0;
            unordered_map<char, int> cnt;
            auto vowel = [&](char c) -> bool {
                return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
            };
            for (char c : word) {
                if (vowel(c)) {
                    cnt[c]++;
                } else {
                    ++x;
                }
                while (x >= k && cnt.size() == 5) {
                    char d = word[l++];
                    if (vowel(d)) {
                        if (--cnt[d] == 0) {
                            cnt.erase(d);
                        }
                    } else {
                        --x;
                    }
                }
                ans += l;
            }
            return ans;
        };

        return f(k) - f(k + 1);
    }
};
```

#### Go

```go
func countOfSubstrings(word string, k int) int {
	f := func(k int) int {
		var ans int = 0
		l, x := 0, 0
		cnt := make(map[rune]int)
		vowel := func(c rune) bool {
			return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
		}
		for _, c := range word {
			if vowel(c) {
				cnt[c]++
			} else {
				x++
			}
			for x >= k && len(cnt) == 5 {
				d := rune(word[l])
				l++
				if vowel(d) {
					cnt[d]--
					if cnt[d] == 0 {
						delete(cnt, d)
					}
				} else {
					x--
				}
			}
			ans += l
		}
		return ans
	}

	return f(k) - f(k+1)
}
```

#### TypeScript

```ts
function countOfSubstrings(word: string, k: number): number {
    const f = (k: number): number => {
        let ans = 0;
        let l = 0,
            x = 0;
        const cnt = new Map<string, number>();

        const vowel = (c: string): boolean => {
            return c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u';
        };

        for (const c of word) {
            if (vowel(c)) {
                cnt.set(c, (cnt.get(c) || 0) + 1);
            } else {
                x++;
            }

            while (x >= k && cnt.size === 5) {
                const d = word[l++];
                if (vowel(d)) {
                    cnt.set(d, cnt.get(d)! - 1);
                    if (cnt.get(d) === 0) {
                        cnt.delete(d);
                    }
                } else {
                    x--;
                }
            }
            ans += l;
        }

        return ans;
    };

    return f(k) - f(k + 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn count_of_substrings(word: String, k: i32) -> i32 {
        fn f(word: &Vec<char>, k: i32) -> i32 {
            let mut ans = 0;
            let mut l = 0;
            let mut x = 0;
            let mut cnt = std::collections::HashMap::new();

            let is_vowel = |c: char| matches!(c, 'a' | 'e' | 'i' | 'o' | 'u');

            for (r, &c) in word.iter().enumerate() {
                if is_vowel(c) {
                    *cnt.entry(c).or_insert(0) += 1;
                } else {
                    x += 1;
                }

                while x >= k && cnt.len() == 5 {
                    let d = word[l];
                    l += 1;
                    if is_vowel(d) {
                        let count = cnt.entry(d).or_insert(0);
                        *count -= 1;
                        if *count == 0 {
                            cnt.remove(&d);
                        }
                    } else {
                        x -= 1;
                    }
                }
                ans += l as i32;
            }
            ans
        }

        let chars: Vec<char> = word.chars().collect();
        f(&chars, k) - f(&chars, k + 1)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
