# [1755. 最接近目标值的子序列和](https://leetcode.cn/problems/closest-subsequence-sum)

[English Version](/solution/1700-1799/1755.Closest%20Subsequence%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个目标值 <code>goal</code> 。</p>

<p>你需要从 <code>nums</code> 中选出一个子序列，使子序列元素总和最接近 <code>goal</code> 。也就是说，如果子序列元素和为 <code>sum</code> ，你需要 <strong>最小化绝对差</strong> <code>abs(sum - goal)</code> 。</p>

<p>返回 <code>abs(sum - goal)</code> 可能的 <strong>最小值</strong> 。</p>

<p>注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [5,-7,3,5], goal = 6
<strong>输出：</strong>0
<strong>解释：</strong>选择整个数组作为选出的子序列，元素和为 6 。
子序列和与目标值相等，所以绝对差为 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [7,-9,15,-2], goal = -5
<strong>输出：</strong>1
<strong>解释：</strong>选出子序列 [7,-9,-2] ，元素和为 -4 。
绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], goal = -7
<strong>输出：</strong>7
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 40</code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= goal &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + 二分查找**

每个数选或不选两种可能，所以 `n` 个数就有 `2^n` 种组合，由于 `n` 最大为 40，枚举 `2^40` 种组合显然会超时。

可以把数组分成左右两部分，分别求出两部分所有子序列和（`2 * 2^(n/2)` 种组合）记为 `lsum` 和 `rsum`。最后，只需找到最接近 `goal` 的 `lsum[i] + rsum[j]`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minAbsDifference(self, nums: List[int], goal: int) -> int:
        n = len(nums)
        left = set()
        right = set()

        self.getSubSeqSum(0, 0, nums[: n // 2], left)
        self.getSubSeqSum(0, 0, nums[n // 2 :], right)

        result = inf
        right = sorted(right)
        rl = len(right)

        for l in left:
            remaining = goal - l
            idx = bisect_left(right, remaining)

            if idx < rl:
                result = min(result, abs(remaining - right[idx]))

            if idx > 0:
                result = min(result, abs(remaining - right[idx - 1]))

        return result

    def getSubSeqSum(self, i: int, curr: int, arr: List[int], result: Set[int]):
        if i == len(arr):
            result.add(curr)
            return

        self.getSubSeqSum(i + 1, curr, arr, result)
        self.getSubSeqSum(i + 1, curr + arr[i], arr, result)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        List<Integer> lsum = new ArrayList<>();
        List<Integer> rsum = new ArrayList<>();
        dfs(nums, lsum, 0, n / 2, 0);
        dfs(nums, rsum, n / 2, n, 0);

        rsum.sort(Integer::compareTo);
        int res = Integer.MAX_VALUE;

        for (Integer x : lsum) {
            int target = goal - x;
            int left = 0, right = rsum.size();
            while (left < right) {
                int mid = (left + right) >> 1;
                if (rsum.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left < rsum.size()) {
                res = Math.min(res, Math.abs(target - rsum.get(left)));
            }
            if (left > 0) {
                res = Math.min(res, Math.abs(target - rsum.get(left - 1)));
            }
        }

        return res;
    }

    private void dfs(int[] nums, List<Integer> sum, int i, int n, int cur) {
        if (i == n) {
            sum.add(cur);
            return;
        }

        dfs(nums, sum, i + 1, n, cur);
        dfs(nums, sum, i + 1, n, cur + nums[i]);
    }
}
```

### **Go**

```go
func minAbsDifference(nums []int, goal int) int {
	n := len(nums)
	lsum := make([]int, 0)
	rsum := make([]int, 0)

	dfs(nums[:n/2], &lsum, 0, 0)
	dfs(nums[n/2:], &rsum, 0, 0)

	sort.Ints(rsum)
	res := math.MaxInt32

	for _, x := range lsum {
		t := goal - x
		l, r := 0, len(rsum)
		for l < r {
			m := int(uint(l+r) >> 1)
			if rsum[m] < t {
				l = m + 1
			} else {
				r = m
			}
		}
		if l < len(rsum) {
			res = min(res, abs(t-rsum[l]))
		}
		if l > 0 {
			res = min(res, abs(t-rsum[l-1]))
		}
	}

	return res
}

func dfs(nums []int, sum *[]int, i, cur int) {
	if i == len(nums) {
		*sum = append(*sum, cur)
		return
	}

	dfs(nums, sum, i+1, cur)
	dfs(nums, sum, i+1, cur+nums[i])
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **C++**

```cpp
class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) {
        int n = nums.size();
        vector<int> lsum;
        vector<int> rsum;
        dfs(nums, lsum, 0, n / 2, 0);
        dfs(nums, rsum, n / 2, n, 0);

        sort(rsum.begin(), rsum.end());
        int res = INT_MAX;

        for (int x : lsum) {
            int target = goal - x;
            int left = 0, right = rsum.size();
            while (left < right) {
                int mid = (left + right) >> 1;
                if (rsum[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left < rsum.size()) {
                res = min(res, abs(target - rsum[left]));
            }
            if (left > 0) {
                res = min(res, abs(target - rsum[left - 1]));
            }
        }

        return res;
    }

private:
    void dfs(vector<int>& nums, vector<int>& sum, int i, int n, int cur) {
        if (i == n) {
            sum.emplace_back(cur);
            return;
        }

        dfs(nums, sum, i + 1, n, cur);
        dfs(nums, sum, i + 1, n, cur + nums[i]);
    }
};
```

### **...**

```

```

<!-- tabs:end -->
