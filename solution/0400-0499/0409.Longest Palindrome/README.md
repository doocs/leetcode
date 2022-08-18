# [409. 最长回文串](https://leetcode.cn/problems/longest-palindrome)

[English Version](/solution/0400-0499/0409.Longest%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含大写字母和小写字母的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，返回&nbsp;<em>通过这些字母构造成的 <strong>最长的回文串</strong></em>&nbsp;。</p>

<p>在构造过程中，请注意 <strong>区分大小写</strong> 。比如&nbsp;<code>"Aa"</code>&nbsp;不能当做一个回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1: </strong></p>

<pre>
<strong>输入:</strong>s = "abccccdd"
<strong>输出:</strong>7
<strong>解释:</strong>
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>s = "a"
<strong>输入:</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>&nbsp;只由小写 <strong>和/或</strong> 大写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

> 一个回文字符串，最多存在一个出现奇数次数的字符，

先统计所有字符出现的次数，通用的方式是哈希表。题目已说明只存在大小写字母（52 种可能），也可以使用数组来存储。

而后，可分两种方式：

-   布尔变量
    -   累加出现次数为偶数的数值。
    -   对于奇数，第一次出现，完整累加；后续出现，则需要对次数 `-1` 去奇，再累加。
-   计数器
    -   记录奇数出现的次数，最后的结果回文长度由 `s.length - count` 得知。
    -   如果只存在一个奇数，那么可以直接返回 `s.length`.

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPalindrome(self, s: str) -> int:
        n = len(s)
        counter = Counter(s)
        odd_cnt = sum(e % 2 for e in counter.values())
        return n if odd_cnt == 0 else n - odd_cnt + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            ++counter[c];
        }
        int oddCnt = 0;
        for (int e : counter) {
            oddCnt += (e % 2);
        }
        int n = s.length();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
```

### **TypeScript**

```ts
function longestPalindrome(s: string): number {
    let n = s.length;
    let ans = 0;
    let record = new Array(128).fill(0);
    for (let i = 0; i < n; i++) {
        record[s.charCodeAt(i)]++;
    }
    for (let i = 65; i < 128; i++) {
        let count = record[i];
        ans += count % 2 == 0 ? count : count - 1;
    }
    return ans < s.length ? ans + 1 : ans;
}
```

```ts
function longestPalindrome(s: string): number {
    const map = new Map();
    for (const c of s) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    let hasOdd = false;
    let res = 0;
    for (const v of map.values()) {
        res += v;
        if (v & 1) {
            hasOdd = true;
            res--;
        }
    }
    return res + (hasOdd ? 1 : 0);
}
```

### **C++**

```cpp
class Solution {
public:
    int longestPalindrome(string s) {
        vector<int> counter(128);
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (int e : counter) oddCnt += e % 2;
        int n = s.size();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
};
```

### **Go**

```go
func longestPalindrome(s string) int {
	counter := make([]int, 128)
	for _, c := range s {
		counter[c]++
	}
	oddCnt := 0
	for _, e := range counter {
		oddCnt += e % 2
	}
	n := len(s)
	if oddCnt == 0 {
		return n
	}
	return n - oddCnt + 1
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn longest_palindrome(s: String) -> i32 {
        let mut map: HashMap<char, i32> = HashMap::new();
        for c in s.chars() {
            map.insert(c, map.get(&c).unwrap_or(&0) + 1);
        }
        let mut has_odd = false;
        let mut res = 0;
        for v in map.values() {
            res += v;
            if v % 2 == 1 {
                has_odd = true;
                res -= 1;
            }
        }
        res + if has_odd { 1 } else { 0 }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
