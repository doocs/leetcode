# [面试题 01.02. 判定是否互为字符重排](https://leetcode.cn/problems/check-permutation-lcci)

[English Version](/lcci/01.02.Check%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

先判断两个字符串的长度是否相等，若不相等则直接返回 `false`。

然后用一个数组或哈希表统计字符串 $s1$ 中字符出现的次数。

接着遍历另一个字符串 $s2$，每遍历到一个字符，就将该字符对应的次数减一，如果减一后的次数小于 $0$，则说明两个字符串中字符出现的次数不同，直接返回 `false`。

最后遍历完字符串 $s2$，返回 `true`。

注意：本题测试用例所有字符串仅包含小写字母，因此我们可以直接开一个长度为 $26$ 的数组来计数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串的长度，而 $C$ 为字符集的大小，本题 $C=26$。

**方法二：排序**

按照字典序对两个字符串进行排序，然后比较两个字符串是否相等。

时间复杂度 $O(n\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return Counter(s1) == Counter(s2)
```

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if (s1.size() != s2.size()) return false;
        int cnt[26] = {0};
        for (char & c : s1) ++cnt[c - 'a'];
        for (char & c : s2) if (--cnt[c - 'a'] < 0) return false;
        return true;
    }
};
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

### **Go**

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

```go
func CheckPermutation(s1 string, s2 string) bool {
	cs1, cs2 := []byte(s1), []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	return string(cs1) == string(cs2)
}
```

### **JavaScript**

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

### **TypeScript**

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

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    return [...s1].sort().join('') === [...s2].sort().join('');
}
```

### **Rust**

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

### **...**

```

```

<!-- tabs:end -->
