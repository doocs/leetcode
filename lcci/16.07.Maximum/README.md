---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [面试题 16.07. 最大数值](https://leetcode.cn/problems/maximum-lcci)

[English Version](/lcci/16.07.Maximum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>编写一个方法，找出两个数字<code>a</code>和<code>b</code>中最大的那一个。不得使用if-else或其他比较运算符。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> a = 1, b = 2
<strong>输出：</strong> 2
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

<!-- thinking:start -->

> **思考**
>
> 不用比较运算求 $\max(a,b)$。条件分支或 `if` 通常会编译成比较。
>
> $a-b$ 的符号位标明谁更大：符号为 $1$ 则 $a<b$。
>
> 取出 $64$ 位视图下的符号位 $k$，返回 $a(k\oplus 1)+b k$。用位运算与乘法选择，而不写关系运算符。

<!-- thinking:end -->

我们可以提取 $a-b$ 的符号位 $k$，如果符号位为 $1$，说明 $a \lt b$；如果符号位为 $0$，说明 $a \ge b$。

那么最后的结果就是 $a \times (k \oplus 1) + b \times k$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximum(self, a: int, b: int) -> int:
        k = (int(((a - b) & 0xFFFFFFFFFFFFFFFF) >> 63)) & 1
        return a * (k ^ 1) + b * k
```

#### Java

```java
class Solution {
    public int maximum(int a, int b) {
        int k = (int) (((long) a - (long) b) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximum(int a, int b) {
        int k = ((static_cast<long long>(a) - static_cast<long long>(b)) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
};
```

#### Go

```go
func maximum(a int, b int) int {
	k := (a - b) >> 63 & 1
	return a*(k^1) + b*k
}
```

#### TypeScript

```ts
function maximum(a: number, b: number): number {
    const k: number = Number(((BigInt(a) - BigInt(b)) >> BigInt(63)) & BigInt(1));
    return a * (k ^ 1) + b * k;
}
```

#### Swift

```swift
class Solution {
    func maximum(_ a: Int, _ b: Int) -> Int {
        let diff = Int64(a) - Int64(b)
        let k = Int((diff >> 63) & 1)
        return a * (k ^ 1) + b * k
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
