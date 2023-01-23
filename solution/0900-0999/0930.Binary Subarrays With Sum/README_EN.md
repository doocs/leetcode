# [930. Binary Subarrays With Sum](https://leetcode.com/problems/binary-subarrays-with-sum)

[中文文档](/solution/0900-0999/0930.Binary%20Subarrays%20With%20Sum/README.md)

## Description

<p>Given a binary array <code>nums</code> and an integer <code>goal</code>, return <em>the number of non-empty <strong>subarrays</strong> with a sum</em> <code>goal</code>.</p>

<p>A <strong>subarray</strong> is a contiguous part of the array.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,0,1,0,1], goal = 2

<strong>Output:</strong> 4

<strong>Explanation:</strong> The 4 subarrays are bolded and underlined below:

[<u><strong>1,0,1</strong></u>,0,1]

[<u><strong>1,0,1,0</strong></u>,1]

[1,<u><strong>0,1,0,1</strong></u>]

[1,0,<u><strong>1,0,1</strong></u>]

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [0,0,0,0,0], goal = 0

<strong>Output:</strong> 15

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
    <li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
    <li><code>0 &lt;= goal &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        cnt = Counter({0: 1})
        ans = s = 0
        for v in nums:
            s += v
            ans += cnt[s - goal]
            cnt[s] += 1
        return ans
```

```python
class Solution:
    def numSubarraysWithSum(self, nums: List[int], goal: int) -> int:
        i1 = i2 = s1 = s2 = j = ans = 0
        n = len(nums)
        while j < n:
            s1 += nums[j]
            s2 += nums[j]
            while i1 <= j and s1 > goal:
                s1 -= nums[i1]
                i1 += 1
            while i2 <= j and s2 >= goal:
                s2 -= nums[i2]
                i2 += 1
            ans += i2 - i1
            j += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int[] cnt = new int[nums.length + 1];
        cnt[0] = 1;
        int ans = 0, s = 0;
        for (int v : nums) {
            s += v;
            if (s - goal >= 0) {
                ans += cnt[s - goal];
            }
            ++cnt[s];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int i1 = 0, i2 = 0, s1 = 0, s2 = 0, j = 0, ans = 0;
        int n = nums.length;
        while (j < n) {
            s1 += nums[j];
            s2 += nums[j];
            while (i1 <= j && s1 > goal) {
                s1 -= nums[i1++];
            }
            while (i2 <= j && s2 >= goal) {
                s2 -= nums[i2++];
            }
            ans += i2 - i1;
            ++j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubarraysWithSum(vector<int>& nums, int goal) {
        int cnt[nums.size() + 1];
        memset(cnt, 0, sizeof cnt);
        cnt[0] = 1;
        int ans = 0, s = 0;
        for (int& v : nums) {
            s += v;
            if (s - goal >= 0) {
                ans += cnt[s - goal];
            }
            ++cnt[s];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int numSubarraysWithSum(vector<int>& nums, int goal) {
        int i1 = 0, i2 = 0, s1 = 0, s2 = 0, j = 0, ans = 0;
        int n = nums.size();
        while (j < n)
        {
            s1 += nums[j];
            s2 += nums[j];
            while (i1 <= j && s1 > goal) s1 -= nums[i1++];
            while (i2 <= j && s2 >= goal) s2 -= nums[i2++];
            ans += i2 - i1;
            ++j;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubarraysWithSum(nums []int, goal int) (ans int) {
	cnt := map[int]int{0: 1}
	s := 0
	for _, v := range nums {
		s += v
		ans += cnt[s-goal]
		cnt[s]++
	}
	return
}
```

```go
func numSubarraysWithSum(nums []int, goal int) int {
	i1, i2, s1, s2, j, ans, n := 0, 0, 0, 0, 0, 0, len(nums)
	for j < n {
		s1 += nums[j]
		s2 += nums[j]
		for i1 <= j && s1 > goal {
			s1 -= nums[i1]
			i1++
		}
		for i2 <= j && s2 >= goal {
			s2 -= nums[i2]
			i2++
		}
		ans += i2 - i1
		j++
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function (nums, goal) {
    const cnt = new Array(nums.length + 1).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let s = 0;
    for (const v of nums) {
        s += v;
        if (s >= goal) {
            ans += cnt[s - goal];
        }
        ++cnt[s];
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function (nums, goal) {
    let i1 = 0,
        i2 = 0,
        s1 = 0,
        s2 = 0,
        j = 0,
        ans = 0;
    const n = nums.length;
    while (j < n) {
        s1 += nums[j];
        s2 += nums[j];
        while (i1 <= j && s1 > goal) s1 -= nums[i1++];
        while (i2 <= j && s2 >= goal) s2 -= nums[i2++];
        ans += i2 - i1;
        ++j;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
