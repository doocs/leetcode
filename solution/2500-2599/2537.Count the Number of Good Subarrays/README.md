---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2537.Count%20the%20Number%20of%20Good%20Subarrays/README.md
rating: 1891
source: 第 328 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

<!-- problem:start -->

# [2537. 统计好子数组的数目](https://leetcode.cn/problems/count-the-number-of-good-subarrays)

[English Version](/solution/2500-2599/2537.Count%20the%20Number%20of%20Good%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>&nbsp;和一个整数 <code>k</code>&nbsp;，请你返回 <code>nums</code>&nbsp;中 <strong>好</strong>&nbsp;子数组的数目。</p>

<p>一个子数组 <code>arr</code>&nbsp;如果有 <strong>至少</strong>&nbsp;<code>k</code>&nbsp;对下标 <code>(i, j)</code>&nbsp;满足 <code>i &lt; j</code>&nbsp;且 <code>arr[i] == arr[j]</code>&nbsp;，那么称它是一个 <strong>好</strong>&nbsp;子数组。</p>

<p><strong>子数组</strong>&nbsp;是原数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,1,1,1,1], k = 10
<b>输出：</b>1
<b>解释：</b>唯一的好子数组是这个数组本身。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [3,1,4,3,2,2,4], k = 2
<b>输出：</b>4
<b>解释：</b>总共有 4 个不同的好子数组：
- [3,1,4,3,2,2] 有 2 对。
- [3,1,4,3,2,2,4] 有 3 对。
- [1,4,3,2,2,4] 有 2 对。
- [4,3,2,2,4] 有 2 对。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 双指针

如果一个子数组中包含 $k$ 对相同的元素，那么这个子数组一定包含至少 $k$ 对相同的元素。

我们用一个哈希表 $\textit{cnt}$ 统计窗口内数组元素出现的次数，用 $\textit{cur}$ 统计窗口内相同元素的对数，用 $i$ 维护窗口的左端点。

遍历数组 $\textit{nums}$，我们将当前元素 $x$ 作为右端点，那么窗口内相同元素的对数将增加 $\textit{cnt}[x]$，同时将 $x$ 的出现次数加一，即 $\textit{cnt}[x] \leftarrow \textit{cnt}[x] + 1$。接下来，我们循环判断移出左端点后窗口内相同元素的对数是否大于等于 $k$，如果大于等于 $k$，那么我们将左端点元素的出现次数减一，即 $\textit{cnt}[\textit{nums}[i]] \leftarrow \textit{cnt}[\textit{nums}[i]] - 1$，同时将窗口内相同元素的对数减去 $\textit{cnt}[\textit{nums}[i]]$，即 $\textit{cur} \leftarrow \textit{cur} - \textit{cnt}[\textit{nums}[i]]$，同时将左端点右移，即 $i \leftarrow i + 1$。此时窗口左端点以及左侧的所有元素都可以作为当前右端点的左端点，因此答案加上 $i + 1$。

最后，我们返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGood(self, nums: List[int], k: int) -> int:
        cnt = Counter()
        ans = cur = 0
        i = 0
        for x in nums:
            cur += cnt[x]
            cnt[x] += 1
            while cur - cnt[nums[i]] + 1 >= k:
                cnt[nums[i]] -= 1
                cur -= cnt[nums[i]]
                i += 1
            if cur >= k:
                ans += i + 1
        return ans
```

#### Java

```java
class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0, cur = 0;
        int i = 0;
        for (int x : nums) {
            cur += cnt.merge(x, 1, Integer::sum) - 1;
            while (cur - cnt.get(nums[i]) + 1 >= k) {
                cur -= cnt.merge(nums[i++], -1, Integer::sum);
            }
            if (cur >= k) {
                ans += i + 1;
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
    long long countGood(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        long long ans = 0;
        long long cur = 0;
        int i = 0;
        for (int& x : nums) {
            cur += cnt[x]++;
            while (cur - cnt[nums[i]] + 1 >= k) {
                cur -= --cnt[nums[i++]];
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countGood(nums []int, k int) int64 {
	cnt := map[int]int{}
	ans, cur := 0, 0
	i := 0
	for _, x := range nums {
		cur += cnt[x]
		cnt[x]++
		for cur-cnt[nums[i]]+1 >= k {
			cnt[nums[i]]--
			cur -= cnt[nums[i]]
			i++
		}
		if cur >= k {
			ans += i + 1
		}
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countGood(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, i] = [0, 0, 0];

    for (const x of nums) {
        const count = cnt.get(x) || 0;
        cur += count;
        cnt.set(x, count + 1);

        while (cur - (cnt.get(nums[i])! - 1) >= k) {
            const countI = cnt.get(nums[i])!;
            cnt.set(nums[i], countI - 1);
            cur -= countI - 1;
            i += 1;
        }

        if (cur >= k) {
            ans += i + 1;
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn count_good(nums: Vec<i32>, k: i32) -> i64 {
        let mut cnt = HashMap::new();
        let (mut ans, mut cur, mut i) = (0i64, 0i64, 0);

        for &x in &nums {
            cur += *cnt.get(&x).unwrap_or(&0);
            *cnt.entry(x).or_insert(0) += 1;

            while cur - (cnt[&nums[i]] - 1) >= k as i64 {
                *cnt.get_mut(&nums[i]).unwrap() -= 1;
                cur -= cnt[&nums[i]];
                i += 1;
            }

            if cur >= k as i64 {
                ans += (i + 1) as i64;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
