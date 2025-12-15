---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3769.Sort%20Integers%20by%20Binary%20Reflection/README.md
rating: 1363
source: 第 479 场周赛 Q1
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [3769. 二进制反射排序](https://leetcode.cn/problems/sort-integers-by-binary-reflection)

[English Version](/solution/3700-3799/3769.Sort%20Integers%20by%20Binary%20Reflection/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p><strong>二进制反射</strong> 是对一个 <strong>正整数</strong> 的二进制表示按顺序反转（忽略前导零）后，将反转得到的二进制数转为十进制的结果。</p>

<p>请按每个元素的二进制反射值的 <strong>升序</strong> 对数组进行排序。如果两个不同的数字具有相同的二进制反射值，则 <strong>较小</strong> 的原始数字应排在前面。</p>

<p>返回排序后的数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,5,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,4,5]</span></p>

<p><strong>解释：</strong></p>

<p>二进制反射值为：</p>

<ul>
	<li>4 -&gt; (二进制) <code>100</code> -&gt; (反转) <code>001</code> -&gt; 1</li>
	<li>5 -&gt; (二进制) <code>101</code> -&gt; (反转) <code>101</code> -&gt; 5</li>
	<li>4 -&gt; (二进制) <code>100</code> -&gt; (反转) <code>001</code> -&gt; 1</li>
</ul>
根据反射值排序为 <code>[4, 4, 5]</code>。</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,6,5,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">[8,3,6,5]</span></p>

<p><strong>解释：</strong></p>

<p>二进制反射值为：</p>

<ul>
	<li>3 -&gt; (二进制) <code>11</code> -&gt; (反转) <code>11</code> -&gt; 3</li>
	<li>6 -&gt; (二进制) <code>110</code> -&gt; (反转) <code>011</code> -&gt; 3</li>
	<li>5 -&gt; (二进制) <code>101</code> -&gt; (反转) <code>101</code> -&gt; 5</li>
	<li>8 -&gt; (二进制) <code>1000</code> -&gt; (反转) <code>0001</code> -&gt; 1</li>
</ul>
根据反射值排序为 <code>[8, 3, 6, 5]</code>。<br />
注意，3 和 6 的反射值相同，因此需要按原始值的升序排列。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自定义排序

我们定义一个函数 $f(x)$ 来计算整数 $x$ 的二进制反射值。具体地，我们不断取出 $x$ 的最低位，并将其添加到结果 $y$ 的末尾，直到 $x$ 变为 $0$。

然后，我们对数组 $\textit{nums}$ 进行排序，排序的关键字为每个元素的二进制反射值和原始值的元组 $(f(x), x)$。这样可以确保当两个元素的二进制反射值相同时，较小的原始值会排在前面。

最后，我们返回排序后的数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortByReflection(self, nums: List[int]) -> List[int]:
        def f(x: int) -> int:
            y = 0
            while x:
                y = y << 1 | (x & 1)
                x >>= 1
            return y

        nums.sort(key=lambda x: (f(x), x))
        return nums
```

#### Java

```java
class Solution {
    public int[] sortByReflection(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        Arrays.setAll(a, i -> nums[i]);

        Arrays.sort(a, (u, v) -> {
            int fu = f(u);
            int fv = f(v);
            if (fu != fv) {
                return Integer.compare(fu, fv);
            }
            return Integer.compare(u, v);
        });

        for (int i = 0; i < n; i++) nums[i] = a[i];
        return nums;
    }

    private int f(int x) {
        int y = 0;
        while (x != 0) {
            y = (y << 1) | (x & 1);
            x >>= 1;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortByReflection(vector<int>& nums) {
        auto f = [](int x) {
            int y = 0;
            while (x) {
                y = (y << 1) | (x & 1);
                x >>= 1;
            }
            return y;
        };

        sort(nums.begin(), nums.end(), [&](int a, int b) {
            int fa = f(a);
            int fb = f(b);
            if (fa != fb) {
                return fa < fb;
            }
            return a < b;
        });

        return nums;
    }
};
```

#### Go

```go
func sortByReflection(nums []int) []int {
	f := func(x int) int {
		y := 0
		for x != 0 {
			y = (y << 1) | (x & 1)
			x >>= 1
		}
		return y
	}

	sort.Slice(nums, func(i, j int) bool {
		fi := f(nums[i])
		fj := f(nums[j])
		if fi != fj {
			return fi < fj
		}
		return nums[i] < nums[j]
	})

	return nums
}
```

#### TypeScript

```ts
function sortByReflection(nums: number[]): number[] {
    const f = (x: number): number => {
        let y = 0;
        for (; x; x >>= 1) {
            y = (y << 1) | (x & 1);
        }
        return y;
    };

    nums.sort((a, b) => {
        const fa = f(a);
        const fb = f(b);
        if (fa !== fb) {
            return fa - fb;
        }
        return a - b;
    });

    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
