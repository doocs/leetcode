# [3132. 找出与数组相加的整数 II](https://leetcode.cn/problems/find-the-integer-added-to-array-ii)

[English Version](/solution/3100-3199/3132.Find%20the%20Integer%20Added%20to%20Array%20II/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数数组 <code>nums1</code> 和 <code>nums2</code>。</p>

<p>从 <code>nums1</code> 中移除两个元素，并且所有其他元素都与变量 <code>x</code> 所表示的整数相加。如果 <code>x</code> 为负数，则表现为元素值的减少。</p>

<p>执行上述操作后，<code>nums1</code> 和 <code>nums2</code> <strong>相等</strong> 。当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 <strong>相等</strong> 。</p>

<p>返回能够实现数组相等的 <strong>最小 </strong>整数<em> </em><code>x</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [4,20,16,12,8], nums2 = [14,18,10]</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">-2</span></p>

<p><strong>解释：</strong></p>

<p>移除 <code>nums1</code> 中下标为 <code>[0,4]</code> 的两个元素，并且每个元素与 <code>-2</code> 相加后，<code>nums1</code> 变为 <code>[18,14,10]</code> ，与 <code>nums2</code> 相等。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [3,5,5,3], nums2 = [7,7]</span></p>

<p><strong>输出：</strong><span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">2</span></p>

<p><strong>解释：</strong></p>

<p>移除 <code>nums1</code> 中下标为 <code>[0,3]</code> 的两个元素，并且每个元素与 <code>2</code> 相加后，<code>nums1</code> 变为 <code>[7,7]</code> ，与 <code>nums2</code> 相等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums1.length &lt;= 200</code></li>
	<li><code>nums2.length == nums1.length - 2</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
	<li>测试用例以这样的方式生成：存在一个整数 <code>x</code>，<code>nums1</code> 中的每个元素都与 <code>x</code> 相加后，再移除两个元素，<code>nums1</code> 可以与 <code>nums2</code> 相等。</li>
</ul>

## 解法

### 方法一：排序 + 枚举 + 双指针

我们首先对数组 $nums1$ 和 $nums2$ 进行排序，由于我们需要从 $nums1$ 中移除两个元素，因此我们只需要考虑 $nums1$ 的前三个元素，分别记为 $a_1, a_2, a_3$，我们可以枚举 $nums2$ 的第一个元素 $b_1$，那么我们可以得到 $x = b_1 - a_i$，其中 $i \in \{1, 2, 3\}$。然后我们可以通过双指针的方法来判断是否存在一个整数 $x$，使得 $nums1$ 和 $nums2$ 相等，取满足条件的最小的 $x$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        def f(x: int) -> bool:
            i = j = cnt = 0
            while i < len(nums1) and j < len(nums2):
                if nums2[j] - nums1[i] != x:
                    cnt += 1
                else:
                    j += 1
                i += 1
            return cnt <= 2

        nums1.sort()
        nums2.sort()
        return min(
            x
            for x in (nums2[0] - nums1[0], nums2[0] - nums1[1], nums2[0] - nums1[2])
            if f(x)
        )
```

```java
class Solution {
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = 1 << 30;
        for (int i = 0; i < 3; ++i) {
            int x = nums2[0] - nums1[i];
            if (f(nums1, nums2, x)) {
                ans = Math.min(ans, x);
            }
        }
        return ans;
    }

    private boolean f(int[] nums1, int[] nums2, int x) {
        int i = 0, j = 0, cnt = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] - nums1[i] != x) {
                ++cnt;
            } else {
                ++j;
            }
            ++i;
        }
        return cnt <= 2;
    }
}
```

```cpp
class Solution {
public:
    int minimumAddedInteger(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        int ans = 1 << 30;
        auto f = [&](int x) {
            int i = 0, j = 0, cnt = 0;
            while (i < nums1.size() && j < nums2.size()) {
                if (nums2[j] - nums1[i] != x) {
                    ++cnt;
                } else {
                    ++j;
                }
                ++i;
            }
            return cnt <= 2;
        };
        for (int i = 0; i < 3; ++i) {
            int x = nums2[0] - nums1[i];
            if (f(x)) {
                ans = min(ans, x);
            }
        }
        return ans;
    }
};
```

```go
func minimumAddedInteger(nums1 []int, nums2 []int) int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	ans := 1 << 30
	f := func(x int) bool {
		i, j, cnt := 0, 0, 0
		for i < len(nums1) && j < len(nums2) {
			if nums2[j]-nums1[i] != x {
				cnt++
			} else {
				j++
			}
			i++
		}
		return cnt <= 2
	}
	for _, a := range nums1[:3] {
		x := nums2[0] - a
		if f(x) {
			ans = min(ans, x)
		}
	}
	return ans
}
```

```ts
function minimumAddedInteger(nums1: number[], nums2: number[]): number {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => a - b);
    const f = (x: number): boolean => {
        let [i, j, cnt] = [0, 0, 0];
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] - nums1[i] !== x) {
                ++cnt;
            } else {
                ++j;
            }
            ++i;
        }
        return cnt <= 2;
    };
    let ans = Infinity;
    for (let i = 0; i < 3; ++i) {
        const x = nums2[0] - nums1[i];
        if (f(x)) {
            ans = Math.min(ans, x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
