# [1346. 检查整数及其两倍数是否存在](https://leetcode-cn.com/problems/check-if-n-and-its-double-exist)

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        map = collections.defaultdict(int)
        for i, num in enumerate(arr):
            map[num] = i
        for i, num in enumerate(arr):
            if num << 1 in map and i != map[num << 1]:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
};
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
