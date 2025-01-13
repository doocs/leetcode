---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0268.Missing%20Number/README.md
tags:
    - 位运算
    - 数组
    - 哈希表
    - 数学
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [268. 丢失的数字](https://leetcode.cn/problems/missing-number)

[English Version](/solution/0200-0299/0268.Missing%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个包含 <code>[0, n]</code>&nbsp;中&nbsp;<code>n</code>&nbsp;个数的数组 <code>nums</code> ，找出 <code>[0, n]</code> 这个范围内没有出现在数组中的那个数。</p>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [3,0,1]</p>

<p><strong>输出：</strong>2</p>

<p><b>解释：</b><code>n = 3</code>，因为有 3 个数字，所以所有的数字都在范围 <code>[0,3]</code> 内。2 是丢失的数字，因为它没有出现在 <code>nums</code> 中。</p>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [0,1]</p>

<p><strong>输出：</strong>2</p>

<p><b>解释：</b><code>n = 2</code>，因为有 2 个数字，所以所有的数字都在范围 <code>[0,2]</code> 内。2 是丢失的数字，因为它没有出现在 <code>nums</code> 中。</p>
</div>

<p><strong>示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>nums = [9,6,4,2,3,5,7,0,1]</p>

<p><strong>输出：</strong>8</p>

<p><b>解释：</b><code>n = 9</code>，因为有 9 个数字，所以所有的数字都在范围 <code>[0,9]</code> 内。8 是丢失的数字，因为它没有出现在 <code>nums</code> 中。</p>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= n</code></li>
	<li><code>nums</code> 中的所有数字都 <strong>独一无二</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

异或运算的性质：

-   任何数和 $0$ 做异或运算，结果仍然是原来的数，即 $x \oplus 0 = x$；
-   任何数和其自身做异或运算，结果是 $0$，即 $x \oplus x = 0$；

因此，我们可以遍历数组，将数字 $[0,..n]$ 与数组中的元素进行异或运算，最后的结果就是缺失的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        return reduce(xor, (i ^ v for i, v in enumerate(nums, 1)))
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans ^= (i ^ nums[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans ^= (i ^ nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func missingNumber(nums []int) (ans int) {
	n := len(nums)
	ans = n
	for i, v := range nums {
		ans ^= (i ^ v)
	}
	return
}
```

#### TypeScript

```ts
function missingNumber(nums: number[]): number {
    const n = nums.length;
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ans ^= i ^ nums[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut ans = n;
        for (i, v) in nums.iter().enumerate() {
            ans ^= (i as i32) ^ v;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    const n = nums.length;
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ans ^= i ^ nums[i];
    }
    return ans;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function missingNumber($nums) {
        $n = count($nums);
        $sumN = (($n + 1) * $n) / 2;
        for ($i = 0; $i < $n; $i++) {
            $sumN -= $nums[$i];
        }
        return $sumN;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：数学

我们也可以用数学求解。求出 $[0,..n]$ 的和，减去数组中所有数的和，就得到了缺失的数字。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        n = len(nums)
        return (1 + n) * n // 2 - sum(nums)
```

#### Java

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans += i - nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int n = nums.size();
        return (1 + n) * n / 2 - accumulate(nums.begin(), nums.end(), 0);
    }
};
```

#### Go

```go
func missingNumber(nums []int) (ans int) {
	n := len(nums)
	ans = n
	for i, v := range nums {
		ans += i - v
	}
	return
}
```

#### TypeScript

```ts
function missingNumber(nums: number[]): number {
    const n = nums.length;
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ans += i - nums[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut ans = n;
        for (i, &v) in nums.iter().enumerate() {
            ans += (i as i32) - v;
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    const n = nums.length;
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ans += i - nums[i];
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
