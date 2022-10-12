# [525. Contiguous Array](https://leetcode.com/problems/contiguous-array)

[中文文档](/solution/0500-0599/0525.Contiguous%20Array/README.md)

## Description

<p>Given a binary array <code>nums</code>, return <em>the maximum length of a contiguous subarray with an equal number of </em><code>0</code><em> and </em><code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

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
