# [面试题 01.04. 回文排列](https://leetcode.cn/problems/palindrome-permutation-lcci)

[English Version](/lcci/01.04.Palindrome%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。</p>

<p>回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。</p>

<p>回文串不一定是字典当中的单词。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre><strong>输入：&quot;</strong>tactcoa&quot;
<strong>输出：</strong>true（排列有&quot;tacocat&quot;、&quot;atcocta&quot;，等等）
</pre>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用哈希表 `cnt` 存储每个字符出现的次数。若次数为奇数的字符超过 $1$ 个，则不是回文排列。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        cnt = Counter(s)
        return sum(v & 1 for v in cnt.values()) < 2
```

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> cnt =  new HashMap<>();
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

### **C++**

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

### **Go**

```go
func canPermutePalindrome(s string) bool {
	vis := map[rune]bool{}
	cnt := 0
	for _, c := range s {
		if vis[c] {
			vis[c] = false
			cnt--
		} else {
			vis[c] = true
			cnt++
		}
	}
	return cnt < 2
}
```

### **TypeScript**

```ts
function canPermutePalindrome(s: string): boolean {
    const set = new Set<string>();
    for (const c of s) {
        if (set.has(c)) {
            set.delete(c);
        } else {
            set.add(c);
        }
    }
    return set.size <= 1;
}
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut set = HashSet::new();
        for c in s.chars() {
            if set.contains(&c) {
                set.remove(&c);
            } else {
                set.insert(c);
            }
        }
        set.len() <= 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
