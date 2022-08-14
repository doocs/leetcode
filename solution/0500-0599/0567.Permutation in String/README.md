# [567. 字符串的排列](https://leetcode.cn/problems/permutation-in-string)

[English Version](/solution/0500-0599/0567.Permutation%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
<strong>输出：</strong>true
<strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
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
function checkInclusion(s1: string, s2: string): boolean {
    // 滑动窗口方案
    if (s1.length > s2.length) {
        return false;
    }

    const n = s1.length;
    const m = s2.length;

    const toCode = (s: string) => s.charCodeAt(0) - 97;
    const isMatch = () => {
        for (let i = 0; i < 26; i++) {
            if (arr1[i] !== arr2[i]) {
                return false;
            }
        }
        return true;
    };

    const arr1 = new Array(26).fill(0);
    for (const s of s1) {
        const index = toCode(s);
        arr1[index]++;
    }

    const arr2 = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        const index = toCode(s2[i]);
        arr2[index]++;
    }

    for (let l = 0, r = n; r < m; l++, r++) {
        if (isMatch()) {
            return true;
        }

        const i = toCode(s2[l]);
        const j = toCode(s2[r]);
        arr2[i]--;
        arr2[j]++;
    }
    return isMatch();
}
```

### **Rust**

```rust
use std::collections::HashMap;


impl Solution {
    // 测试两个哈希表是否匹配
    fn is_match(m1: &HashMap<char, i32>, m2: &HashMap<char, i32>) -> bool {
        for (k, v) in m1.iter() {
            if m2.get(k).unwrap_or(&0) != v {
                return false;
            }
        }
        true
    }
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        if s1.len() > s2.len() {
            return false;
        }
        let mut m1 = HashMap::new();
        let mut m2 = HashMap::new();
        // 初始化表 1
        for c in s1.chars() {
            m1.insert(c, m1.get(&c).unwrap_or(&0) + 1);
        }
        let cs: Vec<char> = s2.chars().collect();
        // 初始化窗口
        let mut i = 0;
        while i < s1.len() {
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            i += 1;
        }
        if Self::is_match(&m1, &m2) {
            return true;
        }
        // 持续滑动窗口，直到匹配或超出边界
        let mut j = 0;
        while i < cs.len() {
            m2.insert(cs[j], m2.get(&cs[j]).unwrap_or(&1) - 1);
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            j += 1;
            i += 1;
            if Self::is_match(&m1, &m2) {
                return true;
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
