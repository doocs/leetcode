---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README.md
rating: 1348
source: 第 444 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 链表
    - 双向链表
    - 有序集合
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [3507. 移除最小数对使数组有序 I](https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-i)

[English Version](/solution/3500-3599/3507.Minimum%20Pair%20Removal%20to%20Sort%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>nums</code>，你可以执行以下操作任意次数：</p>

<ul>
	<li>选择 <strong>相邻&nbsp;</strong>元素对中 <strong>和最小</strong> 的一对。如果存在多个这样的对，选择最左边的一个。</li>
	<li>用它们的和替换这对元素。</li>
</ul>

<p>返回将数组变为&nbsp;<strong>非递减&nbsp;</strong>所需的&nbsp;<strong>最小操作次数&nbsp;</strong>。</p>

<p>如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为<strong>非递减</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,2,3,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>元素对 <code>(3,1)</code> 的和最小，为 4。替换后&nbsp;<code>nums = [5,2,4]</code>。</li>
	<li>元素对 <code>(2,4)</code> 的和为 6。替换后&nbsp;<code>nums = [5,6]</code>。</li>
</ul>

<p>数组 <code>nums</code> 在两次操作后变为非递减。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组 <code>nums</code> 已经是非递减的。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>-1000&nbsp;&lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个函数 $\text{is\_non\_decreasing}(a)$，用于判断数组 $a$ 是否为非递减数组。

我们使用一个循环，直到数组 $arr$ 为非递减数组为止。在每次循环中，我们找到数组 $arr$ 中相邻元素对的和的最小值，并记录该对的左边元素的下标 $k$。然后，我们将该对的和替换左边的元素，并删除右边的元素。最后，我们返回操作的次数。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        arr = nums[:]
        ans = 0

        def is_non_decreasing(a: List[int]) -> bool:
            for i in range(1, len(a)):
                if a[i] < a[i - 1]:
                    return False
            return True

        while not is_non_decreasing(arr):
            k = 0
            s = arr[0] + arr[1]
            for i in range(1, len(arr) - 1):
                t = arr[i] + arr[i + 1]
                if s > t:
                    s = t
                    k = i
            arr[k] = s
            arr.pop(k + 1)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (int x : nums) {
            arr.add(x);
        }
        int ans = 0;
        while (!isNonDecreasing(arr)) {
            int k = 0;
            int s = arr.get(0) + arr.get(1);
            for (int i = 1; i < arr.size() - 1; ++i) {
                int t = arr.get(i) + arr.get(i + 1);
                if (s > t) {
                    s = t;
                    k = i;
                }
            }
            arr.set(k, s);
            arr.remove(k + 1);
            ++ans;
        }
        return ans;
    }

    private boolean isNonDecreasing(List<Integer> arr) {
        for (int i = 1; i < arr.size(); ++i) {
            if (arr.get(i) < arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        vector<int> arr = nums;
        int ans = 0;

        while (!isNonDecreasing(arr)) {
            int k = 0;
            int s = arr[0] + arr[1];

            for (int i = 1; i < arr.size() - 1; ++i) {
                int t = arr[i] + arr[i + 1];
                if (s > t) {
                    s = t;
                    k = i;
                }
            }

            arr[k] = s;
            arr.erase(arr.begin() + (k + 1));
            ++ans;
        }

        return ans;
    }

private:
    bool isNonDecreasing(const vector<int>& arr) {
        for (int i = 1; i < (int) arr.size(); ++i) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func minimumPairRemoval(nums []int) int {
	arr := append([]int(nil), nums...)
	ans := 0

	isNonDecreasing := func(a []int) bool {
		for i := 1; i < len(a); i++ {
			if a[i] < a[i-1] {
				return false
			}
		}
		return true
	}

	for !isNonDecreasing(arr) {
		k := 0
		s := arr[0] + arr[1]

		for i := 1; i < len(arr)-1; i++ {
			t := arr[i] + arr[i+1]
			if s > t {
				s = t
				k = i
			}
		}

		arr[k] = s
		copy(arr[k+1:], arr[k+2:])
		arr = arr[:len(arr)-1]
		ans++
	}

	return ans
}
```

#### TypeScript

```ts
function minimumPairRemoval(nums: number[]): number {
    const arr = nums.slice();
    let ans = 0;
    const isNonDecreasing = (a: number[]): boolean => {
        for (let i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    };
    while (!isNonDecreasing(arr)) {
        let k = 0;
        let s = arr[0] + arr[1];
        for (let i = 1; i < arr.length - 1; ++i) {
            const t = arr[i] + arr[i + 1];
            if (s > t) {
                s = t;
                k = i;
            }
        }
        arr[k] = s;
        arr.splice(k + 1, 1);
        ans++;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_pair_removal(nums: Vec<i32>) -> i32 {
        let mut arr: Vec<i32> = nums.clone();
        let mut ans: i32 = 0;

        fn is_non_decreasing(a: &Vec<i32>) -> bool {
            for i in 1..a.len() {
                if a[i] < a[i - 1] {
                    return false;
                }
            }
            true
        }

        while !is_non_decreasing(&arr) {
            let mut k: usize = 0;
            let mut s: i32 = arr[0] + arr[1];
            for i in 1..arr.len() - 1 {
                let t: i32 = arr[i] + arr[i + 1];
                if s > t {
                    s = t;
                    k = i;
                }
            }
            arr[k] = s;
            arr.remove(k + 1);
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
