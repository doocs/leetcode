# [927. 三等分](https://leetcode.cn/problems/three-equal-parts)

[English Version](/solution/0900-0999/0927.Three%20Equal%20Parts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由 <code>0</code> 和 <code>1</code> 组成的数组<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;，将数组分成 &nbsp;<strong>3&nbsp;个非空的部分</strong> ，使得所有这些部分表示相同的二进制值。</p>

<p>如果可以做到，请返回<strong>任何</strong>&nbsp;<code>[i, j]</code>，其中 <code>i+1 &lt; j</code>，这样一来：</p>

<ul>
	<li><code>arr[0], arr[1], ..., arr[i]</code>&nbsp;为第一部分；</li>
	<li><code>arr[i + 1], arr[i + 2], ..., arr[j - 1]</code>&nbsp;为第二部分；</li>
	<li><code>arr[j], arr[j + 1], ..., arr[arr.length - 1]</code>&nbsp;为第三部分。</li>
	<li>这三个部分所表示的二进制值相等。</li>
</ul>

<p>如果无法做到，就返回&nbsp;<code>[-1, -1]</code>。</p>

<p>注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，<code>[1,1,0]</code>&nbsp;表示十进制中的&nbsp;<code>6</code>，而不会是&nbsp;<code>3</code>。此外，前导零也是<strong>被允许</strong>的，所以&nbsp;<code>[0,1,1]</code> 和&nbsp;<code>[1,1]</code>&nbsp;表示相同的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,0,1,0,1]
<strong>输出：</strong>[0,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,0,1,1]
<strong>输出：</strong>[-1,-1]</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,0,0,1]
<strong>输出：</strong>[0,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>3 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[i]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：将 1 的数量三等分**

将 $1$ 的数量三等分，找到每一部分的第一个 $1$，分别记为 $i$, $j$, $k$。

然后从 $i$, $j$, $k$ 开始往后同时遍历每一部分，判断三部分对应的值是否相等，是则继续遍历，直至 $k$ 到达 $arr$ 末尾。

遍历结束时，若 $k=n$，说明满足三等分，返回此时的 $[i-1,j]$ 作为答案。

时间复杂度 $O(n)$，其中 $n$ 表示 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def threeEqualParts(self, arr: List[int]) -> List[int]:
        def find(cnt):
            s = 0
            for i, v in enumerate(arr):
                s += v
                if s == cnt:
                    return i
            return -1

        n = len(arr)
        cnt, mod = divmod(sum(arr), 3)
        if mod:
            return [-1, -1]
        if cnt == 0:
            return [0, n - 1]
        i = find(1)
        j = find(cnt + 1)
        k = find(cnt * 2 + 1)
        while k < n and arr[i] == arr[j] == arr[k]:
            i, j, k = i + 1, j + 1, k + 1
        if k == n:
            return [i - 1, j]
        return [-1, -1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        int cnt1 = 0;
        for (int v : arr) {
            cnt1 += v;
        }
        int cnt = cnt1 / 3;
        int mod = cnt1 % 3;
        if (mod != 0) {
            return new int[]{-1, -1};
        }
        if (cnt == 0) {
            return new int[]{0, n - 1};
        }
        int i = find(arr, 1);
        int j = find(arr, cnt + 1);
        int k = find(arr, cnt * 2 + 1);
        while (k < n && arr[i] == arr[j] && arr[j] == arr[k]) {
            ++i;
            ++j;
            ++k;
        }
        if (k == n) {
            return new int[]{i - 1, j};
        }
        return new int[]{-1, -1};
    }

    private int find(int[] arr, int cnt) {
        int s = 0;
        for (int i = 0; i < arr.length; ++i) {
            s += arr[i];
            if (s == cnt) {
                return i;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> threeEqualParts(vector<int>& arr) {
        int n = arr.size();
        int cnt1 = accumulate(arr.begin(), arr.end(), 0);
        int cnt = cnt1 / 3;
        int mod = cnt1 % 3;
        if (mod) return {-1, -1};
        if (cnt == 0) return {0, n - 1};
        int i = find(arr, 1);
        int j = find(arr, cnt + 1);
        int k = find(arr, cnt * 2 + 1);
        while (k < n && arr[i] == arr[j] && arr[j] == arr[k]) {
            ++i;
            ++j;
            ++k;
        }
        if (k == n) return {i - 1, j};
        return {-1, -1};
    }

    int find(vector<int>& arr, int cnt) {
        int s = 0;
        for (int i = 0; i < arr.size(); ++i) {
            s += arr[i];
            if (s == cnt) return i;
        }
        return -1;
    }
};
```

### **Go**

```go
func threeEqualParts(arr []int) []int {
	n := len(arr)
	cnt1 := 0
	for _, v := range arr {
		cnt1 += v
	}
	cnt := cnt1 / 3
	mod := cnt1 % 3
	if mod != 0 {
		return []int{-1, -1}
	}
	if cnt == 0 {
		return []int{0, n - 1}
	}
	find := func(cnt int) int {
		s := 0
		for i, v := range arr {
			s += v
			if s == cnt {
				return i
			}
		}
		return -1
	}
	i, j, k := find(1), find(cnt+1), find(cnt*2+1)
	for k < n && arr[i] == arr[j] && arr[j] == arr[k] {
		i++
		j++
		k++
	}
	if k == n {
		return []int{i - 1, j}
	}
	return []int{-1, -1}
}
```

### **...**

```

```

<!-- tabs:end -->
