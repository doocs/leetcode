# [1385. 两个数组间的距离值](https://leetcode.cn/problems/find-the-distance-value-between-two-arrays)

[English Version](/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们可以先对数组 $arr2$ 排序，然后对于数组 $arr1$ 中的每个元素 $a$，使用二分查找，找到数组 $arr2$ 中第一个大于等于 $a-d$ 的元素，如果元素存在，且小于等于 $a+d$，则说明不符合距离要求，否则说明符合距离要求。我们将符合距离要求的元素个数累加，即为答案。

时间复杂度 $O((m + n) \times \log n)$，空间复杂度 $O(\log n)$。其中 $m$ 和 $n$ 分别是数组 $arr1$ 和 $arr2$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        def check(a: int) -> bool:
            i = bisect_left(arr2, a - d)
            return i == len(arr2) or arr2[i] > a + d

        arr2.sort()
        return sum(check(a) for a in arr1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int a : arr1) {
            if (check(arr2, a, d)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int[] arr, int a, int d) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l >= arr.length || arr[l] > a + d;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        auto check = [&](int a) -> bool {
            auto it = lower_bound(arr2.begin(), arr2.end(), a - d);
            return it == arr2.end() || *it > a + d;
        };
        sort(arr2.begin(), arr2.end());
        int ans = 0;
        for (int& a : arr1) {
            ans += check(a);
        }
        return ans;
    }
};
```

### **Go**

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) (ans int) {
	sort.Ints(arr2)
	for _, a := range arr1 {
		i := sort.SearchInts(arr2, a-d)
		if i == len(arr2) || arr2[i] > a+d {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function findTheDistanceValue(arr1: number[], arr2: number[], d: number): number {
    const check = (a: number) => {
        let l = 0;
        let r = arr2.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr2[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l === arr2.length || arr2[l] > a + d;
    };
    arr2.sort((a, b) => a - b);
    let ans = 0;
    for (const a of arr1) {
        if (check(a)) {
            ++ans;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, mut arr2: Vec<i32>, d: i32) -> i32 {
        arr2.sort();
        let n = arr2.len();
        let mut res = 0;
        for &num in arr1.iter() {
            let mut left = 0;
            let mut right = n - 1;
            while left < right {
                let mid = left + (right - left) / 2;
                if arr2[mid] <= num {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if
                i32::abs(num - arr2[left]) <= d ||
                (left != 0 && i32::abs(num - arr2[left - 1]) <= d)
            {
                continue;
            }
            res += 1;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
