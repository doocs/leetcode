---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0260.Single%20Number%20III/README.md
tags:
    - 位运算
    - 数组
---

# [260. 只出现一次的数字 III](https://leetcode.cn/problems/single-number-iii)

[English Version](/solution/0200-0299/0260.Single%20Number%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,3,2,5]
<strong>输出：</strong>[3,5]
<strong>解释：</strong>[5, 3] 也是有效的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,0]
<strong>输出：</strong>[-1,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1]
<strong>输出：</strong>[1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>除两个只出现一次的整数外，<code>nums</code> 中的其他数字都出现两次</li>
</ul>

## 解法

### 方法一：位运算

异或运算有以下性质：

-   任何数和 $0$ 做异或运算，结果仍然是原来的数，即 $x \oplus 0 = x$；
-   任何数和其自身做异或运算，结果是 $0$，即 $x \oplus x = 0$；

由于数组中除了两个数字之外，其他数字都出现了两次，因此我们对数组中的所有数字进行异或运算，得到的结果即为两个只出现一次的数字的异或结果。

而由于这两个数字不相等，因此异或结果中至少存在一位为 $1$。我们可以通过 `lowbit` 运算找到异或结果中最低位的 $1$，并将数组中的所有数字按照该位是否为 $1$ 分为两组，这样两个只出现一次的数字就被分到了不同的组中。

对两个组分别进行异或运算，即可得到两个只出现一次的数字 $a$ 和 $b$。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        xs = reduce(xor, nums)
        a = 0
        lb = xs & -xs
        for x in nums:
            if x & lb:
                a ^= x
        b = xs ^ a
        return [a, b]
```

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int xs = 0;
        for (int x : nums) {
            xs ^= x;
        }
        int lb = xs & -xs;
        int a = 0;
        for (int x : nums) {
            if ((x & lb) != 0) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return new int[] {a, b};
    }
}
```

```cpp
class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long long xs = 0;
        for (int& x : nums) {
            xs ^= x;
        }
        int lb = xs & -xs;
        int a = 0;
        for (int& x : nums) {
            if (x & lb) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return {a, b};
    }
};
```

```go
func singleNumber(nums []int) []int {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	lb := xs & -xs
	a := 0
	for _, x := range nums {
		if x&lb != 0 {
			a ^= x
		}
	}
	b := xs ^ a
	return []int{a, b}
}
```

```ts
function singleNumber(nums: number[]): number[] {
    const xs = nums.reduce((a, b) => a ^ b);
    const lb = xs & -xs;
    let a = 0;
    for (const x of nums) {
        if (x & lb) {
            a ^= x;
        }
    }
    const b = xs ^ a;
    return [a, b];
}
```

```rust
impl Solution {
    pub fn single_number(nums: Vec<i32>) -> Vec<i32> {
        let xs = nums.iter().fold(0, |r, v| r ^ v);
        let lb = xs & -xs;
        let mut a = 0;
        for x in &nums {
            if (x & lb) != 0 {
                a ^= x;
            }
        }
        let b = xs ^ a;
        vec![a, b]
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumber = function (nums) {
    const xs = nums.reduce((a, b) => a ^ b);
    const lb = xs & -xs;
    let a = 0;
    for (const x of nums) {
        if (x & lb) {
            a ^= x;
        }
    }
    const b = xs ^ a;
    return [a, b];
};
```

```cs
public class Solution {
    public int[] SingleNumber(int[] nums) {
        int xs = nums.Aggregate(0, (a, b) => a ^ b);
        int lb = xs & -xs;
        int a = 0;
        foreach(int x in nums) {
            if ((x & lb) != 0) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return new int[] {a, b};
    }
}
```

<!-- tabs:end -->

<!-- end -->
