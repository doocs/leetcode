---
comments: true
difficulty: 简单
rating: 1234
source: 第 22 场双周赛 Q1
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [1385. 两个数组间的距离值](https://leetcode.cn/problems/find-the-distance-value-between-two-arrays)

[English Version](/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p dir="ltr">给定两个整数数组 <code dir="ltr">arr1</code> 和 <code dir="ltr">arr2</code>，以及一个整数 <code dir="ltr">d</code>。</p>

<p dir="ltr">如果对于每个元素 <code dir="ltr">arr2[j]</code>，都满足 <code dir="ltr">|arr1[i] - arr2[j]| &gt; d</code>，则称元素 <code dir="ltr">arr1[i]</code> 是&nbsp;<strong>远离&nbsp;</strong>的。换句话说，<code dir="ltr">arr2</code> 中不存在与该元素距离不超过 <code dir="ltr">d</code> 的元素。</p>

<p dir="ltr">返回&nbsp;<strong>距离值</strong>，即 <code dir="ltr">arr1</code> 中&nbsp;<strong>远离&nbsp;</strong>元素的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>4 是远离元素。<code>arr2</code> 中与它最接近的值是 1，且 <code>|4 - 1| = 3</code>，大于 <code>d = 2</code>。</li>
	<li>5 是远离元素。<code>arr2</code> 中与它最接近的值是 8，且 <code>|5 - 8| = 3</code>，大于 <code>d = 2</code>。</li>
	<li>8 不是远离元素，因为 <code>|8 - 8| = 0</code>，不超过 <code>d = 2</code>。</li>
	<li>共有两个远离元素，因此距离值为 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>1 是远离元素。<code>arr2</code> 中与它最接近的值是 -3，且 <code>|1 - (-3)| = 4</code>，大于 <code>d = 3</code>。</li>
	<li>2 是远离元素。<code>arr2</code> 中与它最接近的值是 6，且 <code>|2 - 6| = 4</code>，大于 <code>d = 3</code>。</li>
	<li>4 不是远离元素，因为 <code>|4 - 6| = 2</code>，不超过 <code>d = 3</code>。</li>
	<li>3 不是远离元素，因为 <code>|3 - 6| = 3</code>，不超过 <code>d = 3</code>。</li>
	<li>共有两个远离元素，因此距离值为 2。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>100 是远离元素。<code>arr2</code> 中与它最接近的值是 10，且 <code>|100 - 10| = 90</code>，大于 <code>d = 6</code>。</li>
	<li>2 不是远离元素，因为 <code>|2 - (-2)| = 4</code>，不超过 <code>d = 6</code>。</li>
	<li>1 不是远离元素，因为 <code>|1 - (-2)| = 3</code>，不超过 <code>d = 6</code>。</li>
	<li>3 不是远离元素，因为 <code>|3 - 7| = 4</code>，不超过 <code>d = 6</code>。</li>
	<li>只有一个远离元素，因此距离值为 1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= arr1[i], arr2[j] &lt;= 1000</code></li>
	<li><code>0 &lt;= d &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

<!-- thinking:start -->

> **思考**
>
> 统计 $\textit{arr1}$ 中与 $\textit{arr2}$ 所有元素距离都大于 $d$ 的个数。双重扫描为 $O(mn)$。对 $\textit{arr2}$ 排序后，每个 $x$ 只需看是否存在落在 $[x-d,x+d]$ 内的值：二分第一个 $\ge x-d$ 的位置，若越界或该值已大于 $x+d$，则 $x$ 合法。

<!-- thinking:end -->

我们可以先对数组 $\textit{arr2}$ 排序，然后对于数组 $\textit{arr1}$ 中的每个元素 $x$，使用二分查找，找到数组 $\textit{arr2}$ 中第一个大于等于 $x - d$ 的元素，如果元素存在，且小于等于 $x + d$，则说明不符合距离要求，否则说明符合距离要求。我们将符合距离要求的元素个数累加，即为答案。

时间复杂度 $O((m + n) \log n)$，空间复杂度 $O(\log n)$。其中 $m$ 和 $n$ 分别是数组 $\textit{arr1}$ 和 $\textit{arr2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheDistanceValue(self, arr1: list[int], arr2: list[int], d: int) -> int:
        arr2.sort()
        ans = 0
        for x in arr1:
            i = bisect_left(arr2, x - d)
            ans += i == len(arr2) or arr2[i] > x + d
        return ans
```

#### Java

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x - d);
            i = i < 0 ? -i - 1 : i;
            if (i == arr2.length || arr2[i] > x + d) {
                ++ans;
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
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        ranges::sort(arr2);
        int ans = 0;
        for (int x : arr1) {
            auto it = ranges::lower_bound(arr2, x - d);
            if (it == arr2.end() || *it > x + d) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) (ans int) {
	sort.Ints(arr2)
	for _, x := range arr1 {
		i := sort.SearchInts(arr2, x-d)
		if i == len(arr2) || arr2[i] > x+d {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function findTheDistanceValue(arr1: number[], arr2: number[], d: number): number {
    arr2.sort((a, b) => a - b);
    let ans: number = 0;
    for (const x of arr1) {
        const i = _.sortedIndex(arr2, x - d);
        if (i === arr2.length || arr2[i] > x + d) {
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, mut arr2: Vec<i32>, d: i32) -> i32 {
        arr2.sort();
        let mut ans = 0;
        for &x in &arr1 {
            let i = match arr2.binary_search(&(x - d)) {
                Ok(j) => j,
                Err(j) => j,
            };
            if i == arr2.len() || arr2[i] > x + d {
                ans += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
