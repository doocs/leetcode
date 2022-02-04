# [525. Contiguous Array](https://leetcode.com/problems/contiguous-array)

[中文文档](/solution/0500-0599/0525.Contiguous%20Array/README.md)

## Description

<p>Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1. </p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [0,1]

<b>Output:</b> 2

<b>Explanation:</b> [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> [0,1,0]

<b>Output:</b> 2

<b>Explanation:</b> [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

</pre>

</p>

<p><b>Note:</b>

The length of the given binary array will not exceed 50,000.

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int, int> mp;
        int s = 0, ans = 0;
        mp[0] = -1;
        for (int i = 0; i < nums.size(); ++i)
        {
            s += nums[i] == 1 ? 1 : -1;
            if (mp.count(s)) ans = max(ans, i - mp[s]);
            else mp[s] = i;
        }
        return ans;
    }
};
```

### **Go**

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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

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

### **...**

```

```

<!-- tabs:end -->
