# [1887. 使数组元素相等的减少操作次数](https://leetcode.cn/problems/reduction-operations-to-make-the-array-elements-equal)

[English Version](/solution/1800-1899/1887.Reduction%20Operations%20to%20Make%20the%20Array%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，你的目标是令 <code>nums</code> 中的所有元素相等。完成一次减少操作需要遵照下面的几个步骤：</p>

<ol>
	<li>找出 <code>nums</code> 中的 <strong>最大</strong> 值。记这个值为 <code>largest</code> 并取其下标 <code>i</code> （<strong>下标从 0 开始计数</strong>）。如果有多个元素都是最大值，则取最小的 <code>i</code> 。</li>
	<li>找出 <code>nums</code> 中的 <strong>下一个最大</strong> 值，这个值 <strong>严格小于</strong> <code>largest</code> ，记为 <code>nextLargest</code> 。</li>
	<li>将 <code>nums[i]</code> 减少到 <code>nextLargest</code> 。</li>
</ol>

<p>返回使<em> </em><code>nums</code><em> </em>中的所有元素相等的操作次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,1,3]
<strong>输出：</strong>3
<strong>解释：</strong>需要 3 次操作使 nums 中的所有元素相等：
1. largest = 5 下标为 0 。nextLargest = 3 。将 nums[0] 减少到 3 。nums = [<strong>3</strong>,1,3] 。
2. largest = 3 下标为 0 。nextLargest = 1 。将 nums[0] 减少到 1 。nums = [<strong>1</strong>,1,3] 。
3. largest = 3 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [<strong>1</strong>,1,<strong>1</strong>] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>0
<strong>解释：</strong>nums 中的所有元素已经是相等的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2,2,3]
<strong>输出：</strong>4
<strong>解释：</strong>需要 4 次操作使 nums 中的所有元素相等：
1. largest = 3 下标为 4 。nextLargest = 2 。将 nums[4] 减少到 2 。nums = [1,1,2,2,<strong>2</strong>] 。
2. largest = 2 下标为 2 。nextLargest = 1 。将 nums[2] 减少到 1 。nums = [1,1,<strong>1</strong>,2,2] 。 
3. largest = 2 下标为 3 。nextLargest = 1 。将 nums[3] 减少到 1 。nums = [1,1,1,<strong>1</strong>,2] 。 
4. largest = 2 下标为 4 。nextLargest = 1 。将 nums[4] 减少到 1 。nums = [1,1,1,1,<strong>1</strong>] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= nums[i] <= 5 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

对 $nums$ 进行排序，用 $cnt$ 表示元素所需的操作次数，初始时 $cnt=0$。

遍历 $nums[1..n-1]$，如果当前元素 $nums[i]$ 不等于 $nums[i-1]$，则将 $cnt$ 加一。累加当前 $cnt$ 到答案 $ans$。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        nums.sort()
        ans = cnt = 0
        for i, v in enumerate(nums[1:]):
            if v != nums[i]:
                cnt += 1
            ans += cnt
        return ans
```

```python
class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        ans = cnt = 0
        for _, v in sorted(Counter(nums).items()):
            ans += cnt * v
            cnt += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int reductionOperations(int[] nums) {
        Map<Integer, Integer> tm = new TreeMap<>();
        for (int v : nums) {
            tm.put(v, tm.getOrDefault(v, 0) + 1);
        }
        int ans = 0, cnt = 0;
        for (int v : tm.values()) {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function reductionOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    let cnt = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] != nums[i - 1]) {
            ++cnt;
        }
        ans += cnt;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.size(); ++i) {
            cnt += nums[i] != nums[i - 1];
            ans += cnt;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        map<int, int> m;
        for (int v : nums) ++m[v];
        int ans = 0, cnt = 0;
        for (auto [_, v] : m)
        {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
};
```

### **Go**

```go
func reductionOperations(nums []int) int {
	sort.Ints(nums)
	ans, cnt := 0, 0
	for i, v := range nums[1:] {
		if v != nums[i] {
			cnt++
		}
		ans += cnt
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
