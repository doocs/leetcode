---
comments: true
difficulty: 简单
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [136. 只出现一次的数字](https://leetcode.cn/problems/single-number)

[English Version](/solution/0100-0199/0136.Single%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>非空</strong> 整数数组 <code>nums</code> ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。</p>

<p>你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [2,2,1]</p>

<p><strong>输出：</strong>1</p>
</div>

<p><strong class="example">示例 2 ：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [4,1,2,1,2]</p>

<p><strong>输出：</strong>4</p>
</div>

<p><strong class="example">示例 3 ：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [1]</p>

<p><strong>输出：</strong>1</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li>除了某个元素只出现一次以外，其余每个元素均出现两次。</li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

<!-- thinking:start -->

> **思考**
>
> 其余数出现两次、一个出现一次，要求线性时间、常数空间。哈希计数空间是 $O(n)$。异或满足 $x\oplus x=0$、$x\oplus 0=x$ 且可交换，全部异或后成对的数消掉，剩下即只出现一次的那个。

<!-- thinking:end -->

异或运算的性质：

- 任何数和 $0$ 做异或运算，结果仍然是原来的数，即 $x \oplus 0 = x$；
- 任何数和其自身做异或运算，结果是 $0$，即 $x \oplus x = 0$；

我们对该数组所有元素进行异或运算，结果就是那个只出现一次的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        return reduce(xor, nums)
```

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            ans ^= v;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int v : nums) {
            ans ^= v;
        }
        return ans;
    }
};
```

#### Go

```go
func singleNumber(nums []int) (ans int) {
	for _, v := range nums {
		ans ^= v
	}
	return
}
```

#### TypeScript

```ts
function singleNumber(nums: number[]): number {
    return nums.reduce((r, v) => r ^ v);
}
```

#### Rust

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        nums.into_iter().reduce(|r, v| r ^ v).unwrap()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumber = function (nums) {
    return nums.reduce((a, b) => a ^ b);
};
```

#### C#

```cs
public class Solution {
    public int SingleNumber(int[] nums) {
        return nums.Aggregate(0, (a, b) => a ^ b);
    }
}
```

#### C

```c
int singleNumber(int* nums, int numsSize) {
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        ans ^= nums[i];
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        return nums.reduce(0, ^)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一已是异或折叠。本写法用语言自带的归约完成同一运算，逻辑不变。

<!-- thinking:end -->

<!-- tabs:start -->

#### Java

```java
class Solution {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
