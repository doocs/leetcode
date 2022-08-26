# [1426. Counting Elements](https://leetcode.com/problems/counting-elements)

[中文文档](/solution/1400-1499/1426.Counting%20Elements/README.md)

## Description

<p>Given an integer array <code>arr</code>, count how many elements <code>x</code> there are, such that <code>x + 1</code> is also in <code>arr</code>. If there are duplicates in <code>arr</code>, count them separately.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1 and 2 are counted cause 2 and 3 are in arr.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,3,3,5,5,7,7]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No numbers are counted, cause there is no 2, 4, 6, or 8 in arr.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        return sum(x + 1 in arr for x in arr)
```

```python
class Solution:
    def countElements(self, arr: List[int]) -> int:
        s = set(arr)
        return sum(x + 1 in s for x in arr)
```

### **Java**

```java
class Solution {
    public int countElements(int[] arr) {
        int ans = 0;
        for (int x : arr) {
            for (int v : arr) {
                if (x + 1 == v) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int num : arr) {
            s.add(num);
        }
        int res = 0;
        for (int num : arr) {
            if (s.contains(num + 1)) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countElements(vector<int>& arr) {
        int ans = 0;
        for (int x : arr) {
            for (int v : arr) {
                if (x + 1 == v) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countElements(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        int ans = 0;
        for (int x : arr) {
            ans += s.count(x + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func countElements(arr []int) int {
	ans := 0
	for _, x := range arr {
		for _, v := range arr {
			if x+1 == v {
				ans++
				break
			}
		}
	}
	return ans
}
```

```go
func countElements(arr []int) int {
	s := map[int]bool{}
	for _, x := range arr {
		s[x] = true
	}
	ans := 0
	for _, x := range arr {
		if s[x+1] {
			ans++
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    let ans = 0;
    for (const x of arr) {
        ans += arr.includes(x + 1);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var countElements = function (arr) {
    const s = new Set();
    for (const x of arr) {
        s.add(x);
    }
    let ans = 0;
    for (const x of arr) {
        if (s.has(x + 1)) {
            ++ans;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
