# [1885. 统计数对](https://leetcode.cn/problems/count-pairs-in-two-arrays)

[English Version](/solution/1800-1899/1885.Count%20Pairs%20in%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度为 <code>n</code> 的整数数组 <code>nums1</code>&nbsp;和&nbsp;<code>nums2</code> ，找出所有满足 <code>i &lt; j</code> 且 <code>nums1[i] + nums1[j] &gt; nums2[i] + nums2[j]</code>&nbsp;的数对 <code>(i, j)</code> 。</p>

<p>返回满足条件数对的<strong> 个数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,1,2,1], nums2 = [1,2,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>满足条件的数对有 1 个：(0, 2) ，因为 nums1[0] + nums1[2] = 2 + 2 &gt; nums2[0] + nums2[2] = 1 + 1</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,10,6,2], nums2 = [1,4,1,5]
<strong>输出：</strong>5
<strong>解释：</strong>以下数对满足条件：
- (0, 1) 因为 nums1[0] + nums1[1] = 1 + 10 &gt; nums2[0] + nums2[1] = 1 + 4
- (0, 2) 因为 nums1[0] + nums1[2] = 1 + 6 &gt; nums2[0] + nums2[2] = 1 + 1
- (1, 2) 因为 nums1[1] + nums1[2] = 10 + 6 &gt; nums2[1] + nums2[2] = 4 + 1
- (1, 3) 因为 nums1[1] + nums1[3] = 10 + 2 &gt; nums2[1] + nums2[3] = 4 + 5
- (2, 3) 因为 nums1[2] + nums1[3] = 6 + 2 &gt; nums2[2] + nums2[3] = 1 + 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

`nums1[i] + nums1[j] > nums2[i] + nums2[j]` 可以转换为 `nums1[i] - nums2[i] > -(nums1[j] - nums2[j])`。

因此，对 nums1 和 nums2 求对应元素的差值，得到 d 数组，题目就是求 `d[i] > -d[j]` 的所有数对个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, nums1: List[int], nums2: List[int]) -> int:
        n = len(nums1)
        d = [nums1[i] - nums2[i] for i in range(n)]
        d.sort()
        return sum(n - bisect_right(d, -v, lo=i + 1) for i, v in enumerate(d))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(d);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int left = i + 1, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (d[mid] > -d[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += n - left;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> d(n);
        for (int i = 0; i < n; ++i) d[i] = nums1[i] - nums2[i];
        sort(d.begin(), d.end());
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = upper_bound(d.begin() + i + 1, d.end(), -d[i]) - d.begin();
            ans += n - j;
        }
        return ans;
    }
};
```

### **Go**

```go
func countPairs(nums1 []int, nums2 []int) int64 {
	n := len(nums1)
	d := make([]int, n)
	for i, v := range nums1 {
		d[i] = v - nums2[i]
	}
	sort.Ints(d)
	var ans int64
	for i, v := range d {
		left, right := i+1, n
		for left < right {
			mid := (left + right) >> 1
			if d[mid] > -v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans += int64(n - left)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
