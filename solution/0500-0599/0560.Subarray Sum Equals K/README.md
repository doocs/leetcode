# [560. 和为 K 的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k)

[English Version](/solution/0500-0599/0560.Subarray%20Sum%20Equals%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组和一个整数&nbsp;<strong>k，</strong>你需要找到该数组中和为&nbsp;<strong>k&nbsp;</strong>的连续的子数组的个数。</p>

<p><strong>示例 1 :</strong></p>

<pre>
<strong>输入:</strong>nums = [1,1,1], k = 2
<strong>输出:</strong> 2 , [1,1] 与 [1,1] 为两种不同的情况。
</pre>

<p><strong>说明 :</strong></p>

<ol>
	<li>数组的长度为 [1, 20,000]。</li>
	<li>数组中元素的范围是 [-1000, 1000] ，且整数&nbsp;<strong>k&nbsp;</strong>的范围是&nbsp;[-1e7, 1e7]。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        mp = Counter()
        mp[0] = 1
        res = s = 0
        for num in nums:
            s += num
            res += mp[s - k]
            mp[s] += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        int s = 0;
        for (int num : nums) {
            s += num;
            res += map.getOrDefault(s - k, 0);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function subarraySum(nums: number[], k: number): number {
    let ans = 0,
        pre = 0;
    let hashTable = new Map();
    hashTable.set(0, 1);
    for (let num of nums) {
        pre += num;
        ans += hashTable.get(pre - k) || 0;
        hashTable.set(pre, (hashTable.get(pre) || 0) + 1);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = 1;
        int res = 0, s = 0;
        for (int num : nums)
        {
            s += num;
            res += mp[s - k];
            ++mp[s];
        }
        return res;
    }
};
```

### **Go**

```go
func subarraySum(nums []int, k int) int {
	mp := make(map[int]int)
	mp[0] = 1
	res, s := 0, 0
	for _, num := range nums {
		s += num
		res += mp[s-k]
		mp[s]++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
