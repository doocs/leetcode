# [2841. 几乎唯一子数组的最大和](https://leetcode.cn/problems/maximum-sum-of-almost-unique-subarray)

[English Version](/solution/2800-2899/2841.Maximum%20Sum%20of%20Almost%20Unique%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和两个正整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>k</code>&nbsp;。</p>

<p>请你返回 <code>nums</code>&nbsp;中长度为 <code>k</code>&nbsp;的&nbsp;<strong>几乎唯一</strong>&nbsp;子数组的 <strong>最大和</strong>&nbsp;，如果不存在几乎唯一子数组，请你返回 <code>0</code>&nbsp;。</p>

<p>如果 <code>nums</code>&nbsp;的一个子数组有至少 <code>m</code>&nbsp;个互不相同的元素，我们称它是 <strong>几乎唯一</strong>&nbsp;子数组。</p>

<p>子数组指的是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,6,7,3,1,7], m = 3, k = 4
<b>输出：</b>18
<b>解释：</b>总共有 3 个长度为 k = 4 的几乎唯一子数组。分别为 [2, 6, 7, 3] ，[6, 7, 3, 1] 和 [7, 3, 1, 7] 。这些子数组中，和最大的是 [2, 6, 7, 3] ，和为 18 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,9,9,2,4,5,4], m = 1, k = 3
<b>输出：</b>23
<b>解释：</b>总共有 5 个长度为 k = 3 的几乎唯一子数组。分别为 [5, 9, 9] ，[9, 9, 2] ，[9, 2, 4] ，[2, 4, 5] 和 [4, 5, 4] 。这些子数组中，和最大的是 [5, 9, 9] ，和为 23 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1,2,1,2,1], m = 3, k = 3
<b>输出：</b>0
<b>解释：</b>输入数组中不存在长度为 <code>k = 3</code> 的子数组含有至少  <code>m = 3</code> 个互不相同元素的子数组。所以不存在几乎唯一子数组，最大和为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSum(self, nums: List[int], m: int, k: int) -> int:
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = 0
        if len(cnt) >= m:
            ans = s
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            s += nums[i] - nums[i - k]
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            if len(cnt) >= m:
                ans = max(ans, s)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maxSum(List<Integer> nums, int m, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = nums.size();
        long s = 0;
        for (int i = 0; i < k; ++i) {
            cnt.merge(nums.get(i), 1, Integer::sum);
            s += nums.get(i);
        }
        long ans = 0;
        if (cnt.size() >= m) {
            ans = s;
        }
        for (int i = k; i < n; ++i) {
            cnt.merge(nums.get(i), 1, Integer::sum);
            if (cnt.merge(nums.get(i - k), -1, Integer::sum) == 0) {
                cnt.remove(nums.get(i - k));
            }
            s += nums.get(i) - nums.get(i - k);
            if (cnt.size() >= m) {
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxSum(vector<int>& nums, int m, int k) {
        unordered_map<int, int> cnt;
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
            s += nums[i];
        }
        long long ans = cnt.size() >= m ? s : 0;
        for (int i = k; i < n; ++i) {
            cnt[nums[i]]++;
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            s += nums[i] - nums[i - k];
            if (cnt.size() >= m) {
                ans = max(ans, s);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSum(nums []int, m int, k int) int64 {
	cnt := map[int]int{}
	var s int64
	for _, x := range nums[:k] {
		cnt[x]++
		s += int64(x)
	}
	var ans int64
	if len(cnt) >= m {
		ans = s
	}
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += int64(nums[i]) - int64(nums[i-k])
		if len(cnt) >= m {
			ans = max(ans, s)
		}
	}
	return ans
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxSum(nums: number[], m: number, k: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let s = 0;
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        s += nums[i];
    }
    let ans = cnt.size >= m ? s : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        s += nums[i] - nums[i - k];
        if (cnt.size >= m) {
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
