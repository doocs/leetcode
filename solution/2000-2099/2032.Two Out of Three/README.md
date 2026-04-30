---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2032.Two%20Out%20of%20Three/README.md
rating: 1269
source: 第 262 场周赛 Q1
tags:
    - 位运算
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2032. 至少在两个数组中出现的值](https://leetcode.cn/problems/two-out-of-three)

[English Version](/solution/2000-2099/2032.Two%20Out%20of%20Three/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组 + 枚举

我们可以先将每个数组中的元素放入数组中，然后枚举 $1$ 到 $100$ 中的每个数 $i$，判断 $i$ 是否在至少两个数组中出现过。若是，则将 $i$ 加入答案数组中。

时间复杂度 $O(n_1 + n_2 + n_3)$，空间复杂度 $O(n_1 + n_2 + n_3)$。其中 $n_1, n_2, n_3$ 分别为数组 `nums1`、`nums2` 和 `nums3` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoOutOfThree(
        self, nums1: List[int], nums2: List[int], nums3: List[int]
    ) -> List[int]:
        s1, s2, s3 = set(nums1), set(nums2), set(nums3)
        return [i for i in range(1, 101) if (i in s1) + (i in s2) + (i in s3) > 1]
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto get = [](vector<int>& nums) {
            vector<int> cnt(101);
            for (int& v : nums) cnt[v] = 1;
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

#### Go

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

#### TypeScript

```ts
function twoOutOfThree(nums1: number[], nums2: number[], nums3: number[]): number[] {
    const get = (nums: number[]): number[] => {
        const s = new Array(101).fill(0);
        for (const v of nums) {
            s[v] = 1;
        }
        return s;
    };

    const s1 = get(nums1),
        s2 = get(nums2),
        s3 = get(nums3);
    const ans: number[] = [];

    for (let i = 1; i <= 100; i++) {
        if (s1[i] + s2[i] + s3[i] > 1) {
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let get = |nums: Vec<i32>| -> [i32; 101] {
            let mut s = [0; 101];
            for v in nums {
                s[v as usize] = 1;
            }
            s
        };

        let s1 = get(nums1);
        let s2 = get(nums2);
        let s3 = get(nums3);
        let mut ans = Vec::new();

        for i in 1..=100 {
            if s1[i] + s2[i] + s3[i] > 1 {
                ans.push(i as i32);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 位运算

我们可以用一个哈希表 $\textit{mask}$ 来记录每个数在哪些数组中出现过。对于每个数组，我们将其中的元素加入哈希表中，并将对应的位设置为 $1$。例如，如果是第一个数组，则将对应的位设置为 $1$；如果是第二个数组，则将对应的位设置为 $2$；如果是第三个数组，则将对应的位设置为 $4$。

最后，我们枚举哈希表中的每个数，判断其对应的值对应的二进制位中是否至少有两位为 $1$，如果是，则将该数加入答案数组中。

时间复杂度 $O(n_1 + n_2 + n_3)$，空间复杂度 $O(n_1 + n_2 + n_3)$。其中 $n_1, n_2, n_3$ 分别为数组 $\textit{nums1}$, $\textit{nums2}$ 和 $\textit{nums3}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def twoOutOfThree(
        self, nums1: List[int], nums2: List[int], nums3: List[int]
    ) -> List[int]:
        mask = defaultdict(int)
        for i, nums in enumerate((nums1, nums2, nums3)):
            for x in nums:
                mask[x] |= 1 << i
        return [x for x, v in mask.items() if v & (v - 1)]
```

#### Java

```java
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> mask = new HashMap<>();
        int[][] nums = {nums1, nums2, nums3};
        for (int i = 0; i < 3; i++) {
            for (int x : nums[i]) {
                mask.merge(x, 1 << i, (oldVal, newVal) -> oldVal | newVal);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (var e : mask.entrySet()) {
            if ((e.getValue() & (e.getValue() - 1)) != 0) {
                ans.add(e.getKey());
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        unordered_map<int, int> mask;
        vector<vector<int>*> all = {&nums1, &nums2, &nums3};

        for (int i = 0; i < 3; ++i) {
            for (int x : *all[i]) {
                mask[x] |= (1 << i);
            }
        }

        vector<int> ans;
        for (auto& [x, m] : mask) {
            if (m & (m - 1)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) (ans []int) {
	mask := make(map[int]int)
	for i, nums := range [][]int{nums1, nums2, nums3} {
		for _, x := range nums {
			mask[x] |= 1 << i
		}
	}
	for x, m := range mask {
		if m&(m-1) != 0 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function twoOutOfThree(nums1: number[], nums2: number[], nums3: number[]): number[] {
    const mask = new Map<number, number>();
    const all = [nums1, nums2, nums3];

    all.forEach((nums, i) => {
        for (const x of nums) {
            mask.set(x, (mask.get(x) || 0) | (1 << i));
        }
    });

    return Array.from(mask.entries())
        .filter(([_, m]) => (m & (m - 1)) !== 0)
        .map(([x, _]) => x);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn two_out_of_three(nums1: Vec<i32>, nums2: Vec<i32>, nums3: Vec<i32>) -> Vec<i32> {
        let mut mask = HashMap::new();
        let all = vec![nums1, nums2, nums3];

        for (i, nums) in all.into_iter().enumerate() {
            for x in nums {
                *mask.entry(x).or_insert(0) |= 1 << i;
            }
        }

        mask.into_iter()
            .filter(|&(_, m)| m & (m - 1) != 0)
            .map(|(x, _)| x)
            .collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
