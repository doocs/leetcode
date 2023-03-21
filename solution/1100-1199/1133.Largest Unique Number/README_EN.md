# [1133. Largest Unique Number](https://leetcode.com/problems/largest-unique-number)

[中文文档](/solution/1100-1199/1133.Largest%20Unique%20Number/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the largest integer that only occurs once</em>. If no integer occurs once, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,7,3,9,4,9,8,3,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it is the answer.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,9,8,8]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no number that occurs only once.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestUniqueNumber(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return next((x for x in range(1000, -1, -1) if cnt[x] == 1), -1)
```

```python
class Solution:
    def largestUniqueNumber(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return max((x for x, v in cnt.items() if v == 1), default=-1)
```

### **Java**

```java
class Solution {
    public int largestUniqueNumber(int[] nums) {
        int[] cnt = new int[1001];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x = 1000; x >= 0; --x) {
            if (cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestUniqueNumber(vector<int>& nums) {
        int cnt[1001]{};
        for (int& x : nums) {
            ++cnt[x];
        }
        for (int x = 1000; ~x; --x) {
            if (cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func largestUniqueNumber(nums []int) int {
	cnt := [1001]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := 1000; x >= 0; x-- {
		if cnt[x] == 1 {
			return x
		}
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var largestUniqueNumber = function (nums) {
    const cnt = new Array(1001).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1000; x >= 0; --x) {
        if (cnt[x] == 1) {
            return x;
        }
    }
    return -1;
};
```

### **TypeScript**

```ts
function largestUniqueNumber(nums: number[]): number {
    const cnt = new Array(1001).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1000; x >= 0; --x) {
        if (cnt[x] == 1) {
            return x;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
