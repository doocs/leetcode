# [1389. 按既定顺序创建目标数组](https://leetcode.cn/problems/create-target-array-in-the-given-order)

[English Version](/solution/1300-1399/1389.Create%20Target%20Array%20in%20the%20Given%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>nums</code> 和 <code>index</code>。你需要按照以下规则创建目标数组：</p>

<ul>
	<li>目标数组 <code>target</code> 最初为空。</li>
	<li>按从左到右的顺序依次读取 <code>nums[i]</code> 和 <code>index[i]</code>，在 <code>target</code> 数组中的下标 <code>index[i]</code> 处插入值 <code>nums[i]</code> 。</li>
	<li>重复上一步，直到在 <code>nums</code> 和 <code>index</code> 中都没有要读取的元素。</li>
</ul>

<p>请你返回目标数组。</p>

<p>题目保证数字插入位置总是存在。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,1,2,3,4], index = [0,1,2,2,1]
<strong>输出：</strong>[0,4,1,3,2]
<strong>解释：</strong>
nums       index     target
0            0        [0]
1            1        [0,1]
2            2        [0,1,2]
3            2        [0,1,3,2]
4            1        [0,4,1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,0], index = [0,1,2,3,0]
<strong>输出：</strong>[0,1,2,3,4]
<strong>解释：</strong>
nums       index     target
1            0        [1]
2            1        [1,2]
3            2        [1,2,3]
4            3        [1,2,3,4]
0            0        [0,1,2,3,4]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1], index = [0]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, index.length &lt;= 100</code></li>
	<li><code>nums.length == index.length</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>0 &lt;= index[i] &lt;= i</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def createTargetArray(self, nums: List[int], index: List[int]) -> List[int]:
        target = []
        for i in range(len(nums)):
            target.insert(index[i], nums[i])
        return target
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            target.add(index[i], nums[i]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = target.get(i);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function createTargetArray(nums: number[], index: number[]): number[] {
    let ans = [];
    for (let i = 0; i < nums.length; i++) {
        ans.splice(index[i], 0, nums[i]);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> createTargetArray(vector<int>& nums, vector<int>& index) {
        vector<int> target;
        for (int i = 0; i < nums.size(); ++i) {
            target.insert(target.begin() + index[i], nums[i]);
        }
        return target;
    }
};
```

### **Go**

```go
func createTargetArray(nums []int, index []int) []int {
	target := make([]int, len(nums))
	for i, v := range nums {
		copy(target[index[i]+1:], target[index[i]:])
		target[index[i]] = v
	}
	return target
}
```

### **...**

```

```

<!-- tabs:end -->
