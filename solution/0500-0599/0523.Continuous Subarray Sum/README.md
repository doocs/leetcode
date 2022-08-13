# [523. 连续的子数组和](https://leetcode.cn/problems/continuous-subarray-sum)

[English Version](/solution/0500-0599/0523.Continuous%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：</p>

<ul>
	<li>子数组大小 <strong>至少为 2</strong> ，且</li>
	<li>子数组元素总和为 <code>k</code> 的倍数。</li>
</ul>

<p>如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>如果存在一个整数 <code>n</code> ，令整数 <code>x</code> 符合 <code>x = n * k</code> ，则称 <code>x</code> 是 <code>k</code> 的一个倍数。<code>0</code> 始终视为 <code>k</code> 的一个倍数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [23<u>,2,4</u>,6,7], k = 6
<strong>输出：</strong>true
<strong>解释：</strong>[2,4] 是一个大小为 2 的子数组，并且和为 6 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [<u>23,2,6,4,7</u>], k = 6
<strong>输出：</strong>true
<strong>解释：</strong>[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [23,2,6,4,7], k = 13
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>0 <= sum(nums[i]) <= 2<sup>31</sup> - 1</code></li>
	<li><code>1 <= k <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 哈希表。

要满足区间和是 k 的倍数，也即 `s[i] - s[j] = n * k` (其中 `i - j >= 2`)，变形，得 `s[i] / k - s[j] / k = n`，所以只要满足 `s[i] % k == s[j] % k` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        s = 0
        mp = {0: -1}
        for i, v in enumerate(nums):
            s += v
            r = s % k
            if r in mp and i - mp[r] >= 2:
                return True
            if r not in mp:
                mp[r] = i
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            int r = s % k;
            if (mp.containsKey(r) && i - mp.get(r) >= 2) {
                return true;
            }
            if (!mp.containsKey(r)) {
                mp.put(r, i);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = -1;
        int s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            int r = s % k;
            if (mp.count(r) && i - mp[r] >= 2) return true;
            if (!mp.count(r)) mp[r] = i;
        }
        return false;
    }
};
```

### **Go**

```go
func checkSubarraySum(nums []int, k int) bool {
	mp := map[int]int{0: -1}
	s := 0
	for i, v := range nums {
		s += v
		r := s % k
		if j, ok := mp[r]; ok && i-j >= 2 {
			return true
		}
		if _, ok := mp[r]; !ok {
			mp[r] = i
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
