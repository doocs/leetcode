---
comment: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.06.Compress%20String/README.md
---

# [面试题 01.06. 字符串压缩](https://leetcode.cn/problems/compress-string-lcci)

[English Version](/lcci/01.06.Compress%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串<code>aabcccccaaa</code>会变为<code>a2b1c5a3</code>。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>："aabcccccaaa"
<strong> 输出</strong>："a2b1c5a3"
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>："abbccd"
<strong> 输出</strong>："abbccd"
<strong> 解释</strong>："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
</pre>

<p><strong>提示：</strong></p>

<ol>
<li>字符串长度在[0, 50000]范围内。</li>
</ol>

## 解法

### 方法一：双指针

我们可以利用双指针找出每个连续字符的起始位置和结束位置，计算出连续字符的长度，然后将字符和长度拼接到字符串 $t$ 中。

最后，我们比较 $t$ 和 $S$ 的长度，如果 $t$ 的长度小于 $S$，则返回 $t$，否则返回 $S$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

```python
class Solution:
    def compressString(self, S: str) -> str:
        t = "".join(a + str(len(list(b))) for a, b in groupby(S))
        return min(S, t, key=len)
```

```python
class Solution:
    def compressString(self, S: str) -> str:
        t = []
        i, n = 0, len(S)
        while i < n:
            j = i + 1
            while j < n and S[j] == S[i]:
                j += 1
            t.append(S[i] + str(j - i))
            i = j
        return min(S, "".join(t), key=len)
```

```java
class Solution {
    public String compressString(String S) {
        int n = S.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S.charAt(j) == S.charAt(i)) {
                ++j;
            }
            sb.append(S.charAt(i)).append(j - i);
            i = j;
        }
        String t = sb.toString();
        return t.length() < n ? t : S;
    }
}
```

```cpp
class Solution {
public:
    string compressString(string S) {
        int n = S.size();
        string t;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S[j] == S[i]) {
                ++j;
            }
            t += S[i];
            t += to_string(j - i);
            i = j;
        }
        return t.size() < n ? t : S;
    }
};
```

```go
func compressString(S string) string {
	n := len(S)
	sb := strings.Builder{}
	for i := 0; i < n; {
		j := i + 1
		for j < n && S[j] == S[i] {
			j++
		}
		sb.WriteByte(S[i])
		sb.WriteString(strconv.Itoa(j - i))
		i = j
	}
	if t := sb.String(); len(t) < n {
		return t
	}
	return S
}
```

```rust
impl Solution {
    pub fn compress_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let mut t = Vec::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            let mut j = i + 1;
            while j < n && cs[j] == cs[i] {
                j += 1;
            }
            t.push(cs[i]);
            t.extend((j - i).to_string().chars());
            i = j;
        }

        let t = t.into_iter().collect::<String>();
        if s.len() <= t.len() {
            s
        } else {
            t
        }
    }
}
```

```js
/**
 * @param {string} S
 * @return {string}
 */
var compressString = function (S) {
    const n = S.length;
    const t = [];
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && S.charAt(j) === S.charAt(i)) {
            ++j;
        }
        t.push(S.charAt(i), j - i);
        i = j;
    }
    return t.length < n ? t.join('') : S;
};
```

```swift
class Solution {
    func compressString(_ S: String) -> String {
        let n = S.count
        var compressed = ""
        var i = 0

        while i < n {
            var j = i
            let currentChar = S[S.index(S.startIndex, offsetBy: i)]
            while j < n && S[S.index(S.startIndex, offsetBy: j)] == currentChar {
                j += 1
            }
            compressed += "\(currentChar)\(j - i)"
            i = j
        }

        return compressed.count < n ? compressed : S
    }
}
```

<!-- tabs:end -->

<!-- end -->
