# [1768. 交替合并字符串](https://leetcode.cn/problems/merge-strings-alternately)

[English Version](/solution/1700-1799/1768.Merge%20Strings%20Alternately/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>word1</code> 和 <code>word2</code> 。请你从 <code>word1</code> 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。</p>

<p>返回 <strong>合并后的字符串</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = "abc", word2 = "pqr"
<strong>输出：</strong>"apbqcr"
<strong>解释：</strong>字符串合并情况如下所示：
word1：  a   b   c
word2：    p   q   r
合并后：  a p b q c r
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = "ab", word2 = "pqrs"
<strong>输出：</strong>"apbqrs"
<strong>解释：</strong>注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
word1：  a   b 
word2：    p   q   r   s
合并后：  a p b q   r   s
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word1 = "abcd", word2 = "pq"
<strong>输出：</strong>"apbqcd"
<strong>解释：</strong>注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
word1：  a   b   c   d
word2：    p   q 
合并后：  a p b q c   d
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word1.length, word2.length <= 100</code></li>
	<li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接模拟**

遍历 `word1`, `word2` 两个字符串，依次取出字符，拼接到结果字符串中。Python 代码可以简化为一行。

时间复杂度 $O(m + n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是两个字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        return ''.join(a + b for a, b in zip_longest(word1, word2, fillvalue=''))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) {
                ans.append(word1.charAt(i));
            }
            if (i < n) {
                ans.append(word2.charAt(i));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string mergeAlternately(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        string ans;
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) ans += word1[i];
            if (i < n) ans += word2[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func mergeAlternately(word1 string, word2 string) string {
	m, n := len(word1), len(word2)
	ans := make([]byte, 0, m+n)
	for i := 0; i < m || i < n; i++ {
		if i < m {
			ans = append(ans, word1[i])
		}
		if i < n {
			ans = append(ans, word2[i])
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function mergeAlternately(word1: string, word2: string): string {
    const res = [];
    const n = Math.max(word1.length, word2.length);
    for (let i = 0; i < n; i++) {
        word1[i] && res.push(word1[i]);
        word2[i] && res.push(word2[i]);
    }
    return res.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn merge_alternately(word1: String, word2: String) -> String {
        let s1 = word1.as_bytes();
        let s2 = word2.as_bytes();
        let n = s1.len().max(s2.len());
        let mut res = vec![];
        for i in 0..n {
            if s1.get(i).is_some() {
                res.push(s1[i]);
            }
            if s2.get(i).is_some() {
                res.push(s2[i]);
            }
        }
        String::from_utf8(res).unwrap()
    }
}
```

### **C**

```c
char *mergeAlternately(char *word1, char *word2) {
    int m = strlen(word1);
    int n = strlen(word2);
    char *ans = malloc(sizeof(char) * (n + m + 1));
    int i = 0;
    int j = 0;
    while (i + j != m + n) {
        if (i < m) {
            ans[i + j] = word1[i];
            i++;
        }
        if (j < n) {
            ans[i + j] = word2[j];
            j++;
        }
    }
    ans[n + m] = '\0';
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
