# [1249. 移除无效的括号](https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses)

[English Version](/solution/1200-1299/1249.Minimum%20Remove%20to%20Make%20Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>'('</code>、<code>')'</code> 和小写字母组成的字符串 <code>s</code>。</p>

<p>你需要从字符串中删除最少数目的 <code>'('</code> 或者 <code>')'</code>&nbsp;（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。</p>

<p>请返回任意一个合法字符串。</p>

<p>有效「括号字符串」应当符合以下&nbsp;<strong>任意一条&nbsp;</strong>要求：</p>

<ul>
	<li>空字符串或只包含小写字母的字符串</li>
	<li>可以被写作&nbsp;<code>AB</code>（<code>A</code>&nbsp;连接&nbsp;<code>B</code>）的字符串，其中&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;都是有效「括号字符串」</li>
	<li>可以被写作&nbsp;<code>(A)</code>&nbsp;的字符串，其中&nbsp;<code>A</code>&nbsp;是一个有效的「括号字符串」</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "lee(t(c)o)de)"
<strong>输出：</strong>"lee(t(c)o)de"
<strong>解释：</strong>"lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a)b(c)d"
<strong>输出：</strong>"ab(c)d"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "))(("
<strong>输出：</strong>""
<strong>解释：</strong>空字符串也是有效的
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;可能是&nbsp;<code>'('</code>、<code>')'</code>&nbsp;或英文小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

主体分为两步：

1. 遍历字符串 `s`，统计其中**成对**的括号数量。流程：
    1. 从前往后遍历 `s`，统计 `(` 的数量。
    2. 当遇到 `)` 时，对比当前 `(` 记录的数量，在 `)` 统计数量不超过 `(` 时才可算有效的数据。
        > `)` 有效的前提：在其前方存在对应的 `(`。
2. 再次遍历，生成返回值。流程：
    1. 准备一变量，记录加入返回值当中 `(` 的数量。
    2. 遍历字符串 `s`，流程：
        - 遇到 `(`
            - 确定当前还缺少成对括号时（查看成对统计数据），才让其加入，并使 `(` 统计数量 + 1。
        - 遇到 `)`
            - 确定前方存在 `(`（查看 `(` 统计数量）时，才让其加入，并删减 `(` 统计数据与成对数量（-1 操作）。
        - 其他字符串正常加入。

疑问：为什么要同时调整 `(` 统计数据与成对数量？

-   需要确保前方存在可用的 `(`，才能安心放入 `)`。
-   而删减成对数量是防止放入过量的 `(`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function minRemoveToMakeValid(s: string): string {
    let left = 0;
    let right = 0;
    for (const c of s) {
        if (c === '(') {
            left++;
        } else if (c === ')') {
            if (right < left) {
                right++;
            }
        }
    }

    let hasLeft = 0;
    let res = '';
    for (const c of s) {
        if (c === '(') {
            if (hasLeft < right) {
                hasLeft++;
                res += c;
            }
        } else if (c === ')') {
            if (hasLeft != 0 && right !== 0) {
                right--;
                hasLeft--;
                res += c;
            }
        } else {
            res += c;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_remove_to_make_valid(s: String) -> String {
        let bs = s.as_bytes();
        let mut right = {
            let mut left = 0;
            let mut right = 0;
            for c in bs.iter() {
                match c {
                    &b'(' => left += 1,
                    &b')' if right < left => right += 1,
                    _ => {}
                }
            }
            right
        };
        let mut has_left = 0;
        let mut res = vec![];
        for c in bs.iter() {
            match c {
                &b'(' => {
                    if has_left < right {
                        has_left += 1;
                        res.push(*c);
                    }
                }
                &b')' => {
                    if has_left != 0 && right != 0 {
                        right -= 1;
                        has_left -= 1;
                        res.push(*c);
                    }
                }
                _ => {
                    res.push(*c);
                }
            }
        }
        String::from_utf8_lossy(&res).to_string()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
