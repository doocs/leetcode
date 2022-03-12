# [524. 通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting)

[English Version](/solution/0500-0599/0524.Longest%20Word%20in%20Dictionary%20through%20Deleting/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符串数组 <code>dictionary</code> ，找出并返回&nbsp;<code>dictionary</code> 中最长的字符串，该字符串可以通过删除 <code>s</code> 中的某些字符得到。</p>

<p>如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
<strong>输出：</strong>"apple"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abpcplea", dictionary = ["a","b","c"]
<strong>输出：</strong>"a"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 1000</code></li>
	<li><code>s</code> 和 <code>dictionary[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
function findLongestWord(s: string, dictionary: string[]): string {
    dictionary.sort((a, b) => {
        if (a.length === b.length) {
            return b < a ? 1 : -1;
        }
        return b.length - a.length;
    });
    const n = s.length;
    for (const target of dictionary) {
        const m = target.length;
        if (m > n) {
            continue;
        }
        let i = 0;
        let j = 0;
        while (i < n && j < m) {
            if (s[i] === target[j]) {
                j++;
            }
            i++;
        }
        if (j === m) {
            return target;
        }
    }
    return '';
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_longest_word(s: String, mut dictionary: Vec<String>) -> String {
        dictionary.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for target in dictionary {
            let target: Vec<char> = target.chars().collect();
            let n = target.len();
            let mut i = 0;
            for c in s.chars() {
                if i == n {
                    break;
                }
                if c == target[i] {
                    i += 1;
                }
            }
            if i == n {
                return target.iter().collect();
            }
        }
        String::new()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
