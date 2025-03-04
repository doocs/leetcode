---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2419.Longest%20Subarray%20With%20Maximum%20Bitwise%20AND/README.md
rating: 1495
source: 第 312 场周赛 Q2
tags:
    - 位运算
    - 脑筋急转弯
    - 数组
---

<!-- problem:start -->

# [2419. 按位与最大的最长子数组](https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and)

[English Version](/solution/2400-2499/2419.Longest%20Subarray%20With%20Maximum%20Bitwise%20AND/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> 。</p>

<p>考虑 <code>nums</code> 中进行 <strong>按位与（bitwise AND）</strong>运算得到的值 <strong>最大</strong> 的 <strong>非空</strong> 子数组。</p>

<ul>
	<li>换句话说，令 <code>k</code> 是 <code>nums</code> <strong>任意</strong> 子数组执行按位与运算所能得到的最大值。那么，只需要考虑那些执行一次按位与运算后等于 <code>k</code> 的子数组。</li>
</ul>

<p>返回满足要求的 <strong>最长</strong> 子数组的长度。</p>

<p>数组的按位与就是对数组中的所有数字进行按位与运算。</p>

<p><strong>子数组</strong> 是数组中的一个连续元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,3,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
子数组按位与运算的最大值是 3 。
能得到此结果的最长子数组是 [3,3]，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>
子数组按位与运算的最大值是 4 。 
能得到此结果的最长子数组是 [4]，所以返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

由于按位与的操作，不会使得数字变大，因此最大值就是数组中的最大值。

题目可以转换为求最大值在数组中最多连续出现的次数。

我们先遍历数组 $\textit{nums}$ 找到最大值 $\textit{mx}$，然后再遍历数组一次，找到最大值连续出现的次数，最后返回这个次数即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        ans = cnt = 0
        for x in nums:
            if x == mx:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 0
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        int ans = 0, cnt = 0;
        for (int x : nums) {
            if (x == mx) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int mx = ranges::max(nums);
        int ans = 0, cnt = 0;
        for (int x : nums) {
            if (x == mx) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) (ans int) {
	mx := slices.Max(nums)
	cnt := 0
	for _, x := range nums {
		if x == mx {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 0
		}
	}
	return
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    const mx = Math.max(...nums);
    let [ans, cnt] = [0, 0];
    for (const x of nums) {
        if (x === mx) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 0;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_subarray(nums: Vec<i32>) -> i32 {
        let mx = *nums.iter().max().unwrap();
        let mut ans = 0;
        let mut cnt = 0;

        for &x in nums.iter() {
            if x == mx {
                cnt += 1;
                ans = ans.max(cnt);
            } else {
                cnt = 0;
            }
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
var longestSubarray = function (nums) {
    const mx = Math.max(...nums);
    let [ans, cnt] = [0, 0];
    for (const x of nums) {
        if (x === mx) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 0;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
