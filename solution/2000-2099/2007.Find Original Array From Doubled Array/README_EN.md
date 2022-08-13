# [2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array)

[中文文档](/solution/2000-2099/2007.Find%20Original%20Array%20From%20Doubled%20Array/README.md)

## Description

<p>An integer array <code>original</code> is transformed into a <strong>doubled</strong> array <code>changed</code> by appending <strong>twice the value</strong> of every element in <code>original</code>, and then randomly <strong>shuffling</strong> the resulting array.</p>

<p>Given an array <code>changed</code>, return <code>original</code><em> if </em><code>changed</code><em> is a <strong>doubled</strong> array. If </em><code>changed</code><em> is not a <strong>doubled</strong> array, return an empty array. The elements in</em> <code>original</code> <em>may be returned in <strong>any</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> changed = [1,3,4,2,6,8]
<strong>Output:</strong> [1,3,4]
<strong>Explanation:</strong> One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> changed = [6,3,0,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> changed = [1]
<strong>Output:</strong> []
<strong>Explanation:</strong> changed is not a doubled array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        if len(changed) % 2 != 0:
            return []
        n = 100010
        counter = [0] * n
        for x in changed:
            counter[x] += 1
        if counter[0] % 2 != 0:
            return []
        res = [0] * (counter[0] // 2)
        for i in range(1, n):
            if counter[i] == 0:
                continue
            if i * 2 > n or counter[i] > counter[i * 2]:
                return []
            res.extend([i] * counter[i])
            counter[i * 2] -= counter[i]
        return res
```

### **Java**

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0) {
            return new int[]{};
        }
        int n = 100010;
        int[] counter = new int[n];
        for (int x : changed) {
            ++counter[x];
        }
        if (counter[0] % 2 != 0) {
            return new int[]{};
        }
        int[] res = new int[changed.length / 2];
        int j = counter[0] / 2;
        for (int i = 1; i < n; ++i) {
            if (counter[i] == 0) {
                continue;
            }
            if (i * 2 >= n || counter[i] > counter[i * 2]) {
                return new int[]{};
            }
            counter[i * 2] -= counter[i];
            while (counter[i]-- > 0) {
                res[j++] = i;
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
    vector<int> findOriginalArray(vector<int>& changed) {
        if (changed.size() % 2 != 0) return {};
        int n = 100010;
        vector<int> counter(n);
        for (int x : changed) ++counter[x];
        if (counter[0] % 2 != 0) return {};
        vector<int> res(changed.size() / 2);
        int j = counter[0] / 2;
        for (int i = 1; i < n; ++i) {
            if (counter[i] == 0) continue;
            if (i * 2 >= n || counter[i] > counter[i * 2]) return {};
            counter[i * 2] -= counter[i];
            while (counter[i]--) res[j++] = i;
        }
        return res;
    }
};
```

### **Go**

```go
func findOriginalArray(changed []int) []int {
	if len(changed)%2 != 0 {
		return []int{}
	}
	n := 100010
	counter := make([]int, n)
	for _, x := range changed {
		counter[x]++
	}
	if counter[0]%2 != 0 {
		return []int{}
	}
	var res []int
	for j := 0; j < counter[0]/2; j++ {
		res = append(res, 0)
	}
	for i := 1; i < n; i++ {
		if counter[i] == 0 {
			continue
		}
		if i*2 >= n || counter[i] > counter[i*2] {
			return []int{}
		}
		for j := 0; j < counter[i]; j++ {
			res = append(res, i)
		}
		counter[i*2] -= counter[i]
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
