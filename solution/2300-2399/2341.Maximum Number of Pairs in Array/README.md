# [2341. 数组能形成多少数对](https://leetcode.cn/problems/maximum-number-of-pairs-in-array)

[English Version](/solution/2300-2399/2341.Maximum%20Number%20of%20Pairs%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。在一步操作中，你可以执行以下步骤：</p>

<ul>
	<li>从 <code>nums</code> 选出 <strong>两个</strong> <strong>相等的</strong> 整数</li>
	<li>从 <code>nums</code> 中移除这两个整数，形成一个 <strong>数对</strong></li>
</ul>

<p>请你在 <code>nums</code> 上多次执行此操作直到无法继续执行。</p>

<p>返回一个下标从 <strong>0</strong> 开始、长度为 <code>2</code> 的整数数组 <code>answer</code> 作为答案，其中<em> </em><code>answer[0]</code><em> </em>是形成的数对数目，<code>answer[1]</code> 是对 <code>nums</code> 尽可能执行上述操作后剩下的整数数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,2,1,3,2,2]
<strong>输出：</strong>[3,1]
<strong>解释：</strong>
nums[0] 和 nums[3] 形成一个数对，并从 nums 中移除，nums = [3,2,3,2,2] 。
nums[0] 和 nums[2] 形成一个数对，并从 nums 中移除，nums = [2,2,2] 。
nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [2] 。
无法形成更多数对。总共形成 3 个数对，nums 中剩下 1 个数字。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>nums[0] 和 nums[1] 形成一个数对，并从 nums 中移除，nums = [] 。
无法形成更多数对。总共形成 1 个数对，nums 中剩下 0 个数字。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [0]
<strong>输出：</strong>[0,1]
<strong>解释：</strong>无法形成数对，nums 中剩下 1 个数字。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfPairs(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        s = sum(v // 2 for v in cnt.values())
        return [s, len(nums) - s * 2]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int v : nums) {
            ++cnt[v];
        }
        int s = 0;
        for (int v : cnt) {
            s += v / 2;
        }
        return new int[]{s, nums.length - s * 2};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> numberOfPairs(vector<int>& nums) {
        vector<int> cnt(101);
        for (int v : nums) ++cnt[v];
        int s = 0;
        for (int v : cnt) s += v / 2;
        vector<int> ans(2);
        ans[0] = s;
        ans[1] = nums.size() - s * 2;
        return ans;
    }
};
```

### **Go**

```go
func numberOfPairs(nums []int) []int {
    cnt := make([]int, 101)
    for _, v := range nums {
        cnt[v]++
    }
    s := 0
    for _, v := range cnt {
        s += v / 2
    }
    return []int{s, len(nums) - s * 2}
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
