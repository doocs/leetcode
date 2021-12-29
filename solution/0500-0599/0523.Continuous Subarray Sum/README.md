# [523. 连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum)

[English Version](/solution/0500-0599/0523.Continuous%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含<strong> 非负数</strong> 的数组和一个目标<strong> 整数</strong> <code>k</code> ，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 <code>k</code> 的倍数，即总和为 <code>n * k</code> ，其中 <code>n</code> 也是一个<strong>整数</strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[23,2,4,6,7], k = 6
<strong>输出：</strong>True
<strong>解释：</strong>[2,4] 是一个大小为 2 的子数组，并且和为 6。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[23,2,6,4,7], k = 6
<strong>输出：</strong>True
<strong>解释：</strong>[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
</pre>

<p> </p>

<p><strong>说明：</strong></p>

<ul>
	<li>数组的长度不会超过 <code>10,000</code> 。</li>
	<li>你可以认为所有数字总和在 32 位有符号整数范围内。</li>
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
        for (int i = 0; i < nums.size(); ++i)
        {
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
