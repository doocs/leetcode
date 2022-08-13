# [974. 和可被 K 整除的子数组](https://leetcode.cn/problems/subarray-sums-divisible-by-k)

[English Version](/solution/0900-0999/0974.Subarray%20Sums%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数 <code>k</code> ，返回其中元素之和可被 <code>k</code>&nbsp;整除的（连续、非空） <strong>子数组</strong> 的数目。</p>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,0,-2,-3,1], k = 5
<strong>输出：</strong>7
<strong>解释：
</strong>有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [5], k = 9
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 哈希表。

注意：不同的语言负数取模的值不一定相同，有的语言为负数，对于这种情况需要特殊处理。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarraysDivByK(self, nums: List[int], k: int) -> int:
        ans = s = 0
        counter = Counter({0: 1})
        for num in nums:
            s += num
            ans += counter[s % k]
            counter[s % k] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(0, 1);
        int s = 0, ans = 0;
        for (int num : nums) {
            s += num;
            int t = (s % k + k) % k;
            ans += counter.getOrDefault(t, 0);
            counter.put(t, counter.getOrDefault(t, 0) + 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        counter[0] = 1;
        int s = 0, ans = 0;
        for (int& num : nums) {
            s += num;
            int t = (s % k + k) % k;
            ans += counter[t];
            ++counter[t];
        }
        return ans;
    }
};
```

### **Go**

```go
func subarraysDivByK(nums []int, k int) int {
	counter := map[int]int{0: 1}
	ans, s := 0, 0
	for _, num := range nums {
		s += num
		t := (s%k + k) % k
		ans += counter[t]
		counter[t]++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
