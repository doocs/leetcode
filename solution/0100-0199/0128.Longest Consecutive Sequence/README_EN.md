# [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence)

[中文文档](/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README.md)

## Description

<p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,4,200,1,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest consecutive elements sequence is <code>[1, 2, 3, 4]</code>. Therefore its length is 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

**1. Sort**

Sort the array then compare each value. `O(nlogn)`, `O(1)`.

**2. HashSet**

Making use of hash set. `O(n)`, `O(n)`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        nums.sort()
        res = t = 1
        for i in range(1, n):
            if nums[i] == nums[i - 1]:
                continue
            if nums[i] - nums[i - 1] == 1:
                t += 1
                res = max(res, t)
            else:
                t = 1
        return res
```

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s, res = set(nums), 0
        for num in nums:
            if num - 1 not in s:
                t, next = 1, num + 1
                while next in s:
                    t, next = t + 1, next + 1
                res = max(res, t)
        return res
```

### **Java**

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return n;
        }
        Arrays.sort(nums);
        int res = 1, t = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                t += 1;
                res = Math.max(res, t);
            } else {
                t = 1;
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (!s.contains(num - 1)) {
                int t = 1, next = num + 1;
                while (s.contains(next++)) {
                    ++t;
                }
                res = Math.max(res, t);
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        int n = nums.size();
        if (n < 2)
            return n;
        sort(nums.begin(), nums.end());
        int res = 1, t = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == nums[i - 1])
                continue;
            if (nums[i] - nums[i - 1] == 1) {
                ++t;
                res = max(res, t);
            } else {
                t = 1;
            }
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s;
        for (int num : nums)
            s.insert(num);
        int res = 0;
        for (int num : nums) {
            if (!s.count(num - 1)) {
                int t = 1, next = num + 1;
                while (s.count(next++))
                    ++t;
                res = max(res, t);
            }
        }
        return res;
    }
};
```

### **Go**

```go
func longestConsecutive(nums []int) int {
	n := len(nums)
	if n < 2 {
		return n
	}
	sort.Ints(nums)
	res, t := 1, 1
	for i := 1; i < n; i++ {
		if nums[i] == nums[i-1] {
			continue
		}
		if nums[i]-nums[i-1] == 1 {
			t++
			res = max(res, t)
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func longestConsecutive(nums []int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
	}
	res := 0
	for _, num := range nums {
		if !s[num-1] {
			t, next := 1, num+1
			for s[next] {
				next++
				t++
			}
			res = max(res, t)
		}
	}
	return res
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
