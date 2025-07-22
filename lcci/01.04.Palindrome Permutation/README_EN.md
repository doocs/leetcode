---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.04.Palindrome%20Permutation/README_EN.md
---

<!-- problem:start -->

# [01.04. Palindrome Permutation](https://leetcode.cn/problems/palindrome-permutation-lcci)

[中文文档](/lcci/01.04.Palindrome%20Permutation/README.md)

## Description

<!-- description:start -->

<p>Given a string, write a function to check if it is a permutation of a palin&shy; drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.</p>

<p>&nbsp;</p>

<p><strong>Example1: </strong></p>

<pre>

<strong>Input: &quot;</strong>tactcoa&quot;

<strong>Output: </strong>true（permutations: &quot;tacocat&quot;、&quot;atcocta&quot;, etc.）

</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $cnt$ to store the occurrence count of each character. If more than $1$ character has an odd count, then it is not a palindrome permutation.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

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

<!-- solution:start -->

### Solution 2: Another Implementation of Hash Table

We use a hash table $vis$ to store whether each character has appeared. If it has appeared, we remove the character from the hash table; otherwise, we add the character to the hash table.

Finally, we check whether the number of characters in the hash table is less than $2$. If it is, then it is a palindrome permutation.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

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
