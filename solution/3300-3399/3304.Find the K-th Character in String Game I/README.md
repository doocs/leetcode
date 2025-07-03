---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3304.Find%20the%20K-th%20Character%20in%20String%20Game%20I/README.md
rating: 1288
source: 第 417 场周赛 Q1
tags:
    - 位运算
    - 递归
    - 数学
    - 模拟
---

<!-- problem:start -->

# [3304. 找出第 K 个字符 I](https://leetcode.cn/problems/find-the-k-th-character-in-string-game-i)

[English Version](/solution/3300-3399/3304.Find%20the%20K-th%20Character%20in%20String%20Game%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 正在玩一个游戏。最初，Alice 有一个字符串 <code>word = "a"</code>。</p>

<p>给定一个<strong>正整数</strong> <code>k</code>。</p>

<p>现在 Bob 会要求 Alice 执行以下操作<strong> 无限次 </strong>:</p>

<ul>
	<li>将 <code>word</code> 中的每个字符<strong> 更改 </strong>为英文字母表中的<strong> 下一个 </strong>字符来生成一个新字符串，并将其<strong> 追加 </strong>到原始的 <code>word</code>。</li>
</ul>

<p>例如，对 <code>"c"</code> 进行操作生成 <code>"cd"</code>，对 <code>"zb"</code> 进行操作生成 <code>"zbac"</code>。</p>

<p>在执行足够多的操作后， <code>word</code> 中 <strong>至少 </strong>存在 <code>k</code> 个字符，此时返回 <code>word</code> 中第 <code>k</code> 个字符的值。</p>

<p><strong>注意</strong>，在操作中字符 <code>'z'</code> 可以变成 <code>'a'</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">k = 5</span></p>

<p><strong>输出：</strong><span class="example-io">"b"</span></p>

<p><strong>解释：</strong></p>

<p>最初，<code>word = "a"</code>。需要进行三次操作:</p>

<ul>
	<li>生成的字符串是 <code>"b"</code>，<code>word</code> 变为 <code>"ab"</code>。</li>
	<li>生成的字符串是 <code>"bc"</code>，<code>word</code> 变为 <code>"abbc"</code>。</li>
	<li>生成的字符串是 <code>"bccd"</code>，<code>word</code> 变为 <code>"abbcbccd"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">k = 10</span></p>

<p><strong>输出：</strong><span class="example-io">"c"</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 500</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以使用一个数组 $\textit{word}$ 来存储每次操作后的字符串，当 $\textit{word}$ 的长度小于 $k$ 时，我们不断地对 $\textit{word}$ 进行操作。

最后返回 $\textit{word}[k - 1]$ 即可。

时间复杂度 $O(k)$，空间复杂度 $O(k)$。其中 $k$ 为输入参数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthCharacter(self, k: int) -> str:
        word = [0]
        while len(word) < k:
            word.extend([(x + 1) % 26 for x in word])
        return chr(ord("a") + word[k - 1])
```

#### Java

```java
class Solution {
    public char kthCharacter(int k) {
        List<Integer> word = new ArrayList<>();
        word.add(0);
        while (word.size() < k) {
            for (int i = 0, m = word.size(); i < m; ++i) {
                word.add((word.get(i) + 1) % 26);
            }
        }
        return (char) ('a' + word.get(k - 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    char kthCharacter(int k) {
        vector<int> word;
        word.push_back(0);
        while (word.size() < k) {
            int m = word.size();
            for (int i = 0; i < m; ++i) {
                word.push_back((word[i] + 1) % 26);
            }
        }
        return 'a' + word[k - 1];
    }
};
```

#### Go

```go
func kthCharacter(k int) byte {
	word := []int{0}
	for len(word) < k {
		m := len(word)
		for i := 0; i < m; i++ {
			word = append(word, (word[i]+1)%26)
		}
	}
	return 'a' + byte(word[k-1])
}
```

#### TypeScript

```ts
function kthCharacter(k: number): string {
    const word: number[] = [0];
    while (word.length < k) {
        word.push(...word.map(x => (x + 1) % 26));
    }
    return String.fromCharCode(97 + word[k - 1]);
}
```

#### Rust

```rust
impl Solution {
    pub fn kth_character(k: i32) -> char {
        let mut word = vec![0];
        while word.len() < k as usize {
            let m = word.len();
            for i in 0..m {
                word.push((word[i] + 1) % 26);
            }
        }
        (b'a' + word[(k - 1) as usize] as u8) as char
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
