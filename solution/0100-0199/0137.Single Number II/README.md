# [137. 只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii)

[English Version](/solution/0100-0199/0137.Single%20Number%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>

<p>你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0,1,0,1,99]
<strong>输出：</strong>99
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

我们可以枚举每个二进制位 $i$，对于每个二进制位，我们统计所有数字在该二进制位上的和，如果该二进制位上的和能被 $3$ 整除，那么只出现一次的数字在该二进制位上为 $0$，否则为 $1$。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(1)$。其中 $n$ 和 $M$ 分别是数组的长度和数组中元素的范围。

**方法二：数字电路**

我们考虑一种更高效的方法，该方法使用数字电路来模拟上述的位运算。

一个整数的每个二进制位是 $0$ 或 $1$，只能表示 $2$ 种状态。但我们需要表示当前遍历过的所有整数的第 $i$ 位之和模 $3$ 的结果，因此，我们可以使用 $a$ 和 $b$ 两个整数来表示。那么会有以下三种情况：

1. 整数 $a$ 的第 $i$ 位为 $0$ 且整数 $b$ 的第 $i$ 位为 $0$，表示模 $3$ 结果是 $0$；
1. 整数 $a$ 的第 $i$ 位为 $0$ 且整数 $b$ 的第 $i$ 位为 $1$，表示模 $3$ 结果是 $1$；
1. 整数 $a$ 的第 $i$ 位为 $1$ 且整数 $b$ 的第 $i$ 位为 $0$，表示模 $3$ 结果是 $2$。

我们用整数 $c$ 表示当前要读入的数，那么有以下真值表：

| $a_i$ | $b_i$ | $c_i$ | 新的 $a_i$ | 新的 $b_i$ |
| ----- | ----- | ----- | ---------- | ---------- |
| 0     | 0     | 0     | 0          | 0          |
| 0     | 0     | 1     | 0          | 1          |
| 0     | 1     | 0     | 0          | 1          |
| 0     | 1     | 1     | 1          | 0          |
| 1     | 0     | 0     | 1          | 0          |
| 1     | 0     | 1     | 0          | 0          |

基于以上真值表，我们可以写出逻辑表达式：

$$
a_i = a_i' b_i c_i + a_i b_i' c_i'
$$

以及：

$$
b_i = a_i' b_i' c_i + a_i' b_i c_i' = a_i' (b_i \oplus c_i)
$$

最后结果是 $b$，因为 $b$ 的二进制位上为 $1$ 时表示这个数字出现了 $1$ 次。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        for i in range(32):
            cnt = sum(num >> i & 1 for num in nums)
            if cnt % 3:
                if i == 31:
                    ans -= 1 << i
                else:
                    ans |= 1 << i
        return ans
```

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        a = b = 0
        for c in nums:
            aa = (~a & b & c) | (a & ~b & ~c)
            bb = ~a & (b ^ c)
            a, b = aa, bb
        return b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num >> i & 1;
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
}
```

### **Go**

需要注意 Golang 中的 `int` 在 64 位平台上相当于 `int64`

```go
func singleNumber(nums []int) int {
	ans := int32(0)
	for i := 0; i < 32; i++ {
		cnt := int32(0)
		for _, num := range nums {
			cnt += int32(num) >> i & 1
		}
		cnt %= 3
		ans |= cnt << i
	}
	return int(ans)
}
```

```go
func singleNumber(nums []int) int {
	a, b := 0, 0
	for _, c := range nums {
		aa := (^a & b & c) | (a & ^b & ^c)
		bb := ^a & (b ^ c)
		a, b = aa, bb
	}
	return b
}
```

### **C++**

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int num : nums) {
                cnt += ((num >> i) & 1);
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
};
```

### **TypeScript**

```ts
function singleNumber(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const count = nums.reduce((r, v) => r + ((v >> i) & 1), 0);
        ans |= count % 3 << i;
    }
    return ans;
}
```

```ts
function singleNumber(nums: number[]): number {
    let a = 0;
    let b = 0;
    for (const c of nums) {
        const aa = (~a & b & c) | (a & ~b & ~c);
        const bb = ~a & (b ^ c);
        a = aa;
        b = bb;
    }
    return b;
}
```

### **Rust**

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let count = nums.iter().map(|v| v >> i & 1).sum::<i32>();
            ans |= count % 3 << i;
        }
        ans
    }
}
```

### **C**

```c
int singleNumber(int *nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
        int count = 0;
        for (int j = 0; j < numsSize; j++) {
            if (nums[j] >> i & 1) {
                count++;
            }
        }
        ans |= (uint)(count % 3) << i;
    }
    return ans;
}
```

### **Swift**

```swift
class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var a = nums.sorted()
        var n = a.count
        for i in stride(from: 0, through: n - 2, by: 3) {
            if a[i] != a[i + 1] {
                return a[i]
            }
        }
        return a[n - 1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
