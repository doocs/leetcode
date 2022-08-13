# [410. 分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum)

[English Version](/solution/0400-0499/0410.Split%20Array%20Largest%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数数组 <code>nums</code> 和一个整数&nbsp;<code>m</code> ，你需要将这个数组分成&nbsp;<code>m</code><em>&nbsp;</em>个非空的连续子数组。</p>

<p>设计一个算法使得这&nbsp;<code>m</code><em>&nbsp;</em>个子数组各自和的最大值最小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,2,5,10,8], m = 2
<strong>输出：</strong>18
<strong>解释：</strong>
一共有四种方法将 nums 分割为 2 个子数组。 
其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5], m = 2
<strong>输出：</strong>9
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,4], m = 3
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= m &lt;= min(50, nums.length)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

二分枚举**子数组的和的最大值**，找到满足条件的最小值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        def check(x):
            s, cnt = 0, 1
            for num in nums:
                if s + num > x:
                    cnt += 1
                    s = num
                else:
                    s += num
            return cnt <= m

        left, right = max(nums), sum(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int splitArray(int[] nums, int m) {
        int mx = -1;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int left = mx, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int m, int x) {
        int s = 0, cnt = 1;
        for (int num : nums) {
            if (s + num > x) {
                ++cnt;
                s = num;
            } else {
                s += num;
            }
        }
        return cnt <= m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int splitArray(vector<int>& nums, int m) {
        int left = *max_element(nums.begin(), nums.end()), right = (int)1e9;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(nums, m, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    bool check(vector<int>& nums, int m, int x) {
        int s = 0, cnt = 1;
        for (int num : nums) {
            if (s + num > x) {
                ++cnt;
                s = num;
            } else {
                s += num;
            }
        }
        return cnt <= m;
    }
};
```

### **Go**

```go
func splitArray(nums []int, m int) int {
	mx := -1
	for _, num := range nums {
		mx = max(mx, num)
	}
	left, right := mx, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		if check(nums, m, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(nums []int, m, x int) bool {
	s, cnt := 0, 1
	for _, num := range nums {
		if s+num > x {
			cnt++
			s = num
		} else {
			s += num
		}
	}
	return cnt <= m
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
