---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2683.Neighboring%20Bitwise%20XOR/README.md
rating: 1517
tags:
    - 位运算
    - 数组
---

# [2683. 相邻值的按位异或](https://leetcode.cn/problems/neighboring-bitwise-xor)

[English Version](/solution/2600-2699/2683.Neighboring%20Bitwise%20XOR/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的数组 <code>derived</code> 是由同样长度为 <code>n</code> 的原始 <strong>二进制数组</strong> <code>original</code> 通过计算相邻值的 <strong>按位异或（⊕）</strong>派生而来。</p>

<p>特别地，对于范围&nbsp;<code>[0, n - 1]</code> 内的每个下标 <code>i</code> ：</p>

<ul>
	<li>如果 <code>i = n - 1</code> ，那么 <code>derived[i] = original[i] ⊕ original[0]</code></li>
	<li>否则 <code>derived[i] = original[i] ⊕ original[i + 1]</code></li>
</ul>

<p>给你一个数组 <code>derived</code> ，请判断是否存在一个能够派生得到 <code>derived</code> 的 <strong>有效原始二进制数组</strong> <code>original</code> 。</p>

<p>如果存在满足要求的原始二进制数组，返回 <em><strong>true</strong> </em>；否则，返回<em> <strong>false</strong> </em>。</p>

<ul>
	<li>二进制数组是仅由 <strong>0</strong> 和 <strong>1</strong> 组成的数组。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>derived = [1,1,0]
<strong>输出：</strong>true
<strong>解释：</strong>能够派生得到 [1,1,0] 的有效原始二进制数组是 [0,1,0] ：
derived[0] = original[0] ⊕ original[1] = 0 ⊕ 1 = 1 
derived[1] = original[1] ⊕ original[2] = 1 ⊕ 0 = 1
derived[2] = original[2] ⊕ original[0] = 0 ⊕ 0 = 0
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>derived = [1,1]
<strong>输出：</strong>true
<strong>解释：</strong>能够派生得到 [1,1] 的有效原始二进制数组是 [0,1] ：
derived[0] = original[0] ⊕ original[1] = 1
derived[1] = original[1] ⊕ original[0] = 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>derived = [1,0]
<strong>输出：</strong>false
<strong>解释：</strong>不存在能够派生得到 [1,0] 的有效原始二进制数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == derived.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>derived</code> 中的值不是 <strong>0</strong> 就是 <strong>1</strong> 。</li>
</ul>

## 解法

### 方法一：位运算

我们不妨假设原始二进制数组为 $a$，派生数组为 $b$，那么有：

$$
b_0 = a_0 \oplus a_1 \\
b_1 = a_1 \oplus a_2 \\
\cdots \\
b_{n-1} = a_{n-1} \oplus a_0
$$

由于异或运算满足交换律和结合律，因此有：

$$
b_0 \oplus b_1 \oplus \cdots \oplus b_{n-1} = (a_0 \oplus a_1) \oplus (a_1 \oplus a_2) \oplus \cdots \oplus (a_{n-1} \oplus a_0) = 0
$$

因此，只要派生数组的所有元素的异或和为 $0$，就一定存在一个满足要求的原始二进制数组。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def doesValidArrayExist(self, derived: List[int]) -> bool:
        return reduce(xor, derived) == 0
```

```java
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
}
```

```cpp
class Solution {
public:
    bool doesValidArrayExist(vector<int>& derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
};
```

```go
func doesValidArrayExist(derived []int) bool {
	s := 0
	for _, x := range derived {
		s ^= x
	}
	return s == 0
}
```

```ts
function doesValidArrayExist(derived: number[]): boolean {
    let s = 0;
    for (const x of derived) {
        s ^= x;
    }
    return s === 0;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```ts
function doesValidArrayExist(derived: number[]): boolean {
    return derived.reduce((acc, x) => acc ^ x, 0) === 0;
}
```

<!-- tabs:end -->

<!-- end -->
