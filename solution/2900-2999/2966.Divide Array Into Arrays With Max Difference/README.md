---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README.md
rating: 1395
source: 第 376 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2966. 划分数组并满足最大差限制](https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference)

[English Version](/solution/2900-2999/2966.Divide%20Array%20Into%20Arrays%20With%20Max%20Difference/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，以及一个正整数 <code>k</code> 。</p>

<p>将这个数组划分为&nbsp;<code>n / 3</code>&nbsp;个长度为 <code>3</code> 的子数组，并满足以下条件：</p>

<ul>
	<li>子数组中<strong> 任意 </strong>两个元素的差必须 <strong>小于或等于</strong> <code>k</code> 。</li>
</ul>

<p>返回一个<em> </em><strong>二维数组 </strong>，包含所有的子数组。如果不可能满足条件，就返回一个空数组。如果有多个答案，返回 <strong>任意一个</strong> 即可。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,4,8,7,9,3,5,1], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>[[1,1,3],[3,4,5],[7,8,9]]</span></p>

<p><strong>解释：</strong></p>

<p>每个数组中任何两个元素之间的差小于或等于 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [2,4,2,2,5,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>将&nbsp;<code>nums</code>&nbsp;划分为 2 个长度为 3 的数组的不同方式有：</p>

<ul>
	<li>[[2,2,2],[2,4,5]] （及其排列）</li>
	<li>[[2,2,4],[2,2,5]] （及其排列）</li>
</ul>

<p>因为有四个 2，所以无论我们如何划分，都会有一个包含元素 2 和 5 的数组。因为&nbsp;<code>5 - 2 = 3 &gt; k</code>，条件无法被满足，所以没有合法的划分。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">[[2,2,12],[4,8,5],[5,9,7],[7,8,5],[5,9,10],[11,12,2]]</span></p>

<p><strong>解释：</strong></p>

<p>每个数组中任何两个元素之间的差小于或等于 14。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> 是 <code>3</code> 的倍数</li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们先对数组进行排序，然后每次取出三个元素，如果这三个元素的最大值和最小值的差大于 $k$，则无法满足条件，返回空数组。否则，我们将这三个元素组成的数组加入答案数组中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divideArray(self, nums: List[int], k: int) -> List[List[int]]:
        nums.sort()
        ans = []
        n = len(nums)
        for i in range(0, n, 3):
            t = nums[i : i + 3]
            if t[2] - t[0] > k:
                return []
            ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][];
        for (int i = 0; i < n; i += 3) {
            int[] t = Arrays.copyOfRange(nums, i, i + 3);
            if (t[2] - t[0] > k) {
                return new int[][] {};
            }
            ans[i / 3] = t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> divideArray(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ans;
        int n = nums.size();
        for (int i = 0; i < n; i += 3) {
            vector<int> t = {nums[i], nums[i + 1], nums[i + 2]};
            if (t[2] - t[0] > k) {
                return {};
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

#### Go

```go
func divideArray(nums []int, k int) [][]int {
	sort.Ints(nums)
	ans := [][]int{}
	for i := 0; i < len(nums); i += 3 {
		t := slices.Clone(nums[i : i+3])
		if t[2]-t[0] > k {
			return [][]int{}
		}
		ans = append(ans, t)
	}
	return ans
}
```

#### TypeScript

```ts
function divideArray(nums: number[], k: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    for (let i = 0; i < nums.length; i += 3) {
        const t = nums.slice(i, i + 3);
        if (t[2] - t[0] > k) {
            return [];
        }
        ans.push(t);
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func divideArray(_ nums: [Int], _ k: Int) -> [[Int]] {
        var sortedNums = nums.sorted()
        var ans: [[Int]] = []

        for i in stride(from: 0, to: sortedNums.count, by: 3) {
            if i + 2 >= sortedNums.count {
                return []
            }

            let t = Array(sortedNums[i..<i+3])
            if t[2] - t[0] > k {
                return []
            }

            ans.append(t)
        }

        return ans
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn divide_array(mut nums: Vec<i32>, k: i32) -> Vec<Vec<i32>> {
        nums.sort();
        let mut ans = Vec::new();
        let n = nums.len();

        for i in (0..n).step_by(3) {
            if i + 2 >= n {
                return vec![];
            }

            let t = &nums[i..i+3];
            if t[2] - t[0] > k {
                return vec![];
            }

            ans.push(t.to_vec());
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] DivideArray(int[] nums, int k) {
        Array.Sort(nums);
        List<int[]> ans = new List<int[]>();

        for (int i = 0; i < nums.Length; i += 3) {
            if (i + 2 >= nums.Length) {
                return new int[0][];
            }

            int[] t = new int[] { nums[i], nums[i + 1], nums[i + 2] };
            if (t[2] - t[0] > k) {
                return new int[0][];
            }

            ans.Add(t);
        }

        return ans.ToArray();
    }
}
```

#### Dart

```dart
class Solution {
  List<List<int>> divideArray(List<int> nums, int k) {
    nums.sort();
    List<List<int>> ans = [];

    for (int i = 0; i < nums.length; i += 3) {
      if (i + 2 >= nums.length) {
        return [];
      }

      List<int> t = nums.sublist(i, i + 3);
      if (t[2] - t[0] > k) {
        return [];
      }

      ans.add(t);
    }

    return ans;
  }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
