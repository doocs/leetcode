# [1346. 检查整数及其两倍数是否存在](https://leetcode.cn/problems/check-if-n-and-its-double-exist)

[English Version](/solution/1300-1399/1346.Check%20If%20N%20and%20Its%20Double%20Exist/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>，请你检查是否存在两个整数&nbsp;<code>N</code> 和 <code>M</code>，满足&nbsp;<code>N</code>&nbsp;是&nbsp;<code>M</code>&nbsp;的两倍（即，<code>N = 2 * M</code>）。</p>

<p>更正式地，检查是否存在两个下标&nbsp;<code>i</code> 和 <code>j</code> 满足：</p>

<ul>
	<li><code>i != j</code></li>
	<li><code>0 &lt;= i, j &lt; arr.length</code></li>
	<li><code>arr[i] == 2 * arr[j]</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [10,2,5,3]
<strong>输出：</strong>true
<strong>解释：</strong>N<code> = 10</code> 是 M<code> = 5 的两倍</code>，即 <code>10 = 2 * 5 。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [7,1,14,11]
<strong>输出：</strong>true
<strong>解释：</strong>N<code> = 14</code> 是 M<code> = 7 的两倍</code>，即 <code>14 = 2 * 7 </code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [3,1,7,11]
<strong>输出：</strong>false
<strong>解释：</strong>在该情况下不存在 N 和 M 满足 N = 2 * M 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 500</code></li>
	<li><code>-10^3 &lt;= arr[i] &lt;= 10^3</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

使用哈希表 `m` 记录 `arr` 每个元素 `v` 及其对应的下标 `i`。

遍历 `arr` 每个元素 `v`，若能在哈希表中找到 `v * 2`，且下标值与当前 `v` 的下标值不相等，说明找到了满足条件的元素，返回 `true`。否则遍历结束返回 `false`。

时间复杂度：$O(n)$。
空间复杂度：$O(n)$。

**方法二：排序 + 二分查找**

首先对 `arr` 排序。

然后遍历 `arr` 每个元素 `v`，二分查找 `arr` 中是否存在 `v * 2` 元素，是则返回 `true`。

注意，元素可能为 0，这种情况下，`v*2` 的值同样为 0，二分查找可能会找到同个位置的元素，与题意不符。因此，可以预先统计 `arr` 中元素 0 的个数，若超过 1 个，可提前返回 `true`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        m = {v: i for i, v in enumerate(arr)}
        return any(v << 1 in m and m[v << 1] != i for i, v in enumerate(arr))
```

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        s = set()
        for v in arr:
            if v * 2 in s or (v % 2 == 0 and v // 2 in s):
                return True
            s.add(v)
        return False
```

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        if arr.count(0) > 1:
            return True
        arr.sort()
        n = len(arr)
        for v in arr:
            idx = bisect_left(arr, v * 2)
            if v != 0 and idx != n and arr[idx] == v * 2:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            m.put(arr[i], i);
        }
        for (int i = 0; i < n; ++i) {
            if (m.containsKey(arr[i] << 1) && m.get(arr[i] << 1) != i) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            if (s.contains(v * 2) || (v % 2 == 0 && s.contains(v / 2))) {
                return true;
            }
            s.add(v);
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v == 0) {
                ++cnt;
                if (cnt > 1) {
                    return true;
                }
            }
        }
        Arrays.sort(arr);
        for (int v : arr) {
            if (v != 0) {
                int idx = Arrays.binarySearch(arr, v * 2);
                if (idx >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_map<int, int> m;
        int n = arr.size();
        for (int i = 0; i < n; ++i) m[arr[i]] = i;
        for (int i = 0; i < n; ++i)
            if (m.count(arr[i] * 2) && m[arr[i] * 2] != i)
                return true;
        return false;
    }
};
```

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_set<int> s;
        for (int& v : arr) {
            if (s.count(v * 2) || (v % 2 == 0 && s.count(v / 2))) {
                return true;
            }
            s.insert(v);
        }
        return false;
    }
};
```

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        int cnt = 0;
        for (int& v : arr) if (v == 0) ++cnt;
        if (cnt > 1) return true;
        sort(arr.begin(), arr.end());
        int n = arr.size();
        for (int& v : arr)
        {
            if (v == 0) continue;
            int idx = lower_bound(arr.begin(), arr.end(), v * 2) - arr.begin();
            if (idx != n && arr[idx] == v * 2) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func checkIfExist(arr []int) bool {
	m := make(map[int]int)
	for i, v := range arr {
		m[v] = i
	}
	for i, v := range arr {
		if j, ok := m[v*2]; ok && j != i {
			return true
		}
	}
	return false
}
```

```go
func checkIfExist(arr []int) bool {
	s := map[int]bool{}
	for _, v := range arr {
		if s[v*2] || (v%2 == 0 && s[v/2]) {
			return true
		}
		s[v] = true
	}
	return false
}
```

```go
func checkIfExist(arr []int) bool {
	cnt := 0
	for _, v := range arr {
		if v == 0 {
			cnt++
			if cnt > 1 {
				return true
			}
		}
	}
	sort.Ints(arr)
	n := len(arr)
	for _, v := range arr {
		if v != 0 {
			left, right := 0, n
			for left < right {
				mid := (left + right) >> 1
				if arr[mid] >= v*2 {
					right = mid
				} else {
					left = mid + 1
				}
			}
			if right != n && arr[left] == v*2 {
				return true
			}
		}
	}
	return false
}
```

### **TypeScript**

```ts
function checkIfExist(arr: number[]): boolean {
    const s = new Set();
    for (const v of arr) {
        if (s.has(v << 1) || s.has(v / 2)) {
            return true;
        }
        s.add(v);
    }
    return false;
}
```

```ts
function checkIfExist(arr: number[]): boolean {
    let cnt = 0;
    for (const v of arr) {
        if (v == 0) {
            ++cnt;
            if (cnt > 1) {
                return true;
            }
        }
    }
    const n = arr.length;
    arr.sort((a, b) => a - b);
    for (const v of arr) {
        if (v != 0) {
            let left = 0,
                right = n;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (arr[mid] >= v * 2) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left != n && arr[left] == v * 2) {
                return true;
            }
        }
    }
    return false;
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var checkIfExist = function (arr) {
    const s = new Set();
    for (const v of arr) {
        if (s.has(v << 1) || s.has(v / 2)) {
            return true;
        }
        s.add(v);
    }
    return false;
};
```

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var checkIfExist = function (arr) {
    let cnt = 0;
    for (const v of arr) {
        if (v == 0) {
            ++cnt;
            if (cnt > 1) {
                return true;
            }
        }
    }
    const n = arr.length;
    arr.sort((a, b) => a - b);
    for (const v of arr) {
        if (v != 0) {
            let left = 0,
                right = n;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (arr[mid] >= v * 2) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left != n && arr[left] == v * 2) {
                return true;
            }
        }
    }
    return false;
};
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn check_if_exist(arr: Vec<i32>) -> bool {
        let mut map = HashMap::new();
        for (i, v) in arr.iter().enumerate() {
            map.insert(v, i);
        }
        for (i, v) in arr.iter().enumerate() {
            if map.contains_key(&(v * 2)) && map[&(v * 2)] != i {
                return true;
            }
        }
        false
    }
}
```

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn check_if_exist(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        for i in 0..n {
            let target = arr[i] * 2;
            let mut left = 0;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                match arr[mid].cmp(&target) {
                    Ordering::Less => left = mid + 1,
                    Ordering::Greater => right = mid,
                    Ordering::Equal => {
                        if mid == i {
                            break;
                        }
                        return true;
                    }
                }
            }
        }
        false
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Boolean
     */
    function checkIfExist($arr) {
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i] * 2] = $i;
        }
        for ($i = 0; $i < count($arr); $i++) {
            if (isset($hashtable[$arr[$i]]) && $hashtable[$arr[$i]] != $i) return true;
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
