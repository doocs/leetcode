# [2396. 严格回文的数字](https://leetcode.cn/problems/strictly-palindromic-number)

[English Version](/solution/2300-2399/2396.Strictly%20Palindromic%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个整数 <code>n</code>&nbsp;在 <code>b</code>&nbsp;进制下（<code>b</code>&nbsp;为 <code>2</code>&nbsp;到 <code>n - 2</code>&nbsp;之间的所有整数）对应的字符串&nbsp;<strong>全部</strong>&nbsp;都是 <strong>回文的</strong>&nbsp;，那么我们称这个数&nbsp;<code>n</code>&nbsp;是 <strong>严格回文</strong>&nbsp;的。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，如果 <code>n</code>&nbsp;是 <strong>严格回文</strong>&nbsp;的，请返回&nbsp;<code>true</code> ，否则返回<em>&nbsp;</em><code>false</code>&nbsp;。</p>

<p>如果一个字符串从前往后读和从后往前读完全相同，那么这个字符串是 <strong>回文的</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 9
<b>输出：</b>false
<b>解释：</b>在 2 进制下：9 = 1001 ，是回文的。
在 3 进制下：9 = 100 ，不是回文的。
所以，9 不是严格回文数字，我们返回 false 。
注意在 4, 5, 6 和 7 进制下，n = 9 都不是回文的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 4
<b>输出：</b>false
<b>解释：</b>我们只考虑 2 进制：4 = 100 ，不是回文的。
所以我们返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

当 $n=4$ 时，二进制表示为 $100$，不是回文串；

当 $n \gt 4$ 时，此时 $n-2$ 的二进制表示为 $12$，不是回文串。

因此，我们直接返回 `false` 即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isStrictlyPalindromic(self, n: int) -> bool:
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isStrictlyPalindromic(int n) {
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isStrictlyPalindromic(int n) {
        return false;
    }
};
```

### **Go**

```go
func isStrictlyPalindromic(n int) bool {
	return false
}
```

### **TypeScript**

```ts
function isStrictlyPalindromic(n: number): boolean {
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_strictly_palindromic(n: i32) -> bool {
        false
    }
}
```

### **C**

```c
bool isStrictlyPalindromic(int n) {
    return 0;
}
```

### **...**

```


```

<!-- tabs:end -->
