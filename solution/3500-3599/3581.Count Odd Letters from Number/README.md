---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3581.Count%20Odd%20Letters%20from%20Number/README.md
tags:
    - 哈希表
    - 字符串
    - 计数
    - 模拟
---

<!-- problem:start -->

# [3581. 计算数字中的奇数字母数量 🔒](https://leetcode.cn/problems/count-odd-letters-from-number)

[English Version](/solution/3500-3599/3581.Count%20Odd%20Letters%20from%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你被给定一个整数 <code>n</code>，执行以下步骤：</p>

<ul>
	<li>将&nbsp;<code>n</code>&nbsp;的每个数位转换为它的小写英文单词（例如 4 → "four", 1 → "one"）。</li>
	<li>将那些单词按照 <strong>原始数字顺序</strong> <strong>连接</strong> 起来形成一个字符串 <code>s</code>。</li>
</ul>

<p>返回字符串 <code>s</code> 中出现 <strong>奇数</strong> 次的 <strong>不同</strong> 字符的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 41</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>41 → <code>"fourone"</code></p>

<p>出现奇数次的字母：<code>'f'</code>，<code>'u'</code>，<code>'r'</code>，<code>'n'</code>，<code>'e'</code>。因此，答案为 5。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 20</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>20 → <code>"twozero"</code></p>

<p>出现奇数次的字母：<code>'t'</code>，<code>'w'</code>，<code>'z'</code>，<code>'e'</code>，<code>'r'</code>。因此，答案为 5。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟 + 位运算

我们可以将每个数字转换为对应的英文单词，然后统计每个字母出现的次数。由于字母的数量有限，我们可以使用一个整数 $\textit{mask}$ 来表示每个字母的出现情况。具体地，我们可以将字母映射到整数的二进制位上，如果某个字母出现了奇数次，则对应的二进制位为 1，否则为 0。最后，我们只需要统计 $\textit{mask}$ 中为 1 的位数，即为答案。

时间复杂度 $O(\log n)$，其中 $n$ 是输入的整数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
d = {
    0: "zero",
    1: "one",
    2: "two",
    3: "three",
    4: "four",
    5: "five",
    6: "six",
    7: "seven",
    8: "eight",
    9: "nine",
}


class Solution:
    def countOddLetters(self, n: int) -> int:
        mask = 0
        while n:
            x = n % 10
            n //= 10
            for c in d[x]:
                mask ^= 1 << (ord(c) - ord("a"))
        return mask.bit_count()
```

#### Java

```java
class Solution {
    private static final Map<Integer, String> d = new HashMap<>();
    static {
        d.put(0, "zero");
        d.put(1, "one");
        d.put(2, "two");
        d.put(3, "three");
        d.put(4, "four");
        d.put(5, "five");
        d.put(6, "six");
        d.put(7, "seven");
        d.put(8, "eight");
        d.put(9, "nine");
    }

    public int countOddLetters(int n) {
        int mask = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            for (char c : d.get(x).toCharArray()) {
                mask ^= 1 << (c - 'a');
            }
        }
        return Integer.bitCount(mask);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOddLetters(int n) {
        static const unordered_map<int, string> d = {
            {0, "zero"},
            {1, "one"},
            {2, "two"},
            {3, "three"},
            {4, "four"},
            {5, "five"},
            {6, "six"},
            {7, "seven"},
            {8, "eight"},
            {9, "nine"}};

        int mask = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            for (char c : d.at(x)) {
                mask ^= 1 << (c - 'a');
            }
        }
        return __builtin_popcount(mask);
    }
};
```

#### Go

```go
func countOddLetters(n int) int {
	d := map[int]string{
		0: "zero",
		1: "one",
		2: "two",
		3: "three",
		4: "four",
		5: "five",
		6: "six",
		7: "seven",
		8: "eight",
		9: "nine",
	}

	mask := 0
	for n > 0 {
		x := n % 10
		n /= 10
		for _, c := range d[x] {
			mask ^= 1 << (c - 'a')
		}
	}

	return bits.OnesCount32(uint32(mask))
}
```

#### TypeScript

```ts
function countOddLetters(n: number): number {
    const d: Record<number, string> = {
        0: 'zero',
        1: 'one',
        2: 'two',
        3: 'three',
        4: 'four',
        5: 'five',
        6: 'six',
        7: 'seven',
        8: 'eight',
        9: 'nine',
    };

    let mask = 0;
    while (n > 0) {
        const x = n % 10;
        n = Math.floor(n / 10);
        for (const c of d[x]) {
            mask ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }

    return bitCount(mask);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_odd_letters(mut n: i32) -> i32 {
        use std::collections::HashMap;

        let d: HashMap<i32, &str> = [
            (0, "zero"),
            (1, "one"),
            (2, "two"),
            (3, "three"),
            (4, "four"),
            (5, "five"),
            (6, "six"),
            (7, "seven"),
            (8, "eight"),
            (9, "nine"),
        ]
        .iter()
        .cloned()
        .collect();

        let mut mask: u32 = 0;

        while n > 0 {
            let x = n % 10;
            n /= 10;
            if let Some(word) = d.get(&x) {
                for c in word.chars() {
                    let bit = 1 << (c as u8 - b'a');
                    mask ^= bit as u32;
                }
            }
        }

        mask.count_ones() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
