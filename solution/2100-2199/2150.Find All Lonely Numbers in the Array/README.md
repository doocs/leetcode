---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2150.Find%20All%20Lonely%20Numbers%20in%20the%20Array/README.md
rating: 1275
source: 第 277 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [2150. 找出数组中的所有孤独数字](https://leetcode.cn/problems/find-all-lonely-numbers-in-the-array)

[English Version](/solution/2100-2199/2150.Find%20All%20Lonely%20Numbers%20in%20the%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。如果数字 <code>x</code> 在数组中仅出现 <strong>一次</strong> ，且没有 <strong>相邻</strong> 数字（即，<code>x + 1</code> 和 <code>x - 1</code>）出现在数组中，则认为数字 <code>x</code> 是 <strong>孤独数字</strong> 。</p>

<p>返回<em> </em><code>nums</code> 中的 <strong>所有</strong> 孤独数字。你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [10,6,5,8]
<strong>输出：</strong>[10,8]
<strong>解释：</strong>
- 10 是一个孤独数字，因为它只出现一次，并且 9 和 11 没有在 nums 中出现。
- 8 是一个孤独数字，因为它只出现一次，并且 7 和 9 没有在 nums 中出现。
- 5 不是一个孤独数字，因为 6 出现在 nums 中，反之亦然。
因此，nums 中的孤独数字是 [10, 8] 。
注意，也可以返回 [8, 10] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,5,3]
<strong>输出：</strong>[1,5]
<strong>解释：</strong>
- 1 是一个孤独数字，因为它只出现一次，并且 0 和 2 没有在 nums 中出现。
- 5 是一个孤独数字，因为它只出现一次，并且 4 和 6 没有在 nums 中出现。
- 3 不是一个孤独数字，因为它出现两次。
因此，nums 中的孤独数字是 [1, 5] 。
注意，也可以返回 [5, 1] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{cnt}$ 记录每个数字出现的次数，然后遍历哈希表，对于每个数字及其出现次数 $(x, v)$，如果 $v = 1$ 且 $\textit{cnt}[x - 1] = 0$ 且 $\textit{cnt}[x + 1] = 0$，则 $x$ 是一个孤独数字，将其加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLonely(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return [
            x for x, v in cnt.items() if v == 1 and cnt[x - 1] == 0 and cnt[x + 1] == 0
        ]
```

#### Java

```java
class Solution {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v == 1 && !cnt.containsKey(x - 1) && !cnt.containsKey(x + 1)) {
                ans.add(x);
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
    vector<int> findLonely(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            cnt[x]++;
        }
        vector<int> ans;
        for (auto& [x, v] : cnt) {
            if (v == 1 && !cnt.contains(x - 1) && !cnt.contains(x + 1)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findLonely(nums []int) (ans []int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x, v := range cnt {
		if v == 1 && cnt[x-1] == 0 && cnt[x+1] == 0 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findLonely(nums: number[]): number[] {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const ans: number[] = [];
    for (const [x, v] of cnt) {
        if (v === 1 && !cnt.has(x - 1) && !cnt.has(x + 1)) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
