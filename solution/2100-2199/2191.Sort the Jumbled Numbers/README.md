---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2191.Sort%20the%20Jumbled%20Numbers/README.md
rating: 1496
source: 第 73 场双周赛 Q2
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [2191. 将杂乱无章的数字排序](https://leetcode.cn/problems/sort-the-jumbled-numbers)

[English Version](/solution/2100-2199/2191.Sort%20the%20Jumbled%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>mapping</code>&nbsp;，它表示一个十进制数的映射规则，<code>mapping[i] = j</code>&nbsp;表示这个规则下将数位&nbsp;<code>i</code>&nbsp;映射为数位 <code>j</code>&nbsp;。</p>

<p>一个整数 <strong>映射后的值</strong>&nbsp;为将原数字每一个数位 <code>i</code>&nbsp;（<code>0 &lt;= i &lt;= 9</code>）映射为&nbsp;<code>mapping[i]</code>&nbsp;。</p>

<p>另外给你一个整数数组&nbsp;<code>nums</code>&nbsp;，请你将数组<em>&nbsp;</em><code>nums</code>&nbsp;中每个数按照它们映射后对应数字非递减顺序排序后返回。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>如果两个数字映射后对应的数字大小相同，则将它们按照输入中的 <strong>相对顺序</strong>&nbsp;排序。</li>
	<li><code>nums</code>&nbsp;中的元素只有在排序的时候需要按照映射后的值进行比较，返回的值应该是输入的元素本身。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
<b>输出：</b>[338,38,991]
<b>解释：</b>
将数字 991 按照如下规则映射：
1. mapping[9] = 6 ，所有数位 9 都会变成 6 。
2. mapping[1] = 9 ，所有数位 1 都会变成 9 。
所以，991 映射的值为 669 。
338 映射为 007 ，去掉前导 0 后得到 7 。
38 映射为 07 ，去掉前导 0 后得到 7 。
由于 338 和 38 映射后的值相同，所以它们的前后顺序保留原数组中的相对位置关系，338 在 38 的前面。
所以，排序后的数组为 [338,38,991] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
<b>输出：</b>[123,456,789]
<b>解释：</b>789 映射为 789 ，456 映射为 456 ，123 映射为 123 。所以排序后数组为 [123,456,789] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>mapping.length == 10</code></li>
	<li><code>0 &lt;= mapping[i] &lt;= 9</code></li>
	<li><code>mapping[i]</code>&nbsp;的值 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

我们遍历数组 $nums$ 中的每个元素 $nums[i]$，将其映射后的值 $y$ 与下标 $i$ 一起存入数组 $arr$ 中，然后对数组 $arr$ 进行排序，最后将排序后的数组 $arr$ 中的下标 $i$ 取出，转换为原数组 $nums$ 中的元素 $nums[i]$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortJumbled(self, mapping: List[int], nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            if x == 0:
                return mapping[0]
            y, k = 0, 1
            while x:
                x, v = divmod(x, 10)
                v = mapping[v]
                y = k * v + y
                k *= 10
            return y

        arr = sorted((f(x), i) for i, x in enumerate(nums))
        return [nums[i] for _, i in arr]
```

#### Java

```java
class Solution {
    private int[] mapping;

    public int[] sortJumbled(int[] mapping, int[] nums) {
        this.mapping = mapping;
        int n = nums.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {f(nums[i]), i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i][1]];
        }
        return ans;
    }

    private int f(int x) {
        if (x == 0) {
            return mapping[0];
        }
        int y = 0;
        for (int k = 1; x > 0; x /= 10) {
            int v = mapping[x % 10];
            y = k * v + y;
            k *= 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortJumbled(vector<int>& mapping, vector<int>& nums) {
        auto f = [&](int x) {
            if (x == 0) {
                return mapping[0];
            }
            int y = 0;
            for (int k = 1; x; x /= 10) {
                int v = mapping[x % 10];
                y = k * v + y;
                k *= 10;
            }
            return y;
        };
        const int n = nums.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) {
            arr.emplace_back(f(nums[i]), i);
        }
        sort(arr.begin(), arr.end());
        vector<int> ans;
        for (const auto& [_, i] : arr) {
            ans.push_back(nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func sortJumbled(mapping []int, nums []int) (ans []int) {
	n := len(nums)
	f := func(x int) (y int) {
		if x == 0 {
			return mapping[0]
		}
		for k := 1; x > 0; x /= 10 {
			v := mapping[x%10]
			y = k*v + y
			k *= 10
		}
		return
	}
	arr := make([][2]int, n)
	for i, x := range nums {
		arr[i] = [2]int{f(x), i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i][0] < arr[j][0] || arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1] })
	for _, p := range arr {
		ans = append(ans, nums[p[1]])
	}
	return
}
```

#### TypeScript

```ts
function sortJumbled(mapping: number[], nums: number[]): number[] {
    const n = nums.length;
    const f = (x: number): number => {
        if (x === 0) {
            return mapping[0];
        }
        let y = 0;
        for (let k = 1; x; x = (x / 10) | 0) {
            const v = mapping[x % 10];
            y += v * k;
            k *= 10;
        }
        return y;
    };
    const arr: number[][] = nums.map((x, i) => [f(x), i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(x => nums[x[1]]);
}
```

#### JavaScript

```js
/**
 * @param {number[]} mapping
 * @param {number[]} nums
 * @return {number[]}
 */
var sortJumbled = function (mapping, nums) {
    const n = nums.length;
    const f = x => {
        if (x === 0) {
            return mapping[0];
        }
        let y = 0;
        for (let k = 1; x; x = (x / 10) | 0) {
            const v = mapping[x % 10];
            y += v * k;
            k *= 10;
        }
        return y;
    };
    const arr = nums.map((x, i) => [f(x), i]);
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(x => nums[x[1]]);
};
```

#### Rust

```rust
impl Solution {
    pub fn sort_jumbled(mapping: Vec<i32>, nums: Vec<i32>) -> Vec<i32> {
        let f = |x: i32| -> i32 {
            if x == 0 {
                return mapping[0];
            }
            let mut y = 0;
            let mut k = 1;
            let mut num = x;
            while num != 0 {
                let v = mapping[(num % 10) as usize];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            y
        };

        let n = nums.len();
        let mut arr: Vec<(i32, usize)> = Vec::with_capacity(n);
        for i in 0..n {
            arr.push((f(nums[i]), i));
        }
        arr.sort();

        let mut ans: Vec<i32> = Vec::with_capacity(n);
        for (_, i) in arr {
            ans.push(nums[i]);
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int[] SortJumbled(int[] mapping, int[] nums) {
        Func<int, int> f = (int x) => {
            if (x == 0) {
                return mapping[0];
            }
            int y = 0;
            int k = 1;
            int num = x;
            while (num != 0) {
                int v = mapping[num % 10];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            return y;
        };

        int n = nums.Length;
        List<(int, int)> arr = new List<(int, int)>();
        for (int i = 0; i < n; ++i) {
            arr.Add((f(nums[i]), i));
        }
        arr.Sort();

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i].Item2];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
