---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0477.Total%20Hamming%20Distance/README.md
tags:
    - 位运算
    - 数组
    - 数学
---

<!-- problem:start -->

# [477. 汉明距离总和](https://leetcode.cn/problems/total-hamming-distance)

[English Version](/solution/0400-0499/0477.Total%20Hamming%20Distance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>两个整数的&nbsp;<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E8%B7%9D%E7%A6%BB/475174?fr=aladdin">汉明距离</a> 指的是这两个数字的二进制数对应位不同的数量。</p>

<p>给你一个整数数组 <code>nums</code>，请你计算并返回 <code>nums</code> 中任意两个数之间 <strong>汉明距离的总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,14,2]
<strong>输出：</strong>6
<strong>解释：</strong>在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
所以答案为：
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,14,4]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>给定输入的对应答案符合 <strong>32-bit</strong> 整数范围</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们在 $[0, 31]$ 的范围内枚举每一位，对于当前枚举的位 $i$，我们统计所有数字中的第 $i$ 位为 $1$ 的个数 $a$，那么这些数字中的第 $i$ 位为 $0$ 的个数就是 $b = n - a$，其中 $n$ 是数组的长度。这样的话，在第 $i$ 位上的汉明距离之和就是 $a \times b$，我们把所有的位的汉明距离相加即为答案。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组的长度和数组中的元素的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def totalHammingDistance(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        for i in range(32):
            a = sum(x >> i & 1 for x in nums)
            b = n - a
            ans += a * b
        return ans
```

```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 32; ++i) {
            int a = 0;
            for (int x : nums) {
                a += (x >> i & 1);
            }
            int b = n - a;
            ans += a * b;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int i = 0; i < 32; ++i) {
            int a = 0;
            for (int x : nums) {
                a += x >> i & 1;
            }
            int b = n - a;
            ans += a * b;
        }
        return ans;
    }
};
```

```go
func totalHammingDistance(nums []int) (ans int) {
	for i := 0; i < 32; i++ {
		a := 0
		for _, x := range nums {
			a += x >> i & 1
		}
		b := len(nums) - a
		ans += a * b
	}
	return
}
```

```ts
function totalHammingDistance(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; ++i) {
        const a = nums.filter(x => (x >> i) & 1).length;
        const b = nums.length - a;
        ans += a * b;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn total_hamming_distance(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let mut a = 0;
            for &x in nums.iter() {
                a += (x >> i) & 1;
            }
            let b = (nums.len() as i32) - a;
            ans += a * b;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
