---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2170.Minimum%20Operations%20to%20Make%20the%20Array%20Alternating/README.md
rating: 1662
source: 第 280 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [2170. 使数组变成交替数组的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating)

[English Version](/solution/2100-2199/2170.Minimum%20Operations%20to%20Make%20the%20Array%20Alternating/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，该数组由 <code>n</code> 个正整数组成。</p>

<p>如果满足下述条件，则数组 <code>nums</code> 是一个 <strong>交替数组</strong> ：</p>

<ul>
	<li><code>nums[i - 2] == nums[i]</code> ，其中 <code>2 &lt;= i &lt;= n - 1</code> 。</li>
	<li><code>nums[i - 1] != nums[i]</code> ，其中 <code>1 &lt;= i &lt;= n - 1</code> 。</li>
</ul>

<p>在一步 <strong>操作</strong> 中，你可以选择下标 <code>i</code> 并将 <code>nums[i]</code> <strong>更改</strong> 为 <strong>任一</strong> 正整数。</p>

<p>返回使数组变成交替数组的 <strong>最少操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,2,4,3]
<strong>输出：</strong>3
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,<em><strong>1</strong></em>,<em><strong>3</strong></em>,<em><strong>1</strong></em>] 。
在这种情况下，操作数为 3 。
可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [1,2,<em><strong>1</strong></em>,2,<em><strong>1</strong></em>].
在这种情况下，操作数为 2 。
注意，数组不能转换成 [<em><strong>2</strong></em>,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：维护奇偶位置的计数

根据题目描述，我们可以知道，如果数组 $\textit{nums}$ 是一个交替数组，那么数组中的奇数位置和偶数位置的元素一定是不同的，且奇数位置的元素相同，偶数位置的元素也相同。

要使得数组 $\textit{nums}$ 变成交替数组的操作数最少，我们可以通过统计奇数位置和偶数位置的元素的出现次数，找到偶数位置出现次数最多的两个元素 $a_0$ 和 $a_2$，以及对应的出现次数 $a_1$ 和 $a_3$；再找到奇数位置出现次数最多的两个元素 $b_0$ 和 $b_2$，以及对应的出现次数 $b_1$ 和 $b_3$。

如果 $a_0 \neq b_0$，那么我们可以将数组 $\textit{nums}$ 中偶数位置的元素全部变成 $a_0$，奇数位置的元素全部变成 $b_0$，这样操作数为 $n - (a_1 + b_1)$；如果 $a_0 = b_0$，那么我们可以将数组 $\textit{nums}$ 中偶数位置的元素全部变成 $a_0$，奇数位置的元素全部变成 $b_2$，或者将数组 $\textit{nums}$ 中偶数位置的元素全部变成 $a_2$，奇数位置的元素全部变成 $b_0$，这样操作数为 $n - \max(a_1 + b_3, a_3 + b_1)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        def f(i: int) -> Tuple[int, int, int, int]:
            k1 = k2 = 0
            cnt = Counter(nums[i::2])
            for k, v in cnt.items():
                if cnt[k1] < v:
                    k2, k1 = k1, k
                elif cnt[k2] < v:
                    k2 = k
            return k1, cnt[k1], k2, cnt[k2]

        a, b = f(0), f(1)
        n = len(nums)
        if a[0] != b[0]:
            return n - (a[1] + b[1])
        return n - max(a[1] + b[3], a[3] + b[1])
```

#### Java

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int[] a = f(nums, 0);
        int[] b = f(nums, 1);
        int n = nums.length;
        if (a[0] != b[0]) {
            return n - (a[1] + b[1]);
        }
        return n - Math.max(a[1] + b[3], a[3] + b[1]);
    }

    private int[] f(int[] nums, int i) {
        int k1 = 0, k2 = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (; i < nums.length; i += 2) {
            cnt.merge(nums[i], 1, Integer::sum);
        }
        for (var e : cnt.entrySet()) {
            int k = e.getKey(), v = e.getValue();
            if (cnt.getOrDefault(k1, 0) < v) {
                k2 = k1;
                k1 = k;
            } else if (cnt.getOrDefault(k2, 0) < v) {
                k2 = k;
            }
        }
        return new int[] {k1, cnt.getOrDefault(k1, 0), k2, cnt.getOrDefault(k2, 0)};
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        auto f = [&](int i) -> vector<int> {
            int k1 = 0, k2 = 0;
            unordered_map<int, int> cnt;
            for (; i < nums.size(); i += 2) {
                cnt[nums[i]]++;
            }
            for (auto& [k, v] : cnt) {
                if (!k1 || cnt[k1] < v) {
                    k2 = k1;
                    k1 = k;
                } else if (!k2 || cnt[k2] < v) {
                    k2 = k;
                }
            }
            return {k1, !k1 ? 0 : cnt[k1], k2, !k2 ? 0 : cnt[k2]};
        };
        vector<int> a = f(0);
        vector<int> b = f(1);
        int n = nums.size();
        if (a[0] != b[0]) {
            return n - (a[1] + b[1]);
        }
        return n - max(a[1] + b[3], a[3] + b[1]);
    }
};
```

#### Go

```go
func minimumOperations(nums []int) int {
	f := func(i int) [4]int {
		cnt := make(map[int]int)
		for ; i < len(nums); i += 2 {
			cnt[nums[i]]++
		}

		k1, k2 := 0, 0
		for k, v := range cnt {
			if cnt[k1] < v {
				k2, k1 = k1, k
			} else if cnt[k2] < v {
				k2 = k
			}
		}
		return [4]int{k1, cnt[k1], k2, cnt[k2]}
	}

	a := f(0)
	b := f(1)
	n := len(nums)
	if a[0] != b[0] {
		return n - (a[1] + b[1])
	}
	return n - max(a[1]+b[3], a[3]+b[1])
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[]): number {
    const f = (i: number): [number, number, number, number] => {
        const cnt: Map<number, number> = new Map();
        for (; i < nums.length; i += 2) {
            cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        }

        let [k1, k2] = [0, 0];
        for (const [k, v] of cnt) {
            if ((cnt.get(k1) || 0) < v) {
                k2 = k1;
                k1 = k;
            } else if ((cnt.get(k2) || 0) < v) {
                k2 = k;
            }
        }
        return [k1, cnt.get(k1) || 0, k2, cnt.get(k2) || 0];
    };

    const a = f(0);
    const b = f(1);
    const n = nums.length;
    if (a[0] !== b[0]) {
        return n - (a[1] + b[1]);
    }
    return n - Math.max(a[1] + b[3], a[3] + b[1]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
