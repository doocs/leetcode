---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2696.Minimum%20String%20Length%20After%20Removing%20Substrings/README.md
rating: 1282
tags:
    - 栈
    - 字符串
    - 模拟
---

# [2696. 删除子串后的字符串最小长度](https://leetcode.cn/problems/minimum-string-length-after-removing-substrings)

[English Version](/solution/2600-2699/2696.Minimum%20String%20Length%20After%20Removing%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由 <strong>大写</strong> 英文字符组成的字符串 <code>s</code> 。</p>

<p>你可以对此字符串执行一些操作，在每一步操作中，你可以从 <code>s</code> 中删除 <strong>任一个</strong> <code>"AB"</code> 或 <code>"CD"</code> 子字符串。</p>

<p>通过执行操作，删除所有&nbsp;<code>"AB"</code> 和 <code>"CD"</code> 子串，返回可获得的最终字符串的 <strong>最小</strong> 可能长度。</p>

<p><strong>注意</strong>，删除子串后，重新连接出的字符串可能会产生新的&nbsp;<code>"AB"</code> 或 <code>"CD"</code> 子串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ABFCACDB"
<strong>输出：</strong>2
<strong>解释：</strong>你可以执行下述操作：
- 从 "<em><strong>AB</strong></em>FCACDB" 中删除子串 "AB"，得到 s = "FCACDB" 。
- 从 "FCA<em><strong>CD</strong></em>B" 中删除子串 "CD"，得到 s = "FCAB" 。
- 从 "FC<strong><em>AB</em></strong>" 中删除子串 "AB"，得到 s = "FC" 。
最终字符串的长度为 2 。
可以证明 2 是可获得的最小长度。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ACBBD"
<strong>输出：</strong>5
<strong>解释：</strong>无法执行操作，字符串长度不变。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由大写英文字母组成</li>
</ul>

## 解法

### 方法一：栈

我们遍历字符串 $s$，对于当前遍历到的字符 $c$，如果栈不为空且栈顶元素 $top$ 与 $c$ 可以组成 $AB$ 或 $CD$，则弹出栈顶元素，否则将 $c$ 入栈。

最后栈中剩余的元素个数就是最终字符串的长度。

> 在实现上，我们可以在栈中预先放入一个空字符，这样就不需要在遍历字符串时判断栈是否为空了，最后返回栈的大小减一即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minLength(self, s: str) -> int:
        stk = [""]
        for c in s:
            if (c == "B" and stk[-1] == "A") or (c == "D" and stk[-1] == "C"):
                stk.pop()
            else:
                stk.append(c)
        return len(stk) - 1
```

```java
class Solution {
    public int minLength(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        stk.push(' ');
        for (char c : s.toCharArray()) {
            if ((c == 'B' && stk.peek() == 'A') || (c == 'D' && stk.peek() == 'C')) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.size() - 1;
    }
}
```

```cpp
class Solution {
public:
    int minLength(string s) {
        string stk = " ";
        for (char& c : s) {
            if ((c == 'B' && stk.back() == 'A') || (c == 'D' && stk.back() == 'C')) {
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk.size() - 1;
    }
};
```

```go
func minLength(s string) int {
	stk := []byte{' '}
	for _, c := range s {
		if (c == 'B' && stk[len(stk)-1] == 'A') || (c == 'D' && stk[len(stk)-1] == 'C') {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, byte(c))
		}
	}
	return len(stk) - 1
}
```

```ts
function minLength(s: string): number {
    const stk: string[] = [''];
    for (const c of s) {
        if (c === 'B' && stk.at(-1)! === 'A') {
            stk.pop();
        } else if (c === 'D' && stk.at(-1)! === 'C') {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length - 1;
}
```

```rust
impl Solution {
    pub fn min_length(s: String) -> i32 {
        let mut ans: Vec<u8> = Vec::new();

        for c in s.bytes() {
            if let Some(last) = ans.last() {
                if *last == b'A' && c == b'B' {
                    ans.pop();
                } else if *last == b'C' && c == b'D' {
                    ans.pop();
                } else {
                    ans.push(c);
                }
            } else {
                ans.push(c);
            }
        }

        ans.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
