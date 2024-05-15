---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0525.Contiguous%20Array/README.md
tags:
    - 数组
    - 哈希表
    - 前缀和
---

# [525. 连续数组](https://leetcode.cn/problems/contiguous-array)

[English Version](/solution/0500-0599/0525.Contiguous%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,0]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        s = ans = 0
        mp = {0: -1}
        for i, v in enumerate(nums):
            s += 1 if v == 1 else -1
            if s in mp:
                ans = max(ans, i - mp[s])
            else:
                mp[s] = i
        return ans
```

```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int s = 0, ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i] == 1 ? 1 : -1;
            if (mp.containsKey(s)) {
                ans = Math.max(ans, i - mp.get(s));
            } else {
                mp.put(s, i);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> mp;
        int s = 0, ans = 0;
        mp[0] = -1;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i] == 1 ? 1 : -1;
            if (mp.count(s))
                ans = max(ans, i - mp[s]);
            else
                mp[s] = i;
        }
        return ans;
    }
};
```

```go
func findMaxLength(nums []int) int {
	mp := map[int]int{0: -1}
	s, ans := 0, 0
	for i, v := range nums {
		if v == 0 {
			v = -1
		}
		s += v
		if j, ok := mp[s]; ok {
			ans = max(ans, i-j)
		} else {
			mp[s] = i
		}
	}
	return ans
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    const mp = new Map();
    mp.set(0, -1);
    let s = 0;
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i] == 0 ? -1 : 1;
        if (mp.has(s)) ans = Math.max(ans, i - mp.get(s));
        else mp.set(s, i);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
