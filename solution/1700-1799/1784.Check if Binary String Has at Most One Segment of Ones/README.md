---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1784.Check%20if%20Binary%20String%20Has%20at%20Most%20One%20Segment%20of%20Ones/README.md
rating: 1206
source: 第 231 场周赛 Q1
tags:
    - 字符串
---

# [1784. 检查二进制字符串字段](https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones)

[English Version](/solution/1700-1799/1784.Check%20if%20Binary%20String%20Has%20at%20Most%20One%20Segment%20of%20Ones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>s</code> ，该字符串 <strong>不含前导零</strong> 。</p>

<p>如果 <code>s</code> 包含 <strong>零个或一个由连续的 <code>'1'</code> 组成的字段</strong> ，返回 <code>true</code>​​​ 。否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1001"
<strong>输出：</strong>false
<strong>解释：</strong>由连续若干个&nbsp;<code>'1'</code> 组成的字段数量为 2，返回 false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "110"
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code>​​​​ 为 <code>'0'</code> 或 <code>'1'</code></li>
	<li><code>s[0]</code> 为 <code>'1'</code></li>
</ul>

## 解法

### 方法一：0 后面不能有 1

注意到字符串 $s$ 不含前导零，说明 $s$ 以 '1' 开头。

若字符串 $s$ 存在 "01" 串，那么 $s$ 就是形如 "1...01..." 的字符串，此时 $s$ 出现了至少两个连续的 '1' 片段，不满足题意，返回 `false`。

若字符串 $s$ 不存在 "01" 串，那么 $s$ 只能是形如 "1..1000..." 的字符串，此时 $s$ 只有一个连续的 '1' 片段，满足题意，返回 `true`。

因此，只需要判断字符串 $s$ 是否存在 "01" 串即可。

时间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        return '01' not in s
```

```java
class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
```

```cpp
class Solution {
public:
    bool checkOnesSegment(string s) {
        return s.find("01") == -1;
    }
};
```

```go
func checkOnesSegment(s string) bool {
	return !strings.Contains(s, "01")
}
```

```ts
function checkOnesSegment(s: string): boolean {
    let pre = s[0];
    for (const c of s) {
        if (pre !== c && c === '1') {
            return false;
        }
        pre = c;
    }
    return true;
}
```

```rust
impl Solution {
    pub fn check_ones_segment(s: String) -> bool {
        !s.contains("01")
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```ts
function checkOnesSegment(s: string): boolean {
    return !s.includes('01');
}
```

<!-- tabs:end -->

<!-- end -->
