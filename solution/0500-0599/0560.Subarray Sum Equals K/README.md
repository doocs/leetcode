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
        counter = Counter({0: 1})
        ans = s = 0
        for num in nums:
            s += num
            ans += counter[s - k]
            counter[s] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(0, 1);
        int ans = 0, s = 0;
        for (int num : nums) {
            s += num;
            ans += counter.getOrDefault(s - k, 0);
            counter.put(s, counter.getOrDefault(s, 0) + 1);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function subarraySum(nums: number[], k: number): number {
    let ans = 0,
        s = 0;
    let counter = new Map();
    counter[0] = 1;
    for (const num of nums) {
        s += num;
        ans += counter[s - k] || 0;
        counter[s] = (counter[s] || 0) + 1;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        counter[0] = 1;
        int ans = 0, s = 0;
        for (int& num : nums)
        {
            s += num;
            ans += counter[s - k];
            ++counter[s];
        }
        return ans;
    }
};
```

### **Go**

```go
func subarraySum(nums []int, k int) int {
	counter := map[int]int{0: 1}
	ans, s := 0, 0
	for _, num := range nums {
		s += num
		ans += counter[s-k]
		counter[s]++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
