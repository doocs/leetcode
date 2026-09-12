---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [面试题 01.01. 判定字符是否唯一](https://leetcode.cn/problems/is-unique-lcci)

[English Version](/lcci/01.01.Is%20Unique/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现一个算法，确定一个字符串 <code>s</code> 的所有字符是否全都不同。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = &quot;leetcode&quot;
<strong>输出:</strong> false
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = &quot;abc&quot;
<strong>输出:</strong> true
</pre>

<p><strong>限制：</strong></p>
<ul>
	<li><code>0 <= len(s) <= 100 </code></li>
	<li>如果你不使用额外的数据结构，会很加分。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

<!-- thinking:start -->

> **思考**
>
> 若用哈希表记录已出现字符，一次扫描即可判定，时间 $O(n)$，空间与字符集规模成正比。题目限制 $n \le 100$，该做法可以通过；进阶要求不使用额外数据结构。
>
> 在仅含小写字母的前提下，字符种类至多为 $26$，可用一个整数的各位表示「某字母是否已出现」。遍历到字符 $c$ 时，先查对应位：若已为 $1$，则存在重复；否则将该位置 $1$。
>
> 位运算把集合查询与插入都落到常数时间与常数空间，因此选用掩码而非哈希表或布尔数组。

<!-- thinking:end -->

根据示例，可以假定字符串中只包含小写字母（实际验证，也符合假设）。

因此，我们可以使用一个 $32$ 位整数 `mask` 的每一位来表示字符串中的每一个字符是否出现过。

时间复杂度 $O(n)$，其中 $n$ 为字符串长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for i in map(lambda c: ord(c) - ord("a"), astr):
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
```

#### Java

```java
class Solution {
    public boolean isUnique(String astr) {
        int mask = 0;
        for (char c : astr.toCharArray()) {
            int i = c - 'a';
            if (((mask >> i) & 1) == 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isUnique(string astr) {
        int mask = 0;
        for (char c : astr) {
            int i = c - 'a';
            if (mask >> i & 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
};
```

#### Go

```go
func isUnique(astr string) bool {
	mask := 0
	for _, c := range astr {
		i := c - 'a'
		if mask>>i&1 == 1 {
			return false
		}
		mask |= 1 << i
	}
	return true
}
```

#### TypeScript

```ts
function isUnique(astr: string): boolean {
    let mask = 0;
    for (let j = 0; j < astr.length; ++j) {
        const i = astr.charCodeAt(j) - 'a'.charCodeAt(0);
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let mask = 0;
    for (const c of astr) {
        const i = c.charCodeAt() - 'a'.charCodeAt();
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
};
```

#### Swift

```swift
class Solution {
    func isUnique(_ astr: String) -> Bool {
        var mask = 0
        for c in astr {
            let i = Int(c.asciiValue! - Character("a").asciiValue!)
            if (mask >> i) & 1 != 0 {
                return false
            }
            mask |= 1 << i
        }
        return true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
