# [面试题 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

## 题目描述

<p>请实现一个函数，把字符串 <code>s</code> 中的每个空格替换成&quot;%20&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;We are happy.&quot;
<strong>输出：</strong>&quot;We%20are%20happy.&quot;</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 10000</code></p>

## 解法

-   使用 `replace()` 替换。
-   遍历添加。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        return s.replace(' ', '%20')
```

### **Java**

使用 replace：

```java
class Solution {
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
```

使用 StringBuilder：

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            sb.append(c == ' ' ? "%20" : c);
        }
        return sb.toString();
    }
}
```

### **JavaScript**

使用字符串内置方法：

```js
/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    return s.split(' ').join('%20');
};
```

双指针：

```js
/**
 * @param {string}
 * @return {string}
 */
var replaceSpace = function (s) {
    s = s.split('');
    let emptyNum = 0;
    for (let i = 0; i < s.length; i++) {
        if (s[i] === ' ') {
            emptyNum++;
        }
    }
    let p1 = s.length - 1;
    let p2 = p1 + 2 * emptyNum;
    while (p1 >= 0 && p2 > p1) {
        if (s[p1] === ' ') {
            s[p2] = '0';
            s[--p2] = '2';
            s[--p2] = '%';
        } else {
            s[p2] = s[p1];
        }
        p1--;
        p2--;
    }
    return s.join('');
};
```

### **Go**

```go
func replaceSpace(s string) string {
	return strings.Replace(s, " ", "%20", -1)
}
```

### **C++**

```cpp
class Solution {
public:
    string replaceSpace(string s) {
        string ans;
        for (char ch : s) {
            if (ch == ' ')
                ans += "%20";
            else
                ans += ch;
        }
        return ans;
    }
};
```

### **TypeScript**

使用 `replace()`：

```ts
function replaceSpace(s: string): string {
    return s.replace(/\s/g, '%20');
}
```

遍历添加：

```ts
function replaceSpace(s: string): string {
    const strArr = [];
    for (const c of s) {
        strArr.push(c === ' ' ? '%20' : c);
    }
    return strArr.join('');
}
```

### **Rust**

使用 `replace()`：

```rust
impl Solution {
    pub fn replace_space(s: String) -> String {
        s.replace(' ', "%20")
    }
}
```

遍历添加：

```rust
impl Solution {
    pub fn replace_space(s: String) -> String {
        let mut result = String::new();
        for c in s.chars() {
            if c == ' ' {
                result.push_str("%20");
            } else {
                result.push(c);
            }
        }
        result
    }
}
```

### **C#**

使用 `Replace()`：

```cs
public class Solution {
    public string ReplaceSpace(string s) {
        return s.Replace(" ", "%20");
    }
}
```

遍历添加：

```cs
public class Solution {
    public string ReplaceSpace(string s) {
        StringBuilder res = new StringBuilder();
        foreach (var c in s) {
            if (c == ' ') {
                res.Append("%20");
            } else {
                res.Append(c);
            }
        }
        return res.ToString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
