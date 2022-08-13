# [1968. 构造元素不等于两相邻元素平均值的数组](https://leetcode.cn/problems/array-with-elements-not-equal-to-average-of-neighbors)

[English Version](/solution/1900-1999/1968.Array%20With%20Elements%20Not%20Equal%20to%20Average%20of%20Neighbors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>下标从 0 开始</strong> 的数组 <code>nums</code> ，数组由若干 <strong>互不相同的</strong> 整数组成。你打算重新排列数组中的元素以满足：重排后，数组中的每个元素都 <strong>不等于</strong> 其两侧相邻元素的 <strong>平均值</strong> 。</p>

<p>更公式化的说法是，重新排列的数组应当满足这一属性：对于范围&nbsp;<code>1 &lt;= i &lt; nums.length - 1</code> 中的每个 <code>i</code> ，<code>(nums[i-1] + nums[i+1]) / 2</code> <strong>不等于</strong> <code>nums[i]</code> 均成立 。</p>

<p>返回满足题意的任一重排结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>[1,2,4,5,3]
<strong>解释：</strong>
i=1, nums[i] = 2, 两相邻元素平均值为 (1+4) / 2 = 2.5
i=2, nums[i] = 4, 两相邻元素平均值为 (2+5) / 2 = 3.5
i=3, nums[i] = 5, 两相邻元素平均值为 (4+3) / 2 = 3.5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [6,2,0,9,7]
<strong>输出：</strong>[9,7,6,2,0]
<strong>解释：</strong>
i=1, nums[i] = 7, 两相邻元素平均值为 (9+6) / 2 = 7.5
i=2, nums[i] = 6, 两相邻元素平均值为 (7+2) / 2 = 4.5
i=3, nums[i] = 2, 两相邻元素平均值为 (6+0) / 2 = 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

**方法二：随机打乱**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        m = (n + 1) >> 1
        ans = []
        for i in range(m):
            ans.append(nums[i])
            if i + m < n:
                ans.append(nums[i + m])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = (n + 1) >> 1;
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i += 2, j++) {
            ans[i] = nums[j];
            if (j + m < n) {
                ans[i + 1] = nums[j + m];
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
    vector<int> rearrangeArray(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<int> ans;
        int n = nums.size();
        int m = (n + 1) >> 1;
        for (int i = 0; i < m; ++i) {
            ans.push_back(nums[i]);
            if (i + m < n) ans.push_back(nums[i + m]);
        }
        return ans;
    }
};
```

### **Go**

```go
func rearrangeArray(nums []int) []int {
	sort.Ints(nums)
	n := len(nums)
	m := (n + 1) >> 1
	var ans []int
	for i := 0; i < m; i++ {
		ans = append(ans, nums[i])
		if i+m < n {
			ans = append(ans, nums[i+m])
		}
	}
	return ans
}
```

```go
func rearrangeArray(nums []int) []int {
	rand.Seed(time.Now().UnixNano())
outer:
	for {
		rand.Shuffle(len(nums), func(i, j int) { nums[i], nums[j] = nums[j], nums[i] })
		for i := 1; i < len(nums)-1; i++ {
			if nums[i]*2 == nums[i-1]+nums[i+1] {
				continue outer
			}
		}
		return nums
	}
}
```

### **...**

```

```

<!-- tabs:end -->
