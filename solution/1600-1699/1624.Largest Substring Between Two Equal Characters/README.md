# [1624. 两个相同字符之间的最长子字符串](https://leetcode.cn/problems/largest-substring-between-two-equal-characters)

[English Version](/solution/1600-1699/1624.Largest%20Substring%20Between%20Two%20Equal%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，请你返回 <strong>两个相同字符之间的最长子字符串的长度</strong> <em>，</em>计算长度时不含这两个字符。如果不存在这样的子字符串，返回 <code>-1</code> 。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "aa"
<strong>输出：</strong>0
<strong>解释：</strong>最优的子字符串是两个 'a' 之间的空子字符串。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abca"
<strong>输出：</strong>2
<strong>解释：</strong>最优的子字符串是 "bc" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "cbzxy"
<strong>输出：</strong>-1
<strong>解释：</strong>s 中不存在出现出现两次的字符，所以返回 -1 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = "cabbac"
<strong>输出：</strong>4
<strong>解释：</strong>最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 300</code></li>
	<li><code>s</code> 只含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

用数组或哈希表记录字符串 $s$ 每个字符第一次出现的位置。由于本题中字符串 $s$ 只含小写英文字母，因此可以用一个长度为 $26$ 的数组 $d$ 来记录，初始时数组元素值均为 $-1$。

遍历字符串 $s$ 中每个字符 $c$，若 $c$ 在数组中的值为 $-1$，则更新为当前位置 $i$；否则我们将答案更新为当前位置 $i$ 与数组中的值 $d[c]$ 的差值的最大值减一，即 $ans = \max (ans, i - d[c]-1)$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串长度，而 $C$ 为字符串 $s$ 的字符集大小，本题 $C=26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxLengthBetweenEqualCharacters(self, s: str) -> int:
        d = {}
        ans = -1
        for i, c in enumerate(s):
            if c in d:
                ans = max(ans, i - d[c] - 1)
            else:
                d[c] = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] d = new int[26];
        Arrays.fill(d, -1);
        int ans = -1;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = Math.max(ans, i - d[j] - 1);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxLengthBetweenEqualCharacters(string s) {
        vector<int> d(26, -1);
        int ans = -1;
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = max(ans, i - d[j] - 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxLengthBetweenEqualCharacters(s string) int {
	d := make([]int, 26)
	for i := range d {
		d[i] = -1
	}
	ans := -1
	for i, c := range s {
		c -= 'a'
		if d[c] == -1 {
			d[c] = i
		} else {
			ans = max(ans, i-d[c]-1)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))

int maxLengthBetweenEqualCharacters(char *s) {
    int pos[26];
    memset(pos, -1, sizeof(pos));
    int n = strlen(s);
    int res = -1;
    for (int i = 0; i < n; i++) {
        char c = s[i];
        int j = c - 'a';
        if (pos[j] == -1) {
            pos[j] = i;
        } else {
            res = max(res, i - pos[j] - 1);
        }
    }
    return res;
}
```

### **TypeScript**

```ts
function maxLengthBetweenEqualCharacters(s: string): number {
    const n = s.length;
    const pos = new Array(26).fill(-1);
    let res = -1;
    for (let i = 0; i < n; i++) {
        const j = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (pos[j] === -1) {
            pos[j] = i;
        } else {
            res = Math.max(res, i - pos[j] - 1);
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut pos = [-1; 26];
        let mut res = -1;
        for i in 0..n {
            let j = (s[i] - b'a') as usize;
            let i = i as i32;
            if pos[j] == -1 {
                pos[j] = i;
            } else {
                res = res.max(i - pos[j] - 1);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
