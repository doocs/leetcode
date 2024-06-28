---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3199.Count%20Triplets%20with%20Even%20XOR%20Set%20Bits%20I/README.md
---

<!-- problem:start -->

# [3199. Count Triplets with Even XOR Set Bits I 🔒](https://leetcode.cn/problems/count-triplets-with-even-xor-set-bits-i)

[English Version](/solution/3100-3199/3199.Count%20Triplets%20with%20Even%20XOR%20Set%20Bits%20I/README_EN.md)

## 题目描述

<!-- description:start -->

Given three integer arrays <code>a</code>, <code>b</code>, and <code>c</code>, return the number of triplets <code>(a[i], b[j], c[k])</code>, such that the bitwise <code>XOR</code> of the elements of each triplet has an <strong>even</strong> number of <span data-keyword="set-bit">set bits</span>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = [1], b = [2], c = [3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only triplet is <code>(a[0], b[0], c[0])</code> and their <code>XOR</code> is: <code>1 XOR 2 XOR 3 = 00<sub>2</sub></code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = [1,1], b = [2,3], c = [1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Consider these four triplets:</p>

<ul>
	<li><code>(a[0], b[1], c[0])</code>: <code>1 XOR 3 XOR 1 = 011<sub>2</sub></code></li>
	<li><code>(a[1], b[1], c[0])</code>: <code>1 XOR 3 XOR 1 = 011<sub>2</sub></code></li>
	<li><code>(a[0], b[0], c[1])</code>: <code>1 XOR 2 XOR 5 = 110<sub>2</sub></code></li>
	<li><code>(a[1], b[0], c[1])</code>: <code>1 XOR 2 XOR 5 = 110<sub>2</sub></code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length, c.length &lt;= 100</code></li>
	<li><code>0 &lt;= a[i], b[i], c[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

对于两个整数，异或结果中 $1$ 的个数的奇偶性，取决于两个整数的二进制表示中 $1$ 的个数的奇偶性。

我们可以用三个数组 `cnt1`、`cnt2`、`cnt3` 分别记录数组 `a`、`b`、`c` 中每个数的二进制表示中 $1$ 的个数的奇偶性。

然后我们在 $[0, 1]$ 的范围内枚举三个数组中的每个数的二进制表示中 $1$ 的个数的奇偶性，如果三个数的二进制表示中 $1$ 的个数的奇偶性之和为偶数，那么这三个数的异或结果中 $1$ 的个数也为偶数，此时我们将这三个数的组合数相乘累加到答案中。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 `a`、`b`、`c` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def tripletCount(self, a: List[int], b: List[int], c: List[int]) -> int:
        cnt1 = Counter(x.bit_count() & 1 for x in a)
        cnt2 = Counter(x.bit_count() & 1 for x in b)
        cnt3 = Counter(x.bit_count() & 1 for x in c)
        ans = 0
        for i in range(2):
            for j in range(2):
                for k in range(2):
                    if (i + j + k) & 1 ^ 1:
                        ans += cnt1[i] * cnt2[j] * cnt3[k]
        return ans
```

#### Java

```java
class Solution {
    public int tripletCount(int[] a, int[] b, int[] c) {
        int[] cnt1 = new int[2];
        int[] cnt2 = new int[2];
        int[] cnt3 = new int[2];
        for (int x : a) {
            ++cnt1[Integer.bitCount(x) & 1];
        }
        for (int x : b) {
            ++cnt2[Integer.bitCount(x) & 1];
        }
        for (int x : c) {
            ++cnt3[Integer.bitCount(x) & 1];
        }
        int ans = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 2; ++k) {
                    if ((i + j + k) % 2 == 0) {
                        ans += cnt1[i] * cnt2[j] * cnt3[k];
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
    int tripletCount(vector<int>& a, vector<int>& b, vector<int>& c) {
        int cnt1[2]{};
        int cnt2[2]{};
        int cnt3[2]{};
        for (int x : a) {
            ++cnt1[__builtin_popcount(x) & 1];
        }
        for (int x : b) {
            ++cnt2[__builtin_popcount(x) & 1];
        }
        for (int x : c) {
            ++cnt3[__builtin_popcount(x) & 1];
        }
        int ans = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 2; ++k) {
                    if ((i + j + k) % 2 == 0) {
                        ans += cnt1[i] * cnt2[j] * cnt3[k];
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
func tripletCount(a []int, b []int, c []int) (ans int) {
	cnt1 := [2]int{}
	cnt2 := [2]int{}
	cnt3 := [2]int{}
	for _, x := range a {
		cnt1[bits.OnesCount(uint(x))%2]++
	}
	for _, x := range b {
		cnt2[bits.OnesCount(uint(x))%2]++
	}
	for _, x := range c {
		cnt3[bits.OnesCount(uint(x))%2]++
	}
	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			for k := 0; k < 2; k++ {
				if (i+j+k)%2 == 0 {
					ans += cnt1[i] * cnt2[j] * cnt3[k]
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function tripletCount(a: number[], b: number[], c: number[]): number {
    const cnt1: [number, number] = [0, 0];
    const cnt2: [number, number] = [0, 0];
    const cnt3: [number, number] = [0, 0];
    for (const x of a) {
        ++cnt1[bitCount(x) & 1];
    }
    for (const x of b) {
        ++cnt2[bitCount(x) & 1];
    }
    for (const x of c) {
        ++cnt3[bitCount(x) & 1];
    }
    let ans = 0;
    for (let i = 0; i < 2; ++i) {
        for (let j = 0; j < 2; ++j) {
            for (let k = 0; k < 2; ++k) {
                if ((i + j + k) % 2 === 0) {
                    ans += cnt1[i] * cnt2[j] * cnt3[k];
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
