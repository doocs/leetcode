---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.02.Check%20Permutation/README_EN.md
---

<!-- problem:start -->

# [01.02. Check Permutation](https://leetcode.cn/problems/check-permutation-lcci)

[中文文档](/lcci/01.02.Check%20Permutation/README.md)

## Description

<!-- description:start -->

<p>Given two strings,write a method to decide if one is a permutation of the other.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;abc&quot;, s2 = &quot;bca&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;abc&quot;, s2 = &quot;bad&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Note:</strong></p>
<ol>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100</code></li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Array or Hash Table

First, we check whether the lengths of the two strings are equal. If they are not equal, we directly return `false`.

Then, we use an array or hash table to count the occurrence of each character in string $s1$.

Next, we traverse the other string $s2$. For each character we encounter, we decrement its corresponding count. If the count after decrementing is less than $0$, it means that the occurrence of characters in the two strings is different, so we directly return `false`.

Finally, after traversing string $s2$, we return `true`.

Note: In this problem, all test case strings only contain lowercase letters, so we can directly create an array of length $26$ for counting.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string, and $C$ is the size of the character set. In this problem, $C=26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return Counter(s1) == Counter(s2)
```

#### Java

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (char c : s2.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if (s1.size() != s2.size()) {
            return false;
        }
        int cnt[26]{};
        for (char c : s1) {
            ++cnt[c - 'a'];
        }
        for (char c : s2) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func CheckPermutation(s1 string, s2 string) bool {
	if len(s1) != len(s2) {
		return false
	}
	cnt := make([]int, 26)
	for _, c := range s1 {
		cnt[c-'a']++
	}
	for _, c := range s2 {
		if cnt[c-'a']--; cnt[c-'a'] < 0 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    if (s1.length !== s2.length) {
        return false;
    }
    const cnt: Record<string, number> = {};
    for (const c of s1) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    for (const c of s2) {
        if (!cnt[c]) {
            return false;
        }
        cnt[c]--;
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        if s1.len() != s2.len() {
            return false;
        }

        let mut cnt = vec![0; 26];
        for c in s1.chars() {
            cnt[(c as usize - 'a' as usize)] += 1;
        }

        for c in s2.chars() {
            let index = c as usize - 'a' as usize;
            if cnt[index] == 0 {
                return false;
            }
            cnt[index] -= 1;
        }

        true
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    if (s1.length !== s2.length) {
        return false;
    }
    const cnt = {};
    for (const c of s1) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    for (const c of s2) {
        if (!cnt[c]) {
            return false;
        }
        cnt[c]--;
    }
    return true;
};
```

#### Swift

```swift
class Solution {
    func CheckPermutation(_ s1: String, _ s2: String) -> Bool {
        if s1.count != s2.count {
            return false
        }

        var cnt = [Int](repeating: 0, count: 26)

        for char in s1 {
            cnt[Int(char.asciiValue! - Character("a").asciiValue!)] += 1
        }

        for char in s2 {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if cnt[index] == 0 {
                return false
            }
            cnt[index] -= 1
        }

        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting

We can also sort the two strings in lexicographical order, and then compare whether the two strings are equal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

#### Java

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return Arrays.equals(cs1, cs2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        ranges::sort(s1);
        ranges::sort(s2);
        return s1 == s2;
    }
};
```

#### Go

```go
func CheckPermutation(s1 string, s2 string) bool {
	cs1, cs2 := []byte(s1), []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	return string(cs1) == string(cs2)
}
```

#### TypeScript

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    return [...s1].sort().join('') === [...s2].sort().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let mut s1: Vec<char> = s1.chars().collect();
        let mut s2: Vec<char> = s2.chars().collect();
        s1.sort();
        s2.sort();
        s1 == s2
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    return [...s1].sort().join('') === [...s2].sort().join('');
};
```

#### Swift

```swift
class Solution {
    func CheckPermutation(_ s1: String, _ s2: String) -> Bool {
        let s1 = s1.sorted()
        let s2 = s2.sorted()
        return s1 == s2
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
