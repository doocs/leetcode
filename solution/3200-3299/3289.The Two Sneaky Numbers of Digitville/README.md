---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README.md
rating: 1163
source: 第 415 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [3289. 数字小镇中的捣蛋鬼](https://leetcode.cn/problems/the-two-sneaky-numbers-of-digitville)

[English Version](/solution/3200-3299/3289.The%20Two%20Sneaky%20Numbers%20of%20Digitville/README_EN.md)

## 题目描述

<!-- description:start -->

<p>数字小镇 Digitville 中，存在一个数字列表 <code>nums</code>，其中包含从 <code>0</code> 到 <code>n - 1</code> 的整数。每个数字本应 <strong>只出现一次</strong>，然而，有 <strong>两个 </strong>顽皮的数字额外多出现了一次，使得列表变得比正常情况下更长。</p>

<p>为了恢复 Digitville 的和平，作为小镇中的名侦探，请你找出这两个顽皮的数字。</p>

<p>返回一个长度为 2 的数组，包含这两个数字（顺序任意）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,1,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,1]</span></p>

<p><strong>解释：</strong></p>

<p>数字 0 和 1 分别在数组中出现了两次。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,3,2,1,3,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,3]</span></p>

<p><strong>解释: </strong></p>

<p>数字 2 和 3 分别在数组中出现了两次。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,1,5,4,3,4,6,0,9,5,8,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,5]</span></p>

<p><strong>解释: </strong></p>

<p>数字 4 和 5 分别在数组中出现了两次。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>nums.length == n + 2</code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li>输入保证 <code>nums</code> 中<strong> 恰好 </strong>包含两个重复的元素。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个数组 $\textit{cnt}$ 记录每个数字出现的次数。

遍历数组 $\textit{nums}$，当某个数字出现次数为 $2$ 时，将其加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSneakyNumbers(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return [x for x, v in cnt.items() if v == 2]
```

#### Java

```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] ans = new int[2];
        int[] cnt = new int[100];
        int k = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans[k++] = x;
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
    vector<int> getSneakyNumbers(vector<int>& nums) {
        vector<int> ans;
        int cnt[100]{};
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getSneakyNumbers(nums []int) (ans []int) {
	cnt := [100]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] == 2 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function getSneakyNumbers(nums: number[]): number[] {
    const ans: number[] = [];
    const cnt: number[] = Array(100).fill(0);
    for (const x of nums) {
        if (++cnt[x] > 1) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn get_sneaky_numbers(nums: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for x in nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = Vec::new();
        for (x, v) in cnt {
            if v == 2 {
                ans.push(x);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：位运算

设数组 $\textit{nums}$ 的长度为 $n + 2$，其中包含 $0 \sim n - 1$ 的整数，且有两个数字出现了两次。

我们可以通过异或运算来找出这两个数字。首先，我们对数组 $\textit{nums}$ 中的所有数字以及 $0 \sim n - 1$ 的整数进行异或运算，得到的结果为这两个重复数字的异或值，记为 $xx$。

接下来，我们可以通过 $xx$ 找到这两个数字的某些特征，进而将它们分开。具体步骤如下：

1. 找到 $xx$ 的二进制表示中最低位或最高位的 $1$ 的位置，记为 $k$。这个位置表示这两个数字在该位上是不同的。
2. 根据第 $k$ 位的值，将数组 $\textit{nums}$ 中的数字以及 $0 \sim n - 1$ 的整数分成两组：一组在第 $k$ 位上为 $0$，另一组在第 $k$ 位上为 $1$。然后分别对这两组数字进行异或运算，得到的结果即为这两个重复数字。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSneakyNumbers(self, nums: List[int]) -> List[int]:
        n = len(nums) - 2
        xx = nums[n] ^ nums[n + 1]
        for i in range(n):
            xx ^= i ^ nums[i]
        k = xx.bit_length() - 1
        ans = [0, 0]
        for x in nums:
            ans[x >> k & 1] ^= x
        for i in range(n):
            ans[i >> k & 1] ^= i
        return ans
```

#### Java

```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int xx = nums[n] ^ nums[n + 1];
        for (int i = 0; i < n; ++i) {
            xx ^= i ^ nums[i];
        }
        int k = Integer.numberOfTrailingZeros(xx);
        int[] ans = new int[2];
        for (int x : nums) {
            ans[x >> k & 1] ^= x;
        }
        for (int i = 0; i < n; ++i) {
            ans[i >> k & 1] ^= i;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> getSneakyNumbers(vector<int>& nums) {
        int n = nums.size() - 2;
        int xx = nums[n] ^ nums[n + 1];
        for (int i = 0; i < n; ++i) {
            xx ^= i ^ nums[i];
        }
        int k = __builtin_ctz(xx);
        vector<int> ans(2);
        for (int x : nums) {
            ans[(x >> k) & 1] ^= x;
        }
        for (int i = 0; i < n; ++i) {
            ans[(i >> k) & 1] ^= i;
        }
        return ans;
    }
};
```

#### Go

```go
func getSneakyNumbers(nums []int) []int {
	n := len(nums) - 2
	xx := nums[n] ^ nums[n+1]
	for i := 0; i < n; i++ {
		xx ^= i ^ nums[i]
	}
	k := bits.TrailingZeros(uint(xx))
	ans := make([]int, 2)
	for _, x := range nums {
		ans[(x>>k)&1] ^= x
	}
	for i := 0; i < n; i++ {
		ans[(i>>k)&1] ^= i
	}
	return ans
}
```

#### TypeScript

```ts
function getSneakyNumbers(nums: number[]): number[] {
    const n = nums.length - 2;
    let xx = nums[n] ^ nums[n + 1];
    for (let i = 0; i < n; ++i) {
        xx ^= i ^ nums[i];
    }
    const k = Math.clz32(xx & -xx) ^ 31;
    const ans = [0, 0];
    for (const x of nums) {
        ans[(x >> k) & 1] ^= x;
    }
    for (let i = 0; i < n; ++i) {
        ans[(i >> k) & 1] ^= i;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn get_sneaky_numbers(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32 - 2;
        let mut xx = nums[n as usize] ^ nums[(n + 1) as usize];
        for i in 0..n {
            xx ^= i ^ nums[i as usize];
        }
        let k = xx.trailing_zeros();
        let mut ans = vec![0, 0];
        for &x in &nums {
            ans[((x >> k) & 1) as usize] ^= x;
        }
        for i in 0..n {
            ans[((i >> k) & 1) as usize] ^= i;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
