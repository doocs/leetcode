# [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k)

[中文文档](/solution/0500-0599/0560.Subarray%20Sum%20Equals%20K/README.md)

## Description

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the total number of continuous subarrays whose sum equals to <code>k</code></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1], k = 2
<strong>Output:</strong> 2
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3], k = 3
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
