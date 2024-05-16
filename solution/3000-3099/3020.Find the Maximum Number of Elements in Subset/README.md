---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README.md
rating: 1741
source: 第 382 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 枚举
---

# [3020. 子集中元素的最大数量](https://leetcode.cn/problems/find-the-maximum-number-of-elements-in-subset)

[English Version](/solution/3000-3099/3020.Find%20the%20Maximum%20Number%20of%20Elements%20in%20Subset/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个<strong> 正整数 </strong>数组 <code>nums</code> 。</p>

<p>你需要从数组中选出一个满足下述条件的<span data-keyword="subset">子集</span>：</p>

<ul>
	<li>你可以将选中的元素放置在一个下标从 <strong>0</strong> 开始的数组中，并使其遵循以下模式：<code>[x, x<sup>2</sup>, x<sup>4</sup>, ..., x<sup>k/2</sup>, x<sup>k</sup>, x<sup>k/2</sup>, ..., x<sup>4</sup>, x<sup>2</sup>, x]</code>（<strong>注意</strong>，<code>k</code> 可以是任何 <strong>非负</strong> 的 2 的幂）。例如，<code>[2, 4, 16, 4, 2]</code> 和 <code>[3, 9, 3]</code> 都符合这一模式，而 <code>[2, 4, 8, 4, 2]</code> 则不符合。</li>
</ul>

<p>返回满足这些条件的子集中，元素数量的 <strong>最大值 </strong><em>。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,1,2,2]
<strong>输出：</strong>3
<strong>解释：</strong>选择子集 {4,2,2} ，将其放在数组 [2,4,2] 中，它遵循该模式，且 2<sup>2</sup> == 4 。因此答案是 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2,4]
<strong>输出：</strong>1
<strong>解释：</strong>选择子集 {1}，将其放在数组 [1] 中，它遵循该模式。因此答案是 1 。注意我们也可以选择子集 {2} 、{4} 或 {3} ，可能存在多个子集都能得到相同的答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 枚举

我们用一个哈希表 $cnt$ 记录数组 $nums$ 中每个元素出现的次数。对于每个元素 $x$，我们可以将其不断平方，直到其值在哈希表 $cnt$ 中的出现次数小于 $2$ 为止。此时，我们判断 $x$ 在哈希表 $cnt$ 中的出现次数是否为 $1$，如果是则说明 $x$ 仍然可以被选入子集中，否则我们需要从子集中删除一个元素，确保子集个数为奇数。然后我们更新答案。继续枚举下一个元素。

注意我们需要特殊处理 $x = 1$ 的情况。

时间复杂度 $O(n \times \log \log M)$，空间复杂度 $O(n)$。其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度和数组 $nums$ 中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        ans = cnt[1] - (cnt[1] % 2 ^ 1)
        del cnt[1]
        for x in cnt:
            t = 0
            while cnt[x] > 1:
                x = x * x
                t += 2
            t += 1 if cnt[x] else -1
            ans = max(ans, t)
        return ans
```

```java
class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge((long) x, 1, Integer::sum);
        }
        Integer t = cnt.remove(1L);
        int ans = t == null ? 0 : t - (t % 2 ^ 1);
        for (long x : cnt.keySet()) {
            t = 0;
            while (cnt.getOrDefault(x, 0) > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.getOrDefault(x, -1);
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = cnt[1] - (cnt[1] % 2 ^ 1);
        cnt.erase(1);
        for (auto [v, _] : cnt) {
            int t = 0;
            long long x = v;
            while (cnt.count(x) && cnt[x] > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.count(x) ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```go
func maximumLength(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	ans = cnt[1] - (cnt[1]%2 ^ 1)
	delete(cnt, 1)
	for x := range cnt {
		t := 0
		for cnt[x] > 1 {
			x = x * x
			t += 2
		}
		if cnt[x] > 0 {
			t += 1
		} else {
			t -= 1
		}
		ans = max(ans, t)
	}
	return
}
```

```ts
function maximumLength(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    let ans = cnt.has(1) ? cnt.get(1)! - (cnt.get(1)! % 2 ^ 1) : 0;
    cnt.delete(1);
    for (let [x, _] of cnt) {
        let t = 0;
        while (cnt.has(x) && cnt.get(x)! > 1) {
            x = x * x;
            t += 2;
        }
        t += cnt.has(x) ? 1 : -1;
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
