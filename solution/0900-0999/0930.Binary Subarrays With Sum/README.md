# [930. 和相同的二元子数组](https://leetcode.cn/problems/binary-subarrays-with-sum)

[English Version](/solution/0900-0999/0930.Binary%20Subarrays%20With%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二元数组 <code>nums</code> ，和一个整数 <code>goal</code> ，请你统计并返回有多少个和为 <code>goal</code> 的<strong> 非空</strong> 子数组。</p>

<p><strong>子数组</strong> 是数组的一段连续部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,0,1], goal = 2
<strong>输出：</strong>4
<strong>解释：</strong>
有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0,0,0], goal = 0
<strong>输出：</strong>15
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
	<li><code>0 <= goal <= nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表 + 前缀和**

我们可以用数组或哈希表 $cnt$ 记录每个前缀和出现的次数，其中 $cnt[i]$ 表示前缀和为 $i$ 的子数组个数。初始时 $cnt[0] = 1$。

接下来我们遍历数组 `nums`，用变量 $s$ 维护当前的前缀和，对于每个 $s$，我们可以计算出 $s - goal$ 出现的次数，即为以当前位置结尾的满足条件的子数组个数，累加到答案中。然后我们将 $s$ 的计数值加 $1$。

最终的答案即为满足条件的子数组个数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：双指针**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        cnt = Counter({0: 1})
        ans = s = 0
        for v in nums:
            s += v
            ans += cnt[s - goal]
            cnt[s] += 1
        return ans
```

```python
class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        i1 = i2 = s1 = s2 = j = ans = 0
        n = len(nums)
        while j < n:
            s1 += nums[j]
            s2 += nums[j]
            while i1 <= j and s1 > goal:
                s1 -= nums[i1]
                i1 += 1
            while i2 <= j and s2 >= goal:
                s2 -= nums[i2]
                i2 += 1
            ans += i2 - i1
            j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int[] cnt = new int[nums.length + 1];
        cnt[0] = 1;
        int ans = 0, s = 0;
        for (int v : nums) {
            s += v;
            if (s - goal >= 0) {
                ans += cnt[s - goal];
            }
            ++cnt[s];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int i1 = 0, i2 = 0, s1 = 0, s2 = 0, j = 0, ans = 0;
        int n = nums.length;
        while (j < n) {
            s1 += nums[j];
            s2 += nums[j];
            while (i1 <= j && s1 > goal) {
                s1 -= nums[i1++];
            }
            while (i2 <= j && s2 >= goal) {
                s2 -= nums[i2++];
            }
            ans += i2 - i1;
            ++j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubarraysWithSum(vector<int>& nums, int goal) {
        int cnt[nums.size() + 1];
        memset(cnt, 0, sizeof cnt);
        cnt[0] = 1;
        int ans = 0, s = 0;
        for (int& v : nums) {
            s += v;
            if (s - goal >= 0) {
                ans += cnt[s - goal];
            }
            ++cnt[s];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int numSubarraysWithSum(vector<int>& nums, int goal) {
        int i1 = 0, i2 = 0, s1 = 0, s2 = 0, j = 0, ans = 0;
        int n = nums.size();
        while (j < n)
        {
            s1 += nums[j];
            s2 += nums[j];
            while (i1 <= j && s1 > goal) s1 -= nums[i1++];
            while (i2 <= j && s2 >= goal) s2 -= nums[i2++];
            ans += i2 - i1;
            ++j;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubarraysWithSum(nums []int, goal int) (ans int) {
	cnt := map[int]int{0: 1}
	s := 0
	for _, v := range nums {
		s += v
		ans += cnt[s-goal]
		cnt[s]++
	}
	return
}
```

```go
func numSubarraysWithSum(nums []int, goal int) int {
	i1, i2, s1, s2, j, ans, n := 0, 0, 0, 0, 0, 0, len(nums)
	for j < n {
		s1 += nums[j]
		s2 += nums[j]
		for i1 <= j && s1 > goal {
			s1 -= nums[i1]
			i1++
		}
		for i2 <= j && s2 >= goal {
			s2 -= nums[i2]
			i2++
		}
		ans += i2 - i1
		j++
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function (nums, goal) {
    const cnt = new Array(nums.length + 1).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let s = 0;
    for (const v of nums) {
        s += v;
        if (s >= goal) {
            ans += cnt[s - goal];
        }
        ++cnt[s];
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function (nums, goal) {
    let i1 = 0,
        i2 = 0,
        s1 = 0,
        s2 = 0,
        j = 0,
        ans = 0;
    const n = nums.length;
    while (j < n) {
        s1 += nums[j];
        s2 += nums[j];
        while (i1 <= j && s1 > goal) s1 -= nums[i1++];
        while (i2 <= j && s2 >= goal) s2 -= nums[i2++];
        ans += i2 - i1;
        ++j;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
