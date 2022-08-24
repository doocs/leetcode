# [1460. Make Two Arrays Equal by Reversing Sub-arrays](https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays)

[中文文档](/solution/1400-1499/1460.Make%20Two%20Arrays%20Equal%20by%20Reversing%20Sub-arrays/README.md)

## Description

<p>You are given two integer arrays of equal length <code>target</code> and <code>arr</code>. In one step, you can select any <strong>non-empty sub-array</strong> of <code>arr</code> and reverse it. You are allowed to make any number of steps.</p>

<p>Return <code>true</code> <em>if you can make </em><code>arr</code><em> equal to </em><code>target</code><em>&nbsp;or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3,4], arr = [2,4,1,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> You can follow the next steps to convert arr to target:
1- Reverse sub-array [2,4,1], arr becomes [1,4,2,3]
2- Reverse sub-array [4,2], arr becomes [1,2,4,3]
3- Reverse sub-array [4,3], arr becomes [1,2,3,4]
There are multiple ways to convert arr to target, this is not the only way to do so.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [7], arr = [7]
<strong>Output:</strong> true
<strong>Explanation:</strong> arr is equal to target without any reverses.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [3,7,9], arr = [3,7,11]
<strong>Output:</strong> false
<strong>Explanation:</strong> arr does not have value 9 and it can never be converted to target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>target.length == arr.length</code></li>
	<li><code>1 &lt;= target.length &lt;= 1000</code></li>
	<li><code>1 &lt;= target[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        target.sort()
        arr.sort()
        return target == arr
```

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        return Counter(target) == Counter(arr)
```

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        cnt = [0] * 1001
        for a, b in zip(target, arr):
            cnt[a] += 1
            cnt[b] -= 1
        return all(v == 0 for v in cnt)
```

### **Java**

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt1 = new int[1001];
        int[] cnt2 = new int[1001];
        for (int v : target) {
            ++cnt1[v];
        }
        for (int v : arr) {
            ++cnt2[v];
        }
        return Arrays.equals(cnt1, cnt2);
    }
}
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt = new int[1001];
        for (int v : target) {
            ++cnt[v];
        }
        for (int v : arr) {
            if (--cnt[v] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        sort(target.begin(), target.end());
        sort(arr.begin(), arr.end());
        return target == arr;
    }
};
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt1(1001);
        vector<int> cnt2(1001);
        for (int& v : target) ++cnt1[v];
        for (int& v : arr) ++cnt2[v];
        return cnt1 == cnt2;
    }
};
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt(1001);
        for (int& v : target) ++cnt[v];
        for (int& v : arr) if (--cnt[v] < 0) return false;
        return true;
    }
};
```

### **Go**

```go
func canBeEqual(target []int, arr []int) bool {
	sort.Ints(target)
	sort.Ints(arr)
	for i, v := range target {
		if v != arr[i] {
			return false
		}
	}
	return true
}
```

```go
func canBeEqual(target []int, arr []int) bool {
	cnt1 := make([]int, 1001)
	cnt2 := make([]int, 1001)
	for _, v := range target {
		cnt1[v]++
	}
	for _, v := range arr {
		cnt2[v]++
	}
	for i, v := range cnt1 {
		if v != cnt2[i] {
			return false
		}
	}
	return true
}
```

```go
func canBeEqual(target []int, arr []int) bool {
	cnt := make([]int, 1001)
	for _, v := range target {
		cnt[v]++
	}
	for _, v := range arr {
		cnt[v]--
		if cnt[v] < 0 {
			return false
		}
	}
	return true
}
```

### **C**

```c
bool canBeEqual(int* target, int targetSize, int* arr, int arrSize){
    int count[1001] = {0};
    for (int i = 0 ; i < targetSize; i++) {
        count[target[i]]++;
        count[arr[i]]--;
    }
    for (int i = 0; i < 1001; i++) {
        if (count[i] != 0) {
            return false;
        }
    }
    return true;
}
```

### **TypeScript**

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    target.sort((a, b) => a - b);
    arr.sort((a, b) => a - b);
    const n = arr.length;
    for (let i = 0; i < n; i++) {
        if (target[i] !== arr[i]) {
            return false;
        }
    }
    return true;
}
```

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    const n = target.length;
    const count = new Array(1001).fill(0);
    for (let i = 0; i < n; i++) {
        count[target[i]]++;
        count[arr[i]]--;
    }
    return count.every(v => v === 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        target.sort();
        arr.sort();
        target == arr
    }
}
```

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        let n = target.len();
        let mut count = [0; 1001];
        for i in 0..n {
            count[target[i] as usize] += 1;
            count[arr[i] as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
