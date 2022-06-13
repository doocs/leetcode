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

**方法一：暴力枚举**

由于 `arr1` 和 `arr2` 的长度不超过 500，因此可以直接暴力遍历。

时间复杂度 $O(mn)$，其中 $m$ 为 `arr1` 的长度，$n$ 为 `arr2` 的长度。

**方法二：二分查找**

对于 `arr1` 中的每个元素 `a`，若在 `arr2` 中存在 `b`，使得 `b ∈ [a - d, a + d]`，那么就符合距离要求，不进行累加。

因此，可以先对 `arr2` 进行排序。然后对于每个元素 `a`，二分枚举 `arr2` 判断是否存在符合距离要求的 `b`。

时间复杂度 $O((m + n)logn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        return sum(all(abs(a - b) > d for b in arr2) for a in arr1)
```

```python
class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        def check(a):
            idx = bisect_left(arr2, a - d)
            if idx != len(arr2) and arr2[idx] <= a + d:
                return False
            return True

        arr2.sort()
        return sum(check(a) for a in arr1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans = 0;
        for (int a : arr1) {
            if (check(arr2, a, d)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int[] arr, int a, int d) {
        for (int b : arr) {
            if (Math.abs(a - b) <= d) {
                return false;
            }
        }
        return true;
    }
}
```

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
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= a - d) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left != arr.length && arr[left] <= a + d) {
            return false;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        int ans = 0;
        for (int& a : arr1)
            ans += check(arr2, a, d);
        return ans;
    }

    bool check(vector<int>& arr, int a, int d) {
        for (int& b : arr)
            if (abs(a - b) <= d)
                return false;
        return true;
    }
};
```

```cpp
class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        sort(arr2.begin(), arr2.end());
        int ans = 0;
        for (int& a : arr1)
            if (check(arr2, a, d))
                ++ans;
        return ans;
    }

    bool check(vector<int>& arr, int a, int d) {
        int idx = lower_bound(arr.begin(), arr.end(), a - d) - arr.begin();
        if (idx != arr.size() && arr[idx] <= a + d) return false;
        return true;
    }
};
```

### **Go**

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) int {
	check := func(arr []int, a int) bool {
		for _, b := range arr {
			if -d <= a-b && a-b <= d {
				return false
			}
		}
		return true
	}

	ans := 0
	for _, a := range arr1 {
		if check(arr2, a) {
			ans++
		}
	}
	return ans
}
```

```go
func findTheDistanceValue(arr1 []int, arr2 []int, d int) int {
	sort.Ints(arr2)
	check := func(a int) bool {
		left, right := 0, len(arr2)
		for left < right {
			mid := (left + right) >> 1
			if arr2[mid] >= a-d {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if left != len(arr2) && arr2[left] <= a+d {
			return false
		}
		return true
	}
	ans := 0
	for _, a := range arr1 {
		if check(a) {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function findTheDistanceValue(
    arr1: number[],
    arr2: number[],
    d: number,
): number {
    let res = 0;
    for (const num of arr1) {
        if (arr2.every(v => Math.abs(num - v) > d)) {
            res++;
        }
    }
    return res;
}
```

```ts
function findTheDistanceValue(
    arr1: number[],
    arr2: number[],
    d: number,
): number {
    arr2.sort((a, b) => a - b);
    const n = arr2.length;
    let res = 0;
    for (const num of arr1) {
        let left = 0;
        let right = n - 1;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (arr2[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (
            Math.abs(num - arr2[left]) <= d ||
            (left !== 0 && Math.abs(num - arr2[left - 1]) <= d)
        ) {
            continue;
        }
        res++;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, arr2: Vec<i32>, d: i32) -> i32 {
        let mut res = 0;
        for num in arr1.iter() {
            if arr2.iter().all(|v| i32::abs(num - v) > d) {
                res += 1;
            }
        }
        res
    }
}
```

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
            if i32::abs(num - arr2[left]) <= d || (left != 0 && i32::abs(num - arr2[left - 1]) <= d) {
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
