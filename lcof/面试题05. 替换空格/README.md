# [面试题 05. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

## 题目描述

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

**示例 1：**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

**限制：**

- `0 <= s 的长度 <= 10000`

## 解法

使用 replace 替换即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        return s.replace(' ', '%20')
```

### **Java**

- 使用 replace：

```java
class Solution {
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
```

- 使用 StringBuilder：

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

- 使用字符串内置方法

```js
/**
 * @param {string} s
 * @return {string}
 */
var replaceSpace = function (s) {
    return s.split(" ").join("%20");
};
```

- 使用两个指针

```js
/**
 * @param {string}
 * @return {string}
 */
var replaceSpace = function (s) {
    s = s.split("");
    let emptyNum = 0;
    for (let i = 0; i < s.length; i++) {
        if (s[i] === " ") {
            emptyNum++;
        }
    }
    let p1 = s.length - 1;
    let p2 = p1 + 2 * emptyNum;
    while (p1 >= 0 && p2 > p1) {
        if (s[p1] === " ") {
            s[p2] = "0";
            s[--p2] = "2";
            s[--p2] = "%";
        } else {
            s[p2] = s[p1];
        }
        p1--;
        p2--;
    }
    return s.join("");
};
```

### **Go**

```go
func replaceSpace(s string) string {
    return strings.Replace(s, " ", "%20", -1 )
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

```ts
function replaceSpace(s: string): string {
    return s.replace(/\s/g, "%20");
}
```

### **...**

```

```

<!-- tabs:end -->
