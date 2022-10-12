# [1550. Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds)

[中文文档](/solution/1500-1599/1550.Three%20Consecutive%20Odds/README.md)

## Description

Given an integer array <code>arr</code>, return <code>true</code>&nbsp;if there are three consecutive odd numbers in the array. Otherwise, return&nbsp;<code>false</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,6,4,1]
<strong>Output:</strong> false
<b>Explanation:</b> There are no three consecutive odds.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,34,3,4,5,7,23,12]
<strong>Output:</strong> true
<b>Explanation:</b> [5,7,23] are three consecutive odds.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        cnt = 0
        for v in arr:
            if v & 1:
                cnt += 1
            else:
                cnt = 0
            if cnt == 3:
                return True
        return False
```

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        for i in range(len(arr) - 2):
            if arr[i] % 2 + arr[i + 1] % 2 + arr[i + 2] % 2 == 3:
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v % 2 == 1) {
                ++cnt;
            } else {
                cnt = 0;
            }
            if (cnt == 3) {
                return true;
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
    bool threeConsecutiveOdds(vector<int>& arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v & 1) ++cnt;
            else cnt = 0;
            if (cnt == 3) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func threeConsecutiveOdds(arr []int) bool {
	cnt := 0
	for _, v := range arr {
		if v%2 == 1 {
			cnt++
		} else {
			cnt = 0
		}
		if cnt == 3 {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts
function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const v of arr) {
        if (v & 1) {
            ++cnt;
        } else {
            cnt = 0;
        }
        if (cnt == 3) {
            return true;
        }
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
