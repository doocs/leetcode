# [1460. 通过翻转子数组使两个数组相等](https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-subarrays)

[English Version](/solution/1400-1499/1460.Make%20Two%20Arrays%20Equal%20by%20Reversing%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相同的整数数组&nbsp;<code>target</code>&nbsp;和&nbsp;<code>arr</code>&nbsp;。每一步中，你可以选择&nbsp;<code>arr</code>&nbsp;的任意 <strong>非空子数组</strong>&nbsp;并将它翻转。你可以执行此过程任意次。</p>

<p><em>如果你能让 <code>arr</code>&nbsp;变得与 <code>target</code>&nbsp;相同，返回 True；否则，返回 False 。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2,3,4], arr = [2,4,1,3]
<strong>输出：</strong>true
<strong>解释：</strong>你可以按照如下步骤使 arr 变成 target：
1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = [7], arr = [7]
<strong>输出：</strong>true
<strong>解释：</strong>arr 不需要做任何翻转已经与 target 相等。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = [3,7,9], arr = [3,7,11]
<strong>输出：</strong>false
<strong>解释：</strong>arr 没有数字 9 ，所以无论如何也无法变成 target 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>target.length == arr.length</code></li>
	<li><code>1 &lt;= target.length &lt;= 1000</code></li>
	<li><code>1 &lt;= target[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**前言**

由于我们可以对 $arr$ 任意非空子数组进行翻转，也就意味着我们可以交换任意两个相邻元素，使得数组按特定的一种顺序排列。

因此，题目转换为：判断一个数组是否是另一个数组的排列。

**方法一：排序**

分别对数组 $arr$ 和 $target$ 排序，然后比较两数组对应位置的元素是否相等。相等则满足条件。

时间复杂度 $O(nlogn)$，空间复杂度 $O(logn)$。其中 $n$ 是数组 $arr$ 的长度，快排的平均递归深度为 $O(logn)$。

**方法二：数组/哈希表**

由于两数组的数据范围都是 $1 \leq x \leq 1000$，因此我们可以使用数组或哈希表来记录每个数字出现的次数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是数组 $arr$ 的长度，而 $C$ 是数组 $arr$ 元素的值域大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        target.sort()
        arr.sort()
        return target == arr
```

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        return Counter(target) == Counter(arr)
```

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        cnt = [0] * 1001
        for a, b in zip(target, arr):
            cnt[a] += 1
            cnt[b] -= 1
        return all(v == 0 for v in cnt)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt1 = new int[1001];
        int[] cnt2 = new int[1001];
        for (int v : target) {
            ++cnt1[v];
        }
        for (int v : arr) {
            ++cnt2[v];
        }
        return Arrays.equals(cnt1, cnt2);
    }
}
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt = new int[1001];
        for (int v : target) {
            ++cnt[v];
        }
        for (int v : arr) {
            if (--cnt[v] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        sort(target.begin(), target.end());
        sort(arr.begin(), arr.end());
        return target == arr;
    }
};
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt1(1001);
        vector<int> cnt2(1001);
        for (int& v : target) ++cnt1[v];
        for (int& v : arr) ++cnt2[v];
        return cnt1 == cnt2;
    }
};
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt(1001);
        for (int& v : target) ++cnt[v];
        for (int& v : arr) if (--cnt[v] < 0) return false;
        return true;
    }
};
```

### **Go**

```go
func canBeEqual(target []int, arr []int) bool {
	sort.Ints(target)
	sort.Ints(arr)
	for i, v := range target {
		if v != arr[i] {
			return false
		}
	}
	return true
}
```

```go
func canBeEqual(target []int, arr []int) bool {
	cnt1 := make([]int, 1001)
	cnt2 := make([]int, 1001)
	for _, v := range target {
		cnt1[v]++
	}
	for _, v := range arr {
		cnt2[v]++
	}
	for i, v := range cnt1 {
		if v != cnt2[i] {
			return false
		}
	}
	return true
}
```

```go
func canBeEqual(target []int, arr []int) bool {
	cnt := make([]int, 1001)
	for _, v := range target {
		cnt[v]++
	}
	for _, v := range arr {
		cnt[v]--
		if cnt[v] < 0 {
			return false
		}
	}
	return true
}
```

### **C**

```c
bool canBeEqual(int* target, int targetSize, int* arr, int arrSize){
    int count[1001] = {0};
    for (int i = 0 ; i < targetSize; i++) {
        count[target[i]]++;
        count[arr[i]]--;
    }
    for (int i = 0; i < 1001; i++) {
        if (count[i] != 0) {
            return false;
        }
    }
    return true;
}
```

### **TypeScript**

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    target.sort((a, b) => a - b);
    arr.sort((a, b) => a - b);
    const n = arr.length;
    for (let i = 0; i < n; i++) {
        if (target[i] !== arr[i]) {
            return false;
        }
    }
    return true;
}
```

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    const n = target.length;
    const count = new Array(1001).fill(0);
    for (let i = 0; i < n; i++) {
        count[target[i]]++;
        count[arr[i]]--;
    }
    return count.every(v => v === 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        target.sort();
        arr.sort();
        target == arr
    }
}
```

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        let n = target.len();
        let mut count = [0; 1001];
        for i in 0..n {
            count[target[i] as usize] += 1;
            count[arr[i] as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
