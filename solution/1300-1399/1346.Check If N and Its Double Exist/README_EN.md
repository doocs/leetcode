# [1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist)

[中文文档](/solution/1300-1399/1346.Check%20If%20N%20and%20Its%20Double%20Exist/README.md)

## Description

<p>Given an array <code>arr</code> of integers, check if there exists two integers <code>N</code> and <code>M</code> such that <code>N</code> is the double of <code>M</code> ( i.e. <code>N = 2 * M</code>).</p>

<p>More formally check if there exists&nbsp;two indices <code>i</code> and <code>j</code> such that :</p>

<ul>
	<li><code>i != j</code></li>
	<li><code>0 &lt;= i, j &lt; arr.length</code></li>
	<li><code>arr[i] == 2 * arr[j]</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,2,5,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> N<code> = 10</code> is the double of M<code> = 5</code>,that is, <code>10 = 2 * 5</code>.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,1,14,11]
<strong>Output:</strong> true
<strong>Explanation:</strong> N<code> = 14</code> is the double of M<code> = 7</code>,that is, <code>14 = 2 * 7</code>.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,7,11]
<strong>Output:</strong> false
<strong>Explanation:</strong> In this case does not exist N and M, such that N = 2 * M.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 500</code></li>
	<li><code>-10^3 &lt;= arr[i] &lt;= 10^3</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        map = defaultdict(int)
        for i, num in enumerate(arr):
            map[num] = i
        for i, num in enumerate(arr):
            if num << 1 in map and i != map[num << 1]:
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] << 1) && i != map.get(arr[i] << 1))
                return true;
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function checkIfExist(arr: number[]): boolean {
    for (let i = arr.length - 1; i >= 0; --i) {
        let cur = arr[i];
        let t1 = 2 * cur;
        if (arr.includes(t1) && arr.indexOf(t1) != i) {
            return true;
        }
        let t2 = cur >> 1;
        if (cur % 2 == 0 && arr.includes(t2) && arr.indexOf(t2) != i) {
            return true;
        }
    }
    return false;
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_map<int, int> map;
        for (int i = 0; i < arr.size(); ++i) {
            map[arr[i]] = i;
        }
        for (int i = 0; i < arr.size(); ++i) {
            if (map.find(arr[i] * 2) != map.end() && i != map[arr[i] * 2]) {
                return true;
            }
        }
        return false;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
