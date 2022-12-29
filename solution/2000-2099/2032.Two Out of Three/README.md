# [2032. 至少在两个数组中出现的值](https://leetcode.cn/problems/two-out-of-three)

[English Version](/solution/2000-2099/2032.Two%20Out%20of%20Three/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你三个整数数组 <code>nums1</code>、<code>nums2</code> 和 <code>nums3</code> ，请你构造并返回一个 <strong>元素各不相同的</strong> 数组，且由 <strong>至少</strong> 在 <strong>两个</strong> 数组中出现的所有值组成<em>。</em>数组中的元素可以按 <strong>任意</strong> 顺序排列。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
<strong>输出：</strong>[3,2]
<strong>解释：</strong>至少在两个数组中出现的所有值为：
- 3 ，在全部三个数组中都出现过。
- 2 ，在数组 nums1 和 nums2 中出现过。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
<strong>输出：</strong>[2,3,1]
<strong>解释：</strong>至少在两个数组中出现的所有值为：
- 2 ，在数组 nums2 和 nums3 中出现过。
- 3 ，在数组 nums1 和 nums2 中出现过。
- 1 ，在数组 nums1 和 nums3 中出现过。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
<strong>输出：</strong>[]
<strong>解释：</strong>不存在至少在两个数组中出现的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length, nums3.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j], nums3[k] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组 + 枚举**

我们可以先将每个数组中的元素放入数组中，然后枚举 $1$ 到 $100$ 中的每个数 $i$，判断 $i$ 是否在至少两个数组中出现过。若是，则将 $i$ 加入答案数组中。

时间复杂度 $O(n_1 + n_2 + n_3)$，空间复杂度 $O(n_1 + n_2 + n_3)$。其中 $n_1, n_2, n_3$ 分别为数组 `nums1`、`nums2` 和 `nums3` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoOutOfThree(self, nums1: List[int], nums2: List[int], nums3: List[int]) -> List[int]:
        s1, s2, s3 = set(nums1), set(nums2), set(nums3)
        return [i for i in range(1, 101) if (i in s1) + (i in s2) + (i in s3) > 1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            if (s1[i] + s2[i] + s3[i] > 1) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int[] get(int[] nums) {
        int[] s = new int[101];
        for (int num : nums) {
            s[num] = 1;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto get = [](vector<int>& nums) {
            vector<int> cnt(101);
            for (int& v :nums) cnt[v] = 1;
            return cnt;
        };
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i) {
            if (s1[i] + s2[i] + s3[i] > 1) {
                ans.emplace_back(i);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) (ans []int) {
	get := func(nums []int) (s [101]int) {
		for _, v := range nums {
			s[v] = 1
		}
		return
	}
	s1, s2, s3 := get(nums1), get(nums2), get(nums3)
	for i := 1; i <= 100; i++ {
		if s1[i]+s2[i]+s3[i] > 1 {
			ans = append(ans, i)
		}
	}
	return
}
```

### **TypeScript**

```ts
function twoOutOfThree(
    nums1: number[],
    nums2: number[],
    nums3: number[],
): number[] {
    const count = new Array(101).fill(0);
    new Set(nums1).forEach(v => count[v]++);
    new Set(nums2).forEach(v => count[v]++);
    new Set(nums3).forEach(v => count[v]++);
    const ans = [];
    count.forEach((v, i) => {
        if (v >= 2) {
            ans.push(i);
        }
    });
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let mut count = vec![0; 101];
        nums1
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        nums2
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        nums3
            .into_iter()
            .collect::<HashSet<i32>>()
            .iter()
            .for_each(|&v| count[v as usize] += 1);
        let mut ans = Vec::new();
        count.iter().enumerate().for_each(|(i, v)| {
            if *v >= 2 {
                ans.push(i as i32);
            }
        });
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
