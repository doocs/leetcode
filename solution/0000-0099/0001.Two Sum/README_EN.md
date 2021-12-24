# [1. Two Sum](https://leetcode.com/problems/two-sum)

[中文文档](/solution/0000-0099/0001.Two%20Sum/README.md)

## Description

<p>Given an array of integers <code>nums</code>&nbsp;and an integer <code>target</code>, return <em>indices of the two numbers such that they add up to <code>target</code></em>.</p>

<p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not use the <em>same</em> element twice.</p>

<p>You can return the answer in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,11,15], target = 9
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], target = 6
<strong>Output:</strong> [1,2]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3], target = 6
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>Only one valid answer exists.</strong></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        helper = {}
        for i, v in enumerate(nums):
            num = target - v
            if num in helper:
                return [helper[num], i]
            helper[v] = i
```

### **Java**

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                return new int[]{map.get(num), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> map;
        for (int i = 0; i < nums.size(); ++i) {
            int num = target - nums[i];
            if (map.find(num) != map.end()) {
                return {map[num], i};
            }
            map[nums[i]] = i;
        }
        return {};
    }
};
```

### **Go**

```go
func twoSum(nums []int, target int) []int {
	numMap := make(map[int]int)
	for i, num := range nums {
		other := target - num
		if _, ok := numMap[other]; ok {
			return []int{numMap[other], i}
		}
		numMap[num] = i
	}
	return nil
}
```

### **JavaScript**

```js
var twoSum = function (nums, target) {
    const map = new Map();
    for (let i = 0; i < nums.length; i++) {
        if (map.has(target - nums[i])) {
            return [map.get(target - nums[i]), i];
        }
        map.set(nums[i], i);
    }
    return [];
};
```

### **Swift**

```swift
class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var map = [Int: Int]()
        var i = 0
        for num in nums {
            map[num] = i
            i = i + 1
        }
        i = 0
        for num in nums {
            if let otherIndex = map[target - num], otherIndex != i {
                return [i, otherIndex]
            }
            i = i + 1
        }
        return []
    }
}
```

### **Nim**

```nim
import std/enumerate

proc twoSum(nums: seq[int], target: int): seq[int] =
    var
        bal: int
        tdx: int
    for idx, val in enumerate(nums):
        bal = target - val
        if bal in nums:
            tdx = nums.find(bal)
            if idx != tdx:
                return @[idx, tdx]

```

### **...**

```

```

<!-- tabs:end -->
