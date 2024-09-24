---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README.md
rating: 1719
source: 第 160 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [1239. 串联字符串的最大长度](https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters)

[English Version](/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串数组 <code>arr</code>，字符串 <code>s</code> 是将 <code>arr</code>&nbsp;的含有 <strong>不同字母</strong> 的&nbsp;<strong>子序列</strong> 字符串 <strong>连接</strong> 所得的字符串。</p>

<p>请返回所有可行解 <code>s</code> 中最长长度。</p>

<p><strong>子序列</strong> 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = ["un","iq","ue"]
<strong>输出：</strong>4
<strong>解释：</strong>所有可能的串联组合是：
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
最大长度为 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = ["cha","r","act","ers"]
<strong>输出：</strong>6
<strong>解释：</strong>可能的解答有 "chaers" 和 "acters"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = ["abcdefghijklmnopqrstuvwxyz"]
<strong>输出：</strong>26
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 16</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 26</code></li>
	<li><code>arr[i]</code>&nbsp;中只含有小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 位运算

由于题目要求子序列的字符不能重复，字符都是小写字母，因此，我们可以用一个长度为 $26$ 的二进制整数来表示一个子序列，其中第 $i$ 位为 $1$ 表示子序列中含有滴 $i$ 个字符，为 $0$ 表示不含有第 $i$ 个字符。

我们可以用一个数组 $s$ 来存储所有满足条件的子序列的状态，初始时 $s$ 中只有一个元素 $0$。

然后我们遍历数组 $\textit{arr}$，对于每个字符串 $t$，我们用一个整数 $x$ 来表示 $t$ 的状态，然后我们遍历数组 $s$，对于每个状态 $y$，如果 $x$ 和 $y$ 之间没有相同的字符，那么我们将 $x$ 和 $y$ 的并集加入到 $s$ 中，并更新答案。

最后我们返回答案即可。

时间复杂度 $O(2^n + L)$，空间复杂度 $O(2^n)$，其中 $n$ 是字符串数组的长度，而 $L$ 是字符串数组中所有字符串的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxLength(self, arr: List[str]) -> int:
        s = [0]
        for t in arr:
            x = 0
            for b in map(lambda c: ord(c) - 97, t):
                if x >> b & 1:
                    x = 0
                    break
                x |= 1 << b
            if x:
                s.extend((x | y) for y in s if (x & y) == 0)
        return max(x.bit_count() for x in s)
```

#### Java

```java
class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> s = new ArrayList<>();
        s.add(0);
        int ans = 0;
        for (var t : arr) {
            int x = 0;
            for (char c : t.toCharArray()) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s.get(i);
                    if ((x & y) == 0) {
                        s.add(x | y);
                        ans = Math.max(ans, Integer.bitCount(x | y));
                    }
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
    int maxLength(vector<string>& arr) {
        vector<int> s = {0};
        int ans = 0;
        for (const string& t : arr) {
            int x = 0;
            for (char c : t) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s[i];
                    if ((x & y) == 0) {
                        s.push_back(x | y);
                        ans = max(ans, __builtin_popcount(x | y));
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxLength(arr []string) (ans int) {
	s := []int{0}
	for _, t := range arr {
		x := 0
		for _, c := range t {
			b := int(c - 'a')
			if (x>>b)&1 == 1 {
				x = 0
				break
			}
			x |= 1 << b
		}
		if x > 0 {
			for i := len(s) - 1; i >= 0; i-- {
				y := s[i]
				if (x & y) == 0 {
					s = append(s, x|y)
					ans = max(ans, bits.OnesCount(uint(x|y)))
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxLength(arr: string[]): number {
    const s: number[] = [0];
    let ans = 0;
    for (const t of arr) {
        let x = 0;
        for (const c of t) {
            const b = c.charCodeAt(0) - 97;
            if ((x >> b) & 1) {
                x = 0;
                break;
            }
            x |= 1 << b;
        }

        if (x > 0) {
            for (let i = s.length - 1; ~i; --i) {
                const y = s[i];
                if ((x & y) === 0) {
                    s.push(x | y);
                    ans = Math.max(ans, bitCount(x | y));
                }
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
