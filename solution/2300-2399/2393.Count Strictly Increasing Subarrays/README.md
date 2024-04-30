# [2393. 严格递增的子数组个数 🔒](https://leetcode.cn/problems/count-strictly-increasing-subarrays)

[English Version](/solution/2300-2399/2393.Count%20Strictly%20Increasing%20Subarrays/README_EN.md)

<!-- tags:数组,数学,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由&nbsp;<strong>正整数&nbsp;</strong>组成的数组 <code>nums</code> 。</p>

<p>返回&nbsp;<em><strong>严格递增&nbsp;</strong>顺序的 </em><code>nums</code><em>&nbsp;<strong>子数组&nbsp;</strong>的数目。</em></p>

<p data-group="1-1"><strong>子数组&nbsp;</strong>是数组的一部分，且是&nbsp;<strong>连续 </strong>的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,3,5,4,4,6]
<strong>输出:</strong> 10
<strong>解释:</strong> 严格递增的子数组如下:
- 长度为 1 的子数组: [1], [3], [5], [4], [4], [6]。
- 长度为 2 的子数组: [1,3], [3,5], [4,6]。
- 长度为 3 的子数组: [1,3,5]。
子数组的总数是 6 + 3 + 1 = 10。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5]
<strong>输出:</strong> 15
<strong>解释:</strong> 每个子数组都严格递增。我们可以取 15 个子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：双指针

利用双指针，找到每一段连续递增子数组的长度，我们记为 `cnt`，每次将 $(1+cnt)\times cnt / 2$ 累加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(1)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = i = 0
        while i < len(nums):
            j = i + 1
            while j < len(nums) and nums[j] > nums[j - 1]:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            i = j
        return ans
```

```java
class Solution {
    public long countSubarrays(int[] nums) {
        long ans = 0;
        int i = 0, n = nums.length;
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) {
                ++j;
            }
            long cnt = j - i;
            ans += (1 + cnt) * cnt / 2;
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 0;
        int i = 0, n = nums.size();
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) {
                ++j;
            }
            int cnt = j - i;
            ans += 1ll * (1 + cnt) * cnt / 2;
            i = j;
        }
        return ans;
    }
};
```

```go
func countSubarrays(nums []int) int64 {
	ans := 0
	i, n := 0, len(nums)
	for i < n {
		j := i + 1
		for j < n && nums[j] > nums[j-1] {
			j++
		}
		cnt := j - i
		ans += (1 + cnt) * cnt / 2
		i = j
	}
	return int64(ans)
}
```

```ts
function countSubarrays(nums: number[]): number {
    let ans = 0;
    let i = 0;
    const n = nums.length;
    while (i < n) {
        let j = i + 1;
        while (j < n && nums[j] > nums[j - 1]) {
            ++j;
        }
        const cnt = j - i;
        ans += ((1 + cnt) * cnt) / 2;
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：枚举

我们可以枚举数组中的每一个元素，找到以该元素为结尾的严格递增子数组的个数，然后将这些个数累加到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(1)$，其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = pre = cnt = 0
        for x in nums:
            if pre < x:
                cnt += 1
            else:
                cnt = 1
            pre = x
            ans += cnt
        return ans
```

```java
class Solution {
    public long countSubarrays(int[] nums) {
        long ans = 0;
        int pre = 0, cnt = 0;
        for (int x : nums) {
            if (pre < x) {
                ++cnt;
            } else {
                cnt = 1;
            }
            pre = x;
            ans += cnt;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 0;
        int pre = 0, cnt = 0;
        for (int x : nums) {
            if (pre < x) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
            pre = x;
        }
        return ans;
    }
};
```

```go
func countSubarrays(nums []int) (ans int64) {
	pre, cnt := 0, 0
	for _, x := range nums {
		if pre < x {
			cnt++
		} else {
			cnt = 1
		}
		ans += int64(cnt)
		pre = x
	}
	return
}
```

```ts
function countSubarrays(nums: number[]): number {
    let ans = 0;
    let pre = 0;
    let cnt = 0;
    for (const x of nums) {
        if (pre < x) {
            ++cnt;
        } else {
            cnt = 1;
        }
        ans += cnt;
        pre = x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
