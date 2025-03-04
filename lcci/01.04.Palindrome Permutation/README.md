---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.04.Palindrome%20Permutation/README.md
---

<!-- problem:start -->

# [面试题 01.04. 回文排列](https://leetcode.cn/problems/palindrome-permutation-lcci)

[English Version](/lcci/01.04.Palindrome%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。</p>

<p>回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。</p>

<p>回文串不一定是字典当中的单词。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre><strong>输入：&quot;</strong>tactcoa&quot;
<strong>输出：</strong>true（排列有&quot;tacocat&quot;、&quot;atcocta&quot;，等等）
</pre>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用哈希表 $cnt$ 存储每个字符出现的次数。若次数为奇数的字符超过 $1$ 个，则不是回文排列。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        cnt = Counter(s)
        return sum(v & 1 for v in cnt.values()) < 2
```

#### Java

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
        }
        int sum = 0;
        for (int v : cnt.values()) {
            sum += v & 1;
        }
        return sum < 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> cnt;
        for (auto& c : s) {
            ++cnt[c];
        }
        int sum = 0;
        for (auto& [_, v] : cnt) {
            sum += v & 1;
        }
        return sum < 2;
    }
};
```

#### Go

```go
func canPermutePalindrome(s string) bool {
	cnt := map[rune]int{}
	for _, c := range s {
		cnt[c]++
	}
	sum := 0
	for _, v := range cnt {
		sum += v & 1
	}
	return sum < 2
}
```

#### TypeScript

```ts
function canPermutePalindrome(s: string): boolean {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    return Object.values(cnt).filter(v => v % 2 === 1).length < 2;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut cnt = HashMap::new();
        for c in s.chars() {
            *cnt.entry(c).or_insert(0) += 1;
        }
        cnt.values().filter(|&&v| v % 2 == 1).count() < 2
    }
}
```

#### Swift

```swift
class Solution {
    func canPermutePalindrome(_ s: String) -> Bool {
        var cnt = [Character: Int]()
        for char in s {
            cnt[char, default: 0] += 1
        }

        var sum = 0
        for count in cnt.values {
            sum += count % 2
        }

        return sum < 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：哈希表的另一种实现

我们用一个哈希表 $\textit{vis}$ 存储每个字符是否出现过。若出现过，则从哈希表中删除该字符；否则，将该字符加入哈希表。

最后判断哈希表中字符的个数是否小于 $2$，若是，则是回文排列。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        vis = set()
        for c in s:
            if c in vis:
                vis.remove(c)
            else:
                vis.add(c)
        return len(vis) < 2
```

#### Java

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> vis = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!vis.add(c)) {
                vis.remove(c);
            }
        }
        return vis.size() < 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_set<char> vis;
        for (auto& c : s) {
            if (vis.count(c)) {
                vis.erase(c);
            } else {
                vis.insert(c);
            }
        }
        return vis.size() < 2;
    }
};
```

#### Go

```go
func canPermutePalindrome(s string) bool {
	vis := map[rune]bool{}
	for _, c := range s {
		if vis[c] {
			delete(vis, c)
		} else {
			vis[c] = true
		}
	}
	return len(vis) < 2
}
```

#### TypeScript

```ts
function canPermutePalindrome(s: string): boolean {
    const vis = new Set<string>();
    for (const c of s) {
        if (vis.has(c)) {
            vis.delete(c);
        } else {
            vis.add(c);
        }
    }
    return vis.size < 2;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut vis = HashSet::new();
        for c in s.chars() {
            if vis.contains(&c) {
                vis.remove(&c);
            } else {
                vis.insert(c);
            }
        }
        vis.len() < 2
    }
}
```

#### Swift

```swift
class Solution {
    func canPermutePalindrome(_ s: String) -> Bool {
        var vis = Set<Character>()
        for c in s {
            if vis.contains(c) {
                vis.remove(c)
            } else {
                vis.insert(c)
            }
        }
        return vis.count < 2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
