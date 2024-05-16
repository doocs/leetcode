---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.01.Insert%20Into%20Bits/README.md
---

<!-- problem:start -->

# [面试题 05.01. 插入](https://leetcode.cn/problems/insert-into-bits-lcci)

[English Version](/lcci/05.01.Insert%20Into%20Bits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>插入。给定两个32位的整数<code>N</code>与<code>M</code>，以及表示比特位置的<code>i</code>与<code>j</code>。编写一种方法，将<code>M</code>插入<code>N</code>，使得M从N的第j位开始，到第<code>i</code>位结束。假定从<code>j</code>位到<code>i</code>位足以容纳<code>M</code>，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：N = 10000000000, M = 10011, i = 2, j = 6
<strong> 输出</strong>：N = 10001001100
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>： N = 0, M = 11111, i = 0, j = 4
<strong> 输出</strong>：N = 11111
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们先将 $N$ 的第 $i$ 位到第 $j$ 位清零，然后再将 $M$ 左移 $i$ 位，最后将 $M$ 与 $N$ 进行或运算。

时间复杂度 $O(\log n)$，其中 $n$ 是 $N$ 的大小。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def insertBits(self, N: int, M: int, i: int, j: int) -> int:
        for k in range(i, j + 1):
            N &= ~(1 << k)
        return N | M << i
```

```java
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; ++k) {
            N &= ~(1 << k);
        }
        return N | M << i;
    }
}
```

```cpp
class Solution {
public:
    int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; ++k) {
            N &= ~(1 << k);
        }
        return N | M << i;
    }
};
```

```go
func insertBits(N int, M int, i int, j int) int {
	for k := i; k <= j; k++ {
		N &= ^(1 << k)
	}
	return N | M<<i
}
```

```ts
function insertBits(N: number, M: number, i: number, j: number): number {
    for (let k = i; k <= j; ++k) {
        N &= ~(1 << k);
    }
    return N | (M << i);
}
```

```swift
class Solution {
    func insertBits(_ N: Int, _ M: Int, _ i: Int, _ j: Int) -> Int {
        var result = N

        for k in i...j {
            result &= ~(1 << k)
        }

        return result | (M << i)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
