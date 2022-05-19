# [462. 最少移动次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii)

[English Version](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。</p>

<p><strong>例如:</strong></p>

<pre>
<strong>输入:</strong>
[1,2,3]

<strong>输出:</strong>
2

<strong>说明：
</strong>只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）： 

[1,2,3]  =&gt;  [2,2,3]  =&gt;  [2,2,2]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 中位数**

**方法二：排序 + 前缀和**

以上两种方法的时间复杂度均为 O(nlogn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        k = nums[len(nums) >> 1]
        return sum(abs(v - k) for v in nums)
```

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        def move(i):
            v = nums[i]
            a = v * i - s[i]
            b = s[-1] - s[i + 1] - v * (n - i - 1)
            return a + b

        nums.sort()
        s = [0] + list(accumulate(nums))
        n = len(nums)
        return min(move(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int k = nums[nums.size() >> 1];
        int ans = 0;
        for (int& v : nums) ans += abs(v - k);
        return ans;
    }
};
```

### **Go**

```go
func minMoves2(nums []int) int {
	sort.Ints(nums)
	k := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v - k)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
