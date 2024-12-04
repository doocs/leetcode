---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2057.Smallest%20Index%20With%20Equal%20Value/README.md
rating: 1167
source: 第 265 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [2057. 值相等的最小索引](https://leetcode.cn/problems/smallest-index-with-equal-value)

[English Version](/solution/2000-2099/2057.Smallest%20Index%20With%20Equal%20Value/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 0 开始的整数数组 <code>nums</code> ，返回 <code>nums</code> 中满足<em> </em><code>i mod 10 == nums[i]</code><em> </em>的最小下标 <code>i</code> ；如果不存在这样的下标，返回<em> </em><code>-1</code><em> </em>。</p>

<p><code>x mod y</code> 表示 <code>x</code> 除以 <code>y</code> 的 <strong>余数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,2]
<strong>输出：</strong>0
<strong>解释：</strong>
i=0: 0 mod 10 = 0 == nums[0].
i=1: 1 mod 10 = 1 == nums[1].
i=2: 2 mod 10 = 2 == nums[2].
所有下标都满足 i mod 10 == nums[i] ，所以返回最小下标 0
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4,3,2,1]
<strong>输出：</strong>2
<strong>解释：</strong>
i=0: 0 mod 10 = 0 != nums[0].
i=1: 1 mod 10 = 1 != nums[1].
i=2: 2 mod 10 = 2 == nums[2].
i=3: 3 mod 10 = 3 != nums[3].
2 唯一一个满足 i mod 10 == nums[i] 的下标
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5,6,7,8,9,0]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在满足 i mod 10 == nums[i] 的下标
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [2,1,3,5,2]
<strong>输出：</strong>1
<strong>解释：</strong>1 是唯一一个满足 i mod 10 == nums[i] 的下标
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们直接遍历数组，对于每个下标 $i$，我们判断是否满足 $i \bmod 10 = \textit{nums}[i]$，如果满足则返回当前下标 $i$。

如果遍历完数组都没有找到满足条件的下标，则返回 $-1$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestEqual(self, nums: List[int]) -> int:
        for i, x in enumerate(nums):
            if i % 10 == x:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestEqual(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestEqual(nums []int) int {
	for i, x := range nums {
		if i%10 == x {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestEqual(nums: number[]): number {
    for (let i = 0; i < nums.length; ++i) {
        if (i % 10 === nums[i]) {
            return i;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_equal(nums: Vec<i32>) -> i32 {
        for (i, &x) in nums.iter().enumerate() {
            if i % 10 == x as usize {
                return i as i32;
            }
        }
        -1
    }
}
```

#### Cangjie

```cj
class Solution {
    func smallestEqual(nums: Array<Int64>): Int64 {
        for (i in 0..nums.size) {
            if (i % 10 == nums[i]) {
                return i
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
