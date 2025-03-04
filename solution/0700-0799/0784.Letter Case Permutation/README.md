---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0784.Letter%20Case%20Permutation/README.md
tags:
    - 位运算
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [784. 字母大小写全排列](https://leetcode.cn/problems/letter-case-permutation)

[English Version](/solution/0700-0799/0784.Letter%20Case%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，通过将字符串&nbsp;<code>s</code>&nbsp;中的每个字母转变大小写，我们可以获得一个新的字符串。</p>

<p>返回 <em>所有可能得到的字符串集合</em> 。以 <strong>任意顺序</strong> 返回输出。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "a1b2"
<strong>输出：</strong>["a1b2", "a1B2", "A1b2", "A1B2"]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "3z4"
<strong>输出:</strong> ["3z4","3Z4"]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 12</code></li>
	<li><code>s</code>&nbsp;由小写英文字母、大写英文字母和数字组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

由于 $s$ 中的每个字母都可以转换为大写或小写，因此可以使用 DFS 深度优先搜索的方法，枚举所有可能的情况。

具体地，从左到右遍历字符串 $s$，对于遍历到的每个字母，可以选择将其转变为大写或小写，然后继续遍历后面的字母。当遍历到字符串的末尾时，得到一个转换方案，将该方案加入答案即可。

转变大小写的方法可以使用位运算实现。对于一个字母，小写形式与大写形式的 ASCII 码之差为 $32$，因此，我们可以通过将该字母的 ASCII 码与 $32$ 进行异或运算来实现大小写转换。

时间复杂度 $O(n \times 2^n)$，其中 $n$ 是字符串 $s$ 的长度。对于每个字母，我们可以选择将其转换为大写或小写，因此一共有 $2^n$ 种转换方案。对于每种转换方案，我们需要 $O(n)$ 的时间生成一个新的字符串。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i: int) -> None:
            if i >= len(t):
                ans.append("".join(t))
                return
            dfs(i + 1)
            if t[i].isalpha():
                t[i] = chr(ord(t[i]) ^ 32)
                dfs(i + 1)

        t = list(s)
        ans = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] t;

    public List<String> letterCasePermutation(String s) {
        t = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= t.length) {
            ans.add(new String(t));
            return;
        }
        dfs(i + 1);
        if (Character.isLetter(t[i])) {
            t[i] ^= 32;
            dfs(i + 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        string t = s;
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= t.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            if (isalpha(t[i])) {
                t[i] ^= 32;
                dfs(i + 1);
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func letterCasePermutation(s string) (ans []string) {
	t := []byte(s)
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(t) {
			ans = append(ans, string(t))
			return
		}
		dfs(i + 1)
		if t[i] >= 'A' {
			t[i] ^= 32
			dfs(i + 1)
		}
	}

	dfs(0)
	return ans
}
```

#### TypeScript

```ts
function letterCasePermutation(s: string): string[] {
    const t = s.split('');
    const ans: string[] = [];
    const dfs = (i: number) => {
        if (i >= t.length) {
            ans.push(t.join(''));
            return;
        }
        dfs(i + 1);
        if (t[i].charCodeAt(0) >= 65) {
            t[i] = String.fromCharCode(t[i].charCodeAt(0) ^ 32);
            dfs(i + 1);
        }
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        fn dfs(i: usize, t: &mut Vec<char>, ans: &mut Vec<String>) {
            if i >= t.len() {
                ans.push(t.iter().collect());
                return;
            }
            dfs(i + 1, t, ans);
            if t[i].is_alphabetic() {
                t[i] = (t[i] as u8 ^ 32) as char;
                dfs(i + 1, t, ans);
            }
        }

        let mut t: Vec<char> = s.chars().collect();
        let mut ans = Vec::new();
        dfs(0, &mut t, &mut ans);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二进制枚举

对于一个字母，我们可以将其转换为大写或小写，因此对于每个字母，我们可以使用一个二进制位表示其转换的方案，其中 $1$ 表示小写，而 $0$ 表示大写。

我们先统计字符串 $s$ 中字母的个数，记为 $n$，那么一共有 $2^n$ 种转换方案，我们可以使用二进制数的每一位表示每个字母的转换方案，从 $0$ 到 $2^n-1$ 进行枚举。

具体地，我们可以使用一个变量 $i$ 表示当前枚举到的二进制数，其中 $i$ 的第 $j$ 位表示第 $j$ 个字母的转换方案。即 $i$ 的第 $j$ 位为 $1$ 表示第 $j$ 个字母转换为小写，而 $i$ 的第 $j$ 位为 $0$ 表示第 $j$ 个字母转换为大写。

时间复杂度 $O(n \times 2^n)$，其中 $n$ 是字符串 $s$ 的长度。对于每个字母，我们可以选择将其转换为大写或小写，因此一共有 $2^n$ 种转换方案。对于每种转换方案，我们需要 $O(n)$ 的时间生成一个新的字符串。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        ans = []
        n = sum(c.isalpha() for c in s)
        for i in range(1 << n):
            j, t = 0, []
            for c in s:
                if c.isalpha():
                    c = c.lower() if (i >> j) & 1 else c.upper()
                    j += 1
                t.append(c)
            ans.append(''.join(t))
        return ans
```

#### Java

```java
class Solution {
    public List<String> letterCasePermutation(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'A') {
                ++n;
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            StringBuilder t = new StringBuilder();
            for (int k = 0; k < s.length(); ++k) {
                char x = s.charAt(k);
                if (x >= 'A') {
                    x = ((i >> j) & 1) == 1 ? Character.toLowerCase(x) : Character.toUpperCase(x);
                    ++j;
                }
                t.append(x);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        int n = count_if(s.begin(), s.end(), [](char c) { return isalpha(c); });
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            string t;
            for (char c : s) {
                if (isalpha(c)) {
                    c = (i >> j & 1) ? tolower(c) : toupper(c);
                    ++j;
                }
                t += c;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

#### Go

```go
func letterCasePermutation(s string) (ans []string) {
	n := 0
	for _, c := range s {
		if c >= 'A' {
			n++
		}
	}
	for i := 0; i < 1<<n; i++ {
		j := 0
		t := []rune{}
		for _, c := range s {
			if c >= 'A' {
				if ((i >> j) & 1) == 1 {
					c = unicode.ToLower(c)
				} else {
					c = unicode.ToUpper(c)
				}
				j++
			}
			t = append(t, c)
		}
		ans = append(ans, string(t))
	}
	return ans
}
```

#### TypeScript

```ts
function letterCasePermutation(s: string): string[] {
    const ans: string[] = [];
    const n: number = Array.from(s).filter(c => /[a-zA-Z]/.test(c)).length;
    for (let i = 0; i < 1 << n; ++i) {
        let j = 0;
        const t: string[] = [];
        for (let c of s) {
            if (/[a-zA-Z]/.test(c)) {
                t.push((i >> j) & 1 ? c.toLowerCase() : c.toUpperCase());
                j++;
            } else {
                t.push(c);
            }
        }
        ans.push(t.join(''));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        let n = s.chars().filter(|&c| c.is_alphabetic()).count();
        let mut ans = Vec::new();
        for i in 0..(1 << n) {
            let mut j = 0;
            let mut t = String::new();
            for c in s.chars() {
                if c.is_alphabetic() {
                    if (i >> j) & 1 == 1 {
                        t.push(c.to_lowercase().next().unwrap());
                    } else {
                        t.push(c.to_uppercase().next().unwrap());
                    }
                    j += 1;
                } else {
                    t.push(c);
                }
            }
            ans.push(t);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
