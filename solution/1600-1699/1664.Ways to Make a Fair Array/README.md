---
comments: true
difficulty: 中等
rating: 1590
source: 第 216 场周赛 Q3
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [1664. 生成平衡数组的方案数](https://leetcode.cn/problems/ways-to-make-a-fair-array)

[English Version](/solution/1600-1699/1664.Ways%20to%20Make%20a%20Fair%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。你需要选择 <strong>恰好</strong> 一个下标（下标从 <strong>0</strong> 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。</p>

<p>比方说，如果 <code>nums = [6,1,7,4,1]</code> ，那么：</p>

<ul>
	<li>选择删除下标 <code>1</code> ，剩下的数组为 <code>nums = [6,7,4,1]</code> 。</li>
	<li>选择删除下标 <code>2</code> ，剩下的数组为 <code>nums = [6,1,4,1]</code> 。</li>
	<li>选择删除下标 <code>4</code> ，剩下的数组为 <code>nums = [6,1,7,4]</code> 。</li>
</ul>

<p>如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 <strong>平衡数组</strong> 。</p>

<p>请你返回删除操作后，剩下的数组<em> </em><code>nums</code><em> </em>是 <strong>平衡数组</strong> 的 <strong>方案数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,6,4]
<b>输出：</b>1
<strong>解释：</strong>
删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
只有一种让剩余数组成为平衡数组的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>3
<b>解释：</b>你可以删除任意元素，剩余数组都是平衡数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3]
<b>输出：</b>0
<b>解释：</b>不管删除哪个元素，剩下数组都不是平衡数组。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 前缀和

<!-- thinking:start -->

> **思考**
>
> 删掉一个下标后，它左侧的奇偶不变，右侧的奇偶全部对调。 $n$ 达到 $10^5$，不能每次删除都重算两边的和。
>
> 公平数组只要求删除后的偶数位之和等于奇数位之和。右侧对调以后，新的偶数和是左侧偶数加右侧奇数，新的奇数和是左侧奇数加右侧偶数。
>
> 于是用 $l$ 记下已经走过的奇偶和，用 $r$ 记下还没走过的奇偶和。先把当前数从 $r$ 里减掉，交叉相加相等就记一次，再把它加进 $l$。

<!-- thinking:end -->

记 $\textit{nums}$ 的总和为 $s$，偶数下标之和为 $r_0$。 $r[0]$ 和 $r[1]$ 分别是尚未划到左侧的偶数位之和与奇数位之和，初始 $r=[r_0, s-r_0]$。 $l[0]$ 和 $l[1]$ 是已经走过的偶数位之和与奇数位之和，初始都为 $0$。

从左到右扫描。下标 $i$ 上的值是 $x$ 时，先从 $r[i\bmod 2]$ 减去 $x$，这时 $r$ 只含 $i$ 右侧的元素。删掉 $i$ 后左侧奇偶不变、右侧奇偶对调，新的偶数和是 $l[0]+r[1]$，新的奇数和是 $l[1]+r[0]$。二者相等就把答案加一，然后把 $x$ 加进 $l[i\bmod 2]$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToMakeFair(self, nums: list[int]) -> int:
        s = sum(nums)
        r0 = sum(nums[::2])
        r = [r0, s - r0]
        l = [0, 0]
        ans = 0
        for i, x in enumerate(nums):
            r[i % 2] -= x
            if l[0] + r[1] == l[1] + r[0]:
                ans += 1
            l[i % 2] += x
        return ans
```

#### Java

```java
class Solution {
    public int waysToMakeFair(int[] nums) {
        int s = 0, r0 = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            if (i % 2 == 0) {
                r0 += nums[i];
            }
        }
        int[] r = {r0, s - r0};
        int[] l = {0, 0};
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            r[i % 2] -= x;
            if (l[0] + r[1] == l[1] + r[0]) {
                ++ans;
            }
            l[i % 2] += x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        int n = nums.size();
        int s = 0, r0 = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i % 2 == 0) {
                r0 += nums[i];
            }
        }
        int r[2] = {r0, s - r0};
        int l[2] = {0, 0};
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            r[i % 2] -= x;
            if (l[0] + r[1] == l[1] + r[0]) {
                ++ans;
            }
            l[i % 2] += x;
        }
        return ans;
    }
};
```

#### Go

```go
func waysToMakeFair(nums []int) int {
	s, r0 := 0, 0
	for i, x := range nums {
		s += x
		if i%2 == 0 {
			r0 += x
		}
	}
	r := [2]int{r0, s - r0}
	l := [2]int{}
	ans := 0
	for i, x := range nums {
		r[i%2] -= x
		if l[0]+r[1] == l[1]+r[0] {
			ans++
		}
		l[i%2] += x
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToMakeFair = function (nums) {
    const s = nums.reduce((a, b) => a + b, 0);
    let r0 = 0;
    for (let i = 0; i < nums.length; i += 2) {
        r0 += nums[i];
    }
    const r = [r0, s - r0];
    const l = [0, 0];
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        r[i % 2] -= x;
        if (l[0] + r[1] === l[1] + r[0]) {
            ++ans;
        }
        l[i % 2] += x;
    }
    return ans;
};
```

#### TypeScript

```ts
function waysToMakeFair(nums: number[]): number {
    const s = nums.reduce((a, b) => a + b, 0);
    let r0 = 0;
    for (let i = 0; i < nums.length; i += 2) {
        r0 += nums[i];
    }
    const r = [r0, s - r0];
    const l = [0, 0];
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        r[i % 2] -= x;
        if (l[0] + r[1] === l[1] + r[0]) {
            ++ans;
        }
        l[i % 2] += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn ways_to_make_fair(nums: Vec<i32>) -> i32 {
        let s: i32 = nums.iter().sum();
        let r0: i32 = nums.iter().step_by(2).sum();
        let mut r = [r0, s - r0];
        let mut l = [0, 0];
        let mut ans = 0;
        for (i, &x) in nums.iter().enumerate() {
            r[i % 2] -= x;
            if l[0] + r[1] == l[1] + r[0] {
                ans += 1;
            }
            l[i % 2] += x;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
