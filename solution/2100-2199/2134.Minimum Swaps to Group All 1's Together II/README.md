---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2134.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together%20II/README.md
rating: 1748
tags:
    - 数组
    - 滑动窗口
---

# [2134. 最少交换次数来组合所有的 1 II](https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii)

[English Version](/solution/2100-2199/2134.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>交换</strong> 定义为选中一个数组中的两个 <strong>互不相同</strong> 的位置并交换二者的值。</p>

<p><strong>环形</strong> 数组是一个数组，可以认为 <strong>第一个</strong> 元素和 <strong>最后一个</strong> 元素 <strong>相邻</strong> 。</p>

<p>给你一个 <strong>二进制环形</strong> 数组 <code>nums</code> ，返回在 <strong>任意位置</strong> 将数组中的所有 <code>1</code> 聚集在一起需要的最少交换次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,0,1,1,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>这里列出一些能够将所有 1 聚集在一起的方案：
[0,<strong><em>0</em></strong>,<em><strong>1</strong></em>,1,1,0,0] 交换 1 次。
[0,1,<em><strong>1</strong></em>,1,<em><strong>0</strong></em>,0,0] 交换 1 次。
[1,1,0,0,0,0,1] 交换 2 次（利用数组的环形特性）。
无法在交换 0 次的情况下将数组中的所有 1 聚集在一起。
因此，需要的最少交换次数为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,1,1,0,0,1,1,0]
<strong>输出：</strong>2
<strong>解释：</strong>这里列出一些能够将所有 1 聚集在一起的方案：
[1,1,1,0,0,0,0,1,1] 交换 2 次（利用数组的环形特性）。
[1,1,1,1,1,0,0,0,0] 交换 2 次。
无法在交换 0 次或 1 次的情况下将数组中的所有 1 聚集在一起。
因此，需要的最少交换次数为 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,0,0,1]
<strong>输出：</strong>0
<strong>解释：</strong>得益于数组的环形特性，所有的 1 已经聚集在一起。
因此，需要的最少交换次数为 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 为 <code>0</code> 或者 <code>1</code></li>
</ul>

## 解法

### 方法一：滑动窗口

我们先统计数组中 $1$ 的个数，记为 $k$，那么题目实际上就是求一个长度为 $k$ 的环形子数组，使得子数组中 $1$ 的个数最多，那么最少交换次数就是 $k$ 减去子数组中 $1$ 的个数最多的那个子数组中 $1$ 的个数。

我们可以使用滑动窗口来解决这个问题，首先统计数组中前 $k$ 个元素中 $1$ 的个数，记为 $cnt$，然后我们维护一个长度为 $k$ 的滑动窗口，每次向右移动一个位置，更新 $cnt$，同时更新最大的 $cnt$ 值，即 $mx = \max(mx, cnt)$，最后答案就是 $k - mx$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        k = nums.count(1)
        mx = cnt = sum(nums[:k])
        n = len(nums)
        for i in range(k, n + k):
            cnt += nums[i % n]
            cnt -= nums[(i - k + n) % n]
            mx = max(mx, cnt)
        return k - mx
```

```java
class Solution {
    public int minSwaps(int[] nums) {
        int k = Arrays.stream(nums).sum();
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += nums[i];
        }
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.max(mx, cnt);
        }
        return k - mx;
    }
}
```

```cpp
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int k = accumulate(nums.begin(), nums.end(), 0);
        int n = nums.size();
        int cnt = accumulate(nums.begin(), nums.begin() + k, 0);
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = max(mx, cnt);
        }
        return k - mx;
    }
};
```

```go
func minSwaps(nums []int) int {
	k := 0
	for _, x := range nums {
		k += x
	}
	cnt := 0
	for i := 0; i < k; i++ {
		cnt += nums[i]
	}
	mx := cnt
	n := len(nums)
	for i := k; i < n+k; i++ {
		cnt += nums[i%n] - nums[(i-k+n)%n]
		mx = max(mx, cnt)
	}
	return k - mx
}
```

```ts
function minSwaps(nums: number[]): number {
    const k = nums.reduce((a, b) => a + b, 0);
    let cnt = nums.slice(0, k).reduce((a, b) => a + b, 0);
    let mx = cnt;
    const n = nums.length;
    for (let i = k; i < n + k; ++i) {
        cnt += nums[i % n] - nums[(i - k + n) % n];
        mx = Math.max(mx, cnt);
    }
    return k - mx;
}
```

```rust
impl Solution {
    pub fn min_swaps(nums: Vec<i32>) -> i32 {
        let k: i32 = nums.iter().sum();
        let n: usize = nums.len();
        let mut cnt: i32 = 0;
        for i in 0..k {
            cnt += nums[i as usize];
        }
        let mut mx: i32 = cnt;
        for i in k..(n as i32) + k {
            cnt +=
                nums[(i % (n as i32)) as usize] -
                nums[((i - k + (n as i32)) % (n as i32)) as usize];
            mx = mx.max(cnt);
        }
        return k - mx;
    }
}
```

```cs
public class Solution {
    public int MinSwaps(int[] nums) {
        int k = nums.Sum();
        int n = nums.Length;
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += nums[i];
        }
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.Max(mx, cnt);
        }
        return k - mx;
    }
}
```

<!-- tabs:end -->

<!-- end -->
