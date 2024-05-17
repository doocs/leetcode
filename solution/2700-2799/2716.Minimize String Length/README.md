---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2716.Minimize%20String%20Length/README.md
rating: 1242
source: 第 348 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [2716. 最小化字符串长度](https://leetcode.cn/problems/minimize-string-length)

[English Version](/solution/2700-2799/2716.Minimize%20String%20Length/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，重复执行下述操作 <strong>任意</strong> 次：</p>

<ul>
	<li>在字符串中选出一个下标 <code>i</code> ，并使 <code>c</code> 为字符串下标 <code>i</code> 处的字符。并在 <code>i</code> <strong>左侧</strong>（如果有）和 <strong>右侧</strong>（如果有）各 <strong>删除 </strong>一个距离 <code>i</code> <strong>最近</strong> 的字符 <code>c</code> 。</li>
</ul>

<p>请你通过执行上述操作任意次，使 <code>s</code> 的长度 <strong>最小化</strong> 。</p>

<p>返回一个表示 <strong>最小化</strong> 字符串的长度的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabc"
<strong>输出：</strong>3
<strong>解释：</strong>在这个示例中，s 等于 "aaabc" 。我们可以选择位于下标 1 处的字符 'a' 开始。接着删除下标 1 左侧最近的那个 'a'（位于下标 0）以及下标 1 右侧最近的那个 'a'（位于下标 2）。执行操作后，字符串变为 "abc" 。继续对字符串执行任何操作都不会改变其长度。因此，最小化字符串的长度是 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "cbbd"
<strong>输出：</strong>3
<strong>解释：</strong>我们可以选择位于下标 1 处的字符 'b' 开始。下标 1 左侧不存在字符 'b' ，但右侧存在一个字符 'b'（位于下标 2），所以会删除位于下标 2 的字符 'b' 。执行操作后，字符串变为 "cbd" 。继续对字符串执行任何操作都不会改变其长度。因此，最小化字符串的长度是 3 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "dddaaa"
<strong>输出：</strong>2
<strong>解释：</strong>我们可以选择位于下标 1 处的字符 'd' 开始。接着删除下标 1 左侧最近的那个 'd'（位于下标 0）以及下标 1 右侧最近的那个 'd'（位于下标 2）。执行操作后，字符串变为 "daaa" 。继续对新字符串执行操作，可以选择位于下标 2 的字符 'a' 。接着删除下标 2 左侧最近的那个 'a'（位于下标 1）以及下标 2 右侧最近的那个 'a'（位于下标 3）。执行操作后，字符串变为 "da" 。继续对字符串执行任何操作都不会改变其长度。因此，最小化字符串的长度是 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

题目实际上可以转化为求字符串中不同字符的个数，因此，我们只需要统计字符串中不同字符的个数即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串的长度；而 $C$ 是字符集的大小，本题中字符集为小写英文字母，因此 $C=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimizedStringLength(self, s: str) -> int:
        return len(set(s))
```

#### Java

```java
class Solution {
    public int minimizedStringLength(String s) {
        Set<Character> ss = new HashSet<>();
        for (int i = 0; i < s.length(); ++i) {
            ss.add(s.charAt(i));
        }
        return ss.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimizedStringLength(string s) {
        unordered_set<char> ss(s.begin(), s.end());
        return ss.size();
    }
};
```

#### Go

```go
func minimizedStringLength(s string) int {
	ss := map[rune]struct{}{}
	for _, c := range s {
		ss[c] = struct{}{}
	}
	return len(ss)
}
```

#### TypeScript

```ts
function minimizedStringLength(s: string): number {
    return new Set(s.split('')).size;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut hash = HashMap::new();

        for c in s.chars() {
            hash.insert(c, true);
        }

        hash.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn minimized_string_length(s: String) -> i32 {
        let mut set = HashSet::new();

        for c in s.chars() {
            set.insert(c);
        }

        set.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
