# [1394. Find Lucky Integer in an Array](https://leetcode.com/problems/find-lucky-integer-in-an-array)

[中文文档](/solution/1300-1399/1394.Find%20Lucky%20Integer%20in%20an%20Array/README.md)

## Description

<p>Given an array of integers <code>arr</code>, a <strong>lucky integer</strong> is an integer that has a frequency in the array equal to its value.</p>

<p>Return <em>the largest <strong>lucky integer</strong> in the array</em>. If there is no <strong>lucky integer</strong> return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,3,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The only lucky number in the array is 2 because frequency[2] == 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,2,3,3,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 1, 2 and 3 are all lucky numbers, return the largest of them.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,2,2,3,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no lucky numbers in the array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 500</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 500</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLucky(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        ans = -1
        for x, v in cnt.items():
            if x == v and ans < x:
                ans = x
        return ans
```

### **Java**

```java
class Solution {
    public int findLucky(int[] arr) {
        int[] cnt = new int[510];
        for (int x : cnt) {
            ++cnt[x];
        }
        int ans = -1;
        for (int x = 1; x < cnt.length; ++x) {
            if (cnt[x] == x) {
                ans = x;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLucky(vector<int>& arr) {
        int cnt[510];
        memset(cnt, 0, sizeof(cnt));
        for (int x : arr) {
            ++cnt[x];
        }
        int ans = -1;
        for (int x = 1; x < 510; ++x) {
            if (cnt[x] == x) {
                ans = x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLucky(arr []int) int {
	cnt := [510]int{}
	for _, x := range arr {
		cnt[x]++
	}
	ans := -1
	for x := 1; x < len(cnt); x++ {
		if cnt[x] == x {
			ans = x
		}
	}
	return ans
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $arr
     * @return Integer
     */
    function findLucky($arr) {
        $max = -1;
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i]] += 1;
        }
        $keys = array_keys($hashtable);
        for ($j = 0; $j < count($keys); $j++) {
            if ($hashtable[$keys[$j]] == $keys[$j]) $max = max($max, $keys[$j]);
        }
        return $max;
    }
}
```

### **TypeScript**

```ts
function findLucky(arr: number[]): number {
    const cnt = new Array(510).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    let ans = -1;
    for (let x = 1; x < cnt.length; ++x) {
        if (cnt[x] === x) {
            ans = x;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
