---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1005.Maximize%20Sum%20Of%20Array%20After%20K%20Negations/README.md
rating: 1274
source: 第 127 场周赛 Q1
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [1005. K 次取反后最大化的数组和](https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations)

[English Version](/solution/1000-1099/1005.Maximize%20Sum%20Of%20Array%20After%20K%20Negations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，按以下方法修改该数组：</p>

<ul>
	<li>选择某个下标 <code>i</code>&nbsp;并将 <code>nums[i]</code> 替换为 <code>-nums[i]</code> 。</li>
</ul>

<p>重复这个过程恰好 <code>k</code> 次。可以多次选择同一个下标 <code>i</code> 。</p>

<p>以这种方式修改数组后，返回数组 <strong>可能的最大和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,2,3], k = 1
<strong>输出：</strong>5
<strong>解释：</strong>选择下标 1 ，nums 变为 [4,-2,3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-1,0,2], k = 3
<strong>输出：</strong>6
<strong>解释：</strong>选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-3,-1,5,-4], k = 2
<strong>输出：</strong>13
<strong>解释：</strong>选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 计数

我们观察发现，要使得数组的和尽可能大，就应该尽可能地将数组中的最小负数变成正数。

而题目中元素的范围为 $[-100,100]$，因此，我们可以先用哈希表 $\textit{cnt}$ 统计数组 $\textit{nums}$ 中每个元素出现的次数。接着从 $-100$ 开始遍历 $x$，如果哈希表中存在 $x$，那么我们取 $m = \min(cnt[x], k)$ 作为元素 $x$ 取反的个数，然后我们将 $\textit{cnt}[x]$ 减去 $m$，将 $\textit{cnt}[-x]$ 加上 $m$，并将 $k$ 减去 $m$。如果 $k$ 为 $0$，说明操作已经结束，直接退出循环即可。

如果 $k$ 仍然为奇数，且 $\textit{cnt}[0]=0$，那么我们还需要取 $\textit{cnt}$ 中最小的一个正数 $x$，将 $\textit{cnt}[x]$ 减去 $1$，将 $\textit{cnt}[-x]$ 加上 $1$。

最后，我们遍历哈希表 $\textit{cnt}$，将 $x$ 与 $\textit{cnt}[x]$ 相乘的结果累加，即为答案。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 和 $M$ 分别为数组 $\textit{nums}$ 的长度和 $\textit{nums}$ 的数据范围大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums)
        for x in range(-100, 0):
            if cnt[x]:
                m = min(cnt[x], k)
                cnt[x] -= m
                cnt[-x] += m
                k -= m
                if k == 0:
                    break
        if k & 1 and cnt[0] == 0:
            for x in range(1, 101):
                if cnt[x]:
                    cnt[x] -= 1
                    cnt[-x] += 1
                    break
        return sum(x * v for x, v in cnt.items())
```

#### Java

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt.getOrDefault(x, 0) > 0) {
                int m = Math.min(cnt.get(x), k);
                cnt.merge(x, -m, Integer::sum);
                cnt.merge(-x, m, Integer::sum);
                k -= m;
            }
        }
        if ((k & 1) == 1 && cnt.getOrDefault(0, 0) == 0) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt.getOrDefault(x, 0) > 0) {
                    cnt.merge(x, -1, Integer::sum);
                    cnt.merge(-x, 1, Integer::sum);
                    break;
                }
            }
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            ans += e.getKey() * e.getValue();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestSumAfterKNegations(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            ++cnt[x];
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt[x]) {
                int m = min(cnt[x], k);
                cnt[x] -= m;
                cnt[-x] += m;
                k -= m;
            }
        }
        if ((k & 1) && !cnt[0]) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt[x]) {
                    --cnt[x];
                    ++cnt[-x];
                    break;
                }
            }
        }
        int ans = 0;
        for (auto& [x, v] : cnt) {
            ans += x * v;
        }
        return ans;
    }
};
```

#### Go

```go
func largestSumAfterKNegations(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := -100; x < 0 && k > 0; x++ {
		if cnt[x] > 0 {
			m := min(k, cnt[x])
			cnt[x] -= m
			cnt[-x] += m
			k -= m
		}
	}
	if k&1 == 1 && cnt[0] == 0 {
		for x := 1; x <= 100; x++ {
			if cnt[x] > 0 {
				cnt[x]--
				cnt[-x]++
				break
			}
		}
	}
	for x, v := range cnt {
		ans += x * v
	}
	return
}
```

#### TypeScript

```ts
function largestSumAfterKNegations(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    for (let x = -100; x < 0 && k > 0; ++x) {
        if (cnt.get(x)! > 0) {
            const m = Math.min(cnt.get(x) || 0, k);
            cnt.set(x, (cnt.get(x) || 0) - m);
            cnt.set(-x, (cnt.get(-x) || 0) + m);
            k -= m;
        }
    }
    if ((k & 1) === 1 && (cnt.get(0) || 0) === 0) {
        for (let x = 1; x <= 100; ++x) {
            if (cnt.get(x)! > 0) {
                cnt.set(x, (cnt.get(x) || 0) - 1);
                cnt.set(-x, (cnt.get(-x) || 0) + 1);
                break;
            }
        }
    }
    return Array.from(cnt.entries()).reduce((acc, [k, v]) => acc + k * v, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
