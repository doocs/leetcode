---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1403.Minimum%20Subsequence%20in%20Non-Increasing%20Order/README.md
rating: 1288
source: 第 183 场周赛 Q1
tags:
    - 贪心
    - 数组
    - 排序
---

# [1403. 非递增顺序的最小子序列](https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order)

[English Version](/solution/1400-1499/1403.Minimum%20Subsequence%20in%20Non-Increasing%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>nums</code>，请你从中抽取一个子序列，满足该子序列的元素之和 <strong>严格</strong> 大于未包含在该子序列中的各元素之和。</p>

<p>如果存在多个解决方案，只需返回 <strong>长度最小</strong> 的子序列。如果仍然有多个解决方案，则返回 <strong>元素之和最大</strong> 的子序列。</p>

<p>与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。</p>

<p><strong>注意</strong>，题目数据保证满足所有约束条件的解决方案是 <strong>唯一</strong> 的。同时，返回的答案应当按 <strong>非递增顺序</strong> 排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,10,9,8]
<strong>输出：</strong>[10,9] 
<strong>解释：</strong>子序列 [10,9] 和 [10,8] 是最小的、满足元素之和大于其他各元素之和的子序列。但是 [10,9] 的元素之和最大。&nbsp;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,4,7,6,7]
<strong>输出：</strong>[7,7,6] 
<strong>解释：</strong>子序列 [7,7] 的和为 14 ，不严格大于剩下的其他元素之和（14 = 4 + 4 + 6）。因此，[7,6,7] 是满足题意的最小子序列。注意，元素按非递增顺序返回。  
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：排序

我们可以先对数组 $nums$ 按照从大到小的顺序排序，然后依次从大到小加入数组中的元素，每次加入后判断当前元素之和是否大于剩余元素之和，如果大于则返回当前数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minSubsequence(self, nums: List[int]) -> List[int]:
        ans = []
        s, t = sum(nums), 0
        for x in sorted(nums, reverse=True):
            t += x
            ans.append(x)
            if t > s - t:
                break
        return ans
```

```java
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int s = Arrays.stream(nums).sum();
        int t = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            t += nums[i];
            ans.add(nums[i]);
            if (t > s - t) {
                break;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> minSubsequence(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        int s = accumulate(nums.begin(), nums.end(), 0);
        int t = 0;
        vector<int> ans;
        for (int x : nums) {
            t += x;
            ans.push_back(x);
            if (t > s - t) {
                break;
            }
        }
        return ans;
    }
};
```

```go
func minSubsequence(nums []int) (ans []int) {
	sort.Ints(nums)
	s, t := 0, 0
	for _, x := range nums {
		s += x
	}
	for i := len(nums) - 1; ; i-- {
		t += nums[i]
		ans = append(ans, nums[i])
		if t > s-t {
			return
		}
	}
}
```

```ts
function minSubsequence(nums: number[]): number[] {
    nums.sort((a, b) => b - a);
    const s = nums.reduce((r, c) => r + c);
    let t = 0;
    for (let i = 0; ; ++i) {
        t += nums[i];
        if (t > s - t) {
            return nums.slice(0, i + 1);
        }
    }
}
```

```rust
impl Solution {
    pub fn min_subsequence(mut nums: Vec<i32>) -> Vec<i32> {
        nums.sort_by(|a, b| b.cmp(a));
        let sum = nums.iter().sum::<i32>();
        let mut res = vec![];
        let mut t = 0;
        for num in nums.into_iter() {
            t += num;
            res.push(num);
            if t > sum - t {
                break;
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- end -->
