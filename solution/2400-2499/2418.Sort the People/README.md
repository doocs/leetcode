# [2418. 按身高排序](https://leetcode.cn/problems/sort-the-people)

[English Version](/solution/2400-2499/2418.Sort%20the%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>names</code> ，和一个由 <strong>互不相同</strong> 的正整数组成的数组 <code>heights</code> 。两个数组的长度均为 <code>n</code> 。</p>

<p>对于每个下标 <code>i</code>，<code>names[i]</code> 和 <code>heights[i]</code> 表示第 <code>i</code> 个人的名字和身高。</p>

<p>请按身高 <strong>降序</strong> 顺序返回对应的名字数组 <code>names</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>names = ["Mary","John","Emma"], heights = [180,165,170]
<strong>输出：</strong>["Mary","Emma","John"]
<strong>解释：</strong>Mary 最高，接着是 Emma 和 John 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>names = ["Alice","Bob","Bob"], heights = [155,185,150]
<strong>输出：</strong>["Bob","Alice","Bob"]
<strong>解释：</strong>第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == names.length == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>names[i]</code> 由大小写英文字母组成</li>
	<li><code>heights</code> 中的所有值互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接模拟**

直接按照题意，从高到低遍历身高数组，将对应的名字加入结果数组即可。

时间复杂度 $O(n\log n)$。其中 $n$ 为数组 `heights` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        idx = list(range(len(heights)))
        idx.sort(key=lambda i: -heights[i])
        return [names[i] for i in idx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {heights[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {
        int n = heights.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {-heights[i], i};
        }
        sort(arr.begin(), arr.end());
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i].second];
        }
        return ans;
    }
};
```

### **Go**

```go
func sortPeople(names []string, heights []int) []string {
	n := len(heights)
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range heights {
		arr[i] = pair{v, i}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].v > arr[j].v })
	ans := make([]string, n)
	for i, v := range arr {
		ans[i] = names[v.i]
	}
	return ans
}
```

### **TypeScript**

```ts
function sortPeople(names: string[], heights: number[]): string[] {
    return names
        .map<[string, number]>((s, i) => [s, heights[i]])
        .sort((a, b) => b[1] - a[1])
        .map(([v]) => v);
}
```

### **Rust**

```rust
impl Solution {
    pub fn sort_people(names: Vec<String>, heights: Vec<i32>) -> Vec<String> {
        let mut combine: Vec<(String, i32)> = names.into_iter().zip(heights.into_iter()).collect();
        combine.sort_by(|a, b| b.1.cmp(&a.1));
        combine.iter().map(|s| s.0.clone()).collect()
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param String[] $names
     * @param Integer[] $heights
     * @return String[]
     */
    function sortPeople($names, $heights) {
        for ($i = 0; $i < count($heights); $i++) {
            $hashtable[$heights[$i]] = $names[$i];
        }
        rsort($heights);
        for ($j = 0; $j < count($heights); $j++) {
            $rs[$j] = $hashtable[$heights[$j]];
        }
        return $rs;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
