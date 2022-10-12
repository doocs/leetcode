# [1385. Find the Distance Value Between Two Arrays](https://leetcode.com/problems/find-the-distance-value-between-two-arrays)

[中文文档](/solution/1300-1399/1385.Find%20the%20Distance%20Value%20Between%20Two%20Arrays/README.md)

## Description

<p>Given two integer arrays <code>arr1</code> and <code>arr2</code>, and the integer <code>d</code>, <em>return the distance value between the two arrays</em>.</p>

<p>The distance value is defined as the number of elements <code>arr1[i]</code> such that there is not any element <code>arr2[j]</code> where <code>|arr1[i]-arr2[j]| &lt;= d</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
For arr1[0]=4 we have: 
|4-10|=6 &gt; d=2 
|4-9|=5 &gt; d=2 
|4-1|=3 &gt; d=2 
|4-8|=4 &gt; d=2 
For arr1[1]=5 we have: 
|5-10|=5 &gt; d=2 
|5-9|=4 &gt; d=2 
|5-1|=4 &gt; d=2 
|5-8|=3 &gt; d=2
For arr1[2]=8 we have:
<strong>|8-10|=2 &lt;= d=2</strong>
<strong>|8-9|=1 &lt;= d=2</strong>
|8-1|=7 &gt; d=2
<strong>|8-8|=0 &lt;= d=2</strong>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= arr1[i], arr2[j] &lt;= 1000</code></li>
	<li><code>0 &lt;= d &lt;= 100</code></li>
</ul>

## Solutions

**Method 1: Brute-force**

**Method 2: Binary search**

<!-- tabs:start -->

### **Python3**

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
