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

**方法一：数组或哈希表计数**

用数组或者哈希表统计 `nums` 中每个数字出现的次数，由于题目中数字的范围是 $[-100, 100]$，我们可以直接创建一个大小为 $201$ 的数组来统计。

然后对 `nums` 按照数字出现次数升序排序，如果出现次数相同，则按照数字降序排序。

时间复杂度为 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frequencySort(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        return sorted(nums, key=lambda x: (cnt[x], -x))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        List<Integer> t = new ArrayList<>();
        for (int v : nums) {
            v += 100;
            ++cnt[v];
            t.add(v);
        }
        t.sort((a, b) -> cnt[a] == cnt[b] ? b - a : cnt[a] - cnt[b]);
        int[] ans = new int[nums.length];
        int i = 0;
        for (int v : t) {
            ans[i++] = v - 100;
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
        for (int v : nums) {
            ++cnt[v + 100];
        }
        sort(nums.begin(), nums.end(), [&](const int a, const int b) {
            if (cnt[a + 100] == cnt[b + 100]) return a > b;
            return cnt[a + 100] < cnt[b + 100];
        });
        return nums;
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
	sort.Slice(nums, func(i, j int) bool {
		a, b := nums[i]+100, nums[j]+100
		return cnt[a] < cnt[b] || cnt[a] == cnt[b] && a > b
	})
	return nums
}
```

### **TypeScript**

```ts
function frequencySort(nums: number[]): number[] {
    const map = new Map<number, number>();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }
    return nums.sort((a, b) => map.get(a) - map.get(b) || b - a);
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn frequency_sort(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut map = HashMap::new();
        for &num in nums.iter() {
            *map.entry(num).or_insert(0) += 1;
        }
        nums.sort_by(|a, b| {
            if map.get(a) == map.get(b) {
                return b.cmp(a);
            }
            map.get(a).cmp(&map.get(b))
        });
        nums
    }
}
```

### **...**

```

```

<!-- tabs:end -->
