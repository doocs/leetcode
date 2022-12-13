# [1832. 判断句子是否为全字母句](https://leetcode.cn/problems/check-if-the-sentence-is-pangram)

[English Version](/solution/1800-1899/1832.Check%20if%20the%20Sentence%20Is%20Pangram/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>全字母句</strong> 指包含英语字母表中每个字母至少一次的句子。</p>

<p>给你一个仅由小写英文字母组成的字符串 <code>sentence</code> ，请你判断 <code>sentence</code> 是否为 <strong>全字母句</strong> 。</p>

<p>如果是，返回<em> </em><code>true</code> ；否则，返回<em> </em><code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "thequickbrownfoxjumpsoverthelazydog"
<strong>输出：</strong>true
<strong>解释：</strong><code>sentence</code> 包含英语字母表中每个字母至少一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "leetcode"
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sentence.length <= 1000</code></li>
	<li><code>sentence</code> 由小写英语字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

遍历字符串 `sentence`，用数组或哈希表记录出现过的字母，最后判断数组或哈希表中是否有 $26$ 个字母即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 `sentence` 的长度，而 $C$ 为字符集大小。本题中 $C = 26$。

**方法二：位运算**

我们也可以用一个整数 $mask$ 记录出现过的字母，其中 $mask$ 的第 $i$ 位表示第 $i$ 个字母是否出现过。

最后判断 $mask$ 的二进制表示中是否有 $26$ 个 $1$，也即判断 $mask$ 是否等于 $2^{26} - 1$。若是，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 `sentence` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        return len(set(sentence)) == 26
```

```python
class Solution:
    def checkIfPangram(self, sentence: str) -> bool:
        mask = 0
        for c in sentence:
            mask |= 1 << (ord(c) - ord('a'))
        return mask == (1 << 26) - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] vis = new boolean[26];
        for (int i = 0; i < sentence.length(); ++i) {
            vis[sentence.charAt(i) - 'a'] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        int mask = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            mask |= 1 << (sentence.charAt(i) - 'a');
        }
        return mask == (1 << 26) - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkIfPangram(string sentence) {
        int vis[26] = {0};
        for (char& c : sentence) vis[c - 'a'] = 1;
        for (int& v : vis) if (!v) return false;
        return true;
    }
};
```

```cpp
class Solution {
public:
    bool checkIfPangram(string sentence) {
        int mask = 0;
        for (char& c : sentence) mask |= 1 << (c - 'a');
        return mask == (1 << 26) - 1;
    }
};
```

### **Go**

```go
func checkIfPangram(sentence string) bool {
	vis := [26]bool{}
	for _, c := range sentence {
		vis[c-'a'] = true
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
```

```go
func checkIfPangram(sentence string) bool {
	mask := 0
	for _, c := range sentence {
		mask |= 1 << int(c-'a')
	}
	return mask == 1<<26-1
}
```

### **TypeScript**

```ts
function checkIfPangram(sentence: string): boolean {
    const vis = new Array(26).fill(false);
    for (const c of sentence) {
        vis[c.charCodeAt(0) - 'a'.charCodeAt(0)] = true;
    }
    return vis.every(v => v);
}
```

```ts
function checkIfPangram(sentence: string): boolean {
    let mark = 0;
    for (const c of sentence) {
        mark |= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
    }
    return mark === (1 << 26) - 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_if_pangram(sentence: String) -> bool {
        let mut vis = [false; 26];
        for c in sentence.as_bytes() {
            vis[(*c - b'a') as usize] = true;
        }
        vis.iter().all(|v| *v)
    }
}
```

```rust
impl Solution {
    pub fn check_if_pangram(sentence: String) -> bool {
        let mut mark = 0;
        for c in sentence.as_bytes() {
            mark |= 1 << *c - b'a';
        }
        mark == (1 << 26) - 1
    }
}
```

### **C**

```c
bool checkIfPangram(char *sentence) {
    int vis[26] = {0};
    for (int i = 0; sentence[i]; i++) {
        vis[sentence[i] - 'a'] = 1;
    }
    for (int i = 0; i < 26; i++) {
        if (!vis[i]) {
            return 0;
        }
    }
    return 1;
}
```

```c
bool checkIfPangram(char *sentence) {
    int mark = 0;
    for (int i = 0; sentence[i]; i++) {
        mark |= 1 << (sentence[i] - 'a');
    }
    return mark == (1 << 26) - 1;
}
```

### **...**

```

```

<!-- tabs:end -->
