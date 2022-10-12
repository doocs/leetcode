# [594. Longest Harmonious Subsequence](https://leetcode.com/problems/longest-harmonious-subsequence)

[中文文档](/solution/0500-0599/0594.Longest%20Harmonious%20Subsequence/README.md)

## Description

<p>We define a harmonious array as an array where the difference between its maximum value and its minimum value is <b>exactly</b> <code>1</code>.</p>

<p>Given an integer array <code>nums</code>, return <em>the length of its longest harmonious subsequence among all its possible subsequences</em>.</p>

<p>A <strong>subsequence</strong> of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,3,2,2,5,2,3,7]

<strong>Output:</strong> 5

<strong>Explanation:</strong> The longest harmonious subsequence is [3,2,2,2,3].

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,3,4]

<strong>Output:</strong> 2

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,1,1,1]

<strong>Output:</strong> 0

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>

    <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLHS(self, nums: List[int]) -> int:
        counter = Counter(nums)
        ans = 0
        for num in nums:
            if num + 1 in counter:
                ans = max(ans, counter[num] + counter[num + 1])
        return ans
```

```python
class Solution:
    def findLHS(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return max([counter[num] + counter[num + 1] for num in nums if num + 1 in counter], default=0)
```

### **Java**

```java
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.containsKey(num + 1)) {
                ans = Math.max(ans, counter.get(num) + counter.get(num + 1));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLHS(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.count(num + 1)) {
                ans = max(ans, counter[num] + counter[num + 1]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLHS(nums []int) int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for _, num := range nums {
		if counter[num+1] > 0 {
			ans = max(ans, counter[num]+counter[num+1])
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
