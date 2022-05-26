# [1636. 按照频率将数组升序排序](https://leetcode.cn/problems/sort-array-by-increasing-frequency)

[English Version](/solution/1600-1699/1636.Sort%20Array%20by%20Increasing%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，请你将数组按照每个值的频率 <strong>升序</strong> 排序。如果有多个值的频率相同，请你按照数值本身将它们 <strong>降序</strong> 排序。 </p>

<p>请你返回排序后的数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,1,2,2,2,3]
<b>输出：</b>[3,1,1,2,2,2]
<b>解释：</b>'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,3,1,3,2]
<b>输出：</b>[1,3,3,2,2]
<b>解释：</b>'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [-1,1,-6,4,5,-6,1,4,1]
<b>输出：</b>[5,-1,4,4,-6,-6,1,1,1]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frequencySort(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        cnt = sorted(cnt.items(), key=lambda x: (x[1], -x[0]))
        ans = []
        for v, freq in cnt:
            ans.extend([v] * freq)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        for (int v : nums) {
            ++cnt[v + 100];
        }
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                t.add(new int[]{cnt[i], i});
            }
        }
        t.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] ans = new int[nums.length];
        int i = 0;
        for (int[] e : t) {
            for (int j = 0; j < e[0]; ++j) {
                ans[i++] = e[1] - 100;
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
    vector<int> frequencySort(vector<int>& nums) {
        vector<int> cnt(201);
        for (int& v : nums) ++cnt[v + 100];
        vector<vector<int>> t;
        for (int i = 0; i < cnt.size(); ++i)
            if (cnt[i])
                t.push_back({cnt[i], -i});
        sort(t.begin(), t.end());
        vector<int> ans;
        for (auto& e : t)
            for (int j = 0; j < e[0]; ++j)
                ans.push_back(-e[1] - 100);
        return ans;
    }
};
```

### **Go**

```go
func frequencySort(nums []int) []int {
	cnt := make([]int, 201)
	for _, v := range nums {
		cnt[v+100]++
	}
	var t [][]int
	for i, v := range cnt {
		if v > 0 {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool {
		if t[i][0] == t[j][0] {
			return t[i][1] > t[j][1]
		}
		return t[i][0] < t[j][0]
	})
	var ans []int
	for _, e := range t {
		for i := 0; i < e[0]; i++ {
			ans = append(ans, e[1]-100)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
