# [2405. 子字符串的最优划分](https://leetcode.cn/problems/optimal-partition-of-string)

[English Version](/solution/2400-2499/2405.Optimal%20Partition%20of%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请你将该字符串划分成一个或多个 <strong>子字符串</strong> ，并满足每个子字符串中的字符都是 <strong>唯一</strong> 的。也就是说，在单个子字符串中，字母的出现次数都不超过 <strong>一次</strong> 。</p>

<p>满足题目要求的情况下，返回 <strong>最少</strong> 需要划分多少个子字符串<em>。</em></p>

<p>注意，划分后，原字符串中的每个字符都应该恰好属于一个子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abacaba"
<strong>输出：</strong>4
<strong>解释：</strong>
两种可行的划分方法分别是 ("a","ba","cab","a") 和 ("ab","a","ca","ba") 。
可以证明最少需要划分 4 个子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ssssss"
<strong>输出：</strong>6
<strong>解释：
</strong>只存在一种可行的划分方法 ("s","s","s","s","s","s") 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

根据题意，每个子字符串应该尽可能长，且包含的字符唯一。我们只需要贪心地进行划分即可。

过程中，可以用哈希表记录当前子字符串的所有字符，空间复杂度 $O(n)$；也可以使用一个数字，用位运算的方式记录字符，空间复杂度 $O(1)$。

时间复杂度 $O(n)$。其中 $n$ 是字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionString(self, s: str) -> int:
        ss = set()
        ans = 1
        for c in s:
            if c in ss:
                ans += 1
                ss = set()
            ss.add(c)
        return ans
```

```python
class Solution:
    def partitionString(self, s: str) -> int:
        ans, v = 1, 0
        for c in s:
            i = ord(c) - ord('a')
            if (v >> i) & 1:
                v = 0
                ans += 1
            v |= 1 << i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int partitionString(String s) {
        Set<Character> ss = new HashSet<>();
        int ans = 1;
        for (char c : s.toCharArray()) {
            if (ss.contains(c)) {
                ++ans;
                ss.clear();
            }
            ss.add(c);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int partitionString(String s) {
        int v = 0;
        int ans = 1;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (((v >> i) & 1) == 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int partitionString(string s) {
        unordered_set<char> ss;
        int ans = 1;
        for (char c : s) {
            if (ss.count(c)) {
                ++ans;
                ss.clear();
            }
            ss.insert(c);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int partitionString(string s) {
        int ans = 1;
        int v = 0;
        for (char c : s) {
            int i = c - 'a';
            if ((v >> i) & 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
};
```

### **Go**

```go
func partitionString(s string) int {
	ss := map[rune]bool{}
	ans := 1
	for _, c := range s {
		if ss[c] {
			ans++
			ss = map[rune]bool{}
		}
		ss[c] = true
	}
	return ans
}
```

```go
func partitionString(s string) int {
	ans, v := 1, 0
	for _, c := range s {
		i := int(c - 'a')
		if v>>i&1 == 1 {
			v = 0
			ans++
		}
		v |= 1 << i
	}
	return ans
}
```

### **TypeScript**

```ts
function partitionString(s: string): number {
    const set = new Set();
    let res = 1;
    for (const c of s) {
        if (set.has(c)) {
            res++;
            set.clear();
        }
        set.add(c);
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn partition_string(s: String) -> i32 {
        let mut set = HashSet::new();
        let mut res = 1;
        for c in s.as_bytes().iter() {
            if set.contains(c) {
                res += 1;
                set.clear();
            }
            set.insert(c);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
