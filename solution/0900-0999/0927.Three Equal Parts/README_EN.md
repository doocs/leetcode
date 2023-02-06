# [927. Three Equal Parts](https://leetcode.com/problems/three-equal-parts)

[中文文档](/solution/0900-0999/0927.Three%20Equal%20Parts/README.md)

## Description

<p>You are given an array <code>arr</code> which consists of only zeros and ones, divide the array into <strong>three non-empty parts</strong> such that all of these parts represent the same binary value.</p>

<p>If it is possible, return any <code>[i, j]</code> with <code>i + 1 &lt; j</code>, such that:</p>

<ul>
	<li><code>arr[0], arr[1], ..., arr[i]</code> is the first part,</li>
	<li><code>arr[i + 1], arr[i + 2], ..., arr[j - 1]</code> is the second part, and</li>
	<li><code>arr[j], arr[j + 1], ..., arr[arr.length - 1]</code> is the third part.</li>
	<li>All three parts have equal binary values.</li>
</ul>

<p>If it is not possible, return <code>[-1, -1]</code>.</p>

<p>Note that the entire part is used when considering what binary value it represents. For example, <code>[1,1,0]</code> represents <code>6</code> in decimal, not <code>3</code>. Also, leading zeros <strong>are allowed</strong>, so <code>[0,1,1]</code> and <code>[1,1]</code> represent the same value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> arr = [1,0,1,0,1]
<strong>Output:</strong> [0,3]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> arr = [1,1,0,1,1]
<strong>Output:</strong> [-1,-1]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> arr = [1,1,0,0,1]
<strong>Output:</strong> [0,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[i]</code> is <code>0</code> or <code>1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def threeEqualParts(self, arr: List[int]) -> List[int]:
        def find(x):
            s = 0
            for i, v in enumerate(arr):
                s += v
                if s == x:
                    return i

        n = len(arr)
        cnt, mod = divmod(sum(arr), 3)
        if mod:
            return [-1, -1]
        if cnt == 0:
            return [0, n - 1]

        i, j, k = find(1), find(cnt + 1), find(cnt * 2 + 1)
        while k < n and arr[i] == arr[j] == arr[k]:
            i, j, k = i + 1, j + 1, k + 1
        return [i - 1, j] if k == n else [-1, -1]
```

### **Java**

```java
class Solution {
    private int[] arr;

    public int[] threeEqualParts(int[] arr) {
        this.arr = arr;
        int cnt = 0;
        int n = arr.length;
        for (int v : arr) {
            cnt += v;
        }
        if (cnt % 3 != 0) {
            return new int[] {-1, -1};
        }
        if (cnt == 0) {
            return new int[] {0, n - 1};
        }
        cnt /= 3;

        int i = find(1), j = find(cnt + 1), k = find(cnt * 2 + 1);
        for (; k < n && arr[i] == arr[j] && arr[j] == arr[k]; ++i, ++j, ++k) {
        }
        return k == n ? new int[] {i - 1, j} : new int[] {-1, -1};
    }

    private int find(int x) {
        int s = 0;
        for (int i = 0; i < arr.length; ++i) {
            s += arr[i];
            if (s == x) {
                return i;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> threeEqualParts(vector<int>& arr) {
        int n = arr.size();
        int cnt = accumulate(arr.begin(), arr.end(), 0);
        if (cnt % 3) return {-1, -1};
        if (!cnt) return {0, n - 1};
        cnt /= 3;

        auto find = [&](int x) {
            int s = 0;
            for (int i = 0; i < n; ++i) {
                s += arr[i];
                if (s == x) return i;
            }
            return 0;
        };
        int i = find(1), j = find(cnt + 1), k = find(cnt * 2 + 1);
        for (; k < n && arr[i] == arr[j] && arr[j] == arr[k]; ++i, ++j, ++k) {}
        return k == n ? vector<int>{i - 1, j} : vector<int>{-1, -1};
    }
};
```

### **Go**

```go
func threeEqualParts(arr []int) []int {
	find := func(x int) int {
		s := 0
		for i, v := range arr {
			s += v
			if s == x {
				return i
			}
		}
		return 0
	}
	n := len(arr)
	cnt := 0
	for _, v := range arr {
		cnt += v
	}
	if cnt%3 != 0 {
		return []int{-1, -1}
	}
	if cnt == 0 {
		return []int{0, n - 1}
	}
	cnt /= 3
	i, j, k := find(1), find(cnt+1), find(cnt*2+1)
	for ; k < n && arr[i] == arr[j] && arr[j] == arr[k]; i, j, k = i+1, j+1, k+1 {
	}
	if k == n {
		return []int{i - 1, j}
	}
	return []int{-1, -1}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {number[]}
 */
var threeEqualParts = function (arr) {
    function find(x) {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            s += arr[i];
            if (s == x) {
                return i;
            }
        }
        return 0;
    }
    const n = arr.length;
    let cnt = 0;
    for (const v of arr) {
        cnt += v;
    }
    if (cnt % 3) {
        return [-1, -1];
    }
    if (cnt == 0) {
        return [0, n - 1];
    }
    cnt = Math.floor(cnt / 3);
    let [i, j, k] = [find(1), find(cnt + 1), find(cnt * 2 + 1)];
    for (; k < n && arr[i] == arr[j] && arr[j] == arr[k]; ++i, ++j, ++k) {}
    return k == n ? [i - 1, j] : [-1, -1];
};
```

### **...**

```

```

<!-- tabs:end -->
