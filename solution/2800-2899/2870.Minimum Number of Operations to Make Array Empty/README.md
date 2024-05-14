# [2870. 使数组为空的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-empty/)

[English Version](/solution/2800-2899/2870.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Empty/README_EN.md)

<!-- tags:贪心,数组,哈希表,计数 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的正整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>你可以对数组执行以下两种操作 <strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>从数组中选择 <strong>两个</strong>&nbsp;值 <strong>相等</strong>&nbsp;的元素，并将它们从数组中 <strong>删除</strong>&nbsp;。</li>
	<li>从数组中选择 <strong>三个</strong>&nbsp;值 <strong>相等</strong>&nbsp;的元素，并将它们从数组中 <strong>删除</strong>&nbsp;。</li>
</ul>

<p>请你返回使数组为空的 <strong>最少</strong>&nbsp;操作次数，如果无法达成，请返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,3,2,2,4,2,3,4]
<b>输出：</b>4
<b>解释：</b>我们可以执行以下操作使数组为空：
- 对下标为 0 和 3 的元素执行第一种操作，得到 nums = [3,3,2,4,2,3,4] 。
- 对下标为 2 和 4 的元素执行第一种操作，得到 nums = [3,3,4,3,4] 。
- 对下标为 0 ，1 和 3 的元素执行第二种操作，得到 nums = [4,4] 。
- 对下标为 0 和 1 的元素执行第一种操作，得到 nums = [] 。
至少需要 4 步操作使数组为空。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,2,2,3,3]
<b>输出：</b>-1
<b>解释：</b>无法使数组为空。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 贪心

我们用一个哈希表 $count$ 统计数组中每个元素出现的次数，然后遍历哈希表，对于每个元素 $x$，如果 $x$ 出现的次数为 $c$，那么我们可以进行 $\lfloor \frac{c+2}{3} \rfloor$ 次操作，将 $x$ 删除，最后我们返回所有元素的操作次数之和即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        count = Counter(nums)
        ans = 0
        for c in count.values():
            if c == 1:
                return -1
            ans += (c + 2) // 3
        return ans
```

```java
class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            // count.put(num, count.getOrDefault(num, 0) + 1);
            count.merge(num, 1, Integer::sum);
        }
        int ans = 0;
        for (int c : count.values()) {
            if (c < 2) {
                return -1;
            }
            int r = c % 3;
            int d = c / 3;
            switch (r) {
                case (0) -> {
                    ans += d;
                }
                default -> {
                    ans += d + 1;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_map<int, int> count;
        for (int num : nums) {
            ++count[num];
        }
        int ans = 0;
        for (auto& [_, c] : count) {
            if (c < 2) {
                return -1;
            }
            ans += (c + 2) / 3;
        }
        return ans;
    }
};
```

```go
func minOperations(nums []int) (ans int) {
	count := map[int]int{}
	for _, num := range nums {
		count[num]++
	}
	for _, c := range count {
		if c < 2 {
			return -1
		}
		ans += (c + 2) / 3
	}
	return
}
```

```ts
function minOperations(nums: number[]): number {
    const count: Map<number, number> = new Map();
    for (const num of nums) {
        count.set(num, (count.get(num) ?? 0) + 1);
    }
    let ans = 0;
    for (const [_, c] of count) {
        if (c < 2) {
            return -1;
        }
        ans += ((c + 2) / 3) | 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
