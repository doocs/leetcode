# [2248. 多个数组求交集](https://leetcode.cn/problems/intersection-of-multiple-arrays)

[English Version](/solution/2200-2299/2248.Intersection%20of%20Multiple%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>nums</code> ，其中 <code>nums[i]</code> 是由 <strong>不同</strong> 正整数组成的一个非空数组，按 <strong>升序排列</strong> 返回一个数组，数组中的每个元素在 <code>nums</code>&nbsp;<strong>所有数组</strong> 中都出现过。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[<em><strong>3</strong></em>,1,2,<em><strong>4</strong></em>,5],[1,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>],[<em><strong>3</strong></em>,<em><strong>4</strong></em>,5,6]]
<strong>输出：</strong>[3,4]
<strong>解释：</strong>
nums[0] = [<em><strong>3</strong></em>,1,2,<em><strong>4</strong></em>,5]，nums[1] = [1,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>]，nums[2] = [<em><strong>3</strong></em>,<em><strong>4</strong></em>,5,6]，在 nums 中每个数组中都出现的数字是 3 和 4 ，所以返回 [3,4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[4,5,6]]
<strong>输出：</strong>[]
<strong>解释：</strong>
不存在同时出现在 nums[0] 和 nums[1] 的整数，所以返回一个空列表 [] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(nums[i].length) &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i][j] &lt;= 1000</code></li>
	<li><code>nums[i]</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        cnt = [0] * 1001
        for num in nums:
            for v in num:
                cnt[v] += 1
        n = len(nums)
        return [i for i, v in enumerate(cnt) if v == n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] cnt = new int[1001];
        for (int[] num : nums) {
            for (int i : num) {
                cnt[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            if (cnt[i] == nums.length) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function intersection(nums: number[][]): number[] {
    const n = nums.length;
    let ans = nums[0];
    for (let i = 1; i < n && ans.length; i++) {
        const cur = new Set(nums[i]);
        // get intersect
        ans = ans.filter(v => cur.has(v));
    }
    return ans.sort((a, b) => a - b);
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        vector<int> cnt(1001);
        for (auto& num : nums)
            for (int v : num)
                ++cnt[v];
        int n = nums.size();
        vector<int> ans;
        for (int i = 1; i < 1001; ++i)
            if (cnt[i] == n)
                ans.push_back(i);
        return ans;
    }
};
```

### **Go**

```go
func intersection(nums [][]int) []int {
	cnt := make([]int, 1001)
	for _, num := range nums {
		for _, v := range num {
			cnt[v]++
		}
	}
	var ans []int
	for i, v := range cnt {
		if v == len(nums) {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
