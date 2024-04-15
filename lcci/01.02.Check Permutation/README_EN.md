# [01.02. Check Permutation](https://leetcode.cn/problems/check-permutation-lcci)

[中文文档](/lcci/01.02.Check%20Permutation/README.md)

## Description

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

## Solutions

### Solution 1: Array or Hash Table

First, we check whether the lengths of the two strings are equal. If they are not equal, we directly return `false`.

Then, we use an array or hash table to count the occurrence of each character in string $s1$.

Next, we traverse the other string $s2$. For each character we encounter, we decrement its corresponding count. If the count after decrementing is less than $0$, it means that the occurrence of characters in the two strings is different, so we directly return `false`.

Finally, after traversing string $s2$, we return `true`.

Note: In this problem, all test case strings only contain lowercase letters, so we can directly create an array of length $26$ for counting.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string, and $C$ is the size of the character set. In this problem, $C=26$.

<!-- tabs:start -->

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return Counter(s1) == Counter(s2)
```

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

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if (s1.size() != s2.size()) return false;
        int cnt[26] = {0};
        for (char& c : s1) ++cnt[c - 'a'];
        for (char& c : s2)
            if (--cnt[c - 'a'] < 0) return false;
        return true;
    }
};
```

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
		cnt[c-'a']--
		if cnt[c-'a'] < 0 {
			return false
		}
	}
	return true
}
```

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    const n = s1.length;
    const m = s2.length;
    if (n !== m) {
        return false;
    }
    const map = new Map<string, number>();
    for (let i = 0; i < n; i++) {
        map.set(s1[i], (map.get(s1[i]) ?? 0) + 1);
        map.set(s2[i], (map.get(s2[i]) ?? 0) - 1);
    }
    for (const v of map.values()) {
        if (v !== 0) {
            return false;
        }
    }
    return true;
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let n = s1.len();
        let m = s2.len();
        if n != m {
            return false;
        }
        let s1 = s1.as_bytes();
        let s2 = s2.as_bytes();
        let mut map = HashMap::new();
        for i in 0..n {
            *map.entry(s1[i]).or_insert(0) += 1;
            *map.entry(s2[i]).or_insert(0) -= 1;
        }
        map.values().all(|i| *i == 0)
    }
}
```

```js
/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    if (s1.length != s2.length) {
        return false;
    }
    const cnt = new Array(26).fill(0);
    for (let i = 0; i < s1.length; ++i) {
        const j = s1.codePointAt(i) - 'a'.codePointAt(0);
        ++cnt[j];
    }
    for (let i = 0; i < s2.length; ++i) {
        const j = s2.codePointAt(i) - 'a'.codePointAt(0);
        if (--cnt[j] < 0) {
            return false;
        }
    }
    return true;
};
```

```swift
class Solution {
    func CheckPermutation(_ s1: String, _ s2: String) -> Bool {
        if s1.count != s2.count {
            return false
        }

        var cnt = Array(repeating: 0, count: 26)

        for char in s1 {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            cnt[index] += 1
        }

        for char in s2 {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            cnt[index] -= 1
            if cnt[index] < 0 {
                return false
            }
        }

        return true
    }
}
```

<!-- tabs:end -->

### Solution 2: Sorting

We can also sort the two strings in lexicographical order, and then compare whether the two strings are equal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

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

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        sort(s1.begin(), s1.end());
        sort(s2.begin(), s2.end());
        return s1 == s2;
    }
};
```

```go
func CheckPermutation(s1 string, s2 string) bool {
	cs1, cs2 := []byte(s1), []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	return string(cs1) == string(cs2)
}
```

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    return [...s1].sort().join('') === [...s2].sort().join('');
}
```

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

<!-- tabs:end -->

<!-- end -->
