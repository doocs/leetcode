# [2150. 找出数组中的所有孤独数字](https://leetcode.cn/problems/find-all-lonely-numbers-in-the-array)

[English Version](/solution/2100-2199/2150.Find%20All%20Lonely%20Numbers%20in%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。如果数字 <code>x</code> 在数组中仅出现 <strong>一次</strong> ，且没有 <strong>相邻</strong> 数字（即，<code>x + 1</code> 和 <code>x - 1</code>）出现在数组中，则认为数字 <code>x</code> 是 <strong>孤独数字</strong> 。</p>

<p>返回<em> </em><code>nums</code> 中的 <strong>所有</strong> 孤独数字。你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [10,6,5,8]
<strong>输出：</strong>[10,8]
<strong>解释：</strong>
- 10 是一个孤独数字，因为它只出现一次，并且 9 和 11 没有在 nums 中出现。
- 8 是一个孤独数字，因为它只出现一次，并且 7 和 9 没有在 nums 中出现。
- 5 不是一个孤独数字，因为 6 出现在 nums 中，反之亦然。
因此，nums 中的孤独数字是 [10, 8] 。
注意，也可以返回 [8, 10] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,5,3]
<strong>输出：</strong>[1,5]
<strong>解释：</strong>
- 1 是一个孤独数字，因为它只出现一次，并且 0 和 2 没有在 nums 中出现。
- 5 是一个孤独数字，因为它只出现一次，并且 4 和 6 没有在 nums 中出现。
- 3 不是一个孤独数字，因为它出现两次。
因此，nums 中的孤独数字是 [1, 5] 。
注意，也可以返回 [5, 1] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLonely(self, nums: List[int]) -> List[int]:
        counter = Counter(nums)
        ans = []
        for num, cnt in counter.items():
            if cnt == 1 and counter[num - 1] == 0 and counter[num + 1] == 0:
                ans.append(num)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        counter.forEach((k, v) -> {
            if (v == 1 && !counter.containsKey(k - 1) && !counter.containsKey(k + 1)) {
                ans.add(k);
            }
        });
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findLonely(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) ++counter[num];
        vector<int> ans;
        for (auto& e : counter) {
            int k = e.first, v = e.second;
            if (v == 1 && !counter.count(k - 1) && !counter.count(k + 1)) ans.push_back(k);
        }
        return ans;
    }
};
```

### **Go**

```go
func findLonely(nums []int) []int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	var ans []int
	for k, v := range counter {
		if v == 1 && counter[k-1] == 0 && counter[k+1] == 0 {
			ans = append(ans, k)
		}
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function findLonely(nums: number[]): number[] {
    let hashMap: Map<number, number> = new Map();
    for (let num of nums) {
        hashMap.set(num, (hashMap.get(num) || 0) + 1);
    }
    let ans: Array<number> = [];
    for (let [num, count] of hashMap.entries()) {
        if (count == 1 && !hashMap.get(num - 1) && !hashMap.get(num + 1)) {
            ans.push(num);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
