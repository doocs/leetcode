---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.02.Check%20Permutation/README.md
---

<!-- problem:start -->

# [面试题 01.02. 判定是否互为字符重排](https://leetcode.cn/problems/check-permutation-lcci)

[English Version](/lcci/01.02.Check%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串 <code>s1</code> 和 <code>s2</code>，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s1 = &quot;abc&quot;, s2 = &quot;bca&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s1 = &quot;abc&quot;, s2 = &quot;bad&quot;
<strong>输出:</strong> false
</pre>

<p><strong>说明：</strong></p>

<ul>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100 </code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组或哈希表

我们先判断两个字符串的长度是否相等，若不相等则直接返回 `false`。

然后用一个数组或哈希表统计字符串 $s1$ 中字符出现的次数。

接着遍历另一个字符串 $s2$，每遍历到一个字符，就将该字符对应的次数减一，如果减一后的次数小于 $0$，则说明两个字符串中字符出现的次数不同，直接返回 `false`。

最后遍历完字符串 $s2$，返回 `true`。

注意：本题测试用例所有字符串仅包含小写字母，因此我们可以直接开一个长度为 $26$ 的数组来计数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串的长度，而 $C$ 为字符集的大小，本题 $C=26$。

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

<!-- solution:start-->

### 方法二：排序

我们也按照字典序对两个字符串进行排序，然后比较两个字符串是否相等。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

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
