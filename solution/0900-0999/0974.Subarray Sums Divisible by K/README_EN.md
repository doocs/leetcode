# [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k)

[中文文档](/solution/0900-0999/0974.Subarray%20Sums%20Divisible%20by%20K/README.md)

## Description

<p>Given an array <code>A</code> of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by <code>K</code>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">[4,5,0,-2,-3,1]</span>, K = <span id="example-input-1-2">5</span>

<strong>Output: </strong><span id="example-output-1">7</span>

<strong>Explanation: </strong>There are 7 subarrays with a sum divisible by K = 5:

[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 30000</code></li>
	<li><code>-10000 &lt;= A[i] &lt;= 10000</code></li>
	<li><code>2 &lt;= K &lt;= 10000</code></li>
</ol>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        for (int& num : nums)
        {
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
