---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0401.Binary%20Watch/README.md
tags:
    - 位运算
    - 回溯
---

<!-- problem:start -->

# [401. 二进制手表](https://leetcode.cn/problems/binary-watch)

[English Version](/solution/0400-0499/0401.Binary%20Watch/README_EN.md)

## 题目描述

<!-- description:start -->

<p>二进制手表顶部有 4 个 LED 代表<strong> 小时（0-11）</strong>，底部的 6 个 LED 代表<strong> 分钟（0-59）</strong>。每个 LED 代表一个 0 或 1，最低位在右侧。</p>

<ul>
	<li>例如，下面的二进制手表读取 <code>"4:51"</code> 。</li>
</ul>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0401.Binary%20Watch/images/binarywatch.jpg" style="height: 300px; width" /></p>

<p>给你一个整数 <code>turnedOn</code> ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>小时不会以零开头：</p>

<ul>
	<li>例如，<code>"01:00"</code> 是无效的时间，正确的写法应该是 <code>"1:00"</code> 。</li>
</ul>

<p>分钟必须由两位数组成，可能会以零开头：</p>

<ul>
	<li>例如，<code>"10:2"</code> 是无效的时间，正确的写法应该是 <code>"10:02"</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>turnedOn = 1
<strong>输出：</strong>["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>turnedOn = 9
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= turnedOn &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举组合

题目可以转换为求 $i \in [0, 12)$ 和 $j \in [0, 60)$ 的所有可能组合。

合法组合需要满足的条件是 $i$ 的二进制形式中 1 的个数加上 $j$ 的二进制形式中 1 的个数，结果等于 $\textit{turnedOn}$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        return [
            '{:d}:{:02d}'.format(i, j)
            for i in range(12)
            for j in range(60)
            if (bin(i) + bin(j)).count('1') == turnedOn
        ]
```

#### Java

```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    ans.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> ans;
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 60; ++j) {
                if (__builtin_popcount(i) + __builtin_popcount(j) == turnedOn) {
                    ans.push_back(to_string(i) + ":" + (j < 10 ? "0" : "") + to_string(j));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func readBinaryWatch(turnedOn int) []string {
	var ans []string
	for i := 0; i < 12; i++ {
		for j := 0; j < 60; j++ {
			if bits.OnesCount(uint(i))+bits.OnesCount(uint(j)) == turnedOn {
				ans = append(ans, fmt.Sprintf("%d:%02d", i, j))
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function readBinaryWatch(turnedOn: number): string[] {
    const ans: string[] = [];

    for (let i = 0; i < 12; ++i) {
        for (let j = 0; j < 60; ++j) {
            if (bitCount(i) + bitCount(j) === turnedOn) {
                ans.push(`${i}:${j.toString().padStart(2, '0')}`);
            }
        }
    }

    return ans;
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
    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();

        for i in 0u32..12 {
            for j in 0u32..60 {
                if (i.count_ones() + j.count_ones()) as i32 == turned_on {
                    ans.push(format!("{}:{:02}", i, j));
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二进制枚举

我们可以利用 $10$ 个二进制位表示手表，其中前 $4$ 位代表小时，后 $6$ 位代表分钟。枚举 $[0, 2^{10})$ 中的每个数，判断其二进制表示中 1 的个数是否等于 $\textit{turnedOn}$，如果是，则将其转换为时间格式加入答案中。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        ans = []
        for i in range(1 << 10):
            h, m = i >> 6, i & 0b111111
            if h < 12 and m < 60 and i.bit_count() == turnedOn:
                ans.append('{:d}:{:02d}'.format(h, m))
        return ans
```

#### Java

```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << 10; ++i) {
            int h = i >> 6, m = i & 0b111111;
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(String.format("%d:%02d", h, m));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> ans;
        for (int i = 0; i < 1 << 10; ++i) {
            int h = i >> 6, m = i & 0b111111;
            if (h < 12 && m < 60 && __builtin_popcount(i) == turnedOn) {
                ans.push_back(to_string(h) + ":" + (m < 10 ? "0" : "") + to_string(m));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func readBinaryWatch(turnedOn int) []string {
	var ans []string
	for i := 0; i < 1<<10; i++ {
		h, m := i>>6, i&0b111111
		if h < 12 && m < 60 && bits.OnesCount(uint(i)) == turnedOn {
			ans = append(ans, fmt.Sprintf("%d:%02d", h, m))
		}
	}
	return ans
}
```

#### TypeScript

```ts
function readBinaryWatch(turnedOn: number): string[] {
    const ans: string[] = [];

    for (let i = 0; i < 1 << 10; ++i) {
        const h = i >> 6;
        const m = i & 0b111111;

        if (h < 12 && m < 60 && bitCount(i) === turnedOn) {
            ans.push(`${h}:${m < 10 ? '0' : ''}${m}`);
        }
    }

    return ans;
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
    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();

        for i in 0u32..(1 << 10) {
            let h = i >> 6;
            let m = i & 0b111111;

            if h < 12 && m < 60 && i.count_ones() as i32 == turned_on {
                ans.push(format!("{}:{:02}", h, m));
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
