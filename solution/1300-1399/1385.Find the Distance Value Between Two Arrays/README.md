---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README.md
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

<p>给你两个整数数组&nbsp;<code>arr1</code>&nbsp;，&nbsp;<code>arr2</code>&nbsp;和一个整数&nbsp;<code>d</code>&nbsp;，请你返回两个数组之间的&nbsp;<strong>距离值</strong>&nbsp;。</p>

<p>「<strong>距离值</strong>」<strong>&nbsp;</strong>定义为符合此距离要求的元素数目：对于元素&nbsp;<code>arr1[i]</code>&nbsp;，不存在任何元素&nbsp;<code>arr2[j]</code>&nbsp;满足 <code>|arr1[i]-arr2[j]| &lt;= d</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
<strong>输出：</strong>2
<strong>解释：</strong>
对于 arr1[0]=4 我们有：
|4-10|=6 &gt; d=2 
|4-9|=5 &gt; d=2 
|4-1|=3 &gt; d=2 
|4-8|=4 &gt; d=2 
所以 arr1[0]=4 符合距离要求

对于 arr1[1]=5 我们有：
|5-10|=5 &gt; d=2 
|5-9|=4 &gt; d=2 
|5-1|=4 &gt; d=2 
|5-8|=3 &gt; d=2
所以 arr1[1]=5 也符合距离要求

对于 arr1[2]=8 我们有：
<strong>|8-10|=2 &lt;= d=2</strong>
<strong>|8-9|=1 &lt;= d=2</strong>
|8-1|=7 &gt; d=2
<strong>|8-8|=0 &lt;= d=2</strong>
存在距离小于等于 2 的情况，不符合距离要求 

故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li>
	<li><code>-10^3 &lt;= arr1[i], arr2[j] &lt;= 10^3</code></li>
	<li><code>0 &lt;= d &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找

我们可以先对数组 $\textit{arr2}$ 排序，然后对于数组 $\textit{arr1}$ 中的每个元素 $x$，使用二分查找，找到数组 $\textit{arr2}$ 中第一个大于等于 $x - d$ 的元素，如果元素存在，且小于等于 $x + d$，则说明不符合距离要求，否则说明符合距离要求。我们将符合距离要求的元素个数累加，即为答案。

时间复杂度 $O((m + n) \times \log n)$，空间复杂度 $O(\log n)$。其中 $m$ 和 $n$ 分别是数组 $\textit{arr1}$ 和 $\textit{arr2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
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
