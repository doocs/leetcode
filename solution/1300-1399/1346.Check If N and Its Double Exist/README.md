---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1346.Check%20If%20N%20and%20Its%20Double%20Exist/README.md
rating: 1225
source: 第 175 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 排序
---

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

### 方法一：哈希表

我们定义一个哈希表 $s$，用于记录访问过的元素。

遍历数组 $arr$，对于每个元素 $x$，如果 $x$ 的两倍或者 $x$ 的一半在哈希表 $s$ 中，那么返回 `true`。否则将 $x$ 加入哈希表 $s$。

若遍历结束后没有找到满足条件的元素，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        s = set()
        for x in arr:
            if x * 2 in s or (x % 2 == 0 and x // 2 in s):
                return True
            s.add(x)
        return False
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            if (s.contains(x * 2) || ((x % 2 == 0 && s.contains(x / 2)))) {
                return true;
            }
            s.add(x);
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_set<int> s;
        for (int x : arr) {
            if (s.contains(x * 2) || (x % 2 == 0 && s.contains(x / 2))) {
                return true;
            }
            s.insert(x);
        }
        return false;
    }
};
```

```go
func checkIfExist(arr []int) bool {
	s := map[int]bool{}
	for _, x := range arr {
		if s[x*2] || (x%2 == 0 && s[x/2]) {
			return true
		}
		s[x] = true
	}
	return false
}
```

```ts
function checkIfExist(arr: number[]): boolean {
    const s: Set<number> = new Set();
    for (const x of arr) {
        if (s.has(x * 2) || (x % 2 === 0 && s.has((x / 2) | 0))) {
            return true;
        }
        s.add(x);
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
