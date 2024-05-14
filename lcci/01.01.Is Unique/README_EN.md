---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.01.Is%20Unique/README_EN.md
---

# [01.01. Is Unique](https://leetcode.cn/problems/is-unique-lcci)

[中文文档](/lcci/01.01.Is%20Unique/README.md)

## Description

<p>Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong> = &quot;leetcode&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s = &quot;abc&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>0 &lt;= len(s) &lt;= 100 </code></li>
</ul>

## Solutions

### Solution 1: Bit Manipulation

Based on the examples, we can assume that the string only contains lowercase letters (which is confirmed by actual verification).

Therefore, we can use each bit of a $32$-bit integer `mask` to represent whether each character in the string has appeared.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        mask = 0
        for c in astr:
            i = ord(c) - ord('a')
            if (mask >> i) & 1:
                return False
            mask |= 1 << i
        return True
```

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

<!-- end -->
