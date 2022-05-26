# [2089. 找出数组排序后的目标下标](https://leetcode.cn/problems/find-target-indices-after-sorting-array)

[English Version](/solution/2000-2099/2089.Find%20Target%20Indices%20After%20Sorting%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 以及一个目标元素 <code>target</code> 。</p>

<p><strong>目标下标</strong> 是一个满足&nbsp;<code>nums[i] == target</code> 的下标 <code>i</code> 。</p>

<p>将 <code>nums</code> 按 <strong>非递减</strong> 顺序排序后，返回由 <code>nums</code> 中目标下标组成的列表。如果不存在目标下标，返回一个 <strong>空</strong> 列表。返回的列表必须按 <strong>递增</strong> 顺序排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 2
<strong>输出：</strong>[1,2]
<strong>解释：</strong>排序后，nums 变为 [1,<em><strong>2</strong></em>,<em><strong>2</strong></em>,3,5] 。
满足 nums[i] == 2 的下标是 1 和 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 3
<strong>输出：</strong>[3]
<strong>解释：</strong>排序后，nums 变为 [1,2,2,<em><strong>3</strong></em>,5] 。
满足 nums[i] == 3 的下标是 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 5
<strong>输出：</strong>[4]
<strong>解释：</strong>排序后，nums 变为 [1,2,2,3,<em><strong>5</strong></em>] 。
满足 nums[i] == 5 的下标是 4 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,5,2,3], target = 4
<strong>输出：</strong>[]
<strong>解释：</strong>nums 中不含值为 4 的元素。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i], target &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        nums.sort()
        return [i for i, num in enumerate(nums) if num == target]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function targetIndices(nums: number[], target: number): number[] {
    nums.sort((a, b) => a - b);
    let ans = [];
    for (let i = 0; i < nums.length && nums[i] <= target; i++) {
        let cur = nums[i];
        if (cur == target) {
            ans.push(i);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i)
            if (nums[i] == target)
                ans.push_back(i);
        return ans;
    }
};
```

### **Go**

```go
func targetIndices(nums []int, target int) []int {
	sort.Ints(nums)
	var ans []int
	for i, num := range nums {
		if num == target {
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
