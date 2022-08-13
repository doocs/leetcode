# [2295. 替换数组中的元素](https://leetcode.cn/problems/replace-elements-in-an-array)

[English Version](/solution/2200-2299/2295.Replace%20Elements%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它包含 <code>n</code>&nbsp;个 <strong>互不相同</strong>&nbsp;的正整数。请你对这个数组执行 <code>m</code>&nbsp;个操作，在第 <code>i</code>&nbsp;个操作中，你需要将数字&nbsp;<code>operations[i][0]</code> 替换成&nbsp;<code>operations[i][1]</code>&nbsp;。</p>

<p>题目保证在第 <code>i</code>&nbsp;个操作中：</p>

<ul>
	<li><code>operations[i][0]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中存在。</li>
	<li><code>operations[i][1]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中不存在。</li>
</ul>

<p>请你返回执行完所有操作后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
<b>输出：</b>[3,2,7,1]
<b>解释：</b>我们对 nums 执行以下操作：
- 将数字 1 替换为 3 。nums 变为 [<em><strong>3</strong></em>,2,4,6] 。
- 将数字 4 替换为 7 。nums 变为 [3,2,<em><strong>7</strong></em>,6] 。
- 将数字 6 替换为 1 。nums 变为 [3,2,7,<em><strong>1</strong></em>] 。
返回最终数组 [3,2,7,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2], operations = [[1,3],[2,1],[3,2]]
<b>输出：</b>[2,1]
<b>解释：</b>我们对 nums 执行以下操作：
- 将数字 1 替换为 3 。nums 变为 [<em><strong>3</strong></em>,2] 。
- 将数字 2 替换为 1 。nums 变为 [3,<em><strong>1</strong></em>] 。
- 将数字 3 替换为 2 。nums 变为 [<em><strong>2</strong></em>,1] 。
返回最终数组 [2,1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == operations.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中所有数字 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>operations[i].length == 2</code></li>
	<li><code>1 &lt;= nums[i], operations[i][0], operations[i][1] &lt;= 10<sup>6</sup></code></li>
	<li>在执行第&nbsp;<code>i</code> 个操作时，<code>operations[i][0]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中存在。</li>
	<li>在执行第&nbsp;<code>i</code>&nbsp;个操作时，<code>operations[i][1]</code>&nbsp;在&nbsp;<code>nums</code>&nbsp;中不存在。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrayChange(self, nums: List[int], operations: List[List[int]]) -> List[int]:
        d = {v: i for i, v in enumerate(nums)}
        for a, b in operations:
            idx = d[a]
            d.pop(a)
            d[b] = idx
        ans = [0] * len(nums)
        for v, i in d.items():
            ans[i] = v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            d.put(nums[i], i);
        }
        for (int[] op : operations) {
            int a = op[0], b = op[1];
            int idx = d.get(a);
            d.remove(a);
            d.put(b, idx);
        }
        int[] ans = new int[n];
        d.forEach((v, i) -> {
            ans[i] = v;
        });
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        int n = nums.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) d[nums[i]] = i;
        for (auto& op : operations) {
            int a = op[0], b = op[1];
            int idx = d[a];
            d.erase(a);
            d[b] = idx;
        }
        vector<int> ans(n);
        for (auto& [v, i] : d) ans[i] = v;
        return ans;
    }
};
```

### **Go**

```go
func arrayChange(nums []int, operations [][]int) []int {
	d := map[int]int{}
	for i, v := range nums {
		d[v] = i
	}
	for _, op := range operations {
		a, b := op[0], op[1]
		idx := d[a]
		delete(d, a)
		d[b] = idx
	}
	ans := make([]int, len(nums))
	for v, i := range d {
		ans[i] = v
	}
	return ans
}
```

### **TypeScript**

```ts
function arrayChange(nums: number[], operations: number[][]): number[] {
    const n = nums.length;
    let hashMap = new Map(nums.map((v, i) => [v, i]));
    for (let [oldVal, newVal] of operations) {
        let idx = hashMap.get(oldVal);
        hashMap.delete(oldVal);
        hashMap.set(newVal, idx);
    }
    let ans = new Array(n);
    for (let [val, key] of hashMap.entries()) {
        ans[key] = val;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
