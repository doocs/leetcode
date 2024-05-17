---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20011.%200%20%E5%92%8C%201%20%E4%B8%AA%E6%95%B0%E7%9B%B8%E5%90%8C%E7%9A%84%E5%AD%90%E6%95%B0%E7%BB%84/README.md
---

<!-- problem:start -->

# [剑指 Offer II 011. 0 和 1 个数相同的子数组](https://leetcode.cn/problems/A1NYOS)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,0]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 525&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/contiguous-array/">https://leetcode.cn/problems/contiguous-array/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和

我们可以将数组中的 $0$ 视作 $-1$，那么可以将问题转化为求最长的连续子数组，其元素和为 $0$。

我们使用哈希表记录每个前缀和第一次出现的位置。当我们枚举到位置 $i$ 时，如果 $sum[i]$ 在哈希表中已经存在，那么以 $i$ 结尾的最长连续子数组的长度为 $i - d[sum[i]]$，其中 $d[sum[i]]$ 表示前缀和 $sum[i]$ 第一次出现的位置，我们更新答案。如果 $sum[i]$ 在哈希表中不存在，我们将 $sum[i]$ 加入哈希表中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += 1 if x else -1
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int ans = 0, s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i] == 0 ? -1 : 1;
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> d;
        d[0] = -1;
        int ans = 0, s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s += nums[i] ? 1 : -1;
            if (d.count(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

```go
func findMaxLength(nums []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		if x == 1 {
			s++
		} else {
			s--
		}
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
}
```

```ts
function findMaxLength(nums: number[]): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        s += nums[i] === 0 ? -1 : 1;
        if (d.has(s)) {
            ans = Math.max(ans, i - d.get(s)!);
        } else {
            d.set(s, i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
