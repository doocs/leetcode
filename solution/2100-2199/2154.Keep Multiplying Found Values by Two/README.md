# [2154. 将找到的值乘以 2](https://leetcode.cn/problems/keep-multiplying-found-values-by-two)

[English Version](/solution/2100-2199/2154.Keep%20Multiplying%20Found%20Values%20by%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，另给你一个整数 <code>original</code> ，这是需要在 <code>nums</code> 中搜索的第一个数字。</p>

<p>接下来，你需要按下述步骤操作：</p>

<ol>
	<li>如果在 <code>nums</code> 中找到 <code>original</code> ，将 <code>original</code>&nbsp;<strong>乘以</strong> 2 ，得到新 <code>original</code>（即，令 <code>original = 2 * original</code>）。</li>
	<li>否则，停止这一过程。</li>
	<li>只要能在数组中找到新 <code>original</code> ，就对新 <code>original</code> 继续 <strong>重复</strong> 这一过程<strong>。</strong></li>
</ol>

<p>返回<em> </em><code>original</code> 的 <strong>最终</strong> 值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,3,6,1,12], original = 3
<strong>输出：</strong>24
<strong>解释：</strong> 
- 3 能在 nums 中找到。3 * 2 = 6 。
- 6 能在 nums 中找到。6 * 2 = 12 。
- 12 能在 nums 中找到。12 * 2 = 24 。
- 24 不能在 nums 中找到。因此，返回 24 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,7,9], original = 4
<strong>输出：</strong>4
<strong>解释：</strong>
- 4 不能在 nums 中找到。因此，返回 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], original &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findFinalValue(self, nums: List[int], original: int) -> int:
        s = set(nums)
        while original in s:
            original <<= 1
        return original
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int findFinalValue(int[] nums, int original) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        while (s.contains(original)) {
            original <<= 1;
        }
        return original;
    }
}
```

### **TypeScript**

```ts
function findFinalValue(nums: number[], original: number): number {
    let set: Set<number> = new Set(nums);
    while (set.has(original)) {
        original *= 2;
    }
    return original;
}
```

### **C++**

```cpp
class Solution {
public:
    int findFinalValue(vector<int>& nums, int original) {
        unordered_set<int> s;
        for (int num : nums) s.insert(num);
        while (s.count(original)) original <<= 1;
        return original;
    }
};
```

### **Go**

```go
func findFinalValue(nums []int, original int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
	}
	for s[original] {
		original <<= 1
	}
	return original
}
```

### **...**

```

```

<!-- tabs:end -->
