# [454. 四数相加 II](https://leetcode-cn.com/problems/4sum-ii)

[English Version](/solution/0400-0499/0454.4Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定四个包含整数的数组列表&nbsp;A , B , C , D ,计算有多少个元组 <code>(i, j, k, l)</code>&nbsp;，使得&nbsp;<code>A[i] + B[j] + C[k] + D[l] = 0</code>。</p>

<p>为了使问题简单化，所有的 A, B, C, D 具有相同的长度&nbsp;N，且 0 &le; N &le; 500 。所有整数的范围在 -2<sup>28</sup> 到 2<sup>28</sup> - 1 之间，最终结果不会超过&nbsp;2<sup>31</sup> - 1 。</p>

<p><strong>例如:</strong></p>

<pre>
<strong>输入:</strong>
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

<strong>输出:</strong>
2

<strong>解释:</strong>
两个元组如下:
1. (0, 0, 0, 1) -&gt; A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -&gt; A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fourSumCount(self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]) -> int:
        counter = Counter()
        for a in nums1:
            for b in nums2:
                counter[a + b] += 1
        ans = 0
        for c in nums3:
            for d in nums4:
                ans += counter[-(c + d)]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                counter.put(a + b, counter.getOrDefault(a + b, 0) + 1);
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += counter.getOrDefault(-(c + d), 0);
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
    int fourSumCount(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3, vector<int>& nums4) {
        unordered_map<int, int> counter;
        for (int a : nums1)
            for (int b : nums2)
                ++counter[a + b];
        int ans = 0;
        for (int c : nums3)
            for (int d : nums4)
                ans += counter[-(c + d)];
        return ans;
    }
};
```

### **Go**

```go
func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) int {
	counter := make(map[int]int)
	for _, a := range nums1 {
		for _, b := range nums2 {
			counter[a+b]++
		}
	}
	ans := 0
	for _, c := range nums3 {
		for _, d := range nums4 {
			ans += counter[-(c + d)]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
