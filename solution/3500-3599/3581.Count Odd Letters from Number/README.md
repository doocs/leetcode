---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3581.Count%20Odd%20Letters%20from%20Number/README.md
---

<!-- problem:start -->

# [3581. Count Odd Letters from Number 🔒](https://leetcode.cn/problems/count-odd-letters-from-number)

[English Version](/solution/3500-3599/3581.Count%20Odd%20Letters%20from%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer <code>n</code> perform the following steps:</p>

<ul>
	<li>Convert each digit of <code>n</code> into its <em>lowercase English word</em> (e.g., 4 &rarr; &quot;four&quot;, 1 &rarr; &quot;one&quot;).</li>
	<li><strong>Concatenate</strong> those words in the <strong>original digit order</strong> to form a string <code>s</code>.</li>
</ul>

<p>Return the number of <strong>distinct</strong> characters in <code>s</code> that appear an <strong>odd</strong> number of times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 41</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>41 &rarr; <code>&quot;fourone&quot;</code></p>

<p>Characters with odd frequencies: <code>&#39;f&#39;</code>, <code>&#39;u&#39;</code>, <code>&#39;r&#39;</code>, <code>&#39;n&#39;</code>, <code>&#39;e&#39;</code>. Thus, the answer is 5.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>20 &rarr; <code>&quot;twozero&quot;</code></p>

<p>Characters with odd frequencies: <code>&#39;t&#39;</code>, <code>&#39;w&#39;</code>, <code>&#39;z&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;r&#39;</code>. Thus, the answer is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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
