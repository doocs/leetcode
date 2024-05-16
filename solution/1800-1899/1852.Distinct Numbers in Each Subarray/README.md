---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README.md
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

<!-- problem:start -->

# [1852. 每个子数组的数字种类数 🔒](https://leetcode.cn/problems/distinct-numbers-in-each-subarray)

[English Version](/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>与一个整数 <code>k</code>，请你构造一个长度 <code>n-k+1</code> 的数组 <code>ans</code>，这个数组第<code>i</code>个元素 <code>ans[i]</code> 是每个长度为k的子数组 <code>nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]]</code>中数字的种类数。</p>

<p>返回这个数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,2,2,1,3], k = 3
<strong>输出:</strong> [3,2,2,2,3]
<b>解释</b>：每个子数组的数字种类计算方法如下：
- nums[0:2] = [1,2,3] 所以 ans[0] = 3
- nums[1:3] = [2,3,2] 所以 ans[1] = 2
- nums[2:4] = [3,2,2] 所以 ans[2] = 2
- nums[3:5] = [2,2,1] 所以 ans[3] = 2
- nums[4:6] = [2,1,3] 所以 ans[4] = 3
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,1,1,2,3,4], k = 4
<strong>输出:</strong> [1,2,3,4]
<strong>解释: </strong>每个子数组的数字种类计算方法如下：
- nums[0:3] = [1,1,1,1] 所以 ans[0] = 1
- nums[1:4] = [1,1,1,2] 所以 ans[1] = 2
- nums[2:5] = [1,1,2,3] 所以 ans[2] = 3
- nums[3:6] = [1,2,3,4] 所以 ans[3] = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口 + 哈希表

我们用一个哈希表 $cnt$ 记录每个长度为 $k$ 的子数组中数字的出现次数。

接下来，我们首先遍历数组前 $k$ 个元素，记录每个元素出现的次数，遍历后，我们将哈希表的大小作为答案数组的第一个元素。

然后，我们从下标 $k$ 继续遍历数组，每次遍历时，我们将当前元素的出现次数加一，并且将当前元素左边的元素的出现次数减一，如果减一后的出现次数为 $0$，则将其从哈希表中删除，然后将哈希表的大小作为答案数组的下一个元素，继续遍历。

遍历结束后，我们返回答案数组。

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为数组 $nums$ 的长度，而 $k$ 为题目给定的参数。

<!-- tabs:start -->

```python
class Solution:
    def distinctNumbers(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums[:k])
        ans = [len(cnt)]
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            ans.append(len(cnt))
        return ans
```

```java
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = cnt.size();
        for (int i = k; i < n; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            if (cnt.merge(nums[i - k], -1, Integer::sum) == 0) {
                cnt.remove(nums[i - k]);
            }
            ans[i - k + 1] = cnt.size();
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i]];
        }
        int n = nums.size();
        vector<int> ans;
        ans.push_back(cnt.size());
        for (int i = k; i < n; ++i) {
            ++cnt[nums[i]];
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            ans.push_back(cnt.size());
        }
        return ans;
    }
};
```

```go
func distinctNumbers(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, x := range nums[:k] {
		cnt[x]++
	}
	ans := []int{len(cnt)}
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		ans = append(ans, len(cnt))
	}
	return ans
}
```

```ts
function distinctNumbers(nums: number[], k: number): number[] {
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
    }
    const ans: number[] = [cnt.size];
    for (let i = k; i < nums.length; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        ans.push(cnt.size);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：滑动窗口 + 数组

我们也可以用数组来代替哈希表，这样可以提升一定的性能。

时间复杂度 $O(n)$，空间复杂度 $O(M)$。其中 $n$ 为数组 $nums$ 的长度，而 $M$ 为数组 $nums$ 中的最大值，本题中 $M \leq 10^5$。

<!-- tabs:start -->

```java
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[] cnt = new int[m + 1];
        int v = 0;
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        int m = *max_element(begin(nums), end(nums));
        int cnt[m + 1];
        memset(cnt, 0, sizeof(cnt));
        int n = nums.size();
        int v = 0;
        vector<int> ans(n - k + 1);
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
};
```

```go
func distinctNumbers(nums []int, k int) (ans []int) {
	m := slices.Max(nums)
	cnt := make([]int, m+1)
	v := 0
	for _, x := range nums[:k] {
		cnt[x]++
		if cnt[x] == 1 {
			v++
		}
	}
	ans = append(ans, v)
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		if cnt[nums[i]] == 1 {
			v++
		}
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			v--
		}
		ans = append(ans, v)
	}
	return
}
```

```ts
function distinctNumbers(nums: number[], k: number): number[] {
    const m = Math.max(...nums);
    const cnt: number[] = Array(m + 1).fill(0);
    let v: number = 0;
    for (let i = 0; i < k; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
    }
    const ans: number[] = [v];
    for (let i = k; i < nums.length; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
        if (--cnt[nums[i - k]] === 0) {
            v--;
        }
        ans.push(v);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
