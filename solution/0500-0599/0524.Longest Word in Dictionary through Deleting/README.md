---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0524.Longest%20Word%20in%20Dictionary%20through%20Deleting/README.md
tags:
    - 数组
    - 双指针
    - 字符串
    - 排序
---

<!-- problem:start -->

# [524. 通过删除字母匹配到字典里最长单词](https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting)

[English Version](/solution/0500-0599/0524.Longest%20Word%20in%20Dictionary%20through%20Deleting/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个字符串数组 <code>dictionary</code> ，找出并返回&nbsp;<code>dictionary</code> 中最长的字符串，该字符串可以通过删除 <code>s</code> 中的某些字符得到。</p>

<p>如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
<strong>输出：</strong>"apple"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abpcplea", dictionary = ["a","b","c"]
<strong>输出：</strong>"a"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 1000</code></li>
	<li><code>s</code> 和 <code>dictionary[i]</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：判断子序列

我们定义一个函数 $check(s, t)$，用于判断字符串 $s$ 是否是字符串 $t$ 的子序列。我们可以使用双指针的方法，初始化两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 和字符串 $t$ 的开头，然后不断移动指针 $j$，如果 $s[i]$ 和 $t[j]$ 相等，则移动指针 $i$，最后判断 $i$ 是否等于 $s$ 的长度即可。若 $i$ 等于 $s$ 的长度，则说明 $s$ 是 $t$ 的子序列。

我们初始化答案字符串 $ans$ 为空字符串，然后遍历数组 $dictionary$ 中的每个字符串 $t$，如果 $t$ 是 $s$ 的子序列，并且 $t$ 的长度大于 $ans$ 的长度，或者 $t$ 的长度等于 $ans$ 的长度且 $t$ 字典序小于 $ans$，则更新 $ans$ 为 $t$。

时间复杂度 $O(d \times (m + n))$，其中 $d$ 是字符串列表的长度，而 $m$ 和 $n$ 分别是字符串 $s$ 的长度和字符串列表中字符串的平均长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLongestWord(self, s: str, dictionary: List[str]) -> str:
        def check(s: str, t: str) -> bool:
            m, n = len(s), len(t)
            i = j = 0
            while i < m and j < n:
                if s[i] == t[j]:
                    i += 1
                j += 1
            return i == m

        ans = ""
        for t in dictionary:
            if check(t, s) and (len(ans) < len(t) or (len(ans) == len(t) and ans > t)):
                ans = t
        return ans
```

#### Java

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String t : dictionary) {
            int a = ans.length(), b = t.length();
            if (check(t, s) && (a < b || (a == b && t.compareTo(ans) < 0))) {
                ans = t;
            }
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0;
        for (int j = 0; i < m && j < n; ++j) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
        }
        return i == m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findLongestWord(string s, vector<string>& dictionary) {
        string ans = "";
        auto check = [&](const string& s, const string& t) {
            int m = s.size(), n = t.size();
            int i = 0;
            for (int j = 0; i < m && j < n; ++j) {
                if (s[i] == t[j]) {
                    ++i;
                }
            }
            return i == m;
        };
        for (auto& t : dictionary) {
            int a = ans.size(), b = t.size();
            if (check(t, s) && (a < b || (a == b && ans > t))) {
                ans = t;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLongestWord(s string, dictionary []string) string {
	ans := ''
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		i := 0
		for j := 0; i < m && j < n; j++ {
			if s[i] == t[j] {
				i++
			}
		}
		return i == m
	}
	for _, t := range dictionary {
		a, b := len(ans), len(t)
		if check(t, s) && (a < b || (a == b && ans > t)) {
			ans = t
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findLongestWord(s: string, dictionary: string[]): string {
    const check = (s: string, t: string): boolean => {
        const [m, n] = [s.length, t.length];
        let i = 0;
        for (let j = 0; i < m && j < n; ++j) {
            if (s[i] === t[j]) {
                ++i;
            }
        }
        return i === m;
    };
    let ans: string = '';
    for (const t of dictionary) {
        const [a, b] = [ans.length, t.length];
        if (check(t, s) && (a < b || (a === b && ans > t))) {
            ans = t;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_longest_word(s: String, dictionary: Vec<String>) -> String {
        let mut ans = String::new();
        for t in dictionary {
            let a = ans.len();
            let b = t.len();
            if Self::check(&t, &s) && (a < b || (a == b && t < ans)) {
                ans = t;
            }
        }
        ans
    }

    fn check(s: &str, t: &str) -> bool {
        let (m, n) = (s.len(), t.len());
        let mut i = 0;
        let mut j = 0;
        let s: Vec<char> = s.chars().collect();
        let t: Vec<char> = t.chars().collect();

        while i < m && j < n {
            if s[i] == t[j] {
                i += 1;
            }
            j += 1;
        }
        i == m
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
