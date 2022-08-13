# [128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence)

[English Version](/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个未排序的整数数组 <code>nums</code> ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。</p>

<p>请你设计并实现时间复杂度为 <code>O(n)</code><em> </em>的算法解决此问题。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [100,4,200,1,3,2]
<strong>输出：</strong>4
<strong>解释：</strong>最长数字连续序列是 <code>[1, 2, 3, 4]。它的长度为 4。</code></pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,3,7,2,5,8,4,6,0,1]
<strong>输出：</strong>9
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法 1：排序**

设 res 表示连续序列的最大长度，t 表示当前合法连续序列的长度，初始时 `res = t = 1`。

先排序数组，然后从下标 1 开始遍历数组，判断 `nums[i]` 与前一个数 `nums[i - 1]` 的大小关系：

-   若 `nums[i] == nums[i - 1]`，直接跳过；
-   若 `nums[i] - nums[i - 1] == 1`，说明是连续序列，t 自增，利用 `res = max(res, t)` 更新最大长度；
-   否则 t 重置为 1，继续往下遍历。

此方法时间复杂度 `O(nlogn)`，空间复杂度 `O(1)`。

**方法 2：哈希表**

设 res 表示连续序列的最大长度，初始为 0。哈希表 s 存放数组出现的每个元素。

遍历数组，以当前遍历到的元素 `nums[i]` 做为起点，循环判断 `nums[i] + 1`，`nums[i] + 2` ... 是否存在 s 中，并不断更新连续序列的最大长度。

在这个过程中，如果 `nums[i]`, `nums[i] + 1`, `nums[i + 2]`, ... 是一个连续序列，遍历下个元素 `nums[i] + 1` 时，其实无需再重复循环。因此，只需要判断 `nums[i] - 1` 是否在 s 中，是则直接跳过。

此方法时间复杂度 `O(n)`，空间复杂度 `O(n)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
