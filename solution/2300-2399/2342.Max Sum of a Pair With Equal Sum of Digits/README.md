---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README.md
rating: 1308
source: 第 302 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2342. 数位和相等数对的最大和](https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits)

[English Version](/solution/2300-2399/2342.Max%20Sum%20of%20a%20Pair%20With%20Equal%20Sum%20of%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组中的元素都是 <strong>正</strong> 整数。请你选出两个下标 <code>i</code> 和 <code>j</code>（<code>i != j</code>），且 <code>nums[i]</code> 的数位和 与&nbsp; <code>nums[j]</code> 的数位和相等。</p>

<p>请你找出所有满足条件的下标 <code>i</code> 和 <code>j</code> ，找出并返回<em> </em><code>nums[i] + nums[j]</code><em> </em>可以得到的 <strong>最大值</strong><em>。</em>如果不存在这样的下标对，返回 -1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [18,43,36,13,7]
<strong>输出：</strong>54
<strong>解释：</strong>满足条件的数对 (i, j) 为：
- (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
- (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
所以可以获得的最大和是 54 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,12,19,14]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在满足条件的数对，返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $d$ 记录每个数位和对应的最大值，初始化一个答案变量 $ans = -1$。

接下来，我们遍历数组 $nums$，对于每个数 $v$，我们计算它的数位和 $x$，如果 $x$ 在哈希表 $d$ 中存在，那么我们就更新答案 $ans = \max(ans, d[x] + v)$。然后更新哈希表 $d[x] = \max(d[x], v)$。

最后返回答案 $ans$ 即可。

由于 $nums$ 中的元素最大为 $10^9$，因此数位和最大为 $9 \times 9 = 81$，我们可以直接定义一个长度为 $100$ 的数组 $d$ 来代替哈希表。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(D)$，其中 $n$ 是数组 $nums$ 的长度，而 $M$ 和 $D$ 分别是数组 $nums$ 中的元素的最大值和数位和的最大值。本题中 $M \leq 10^9$，$D \leq 81$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        d = defaultdict(int)
        ans = -1
        for v in nums:
            x, y = 0, v
            while y:
                x += y % 10
                y //= 10
            if x in d:
                ans = max(ans, d[x] + v)
            d[x] = max(d[x], v)
        return ans
```

#### Java

```java
class Solution {
    public int maximumSum(int[] nums) {
        int[] d = new int[100];
        int ans = -1;
        for (int v : nums) {
            int x = 0;
            for (int y = v; y > 0; y /= 10) {
                x += y % 10;
            }
            if (d[x] > 0) {
                ans = Math.max(ans, d[x] + v);
            }
            d[x] = Math.max(d[x], v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumSum(vector<int>& nums) {
        int d[100]{};
        int ans = -1;
        for (int v : nums) {
            int x = 0;
            for (int y = v; y; y /= 10) {
                x += y % 10;
            }
            if (d[x]) {
                ans = max(ans, d[x] + v);
            }
            d[x] = max(d[x], v);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSum(nums []int) int {
	d := [100]int{}
	ans := -1
	for _, v := range nums {
		x := 0
		for y := v; y > 0; y /= 10 {
			x += y % 10
		}
		if d[x] > 0 {
			ans = max(ans, d[x]+v)
		}
		d[x] = max(d[x], v)
	}
	return ans
}
```

#### TypeScript

```ts
function maximumSum(nums: number[]): number {
    const d: number[] = Array(100).fill(0);
    let ans = -1;
    for (const v of nums) {
        let x = 0;
        for (let y = v; y; y = (y / 10) | 0) {
            x += y % 10;
        }
        if (d[x]) {
            ans = Math.max(ans, d[x] + v);
        }
        d[x] = Math.max(d[x], v);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_sum(nums: Vec<i32>) -> i32 {
        let mut d = vec![0; 100];
        let mut ans = -1;

        for &v in nums.iter() {
            let mut x: usize = 0;
            let mut y = v;
            while y > 0 {
                x += (y % 10) as usize;
                y /= 10;
            }
            if d[x] > 0 {
                ans = ans.max(d[x] + v);
            }
            d[x] = d[x].max(v);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
